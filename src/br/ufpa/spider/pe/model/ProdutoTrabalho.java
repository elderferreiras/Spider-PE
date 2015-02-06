package br.ufpa.spider.pe.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name="produto_trabalho")
public class ProdutoTrabalho {

	@Id
	@Column(name="idproduto_trabalho")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="idcomponent")
	private Integer idComponent;	

	@Column(name="nome")
	private String nome;
	
	@Column(name =  "arq_xml")
	private String arqXML;
	
	@Column(name="objetivos")
	private String objetivos;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="informacoesAdicionais")
	private String informacoesAdicionais;
	
	@Column(name="caminho")
	private String caminho;
	
	@OneToMany(mappedBy="produtoTrabalho")
	@Cascade(value=CascadeType.ALL)
	private List<PlanoComunicacao> planoComunicacao = new ArrayList<PlanoComunicacao>();	
	
	@ManyToMany(fetch = FetchType.LAZY)  
	@JoinTable(name = "produto_trabalho_x_tarefa",  
	joinColumns = { @JoinColumn(name = "idproduto_trabalho",  
	updatable =  false) }, inverseJoinColumns = {  
	@JoinColumn(name = "idtarefa", updatable = false) })
	private List<Tarefa> tarefa = new ArrayList<Tarefa>();
	
	@OneToMany(mappedBy="produtoTrabalho")
	private List<Marco> marco = new ArrayList<Marco>();
	
	@OneToOne
	@JoinColumn(name = "checkListArtefatos")
	private CheckListArtefatos checkListArtefatos;
	
	@OneToOne(mappedBy = "produtoTrabalho")
	private PlanoMedicao planoMedicao;
	
	@OneToOne(mappedBy = "produtoTrabalho")
	private PoliticaOrganizacional politicaOrganizacional;

	public List<Tarefa> getTarefa() {
		return tarefa;
	}

	public void setTarefa(List<Tarefa> tarefa) {
		this.tarefa = tarefa;
	}

	public List<Marco> getMarco() {
		return marco;
	}

	public List<PlanoComunicacao> getPlanoComunicacao() {
		return planoComunicacao;
	}

	public void setPlanoComunicacao(List<PlanoComunicacao> planoComunicacao) {
		this.planoComunicacao = planoComunicacao;
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

	public CheckListArtefatos getCheckListArtefatos() {
		return checkListArtefatos;
	}

	public void setCheckListArtefatos(CheckListArtefatos checkListArtefatos) {
		this.checkListArtefatos = checkListArtefatos;
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
	
	@Override
	public String toString() {
		return getNome();
	}

	public PlanoMedicao getPlanoMedicao() {
		return planoMedicao;
	}

	public void setPlanoMedicao(PlanoMedicao planoMedicao) {
		this.planoMedicao = planoMedicao;
	}

	public PoliticaOrganizacional getPoliticaOrganizacional() {
		return politicaOrganizacional;
	}

	public void setPoliticaOrganizacional(PoliticaOrganizacional politicaOrganizacional) {
		this.politicaOrganizacional = politicaOrganizacional;
	}

	public String getArqXML() {
		return arqXML;
	}

	public void setArqXML(String arqXML) {
		this.arqXML = arqXML;
	}

}
