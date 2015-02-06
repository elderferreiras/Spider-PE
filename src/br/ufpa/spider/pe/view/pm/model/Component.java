package br.ufpa.spider.pe.view.pm.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um componente da modelagem. Essa classe guarda todas as
 * informações necessárias para representar qualquer componente utilizado na
 * modelagem de processos.
 */
public class Component {

    
    /**
     * O tipo do componente. Não pode ser nulo.
     */
    private ComponentType type;

    /**
     * O diagrama que descreve o componente. Pode ser nulo.
     */
    private Diagram diagram;

    /**
     * Caso o componente representado seja algum tipo de trabalho do processo
     * intanciado esse valor representa o percentual de tempo do projeto que
     * esse trabalho consome.
     */
    private Float time;

    /**
     * O identificador do componente. Deve ser único e não nulo.
     */
    private Integer id;

    

    /**
     * O component pai do componente atual. Pode ser nulo somente quando o
     * component for do tipo processo padrão e conjunto de processos
     * instanciados.
     */
    private Integer parentId;

    

    /**
     * As informações adicionais do componente. Pode ser nulo.
     */
    private String additionalInformations;

    /**
     * A descrição do componente. Pode ser nulo.
     */
    private String description;

    /**
     * Dependendo do componente, esse atributo pode guardar informações de
     * naturezas diferentes. Por exemplo, quando o tipo do componente for
     * definição de tarefa esse atributo guarda os passos da tarefa, quando o
     * tipo for disciplina esse atributo guarda os resultados esperados ou
     * práticas dessa disciplina.
     */
    private String details;

    /**
     * O nome do componente.
     */
    private String name;

    /**
     * Os objetivos do componente.
     */
    private String objectives;

    /**
     * Indica os estereótipos atribuídos ao componente.
     */
    private List<Integer> stereotypesIds;

    public void setStereotypesIds(List<Integer> stereotypesIds) {
        this.stereotypesIds = stereotypesIds;
    }

    public Component() {
        stereotypesIds = new ArrayList<Integer>();
    }

    

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTime(Float time) {
        this.time = time;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }

    

    public Float getTime() {
        return time;
    }

   
    

    /**
     * @return o tipo do componente
     */
    public ComponentType getType() {
        return type;
    }

    /**
     * @return o diagrama que descreve o componente
     */
    public Diagram getDiagram() {
        return diagram;
    }

    /**
     * @param diagram o diagrama para ser atribuído ao componente
     */
    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    /**
     * @return o identificador do componente
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return o id do componente pai de acordo com a hierarquia da modelagem
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parent o id do componente pai de acordo com a hierarquia da
     * modelagem.
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
        /*Component parent = Modelling.getModelling().getComponentById(parentId);
        if (parent != null) {
            parent.addChild(id);
        }*/
    }

    /**
     * @return uma lista com todos os ids dos componentes filhos de acordo com
     * a hierarquia da modelagem
     */
    

    /**
     * @return as informações adicionais do componente
     */
    public String getAdditionalInformations() {
        return additionalInformations;
    }

    /**
     * @param additionalInformations as informações adicionais para atribuir
     */
    public void setAdditionalInformations(String additionalInformations) {
        this.additionalInformations = additionalInformations;
    }

    /**
     * @return a descrição do componente
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description a descrição para atribuir
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return os detalhes específicos do componente
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details os detalhes para atribuir
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * @return o nome do componente
     */
    public String getName() {
        return name;
    }

    /**
     * @param name o nome para atribuir ao componente
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return os objetivos do componente
     */
    public String getObjectives() {
        return objectives;
    }

    /**
     * @param objectives os objetivos para atribuir
     */
    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    /**
     * Retorna uma lista com os ids dos estereótipos atribuídos ao componente.
     * @return a lista com o s ids dos estereótipos
     */
    public List<Integer> getStereotypesIds() {
        return stereotypesIds;
    }

    @Override
    public String toString() {
        return name;
    }

}
