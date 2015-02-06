package br.ufpa.spider.pe.controller;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.RevisaoProcesso;
import br.ufpa.spider.pe.model.RevisaoProcesso;
import br.ufpa.spider.pe.model.dao.RevisaoProcessoDAO;
import br.ufpa.spider.pe.model.dao.RevisaoProcessoDAO;

public class RevisaoProcessoController {
	public static void saverevisaoProcesso(RevisaoProcesso revisaoProcesso) {

		try {
			if (revisaoProcesso.getId() == 0) {
				RevisaoProcessoDAO.createRevisaoProcesso(revisaoProcesso);
				JOptionPane.showMessageDialog(null, "Revisao de Processo cadastrada.");
			} else {
				RevisaoProcessoDAO.createRevisaoProcesso(revisaoProcesso);
				JOptionPane.showMessageDialog(null, "Revisao de Processo atualizada.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void saverevisaoProcesso(RevisaoProcesso revisaoProcesso, boolean msg) {

		try {
			if (revisaoProcesso.getId() == 0) {
				RevisaoProcessoDAO.createRevisaoProcesso(revisaoProcesso);
				if(msg)
					JOptionPane.showMessageDialog(null, "Revisao de Processo cadastrada.");
			} else {
				RevisaoProcessoDAO.createRevisaoProcesso(revisaoProcesso);
				if(msg)
					JOptionPane.showMessageDialog(null, "Revisao de Processo atualizada.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
