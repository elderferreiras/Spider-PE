package br.ufpa.spider.pe.controller;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.RevisaoArtefatos;
import br.ufpa.spider.pe.model.RevisaoArtefatos;
import br.ufpa.spider.pe.model.dao.RevisaoArtefatosDAO;
import br.ufpa.spider.pe.model.dao.RevisaoArtefatosDAO;

public class RevisaoArtefatosController {
	public static void saveRevisaoArtefatos(RevisaoArtefatos revisaoArtefatos) {

		try {
			if (revisaoArtefatos.getId() == 0) {
				RevisaoArtefatosDAO.createRevisaoArtefatos(revisaoArtefatos);
				//JOptionPane.showMessageDialog(null, "Arquivo cadastrado");
			} else {
				RevisaoArtefatosDAO.createRevisaoArtefatos(revisaoArtefatos);
				//JOptionPane.showMessageDialog(null, "Arquivo atualizado");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void saveRevisaoArtefatos(RevisaoArtefatos revisaoArtefatos, boolean msg) {

		try {
			if (revisaoArtefatos.getId() == 0) {
				RevisaoArtefatosDAO.createRevisaoArtefatos(revisaoArtefatos);
				if(msg)
					JOptionPane.showMessageDialog(null, "Revisao de Artefato cadastrada.");
			} else {
				RevisaoArtefatosDAO.createRevisaoArtefatos(revisaoArtefatos);
				if(msg)
					JOptionPane.showMessageDialog(null, "Revisao de Artefato atualizada.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
