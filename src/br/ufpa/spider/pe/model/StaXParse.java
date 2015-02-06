package br.ufpa.spider.pe.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import br.ufpa.spider.pe.controller.ProcessoController;

public class StaXParse {
	private static final String COMPONENT = "component";
	private static final String TYPE = "type";
	
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String OBJECTIVES = "objectives";
	private static final String DESCRIPTION = "description";
	private static final String ADDITIONALINFORMATION = "additionalInformations";
	private static final String DATAILS = "datails";
	private static final String CAPABILITIES = "capabilities";
	
	private XMLEvent event = null;
	private XMLEventReader eventReader = null;
	private String configFile;
	
	public StaXParse(String fileXml) {
		this.configFile = fileXml;
	}
	@SuppressWarnings({ "unchecked", "null" })
	public void readConfig() {
		String id="", name="", objectives="", description="", additionalinformation="", datails="";
		
		try {
			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = new FileInputStream(configFile);
			eventReader = inputFactory.createXMLEventReader(in);
			Object object = null;
			
			while (eventReader.hasNext()) {
				event = eventReader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement(); 
						//event = eventReader.nextEvent(); //***attetion, pode pular uma linha
						System.out.println(event.toString());
						if (startElement.getName().getLocalPart() == (COMPONENT)) {
							
						}
						if (event.isStartElement()) {
							if (event.asStartElement().getName().getLocalPart().equals(TYPE)) {
								event = eventReader.nextEvent();
								object = identificaType(event.asCharacters().getData());
								continue;
							} // fim do if event.asStartElement().getName().getLocalPart().equals(MODE)
						} // fim do if event.isStartElement()
						if (event.isStartElement()) {
							if (event.asStartElement().getName().getLocalPart().equals(ID)) {
								event = eventReader.nextEvent();
								JOptionPane.showMessageDialog(null, "id"+event.isCharacters());
								id = event.asCharacters().getData();
								continue;
							} // fim do if event.asStartElement().getName().getLocalPart().equals(MODE)
						} // fim do if event.isStartElement()
						
						if (event.isStartElement()) {
							if (event.asStartElement().getName().getLocalPart().equals(NAME)) {
								event = eventReader.nextEvent();
								//name = event.asCharacters().getData();
								continue;
							} // fim do if event.asStartElement().getName().getLocalPart().equals(MODE)
						} // fim do if event.isStartElement()
						if (event.isStartElement()) {
							if (event.asStartElement().getName().getLocalPart().equals(OBJECTIVES)) {
								event = eventReader.nextEvent();
								//objectives = event.asCharacters().getData();
								continue;
							} // fim do if event.asStartElement().getName().getLocalPart().equals(UNIT)
						}
						if (event.isStartElement()) {
							if (event.asStartElement().getName().getLocalPart().equals(DESCRIPTION)) {
								event = eventReader.nextEvent();
								JOptionPane.showMessageDialog(null, event.isCharacters());
								//description = event.asCharacters().getData();
								continue;
							} // fim do if event.asStartElement().getName().getLocalPart().equals(CURRENT)
						}
						if (event.isStartElement()) {
							if (event.asStartElement().getName().getLocalPart().equals(ADDITIONALINFORMATION)) {
								event = eventReader.nextEvent();
									JOptionPane.showMessageDialog(null, event.toString());
									//additionalinformation = event.asCharacters().getData();
								continue;
							} // fim do if event.asStartElement().getName().getLocalPart().equals(INTERACTIVE)
						}
						
						if (event.isEndElement()) {
							EndElement endElement = event.asEndElement();
							if (endElement.getName().getLocalPart() == (COMPONENT)) {
								break;
							} // fim do if endElement.getName().getLocalPart() == (ITEM)
							continue;
						} // fim do if event.isEndElement()
						
						/**********************/
						//recebeType(object); // vai realizar tarefas semelhantes as abaixo
						/***********************/
						/*PROCESSO*/

						if(object instanceof Processo){
							Processo processo = new Processo();
							processo.setIdComponent(Integer.parseInt(id));
							processo.setDescricao(description);
							processo.setInformacoesAdicionais(additionalinformation);
							ProcessoController.saveProcesso(processo);
						}
					/*FASE*/
					if (object instanceof Fase){
						
					}
					/*ITERACAO*/
					if (object instanceof Iteracao){
						recebeType((Iteracao) object);
					}
					/*TAREFA*/
					if (object instanceof Tarefa){
						recebeType((Tarefa) object);
					}
					/*PAPEL*/
					if (object instanceof Papel){
						recebeType((Papel) object);
					}
					/*MARCO*/
					if (object instanceof Marco){
						recebeType((Marco) object);
					}
					/*PRODUTO DE TRABALHO*/
					if (object instanceof ProdutoTrabalho){
						recebeType((ProdutoTrabalho) object);
					}
					/*ATIVIDADE*/
					if (object instanceof Atividade){
						recebeType((Atividade) object);
					}
					/*SOFTWARE*/
					if (object instanceof Software){
						recebeType((Software) object);
					}
					/*PROCEDIMENTO*/
					if (object instanceof Procedimento){
						recebeType((Procedimento) object);
					}
					/*HUMANO*/
					if (object instanceof Humano){
						recebeType((Humano) object);
					}
	
				} // fim do if event.isStartElement()
				//fim da busca no xml
	
				// If we reach the end of an item element we add it to the list
//				if (event.isEndElement()) {
//					EndElement endElement = event.asEndElement();
//					if (endElement.getName().getLocalPart() == (ITEM)) {
//						items.add(item);
//					} // fim do if endElement.getName().getLocalPart() == (ITEM)
//				} // fim do if event.isEndElement()

			} // fim do WHILE eventReader.hasNext()
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} // fim dos caths
	} // fim do metodo readConfig

	private Object identificaType(String type) {
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
		
		if(type.equals("humanResource")){
			return new Humano();
		}

		return null;
	}
	
	private void recebeType(Processo objeto) throws XMLStreamException{
	System.out.println("processo");
		
	}
	
	private void recebeType(Fase objeto){
		System.out.println("fase");
	}
	
	private void recebeType(Iteracao objeto){
		System.out.println("iteracao");
	}
	
	private void recebeType(Atividade objeto){
		System.out.println("atividade");
	}
	
	private void recebeType(Tarefa objeto){
		System.out.println("tarefa");
	}
	
	private void recebeType(Papel objeto){
		System.out.println("papel");
	}
	
	private void recebeType(Marco objeto){
		System.out.println("marco");
	}
	
	private void recebeType(ProdutoTrabalho objeto){
		System.out.println("produto");
	}
	
	private void recebeType(Software objeto){
		System.out.println("software");
	}
	
	private void recebeType(Humano objeto){
		System.out.println("humano");
	}
	
	private void recebeType(Procedimento objeto){
		System.out.println("procedimento");
	}

}
