package br.ufpa.spider.pe.model.set.dao;

import java.util.List;

import br.ufpa.spider.pe.model.dao.GenericDAO;
import br.ufpa.spider.pe.model.set.SpiderCL;

public class SpiderCLDAO extends GenericDAO {

public static void createSpiderCL(SpiderCL ferramentaEstimativa){
	     create(ferramentaEstimativa);
	}
	
	public static void updateSpiderCL(SpiderCL ferramentaEstimativa){
	     update(ferramentaEstimativa);
	}
	
	public static void removeSpiderCL(SpiderCL ferramentaEstimativa){
	     remove(ferramentaEstimativa);
	}
	
	public static  List<SpiderCL> findAll(){
		List<SpiderCL> list = findAll("spider_cl");
	    return list;
	}
	
	public static SpiderCL findById(int id)
	{
		for (SpiderCL ferramentaEstimativa: findAll()){
			if(ferramentaEstimativa.getId() ==id){
				return ferramentaEstimativa;
			}
		}
		return null;
	}

}
