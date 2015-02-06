package br.ufpa.spider.pe.model.dao;

import java.util.List;

import br.ufpa.spider.pe.model.RevisaoProcesso;

public class RevisaoProcessoDAO extends GenericDAO {
	public static void createRevisaoProcesso(RevisaoProcesso revisaoProcesso){
	     create(revisaoProcesso);
	}
	
	public static void updateRevisaoProcesso(RevisaoProcesso revisaoProcesso){
	     update(revisaoProcesso);
	}
	
	public static void removeRevisaoProcesso(RevisaoProcesso revisaoProcesso){
	     remove(revisaoProcesso);
	}
	
	public static  List<RevisaoProcesso> findAll(){
		List<RevisaoProcesso> list = findAll("revisao_processo");
	    return list;
	}
	
	
	public static RevisaoProcesso findByName(String name)
	{
		RevisaoProcesso revisaoProcesso = null;
		for (RevisaoProcesso user: findAll()){
			if(user.getNome().contains(name.trim())){
				revisaoProcesso = user;
				break;
			}
		}
		
		return revisaoProcesso;
	}
	
	public static RevisaoProcesso findById(int id)
	{
		for (RevisaoProcesso revisaoProcesso: findAll()){
			if(revisaoProcesso.getId() ==id){
				return revisaoProcesso;
			}
		}
		return null;
	}
}
