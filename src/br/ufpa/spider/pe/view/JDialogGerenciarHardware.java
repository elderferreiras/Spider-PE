package br.ufpa.spider.pe.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.ButtonGroup;

import br.ufpa.spider.pe.controller.HardwareController;
import br.ufpa.spider.pe.model.Hardware;
import br.ufpa.spider.pe.model.dao.HardwareDAO;
import br.ufpa.spider.pe.model.util.MyTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JDialogGerenciarHardware extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField jTextFieldHardware;
	private JTextField jTextFieldMarca;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	DefaultTableModel tableModel;
	private Hardware hardware;
	private JTextArea jTextFieldEspecifacao;
	private JRadioButton rdbtnConsumvel;
	private JRadioButton rdbtnExclusivo;
	private JRadioButton rdbtnCompartilhado;
	private boolean editar;
	/**
	 * Create the dialog.
	 */
	public JDialogGerenciarHardware() {
		setTitle("Gerenciar Hardware");
		setModal(true);
		setBounds(100, 100, 508, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 61, 0, 24, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		JLabel lblHardware = new JLabel("Hardware");
		GridBagConstraints gbc_lblHardware = new GridBagConstraints();
		gbc_lblHardware.insets = new Insets(0, 0, 5, 5);
		gbc_lblHardware.anchor = GridBagConstraints.WEST;
		gbc_lblHardware.gridx = 0;
		gbc_lblHardware.gridy = 0;
		contentPanel.add(lblHardware, gbc_lblHardware);
		
		jTextFieldHardware = new JTextField();
		GridBagConstraints gbc_jTextFieldHardware = new GridBagConstraints();
		gbc_jTextFieldHardware.insets = new Insets(0, 0, 5, 0);
		gbc_jTextFieldHardware.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldHardware.gridx = 1;
		gbc_jTextFieldHardware.gridy = 0;
		contentPanel.add(jTextFieldHardware, gbc_jTextFieldHardware);
		jTextFieldHardware.setColumns(10);
		
		JLabel lblEspecificao = new JLabel("Especifica\u00E7\u00E3o");
		GridBagConstraints gbc_lblEspecificao = new GridBagConstraints();
		gbc_lblEspecificao.anchor = GridBagConstraints.WEST;
		gbc_lblEspecificao.insets = new Insets(0, 0, 5, 5);
		gbc_lblEspecificao.gridx = 0;
		gbc_lblEspecificao.gridy = 1;
		contentPanel.add(lblEspecificao, gbc_lblEspecificao);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		contentPanel.add(scrollPane, gbc_scrollPane);
		
		jTextFieldEspecifacao = new JTextArea();
		scrollPane.setViewportView(jTextFieldEspecifacao);
		
		JLabel lblMarca = new JLabel("Marca");
		GridBagConstraints gbc_lblMarca = new GridBagConstraints();
		gbc_lblMarca.anchor = GridBagConstraints.WEST;
		gbc_lblMarca.insets = new Insets(0, 0, 5, 5);
		gbc_lblMarca.gridx = 0;
		gbc_lblMarca.gridy = 2;
		contentPanel.add(lblMarca, gbc_lblMarca);
		
		jTextFieldMarca = new JTextField();
		GridBagConstraints gbc_jTextFieldMarca = new GridBagConstraints();
		gbc_jTextFieldMarca.insets = new Insets(0, 0, 5, 0);
		gbc_jTextFieldMarca.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldMarca.gridx = 1;
		gbc_jTextFieldMarca.gridy = 2;
		contentPanel.add(jTextFieldMarca, gbc_jTextFieldMarca);
		jTextFieldMarca.setColumns(10);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		contentPanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		rdbtnConsumvel = new JRadioButton("Consum\u00EDvel");
		buttonGroup.add(rdbtnConsumvel);
		rdbtnConsumvel.setMnemonic('3');
		rdbtnConsumvel.setActionCommand("3"); 		
		GridBagConstraints gbc_rdbtnConsumvel = new GridBagConstraints();
		gbc_rdbtnConsumvel.anchor = GridBagConstraints.WEST;
		gbc_rdbtnConsumvel.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnConsumvel.gridx = 0;
		gbc_rdbtnConsumvel.gridy = 0;
		panel.add(rdbtnConsumvel, gbc_rdbtnConsumvel);
		
		rdbtnExclusivo = new JRadioButton("Exclusivo");
		rdbtnExclusivo.setMnemonic('2');
		rdbtnExclusivo.setActionCommand("2"); 	
		buttonGroup.add(rdbtnExclusivo);
		GridBagConstraints gbc_rdbtnExclusivo = new GridBagConstraints();
		gbc_rdbtnExclusivo.anchor = GridBagConstraints.WEST;
		gbc_rdbtnExclusivo.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnExclusivo.gridx = 1;
		gbc_rdbtnExclusivo.gridy = 0;
		panel.add(rdbtnExclusivo, gbc_rdbtnExclusivo);
		
		rdbtnCompartilhado = new JRadioButton("Compartilhado");
		rdbtnCompartilhado.setMnemonic('1');
		rdbtnCompartilhado.setActionCommand("1"); 	
		buttonGroup.add(rdbtnCompartilhado);
		GridBagConstraints gbc_rdbtnCompartilhado = new GridBagConstraints();
		gbc_rdbtnCompartilhado.anchor = GridBagConstraints.WEST;
		gbc_rdbtnCompartilhado.gridx = 2;
		gbc_rdbtnCompartilhado.gridy = 0;
		panel.add(rdbtnCompartilhado, gbc_rdbtnCompartilhado);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 4;
		contentPanel.add(scrollPane_1, gbc_scrollPane_1);
		
		table = new JTable();
		Vector columns = new Vector();
		Vector data = new Vector();			
        columns.add("Recurso Hardware");	
        columns.add("Marca");
        columns.add("Especifica\u00e7\u00e3o");	
        columns.add("Tipo");
        table.getTableHeader().setReorderingAllowed(false); 
		tableModel = MyTableModel.modelRecursoHardware(columns, data);			
		table.setModel(tableModel);
		scrollPane_1.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 2;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 3;
		contentPanel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonAdicionar();
			}
		});
		GridBagConstraints gbc_btnAdicionar = new GridBagConstraints();
		gbc_btnAdicionar.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdicionar.gridx = 0;
		gbc_btnAdicionar.gridy = 0;
		panel_1.add(btnAdicionar, gbc_btnAdicionar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonEditar();
			}
		});
		GridBagConstraints gbc_btnEditar = new GridBagConstraints();
		gbc_btnEditar.insets = new Insets(0, 0, 5, 0);
		gbc_btnEditar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEditar.gridx = 0;
		gbc_btnEditar.gridy = 1;
		panel_1.add(btnEditar, gbc_btnEditar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonRemover();
			}
		});
		GridBagConstraints gbc_btnRemover = new GridBagConstraints();
		gbc_btnRemover.gridx = 0;
		gbc_btnRemover.gridy = 2;
		panel_1.add(btnRemover, gbc_btnRemover);
		populaTabela();
	}
	protected void jButtonRemover() {
		// TODO Auto-generated method stub
		
	}
	protected void jButtonEditar() {
		hardware = (Hardware) table.getValueAt(table.getSelectedRow(), 0);
		jTextFieldEspecifacao.setText(hardware.getEspecificacoesTecnicas());
		jTextFieldHardware.setText(hardware.getNome());
		jTextFieldMarca.setText(hardware.getMarca());

		if(hardware.getTipo().equals("Compartilhado")){
			rdbtnCompartilhado.setSelected(true);
		} else if(hardware.getTipo().equals("Exclusivo")) {
			rdbtnExclusivo.setSelected(true);
		} else if(hardware.getTipo().equals("Consumível")) {
			rdbtnConsumvel.setSelected(true);
		}
		editar = true;
	}
	protected void jButtonAdicionar() {
		if(!(buttonGroup.getSelection() == null)){
			if(!editar) {
				hardware = new Hardware();
			}	else hardware = (Hardware) table.getValueAt(table.getSelectedRow(), 0);
			
			String tipoHardware=null;
			
			if(Integer.parseInt(buttonGroup.getSelection().getActionCommand()) == 1){
				tipoHardware = "Compartilhado";
			} else if(Integer.parseInt(buttonGroup.getSelection().getActionCommand()) == 2) {
				tipoHardware = "Exclusivo";
			} else if(Integer.parseInt(buttonGroup.getSelection().getActionCommand()) == 3) {
				tipoHardware = "Consumível";
			}
			
			hardware.setNome(jTextFieldHardware.getText());
			hardware.setTipo(tipoHardware);
			hardware.setEstado("NOTUSED");
			hardware.setMarca(jTextFieldMarca.getText());
			hardware.setEspecificacoesTecnicas(jTextFieldEspecifacao.getText());		
			HardwareController.saveHardware(hardware, false);
			populaTabela();
			clear();
		}else JOptionPane.showMessageDialog(null, "Selecione o tipo de Recurso: Consumível, Exclusivo, Compartilhado.");
	}
	private void clear() {
		jTextFieldEspecifacao.setText("");
		jTextFieldHardware.setText("");
		jTextFieldMarca.setText("");
		rdbtnCompartilhado.setSelected(true);
		editar = false;
	}
	private void populaTabela() {
		tableModel.setRowCount(0);
		for (Hardware hardware : HardwareDAO.findAll()) {
			tableModel.addRow( new Object[]{ hardware, hardware.getMarca(), hardware.getEspecificacoesTecnicas(),hardware.getTipo()});
		}
		
	}

}
