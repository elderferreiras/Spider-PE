package br.ufpa.spider.pe.model.set.dao;

import java.util.List;

import br.ufpa.spider.pe.model.dao.GenericDAO;
import br.ufpa.spider.pe.model.set.SpiderMPlan;

public class SpiderMPlanDAO extends GenericDAO {

public static void createSpiderMPlan(SpiderMPlan ferramentaEstimativa){
	     create(ferramentaEstimativa);
	}
	
	public static void updateSpiderMPlan(SpiderMPlan ferramentaEstimativa){
	     update(ferramentaEstimativa);
	}
	
	public static void removeSpiderMPlan(SpiderMPlan ferramentaEstimativa){
	     remove(ferramentaEstimativa);
	}
	
	public static  List<SpiderMPlan> findAll(){
		List<SpiderMPlan> list = findAll("spider_mplan");
	    return list;
	}
	
	public static SpiderMPlan findById(int id)
	{
		for (SpiderMPlan ferramentaEstimativa: findAll()){
			if(ferramentaEstimativa.getId() ==id){
				return ferramentaEstimativa;
			}
		}
		return null;
	}

}
