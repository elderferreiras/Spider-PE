package br.ufpa.spider.pe.model.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.ufpa.spider.pe.model.Atividade;
import br.ufpa.spider.pe.model.Campo;
import br.ufpa.spider.pe.model.Capacidade;
import br.ufpa.spider.pe.model.CheckListArtefatos;
import br.ufpa.spider.pe.model.CheckListProcesso;
import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.GerenteProcesso;
import br.ufpa.spider.pe.model.Hardware;
import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.Iteracao;
import br.ufpa.spider.pe.model.JoinFork;
import br.ufpa.spider.pe.model.Log;
import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.Medicao;
import br.ufpa.spider.pe.model.OperadorLogico;
import br.ufpa.spider.pe.model.Papel;
import br.ufpa.spider.pe.model.PlanoComunicacao;
import br.ufpa.spider.pe.model.PlanoMedicao;
import br.ufpa.spider.pe.model.PoliticaOrganizacional;
import br.ufpa.spider.pe.model.Problema;
import br.ufpa.spider.pe.model.Procedimento;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.ProdutoTrabalho;
import br.ufpa.spider.pe.model.RevisaoArtefatos;
import br.ufpa.spider.pe.model.RevisaoProcesso;
import br.ufpa.spider.pe.model.Risco;
import br.ufpa.spider.pe.model.Software;
import br.ufpa.spider.pe.model.Stakeholder;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.Tipo;
import br.ufpa.spider.pe.model.Transicao;
import br.ufpa.spider.pe.model.Treinamento;
import br.ufpa.spider.pe.model.set.Database;
import br.ufpa.spider.pe.model.set.Email;
import br.ufpa.spider.pe.model.set.FerramentaEstimativa;
import br.ufpa.spider.pe.model.set.RedMine;
import br.ufpa.spider.pe.model.set.SVN;
import br.ufpa.spider.pe.model.set.SpiderCL;
import br.ufpa.spider.pe.model.set.SpiderMPlan;
import br.ufpa.spider.pe.model.set.Usuario;
import br.ufpa.spider.pe.model.set.XML;

public class DataBaseManager {
	private static EntityManagerFactory emf = null;
	private static EntityManager em = null;
	
	
	private final Class<?>[] CLASSES = new Class<?>[]{
			PoliticaOrganizacional.class, 
			Processo.class, 
			Procedimento.class, 
			ProdutoTrabalho.class, 
			PlanoComunicacao.class,
			Atividade.class,
			Campo.class,
			Capacidade.class,
			Fase.class,
			Hardware.class,
			Humano.class,
			Iteracao.class,
			Marco.class,
			Problema.class,
			Risco.class,
			Software.class,
			Stakeholder.class,
			Tarefa.class,
			Tipo.class,
			Treinamento.class,
			Papel.class,
			OperadorLogico.class, 
			CheckListProcesso.class,
			CheckListArtefatos.class,
			RevisaoProcesso.class,
			RevisaoArtefatos.class,
			Medicao.class,
			PlanoMedicao.class,
			FerramentaEstimativa.class,
			Database.class,
			Email.class,
			GerenteProcesso.class,
			RedMine.class,
			SpiderCL.class,
			SpiderMPlan.class,
			SVN.class,
			Usuario.class,
			XML.class,
			Transicao.class,
			JoinFork.class,
			Log.class
			};

	
	private DataBaseManager(){
		emf = DynamicPersistenceUnits.createEMF(CLASSES, "localhost", "3306" , "spider_pe", "root", "root");
		em = emf.createEntityManager();
		}
	
	//m�todo que pega a inst�ncia �nica
	private synchronized static EntityManagerFactory getManagerFactory() {
		if(emf==null)
			new DataBaseManager();
		
		return emf;
	}
	
	public static EntityManager getDataBaseSession(){
		if(em==null)
			em = getManagerFactory().createEntityManager();
		return em;
	}

	public static EntityManager getEm() {
		return em;
	}

	public static void setEm(EntityManager em) {
		DataBaseManager.em = em;
	}

}
