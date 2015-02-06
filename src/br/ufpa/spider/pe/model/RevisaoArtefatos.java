package br.ufpa.spider.pe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="revisao_artefatos")
public class RevisaoArtefatos {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="data")
	private String data;
	
	@Column(name="caminho")
	private String caminho;

	public String getData() {
		return data;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@ManyToOne
	@JoinColumn(name = "marco")
	//@Cascade(value=CascadeType.ALL)
	private Marco marco;
	
	@ManyToOne
	@JoinColumn(name = "checkListArtefatos")
	//@Cascade(value=CascadeType.ALL)
	private CheckListArtefatos checkListArtefatos;

	public Marco getMarco() {
		return marco;
	}

	public void setMarco(Marco marco) {
		this.marco = marco;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public CheckListArtefatos getChecklistartefatos() {
		return checkListArtefatos;
	}

	public void setChecklistartefatos(CheckListArtefatos checklistartefatos) {
		this.checkListArtefatos = checklistartefatos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString(){
		return this.getNome();
	}
}
