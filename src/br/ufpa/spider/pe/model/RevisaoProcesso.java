package br.ufpa.spider.pe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="revisao_processo")
public class RevisaoProcesso {
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
	private Marco marco;
	
	@ManyToOne
	@JoinColumn(name = "checklistprocesso")
	private CheckListProcesso checklistprocesso;
	
	@ManyToOne
	@JoinColumn(name = "processo")
	private Processo processo;

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public CheckListProcesso getChecklistprocesso() {
		return checklistprocesso;
	}

	public void setChecklistprocesso(CheckListProcesso checklistprocesso) {
		this.checklistprocesso = checklistprocesso;
	}

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
	
	public String toString(){
		return getNome();
	}

}
