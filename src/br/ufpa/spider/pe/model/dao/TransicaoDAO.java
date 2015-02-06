package br.ufpa.spider.pe.model.dao;

import java.util.List;

import br.ufpa.spider.pe.model.Transicao;

public class TransicaoDAO extends GenericDAO {

public static void createTransicao(Transicao transicao){
	     create(transicao);
	}
	
	public static void updateTransicao(Transicao transicao){
	     update(transicao);
	}
	
	public static void removeTransicao(Transicao transicao){
	     remove(transicao);
	}
	
	public static  List<Transicao> findAll(){
		List<Transicao> list = findAll("transicao");
	    return list;
	}
	
	public static Transicao findById(int id, Integer source, Integer destination, int idProcess)
	{
		for (Transicao transicao: findAll()){
			if(transicao.getIdComponent() == id){
				if(transicao.getSourceId().equals(source) &&  transicao.getDestinationId().equals(destination) && transicao.getProcessId().equals(idProcess))
					return transicao;
			}
		}
		return null;
	}
}
