package br.ufpa.spider.pe.controller;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.GerenteProcesso;
import br.ufpa.spider.pe.model.dao.GerenteProcessoDAO;

public class GerenteProcessoController {
	public static void saveGerenteProcesso(GerenteProcesso gerenteProcesso) {

		try {
			if (gerenteProcesso.getId() == 0) {
				GerenteProcessoDAO.createGerenteProcesso(gerenteProcesso);
					JOptionPane.showMessageDialog(null, "Gerente de Processo cadastrado.");
			} else {
				GerenteProcessoDAO.updateGerenteProcesso(gerenteProcesso);
					JOptionPane.showMessageDialog(null, "Gerente de Processo atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
