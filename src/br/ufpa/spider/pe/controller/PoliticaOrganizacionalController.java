package br.ufpa.spider.pe.controller;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.PoliticaOrganizacional;
import br.ufpa.spider.pe.model.PoliticaOrganizacional;
import br.ufpa.spider.pe.model.dao.PoliticaOrganizacionalDAO;
import br.ufpa.spider.pe.model.dao.PoliticaOrganizacionalDAO;

public class PoliticaOrganizacionalController {
	public static void savePoliticaOrganizacional(PoliticaOrganizacional politicaOrganizacional) {

		try {
			if (politicaOrganizacional.getId() == 0) {
				PoliticaOrganizacionalDAO.createPoliticaOrganizacional(politicaOrganizacional);
				JOptionPane.showMessageDialog(null, "Politica Organizacional cadastrada.");
			} else {
				PoliticaOrganizacionalDAO.updatePoliticaOrganizacional(politicaOrganizacional);
				JOptionPane.showMessageDialog(null, "Politica Organizacional atualizada.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void savePoliticaOrganizacional(PoliticaOrganizacional politicaOrganizacional, boolean msg) {

		try {
			if (politicaOrganizacional.getId() == 0) {
				PoliticaOrganizacionalDAO.createPoliticaOrganizacional(politicaOrganizacional);
				if(msg)
					JOptionPane.showMessageDialog(null, "Politica Organizacional cadastrada.");
			} else {
				PoliticaOrganizacionalDAO.updatePoliticaOrganizacional(politicaOrganizacional);
				if(msg)
					JOptionPane.showMessageDialog(null, "Politica Organizacional atualizada.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
