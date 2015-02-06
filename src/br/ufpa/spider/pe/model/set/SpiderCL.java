package br.ufpa.spider.pe.model.set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="spider_cl")
public class SpiderCL {
	@Id
	@Column(name="id_spider_cl")
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@Column(name="url_ferramenta")
	String url_ferramenta;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl_ferramenta() {
		return url_ferramenta;
	}

	public void setUrl_ferramenta(String url_ferramenta) {
		this.url_ferramenta = url_ferramenta;
	}
}
