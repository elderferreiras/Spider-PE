package br.ufpa.spider.pe.model.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

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

public class XMLParse {
	private static final String COMPONENT = "component";
	private static final String TYPE = "type";
	
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String OBJECTIVES = "objectives";
	private static final String DESCRIPTION = "description";
	private static final String ADDITIONALINFORMATION = "additionalInformations";
	private static final String DATAILS = "datails";
	private static final String PARENTID = "parentId";
	private static final String DIAGRAMCOMPONENTS = "diagramComponents";
	
	private XMLEvent event = null;
	private XMLEventReader eventReader = null;
	
	
	private String nameFile;
	
	//FIELDS XML COMPONENTS
	private String name="", objectives="", description="", additionalinformation="", datails="";
	private List<Integer> diagramComponentsIds = new ArrayList<Integer>();
	private int id;
	private int parentid;
	Object object = null;
	
	/*CONSTRUCTOR*/
	public XMLParse(String fileXml) {
		this.nameFile = fileXml;
	}
	

	
	
	@SuppressWarnings({ "unchecked", "null" })
	public void readConfig() throws FileNotFoundException, XMLStreamException {
		// First create a new XMLInputFactory
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		// Setup a new eventReader
		InputStream in = new FileInputStream(nameFile);
		eventReader = inputFactory.createXMLEventReader(in);
		int cont = 0;
		while (eventReader.hasNext()) {
			cont++;
			event = eventReader.nextEvent();
			if(event.isStartElement()){
				if(event.asStartElement().getName().getLocalPart() == (COMPONENT)){
					while(eventReader.hasNext()){
						event = eventReader.nextEvent();
						if(event.isStartElement()){
							if(event.asStartElement().getName().getLocalPart().equals(DIAGRAMCOMPONENTS)){
								while(eventReader.hasNext()){
									event = eventReader.nextEvent();
									if(event.isStartElement()){
										if(event.asStartElement().getName().getLocalPart().equals(ID)){
											if(event.isCharacters()){
												diagramComponentsIds.add(Integer.parseInt(event.asCharacters().getData()));
											}
										}
									}
									/** aqui pode pegar informacoes quanto a posicoes dos itens **/
									if(event.isEndElement()){
										if(event.asEndElement().getName().getLocalPart().equals(DIAGRAMCOMPONENTS)){
											event = eventReader.nextEvent();
											break;
										}
									}
								}
							}
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(TYPE)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										object = identifyType(event.asCharacters().getData());
										continue;
									}
								}
							}
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(ID)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										id = Integer.parseInt(event.asCharacters().getData());
										continue;
									}
								}
							}
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(NAME)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										name = event.asCharacters().getData();
										continue;
									}
								}
							}
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(PARENTID)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										parentid = Integer.parseInt(event.asCharacters().getData());
										continue;
									}
								}
							}
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(OBJECTIVES)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										objectives = event.asCharacters().getData();
										continue;
									}
								}
							}
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(DESCRIPTION)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										description = event.asCharacters().getData();
										continue;
									}
								}
							}
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(ADDITIONALINFORMATION)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										additionalinformation = event.asCharacters().getData();
										continue;
									}
								}
							}
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(DATAILS)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										datails = event.asCharacters().getData();
										continue;
									}
								}
							}
						}
						if(event.isEndElement()){
							if(event.asEndElement().getName().getLocalPart().equals(COMPONENT)){
								break;
							}
						}
					}
				}
			}
			
		/*PROCESSO*/

		if(object instanceof Processo){
			File file = new File(nameFile);
			
			byte[] bFile = new byte[(int) file.length()];
			 
	        try {
	            FileInputStream fileInputStream = new FileInputStream(file);
	            fileInputStream.read(bFile);
	            fileInputStream.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			
			Processo processo = new Processo();
			processo.setNome(name);
			processo.setIdComponent(id);
			processo.setDescricao(description);
			processo.setInformacoesAdicionais(additionalinformation);
			processo.setObjetivos(objectives);
			processo.setArqXML(nameFile);
			processo.setProcesso_xml(bFile);
			ProcessoController.saveProcesso(processo, false);
			setDefaultValuesAllFields();
		}
		/*FASE*/
		if (object instanceof Fase){
			Fase fase = new Fase();
			fase.setIdComponent(id);
			fase.setNome(name);
			fase.setCicloVida(datails);
			fase.setObjetivos(objectives);
			fase.setDescricao(description);
			fase.setInformacoesAdicionais(additionalinformation);
			FaseController.saveFase(fase, false);
			setDefaultValuesAllFields();
		}
		/*ITERACAO*/
		if (object instanceof Iteracao){
			Iteracao iteracao = new Iteracao();
			iteracao.setIdComponent(id);
			iteracao.setNome(name);
			iteracao.setObjetivos(objectives);
			iteracao.setDescricao(description);
			iteracao.setInformacoesAdicionais(additionalinformation);
			IteracaoController.saveIteracao(iteracao, false);
			setDefaultValuesAllFields();
		}
		/*ATIVIDADE*/
		if (object instanceof Atividade){
			Atividade atividade = new Atividade();
			atividade.setIdComponent(id);
			atividade.setNome(name);
			atividade.setObjetivos(objectives);
			atividade.setDescricao(description);
			atividade.setInformacoesAdicionais(additionalinformation);
			AtividadeController.saveatividade(atividade, false);
			setDefaultValuesAllFields();
		}
		/*TAREFA*/
		if (object instanceof Tarefa){
			Tarefa tarefa = new Tarefa();
			tarefa.setIdComponent(id);
			tarefa.setNome(name);
			tarefa.setHabilidadesRequeridas(datails);
			tarefa.setObjetivos(objectives);
			tarefa.setDescricao(description);
			tarefa.setArqXML(nameFile);
			tarefa.setInformacoesAdicionais(additionalinformation);
			TarefaController.saveTarefa(tarefa, false);
			setDefaultValuesAllFields();
			
		}
		/*MARCO*/
		if (object instanceof Marco){
			Marco marco = new Marco();
			marco.setIdComponent(id);
			marco.setNome(name);
			marco.setObjetivos(objectives);
			marco.setDescricao(description);
			marco.setInformacoesAdicionais(additionalinformation);
			MarcoController.saveMarco(marco, false);
			setDefaultValuesAllFields();
		}
		/*PAPEL*/
		if (object instanceof Papel){
			Papel papel = new Papel();
			papel.setIdComponent(id);
			papel.setNome(name);
			papel.setObjetivos(objectives);
			papel.setDescricao(description);
			papel.setArqXML(nameFile);
			papel.setInformacoesAdicionais(additionalinformation);
			PapelController.savePapel(papel, false);
			setDefaultValuesAllFields();
		}
		/*PRODUTO DE TRABALHO*/
		if (object instanceof ProdutoTrabalho){
			ProdutoTrabalho produto = new ProdutoTrabalho();
			produto.setIdComponent(id);
			produto.setNome(name);
			produto.setObjetivos(objectives);
			produto.setDescricao(description);
			produto.setArqXML(nameFile);
			produto.setInformacoesAdicionais(additionalinformation);
			produto.setCaminho(datails);
			
			ProdutoTrabalhoController.saveProdutoTrabalho(produto, false);
			setDefaultValuesAllFields();
		}
		/*SOFTWARE*/
		if (object instanceof Software){
			Software software = new Software();
			software.setIdComponent(id);
			software.setDescricao(description);
			software.setObjetivos(objectives);
			software.setInformacoesAdicionais(additionalinformation);
			software.setCaminho(datails);
			software.setNome(name);
			software.setArqXML(nameFile);
			SoftwareController.saveSoftware(software, false);
			setDefaultValuesAllFields();
		}
		/*PROCEDIMENTO*/
		if (object instanceof Procedimento){
			Procedimento procedimento = new Procedimento();
			procedimento.setIdComponent(id);
			procedimento.setNome(name);
			procedimento.setInformacoesAdicionais(additionalinformation);
			procedimento.setDescricao(description);
			procedimento.setCaminho(datails);
			procedimento.setArqXML(nameFile);
			procedimento.setObjetivos(objectives);
			ProcedimentoController.save(procedimento, false);
			setDefaultValuesAllFields();
		}
	  }
		readConfigRelationshios();
	}
	
	private void readConfigRelationshios() throws FileNotFoundException, XMLStreamException {
		readConfigRelationshipFase();
		readConfigRelationshipIteracao();
		readConfigRelationshipAtividade();
		readConfigRelationshipMarco();
		readConfigRelationshipTarefa();
		
	}


	public void readConfigRelationshipFase() throws FileNotFoundException, XMLStreamException {
		// First create a new XMLInputFactory
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		// Setup a new eventReader
		InputStream in = new FileInputStream(nameFile);
		eventReader = inputFactory.createXMLEventReader(in);
		while (eventReader.hasNext()) {
			event = eventReader.nextEvent();
			if(event.isStartElement()){
				if(event.asStartElement().getName().getLocalPart() == (COMPONENT)){
					while(eventReader.hasNext()){
						event = eventReader.nextEvent();
						if(event.isStartElement()){
							if(event.asStartElement().getName().getLocalPart().equals(DIAGRAMCOMPONENTS)){
								while(eventReader.hasNext()){
									event = eventReader.nextEvent();
									if(event.isStartElement()){
										if(event.asStartElement().getName().getLocalPart().equals(ID)){
											event = eventReader.nextEvent();
											if(event.isCharacters()){
												diagramComponentsIds.add(Integer.parseInt(event.asCharacters().getData()));
												continue;
											}
										}
									}
									/** aqui pode pegar informacoes quanto a posicoes dos itens **/
									if(event.isEndElement()){
										if(event.asEndElement().getName().getLocalPart().equals(DIAGRAMCOMPONENTS)){
											event = eventReader.nextEvent();
											break;
										}
									}
								}
							}
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(TYPE)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										object = identifyType(event.asCharacters().getData());
										continue;
									}
								}
							}
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(ID)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										id = Integer.parseInt(event.asCharacters().getData());
										continue;
									}
								}
							}
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(PARENTID)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										parentid = Integer.parseInt(event.asCharacters().getData());
										continue;
									}
								}
							}

						}
						if(event.isEndElement()){
							if(event.asEndElement().getName().getLocalPart().equals(COMPONENT)){
								break;
							}
						}
					}
				}
			}
			/*PROCESSO*/

			if(object instanceof Processo){
				setDefaultValuesAllFields();
			}
			/*FASE*/
			if (object instanceof Fase){
				Fase fase = null;
				for(Fase f: FaseDAO.findAll()){
					if(f.getProcesso() == null){
						if(f.getIdComponent() == id){
							fase = f;
							break;
						}
					}
				}
				Processo processo = ProcessoDAO.findByIdComponentAndNameArqXml(parentid, nameFile);
				fase.setProcesso(processo);
				FaseController.saveFase(fase, false);
				setDefaultValuesAllFields();
			}
	   }
		
	}
	
	public void readConfigRelationshipIteracao() throws FileNotFoundException, XMLStreamException {
		// First create a new XMLInputFactory
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		// Setup a new eventReader
		InputStream in = new FileInputStream(nameFile);
		eventReader = inputFactory.createXMLEventReader(in);
		while (eventReader.hasNext()) {
			event = eventReader.nextEvent();
			if(event.isStartElement()){
				if(event.asStartElement().getName().getLocalPart() == (COMPONENT)){
					while(eventReader.hasNext()){
						event = eventReader.nextEvent();
						if(event.isStartElement()){
							if(event.asStartElement().getName().getLocalPart().equals(DIAGRAMCOMPONENTS)){
								while(eventReader.hasNext()){
									event = eventReader.nextEvent();
									if(event.isStartElement()){
										if(event.asStartElement().getName().getLocalPart().equals(ID)){
											event = eventReader.nextEvent();
											if(event.isCharacters()){
												diagramComponentsIds.add(Integer.parseInt(event.asCharacters().getData()));
												continue;
											}
										}
									}
									/** aqui pode pegar informacoes quanto a posicoes dos itens **/
									if(event.isEndElement()){
										if(event.asEndElement().getName().getLocalPart().equals(DIAGRAMCOMPONENTS)){
											event = eventReader.nextEvent();
											break;
										}
									}
								}
							}
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(TYPE)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										object = identifyType(event.asCharacters().getData());
										continue;
									}
								}
							}
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(ID)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										id = Integer.parseInt(event.asCharacters().getData());
										continue;
									}
								}
							}
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(PARENTID)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										parentid = Integer.parseInt(event.asCharacters().getData());
										continue;
									}
								}
							}

						}
						if(event.isEndElement()){
							if(event.asEndElement().getName().getLocalPart().equals(COMPONENT)){
								break;
							}
						}
					}
				}
			}
			
			/*ITERACAO*/
			if (object instanceof Iteracao){
				Iteracao iteracao = null;
				
				for(Iteracao i: IteracaoDAO.findAll()){
					if(i.getFase() == null){
						if(i.getIdComponent() == id){
							iteracao = i;
							break;
						}
					}
				}
				for(Fase f:FaseDAO.findAll()){
					Processo p = f.getProcesso();
					if(p.getArqXML().equals(nameFile) && f.getIdComponent() == parentid){
						iteracao.setFase(f);
					}
				}
				IteracaoController.saveIteracao(iteracao, false);
				setDefaultValuesAllFields();
			}
	   }
		
	}
	
	public void readConfigRelationshipAtividade() throws FileNotFoundException, XMLStreamException {
		// First create a new XMLInputFactory
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		// Setup a new eventReader
		InputStream in = new FileInputStream(nameFile);
		eventReader = inputFactory.createXMLEventReader(in);
		List<Atividade> atividades = new ArrayList<Atividade>();
		List<Integer> parentIds = new ArrayList<Integer>();
		while (eventReader.hasNext()) {
			event = eventReader.nextEvent();
			if(event.isStartElement()){
				if(event.asStartElement().getName().getLocalPart() == (COMPONENT)){
					while(eventReader.hasNext()){
						event = eventReader.nextEvent();
						if(event.isStartElement()){
							if(event.asStartElement().getName().getLocalPart().equals(DIAGRAMCOMPONENTS)){
								while(eventReader.hasNext()){
									event = eventReader.nextEvent();
									if(event.isStartElement()){
										if(event.asStartElement().getName().getLocalPart().equals(ID)){
											event = eventReader.nextEvent();
											if(event.isCharacters()){
												diagramComponentsIds.add(Integer.parseInt(event.asCharacters().getData()));
												continue;
											}
										}
									}
									/** aqui pode pegar informacoes quanto a posicoes dos itens **/
									if(event.isEndElement()){
										if(event.asEndElement().getName().getLocalPart().equals(DIAGRAMCOMPONENTS)){
											event = eventReader.nextEvent();
											break;
										}
									}
								}
							}
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(TYPE)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										object = identifyType(event.asCharacters().getData());
										continue;
									}
								}
							}
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(ID)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										id = Integer.parseInt(event.asCharacters().getData());
										continue;
									}
								}
							}
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(PARENTID)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										parentid = Integer.parseInt(event.asCharacters().getData());
										continue;
									}
								}
							}

						}
						if(event.isEndElement()){
							if(event.asEndElement().getName().getLocalPart().equals(COMPONENT)){
								break;
							}
						}
					}
				}
			}
			
			/*ATIVIDADE*/
			if (object instanceof Atividade){
				for(Atividade a: AtividadeDAO.findAll()){
					if(a.getAtividade() == null && a.getIteracao() == null){
						if(a.getIdComponent() == id){
							atividades.add(a);
							parentIds.add(parentid);
							break;
						}
					}
				}
				/* set iteracao */
				for (Iteracao i: IteracaoDAO.findAll()) {
					Processo p = i.getFase().getProcesso();
					if(p != null){
						if(p.getArqXML().equals(nameFile) && i.getIdComponent() == parentid){
							atividades.get(atividades.size()-1).setIteracao(i);
						}
					}
				}
				
				/* set fase */
				for (Fase f: FaseDAO.findAll()) {
					Processo p = f.getProcesso();
					if(p != null){
						if(p.getArqXML().equals(nameFile) && f.getIdComponent() == parentid){
							atividades.get(atividades.size()-1).setFase(f);
						}
					}
				}
				/* set atividade */
				for (Atividade a: AtividadeDAO.findAll()) {
					Processo p = processoByAtividade(a);
					if(p!=null){
						if(p.getArqXML().equals(nameFile) && a.getIdComponent() == parentid){
							atividades.get(atividades.size()-1).setAtividade(a);
						}
					}
				}
				setDefaultValuesAllFields();
			}
	   }
		int cont = 0;
		for(Atividade at: atividades){
			int parentIdInterno = parentIds.get(cont);
			if(at.getAtividade() == null && at.getIteracao() == null){
				for (Iteracao i: IteracaoDAO.findAll()) {
					Processo p = i.getFase().getProcesso();
					if(p != null){
						if(p.getArqXML().equals(nameFile) && i.getIdComponent() == parentIdInterno){
							at.setIteracao(i);
							break;
						}
					}
				}
				/* set atividade */
				for (Atividade a: AtividadeDAO.findAll()) {
					Processo p = processoByAtividade(a);
					if(p!=null){
						if(p.getArqXML().equals(nameFile) && a.getIdComponent() == parentIdInterno){
							at.setAtividade(a);
							break;
						}
					}
				}
			}
		}
		
		for(Atividade at: atividades){
			AtividadeController.saveatividade(at, false);
		}
		
	}
	
	public void readConfigRelationshipMarco() throws FileNotFoundException, XMLStreamException {
		// First create a new XMLInputFactory
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		// Setup a new eventReader
		InputStream in = new FileInputStream(nameFile);
		eventReader = inputFactory.createXMLEventReader(in);
		while (eventReader.hasNext()) {
			event = eventReader.nextEvent();
			if(event.isStartElement()){
				if(event.asStartElement().getName().getLocalPart() == (COMPONENT)){
					while(eventReader.hasNext()){
						event = eventReader.nextEvent();
						if(event.isStartElement()){
							if(event.asStartElement().getName().getLocalPart().equals(DIAGRAMCOMPONENTS)){
								while(eventReader.hasNext()){
									event = eventReader.nextEvent();
									if(event.isStartElement()){
										if(event.asStartElement().getName().getLocalPart().equals(ID)){
											event = eventReader.nextEvent();
											if(event.isCharacters()){
												diagramComponentsIds.add(Integer.parseInt(event.asCharacters().getData()));
												continue;
											}
										}
									}
									/** aqui pode pegar informacoes quanto a posicoes dos itens **/
									if(event.isEndElement()){
										if(event.asEndElement().getName().getLocalPart().equals(DIAGRAMCOMPONENTS)){
											event = eventReader.nextEvent();
											break;
										}
									}
								}
							}
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(TYPE)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										object = identifyType(event.asCharacters().getData());
										continue;
									}
								}
							}
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(ID)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										id = Integer.parseInt(event.asCharacters().getData());
										continue;
									}
								}
							}
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(PARENTID)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										parentid = Integer.parseInt(event.asCharacters().getData());
										continue;
									}
								}
							}

						}
						if(event.isEndElement()){
							if(event.asEndElement().getName().getLocalPart().equals(COMPONENT)){
								break;
							}
						}
					}
				}
			}
			
			/*MARCO*/
			if (object instanceof Marco){
				Marco marco = null;
				for(Marco m: MarcoDAO.findAll()){
					if(m.getFase() == null){
						if(m.getIdComponent() == id){
							marco = m;
							break;
						}
					}
				}
				for(Fase f: FaseDAO.findAll()){
					Processo p = f.getProcesso();
					if(p.getArqXML().equals(nameFile) && f.getIdComponent() == parentid){
						marco.setFase(f);
					}
				}
				MarcoController.saveMarco(marco, false);
				setDefaultValuesAllFields();
			}
	   }
		
	}
	
	public void readConfigRelationshipTarefa() throws FileNotFoundException, XMLStreamException {
		// First create a new XMLInputFactory
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		// Setup a new eventReader
		InputStream in = new FileInputStream(nameFile);
		eventReader = inputFactory.createXMLEventReader(in);
		while (eventReader.hasNext()) {
			event = eventReader.nextEvent();
			if(event.isStartElement()){
				if(event.asStartElement().getName().getLocalPart() == (COMPONENT)){
					while(eventReader.hasNext()){
						event = eventReader.nextEvent();
						if(event.isStartElement()){
							if(event.asStartElement().getName().getLocalPart().equals(DIAGRAMCOMPONENTS)){
								diagramComponentsIds = new ArrayList<Integer>();
								while(eventReader.hasNext()){
									event = eventReader.nextEvent();
									if(event.isStartElement()){
										if(event.asStartElement().getName().getLocalPart().equals(ID)){
											event = eventReader.nextEvent();
											if(event.isCharacters()){
												diagramComponentsIds.add(Integer.parseInt(event.asCharacters().getData()));
												continue;
											}
										}
									}
									/** aqui pode pegar informacoes quanto a posicoes dos itens **/
									if(event.isEndElement()){
										if(event.asEndElement().getName().getLocalPart().equals(DIAGRAMCOMPONENTS)){
											event = eventReader.nextEvent();
											break;
										}
									}
								}
							} // end if(event.asStartElement().getName().getLocalPart().equals(DIAGRAMCOMPONENTS))
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(TYPE)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										object = identifyType(event.asCharacters().getData());
										continue;
									}
								}
							}
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(ID)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										id = Integer.parseInt(event.asCharacters().getData());
										continue;
									}
								}
							}
							if(event.isStartElement()){
								if(event.asStartElement().getName().getLocalPart().equals(PARENTID)){
									event = eventReader.nextEvent();
									if(event.isCharacters()){
										parentid = Integer.parseInt(event.asCharacters().getData());
										continue;
									}
								}
							}

						}
						if(event.isEndElement()){
							if(event.asEndElement().getName().getLocalPart().equals(COMPONENT)){
								break;
							}
						}
					}
				}
			}
			

			/*TAREFA*/
			Tarefa tarefa = null;
			if (object instanceof Tarefa){
				for(Tarefa t: TarefaDAO.findAll()){
					if(t.getArqXML().equals(nameFile)){
						if(t.getIdComponent() == id){
							tarefa = t;
							break;
							
							
						}
					}
				}
				/* set iteracao */
				
				for (Iteracao i: IteracaoDAO.findAll()) {
					Processo p = i.getFase().getProcesso();
					if(p.getArqXML().equals(nameFile) && i.getIdComponent() == parentid){
						tarefa.setIteracao(i);
					}
				}
				/* set fase */
				for (Fase f: FaseDAO.findAll()) {
					Processo p = f.getProcesso();
					if(p.getArqXML().equals(nameFile) && f.getIdComponent() == parentid){
						tarefa.setFase(f);
					}
				}
				/* set atividade */
				for (Atividade a: AtividadeDAO.findAll()) {
					Processo p = processoByAtividade(a);
					if(p!=null)
						if(p.getArqXML() == nameFile && a.getIdComponent() == parentid){
							tarefa.setAtividade(a);
						}
				}
				/* set papeis */
				for(Papel papel: PapelDAO.findAll()){
					if(papel.getArqXML().equals(nameFile)){
						for(Integer idComp : diagramComponentsIds){
							if(papel.getIdComponent() == idComp){
								if(!tarefa.getPapeis().contains(papel)){
									List<Integer> listaIdCompTarefa = new ArrayList<Integer>();
									for(Papel p: tarefa.getPapeis()){
										listaIdCompTarefa.add(p.getIdComponent());
									}
									if(!listaIdCompTarefa.contains(papel.getIdComponent())){
										if(papel.getTarefa() == null){
											tarefa.getPapeis().add(papel);
											papel.setTarefa(tarefa);
											PapelController.savePapel(papel, false);
										} else {
												Papel papelNovo = new Papel();
												papelNovo.setArqXML(papel.getArqXML());
												papelNovo.setDescricao(papel.getDescricao());
												papelNovo.setIdComponent(papel.getIdComponent());
												papelNovo.setInformacoesAdicionais(papel.getInformacoesAdicionais());
												papelNovo.setNome(papel.getNome());
												papelNovo.setObjetivos(papel.getObjetivos());
												papelNovo.setTarefa(tarefa);
												PapelController.savePapel(papelNovo, false);
												tarefa.getPapeis().add(papelNovo);
											}
										}
									}
							}
						}
					}
				}
				
				/* set procedimentos */
				for(Procedimento procedimento: ProcedimentoDAO.findAll()){
					if(procedimento.getArqXML().equals(nameFile)){
						for(Integer idComp : diagramComponentsIds){
							if(procedimento.getIdComponent() == idComp){
								if(!tarefa.getProcedimentos().contains(procedimento))
									tarefa.getProcedimentos().add(procedimento);
							}
						}
					}
				}
				
				/* set softwares */
				for(Software s: SoftwareDAO.findAll()){
					if(s.getArqXML().equals(nameFile)){
						for(Integer idComp : diagramComponentsIds){
							if(s.getIdComponent() == idComp){
								if(!tarefa.getSoftwares().contains(s))
									tarefa.getSoftwares().add(s);
							}
						}
					}
				}
				
				/* set softwares */
				for(ProdutoTrabalho pt: ProdutoTrabalhoDAO.findAll()){
					if(pt.getArqXML() == (nameFile)){
						for(Integer idComp : diagramComponentsIds){
							if(pt.getIdComponent() == idComp){
								if(tarefa.getProdutoTrabalho().contains(pt))
									tarefa.getProdutoTrabalho().add(pt);
							}
						}
					}
				}
				
				setDefaultValuesAllFields();
				TarefaController.saveTarefa(tarefa, false);
			}
	   }
		
	}
	
	private Processo processoByAtividade(Atividade a) {
		if(a.getAtividade() != null){
			return processoByAtividade(a.getAtividade());
		} else if (a.getIteracao() != null) {
			return a.getIteracao().getFase().getProcesso();
		} else if (a.getFase() != null){
			return a.getFase().getProcesso();
		}
		return null;
	}

	private Object identifyType(String type) {
		if(type.equals("PROCESS")){
			return new Processo();
		}
		
		if(type.equals("PHASE")){
			return new Fase();
		}
		
		if(type.equals("ITERATION")){
			return new Iteracao();
		}
		
		if(type.equals("ACTIVITY")){
			return new Atividade();
		}
		
		if(type.equals("TASK_USE")){
			return new Tarefa();
		}
		
		if(type.equals("ROLE_USE")){
			return new Papel();
		}
		
		if(type.equals("MILESTONE")){
			return new Marco();
		}
		
		if(type.equals("WORK_PRODUCT_USE")){
			return new ProdutoTrabalho();
		}
		
		if(type.equals("TOOL_USE")){
			return new Software();
		}
		
		if(type.equals("GUIDANCE")){
			return new Procedimento();
		}

		return null;
	}
	
	private void setDefaultValuesAllFields(){
		object = null;
		name = "";
		description = "";
		additionalinformation = "";
		datails = "";
		id = 0;
		objectives = "";
		diagramComponentsIds = new ArrayList<Integer>();
	}
}
