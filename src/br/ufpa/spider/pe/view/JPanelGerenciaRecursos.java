package br.ufpa.spider.pe.view;

import java.awt.Font;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.ufpa.spider.pe.controller.HardwareController;
import br.ufpa.spider.pe.controller.SoftwareController;
import br.ufpa.spider.pe.controller.TarefaController;
import br.ufpa.spider.pe.model.Atividade;
import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Hardware;
import br.ufpa.spider.pe.model.Iteracao;
import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.Risco;
import br.ufpa.spider.pe.model.Software;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.dao.HardwareDAO;
import br.ufpa.spider.pe.model.dao.MarcoDAO;
import br.ufpa.spider.pe.model.dao.ProcessoDAO;
import br.ufpa.spider.pe.model.dao.RiscoDAO;
import br.ufpa.spider.pe.model.dao.SoftwareDAO;
import br.ufpa.spider.pe.model.dao.TarefaDAO;
import br.ufpa.spider.pe.model.util.MyTableModel;
import javax.swing.JTextArea;

public class JPanelGerenciaRecursos extends JPanel {
	private JTable table;
	private DefaultTableModel tableModel;
	private JComboBox jComboBoxTarefa;
	private JComboBox jComboBoxRecursos;
	private Tarefa tarefa = null;
	private Marco marco = null;
	private Hardware hardware;
	private Software software;
	private boolean editar = false;
	private JTextArea jTextAreaMarcaEspecificacoes;

	/**
	 * Create the panel.
	 */
	public JPanelGerenciaRecursos() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{186, 163, 61, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{2.0, 2.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTarefa = new JLabel("Tarefa/Marco");
		lblTarefa.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblTarefa = new GridBagConstraints();
		gbc_lblTarefa.anchor = GridBagConstraints.WEST;
		gbc_lblTarefa.insets = new Insets(0, 0, 5, 5);
		gbc_lblTarefa.gridx = 0;
		gbc_lblTarefa.gridy = 0;
		add(lblTarefa, gbc_lblTarefa);
		
		JLabel lblRecursos = new JLabel("Recursos");
		lblRecursos.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblRecursos = new GridBagConstraints();
		gbc_lblRecursos.anchor = GridBagConstraints.WEST;
		gbc_lblRecursos.insets = new Insets(0, 0, 5, 5);
		gbc_lblRecursos.gridx = 1;
		gbc_lblRecursos.gridy = 0;
		add(lblRecursos, gbc_lblRecursos);
		
		JLabel lblMarcaEEspecificaes = new JLabel("Marca e Especifica\u00E7\u00F5es T\u00E9cnicas");
		lblMarcaEEspecificaes.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblMarcaEEspecificaes = new GridBagConstraints();
		gbc_lblMarcaEEspecificaes.anchor = GridBagConstraints.WEST;
		gbc_lblMarcaEEspecificaes.insets = new Insets(0, 0, 5, 5);
		gbc_lblMarcaEEspecificaes.gridx = 2;
		gbc_lblMarcaEEspecificaes.gridy = 0;
		add(lblMarcaEEspecificaes, gbc_lblMarcaEEspecificaes);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridheight = 2;
		gbc_scrollPane_1.gridwidth = 3;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.gridx = 2;
		gbc_scrollPane_1.gridy = 1;
		add(scrollPane_1, gbc_scrollPane_1);
		
		jTextAreaMarcaEspecificacoes = new JTextArea();
		jTextAreaMarcaEspecificacoes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jTextAreaMarcaEspecificacoes.setBorder(null);
		jTextAreaMarcaEspecificacoes.setEnabled(false);
		scrollPane_1.setViewportView(jTextAreaMarcaEspecificacoes);
		jTextAreaMarcaEspecificacoes.setLineWrap(true);
		
		jComboBoxTarefa = new JComboBox();
		GridBagConstraints gbc_jComboBoxTarefa = new GridBagConstraints();
		gbc_jComboBoxTarefa.insets = new Insets(0, 0, 5, 5);
		gbc_jComboBoxTarefa.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxTarefa.gridx = 0;
		gbc_jComboBoxTarefa.gridy = 1;
		add(jComboBoxTarefa, gbc_jComboBoxTarefa);
		
		jComboBoxRecursos = new JComboBox();
		
		ItemListener listener = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemListenerStateChaged(e);
			}
		};
		jComboBoxRecursos.addItemListener(listener);
		jComboBoxRecursos.addFocusListener(new FocusListener(){  

			@Override 
		    public void focusGained(FocusEvent arg0) {  
				populaComboBoxRecursos();
		    }  

			@Override
		    public void focusLost(FocusEvent arg0){               
		        //a\u00E7\u00E3o desejada quando perde o foco  
		    }
		      
		});  
		GridBagConstraints gbc_jComboBoxRecursos = new GridBagConstraints();
		gbc_jComboBoxRecursos.insets = new Insets(0, 0, 5, 5);
		gbc_jComboBoxRecursos.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxRecursos.gridx = 1;
		gbc_jComboBoxRecursos.gridy = 1;
		add(jComboBoxRecursos, gbc_jComboBoxRecursos);
		
