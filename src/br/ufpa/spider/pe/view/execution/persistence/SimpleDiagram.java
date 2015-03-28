package br.ufpa.spider.pe.view.execution.persistence;

import java.util.List;
import br.ufpa.spider.pe.view.execution.model.DiagramComponent;
import br.ufpa.spider.pe.view.execution.model.DiagramComponentStereotypesGroup;
import br.ufpa.spider.pe.view.execution.model.DiagramRelationship;
import br.ufpa.spider.pe.view.execution.model.DiagramRelationshipStereotypesGroup;

public class SimpleDiagram {

    List<DiagramComponent> diagramComponents;

    List<DiagramComponentStereotypesGroup> diagramComponentStereotypesGroups;

    List<DiagramRelationship> diagramRelationships;

    List<DiagramRelationshipStereotypesGroup> diagramRelationshipStereotypesGroups;

	public List<DiagramComponent> getDiagramComponents() {
		return diagramComponents;
	}

	public void setDiagramComponents(List<DiagramComponent> diagramComponents) {
		this.diagramComponents = diagramComponents;
	}

	public List<DiagramComponentStereotypesGroup> getDiagramComponentStereotypesGroups() {
		return diagramComponentStereotypesGroups;
	}

	public void setDiagramComponentStereotypesGroups(
			List<DiagramComponentStereotypesGroup> diagramComponentStereotypesGroups) {
		this.diagramComponentStereotypesGroups = diagramComponentStereotypesGroups;
	}

	public List<DiagramRelationship> getDiagramRelationships() {
		return diagramRelationships;
	}

	public void setDiagramRelationships(
			List<DiagramRelationship> diagramRelationships) {
		this.diagramRelationships = diagramRelationships;
	}

	public List<DiagramRelationshipStereotypesGroup> getDiagramRelationshipStereotypesGroups() {
		return diagramRelationshipStereotypesGroups;
	}

	public void setDiagramRelationshipStereotypesGroups(
			List<DiagramRelationshipStereotypesGroup> diagramRelationshipStereotypesGroups) {
		this.diagramRelationshipStereotypesGroups = diagramRelationshipStereotypesGroups;
	}

}
