package br.ufpa.spider.pe.model.set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="database")
public class Database {
	@Id
	@Column(name="id_database")
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@Column(name="url_bd")
	String url_bd;
	
	@Column(name="porta")
	String porta;
	
	@Column(name="usuario")
	String usuario;
	
	@Column(name="senha")
	String senha;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl_bd() {
		return url_bd;
	}

	public void setUrl_bd(String url_bd) {
		this.url_bd = url_bd;
	}

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
