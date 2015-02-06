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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.tmatesoft.sqljet.core.internal.lang.SqlParser.join_constraint_return;

import br.ufpa.spider.pe.controller.CapacidadeController;
import br.ufpa.spider.pe.controller.HumanoController;
import br.ufpa.spider.pe.model.Capacidade;
import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.Tipo;
import br.ufpa.spider.pe.model.dao.CampoDAO;
import br.ufpa.spider.pe.model.dao.HumanoDAO;
import br.ufpa.spider.pe.model.dao.ProcessoDAO;
import br.ufpa.spider.pe.model.util.MyTableModel;
import javax.swing.ImageIcon;

public class JPanelCapacidades extends JPanel implements FocusListener {
	private JTextField jTextFieldEmail;
	private JLabel JLabelRecursoHumano;
	private JLabel JLabelEmail;
	private JLabel jLabelConhecimento;
	private JTextArea jTextAreaConhecimento;
	private JPanel jPanelMeio;
	private JButton jButtonPapeisExercidosRight;
	private JButton JButtonFormacaoAcademicaRight_1;
	private JButton jButtonPapeisExercidosLeft_1;
	private JButton jButtonFormacaoAcademicaLeft_1;
	private JLabel JLabelFormacaoProfissional;		
	private JLabel jLabelCursosCertificacoes;		
	private JButton JButtonCadastrar;				
	private JPanel jPanelTopo;
	private DefaultListModel listModelPapeisExercidos1;
	private DefaultListModel listModelPapeisExercidos2;
	private DefaultListModel listModelFormacaoAcademica1;
	private DefaultListModel listModelFormacaoAcademica2;
	private DefaultTableModel tableModel;
	private Vector<String> columns;
	private Vector data;
	private JLabel label;
	private JScrollPane scrollPane_5;
	private JPanel jPanelBaixo;
	private JPanel panel;
	private JTable table;
	private JTextArea jTextAreaFormacaoProfissional;
	private JTextArea jTextAreaCursosCertificacoes;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_6;
	private JScrollPane scrollPane_7;
	private JLabel lblPapeisExercidos;
	private JList jListPapeisExercidos1;
	private JScrollPane scrollPane_1;
	private JList jListPapeisExercidos2;
	private JScrollPane scrollPane_2;
	private JList jListFormacaoAcademica1;
	private JScrollPane scrollPane_3;
	private JList jListFormacaoAcademica2;
	private JScrollPane scrollPane_4;
	boolean editar = false;
	Humano humano;
	private JButton btnLimpar;
	private Capacidade capacidade;
	private Processo processo = JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso();
	private JButton btnNewButton;
	private JComboBox jComboBoxHumano;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	
	public JPanelCapacidades() {		
		listModelPapeisExercidos1 = new DefaultListModel();
		
		listModelPapeisExercidos2 = new DefaultListModel();
		
		listModelFormacaoAcademica1 = new DefaultListModel();
		
		listModelFormacaoAcademica2 = new DefaultListModel();
		
		setBorder(new EmptyBorder(25, 20, 25, 20));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 126, 285, 0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		jPanelTopo = new JPanel();
		GridBagConstraints gbc_jPanelTopo = new GridBagConstraints();
		gbc_jPanelTopo.insets = new Insets(0, 0, 5, 0);
		gbc_jPanelTopo.fill = GridBagConstraints.BOTH;
		gbc_jPanelTopo.gridx = 0;
		gbc_jPanelTopo.gridy = 0;
		add(jPanelTopo, gbc_jPanelTopo);
		GridBagLayout gbl_jPanelTopo = new GridBagLayout();
		gbl_jPanelTopo.columnWidths = new int[]{0, 0, 0, 0};
		gbl_jPanelTopo.rowHeights = new int[]{0, 0, 0};
		gbl_jPanelTopo.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0};
		gbl_jPanelTopo.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		jPanelTopo.setLayout(gbl_jPanelTopo);
		
