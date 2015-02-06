package br.ufpa.spider.pe.model.set.dao;

import java.util.List;

import br.ufpa.spider.pe.model.dao.GenericDAO;
import br.ufpa.spider.pe.model.set.Database;

public class DatabaseDAO extends GenericDAO {

public static void createDatabase(Database ferramentaEstimativa){
	     create(ferramentaEstimativa);
	}
	
	public static void updateDatabase(Database ferramentaEstimativa){
	     update(ferramentaEstimativa);
	}
	
	public static void removeDatabase(Database ferramentaEstimativa){
	     remove(ferramentaEstimativa);
	}
	
	public static  List<Database> findAll(){
		List<Database> list = findAll("database");
	    return list;
	}
	
	public static Database findById(int id)
	{
		for (Database ferramentaEstimativa: findAll()){
			if(ferramentaEstimativa.getId() ==id){
				return ferramentaEstimativa;
			}
		}
		return null;
	}

}
