package br.ufpa.spider.pe.controller;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.dao.FaseDAO;
import br.ufpa.spider.pe.model.dao.FaseDAO;
import br.ufpa.spider.pe.model.dao.PoliticaOrganizacionalDAO;

public class FaseController {

	public static void saveFase(Fase Fase) {

		try {
			if (Fase.getId() == 0) {
				FaseDAO.createFase(Fase);
				JOptionPane.showMessageDialog(null, "Fase cadastrada.");
			} else {
				FaseDAO.updateFase(Fase);
				JOptionPane.showMessageDialog(null, "Fase atualizada.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void saveFase(Fase fase, boolean msg) {

		try {
			if (fase.getId() == 0) {
				FaseDAO.createFase(fase);
				if(msg)
					JOptionPane.showMessageDialog(null, "Fase cadastrada.");
			} else {
				FaseDAO.createFase(fase);
				if(msg)
					JOptionPane.showMessageDialog(null, "Fase atualizada.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
