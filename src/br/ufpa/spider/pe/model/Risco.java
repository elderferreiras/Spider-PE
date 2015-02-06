package br.ufpa.spider.pe.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name="risco")
public class Risco {

	@Id
	@Column(name="idrisco")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="nome", length = 150)
	private String nome;
	
	@Column(name="tipoRisco", length = 80)
	private String tipoRisco;
	
	@Column(name="descricao", length = 200)
	private String descricao;
	
	@Column(name="probabilidade", length = 10)
	private String probabilidade;
	
	@Column(name="impacto", length = 20)
	private String impacto;
	
	@Column(name="severidade", length = 20)
	private String severidade;
	
	@Column(name="mitigacao", length = 200)
	private String mitigacao;
	
	@Column(name="contigencia", length = 200)
	private String contingencia;
	
	@Column(name="acao_tomada", length = 200)
	private String acaoTomada;
	
	@ManyToOne
	@JoinColumn(name = "humano")
	//@Cascade(value=CascadeType.ALL)
	private Humano humano;
	
	@ManyToOne
	@JoinColumn(name = "tipo")
	private Tipo tipo;

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoRisco() {
		return tipoRisco;
	}

	public void setTipoRisco(String tipoRisco) {
		this.tipoRisco = tipoRisco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getProbabilidade() {
		return probabilidade;
	}

	public void setProbabilidade(String probabilidade) {
		this.probabilidade = probabilidade;
	}

	public String getImpacto() {
		return impacto;
	}

	public void setImpacto(String impacto) {
		this.impacto = impacto;
	}

	public String getSeveridade() {
		return severidade;
	}

	public void setSeveridade(String severidade) {
		this.severidade = severidade;
	}

	public String getMitigacao() {
		return mitigacao;
	}

	public void setMitigacao(String mitigacao) {
		this.mitigacao = mitigacao;
	}

	public String getContingencia() {
		return contingencia;
	}

	public void setContingencia(String contingencia) {
		this.contingencia = contingencia;
	}

	public String getAcaoTomada() {
		return acaoTomada;
	}

	public void setAcaoTomada(String acaoTomada) {
		this.acaoTomada = acaoTomada;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getNome();
	}

}
