package br.ufpa.spider.pe.view.execution.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um conjunto de estereótipos dos quais apenas um dos estereótipos
 * pode ser atriuído à um componente de um tipo específico. Por exemplo, se em
 * um grupo de estereótipos relacionados com os componentes do tipo
 * "Definição De Tarefa" temos os estereótipos "Organizacional" e "Gerência",
 * significa que mais de um desses estereótipos não podem ser atribuídos
 * simultaneamente ao componente. Dessa forma, se um componente do tipo
 * "Definição De Tarefa" tem o estereótipo "Organizacional" ele não pode ter o
 * estereótipo "Gerência" e vice-versa.
 */
public class StereotypesGroup {

    /**
     * O id do grupo de estereótipos.
     */
    private Integer id;

    /**
     * O tipo do componente
     */
    private ExtendibleElementType type;

    /**
     * Os ids dos estereóipos que formam esse grupo de estereótipos.
     */
    private List<Integer> stereotypesIds;

    /**
     * @return o id do grupo de estereótipos
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return o tipo do componente ao qual o grupo de estereótipos se aplica
     */
    public ExtendibleElementType getType() {
        return type;
    }

    public void setType(ExtendibleElementType type) {
        this.type = type;
    }

    public StereotypesGroup() {
        stereotypesIds = new ArrayList<Integer>();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return uma lista contendo todos os ids dos estereótipos que formam o
     * grupo de estereótipos
     */
    public List<Integer> getStereotypesIds() {
        return stereotypesIds;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append(type.toString());
        stringBuilder.append(": ");
        stringBuilder.append(Modelling.getModelling().getStereotype(stereotypesIds.get(0)).toString());
        for(int i = 1; i < stereotypesIds.size(); ++ i) {
            stringBuilder.append(", ");
            stringBuilder.append(Modelling.getModelling().getStereotype(stereotypesIds.get(i)).toString());
        }
        stringBuilder.append(".");
        return stringBuilder.toString();
    }



    

}
