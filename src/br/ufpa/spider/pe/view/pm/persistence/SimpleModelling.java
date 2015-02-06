package br.ufpa.spider.pe.view.pm.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.view.pm.logic.ModellingController;
import br.ufpa.spider.pe.view.pm.model.Component;
import br.ufpa.spider.pe.view.pm.model.ComponentType;
import br.ufpa.spider.pe.view.pm.model.Diagram;
import br.ufpa.spider.pe.view.pm.model.DiagramComponent;
import br.ufpa.spider.pe.view.pm.model.DiagramComponentStereotypesGroup;
import br.ufpa.spider.pe.view.pm.model.DiagramRelationship;
import br.ufpa.spider.pe.view.pm.model.DiagramRelationshipStereotypesGroup;
import br.ufpa.spider.pe.view.pm.model.HumanResource;
import br.ufpa.spider.pe.view.pm.model.Modelling;
import br.ufpa.spider.pe.view.pm.model.ModellingVersion;
import br.ufpa.spider.pe.view.pm.model.Relationship;
import br.ufpa.spider.pe.view.pm.model.Stereotype;
import br.ufpa.spider.pe.view.pm.model.StereotypesGroup;

public class SimpleModelling {

    private String introduction;

    private String historic;

    private String termsAndAbbreviations;

    private String standards;

    private String overview;

    List<ModellingVersion> versions;

    public List<ModellingVersion> getVersions() {
		return versions;
	}
	public void setVersions(List<ModellingVersion> versions) {
		this.versions = versions;
	}
	public List<HumanResource> getHumansResources() {
		return humansResources;
	}
	public void setHumansResources(List<HumanResource> humansResources) {
		this.humansResources = humansResources;
	}
	public List<Stereotype> getStereotypes() {
		return stereotypes;
	}
	public void setStereotypes(List<Stereotype> stereotypes) {
		this.stereotypes = stereotypes;
	}
	public List<StereotypesGroup> getStereotypesGroups() {
		return stereotypesGroups;
	}
	public void setStereotypesGroups(List<StereotypesGroup> stereotypesGroups) {
		this.stereotypesGroups = stereotypesGroups;
	}
	public List<SimpleComponent> getComponents() {
		return components;
	}
	public void setComponents(List<SimpleComponent> components) {
		this.components = components;
	}
	public List<Relationship> getRelationships() {
		return relationships;
	}
	public void setRelationships(List<Relationship> relationships) {
		this.relationships = relationships;
	}

	List<HumanResource> humansResources;

    List<Stereotype> stereotypes;

    List<StereotypesGroup> stereotypesGroups;

    List<SimpleComponent> components;

    List<Relationship> relationships;

