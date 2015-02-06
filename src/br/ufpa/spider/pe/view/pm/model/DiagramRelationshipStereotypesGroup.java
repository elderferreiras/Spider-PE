package br.ufpa.spider.pe.view.pm.model;

import java.io.Serializable;

public class DiagramRelationshipStereotypesGroup implements Serializable{

    /**
     * O id do componente da modelagem que será representado no diagrama.
     */
    private Integer id;

    /**
     * A posição x do componente.
     */
    private Double x;

    /**
     * A posição y do componente.
     */
    private Double y;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Integer i : Modelling.getModelling().getRelationship(id).getStereotypesIds()) {
            stringBuilder.append("<<");
            stringBuilder.append(Modelling.getModelling().getStereotype(i).getName());
            stringBuilder.append(">>\n");
        }
        return stringBuilder.toString();
    }

}
