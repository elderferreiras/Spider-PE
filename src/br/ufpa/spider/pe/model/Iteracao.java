package br.ufpa.spider.pe.model;

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

@Entity(name="iteracao")
public class Iteracao {
	@Id
	@Column(name="iditerecao")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="idcomponent")
	private Integer idComponent;
	
	@Column(name="nome")
	private String nome;

	@Column(name="objetivos")
	String objetivos;
	
	@Column(name="descricao")
	String descricao;
	
	@Column(name="informacoesAdicionais")
	String informacoesAdicionais;	
	
	@Column(name="estado", length = 45)
	private String estado;
	
	@Column(name="status", length = 45)
	private String status;
	
	@Column(name="inicio_previsto")
	private String inicioPrevisto;
	
	@Column(name="fim_previsto")
	private String fimPrevisto;
	
	@OneToMany(mappedBy="iteracao")
	@Cascade(value=CascadeType.ALL)
	private List<Tarefa> tarefa = new ArrayList<Tarefa>();
	
	@OneToMany(mappedBy="iteracao")
	private List<Atividade> atividade = new ArrayList<Atividade>();
	
	@ManyToOne
	@JoinColumn(name = "fase")
	//@Cascade(value=CascadeType.ALL)
	private Fase fase;

	public Fase getFase() {
		return fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
	}

	public List<Atividade> getAtividade() {
		return atividade;
	}

	public void setAtividade(List<Atividade> atividade) {
		this.atividade = atividade;
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

	public void setIdComponent(Integer idComponent) {
		this.idComponent = idComponent;
	}
	
	@Override
	public String toString() {
		return getNome();
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