    void fill() {
        introduction = Modelling.getModelling().getIntroduction();
        historic = Modelling.getModelling().getHistoric();
        termsAndAbbreviations = Modelling.getModelling().getTermsAndAbbreviations();
        standards = Modelling.getModelling().getStandards();
        overview = Modelling.getModelling().getOverview();
        versions = Modelling.getModelling().getVersions();
        stereotypes = Modelling.getModelling().getStereotypes();
        humansResources = Modelling.getModelling().getHumanResources(); // aqui
        relationships = Modelling.getModelling().getRelationships(); //aqui
        stereotypesGroups = Modelling.getModelling().getStereotypesGroups();
        components = new ArrayList<SimpleComponent>();
        for (Component component : Modelling.getModelling().getComponents()) { //aqui
            SimpleComponent simpleComponent = new SimpleComponent();
            simpleComponent.additionalInformations = component.getAdditionalInformations();
            simpleComponent.description = component.getDescription();
            simpleComponent.details = component.getDetails();
            simpleComponent.id = component.getId();
            simpleComponent.name = component.getName();
            simpleComponent.objectives = component.getObjectives();
            simpleComponent.parentId = component.getParentId();
            simpleComponent.stereotypesIds = component.getStereotypesIds();
            simpleComponent.time = component.getTime();
            simpleComponent.type = component.getType();
            if (component.getDiagram() != null) {
                simpleComponent.diagram = new SimpleDiagram();
                simpleComponent.diagram.diagramComponentStereotypesGroups = component.getDiagram().getDiagramComponentStereotypesGroups();
                simpleComponent.diagram.diagramComponents = component.getDiagram().getDiagramComponents();
                simpleComponent.diagram.diagramRelationshipStereotypesGroups = component.getDiagram().getDiagramRelationshipStereotypesGroups();
                simpleComponent.diagram.diagramRelationships = component.getDiagram().getDiagramRelationships();
            }
            components.add(simpleComponent);
        }
    }
  //* MÉTODO ABAIXO CRIADO PARA FILTRAGEM DE PROCESSOS INSTANCIADOS NA SPIDER-PE*//
    public SimpleModelling fill(int idProcess) {
    	SimpleModelling simpleModelling = new SimpleModelling();
    	simpleModelling.setVersions(this.getVersions());
    	simpleModelling.setHumansResources(this.getHumansResources());
    	simpleModelling.setStereotypes(getStereotypes());
    	simpleModelling.setStereotypesGroups(getStereotypesGroups());
    	List<Relationship> relations = relationshipsRefactore(idProcess);
    	simpleModelling.setRelationships(relations);
        List<SimpleComponent> components = new ArrayList<SimpleComponent>();
        for (SimpleComponent component : getComponents()) { //aqui, status: em andamento
        	if(component.getType() == ComponentType.PROCESS_PACKAGE){
        		component = processPackageRefactore(idProcess, component, relations);
        	} 
        	if(componentIsAssociatedProcess(idProcess, component)){
        		SimpleComponent simpleComponent = new SimpleComponent();
	            simpleComponent.additionalInformations = component.getAdditionalInformations();
	            simpleComponent.description = component.getDescription();
	            simpleComponent.details = component.getDetails();
	            simpleComponent.id = component.getId();
	            simpleComponent.name = component.getName();
	            simpleComponent.objectives = component.getObjectives();
	            simpleComponent.parentId = component.getParentId();
	            simpleComponent.stereotypesIds = component.getStereotypesIds();
	            simpleComponent.time = component.getTime();
	            simpleComponent.type = component.getType();
	            if (component.getDiagram() != null) {
	                simpleComponent.diagram = new SimpleDiagram();
	                simpleComponent.diagram.diagramComponentStereotypesGroups = component.getDiagram().getDiagramComponentStereotypesGroups();
	                simpleComponent.diagram.diagramComponents = component.getDiagram().getDiagramComponents();
	                simpleComponent.diagram.diagramRelationshipStereotypesGroups = component.getDiagram().getDiagramRelationshipStereotypesGroups();
	                simpleComponent.diagram.diagramRelationships = component.getDiagram().getDiagramRelationships();
	            }
	            components.add(simpleComponent);
        	}
        }
        simpleModelling.setComponents(components);
        return simpleModelling;
    }
  //* MÉTODO ABAIXO CRIADO PARA FILTRAGEM DE PROCESSOS INSTANCIADOS NA SPIDER-PE*//
    private List<Relationship> relationshipsRefactore(int idProcess) {
    List<Relationship> rs = new ArrayList<Relationship>();
    int cont = 0;
	for(Relationship r: getRelationships()){
		SimpleComponent source = getComponentById(r.getSourceId());
		SimpleComponent destination = getComponentById(r.getDestinationId());
		if(componentIsAssociatedProcess(idProcess, source) && componentIsAssociatedProcess(idProcess, destination)){
			rs.add(r);
		} else if(componentIsAssociatedProcess(idProcess, source) && source.getType() == ComponentType.NOTE){
			rs.add(r);
		} else if(componentIsAssociatedProcess(idProcess, destination) && destination.getType() == ComponentType.NOTE){
			rs.add(r);
		}
		
	}
	return rs;
}
	private SimpleComponent getComponentById(Integer id) {
		for (SimpleComponent component2 : getComponents()){
			if(component2.getId().equals(id)){
				return component2;
			}
		}
	return null;
}
	//* MÉTODO ABAIXO CRIADO PARA FILTRAGEM DE PROCESSOS INSTANCIADOS NA SPIDER-PE*//
	private SimpleComponent processPackageRefactore(int idProcess, SimpleComponent component, List<Relationship> relationships) {
		SimpleComponent processPackage = component;
		List<DiagramRelationship> diagramsRelationship = component.getDiagram().getDiagramRelationships();
		List<DiagramRelationship> newDiagramRelationship = new ArrayList<DiagramRelationship>();
		for (DiagramRelationship diagramRelationship : diagramsRelationship) {
			for (Relationship r : relationships) {
				if(diagramRelationship.getId().equals(r.getId())){
					newDiagramRelationship.add(diagramRelationship);
				}
			}
		}
		processPackage.getDiagram().setDiagramRelationships(newDiagramRelationship);
		/* diagram component */
		List<DiagramComponent> diagramsComponent = component.getDiagram().getDiagramComponents();
		List<DiagramComponent> newDiagramComponent = new ArrayList<DiagramComponent>();
		for (DiagramComponent diagramComponent : diagramsComponent) {
			SimpleComponent comp = getComponentById(diagramComponent.getId());
			if(componentIsAssociatedProcess(idProcess, comp)){
				newDiagramComponent.add(diagramComponent);
			}
		}
		processPackage.getDiagram().setDiagramComponents(newDiagramComponent);
		return processPackage;
	}
	//* MÉTODO ABAIXO CRIADO PARA FILTRAGEM DE PROCESSOS INSTANCIADOS NA SPIDER-PE*//
	private boolean componentIsAssociatedProcess(int idProcess, SimpleComponent component) {
		if(component.getType() == ComponentType.PROCESS_PACKAGE || component.getType() == ComponentType.STANDARD_PROCESS){
			return true;
		} else if(component.getParentId() == idProcess || component.getId() == idProcess){
			return true;
		} else {
			SimpleComponent pai = getComponentById(component.getParentId());
			return componentIsAssociatedProcessInternally(idProcess, pai);
		}
	}

