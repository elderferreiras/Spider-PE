package br.ufpa.spider.pe.controller;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Iteracao;
import br.ufpa.spider.pe.model.Iteracao;
import br.ufpa.spider.pe.model.dao.IteracaoDAO;
import br.ufpa.spider.pe.model.dao.IteracaoDAO;
import br.ufpa.spider.pe.model.dao.PoliticaOrganizacionalDAO;

public class IteracaoController {

	public static void saveIteracao(Iteracao Iteracao) {

		try {
			if (Iteracao.getId() == 0) {
				IteracaoDAO.createIteracao(Iteracao);
				JOptionPane.showMessageDialog(null, "Iteracao cadastrada.");
			} else {
				IteracaoDAO.updateIteracao(Iteracao);
				JOptionPane.showMessageDialog(null, "Iteracao atualizada.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void saveIteracao(Iteracao iteracao, boolean msg) {

		try {
			if (iteracao.getId() == 0) {
				IteracaoDAO.createIteracao(iteracao);
				if (msg)
					JOptionPane.showMessageDialog(null, "Iteracao cadastrada.");
			} else {
				IteracaoDAO.createIteracao(iteracao);
				if (msg)
					JOptionPane.showMessageDialog(null, "Iteracao atualizada.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
