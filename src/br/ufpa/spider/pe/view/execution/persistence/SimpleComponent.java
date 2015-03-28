package br.ufpa.spider.pe.view.execution.persistence;

import java.util.List;
import br.ufpa.spider.pe.view.execution.model.ComponentType;

public class SimpleComponent {

    Integer parentId;

    String additionalInformations;

    String description;

    String details;

    ComponentType type;

    SimpleDiagram diagram;

    Float time;

    Integer id;

    String objectives;

    String name;

    String path;

    List<Integer> stereotypesIds;

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getAdditionalInformations() {
		return additionalInformations;
	}

	public void setAdditionalInformations(String additionalInformations) {
		this.additionalInformations = additionalInformations;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public ComponentType getType() {
		return type;
	}

	public void setType(ComponentType type) {
		this.type = type;
	}

	public SimpleDiagram getDiagram() {
		return diagram;
	}

	public void setDiagram(SimpleDiagram diagram) {
		this.diagram = diagram;
	}

	public Float getTime() {
		return time;
	}

	public void setTime(Float time) {
		this.time = time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getObjectives() {
		return objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<Integer> getStereotypesIds() {
		return stereotypesIds;
	}

	public void setStereotypesIds(List<Integer> stereotypesIds) {
		this.stereotypesIds = stereotypesIds;
	}

}
