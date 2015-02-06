package br.ufpa.spider.pe.view.pm.logic;

import java.net.Proxy.Type;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Atividade;
import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Iteracao;
import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.dao.AtividadeDAO;
import br.ufpa.spider.pe.model.dao.FaseDAO;
import br.ufpa.spider.pe.model.dao.IteracaoDAO;
import br.ufpa.spider.pe.model.dao.ProcessoDAO;
import br.ufpa.spider.pe.model.dao.TarefaDAO;
import br.ufpa.spider.pe.view.JDialog_Spider_Login;
import br.ufpa.spider.pe.view.pm.model.Component;
import br.ufpa.spider.pe.view.pm.model.ComponentType;
import br.ufpa.spider.pe.view.pm.model.Modelling;

public class ExecutionController {
	public static final String EXECUCAO = "Em Execu\u00e7\u00e3o";
	public static final String PAUSADA = "Pausada";
	public static final String FINALIZADA = "Finalizada";
	public static final String ATRASADA = "Atrasada";
	public static final String ADIANTADA = "Adiantado";
	
	public static final String AND = "A\nN\nD";
	public static final String OR = "O\nR";
	public static final String XOR = "X\nO\nR";
	
	public static final String startToStart = "startToStart";
	public static final String finishToStart = "finishToStart";
	public static final String finishToFinish = "finishToFinish";
	public static final String startToFinish = "startToFinish";
	public static final String NAO_INICIALIZADO = "N\u00e3o Inicializado";
	
	public  static String getEstadoTarefa(Component component) {		
        	Tarefa tarefa = FindByProcess.findTarefa(component);
        	if(tarefa.getEstado()!=null){        		
        		if(tarefa.getEstado().equals(EXECUCAO))
        			return ComponentImageFile.TASK_USE_PLAY;
        		else if (tarefa.getEstado().equals(PAUSADA))
        			return ComponentImageFile.TASK_USE_PAUSED;
        		else if(tarefa.getEstado().equals(FINALIZADA))
        			return ComponentImageFile.TASK_USE_FINISHED;        		
        	}
        	return ComponentImageFile.TASK_USE;
	}

	public static String getEstadoAtividade(Component component) {
		int pausada, execucao, finalizada, naoiniciada;
		pausada = execucao = finalizada = naoiniciada = 0;
		Atividade atividade = FindByProcess.findAtividade(component);
		for (Tarefa tarefa : atividade.getTarefa()) {
			if(tarefa.getEstado() == null)
				naoiniciada++;
			else if(tarefa.getEstado().equals(FINALIZADA))
				finalizada++;
			else if(tarefa.getEstado().equals(EXECUCAO))
				execucao++;
			else if(tarefa.getEstado().equals(PAUSADA)) {
				pausada++;
			}
		}	
		
		for (Atividade atv : atividade.getAtividades()) {
			if(atv.getEstado() == null)
				naoiniciada++;
			else if(atv.getEstado().equals(FINALIZADA))
				finalizada++;
			else if(atv.getEstado().equals(EXECUCAO))
				execucao++;
			else if(atv.getEstado().equals(PAUSADA)) {
				pausada++;
			}
		}	
		
		if(execucao>0 && naoiniciada==0 && finalizada == 0 && pausada == 0)
			return ComponentImageFile.ACTIVITY_PLAY;
		if(execucao==0 && naoiniciada==0 && finalizada >  0 && pausada == 0)
			return ComponentImageFile.ACTIVITY_CHECK;
		if(execucao==0 && naoiniciada==0 && finalizada ==  0 && pausada > 0)
			return ComponentImageFile.ACTIVITY_PAUSE;
		if(execucao>0)
			return ComponentImageFile.ACTIVITY_PLAY;
		if((naoiniciada>0 || finalizada>0) && execucao == 0 && pausada>0)
			return  ComponentImageFile.ACTIVITY_PAUSE;
		if(naoiniciada>0 && finalizada>0 && execucao == 0 && pausada==0)
			return  ComponentImageFile.ACTIVITY_PLAY;
		return ComponentImageFile.ACTIVITY;
	}

	public static String getEstadoFase(Component component) {
		Fase fase = FindByProcess.findFase(component);
		if(fase!=null){
			if (fase.getEstado() == null)
				return ComponentImageFile.PHASE;
			else if(fase.getEstado().equals(EXECUCAO))
				return  ComponentImageFile.PHASE_PLAY;
			else if(fase.getEstado().equals(PAUSADA))
				return ComponentImageFile.PHASE_PAUSE;
			else if(fase.getEstado().equals(FINALIZADA))
				return ComponentImageFile.PHASE_CHECK;
		}
		return ComponentImageFile.PHASE;
	}
	public static String getEstadoIteracao(Component component) {
		Iteracao iteracao = FindByProcess.findIteracao(component);
		if(iteracao.getEstado() == null)
			return  ComponentImageFile.ITERATION;
		else if(iteracao.getEstado().equals(EXECUCAO))
			return  ComponentImageFile.ITERATION_PLAY;
		else if(iteracao.getEstado().equals(PAUSADA))
			return ComponentImageFile.ITERATION_PAUSE;
		else if(iteracao.getEstado().equals(FINALIZADA))
			return ComponentImageFile.ITERATION_CHECK;
		return ComponentImageFile.ITERATION;
	}
	
