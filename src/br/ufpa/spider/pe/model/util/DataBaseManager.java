package br.ufpa.spider.pe.model.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
import br.ufpa.spider.pe.view.util.PasswordSecurity;

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
		String HOST_NAME = "";
		String PORT = "";
		String USER = "";
		String PASS = "";
		
		try {
			File fXmlFile = new File("databaseConfig.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Root element :"
					+ doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("database");

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :"
						+ nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					HOST_NAME = eElement.getElementsByTagName("hostName").item(0).getTextContent();
					PORT = eElement.getElementsByTagName("port").item(0).getTextContent();
					USER = eElement.getElementsByTagName("userName").item(0).getTextContent();
					PASS = PasswordSecurity.decrypt(eElement.getElementsByTagName("password").item(0).getTextContent());

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		emf = DynamicPersistenceUnits.createEMF(CLASSES, HOST_NAME, PORT , "spider_pe", USER, PASS);
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
