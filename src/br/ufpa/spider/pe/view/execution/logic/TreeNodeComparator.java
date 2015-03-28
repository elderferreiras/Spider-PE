package br.ufpa.spider.pe.view.execution.logic;

import java.util.Comparator;

import br.ufpa.spider.pe.view.execution.gui.TreeNode;
import br.ufpa.spider.pe.view.execution.model.Component;
import br.ufpa.spider.pe.view.execution.model.ComponentType;
import br.ufpa.spider.pe.view.execution.model.Modelling;

public class TreeNodeComparator implements Comparator<Object> {

    @Override
    public int compare(Object o1, Object o2) {
        Component c1 = Modelling.getModelling().getComponent(((TreeNode) o1).getId());
        Component c2 = Modelling.getModelling().getComponent(((TreeNode) o2).getId());
        if (c1.getType() == c2.getType()) {
            return c1.getName().compareToIgnoreCase(c2.getName());
        } else {
            if (c1.getType() == ComponentType.DISCIPLINE) {
                return -1;
            } else if (c2.getType() == ComponentType.DISCIPLINE) {
                return 1;
            } else if (c1.getType() == ComponentType.PHASE) {
                return -1;
            } else if (c2.getType() == ComponentType.PHASE) {
                return 1;
            } else if (c1.getType() == ComponentType.ITERATION) {
                return -1;
            } else if (c2.getType() == ComponentType.ITERATION) {
                return 1;
            } else if (c1.getType() == ComponentType.MILESTONE) {
                return -1;
            } else if (c2.getType() == ComponentType.MILESTONE) {
                return 1;
            } else if (c1.getType() == ComponentType.ACTIVITY) {
                return -1;
            } else if (c2.getType() == ComponentType.ACTIVITY) {
                return 1;
            } else {
                return c1.getName().compareToIgnoreCase(c2.getName());
            }
        }
    }

}
