package br.ufpa.spider.pe.view.management;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import br.ufpa.spider.pe.controller.RiscoController;
import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.Risco;
import br.ufpa.spider.pe.model.dao.RiscoDAO;
import br.ufpa.spider.pe.model.util.MyTableModel;
import br.ufpa.spider.pe.view.Spider_PE_Home;

public class JPanelGerenciaRisco extends JPanel {
	private JTable table;
	private DefaultTableModel tableModel;
	private JComboBox jComboBoxRiscoOcorrido;
	private JTextArea jTextAreaAcaoTomada;
	private Risco risco;

	/**
	 * Create the panel.
	 */
	public JPanelGerenciaRisco() {
		setBorder(new EmptyBorder(15, 15, 5, 15));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{171, 161, 0, 78, 0};
		gridBagLayout.rowHeights = new int[]{0, 20, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 3.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblRiscoOcorrido = new JLabel("Risco Ocorrido");
		GridBagConstraints gbc_lblRiscoOcorrido = new GridBagConstraints();
		gbc_lblRiscoOcorrido.anchor = GridBagConstraints.WEST;
		gbc_lblRiscoOcorrido.insets = new Insets(0, 0, 5, 5);
		gbc_lblRiscoOcorrido.gridx = 0;
		gbc_lblRiscoOcorrido.gridy = 0;
		add(lblRiscoOcorrido, gbc_lblRiscoOcorrido);
		
		JLabel lblAoTomada = new JLabel("A\u00E7\u00E3o  Tomada");
		GridBagConstraints gbc_lblAoTomada = new GridBagConstraints();
		gbc_lblAoTomada.anchor = GridBagConstraints.WEST;
		gbc_lblAoTomada.insets = new Insets(0, 0, 5, 5);
		gbc_lblAoTomada.gridx = 1;
		gbc_lblAoTomada.gridy = 0;
		add(lblAoTomada, gbc_lblAoTomada);
		
		jComboBoxRiscoOcorrido = new JComboBox();
		ItemListener listener = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemListenerStateChaged(e);
			}
		};
		jComboBoxRiscoOcorrido.addItemListener(listener);
		GridBagConstraints gbc_jComboBoxRiscoOcorrido = new GridBagConstraints();
		gbc_jComboBoxRiscoOcorrido.anchor = GridBagConstraints.NORTH;
		gbc_jComboBoxRiscoOcorrido.insets = new Insets(0, 0, 5, 5);
		gbc_jComboBoxRiscoOcorrido.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxRiscoOcorrido.gridx = 0;
		gbc_jComboBoxRiscoOcorrido.gridy = 1;
		add(jComboBoxRiscoOcorrido, gbc_jComboBoxRiscoOcorrido);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		jTextAreaAcaoTomada = new JTextArea();
		jTextAreaAcaoTomada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jTextAreaAcaoTomada.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 0));
		jTextAreaAcaoTomada.setLineWrap(true);
		scrollPane.setViewportView(jTextAreaAcaoTomada);
		
		JButton jButtonCadastrar = new JButton("Cadastrar");
		jButtonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				JButtonCadastraRisco(arg0);
			}
		});
		GridBagConstraints gbc_jButtonCadastrar = new GridBagConstraints();
		gbc_jButtonCadastrar.fill = GridBagConstraints.BOTH;
		gbc_jButtonCadastrar.insets = new Insets(0, 0, 5, 5);
		gbc_jButtonCadastrar.gridx = 2;
		gbc_jButtonCadastrar.gridy = 2;
		add(jButtonCadastrar, gbc_jButtonCadastrar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonExcluirActionPerformed(arg0);				
			}
		});
		btnExcluir.setPreferredSize(new Dimension(80, 23));
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.fill = GridBagConstraints.BOTH;
		gbc_btnExcluir.insets = new Insets(0, 0, 5, 0);
		gbc_btnExcluir.gridx = 3;
		gbc_btnExcluir.gridy = 2;
		add(btnExcluir, gbc_btnExcluir);

       
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridwidth = 4;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 3;
		add(scrollPane_1, gbc_scrollPane_1);
		
		table = new JTable();		
		table.getTableHeader().setReorderingAllowed(false); 
		table.setBorder(new LineBorder(Color.LIGHT_GRAY));
		table.setPreferredScrollableViewportSize(new Dimension(700, 16));	
		 Vector<String>  columns = new Vector<String>();
		 Vector<String>  data = new Vector<String>();
		 columns .add("Risco Ocorrido");
	     columns.add("A\u00E7\u00e3o Tomada");
			
		 tableModel = MyTableModel.modelGerenciaRisco(columns, data);
		 table.setModel(tableModel);
		 
		 table.getColumnModel().getColumn(0).setPreferredWidth(300);
		 table.getColumnModel().getColumn(1).setPreferredWidth(500);

		 scrollPane_1.setViewportView(table);
		 
		 populaComboBox();
		 populaTabela();
		 
		 addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent arg0) {
				populaComboBox();
				populaTabela();
			}
			
			@Override
			public void componentResized(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		 table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					jTableMouseCliked(arg0);
				}
			});
	}

	protected void JButtonCadastraRisco(ActionEvent arg0) {
		risco = (Risco) jComboBoxRiscoOcorrido.getSelectedItem();
		if(risco!=null){
			risco.setAcaoTomada(jTextAreaAcaoTomada.getText());
		RiscoController.saveRisco(risco);
		populaTabela();
		}
		
	}

	protected void jButtonExcluirActionPerformed(ActionEvent arg0) {	
		int i = JOptionPane.showConfirmDialog(null ,"Deseja excluir o Risco selecionado?", 
				"Risco",JOptionPane.YES_NO_OPTION); 
				if (i == JOptionPane.YES_OPTION ) { 
					risco = ((Risco) jComboBoxRiscoOcorrido.getSelectedItem());
					risco.getHumano().getRisco().remove(risco);
					RiscoDAO.removeRisco(risco);	
					JOptionPane.showMessageDialog(null, "Risco Exclu\u00eddo");
					populaTabela();	
					populaComboBox();
					jTextAreaAcaoTomada.setText("");					
				} 
		
	}

	public void populaComboBox() {
			
			jComboBoxRiscoOcorrido.removeAllItems();
			jComboBoxRiscoOcorrido.addItem("Selecione...");			
			List<Risco> riscos =  consultaRiscos();
			if (riscos.size() != 0) {
				for (Risco risco : riscos) {
					jComboBoxRiscoOcorrido.addItem(risco);
				}
			}
				
	}

	public void populaTabela() {	
			List<Risco> riscos = consultaRiscos();
			if (!(riscos.size() == 0) || riscos !=null) {
				tableModel.setNumRows(0);
				for (Risco risco : riscos) {
					tableModel.addRow(new Object[] { risco, risco.getAcaoTomada() });
				}
			}		
	} 
	
	private void itemListenerStateChaged(ItemEvent e) {			
		if(e.getStateChange() == ItemEvent.SELECTED) {
			if(!jComboBoxRiscoOcorrido.getSelectedItem().equals("Selecione...")){
					risco = (Risco) jComboBoxRiscoOcorrido.getSelectedItem();
					if(risco!=null){
						jTextAreaAcaoTomada.setText(risco.getAcaoTomada() == null ? "" : risco.getAcaoTomada());
					}
				}
			else{
				jTextAreaAcaoTomada.setText("");
			}
		}
	}
	
	private List<Humano> consultaHumanos() {
		ArrayList<Humano> humanos= new ArrayList<Humano>();		
		for (Humano humano : Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso().getHumano()) {
									humanos.add(humano);
		}				  
		return humanos;
	}
	
	protected void jTableMouseCliked(MouseEvent arg0) {	
		if(arg0.getClickCount()==2){
			jComboBoxRiscoOcorrido.setSelectedItem(table.getValueAt(table.getSelectedRow(), 0));
		}
	}	
	
	private List<Risco> consultaRiscos() {
		ArrayList<Risco> riscos= new ArrayList<Risco>();
		for(Humano humano: consultaHumanos()){
			for(Risco risco: humano.getRiscos()){
				riscos.add(risco);
			}
		}		
		return riscos;
	}
}
