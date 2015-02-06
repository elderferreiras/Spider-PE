package br.ufpa.spider.pe.model.set.dao;

import java.util.List;

import br.ufpa.spider.pe.model.dao.GenericDAO;
import br.ufpa.spider.pe.model.set.SVN;

public class SVNDAO extends GenericDAO {

public static void createSVN(SVN ferramentaEstimativa){
	     create(ferramentaEstimativa);
	}
	
	public static void updateSVN(SVN ferramentaEstimativa){
	     update(ferramentaEstimativa);
	}
	
	public static void removeSVN(SVN ferramentaEstimativa){
	     remove(ferramentaEstimativa);
	}
	
	public static  List<SVN> findAll(){
		List<SVN> list = findAll("svn");
	    return list;
	}
	
	public static SVN findById(int id)
	{
		for (SVN ferramentaEstimativa: findAll()){
			if(ferramentaEstimativa.getId() ==id){
				return ferramentaEstimativa;
			}
		}
		return null;
	}

}
