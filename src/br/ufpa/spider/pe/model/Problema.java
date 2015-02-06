package br.ufpa.spider.pe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name="problema")
public class Problema {
	@Id
	@Column(name="idproblema")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="ticket")
	private String ticket;
	
	@Column(name="origem")
	private String origem;
	
	@Column(name="afetado")
	private String afetado;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getAfetado() {
		return afetado;
	}

	public void setAfetado(String afetado) {
		this.afetado = afetado;
	}
	
	@Override
	public String toString() {
		return getTicket();
	}
	
}
