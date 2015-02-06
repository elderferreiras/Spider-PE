package br.ufpa.spider.pe.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.JoinFork;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.Tarefa;

public class JoinForkDAO extends GenericDAO {

public static void createJoinFork(JoinFork joinFork){
	     create(joinFork);
	}
	
	public static void updateJoinFork(JoinFork joinFork){
	     update(joinFork);
	}
	
	public static void removeJoinFork(JoinFork joinFork){
	     remove(joinFork);
	}
	
	public static  List<JoinFork> findAll(){
		List<JoinFork> list = findAll("joinFork");
	    return list;
	}
	
	public static JoinFork findById(int id, int i)
	{
		for (JoinFork joinFork: findAll()){
			if(joinFork.getIdComponent() ==id){
				if(joinFork.getProcessId().equals(i))
					return joinFork;
			}
		}
		return null;
	}
	
	public static JoinFork findByIdComponent(Integer idComponent){
		for(JoinFork a: findAll()){
			if(a.getIdComponent() == idComponent){
				return a;
			}
		}
		return null;
	}
}
