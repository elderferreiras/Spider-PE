package br.ufpa.spider.pe.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JScrollPane;

import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.Log;
import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.Papel;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.dao.HumanoDAO;
import br.ufpa.spider.pe.model.util.MyTableModel;

public class JFrameLog extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private Vector<String> columns;
	private Vector data;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameLog frame = new JFrameLog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrameLog() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		columns = new Vector();
		data = new Vector();
		
		columns.add("Tarefa");
		columns.add("Data e Hora");
		columns.add("Estado");
		columns.add("Status");
		tableModel = MyTableModel.modelLog(columns, data);
		
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		populaTabela((Humano) JDialog_Spider_Login.getInstance().getInstanciaLogado());
	}
	
	private void populaTabela(Humano humano){
		tableModel.setNumRows(0);
		for(Papel papel :humano.getPapeisAlocados()){
			if(papel.getTarefa() != null){
				Tarefa tarefa = papel.getTarefa();
				for(Log log : tarefa.getLog()){
					tableModel.addRow(new Object[]{tarefa, log.getDate(), log.getEstado(), tarefa.getStatus()});
				}
			}
			
		}
		this.repaint();
	}

}
