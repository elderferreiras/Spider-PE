package br.ufpa.spider.pe.view.pm.gui;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxRectangle;
import java.awt.Point;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import javax.swing.JTree;
import javax.swing.tree.TreePath;
import br.ufpa.spider.pe.view.pm.logic.ModellingController;
import br.ufpa.spider.pe.view.pm.model.ComponentType;
import br.ufpa.spider.pe.view.pm.model.Modelling;

public class StandardProcessTreeDragGestureListener implements DragGestureListener {

    @Override
    public void dragGestureRecognized(DragGestureEvent dragGestureEvent) {
        JTree tree = (JTree) dragGestureEvent.getComponent();
        TreePath path = tree.getSelectionPath();
        if (path != null) {
            TreeNode treeNode = (TreeNode) path.getLastPathComponent();
            ComponentType componentType = Modelling.getModelling().getComponent(treeNode.getId()).getType();
            if (componentType == ComponentType.GUIDANCE
                || componentType == ComponentType.TASK_DEFINITION
                || componentType == ComponentType.TOOL_DEFINITION
                || componentType == ComponentType.WORK_PRODUCT_DEFINITION
                || componentType == ComponentType.ROLE_DEFINITION) {
                mxCell cell = ModellingController.getCellToDrop(treeNode.getId());
                mxRectangle bounds = (mxGeometry) cell.getGeometry().clone();
                final mxGraphTransferable graphTransferable = new mxGraphTransferable(new Object[] {cell}, bounds);
                try {
                    dragGestureEvent.startDrag(null, mxConstants.EMPTY_IMAGE, new Point(), graphTransferable, null);
                } catch (Exception e) {
                }
            }
        }
    }

}
