package br.ufpa.spider.pe.controller;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Tipo;
import br.ufpa.spider.pe.model.Tipo;
import br.ufpa.spider.pe.model.dao.TipoDAO;
import br.ufpa.spider.pe.model.dao.TipoDAO;

public class TipoController {
	public static void savetipo(Tipo tipo) {

		try {
			if (tipo.getId() == 0) {
				TipoDAO.createTipo(tipo);
				JOptionPane.showMessageDialog(null, "Tipo cadastrado.");
			} else {
				TipoDAO.createTipo(tipo);
				JOptionPane.showMessageDialog(null, "Tipo atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void savetipo(Tipo tipo, boolean msg) {

		try {
			if (tipo.getId() == 0) {
				TipoDAO.createTipo(tipo);
				if(msg)
					JOptionPane.showMessageDialog(null, "Tipo cadastrado.");
			} else {
				TipoDAO.createTipo(tipo);
				if(msg)
					JOptionPane.showMessageDialog(null, "Tipo atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
