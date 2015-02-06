package br.ufpa.spider.pe.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.persistence.JoinColumn;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.ufpa.spider.pe.controller.PlanoComunicacaoController;
import br.ufpa.spider.pe.model.Campo;
import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.PlanoComunicacao;
import br.ufpa.spider.pe.model.PlanoMedicao;
import br.ufpa.spider.pe.model.PoliticaOrganizacional;
import br.ufpa.spider.pe.model.ProdutoTrabalho;
import br.ufpa.spider.pe.model.Stakeholder;
import br.ufpa.spider.pe.model.Tipo;
import br.ufpa.spider.pe.model.dao.CampoDAO;
import br.ufpa.spider.pe.model.dao.FaseDAO;
import br.ufpa.spider.pe.model.dao.HumanoDAO;
import br.ufpa.spider.pe.model.dao.PlanoComunicacaoDAO;
import br.ufpa.spider.pe.model.dao.ProcessoDAO;
import br.ufpa.spider.pe.model.dao.TipoDAO;
import br.ufpa.spider.pe.model.util.MyTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class JPanelPlanoComunicacao extends JPanel {
	private JTable table;
	private DefaultTableModel tableModel;
	private JComboBox jComboBoxArtefato;
	private JComboBox jComboBoxFormaComunicacao;
	private JComboBox jComboBoxQuandoComunicado;
	private JComboBox jComboBoxTipoEmissor;
	private JComboBox jComboBoxTipoReceptor;
	private JList jListEmissorA;
	private JButton jButtonListEmissorAdd;
	private JButton jButtonRemoveEmissor;
	private JList jListEmissorB;
	private JList jListReceptorA;
	private JButton button_1;
	private JButton jButtonRemoveReceptor;
	private JList jListReceptorB;
	private JScrollPane scrollPane;
	private JButton jButtonExcluir;
	private JButton jButtonAdicionar;
	private JComboBox jComboBoxFase;
	private DefaultListModel listaEmissorA = new DefaultListModel(); 
	private DefaultListModel listaEmissorB = new DefaultListModel();
	private DefaultListModel listaReceptorA = new DefaultListModel();
	private DefaultListModel listaReceptorB = new DefaultListModel();
	private ProdutoTrabalho produtoTrabalho;
	private Fase fase;
	protected boolean editar;
	private PlanoComunicacao planoComunicacao = new PlanoComunicacao();

	/**
	 * Create the panel.
	 */
	public JPanelPlanoComunicacao() {
		setBorder(new EmptyBorder(10, 10, 0, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 39, 107, 107, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 2.0, 0.0, 2.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridwidth = 3;
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		panel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{169, 35, 172, 0};
		gbl_panel_3.rowHeights = new int[]{14, 20, 14, 20, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblArtefato = new JLabel("Artefato");
		GridBagConstraints gbc_lblArtefato = new GridBagConstraints();
		gbc_lblArtefato.anchor = GridBagConstraints.WEST;
		gbc_lblArtefato.insets = new Insets(0, 0, 5, 5);
		gbc_lblArtefato.gridx = 0;
		gbc_lblArtefato.gridy = 0;
		panel_3.add(lblArtefato, gbc_lblArtefato);
		
		JLabel lblFormaDeComunicao = new JLabel("Forma de Comunica\u00E7\u00E3o");
		GridBagConstraints gbc_lblFormaDeComunicao = new GridBagConstraints();
		gbc_lblFormaDeComunicao.anchor = GridBagConstraints.WEST;
		gbc_lblFormaDeComunicao.insets = new Insets(0, 0, 5, 0);
		gbc_lblFormaDeComunicao.gridx = 2;
		gbc_lblFormaDeComunicao.gridy = 0;
		panel_3.add(lblFormaDeComunicao, gbc_lblFormaDeComunicao);
		
		jComboBoxArtefato = new JComboBox();
		GridBagConstraints gbc_jComboBoxArtefato = new GridBagConstraints();
		gbc_jComboBoxArtefato.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxArtefato.insets = new Insets(0, 0, 5, 5);
		gbc_jComboBoxArtefato.gridx = 0;
		gbc_jComboBoxArtefato.gridy = 1;
		panel_3.add(jComboBoxArtefato, gbc_jComboBoxArtefato);
		
		jComboBoxFormaComunicacao = new JComboBox();
		jComboBoxFormaComunicacao.addFocusListener(new FocusListener(){  

			@Override 
		    public void focusGained(FocusEvent arg0) { 
				populaComboBoxFormaComunicacao();
		    }  

			@Override
		    public void focusLost(FocusEvent arg0){  
		    }
		      
		});  
		GridBagConstraints gbc_jComboBoxFormaComunicacao = new GridBagConstraints();
		gbc_jComboBoxFormaComunicacao.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxFormaComunicacao.insets = new Insets(0, 0, 5, 0);
		gbc_jComboBoxFormaComunicacao.gridx = 2;
		gbc_jComboBoxFormaComunicacao.gridy = 1;
		panel_3.add(jComboBoxFormaComunicacao, gbc_jComboBoxFormaComunicacao);
		
		JLabel lblQuandoComunicado = new JLabel("Quando \u00E9 Comunicado");
		GridBagConstraints gbc_lblQuandoComunicado = new GridBagConstraints();
		gbc_lblQuandoComunicado.anchor = GridBagConstraints.WEST;
		gbc_lblQuandoComunicado.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuandoComunicado.gridx = 0;
		gbc_lblQuandoComunicado.gridy = 2;
		panel_3.add(lblQuandoComunicado, gbc_lblQuandoComunicado);
		
		JLabel lblFase = new JLabel("Fase");
		GridBagConstraints gbc_lblFase = new GridBagConstraints();
		gbc_lblFase.anchor = GridBagConstraints.WEST;
		gbc_lblFase.insets = new Insets(0, 0, 5, 0);
		gbc_lblFase.gridx = 2;
		gbc_lblFase.gridy = 2;
		panel_3.add(lblFase, gbc_lblFase);
		
		jComboBoxQuandoComunicado = new JComboBox();
		GridBagConstraints gbc_jComboBoxQuandoComunicado = new GridBagConstraints();
		gbc_jComboBoxQuandoComunicado.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxQuandoComunicado.insets = new Insets(0, 0, 0, 5);
		gbc_jComboBoxQuandoComunicado.gridx = 0;
		gbc_jComboBoxQuandoComunicado.gridy = 3;
		panel_3.add(jComboBoxQuandoComunicado, gbc_jComboBoxQuandoComunicado);
		
		jComboBoxFase = new JComboBox();
		GridBagConstraints gbc_jComboBoxFase = new GridBagConstraints();
		gbc_jComboBoxFase.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxFase.gridx = 2;
		gbc_jComboBoxFase.gridy = 3;
		panel_3.add(jComboBoxFase, gbc_jComboBoxFase);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EmptyBorder(10, 0, 10, 0));
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridwidth = 6;
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 1;
		panel.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_4.rowHeights = new int[]{0, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JLabel lblEmissor = new JLabel("Tipo de Emissor");
		GridBagConstraints gbc_lblEmissor = new GridBagConstraints();
		gbc_lblEmissor.anchor = GridBagConstraints.WEST;
		gbc_lblEmissor.insets = new Insets(0, 0, 0, 5);
		gbc_lblEmissor.gridx = 0;
		gbc_lblEmissor.gridy = 0;
		panel_4.add(lblEmissor, gbc_lblEmissor);
		
		jComboBoxTipoEmissor = new JComboBox();
		jComboBoxTipoEmissor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionListenerTipoEmissor();
			}
		});
		GridBagConstraints gbc_jComboBoxTipoEmissor = new GridBagConstraints();
		gbc_jComboBoxTipoEmissor.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxTipoEmissor.insets = new Insets(0, 0, 0, 5);
		gbc_jComboBoxTipoEmissor.gridx = 1;
		gbc_jComboBoxTipoEmissor.gridy = 0;
		panel_4.add(jComboBoxTipoEmissor, gbc_jComboBoxTipoEmissor);
		
		JLabel lblTipoDeReceptor = new JLabel("Tipo de Receptor");
		GridBagConstraints gbc_lblTipoDeReceptor = new GridBagConstraints();
		gbc_lblTipoDeReceptor.anchor = GridBagConstraints.EAST;
		gbc_lblTipoDeReceptor.insets = new Insets(0, 0, 0, 5);
		gbc_lblTipoDeReceptor.gridx = 2;
		gbc_lblTipoDeReceptor.gridy = 0;
		panel_4.add(lblTipoDeReceptor, gbc_lblTipoDeReceptor);
		
		jComboBoxTipoReceptor = new JComboBox();
		jComboBoxTipoReceptor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionListenerTipoReceptor();
			}
		});
		GridBagConstraints gbc_jComboBoxTipoReceptor = new GridBagConstraints();
		gbc_jComboBoxTipoReceptor.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxTipoReceptor.gridx = 3;
		gbc_jComboBoxTipoReceptor.gridy = 0;
		panel_4.add(jComboBoxTipoReceptor, gbc_jComboBoxTipoReceptor);
		
		JLabel lblEmissores = new JLabel("Emissor(es)");
		GridBagConstraints gbc_lblEmissores = new GridBagConstraints();
		gbc_lblEmissores.anchor = GridBagConstraints.WEST;
		gbc_lblEmissores.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmissores.gridx = 0;
		gbc_lblEmissores.gridy = 2;
		panel.add(lblEmissores, gbc_lblEmissores);
		
		JLabel lblReceptores = new JLabel("Receptor(es)");
		GridBagConstraints gbc_lblReceptores = new GridBagConstraints();
		gbc_lblReceptores.insets = new Insets(0, 0, 5, 5);
		gbc_lblReceptores.gridx = 3;
		gbc_lblReceptores.gridy = 2;
		panel.add(lblReceptores, gbc_lblReceptores);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 3;
		panel.add(scrollPane_1, gbc_scrollPane_1);
		
		jListEmissorA = new JList();
		scrollPane_1.setViewportView(jListEmissorA);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 3;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		jButtonListEmissorAdd = new JButton(">");
		jButtonListEmissorAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(jComboBoxTipoEmissor.getSelectedItem().toString().equals("Recurso Humano"))
					jButtonAdicionarEmissorHumano();	
				else if (jComboBoxTipoEmissor.getSelectedItem().toString().equals("Stakeholder"))
					jButtonAdicionarListaEmissorStakeholder();	
			}
		});
		GridBagConstraints gbc_jButtonListEmissorAdd = new GridBagConstraints();
		gbc_jButtonListEmissorAdd.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonListEmissorAdd.gridx = 0;
		gbc_jButtonListEmissorAdd.gridy = 0;
		panel_1.add(jButtonListEmissorAdd, gbc_jButtonListEmissorAdd);
		
		jButtonRemoveEmissor = new JButton("<");
		jButtonRemoveEmissor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jComboBoxTipoEmissor.getSelectedItem().toString().equals("Recurso Humano"))
					jButtonRemoveEmissorHumano();
				else if (jComboBoxTipoEmissor.getSelectedItem().toString().equals("Stakeholder"))
					jButtonRemoveEmissorStakeholder();	
			}
		});
		GridBagConstraints gbc_jButtonRemoveEmissor = new GridBagConstraints();
		gbc_jButtonRemoveEmissor.gridx = 0;
		gbc_jButtonRemoveEmissor.gridy = 1;
		panel_1.add(jButtonRemoveEmissor, gbc_jButtonRemoveEmissor);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_2.gridx = 2;
		gbc_scrollPane_2.gridy = 3;
		panel.add(scrollPane_2, gbc_scrollPane_2);
		
		jListEmissorB = new JList();
		scrollPane_2.setViewportView(jListEmissorB);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_3.gridx = 3;
		gbc_scrollPane_3.gridy = 3;
		panel.add(scrollPane_3, gbc_scrollPane_3);
		
		jListReceptorA = new JList();
		scrollPane_3.setViewportView(jListReceptorA);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.gridx = 4;
		gbc_panel_2.gridy = 3;
		panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		button_1 = new JButton(">");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jComboBoxTipoReceptor.getSelectedItem().toString().equals("Recurso Humano"))
					jButtonAdicionarReceptorHumano();	
				else if (jComboBoxTipoReceptor.getSelectedItem().toString().equals("Stakeholder"))
					jButtonAdicionarListaReceptorStakeholder();	
			}
		});
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 5, 0);
		gbc_button_1.gridx = 0;
		gbc_button_1.gridy = 0;
		panel_2.add(button_1, gbc_button_1);
		
		jButtonRemoveReceptor = new JButton("<");
		jButtonRemoveReceptor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jComboBoxTipoReceptor.getSelectedItem().toString().equals("Recurso Humano"))
					jButtonRemoveReceptorHumano();	
				else if (jComboBoxTipoReceptor.getSelectedItem().toString().equals("Stakeholder"))
					jButtonRemoveReceptorStakeholder();	
				
			}
		});
		GridBagConstraints gbc_jButtonRemoveReceptor = new GridBagConstraints();
		gbc_jButtonRemoveReceptor.gridx = 0;
		gbc_jButtonRemoveReceptor.gridy = 1;
		panel_2.add(jButtonRemoveReceptor, gbc_jButtonRemoveReceptor);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_4 = new GridBagConstraints();
		gbc_scrollPane_4.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_4.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_4.gridx = 5;
		gbc_scrollPane_4.gridy = 3;
		panel.add(scrollPane_4, gbc_scrollPane_4);
		
		jListReceptorB = new JList();
		scrollPane_4.setViewportView(jListReceptorB);
		
		jButtonAdicionar = new JButton("Adicionar");
		jButtonAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonAdicionar(arg0);
			}
		});
		GridBagConstraints gbc_jButtonAdicionar = new GridBagConstraints();
		gbc_jButtonAdicionar.anchor = GridBagConstraints.EAST;
		gbc_jButtonAdicionar.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonAdicionar.gridx = 5;
		gbc_jButtonAdicionar.gridy = 4;
		panel.add(jButtonAdicionar, gbc_jButtonAdicionar);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 5;
		panel.add(scrollPane, gbc_scrollPane);
		
		 table = new JTable();
		 Vector<String>  columns = new Vector<String>();
		 Vector<String>  data = new Vector<String>();
		 
		 columns .add("Artefato");
		 columns .add("Emissor");
		 columns .add("Receptor");
	     columns.add("Forma de Comunica\u00E7\u00E3o");
			
		 tableModel = MyTableModel.modelPlanoComunicacao(columns, data);
		 table.setModel(tableModel);
		 
		 table.getColumnModel().getColumn(0).setPreferredWidth(500);
		 table.getColumnModel().getColumn(1).setPreferredWidth(500);
		 table.getColumnModel().getColumn(2).setPreferredWidth(500);
		 table.getColumnModel().getColumn(3).setPreferredWidth(500);
			table.getTableHeader().setReorderingAllowed(false); 
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jTableMouseCliked(arg0);
			}
		});
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 5;
		gbc_panel_5.gridy = 6;
		panel.add(panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{0, 0, 0};
		gbl_panel_5.rowHeights = new int[]{23, 0};
		gbl_panel_5.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		jButtonExcluir = new JButton("Excluir");
		jButtonExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonExcluir();
			}
		});
		GridBagConstraints gbc_jButtonExcluir = new GridBagConstraints();
		gbc_jButtonExcluir.anchor = GridBagConstraints.WEST;
		gbc_jButtonExcluir.gridx = 1;
		gbc_jButtonExcluir.gridy = 0;
		panel_5.add(jButtonExcluir, gbc_jButtonExcluir);
		
		populaComboBoxArtefato();
		populaComboBoxFormaComunicacao();
		populaComboBoxQuandoComunicado();
		populaComboBoxFase();
		populaComboBoxEmissor();
		populaComboboxReceptor();
		populaTabela();
	}
	

	protected void jTableMouseCliked(MouseEvent arg0) {
		if(arg0.getClickCount()==2)
			jButtonEditar();
	}


	protected void jButtonEditar() {
			planoComunicacao =  (PlanoComunicacao) tableModel.getValueAt(table.getSelectedRow(), 0);
			jComboBoxArtefato.setSelectedItem(planoComunicacao.getProdutoTrabalho());
			jComboBoxFase.setSelectedItem(planoComunicacao.getFase());
			jComboBoxFormaComunicacao.setSelectedItem(TipoDAO.findByName(planoComunicacao.getForma()));
			jComboBoxQuandoComunicado.setSelectedItem(planoComunicacao.getQuando_comunicado());	
			trataTipoEmissor(planoComunicacao.getEmissor());
			trataTipoReceptor(planoComunicacao.getReceptor());
	}

	private void trataTipoReceptor(String receptor) {		
		ArrayList<Humano> humanos = new ArrayList<Humano>();
		ArrayList<Stakeholder> stakeHolders = new ArrayList<Stakeholder>();
		
		boolean human = false;
		
		for (Humano humano : JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getHumano()) {
				if(receptor.contains(humano.getNome()))
					{ humanos.add(humano);	human = true; }				
		}
		for (Stakeholder stakeholder : JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getStakeHolder()) {
			if(receptor.contains(stakeholder.getNome()))
					{ stakeHolders.add(stakeholder); human = false; }
		}
		
		if(human){
			jComboBoxTipoReceptor.setSelectedItem("Recurso Humano");
			for (Humano humano : humanos) {
				listaReceptorB.addElement(humano);
				listaReceptorA.removeElement(humano);
				jListReceptorA.setModel(listaReceptorA);
				jListReceptorB.setModel(listaReceptorB);
			}
		} else {
			jComboBoxTipoReceptor.setSelectedItem("Stakeholder");
			for (Stakeholder stakeHolder : stakeHolders) {
				listaReceptorB.addElement(stakeHolder);
				jListReceptorB.setModel(listaReceptorB);
				listaReceptorA.removeElement(stakeHolder);
				jListReceptorA.setModel(listaReceptorA);
			}
		}
		
	}


	private void trataTipoEmissor(String emissor) {		
		ArrayList<Humano> humanos = new ArrayList<Humano>();
		ArrayList<Stakeholder> stakeHolders = new ArrayList<Stakeholder>();
		boolean human = false;
		
		for (Humano humano : JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getHumano()) {
				if(emissor.contains(humano.getNome()))
					{ humanos.add(humano);	human = true; }				
		}
		for (Stakeholder stakeholder : JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getStakeHolder()) {
			if(emissor.contains(stakeholder.getNome()))
					{ stakeHolders.add(stakeholder); human = false; }
		}
		
		if(human){
			jComboBoxTipoEmissor.setSelectedItem("Recurso Humano");
			for (Humano humano : humanos) {
				listaEmissorB.addElement(humano);
				listaEmissorA.removeElement(humano);
				jListEmissorA.setModel(listaEmissorA);
				jListEmissorB.setModel(listaEmissorB);
			}
		} else {
			jComboBoxTipoEmissor.setSelectedItem("Stakeholder");
			for (Stakeholder stakeHolder : stakeHolders) {
				listaEmissorB.addElement(stakeHolder);
				listaEmissorA.removeElement(stakeHolder);
				jListEmissorA.setModel(listaEmissorA);
				jListEmissorB.setModel(listaEmissorB);
			}
		}
	}


	protected void jButtonExcluir() {	
		int i = JOptionPane.showConfirmDialog(null ,"Deseja excluir a Aloca\u00e7\u00e3o selecionada?", 
				"Aloca\u00e7\u00e3o",JOptionPane.YES_NO_OPTION); 
		if (i == JOptionPane.YES_OPTION ) { 
			if(!jComboBoxFase.toString().equals("Selecione...")){
				planoComunicacao =  (PlanoComunicacao) tableModel.getValueAt(table.getSelectedRow(), 0);				
				PlanoComunicacaoDAO.removePlanoComunicacao(planoComunicacao);			
				Fase fase = planoComunicacao.getFase();
				fase.getPlanoComunicacao().remove(planoComunicacao);
				FaseDAO.updateFase(fase);
				JOptionPane.showMessageDialog(null, "Plano de Comunica\u00e7\u00e3o excluído.");
				populaTabela();
			}
			clear();
		}
	}


	private void clear() {
		jComboBoxArtefato.setSelectedItem("Selecione...");
		jComboBoxFase.setSelectedItem("Selecione...");
		jComboBoxFormaComunicacao.setSelectedItem("Selecione...");
		jComboBoxQuandoComunicado.setSelectedItem("Selecione...");	
		jComboBoxTipoEmissor.setSelectedItem("Selecione...");
		jComboBoxTipoEmissor.setSelectedItem("Selecione");	
		listaEmissorA.removeAllElements();
		listaEmissorB.removeAllElements();
		listaReceptorA.removeAllElements();
		listaReceptorB.removeAllElements();
	}


	private void populaTabela() {
		tableModel.setNumRows(0);
		for (Fase fase : JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getFase()) {
			for (PlanoComunicacao planoComunicacao : fase.getPlanoComunicacao()) {
				tableModel.addRow(new Object[]{ planoComunicacao, planoComunicacao.getEmissor(), planoComunicacao.getReceptor(), planoComunicacao.getForma() });
			}			
		}
	}


	protected void actionListenerTipoReceptor() {
		if(jComboBoxTipoReceptor.getSelectedItem().toString().equals("Recurso Humano"))
			populaJListReceptorHumano();	
		else if (jComboBoxTipoReceptor.getSelectedItem().toString().equals("Stakeholder"))
			populaJListReceptorStakeholder();
		
	}

	private void populaJListReceptorStakeholder() {
		listaReceptorB.removeAllElements();
		listaReceptorA.removeAllElements();
		for (Stakeholder stakeHolder : JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getStakeHolder()) {
			listaReceptorA.addElement(stakeHolder);  
		}
		jListReceptorA.setModel(listaReceptorA);  
	}


	protected void actionListenerTipoEmissor() {
		if(jComboBoxTipoEmissor.getSelectedItem().toString().equals("Recurso Humano"))
			populaJListEmissorHumano();	
		else if (jComboBoxTipoEmissor.getSelectedItem().toString().equals("Stakeholder"))
			populaJListEmissorStakeholder();
		
	}


	private void populaJListEmissorStakeholder() {
		listaEmissorB.removeAllElements();
		listaEmissorA.removeAllElements();
		for (Stakeholder stakeHolder : JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getStakeHolder()) {
			listaEmissorA.addElement(stakeHolder);  
		}
		jListEmissorA.setModel(listaEmissorA);  
		
	}


	protected void jButtonAdicionar(ActionEvent arg0) {
		if(!jComboBoxArtefato.getSelectedItem().toString().equals("Selecione...")){
		if(!jComboBoxFase.getSelectedItem().toString().equals("Selecione...")){
		if(!jComboBoxQuandoComunicado.getSelectedItem().equals("Selecione...")){
		if(!jComboBoxFormaComunicacao.getSelectedItem().equals("Selecione...")){
		if(!jComboBoxTipoEmissor.getSelectedItem().equals(("Selecione..."))){
		if(!jComboBoxTipoReceptor.getSelectedItem().equals(("Selecione..."))){	
		if(!(listaEmissorB.size() == 0)){
		if(!(listaReceptorB.size() == 0)){
			if(editar)
			{
				 planoComunicacao = (PlanoComunicacao) tableModel.getValueAt(table.getSelectedRow(), 0);
			} 
			else
			{
				planoComunicacao = new PlanoComunicacao();
			}

				produtoTrabalho =   (ProdutoTrabalho) jComboBoxArtefato.getSelectedItem();		
				
			   
				planoComunicacao.setEmissor(getEmissor());
				planoComunicacao.setReceptor(getReceptor());
				planoComunicacao.setForma(jComboBoxFormaComunicacao.getSelectedItem().toString());
				planoComunicacao.setQuando_comunicado(jComboBoxQuandoComunicado.getSelectedItem().toString());
				planoComunicacao.setFase((Fase) jComboBoxFase.getSelectedItem());		
				planoComunicacao.setProdutoTrabalho(produtoTrabalho);		
				PlanoComunicacaoController.savePlanoComunicacao(planoComunicacao);
				
				Fase fase = (Fase) jComboBoxFase.getSelectedItem();
				
				if(!editar)
					fase.getPlanoComunicacao().add(planoComunicacao);
				
				FaseDAO.updateFase(fase);

				clear();
				populaTabela();
				
				editar = false;
				planoComunicacao = null;
		} else JOptionPane.showMessageDialog(null, "\u00c9 obrigat\u00f3rio que haja pelo menos um emissor.");
		} else JOptionPane.showMessageDialog(null, "\u00c9 obrigat\u00f3rio que haja pelo menos um receptor.");
		} else JOptionPane.showMessageDialog(null, "Selecione um tipo de receptor.");
		} else JOptionPane.showMessageDialog(null, "Selecione um tipo de emissor.");
		} else JOptionPane.showMessageDialog(null, "Selecione uma forma de comunica\u00e7\u00e3o.");
		} else JOptionPane.showMessageDialog(null, "Selecione uma op\u00e7\u00e3o em 'Quando \u00e9 Comunicado'.");
		} else JOptionPane.showMessageDialog(null, "Selecione uma Fase.");
		} else JOptionPane.showMessageDialog(null, "Selecione um Artefato.");

		editar = false;
	}


	private String getReceptor() {
		String teste = "";
		for(int i = 0; i<listaReceptorB.size(); i++){
			if(i==listaReceptorB.size()-1)
				teste += listaReceptorB.getElementAt(i).toString() + ".";
			else
				teste += listaReceptorB.getElementAt(i).toString() + ", ";
		}
		return teste;
	}


	private String getEmissor() {
		String teste = "";
		for(int i = 0; i<listaEmissorB.size(); i++){
			if(i==listaEmissorB.size()-1)
				teste += listaEmissorB.getElementAt(i).toString() + ".";
			else
				teste += listaEmissorB.getElementAt(i).toString() + ", ";
		}
		return teste;
	}


	protected void jButtonRemoveReceptorHumano() {
		if(listaReceptorB.size()>0){
			Humano humano = (Humano) jListReceptorB.getSelectedValue();				
			listaReceptorA.addElement(humano);
			jListReceptorA.setModel(listaReceptorA);
			listaReceptorB.removeElement(humano);
			jListReceptorB.setModel(listaReceptorB);
		}				
	}
	
	protected void jButtonRemoveReceptorStakeholder() {
		if(listaReceptorB.size()>0){
			Stakeholder stakeholder = (Stakeholder) jListReceptorB.getSelectedValue();				
			listaReceptorA.addElement(stakeholder);
			jListReceptorA.setModel(listaReceptorA);
			listaReceptorB.removeElement(stakeholder);
			jListReceptorB.setModel(listaReceptorB);
		}				
	}

	protected void jButtonRemoveEmissorStakeholder() {
		if(listaEmissorB.size()>0){
			Stakeholder stakeholder = (Stakeholder) jListEmissorB.getSelectedValue();				
			listaEmissorA.addElement(stakeholder);
			jListEmissorA.setModel(listaEmissorA);
			listaEmissorB.removeElement(stakeholder);
			jListEmissorB.setModel(listaEmissorB);
		}				
	}

	
	protected void jButtonAdicionarReceptorHumano() {
		if(listaReceptorA.size()>0){
			Humano humano = (Humano) jListReceptorA.getSelectedValue();	
			listaReceptorB.addElement(humano);
			jListReceptorB.setModel(listaReceptorB);
			listaReceptorA.removeElement(humano);
			jListReceptorA.setModel(listaReceptorA);
		}		
	}

	protected void jButtonAdicionarEmissorHumano() {
		if(listaEmissorA.size()>0){
			Humano humano = (Humano) jListEmissorA.getSelectedValue();	
			listaEmissorB.addElement(humano);
			jListEmissorB.setModel(listaEmissorB);
			listaEmissorA.removeElement(humano);
			jListEmissorA.setModel(listaEmissorA);
		}
	}

	protected void jButtonRemoveEmissorHumano() {
		if(listaEmissorB.size()>0){
			Humano humano = (Humano) jListEmissorB.getSelectedValue();				
			listaEmissorA.addElement(humano);
			jListEmissorA.setModel(listaEmissorA);
			listaEmissorB.removeElement(humano);
			jListEmissorB.setModel(listaEmissorB);
		}				
	}
	
	protected void jButtonAdicionarListaReceptorStakeholder() {
		if(listaReceptorA.size()>0){
			Stakeholder stakeholder = (Stakeholder) jListReceptorA.getSelectedValue();	
			listaReceptorB.addElement(stakeholder);
			jListReceptorB.setModel(listaReceptorB);
			listaReceptorA.removeElement(stakeholder);
			jListReceptorA.setModel(listaReceptorA);
		}		
	}

	protected void jButtonAdicionarListaEmissorStakeholder() {
		if(listaEmissorA.size()>0){
			Stakeholder stakeholder = (Stakeholder)jListEmissorA.getSelectedValue();	
			listaEmissorB.addElement(stakeholder);
			jListEmissorB.setModel(listaEmissorB);
			listaEmissorA.removeElement(stakeholder);
			jListEmissorA.setModel(listaEmissorA);
		}
	}

	private void populaJListReceptorHumano() {
		listaReceptorB.removeAllElements();
		listaReceptorA.removeAllElements();
		for (Humano humano : HumanoDAO.findAll()) {
			listaReceptorA.addElement(humano);  
		}
		jListReceptorA.setModel(listaReceptorA);  	
	}

	private void populaJListEmissorHumano() {
		listaEmissorB.removeAllElements();
		listaEmissorA.removeAllElements();
		for (Humano humano : HumanoDAO.findAll()) {
			listaEmissorA.addElement(humano);  
		}
		jListEmissorA.setModel(listaEmissorA); 
	}

	private void populaComboboxReceptor() {
		jComboBoxTipoReceptor.removeAllItems();
		jComboBoxTipoReceptor.addItem("Selecione...");
		jComboBoxTipoReceptor.addItem("Recurso Humano");
		jComboBoxTipoReceptor.addItem("Stakeholder");	
	}

	private void populaComboBoxEmissor() {
		jComboBoxTipoEmissor.removeAllItems();
		jComboBoxTipoEmissor.addItem("Selecione...");
		jComboBoxTipoEmissor.addItem("Recurso Humano");
		jComboBoxTipoEmissor.addItem("Stakeholder");		
	}

	private void populaComboBoxFase() {
		jComboBoxFase.removeAllItems();
		jComboBoxFase.addItem("Selecione...");
		for (Fase fase : JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getFase()){
			jComboBoxFase.addItem(fase);
		}
		
	}

	private void populaComboBoxQuandoComunicado() {
		jComboBoxQuandoComunicado.removeAllItems();
		jComboBoxQuandoComunicado.addItem("Selecione...");
		jComboBoxQuandoComunicado.addItem("Antes");
		jComboBoxQuandoComunicado.addItem("Durante");
		jComboBoxQuandoComunicado.addItem("Depois");		
	}

	private void populaComboBoxFormaComunicacao() {
		jComboBoxFormaComunicacao.removeAllItems();
		jComboBoxFormaComunicacao.addItem("Selecione...");
		for (Campo campo : CampoDAO.findAll()) {
			if(campo.getNome().equals("Forma de Comunica\u00e7\u00e3o")){
				for (Tipo tipo : campo.getTipo()) {
					jComboBoxFormaComunicacao.addItem(tipo);
				}
			}			
		}
		
	}

	private void populaComboBoxArtefato() {
		jComboBoxArtefato.removeAllItems();
		jComboBoxArtefato.addItem("Selecione...");
		for (Fase fase :  JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getFase()) {
			for (Marco marco : fase.getMarco()) {
				if(marco.getProdutoTrabalho()!=null)
					jComboBoxArtefato.addItem(marco.getProdutoTrabalho());				
			}
		}		
		if(JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getPoliticaOrganizacional()!=null)
			jComboBoxArtefato.addItem(JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getPoliticaOrganizacional().getProdutoTrabalho());
		
		if(JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getPlanoMedicao()!=null)
			jComboBoxArtefato.addItem(JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getPlanoMedicao().getProdutoTrabalho());
	}

}
