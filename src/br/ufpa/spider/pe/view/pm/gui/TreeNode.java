package br.ufpa.spider.pe.view.pm.gui;

import javax.swing.tree.DefaultMutableTreeNode;
import br.ufpa.spider.pe.view.pm.model.Modelling;

public class TreeNode extends DefaultMutableTreeNode {

    private int id;

    public int getId() {
        return id;
    }

    public TreeNode(int id) {
        super(Modelling.getModelling().getComponent(id), true);
        this.id = id;
    }

}
