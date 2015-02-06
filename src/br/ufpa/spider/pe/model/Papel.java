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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name="papel")
public class Papel {
	@Id
	@Column(name="idpapel")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="idcomponent")
	private Integer idComponent;

	@Column(name="objetivos")
	String objetivos;
	
	@Column(name="descricao")
	String descricao;
	
	@Column(name="informacoesAdicionais")
	String informacoesAdicionais;
	
	@Column(name =  "arq_xml")
	private String arqXML;
	
	@ManyToMany(fetch = FetchType.LAZY)  
	@JoinTable(name = "alocacao",  
	joinColumns = { @JoinColumn(name = "idpapel",  
	updatable =  false) }, inverseJoinColumns = {  
	@JoinColumn(name = "idhumano", updatable = false) })
	private List<Humano> humanosAlocados = new ArrayList<Humano>();
	
	@ManyToOne
	@JoinColumn(name = "tarefa")
	private Tarefa tarefa;
	
	@ManyToOne
	@JoinColumn(name = "marco")
	private Marco marco;
	
	@Column(name="nome")
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public List<Humano> getHumanosAlocados() {
		return humanosAlocados;
	}

	public void setHumanosAlocados(List<Humano> humanosAlocados) {
		this.humanosAlocados = humanosAlocados;
	}

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

	public String getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getInformacoesAdicionais() {
		return informacoesAdicionais;
	}

	public void setInformacoesAdicionais(String informacoesAdicionais) {
		this.informacoesAdicionais = informacoesAdicionais;
	}
	
	@Override
	public String toString() {
		return getNome();
	}

	public String getArqXML() {
		return arqXML;
	}

	public void setArqXML(String arqXML) {
		this.arqXML = arqXML;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public Marco getMarco() {
		return marco;
	}

	public void setMarco(Marco marco) {
		this.marco = marco;
	}

}