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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name="atividade")
public class Atividade {	
	@Id
	@Column(name="idatividade")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;	
	
	@Column(name="idcomponent")
	private int idComponent;
	
	@Column(name="nome")
	private String nome;

	@Column(name="objetivos")
	String objetivos;
	
	@Column(name="descricao")
	String descricao;
	
	@Column(name =  "arq_xml")
	private String arqXML;
	
	@Column(name="inicio_previsto")
	private String inicioPrevisto;
	
	@Column(name="fim_previsto")
	private String fimPrevisto;
	
	@Column(name="informacoesAdicionais")
	String informacoesAdicionais;	
	
	@Column(name="estado", length = 45)
	private String estado;
	
	@Column(name="status", length = 45)
	private String status;
	
	@OneToMany(mappedBy="atividade")
	@Cascade(value=CascadeType.ALL)
	private List<Tarefa> tarefa = new ArrayList<Tarefa>();
	
	@ManyToOne
	@JoinColumn(name = "iteracao")
	private Iteracao iteracao;
	
	@ManyToOne
	@JoinColumn(name = "fase")
	private Fase fase;
	
	@OneToMany(mappedBy="atividade")
	@Cascade(value=CascadeType.ALL)
	private List<Atividade> atividades = new ArrayList<Atividade>();
	
	@ManyToOne
	@JoinColumn(name = "atividadesingle")
	private Atividade atividade;
	
	public Iteracao getIteracao() {
		return iteracao;
	}

	public void setIteracao(Iteracao iteracao) {
		this.iteracao = iteracao;
	}

	public List<Tarefa> getTarefa() {
		return tarefa;
	}

	public void setTarefa(List<Tarefa> tarefa) {
		this.tarefa = tarefa;
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

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}
	
	@Override
	public String toString() {
		return getNome();
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public Fase getFase() {
		return fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
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

	public String getArqXML() {
		return arqXML;
	}

	public void setArqXML(String arqXML) {
		this.arqXML = arqXML;
	}
}
