package br.ufpa.spider.pe.view.pm.logic;

import br.ufpa.spider.pe.model.Atividade;
import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Iteracao;
import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.dao.AtividadeDAO;
import br.ufpa.spider.pe.model.dao.FaseDAO;
import br.ufpa.spider.pe.model.dao.IteracaoDAO;
import br.ufpa.spider.pe.model.dao.MarcoDAO;
import br.ufpa.spider.pe.model.dao.ProcessoDAO;
import br.ufpa.spider.pe.model.dao.TarefaDAO;
import br.ufpa.spider.pe.view.JDialog_Spider_Login;
import br.ufpa.spider.pe.view.pm.model.Component;

public class FindByProcess {

	public static Atividade findAtividade(Component component) {
		 for (Atividade atividade : AtividadeDAO.findAll()) {
			 if(atividade.getNome().equals(component.getName()) && atividade.getIdComponent() == component.getId()){
				 if(JDialog_Spider_Login.getInstance().getLogado() == 2){
					 if(AtividadeDAO.getProcesso(atividade).equals(JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso()))
					 { return atividade;}
				 } else  if(JDialog_Spider_Login.getInstance().getLogado() == 3){
					 if(AtividadeDAO.getProcesso(atividade).equals(JDialog_Spider_Login.getInstance().view.getProcesso()))
					 { return atividade;}
				 }
					
			 }
		}
		return null;     
	}
	
	public static Tarefa findTarefa(Component component) {
		 for (Tarefa tarefa : TarefaDAO.findAll()) {
			 if(tarefa.getNome().equals(component.getName()))
				 if(tarefa.getIdComponent() == component.getId())
					 if(JDialog_Spider_Login.getInstance().getLogado() == 2){
						 if(TarefaDAO.getProcesso(tarefa).equals(JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso())){
						 	return tarefa;
						 }
					 } else if(JDialog_Spider_Login.getInstance().getLogado() == 3){
						 if(TarefaDAO.getProcesso(tarefa).equals(JDialog_Spider_Login.getInstance().view.getProcesso())){
							 	return tarefa;
							 }
					 }
		}
		return null;     
	}
	
	public static Marco findMarco(Component component) {
		 for (Marco marco : MarcoDAO.findAll()) {
			 if(marco.getNome().equals(component.getName()))
				 if(marco.getIdComponent() == component.getId())
					 if(JDialog_Spider_Login.getInstance().getLogado() == 2){
						 if(MarcoDAO.getProcesso(marco).equals(JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso())){
						 	return marco;
						 }
					 } else if(JDialog_Spider_Login.getInstance().getLogado() == 3){
						 if(MarcoDAO.getProcesso(marco).equals(JDialog_Spider_Login.getInstance().view.getProcesso())){
							 	return marco;
							 }
					 }
		}
		return null;     
	}
	
	public static Fase findFase(Component component) {
		 for (Fase fase : FaseDAO.findAll()) {
			 if(fase.getNome().equals(component.getName()))
				 if(fase.getIdComponent() == component.getId())
					 if(JDialog_Spider_Login.getInstance().getLogado() == 2){
						 if(FaseDAO.getProcesso(fase).equals(JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso())){
						 	return fase;
						 }
					 } else if(JDialog_Spider_Login.getInstance().getLogado() == 3){
						 if(FaseDAO.getProcesso(fase).equals(JDialog_Spider_Login.getInstance().view.getProcesso())){
							 	return fase;
							 }
					 }
		}
		return null;     
	}

	public static Iteracao findIteracao(Component component) {
		for (Iteracao iteracao : IteracaoDAO.findAll()) {
			 if(iteracao.getNome().equals(component.getName()))
				 if(iteracao.getIdComponent() == component.getId())
					 if(JDialog_Spider_Login.getInstance().getLogado() == 2){
						 if(IteracaoDAO.getProcesso(iteracao).equals(JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso())){
						 	return iteracao;
						 }
					 } else if(JDialog_Spider_Login.getInstance().getLogado() == 3){
						 if(IteracaoDAO.getProcesso(iteracao).equals(JDialog_Spider_Login.getInstance().view.getProcesso())){
							 	return iteracao;
							 }
					 }
		}
		return null;     
	}

	public static Processo findProcesso(Component component) {
		for (Processo processo : ProcessoDAO.findAll()) {
			 if(processo.getNome().equals(component.getName()))
				 if(processo.getIdComponent() == component.getId())
					 if(JDialog_Spider_Login.getInstance().getLogado() == 2){
						 if(processo.equals(JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso())){
						 	return processo;
						 }
					 } else if(JDialog_Spider_Login.getInstance().getLogado() == 3){
						 if(processo.equals(JDialog_Spider_Login.getInstance().view.getProcesso())){
							 	return processo;
							 }
					 }
		}
		return null; 
	}

}
