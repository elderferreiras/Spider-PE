package br.ufpa.spider.pe.view.administration;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JTable;

import java.awt.GridBagConstraints;

import javax.swing.JButton;

import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.tmatesoft.sqljet.core.internal.lang.SqlParser.neq_subexpr_return;

import br.ufpa.spider.pe.controller.GerenteProcessoController;
import br.ufpa.spider.pe.model.GerenteProcesso;
import br.ufpa.spider.pe.model.Hardware;
import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.Software;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.dao.GerenteProcessoDAO;
import br.ufpa.spider.pe.model.dao.HumanoDAO;
import br.ufpa.spider.pe.model.dao.ProcessoDAO;
import br.ufpa.spider.pe.model.util.MyTableModel;
import br.ufpa.spider.pe.view.util.Icone;
import br.ufpa.spider.pe.view.util.PasswordSecurity;

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelManagers extends JPanel {
	private JTable table;
	private JTextField jTextFieldLogin;
	private JPasswordField jTextFieldSenha;
	private JPasswordField jTextFieldConfirmaSenha;
	private DefaultTableModel tableModel;
	private JComboBox jComboBoxProcesso;
	GerenteProcesso gerenteProcesso;
	Processo processo;
	boolean editar = false;
	private JComboBox jComboBoxHumano;
	private Humano humano;

	/**
	 * Create the panel.
	 */
	
	public JPanelManagers() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 185, 0};
		gridBagLayout.rowHeights = new int[]{34, 0, 0, 0, 0, 0, 0, 50, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblSelecionarGerentesDe = new JLabel("Cadastrar Gerentes de Processo");
		lblSelecionarGerentesDe.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblSelecionarGerentesDe = new GridBagConstraints();
		gbc_lblSelecionarGerentesDe.anchor = GridBagConstraints.WEST;
		gbc_lblSelecionarGerentesDe.gridwidth = 2;
		gbc_lblSelecionarGerentesDe.insets = new Insets(0, 0, 5, 0);
		gbc_lblSelecionarGerentesDe.gridx = 0;
		gbc_lblSelecionarGerentesDe.gridy = 0;
		add(lblSelecionarGerentesDe, gbc_lblSelecionarGerentesDe);
		
		JLabel lblProcesso = new JLabel("Processo");
		GridBagConstraints gbc_lblProcesso = new GridBagConstraints();
		gbc_lblProcesso.anchor = GridBagConstraints.WEST;
		gbc_lblProcesso.insets = new Insets(0, 0, 5, 5);
		gbc_lblProcesso.gridx = 0;
		gbc_lblProcesso.gridy = 1;
		add(lblProcesso, gbc_lblProcesso);
		
		jComboBoxProcesso = new JComboBox();
		jComboBoxProcesso.setPreferredSize(new Dimension(200, 20));
		GridBagConstraints gbc_jComboBoxProcesso = new GridBagConstraints();
		gbc_jComboBoxProcesso.anchor = GridBagConstraints.WEST;
		gbc_jComboBoxProcesso.insets = new Insets(0, 0, 5, 0);
		gbc_jComboBoxProcesso.gridx = 1;
		gbc_jComboBoxProcesso.gridy = 1;
		add(jComboBoxProcesso, gbc_jComboBoxProcesso);
		
		JLabel lblGerenteDeProcesso = new JLabel("Gerente");
		GridBagConstraints gbc_lblGerenteDeProcesso = new GridBagConstraints();
		gbc_lblGerenteDeProcesso.anchor = GridBagConstraints.WEST;
		gbc_lblGerenteDeProcesso.insets = new Insets(0, 0, 5, 5);
		gbc_lblGerenteDeProcesso.gridx = 0;
		gbc_lblGerenteDeProcesso.gridy = 2;
		add(lblGerenteDeProcesso, gbc_lblGerenteDeProcesso);
		
		jComboBoxHumano = new JComboBox();
		jComboBoxHumano.setPreferredSize(new Dimension(200, 20));
		jComboBoxHumano.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				jComboBoxHumano.removeAllItems();
				jComboBoxHumano.addItem("Selecione...");
				for (Humano humano : HumanoDAO.findAll()) {
					jComboBoxHumano.addItem(humano);
				}
			}
		});
		GridBagConstraints gbc_jComboBoxHumano = new GridBagConstraints();
		gbc_jComboBoxHumano.anchor = GridBagConstraints.WEST;
		gbc_jComboBoxHumano.insets = new Insets(0, 0, 5, 0);
		gbc_jComboBoxHumano.gridx = 1;
		gbc_jComboBoxHumano.gridy = 2;
		add(jComboBoxHumano, gbc_jComboBoxHumano);
		
		JLabel lblLogin = new JLabel("Login (e-mail)");
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.anchor = GridBagConstraints.WEST;
		gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogin.gridx = 0;
		gbc_lblLogin.gridy = 3;
		add(lblLogin, gbc_lblLogin);
		
		jTextFieldLogin = new JTextField();
		GridBagConstraints gbc_jTextFieldLogin = new GridBagConstraints();
		gbc_jTextFieldLogin.insets = new Insets(0, 0, 5, 0);
		gbc_jTextFieldLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldLogin.gridx = 1;
		gbc_jTextFieldLogin.gridy = 3;
		add(jTextFieldLogin, gbc_jTextFieldLogin);
		jTextFieldLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		GridBagConstraints gbc_lblSenha = new GridBagConstraints();
		gbc_lblSenha.anchor = GridBagConstraints.WEST;
		gbc_lblSenha.insets = new Insets(0, 0, 5, 5);
		gbc_lblSenha.gridx = 0;
		gbc_lblSenha.gridy = 4;
		add(lblSenha, gbc_lblSenha);
		
		jTextFieldSenha = new JPasswordField();
		jTextFieldSenha.setPreferredSize(new Dimension(100, 20));
		GridBagConstraints gbc_jTextFieldSenha = new GridBagConstraints();
		gbc_jTextFieldSenha.anchor = GridBagConstraints.WEST;
		gbc_jTextFieldSenha.insets = new Insets(0, 0, 5, 0);
		gbc_jTextFieldSenha.gridx = 1;
		gbc_jTextFieldSenha.gridy = 4;
		add(jTextFieldSenha, gbc_jTextFieldSenha);
		
		JLabel lblRepetirSenha = new JLabel("Repetir Senha");
		GridBagConstraints gbc_lblRepetirSenha = new GridBagConstraints();
		gbc_lblRepetirSenha.anchor = GridBagConstraints.WEST;
		gbc_lblRepetirSenha.insets = new Insets(0, 0, 5, 5);
		gbc_lblRepetirSenha.gridx = 0;
		gbc_lblRepetirSenha.gridy = 5;
		add(lblRepetirSenha, gbc_lblRepetirSenha);
		
		jTextFieldConfirmaSenha = new JPasswordField();
		jTextFieldConfirmaSenha.setPreferredSize(new Dimension(100, 20));
		GridBagConstraints gbc_jTextFieldConfirmaSenha = new GridBagConstraints();
		gbc_jTextFieldConfirmaSenha.anchor = GridBagConstraints.WEST;
		gbc_jTextFieldConfirmaSenha.insets = new Insets(0, 0, 5, 0);
		gbc_jTextFieldConfirmaSenha.gridx = 1;
		gbc_jTextFieldConfirmaSenha.gridy = 5;
		add(jTextFieldConfirmaSenha, gbc_jTextFieldConfirmaSenha);
		
		JButton jButtonCadastrar = new JButton("Cadastrar");
		jButtonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCadastrar();
			}
		});
		GridBagConstraints gbc_jButtonCadastrar = new GridBagConstraints();
		gbc_jButtonCadastrar.anchor = GridBagConstraints.EAST;
		gbc_jButtonCadastrar.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonCadastrar.gridx = 1;
		gbc_jButtonCadastrar.gridy = 6;
		add(jButtonCadastrar, gbc_jButtonCadastrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(23, 23));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 7;
		add(scrollPane, gbc_scrollPane);
		Vector columns;
		Vector data;
		columns = new Vector();    
		data = new Vector();

        columns.add("Processo");
        columns.add("Gerente");
        columns.add("Login");
        columns.add("Excluir");
        
		tableModel = MyTableModel.modelManagers(columns, data);
		table = new JTable();
		table.setBorder(new LineBorder(Color.LIGHT_GRAY));
		table.setPreferredScrollableViewportSize(new Dimension(700, 16));	
		table.setModel(tableModel);				
				
		table.getColumnModel().getColumn(0).setPreferredWidth(250);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(3).setPreferredWidth(10);
		scrollPane.setViewportView(table);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jTableMouseCliked(arg0);
			}
		});
		
		ItemListener listener = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemListenerStateChaged(e);
			}
		};
		jComboBoxHumano.addItemListener(listener);		
		
		populaComboBox();
		populaTabela();

	}

	protected void itemListenerStateChaged(ItemEvent e) {
		if(jComboBoxHumano.getSelectedItem()!=null)
		 if(!jComboBoxHumano.getSelectedItem().equals("Selecione..."))
			if(((Humano) jComboBoxHumano.getSelectedItem()).getGerente()!=null){
				jTextFieldLogin.setText(((Humano) jComboBoxHumano.getSelectedItem()).getEmail());
				jTextFieldSenha.setText(((Humano) jComboBoxHumano.getSelectedItem()).getSenha());
			}
		
	}

	private void populaTabela() {
		tableModel.setNumRows(0);
		for (Processo processo : ProcessoDAO.findAll()) {
			if(processo.getGerenteProcesso() != null)
				tableModel.addRow(new Object[]{ processo, 
						processo.getGerenteProcesso(), processo.getGerenteProcesso().getHumano().getEmail(), new Icone().getExcluir()});
		}
	}

	private void populaComboBox() {
		jComboBoxProcesso.removeAllItems();
		jComboBoxProcesso.addItem("Selecione...");
		for (Processo processo : ProcessoDAO.findAll()) {
			jComboBoxProcesso.addItem(processo);
		}
		
		jComboBoxHumano.removeAllItems();
		jComboBoxHumano.addItem("Selecione...");
		for (Humano humano : HumanoDAO.findAll()) {
			jComboBoxHumano.addItem(humano);
		}
		
	}

	protected void jButtonCadastrar() {	
		processo = ((Processo)jComboBoxProcesso.getSelectedItem());
		humano = ((Humano)jComboBoxHumano.getSelectedItem());
		if(processo.getGerenteProcesso() == null){			
			if(humano.getGerente() == null){
				gerenteProcesso = new GerenteProcesso();					
			} else gerenteProcesso = ((Humano)jComboBoxHumano.getSelectedItem()).getGerente();			
			gerenteProcesso.getProcesso().add(processo);
			GerenteProcessoController.saveGerenteProcesso(gerenteProcesso);		
			
			processo.setGerenteProcesso(gerenteProcesso);
			ProcessoDAO.updateProcesso(processo);
			
			humano = ((Humano)jComboBoxHumano.getSelectedItem());
			humano.setEmail(jTextFieldLogin.getText());
			try {
				humano.setSenha(PasswordSecurity.encrypt(jTextFieldSenha.getText()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			humano.setGerente(gerenteProcesso);			
			HumanoDAO.updateHumano(humano);
			
			gerenteProcesso.setHumano(humano);
			GerenteProcessoDAO.updateGerenteProcesso(gerenteProcesso);
			clear();
			populaTabela();
		} else JOptionPane.showMessageDialog(null, "O Processo selecionado já possui um Gerente.");
	}

	private void clear() {
		editar = false;
		processo = null;
		gerenteProcesso = null;
		humano = null;
		jComboBoxProcesso.setSelectedItem("Selecione...");
		jTextFieldConfirmaSenha.setText("");
		jTextFieldLogin.setText("");
		jComboBoxHumano.setSelectedItem("Selecione...");		
		jTextFieldSenha.setText("");
	}

	protected void jTableMouseCliked(MouseEvent arg0) {
		if(arg0.getClickCount()==2){
			if(table.getSelectedColumn()>=0 && table.getSelectedColumn()<3)
				jButtonEditar();
			else if (table.getSelectedColumn() == 3){
				int i = JOptionPane.showConfirmDialog(null ,"Deseja excluir a Gerente de Processo selecionado?", 
						"Gerente de Processo",JOptionPane.YES_NO_OPTION); 
				if (i == JOptionPane.YES_OPTION ) 
					jButtonExcluir();
			}
		}
	}

	private void jButtonEditar() {
		processo = (Processo) table.getValueAt(table.getSelectedRow(), 0);
		//if(processo.getGerenteProcesso() != null)
		//	tableModel.addRow(new Object[]{ processo, 
		//			processo.getGerenteProcesso(), processo.getGerenteProcesso().getHumano().getEmail(), new Icone().getExcluir()});
		processo = (Processo) table.getValueAt(table.getSelectedRow(), 0);
		gerenteProcesso = processo.getGerenteProcesso();
		humano = gerenteProcesso.getHumano();
		
		jComboBoxHumano.setSelectedItem(humano);
		jComboBoxProcesso.setSelectedItem(processo);
		jTextFieldLogin.setText(humano.getEmail());
		jTextFieldSenha.setText(humano.getSenha());
	}

	private void jButtonExcluir() {
		processo = (Processo) table.getValueAt(table.getSelectedRow(), 0);
		processo.setGerenteProcesso(null);
		ProcessoDAO.updateProcesso(processo);
		populaTabela();
		}

}
