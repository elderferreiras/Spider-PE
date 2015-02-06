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
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name = "gerente_processo")
public class GerenteProcesso {
	@Id
	@Column(name = "id_gerente_processo")
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@OneToMany(mappedBy="gerente_processo")
	private List<Processo> processo = new ArrayList<Processo>();
	
	@OneToOne
	@JoinColumn(name = "humano", updatable = true)
	private Humano humano;

	public List<Processo> getProcesso() {
		return processo;
	}

	public void setProcesso(List<Processo> processo) {
		this.processo = processo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getHumano().getNome();
	}

	public Humano getHumano() {
		return humano;
	}

	public void setHumano(Humano humano) {
		this.humano = humano;
	}
}
