package br.ufpa.spider.pe.model.dao;

import java.util.List;

import br.ufpa.spider.pe.model.CheckListArtefatos;

public class CheckListArtefatosDAO extends GenericDAO {

public static void createCheckListArtefatos(CheckListArtefatos checkListArtefatos){
	     create(checkListArtefatos);
	}
	
	public static void updateCheckListArtefatos(CheckListArtefatos checkListArtefatos){
	     update(checkListArtefatos);
	}
	
	public static void removeCheckListArtefatos(CheckListArtefatos checkListArtefatos){
	     remove(checkListArtefatos);
	}
	
	public static  List<CheckListArtefatos> findAll(){
		List<CheckListArtefatos> list = findAll("checkListArtefatos");
	    return list;
	}
	
	
	public static CheckListArtefatos findByName(String name)
	{
		CheckListArtefatos checkListArtefatos = null;
		for (CheckListArtefatos user: findAll()){
			if(user.getNome().contains(name.trim())){
				checkListArtefatos = user;
				break;
			}
		}
		
		return checkListArtefatos;
	}
	
	public static CheckListArtefatos findById(int id)
	{
		for (CheckListArtefatos checkListArtefatos: findAll()){
			if(checkListArtefatos.getId() ==id){
				return checkListArtefatos;
			}
		}
		return null;
	}

}
