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

@Entity(name="tipo")
public class Tipo {
	@Id
	@Column(name="idtipo")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="nome", length = 45)
	private String nome;
	
	@Column(name="fator_conversao")
	private int fatorConversao;
	
	@Column(name="classificacao", length = 80)
	private String classificacao;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "campo")
	private Campo campo;
	
	@OneToMany(mappedBy="tipo")
	@Cascade(value=CascadeType.ALL)
	private List<Tarefa> tarefas = new ArrayList<Tarefa>();	
	
	@OneToMany(mappedBy="tipo")
	@Cascade(value=CascadeType.ALL)
	private List<Risco> riscos = new ArrayList<Risco>();
	
	@OneToMany(mappedBy="tipo")
	@Cascade(value=CascadeType.ALL)
	private List<Treinamento> treinamentos = new ArrayList<Treinamento>();
	
	
	@OneToMany(mappedBy="tipo")
	@Cascade(value=CascadeType.ALL)
	private List<PlanoComunicacao> planosComunicacao = new ArrayList<PlanoComunicacao>();
	
	@ManyToMany(fetch = FetchType.LAZY)  
	@JoinTable(name = "papeis_exercidos",  
	joinColumns = { @JoinColumn(name = "idtipo",  
	updatable =  false) }, inverseJoinColumns = {  
	@JoinColumn(name = "idhumano", updatable = false) })
	private List<Humano> humanosPapeisExercidos = new ArrayList<Humano>();
	
	@ManyToMany(fetch = FetchType.LAZY)  
	@JoinTable(name = "formacao_academica",  
	joinColumns = { @JoinColumn(name = "idtipo",  
	updatable =  false) }, inverseJoinColumns = {  
	@JoinColumn(name = "idhumano", updatable = false) })
	private List<Humano> humanosFormacaoAcademica = new ArrayList<Humano>();
	
	@OneToOne
	@JoinColumn(name = "checklistprocesso")
	private CheckListProcesso checklistprocesso;
	
	public CheckListProcesso getCheklistProcesso() {
		return checklistprocesso;
	}

	public void setCheklistProcesso(CheckListProcesso checklistProcesso) {
		this.checklistprocesso = checklistProcesso;
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

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public Campo getCampo() {
		return campo;
	}

	public void setCampo(Campo campo) {
		this.campo = campo;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getNome();
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public List<Risco> getRiscos() {
		return riscos;
	}

	public void setRiscos(List<Risco> riscos) {
		this.riscos = riscos;
	}

	public List<Treinamento> getTreinamentos() {
		return treinamentos;
	}

	public void setTreinamentos(List<Treinamento> treinamentos) {
		this.treinamentos = treinamentos;
	}

	public List<PlanoComunicacao> getPlanosComunicacao() {
		return planosComunicacao;
	}

	public void setPlanosComunicacao(List<PlanoComunicacao> planosComunicacao) {
		this.planosComunicacao = planosComunicacao;
	}

	public List<Humano> getHumanosPapeisExercidos() {
		return humanosPapeisExercidos;
	}

	public void setHumanosPapeisExercidos(List<Humano> humanosPapeisExercidos) {
		this.humanosPapeisExercidos = humanosPapeisExercidos;
	}

	public List<Humano> getHumanosFormacaoAcademica() {
		return humanosFormacaoAcademica;
	}

	public void setHumanosFormacaoAcademica(List<Humano> humanosFormacaoAcademica) {
		this.humanosFormacaoAcademica = humanosFormacaoAcademica;
	}

	public int getFatorConversao() {
		return fatorConversao;
	}

	public void setFatorConversao(int fatorConversao) {
		this.fatorConversao = fatorConversao;
	}

}
