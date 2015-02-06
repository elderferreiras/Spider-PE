package br.ufpa.spider.pe.controller;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Log;
import br.ufpa.spider.pe.model.dao.LogDAO;
public class LogController {

	public static void saveLog(Log log) {

		try {
			if (log.getId() == 0) {
				LogDAO.createLog(log);
				//JOptionPane.showMessageDialog(null, "Log cadastrado.");
			} else {
				LogDAO.updateLog(log);
				//JOptionPane.showMessageDialog(null, "Log atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
