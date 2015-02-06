package br.ufpa.spider.pe.controller;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.PoliticaOrganizacional;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.dao.ProcessoDAO;
import br.ufpa.spider.pe.model.dao.PoliticaOrganizacionalDAO;
import br.ufpa.spider.pe.model.dao.ProcessoDAO;

public class ProcessoController {
	public static void saveProcesso(Processo processo) {

		try {
			if (processo.getId() == 0) {
				ProcessoDAO.createProcesso(processo);
				JOptionPane.showMessageDialog(null, "Processo cadastrado.");
			} else {
				ProcessoDAO.updateProcesso(processo);
				JOptionPane.showMessageDialog(null, "Processo atualizado.");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public static void saveProcesso(Processo processo, boolean msg) {

		try {
			if (processo.getId() == 0) {
				ProcessoDAO.createProcesso(processo);
				if(msg)
					JOptionPane.showMessageDialog(null, "Processo cadastrado.");
			} else {
				ProcessoDAO.createProcesso(processo);
				if(msg)
					JOptionPane.showMessageDialog(null, "Processo atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
