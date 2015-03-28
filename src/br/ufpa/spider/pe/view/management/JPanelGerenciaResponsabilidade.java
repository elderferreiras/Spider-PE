package br.ufpa.spider.pe.view.management;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.ufpa.spider.pe.controller.PapelController;
import br.ufpa.spider.pe.model.Atividade;
import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.Iteracao;
import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.Papel;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.Software;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.Tipo;
import br.ufpa.spider.pe.model.Treinamento;
import br.ufpa.spider.pe.model.dao.HumanoDAO;
import br.ufpa.spider.pe.model.dao.MarcoDAO;
import br.ufpa.spider.pe.model.dao.PapelDAO;
import br.ufpa.spider.pe.model.dao.SoftwareDAO;
import br.ufpa.spider.pe.model.dao.TarefaDAO;
import br.ufpa.spider.pe.model.util.MyTableModel;
import br.ufpa.spider.pe.view.Spider_PE_Home;

public class JPanelGerenciaResponsabilidade extends JPanel {
	private JTable table;
	private DefaultTableModel tableModel;
	private JComboBox jComboBoxTarefa;
	private JComboBox jComboBoxPapelAssociado;
	private JButton btnAtribuir;
	private JButton btnExcluir;
	private JScrollPane scrollPane;
	private JTextArea jTextAreaHabilidadesRequeridas;
	private JLabel lblRecursoHumano;
	private JLabel lblPapisExercidos;
	private JTextArea jTextAreaConhecimento;
	private JComboBox jComboBoxRecursoHumano;
	private JTextArea jTextAreaPapeiExercido;
	private JLabel lblConhecimento;
	private JLabel lblPapeisRequeridos;
	private Tarefa tarefa;
	private Papel papel;
	private Humano humano;
	private boolean editar;
	private DefaultListModel listModel;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private Marco marco;

