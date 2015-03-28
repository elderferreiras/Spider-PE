package br.ufpa.spider.pe.view.execution.model;

public class HumanResource {

    private Integer id;

    private String name;

    private String capabilities;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    public String getCapabilities() {
        return capabilities;
    }

    @Override
    public String toString() {
        return name;
    }



}
