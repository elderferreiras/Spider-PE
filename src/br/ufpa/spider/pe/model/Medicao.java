package br.ufpa.spider.pe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="medicao")
public class Medicao {
	
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
	
	@ManyToOne
	@JoinColumn(name = "marco")
	//@Cascade(value=CascadeType.ALL)
	private Marco marco;
	
	@ManyToOne
	@JoinColumn(name = "planoMedicao")
	//@Cascade(value=CascadeType.ALL)
	private PlanoMedicao planoMedicao;

	public Marco getMarco() {
		return marco;
	}

	public void setMarco(Marco marco) {
		this.marco = marco;
	}

	public String getData() {
		return data;
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

	public PlanoMedicao getPlanoMedicao() {
		return planoMedicao;
	}

	public void setPlanoMedicao(PlanoMedicao planoMedicao) {
		this.planoMedicao = planoMedicao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
