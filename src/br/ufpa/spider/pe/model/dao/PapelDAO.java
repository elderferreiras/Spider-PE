package br.ufpa.spider.pe.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Papel;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.Tarefa;

public class PapelDAO extends GenericDAO {
	
	public static void createPapel(Papel papel){
	     create(papel);
	}
	
	public static void updatePapel(Papel papel){
	     update(papel);
	}
	
	public static void removePapel(Papel papel){
	     remove(papel);
	}
	
	public static  List<Papel> findAll(){
		List<Papel> list = findAll("papel");
	    return list;
	}
	
	
	public static Papel findById(int id)
	{
		for (Papel papel: findAll()){
			if(papel.getId() == (id)){
				return papel;
			}
		}
		return null;
	}
	
	public static Papel findByIdComponent(Integer idComponent, String nameFile){
		for(Papel p: findAll()){
			if(p.getIdComponent().equals(idComponent)){
				if(p.getArqXML().equals(nameFile)){
					return p;
				}
			}
		}
		return null;
	}
	
	public static List<Papel> getPapelbyProcess(Processo processo){
		List <Papel> papeis = new ArrayList<Papel>();
		for(Papel p: findAll()){
			for(Tarefa t: TarefaDAO.getTarefasByProcesso(processo)){
				if(p.getTarefa() != null){
					if(p.getTarefa().equals(t)){
						papeis.add(p);
					}
				}
			}
		}
		return papeis;
	}
}