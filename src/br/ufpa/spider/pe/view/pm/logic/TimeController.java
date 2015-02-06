package br.ufpa.spider.pe.view.pm.logic;

import java.util.Date;

import br.ufpa.spider.pe.controller.MarcoController;
import br.ufpa.spider.pe.controller.TarefaController;
import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.Tarefa;

public class TimeController {
	
	public static Double getTempoCorrente(){
		return Double.parseDouble(Long.toString(new Date().getTime()/(1000*60)));
	}
	
	public static void ChangeToStarted(Tarefa tarefa){
		tarefa.setTimeStart(getTempoCorrente());
		TarefaController.saveTarefa(tarefa);
	}
	
	public static void ChangeToPausedOrFinished(Tarefa tarefa){
		tarefa.getClockInterno();
		TarefaController.saveTarefa(tarefa);
	}
	
	
	/** MARCOS **/
	
	public static void ChangeToStarted(Marco marco){
		marco.setTimeStart(getTempoCorrente());
		MarcoController.saveMarco(marco);
	}

	
	public static void ChangeToPausedOrFinished(Marco marco){
		marco.getClockInterno();
		MarcoController.saveMarco(marco);
	}
	

}
