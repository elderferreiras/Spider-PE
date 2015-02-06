package br.ufpa.spider.pe.model.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.controller.AtividadeController;
import br.ufpa.spider.pe.controller.FaseController;
import br.ufpa.spider.pe.controller.IteracaoController;
import br.ufpa.spider.pe.controller.MarcoController;
import br.ufpa.spider.pe.controller.PapelController;
import br.ufpa.spider.pe.controller.ProcedimentoController;
import br.ufpa.spider.pe.controller.ProcessoController;
import br.ufpa.spider.pe.controller.ProdutoTrabalhoController;
import br.ufpa.spider.pe.controller.SoftwareController;
import br.ufpa.spider.pe.controller.TarefaController;
import br.ufpa.spider.pe.model.Atividade;
import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Iteracao;
import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.Papel;
import br.ufpa.spider.pe.model.Procedimento;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.ProdutoTrabalho;
import br.ufpa.spider.pe.model.Software;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.dao.AtividadeDAO;
import br.ufpa.spider.pe.model.dao.FaseDAO;
import br.ufpa.spider.pe.model.dao.IteracaoDAO;
import br.ufpa.spider.pe.model.dao.MarcoDAO;
import br.ufpa.spider.pe.model.dao.PapelDAO;
import br.ufpa.spider.pe.model.dao.ProcedimentoDAO;
import br.ufpa.spider.pe.model.dao.ProcessoDAO;
import br.ufpa.spider.pe.model.dao.ProdutoTrabalhoDAO;
import br.ufpa.spider.pe.model.dao.SoftwareDAO;
import br.ufpa.spider.pe.model.dao.TarefaDAO;
import br.ufpa.spider.pe.view.pm.model.ComponentType;
import br.ufpa.spider.pe.view.pm.model.DiagramComponent;
import br.ufpa.spider.pe.view.pm.persistence.ModellingFileManager;
import br.ufpa.spider.pe.view.pm.persistence.SimpleComponent;
import br.ufpa.spider.pe.view.pm.persistence.SimpleModelling;

public class Converter {
//	public static void main(String[] args) {
//		setPathSource("/home/andre/Área de Trabalho/ProcessosInstanciados.xml");
//		try {
//			load();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	private static SimpleModelling simple;
	private static String pathSource;
	
	public Converter(String path) {
		setPathSource(path);
	}
	
	public static void load() throws FileNotFoundException, IOException{
		simple = ModellingFileManager.load2(pathSource);
		importProcessos(getListComponents(ComponentType.PROCESS));
		importFases(getListComponents(ComponentType.PHASE));
		importIteracoes(getListComponents(ComponentType.ITERATION));
		importMarcos(getListComponents(ComponentType.MILESTONE));
		importPapeis(getListComponents(ComponentType.ROLE_USE));
		importProdutosTrabalho(getListComponents(ComponentType.WORK_PRODUCT_USE));
		importSoftwares(getListComponents(ComponentType.TOOL_USE));
		importProcedimentos(getListComponents(ComponentType.GUIDANCE));
		importAtividades(getListComponents(ComponentType.ACTIVITY));
		importTarefas(getListComponents(ComponentType.TASK_USE));
		attMarcosHere();
	}
	
	
	private static List<SimpleComponent> getListComponents(ComponentType tipo){
		List<SimpleComponent> componentes = new ArrayList<SimpleComponent>();
		for (SimpleComponent comp : simple.getComponents()) {
			if(comp.getType().equals(tipo)){
				componentes.add(comp);
			}
		}
		return componentes;
	}
	
