package br.ufpa.spider.pe.model.dao;

import java.util.List;

import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Procedimento;

public class ProcedimentoDAO extends GenericDAO {
	public static void createProcedimento(Procedimento procedimento){
	     create(procedimento);
	}
	
	public static void updateProcedimento(Procedimento procedimento){
	     update(procedimento);
	}
	
	public static void removeProcedimento(Procedimento procedimento){
	     remove(procedimento);
	}
	
	public static  List<Procedimento> findAll(){
		List<Procedimento> list = findAll("procedimento");
	    return list;
	}
	
	
	public static Procedimento findByName(String name)
	{
		Procedimento procedimento = null;
		for (Procedimento user: findAll()){
			if(user.getNome().contains(name.trim())){
				procedimento = user;
				break;
			}
		}
		
		return procedimento;
	}
	
	public static Procedimento findById(int id)
	{
		for (Procedimento procedimento: findAll()){
			if(procedimento.getId() ==id){
				return procedimento;
			}
		}
		return null;
	}
	
	public static Procedimento findByIdComponent(Integer idComponent, String nameFile){
		for(Procedimento p: findAll()){
			if(p.getIdComponent().equals(idComponent)){
				if(p.getArqXML().equals(nameFile)){
					return p;
				}
			}
		}
		return null;
	}
}
