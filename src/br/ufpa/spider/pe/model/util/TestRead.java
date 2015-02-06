package br.ufpa.spider.pe.model.util;

import java.io.FileNotFoundException;

import javax.swing.JOptionPane;
import javax.xml.stream.XMLStreamException;

import com.lowagie.text.xml.XmlParser;

public class TestRead {
	public static void main(String[] args) {
		XMLParse xml = new XMLParse("Heuristica Caxeiro Viajant.docx");
		try {
			xml.readConfig();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Arquivo não encontrado");
		} catch (XMLStreamException e) {
			JOptionPane.showMessageDialog(null, "Arquivo Inválido");
			e.printStackTrace();
		}
	}
}
