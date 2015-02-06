package br.ufpa.spider.pe.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.controller.MarcoController;
import br.ufpa.spider.pe.controller.PapelController;
import br.ufpa.spider.pe.controller.TarefaController;
import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.Papel;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.Tarefa;

public class MarcoDAO extends GenericDAO {
	public static void createMarco(Marco marco){
	     create(marco);
	}
	
	public static void updateMarco(Marco marco){
	     update(marco);
	}
	
	public static void removeMarco(Marco marco){
	     remove(marco);
	}
	
	public static  List<Marco> findAll(){
		List<Marco> list = findAll("marco");
	    return list;
	}
	
	
	public static Marco findByName(String name)
	{
		Marco marco = null;
		for (Marco user: findAll()){
			if(user.getNome().contains(name.trim())){
				marco = user;
				break;
			}
		}
		
		return marco;
	}
	
	public static Marco findById(int id)
	{
		for (Marco marco: findAll()){
			if(marco.getId() ==id){
				return marco;
			}
		}
		return null;
	}
	
	public static void attMarcos(Processo processo){
		List<Papel> papeisNovos = new ArrayList<Papel>();
		if(processo == null){
			JOptionPane.showMessageDialog(null, "processo é nulo");
		} else if (processo.getFase().isEmpty()){
			JOptionPane.showMessageDialog(null, "fases é nulo");
		}
		for(Fase fases: processo.getFase()){
			for(Marco marcos :fases.getMarco()){
				if(marcos.getPapeis().isEmpty()){
					for(Papel papel: PapelDAO.getPapelbyProcess(processo)){
						if (papel != null){
								Papel papelNovo = new Papel();
								papelNovo.setArqXML(papel.getArqXML());
								papelNovo.setDescricao(papel.getDescricao());
								papelNovo.setIdComponent(papel.getIdComponent());
								papelNovo.setInformacoesAdicionais(papel.getInformacoesAdicionais());
								papelNovo.setNome(papel.getNome());
								papelNovo.setObjetivos(papel.getObjetivos());
								papelNovo.setMarco(marcos);
								marcos.getPapeis().add(papelNovo);
								papeisNovos.add(papelNovo);
						}
					}
				}
				MarcoController.saveMarco(marcos, false);
			}
		}
		
		for(Papel papel : papeisNovos){
			PapelController.savePapel(papel, false);
		}
	}
	
	public static Marco findByIdComponent(Integer idComponent){
		for(Marco m: findAll()){
			if(m.getIdComponent() == idComponent){
				return m;
			}
		}
		return null;
	}

	public static Processo getProcesso(Marco marco) {
		return marco.getFase().getProcesso();
	}
	
	/**
	 * 
	 * @param Lista de Tarefas
	 * @return retorna a maior data dentre as datas de Fim Previsto das 
	 * marcos da lista passada como parametro.
	 */
	
	public static String findDataFimPrevisto(List<Marco> marcos){
		int dataFim = Integer.MIN_VALUE;
		String fimPrevisto = "?";
		for (Marco marco : marcos) {
			if(marco.getFimPrevisto() != null){
				String dataString = marco.getFimPrevisto();
				int dataInt = convertDataStringforInt(dataString);
				if(dataInt >= dataFim){
					dataFim = dataInt;
					fimPrevisto = dataString;
				}
			}
		}
		return fimPrevisto;
	}
	
	/**
	 * 
	 * @param Lista de Marcos
	 * @return retorna a maior data dentre as datas de Fim Real das 
	 * marcos da lista passada como parametro.
	 */
	
	public static String findDataFimReal(List<Marco> marcos){
		int dataFim = Integer.MIN_VALUE;
		String fimReal = "?";
		for (Marco marco : marcos) {
			if(marco.getFimReal() != null){
				String dataString = marco.getFimReal();
				int dataInt = convertDataStringforInt(dataString);
				if(dataInt >= dataFim){
					dataFim = dataInt;
					fimReal = dataString;
				}
			}
		}
		return fimReal;
	}
	
	/**
	 * 
	 * @param Lista de marcos
	 * @return retorna a menor data dentre as datas de Inicio Previsto das marcos 
	 * da lista passada como parâmetro.
	 */
	public static String findDataInicioPrevisto(List<Marco> marcos){
		int dataInicial = Integer.MAX_VALUE;
		String inicioPrevisto = "?";
		for (Marco marco : marcos) {
			if(marco.getInicioPrevisto() != null){
				String dataString = marco.getInicioPrevisto();
				int dataInt = convertDataStringforInt(dataString);
				if(dataInt <= dataInicial){
					dataInicial = dataInt;
					inicioPrevisto = dataString;
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
		carga = carga.replace(",", ".");
		return Double.parseDouble(carga);
	}
	
	/**
	 * 
	 * @param Lista de marcos
	 * @return retorna a menor data dentre as datas de Inicio Real das marcos 
	 * da lista passada como parâmetro.
	 */
	public static String findDataInicioReal(List<Marco> marcos){
		int dataInicial = Integer.MAX_VALUE;
		String inicioReal = "?";
		for (Marco marco : marcos) {			
			if(marco.getInicioReal() != null){
				String dataString = marco.getInicioReal();
				int dataInt = convertDataStringforInt(dataString);
				if(dataInt <= dataInicial){
					dataInicial = dataInt;
					inicioReal = dataString;
				}
			}
		}
		return inicioReal;
	}
	
	/**
	 * 
	 * @param dataString eh uma data em String no formato dd/mm/aaaa
	 * @return retorna a data no formato aaaammdd para fins de comparacao de
	 * datas.
	 */
	private static int convertDataStringforInt(String dataString){
		String data1 = dataString.substring(6,10)+dataString.substring(3,5)+dataString.substring(0,2);
		int dataInt = Integer.parseInt(data1);
		return dataInt;
	}
	/**
	 * 
	 * @param marcos
	 * @return retorna a soma das cargas horarias da lista de marcos passada como parametro
	 */
	public static String findCargaHoraria(List<Marco> marcos){
		Double carga = 0.0;
		for(Marco marco : marcos){
			if(marco.getCargaHoraria() != null){
				carga += MarcoDAO.convertCargaStringtoDouble(marco.getCargaHoraria());
			}
		}
		return carga.toString();
	}
	
	/**
	 * 
	 * @param marcos
	 * @return retorna a soma dos clocks internos da lista de marcos passada como parametro
	 */
	public static String findClockInterno(List<Marco> marcos){
		Double clock = 0.0;
		for(Marco marco : marcos){
			if(marco.getClockInterno() != null){
				clock += MarcoDAO.convertCargaStringtoDouble(marco.getClockInterno());
			}
		}
		return clock.toString();
	}
}