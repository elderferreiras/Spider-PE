package br.ufpa.spider.pe.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;

import br.ufpa.spider.pe.model.Atividade;
import br.ufpa.spider.pe.model.Iteracao;
import br.ufpa.spider.pe.model.Log;
import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.ProdutoTrabalho;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.view.JDialog_Spider_Login;

public class TarefaDAO extends GenericDAO{
	public static void createTarefa(Tarefa tarefa){
	     create(tarefa);
	}
	
	public static void updateTarefa(Tarefa tarefa){
	     update(tarefa);
	}
	
	public static void removeTarefa(Tarefa tarefa){
	     remove(tarefa);
	}
	
	public static  List<Tarefa> findAll(){
		List<Tarefa> list = findAll("tarefa");
	    return list;
	}
	
	
	public static Tarefa findByName(String name)
	{
		Tarefa tarefa = null;
		for (Tarefa user: findAll()){
			if(user.getNome().contains(name.trim())){
				tarefa = user;
				break;
			}
		}
		
		return tarefa;
	}
	
	public static Tarefa findById(int id)
	{
		for (Tarefa tarefa: findAll()){
			if(tarefa.getId() ==id){
				return tarefa;
			}
		}
		return null;
	}
	
	public static Tarefa findByIdComponent(Integer idComponent){
		for(Tarefa t: findAll()){
			if(t.getIdComponent() == idComponent){
				return t;
			}
		}
		return null;
	}
	
	public static List<Tarefa> getTarefasByProcesso(Processo processo) {
		ArrayList<Tarefa> tarefas= null;
		if(processo != null){
			tarefas = new ArrayList<Tarefa>();
			for (Tarefa tarefa : TarefaDAO.findAll()) {
				Processo processo2 = null;
				if(tarefa.getAtividade() != null){
					processo2 = getProcessoConnected(tarefa.getAtividade());
				} else if (tarefa.getIteracao() != null){
					processo2 = getProcessoConnected(tarefa.getIteracao());
				} else if (tarefa.getFase() != null){
					processo2 = tarefa.getFase().getProcesso();
				}
				
				if(processo.equals(processo2)){
					tarefas.add(tarefa);
				}
			}
		}
		return tarefas;
	}
	
	private static Processo getProcessoConnected(Atividade atividade){
			if(atividade.getAtividade() != null){
				return getProcessoConnected(atividade.getAtividade());
			} else if (atividade.getIteracao() != null) {
				return getProcessoConnected(atividade.getIteracao());
			} else if (atividade.getFase() != null){
				return atividade.getFase().getProcesso();
			}
			return null;
	}
	
	private static Processo getProcessoConnected(Iteracao iteracao){
		return iteracao.getFase().getProcesso();
	}
	
	public static Processo getProcesso(Tarefa tarefa){
		if(tarefa.getIteracao()!=null){
			return tarefa.getIteracao().getFase().getProcesso();
		}
		else if(tarefa.getAtividade()!=null){
			return AtividadeDAO.getProcesso(tarefa.getAtividade());
		} else if (tarefa.getFase() != null){
			return tarefa.getFase().getProcesso();
		}
		return null;
	}
	
	/**
	 * 
	 * @param dataString eh uma data em String no formato dd/mm/aaaa
	 * @return retorna a data no formato aaaammdd para fins de comparacao de
	 * datas.
	 */
	public static int convertDataStringforInt(String dataString){
		String data1 = dataString.substring(6,10)+dataString.substring(3,5)+dataString.substring(0,2);
		int dataInt = Integer.parseInt(data1);
		return dataInt;
	}
	/**
	 * 
	 * @param tarefas
	 * @return retorna a soma das cargas horarias da lista de tarefas passada como parametro
	 */
	public static String findCargaHoraria(List<Tarefa> tarefas, List<Marco> marcos){
		Double carga = 0.0;
		for(Tarefa tarefa : tarefas){
			if(tarefa.getCargaHoraria() != null){
				carga += TarefaDAO.convertCargaStringtoDouble(tarefa.getCargaHoraria());
			}
		}
		if(marcos != null){
			for(Marco marco : marcos){
				if(marco.getCargaHoraria() != null){
					carga += TarefaDAO.convertCargaStringtoDouble(marco.getCargaHoraria());
				}
			}
		}
		return carga.toString();
	}
	
