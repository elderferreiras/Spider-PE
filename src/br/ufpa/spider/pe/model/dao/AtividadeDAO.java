package br.ufpa.spider.pe.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Atividade;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.Tarefa;

public class AtividadeDAO extends GenericDAO {

public static void createAtividade(Atividade atividade){
	     create(atividade);
	}
	
	public static void updateAtividade(Atividade atividade){
	     update(atividade);
	}
	
	public static void removeAtividade(Atividade atividade){
	     remove(atividade);
	}
	
	public static  List<Atividade> findAll(){
		List<Atividade> list = findAll("atividade");
	    return list;
	}
	
	
	public static Atividade findByName(String name)
	{
		Atividade atividade = null;
		for (Atividade user: findAll()){
			if(user.getNome().contains(name.trim())){
				atividade = user;
				break;
			}
		}
		
		return atividade;
	}
	
	public static Atividade findById(int id)
	{
		for (Atividade atividade: findAll()){
			if(atividade.getId() ==id){
				return atividade;
			}
		}
		return null;
	}
	
	public static Atividade findByIdComponent(Integer idComponent, String nameFile){
		for(Atividade a: findAll()){
			if(a.getIdComponent() == idComponent){
				if(a.getArqXML().equals(nameFile)){
					return a;
				}
			}
		}
		return null;
	}
	/**
	 * 
	 * @param atividade
	 * @return
	 */
	public static Processo getProcesso(Atividade atividade){		
		if(atividade.getIteracao()!=null){
			return atividade.getIteracao().getFase().getProcesso();
		} else if(atividade.getFase() != null) {
			return atividade.getFase().getProcesso();
		} else {
			return getProcesso(atividade.getAtividade());	 
		}
	}
	/**
	 * 
	 * @param atividade
	 * @return retorna a menor data dentre as datas de Inicio Previsto das tarefas que fazem parte 
	 * da atividade passada como parametro.
	 */
	public static String findDataInicioPrevisto(Atividade atividade){
		return TarefaDAO.findDataInicioPrevisto(findAllTarefas(atividade), null);
	}
	
	/**
	 * 
	 * @param atividade
	 * @return retorna a maior data dentre as datas de Fim Real das tarefas que fazem parte 
	 * da atividade passada como parametro.
	 */
	
	public static String findDataFimReal(Atividade atividade){
		return TarefaDAO.findDataFimReal(findAllTarefas(atividade), null);
	}
	/**
	 * 
	 * @param atividade
	 * @return retorna a menor data dentre as datas de Inicio Real das tarefas que fazem parte 
	 * da atividade passada como parametro.
	 */
	public static String findDataInicioReal(Atividade atividade){
		return TarefaDAO.findDataInicioReal(findAllTarefas(atividade), null);
	}
	
	/**
	 * 
	 * @param atividade
	 * @return retorna a maior data dentre as datas de Fim Previsto das tarefas que fazem parte 
	 * da atividade passada como parametro.
	 */
	
	public static String findDataFimPrevisto(Atividade atividade){
		return TarefaDAO.findDataFimPrevisto(findAllTarefas(atividade), null);
	}
	
	public static String findCargaHoraria(Atividade atividade){
		return TarefaDAO.findCargaHoraria(findAllTarefas(atividade), null);
	}
	
	public static String findClockInterno(Atividade atividade){
		return TarefaDAO.findClockInterno(findAllTarefas(atividade), null);
	}
	/**
	 * 
	 * @param atividade
	 * @return retorna todas as tarefas que fazem 
	 * parte da atividade passada como parametro.
	 */
	public static List<Tarefa> findAllTarefas(Atividade atividade) {
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		if(atividade.getTarefa() != null){
			tarefas.addAll(atividade.getTarefa());
		}
		if(atividade.getAtividades() != null){
			for(Atividade a: atividade.getAtividades()){
				tarefas.addAll(findAllTarefas(a));
			}
		}
		return tarefas;
	}

}
