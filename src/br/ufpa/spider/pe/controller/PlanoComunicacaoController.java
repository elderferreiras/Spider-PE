package br.ufpa.spider.pe.controller;


import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.PlanoComunicacao;
import br.ufpa.spider.pe.model.PlanoComunicacao;
import br.ufpa.spider.pe.model.dao.PlanoComunicacaoDAO;
import br.ufpa.spider.pe.model.dao.PlanoComunicacaoDAO;
import br.ufpa.spider.pe.model.dao.PoliticaOrganizacionalDAO;

public class PlanoComunicacaoController {

	public static void savePlanoComunicacao(PlanoComunicacao planoComunicacao) {

		try {
			if (planoComunicacao.getId() == 0) {
				PlanoComunicacaoDAO.createPlanoComunicacao(planoComunicacao);
				JOptionPane.showMessageDialog(null, "Plano de Comunicao cadastrado.");
			} else {
				PlanoComunicacaoDAO.createPlanoComunicacao(planoComunicacao);
				JOptionPane.showMessageDialog(null, "Plano de Comunicao atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void savePlanoComunicacao(PlanoComunicacao planoComunicacao, boolean msg) {

		try {
			if (planoComunicacao.getId() == 0) {
				PlanoComunicacaoDAO.createPlanoComunicacao(planoComunicacao);
				if(msg)
					JOptionPane.showMessageDialog(null, "Plano de Comunicacao cadastrado");
			} else {
				PlanoComunicacaoDAO.createPlanoComunicacao(planoComunicacao);
				if(msg)
					JOptionPane.showMessageDialog(null, "Plano de Comunicacao atualizado");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
