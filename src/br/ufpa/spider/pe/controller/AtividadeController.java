package br.ufpa.spider.pe.controller;


import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Atividade;
import br.ufpa.spider.pe.model.dao.AtividadeDAO;
import br.ufpa.spider.pe.model.dao.PoliticaOrganizacionalDAO;

public class AtividadeController {

	public static void saveatividade(Atividade atividade) {

		try {
			if (atividade.getId() == 0) {
				AtividadeDAO.createAtividade(atividade);
				JOptionPane.showMessageDialog(null, "Atividade cadastrada.");
			} else {
				AtividadeDAO.createAtividade(atividade);
				JOptionPane.showMessageDialog(null, "Atividade atualizada.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void saveatividade(Atividade atividade, boolean msg) {

		try {
			if (atividade.getId() == 0) {
				AtividadeDAO.createAtividade(atividade);
				if(msg)
					JOptionPane.showMessageDialog(null, "Atividade cadastrada.");
			} else {
				AtividadeDAO.createAtividade(atividade);
				if(msg)
					JOptionPane.showMessageDialog(null, "Atividade atualizada.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
