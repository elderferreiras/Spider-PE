package br.ufpa.spider.pe.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.ufpa.spider.pe.model.Atividade;
import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Iteracao;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.Tarefa;

public class IteracaoDAO extends GenericDAO {
	
	public static void createIteracao(Iteracao iteracao){
	     create(iteracao);
	}
	
	public static void updateIteracao(Iteracao iteracao){
	     update(iteracao);
	}
	
	public static void removeIteracao(Iteracao iteracao){
	     remove(iteracao);
	}
	
	public static  List<Iteracao> findAll(){
		List<Iteracao> list = findAll("iteracao");
	    return list;
	}
	
	
	public static Iteracao findByName(String name)
	{
		Iteracao iteracao = null;
		for (Iteracao user: findAll()){
			if(user.getNome().contains(name.trim())){
				iteracao = user;
				break;
			}
		}
		
		return iteracao;
	}
	
	public static Iteracao findById(int id)
	{
		for (Iteracao iteracao: findAll()){
			if(iteracao.getId() == id){
				return iteracao;
			}
		}
		return null;
	}
	
	public static Iteracao findByIdComponent(Integer idComponent, String nameFile){
		for(Iteracao i: findAll()){
			if(i.getIdComponent() == idComponent){
				Processo processo = i.getFase().getProcesso();
				if(processo != null && processo.getArqXML().equals(nameFile)){
					return i;
				}
			}
		}
		return null;
	}
	
	public static String findCargaHoraria(Iteracao iteracao){
		return TarefaDAO.findCargaHoraria(findAllTarefas(iteracao), null);
	}
	
	public static String findClockInterno(Iteracao iteracao){
		return TarefaDAO.findClockInterno(findAllTarefas(iteracao), null);
	}
	
	public static String findDataFimPrevisto(Iteracao iteracao){
		return TarefaDAO.findDataFimPrevisto(findAllTarefas(iteracao), null);
	}
	
	public static String findDataInicioPrevisto(Iteracao iteracao){
		return TarefaDAO.findDataInicioPrevisto(findAllTarefas(iteracao), null);
	}
	
	public static String findDataFimReal(Iteracao iteracao){
		return TarefaDAO.findDataFimReal(findAllTarefas(iteracao), null);
	}
	
	public static String findDataInicioReal(Iteracao iteracao){
		return TarefaDAO.findDataInicioReal(findAllTarefas(iteracao), null);
	}
	/**
	 * 
	 * @param iteracao
	 * @return retorna todas as tarefas que fazem 
	 * parte da iteracao passada como parametro.
	 */
	public static List<Tarefa> findAllTarefas(Iteracao iteracao){
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		if(iteracao.getAtividade() != null){
			for(Atividade a: iteracao.getAtividade()){
				tarefas.addAll(AtividadeDAO.findAllTarefas(a));
			}
		}
		
		if(iteracao.getTarefa() != null){
			tarefas.addAll(iteracao.getTarefa());
		}
		
		return tarefas;
	}

	public static Processo getProcesso(Iteracao iteracao) {
		// TODO Auto-generated method stub
		return iteracao.getFase().getProcesso();
	}
	
	
}
