package br.ufpa.spider.pe.model.dao;

import java.util.List;

import br.ufpa.spider.pe.model.PlanoMedicao;

public class PlanoMedicaoDAO extends GenericDAO {

public static void createPlanoMedicao(PlanoMedicao planoMedicao){
	     create(planoMedicao);
	}
	
	public static void updatePlanoMedicao(PlanoMedicao planoMedicao){
	     update(planoMedicao);
	}
	
	public static void removePlanoMedicao(PlanoMedicao planoMedicao){
	     remove(planoMedicao);
	}
	
	public static  List<PlanoMedicao> findAll(){
		List<PlanoMedicao> list = findAll("planoMedicao");
	    return list;
	}
	
	
	public static PlanoMedicao findByName(String name)
	{
		PlanoMedicao planoMedicao = null;
		for (PlanoMedicao user: findAll()){
			if(user.getNome().contains(name.trim())){
				planoMedicao = user;
				break;
			}
		}
		
		return planoMedicao;
	}
	
	public static PlanoMedicao findById(int id)
	{
		for (PlanoMedicao planoMedicao: findAll()){
			if(planoMedicao.getId() ==id){
				return planoMedicao;
			}
		}
		return null;
	}

}
