package br.ufpa.spider.pe.model.dao;

import java.util.List;

import br.ufpa.spider.pe.model.GerenteProcesso;
import br.ufpa.spider.pe.model.Humano;

public class GerenteProcessoDAO extends GenericDAO {

public static void createGerenteProcesso(GerenteProcesso gerente_processo){
	     create(gerente_processo);
	}
	
	public static void updateGerenteProcesso(GerenteProcesso gerente_processo){
	     update(gerente_processo);
	}
	
	public static void removeGerenteProcesso(GerenteProcesso gerente_processo){
	     remove(gerente_processo);
	}
	
	public static  List<GerenteProcesso> findAll(){
		List<GerenteProcesso> list = findAll("gerente_processo");
	    return list;
	}
	
	
	public static Humano findByLogin(String name)
	{
		Humano humano = null;
		for (GerenteProcesso user: findAll()){
			if(user.getHumano().getEmail().equals(name.trim())){
				humano = user.getHumano();
				break;
			}
		}
		
		return humano;
	}
	
	public static GerenteProcesso findById(int id)
	{
		for (GerenteProcesso gerente_processo: findAll()){
			if(gerente_processo.getId() ==id){
				return gerente_processo;
			}
		}
		return null;
	}
}
