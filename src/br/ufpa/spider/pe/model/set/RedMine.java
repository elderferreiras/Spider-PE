package br.ufpa.spider.pe.model.set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="redmine")
public class RedMine {
	@Id
	@Column(name="id_redmine")
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@Column(name="urlFerramenta")
	String urlFerramenta;
	
	@Column(name="urlBanco")
	String urlBanco;
	
	@Column(name="usuarioBanco")
	String usuarioBanco;
	
	@Column(name="senhaBanco")
	String senhaBanco;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrlFerramenta() {
		return urlFerramenta;
	}

	public void setUrlFerramenta(String urlFerramenta) {
		this.urlFerramenta = urlFerramenta;
	}

	public String getUrlBanco() {
		return urlBanco;
	}

	public void setUrlBanco(String urlBanco) {
		this.urlBanco = urlBanco;
	}

	public String getUsuarioBanco() {
		return usuarioBanco;
	}

	public void setUsuarioBanco(String usuarioBanco) {
		this.usuarioBanco = usuarioBanco;
	}

	public String getSenhaBanco() {
		return senhaBanco;
	}

	public void setSenhaBanco(String senhaBanco) {
		this.senhaBanco = senhaBanco;
	}

}
