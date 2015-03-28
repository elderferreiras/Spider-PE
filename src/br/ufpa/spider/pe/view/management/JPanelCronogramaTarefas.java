package br.ufpa.spider.pe.view.management;

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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.ufpa.spider.pe.controller.MarcoController;
import br.ufpa.spider.pe.controller.TarefaController;
import br.ufpa.spider.pe.model.Campo;
import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.Tipo;
import br.ufpa.spider.pe.model.dao.CampoDAO;
import br.ufpa.spider.pe.model.dao.MarcoDAO;
import br.ufpa.spider.pe.model.dao.TarefaDAO;
import br.ufpa.spider.pe.model.util.MeuDocument;
import br.ufpa.spider.pe.model.util.MyTableModel;
import br.ufpa.spider.pe.view.Spider_PE_Home;
import br.ufpa.spider.pe.view.util.Icone;

import com.toedter.calendar.JDateChooser;

public class JPanelCronogramaTarefas extends JPanel {
	private JLabel jLabelTarefa;
	private JLabel jLabelDataInicio;
	private JLabel jLabelDataFim;
	private JLabel jLabelCargaHoraria;
	private JLabel jLabelUnidadeEsforco;
	private static JComboBox jComboBoxTarefa;
	private JComboBox jComboBoxUnidadeMedida;
	private JButton jButtonCadastrar;
	private MaskFormatter formatDate;
	private MaskFormatter formatCargaHoraria;
	private JTextField jTextFieldCargaHoraria;
	private Vector<String> columns;
	private Vector data;
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel panelDataFim;
	private JDateChooser componentDataInicio;
	private JDateChooser componentDataFinal;
	private String ano;
   
