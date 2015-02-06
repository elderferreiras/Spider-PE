package br.ufpa.spider.pe.view.pm.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Um relacionamento da modelagem. Indica uma relação entre dois componentes da
 * modelagem.
 */
public class Relationship {

    
    /**
     * As informações adicionais do componente. Pode ser nulo.
     */
    private String additionalInformations;

    /**
     * A descrição do componente. Pode ser nulo.
     */
    private String description;

    /**
     * Os objetivos do componente.
     */
    private String objectives;

    /**
     * O id do relacionamento.
     */
    private Integer id;

    /**
     * O tipo do relaciomento.
     */
    private RelationshipType type;

    /**
     * O id do componente que é a origem do relacionamento.
     */
    private Integer sourceId;

    /**
     * O id do componente que é o destino do relacionamento.
     */
    private Integer destinationId;

    /**
     * O rótulo do relacionamento.
     */
    private String label;

    /**
     * Indica os estereótipos atribuídos ao relacionamento.
     */
    private List<Integer> stereotypesIds;

    public Relationship() {
        stereotypesIds = new ArrayList<Integer>();
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public void setType(RelationshipType type) {
        this.type = type;
    }

    public String getObjectives() {
        return objectives;
    }

    public String getAdditionalInformations() {
        return additionalInformations;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAdditionalInformations(String additionalInformations) {
        this.additionalInformations = additionalInformations;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    /**
     * @return o id do relacionamento
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return o id do componente que é o destino do relacionamento
     */
    public Integer getDestinationId() {
        return destinationId;
    }

    /**
     * @return o id do componente que é a origem do relacionamento
     */
    public Integer getSourceId() {
        return sourceId;
    }

    /**
     * @return o tipo do relacionamento
     */
    public RelationshipType getType() {
        return type;
    }

    /**
     * @return o rótulo do relacionamento
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label o rótulo para atribuir ao relacionamento
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Retorna uma lista com os ids dos estereótipos atribuídos ao
     * relacionamento.
     * @return a lista com o s ids dos estereótipos
     */
    public List<Integer> getStereotypesIds() {
        return stereotypesIds;
    }

}
