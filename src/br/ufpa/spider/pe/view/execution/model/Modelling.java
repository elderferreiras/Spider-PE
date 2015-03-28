package br.ufpa.spider.pe.view.execution.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representa uma modelagem de processos. Essa classe possui um conjunto de
 * componentes e um conjunto de relacionamentos.
 */
public class Modelling {

    private static Modelling modelling;

    public static Modelling getModelling() {
        return modelling;
    }

    public static void newModelling() {
        modelling = new Modelling();
    }

    private String introduction;

    private String historic;

    private String termsAndAbbreviations;

    private String standards;

    private String overview;

    private List<ModellingVersion> versions;

    /**
     * O componente que representa o conjunto de processos instanciados.
     */
    private Component processPackage;

    /**
     * O componente que representa o processo padrão.
     */
    private Component standardProcess;

    /**
     * Os recursos humanos da modelagem mapeados pelo id.
     */
    private Map<Integer, HumanResource> humanResources;

    /**
     * Os componentes da modelagem mapeados pelo id.
     */
    private Map<Integer, Component> components;

    /**
     * Os relacionamentos da modelagem mapeados pelo id.
     */
    private Map<Integer, Relationship> relationships;

    /**
     * Os estereótipos da modelagem mapeados pelo id.
     */
    private Map<Integer, Stereotype> stereotypes;

    /**
     * Os grupos de estereótipos da modelagem mapeados pelo id.
     */
    private Map<Integer, StereotypesGroup> stereotypesGroups;

    private Modelling() {
        stereotypes = new HashMap<Integer, Stereotype>();
        stereotypesGroups = new HashMap<Integer, StereotypesGroup>();
        components = new HashMap<Integer, Component>();
        relationships = new HashMap<Integer, Relationship>();
        humanResources = new HashMap<Integer, HumanResource>();
        versions = new ArrayList<ModellingVersion>();
    }

    public Component getComponentToUpdate(Integer componentId) {
        if (componentId != null) {
            Component componentOnModeling = getComponent(componentId);
            if (componentOnModeling != null) {
                Component component = new Component();
                component.setAdditionalInformations(componentOnModeling.getAdditionalInformations());
                component.setDescription(componentOnModeling.getDescription());
                component.setDetails(componentOnModeling.getDetails());
                component.setId(componentOnModeling.getId());
                component.setName(componentOnModeling.getName());
                component.setObjectives(componentOnModeling.getObjectives());
                component.setParentId(componentOnModeling.getParentId());
                component.setTime(componentOnModeling.getTime());
                component.setType(componentOnModeling.getType());
                for (Integer stereotyopeId : componentOnModeling.getStereotypesIds()) {
                    component.getStereotypesIds().add(stereotyopeId);
                }
                if (componentOnModeling.getDiagram() != null) {
                    component.setDiagram(new Diagram());

                    for (DiagramComponent diagramComponent : componentOnModeling.getDiagram().getDiagramComponents()) {
                        DiagramComponent newDiagramComponent = new DiagramComponent();
                        newDiagramComponent.setId(diagramComponent.getId());
                        newDiagramComponent.setHeight(diagramComponent.getHeight());
                        newDiagramComponent.setWidth(diagramComponent.getWidth());
                        newDiagramComponent.setX(diagramComponent.getX());
                        newDiagramComponent.setY(diagramComponent.getY());
                        component.getDiagram().addDiagramComponent(newDiagramComponent);
                    }

                    for (DiagramRelationship diagramRelationship : componentOnModeling.getDiagram().getDiagramRelationships()) {
                        DiagramRelationship newDiagramRelationship = new DiagramRelationship();
                        newDiagramRelationship.setId(diagramRelationship.getId());
                        newDiagramRelationship.setLabelX(diagramRelationship.getLabelX());
                        newDiagramRelationship.setLabelY(diagramRelationship.getLabelY());
                        component.getDiagram().addDiagramRelationship(newDiagramRelationship);
                    }

                    for (DiagramRelationshipStereotypesGroup diagramRelationshipStereotypesGroup : componentOnModeling.getDiagram().getDiagramRelationshipStereotypesGroups()) {
                        DiagramRelationshipStereotypesGroup newDiagramRelationshipStereotypesGroup = new DiagramRelationshipStereotypesGroup();
                        newDiagramRelationshipStereotypesGroup.setId(diagramRelationshipStereotypesGroup.getId());
                        newDiagramRelationshipStereotypesGroup.setX(diagramRelationshipStereotypesGroup.getX());
                        newDiagramRelationshipStereotypesGroup.setY(diagramRelationshipStereotypesGroup.getY());
                        component.getDiagram().addDiagramRelationshipStereotypesGroup(newDiagramRelationshipStereotypesGroup);
                    }

                    for (DiagramComponentStereotypesGroup diagramComponentStereotypesGroup : componentOnModeling.getDiagram().getDiagramComponentStereotypesGroups()) {
                        DiagramComponentStereotypesGroup newDiagramComponentStereotypesGroup = new DiagramComponentStereotypesGroup();
                        newDiagramComponentStereotypesGroup.setId(diagramComponentStereotypesGroup.getId());
                        newDiagramComponentStereotypesGroup.setX(diagramComponentStereotypesGroup.getX());
                        newDiagramComponentStereotypesGroup.setY(diagramComponentStereotypesGroup.getY());
                        component.getDiagram().addDiagramComponentStereotypesGroup(newDiagramComponentStereotypesGroup);
                    }
                }
                return component;
            }
        }
        return null;
    }

