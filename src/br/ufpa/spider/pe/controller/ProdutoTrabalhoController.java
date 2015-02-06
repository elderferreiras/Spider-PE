package br.ufpa.spider.pe.controller;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.ProdutoTrabalho;
import br.ufpa.spider.pe.model.ProdutoTrabalho;
import br.ufpa.spider.pe.model.dao.ProdutoTrabalhoDAO;
import br.ufpa.spider.pe.model.dao.ProdutoTrabalhoDAO;

public class ProdutoTrabalhoController {
	public static void saveProdutoTrabalho(ProdutoTrabalho produtoTrabalho) {

		try {
			if (produtoTrabalho.getId() == 0) {
				ProdutoTrabalhoDAO.createProdutoTrabalho(produtoTrabalho);
				JOptionPane.showMessageDialog(null, "Produto de Trabalho cadastrado.");
			} else {
				ProdutoTrabalhoDAO.updateProdutoTrabalho(produtoTrabalho);
				JOptionPane.showMessageDialog(null, "Produto de Trabalho atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void saveProdutoTrabalho(ProdutoTrabalho produtoTrabalho, boolean msg) {

		try {
			if (produtoTrabalho.getId() == 0) {
				ProdutoTrabalhoDAO.createProdutoTrabalho(produtoTrabalho);
				if(msg)
					JOptionPane.showMessageDialog(null, "Produto de Trabalho cadastrado.");
			} else {
				ProdutoTrabalhoDAO.createProdutoTrabalho(produtoTrabalho);
				if(msg)
					JOptionPane.showMessageDialog(null, "Produto de Trabalho atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
