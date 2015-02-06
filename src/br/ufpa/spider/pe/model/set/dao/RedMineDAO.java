package br.ufpa.spider.pe.model.set.dao;

import java.util.List;

import br.ufpa.spider.pe.model.dao.GenericDAO;
import br.ufpa.spider.pe.model.set.RedMine;

public class RedMineDAO extends GenericDAO {

public static void createRedMine(RedMine ferramentaEstimativa){
	     create(ferramentaEstimativa);
	}
	
	public static void updateRedMine(RedMine ferramentaEstimativa){
	     update(ferramentaEstimativa);
	}
	
	public static void removeRedMine(RedMine ferramentaEstimativa){
	     remove(ferramentaEstimativa);
	}
	
	public static  List<RedMine> findAll(){
		List<RedMine> list = findAll("redmine");
	    return list;
	}
	
	public static RedMine findById(int id)
	{
		for (RedMine ferramentaEstimativa: findAll()){
			if(ferramentaEstimativa.getId() ==id){
				return ferramentaEstimativa;
			}
		}
		return null;
	}

}
