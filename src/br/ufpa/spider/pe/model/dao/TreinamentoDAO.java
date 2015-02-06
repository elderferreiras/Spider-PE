package br.ufpa.spider.pe.model.dao;

import java.util.List;

import br.ufpa.spider.pe.model.Treinamento;

public class TreinamentoDAO extends GenericDAO {
	public static void createTreinamento(Treinamento treinamento){
	     create(treinamento);
	}
	
	public static void updateTreinamento(Treinamento treinamento){
	     update(treinamento);
	}
	
	public static void removeTreinamento(Treinamento treinamento){
	     remove(treinamento);
	}
	
	public static  List<Treinamento> findAll(){
		List<Treinamento> list = findAll("treinamento");
	    return list;
	}
	
	
	public static Treinamento findByName(String name)
	{
		Treinamento treinamento = null;
		for (Treinamento t: findAll()){
			if(t.getNome().contains(name.trim())){
				treinamento = t;
				break;
			}
		}
		
		return treinamento;
	}
	
	public static Treinamento findById(int id)
	{
		for (Treinamento treinamento: findAll()){
			if(treinamento.getId() ==id){
				return treinamento;
			}
		}
		return null;
	}

}
