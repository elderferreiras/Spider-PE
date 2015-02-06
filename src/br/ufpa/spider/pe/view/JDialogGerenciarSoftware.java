package br.ufpa.spider.pe.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import br.ufpa.spider.pe.controller.SoftwareController;
import br.ufpa.spider.pe.model.Software;
import br.ufpa.spider.pe.model.dao.SoftwareDAO;
import br.ufpa.spider.pe.model.util.MyTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class JDialogGerenciarSoftware extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField jTextFieldSoftware;
	private JTable table;
	private DefaultTableModel tableModel;
	private JRadioButton rdbtnConsumvel;
	private JRadioButton rdbtnExclusivo;
	private JRadioButton rdbtnCompartilhado;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Software software;
	private boolean editar;
	/**
	 * Create the dialog.
	 */
	public JDialogGerenciarSoftware() {
		setModal(true);
		setTitle("Gerenciar Software");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 22, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblSoftware = new JLabel("Software");
			GridBagConstraints gbc_lblSoftware = new GridBagConstraints();
			gbc_lblSoftware.insets = new Insets(0, 0, 5, 5);
			gbc_lblSoftware.anchor = GridBagConstraints.EAST;
			gbc_lblSoftware.gridx = 0;
			gbc_lblSoftware.gridy = 0;
			contentPanel.add(lblSoftware, gbc_lblSoftware);
		}
		{
			jTextFieldSoftware = new JTextField();
			GridBagConstraints gbc_jTextFieldSoftware = new GridBagConstraints();
			gbc_jTextFieldSoftware.insets = new Insets(0, 0, 5, 5);
			gbc_jTextFieldSoftware.fill = GridBagConstraints.HORIZONTAL;
			gbc_jTextFieldSoftware.gridx = 1;
			gbc_jTextFieldSoftware.gridy = 0;
			contentPanel.add(jTextFieldSoftware, gbc_jTextFieldSoftware);
			jTextFieldSoftware.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridheight = 2;
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 2;
			gbc_panel.gridy = 1;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JButton jButtonAdicionar = new JButton("Adicionar");
				jButtonAdicionar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						jButtonAdicionar();
					}
				});
				GridBagConstraints gbc_jButtonAdicionar = new GridBagConstraints();
				gbc_jButtonAdicionar.insets = new Insets(0, 0, 5, 0);
				gbc_jButtonAdicionar.gridx = 0;
				gbc_jButtonAdicionar.gridy = 0;
				panel.add(jButtonAdicionar, gbc_jButtonAdicionar);
			}
			{
				JButton jButtonEditar = new JButton("Editar");
				jButtonEditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						jButtonEditar();
					}
				});
				GridBagConstraints gbc_jButtonEditar = new GridBagConstraints();
				gbc_jButtonEditar.fill = GridBagConstraints.HORIZONTAL;
				gbc_jButtonEditar.insets = new Insets(0, 0, 5, 0);
				gbc_jButtonEditar.gridx = 0;
				gbc_jButtonEditar.gridy = 1;
				panel.add(jButtonEditar, gbc_jButtonEditar);
			}
			{
				JButton jButtonRemover = new JButton("Remover");
				jButtonRemover.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						jButtonRemover();
					}
				});
				GridBagConstraints gbc_jButtonRemover = new GridBagConstraints();
				gbc_jButtonRemover.fill = GridBagConstraints.HORIZONTAL;
				gbc_jButtonRemover.gridx = 0;
				gbc_jButtonRemover.gridy = 2;
				panel.add(jButtonRemover, gbc_jButtonRemover);
			}
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.gridwidth = 2;
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 1;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{79, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{23, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				rdbtnConsumvel = new JRadioButton("Consum\u00EDvel");
				buttonGroup.add(rdbtnConsumvel);
				rdbtnConsumvel.setMnemonic('3');
				rdbtnConsumvel.setActionCommand("3"); 		
				GridBagConstraints gbc_rdbtnConsumvel = new GridBagConstraints();
				gbc_rdbtnConsumvel.insets = new Insets(0, 0, 0, 5);
				gbc_rdbtnConsumvel.anchor = GridBagConstraints.NORTHWEST;
				gbc_rdbtnConsumvel.gridx = 0;
				gbc_rdbtnConsumvel.gridy = 0;
				panel.add(rdbtnConsumvel, gbc_rdbtnConsumvel);
			}
			{
				rdbtnExclusivo = new JRadioButton("Exclusivo");
				buttonGroup.add(rdbtnExclusivo);
				rdbtnExclusivo.setMnemonic('2');
				rdbtnExclusivo.setActionCommand("2"); 	
				GridBagConstraints gbc_rdbtnExclusivo = new GridBagConstraints();
				gbc_rdbtnExclusivo.insets = new Insets(0, 0, 0, 5);
				gbc_rdbtnExclusivo.gridx = 1;
				gbc_rdbtnExclusivo.gridy = 0;
				panel.add(rdbtnExclusivo, gbc_rdbtnExclusivo);
			}
			{
				rdbtnCompartilhado = new JRadioButton("Compartilhado");
				buttonGroup.add(rdbtnCompartilhado);
				rdbtnCompartilhado.setMnemonic('1');
				rdbtnCompartilhado.setActionCommand("1"); 	
				GridBagConstraints gbc_rdbtnCompartilhado = new GridBagConstraints();
				gbc_rdbtnCompartilhado.gridx = 2;
				gbc_rdbtnCompartilhado.gridy = 0;
				panel.add(rdbtnCompartilhado, gbc_rdbtnCompartilhado);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridwidth = 2;
			gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 2;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				table = new JTable();
				Vector columns = new Vector();
				Vector data = new Vector();		
				
		        columns.add("Software");	
		        columns.add("Tipo");
		        
		        table.getTableHeader().setReorderingAllowed(false); 
				tableModel = MyTableModel.modelRecursoSoftware(columns, data);			
				table.setModel(tableModel);
				scrollPane.setViewportView(table);
			}
		}
		populaTabela();
	}
	private void populaTabela() {
		tableModel.setRowCount(0);
		for (Software software : SoftwareDAO.findAll()) {
				tableModel.addRow( new Object[]{software, software.getTipo()});
		}
	}
	protected void jButtonRemover() {
		// TODO Auto-generated method stub
		
	}
	protected void jButtonEditar() {
		software = (Software)table.getValueAt(table.getSelectedRow(),0);
		jTextFieldSoftware.setText(software.getNome());

		if(software.getTipo()!=null){
			if(software.getTipo().equals("Compartilhado")){
				rdbtnCompartilhado.setSelected(true);
			} else if(software.getTipo().equals("Exclusivo")) {
				rdbtnExclusivo.setSelected(true);
			} else if(software.getTipo().equals("Consumível")) {
				rdbtnConsumvel.setSelected(true);
			}
		}
		editar = true;
		
	}
	protected void jButtonAdicionar() {
		if(!(buttonGroup.getSelection() == null)){
			String tipoSoftware = null;		
			if(Integer.parseInt(buttonGroup.getSelection().getActionCommand()) == 1){
				tipoSoftware="Compartilhado";
			} else if(Integer.parseInt(buttonGroup.getSelection().getActionCommand()) == 2) {
				tipoSoftware="Exclusivo";
			} else if(Integer.parseInt(buttonGroup.getSelection().getActionCommand()) == 3) {
				tipoSoftware="Consumível";
			}		
	
			if(editar)
				software = (Software)table.getValueAt(table.getSelectedRow(),0);
			else software = new Software();	
			software.setNome(jTextFieldSoftware.getText());
			software.setTipo(tipoSoftware);
			SoftwareController.saveSoftware(software,false);
			clear();
			populaTabela();
			}else JOptionPane.showMessageDialog(null, "Selecione o tipo de Recurso: Consumível, Exclusivo, Compartilhado.");
	}
	private void clear() {
		jTextFieldSoftware.setText("");
		rdbtnCompartilhado.setSelected(true);
		editar = false;
	}

}
