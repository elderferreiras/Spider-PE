package br.ufpa.spider.pe.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.hql.ast.tree.Statement;

import java.util.List;
import br.ufpa.spider.pe.model.util.*;
import br.ufpa.spider.pe.model.util.DataBaseManager;

public class GenericDAO {
	static EntityManager em = DataBaseManager.getDataBaseSession();
	/**
	 * 
	 * @param Cria uma instancia de "object" no banco de dados
	 */
	public static void create (Object object)
	{
		 em.getTransaction().begin();
	     em.persist(object); // joga no banco de dados, se tiver entao cria outro, se n�o tiver ele cria
	     em.getTransaction().commit();
	   //  em.close();
	}
	/**
	 * 
	 * @param Atualiza a instancia de "object" no banco  de dados
	 */
	public static void update (Object object)
	{
		 em.getTransaction().begin();
	     em.merge(object); // se ID j� existir ele atualiza, se n�o cria o objeto (serve como persiste)
	     em.getTransaction().commit();
	    // em.close();
	}
	/**
	 * 
	 * @param Remove a instancia de "object" no banco de dados
	 */
	public static void remove (Object object)
	{
		 em.getTransaction().begin();
	     em.remove(object); // remove o objeto
	     em.getTransaction().commit();
	    // em.close();
	}
	/**
	 * 
	 * @param entity - identifica o nome de uma tabela
	 * @return Retorna uma lista que possua todos os elementos de uma tabela
	 */
	public static List findAll (String entity)
	{
		List list =null;		
		em.getTransaction().begin();		
		list = em.createQuery("SELECT a FROM "+entity+" a").getResultList();
		em.getTransaction().commit();
		//em.close();
		
		return list;
	}
}
