package br.ufpa.spider.pe.controller;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.dao.TarefaDAO;
import br.ufpa.spider.pe.model.dao.TarefaDAO;

public class TarefaController {

	public static void saveTarefa(Tarefa Tarefa) {

		try {
			if (Tarefa.getId() == 0) {
				TarefaDAO.createTarefa(Tarefa);
				JOptionPane.showMessageDialog(null, "Tarefa cadastrada.");
			} else {
				TarefaDAO.updateTarefa(Tarefa);
				JOptionPane.showMessageDialog(null, "Tarefa atualizada.");
			}
		} catch (Exception e) {
		}
	}
	
	public static void saveTarefa(Tarefa tarefa, boolean msg) {

		try {
			if (tarefa.getId() == 0) {
				TarefaDAO.createTarefa(tarefa);
				if(msg)
					JOptionPane.showMessageDialog(null, "Tarefa cadastrada.");
			} else {
				TarefaDAO.createTarefa(tarefa);
				if(msg)
					JOptionPane.showMessageDialog(null, "Tarefa atualizada.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