		JLabelRecursoHumano = new JLabel("Recurso Humano");
		GridBagConstraints gbc_JLabelRecursoHumano = new GridBagConstraints();
		gbc_JLabelRecursoHumano.anchor = GridBagConstraints.EAST;
		gbc_JLabelRecursoHumano.insets = new Insets(0, 0, 5, 5);
		gbc_JLabelRecursoHumano.gridx = 0;
		gbc_JLabelRecursoHumano.gridy = 0;
		jPanelTopo.add(JLabelRecursoHumano, gbc_JLabelRecursoHumano);
		
		jComboBoxHumano = new JComboBox();
		GridBagConstraints gbc_jComboBoxHumano = new GridBagConstraints();
		gbc_jComboBoxHumano.insets = new Insets(0, 0, 5, 5);
		gbc_jComboBoxHumano.fill = GridBagConstraints.BOTH;
		gbc_jComboBoxHumano.gridx = 1;
		gbc_jComboBoxHumano.gridy = 0;
		jPanelTopo.add(jComboBoxHumano, gbc_jComboBoxHumano);
		
		JLabelEmail = new JLabel("Email");
		GridBagConstraints gbc_JLabelEmail = new GridBagConstraints();
		gbc_JLabelEmail.anchor = GridBagConstraints.WEST;
		gbc_JLabelEmail.insets = new Insets(0, 0, 5, 5);
		gbc_JLabelEmail.gridx = 2;
		gbc_JLabelEmail.gridy = 0;
		jPanelTopo.add(JLabelEmail, gbc_JLabelEmail);
		
		jTextFieldEmail = new JTextField();
		jTextFieldEmail.setMaximumSize(new Dimension(2147483647, 25));
		jTextFieldEmail.setMinimumSize(new Dimension(4, 25));
		jTextFieldEmail.setPreferredSize(new Dimension(4, 25));
		GridBagConstraints gbc_jTextFieldEmail = new GridBagConstraints();
		gbc_jTextFieldEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldEmail.insets = new Insets(0, 0, 5, 0);
		gbc_jTextFieldEmail.gridx = 3;
		gbc_jTextFieldEmail.gridy = 0;
		jPanelTopo.add(jTextFieldEmail, gbc_jTextFieldEmail);
		jTextFieldEmail.setColumns(10);
		
		jLabelConhecimento = new JLabel("Conhecimento");
		GridBagConstraints gbc_jLabelConhecimento = new GridBagConstraints();
		gbc_jLabelConhecimento.anchor = GridBagConstraints.WEST;
		gbc_jLabelConhecimento.insets = new Insets(0, 0, 0, 5);
		gbc_jLabelConhecimento.gridx = 0;
		gbc_jLabelConhecimento.gridy = 1;
		jPanelTopo.add(jLabelConhecimento, gbc_jLabelConhecimento);
		
		scrollPane_5 = new JScrollPane();
		scrollPane_5.setPreferredSize(new Dimension(3, 40));
		scrollPane_5.setMinimumSize(new Dimension(22, 40));
		GridBagConstraints gbc_scrollPane_5 = new GridBagConstraints();
		gbc_scrollPane_5.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_5.gridwidth = 3;
		gbc_scrollPane_5.gridx = 1;
		gbc_scrollPane_5.gridy = 1;
		jPanelTopo.add(scrollPane_5, gbc_scrollPane_5);
		
		jTextAreaConhecimento = new JTextArea();
		jTextAreaConhecimento.setMinimumSize(new Dimension(0, 30));
		jTextAreaConhecimento.setPreferredSize(new Dimension(0, 30));
		jTextAreaConhecimento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPane_5.setViewportView(jTextAreaConhecimento);
		jTextAreaConhecimento.setLineWrap(true);
		jTextAreaConhecimento.setEnabled(true);
		
