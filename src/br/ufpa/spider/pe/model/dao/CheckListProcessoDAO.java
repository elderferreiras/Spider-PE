package br.ufpa.spider.pe.model.dao;

import java.util.List;

import br.ufpa.spider.pe.model.CheckListProcesso;

public class CheckListProcessoDAO extends GenericDAO {

public static void createCheckListProcesso(CheckListProcesso checkListProcesso){
	     create(checkListProcesso);
	}
	
	public static void updateCheckListProcesso(CheckListProcesso checkListProcesso){
	     update(checkListProcesso);
	}
	
	public static void removeCheckListProcesso(CheckListProcesso checkListProcesso){
	     remove(checkListProcesso);
	}
	
	public static  List<CheckListProcesso> findAll(){
		List<CheckListProcesso> list = findAll("checklistprocesso");
	    return list;
	}
	
	
	public static CheckListProcesso findByName(String name)
	{
		CheckListProcesso checkListProcesso = null;
		for (CheckListProcesso user: findAll()){
			if(user.getNome().contains(name.trim())){
				checkListProcesso = user;
				break;
			}
		}
		
		return checkListProcesso;
	}
	
	public static CheckListProcesso findById(int id)
	{
		for (CheckListProcesso checkListProcesso: findAll()){
			if(checkListProcesso.getId() ==id){
				return checkListProcesso;
			}
		}
		return null;
	}

}
