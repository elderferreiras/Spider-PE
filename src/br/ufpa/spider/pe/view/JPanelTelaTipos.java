package br.ufpa.spider.pe.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import br.ufpa.spider.pe.controller.HardwareController;
import br.ufpa.spider.pe.controller.HumanoController;
import br.ufpa.spider.pe.controller.SoftwareController;
import br.ufpa.spider.pe.model.Campo;
import br.ufpa.spider.pe.model.Hardware;
import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.Software;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.Tipo;
import br.ufpa.spider.pe.model.dao.CampoDAO;
import br.ufpa.spider.pe.model.dao.HardwareDAO;
import br.ufpa.spider.pe.model.dao.SoftwareDAO;
import br.ufpa.spider.pe.model.dao.TipoDAO;
import br.ufpa.spider.pe.model.util.MyTableModel;
import br.ufpa.spider.pe.view.util.Icone;
import br.ufpa.spider.pe.view.util.JDialogConfigTipo;

public class JPanelTelaTipos extends JPanel {
	private JTextField jTextFieldTipo;
	private JLabel jLabelCampoRelacionado;
	private JLabel jLabelTipo;
	private JButton jButtonCadastrar;
	private Tipo tipo;
	private JTable jTable;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private JLabel lblIssoUm;
	private JButton btnExcluir;
	private Hardware hardware;
	private JComboBox jComboBoxCampoRelacionado;
	private JScrollPane scrollPane_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel panel_1;
	private JComboBox jComboBoxMutavel;