	private static void importProcessos(List<SimpleComponent> processos){
		for (SimpleComponent simpleComponent : processos) {
			File file = new File(pathSource);
			
			byte[] bFile = new byte[(int) file.length()];
			 
	        try {
	            FileInputStream fileInputStream = new FileInputStream(file);
	            fileInputStream.read(bFile);
	            fileInputStream.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
			Processo processo = new Processo();
			processo.setIdComponent(simpleComponent.getId());
			processo.setArqXML(pathSource);
			processo.setNome(simpleComponent.getName());
			processo.setObjetivos(simpleComponent.getObjectives());
			processo.setDescricao(simpleComponent.getDescription());
			processo.setProcesso_xml(bFile);
			processo.setInformacoesAdicionais(simpleComponent.getAdditionalInformations());
			ProcessoController.saveProcesso(processo, false);
		}
	}
	
	private static void importFases(List<SimpleComponent> fases){
		for (SimpleComponent simpleComponent : fases) {
			Fase fase = new Fase();
			fase.setIdComponent(simpleComponent.getId());
			fase.setNome(simpleComponent.getName());
			fase.setCicloVida(simpleComponent.getDetails());
			fase.setObjetivos(simpleComponent.getObjectives());
			fase.setDescricao(simpleComponent.getDescription());
			fase.setInformacoesAdicionais(simpleComponent.getAdditionalInformations());
			Processo processo = ProcessoDAO.findByIdComponentAndNameArqXml(simpleComponent.getParentId(), pathSource);
			if(processo != null){
				fase.setProcesso(processo);
				processo.getFase().add(fase);
				ProcessoController.saveProcesso(processo, false);
			}
			
			FaseController.saveFase(fase, false);
			
			if(fase.getCicloVida().equals("SEQUENTIAL")){
				 Marco marco = new Marco();
				 marco.setNome("Marco da "+fase.getNome());
				 marco.setFase(fase);
				 MarcoController.saveMarco(marco, false);
				 FaseController.saveFase(fase, false);
			}
		}
	}
	
	private static void importIteracoes(List<SimpleComponent> iteracoes){
		for (SimpleComponent simpleComponent : iteracoes) {
			Iteracao iteracao = new Iteracao();
			iteracao.setIdComponent(simpleComponent.getId());
			iteracao.setNome(simpleComponent.getName());
			iteracao.setObjetivos(simpleComponent.getObjectives());
			iteracao.setDescricao(simpleComponent.getDescription());
			iteracao.setInformacoesAdicionais(simpleComponent.getAdditionalInformations());
			Fase fase = FaseDAO.findByIdComponent(simpleComponent.getParentId(), pathSource);
			if(fase != null){
				iteracao.setFase(fase);
				fase.getIteracao().add(iteracao);
				IteracaoController.saveIteracao(iteracao, false);
			}
			IteracaoController.saveIteracao(iteracao, false);
		}
	}
	
	private static void importMarcos(List<SimpleComponent> marcos){
		for (SimpleComponent simpleComponent : marcos) {
			Marco marco = new Marco();
			marco.setIdComponent(simpleComponent.getId());
			marco.setNome(simpleComponent.getName());
			marco.setObjetivos(simpleComponent.getObjectives());
			marco.setDescricao(simpleComponent.getDescription());
			marco.setInformacoesAdicionais(simpleComponent.getAdditionalInformations());
			Fase fase = FaseDAO.findByIdComponent(simpleComponent.getParentId(), pathSource);
			if(fase != null){
				marco.setFase(fase);
				fase.getMarco().add(marco);
				MarcoController.saveMarco(marco, false);
			}
			MarcoController.saveMarco(marco, false);
		}

	}
	
	private static void importAtividades(List<SimpleComponent> atividades){
		List<Atividade> atvs = new ArrayList<Atividade>();
		List<Integer> idsParents = new ArrayList<Integer>();
		for (SimpleComponent simpleComponent : atividades) {
			Atividade atividade = new Atividade();
			atividade.setIdComponent(simpleComponent.getId());
			atividade.setArqXML(pathSource);
			atividade.setNome(simpleComponent.getName());
			atividade.setObjetivos(simpleComponent.getObjectives());
			atividade.setDescricao(simpleComponent.getDescription());
			atividade.setInformacoesAdicionais(simpleComponent.getAdditionalInformations());
			Iteracao iteracao = IteracaoDAO.findByIdComponent(simpleComponent.getParentId(), pathSource);
			Fase fase = FaseDAO.findByIdComponent(simpleComponent.getParentId(), pathSource);
			if(iteracao != null){
				atividade.setIteracao(iteracao);
			} else if (fase != null){
				atividade.setFase(fase);
			}
			atvs.add(atividade);
			idsParents.add(simpleComponent.getParentId());
		}
		int index = 0;
		for (Atividade atividade : atvs) {
			for(Atividade atividade2 : atvs){
				if(atividade2.getIdComponent() == idsParents.get(index)){
					if(atividade.getFase() == null && atividade.getIteracao() == null){
					atividade.setAtividade(atividade2);
					}
				}
			}
			index++;
		}
		
		for (Atividade a : atvs) {
			AtividadeController.saveatividade(a, false);
		}
	
	}
	
	private static void importTarefas(List<SimpleComponent> tarefas){
		List<Papel> papeisNovos = new ArrayList<Papel>();
		for (SimpleComponent simpleComponent : tarefas) {
			Tarefa tarefa = new Tarefa();
			tarefa.setIdComponent(simpleComponent.getId());
			tarefa.setNome(simpleComponent.getName());
			tarefa.setHabilidadesRequeridas(simpleComponent.getDetails());
			tarefa.setObjetivos(simpleComponent.getObjectives());
			tarefa.setDescricao(simpleComponent.getDescription());
			tarefa.setInformacoesAdicionais(simpleComponent.getAdditionalInformations());
			Iteracao iteracao = IteracaoDAO.findByIdComponent(simpleComponent.getParentId(), pathSource);
			Fase fase = FaseDAO.findByIdComponent(simpleComponent.getParentId(), pathSource);
			Atividade atividade = AtividadeDAO.findByIdComponent(simpleComponent.getParentId(), pathSource);
			if(iteracao != null){
				tarefa.setIteracao(iteracao);
			} else if (fase != null){
				tarefa.setFase(fase);
			} else if (atividade != null){
				tarefa.setAtividade(atividade);
			}
			TarefaController.saveTarefa(tarefa, false);
			for (DiagramComponent diagramComponent : simpleComponent.getDiagram().getDiagramComponents()) {
				Software software = SoftwareDAO.findByIdComponent(diagramComponent.getId(), pathSource);
				ProdutoTrabalho produto = ProdutoTrabalhoDAO.findByIdComponent(diagramComponent.getId(), pathSource);
				Procedimento procedimento = ProcedimentoDAO.findByIdComponent(diagramComponent.getId(), pathSource);
				Papel papel = PapelDAO.findByIdComponent(diagramComponent.getId(), pathSource);
				if(software != null){
					tarefa.getSoftwares().add(software);
				} else if (produto != null){
					tarefa.getProdutoTrabalho().add(produto);
				} else if (procedimento != null){
					tarefa.getProcedimentos().add(procedimento);
				} else if (papel != null){
					if(papel.getTarefa() == null){
					 papel.setTarefa(tarefa);
					 tarefa.getPapeis().add(papel);
					} else {
						Papel papelNovo = new Papel();
						papelNovo.setArqXML(papel.getArqXML());
						papelNovo.setDescricao(papel.getDescricao());
						papelNovo.setIdComponent(papel.getIdComponent());
						papelNovo.setInformacoesAdicionais(papel.getInformacoesAdicionais());
						papelNovo.setNome(papel.getNome());
						papelNovo.setObjetivos(papel.getObjetivos());
						papelNovo.setTarefa(tarefa);
						tarefa.getPapeis().add(papelNovo);
						papeisNovos.add(papelNovo);
					}
				}
			}
			TarefaController.saveTarefa(tarefa, false);
		}
		
		
		for(Papel papel : papeisNovos){
			PapelController.savePapel(papel, false);
		}
	}
	
	private static void importPapeis(List<SimpleComponent> papeis){
		for (SimpleComponent simpleComponent : papeis) {
			Papel papel = new Papel();
			papel.setIdComponent(simpleComponent.getId());
			papel.setNome(simpleComponent.getName());
			papel.setArqXML(pathSource);
			papel.setObjetivos(simpleComponent.getObjectives());
			papel.setDescricao(simpleComponent.getDescription());
			papel.setInformacoesAdicionais(simpleComponent.getAdditionalInformations());
			PapelController.savePapel(papel, false);
		}
	}
	
	private static void importSoftwares(List<SimpleComponent> softwares){
		for (SimpleComponent simpleComponent : softwares) {
			Software software = new Software();
			software.setIdComponent(simpleComponent.getId());
			software.setNome(simpleComponent.getName());
			software.setArqXML(pathSource);
			software.setCaminho(simpleComponent.getDetails());
			software.setObjetivos(simpleComponent.getObjectives());
			software.setDescricao(simpleComponent.getDescription());
			software.setInformacoesAdicionais(simpleComponent.getAdditionalInformations());
			SoftwareController.saveSoftware(software, false);
		}
	}
	
	private static void importProdutosTrabalho(List<SimpleComponent> produtos){
		for (SimpleComponent simpleComponent : produtos) {
			ProdutoTrabalho produto = new ProdutoTrabalho();
			produto.setIdComponent(simpleComponent.getId());
			produto.setNome(simpleComponent.getName());
			produto.setArqXML(pathSource);
			produto.setCaminho(simpleComponent.getDetails());
			produto.setObjetivos(simpleComponent.getObjectives());
			produto.setDescricao(simpleComponent.getDescription());
			produto.setInformacoesAdicionais(simpleComponent.getAdditionalInformations());
			ProdutoTrabalhoController.saveProdutoTrabalho(produto, false);
		}
	}
	
	private static void importProcedimentos(List<SimpleComponent> procedimentos){
		for (SimpleComponent simpleComponent : procedimentos) {
			Procedimento procedimento = new Procedimento();
			procedimento.setIdComponent(simpleComponent.getId());
			procedimento.setNome(simpleComponent.getName());
			procedimento.setArqXML(pathSource);
			procedimento.setCaminho(simpleComponent.getDetails());
			procedimento.setObjetivos(simpleComponent.getObjectives());
			procedimento.setDescricao(simpleComponent.getDescription());
			procedimento.setInformacoesAdicionais(simpleComponent.getAdditionalInformations());
			ProcedimentoController.save(procedimento, false);
		}
	}
	
	public static void setPathSource(String path){
		pathSource = path;
	}
	
	public static void attMarcosHere(){
		List<Processo> processos = ProcessoDAO.findAll();
		for (Processo processo : processos) {
			MarcoDAO.attMarcos(processo);
		}
	}
	

}
