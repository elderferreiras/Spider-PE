package br.ufpa.spider.pe.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import br.ufpa.spider.pe.model.util.MyTableModel;
import br.ufpa.spider.pe.view.util.Icone;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class JPanelProdutoTrabalho extends JPanel {
	private JTextField textField;
	private DefaultTableModel tableModel;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public JPanelProdutoTrabalho() {
		setBorder(new EmptyBorder(10, 10, 0, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{72, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(0, 0, 10, 0));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{95, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBorder(new TitledBorder(null, "Definir Crit\u00E9rios", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		toolBar.setFloatable(false);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.insets = new Insets(0, 0, 5, 5);
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		panel_1.add(toolBar, gbc_toolBar);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(JPanelProdutoTrabalho.class.getResource("/br/ufpa/spider/pe/img/logo_spider-qa.png")));
		toolBar.add(button);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setBorder(new TitledBorder(null, "Criar CheckList", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		toolBar_1.setFloatable(false);
		GridBagConstraints gbc_toolBar_1 = new GridBagConstraints();
		gbc_toolBar_1.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar_1.gridx = 1;
		gbc_toolBar_1.gridy = 0;
		panel_1.add(toolBar_1, gbc_toolBar_1);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(JPanelProdutoTrabalho.class.getResource("/br/ufpa/spider/pe/img/spider_cl.png")));
		toolBar_1.add(button_1);
		
		JLabel lblDefinirCritriosPara = new JLabel("Definir Crit\u00E9rios para Avaliar Produtos de Trabalho");
		GridBagConstraints gbc_lblDefinirCritriosPara = new GridBagConstraints();
		gbc_lblDefinirCritriosPara.anchor = GridBagConstraints.NORTH;
		gbc_lblDefinirCritriosPara.insets = new Insets(0, 0, 0, 5);
		gbc_lblDefinirCritriosPara.gridx = 0;
		gbc_lblDefinirCritriosPara.gridy = 1;
		panel_1.add(lblDefinirCritriosPara, gbc_lblDefinirCritriosPara);
		
		JLabel lblNewLabel = new JLabel("Criar CheckList para Avalia\u00E7\u00E3o de Produtos de Trabalho");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblEnviarCheklist = new JLabel("Enviar ChekList");
		GridBagConstraints gbc_lblEnviarCheklist = new GridBagConstraints();
		gbc_lblEnviarCheklist.anchor = GridBagConstraints.WEST;
		gbc_lblEnviarCheklist.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnviarCheklist.gridx = 0;
		gbc_lblEnviarCheklist.gridy = 1;
		panel.add(lblEnviarCheklist, gbc_lblEnviarCheklist);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnProcurar = new JButton("Procurar");
		GridBagConstraints gbc_btnProcurar = new GridBagConstraints();
		gbc_btnProcurar.insets = new Insets(0, 0, 5, 0);
		gbc_btnProcurar.gridx = 2;
		gbc_btnProcurar.gridy = 1;
		panel.add(btnProcurar, gbc_btnProcurar);
		
		JButton btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.EAST;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 1;
		gbc_btnCancelar.gridy = 2;
		panel.add(btnCancelar, gbc_btnCancelar);
		
		JButton btnEnviar = new JButton("Enviar");
		GridBagConstraints gbc_btnEnviar = new GridBagConstraints();
		gbc_btnEnviar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEnviar.insets = new Insets(0, 0, 5, 0);
		gbc_btnEnviar.gridx = 2;
		gbc_btnEnviar.gridy = 2;
		panel.add(btnEnviar, gbc_btnEnviar);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		panel.add(scrollPane, gbc_scrollPane);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jTableMouseClicked(arg0);
			}
		});
		 table.setBorder(new LineBorder(Color.LIGHT_GRAY));
		 table.setPreferredScrollableViewportSize(new Dimension(700, 16));	
		 Vector<String>  columns = new Vector<String>();
		 Vector<String>  data = new Vector<String>();
		 columns .add("ChekList de Avalia\u00E7\u00E3o");
	     columns.add("Baixar");
	     columns.add("Excluir");
			
		 tableModel = MyTableModel.modelProdutoTrabalho(columns, data);
		 table.setModel(tableModel);		 
		 table.getColumnModel().getColumn(0).setPreferredWidth(800);
		 table.getColumnModel().getColumn(1).setPreferredWidth(10);
		 table.getColumnModel().getColumn(2).setPreferredWidth(10);
		 scrollPane.setViewportView(table);
		 
		 
		 scrollPane.setViewportView(table);
		 populaTabela();

	}

	protected void jTableMouseClicked(MouseEvent arg0) {
		if(arg0.getClickCount() == 1){
			int opcao = table.getSelectedColumn();
			if(opcao == 1)
				JOptionPane.showMessageDialog(null, "Baixando...");
			else if(opcao == 2)
				JOptionPane.showMessageDialog(null, "Excluindo...");
		}
		
	}

	private void populaTabela() {
		for(int i=1; i<=50; i++){
		tableModel.addRow(new Object[]{ "teste_"+i+".pdf", new Icone().getBaixar(), new Icone().getExcluir()});		
		}
	}

}
