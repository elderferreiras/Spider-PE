package br.ufpa.spider.pe.model.dao;

import java.util.List;

import br.ufpa.spider.pe.model.Capacidade;

public class CapacidadeDAO extends GenericDAO {

public static void createCapacidade(Capacidade capacidade){
	     create(capacidade);
	}
	
	public static void updateCapacidade(Capacidade capacidade){
	     update(capacidade);
	}
	
	public static void removeCapacidade(Capacidade capacidade){
	     remove(capacidade);
	}
	
	public static  List<Capacidade> findAll(){
		List<Capacidade> list = findAll("capacidade");
	    return list;
	}
	
	
//	public static Capacidade findByName(String name)
//	{
//		Capacidade capacidade = null;
//		for (Capacidade user: findAll()){
//			if(user.getNome().contains(name.trim())){
//				capacidade = user;
//				break;
//			}
//		}
//		
//		return capacidade;
//	}
	
	public static Capacidade findById(int id)
	{
		for (Capacidade capacidade: findAll()){
			if(capacidade.getId() ==id){
				return capacidade;
			}
		}
		return null;
	}

}
