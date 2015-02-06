package br.ufpa.spider.pe.controller;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.PlanoMedicao;
import br.ufpa.spider.pe.model.PlanoMedicao;
import br.ufpa.spider.pe.model.dao.PlanoMedicaoDAO;
import br.ufpa.spider.pe.model.dao.PlanoMedicaoDAO;

public class PlanoMedicaoController {
	public static void saveplanoMedicao(PlanoMedicao planoMedicao) {

		try {
			if (planoMedicao.getId() == 0) {
				PlanoMedicaoDAO.createPlanoMedicao(planoMedicao);
				JOptionPane.showMessageDialog(null, "Plano de Medicao cadastrado.");
			} else {
				PlanoMedicaoDAO.createPlanoMedicao(planoMedicao);
				JOptionPane.showMessageDialog(null, "Plano de Medicao atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void saveplanoMedicao(PlanoMedicao planoMedicao, boolean msg) {

		try {
			if (planoMedicao.getId() == 0) {
				PlanoMedicaoDAO.createPlanoMedicao(planoMedicao);
				if(msg)
					JOptionPane.showMessageDialog(null, "Plano de Medicao cadastrado.");
			} else {
				PlanoMedicaoDAO.createPlanoMedicao(planoMedicao);
				if(msg)
					JOptionPane.showMessageDialog(null, "Plano de Medicao atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
