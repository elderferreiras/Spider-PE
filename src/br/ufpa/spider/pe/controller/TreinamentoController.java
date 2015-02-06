package br.ufpa.spider.pe.controller;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Treinamento;
import br.ufpa.spider.pe.model.dao.TreinamentoDAO;
import br.ufpa.spider.pe.model.dao.PoliticaOrganizacionalDAO;

public class TreinamentoController {

	public static void saveTreinamento(Treinamento treinamento) {

		try {
			if (treinamento.getId() == 0) {
				TreinamentoDAO.createTreinamento(treinamento);
				JOptionPane.showMessageDialog(null, "Treinamento cadastrado.");
			} else {
				TreinamentoDAO.updateTreinamento(treinamento);
				JOptionPane.showMessageDialog(null, "Treinamento atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void saveTreinamento(Treinamento treinamento, boolean msg) {

		try {
			if (treinamento.getId() == 0) {
				TreinamentoDAO.createTreinamento(treinamento);
				if(msg){
					JOptionPane.showMessageDialog(null, "Treinamento cadastrado.");
				}
			} else {
				TreinamentoDAO.updateTreinamento(treinamento);
				if(msg){
					JOptionPane.showMessageDialog(null, "Treinamento atualizado.");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
