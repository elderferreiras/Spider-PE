package br.ufpa.spider.pe.model.dao;

import java.util.List;

import br.ufpa.spider.pe.model.Problema;

public class ProblemaDAO extends GenericDAO {
	public static void createProblema(Problema problema){
	     create(problema);
	}
	
	public static void updateProblema(Problema problema){
	     update(problema);
	}
	
	public static void removeProblema(Problema problema){
	     remove(problema);
	}
	
	public static  List<Problema> findAll(){
		List<Problema> list = findAll("problema");
	    return list;
	}
	
	
//	public static Problema findByName(String name)
//	{
//		Problema problema = null;
//		for (Problema user: findAll()){
//			if(user.getNome().contains(name.trim())){
//				problema = user;
//				break;
//			}
//		}
//		
//		return problema;
//	}
	
	public static Problema findById(int id)
	{
		for (Problema problema: findAll()){
			if(problema.getId() ==id){
				return problema;
			}
		}
		return null;
	}
}
