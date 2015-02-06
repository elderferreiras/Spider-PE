package br.ufpa.spider.pe.controller;

import javax.swing.JOptionPane;


import br.ufpa.spider.pe.model.Capacidade;
import br.ufpa.spider.pe.model.dao.CapacidadeDAO;

public class CapacidadeController {
	public static void saveCapacidade(Capacidade capacidade) {

		try {
			if (capacidade.getId() == 0) {
				CapacidadeDAO.createCapacidade(capacidade);
				JOptionPane.showMessageDialog(null, "Capacidade cadastrada.");
			} else {
				CapacidadeDAO.updateCapacidade(capacidade);
				JOptionPane.showMessageDialog(null, "Capacidade atualizada.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void saveCapacidade(Capacidade capacidade, boolean msg) {

		try {
			if (capacidade.getId() == 0) {
				CapacidadeDAO.createCapacidade(capacidade);
				if(msg)
					JOptionPane.showMessageDialog(null, "Capacidade cadastrada.");
			} else {
				CapacidadeDAO.createCapacidade(capacidade);
				if(msg)
					JOptionPane.showMessageDialog(null, "Capacidade atualizada.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
