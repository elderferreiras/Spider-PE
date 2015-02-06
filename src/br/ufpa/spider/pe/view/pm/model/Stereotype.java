package br.ufpa.spider.pe.view.pm.model;

/**
 * Um estereótipo que pode ser atribuído a um componente da modelagem.
 */
public class Stereotype {

    /**
     * O id do estereótipo.
     */
    private Integer id;

    /**
     * O nome do estereótipo.
     */
    private String name;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return o id do estereótipo
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return o nome do estereótipo
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }



}
