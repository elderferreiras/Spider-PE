package br.ufpa.spider.pe.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@javax.persistence.Entity(name = "politica_organizacional")
public class PoliticaOrganizacional {
	@Id
	@Column(name="idpolitica_organizacional")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="nome_organizacao")
	private String nome;
	
	@Column(name="diretrizes_gerais")
	private String diretrizesGerais;
	
	@Column(name="responsabilidades_gerais")
	private String responsabilidadesGerais;
	
	@Column(name="politica_qualidade")
	private String politicaQualidade;
	
	@Column(name="politica_desenvolvimento")
	private String politicaDesenvolvimento;
	
	@Column(name="politicas_especificas")
	private String politicasEspecificas;
	
	@Column(name="referencias")
	private String referencias;
	
	@Column(name="versao")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int versao;
	
	@OneToOne
	@JoinColumn(name = "processo")
	private Processo processo = new Processo();
	
	@OneToOne
	@JoinColumn(name = "produtoTrabalho")
	private ProdutoTrabalho produtoTrabalho;
	
	public int getVersao() {
		return versao;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}

	
	
	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
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

	public String getDiretrizesGerais() {
		return diretrizesGerais;
	}

	public void setDiretrizesGerais(String diretrizesGerais) {
		this.diretrizesGerais = diretrizesGerais;
	}

	public String getResponsabilidadesGerais() {
		return responsabilidadesGerais;
	}

	public void setResponsabilidadesGerais(String responsabilidadesGerais) {
		this.responsabilidadesGerais = responsabilidadesGerais;
	}

	public String getPoliticaQualidade() {
		return politicaQualidade;
	}

	public void setPoliticaQualidade(String politicaQualidade) {
		this.politicaQualidade = politicaQualidade;
	}

	public String getPoliticaDesenvolvimento() {
		return politicaDesenvolvimento;
	}

	public void setPoliticaDesenvolvimento(String politicaDesenvolvimento) {
		this.politicaDesenvolvimento = politicaDesenvolvimento;
	}

	public String getPoliticasEspecificas() {
		return politicasEspecificas;
	}

	public void setPoliticasEspecificas(String politicasEspecificas) {
		this.politicasEspecificas = politicasEspecificas;
	}

	public String getReferencias() {
		return referencias;
	}

	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}

	public ProdutoTrabalho getProdutoTrabalho() {
		return produtoTrabalho;
	}

	public void setProdutoTrabalho(ProdutoTrabalho produtoTrabalho) {
		this.produtoTrabalho = produtoTrabalho;
	}
	
	@Override
	public String toString() {
		return getProdutoTrabalho().getNome();
	}

}
