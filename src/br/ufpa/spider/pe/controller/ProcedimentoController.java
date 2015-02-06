package br.ufpa.spider.pe.controller;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Procedimento;
import br.ufpa.spider.pe.model.Procedimento;
import br.ufpa.spider.pe.model.dao.ProcedimentoDAO;
import br.ufpa.spider.pe.model.dao.ProcedimentoDAO;

public class ProcedimentoController {
	public static void save(Procedimento procedimento) {

		try {
			if (procedimento.getId() == 0) {
				ProcedimentoDAO.createProcedimento(procedimento);
				JOptionPane.showMessageDialog(null, "Procedimento cadastrado.");
			} else {
				ProcedimentoDAO.updateProcedimento(procedimento);
				JOptionPane.showMessageDialog(null, "Procedimento atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void save(Procedimento procedimento, boolean msg) {

		try {
			if (procedimento.getId() == 0) {
				ProcedimentoDAO.createProcedimento(procedimento);
				if(msg)
					JOptionPane.showMessageDialog(null, "Procedimento cadastrado.");
			} else {
				ProcedimentoDAO.createProcedimento(procedimento);
				if(msg)
					JOptionPane.showMessageDialog(null, "Procedimento atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
