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
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import br.ufpa.spider.pe.controller.HumanoController;
import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.dao.HumanoDAO;
import br.ufpa.spider.pe.model.util.MyTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class JDialogGerenciarHumanos extends JDialog {
	private JTextField jTextFieldRecursoHumano;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton jButtonAdicionar;
	private JButton jButtonEditar;
	private JButton jButtonRemover;
	private JRadioButton radioConsumivel;
	private JRadioButton radioExclusivo;
	private JRadioButton radioCompartilhado;
	private DefaultTableModel tableModel;
	private boolean editar;
	private Humano humano;

	/**
	 * Create the dialog.
	 */
	public JDialogGerenciarHumanos() {
		setTitle("Gerenciar Humanos");
		setModal(true);
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EmptyBorder(10, 10, 10, 10));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			getContentPane().add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 27, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblRecursoHumano = new JLabel("Recurso Humano");
				GridBagConstraints gbc_lblRecursoHumano = new GridBagConstraints();
				gbc_lblRecursoHumano.anchor = GridBagConstraints.EAST;
				gbc_lblRecursoHumano.insets = new Insets(0, 0, 5, 5);
				gbc_lblRecursoHumano.gridx = 0;
				gbc_lblRecursoHumano.gridy = 0;
				panel.add(lblRecursoHumano, gbc_lblRecursoHumano);
			}
			{
				jTextFieldRecursoHumano = new JTextField();
				GridBagConstraints gbc_jTextFieldRecursoHumano = new GridBagConstraints();
				gbc_jTextFieldRecursoHumano.fill = GridBagConstraints.HORIZONTAL;
				gbc_jTextFieldRecursoHumano.insets = new Insets(0, 0, 5, 0);
				gbc_jTextFieldRecursoHumano.gridx = 1;
				gbc_jTextFieldRecursoHumano.gridy = 0;
				panel.add(jTextFieldRecursoHumano, gbc_jTextFieldRecursoHumano);
				jTextFieldRecursoHumano.setColumns(10);
			}
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.gridwidth = 2;
				gbc_panel_1.insets = new Insets(0, 0, 5, 5);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 1;
				panel.add(panel_1, gbc_panel_1);
				GridBagLayout gbl_panel_1 = new GridBagLayout();
				gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
				gbl_panel_1.rowHeights = new int[]{0, 0};
				gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
				gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
				panel_1.setLayout(gbl_panel_1);
				{
					radioCompartilhado = new JRadioButton("Compartilhado");
					radioCompartilhado.setMnemonic('1');
					radioCompartilhado.setActionCommand("1"); 	
					buttonGroup.add(radioCompartilhado);
					GridBagConstraints gbc_radioCompartilhado = new GridBagConstraints();
					gbc_radioCompartilhado.insets = new Insets(0, 0, 0, 5);
					gbc_radioCompartilhado.gridx = 0;
					gbc_radioCompartilhado.gridy = 0;
					panel_1.add(radioCompartilhado, gbc_radioCompartilhado);
					
				}
				{
					radioConsumivel = new JRadioButton("Consum\u00EDvel");
					radioConsumivel.setMnemonic('3');
					radioConsumivel.setActionCommand("3"); 		
					buttonGroup.add(radioConsumivel);
					GridBagConstraints gbc_radioConsumivel = new GridBagConstraints();
					gbc_radioConsumivel.insets = new Insets(0, 0, 0, 5);
					gbc_radioConsumivel.gridx = 1;
					gbc_radioConsumivel.gridy = 0;
					panel_1.add(radioConsumivel, gbc_radioConsumivel);				
				}
				{
					radioExclusivo = new JRadioButton("Exclusivo");
					radioExclusivo.setMnemonic('2');
					radioExclusivo.setActionCommand("2"); 	
					buttonGroup.add(radioExclusivo);
					GridBagConstraints gbc_radioExclusivo = new GridBagConstraints();
					gbc_radioExclusivo.gridx = 2;
					gbc_radioExclusivo.gridy = 0;
					panel_1.add(radioExclusivo, gbc_radioExclusivo);
						
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
				panel.add(scrollPane, gbc_scrollPane);
				{
					table = new JTable();
					Vector columns = new Vector();
					Vector data = new Vector();	
					
			        columns.add("Recurso Humano");	
			        columns.add("Tipo");
			        
			        table.getTableHeader().setReorderingAllowed(false); 
					tableModel = MyTableModel.modelRecursoHumano(columns, data);			
					table.setModel(tableModel);
					scrollPane.setViewportView(table);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.gridheight = 2;
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 2;
				gbc_panel_1.gridy = 1;
				panel.add(panel_1, gbc_panel_1);
				GridBagLayout gbl_panel_1 = new GridBagLayout();
				gbl_panel_1.columnWidths = new int[]{0, 0};
				gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0};
				gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
				gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
				panel_1.setLayout(gbl_panel_1);
				{
					jButtonAdicionar = new JButton("Adicionar");
					jButtonAdicionar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							jButtonAdicionar();
						}
					});
					GridBagConstraints gbc_jButtonAdicionar = new GridBagConstraints();
					gbc_jButtonAdicionar.fill = GridBagConstraints.BOTH;
					gbc_jButtonAdicionar.insets = new Insets(0, 0, 5, 0);
					gbc_jButtonAdicionar.gridx = 0;
					gbc_jButtonAdicionar.gridy = 0;
					panel_1.add(jButtonAdicionar, gbc_jButtonAdicionar);
				}
				{
					jButtonEditar = new JButton("Editar");
					jButtonEditar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							jButtonEditar();
						}
					});
					GridBagConstraints gbc_jButtonEditar = new GridBagConstraints();
					gbc_jButtonEditar.fill = GridBagConstraints.BOTH;
					gbc_jButtonEditar.insets = new Insets(0, 0, 5, 0);
					gbc_jButtonEditar.gridx = 0;
					gbc_jButtonEditar.gridy = 1;
					panel_1.add(jButtonEditar, gbc_jButtonEditar);
				}
				{
					jButtonRemover = new JButton("Remover");
					jButtonRemover.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						}
					});
					GridBagConstraints gbc_jButtonRemover = new GridBagConstraints();
					gbc_jButtonRemover.fill = GridBagConstraints.BOTH;
					gbc_jButtonRemover.gridx = 0;
					gbc_jButtonRemover.gridy = 2;
					panel_1.add(jButtonRemover, gbc_jButtonRemover);
				}
			}
		}
		updateTable();
	}

	protected void jButtonEditar() {
		editar = true;		
		humano = (Humano) tableModel.getValueAt(table.getSelectedRow(), 0);
		jTextFieldRecursoHumano.setText(humano.getNome());
		
		if(humano.getTipo().equals("Compartilhado")){
			radioCompartilhado.setSelected(true);
		} else if(humano.getTipo().equals("Exclusivo")) {
			radioExclusivo.setSelected(true);
		} else if(humano.getTipo().equals("Consumível")) {
			radioConsumivel.setSelected(true);
		}
	}

	protected void jButtonAdicionar() {
		String tipoRecurso = null;
		if(!(buttonGroup.getSelection() == null)){
			if(Integer.parseInt(buttonGroup.getSelection().getActionCommand()) == 1){
				tipoRecurso = "Compartilhado";
			} else if(Integer.parseInt(buttonGroup.getSelection().getActionCommand()) == 2) {
				tipoRecurso = "Exclusivo";
			} else if(Integer.parseInt(buttonGroup.getSelection().getActionCommand()) == 3) {
				tipoRecurso = "Consumível";
			}			
		
			if(editar)
				humano = (Humano) table.getValueAt(table.getSelectedRow(), 0);
			else humano = new Humano();
			
			humano.setNome(jTextFieldRecursoHumano.getText());
			humano.setTipo(tipoRecurso);
			HumanoController.saveHumano(humano, false);
			updateTable();
			clear();
		} else JOptionPane.showMessageDialog(null, "Selecione o tipo de Recurso: Consumível, Exclusivo, Compartilhado.");
		
	}

	private void clear() {
		humano = null;
		jTextFieldRecursoHumano.setText("");
		radioCompartilhado.setSelected(true);
		radioConsumivel.setSelected(false);
		radioExclusivo.setSelected(false);
		editar = false;
	}

	private void updateTable() {
		tableModel.setRowCount(0);
		for (Humano humano : HumanoDAO.findAll()) {
			tableModel.addRow(new Object[]{humano, humano.getTipo()});
		}
		
	}

}
