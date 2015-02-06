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

import br.ufpa.spider.pe.model.dao.TarefaDAO;
import br.ufpa.spider.pe.view.pm.logic.TimeController;

@Entity(name="marco")
public class Marco {
	@Id
	@Column(name="idmarco")
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
	
	@Column(name="inicio_real")
	private String inicioReal;
	
	@Column(name="fim_real")
	private String fimReal;
	
	@Column(name="carga_horaria")
	private String cargaHoraria;
	
	@Column(name="und_carga_horaria", length = 20)
	private String undCargaHoraria;
	
	@Column(name="clock_interno")
	private String clockInterno;
	

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
	
	@Column(name="qtd_esforco")
	private String qtdEsforco;
	
	@Column(name="und_esforco", length = 20)
	private String undEsforco;
	
	@Column(name = "timeStart")
	private Double timeStart;
	
	@ManyToOne
	@JoinColumn(name = "fase")
	private Fase fase;
	
	
	@Column(name="habilidadesRequeridas")
	private String habilidadesRequeridas;
	
	@ManyToOne
	@JoinColumn(name="produtoTrabalho")
	private ProdutoTrabalho produtoTrabalho;
	
	@OneToMany(mappedBy="marco")
	@Cascade(value=CascadeType.ALL)
	private List<Medicao> medicoes = new ArrayList<Medicao>();
	
	@OneToMany(mappedBy="marco")
	@Cascade(value=CascadeType.ALL)
	private List<RevisaoProcesso> revisaoProcessos = new ArrayList<RevisaoProcesso>();
	
	@OneToMany(mappedBy="marco")
	@Cascade(value=CascadeType.ALL)
	private List<RevisaoArtefatos> revisaoArtefatos = new ArrayList<RevisaoArtefatos>();
	
	@ManyToMany(fetch = FetchType.LAZY)  
	@JoinTable(name = "softwares_x_marco",  
	joinColumns = { @JoinColumn(name = "idmarco",  
	updatable =  false) }, inverseJoinColumns = {  
	@JoinColumn(name = "idsoftware", updatable = false) })
	@Cascade(value=CascadeType.ALL)
	private List<Software> softwares = new ArrayList<Software>();
	
	@ManyToMany(fetch = FetchType.LAZY)  
	@JoinTable(name = "hardwares_x_marco",  
	joinColumns = { @JoinColumn(name = "idmarco",  
	updatable =  false) }, inverseJoinColumns = {  
	@JoinColumn(name = "idhardware", updatable = false) })
	//@Cascade(value=CascadeType.ALL)
	private List<Hardware> hardwares = new ArrayList<Hardware>();
	
	@OneToMany(mappedBy="marco")
	@Cascade(value=CascadeType.ALL)
	private List<Papel> papeis = new ArrayList<Papel>();

	public ProdutoTrabalho getProdutoTrabalho() {
		return produtoTrabalho;
	}

	public List<Medicao> getMedicoes() {
		return medicoes;
	}

	public void setMedicoes(List<Medicao> medicoes) {
		this.medicoes = medicoes;
	}

	public List<RevisaoProcesso> getRevisaoProcessos() {
		return revisaoProcessos;
	}

	public void setRevisaoProcessos(List<RevisaoProcesso> revisaoProcessos) {
		this.revisaoProcessos = revisaoProcessos;
	}

	public List<RevisaoArtefatos> getRevisaoArtefatos() {
		return revisaoArtefatos;
	}

	public void setRevisaoArtefatos(List<RevisaoArtefatos> revisaoArtefatos) {
		this.revisaoArtefatos = revisaoArtefatos;
	}

	public void setProdutoTrabalho(ProdutoTrabalho produtoTrabalho) {
		this.produtoTrabalho = produtoTrabalho;
	}

	public Fase getFase() {
		return fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
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

	public String getInicioReal() {
		return inicioReal;
	}

	public void setInicioReal(String inicioReal) {
		this.inicioReal = inicioReal;
	}

	public String getFimReal() {
		return fimReal;
	}

	public void setFimReal(String fimReal) {
		this.fimReal = fimReal;
	}

	public String getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getUndCargaHoraria() {
		return undCargaHoraria;
	}

	public void setUndCargaHoraria(String undCargaHoraria) {
		this.undCargaHoraria = undCargaHoraria;
	}

	public String getClockInterno() {
		Double tempoCorrente = TimeController.getTempoCorrente();
		Double clock = TarefaDAO.convertCargaStringtoDouble(clockInterno)*60 + (tempoCorrente - getTimeStart());
		clock = clock/60; // minuto para hora
		setClockInterno(clock.toString());
		setTimeStart(tempoCorrente);
		return clockInterno;
	}

	public void setClockInterno(String clockInterno) {
		this.clockInterno = clockInterno;
	}

	public String getQtdEsforco() {
		return qtdEsforco;
	}

	public void setQtdEsforco(String qtdEsforco) {
		this.qtdEsforco = qtdEsforco;
	}

	public String getUndEsforco() {
		return undEsforco;
	}

	public void setUndEsforco(String undEsforco) {
		this.undEsforco = undEsforco;
	}

	public Double getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Double timeStart) {
		this.timeStart = timeStart;
	}

	public List<Software> getSoftwares() {
		return softwares;
	}

	public void setSoftwares(List<Software> softwares) {
		this.softwares = softwares;
	}

	public List<Hardware> getHardwares() {
		return hardwares;
	}

	public void setHardwares(List<Hardware> hardwares) {
		this.hardwares = hardwares;
	}

	public List<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}

	public String getHabilidadesRequeridas() {
		return habilidadesRequeridas;
	}

	public void setHabilidadesRequeridas(String habilidadesRequeridas) {
		this.habilidadesRequeridas = habilidadesRequeridas;
	}

}