	public static String getEstadoProcesso(Component component) {
		Processo processo = null;
		if(JDialog_Spider_Login.getInstance().getLogado() == 2){
			 processo = JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso();
		 } else if(JDialog_Spider_Login.getInstance().getLogado() == 3){
			 processo = JDialog_Spider_Login.getInstance().view.getProcesso();
		 }
		if(processo!=null){
			if (processo.getEstado() == null)
				return ComponentImageFile.PROCESS;
			if(processo.getEstado().equals(EXECUCAO))
				return  ComponentImageFile.PROCESS_PLAY;
			else if(processo.getEstado().equals(PAUSADA))
				return ComponentImageFile.PROCESS_PAUSE;
			else if(processo.getEstado().equals(FINALIZADA))
				return ComponentImageFile.PROCESS_CHECK;
		}
			return ComponentImageFile.PROCESS;

	}

	public static void update(Tarefa tarefa) {
		Atividade atividade = tarefa.getAtividade();	
		if(atividade!=null)
			ExecutionController.setEstadoAtividade(atividade);
		
		Iteracao iteracao;
		if(atividade==null)
			 iteracao = tarefa.getIteracao();
		else
			 iteracao = atividade.getIteracao();
		if(iteracao!=null)
			ExecutionController.setEstadoIteracao(iteracao);
		Fase fase;
		
		if(atividade==null)
			 fase = iteracao.getFase();
		else
			 fase = atividade.getFase();
		
		if(fase==null)
			fase = iteracao.getFase();
		Processo processo = null;		
		if(fase!=null){
			ExecutionController.setEstadoFase(fase);
			 processo = fase.getProcesso();
		}
		
		if(processo!=null)
			ExecutionController.setEstadoProcesso(processo);
			
	}

	private static void setEstadoProcesso(Processo processo) {
		int pausada, execucao, finalizada, naoiniciada;
		pausada = execucao = finalizada = naoiniciada = 0;
		
		String estado = null;
		
		for (Fase fase : processo.getFase()) {
			if(fase.getEstado() == null)
				naoiniciada++;
			else if(fase.getEstado().equals(FINALIZADA))
				finalizada++;
			else if(fase.getEstado().equals(EXECUCAO))
				execucao++;
			else if(fase.getEstado().equals(PAUSADA)) {
				pausada++;
			}
		}		
		
		if(execucao>0 && naoiniciada==0 && finalizada == 0 && pausada == 0)
			estado = EXECUCAO;
		if(execucao==0 && naoiniciada==0 && finalizada >  0 && pausada == 0)
			estado = FINALIZADA;
		if(execucao==0 && naoiniciada==0 && finalizada ==  0 && pausada > 0)
			estado = PAUSADA;
		if(execucao>0)
			estado = EXECUCAO;
		if((naoiniciada>0 || finalizada>0) && execucao == 0 && pausada>0)
			estado = PAUSADA;
		if(naoiniciada>0 && finalizada>0 && execucao == 0 && pausada==0)
			estado = EXECUCAO;
		
		if(estado!=null){
			processo.setEstado(estado);
			ProcessoDAO.updateProcesso(processo);
		}		
	}

	private static void setEstadoFase(Fase fase) {
		int pausada, execucao, finalizada, naoiniciada;
		pausada = execucao = finalizada = naoiniciada = 0;
		
		String estado = null;
		
			for (Atividade atividade : fase.getAtividades()) {
				if(atividade.getEstado() == null)
					naoiniciada++;
				else if(atividade.getEstado().equals(FINALIZADA))
					finalizada++;
				else if(atividade.getEstado().equals(EXECUCAO))
					execucao++;
				else if(atividade.getEstado().equals(PAUSADA)) {
					pausada++;
				}
			}
			
			for (Iteracao iteracao : fase.getIteracao()) {
				if(iteracao.getEstado() == null)
					naoiniciada++;
				else if(iteracao.getEstado().equals(FINALIZADA))
					finalizada++;
				else if(iteracao.getEstado().equals(EXECUCAO))
					execucao++;
				else if(iteracao.getEstado().equals(PAUSADA)) {
					pausada++;
				}
			}
		
		if(execucao>0 && naoiniciada==0 && finalizada == 0 && pausada == 0)
			estado = EXECUCAO;
		if(execucao==0 && naoiniciada==0 && finalizada >  0 && pausada == 0)
			estado = FINALIZADA;
		if(execucao==0 && naoiniciada==0 && finalizada ==  0 && pausada > 0)
			estado = PAUSADA;
		if(execucao>0)
			estado = EXECUCAO;
		if((naoiniciada>0 || finalizada>0) && execucao == 0 && pausada>0)
			estado = PAUSADA;
		if(naoiniciada>0 && finalizada>0 && execucao == 0 && pausada==0)
			estado = EXECUCAO;
		if(estado!=null){
			fase.setEstado(estado);
			FaseDAO.updateFase(fase);
		}		
		
	}

