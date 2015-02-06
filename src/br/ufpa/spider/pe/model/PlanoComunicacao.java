package br.ufpa.spider.pe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name="plano_comunicacao")
public class PlanoComunicacao {
	@Id
	@Column(name="idplano_comunicacao")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="quando_comunicado", length = 45)
	private String quando_comunicado;
	
	@ManyToOne
	@JoinColumn(name = "fase")
	private Fase fase;
	
	@Column(name="emissor", length = 45)
	private String emissor;
	
	@Column(name="receptor", length = 45)
	private String receptor;
	
	@Column(name="forma", length = 45)
	private String forma;
	
	@ManyToOne
	@JoinColumn(name = "produtoTrabalho")
	private ProdutoTrabalho produtoTrabalho;
	
	@ManyToOne
	@JoinColumn(name = "tipo")
	private Tipo tipo;

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public ProdutoTrabalho getProdutoTrabalho() {
		return produtoTrabalho;
	}

	public void setProdutoTrabalho(
			ProdutoTrabalho produtoTrabalho) {
		this.produtoTrabalho = produtoTrabalho;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuando_comunicado() {
		return quando_comunicado;
	}

	public void setQuando_comunicado(String quando_comunicado) {
		this.quando_comunicado = quando_comunicado;
	}

	public Fase getFase() {
		return fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
	}

	public String getEmissor() {
		return emissor;
	}

	public void setEmissor(String emissor) {
		this.emissor = emissor;
	}

	public String getReceptor() {
		return receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	public String getForma() {
		return forma;
	}

	public void setForma(String forma) {
		this.forma = forma;
	}
	
	@Override
	public String toString() {
		return getProdutoTrabalho().getNome();
	}

}
