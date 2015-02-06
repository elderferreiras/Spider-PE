package br.ufpa.spider.pe.model.set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="email")
public class Email {
	@Id
	@Column(name="id_email")
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@Column(name="servidorEnvio")
	String servidorEnvio;
	
	@Column(name="porta")
	String porta;
	
	@Column(name="protocolo")
	String protocolo;
	
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

	public String getServidorEnvio() {
		return servidorEnvio;
	}

	public void setServidorEnvio(String servidorEnvio) {
		this.servidorEnvio = servidorEnvio;
	}

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
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