	/**
	 * Create the panel.
	 */
	public JPanelGerenciaResponsabilidade() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		listModel = new DefaultListModel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{224, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		ItemListener listener = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemListenerStateChaged(e);
			}
		};
		ItemListener listenerHumano = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemListenerStateChagedHumano(e);
			}
		};
		
		Vector<String>  columns = new Vector<String>();
		 Vector<String>  data = new Vector<String>();
		 columns .add("Tarefa/Marco");
		 columns.add("Papel");
	     columns.add("Respons\u00e1veis");
			
		 tableModel = MyTableModel.modelGerenciaResponsabilidade(columns, data);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{236, 205, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{236, 236, 0};
		gbl_panel_1.rowHeights = new int[]{0, 66, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblTarefa = new JLabel("Tarefa/Marco");
		GridBagConstraints gbc_lblTarefa = new GridBagConstraints();
		gbc_lblTarefa.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTarefa.insets = new Insets(0, 0, 5, 5);
		gbc_lblTarefa.gridx = 0;
		gbc_lblTarefa.gridy = 0;
		panel_1.add(lblTarefa, gbc_lblTarefa);
		lblTarefa.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblResponsveis = new JLabel("Papel Associado");
		GridBagConstraints gbc_lblResponsveis = new GridBagConstraints();
		gbc_lblResponsveis.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblResponsveis.insets = new Insets(0, 0, 5, 0);
		gbc_lblResponsveis.gridx = 1;
		gbc_lblResponsveis.gridy = 0;
		panel_1.add(lblResponsveis, gbc_lblResponsveis);
		lblResponsveis.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jComboBoxTarefa = new JComboBox();
		GridBagConstraints gbc_jComboBoxTarefa = new GridBagConstraints();
		gbc_jComboBoxTarefa.anchor = GridBagConstraints.NORTH;
		gbc_jComboBoxTarefa.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxTarefa.insets = new Insets(0, 0, 0, 5);
		gbc_jComboBoxTarefa.gridx = 0;
		gbc_jComboBoxTarefa.gridy = 1;
		panel_1.add(jComboBoxTarefa, gbc_jComboBoxTarefa);
		
		jComboBoxPapelAssociado = new JComboBox();
		GridBagConstraints gbc_jComboBoxPapelAssociado = new GridBagConstraints();
		gbc_jComboBoxPapelAssociado.anchor = GridBagConstraints.NORTH;
		gbc_jComboBoxPapelAssociado.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxPapelAssociado.gridx = 1;
		gbc_jComboBoxPapelAssociado.gridy = 1;
		panel_1.add(jComboBoxPapelAssociado, gbc_jComboBoxPapelAssociado);
		jComboBoxPapelAssociado.addItem("Selecione...");
		jComboBoxTarefa.addItemListener(listener);
		
		panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridheight = 2;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 0;
		panel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{205, 0};
		gbl_panel_3.rowHeights = new int[]{0, 66, 0, 69, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		lblPapeisRequeridos = new JLabel("Habilidades Requeridas");
		GridBagConstraints gbc_lblPapeisRequeridos = new GridBagConstraints();
		gbc_lblPapeisRequeridos.anchor = GridBagConstraints.NORTH;
		gbc_lblPapeisRequeridos.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPapeisRequeridos.insets = new Insets(0, 0, 5, 0);
		gbc_lblPapeisRequeridos.gridx = 0;
		gbc_lblPapeisRequeridos.gridy = 0;
		panel_3.add(lblPapeisRequeridos, gbc_lblPapeisRequeridos);
		lblPapeisRequeridos.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 1;
		panel_3.add(scrollPane_2, gbc_scrollPane_2);
		
		jTextAreaHabilidadesRequeridas = new JTextArea();
		scrollPane_2.setViewportView(jTextAreaHabilidadesRequeridas);
		jTextAreaHabilidadesRequeridas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jTextAreaHabilidadesRequeridas.setBorder(null);
		jTextAreaHabilidadesRequeridas.setEditable(false);
		
		lblConhecimento = new JLabel("Conhecimento");
		GridBagConstraints gbc_lblConhecimento = new GridBagConstraints();
		gbc_lblConhecimento.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblConhecimento.insets = new Insets(0, 0, 5, 0);
		gbc_lblConhecimento.gridx = 0;
		gbc_lblConhecimento.gridy = 2;
		panel_3.add(lblConhecimento, gbc_lblConhecimento);
		lblConhecimento.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridx = 0;
		gbc_scrollPane_3.gridy = 3;
		panel_3.add(scrollPane_3, gbc_scrollPane_3);
		
		jTextAreaConhecimento = new JTextArea();
		scrollPane_3.setViewportView(jTextAreaConhecimento);
		jTextAreaConhecimento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jTextAreaConhecimento.setBorder(null);
		jTextAreaConhecimento.setEditable(false);
		jTextAreaConhecimento.setToolTipText("Todas as informa\u00E7\u00F5es referentes \u00E0 Capacidade do Recursos Humano");
		
		panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{236, 236, 0};
		gbl_panel_2.rowHeights = new int[]{0, 69, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		lblRecursoHumano = new JLabel("Recurso Humano");
		GridBagConstraints gbc_lblRecursoHumano = new GridBagConstraints();
		gbc_lblRecursoHumano.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRecursoHumano.insets = new Insets(0, 0, 5, 5);
		gbc_lblRecursoHumano.gridx = 0;
		gbc_lblRecursoHumano.gridy = 0;
		panel_2.add(lblRecursoHumano, gbc_lblRecursoHumano);
		lblRecursoHumano.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblPapisExercidos = new JLabel("Pap\u00E9is Exercidos");
		GridBagConstraints gbc_lblPapisExercidos = new GridBagConstraints();
		gbc_lblPapisExercidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPapisExercidos.insets = new Insets(0, 0, 5, 0);
		gbc_lblPapisExercidos.gridx = 1;
		gbc_lblPapisExercidos.gridy = 0;
		panel_2.add(lblPapisExercidos, gbc_lblPapisExercidos);
		lblPapisExercidos.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jComboBoxRecursoHumano = new JComboBox();
		GridBagConstraints gbc_jComboBoxRecursoHumano = new GridBagConstraints();
		gbc_jComboBoxRecursoHumano.anchor = GridBagConstraints.NORTH;
		gbc_jComboBoxRecursoHumano.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxRecursoHumano.insets = new Insets(0, 0, 0, 5);
		gbc_jComboBoxRecursoHumano.gridx = 0;
		gbc_jComboBoxRecursoHumano.gridy = 1;
		panel_2.add(jComboBoxRecursoHumano, gbc_jComboBoxRecursoHumano);
		
		jTextAreaPapeiExercido = new JTextArea();
		GridBagConstraints gbc_jTextAreaPapeiExercido = new GridBagConstraints();
		gbc_jTextAreaPapeiExercido.fill = GridBagConstraints.BOTH;
		gbc_jTextAreaPapeiExercido.gridx = 1;
		gbc_jTextAreaPapeiExercido.gridy = 1;
		panel_2.add(jTextAreaPapeiExercido, gbc_jTextAreaPapeiExercido);
		jTextAreaPapeiExercido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jTextAreaPapeiExercido.setBorder(null);
		jTextAreaPapeiExercido.setEditable(false);
		jTextAreaPapeiExercido.setLineWrap(true);
		jComboBoxRecursoHumano.addItemListener(listenerHumano);
		
		btnAtribuir = new JButton("Alocar");
		GridBagConstraints gbc_btnAtribuir = new GridBagConstraints();
		gbc_btnAtribuir.anchor = GridBagConstraints.EAST;
		gbc_btnAtribuir.gridwidth = 2;
		gbc_btnAtribuir.insets = new Insets(0, 0, 5, 0);
		gbc_btnAtribuir.gridx = 0;
		gbc_btnAtribuir.gridy = 2;
		panel.add(btnAtribuir, gbc_btnAtribuir);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		panel.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					jTableMouseCliked(arg0);
				}
			});
		scrollPane.setViewportView(table);
		table.setModel(tableModel);
		
		btnExcluir = new JButton("Excluir");
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.anchor = GridBagConstraints.EAST;
		gbc_btnExcluir.gridwidth = 2;
		gbc_btnExcluir.gridx = 0;
		gbc_btnExcluir.gridy = 4;
		panel.add(btnExcluir, gbc_btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonExcluir(arg0);
			}
		});
		
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(400);
		table.getTableHeader().setReorderingAllowed(false);
		btnAtribuir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonAlocar(arg0);
			}
		});
		populaComboBoxTarefa();
		populaComboBoxRecursoHumano();
		populaTabela();
	}

	protected void jTableMouseCliked(MouseEvent arg0) {
		if(arg0.getClickCount()==2){
			jButtonEditar(null);
		}
		
	}

	protected void jButtonEditar(ActionEvent arg0) {
		if ( table.getValueAt(table.getSelectedRow(), 0) instanceof Tarefa){
			tarefa = (Tarefa) table.getValueAt(table.getSelectedRow(), 0);
			jComboBoxTarefa.setSelectedItem(tarefa);
			jComboBoxPapelAssociado.setSelectedItem((Papel)table.getValueAt(table.getSelectedRow(), 1));
			jTextAreaHabilidadesRequeridas.setText(tarefa.getHabilidadesRequeridas()==null? "Sem Habilidades Requeridas cadastradas." : tarefa.getHabilidadesRequeridas());
			humano = (Humano) table.getValueAt(table.getSelectedRow(), 2);
			jComboBoxRecursoHumano.setSelectedItem(humano);
			jTextAreaConhecimento.setText(humano.getCapacidade().getConhecimento());
			String papeisExercidos = "";
			for (Tipo  tipo : humano.getPapeisExercidos()) {
				papeisExercidos = tipo.getNome() + "\n";
			}
			jTextAreaPapeiExercido.setText(papeisExercidos);
			editar = true;
		} if ( table.getValueAt(table.getSelectedRow(), 0) instanceof Marco){
			marco = (Marco) table.getValueAt(table.getSelectedRow(), 0);
			jComboBoxTarefa.setSelectedItem(marco);
			jComboBoxPapelAssociado.setSelectedItem((Papel)table.getValueAt(table.getSelectedRow(), 1));
			jTextAreaHabilidadesRequeridas.setText(marco.getHabilidadesRequeridas()==null? "Sem Habilidades Requeridas cadastradas." : marco.getHabilidadesRequeridas());
			humano = (Humano) table.getValueAt(table.getSelectedRow(), 2);
			jComboBoxRecursoHumano.setSelectedItem(humano);
			jTextAreaConhecimento.setText(humano.getCapacidade().getConhecimento());
			String papeisExercidos = "";
			for (Tipo  tipo : humano.getPapeisExercidos()) {
				papeisExercidos = tipo.getNome() + "\n";
			}
			jTextAreaPapeiExercido.setText(papeisExercidos);
			editar = true;
		}
	}

	protected void jButtonExcluir(ActionEvent arg0) {
		int i = JOptionPane.showConfirmDialog(null ,"Deseja excluir a Aloca\u00e7\u00e3o selecionada?", 
				"Aloca\u00e7\u00e3o",JOptionPane.YES_NO_OPTION); 
		if (i == JOptionPane.YES_OPTION ) { 
			papel = (Papel) tableModel.getValueAt(table.getSelectedRow(), 1);
			humano = (Humano)tableModel.getValueAt(table.getSelectedRow(), 2);
			
			papel.getHumanosAlocados().remove(humano);
			
			populaTabela();
			JOptionPane.showMessageDialog(null, "Aloca\u00e7\u00e3o Exclu\u00edda");
		}
	}

	protected void jButtonAlocar(ActionEvent arg0) {
		
			if(!jComboBoxTarefa.getSelectedItem().toString().equals("Selecione...")){
				if(!jComboBoxPapelAssociado.getSelectedItem().toString().equals("Selecione...")){
					if(!jComboBoxRecursoHumano.getSelectedItem().toString().equals("Selecione...")){
						Humano humano = (Humano) jComboBoxRecursoHumano.getSelectedItem();
						
						 if(humano.getTipo()!=null){
							if(jComboBoxTarefa.getSelectedItem() instanceof Tarefa){
								tarefa = (Tarefa)jComboBoxTarefa.getSelectedItem();
								if(humano.getTipo().equals("Compartilhado")){
									humano.setEstado("USED");
									if(tarefa.getInicioPrevisto()!=null && tarefa.getFimPrevisto()!=null)
										verificaDataRH(tarefa, humano);
									cadastra();						
								} else if(humano.getTipo().equals("Exclusivo")){
									humano.setEstado("USED");
									boolean tem = false;
									for (Tarefa tarefas : getTarefas()) {
										for (Papel papel : tarefas.getPapeis()) {
											if(papel.getHumanosAlocados().contains(humano))
												tem = true;
										}
										
									}					
									if(tem){
										JOptionPane.showMessageDialog(null, "Este recurso \u00e9 do tipo 'Exclusivo' e j\u00e1 est\u00e1 alocado por outra tarefa ou marco.");
									}
									else {
										verificaDataRH(tarefa, humano);
										cadastra();
									}
								} else if(humano.getTipo().equals("Consumï¿½vel")){
									humano.setEstado("USED");
									verificaDataRH(tarefa, humano);
									cadastra();
								}
							} else if (jComboBoxTarefa.getSelectedItem() instanceof Marco){
								marco = (Marco)jComboBoxTarefa.getSelectedItem();
								marco.getPapeis().add(papel);
								MarcoDAO.updateMarco(marco);
								if(humano.getTipo().equals("Compartilhado")){
									humano.setEstado("USED");
									if(marco.getInicioPrevisto()!=null && marco.getFimPrevisto()!=null)
										verificaDataRH(marco, humano);
									cadastra();						
								} else if(humano.getTipo().equals("Exclusivo")){
									humano.setEstado("USED");
									boolean tem = false;
									for (Marco marcos : consultaMarcos()) {
										for (Papel papel : marcos.getPapeis()) {
											if(papel.getHumanosAlocados().contains(humano))
												tem = true;
										}
										
									}					
									if(tem){
										JOptionPane.showMessageDialog(null, "Este recurso \u00e9 do tipo 'Exclusivo' e j\u00e1 est\u00e1 alocado por outra tarefa ou marco.");
									}
									else {
										verificaDataRH(marco, humano);
										cadastra();
									}
								} else if(humano.getTipo().equals("Consumível")){
									humano.setEstado("USED");
									verificaDataRH(marco, humano);
									cadastra();
								}
							}
							
						 } else JOptionPane.showMessageDialog(null, "\u00c9 necess\u00e1rio definir o Recurso Humano selecionado como Compartilhado ou Exclusivo em Tipos.");
					} else JOptionPane.showMessageDialog(null, "Selecione um Recurso Humano.");
				} else JOptionPane.showMessageDialog(null, "Selecione um Papel Associado.");
			} else JOptionPane.showMessageDialog(null, "Selecione uma Tarefa.");
			
			tarefa = null;
			marco = null;
			papel = null;
			humano  = null;
		
		
	}

	private void verificaDataRH(Tarefa tarefa, Humano humano) {
		int dataInicioTarefa = trata(tarefa.getInicioPrevisto());
		int dataFinalTarefa = trata(tarefa.getFimPrevisto());
		
		for (Treinamento treinamento : humano.getTreinamento()) {
			int dataInicioTreinamento = trata(treinamento.getInicio());
			int dataFinalTreinamento = trata(treinamento.getTermino());
			if(dataInicioTarefa<=dataInicioTreinamento && dataFinalTarefa>=dataFinalTreinamento){
				JOptionPane.showMessageDialog(null, "Este recurso realizar\u00e1 um Treinamento no per\u00edodo de "+ treinamento.getInicio() +" \u00e0 "+treinamento.getTermino()+".");
				break;
				}			
		}	
	}
	
	private void verificaDataRH(Marco marco, Humano humano) {
		int dataInicioTarefa = trata(marco.getInicioPrevisto());
		int dataFinalTarefa = trata(marco.getFimPrevisto());
		
		for (Treinamento treinamento : humano.getTreinamento()) {
			int dataInicioTreinamento = trata(treinamento.getInicio());
			int dataFinalTreinamento = trata(treinamento.getTermino());
			if(dataInicioTarefa<=dataInicioTreinamento && dataFinalTarefa>=dataFinalTreinamento){
				JOptionPane.showMessageDialog(null, "Este recurso realizar\u00e1 um Treinamento no per\u00edodo de "+ treinamento.getInicio() +" \u00e0 "+treinamento.getTermino()+".");
				break;
				}			
		}	
	}
	
	private int trata(String string){
		String temp = (String) string.subSequence(6, 9)  +  string.subSequence(3, 4) + string.subSequence(0, 1) ;
		return Integer.parseInt(temp);
	}

	private void cadastra() {
		 if(editar){	
			 	papel.getHumanosAlocados().remove(humano);
				papel = (Papel) jComboBoxPapelAssociado.getSelectedItem();
				humano = (Humano) jComboBoxRecursoHumano.getSelectedItem();				
				if(!papel.getHumanosAlocados().contains(humano))
					papel.getHumanosAlocados().add(humano);
				if(!humano.getPapeisAlocados().contains(papel))
					humano.getPapeisAlocados().add(papel);
				
				PapelDAO.updatePapel(papel);
				HumanoDAO.updateHumano(humano);
				//JOptionPane.showMessageDialog(null, "Recurso Humano alocado \u00e0 Tarefa.");
			} else {
				papel = (Papel) jComboBoxPapelAssociado.getSelectedItem();
				humano = (Humano) jComboBoxRecursoHumano.getSelectedItem();
				
				if(!papel.getHumanosAlocados().contains(humano))
					papel.getHumanosAlocados().add(humano);
				if(!humano.getPapeisAlocados().contains(papel))
					humano.getPapeisAlocados().add(papel);
				
				HumanoDAO.updateHumano(humano);
				PapelDAO.updatePapel(papel);
				//JOptionPane.showMessageDialog(null, "Recurso Humano alocado \u00e0 Tarefa.");
			}
			populaTabela();
			clear();
			editar = false;		
	}

	private void clear() {
		jComboBoxTarefa.setSelectedItem("Selecione...");
		jComboBoxPapelAssociado.setSelectedItem("Selecione...");
		jComboBoxRecursoHumano.setSelectedItem("Selecione...");
		jTextAreaConhecimento.setText("");
		jTextAreaHabilidadesRequeridas.setText("");
		jTextAreaPapeiExercido.setText("");		
	}

	private void populaTabela() {
		tableModel.setNumRows(0);
		for (Tarefa tarefa : getTarefas()) {
			for(Papel papel : tarefa.getPapeis()){
				for(Humano humano : papel.getHumanosAlocados())
					tableModel.addRow(new Object[]{tarefa, papel, humano});
			}		
		}
		for (Marco marco : consultaMarcos()) {
			for(Papel papel : marco.getPapeis()){
				if(papel!=null){
					for(Humano humano : papel.getHumanosAlocados())
						tableModel.addRow(new Object[]{marco, papel, humano});
				}
			}		
		}
	}

	
	protected void itemListenerStateChaged(ItemEvent e) {
		if(!jComboBoxTarefa.getSelectedItem().toString().equals("Selecione...")){
			if(jComboBoxTarefa.getSelectedItem() instanceof Tarefa){
				if(e.getStateChange() == ItemEvent.SELECTED) {
					ArrayList<Papel> papeis = new ArrayList<Papel>();
					jTextAreaHabilidadesRequeridas.setText(((Tarefa)jComboBoxTarefa.getSelectedItem()).getHabilidadesRequeridas() == null ? "Sem Habilidades Requeridas cadastradas": ((Tarefa)jComboBoxTarefa.getSelectedItem()).getHabilidadesRequeridas());				
					for (Papel papel : ((Tarefa)jComboBoxTarefa.getSelectedItem()).getPapeis()) {
						if(!papeis.contains(papel))
							papeis.add(papel);
					}
					populaComboBoxPapelAssociado(papeis);
				}
			} else if (jComboBoxTarefa.getSelectedItem() instanceof Marco){
				if(e.getStateChange() == ItemEvent.SELECTED) {
					jTextAreaHabilidadesRequeridas.setText(((Marco)jComboBoxTarefa.getSelectedItem()).getHabilidadesRequeridas() == null ? "Sem Habilidades Requeridas cadastradas": ((Marco)jComboBoxTarefa.getSelectedItem()).getHabilidadesRequeridas());				
					Processo processo = ((Marco)jComboBoxTarefa.getSelectedItem()).getFase().getProcesso();
					Marco marco = ((Marco)jComboBoxTarefa.getSelectedItem());
					populaComboBoxPapelAssociado(marco.getPapeis());
				}
			}
		}
		
	}
	
	private void populaComboBoxPapelAssociado(List<Papel> papeis) {
		jComboBoxPapelAssociado.removeAllItems();
		jComboBoxPapelAssociado.addItem("Selecione...");
		for (Papel papel : papeis) {
			jComboBoxPapelAssociado.addItem(papel);
		}
	}
	
	private void populaComboBoxRecursoHumano(){
//		jComboBoxRecursoHumano.addItem("Selecione...");
//		List<Humano> humanos = getHumanos();						
//		 if(humanos.size()!=0 || humanos != null){
//				for (Humano humano : humanos) {
//					jComboBoxRecursoHumano.addItem(humano);
//					}				
//			}
		 jComboBoxRecursoHumano.removeAllItems();
			jComboBoxRecursoHumano.addItem("Selecione...");
			for (Humano humano : HumanoDAO.findAll()) {
				if(!humano.equals(Spider_PE_Home.getInstance().getInstanciaLogado())){
					jComboBoxRecursoHumano.addItem(humano);
				}				
			}		
	}

	private void populaComboBoxTarefa() {
		jComboBoxTarefa.removeAllItems();
		List<Tarefa> tarefas = consultaTarefas();
		List<Marco> marcos = consultaMarcos();
		jComboBoxTarefa.addItem("Selecione...");
		if(tarefas.size()!=0 || tarefas !=null){
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
	}
	protected void itemListenerStateChagedHumano(ItemEvent e) {		
		String texto = "";
		if(!jComboBoxRecursoHumano.getSelectedItem().toString().equals("Selecione...")){
			if(e.getStateChange() == ItemEvent.SELECTED){
				Humano humano = (Humano) jComboBoxRecursoHumano.getSelectedItem();
					if(humano.getPapeisExercidos() != null){
						for (Tipo  tipo : humano.getPapeisExercidos()) {
									texto +=tipo.getNome() + "\n";
								}
						jTextAreaPapeiExercido.setText(texto);
						}
					if(humano.getCapacidade() != null){
						jTextAreaConhecimento.setText(humano.getCapacidade().getConhecimento());
					}
				}
	
				
			}
		
	}
	
	private List<Tarefa> consultaTarefas() {
		return TarefaDAO.getTarefasByProcesso(Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso());
	}
	
	private List<Marco> consultaMarcos(){
		List<Fase> fases = Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso().getFase();
		List<Marco> marcos = new ArrayList<Marco>();
		for(Fase fase : fases){
			marcos.addAll(fase.getMarco());
		}
		return marcos;
	}
	
	private List<Humano> getHumanos(){		  
		ArrayList<Humano> humanos= new ArrayList<Humano>();	
		for (Humano humano : Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso().getHumano()) {
			humanos.add(humano);
		}	  
		return humanos;
	}
	
	
	private List<Tarefa> getTarefas(){
			return TarefaDAO.getTarefasByProcesso(Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso());
		}
		

}