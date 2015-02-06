package br.ufpa.spider.pe.view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import br.ufpa.spider.pe.controller.ProblemaController;
import br.ufpa.spider.pe.model.Atividade;
import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.Iteracao;
import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.Problema;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.Tipo;
import br.ufpa.spider.pe.model.dao.CampoDAO;
import br.ufpa.spider.pe.model.dao.ProblemaDAO;
import br.ufpa.spider.pe.model.set.RedMine;
import br.ufpa.spider.pe.model.set.dao.RedMineDAO;
import br.ufpa.spider.pe.model.util.MyTableModel;

public class JPanelRevisaoProblemas extends JPanel {
	private JTextField jTextFieldTicket;
	private JTable jTable;
	private DefaultTableModel tableModel;
	private JLabel jLabelCadastrarProblemas;
	private JLabel JLabelTicket;
	
	private JLabel jLabelOrigem;
	private JComboBox jComboBoxOrigem;
	private JLabel jLabelAfetado;
	private JButton jButtonCadastrar;
	private JScrollPane scrollPane;
	private JComboBox jComboBoxAfetado;
	private JButton jButtonExcluir;
	private JToolBar toolBar;
	private JButton btnNewButton;
	private boolean editar=false;
	private Problema problema;
	
	public JPanelRevisaoProblemas() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 251, 361, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{26, 54, 27, 26, 26, 29, 102, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		jLabelCadastrarProblemas = new JLabel("Cadastrar os problemas identificados durante Monitoramento");
		GridBagConstraints gbc_jLabelCadastrarProblemas = new GridBagConstraints();
		gbc_jLabelCadastrarProblemas.anchor = GridBagConstraints.WEST;
		gbc_jLabelCadastrarProblemas.gridwidth = 2;
		gbc_jLabelCadastrarProblemas.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelCadastrarProblemas.gridx = 0;
		gbc_jLabelCadastrarProblemas.gridy = 0;
		add(jLabelCadastrarProblemas, gbc_jLabelCadastrarProblemas);
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.anchor = GridBagConstraints.WEST;
		gbc_toolBar.gridwidth = 2;
		gbc_toolBar.insets = new Insets(0, 0, 5, 5);
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 1;
		add(toolBar, gbc_toolBar);
		
		btnNewButton = new JButton("");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonRedMine();
			}
		});
		btnNewButton.setIcon(new ImageIcon(JPanelRevisaoProblemas.class.getResource("/br/ufpa/spider/pe/img/redmine.png")));
		toolBar.add(btnNewButton);
		
		JLabelTicket = new JLabel("Ticket Associado");
		GridBagConstraints gbc_JLabelTicket = new GridBagConstraints();
		gbc_JLabelTicket.anchor = GridBagConstraints.SOUTHWEST;
		gbc_JLabelTicket.insets = new Insets(0, 0, 5, 5);
		gbc_JLabelTicket.gridx = 0;
		gbc_JLabelTicket.gridy = 2;
		add(JLabelTicket, gbc_JLabelTicket);
		
		jTextFieldTicket = new JTextField();
		jTextFieldTicket.setMinimumSize(new Dimension(4, 25));
		jTextFieldTicket.setMaximumSize(new Dimension(2147483647, 25));
		jTextFieldTicket.setPreferredSize(new Dimension(4, 25));
		GridBagConstraints gbc_jTextFieldTicket = new GridBagConstraints();
		gbc_jTextFieldTicket.anchor = GridBagConstraints.SOUTH;
		gbc_jTextFieldTicket.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldTicket.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldTicket.gridx = 1;
		gbc_jTextFieldTicket.gridy = 2;
		add(jTextFieldTicket, gbc_jTextFieldTicket);
		jTextFieldTicket.setColumns(10);
		
		jLabelOrigem = new JLabel("Origem do Problema");
		GridBagConstraints gbc_jLabelOrigem = new GridBagConstraints();
		gbc_jLabelOrigem.anchor = GridBagConstraints.SOUTHWEST;
		gbc_jLabelOrigem.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelOrigem.gridx = 0;
		gbc_jLabelOrigem.gridy = 3;
		add(jLabelOrigem, gbc_jLabelOrigem);
		
		jComboBoxOrigem = new JComboBox();

		ItemListener listener = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemListenerStateChaged(e);
			}
		};
		jComboBoxOrigem.addItemListener(listener);
				GridBagConstraints gbc_jComboBoxOrigem = new GridBagConstraints();
		gbc_jComboBoxOrigem.insets = new Insets(0, 0, 5, 5);
		gbc_jComboBoxOrigem.fill = GridBagConstraints.BOTH;
		gbc_jComboBoxOrigem.gridx = 1;
		gbc_jComboBoxOrigem.gridy = 3;
		add(jComboBoxOrigem, gbc_jComboBoxOrigem);
		
		jLabelAfetado = new JLabel("Nome do Item");
		GridBagConstraints gbc_jLabelAfetado = new GridBagConstraints();
		gbc_jLabelAfetado.anchor = GridBagConstraints.SOUTHWEST;
		gbc_jLabelAfetado.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelAfetado.gridx = 0;
		gbc_jLabelAfetado.gridy = 4;
		add(jLabelAfetado, gbc_jLabelAfetado);
		
		jComboBoxAfetado = new JComboBox();
		GridBagConstraints gbc_jComboBoxAfetado = new GridBagConstraints();
		gbc_jComboBoxAfetado.insets = new Insets(0, 0, 5, 5);
		gbc_jComboBoxAfetado.fill = GridBagConstraints.BOTH;
		gbc_jComboBoxAfetado.gridx = 1;
		gbc_jComboBoxAfetado.gridy = 4;
		add(jComboBoxAfetado, gbc_jComboBoxAfetado);
		
		jButtonCadastrar = new JButton("Cadastrar");
		jButtonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonCadastrar(arg0);
			}
		});
		GridBagConstraints gbc_jButtonCadastrar = new GridBagConstraints();
		gbc_jButtonCadastrar.anchor = GridBagConstraints.SOUTHEAST;
		gbc_jButtonCadastrar.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonCadastrar.gridx = 4;
		gbc_jButtonCadastrar.gridy = 5;
		add(jButtonCadastrar, gbc_jButtonCadastrar);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 6;
		add(scrollPane, gbc_scrollPane);
		
		jTable = new JTable();
		jTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jTableMouseClicked(arg0);
			}
		});
		jTable.setBorder(new LineBorder(Color.LIGHT_GRAY));
		jTable.setPreferredScrollableViewportSize(new Dimension(700, 16));	
		 Vector<String>  columns = new Vector<String>();
		 Vector<String>  data = new Vector<String>();
		 columns.add("Ticket");
		 columns.add("Origem");
		 columns.add("Afetado(a)");
			
	     tableModel = MyTableModel.modelRevisaoProblemas(columns, data);
		 jTable.setModel(tableModel);		 
		 jTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		 jTable.getColumnModel().getColumn(1).setPreferredWidth(250);
		 jTable.getColumnModel().getColumn(2).setPreferredWidth(250);
		 jTable.getTableHeader().setReorderingAllowed(false); 
			
		scrollPane.setViewportView(jTable);
		populaTabela();
		scrollPane.setViewportView(jTable);
		
		jButtonExcluir = new JButton("Excluir");
		jButtonExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonExcluir(arg0);
			}
		});
		GridBagConstraints gbc_jButtonExcluir = new GridBagConstraints();
		gbc_jButtonExcluir.anchor = GridBagConstraints.SOUTHEAST;
		gbc_jButtonExcluir.gridx = 4;
		gbc_jButtonExcluir.gridy = 7;
		add(jButtonExcluir, gbc_jButtonExcluir);
		populaComboBox();
	}
	protected void jButtonRedMine() {
		if(!RedMineDAO.findAll().isEmpty() || !(RedMineDAO.findAll() == null)){
			for (RedMine redMine : RedMineDAO.findAll()) {
				Desktop desktop = null;  
				desktop = Desktop.getDesktop();  
				URI uri = null;  
				try {  
				     uri = new URI(redMine.getUrlFerramenta());  
				     desktop.browse(uri);  
				}  
				catch(IOException ioe) {  
				     ioe.printStackTrace();  
				}  
				catch(URISyntaxException use) {  
				     use.printStackTrace();  
				}  
			}
		} else JOptionPane.showMessageDialog(null, "A ferramenta Spider-MPlan ainda n\u00e3o est\u00e1 configurado.");
			
	}
	protected void jTableMouseClicked(MouseEvent arg0) {
		if(arg0.getClickCount() == 2){
			jButtonEditar(null);
		}
		
	}
	protected void itemListenerStateChaged(ItemEvent e) {		
		String origem = jComboBoxOrigem.getSelectedItem().toString();
		jComboBoxAfetado.removeAllItems();
		jComboBoxAfetado.addItem("Selecione...");
		if(origem.equals("Processo")){
			jComboBoxAfetado.addItem(JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso());
		}
		else if(origem.equals("Fase")){ 
			for (Fase fase : JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getFase()) {
				jComboBoxAfetado.addItem(fase);
			}
		}
		else if(origem.equals("Marco")){
			for (Fase fase : JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getFase()) {
				for(Marco marco: fase.getMarco())
					jComboBoxAfetado.addItem(marco);
			}
		}
		else if(origem.equals("Iteraç\u00e3o")){
			for (Fase fase : JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getFase()) {
				for(Iteracao iteracao: fase.getIteracao())
					jComboBoxAfetado.addItem(iteracao);
			}
		}
		else if(origem.equals("Atividade")){
			for (Fase fase : JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getFase()) {
				for(Iteracao iteracao: fase.getIteracao()){
					for (Atividade atividade : iteracao.getAtividade()) {
						jComboBoxAfetado.addItem(atividade);
					}	
				}
			}
		}
		else if(origem.equals("Tarefa")){
			for (Fase fase : JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getFase()) {
				for(Iteracao iteracao: fase.getIteracao()){
					for (Tarefa tarefa : iteracao.getTarefa()) {
						jComboBoxAfetado.addItem(tarefa);
					}	
					for (Atividade atividade : iteracao.getAtividade()) {
						for (Tarefa tarefa : atividade.getTarefa()) {
							jComboBoxAfetado.addItem(tarefa);
						}	
					}	
				}
			}
		}
		else if(origem.equals("Recurso Humano")){
			for (Humano humano : JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getHumano()) {
				jComboBoxAfetado.addItem(humano);
			}
		}
		else if(origem.equals("Recurso de Hardware")){
			for (Tipo tipo : CampoDAO.findByName("Recurso de Hardware").getTipo()) {
				jComboBoxAfetado.addItem(tipo);
			}
		}
		else if(origem.equals("Recurso de Software")){
			for (Tipo tipo : CampoDAO.findByName("Software").getTipo()) {
				jComboBoxAfetado.addItem(tipo);
			}
		}
		
	}
	protected void jButtonExcluir(ActionEvent arg0) {
		int i = JOptionPane.showConfirmDialog(null ,"Deseja excluir a Alocaç\u00e3o selecionada?", 
				"Alocaç\u00e3o",JOptionPane.YES_NO_OPTION); 
		if (i == JOptionPane.YES_OPTION ) { 
			problema = (Problema) jTable.getValueAt(jTable.getSelectedRow(), 0);
			ProblemaDAO.remove(problema);
			JOptionPane.showMessageDialog(null, "Problema excluído.");
			populaTabela();
		}
	}
	protected void jButtonEditar(ActionEvent arg0) {
		problema = (Problema) jTable.getValueAt(jTable.getSelectedRow(), 0);	
		String afetado =  (String) jTable.getValueAt(jTable.getSelectedRow(), 2);	
		jTextFieldTicket.setText(problema.getTicket());
		jComboBoxOrigem.setSelectedItem(problema.getOrigem());
		jComboBoxAfetado.setSelectedItem(afetado);
		editar = true;
	}
	protected void jButtonCadastrar(ActionEvent arg0) {
		if(!editar)
			problema = new Problema();
		problema.setOrigem(jComboBoxOrigem.getSelectedItem().toString());
		problema.setAfetado(jComboBoxAfetado.getSelectedItem().toString());
		problema.setTicket(jTextFieldTicket.getText());		
		ProblemaController.saveProblema(problema);	
		editar = false;
		
		jTextFieldTicket.setText("");
		jComboBoxAfetado.setSelectedItem("Selecione...");
		jComboBoxOrigem.setSelectedItem("Selecione...");
		
		populaTabela();
	}
	private void populaComboBox() {
		jComboBoxOrigem.removeAllItems();
		jComboBoxOrigem.addItem("Selecione...");
		jComboBoxOrigem.addItem("Processo");
		jComboBoxOrigem.addItem("Fase");
		jComboBoxOrigem.addItem("Marco");
		jComboBoxOrigem.addItem("Iteraç\u00e3o");
		jComboBoxOrigem.addItem("Atividade");
		jComboBoxOrigem.addItem("Tarefa");
		jComboBoxOrigem.addItem("Recurso Humano");
		jComboBoxOrigem.addItem("Recurso de Hardware");
		jComboBoxOrigem.addItem("Recurso de Software");		
	}

	private void populaTabela() {
		tableModel.setNumRows(0);
		for (Problema problema : ProblemaDAO.findAll()) {
			tableModel.addRow(new Object[]{problema, problema.getOrigem(), problema.getAfetado()});
		}
		jTable.validate();
		jTable.repaint();
	}

}
