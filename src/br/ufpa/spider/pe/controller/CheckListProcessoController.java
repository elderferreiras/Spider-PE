package br.ufpa.spider.pe.controller;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.CheckListProcesso;
import br.ufpa.spider.pe.model.CheckListProcesso;
import br.ufpa.spider.pe.model.dao.CheckListProcessoDAO;
import br.ufpa.spider.pe.model.dao.CheckListProcessoDAO;

public class CheckListProcessoController {
	public static void saveCheckListProcesso(CheckListProcesso checkListProcesso) {

		try {
			if (checkListProcesso.getId() == 0) {
				CheckListProcessoDAO.createCheckListProcesso(checkListProcesso);
				JOptionPane.showMessageDialog(null,
						"Checklist do Processo cadastrado.");
			} else {
				CheckListProcessoDAO.updateCheckListProcesso(checkListProcesso);
				JOptionPane.showMessageDialog(null,
						"Checklist do Processo atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void saveCheckListProcesso(CheckListProcesso checkListProcesso, boolean msg) {

		try {
			if (checkListProcesso.getId() == 0) {
				CheckListProcessoDAO.createCheckListProcesso(checkListProcesso);
				if (msg)
					JOptionPane.showMessageDialog(null, "Checklist Processo cadastrado.");
			} else {
				CheckListProcessoDAO.createCheckListProcesso(checkListProcesso);
				if (msg)
					JOptionPane.showMessageDialog(null, "Checklist Processo atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