    /**
     * Adiciona um componente já instanciado à modelagem. Caso o componente
     * adicionado utilize um id já utilizado uma excessão será lançada.
     *
     * @param component o componente para adicionar
     */
    public void addComponent(Component component) {
        components.put(component.getId(), component);
        if (component.getType().equals(ComponentType.STANDARD_PROCESS)) {
            standardProcess = component;
        } else if (component.getType().equals(ComponentType.PROCESS_PACKAGE)) {
            processPackage = component;
        }
    }

    public void addHumanResource(HumanResource humanResource) {

        humanResources.put(humanResource.getId(), humanResource);

    }

    public void addRelationship(Relationship relationship) {
        relationships.put(relationship.getId(), relationship);
    }

    public void addStereotype(Stereotype stereotype) {
        stereotypes.put(stereotype.getId(), stereotype);
    }

    public void addStereotypesGroup(StereotypesGroup stereotypesGroup) {
        stereotypesGroups.put(stereotypesGroup.getId(), stereotypesGroup);
    }

    /**
     * @return o componente que representa o conjunto de processos instanciados
     */
    public Component getProcessPackage() {
        return processPackage;
    }

    /**
     * @return o componente que representa o conjunto de processos instanciados
     */
    public Component getStandardProcess() {
        return standardProcess;
    }

    /**
     * @param componentId o id do componente que será retornado
     * @return o componente que possui o id especificado
     */
    public Component getComponent(Integer componentId) {
        return components.get(componentId);
    }

    /**
     * @param relationshipId o id do relacionamento que será retornado
     * @return o relacionamento que possui o id especificado
     */
    public Relationship getRelationship(Integer relationshipId) {
        return relationships.get(relationshipId);
    }

    /**
     * @param stereotypeId o id do estereótipo que será retornado
     * @return o estereótipo que possui o id especificado
     */
    public Stereotype getStereotype(Integer stereotypeId) {
        return stereotypes.get(stereotypeId);
    }

    /**
     * Retorna uma lista com todos os relacionamntos da modelagem.
     * @return a lista com os relacionamentos da modelagem
     */
    public List<Component> getComponents() {
        return new ArrayList<Component>(components.values());
    }

    public List<HumanResource> getHumanResources() {
        return new ArrayList<HumanResource>(humanResources.values());
    }

    /**
     * Retorna uma lista com todos os relacionamntos da modelagem.
     * @return a lista com os relacionamentos da modelagem
     */
    public List<Relationship> getRelationships() {
        return new ArrayList<Relationship>(relationships.values());
    }

    /**
     * @return uma lista com todos os estereótipos da modelagem
     */
    public List<Stereotype> getStereotypes() {
        return new ArrayList<Stereotype>(stereotypes.values());
    }