		JButton jButtonAlocar = new JButton("Alocar");
		jButtonAlocar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonAlocarRecursos(arg0);
			}
		});
		GridBagConstraints gbc_jButtonAlocar = new GridBagConstraints();
		gbc_jButtonAlocar.anchor = GridBagConstraints.EAST;
		gbc_jButtonAlocar.insets = new Insets(0, 0, 5, 5);
		gbc_jButtonAlocar.gridx = 1;
		gbc_jButtonAlocar.gridy = 2;
		add(jButtonAlocar, gbc_jButtonAlocar);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridwidth = 5;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		Vector<String>  columns = new Vector<String>();
		 Vector<String>  data = new Vector<String>();
		 columns .add("Tarefa/Marco");
	     columns.add("Tipo de Recurso");
	     columns.add("Recursos");
	     columns.add("Especifica\u00E7\u00E3o T\u00e9cnica");
	     columns.add("Marca");
			
		 tableModel = MyTableModel.modelGerenciaRecursos(columns, data);
		 table.setModel(tableModel);
		 
		 /*
		 table.getColumnModel().getColumn(0).setPreferredWidth(500);
		 table.getColumnModel().getColumn(1).setPreferredWidth(500);
		 */
		 table.getTableHeader().setReorderingAllowed(false); 
		 table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					jTableMouseCliked(arg0);
				}
			});
		
		JButton jButtonExcluir = new JButton("Excluir");
		jButtonExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonExcluir(arg0);
			}
		});
		GridBagConstraints gbc_jButtonExcluir = new GridBagConstraints();
		gbc_jButtonExcluir.gridx = 4;
		gbc_jButtonExcluir.gridy = 4;
		add(jButtonExcluir, gbc_jButtonExcluir);

		populaComboBoxTarefa();
		populaComboBoxRecursos();
		populaTabela();		
	}

	protected void jTableMouseCliked(MouseEvent arg0) {
		if(arg0.getClickCount()==2){
			jButtonEditar(null);
		}		
	}

	protected void itemListenerStateChaged(ItemEvent e) {
		if(!jComboBoxTarefa.getSelectedItem().toString().equals("Selecione...")){
			if(e.getStateChange() == ItemEvent.SELECTED) {
				if(!jComboBoxRecursos.getSelectedItem().toString().equals("Selecione...")){
					if(jComboBoxRecursos.getSelectedItem() instanceof Software){
						software = (Software)jComboBoxRecursos.getSelectedItem();
						jTextAreaMarcaEspecificacoes.setText(software.getInformacoesAdicionais());
					}
					else if (jComboBoxRecursos.getSelectedItem() instanceof Hardware){
						hardware = (Hardware) jComboBoxRecursos.getSelectedItem();						
						jTextAreaMarcaEspecificacoes.setText(((Hardware) jComboBoxRecursos.getSelectedItem()).getMarca() +
																", " +
															((Hardware) jComboBoxRecursos.getSelectedItem()).getEspecificacoesTecnicas());
					}
				
					
				}
			}
		}
	}

	protected void jButtonEditar(ActionEvent e) {
		if(table.getValueAt(table.getSelectedRow(), 0) instanceof Tarefa){
			tarefa =  (Tarefa) table.getValueAt(table.getSelectedRow(), 0);
			if(table.getValueAt(table.getSelectedRow(), 2) instanceof Hardware){
				hardware = (Hardware)  table.getValueAt(table.getSelectedRow(), 2);	
				jComboBoxRecursos.setSelectedItem(hardware);
				jTextAreaMarcaEspecificacoes.setText(hardware.getMarca() + ", " +hardware.getEspecificacoesTecnicas());
				jComboBoxTarefa.setSelectedItem(tarefa);
				editar = true;
				tarefa.getHardwares().remove(hardware);
				hardware.getTarefas().remove(tarefa);
				TarefaDAO.updateTarefa(tarefa);
				HardwareDAO.updateHardware(hardware);
			} else if(table.getValueAt(table.getSelectedRow(), 2) instanceof Software){
				tarefa =  (Tarefa) table.getValueAt(table.getSelectedRow(), 0);
				software = (Software)  table.getValueAt(table.getSelectedRow(), 2);			
				jComboBoxTarefa.setSelectedItem(tarefa);
				jComboBoxRecursos.setSelectedItem(software);
				jTextAreaMarcaEspecificacoes.setText(software.getInformacoesAdicionais());
				editar = true;
				tarefa.getSoftwares().remove(software);
				software.getTarefas().remove(tarefa);
				TarefaDAO.updateTarefa(tarefa);
				SoftwareDAO.updateSoftware(software);
			}
		} else if(table.getValueAt(table.getSelectedRow(), 0) instanceof Marco) {
			marco =  (Marco) table.getValueAt(table.getSelectedRow(), 0);
			if(table.getValueAt(table.getSelectedRow(), 2) instanceof Hardware){
				hardware = (Hardware)  table.getValueAt(table.getSelectedRow(), 2);	
				jComboBoxRecursos.setSelectedItem(hardware);
				jTextAreaMarcaEspecificacoes.setText(hardware.getMarca() + ", " +hardware.getEspecificacoesTecnicas());
				jComboBoxTarefa.setSelectedItem(marco);
				editar = true;
				marco.getHardwares().remove(hardware);
				hardware.getTarefas().remove(marco);
				MarcoDAO.updateMarco(marco);
				HardwareDAO.updateHardware(hardware);
			} else if(table.getValueAt(table.getSelectedRow(), 2) instanceof Software){
				marco =  (Marco) table.getValueAt(table.getSelectedRow(), 0);
				software = (Software)  table.getValueAt(table.getSelectedRow(), 2);			
				jComboBoxTarefa.setSelectedItem(marco);
				jComboBoxRecursos.setSelectedItem(software);
				jTextAreaMarcaEspecificacoes.setText(software.getInformacoesAdicionais());
				editar = true;
				marco.getSoftwares().remove(software);
				software.getTarefas().remove(marco);
				MarcoDAO.updateMarco(marco);
				SoftwareDAO.updateSoftware(software);
			}
		}
		
	}

	protected void jButtonExcluir(ActionEvent arg0) {
		int i = JOptionPane.showConfirmDialog(null ,"Deseja excluir a Aloca\u00E7\u00E3o selecionada?", 
				"Aloca\u00E7\u00E3o",JOptionPane.YES_NO_OPTION); 
		if (i == JOptionPane.YES_OPTION ) { 
			if(table.getValueAt(table.getSelectedRow(), 0) instanceof Tarefa){
				tarefa =  (Tarefa) table.getValueAt(table.getSelectedRow(), 0);
				if( table.getValueAt(table.getSelectedRow(), 2) instanceof Software){
					tarefa =  (Tarefa) table.getValueAt(table.getSelectedRow(), 0);
					software = (Software)  table.getValueAt(table.getSelectedRow(), 2);				
					tarefa.getSoftwares().remove(software);
					software.getTarefas().remove(tarefa);
					TarefaDAO.updateTarefa(tarefa);
				}
				else if ( table.getValueAt(table.getSelectedRow(), 2) instanceof Hardware){
					tarefa =  (Tarefa) table.getValueAt(table.getSelectedRow(), 0);
					hardware = (Hardware)  table.getValueAt(table.getSelectedRow(), 2);				
					tarefa.getHardwares().remove(hardware);
					hardware.getTarefas().remove(tarefa);
					TarefaDAO.updateTarefa(tarefa);
				}
			} else if (table.getValueAt(table.getSelectedRow(), 0) instanceof Marco){
				marco =  (Marco) table.getValueAt(table.getSelectedRow(), 0);
				if( table.getValueAt(table.getSelectedRow(), 2) instanceof Software){
					software = (Software)  table.getValueAt(table.getSelectedRow(), 2);				
					marco.getSoftwares().remove(software);
					software.getTarefas().remove(marco);
					MarcoDAO.updateMarco(marco);
				}
				else if ( table.getValueAt(table.getSelectedRow(), 2) instanceof Hardware){
					marco =  (Marco) table.getValueAt(table.getSelectedRow(), 0);
					hardware = (Hardware)  table.getValueAt(table.getSelectedRow(), 2);				
					marco.getHardwares().remove(hardware);
					hardware.getTarefas().remove(marco);
					MarcoDAO.updateMarco(marco);
				}
			}
			populaTabela();	
			tarefa = null;
			marco = null;
			hardware = null;
		}
	}

	protected void jButtonAlocarRecursos(ActionEvent arg0) {
		
			if(!jComboBoxTarefa.getSelectedItem().toString().equals("Selecione...")){
				if(!jComboBoxRecursos.getSelectedItem().toString().equals("Selecione...")){
					if(jComboBoxRecursos.getSelectedItem() instanceof Software){
						software(arg0);
					}
					else if (jComboBoxRecursos.getSelectedItem() instanceof Hardware){
							hardware(arg0);
					}
				}
				else JOptionPane.showMessageDialog(null, "\u00c9 necess\u00e1rio selecionar um Recurso.");
			}
			else JOptionPane.showMessageDialog(null, "\u00c9 necess\u00e1rio selecionar uma Tarefa.");
	
			tarefa = null;
			marco = null;
			hardware = null;
			software = null;
			editar = false;
			populaTabela();
			jTextAreaMarcaEspecificacoes.setText("");
			jComboBoxRecursos.setSelectedIndex(0);
			jComboBoxTarefa.setSelectedIndex(0);
		
	}
	private boolean faltaPreencherHardware() {
		for (Hardware hardware : HardwareDAO.findAll()) {
			if(hardware.getTipo()==null)
				return true;
		}
		return false;
	}

	private boolean faltaPreencherSoftware() {
		for (Software software : SoftwareDAO.findAll()) {
			if(software.getTipo()==null)
				return true;
		}
		return false;
	}

	private void software(ActionEvent arg0) {
		if (jComboBoxTarefa.getSelectedItem() instanceof Tarefa){
			
			tarefa = (Tarefa) jComboBoxTarefa.getSelectedItem();
			software = (Software) jComboBoxRecursos.getSelectedItem();
			if(software.getTipo()!=null){			
				if(software.getTipo().equals("Compartilhado")){
					software.setEstado("USED");
					
					if(!tarefa.getSoftwares().contains((Software) jComboBoxRecursos.getSelectedItem())){
						cadastraSoftware(tarefa, software);		
						JOptionPane.showMessageDialog(null, "Recurso alocado \u00e0 Tarefa.");
					} else JOptionPane.showMessageDialog(null, "Tarefa j\u00e1 alocada ao Recurso.");
		
				
				} else if(software.getTipo().equals("EXCLUSIVO")){
					software.setEstado("USED");
					if(software.getTarefas().size() == 0 || software.getTarefas()==null){
						cadastraSoftware(tarefa, software);	
						JOptionPane.showMessageDialog(null, "Recurso alocado \u00e0 Tarefa.");
					}
					else JOptionPane.showMessageDialog(null, "Este recurso \u00e9 do tipo 'Exclusivo' e j\u00e1 est\u00e1 alocado por outra tarefa ou marco.");			
				} else if(software.getTipo().equals("CONSUMIVEL")){
					software.setEstado("USED");				
					if(!tarefa.getSoftwares().contains((Software) jComboBoxRecursos.getSelectedItem())){
						cadastraSoftware(tarefa, software);			
						JOptionPane.showMessageDialog(null, "Recurso alocado \u00e0 Tarefa.");
					} else JOptionPane.showMessageDialog(null, "Tarefa j\u00e1 alocada ao Recurso.");
				}
			} else JOptionPane.showMessageDialog(null, "\u00c9 necess\u00e1rio definir o Software selecionado como Compartilhado, Exclusivo ou Consum\u00edvel em Tipos.");
		} else if (jComboBoxTarefa.getSelectedItem() instanceof Marco){
			marco = (Marco)jComboBoxTarefa.getSelectedItem();
			software = (Software) jComboBoxRecursos.getSelectedItem();
			if(software.getTipo()!=null){			
				if(software.getTipo().equals("Compartilhado")){
					software.setEstado("USED");					
					if(!marco.getSoftwares().contains((Software) jComboBoxRecursos.getSelectedItem())){
						cadastraSoftware(marco, software);		
						JOptionPane.showMessageDialog(null, "Recurso alocado ao Marco.");
					} else JOptionPane.showMessageDialog(null, "Marco j\u00e1 alocado ao Recurso.");
		
				
				} else if(software.getTipo().equals("EXCLUSIVO")){
					software.setEstado("USED");
					if(software.getTarefas().size() == 0 || software.getTarefas()==null){
						cadastraSoftware(tarefa, software);	
						JOptionPane.showMessageDialog(null, "Recurso alocado ao Marco.");
					}
					else JOptionPane.showMessageDialog(null, "Este recurso \u00e9 do tipo 'Exclusivo' e j\u00e1 est\u00e1 alocado por outra tarefa ou marco.");			
				} else if(software.getTipo().equals("CONSUMIVEL")){
					software.setEstado("USED");				
					if(!marco.getSoftwares().contains((Software) jComboBoxRecursos.getSelectedItem())){
						cadastraSoftware(marco, software);			
						JOptionPane.showMessageDialog(null, "Recurso alocado ao Marco.");
					} else JOptionPane.showMessageDialog(null, "Marco j\u00e1 alocado ao Recurso.");
				}
			} else JOptionPane.showMessageDialog(null, "\u00c9 necess\u00e1rio definir o Software selecionado como Compartilhado, Exclusivo ou Consum\u00edvel em Tipos.");
		}
	}

	private void cadastraSoftware(Tarefa tarefa, Software software) {
		JOptionPane.showMessageDialog(null, "passou");
		tarefa.getSoftwares().add(software);
		software.getTarefas().add(tarefa);
		SoftwareDAO.updateSoftware(software);
		TarefaDAO.updateTarefa(tarefa);
	}
	
	private void cadastraSoftware(Marco marco, Software software) {
		marco.getSoftwares().add(software);
		software.getMarcos().add(marco);
		SoftwareDAO.updateSoftware(software);
		MarcoDAO.updateMarco(marco);
	}

	private void hardware(ActionEvent arg0) {
		hardware = (Hardware) jComboBoxRecursos.getSelectedItem();
		if(jComboBoxTarefa.getSelectedItem() instanceof Tarefa){
			tarefa = (Tarefa)jComboBoxTarefa.getSelectedItem();
			if(hardware.getTipo() != null){
				//hardware(arg0);
				if(hardware.getTipo().equals("Compartilhado")){
					hardware.setEstado("USED");				
					if(!tarefa.getHardwares().contains(hardware)){
						cadastraHardware(tarefa, hardware);	
						JOptionPane.showMessageDialog(null, "Recurso alocado \u00e0 Tarefa.");
					} else JOptionPane.showMessageDialog(null, "Tarefa j\u00e1 alocada ao Recurso.");
				} else if(hardware.getTipo().equals("EXCLUSIVO")){
					hardware.setEstado("USED");
					if((hardware.getTarefas().size() == 0) || hardware.getTarefas()==null){
						cadastraHardware(tarefa, hardware);	
						JOptionPane.showMessageDialog(null, "Recurso alocado \u00e0 Tarefa.");
					}
					else JOptionPane.showMessageDialog(null, "Este recurso \u00e9 do tipo 'Exclusivo' e j\u00e1 est\u00e1 alocado por outra tarefa.");
				} else if(hardware.getTipo().equals("CONSUMIVEL")){
					hardware.setEstado("USED");
					if(!tarefa.getHardwares().contains(hardware)){
						cadastraHardware(tarefa, hardware);	
						JOptionPane.showMessageDialog(null, "Recurso alocado \u00e0 Tarefa.");
					} else JOptionPane.showMessageDialog(null, "Tarefa j\u00e1 alocada ao Recurso.");
				}
			} else JOptionPane.showMessageDialog(null, "\u00c9 necess\u00e1rio definir o Hardware selecionado como Compartilhado, Exclusivo ou Consum\u00edvel em Tipos.");
		
		} else if (jComboBoxTarefa.getSelectedItem() instanceof Marco){
			marco = (Marco)jComboBoxTarefa.getSelectedItem();
			if(hardware.getTipo() != null){
				//hardware(arg0);
				if(hardware.getTipo().equals("Compartilhado")){
					hardware.setEstado("USED");				
					if(!marco.getHardwares().contains(hardware)){
						cadastraHardware(marco, hardware);	
						JOptionPane.showMessageDialog(null, "Recurso alocado ao Marco.");
					} else JOptionPane.showMessageDialog(null, "Marco j\u00e1 alocado ao Recurso.");
				} else if(hardware.getTipo().equals("EXCLUSIVO")){
					hardware.setEstado("USED");
					if((hardware.getTarefas().size() == 0) || hardware.getTarefas()==null){
						cadastraHardware(marco, hardware);	
						JOptionPane.showMessageDialog(null, "Recurso alocado ao Marco.");
					}
					else JOptionPane.showMessageDialog(null, "Este recurso \u00e9 do tipo 'Exclusivo' e j\u00e1 est\u00e1 alocado por outra tarefa ou marco.");
				} else if(hardware.getTipo().equals("CONSUMIVEL")){
					hardware.setEstado("USED");
					if(!marco.getHardwares().contains(hardware)){
						cadastraHardware(marco, hardware);	
						JOptionPane.showMessageDialog(null, "Recurso alocado ao Marco.");
					} else JOptionPane.showMessageDialog(null, "Marco j\u00e1 alocado ao Recurso.");
				}
			} else JOptionPane.showMessageDialog(null, "\u00c9 necess\u00e1rio definir o Hardware selecionado como Compartilhado, Exclusivo ou Consum\u00edvel em Tipos.");
		}
	}

	private void cadastraHardware(Tarefa tarefa, Hardware hardware) {
		hardware.getTarefas().add(tarefa);
		tarefa.getHardwares().add(hardware);							
		TarefaDAO.updateTarefa(tarefa);
		HardwareDAO.updateHardware(hardware);
	}
	
	private void cadastraHardware(Marco marco, Hardware hardware) {
		hardware.getMarcos().add(marco);
		marco.getHardwares().add(hardware);							
		MarcoDAO.updateMarco(marco);
		HardwareDAO.updateHardware(hardware);
	}

	private void populaComboBoxRecursos() {
		jComboBoxRecursos.removeAllItems();
		jComboBoxRecursos.addItem("Selecione...");
		for (Hardware hardware : HardwareDAO.findAll()) {
				jComboBoxRecursos.addItem(hardware);
		}
		
		for (Software software : SoftwareDAO.findAll()) {
			jComboBoxRecursos.addItem(software);
		}
	}

	private void populaComboBoxTarefa() {	
		jComboBoxTarefa.removeAllItems();
		List<Tarefa> tarefas = consultaTarefas();
		List<Marco> marcos = consultaMarcos();
		jComboBoxTarefa.addItem("Selecione...");
		if(tarefas!=null){
			for (Tarefa tarefa : tarefas) {
				jComboBoxTarefa.addItem(tarefa);
			}
		}
		
		if(marcos!=null){
			for (Marco marco : marcos) {
				jComboBoxTarefa.addItem(marco);
			}
		}
		
}
	
	private List<Tarefa> consultaTarefas() {
		return TarefaDAO.getTarefasByProcesso(JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso());
	}
	
	private List<Marco> consultaMarcos(){
		List<Fase> fases = JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getFase();
		List<Marco> marcos = new ArrayList<Marco>();
		for(Fase fase : fases){
			marcos.addAll(fase.getMarco());
		}
		return marcos;
	}


	private void populaTabela() {;
		tableModel.setNumRows(0);
		for (Tarefa tarefa :consultaTarefas()) {
			for (Hardware hardware : tarefa.getHardwares()) {
				tableModel.addRow(new Object[]{ tarefa, "Hardware", hardware, hardware.getEspecificacoesTecnicas(), hardware.getMarca()});
			}		
			for(Software software : tarefa.getSoftwares()){
				tableModel.addRow(new Object[]{ tarefa, "Software", software, software.getInformacoesAdicionais(), software.getObjetivos()});
				
			}
		}
		for (Marco marco : consultaMarcos()) {
			for (Hardware hardware : marco.getHardwares()) {
				tableModel.addRow(new Object[]{ marco, "Hardware", hardware, hardware.getEspecificacoesTecnicas(), hardware.getMarca()});
			}		
			for(Software software : marco.getSoftwares()){
				tableModel.addRow(new Object[]{ marco, "Software", software, software.getInformacoesAdicionais(), software.getObjetivos()});
				
			}
		}
	}
}