	/**
	 * Create the panel.
	 */
	public JPanelCronogramaTarefas() {
		setBorder(new EmptyBorder(25, 20, 25, 20));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 111, 126, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		jLabelTarefa = new JLabel("Tarefa/Marco");
		GridBagConstraints gbc_jLabelTarefa = new GridBagConstraints();
		gbc_jLabelTarefa.fill = GridBagConstraints.HORIZONTAL;
		gbc_jLabelTarefa.gridwidth = 2;
		gbc_jLabelTarefa.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelTarefa.gridx = 0;
		gbc_jLabelTarefa.gridy = 0;
		add(jLabelTarefa, gbc_jLabelTarefa);
		
		jLabelDataInicio = new JLabel("Data In\u00EDcio");
		GridBagConstraints gbc_jLabelDataInicio = new GridBagConstraints();
		gbc_jLabelDataInicio.fill = GridBagConstraints.HORIZONTAL;
		gbc_jLabelDataInicio.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelDataInicio.gridx = 2;
		gbc_jLabelDataInicio.gridy = 0;
		add(jLabelDataInicio, gbc_jLabelDataInicio);
		
		jLabelDataFim = new JLabel("Data Fim");
		GridBagConstraints gbc_jLabelDataFim = new GridBagConstraints();
		gbc_jLabelDataFim.fill = GridBagConstraints.HORIZONTAL;

		gbc_jLabelDataFim.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelDataFim.gridx = 3;
		gbc_jLabelDataFim.gridy = 0;
		add(jLabelDataFim, gbc_jLabelDataFim);
		
		jLabelCargaHoraria = new JLabel("Carga Hor\u00E1ria");
		GridBagConstraints gbc_jLabelCargaHoraria = new GridBagConstraints();
		gbc_jLabelCargaHoraria.fill = GridBagConstraints.HORIZONTAL;
		gbc_jLabelCargaHoraria.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelCargaHoraria.gridx = 4;
		gbc_jLabelCargaHoraria.gridy = 0;
		add(jLabelCargaHoraria, gbc_jLabelCargaHoraria);
		
		jLabelUnidadeEsforco = new JLabel("Unidade de Medida");
		GridBagConstraints gbc_jLabelUnidadeEsforco = new GridBagConstraints();
		gbc_jLabelUnidadeEsforco.fill = GridBagConstraints.HORIZONTAL;
		gbc_jLabelUnidadeEsforco.gridwidth = 2;
		gbc_jLabelUnidadeEsforco.insets = new Insets(0, 0, 5, 0);
		gbc_jLabelUnidadeEsforco.gridx = 5;
		gbc_jLabelUnidadeEsforco.gridy = 0;
		add(jLabelUnidadeEsforco, gbc_jLabelUnidadeEsforco);
		
		jComboBoxTarefa = new JComboBox();
		GridBagConstraints gbc_jComboBoxTarefa = new GridBagConstraints();
		gbc_jComboBoxTarefa.gridwidth = 2;
		gbc_jComboBoxTarefa.insets = new Insets(0, 0, 5, 5);
		gbc_jComboBoxTarefa.fill = GridBagConstraints.BOTH;
		gbc_jComboBoxTarefa.gridx = 0;
		gbc_jComboBoxTarefa.gridy = 1;
		
		try {
			formatDate = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		componentDataInicio = new JDateChooser(new Date());		
		GridBagConstraints gbc_panelDataInicio = new GridBagConstraints();
		gbc_panelDataInicio.insets = new Insets(0, 0, 5, 5);
		gbc_panelDataInicio.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelDataInicio.gridx = 2;
		gbc_panelDataInicio.gridy = 1;
		add(componentDataInicio, gbc_panelDataInicio);
		
		componentDataFinal =  new JDateChooser(new Date());		
		GridBagConstraints gbc_panelDataFim = new GridBagConstraints();
		gbc_panelDataFim.insets = new Insets(0, 0, 5, 5);
		gbc_panelDataFim.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelDataFim.gridx = 3;
		gbc_panelDataFim.gridy = 1;
		add(componentDataFinal, gbc_panelDataFim);
		
		jTextFieldCargaHoraria = new JTextField();
		jTextFieldCargaHoraria.setDocument(new MeuDocument());
		GridBagConstraints gbc_jTextFieldCargaHoraria = new GridBagConstraints();
		gbc_jTextFieldCargaHoraria.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldCargaHoraria.fill = GridBagConstraints.BOTH;
		gbc_jTextFieldCargaHoraria.gridx = 4;
		gbc_jTextFieldCargaHoraria.gridy = 1;
		add(jTextFieldCargaHoraria, gbc_jTextFieldCargaHoraria);
		jTextFieldCargaHoraria.setColumns(10);
		
		jComboBoxUnidadeMedida = new JComboBox();
		jComboBoxUnidadeMedida.addFocusListener(new FocusListener(){  

			@Override 
		    public void focusGained(FocusEvent arg0) {  
				populaComboBoxUnidadeMedida();
		    }  

			@Override
		    public void focusLost(FocusEvent arg0){               
		       
		    }	
		      
		});  
		GridBagConstraints gbc_jComboBoxUnidadeEsforco = new GridBagConstraints();
		gbc_jComboBoxUnidadeEsforco.gridwidth = 2;
		gbc_jComboBoxUnidadeEsforco.insets = new Insets(0, 0, 5, 0);
		gbc_jComboBoxUnidadeEsforco.fill = GridBagConstraints.BOTH;
		gbc_jComboBoxUnidadeEsforco.gridx = 5;
		gbc_jComboBoxUnidadeEsforco.gridy = 1;
		add(jComboBoxUnidadeMedida, gbc_jComboBoxUnidadeEsforco);
		
		jButtonCadastrar = new JButton("Salvar");
		jButtonCadastrar.setIcon(new Icone().getSalvar());
		jButtonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonCadastrarActionPerformed(arg0);
			}
		});
		GridBagConstraints gbc_jButtonCadastrar = new GridBagConstraints();
		gbc_jButtonCadastrar.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonCadastrar.gridx = 6;
		gbc_jButtonCadastrar.gridy = 2;
		add(jButtonCadastrar, gbc_jButtonCadastrar);
		
		ItemListener listener = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemListenerStateChaged(e);
			}
		};	
		
		jComboBoxTarefa.addItemListener(listener);
		populaComboBoxTarefa();
				
		
		add(jComboBoxTarefa, gbc_jComboBoxTarefa);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 7;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);
		
		columns = new Vector();
		data = new Vector();
		
		columns.add("Tarefa");
		columns.add("Data In\u00EDcio");
		columns.add("Data Fim");
		columns.add("Carga Hor\u00E1ria");
		columns.add("Unidade de Medida");
		tableModel = MyTableModel.modelCronograma(columns, data);
		
		table = new JTable(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jTableMouseClicked(arg0);
			}
		});
		DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer(); 
		cellRender.setHorizontalAlignment(SwingConstants.CENTER); 
		table.getColumnModel().getColumn(1).setCellRenderer(cellRender);
		table.getColumnModel().getColumn(2).setCellRenderer(cellRender);
		table.getColumnModel().getColumn(3).setCellRenderer(cellRender);
		table.getColumnModel().getColumn(4).setCellRenderer(cellRender);
		scrollPane.setViewportView(table);
		populaComboBoxUnidadeMedida();
		populaTabela();
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

	protected void jTableMouseClicked(MouseEvent arg0) {
		if(arg0.getClickCount() == 2){
			if(tableModel.getValueAt(table.getSelectedRow(), 0) instanceof Tarefa)
				jComboBoxTarefa.setSelectedItem((Tarefa)tableModel.getValueAt(table.getSelectedRow(), 0));
			if(tableModel.getValueAt(table.getSelectedRow(), 0) instanceof Marco)
				jComboBoxTarefa.setSelectedItem((Marco)tableModel.getValueAt(table.getSelectedRow(), 0));
		}
	}

	private void populaTabela(){
		tableModel.setNumRows(0);
		for (Tarefa tarefa : consultaTarefas()) {
			tableModel.addRow(new Object[]{tarefa,tarefa.getInicioPrevisto(), tarefa.getFimPrevisto(),tarefa.getCargaHoraria(), tarefa.getUndCargaHoraria() });
		}
		for (Marco marco : consultaMarcos()) {
			tableModel.addRow(new Object[]{marco,marco.getInicioPrevisto(), marco.getFimPrevisto(),marco.getCargaHoraria(), marco.getUndCargaHoraria() });
		}
		this.repaint();
	}
	
	private void populaComboBoxUnidadeMedida() {
		jComboBoxUnidadeMedida.removeAllItems();
		Campo campo = CampoDAO.findByName("Unidade de Medida (Carga Hor\u00E1ria)");
		jComboBoxUnidadeMedida.addItem("Selecione...");
		for (Tipo tipo : campo.getTipo()) {
			jComboBoxUnidadeMedida.addItem(tipo.getNome());
		}		
	}

	protected void jButtonCadastrarActionPerformed(ActionEvent arg0) {
		try {
			if(jComboBoxUnidadeMedida.getSelectedIndex()>0){
				if(jComboBoxTarefa.getSelectedItem() instanceof Tarefa){
					Tarefa tarefa = TarefaDAO.findById(((Tarefa) jComboBoxTarefa.getSelectedItem()).getId());
					tarefa.setInicioPrevisto(trata(componentDataInicio.getDate()));
					tarefa.setFimPrevisto(trata(componentDataFinal.getDate()));
					tarefa.setCargaHoraria(jTextFieldCargaHoraria.getText());
					tarefa.setUndCargaHoraria((String)jComboBoxUnidadeMedida.getSelectedItem());
					TarefaController.saveTarefa(tarefa);
				}
				if(jComboBoxTarefa.getSelectedItem() instanceof Marco){
					Marco marco = MarcoDAO.findById(((Marco) jComboBoxTarefa.getSelectedItem()).getId());
					marco.setInicioPrevisto(trata(componentDataInicio.getDate()));
					marco.setFimPrevisto(trata(componentDataFinal.getDate()));
					marco.setCargaHoraria(jTextFieldCargaHoraria.getText());
					marco.setUndCargaHoraria((String)jComboBoxUnidadeMedida.getSelectedItem());
					MarcoController.saveMarco(marco);
				}
				populaTabela();
				clear();
				this.repaint();
			} else {
				JOptionPane.showMessageDialog(null, "Nenhuma Unidade de Medida foi selecionada.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (ClassCastException e) {
			JOptionPane.showMessageDialog(null, "Nenhuma tarefa ou marco foi selecionado.", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private String trata(Date date) {
		String mesC = "01";
		String diaC = "01";
		
	    int dia = date.getDate();
	    int mes = date.getMonth() + 1; // retorna um valor de mes entre 0..11
	    if(mes<10)
	    	mesC = "0"+mes;
	    else
	    	mesC = Integer.toString(mes);
	    
	    if(dia<10)
	    	diaC = "0"+dia;
	    else diaC = Integer.toString(dia);
	    	
	    int ano = date.getYear() + 1900;
	    return  diaC  +"/"+mesC+"/"+ano;
	}

	private void clear() {
		componentDataFinal.setDate(new Date());
		componentDataInicio.setDate(new Date());
		jComboBoxUnidadeMedida.setSelectedItem("Selecione...");
		jComboBoxTarefa.setSelectedItem("Selecione...");
		jTextFieldCargaHoraria.setText("");
		
	}

	protected void itemListenerStateChaged(ItemEvent e) {
		/*
		jFormattedTextFieldDataFim.setText("");
		jFormattedTextFieldDataInicio.setText("");
		jTextFieldCargaHoraria.setText("");
		*/
		
		//esse if faz o evento disparar somente quando o item é selecionado
		if(!jComboBoxTarefa.getSelectedItem().toString().equals("Selecione...")){
				if(e.getStateChange() == ItemEvent.SELECTED) {
					if(jComboBoxTarefa.getSelectedItem() instanceof Tarefa){
						Tarefa tarefa = TarefaDAO.findById( ((Tarefa) jComboBoxTarefa.getSelectedItem()).getId());
						if(tarefa!=null)
						{
							if(!(tarefa.getInicioPrevisto() == null))
								componentDataInicio.setDate(new Date(trata(tarefa.getInicioPrevisto())));
							else componentDataInicio.setDate(new Date());
							if(!(tarefa.getFimPrevisto()==null))
								componentDataFinal.setDate(new Date(trata(tarefa.getFimPrevisto())));
							else componentDataFinal.setDate(new Date());
							jTextFieldCargaHoraria.setText(tarefa.getCargaHoraria() == null ? "": tarefa.getCargaHoraria().replace(",", ""));
							jComboBoxUnidadeMedida.setSelectedItem(tarefa.getUndCargaHoraria() == null? "Selecione..." : tarefa.getUndCargaHoraria());
							this.repaint();
						}
					}
					
					if(jComboBoxTarefa.getSelectedItem() instanceof Marco){
						Marco marco = MarcoDAO.findById( ((Marco) jComboBoxTarefa.getSelectedItem()).getId());
						if(marco!=null)
						{
							if(!(marco.getInicioPrevisto() == null))
								componentDataInicio.setDate(new Date(trata(marco.getInicioPrevisto())));
							else componentDataInicio.setDate(new Date());
							if(!(marco.getFimPrevisto()==null))
								componentDataFinal.setDate(new Date(trata(marco.getFimPrevisto())));
							else componentDataFinal.setDate(new Date());
							jTextFieldCargaHoraria.setText(marco.getCargaHoraria() == null ? "": marco.getCargaHoraria().replace(",", ""));
							jComboBoxUnidadeMedida.setSelectedItem(marco.getUndCargaHoraria() == null? "Selecione..." : marco.getUndCargaHoraria());
							this.repaint();
						}
					}
				}
		}
	}
	
	private String trata(String string) {
		String dia = string.substring(0, 2);
		String mes = string.substring(3, 5);
		String ano = string.substring(6, 10);
		return ano + "/" + mes +"/"+dia;
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
}
