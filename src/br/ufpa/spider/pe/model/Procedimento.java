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
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name="procedimento")
public class Procedimento {

	@Id
	@Column(name="idprocedimento")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="idcomponent")
	private Integer idComponent;	

	@Column(name="nome")
	private String nome;
	
	@Column(name="objetivos")
	private String objetivos;
	
	@Column(name =  "arq_xml")
	private String arqXML;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="informacoesAdicionais")
	private String informacoesAdicionais;
	
	@Column(name="caminho")
	private String caminho;
	
	@ManyToMany(fetch = FetchType.LAZY)  
	@JoinTable(name = "procedimento_x_tarefa",  
	joinColumns = { @JoinColumn(name = "idprocedimento",  
	updatable =  false) }, inverseJoinColumns = {  
	@JoinColumn(name = "idtarefa", updatable = false) })
	private List<Tarefa> tarefas = new ArrayList<Tarefa>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdComponent() {
		return idComponent;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public void setIdComponent(Integer idComponent) {
		this.idComponent = idComponent;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public String getArqXML() {
		return arqXML;
	}

	public void setArqXML(String arqXML) {
		this.arqXML = arqXML;
	}

}