	/**
	 * 
	 * @param tarefas
	 * @return retorna a soma dos clocks internos da lista de tarefas passada como parametro
	 */
	public static String findClockInterno(List<Tarefa> tarefas, List<Marco> marcos){
		Double clock = 0.0;
		for(Tarefa tarefa : tarefas){
			if(tarefa.getClockInterno() != null){
				clock += TarefaDAO.convertCargaStringtoDouble(tarefa.getClockInterno());
			}
		}
		if(marcos != null){
			for(Marco marco: marcos){
				if(marco.getClockInterno() != null){
					clock += TarefaDAO.convertCargaStringtoDouble(marco.getClockInterno());
				}
			}
		}
		return clock.toString();
	}
	
	/**
	 * 
	 * @param Lista de Tarefas
	 * @return retorna a maior data dentre as datas de Fim Previsto das 
	 * tarefas da lista passada como parametro.
	 */
	
	public static String findDataFimPrevisto(List<Tarefa> tarefas, List<Marco> marcos){
		int dataFim = Integer.MIN_VALUE;
		String fimPrevisto = "?";
		List<String> datas = new ArrayList<String>();
		for (Tarefa tarefa : tarefas) {
			datas.add(tarefa.getFimPrevisto());
		}
		if(marcos != null){
			for(Marco marco: marcos){
				datas.add(marco.getFimPrevisto());
			}
		}
		for (String data : datas) {
			if(data != null){
				int dataInt = convertDataStringforInt(data);
				if(dataInt >= dataFim){
					dataFim = dataInt;
					fimPrevisto = data;
				}
			}
		}
		return fimPrevisto;
	}
	
	/**
	 * 
	 * @param Lista de Tarefas
	 * @return retorna a maior data dentre as datas de Fim Real das 
	 * tarefas da lista passada como parametro.
	 */
	
	public static String findDataFimReal(List<Tarefa> tarefas, List<Marco> marcos){
		int dataFim = Integer.MIN_VALUE;
		String fimReal = "?";
		List<String> datas = new ArrayList<String>();
		for (Tarefa tarefa : tarefas) {
			datas.add(tarefa.getFimReal());
		}
		if(marcos != null){
			for(Marco marco: marcos){
				//datas.add(marco.getFimReal());
			}
		}
		for (String data : datas) {
			if(data != null){
				int dataInt = convertDataStringforInt(data);
				if(dataInt >= dataFim){
					dataFim = dataInt;
					fimReal = data;
				}
			}
		}
		return fimReal;
	}
	
	/**
	 * 
	 * @param Lista de tarefas
	 * @return retorna a menor data dentre as datas de Inicio Previsto das tarefas 
	 * da lista passada como parâmetro.
	 */
	public static String findDataInicioPrevisto(List<Tarefa> tarefas, List<Marco> marcos){
		int dataInicial = Integer.MAX_VALUE;
		String inicioPrevisto = "?";
		List<String> datas = new ArrayList<String>();
		for (Tarefa tarefa : tarefas) {
			datas.add(tarefa.getInicioPrevisto());
		}
		if(marcos != null){
			for(Marco marco: marcos){
				datas.add(marco.getInicioPrevisto());
			}
		}
		for(String data: datas){
			if(data != null){
				int dataInt = convertDataStringforInt(data);
				if(dataInt <= dataInicial){
					dataInicial = dataInt;
					inicioPrevisto = data;
				}
			}
		}
		
		return inicioPrevisto;
	}
	
	/**
	 * 
	 * @param carga - String no formato xx,xxx
	 * @return retorna a representação da String carga em forma de Double
	 */
	public static double convertCargaStringtoDouble(String carga){
		if(carga!=null){
			carga = carga.replaceAll(",", ".");
			return Double.parseDouble(carga);
		}
		
		return 0;
		
	}
	/**
	 * 
	 * @param Lista de tarefas
	 * @return retorna a menor data dentre as datas de Inicio Real das tarefas 
	 * da lista passada como parâmetro.
	 */
	public static String findDataInicioReal(List<Tarefa> tarefas, List<Marco> marcos){
		int dataInicial = Integer.MAX_VALUE;
		String inicioReal = "?";
		List<String> datas = new ArrayList<String>();
		for (Tarefa tarefa : tarefas) {
			datas.add(tarefa.getInicioReal());
		}
		if(marcos != null){
			for(Marco marco: marcos){
				datas.add(marco.getInicioReal());
			}
		}
		for (String data : datas) {			
			if(data != null){
				int dataInt = convertDataStringforInt(data);
				if(dataInt <= dataInicial){
					dataInicial = dataInt;
					inicioReal = data;
				}
			}
		}
		return inicioReal;
	}

	public static int getOrdem(Tarefa tarefa) {
		int maior = 0;
		for (Log log: tarefa.getLog()){
			if(log.getOrdem()>0){
				maior = log.getOrdem();
			}
		}
		return maior;
	}
}
