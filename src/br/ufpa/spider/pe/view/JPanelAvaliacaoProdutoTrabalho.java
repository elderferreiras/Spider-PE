package br.ufpa.spider.pe.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

public class JPanelAvaliacaoProdutoTrabalho extends JPanel {
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	public JPanelAvaliacaoProdutoTrabalho() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 141, 124, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 37, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblreaDoProcesso = new JLabel("Artefato");
		GridBagConstraints gbc_lblreaDoProcesso = new GridBagConstraints();
		gbc_lblreaDoProcesso.anchor = GridBagConstraints.WEST;
		gbc_lblreaDoProcesso.insets = new Insets(0, 0, 5, 5);
		gbc_lblreaDoProcesso.gridx = 0;
		gbc_lblreaDoProcesso.gridy = 0;
		add(lblreaDoProcesso, gbc_lblreaDoProcesso);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 1;
		add(comboBox, gbc_comboBox);
		
		JLabel lblCriarCheklistPara = new JLabel("Criar Cheklist para Avalia\u00E7\u00E3o do Produto de Trabalho");
		GridBagConstraints gbc_lblCriarCheklistPara = new GridBagConstraints();
		gbc_lblCriarCheklistPara.anchor = GridBagConstraints.WEST;
		gbc_lblCriarCheklistPara.insets = new Insets(0, 0, 5, 5);
		gbc_lblCriarCheklistPara.gridx = 0;
		gbc_lblCriarCheklistPara.gridy = 2;
		add(lblCriarCheklistPara, gbc_lblCriarCheklistPara);
		
		JToolBar toolBar = new JToolBar();
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.insets = new Insets(0, 0, 5, 5);
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 3;
		add(toolBar, gbc_toolBar);
		toolBar.setEnabled(false);
		toolBar.setFloatable(false);
		
		JButton button = new JButton("");
		toolBar.add(button);
		button.setIcon(new ImageIcon(JPanelAvaliacaoProdutoTrabalho.class.getResource("/br/ufpa/spider/pe/img/spider_cl.png")));
		
		JLabel lblNomeDoChecklist = new JLabel("Nome do Checklist criado");
		GridBagConstraints gbc_lblNomeDoChecklist = new GridBagConstraints();
		gbc_lblNomeDoChecklist.anchor = GridBagConstraints.WEST;
		gbc_lblNomeDoChecklist.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomeDoChecklist.gridx = 0;
		gbc_lblNomeDoChecklist.gridy = 4;
		add(lblNomeDoChecklist, gbc_lblNomeDoChecklist);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 4;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JButton btnProcurar = new JButton("Procurar...");
		GridBagConstraints gbc_btnProcurar = new GridBagConstraints();
		gbc_btnProcurar.anchor = GridBagConstraints.WEST;
		gbc_btnProcurar.insets = new Insets(0, 0, 5, 0);
		gbc_btnProcurar.gridx = 2;
		gbc_btnProcurar.gridy = 4;
		add(btnProcurar, gbc_btnProcurar);
		
		JLabel lblEnviarChecklist = new JLabel("Enviar Checklist");
		GridBagConstraints gbc_lblEnviarChecklist = new GridBagConstraints();
		gbc_lblEnviarChecklist.anchor = GridBagConstraints.WEST;
		gbc_lblEnviarChecklist.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnviarChecklist.gridx = 0;
		gbc_lblEnviarChecklist.gridy = 5;
		add(lblEnviarChecklist, gbc_lblEnviarChecklist);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 5;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setMinimumSize(new Dimension(85, 23));
		btnCadastrar.setMaximumSize(new Dimension(85, 23));
		btnCadastrar.setPreferredSize(new Dimension(85, 23));
		GridBagConstraints gbc_btnCadastrar = new GridBagConstraints();
		gbc_btnCadastrar.fill = GridBagConstraints.VERTICAL;
		gbc_btnCadastrar.anchor = GridBagConstraints.WEST;
		gbc_btnCadastrar.insets = new Insets(0, 0, 5, 0);
		gbc_btnCadastrar.gridx = 2;
		gbc_btnCadastrar.gridy = 5;
		add(btnCadastrar, gbc_btnCadastrar);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 6;
		add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);

	}

}
