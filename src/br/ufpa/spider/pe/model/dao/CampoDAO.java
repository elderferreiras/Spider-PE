package br.ufpa.spider.pe.model.dao;

import java.util.List;

import br.ufpa.spider.pe.model.Campo;

public class CampoDAO extends GenericDAO {
	
	public static void createCampo(Campo campo){
	     create(campo);
	}
	
	public static void updateCampo(Campo campo){
	     update(campo);
	}
	
	public static void removeCampo(Campo campo){
	     remove(campo);
	}
	
	public static  List<Campo> findAll(){
		List<Campo> list = findAll("campo");
	    return list;
	}
	
	
	public static Campo findByName(String name)
	{
		Campo campo = null;
		for (Campo user: findAll()){
			if(user.getNome().contains(name.trim())){
				campo = user;
				break;
			}
		}
		
		return campo;
	}
	
	public static Campo findById(int id)
	{
		for (Campo campo: findAll()){
			if(campo.getId() ==id){
				return campo;
			}
		}
		return null;
	}

}

