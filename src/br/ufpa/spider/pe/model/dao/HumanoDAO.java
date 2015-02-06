package br.ufpa.spider.pe.model.dao;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Humano;

public class HumanoDAO extends GenericDAO {
	public static void createHumano(Humano humano){
	     create(humano);
	}
	
	public static void updateHumano(Humano humano){
	     update(humano);
	}
	
	public static void removeHumano(Humano humano){
	     remove(humano);
	}
	
	public static  List<Humano> findAll(){
		List<Humano> list = findAll("humano");
	    return list;
	}
	
	
	public static Humano findByName(String name)
	{
		Humano humano = null;
		for (Humano user: findAll()){
			if(user.getNome().equals(name.trim())){
				humano = user;
				break;
			}
		}
		
		return humano;
	}
	
	public static Humano findByEmail(String email)
	{
		Humano humano = null;
		for (Humano user: findAll()){
			if(!(user.getEmail() == null))
				if(user.getEmail().equals(email.trim())){
				humano = user;
				break;
			}
		}		
		return humano;
	}
	
	public static Humano findById(int id)
	{
		for (Humano humano: findAll()){
			if(humano.getId() ==id){
				return humano;
			}
		}
		return null;
	}
	
	public static Humano findByIdComponent(Integer idComponent){
		for(Humano h: findAll()){
			if(h.getIdHumansResources() == idComponent){
				return h;
			}
		}
		return null;
	}
}
