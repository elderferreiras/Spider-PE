package br.ufpa.spider.pe.view.pm.model;

import java.io.Serializable;

import javax.swing.JOptionPane;

import com.mxgraph.model.mxCell;

import br.ufpa.spider.pe.model.JoinFork;
import br.ufpa.spider.pe.model.Transicao;
import br.ufpa.spider.pe.model.dao.JoinForkDAO;
import br.ufpa.spider.pe.model.dao.TransicaoDAO;
import br.ufpa.spider.pe.view.JDialog_Spider_Login;
import br.ufpa.spider.pe.view.pm.logic.ModellingController;

/**
 * Representa um componente de um diagrama. Essa classe é usada para armazenar
 * informações relacionadas com a exibição de um componente através de um
 * diagrama. Dessa forma, essa classe guarda informações como posição e tamanho
 * do componente do diagrama.
 */
public class DiagramComponent implements Serializable {

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

    /**
     * A altura do componente. Necessário apenas para componentes de tamanho
     * variável como notas e as barras do diagrama de atividades.
     */
    private Double height;

    /**
     * A largura do componente. Necessário apenas para componentes de tamanho
     * variável como noas e as barras do diagrama de atividades.
     */
    private Double width;

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return o id do componente.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return a sua posição no eixo x
     */
    public Double getX() {
        return x;
    }

    /**
     * @param x a posicão no eixo x para atribuir
     */
    public void setX(Double x) {
        this.x = x;
    }

    /**
     * @return a sua posição no eixo x
     */
    public Double getY() {
        return y;
    }

    /**
     * @param y a posicão no eixo y para atribuir
     */
    public void setY(Double y) {
        this.y = y;
    }

    /**
     * @return a altura do componente
     */
    public Double getHeight() {
        return height;
    }

    /**
     * @param height a altura para atribuir
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * @return a largura do componente
     */
    public Double getWidth() {
        return width;
    }

    /**
     * @param width a largura para atribuir
     */
    public void setWidth(Double width) {
        this.width = width;
    }

    //Esse m�todo retorna o nome do componente da modelagem que � representado
    //por esse objeto.
    @Override
    public String toString() {
    	if(JDialog_Spider_Login.getInstance().getLogado() == 3){
    		Component c = Modelling.getModelling().getComponent(getId());
    		if(c.getType().equals(ComponentType.FORK) || c.getType().equals(ComponentType.JOIN)){
    			JoinFork join = JoinForkDAO.findById(c.getId(), JDialog_Spider_Login.getInstance().view.getProcesso().getId());
    			if(join!=null)
    				return join.getTipo();
        	}
    	}
    	else if(JDialog_Spider_Login.getInstance().getLogado() == 2){
    		Component c = Modelling.getModelling().getComponent(getId());
    		if(c.getType().equals(ComponentType.FORK) || c.getType().equals(ComponentType.JOIN)){
    			JoinFork join = JoinForkDAO.findById(getId(), JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getId());
    			if(join!=null)
    				return join.getTipo();
        	}
    	}      		
    		
        return Modelling.getModelling().getComponent(id).getName();
    }

}
