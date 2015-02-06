package br.ufpa.spider.pe.model;import java.util.ArrayList;
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

@Entity(name="software")
public class Software {

	@Id
	@Column(name="idsoftware")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="idComponent")
	private Integer idComponent;
	
	@Column(name="nome")
	String nome;
	
	@Column(name =  "arq_xml")
	private String arqXML;

	@Column(name="objetivos")
	String objetivos;
	
	@Column(name="descricao")
	String descricao;
	
	@Column(name="informacoesAdicionais")
	String informacoesAdicionais;	
	
	@Column(name="estado")
	String estado;
	
	@Column(name="tipo")
	String tipo;
	
	@Column(name="caminho")
	String caminho;	
	
	@ManyToMany(fetch = FetchType.LAZY)  
	@JoinTable(name = "softwares_x_tarefa",  
	joinColumns = { @JoinColumn(name = "idsoftware",  
	updatable =  false) }, inverseJoinColumns = {  
	@JoinColumn(name = "idtarefa", updatable = false) })
	private List<Tarefa> tarefas = new ArrayList<Tarefa>();
	
	@ManyToMany(fetch = FetchType.LAZY)  
	@JoinTable(name = "softwares_x_marco",  
	joinColumns = { @JoinColumn(name = "idsoftware",  
	updatable =  false) }, inverseJoinColumns = {  
	@JoinColumn(name = "idmarco", updatable = false) })
	private List<Marco> marcos = new ArrayList<Marco>();
	


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

	public List<Tarefa> getTarefa() {
		return tarefas;
	}

	public void setTarefa(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
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

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
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

	public String getArqXML() {
		return arqXML;
	}

	public void setArqXML(String arqXML) {
		this.arqXML = arqXML;
	}

	public List<Marco> getMarcos() {
		return marcos;
	}

	public void setMarcos(List<Marco> marcos) {
		this.marcos = marcos;
	}
}