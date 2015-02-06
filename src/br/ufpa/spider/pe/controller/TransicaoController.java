package br.ufpa.spider.pe.controller;

import br.ufpa.spider.pe.model.Transicao;
import br.ufpa.spider.pe.model.dao.TransicaoDAO;

public class TransicaoController {

	public static void savetransicao(Transicao transicao) {

		try {
			if (transicao.getId() == 0) {
				TransicaoDAO.createTransicao(transicao);
			} else {
				TransicaoDAO.updateTransicao(transicao);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
