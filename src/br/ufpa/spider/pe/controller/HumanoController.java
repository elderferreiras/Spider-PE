package br.ufpa.spider.pe.controller;


import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.dao.HumanoDAO;
import br.ufpa.spider.pe.model.dao.PoliticaOrganizacionalDAO;

public class HumanoController {

	public static void saveHumano(Humano Humano) {

		try {
			if (Humano.getId() == 0) {
				HumanoDAO.createHumano(Humano);
				JOptionPane.showMessageDialog(null, "Humano cadastrado.");
			} else {
				HumanoDAO.updateHumano(Humano);
				JOptionPane.showMessageDialog(null, "Humano atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void saveHumano(Humano Humano, boolean msg) {

		try {
			if (Humano.getId() == 0) {
				HumanoDAO.createHumano(Humano);
				if(msg){
					JOptionPane.showMessageDialog(null, "Humano cadastrado");
				}
			} else {
				HumanoDAO.updateHumano(Humano);
				if(msg){
					JOptionPane.showMessageDialog(null, "Humano atualizado");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

