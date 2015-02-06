package br.ufpa.spider.pe.model.dao;

import java.util.List;

import br.ufpa.spider.pe.model.Risco;

public class RiscoDAO extends GenericDAO {
	public static void createRisco(Risco risco){
	     create(risco);
	}
	
	public static void updateRisco(Risco risco){
	     update(risco);
	}
	
	public static void removeRisco(Risco risco){
	     remove(risco);
	}
	
	public static  List<Risco> findAll(){
		List<Risco> list = findAll("risco");
	    return list;
	}
	
	
	public static Risco findByName(String name)
	{
		Risco risco = null;
		for (Risco user: findAll()){
			if(user.getNome().contains(name.trim())){
				risco = user;
				break;
			}
		}
		
		return risco;
	}
	
	public static Risco findById(int id)
	{
		for (Risco risco: findAll()){
			if(risco.getId() ==id){
				return risco;
			}
		}
		return null;
	}

}
