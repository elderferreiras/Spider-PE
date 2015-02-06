package br.ufpa.spider.pe.controller;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Campo;
import br.ufpa.spider.pe.model.Campo;
import br.ufpa.spider.pe.model.dao.CampoDAO;
import br.ufpa.spider.pe.model.dao.CampoDAO;

public class CampoController {
	public static void saveCampo(Campo campo) {

		try {
			if (campo.getId() == 0) {
				CampoDAO.createCampo(campo);
				//JOptionPane.showMessageDialog(null, "Arquivo cadastrado");
			} else {
				CampoDAO.createCampo(campo);
				//JOptionPane.showMessageDialog(null, "Arquivo atualizado");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void saveCampo(Campo Campo, boolean msg) {

		try {
			if (Campo.getId() == 0) {
				CampoDAO.createCampo(Campo);
				if(msg)
					JOptionPane.showMessageDialog(null, "Campo cadastrado.");
			} else {
				CampoDAO.createCampo(Campo);
				if(msg)
					JOptionPane.showMessageDialog(null, "Campo atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
