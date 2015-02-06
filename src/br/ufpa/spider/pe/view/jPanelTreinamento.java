package br.ufpa.spider.pe.view;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.ufpa.spider.pe.controller.HumanoController;
import br.ufpa.spider.pe.controller.TreinamentoController;
import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.Tipo;
import br.ufpa.spider.pe.model.Treinamento;
import br.ufpa.spider.pe.model.dao.CampoDAO;
import br.ufpa.spider.pe.model.dao.HumanoDAO;
import br.ufpa.spider.pe.model.dao.TreinamentoDAO;
import br.ufpa.spider.pe.model.util.MeuDocument;
import br.ufpa.spider.pe.model.util.MyTableModel;
import br.ufpa.spider.pe.view.util.SendMail;

import com.toedter.calendar.JDateChooser;

public class jPanelTreinamento extends JPanel {
	private JButton JButtonExcluir;
	private JTable jTableTreinamento;
	private JButton jButtonCadastrar;
	private JPanel jPanelMeio;
	private JPanel jPanelTopo;
	private JLabel JLabelCargaHoraria;
	private JTextField jTextFieldCargaHoraria;
	private JLabel jLabelUnidade;
	private JComboBox jComboBoxUnidade;
	private JLabel JLabelInicio;
	private JFormattedTextField jFormatteTextFieldInicio;
	private JLabel jLabelTermino;
	private JFormattedTextField jFormattedTextFieldTermino;
	private JButton jButtonLeft;
	private JButton jButtonRight;
	private JLabel jLabelLocal;
	private JTextField jTextFieldLocal;
	private JLabel jLabelFinalidade;
	private JLabel jLabelNomeTreinamento;
	private JTextArea jTextAreaFinalidade;
	private JTextField jTextFieldNomeTreinamento;
	private JLabel jLabelPerfilTreinamento;
	private JComboBox jComboBoxPerfilTreinamento;
	private JLabel jLabelTipoTreinamento;
	private JComboBox JComboBoxTipoTreinamento;
	private JList jListParticipantes1;
	private JList jListParticipantes2;
	private JLabel jLabelParticipantes;
	private DefaultListModel listModelParticipantes1;
	private DefaultListModel listModelParticipantes2;
	private JScrollPane scrollPane;
	private Vector<String> columns;
	private Vector data;
	private DefaultTableModel tableModel;
	private boolean editar;
	private Treinamento treinamento;
	private MaskFormatter formatDate;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JDateChooser DataInicio;
	private JDateChooser DataTermino;
	
