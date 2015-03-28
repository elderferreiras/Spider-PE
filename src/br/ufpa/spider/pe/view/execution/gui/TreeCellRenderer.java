package br.ufpa.spider.pe.view.execution.gui;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.Application;

import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.view.execution.logic.ComponentImageFile;
import br.ufpa.spider.pe.view.execution.logic.ExecutionController;
import br.ufpa.spider.pe.view.execution.logic.FindByProcess;
import br.ufpa.spider.pe.view.execution.model.ComponentType;
import br.ufpa.spider.pe.view.execution.model.Modelling;

public class TreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean sel, boolean expanded,
                                                  boolean leaf, int row,
                                                  boolean hasFocus) {
        super.getTreeCellRendererComponent(tree,
                                           value,
                                           sel,
                                           expanded,
                                           leaf,
                                           row,
                                           hasFocus);
        ResourceMap resourceMap = Application.getInstance(
                br.ufpa.spider.pe.view.execution.gui.Application.class).getContext().
                getResourceMap(Spider_PE_Execution.class);
        TreeNode treeNode = null;
        try {
            treeNode = (TreeNode) value;
        } catch (Exception e) {
            return this;
        }
        ComponentType componentType = Modelling.getModelling().
                getComponent(treeNode.getId()).getType();
        if (componentType.equals(ComponentType.TASK_DEFINITION)) {
        	setIcon(new ImageIcon(ImageIcon.class.getResource(ComponentImageFile.TASK_DEFINITION)));
        } else if (componentType.equals(ComponentType.TASK_USE)) {
        	setIcon(new ImageIcon(ImageIcon.class.getResource(ExecutionController.getEstadoTarefa(Modelling.getModelling().getComponent(treeNode.getId())))));            
        } else if (componentType.equals(ComponentType.TASK_USE)) { 
        	setIcon(new ImageIcon(ImageIcon.class.getResource(ExecutionController.getEstadoTarefa(Modelling.getModelling().getComponent(treeNode.getId())))));
        } if (componentType.equals(ComponentType.ACTIVITY)) {
        	setIcon(new ImageIcon(ImageIcon.class.getResource(ExecutionController.getEstadoAtividade(Modelling.getModelling().getComponent(treeNode.getId())))));
        } else if (componentType.equals(ComponentType.DECISION_AND_MERGE)) {
        	 setIcon(new ImageIcon(ImageIcon.class.getResource(ComponentImageFile.DECISION_AND_MERGE)));
        } else if (componentType.equals(ComponentType.DISCIPLINE)) {
        	 setIcon(new ImageIcon(ImageIcon.class.getResource(ComponentImageFile.DISCIPLINE)));
        } else if (componentType.equals(ComponentType.FINAL_STATE)) {
        	 setIcon(new ImageIcon(ImageIcon.class.getResource(ComponentImageFile.FINAL_STATE)));
        } else if (componentType.equals(ComponentType.FORK)) {
        	 setIcon(new ImageIcon(ImageIcon.class.getResource(ComponentImageFile.FORK)));
        } else if (componentType.equals(ComponentType.GUIDANCE)) {
        	setIcon(new ImageIcon(ImageIcon.class.getResource(ComponentImageFile.GUIDANCE)));
        } else if (componentType.equals(ComponentType.GUIDANCE_SET)) {
        	setIcon(new ImageIcon(ImageIcon.class.getResource(ComponentImageFile.GUIDANCE_SET)));
        } else if (componentType.equals(ComponentType.INITIAL_STATE)) {
        	setIcon(new ImageIcon(ImageIcon.class.getResource(ComponentImageFile.INITIAL_STATE)));
        } else if (componentType.equals(ComponentType.ITERATION)) {
        	setIcon(new ImageIcon(ImageIcon.class.getResource(ExecutionController.getEstadoIteracao(Modelling.getModelling().getComponent(treeNode.getId())))));
        } else if (componentType.equals(ComponentType.JOIN)) {
        	setIcon(new ImageIcon(ImageIcon.class.getResource(ComponentImageFile.JOIN)));
        } else if (componentType.equals(ComponentType.MILESTONE)) {
        	setIcon(new ImageIcon(ImageIcon.class.getResource(ExecutionController.getEstadoMarco(Modelling.getModelling().getComponent(treeNode.getId())))));
        } else if (componentType.equals(ComponentType.NOTE)) {
         	setIcon(new ImageIcon(ImageIcon.class.getResource(ComponentImageFile.NOTE)));
        } else if (componentType.equals(ComponentType.PHASE)) {
        	setIcon(new ImageIcon(ImageIcon.class.getResource(ExecutionController.getEstadoFase(Modelling.getModelling().getComponent(treeNode.getId())))));
        } else if (componentType.equals(ComponentType.PROCESS)) {
        	setIcon(new ImageIcon(ImageIcon.class.getResource(ExecutionController.getEstadoProcesso(Modelling.getModelling().getComponent(treeNode.getId())))));
        } else if (componentType.equals(ComponentType.PROCESS_PACKAGE)) {
        	setIcon(new ImageIcon(ImageIcon.class.getResource(ComponentImageFile.PROCESS_PACKAGE)));
        } else if (componentType.equals(ComponentType.ROLE_DEFINITION)) {
        	setIcon(new ImageIcon(ImageIcon.class.getResource(ComponentImageFile.ROLE_DEFINITION)));
        } else if (componentType.equals(ComponentType.ROLE_SET)) {
        	setIcon(new ImageIcon(ImageIcon.class.getResource(ComponentImageFile.ROLE_SET)));
        } else if (componentType.equals(ComponentType.ROLE_USE)) {
        	setIcon(new ImageIcon(ImageIcon.class.getResource(ComponentImageFile.ROLE_USE)));
        } else if (componentType.equals(ComponentType.STANDARD_PROCESS)) {
        	setIcon(new ImageIcon(ImageIcon.class.getResource(ComponentImageFile.STANDARD_PROCESS)));
        } else if (componentType.equals(ComponentType.TOOL_DEFINITION)) {
        	setIcon(new ImageIcon(ImageIcon.class.getResource(ComponentImageFile.TOOL_DEFINITION)));
        } else if (componentType.equals(ComponentType.TOOL_SET)) {
        	setIcon(new ImageIcon(ImageIcon.class.getResource(ComponentImageFile.TOOL_SET)));
        } else if (componentType.equals(ComponentType.TOOL_USE)) {
        	setIcon(new ImageIcon(ImageIcon.class.getResource(ComponentImageFile.TOOL_USE)));
        } else if (componentType.equals(ComponentType.WORK_PRODUCT_DEFINITION)) {
        	 setIcon(new ImageIcon(ImageIcon.class.getResource(ComponentImageFile.WORK_PRODUCT_DEFINITION)));
        } else if (componentType.equals(ComponentType.WORK_PRODUCT_SET)) {
        	 setIcon(new ImageIcon(ImageIcon.class.getResource(ComponentImageFile.WORK_PRODUCT_SET)));
        } else if (componentType.equals(ComponentType.WORK_PRODUCT_USE)) {
        	 setIcon(new ImageIcon(ImageIcon.class.getResource(ComponentImageFile.WORK_PRODUCT_USE)));
        }
        return this;
    }

}
