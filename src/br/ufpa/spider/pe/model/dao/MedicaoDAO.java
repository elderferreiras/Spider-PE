package br.ufpa.spider.pe.model.dao;

import java.util.List;

import br.ufpa.spider.pe.model.Medicao;

public class MedicaoDAO extends GenericDAO {

	public static void createMedicao(Medicao medicao){
	     create(medicao);
	}
	
	public static void updateMedicao(Medicao medicao){
	     update(medicao);
	}
	
	public static void removeMedicao(Medicao medicao){
	     remove(medicao);
	}
	
	public static  List<Medicao> findAll(){
		List<Medicao> list = findAll("medicao");
	    return list;
	}
	
	
	public static Medicao findByName(String name)
	{
		Medicao medicao = null;
		for (Medicao user: findAll()){
			if(user.getNome().contains(name.trim())){
				medicao = user;
				break;
			}
		}
		
		return medicao;
	}
	
	public static Medicao findById(int id)
	{
		for (Medicao medicao: findAll()){
			if(medicao.getId() ==id){
				return medicao;
			}
		}
		return null;
	}

}
