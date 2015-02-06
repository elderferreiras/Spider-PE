package br.ufpa.spider.pe.model.set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="usuario")
public class Usuario {
	@Id
	@Column(name="id_usuario")
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@Column(name="nome")
	String nome;
	
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
	
	@Override
	public String toString() {
		return getLogin();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