    /**
     * @return uma lista com todos os grupos de estereótipos da modelagem
     */
    public List<StereotypesGroup> getStereotypesGroups() {
        return new ArrayList<StereotypesGroup>(stereotypesGroups.values());
    }

    public StereotypesGroup getStereotypesGroupToUpdate(Integer stereotypesGroupId) {
        if (stereotypesGroupId != null) {
            StereotypesGroup stereotypesGroupOnModelling = getStereotypesGroups(stereotypesGroupId);
            if (stereotypesGroupOnModelling != null) {
                StereotypesGroup stereotypesGroup = new StereotypesGroup();
                stereotypesGroup.setId(stereotypesGroupOnModelling.getId());
                stereotypesGroup.setType(stereotypesGroupOnModelling.getType());
                for (Integer stereotypeId : stereotypesGroupOnModelling.getStereotypesIds()) {
                    stereotypesGroup.getStereotypesIds().add(stereotypeId);
                }
                return stereotypesGroup;
            }

        }
        return null;
    }

    public Relationship getRelationshipToUpdate(Integer relationshipId) {
        if (relationshipId != null) {
            Relationship relationshipOnModeling = getRelationship(relationshipId);
            if (relationshipOnModeling != null) {
                Relationship relationship = new Relationship();
                relationship.setSourceId(relationshipOnModeling.getSourceId());
                relationship.setDestinationId(relationshipOnModeling.getDestinationId());
                relationship.setAdditionalInformations(relationshipOnModeling.getAdditionalInformations());
                relationship.setDescription(relationshipOnModeling.getDescription());
                relationship.setId(relationshipOnModeling.getId());
                relationship.setLabel(relationshipOnModeling.getLabel());
                relationship.setObjectives(relationshipOnModeling.getObjectives());
                relationship.setType(relationshipOnModeling.getType());
                for (Integer stereotyopeId : relationshipOnModeling.getStereotypesIds()) {
                    relationship.getStereotypesIds().add(stereotyopeId);
                }
                return relationship;
            }
        }
        return null;
    }

    public Stereotype getStereotypeToUpdate(Integer id) {
        Stereotype stereotype = new Stereotype();
        Stereotype stereotypeOnModelling = getStereotype(id);
        stereotype.setId(id);
        stereotype.setName(stereotypeOnModelling.getName());
        return stereotype;
    }

    public HumanResource getHumanResource(Integer id) {
        return humanResources.get(id);
    }

    public HumanResource getHumanResourceToUpdate(Integer id) {
        HumanResource humanResourceOnModelling = humanResources.get(id);
        if (humanResourceOnModelling == null) {
            return null;
        }
        HumanResource newHumanResource = new HumanResource();
        newHumanResource.setId(humanResourceOnModelling.getId());
        newHumanResource.setName(humanResourceOnModelling.getName());
        newHumanResource.setCapabilities(humanResourceOnModelling.getCapabilities());
        return newHumanResource;
    }

    public void removeHumanResource(HumanResource humanResource) {
        humanResources.remove(humanResource.getId());
    }

    public void removeRelationship(Relationship relationship) {
        relationships.remove(relationship.getId());
    }

    public void removeComponent(Component component) {
        components.remove(component.getId());
    }

    public void removeStereotypesGroup(StereotypesGroup stereotypesGroup) {
        stereotypesGroups.remove(stereotypesGroup.getId());
    }

    public void removeStereotype(Stereotype stereotype) {
        stereotypes.remove(stereotype.getId());
    }

    public StereotypesGroup getStereotypesGroups(Integer id) {
        return stereotypesGroups.get(id);
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getHistoric() {
        return historic;
    }

    public void setHistoric(String historic) {
        this.historic = historic;
    }

    public String getTermsAndAbbreviations() {
        return termsAndAbbreviations;
    }

    public void setTermsAndAbbreviations(String termsAndAbbreviations) {
        this.termsAndAbbreviations = termsAndAbbreviations;
    }

    public String getStandards() {
        return standards;
    }

    public void setStandards(String standards) {
        this.standards = standards;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<ModellingVersion> getVersions() {
        return versions;
    }

    public void setVersions(List<ModellingVersion> versions) {
        this.versions = versions;
    }

}
