package br.ufpa.spider.pe.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import br.ufpa.spider.pe.controller.TarefaController;
import br.ufpa.spider.pe.model.Campo;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.Tipo;
import br.ufpa.spider.pe.model.dao.CampoDAO;
import br.ufpa.spider.pe.model.dao.TarefaDAO;
import br.ufpa.spider.pe.model.util.MeuDocument;
import br.ufpa.spider.pe.model.util.MyTableModel;
import br.ufpa.spider.pe.view.util.Icone;

public class JPanelGerenciaEsforco extends JPanel {
	private JTable table;
	private Vector<String> columns;
	private Vector data;
	private DefaultTableModel tableModel;
	private JTextField jTextFieldQtdEsforco;
	private JComboBox jComboBoxTarefa;
	private JComboBox jComboBoxUnidadeMedida;
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("deprecation")
	public JPanelGerenciaEsforco() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{119, 123, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{155, 72, 0, 67, 38, 93, 0, 52, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_2 = new JLabel("Tarefa");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Quantidade de Esfor\u00E7o");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Unidade de Medida");
		lblNewLabel.setBorder(new EmptyBorder(0, 30, 0, 0));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		ItemListener listener = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemListenerStateChaged(e);
			}
		};
		
		jComboBoxTarefa = new JComboBox();
		jComboBoxTarefa.setBorder(new EmptyBorder(0, 0, 0, 30));
		GridBagConstraints gbc_jComboBoxTarefa = new GridBagConstraints();
		gbc_jComboBoxTarefa.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxTarefa.gridwidth = 2;
		gbc_jComboBoxTarefa.insets = new Insets(0, 0, 5, 5);
		gbc_jComboBoxTarefa.gridx = 0;
		gbc_jComboBoxTarefa.gridy = 2;
		jComboBoxTarefa.addItemListener(listener);
		panel.add(jComboBoxTarefa, gbc_jComboBoxTarefa);
		
		jTextFieldQtdEsforco = new JTextField();
		jTextFieldQtdEsforco.setDocument(new MeuDocument());
		jTextFieldQtdEsforco.setBorder(new EmptyBorder(0, 0, 0, 30));
		GridBagConstraints gbc_jTextFieldQtdEsforco = new GridBagConstraints();
		gbc_jTextFieldQtdEsforco.gridwidth = 2;
		gbc_jTextFieldQtdEsforco.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldQtdEsforco.fill = GridBagConstraints.BOTH;
		gbc_jTextFieldQtdEsforco.gridx = 2;
		gbc_jTextFieldQtdEsforco.gridy = 2;
		panel.add(jTextFieldQtdEsforco, gbc_jTextFieldQtdEsforco);
		jTextFieldQtdEsforco.setColumns(10);
		
		jComboBoxUnidadeMedida = new JComboBox();
		jComboBoxUnidadeMedida.addFocusListener(new FocusListener(){  
			@Override 
		    public void focusGained(FocusEvent arg0) {  
		        populaComboBoxMedida();
		    }  
			@Override
		    public void focusLost(FocusEvent arg0){ 
		    }
		      
		});  
		jComboBoxUnidadeMedida.setBorder(new EmptyBorder(0, 30, 0, 0));
		GridBagConstraints gbc_jComboBoxUnidadeMedida = new GridBagConstraints();
		gbc_jComboBoxUnidadeMedida.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxUnidadeMedida.gridwidth = 2;
		gbc_jComboBoxUnidadeMedida.insets = new Insets(0, 0, 5, 5);
		gbc_jComboBoxUnidadeMedida.gridx = 4;
		gbc_jComboBoxUnidadeMedida.gridy = 2;		
		panel.add(jComboBoxUnidadeMedida, gbc_jComboBoxUnidadeMedida);
		
		JButton btnCadastrar = new JButton("Salvar");
		btnCadastrar.setIcon(new Icone().getSalvar());
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JButtonCadastrar(arg0);
			}
		});
		GridBagConstraints gbc_btnCadastrar = new GridBagConstraints();
		gbc_btnCadastrar.anchor = GridBagConstraints.EAST;
		gbc_btnCadastrar.gridx = 7;
		gbc_btnCadastrar.gridy = 3;
		panel.add(btnCadastrar, gbc_btnCadastrar);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{558, 0};
		gbl_panel_1.rowHeights = new int[]{205, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		columns = new Vector();       

        //columns.add("ID");
        columns.add("Tarefa");
        columns.add("Quantidade de Esfor\u00E7o");
        columns.add("Unidade de Medida");
		tableModel = MyTableModel.modelEsforco(columns, data);
		
		JScrollPane jScrollPane = new JScrollPane();
		GridBagConstraints gbc_jScrollPane = new GridBagConstraints();
		gbc_jScrollPane.fill = GridBagConstraints.BOTH;
		gbc_jScrollPane.gridx = 0;
		gbc_jScrollPane.gridy = 0;
		panel_1.add(jScrollPane, gbc_jScrollPane);
		table = new JTable();
		table.setBorder(new LineBorder(Color.LIGHT_GRAY));
		table.setPreferredScrollableViewportSize(new Dimension(700, 16));	
		table.setModel(tableModel);
		jScrollPane.setViewportView(table);					
				
		//table.getColumnModel().getColumn(0).setPreferredWidth(1);
		table.getColumnModel().getColumn(0).setPreferredWidth(600);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		
		DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
		cellRender.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i=1; i<tableModel.getColumnCount(); i++)
			table.getColumnModel().getColumn(i).setCellRenderer(cellRender); 
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jTableMouseCliked(arg0);
			}
		});
		populaTabela();
		populaComboBox();	
		populaComboBoxMedida();

	}
	
	protected void populaComboBoxMedida() {		
		jComboBoxUnidadeMedida.removeAllItems();
		jComboBoxUnidadeMedida.addItem("Selecione...");
		List<Campo> campos = CampoDAO.findAll();
		for (Campo campo : campos) {
			if(campo.getNome().equals("Unidade de Medida (Esfor\u00E7o)")){
				for(Tipo tipo:campo.getTipo())
					jComboBoxUnidadeMedida.addItem(tipo.getNome());
			}
		}		
	}
	
	protected void JButtonCadastrar(ActionEvent arg0) {
		if(!jTextFieldQtdEsforco.getText().isEmpty()){
			if(!jComboBoxUnidadeMedida.getSelectedItem().toString().equals("Selecione...")){
				Tarefa tarefa  = TarefaDAO.findById(((Tarefa) jComboBoxTarefa.getSelectedItem()).getId());
				tarefa.setQtdEsforco(jTextFieldQtdEsforco.getText());
				tarefa.setUndEsforco((String) jComboBoxUnidadeMedida.getSelectedItem());
				TarefaController.saveTarefa(tarefa);
				populaTabela();
				limpa();
			} else JOptionPane.showMessageDialog(null, "Selecione uma Unidade de Medida");
		} else JOptionPane.showMessageDialog(null, "Quantidade de Esfor�o n�o pode ser um campo nulo.");
		
	}

	private void limpa() {
		jTextFieldQtdEsforco.setText("");
		jComboBoxTarefa.setSelectedItem("Selecione...");
		jComboBoxUnidadeMedida.setSelectedItem("Selecione...");		
	}



	private void populaComboBox() {
	
		jComboBoxTarefa.removeAllItems();
		jComboBoxTarefa.addItem("Selecione...");
		List<Tarefa> listTarefa = consultaTarefas();
		if(!(listTarefa.size()==0)){
			for (Tarefa tarefa : listTarefa) {
			jComboBoxTarefa.addItem(tarefa);
			}
		}

		
	}


	private List<Tarefa> consultaTarefas() {
		return TarefaDAO.getTarefasByProcesso(JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso());
	}
	
	private void populaTabela() {		
		List<Tarefa> listTarefa = consultaTarefas();
		if(listTarefa.size() != 0 ){
			tableModel.setNumRows(0);
		for (Tarefa tarefa :listTarefa) {
			tableModel.addRow(new Object[]{ tarefa, tarefa.getQtdEsforco(), tarefa.getUndEsforco()});
			}
		}	
		
	} 
	

	protected void jTableMouseCliked(MouseEvent arg0) {	
		if(arg0.getClickCount()==2){
			if(table.getSelectedRow()>=0){
				jComboBoxTarefa.setSelectedItem((Tarefa) table.getValueAt(table.getSelectedRow(), 0));
			}	
		}		
	}
	
	protected void preencheCampo(Tarefa tarefa){
		if(tarefa!=null){				
			jTextFieldQtdEsforco.setText(tarefa.getQtdEsforco() == null ? "":tarefa.getQtdEsforco().replace(",", ""));
			jComboBoxUnidadeMedida.setSelectedItem((tarefa.getUndEsforco() == null) || tarefa.getUndEsforco() == "" ? "Selecione...":tarefa.getUndEsforco());			
			jTextFieldQtdEsforco.setBorder(new LineBorder(new Color(171, 173, 179)));
			jComboBoxUnidadeMedida.setBorder(new LineBorder(new Color(171, 173, 179)));
			JDialog_Spider_Login.getInstance().Spider_PE_Home.validate();
		}
	}
	protected void itemListenerStateChaged(ItemEvent e) {	
		jTextFieldQtdEsforco.setText("");
		if(e.getStateChange() == ItemEvent.SELECTED) {
			if(!jComboBoxTarefa.getSelectedItem().toString().equals("Selecione...")){
				preencheCampo((Tarefa) jComboBoxTarefa.getSelectedItem());
			}
			else{
				jComboBoxTarefa.setSelectedItem("Selecione...");
			}
		}
	}
		

}