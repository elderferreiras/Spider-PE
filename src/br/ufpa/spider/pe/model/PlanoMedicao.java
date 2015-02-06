package br.ufpa.spider.pe.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name="planoMedicao")
public class PlanoMedicao {
	@Id
	@Column(name="id_planoMedicao")
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@Column(name="nome")
	private String nome;
	

	@OneToOne
	@JoinColumn(name = "produtoTrabalho")
	private ProdutoTrabalho produtoTrabalho;
	
	@OneToOne
	@JoinColumn(name = "processo")
	private Processo processo;
	
	@OneToMany(mappedBy="planoMedicao")
	@Cascade(value=CascadeType.ALL)
	private List<Medicao> medicoes = new ArrayList<Medicao>();

	
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

	public ProdutoTrabalho getProdutoTrabalho() {
		return produtoTrabalho;
	}

	public void setProdutoTrabalho(ProdutoTrabalho produtoTrabalho) {
		this.produtoTrabalho = produtoTrabalho;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public List<Medicao> getPlanos() {
		return medicoes;
	}

	public void setPlanos(List<Medicao> planos) {
		this.medicoes = planos;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
