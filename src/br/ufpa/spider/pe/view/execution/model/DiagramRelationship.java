package br.ufpa.spider.pe.view.execution.model;

import java.io.Serializable;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Transicao;
import br.ufpa.spider.pe.model.dao.TransicaoDAO;
import br.ufpa.spider.pe.view.Spider_PE_Home;
import br.ufpa.spider.pe.view.execution.logic.FindByProcess;

/**
 * Representa um relacionamento no diagrama.
 */
public class DiagramRelationship implements Serializable {

    /**
     * O id do relacionamento que o objeto representa.
     */
    private Integer id;

    /**
     * A posição x do rótulo do relacionamento. Pode ser nulo, nesse caso não há
     * nenhum rótulo no relacionamento.
     */
    private Double labelX;

    /**
     * A posição y do rótulo do relacionamento. Pode ser nulo, nesse caso não há
     * nenhum rótulo no relacionamento.
     */
    private Double labelY;

    public void setId(Integer id) {
        this.id = id;

    }

    /**
     * @return o id do relacionamento que esse objeto representa
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return a posição x do rótulo desse relacionamento
     */
    public Double getLabelX() {
        return labelX;
    }

    /**
     * @param labelX a posição x do rótulo do relacionamento
     */
    public void setLabelX(Double labelX) {
        this.labelX = labelX;
    }

    /**
     * @return a posição y do rótulo do relacionamento
     */
    public Double getLabelY() {
        return labelY;
    }

    /**
     * @param labelY a posição y do rótulo do relacionamento
     */
    public void setLabelY(Double labelY) {
        this.labelY = labelY;
    }

    //Esse método retorna o nome do relacionamento da modelagem que é
    //representado por esse objeto.
    @Override
    public String toString() {
	    	if(Spider_PE_Home.getInstance().getLogado() == 3){
	    		Relationship transicao = Modelling.getModelling().getRelationship(getId());
	    	    Transicao trans = TransicaoDAO.findById(getId(), transicao.getSourceId(), transicao.getDestinationId(), Spider_PE_Home.getInstance().execution.getProcesso().getId());
	    	    if(trans!=null)	{  
	    		    return trans.getTipo();
	    		    }
	    	} else if(Spider_PE_Home.getInstance().getLogado() == 2) {
	    		Relationship transicao = Modelling.getModelling().getRelationship(getId());
	    	    Transicao trans = TransicaoDAO.findById(getId(), transicao.getSourceId(), transicao.getDestinationId(), Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso().getId());
	    	    if(trans!=null)	    	
	    		    return trans.getTipo();
	    	}
		return  Modelling.getModelling().getRelationship(id).getLabel();
    }

}
