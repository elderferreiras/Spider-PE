package br.ufpa.spider.pe.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name="capacidade")
public class Capacidade {
	
	@Id
	@Column(name="idcapacidade")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
//	@Column(name="papeis", length=45)
//	private String papeis;
//	
//	@Column(name="formacao_academica", length = 45)
//	private String formacaoAcademica;
	
	@Column(name="formacao_profissional", length = 45)
	private String formacaoProfissional;
	
	@Column(name="cursos_certificacoes")
	private String cursosCertificacoes;
	
	@Column(name="conhecimento")
	private String conhecimento;
	
	@OneToOne(mappedBy = "capacidade")
	private Humano humano;
	

	public Humano getHumano() {
		return humano;
	}

	public void setHumano(Humano humano) {
		this.humano = humano;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getFormacaoProfissional() {
		return formacaoProfissional;
	}

	public void setFormacaoProfissional(String formacaoProfissional) {
		this.formacaoProfissional = formacaoProfissional;
	}

	public String getCursosCertificacoes() {
		return cursosCertificacoes;
	}

	public void setCursosCertificacoes(String cursosCertificacoes) {
		this.cursosCertificacoes = cursosCertificacoes;
	}

	public String getConhecimento() {
		return conhecimento;
	}

	public void setConhecimento(String conhecimento) {
		this.conhecimento = conhecimento;
	}

}
