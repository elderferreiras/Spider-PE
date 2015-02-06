package br.ufpa.spider.pe.model;import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name="fase")
public class Fase {
	@Id
	@Column(name="idfase")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="idcomponent")
	private Integer idComponent;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="inicio_previsto")
	private String inicioPrevisto;
	
	@Column(name="fim_previsto")
	private String fimPrevisto;

	@Column(name="objetivos")
	String objetivos;
	
	@Column(name="descricao")
	String descricao;
	
	@Column(name="informacoesAdicionais")
	String informacoesAdicionais;	
	
	@Column(name="estado", length = 45)
	private String estado;
	
	@Column(name="ciclo_vida")
	private String cicloVida;
	
	@Column(name="status", length = 45)
	private String status;
	
	@OneToMany(mappedBy="fase")
	@Cascade(value=CascadeType.ALL)
	private List<Marco> marco = new ArrayList<Marco>();
	
	@OneToMany(mappedBy="fase")
	@Cascade(value=CascadeType.ALL)
	private List<Iteracao> iteracao = new ArrayList<Iteracao>();
	
	@ManyToOne
	@JoinColumn(name = "processo")
	//@Cascade(value=CascadeType.ALL)
	private Processo processo;
	
	@OneToMany(mappedBy="fase")
	private List<PlanoComunicacao> planoComunicacao = new ArrayList<PlanoComunicacao>();
	
	@OneToMany(mappedBy="fase")
	@Cascade(value=CascadeType.ALL)
	private List<Tarefa> tarefas = new ArrayList<Tarefa>();
	
	@OneToMany(mappedBy="fase")
	private List<Atividade> atividades = new ArrayList<Atividade>();

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public List<Iteracao> getIteracao() {
		return iteracao;
	}

	public void setIteracao(List<Iteracao> iteracao) {
		this.iteracao = iteracao;
	}

	public List<Marco> getMarco() {
		return marco;
	}

	public void setMarco(List<Marco> marco) {
		this.marco = marco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdComponent() {
		return idComponent;
	}

	public void setIdComponent(int idComponent) {
		this.idComponent = idComponent;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public void setIdComponent(Integer idComponent) {
		this.idComponent = idComponent;
	}

	@Override
	public String toString() {
		return getNome();
	}

	public List<PlanoComunicacao> getPlanoComunicacao() {
		return planoComunicacao;
	}

	public void setPlanoComunicacao(List<PlanoComunicacao> planoComunicacao) {
		this.planoComunicacao = planoComunicacao;
	}

	public String getCicloVida() {
		return cicloVida;
	}

	public void setCicloVida(String cicloVida) {
		this.cicloVida = cicloVida;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public String getInicioPrevisto() {
		return inicioPrevisto;
	}

	public void setInicioPrevisto(String inicioPrevisto) {
		this.inicioPrevisto = inicioPrevisto;
	}

	public String getFimPrevisto() {
		return fimPrevisto;
	}

	public void setFimPrevisto(String fimPrevisto) {
		this.fimPrevisto = fimPrevisto;
	}

}
