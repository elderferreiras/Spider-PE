package br.ufpa.spider.pe.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.ufpa.spider.pe.model.Atividade;
import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.Tarefa;

public class ProcessoDAO extends GenericDAO{
	
	public static void createProcesso(Processo processo){
	     create(processo);
	}
	
	public static void updateProcesso(Processo processo){
	     update(processo);
	}
	
	public static void removeProcesso(Processo processo){
	     remove(processo);
	}
	
	public static  List<Processo> findAll(){
		List<Processo> list = findAll("processo");
	    return list;
	}
	
	
	public static Processo findByName(String name)
	{
		
		for (Processo p: findAll()){
			if(p.getNome().equals(name.trim())){
				return p;
			}
		}
		
		return null;
	}
	
	public static Processo findByIdComponent(Integer idComponent){
		for(Processo p: findAll()){
			if(p.getIdComponent() == idComponent){
				return p;
			}
		}
		return null;
	}
	
	public static String findCargaHoraria(Processo processo){
		List<Marco> marcos = new ArrayList<Marco>();
		for (Fase fase : processo.getFase()) {
			marcos.addAll(fase.getMarco());
		}
		return TarefaDAO.findCargaHoraria(findAllTarefas(processo), marcos);
	}
	
	public static String findClockInterno(Processo processo){
		List<Marco> marcos = new ArrayList<Marco>();
		for (Fase fase : processo.getFase()) {
			marcos.addAll(fase.getMarco());
		}
		return TarefaDAO.findClockInterno(findAllTarefas(processo), marcos);
	}

	public static Processo findByIdComponentAndNameArqXml(int parentid,String nameArq) {
		for(Processo p: findByNameArqXml(nameArq)){
			if(p.getIdComponent() ==  parentid){
				return p;
			}
		}
		return null;
	}
	
	public static List<Processo> findByNameArqXml(String nameArq){
		List<Processo> processos = new ArrayList<Processo>();
		for(Processo p: findAll()){
			if(p.getArqXML().equals(nameArq)){
				processos.add(p);
			}
		}
		return processos;
	}
	
	/**
	 * 
	 * @param fase
	 * @return retorna todas as tarefas que fazem 
	 * parte da fase passada como parametro.
	 */
	public static List<Tarefa> findAllTarefas(Processo processo){
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		if(processo.getFase() != null){
			for(Fase fase: processo.getFase()){
				tarefas.addAll(FaseDAO.findAllTarefas(fase));
			}
		}
		return tarefas;
	}
	/**
	 * 
	 * @param processo
	 * @return retorna a menor data dentre as datas de Inicio Previsto das tarefas que fazem parte 
	 * do processo passado como parametro.
	 */
	public static String findDataInicioPrevisto(Processo processo){
		List<Marco> marcos = new ArrayList<Marco>();
		for (Fase fase : processo.getFase()) {
			marcos.addAll(fase.getMarco());
		}
		return TarefaDAO.findDataInicioPrevisto(findAllTarefas(processo), marcos);
	}
	
	/**
	 * 
	 * @param processo
	 * @return retorna a maior data dentre as datas de Fim Previsto das tarefas que fazem parte 
	 * do processo passado como parametro.
	 */
	
	public static String findDataFimPrevisto(Processo processo){
		List<Marco> marcos = new ArrayList<Marco>();
		for (Fase fase : processo.getFase()) {
			marcos.addAll(fase.getMarco());
		}
		return TarefaDAO.findDataFimPrevisto(findAllTarefas(processo), marcos);
	}
	
	/**
	 * 
	 * @param processo
	 * @return retorna a menor data dentre as datas de Inicio Real das tarefas que fazem parte 
	 * do processo passado como parametro.
	 */
	public static String findDataInicioReal(Processo processo){
		List<Marco> marcos = new ArrayList<Marco>();
		for (Fase fase : processo.getFase()) {
			marcos.addAll(fase.getMarco());
		}
		return TarefaDAO.findDataInicioReal(findAllTarefas(processo), marcos);
	}
	
	/**
	 * 
	 * @param processo
	 * @return retorna a maior data dentre as datas de Fim Real das tarefas que fazem parte 
	 * do processo passado como parametro.
	 */
	
	public static String findDataFimReal(Processo processo){
		List<Marco> marcos = new ArrayList<Marco>();
		for (Fase fase : processo.getFase()) {
			marcos.addAll(fase.getMarco());
		}
		return TarefaDAO.findDataFimReal(findAllTarefas(processo), marcos);
	}

}

