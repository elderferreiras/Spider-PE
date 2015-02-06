package br.ufpa.spider.pe.controller;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.CheckListArtefatos;
import br.ufpa.spider.pe.model.CheckListArtefatos;
import br.ufpa.spider.pe.model.dao.CheckListArtefatosDAO;
import br.ufpa.spider.pe.model.dao.CheckListArtefatosDAO;

public class CheckListArtefatosController {

	public static void saveCheckListArtefatos(CheckListArtefatos checkListArtefatos) {
	
			try {
				if (checkListArtefatos.getId() == 0) {
					CheckListArtefatosDAO.createCheckListArtefatos(checkListArtefatos);
					JOptionPane.showMessageDialog(null, "Checklist Artefatos cadastrado.");
				} else {
					CheckListArtefatosDAO.updateCheckListArtefatos(checkListArtefatos);
					JOptionPane.showMessageDialog(null, "Checklist Artefatos atualizado.");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	
	public static void saveCheckListArtefatos(CheckListArtefatos checkListArtefatos, boolean msg) {

		try {
			if (checkListArtefatos.getId() == 0) {
				CheckListArtefatosDAO.createCheckListArtefatos(checkListArtefatos);
				if(msg)
					JOptionPane.showMessageDialog(null, "Checklist Artefatos cadastrado.");
			} else {
				CheckListArtefatosDAO.createCheckListArtefatos(checkListArtefatos);
				if(msg)
					JOptionPane.showMessageDialog(null, "Checklist Artefatos atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