	private static void setEstadoIteracao(Iteracao iteracao) {
		int pausada, execucao, finalizada, naoiniciada;
		pausada = execucao = finalizada = naoiniciada = 0;
		
		String estado = null;
		
		for (Atividade atividade : iteracao.getAtividade()) {
			if(atividade.getEstado() == null)
				naoiniciada++;
			else if(atividade.getEstado().equals(FINALIZADA))
				finalizada++;
			else if(atividade.getEstado().equals(EXECUCAO))
				execucao++;
			else if(atividade.getEstado().equals(PAUSADA)) {
				pausada++;
			}
		}	
		
		for (Tarefa tarefa : iteracao.getTarefa()) {
			if(tarefa.getEstado() == null)
				naoiniciada++;
			else if(tarefa.getEstado().equals(FINALIZADA))
				finalizada++;
			else if(tarefa.getEstado().equals(EXECUCAO))
				execucao++;
			else if(tarefa.getEstado().equals(PAUSADA)) {
				pausada++;
			}
		}	
		
		if(execucao>0 && naoiniciada==0 && finalizada == 0 && pausada == 0)
			estado = EXECUCAO;
		if(execucao==0 && naoiniciada==0 && finalizada >  0 && pausada == 0)
			estado = FINALIZADA;
		if(execucao==0 && naoiniciada==0 && finalizada ==  0 && pausada > 0)
			estado = PAUSADA;
		if(execucao>0)
			estado = EXECUCAO;
		if((naoiniciada>0 || finalizada>0) && execucao == 0 && pausada>0)
			estado = PAUSADA;
		if(naoiniciada>0 && finalizada>0 && execucao == 0 && pausada==0)
			estado = EXECUCAO;
		
		if(estado!=null){
			iteracao.setEstado(estado);
			IteracaoDAO.updateIteracao(iteracao);
		}		
	}

	private static void setEstadoAtividade(Atividade atividade) {
		String estado = ExecutionController.getEstadoAtividade(Modelling.getModelling().getComponent(atividade.getIdComponent()));
		if(estado.equals(ComponentImageFile.ACTIVITY_PLAY)){
			atividade.setEstado(EXECUCAO);
		} else if(estado.equals(ComponentImageFile.ACTIVITY_PAUSE)){
			atividade.setEstado(PAUSADA);
		} else if(estado.equals(ComponentImageFile.ACTIVITY_CHECK)){
			atividade.setEstado(FINALIZADA);
		}
		AtividadeDAO.updateAtividade(atividade);
	}
	
	public static Tarefa getEstadoTarefaJoinTaskUse(ArrayList<Tarefa> tarefas) {
		int pausada, execucao, finalizada, naoiniciada;
		pausada = execucao = finalizada = naoiniciada = 0;
		for (Tarefa tarefa : tarefas) {
			if(tarefa.getEstado()!=null){
				if(tarefa.getEstado().equals(FINALIZADA))
					finalizada++;
				else if(tarefa.getEstado().equals(EXECUCAO))
					execucao++;
				else if(tarefa.getEstado().equals(PAUSADA)) {
					pausada++;
				}
			}
		}	
		Tarefa tarefa = new Tarefa();
		String estado = "";
		if(execucao>0 && naoiniciada==0 && finalizada == 0 && pausada == 0)
			estado = EXECUCAO;
		if(execucao==0 && naoiniciada==0 && finalizada >  0 && pausada == 0)
			estado =  FINALIZADA;
		if(execucao==0 && naoiniciada==0 && finalizada ==  0 && pausada > 0)
			estado = PAUSADA;
		if(execucao>0)
			estado = EXECUCAO;
		if((naoiniciada>0 || finalizada>0) && execucao == 0 && pausada>0)
			estado = PAUSADA;
		if(naoiniciada>0 && finalizada>0 && execucao == 0 && pausada==0)
			estado =  EXECUCAO;
		
		tarefa.setEstado(estado);
		return tarefa;
	}

	public static String getEstadoMarco(
			br.ufpa.spider.pe.view.pm.model.Component component) {
		Marco marco = FindByProcess.findMarco(component);
		if(marco.getEstado() == null)
			return  ComponentImageFile.MILESTONE;
		else if(marco.getEstado().equals(EXECUCAO))
			return  ComponentImageFile.MILESTONE_PLAY;
		else if(marco.getEstado().equals(PAUSADA))
			return ComponentImageFile.MILESTONE_PAUSE;
		else if(marco.getEstado().equals(FINALIZADA))
			return ComponentImageFile.MILESTONE_CHECK;
		return ComponentImageFile.MILESTONE;
	}

}
