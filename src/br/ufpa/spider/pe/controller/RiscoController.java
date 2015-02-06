package br.ufpa.spider.pe.controller;


import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Risco;
import br.ufpa.spider.pe.model.Risco;
import br.ufpa.spider.pe.model.dao.RiscoDAO;
import br.ufpa.spider.pe.model.dao.RiscoDAO;

public class RiscoController {
	public static void saveRisco(Risco risco) {

		try {
			if (risco.getId() == 0) {
				RiscoDAO.createRisco(risco);
				JOptionPane.showMessageDialog(null, "Risco cadastrado.");
			} else {
				RiscoDAO.createRisco(risco);
				JOptionPane.showMessageDialog(null, "Risco atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void saveRisco(Risco risco, boolean msg) {

		try {
			if (risco.getId() == 0) {
				RiscoDAO.createRisco(risco);
				if(msg)
					JOptionPane.showMessageDialog(null, "Risco cadastrado.");
			} else {
				RiscoDAO.createRisco(risco);
				if(msg)
					JOptionPane.showMessageDialog(null, "Risco atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
