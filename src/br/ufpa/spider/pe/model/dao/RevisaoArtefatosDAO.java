package br.ufpa.spider.pe.model.dao;

import java.util.List;

import br.ufpa.spider.pe.model.RevisaoArtefatos;

public class RevisaoArtefatosDAO extends GenericDAO {
	public static void createRevisaoArtefatos(RevisaoArtefatos revisoes){
	     create(revisoes);
	}
	
	public static void updateRevisaoArtefatos(RevisaoArtefatos revisoes){
	     update(revisoes);
	}
	
	public static void removeRevisaoArtefatos(RevisaoArtefatos revisoes){
	     remove(revisoes);
	}
	
	public static  List<RevisaoArtefatos> findAll(){
		List<RevisaoArtefatos> list = findAll("revisao_artefatos");
	    return list;
	}
	
	
	public static RevisaoArtefatos findByName(String name)
	{
		RevisaoArtefatos revisoes = null;
		for (RevisaoArtefatos user: findAll()){
			if(user.getNome().contains(name.trim())){
				revisoes = user;
				break;
			}
		}
		
		return revisoes;
	}
	
	public static RevisaoArtefatos findById(int id)
	{
		for (RevisaoArtefatos revisoes: findAll()){
			if(revisoes.getId() ==id){
				return revisoes;
			}
		}
		return null;
	}
}
