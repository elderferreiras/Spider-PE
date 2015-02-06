package br.ufpa.spider.pe.controller;


import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Software;
import br.ufpa.spider.pe.model.Software;
import br.ufpa.spider.pe.model.dao.SoftwareDAO;
import br.ufpa.spider.pe.model.dao.SoftwareDAO;
import br.ufpa.spider.pe.model.dao.PoliticaOrganizacionalDAO;

public class SoftwareController {

	public static void saveSoftware(Software software) {

		try {
			if (software.getId() == 0) {
				SoftwareDAO.createSoftware(software);
				JOptionPane.showMessageDialog(null, "Software cadastrado.");
			} else {
				SoftwareDAO.createSoftware(software);
				JOptionPane.showMessageDialog(null, "Software atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void saveSoftware(Software software, boolean msg) {

		try {
			if (software.getId() == 0) {
				SoftwareDAO.createSoftware(software);
				if(msg)
					JOptionPane.showMessageDialog(null, "Software cadastrada.");
			} else {
				SoftwareDAO.createSoftware(software);
				if(msg)
					JOptionPane.showMessageDialog(null, "Software atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
