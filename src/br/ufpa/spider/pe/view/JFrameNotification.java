package br.ufpa.spider.pe.view;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JList;
import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.JScrollPane;

import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.Papel;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.dao.TarefaDAO;
import br.ufpa.spider.pe.view.pm.logic.ExecutionController;

public class JFrameNotification extends JFrame {
	public static void main(String[] args) {
		JFrameNotification frame = new JFrameNotification();
		frame.setVisible(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
	}
	private JList list;
	private DefaultListModel modelList;
	public JFrameNotification() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		modelList = new DefaultListModel();
		list = new JList(modelList);
		scrollPane.setViewportView(list);
		popularList();
	}
	
	public void popularList(){
		modelList.removeAllElements();
		Humano humano = (Humano) JDialog_Spider_Login.getInstance().getInstanciaLogado();
		for (Papel papel : humano.getPapeisAlocados()) {
			Tarefa tarefa = papel.getTarefa();
			if(tarefa.getStatus()!= null){
				if(tarefa.getStatus().equals(ExecutionController.ATRASADA) &&
						tarefa.getEstado().equals(ExecutionController.EXECUCAO)){
					modelList.addElement("A Tarefa: "+tarefa.getNome()+" está ATRASADA");
				}
			 }
		}
		if(modelList.isEmpty()){
			modelList.addElement("Sem notificações.");
		}
			
	}
	
	private void JFrameNotification() {
		setVisible(true);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
	}

}
