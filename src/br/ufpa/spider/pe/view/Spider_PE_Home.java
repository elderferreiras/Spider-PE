package br.ufpa.spider.pe.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.miginfocom.swing.MigLayout;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.ufpa.spider.pe.model.GerenteProcesso;
import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.dao.GerenteProcessoDAO;
import br.ufpa.spider.pe.model.dao.HumanoDAO;
import br.ufpa.spider.pe.model.set.Usuario;
import br.ufpa.spider.pe.model.set.dao.UsuarioDAO;
import br.ufpa.spider.pe.view.administration.Spider_PE_Administration;
import br.ufpa.spider.pe.view.execution.gui.Spider_PE_Execution;
import br.ufpa.spider.pe.view.management.JDialogWelcome;
import br.ufpa.spider.pe.view.management.Spider_PE_Management;
import br.ufpa.spider.pe.view.util.PasswordSecurity;

public class Spider_PE_Home extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	private JTextField jTextFieldUsuario;
	private JPasswordField jTextFieldSenha;
	private JButton jButtonEntrar;
	private JLabel lblSenha;
	private JLabel lblUsurio;
	private JButton jButtonSair;

	private JLabel label;
	private JComboBox comboBox;
	private int logado = 0;

	public final static int ADMIN = 1;
	public final static int GERENTE = 2;
	public final static int RH = 3;

	public static Spider_PE_Home home = new Spider_PE_Home();
	public static Spider_PE_Management Spider_PE_Management = null;
	public static Spider_PE_Administration Spider_PE_Settings = null;
	public static Spider_PE_Execution execution = null;
	Usuario usuario;
	GerenteProcesso gerenteProcesso;
	Humano humano;

	public static void main(String[] args) {
		inicializar();
	}

	public static void inicializar() {
		try {
			if (tryConnectionWithDatabase()) {
				executar();				
			} else 	{
				Spider_PE_DatabaseConfiguration.getInstance().setVisible(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void executar() {
		JDialogWelcome.getInstance().setVisible(true);
		Thread run = new Thread(new Runnable() {
			@Override
			public void run() {
				if (UsuarioDAO.findAll().isEmpty()
						|| UsuarioDAO.findAll() == null) {
					Usuario admin = new Usuario();
					admin.setLogin("administrador.pe");
					try {
						admin.setSenha(PasswordSecurity.encrypt("jmml72"));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					admin.setNome("Administrador");
					UsuarioDAO.createUsuario(admin);
				}
				JDialogWelcome.getInstance().setVisible(false);
			}
		});

		run.start();
		
		Thread run2 = new Thread(new Runnable() {
			@Override
			public void run() {

				home.getInstance().setVisible(true);
			}
		});

		try {
			run.join();
			run2.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected static boolean tryConnectionWithDatabase() {
		if (new File("databaseConfig.xml").isFile()) {
			try {

				String DB_URL = "";
				String USER = "";
				String PASS = "";

				File fXmlFile = new File("databaseConfig.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);

				doc.getDocumentElement().normalize();

				System.out.println("Root element :"
						+ doc.getDocumentElement().getNodeName());

				NodeList nList = doc.getElementsByTagName("database");

				System.out.println("----------------------------");

				for (int temp = 0; temp < nList.getLength(); temp++) {

					Node nNode = nList.item(temp);

					System.out.println("\nCurrent Element :"
							+ nNode.getNodeName());

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;
						DB_URL = "jdbc:mysql://"
								+ eElement.getElementsByTagName("hostName").item(0).getTextContent()
								+ ":"
								+ eElement.getElementsByTagName("port").item(0)
										.getTextContent() + "/spider_pe";
						System.out.println(DB_URL);
						USER = eElement.getElementsByTagName("userName")
								.item(0).getTextContent();
						PASS = PasswordSecurity.decrypt(eElement.getElementsByTagName("password")
								.item(0).getTextContent());

					}
				}
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection(DB_URL, USER,	PASS);
				return true;
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Create the dialog.
	 */
	public Spider_PE_Home() {
		setTitle("Spider-PE - Autentica\u00E7\u00E3o");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Spider_PE_Home.class.getResource("/br/ufpa/spider/pe/view/util/img/favicon_pe.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		try {
			String nomeSO = System.getProperty("os.name");
			if(nomeSO.contains("Linux")){
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			} else if(nomeSO.contains("Windows")) {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel label = new JLabel("");
		label.setBorder(new EmptyBorder(20, 10, 20, 10));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.NORTH;
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel_1.add(label, gbc_label);
		label.setIcon(new ImageIcon(Spider_PE_Home.class.getResource("/br/ufpa/spider/pe/view/util/img/favicon.png")));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(40, 0, 30, 0));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		panel_1.add(panel, gbc_panel);
		panel.setLayout(new MigLayout("", "[45px][174.00px]", "[20px][20px][20px][25px]"));
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		lblUsurio.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblUsurio, "cell 0 0,alignx left,growy");
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Usu\u00E1rio do Processo", "Gerente do Processo", "Administrador"}));
		panel.add(comboBox, "cell 1 0,growx,aligny center");
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblLogin, "cell 0 1,alignx left,growy");
		
		jTextFieldUsuario = new JTextField();
		panel.add(jTextFieldUsuario, "cell 1 1,grow");
		jTextFieldUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblSenha, "cell 0 2,alignx left,growy");
		
		jTextFieldSenha = new JPasswordField();
		panel.add(jTextFieldSenha, "cell 1 2,grow");
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonEntrar();
			}
		});
		panel.add(btnEntrar, "cell 1 3,alignx right,growy");
		btnEntrar.setIcon(new ImageIcon(Spider_PE_Home.class.getResource("/br/ufpa/spider/pe/view/util/img/on.png")));
		setLocationRelativeTo(null);

	}

	protected void jButtonEntrar() {
		if (comboBox.getSelectedItem().toString().equals("Gerente do Processo")) {
			gerenteProcesso();
			jTextFieldSenha.setText("");
			jTextFieldUsuario.setText("");
		} else if (comboBox.getSelectedItem().toString()
				.equals("Usu\u00E1rio do Processo")) {			
			recursoHumano();
			jTextFieldSenha.setText("");
			jTextFieldUsuario.setText("");
		} else if (comboBox.getSelectedItem().toString()
				.equals("Administrador")) {
			administrador();
			jTextFieldSenha.setText("");
			jTextFieldUsuario.setText("");
		}
	}

	private void administrador() {
		if (UsuarioDAO.findByLogin(jTextFieldUsuario.getText()) == null) {
			JOptionPane.showMessageDialog(null, "Usu\u00E1rio inv\u00E1lido.");
		} else {
			usuario = UsuarioDAO.findByLogin(jTextFieldUsuario.getText());
			if (login(usuario, jTextFieldSenha.getText())) {
				dispose();
				JDialogWelcome.getInstance().setVisible(false);
				logado = ADMIN;
				Spider_PE_Settings = new Spider_PE_Administration();
				Spider_PE_Settings.setVisible(true);
			} else
				JOptionPane.showMessageDialog(null, "Senha inv\u00E1lida.");
		}
	}

	private void gerenteProcesso() {
		if (GerenteProcessoDAO.findByLogin(jTextFieldUsuario.getText()) == null) {
			JOptionPane.showMessageDialog(null, "Login inv\u00E1lido.");
		} else {
			humano = GerenteProcessoDAO
					.findByLogin(jTextFieldUsuario.getText());
			if (login(humano, jTextFieldSenha.getText())) {
				dispose();
				JDialogWelcome.getInstance().setVisible(false);
				logado = GERENTE;
				Spider_PE_Management = new Spider_PE_Management();
				Spider_PE_Management.setVisible(true);
			} else
				JOptionPane.showMessageDialog(null, "Senha inv\u00E1lida.");
		}
	}

	private void recursoHumano() {
		if (HumanoDAO.findByEmail(jTextFieldUsuario.getText()) == null) {
			JOptionPane.showMessageDialog(null, "Usu\u00E1rio inv\u00E1lido.");
		} else {
			humano = HumanoDAO.findByEmail(jTextFieldUsuario.getText());
			if (!humano.getProcessos().isEmpty()) {
				if (login(humano, jTextFieldSenha.getText())) {
					dispose();
					JDialogWelcome.getInstance().setVisible(false);

					logado = RH;
					execution = new Spider_PE_Execution();
					execution.setVisible(true);
				} else
					JOptionPane.showMessageDialog(null, "Senha inv\u00E1lida.");
			} else
				JOptionPane
						.showMessageDialog(null,
								"N\u00e3o h\u00e1 processos alocado para esse usu\u00e1rio.");
		}

		Thread t1 = new Thread(execution);
		// t1.start();
		// t1.run();
	}

	private boolean login(Humano human, String password) {
		try {
			if (PasswordSecurity.decrypt(human.getSenha()).equals(password))
				return true;
			else
				return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private boolean login(Usuario admin, String password) {
		try {
			if (PasswordSecurity.decrypt(admin.getSenha()).equals(password))
				return true;
			else
				return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public int getLogado() {
		return logado;
	}

	public synchronized static Spider_PE_Home getInstance() {
		if (home == null) {
			home = new Spider_PE_Home();
		}
		return home;
	}

	public Object getInstanciaLogado() {
		if (logado == ADMIN)
			return usuario;
		else if (logado == RH)
			return humano;
		else if (logado == GERENTE)
			return humano;
		else
			return null;
	}

	public static int getAdmin() {
		return ADMIN;
	}

	public static int getGerente() {
		return GERENTE;
	}

	public static int getRh() {
		return RH;
	}
	
	
}
