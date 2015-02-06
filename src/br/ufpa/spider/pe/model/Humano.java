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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name="humano")
public class Humano {
	@Id
	@Column(name="idhumano")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="idhumans_resources")
	private int idHumansResources;
	
	@Column(name="email", length = 50)
	private String email;
	
	@Column(name="nome", length = 50)
	private String nome;
	
	@Column(name="senha")
	private String senha;
	
	@Column(name="tipo", length = 50)
	private String tipo;
	
	@Column(name="estado", length = 50)
	private String estado;
	
	@ManyToMany(fetch = FetchType.LAZY)  
	@JoinTable(name = "formacao_academica",  
	joinColumns = { @JoinColumn(name = "idhumano",  
	updatable =  false) }, inverseJoinColumns = {  
	@JoinColumn(name = "idtipo", updatable = false) })
	private List<Tipo> formacoesAcademicas = new ArrayList<Tipo>();
	
	@ManyToMany(fetch = FetchType.LAZY)  
	@JoinTable(name = "papeis_exercidos",  
	joinColumns = { @JoinColumn(name = "idhumano",  
	updatable =  false) }, inverseJoinColumns = {  
	@JoinColumn(name = "idtipo", updatable = false) })
	private List<Tipo> papeisExercidos = new ArrayList<Tipo>();	
	
	@OneToMany(mappedBy="humano")
	@Cascade(value=CascadeType.ALL)
	private List<Risco> riscos = new ArrayList<Risco>();
	
	@OneToOne
	@Cascade(value=CascadeType.ALL)
	@JoinColumn(name = "capacidade")
	private Capacidade capacidade;
	
	@OneToOne(mappedBy = "humano")
	private GerenteProcesso gerente;
	
	@ManyToMany(fetch = FetchType.LAZY)  
	@JoinTable(name = "treinamento_x_recurso_humano",  
	joinColumns = { @JoinColumn(name = "idhumano",  
	updatable =  false) }, inverseJoinColumns = {  
	@JoinColumn(name = "idtreinamento", updatable = false) })
	//@Cascade(value=CascadeType.ALL)
	private List<Treinamento> treinamento = new ArrayList<Treinamento>();
	
	
	@ManyToMany(fetch = FetchType.LAZY)  
	@JoinTable(name = "alocacao",  
	joinColumns = { @JoinColumn(name = "idhumano",  
	updatable =  false) }, inverseJoinColumns = {  
	@JoinColumn(name = "idpapel", updatable = false) })
	private List<Papel> alocacao = new ArrayList<Papel>();
	

	@ManyToMany(fetch = FetchType.LAZY)  
	@JoinTable(name = "processo_x_tarefa",  
	joinColumns = { @JoinColumn(name = "idhumano",  
	updatable =  false) }, inverseJoinColumns = {  
	@JoinColumn(name = "idprocesso", updatable = false) })
	private List<Processo> processos = new ArrayList<Processo>();
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Risco> getRiscos() {
		return riscos;
	}

	public void setRiscos(List<Risco> riscos) {
		this.riscos = riscos;
	}


	public List<Papel> getPapeisAlocados() {
		return alocacao;
	}

	public void setAlocacao(List<Papel> alocacao) {
		this.alocacao = alocacao;
	}

	public List<Treinamento> getTreinamento() {
		return treinamento;
	}

	public void setTreinamento(List<Treinamento> treinamento) {
		this.treinamento = treinamento;
	}
	
	public Capacidade getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Capacidade capacidade) {
		this.capacidade = capacidade;
	}


	public List<Risco> getRisco() {
		return riscos;
	}

	public void setRisco(List<Risco> risco) {
		this.riscos = risco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdHumansResources() {
		return idHumansResources;
	}

	public void setIdHumansResources(int idHumansResources) {
		this.idHumansResources = idHumansResources;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getNome();
	}

	public List<Tipo> getFormacoesAcademicas() {
		return formacoesAcademicas;
	}

	public void setFormacoesAcademicas(List<Tipo> formacoesAcademicas) {
		this.formacoesAcademicas = formacoesAcademicas;
	}

	public List<Tipo> getPapeisExercidos() {
		return papeisExercidos;
	}

	public void setPapeisExercidos(List<Tipo> papeisExercidos) {
		this.papeisExercidos = papeisExercidos;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Papel> getAlocacao() {
		return alocacao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public GerenteProcesso getGerente() {
		return gerente;
	}

	public void setGerente(GerenteProcesso gerente) {
		this.gerente = gerente;
	}

	public List<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

}