	private boolean componentIsAssociatedProcessInternally(int idProcess, SimpleComponent component) {
		if(component.getType() == ComponentType.STANDARD_PROCESS){
			return true;
		}
		if(component.getParentId() == null) {
			return false;
		}
		if(component.getParentId() == idProcess || component.getType() == ComponentType.STANDARD_PROCESS){
			return true;
		} else {
			SimpleComponent pai = getComponentById(component.getParentId());
			return componentIsAssociatedProcessInternally(idProcess, pai);
		}
	}
	private void flushComponent(SimpleComponent simpleComponent) {
        Component component = new Component();
        component.setAdditionalInformations(simpleComponent.additionalInformations);
        component.setDescription(simpleComponent.description);
        component.setDetails(simpleComponent.details);
        component.setId(simpleComponent.id);
        component.setName(simpleComponent.name);
        component.setObjectives(simpleComponent.objectives);
        component.setParentId(simpleComponent.parentId);
        component.setStereotypesIds(simpleComponent.stereotypesIds);
        component.setTime(simpleComponent.time);
        component.setType(simpleComponent.type);
        ModellingController.commitComponent(component);
        for (SimpleComponent simpleComponentFromList : components) {
            if (simpleComponentFromList.parentId != null
                && simpleComponentFromList.parentId.equals(simpleComponent.id)) {
                flushComponent(simpleComponentFromList);
            }
        }
    }

    void flush() {
        Modelling.getModelling().setIntroduction(introduction);
        Modelling.getModelling().setHistoric(historic);
        Modelling.getModelling().setStandards(standards);
        Modelling.getModelling().setTermsAndAbbreviations(termsAndAbbreviations);
        Modelling.getModelling().setOverview(overview);
        Modelling.getModelling().setVersions(versions);
        for (Stereotype stereotype : stereotypes) {
            ModellingController.commitStereotype(stereotype);
        }
        for (StereotypesGroup stereotypesGroup : stereotypesGroups) {
            ModellingController.commitStereotypesGroup(stereotypesGroup);
        }
        for (HumanResource humanResource : humansResources) {
            ModellingController.commitHumanResource(humanResource);
        }
        for (SimpleComponent simpleComponent : components) {
            if (simpleComponent.parentId == null) {
                flushComponent(simpleComponent);
            }
        }
        for (Relationship relationship : relationships) {
            ModellingController.commitRelationship(relationship);
        }
        for (SimpleComponent simpleComponent : components) {
            Component component = Modelling.getModelling().getComponent(simpleComponent.id);
            if (simpleComponent.diagram != null) {

                component.setDiagram(new Diagram());
                for (DiagramComponentStereotypesGroup diagramComponentStereotypesGroup : simpleComponent.diagram.diagramComponentStereotypesGroups) {
                    component.getDiagram().addDiagramComponentStereotypesGroup(diagramComponentStereotypesGroup);
                }
                for (DiagramComponent diagramComponent : simpleComponent.diagram.diagramComponents) {
                    component.getDiagram().addDiagramComponent(diagramComponent);
                }
                for (DiagramRelationshipStereotypesGroup diagramRelationshipStereotypesGroup : simpleComponent.diagram.diagramRelationshipStereotypesGroups) {
                    component.getDiagram().addDiagramRelationshipStereotypesGroup(diagramRelationshipStereotypesGroup);
                }
                for (DiagramRelationship diagramRelationship : simpleComponent.diagram.diagramRelationships) {
                    component.getDiagram().addDiagramRelationship(diagramRelationship);
                }
            }
            ModellingController.commitComponent(component);
        }
    }

}