		jPanelMeio = new JPanel();
		jPanelMeio.addFocusListener(this);   
		GridBagConstraints gbc_jPanelMeio = new GridBagConstraints();
		gbc_jPanelMeio.anchor = GridBagConstraints.SOUTH;
		gbc_jPanelMeio.insets = new Insets(0, 0, 5, 0);
		gbc_jPanelMeio.fill = GridBagConstraints.HORIZONTAL;
		gbc_jPanelMeio.gridx = 0;
		gbc_jPanelMeio.gridy = 1;
		add(jPanelMeio, gbc_jPanelMeio);
		GridBagLayout gbl_jPanelMeio = new GridBagLayout();
		gbl_jPanelMeio.columnWidths = new int[]{28, 157, 50, 150, 30, 0, 26, 0, 30, 60, 150, 30, 0};
		gbl_jPanelMeio.rowHeights = new int[]{27, 0, 0, 0, 0};
		gbl_jPanelMeio.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_jPanelMeio.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		jPanelMeio.setLayout(gbl_jPanelMeio);
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonUpdatePapeisExercidos();
			}
		});
		btnNewButton_1.setPreferredSize(new Dimension(22, 9));
		btnNewButton_1.setMaximumSize(new Dimension(22, 9));
		btnNewButton_1.setMinimumSize(new Dimension(22, 9));
		btnNewButton_1.setIcon(new ImageIcon(JPanelCapacidades.class.getResource("/br/ufpa/spider/pe/img/config/refresh.png")));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 0;
		jPanelMeio.add(btnNewButton_1, gbc_btnNewButton_1);
		
		lblPapeisExercidos = new JLabel("Papeis Exercidos");
		GridBagConstraints gbc_lblPapeisExercidos = new GridBagConstraints();
		gbc_lblPapeisExercidos.anchor = GridBagConstraints.WEST;
		gbc_lblPapeisExercidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblPapeisExercidos.gridx = 1;
		gbc_lblPapeisExercidos.gridy = 0;
		jPanelMeio.add(lblPapeisExercidos, gbc_lblPapeisExercidos);
		
		btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonUpdateFormacaoAcademica();
			}
		});
		btnNewButton_2.setPreferredSize(new Dimension(22, 9));
		btnNewButton_2.setMinimumSize(new Dimension(22, 9));
		btnNewButton_2.setMaximumSize(new Dimension(22, 9));
		btnNewButton_2.setIcon(new ImageIcon(JPanelCapacidades.class.getResource("/br/ufpa/spider/pe/img/config/refresh.png")));
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton_2.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 6;
		gbc_btnNewButton_2.gridy = 0;
		jPanelMeio.add(btnNewButton_2, gbc_btnNewButton_2);
		
		label = new JLabel("Forma\u00E7\u00E3o Acad\u00EAmica");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 7;
		gbc_label.gridy = 0;
		jPanelMeio.add(label, gbc_label);
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridheight = 3;
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 1;
		jPanelMeio.add(scrollPane_1, gbc_scrollPane_1);
		
		jListPapeisExercidos1 = new JList(listModelPapeisExercidos1);
		scrollPane_1.setViewportView(jListPapeisExercidos1);
		
		jButtonPapeisExercidosRight = new JButton(">");
		jButtonPapeisExercidosRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonPapeisExercidosRightActionPerformed(e);
			}
		});
		GridBagConstraints gbc_jButtonPapeisExercidosRight = new GridBagConstraints();
		gbc_jButtonPapeisExercidosRight.insets = new Insets(0, 0, 5, 5);
		gbc_jButtonPapeisExercidosRight.gridx = 2;
		gbc_jButtonPapeisExercidosRight.gridy = 1;
		jPanelMeio.add(jButtonPapeisExercidosRight, gbc_jButtonPapeisExercidosRight);
		
		scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridwidth = 2;
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridheight = 3;
		gbc_scrollPane_2.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_2.gridx = 3;
		gbc_scrollPane_2.gridy = 1;
		jPanelMeio.add(scrollPane_2, gbc_scrollPane_2);
		
		jListPapeisExercidos2 = new JList(listModelPapeisExercidos2);
		scrollPane_2.setViewportView(jListPapeisExercidos2);
		
		scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.gridwidth = 3;
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridheight = 3;
		gbc_scrollPane_3.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_3.gridx = 6;
		gbc_scrollPane_3.gridy = 1;
		jPanelMeio.add(scrollPane_3, gbc_scrollPane_3);
		
		jListFormacaoAcademica1 = new JList(listModelFormacaoAcademica1);
		scrollPane_3.setViewportView(jListFormacaoAcademica1);
		
		JButtonFormacaoAcademicaRight_1 = new JButton(">");
		JButtonFormacaoAcademicaRight_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonFormacaoAcademicaRithtActionPerformed(arg0);
			}
		});
		GridBagConstraints gbc_JButtonFormacaoAcademicaRight_1 = new GridBagConstraints();
		gbc_JButtonFormacaoAcademicaRight_1.insets = new Insets(0, 0, 5, 5);
		gbc_JButtonFormacaoAcademicaRight_1.gridx = 9;
		gbc_JButtonFormacaoAcademicaRight_1.gridy = 1;
		jPanelMeio.add(JButtonFormacaoAcademicaRight_1, gbc_JButtonFormacaoAcademicaRight_1);
		
		scrollPane_4 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_4 = new GridBagConstraints();
		gbc_scrollPane_4.gridwidth = 2;
		gbc_scrollPane_4.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_4.gridheight = 3;
		gbc_scrollPane_4.gridx = 10;
		gbc_scrollPane_4.gridy = 1;
		jPanelMeio.add(scrollPane_4, gbc_scrollPane_4);
		
		jListFormacaoAcademica2 = new JList(listModelFormacaoAcademica2);
		scrollPane_4.setViewportView(jListFormacaoAcademica2);
		
		jButtonPapeisExercidosLeft_1 = new JButton("<");
		jButtonPapeisExercidosLeft_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonPapeisExercidosLeftActionPerformed(e);
			}
		});
		GridBagConstraints gbc_jButtonPapeisExercidosLeft_1 = new GridBagConstraints();
		gbc_jButtonPapeisExercidosLeft_1.insets = new Insets(0, 0, 5, 5);
		gbc_jButtonPapeisExercidosLeft_1.gridx = 2;
		gbc_jButtonPapeisExercidosLeft_1.gridy = 2;
		jPanelMeio.add(jButtonPapeisExercidosLeft_1, gbc_jButtonPapeisExercidosLeft_1);
		
		jButtonFormacaoAcademicaLeft_1 = new JButton("<");
		jButtonFormacaoAcademicaLeft_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonFormacaoAcademicaLeft(e);
			}
		});
		GridBagConstraints gbc_jButtonFormacaoAcademicaLeft_1 = new GridBagConstraints();
		gbc_jButtonFormacaoAcademicaLeft_1.insets = new Insets(0, 0, 5, 5);
		gbc_jButtonFormacaoAcademicaLeft_1.gridx = 9;
		gbc_jButtonFormacaoAcademicaLeft_1.gridy = 2;
		jPanelMeio.add(jButtonFormacaoAcademicaLeft_1, gbc_jButtonFormacaoAcademicaLeft_1);
		
		jPanelBaixo = new JPanel();
		GridBagConstraints gbc_jPanelBaixo = new GridBagConstraints();
		gbc_jPanelBaixo.insets = new Insets(0, 0, 5, 0);
		gbc_jPanelBaixo.fill = GridBagConstraints.BOTH;
		gbc_jPanelBaixo.gridx = 0;
		gbc_jPanelBaixo.gridy = 3;
		add(jPanelBaixo, gbc_jPanelBaixo);
		GridBagLayout gbl_jPanelBaixo = new GridBagLayout();
		gbl_jPanelBaixo.columnWidths = new int[]{0, 37, 0, 0, 0};
		gbl_jPanelBaixo.rowHeights = new int[]{0, 0, 0};
		gbl_jPanelBaixo.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_jPanelBaixo.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		jPanelBaixo.setLayout(gbl_jPanelBaixo);
		
				
				JLabelFormacaoProfissional = new JLabel("Forma\u00E7\u00E3o Profissional");
				GridBagConstraints gbc_JLabelFormacaoProfissional = new GridBagConstraints();
				gbc_JLabelFormacaoProfissional.insets = new Insets(0, 0, 5, 5);
				gbc_JLabelFormacaoProfissional.gridx = 0;
				gbc_JLabelFormacaoProfissional.gridy = 0;
				jPanelBaixo.add(JLabelFormacaoProfissional, gbc_JLabelFormacaoProfissional);
				
				jLabelCursosCertificacoes = new JLabel("Cursos e Certifica\u00E7\u00F5es");
				GridBagConstraints gbc_jLabelCursosCertificacoes = new GridBagConstraints();
				gbc_jLabelCursosCertificacoes.insets = new Insets(0, 0, 5, 0);
				gbc_jLabelCursosCertificacoes.gridx = 2;
				gbc_jLabelCursosCertificacoes.gridy = 0;
				jPanelBaixo.add(jLabelCursosCertificacoes, gbc_jLabelCursosCertificacoes);
				
				scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.gridwidth = 2;
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
				gbc_scrollPane.gridx = 0;
				gbc_scrollPane.gridy = 1;
				jPanelBaixo.add(scrollPane, gbc_scrollPane);
				
				jTextAreaFormacaoProfissional = new JTextArea();
				scrollPane.setViewportView(jTextAreaFormacaoProfissional);
				
				scrollPane_6 = new JScrollPane();
				GridBagConstraints gbc_scrollPane_6 = new GridBagConstraints();
				gbc_scrollPane_6.gridwidth = 2;
				gbc_scrollPane_6.fill = GridBagConstraints.BOTH;
				gbc_scrollPane_6.gridx = 2;
				gbc_scrollPane_6.gridy = 1;
				jPanelBaixo.add(scrollPane_6, gbc_scrollPane_6);
				
				jTextAreaCursosCertificacoes = new JTextArea();
				scrollPane_6.setViewportView(jTextAreaCursosCertificacoes);
		
		
		//tabela
		columns = new Vector();
		data = new Vector();
		columns.add("Nome");
		columns.add("Email");
		columns.add("Conhecimento");
		columns.add("Pap\u00E9is");
		columns.add("Forma\u00E7\u00E3o Acad\u00EAmica");
		columns.add("Forma\u00E7\u00E3o Profissional");
		columns.add("Cursos e Certifica\u00E7\u00F5es");
		tableModel = MyTableModel.modelCapacidade(columns, data);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 4;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});
		btnLimpar.setMinimumSize(new Dimension(81, 23));
		btnLimpar.setMaximumSize(new Dimension(81, 23));
		btnLimpar.setPreferredSize(new Dimension(81, 23));
		GridBagConstraints gbc_btnLimpar = new GridBagConstraints();
		gbc_btnLimpar.fill = GridBagConstraints.VERTICAL;
		gbc_btnLimpar.anchor = GridBagConstraints.EAST;
		gbc_btnLimpar.insets = new Insets(0, 0, 5, 5);
		gbc_btnLimpar.gridx = 0;
		gbc_btnLimpar.gridy = 0;
		panel.add(btnLimpar, gbc_btnLimpar);
		
		btnNewButton = new JButton("Excluir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonExcluir();
			}
		});
		btnNewButton.setMinimumSize(new Dimension(81, 23));
		btnNewButton.setMaximumSize(new Dimension(81, 23));
		btnNewButton.setPreferredSize(new Dimension(81, 23));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JButtonCadastrar = new JButton("Cadastrar");
		GridBagConstraints gbc_JButtonCadastrar = new GridBagConstraints();
		gbc_JButtonCadastrar.fill = GridBagConstraints.BOTH;
		gbc_JButtonCadastrar.insets = new Insets(0, 0, 5, 0);
		gbc_JButtonCadastrar.gridx = 2;
		gbc_JButtonCadastrar.gridy = 0;
		panel.add(JButtonCadastrar, gbc_JButtonCadastrar);
		JButtonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCadastrarActionPerformed(e);
			}
		});		
		scrollPane_7 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_7 = new GridBagConstraints();
		gbc_scrollPane_7.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_7.gridwidth = 3;
		gbc_scrollPane_7.gridx = 0;
		gbc_scrollPane_7.gridy = 1;
		panel.add(scrollPane_7, gbc_scrollPane_7);
		
		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jTableMouseClicked(arg0);
			}
		});
		table.getTableHeader().setReorderingAllowed(false);
		table.setPreferredScrollableViewportSize(new Dimension(700, 200));
		scrollPane_7.setViewportView(table);
		
		ItemListener listener = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemListenerStateChaged(e);
			}
		};
		jComboBoxHumano.addItemListener(listener);	
		
		populaJList();
		populaTabela();
		populaComboBox();
		
		jComboBoxHumano.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				populaComboBox();
				
			}
		});
		 
		 
	}

	 protected void jButtonUpdateFormacaoAcademica() {
		 for(Tipo tipo: CampoDAO.findByName("Forma\u00E7\u00E3o Acad\u00EAmica").getTipo())
			 if(!listModelFormacaoAcademica1.contains(tipo) && !listModelFormacaoAcademica2.contains(tipo))
				listModelFormacaoAcademica1.addElement(tipo);
		jListFormacaoAcademica1.repaint();
		jListFormacaoAcademica1.validate();
		
	}

	protected void jButtonUpdatePapeisExercidos() {
		for(Tipo tipo: CampoDAO.findByName("Pap\u00E9is Exercidos").getTipo())
			if(!listModelPapeisExercidos1.contains(tipo) && !listModelPapeisExercidos2.contains(tipo))
				listModelPapeisExercidos1.addElement(tipo);
		jListPapeisExercidos1.repaint();
		jListPapeisExercidos1.validate();
		
	}

	protected void nameTextFieldFocusLost(FocusEvent evt) {
		 JOptionPane.showMessageDialog(null, "talvez");
		
	}

	@Override
	    public boolean requestFocusInWindow() {
	        if (super.requestFocusInWindow()) {
	            if (jListPapeisExercidos1.requestFocusInWindow()) {
	                JOptionPane.showMessageDialog(null, "something changed");
	                return true;
	            }
	        }
	        return false;
	    }

	protected void itemListenerStateChaged(ItemEvent e) {
		if(jComboBoxHumano.getSelectedItem() != null){
			if (!jComboBoxHumano.getSelectedItem().equals("Selecione...")){
				if(((Humano)jComboBoxHumano.getSelectedItem()).getEmail() != null || ((Humano)jComboBoxHumano.getSelectedItem()).getEmail() != ""){
					jTextFieldEmail.setText(((Humano)jComboBoxHumano.getSelectedItem()).getEmail());
				}
			}
		}
	}


	private void populaComboBox() {
		jComboBoxHumano.removeAllItems();
		jComboBoxHumano.addItem("Selecione...");
		for (Humano humano : HumanoDAO.findAll()) {
			if(!humano.equals(JDialog_Spider_Login.getInstance().getInstanciaLogado())){
				jComboBoxHumano.addItem(humano);
			}				
		}		
	}


	protected void clear() {
		jTextAreaConhecimento.setText("");
		jTextAreaCursosCertificacoes.setText("");
		jTextAreaFormacaoProfissional.setText("");
		jTextFieldEmail.setText("");
		jComboBoxHumano.setSelectedItem("Selecione...");
		populaJList();
		humano = null;
		capacidade = null;
		editar = false;
	}

	private void populaJListPapeis(){
		listModelPapeisExercidos1.removeAllElements();
		listModelPapeisExercidos2.removeAllElements();
		for(Tipo tipo: CampoDAO.findByName("Pap\u00E9is Exercidos").getTipo())
			listModelPapeisExercidos1.addElement(tipo);
		jListPapeisExercidos1.validate();
		jListPapeisExercidos2.validate();
		
		jListPapeisExercidos1.repaint();
		jListPapeisExercidos2.repaint();
	}

	private void populaJList() {
		listModelFormacaoAcademica1.removeAllElements();
		listModelFormacaoAcademica2.removeAllElements();
		for(Tipo tipo: CampoDAO.findByName("Forma\u00E7\u00E3o Acad\u00EAmica").getTipo())
			listModelFormacaoAcademica1.addElement(tipo);	
		jListFormacaoAcademica2.validate();
		jListFormacaoAcademica1.validate();
		populaJListPapeis();
	}

	protected void jButtonFormacaoAcademicaLeft(ActionEvent e) {
		if(!(jListFormacaoAcademica2.getSelectedIndex() == -1)){
			if(listModelFormacaoAcademica2.size()>0){
				Tipo tipo = (Tipo) jListFormacaoAcademica2.getSelectedValue();	
				listModelFormacaoAcademica2.removeElement(tipo);
				jListFormacaoAcademica2.setModel(listModelFormacaoAcademica2);
				listModelFormacaoAcademica1.addElement(tipo);
				jListFormacaoAcademica1.setModel(listModelFormacaoAcademica1);		
			}	
		} else JOptionPane.showMessageDialog(null, "Nenhum item selecionado");
	}

	protected void jButtonPapeisExercidosLeftActionPerformed(ActionEvent e) {
		if(!(jListPapeisExercidos2.getSelectedIndex() == -1)){
			if(listModelPapeisExercidos2.size()>0){
				Tipo tipo = (Tipo) jListPapeisExercidos2.getSelectedValue();	
				listModelPapeisExercidos2.removeElement(tipo);
				jListPapeisExercidos2.setModel(listModelPapeisExercidos2);
				listModelPapeisExercidos1.addElement(tipo);
				jListPapeisExercidos1.setModel(listModelPapeisExercidos1);		
			}		
		} else JOptionPane.showMessageDialog(null, "Nenhum item selecionado");
	}

	protected void jButtonPapeisExercidosRightActionPerformed(ActionEvent e) {
		if(!(jListPapeisExercidos1.getSelectedIndex() == -1)){
			if(listModelPapeisExercidos1.size()>0){
				Tipo tipo = (Tipo) jListPapeisExercidos1.getSelectedValue();	
				listModelPapeisExercidos1.removeElement(tipo);
				jListPapeisExercidos1.setModel(listModelPapeisExercidos1);
				listModelPapeisExercidos2.addElement(tipo);
				jListPapeisExercidos2.setModel(listModelPapeisExercidos2);		
			}	
		} else JOptionPane.showMessageDialog(null, "Nenhum item selecionado");
	}
	
	protected void jButtonFormacaoAcademicaRithtActionPerformed(ActionEvent arg0) {
		if(!(jListFormacaoAcademica1.getSelectedIndex() == -1)){
			if(listModelFormacaoAcademica1.size()>0){
				Tipo tipo = (Tipo) jListFormacaoAcademica1.getSelectedValue();	
				listModelFormacaoAcademica1.removeElement(tipo);
				jListFormacaoAcademica1.setModel(listModelFormacaoAcademica1);
				listModelFormacaoAcademica2.addElement(tipo);
				jListFormacaoAcademica2.setModel(listModelFormacaoAcademica2);		
			}		
		} else JOptionPane.showMessageDialog(null, "Nenhum item selecionado");
	}


	private void populaTabela() {
		tableModel.setRowCount(0);
		for (Humano humano : JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getHumano()) {
				tableModel.addRow(new Object[]{ 
						humano,
							humano.getEmail(), 
								humano.getCapacidade().getConhecimento(),
									humano.getPapeisExercidos(),
										humano.getFormacoesAcademicas(),
											humano.getCapacidade().getFormacaoProfissional(),
												humano.getCapacidade().getCursosCertificacoes()}
			);
		}
	}
	
	protected void jButtonExcluir() {
		try{
			humano = (Humano) table.getValueAt(table.getSelectedRow(), 0);
			processo.getHumano().remove(humano);
			ProcessoDAO.updateProcesso(processo);
			HumanoDAO.removeHumano(humano);
			populaTabela();
			JOptionPane.showMessageDialog(null, "Humano exclu�do.");
			clear();
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	protected void jButtonCadastrarActionPerformed(ActionEvent e) {
		if(!jTextFieldEmail.getText().equals(processo.getGerenteProcesso().getHumano().getEmail())){
			if(!(listModelPapeisExercidos2.getSize() == 0)){
				if(!(listModelFormacaoAcademica2.getSize() == 0)){					
					humano = (Humano) jComboBoxHumano.getSelectedItem();
					humano.setSenha("123456");					
					humano.setEmail(jTextFieldEmail.getText());					
					humano.setPapeisExercidos(setPapeis(humano));
					humano.setFormacoesAcademicas(setFormacoes(humano));
					
					if(editar)
						capacidade = humano.getCapacidade();
					else capacidade = new Capacidade();
					
					capacidade.setConhecimento(jTextAreaConhecimento.getText());
					capacidade.setCursosCertificacoes(jTextAreaCursosCertificacoes.getText());
					capacidade.setFormacaoProfissional(jTextAreaFormacaoProfissional.getText());
					
					humano.setCapacidade(capacidade);
					humano.getProcessos().add(processo);
					
					CapacidadeController.saveCapacidade(capacidade, false);					
					HumanoController.saveHumano(humano);
					if(!editar)
						processo.getHumano().add(humano);					
					ProcessoDAO.updateProcesso(processo);
					clear();
					populaTabela();
				}else JOptionPane.showMessageDialog(null, "� necess�rio preencher Forma\u00E7\u00E3o Acad\u00EAmica.");
			}else JOptionPane.showMessageDialog(null, "� necess�rio preencher  Pap\u00E9is Exercidos.");
		} else JOptionPane.showMessageDialog(null, "O Gerente do Processo n\u00E3o pode ser definido como Recurso Humano.");
	}

	private ArrayList<Tipo> setFormacoes(Humano humano2) {
		ArrayList<Tipo> tipos = new ArrayList<Tipo>();
		for (Object tipo : listModelFormacaoAcademica2.toArray()) {
			tipos.add((Tipo) tipo);
		}
		return tipos;
	}

	private ArrayList<Tipo> setPapeis(Humano humano2) {
		ArrayList<Tipo> tipos = new ArrayList<Tipo>();
		for (Object tipo : listModelPapeisExercidos2.toArray()) {
			tipos.add((Tipo) tipo);
		}
		return tipos;
	}

	protected void jTableMouseClicked(MouseEvent arg0) {
		if(arg0.getClickCount() == 2){
			preencheCampos((Humano)table.getValueAt(table.getSelectedRow(), 0));
			editar = true;
		}
	}

	private void preencheCampos(Humano humano) {
		jTextAreaConhecimento.setText(humano.getCapacidade().getConhecimento());
		jTextAreaCursosCertificacoes.setText(humano.getCapacidade().getCursosCertificacoes());
		jTextAreaFormacaoProfissional.setText(humano.getCapacidade().getFormacaoProfissional());
		jTextFieldEmail.setText(humano.getEmail());
		jComboBoxHumano.setSelectedItem(humano);
		
		populaJList();
		for (Tipo tipo : humano.getPapeisExercidos()) {
			listModelPapeisExercidos2.addElement(tipo);
			listModelPapeisExercidos1.removeElement(tipo);
			jListPapeisExercidos1.setModel(listModelPapeisExercidos1);
			jListPapeisExercidos2.setModel(listModelPapeisExercidos2);
		}
		
		for (Tipo tipo : humano.getFormacoesAcademicas()) {
			listModelFormacaoAcademica2.addElement(tipo);
			listModelFormacaoAcademica1.removeElement(tipo);
			jListFormacaoAcademica1.setModel(listModelFormacaoAcademica1);
			jListFormacaoAcademica2.setModel(listModelFormacaoAcademica2);
		}
	}
	
	@Override
	 public void focusGained(FocusEvent e) {
		 JOptionPane.showMessageDialog(null, "focusGained: " + e.getComponent());
	    }
	    
	    public void focusLost(FocusEvent e) {

			 JOptionPane.showMessageDialog(null, "focusLost: " + e.getComponent());
	    }
	
	
}