	/**
	 * Create the panel.
	 */
	public JPanelTelaTipos() {
		
		
		
		setBorder(new EmptyBorder(20, 20, 20, 20));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 302, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 28, 0, 0,
				0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 3.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new Icone().getExcluir());
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonExcluirActionPerformed(arg0);
			}
		});
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExcluir.gridx = 3;
		gbc_btnExcluir.gridy = 4;
		add(btnExcluir, gbc_btnExcluir);	
		
		jLabelCampoRelacionado = new JLabel("Campo Relacionado");
		GridBagConstraints gbc_jLabelCampoRelacionado = new GridBagConstraints();
		gbc_jLabelCampoRelacionado.anchor = GridBagConstraints.NORTHWEST;
		gbc_jLabelCampoRelacionado.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelCampoRelacionado.gridx = 0;
		gbc_jLabelCampoRelacionado.gridy = 0;
		add(jLabelCampoRelacionado, gbc_jLabelCampoRelacionado);

		jLabelTipo = new JLabel("Tipo");
		GridBagConstraints gbc_jLabelTipo = new GridBagConstraints();
		gbc_jLabelTipo.anchor = GridBagConstraints.NORTHWEST;
		gbc_jLabelTipo.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelTipo.gridx = 1;
		gbc_jLabelTipo.gridy = 0;
		add(jLabelTipo, gbc_jLabelTipo);
		jComboBoxCampoRelacionado = new JComboBox();
		jComboBoxCampoRelacionado.setMinimumSize(new Dimension(28, 25));
		jComboBoxCampoRelacionado.setMaximumSize(new Dimension(28, 28));
		jComboBoxCampoRelacionado.setPreferredSize(new Dimension(28, 15));
		ItemListener listener = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemListenerStateChaged(e);
			}
		};
		
		jComboBoxCampoRelacionado.addItemListener(listener);
		GridBagConstraints gbc_jComboBoxCampoRelacionado = new GridBagConstraints();
		gbc_jComboBoxCampoRelacionado.anchor = GridBagConstraints.NORTH;
		gbc_jComboBoxCampoRelacionado.insets = new Insets(0, 0, 5, 5);
		gbc_jComboBoxCampoRelacionado.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxCampoRelacionado.gridx = 0;
		gbc_jComboBoxCampoRelacionado.gridy = 1;
		add(jComboBoxCampoRelacionado, gbc_jComboBoxCampoRelacionado);
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		

		
				jButtonCadastrar = new JButton("Salvar");
				jButtonCadastrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						jButtonCadastrarActionPerformed(arg0);				
					}
				});
				
			
				GridBagConstraints gbc_jButtonCadastrar = new GridBagConstraints();
				gbc_jButtonCadastrar.fill = GridBagConstraints.HORIZONTAL;
				gbc_jButtonCadastrar.insets = new Insets(0, 0, 5, 0);
				gbc_jButtonCadastrar.gridx = 3;
				gbc_jButtonCadastrar.gridy = 2;
				add(jButtonCadastrar, gbc_jButtonCadastrar);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);
		jTable = new JTable();
		jTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jTableMouseClicked(arg0);
			}
		});
	
		populaComboBox();		
		setaTable();
		populaTabela(((Campo)jComboBoxCampoRelacionado.getSelectedItem()).getTipo());
		
		scrollPane.setViewportView(jTable);
		
		GridBagConstraints gbc_jButtonExcluir = new GridBagConstraints();
		gbc_jButtonExcluir.fill = GridBagConstraints.HORIZONTAL;
		gbc_jButtonExcluir.gridx = 4;
		gbc_jButtonExcluir.gridy = 5;
	
		
		
	}
	
	protected void jTableMouseClicked(MouseEvent arg0) {
		if(arg0.getClickCount() == 2){
			jButtonEditar(null);
		}
	}

	private void gambiarraRSS() {
		panel_1.removeAll();
		if(((Campo) jComboBoxCampoRelacionado.getSelectedItem()).getNome().equals("Software") || ((Campo) jComboBoxCampoRelacionado.getSelectedItem()).getNome().equals("Recurso Humano")){
			jComboBoxMutavel = new JComboBox();
			jComboBoxMutavel.setMinimumSize(new Dimension(28, 25));
			jComboBoxMutavel.setMaximumSize(new Dimension(28, 25));
			jComboBoxMutavel.setPreferredSize(new Dimension(28, 25));
			ItemListener listener = new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					itemListenerStateChagedRecurso(e);
				}
			};
			
			jComboBoxMutavel.addItemListener(listener);
			GridBagConstraints gbc_jComboBoxMutavel = new GridBagConstraints();
			gbc_jComboBoxMutavel.gridwidth = 4;
			gbc_jComboBoxMutavel.insets = new Insets(0, 0, 0, 5);
			gbc_jComboBoxMutavel.fill = GridBagConstraints.HORIZONTAL;
			gbc_jComboBoxMutavel.gridx = 0;
			gbc_jComboBoxMutavel.gridy = 0;
			panel_1.add(jComboBoxMutavel, gbc_jComboBoxMutavel);
			panel_1.repaint();
			panel_1.validate();
			
		} else if(((Campo) jComboBoxCampoRelacionado.getSelectedItem()).getNome().equals("Unidade de Medida (Carga Hor\u00E1ria)")){
			jButtonCadastrar.setText("Configurar");
			jButtonCadastrar.setIcon(new Icone().getConfig());
			btnExcluir.setEnabled(false);
			jLabelTipo.setText("");
			this.repaint();
		} else {
			jLabelTipo.setText("Tipo");
			jButtonCadastrar.setText("Salvar");
			jButtonCadastrar.setIcon(new Icone().getSalvar());
			jTextFieldTipo = new JTextField();
			GridBagConstraints gbc_jTextFieldTipo = new GridBagConstraints();
			gbc_jTextFieldTipo.anchor = GridBagConstraints.NORTH;
			gbc_jTextFieldTipo.fill = GridBagConstraints.HORIZONTAL;
			gbc_jTextFieldTipo.gridwidth = 4;
			gbc_jTextFieldTipo.insets = new Insets(0, 0, 0, 5);
			gbc_jTextFieldTipo.gridx = 0;
			gbc_jTextFieldTipo.gridy = 0;
			panel_1.add(jTextFieldTipo, gbc_jTextFieldTipo);
			
			jTextFieldTipo.setBorder(new LineBorder(new Color(171, 173, 179)));
			jTextFieldTipo.setPreferredSize(new Dimension(32, 25));
			jTextFieldTipo.setMinimumSize(new Dimension(32, 25));
			jTextFieldTipo.setColumns(10);
			jTextFieldTipo.setBorder(new LineBorder(new Color(171, 173, 179)));
			jTextFieldTipo.setPreferredSize(new Dimension(32, 25));
			jTextFieldTipo.setMinimumSize(new Dimension(32, 25));
			panel_1.repaint();
			panel_1.validate();
			btnExcluir.setEnabled(true);
			this.repaint();
		}
		
		
	}

	protected void itemListenerStateChagedRecurso(ItemEvent e) {
				
	}

	protected void itemListenerStateChaged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED) {  
				gambiarraRSS();
				Campo campo = (Campo) jComboBoxCampoRelacionado.getSelectedItem();
							
				setaTable();
				populaTabela(campo.getTipo());
				

				jTable.repaint();
				jTable.validate();
					
			}		
	}

	private void populaComboBox() {		
		List<Campo> campos = CampoDAO.findAll();
		for (Campo campo : campos) {
			jComboBoxCampoRelacionado.addItem(campo);
		}		
	}

	private void setaTable() {		
			Vector columns = new Vector();
			Vector data = new Vector();	
	        columns.add("Tipo");
			tableModel = MyTableModel.modelTipos(columns, data);
			jTable.setModel(tableModel);	
			jTable.getTableHeader().setReorderingAllowed(false);		
	}

	protected void jButtonCadastrarActionPerformed(ActionEvent arg0) {		
		if(isOk()){
			if(jComboBoxCampoRelacionado.getSelectedItem().toString().equals("Unidade de Medida (Carga Hor\u00E1ria)")){
				JDialog dialog = new JDialogConfigTipo();
				dialog.setLocationRelativeTo(this);
				dialog.setVisible(true);
			} else {
				Campo campo = (Campo) jComboBoxCampoRelacionado.getSelectedItem();
				String tipoTipo = "";
				boolean controller = true;
				
					if(tipo==null){
						tipo = new Tipo();
					}
					
					tipo.setCampo(campo);				
					tipo.setNome(jTextFieldTipo.getText());
	
					if(!campo.getTipo().contains(tipo)){
						campo.getTipo().add(tipo);
					}
					
					CampoDAO.updateCampo(campo);
					if(controller)
						//JOptionPane.showMessageDialog(null, "Tipo Cadastrado com sucesso", "Mensagem", JOptionPane.INFORMATION_MESSAGE);				
				
				
				
				
				
				populaTabela(campo.getTipo());
				this.repaint();
				}
			}		
		hardware = null;
		tipo = null;
		
		jTextFieldTipo.setText("");
	}

	protected void jButtonExcluirActionPerformed(ActionEvent arg0) {	
		try{ 	
			if(jComboBoxCampoRelacionado.getSelectedItem().toString().equals("Recurso de Hardware")){
				hardware = (Hardware) jTable.getValueAt(jTable.getSelectedRow(),0);
				
				if(hardware.getTarefas()!=null || hardware.getTarefas().size() == 0){
							HardwareDAO.removeHardware(hardware);
							JOptionPane.showMessageDialog(null, "Recurso de Hardware exclu\u00EDdo.");
				} else{
					for (Tarefa tarefa : hardware.getTarefas()) {
						if(tarefa.getHardwares().contains(hardware))	{
							JOptionPane.showMessageDialog(null, "Este recurso est\u00e1 alocado a uma tarefa. Desaloce o\n Recurso da Tarefa antes de Excluir.");
						}
					}
				}
				populaTabela(null);
			}			
			else{
			Campo campo = CampoDAO.findById(((Campo) jComboBoxCampoRelacionado.getSelectedItem()).getId());		
			Tipo tipo =  TipoDAO.findById(( (Tipo) jTable.getValueAt(jTable.getSelectedRow(),0)).getId());
			campo.getTipo().remove(tipo);
			TipoDAO.removeTipo(tipo);	
			JOptionPane.showMessageDialog(null, "Tipo exclu\u00EDdo", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
			
			populaTabela(campo.getTipo());
			this.repaint();
			}
		} catch(Exception e){
			e.printStackTrace();
			}
	}

	protected void jButtonEditar(ActionEvent e) {	
		if(jTable.getSelectedRow()!=-1){
				tipo = (Tipo) jTable.getValueAt(jTable.getSelectedRow(),0);
				jComboBoxCampoRelacionado.setSelectedItem(tipo.getCampo());
				jTextFieldTipo.setText(tipo.getNome());
		}
	}

	private boolean isOk() {		
		return true;
	}	
	
	public void populaTabela(List<Tipo> tipos) {
		tableModel.setNumRows(0);
		
		
			for (Tipo tipo : tipos) {
				 tableModel.addRow(new Object[]{ tipo });
			}
		
	}
}
