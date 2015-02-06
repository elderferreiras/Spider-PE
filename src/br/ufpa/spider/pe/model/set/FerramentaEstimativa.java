package br.ufpa.spider.pe.model.set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="ferramenta_estimativa")
public class FerramentaEstimativa {
	@Id
	@Column(name="idatividade")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;	
	
	@Column(name="ucp")
	String ucp;
	
	@Column(name="apf")
	String apf;
	
	@Column(name="cocomo")
	String cocomo;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUcp() {
		return ucp;
	}

	public void setUcp(String ucp) {
		this.ucp = ucp;
	}

	public String getApf() {
		return apf;
	}

	public void setApf(String apf) {
		this.apf = apf;
	}

	public String getCocomo() {
		return cocomo;
	}

	public void setCocomo(String cocomo) {
		this.cocomo = cocomo;
	}

}
