package br.ufpa.spider.pe.controller;


import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Problema;
import br.ufpa.spider.pe.model.Problema;
import br.ufpa.spider.pe.model.dao.ProblemaDAO;
import br.ufpa.spider.pe.model.dao.ProblemaDAO;
import br.ufpa.spider.pe.model.dao.PoliticaOrganizacionalDAO;

public class ProblemaController {

	public static void saveProblema(Problema Problema) {

		try {
			if (Problema.getId() == 0) {
				ProblemaDAO.createProblema(Problema);
				JOptionPane.showMessageDialog(null, "Problema cadastrado.");
			} else {
				ProblemaDAO.updateProblema(Problema);
				JOptionPane.showMessageDialog(null, "Problema atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void saveProblema(Problema problema, boolean msg) {

		try {
			if (problema.getId() == 0) {
				ProblemaDAO.createProblema(problema);
				if(msg)
					JOptionPane.showMessageDialog(null, "Problema cadastrado.");
			} else {
				ProblemaDAO.createProblema(problema);
				if(msg)
					JOptionPane.showMessageDialog(null, "Problema atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
