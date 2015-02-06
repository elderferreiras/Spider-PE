package br.ufpa.spider.pe.model.set.dao;

import java.util.List;

import br.ufpa.spider.pe.model.dao.GenericDAO;
import br.ufpa.spider.pe.model.set.XML;

public class XMLDAO extends GenericDAO {

public static void createXML(XML ferramentaEstimativa){
	     create(ferramentaEstimativa);
	}
	
	public static void updateXML(XML ferramentaEstimativa){
	     update(ferramentaEstimativa);
	}
	
	public static void removeXML(XML ferramentaEstimativa){
	     remove(ferramentaEstimativa);
	}
	
	public static  List<XML> findAll(){
		List<XML> list = findAll("xml");
	    return list;
	}
	
	public static XML findById(int id)
	{
		for (XML ferramentaEstimativa: findAll()){
			if(ferramentaEstimativa.getId() ==id){
				return ferramentaEstimativa;
			}
		}
		return null;
	}

}
