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

@Entity(name="stakeholder")
public class Stakeholder {

	@Id
	@Column(name="idstakeholder")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="nome", length = 80)
	private String nome;
	
	@Column(name="desc_interesse", length = 200)
	private String descInteresse;
	
	@Column(name="email", length = 50)
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "processo")
	private Processo processo;
	
	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
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

	public String getDescInteresse() {
		return descInteresse;
	}

	public void setDescInteresse(String descInteresse) {
		this.descInteresse = descInteresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
