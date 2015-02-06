package br.ufpa.spider.pe.controller;


import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Medicao;
import br.ufpa.spider.pe.model.dao.MedicaoDAO;
import br.ufpa.spider.pe.model.dao.PoliticaOrganizacionalDAO;

public class MedicaoController {

	public static void savemedicao(Medicao medicao) {

		try {
			if (medicao.getId() == 0) {
				MedicaoDAO.createMedicao(medicao);
				JOptionPane.showMessageDialog(null, "Medicao cadastrada.");
			} else {
				MedicaoDAO.createMedicao(medicao);
				JOptionPane.showMessageDialog(null, "Medicao atualizada.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void savemedicao(Medicao medicao, boolean msg) {

		try {
			if (medicao.getId() == 0) {
				MedicaoDAO.createMedicao(medicao);
				if(msg)
					JOptionPane.showMessageDialog(null, "Medicao cadastrada.");
			} else {
				MedicaoDAO.createMedicao(medicao);
				if(msg)
					JOptionPane.showMessageDialog(null, "Medicao atualizada.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
