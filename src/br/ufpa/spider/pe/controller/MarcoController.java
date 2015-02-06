package br.ufpa.spider.pe.controller;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.dao.MarcoDAO;
import br.ufpa.spider.pe.model.dao.MarcoDAO;
import br.ufpa.spider.pe.model.dao.PoliticaOrganizacionalDAO;

public class MarcoController {

	public static void saveMarco(Marco marco) {

		try {
			if (marco.getId() == 0) {
				MarcoDAO.createMarco(marco);
				JOptionPane.showMessageDialog(null, "Marco cadastrado.");
			} else {
				MarcoDAO.updateMarco(marco);
				JOptionPane.showMessageDialog(null, "Marco atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void saveMarco(Marco marco, boolean msg) {

		try {
			if (marco.getId() == 0) {
				MarcoDAO.createMarco(marco);
				if(msg)
					JOptionPane.showMessageDialog(null, "Marco cadastrado.");
			} else {
				MarcoDAO.createMarco(marco);
				if(msg)
					JOptionPane.showMessageDialog(null, "Marco atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
