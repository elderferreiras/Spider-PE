package br.ufpa.spider.pe.view.execution.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que representa um diagrama da modelagem. Essa classe é formada por um
 * conjunto de componentes e um conjunto de relacionamentos.
 *
 * @see br.ufpa.spider.pe.view.pm.model.DiagramComponent
 * @see br.ufpa.spider.pe.view.pm.model.DiagramRelationship
 */
public class Diagram {

    /**
     * Os componentes do diagrama mapeados pelo id.
     */
    private Map<Integer, DiagramComponent> components;

    /**
     * Os relacionamentos do diagrama mapeados pelo id.
     */
    private Map<Integer, DiagramRelationship> relationships;

    private Map<Integer, DiagramComponentStereotypesGroup> componentStereotypesGroups;

        private Map<Integer, DiagramRelationshipStereotypesGroup> relationshipStereotypesGroups;

    /**
     * Único construtor da classe.
     */
    public Diagram() {
        components = new HashMap<Integer, DiagramComponent>();
        relationships = new HashMap<Integer, DiagramRelationship>();
        componentStereotypesGroups = new HashMap<Integer, DiagramComponentStereotypesGroup>();
        relationshipStereotypesGroups = new HashMap<Integer, DiagramRelationshipStereotypesGroup>();
    }

    /**
     * @param diagramComponent adiciona um componente ao diagrama
     */
    public void addDiagramComponent(DiagramComponent diagramComponent) {
        components.put(diagramComponent.getId(), diagramComponent);
    }

    public void addDiagramComponentStereotypesGroup(DiagramComponentStereotypesGroup componentStereotypesGroup) {
        componentStereotypesGroups.put(componentStereotypesGroup.getId(), componentStereotypesGroup);
    }

    public DiagramComponentStereotypesGroup getDiagramComponentStereotypesGroup(int componentId) {
        return componentStereotypesGroups.get(componentId);
    }

    public List<DiagramComponentStereotypesGroup> getDiagramComponentStereotypesGroups() {
        return new ArrayList<DiagramComponentStereotypesGroup>(componentStereotypesGroups.values());
    }

     public void addDiagramRelationshipStereotypesGroup(DiagramRelationshipStereotypesGroup relationshipStereotypesGroup) {
        relationshipStereotypesGroups.put(relationshipStereotypesGroup.getId(), relationshipStereotypesGroup);
    }

    public DiagramRelationshipStereotypesGroup getDiagramRelationshipStereotypesGroup(int relationshipId) {
        return relationshipStereotypesGroups.get(relationshipId);
    }

    public List<DiagramRelationshipStereotypesGroup> getDiagramRelationshipStereotypesGroups() {
        return new ArrayList<DiagramRelationshipStereotypesGroup>(relationshipStereotypesGroups.values());
    }

    /**
     * @param relationshipId - o id do componente para ser retornado
     * @return o componente do diagrama com o id informado.
     */
    public DiagramComponent getDiagramComponent(int componentId) {
        return components.get(componentId);
    }

    /**
     * @param diagramRelationship o relacionamento que para ser adicionado ao
     * diagrama
     */
    public void addDiagramRelationship(DiagramRelationship diagramRelationship) {
        relationships.put(diagramRelationship.getId(),
                          diagramRelationship);
    }

    public void removeDiagramRelationship(Integer relationshipId) {
        relationships.remove(relationshipId);
        relationshipStereotypesGroups.remove(relationshipId);
    }

    public void removeComponentStereotypesGroup(Integer componentId) {
        componentStereotypesGroups.remove(componentId);
    }

    public void removeRelationshipStereotypesGroup(Integer relationshipId) {
        relationshipStereotypesGroups.remove(relationshipId);
    }


    public void removeDiagramComponent(Integer componentId) {
        components.remove(componentId);
        componentStereotypesGroups.remove(componentId);
    }

    /**
     * @param relationshipId o id do relacionamento para ser retornado
     * @return o relacionamento com o id especificado
     */
    public DiagramRelationship getDiagramRelationship(int relationshipId) {
        return relationships.get(relationshipId);
    }

    /**
     * @return uma lista com todos os componentes do diagrama
     */
    public List<DiagramComponent> getDiagramComponents() {
        return new ArrayList<DiagramComponent>(components.values());
    }

    /**
     * @return uma lista com todos os relacionamentos do diagrama
     */
    public List<DiagramRelationship> getDiagramRelationships() {
        return new ArrayList<DiagramRelationship>(relationships.values());
    }

}
