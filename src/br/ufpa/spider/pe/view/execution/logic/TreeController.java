package br.ufpa.spider.pe.view.execution.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import br.ufpa.spider.pe.view.execution.gui.TreeNode;
import br.ufpa.spider.pe.view.execution.model.Component;
import br.ufpa.spider.pe.view.execution.model.ComponentType;

class TreeController {

    static JTree processPackageTree;
    private static Map<Integer, TreeNode> treeNodes;

    private TreeController() {
    }

    static void add(Component component) {
        TreeNode treeNode = new TreeNode(component.getId());
        add(treeNode);
        if (component.getParentId() == null) {
            if (component.getType() == ComponentType.PROCESS_PACKAGE) {
                processPackageTree.setModel(new DefaultTreeModel(treeNode, true));
            } else {
                return;
            }
        } else if (component.getType() == ComponentType.DECISION_AND_MERGE
                       || component.getType() == ComponentType.FINAL_STATE
                       || component.getType() == ComponentType.FORK
                       || component.getType() == ComponentType.INITIAL_STATE
                       || component.getType() == ComponentType.JOIN
                       || component.getType() == ComponentType.NOTE) {
            return;
        } else {
            get(component.getParentId()).add(treeNode);
        }
        update();
    }

    private static void sort() {
        DefaultTreeModel defaultTreeModel;
        javax.swing.tree.TreeNode root = null;
        
        defaultTreeModel = (DefaultTreeModel) processPackageTree.getModel();
        if (defaultTreeModel != null) {
            root = (javax.swing.tree.TreeNode) defaultTreeModel.getRoot();
            if (root != null) {
                sortChildren(root);
            }
        }
    }

    private static void sortChildren(javax.swing.tree.TreeNode treeNode) {
        List<javax.swing.tree.TreeNode> children = new ArrayList<javax.swing.tree.TreeNode>();
        for (int i = 0; i < treeNode.getChildCount(); ++i) {
            children.add(treeNode.getChildAt(i));
            sortChildren(treeNode.getChildAt(i));
        }
        Collections.sort(children, new TreeNodeComparator());
        ((DefaultMutableTreeNode) treeNode).removeAllChildren();
        for (javax.swing.tree.TreeNode child : children) {
            ((DefaultMutableTreeNode) treeNode).add((DefaultMutableTreeNode) child);
        }
    }

    static void update() {
        sort();
        processPackageTree.updateUI();
    }

    static void setProcessPackageTree(JTree processPackageTree) {
        TreeController.processPackageTree = processPackageTree;
    }

    static void initialize() {
        treeNodes = new HashMap<Integer, TreeNode>();
        processPackageTree.setModel(null);
        processPackageTree.setRowHeight(35);
        update();
    }

    private static void add(TreeNode treeNode) {
        treeNodes.put(treeNode.getId(), treeNode);
    }

    static void remove(int id) {
        treeNodes.remove(id).removeFromParent();
        update();
    }

    static TreeNode get(int id) {
        return treeNodes.get(id);
    }

}
