package br.ufpa.spider.pe.model.set.dao;

import java.util.List;

import br.ufpa.spider.pe.model.dao.GenericDAO;
import br.ufpa.spider.pe.model.set.FerramentaEstimativa;

public class FerramentaEstimativaDAO extends GenericDAO {

public static void createFerramentaEstimativa(FerramentaEstimativa ferramentaEstimativa){
	     create(ferramentaEstimativa);
	}
	
	public static void updateFerramentaEstimativa(FerramentaEstimativa ferramentaEstimativa){
	     update(ferramentaEstimativa);
	}
	
	public static void removeFerramentaEstimativa(FerramentaEstimativa ferramentaEstimativa){
	     remove(ferramentaEstimativa);
	}
	
	public static  List<FerramentaEstimativa> findAll(){
		List<FerramentaEstimativa> list = findAll("ferramenta_estimativa");
	    return list;
	}
	
	public static FerramentaEstimativa findById(int id)
	{
		for (FerramentaEstimativa ferramentaEstimativa: findAll()){
			if(ferramentaEstimativa.getId() ==id){
				return ferramentaEstimativa;
			}
		}
		return null;
	}

}