	public jPanelTreinamento() {
		setBorder(new EmptyBorder(25, 20, 25, 20));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		jPanelTopo = new JPanel();
		GridBagConstraints gbc_jPanelTopo = new GridBagConstraints();
		gbc_jPanelTopo.gridwidth = 4;
		gbc_jPanelTopo.insets = new Insets(0, 0, 5, 0);
		gbc_jPanelTopo.fill = GridBagConstraints.BOTH;
		gbc_jPanelTopo.gridx = 0;
		gbc_jPanelTopo.gridy = 0;
		add(jPanelTopo, gbc_jPanelTopo);
		GridBagLayout gbl_jPanelTopo = new GridBagLayout();
		gbl_jPanelTopo.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_jPanelTopo.rowHeights = new int[]{29, 0, 45, 0, 0};
		gbl_jPanelTopo.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_jPanelTopo.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		jPanelTopo.setLayout(gbl_jPanelTopo);
		
		jLabelNomeTreinamento = new JLabel("Nome do Treinamento");
		GridBagConstraints gbc_jLabelNomeTreinamento = new GridBagConstraints();
		gbc_jLabelNomeTreinamento.anchor = GridBagConstraints.NORTHWEST;
		gbc_jLabelNomeTreinamento.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelNomeTreinamento.gridx = 0;
		gbc_jLabelNomeTreinamento.gridy = 0;
		jPanelTopo.add(jLabelNomeTreinamento, gbc_jLabelNomeTreinamento);
		
		jTextFieldNomeTreinamento = new JTextField();
		GridBagConstraints gbc_jTextFieldNomeTreinamento = new GridBagConstraints();
		gbc_jTextFieldNomeTreinamento.anchor = GridBagConstraints.NORTH;
		gbc_jTextFieldNomeTreinamento.gridwidth = 3;
		gbc_jTextFieldNomeTreinamento.insets = new Insets(0, 0, 5, 0);
		gbc_jTextFieldNomeTreinamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldNomeTreinamento.gridx = 1;
		gbc_jTextFieldNomeTreinamento.gridy = 0;
		jPanelTopo.add(jTextFieldNomeTreinamento, gbc_jTextFieldNomeTreinamento);
		jTextFieldNomeTreinamento.setColumns(10);
		
		jLabelPerfilTreinamento = new JLabel("Perfil de Treinamento");
		GridBagConstraints gbc_jLabelPerfilTreinamento = new GridBagConstraints();
		gbc_jLabelPerfilTreinamento.anchor = GridBagConstraints.NORTHWEST;
		gbc_jLabelPerfilTreinamento.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelPerfilTreinamento.gridx = 0;
		gbc_jLabelPerfilTreinamento.gridy = 1;
		jPanelTopo.add(jLabelPerfilTreinamento, gbc_jLabelPerfilTreinamento);
		
		jComboBoxPerfilTreinamento = new JComboBox();
		jComboBoxPerfilTreinamento.addFocusListener(new FocusListener(){  

			@Override 
		    public void focusGained(FocusEvent arg0) {  
				populaComboBoxPerfilTreinamento();
		    }  

			@Override
		    public void focusLost(FocusEvent arg0){               
		        //aï¿½ï¿½o desejada quando perde o foco  
		    }
		      
		});  
		GridBagConstraints gbc_jComboBoxPerfilTreinamento = new GridBagConstraints();
		gbc_jComboBoxPerfilTreinamento.anchor = GridBagConstraints.NORTH;
		gbc_jComboBoxPerfilTreinamento.insets = new Insets(0, 0, 5, 5);
		gbc_jComboBoxPerfilTreinamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxPerfilTreinamento.gridx = 1;
		gbc_jComboBoxPerfilTreinamento.gridy = 1;
		
		jPanelTopo.add(jComboBoxPerfilTreinamento, gbc_jComboBoxPerfilTreinamento);
		jLabelTipoTreinamento = new JLabel("Tipo de Treinamento");
		
		GridBagConstraints gbc_jLabelTipoTreinamento = new GridBagConstraints();
		gbc_jLabelTipoTreinamento.anchor = GridBagConstraints.NORTHWEST;
		gbc_jLabelTipoTreinamento.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelTipoTreinamento.gridx = 2;
		gbc_jLabelTipoTreinamento.gridy = 1;
		jPanelTopo.add(jLabelTipoTreinamento, gbc_jLabelTipoTreinamento);
		
		JComboBoxTipoTreinamento = new JComboBox();
		JComboBoxTipoTreinamento.addFocusListener(new FocusListener(){  

			@Override 
		    public void focusGained(FocusEvent arg0) {  
				populaComboBoxTipoTreinamento();
		    }  

			@Override
		    public void focusLost(FocusEvent arg0){               
		        //aï¿½ï¿½o desejada quando perde o foco  
		    }
		      
		});  
		GridBagConstraints gbc_JComboBoxTipoTreinamento = new GridBagConstraints();
		gbc_JComboBoxTipoTreinamento.anchor = GridBagConstraints.NORTH;
		gbc_JComboBoxTipoTreinamento.insets = new Insets(0, 0, 5, 0);
		gbc_JComboBoxTipoTreinamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_JComboBoxTipoTreinamento.gridx = 3;
		gbc_JComboBoxTipoTreinamento.gridy = 1;
		
		jPanelTopo.add(JComboBoxTipoTreinamento, gbc_JComboBoxTipoTreinamento);
		
		jLabelFinalidade = new JLabel("Finalidade");
		GridBagConstraints gbc_jLabelFinalidade = new GridBagConstraints();
		gbc_jLabelFinalidade.anchor = GridBagConstraints.NORTHWEST;
		gbc_jLabelFinalidade.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelFinalidade.gridx = 0;
		gbc_jLabelFinalidade.gridy = 2;
		jPanelTopo.add(jLabelFinalidade, gbc_jLabelFinalidade);
		
		scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridwidth = 3;
		gbc_scrollPane_3.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_3.gridx = 1;
		gbc_scrollPane_3.gridy = 2;
		jPanelTopo.add(scrollPane_3, gbc_scrollPane_3);
		
		jTextAreaFinalidade = new JTextArea();
		jTextAreaFinalidade.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jTextAreaFinalidade.setLineWrap(true);
		scrollPane_3.setViewportView(jTextAreaFinalidade);
		jTextAreaFinalidade.setMinimumSize(new Dimension(50, 22));
		
		jLabelLocal = new JLabel("Local");
		GridBagConstraints gbc_jLabelLocal = new GridBagConstraints();
		gbc_jLabelLocal.anchor = GridBagConstraints.NORTHWEST;
		gbc_jLabelLocal.insets = new Insets(0, 0, 0, 5);
		gbc_jLabelLocal.gridx = 0;
		gbc_jLabelLocal.gridy = 3;
		jPanelTopo.add(jLabelLocal, gbc_jLabelLocal);
		
		jTextFieldLocal = new JTextField();
		GridBagConstraints gbc_jTextFieldLocal = new GridBagConstraints();
		gbc_jTextFieldLocal.anchor = GridBagConstraints.NORTH;
		gbc_jTextFieldLocal.gridwidth = 3;
		gbc_jTextFieldLocal.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldLocal.gridx = 1;
		gbc_jTextFieldLocal.gridy = 3;
		jPanelTopo.add(jTextFieldLocal, gbc_jTextFieldLocal);
		jTextFieldLocal.setColumns(10);
		
		jPanelMeio = new JPanel();
		jPanelMeio.setBorder(new EmptyBorder(10, 0, 0, 0));
		GridBagConstraints gbc_jPanelMeio = new GridBagConstraints();
		gbc_jPanelMeio.gridwidth = 4;
		gbc_jPanelMeio.insets = new Insets(0, 0, 5, 0);
		gbc_jPanelMeio.fill = GridBagConstraints.BOTH;
		gbc_jPanelMeio.gridx = 0;
		gbc_jPanelMeio.gridy = 1;
		add(jPanelMeio, gbc_jPanelMeio);
		GridBagLayout gbl_jPanelMeio = new GridBagLayout();
		gbl_jPanelMeio.columnWidths = new int[]{0, 40, 0, 221, 0, 51, 0, 61, 112, 0};
		gbl_jPanelMeio.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_jPanelMeio.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_jPanelMeio.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		jPanelMeio.setLayout(gbl_jPanelMeio);
		
		JLabelCargaHoraria = new JLabel("Carga Hor\u00E1ria");
		GridBagConstraints gbc_JLabelCargaHoraria = new GridBagConstraints();
		gbc_JLabelCargaHoraria.anchor = GridBagConstraints.WEST;
		gbc_JLabelCargaHoraria.insets = new Insets(0, 0, 5, 5);
		gbc_JLabelCargaHoraria.gridx = 0;
		gbc_JLabelCargaHoraria.gridy = 0;
		jPanelMeio.add(JLabelCargaHoraria, gbc_JLabelCargaHoraria);
		
		jTextFieldCargaHoraria = new JTextField();
		jTextFieldCargaHoraria.setDocument(new MeuDocument());
		GridBagConstraints gbc_jTextFieldCargaHoraria = new GridBagConstraints();
		gbc_jTextFieldCargaHoraria.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldCargaHoraria.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldCargaHoraria.gridx = 1;
		gbc_jTextFieldCargaHoraria.gridy = 0;
		jPanelMeio.add(jTextFieldCargaHoraria, gbc_jTextFieldCargaHoraria);
		jTextFieldCargaHoraria.setColumns(10);

		jComboBoxUnidade = new JComboBox();
		jComboBoxUnidade.addFocusListener(new FocusListener(){  

			@Override 
		    public void focusGained(FocusEvent arg0) {  
				populaComboBoxUnidade();
		    }  

			@Override
		    public void focusLost(FocusEvent arg0){               
		        //Nao desejada quando perde o foco  
		    }
		      
		});  
		
		jLabelUnidade = new JLabel("Unidade");
		GridBagConstraints gbc_jLabelUnidade = new GridBagConstraints();
		gbc_jLabelUnidade.anchor = GridBagConstraints.EAST;
		gbc_jLabelUnidade.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelUnidade.gridx = 2;
		gbc_jLabelUnidade.gridy = 0;
		jPanelMeio.add(jLabelUnidade, gbc_jLabelUnidade);
		
		GridBagConstraints gbc_jComboBoxUnidade = new GridBagConstraints();
		gbc_jComboBoxUnidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxUnidade.insets = new Insets(0, 0, 5, 5);
		gbc_jComboBoxUnidade.gridx = 3;
		gbc_jComboBoxUnidade.gridy = 0;
		jPanelMeio.add(jComboBoxUnidade, gbc_jComboBoxUnidade);
		
		try {
			formatDate = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JLabelInicio = new JLabel("In\u00EDcio");
		GridBagConstraints gbc_JLabelInicio = new GridBagConstraints();
		gbc_JLabelInicio.anchor = GridBagConstraints.EAST;
		gbc_JLabelInicio.insets = new Insets(0, 0, 5, 5);
		gbc_JLabelInicio.gridx = 4;
		gbc_JLabelInicio.gridy = 0;
		jPanelMeio.add(JLabelInicio, gbc_JLabelInicio);
		
		DataInicio = new JDateChooser(new Date());
		
		GridBagConstraints gbc_jFormatteTextFieldInicio = new GridBagConstraints();
		gbc_jFormatteTextFieldInicio.insets = new Insets(0, 0, 5, 5);
		gbc_jFormatteTextFieldInicio.fill = GridBagConstraints.HORIZONTAL;
		gbc_jFormatteTextFieldInicio.gridx = 5;
		gbc_jFormatteTextFieldInicio.gridy = 0;
		jPanelMeio.add(DataInicio, gbc_jFormatteTextFieldInicio);
		
		jLabelTermino = new JLabel("T\u00E9rmino");
		GridBagConstraints gbc_jLabelTermino = new GridBagConstraints();
		gbc_jLabelTermino.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelTermino.gridx = 6;
		gbc_jLabelTermino.gridy = 0;
		jPanelMeio.add(jLabelTermino, gbc_jLabelTermino);
		
		DataTermino = new JDateChooser(new Date());
		GridBagConstraints gbc_jFormattedTextFieldTermino = new GridBagConstraints();
		gbc_jFormattedTextFieldTermino.insets = new Insets(0, 0, 5, 5);
		gbc_jFormattedTextFieldTermino.fill = GridBagConstraints.HORIZONTAL;
		gbc_jFormattedTextFieldTermino.gridx = 7;
		gbc_jFormattedTextFieldTermino.gridy = 0;
		jPanelMeio.add(DataTermino, gbc_jFormattedTextFieldTermino);
		
		jLabelParticipantes = new JLabel("Participantes");
		GridBagConstraints gbc_jLabelParticipantes = new GridBagConstraints();
		gbc_jLabelParticipantes.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelParticipantes.gridx = 0;
		gbc_jLabelParticipantes.gridy = 1;
		jPanelMeio.add(jLabelParticipantes, gbc_jLabelParticipantes);
		
		jButtonRight = new JButton(">");
		jButtonRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonRightActionPerformed(arg0);
			}
		});
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridheight = 3;
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 2;
		listModelParticipantes1 = new DefaultListModel();
		listModelParticipantes2 = new DefaultListModel();
		jPanelMeio.add(scrollPane_1, gbc_scrollPane_1);
		jListParticipantes1 = new JList(listModelParticipantes1);
		jListParticipantes1.setPreferredSize(new Dimension(200, 80));
		jListParticipantes1.setMinimumSize(new Dimension(80, 80));
		jListParticipantes1.setMaximumSize(new Dimension(10000, 80));
		scrollPane_1.setViewportView(jListParticipantes1);
		
		scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridheight = 3;
		gbc_scrollPane_2.gridwidth = 2;
		gbc_scrollPane_2.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_2.gridx = 3;
		gbc_scrollPane_2.gridy = 2;
		jPanelMeio.add(scrollPane_2, gbc_scrollPane_2);
		jListParticipantes2 = new JList(listModelParticipantes2);
		jListParticipantes2.setPreferredSize(new Dimension(100, 80));
		jListParticipantes2.setMinimumSize(new Dimension(80, 80));
		jListParticipantes2.setMaximumSize(new Dimension(10000, 80));
		scrollPane_1.setViewportView(jListParticipantes1);
		scrollPane_2.setViewportView(jListParticipantes2);
		GridBagConstraints gbc_jButtonRight = new GridBagConstraints();
		gbc_jButtonRight.insets = new Insets(0, 0, 5, 5);
		gbc_jButtonRight.gridx = 2;
		gbc_jButtonRight.gridy = 3;
		jPanelMeio.add(jButtonRight, gbc_jButtonRight);
		
		jButtonLeft = new JButton("<");
		jButtonLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonLeftActioPerformed(e);
			}
		});
		GridBagConstraints gbc_jButtonLeft = new GridBagConstraints();
		gbc_jButtonLeft.insets = new Insets(0, 0, 0, 5);
		gbc_jButtonLeft.gridx = 2;
		gbc_jButtonLeft.gridy = 4;
		jPanelMeio.add(jButtonLeft, gbc_jButtonLeft);
		jListParticipantes1.setModel(listModelParticipantes1);
		atualizarJLists();
		
		jButtonCadastrar = new JButton("Cadastrar");
		jButtonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCadastrarActionPerformed(e);
			}
		});
		jButtonCadastrar.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_jButtonCadastrar = new GridBagConstraints();
		gbc_jButtonCadastrar.anchor = GridBagConstraints.EAST;
		gbc_jButtonCadastrar.gridwidth = 2;
		gbc_jButtonCadastrar.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonCadastrar.gridx = 2;
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
		
		columns = new Vector();
		data = new Vector();
		columns.add("Nome");
		columns.add("Perfil");
		columns.add("Tipo");		
		columns.add("Local");
		columns.add("Per\u00EDodo");
		tableModel = MyTableModel.modelTreinamento(columns, data);
		
		jTableTreinamento = new JTable(tableModel);
		jTableTreinamento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jTableMouseClicked(arg0);
			}
		});
		jTableTreinamento.getTableHeader().setReorderingAllowed(false);
		jTableTreinamento.setPreferredScrollableViewportSize(new Dimension(700, 200));
		scrollPane.setViewportView(jTableTreinamento);
		
		List<Treinamento> treinamentos = getListaTreinamentoProcessoSelecionado();
		
		if(treinamentos.size()!=0)
			populaTabela(treinamentos);
		
		JButtonExcluir = new JButton("Excluir");
		JButtonExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonExcluirActionPerformed(e);
			}
		});
		GridBagConstraints gbc_JButtonExcluir = new GridBagConstraints();
		gbc_JButtonExcluir.anchor = GridBagConstraints.EAST;
		gbc_JButtonExcluir.gridx = 3;
		gbc_JButtonExcluir.gridy = 4;
		add(JButtonExcluir, gbc_JButtonExcluir);
		populaComboBoxUnidade();
		populaComboBoxPerfilTreinamento();
		populaComboBoxTipoTreinamento();
	}
	
	protected void jTableMouseClicked(MouseEvent arg0) {
		if(arg0.getClickCount() == 2){
			jButtonEditarActionPerformed(null);
			editar =  true;
		}
	}

	private void populaComboBoxUnidade() {		
		jComboBoxUnidade.removeAllItems();
		
		jComboBoxUnidade.addItem("Selecione...");
		for (Tipo tipo : CampoDAO.findByName("Unidade de Medida (Carga Hor\u00E1ria)").getTipo()) {
			jComboBoxUnidade.addItem(tipo.getNome());
		}
		
	}
	
	private void populaComboBoxTipoTreinamento() {
		JComboBoxTipoTreinamento.removeAllItems();
		
		JComboBoxTipoTreinamento.addItem("Selecione...");
		for (Tipo tipo : CampoDAO.findByName("Tipo de Treinamento").getTipo()) {
			JComboBoxTipoTreinamento.addItem(tipo.getNome());
		}
		
	}
	
	private void populaComboBoxPerfilTreinamento() {
		jComboBoxPerfilTreinamento.removeAllItems();		
		
		jComboBoxPerfilTreinamento.addItem("Selecione...");		
		for (Tipo tipo : CampoDAO.findByName("Perfil de Treinamento").getTipo()) {
			jComboBoxPerfilTreinamento.addItem(tipo.getNome());
		}
	}

	protected void jButtonExcluirActionPerformed(ActionEvent e) {
		int i = JOptionPane.showConfirmDialog(null ,"Deseja excluir o Treinamento selecionado?", 
				"Treinamento",JOptionPane.YES_NO_OPTION); 
				if (i == JOptionPane.YES_OPTION ) { 
					try {
						treinamento = (Treinamento) jTableTreinamento.getValueAt(jTableTreinamento.getSelectedRow(), 0);
						for (Humano h : treinamento.getHumano()) {
							if(h.getTreinamento().contains(treinamento)){
								h.getTreinamento().remove(treinamento);
								HumanoController.saveHumano(h, false);
							}
						}
						TreinamentoDAO.removeTreinamento(treinamento);
						populaTabela(getListaTreinamentoProcessoSelecionado());
						JOptionPane.showMessageDialog(null, "Treinamento exclu\u00EDdo");
						jTextAreaFinalidade.setText("");
						jTextFieldCargaHoraria.setText("");
						jTextFieldLocal.setText("");
						jTextFieldNomeTreinamento.setText("");
						jFormatteTextFieldInicio.setText("");
						jFormattedTextFieldTermino.setText("");
						jComboBoxPerfilTreinamento.setSelectedIndex(0);
						JComboBoxTipoTreinamento.setSelectedIndex(0);
						jComboBoxUnidade.setSelectedIndex(0);
						atualizarJLists();
					} catch (ArrayIndexOutOfBoundsException ex){
						JOptionPane.showMessageDialog(null, "Selecione uma linha.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
	}

	protected void jButtonCadastrarActionPerformed(ActionEvent e) {	
		ArrayList<Humano> humanos = new ArrayList<Humano>();
		if(!editar){
			treinamento = new Treinamento();
		}
		if(!jTextFieldNomeTreinamento.getText().equals("") && jComboBoxPerfilTreinamento.getSelectedIndex()!=0 && JComboBoxTipoTreinamento.getSelectedIndex()!=0 && jComboBoxUnidade.getSelectedIndex()!=0){
			try {
				treinamento.setNome(jTextFieldNomeTreinamento.getText());
				//jTextFieldNomeTreinamento.setBorder(new LineBorder(Color.black, 1));
				treinamento.setCargaHoraria(jTextFieldCargaHoraria.getText());
				//jTextFieldCargaHoraria.setBorder(new LineBorder(Color.black, 1));
				treinamento.setInicio(trata(DataInicio.getDate()));
				//jFormattedTextFieldTermino.setBorder(new LineBorder(Color.black, 1));
				treinamento.setTermino(trata(DataTermino.getDate()));
				//jFormatteTextFieldInicio.setBorder(new LineBorder(Color.black, 1));
				treinamento.setFinalidade(jTextAreaFinalidade.getText());
				//jTextAreaFinalidade.setBorder(new LineBorder(Color.black, 1));
				treinamento.setLocal(jTextFieldLocal.getText());
				//jTextFieldLocal.setBorder(new LineBorder(Color.black, 1));
				treinamento.setPerfil(jComboBoxPerfilTreinamento.getSelectedItem().toString());
				treinamento.setTipoTreinamento(JComboBoxTipoTreinamento.getSelectedItem().toString());
				treinamento.setUnidadeCargaHoraria(jComboBoxUnidade.getSelectedItem().toString());
			
		
			for (Object ob : listModelParticipantes2.toArray()) {
				Humano humano = (Humano) ob;
				if(!editar){
					humano.getTreinamento().add(treinamento);
					treinamento.getHumano().add(humano);					
				} else {
					if(!humano.getTreinamento().contains(treinamento)){
						humano.getTreinamento().add(treinamento);
						treinamento.getHumano().add(humano);
					}
				}
				humanos.add(humano);
			}
			
			try{
				TreinamentoController.saveTreinamento(treinamento);
				String msg = "Você tem um treinamento de " +treinamento.getInicio()+" a "+treinamento.getTermino()+".";
				String titulo_msg = "Treinamento";
				String mensagens = "";
				for (Humano humano : humanos) {
					if(SendMail.sendEmail(humano.getEmail(), titulo_msg , msg)){
						mensagens += "E-mail contendo especificaçes do treinamento enviado para: " + humano.getEmail() +"\n";
					} else mensagens += "E-mail n\u00e3o enviado para " + humano.getEmail()+". Verifique as configura\u00e7\u00f5es de e-mail ou se o e-mail do destinat\u00e1rio est\u00e1 correto.\n";
				}
				JOptionPane.showMessageDialog(null, mensagens);				
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
			
			List<Treinamento> treinamentos = getListaTreinamentoProcessoSelecionado();
			//List<Treinamento> treinamentos = TreinamentoDAO.findAll();
			if(treinamentos.size()!=0)
				populaTabela(treinamentos);
			jTextAreaFinalidade.setText("");
			jTextFieldCargaHoraria.setText("");
			jTextFieldLocal.setText("");
			jTextFieldNomeTreinamento.setText("");
			jFormatteTextFieldInicio.setText("");
			jFormattedTextFieldTermino.setText("");
			jComboBoxPerfilTreinamento.setSelectedIndex(0);
			JComboBoxTipoTreinamento.setSelectedIndex(0);
			jComboBoxUnidade.setSelectedIndex(0);
			atualizarJLists();
			
			//JOptionPane.showMessageDialog(null, "Treinamento Cadastrado com sucesso");
			editar = false;
			
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(null,"Um ou mais campos nulos.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Um ou mais campos nulos.");
		}
		
		
	}
	
	private String trata(Date date) {
		String mesC = "01";
	    int dia = date.getDate();
	    int mes = date.getMonth() + 1; // retorna um valor de mes entre 0..11
	    if(mes<10)
	    	mesC = "0"+mes;
	    else
	    	mesC = Integer.toString(mes);
	    int ano = date.getYear() + 1900;
	    return dia  +"/"+mesC+"/"+ano;
	}

	protected void jButtonEditarActionPerformed(ActionEvent e) {
		try{
			int linha = jTableTreinamento.getSelectedRow();
			treinamento = (Treinamento) jTableTreinamento.getValueAt(linha, 0);
			jTextAreaFinalidade.setText(treinamento.getFinalidade());
			jTextFieldCargaHoraria.setText(treinamento.getCargaHoraria().toString().replace(",", ""));
			jTextFieldLocal.setText(treinamento.getLocal());
			jTextFieldNomeTreinamento.setText(treinamento.getNome());
			
			
			DataInicio.setDate(new Date(trata(treinamento.getInicio())));
			DataTermino.setDate(new Date(trata(treinamento.getTermino())));
			jFormattedTextFieldTermino.setText(treinamento.getTermino());
			jComboBoxPerfilTreinamento.setSelectedItem(treinamento.getPerfil());
			JComboBoxTipoTreinamento.setSelectedItem(treinamento.getTipoTreinamento());
			jComboBoxUnidade.setSelectedItem(treinamento.getUnidadeCargaHoraria());
			atualizarJLists(treinamento);
			editar = true;
			this.repaint();
		} catch (ArrayIndexOutOfBoundsException ex){
			JOptionPane.showMessageDialog(null, "Selecione uma linha", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private String trata(String string) {
		String dia = string.substring(0, 2);
		String mes = string.substring(3, 5);
		String ano = string.substring(6, 10);
		return ano + "/" + mes +"/"+dia;
	}

	protected void jButtonLeftActioPerformed(ActionEvent e) {
		Humano humano = (Humano) jListParticipantes2.getSelectedValue();
		listModelParticipantes2.removeElement(humano);
		listModelParticipantes1.addElement(humano);
	}

	protected void jButtonRightActionPerformed(ActionEvent arg0) {
		Humano humano = (Humano) jListParticipantes1.getSelectedValue();
		listModelParticipantes1.removeElement(humano);
		listModelParticipantes2.addElement(humano);
	}

	private void populaTabela(List<Treinamento> l) {
		tableModel.setNumRows(0);
		for (Treinamento treinamento : l) {
			String periodo=treinamento.getInicio()+" \u00E1 "+treinamento.getTermino();
				tableModel.addRow(new Object[]{ 
						treinamento,
						treinamento.getPerfil(),
						treinamento.getTipoTreinamento(),
						treinamento.getLocal(),
						periodo
						});
		}
	}
	
	private void atualizarJLists(){
		listModelParticipantes1.removeAllElements();
		listModelParticipantes2.removeAllElements();
		for (Humano humano : HumanoDAO.findAll()) {
			listModelParticipantes1.addElement(humano);
		}
			
		this.repaint();
	}
	
	private void atualizarJLists(Treinamento treinamento){
		listModelParticipantes2.removeAllElements();
		for (Humano humano : treinamento.getHumano()) {
			listModelParticipantes2.addElement(humano);
		}
		
		listModelParticipantes1.removeAllElements();
		for (Humano humano : consultaHumanos()) {
			if(!listModelParticipantes2.contains(humano)){
				listModelParticipantes1.addElement(humano);
			}
		}
		this.repaint();
	}
	private ArrayList<Treinamento> getListaTreinamentoProcessoSelecionado(){
		ArrayList<Treinamento> treinamentos = new ArrayList<Treinamento>();
		for (Humano humano : consultaHumanos() ) {
			for (Treinamento treinamento : humano.getTreinamento()) {
				if(!treinamentos.contains(treinamento))
					treinamentos.add(treinamento);
			}
		}
		return treinamentos;
	}

		
	private List<Humano> consultaHumanos() {
		return HumanoDAO.findAll();
	}
	
}
