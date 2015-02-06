package br.ufpa.spider.pe.model.set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="svn")
public class SVN {
	@Id
	@Column(name="id_svn")
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@Column(name="host")
	String host;

	@Column(name="diretorio")
	String diretorio;

	@Column(name="porta")
	String porta;

	@Column(name="protocolo")
	String protocolo;
	
	@Column(name="login")
	String login;
	
	@Column(name="senha")
	String senha;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDiretorio() {
		return diretorio;
	}

	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
