package br.ufpa.spider.pe.model.dao;

import java.util.List;

import br.ufpa.spider.pe.model.Stakeholder;

public class StakerholderDAO extends GenericDAO {
	public static void createStakeholder(Stakeholder Stakeholder){
	     create(Stakeholder);
	}
	
	public static void updateStakeholder(Stakeholder Stakeholder){
	     update(Stakeholder);
	}
	
	public static void removeStakeholder(Stakeholder Stakeholder){
	     remove(Stakeholder);
	}
	
	public static  List<Stakeholder> findAll(){
		List<Stakeholder> list = findAll("Stakeholder");
	    return list;
	}
	
	
	public static Stakeholder findByName(String name)
	{
		Stakeholder Stakeholder = null;
		for (Stakeholder user: findAll()){
			if(user.getNome().contains(name.trim())){
				Stakeholder = user;
				break;
			}
		}
		
		return Stakeholder;
	}
	
	public static Stakeholder findById(int id)
	{
		for (Stakeholder Stakeholder: findAll()){
			if(Stakeholder.getId() ==id){
				return Stakeholder;
			}
		}
		return null;
	}
}
