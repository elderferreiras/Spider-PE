package br.ufpa.spider.pe.model.dao;

import java.util.List;

import br.ufpa.spider.pe.model.Tipo;

public class TipoDAO extends GenericDAO {
	public static void createTipo(Tipo tipo){
	     create(tipo);
	}
	
	public static void updateTipo(Tipo tipo){
	     update(tipo);
	}
	
	public static void removeTipo(Tipo tipo){
	     remove(tipo);
	}
	
	public static  List<Tipo> findAll(){
		List<Tipo> list = findAll("tipo");
	    return list;
	}
	
	
	public static Tipo findByName(String name)
	{
		Tipo tipo = null;
		for (Tipo user: findAll()){
			if(user.getNome().contains(name.trim())){
				tipo = user;
				break;
			}
		}
		
		return tipo;
	}
	
	public static Tipo findById(int id)
	{
		for (Tipo politica: findAll()){
			if(politica.getId() ==id){
				return politica;
			}
		}
		return null;
	}

}
