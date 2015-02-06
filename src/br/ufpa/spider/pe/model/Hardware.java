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

@Entity(name="hardware")
public class Hardware {

	@Id
	@Column(name="idhardware")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="nome", length = 45)
	private String nome;
	
	@Column(name="marca", length = 45)
	private String marca;
	
	@Column(name="especificacoes_tecnicas")
	private String especificacoesTecnicas;
	
	@Column(name="estado")
	private String estado;
	
	@Column(name="tipo")
	private String tipo;
	
	@ManyToMany(fetch = FetchType.LAZY)  
	@JoinTable(name = "hardwares_x_tarefa",  
	joinColumns = { @JoinColumn(name = "idhardware",  
	updatable =  false) }, inverseJoinColumns = {  
	@JoinColumn(name = "idtarefa", updatable = false) })
	private List<Tarefa> tarefas = new ArrayList<Tarefa>();
	
	@ManyToMany(fetch = FetchType.LAZY)  
	@JoinTable(name = "hardwares_x_marco",  
	joinColumns = { @JoinColumn(name = "idhardware",  
	updatable =  false) }, inverseJoinColumns = {  
	@JoinColumn(name = "idmarco", updatable = false) })
	private List<Marco> marcos = new ArrayList<Marco>();

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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getEspecificacoesTecnicas() {
		return especificacoesTecnicas;
	}

	public void setEspecificacoesTecnicas(String especificacoesTecnicas) {
		this.especificacoesTecnicas = especificacoesTecnicas;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
	
	@Override
	public String toString() {
		return getNome();
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

	public List<Marco> getMarcos() {
		return marcos;
	}

	public void setMarcos(List<Marco> marcos) {
		this.marcos = marcos;
	}

}