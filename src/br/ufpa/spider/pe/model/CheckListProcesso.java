package br.ufpa.spider.pe.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name="checklistprocesso")
public class CheckListProcesso {
	@Id
	@Column(name="id_checklistprocesso")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="nome", length = 45)
	private String nome;
	
	@Column(name="caminho")
	private String caminho;
	
	@OneToOne(mappedBy = "checklistprocesso")
	private Tipo tipo;
	
	@OneToMany(mappedBy="checklistprocesso")
	@Cascade(value=CascadeType.ALL)
	private List<RevisaoProcesso> revisaoProcessos = new ArrayList<RevisaoProcesso>();

	public List<RevisaoProcesso> getRevisaoProcessos() {
		return revisaoProcessos;
	}

	public void setRevisaoProcessos(List<RevisaoProcesso> revisaoProcessos) {
		this.revisaoProcessos = revisaoProcessos;
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

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public String toString(){
		return getNome();
	}

}