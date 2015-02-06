package br.ufpa.spider.pe.model.set.dao;

import java.util.List;

import br.ufpa.spider.pe.model.dao.GenericDAO;
import br.ufpa.spider.pe.model.set.Email;

public class EmailDAO extends GenericDAO {

public static void createEmail(Email ferramentaEstimativa){
	     create(ferramentaEstimativa);
	}
	
	public static void updateEmail(Email ferramentaEstimativa){
	     update(ferramentaEstimativa);
	}
	
	public static void removeEmail(Email ferramentaEstimativa){
	     remove(ferramentaEstimativa);
	}
	
	public static  List<Email> findAll(){
		List<Email> list = findAll("email");
	    return list;
	}
	
	
	
	
	public static Email findById(int id)
	{
		for (Email ferramentaEstimativa: findAll()){
			if(ferramentaEstimativa.getId() ==id){
				return ferramentaEstimativa;
			}
		}
		return null;
	}

}
