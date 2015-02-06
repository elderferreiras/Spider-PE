package br.ufpa.spider.pe.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.ufpa.spider.pe.model.Atividade;
import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Iteracao;
import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.Tarefa;

public class FaseDAO extends GenericDAO {
	public static void createFase(Fase fase){
	     create(fase);
	}
	
	public static void updateFase(Fase fase){
	     update(fase);
	}
	
	public static void removeFase(Fase fase){
	     remove(fase);
	}
	
	public static  List<Fase> findAll(){
		List<Fase> list = findAll("fase");
	    return list;
	}
	
	
	public static Fase findByName(String name)
	{
		Fase fase = null;
		for (Fase user: findAll()){
			if(user.getNome().contains(name.trim())){
				fase = user;
				break;
			}
		}
		
		return fase;
	}
	
	public static String findCargaHoraria(Fase fase){
		return TarefaDAO.findCargaHoraria(findAllTarefas(fase), fase.getMarco());
	}
	
	public static String findClockInterno(Fase fase){
		return TarefaDAO.findClockInterno(findAllTarefas(fase), fase.getMarco());
	}
	
	/**
	 * 
	 * @param id
	 * @return Encontra a(s) fase(s) que possui o id passado por parametro
	 */
	public static Fase findById(int id)
	{
		for (Fase fase: findAll()){
			if(fase.getId() ==id){
				return fase;
			}
		}
		return null;
	}
	/**
	 * 
	 * @param idComponent
	 * @return Encontra a(s) fase(s) com o idComponent passado por parametro
	 */
	public static Fase findByIdComponent(Integer idComponent, String nameFile){
		for(Fase f: findAll()){
			if(f.getIdComponent() == idComponent){
				Processo processo = f.getProcesso();
				if(processo != null && processo.getArqXML().equals(nameFile)){
					return f;
				}
			}
		}
		return null;
	}
	/**
	 * 
	 * @param fase
	 * @return retorna a menor data dentre as datas de Inicio Previsto das tarefas que fazem parte 
	 * da fase passada como parametro.
	 */
	public static String findDataInicioPrevisto(Fase fase){
		return TarefaDAO.findDataInicioPrevisto(findAllTarefas(fase), fase.getMarco());
	}
	
	/**
	 * 
	 * @param fase
	 * @return retorna a maior data dentre as datas de Fim Previsto das tarefas que fazem parte 
	 * da fase passada como parametro.
	 */
	
	public static String findDataFimPrevisto(Fase fase){
		return TarefaDAO.findDataFimPrevisto(findAllTarefas(fase), fase.getMarco());
	}
	/**
	 * 
	 * @param fase
	 * @return retorna a menor data dentre as datas de Inicio Real das tarefas que fazem parte 
	 * da fase passada como parametro.
	 */
	public static String findDataInicioReal(Fase fase){
		return TarefaDAO.findDataInicioReal(findAllTarefas(fase), fase.getMarco());
	}
	
	/**
	 * 
	 * @param fase
	 * @return retorna a maior data dentre as datas de Fim Real das tarefas que fazem parte 
	 * da fase passada como parametro.
	 */
	
	public static String findDataFimReal(Fase fase){
		List<Marco> marcos = fase.getMarco();
		return TarefaDAO.findDataFimReal(findAllTarefas(fase), fase.getMarco());
	}
	/**
	 * 
	 * @param fase
	 * @return retorna todas as tarefas que fazem 
	 * parte da fase passada como parametro.
	 */
	public static List<Tarefa> findAllTarefas(Fase fase){
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		if(fase.getIteracao() != null){ // fase no modelo iterativo
			for(Iteracao i : fase.getIteracao()){
				tarefas.addAll(IteracaoDAO.findAllTarefas(i));
			}
		} else { // fase no modelo sequencial
			if(fase.getAtividades() != null){
				for(Atividade a: fase.getAtividades()){
					tarefas.addAll(AtividadeDAO.findAllTarefas(a));
				}
			}
			if(fase.getTarefas() != null){
				tarefas.addAll(fase.getTarefas());
			}
		}
		return tarefas;
	}
	
	/**
	 * 
	 * @param fase
	 * @return Retorna o Processo da qual a Fase passada como parametro faz parte
	 */
	public static Processo getProcesso(Fase fase) {

		return fase.getProcesso();
	}
}
