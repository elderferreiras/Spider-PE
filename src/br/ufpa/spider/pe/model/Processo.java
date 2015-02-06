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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name = "processo")
public class Processo {

	@Id
	@Column(name = "id_processo")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "id_component")
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
	
	@Column(name = "estado")
	private String estado;

	@Column(name = "status")
	private String status;
	
	@Column(name =  "arq_xml")
	private String arqXML;

	@OneToOne(mappedBy="processo")
	private PoliticaOrganizacional politicaOrganizacional;
	
	@OneToMany(mappedBy="processo")
	@Cascade(value=CascadeType.ALL)
	private List<Fase> fase = new ArrayList<Fase>();

	@OneToMany(mappedBy="processo")
	private List<Stakeholder> stakeHolder = new ArrayList<Stakeholder>();

	@OneToOne(mappedBy = "processo")
	@Cascade(value=CascadeType.ALL)
	private PlanoMedicao planoMedicao;
	
	@ManyToMany(fetch = FetchType.LAZY)  
	@JoinTable(name = "processo_x_humano",  
	joinColumns = { @JoinColumn(name = "idprocesso",  
	updatable =  false) }, inverseJoinColumns = {  
	@JoinColumn(name = "idhumano", updatable = false) })
	//@Cascade(value=CascadeType.ALL)
	private List<Humano> humano = new ArrayList<Humano>();	

	@OneToMany(mappedBy="processo")
	@Cascade(value=CascadeType.ALL)
	private List<RevisaoProcesso> revisaoProcessos = new ArrayList<RevisaoProcesso>();	

	@ManyToOne
	@JoinColumn(name = "gerente_processo", updatable = true)
	private GerenteProcesso gerente_processo;	

	@Lob
	@Column(name="processo_xml", columnDefinition="longblob")
	byte[] processo_xml;
	
	public List<RevisaoProcesso> getRevisaoProcessos() {
		return revisaoProcessos;
	}

	public void setRevisaoProcessos(List<RevisaoProcesso> revisaoProcessos) {
		this.revisaoProcessos = revisaoProcessos;
	}

	public List<Stakeholder> getStakeHolder() {
		return stakeHolder;
	}

	public void setStakeHolder(List<Stakeholder> stakeHolder) {
		this.stakeHolder = stakeHolder;
	}

	public void setIdComponent(Integer idComponent) {
		this.idComponent = idComponent;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Fase> getFase() {
		return fase;
	}

	public void setFase(List<Fase> fase) {
		this.fase = fase;
	}

	public PoliticaOrganizacional getPoliticaOrganizacional() {
		return politicaOrganizacional;
	}

	public void setPoliticaOrganizacional(
			PoliticaOrganizacional politicaOrganizacional) {
		this.politicaOrganizacional = politicaOrganizacional;
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

	public PlanoMedicao getPlanoMedicao() {
		return planoMedicao;
	}

	public void setPlanoMedicao(PlanoMedicao planoMedicao) {
		this.planoMedicao = planoMedicao;
	}

	public List<Humano> getHumano() {
		return humano;
	}

	public void setHumano(List<Humano> humano) {
		this.humano = humano;
	}

	public GerenteProcesso getGerenteProcesso() {
		return gerente_processo;
	}

	public void setGerenteProcesso(GerenteProcesso gerenteProcesso) {
		this.gerente_processo = gerenteProcesso;
	}

	public String getArqXML() {
		return arqXML;
	}

	public void setArqXML(String arqXML) {
		this.arqXML = arqXML;
	}
	public byte[] getProcesso_xml() {
		return processo_xml;
	}

	public void setProcesso_xml(byte[] processo_xml) {
		this.processo_xml = processo_xml;
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
