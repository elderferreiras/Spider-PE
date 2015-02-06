package br.ufpa.spider.pe.model.dao;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Log;

public class LogDAO extends GenericDAO {
	public static void createLog(Log humano){
	     create(humano);
	}
	
	public static void updateLog(Log humano){
	     update(humano);
	}
	
	public static void removeLog(Log humano){
	     remove(humano);
	}
	
	public static  List<Log> findAll(){
		List<Log> list = findAll("humano");
	    return list;
	}
	
	
	public static Log findById(int id)
	{
		for (Log humano: findAll()){
			if(humano.getId() ==id){
				return humano;
			}
		}
		return null;
	}
	
	public static Log findByTarefa(int tarefa)
	{
		for (Log log: findAll()){
			if(log.getTarefa().equals(tarefa)){
				return log;
			}
		}
		return null;
	}
}
