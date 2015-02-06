package br.ufpa.spider.pe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class OperadorLogico {
	@Id
	@Column(name="idoperador_logico")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column (name="idcomponet")
	private Integer idComponent;
	
	@Column (name="operador", length = 3)
	private String operador;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdComponent() {
		return idComponent;
	}

	public void setIdComponent(Integer idComponent) {
		this.idComponent = idComponent;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}
}
