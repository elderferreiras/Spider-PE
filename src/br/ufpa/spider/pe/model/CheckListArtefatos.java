package br.ufpa.spider.pe.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name="checkListArtefatos")
public class CheckListArtefatos {

		@Id
		@Column(name="idchecklistartefatos")
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int id;
		
		@Column(name="nome", length = 45)
		private String nome;
		
		@Column(name="caminho")
		private String caminho;
		
		@OneToOne(mappedBy = "checkListArtefatos")
		private ProdutoTrabalho produtoTrabalho;
		
		@OneToMany(mappedBy="checkListArtefatos")
		@Cascade(value=CascadeType.ALL)
		private List<RevisaoArtefatos> revisaoArtefatos = new ArrayList<RevisaoArtefatos>();

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

		public String getCaminho() {
			return caminho;
		}

		public void setCaminho(String caminho) {
			this.caminho = caminho;
		}

		public ProdutoTrabalho getProdutoTrabalho() {
			return produtoTrabalho;
		}

		public void setProdutoTrabalho(ProdutoTrabalho produtoTrabalho) {
			this.produtoTrabalho = produtoTrabalho;
		}

		public List<RevisaoArtefatos> getRevisaoArtefatos() {
			return revisaoArtefatos;
		}

		public void setRevisaoArtefatos(List<RevisaoArtefatos> revisaoArtefatos) {
			this.revisaoArtefatos = revisaoArtefatos;
		}
		
		public String toString(){
			return getNome();
		}
		
		
}
