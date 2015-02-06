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

@Entity(name="treinamento")
public class Treinamento {

	@Id
	@Column(name="idtreinamento")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="perfil", length = 80)
	private String perfil;
	
	@Column(name="tipoTreinamento", length = 80)
	private String tipoTreinamento;
	
	@Column(name="nome", length = 100)
	private String  nome;
	
	@Column(name="local", length = 100)
	private String local;
	
	@Column(name="finalidade", length = 100)
	private String finalidade;
	
	@Column(name="unidadeCargaHoraria", length = 100)
	private String unidadeCargaHoraria;
	
	@Column(name="carga_horaria")
	private String cargaHoraria;
	
	@Column(name="inicio")
	private String inicio;
	
	@Column(name="termino")
	private String termino;
	
	@ManyToMany(fetch = FetchType.LAZY)  
	@JoinTable(name = "treinamento_x_recurso_humano",  
	joinColumns = { @JoinColumn(name = "idtreinamento",  
	updatable =  false) }, inverseJoinColumns = {  
	@JoinColumn(name = "idhumano", updatable = false) })
	//@Cascade(value=CascadeType.ALL)
	private List<Humano> humano = new ArrayList<Humano>();
	
	@ManyToOne
	@JoinColumn(name = "tipo")
	private Tipo tipo;

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public List<Humano> getHumano() {
		return humano;
	}

	public void setHumano(List<Humano> humano) {
		this.humano = humano;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getTipoTreinamento() {
		return tipoTreinamento;
	}

	public void setTipoTreinamento(String tipo) {
		this.tipoTreinamento = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getTermino() {
		return termino;
	}

	public void setTermino(String termino) {
		this.termino = termino;
	}

	public String getFinalidade() {
		return finalidade;
	}

	public void setFinalidade(String finalidade) {
		this.finalidade = finalidade;
	}

	public String getUnidadeCargaHoraria() {
		return unidadeCargaHoraria;
	}

	public void setUnidadeCargaHoraria(String unidadeCargaHoraria) {
		this.unidadeCargaHoraria = unidadeCargaHoraria;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
