package br.ufpa.spider.pe.view.pm.gui;

import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxGraph;
import br.ufpa.spider.pe.view.pm.model.Component;
import br.ufpa.spider.pe.view.pm.model.DiagramComponent;
import br.ufpa.spider.pe.view.pm.model.DiagramComponentStereotypesGroup;
import br.ufpa.spider.pe.view.pm.model.DiagramRelationship;
import br.ufpa.spider.pe.view.pm.model.DiagramRelationshipStereotypesGroup;
import br.ufpa.spider.pe.view.pm.model.Modelling;
import br.ufpa.spider.pe.view.pm.model.Relationship;

public class Graph extends mxGraph {

    @Override
    public String getToolTipForCell(Object cell) {
        String result = null;
        mxCell c = (mxCell) cell;
        if (c.getValue().getClass() == DiagramComponent.class) {
            DiagramComponent diagramComponent = (DiagramComponent) c.getValue();
            Component component = Modelling.getModelling().getComponent(diagramComponent.getId());
            result = component.getType().toStringInPortuguese();
            if (component.getName() != null && !component.getName().equalsIgnoreCase("")) {
                result += ": " + component.getName();
            }
        } else if (c.getValue().getClass() == DiagramRelationship.class) {
            DiagramRelationship diagramRelationship = (DiagramRelationship) c.getValue();
            Relationship relationship = Modelling.getModelling().getRelationship(diagramRelationship.getId());
            result = relationship.getType().toStringInPortuguese();
            if (relationship.getLabel() != null && !relationship.getLabel().equalsIgnoreCase("")) {
                result += ": " + relationship.getLabel();
            }
        } else if (c.getValue().getClass() == DiagramComponentStereotypesGroup.class) {
            DiagramComponentStereotypesGroup diagramComponentStereotypesGroup = (DiagramComponentStereotypesGroup) c.getValue();
            Component component = Modelling.getModelling().getComponent(diagramComponentStereotypesGroup.getId());
            result = component.getType().toStringInPortuguese();
            if (component.getName() != null && !component.getName().equalsIgnoreCase("")) {
                result += ": " + component.getName();
            }
        } else if (c.getValue().getClass() == DiagramRelationshipStereotypesGroup.class) {
            DiagramRelationshipStereotypesGroup diagramRelationshipStereotypesGroup = (DiagramRelationshipStereotypesGroup) c.getValue();
            Relationship relationship = Modelling.getModelling().getRelationship(diagramRelationshipStereotypesGroup.getId());
            result = relationship.getType().toStringInPortuguese();
            if (relationship.getLabel() != null && !relationship.getLabel().equalsIgnoreCase("")) {
                result += ": " + relationship.getLabel();
            }
        }
        return result;
    }
}
