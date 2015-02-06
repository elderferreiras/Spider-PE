package br.ufpa.spider.pe.controller;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Papel;
import br.ufpa.spider.pe.model.Papel;
import br.ufpa.spider.pe.model.dao.PapelDAO;
import br.ufpa.spider.pe.model.dao.PapelDAO;
import br.ufpa.spider.pe.model.dao.PoliticaOrganizacionalDAO;

public class PapelController {

	public static void savePapel(Papel papel) {

		try {
			if (papel.getId() == 0) {
				PapelDAO.createPapel(papel);
				JOptionPane.showMessageDialog(null, "Papel cadastrado.");
			} else {
				PapelDAO.updatePapel(papel);
				JOptionPane.showMessageDialog(null, "Papel atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void savePapel(Papel papel, boolean msg) {

		try {
			if (papel.getId() == 0) {
				PapelDAO.createPapel(papel);
				if (msg)
					JOptionPane.showMessageDialog(null, "Papel cadastrado");
			} else {
				PapelDAO.createPapel(papel);
				if (msg)
					JOptionPane.showMessageDialog(null, "Papel atualizado");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
