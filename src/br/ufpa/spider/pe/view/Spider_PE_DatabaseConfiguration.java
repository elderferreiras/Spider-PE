package br.ufpa.spider.pe.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.hibernate.transaction.JOnASTransactionManagerLookup;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.ufpa.spider.pe.view.util.PasswordSecurity;

public class Spider_PE_DatabaseConfiguration extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldHost;
	private JTextField textFieldPort;
	private JTextField textFieldUser;
	private JLabel lblUsurio;
	private JLabel lblSenha;
	private JPasswordField textFieldPassword;
	private JButton btnConectar;
	private JLabel lblDicaGeralmenteLocalhost;
	private JLabel lblaPortaUtilizada;
	private JLabel lblDicaOUsurio;
	private JLabel lblDicaASenha;
	public static Spider_PE_DatabaseConfiguration databaseConfiguration = new Spider_PE_DatabaseConfiguration();

	/**
	 * Create the frame.
	 */
	public Spider_PE_DatabaseConfiguration() {
		setTitle("Spider-PE - Configura\u00E7\u00E3o do Banco de Dados");
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						Spider_PE_DatabaseConfiguration.class
								.getResource("/br/ufpa/spider/pe/view/util/img/favicon_pe.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		try {
			String nomeSO = System.getProperty("os.name");
			if (nomeSO.contains("Linux")) {
				UIManager
						.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			} else if (nomeSO.contains("Windows")) {
				UIManager
						.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
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
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblLocalDoBanco = new JLabel("Nome do Host");
		GridBagConstraints gbc_lblLocalDoBanco = new GridBagConstraints();
		gbc_lblLocalDoBanco.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocalDoBanco.anchor = GridBagConstraints.EAST;
		gbc_lblLocalDoBanco.gridx = 0;
		gbc_lblLocalDoBanco.gridy = 0;
		contentPane.add(lblLocalDoBanco, gbc_lblLocalDoBanco);

		textFieldHost = new JTextField();
		GridBagConstraints gbc_textFieldHost = new GridBagConstraints();
		gbc_textFieldHost.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldHost.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHost.gridx = 1;
		gbc_textFieldHost.gridy = 0;
		contentPane.add(textFieldHost, gbc_textFieldHost);
		textFieldHost.setColumns(10);

		lblDicaGeralmenteLocalhost = new JLabel(
				"Dica: o nome do host geralmente \u00E9 localhost.");
		lblDicaGeralmenteLocalhost.setFont(new Font("Tahoma", Font.ITALIC, 10));
		GridBagConstraints gbc_lblDicaGeralmenteLocalhost = new GridBagConstraints();
		gbc_lblDicaGeralmenteLocalhost.anchor = GridBagConstraints.WEST;
		gbc_lblDicaGeralmenteLocalhost.insets = new Insets(0, 0, 5, 0);
		gbc_lblDicaGeralmenteLocalhost.gridx = 1;
		gbc_lblDicaGeralmenteLocalhost.gridy = 1;
		contentPane.add(lblDicaGeralmenteLocalhost,
				gbc_lblDicaGeralmenteLocalhost);

		JLabel lblPorta = new JLabel("Porta utilizada");
		GridBagConstraints gbc_lblPorta = new GridBagConstraints();
		gbc_lblPorta.anchor = GridBagConstraints.WEST;
		gbc_lblPorta.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta.gridx = 0;
		gbc_lblPorta.gridy = 2;
		contentPane.add(lblPorta, gbc_lblPorta);

		textFieldPort = new JTextField();
		GridBagConstraints gbc_textFieldPort = new GridBagConstraints();
		gbc_textFieldPort.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPort.gridx = 1;
		gbc_textFieldPort.gridy = 2;
		contentPane.add(textFieldPort, gbc_textFieldPort);
		textFieldPort.setColumns(10);

		lblaPortaUtilizada = new JLabel(
				"Dica: a porta utilizada para se conectar ao MySQL geralmente \u00E9 a 3306.");
		lblaPortaUtilizada.setFont(new Font("Tahoma", Font.ITALIC, 10));
		GridBagConstraints gbc_lblaPortaUtilizada = new GridBagConstraints();
		gbc_lblaPortaUtilizada.anchor = GridBagConstraints.WEST;
		gbc_lblaPortaUtilizada.insets = new Insets(0, 0, 5, 0);
		gbc_lblaPortaUtilizada.gridx = 1;
		gbc_lblaPortaUtilizada.gridy = 3;
		contentPane.add(lblaPortaUtilizada, gbc_lblaPortaUtilizada);

		lblUsurio = new JLabel("Usu\u00E1rio");
		GridBagConstraints gbc_lblUsurio = new GridBagConstraints();
		gbc_lblUsurio.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsurio.anchor = GridBagConstraints.WEST;
		gbc_lblUsurio.gridx = 0;
		gbc_lblUsurio.gridy = 4;
		contentPane.add(lblUsurio, gbc_lblUsurio);

		textFieldUser = new JTextField();
		GridBagConstraints gbc_textFieldUser = new GridBagConstraints();
		gbc_textFieldUser.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUser.gridx = 1;
		gbc_textFieldUser.gridy = 4;
		contentPane.add(textFieldUser, gbc_textFieldUser);
		textFieldUser.setColumns(10);

		lblDicaOUsurio = new JLabel(
				"Dica: o usu\u00E1rio \u00E9 geralmente root.");
		lblDicaOUsurio.setFont(new Font("Tahoma", Font.ITALIC, 10));
		GridBagConstraints gbc_lblDicaOUsurio = new GridBagConstraints();
		gbc_lblDicaOUsurio.anchor = GridBagConstraints.WEST;
		gbc_lblDicaOUsurio.insets = new Insets(0, 0, 5, 0);
		gbc_lblDicaOUsurio.gridx = 1;
		gbc_lblDicaOUsurio.gridy = 5;
		contentPane.add(lblDicaOUsurio, gbc_lblDicaOUsurio);

		lblSenha = new JLabel("Senha");
		GridBagConstraints gbc_lblSenha = new GridBagConstraints();
		gbc_lblSenha.anchor = GridBagConstraints.WEST;
		gbc_lblSenha.insets = new Insets(0, 0, 5, 5);
		gbc_lblSenha.gridx = 0;
		gbc_lblSenha.gridy = 6;
		contentPane.add(lblSenha, gbc_lblSenha);

		textFieldPassword = new JPasswordField();
		GridBagConstraints gbc_textFieldPassword = new GridBagConstraints();
		gbc_textFieldPassword.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPassword.gridx = 1;
		gbc_textFieldPassword.gridy = 6;
		contentPane.add(textFieldPassword, gbc_textFieldPassword);

		lblDicaASenha = new JLabel(
				"Dica: a senha \u00E9 geralment o mesmo nome do usu\u00E1rio.");
		lblDicaASenha.setFont(new Font("Tahoma", Font.ITALIC, 10));
		GridBagConstraints gbc_lblDicaASenha = new GridBagConstraints();
		gbc_lblDicaASenha.anchor = GridBagConstraints.WEST;
		gbc_lblDicaASenha.insets = new Insets(0, 0, 5, 0);
		gbc_lblDicaASenha.gridx = 1;
		gbc_lblDicaASenha.gridy = 7;
		contentPane.add(lblDicaASenha, gbc_lblDicaASenha);

		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tryConnection(arg0);
			}
		});
		GridBagConstraints gbc_btnConectar = new GridBagConstraints();
		gbc_btnConectar.anchor = GridBagConstraints.EAST;
		gbc_btnConectar.gridx = 1;
		gbc_btnConectar.gridy = 8;
		contentPane.add(btnConectar, gbc_btnConectar);
		setLocationRelativeTo(null);
	}

	protected boolean tryConnection(ActionEvent arg0) {
		String DB_URL = "jdbc:mysql://" + textFieldHost.getText() +":" + textFieldPort.getText()+"/spider_pe";
		String USER = textFieldUser.getText();
		String PASS =  textFieldPassword.getText();	
				 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível encontrar o driver para realizar a conexão.");
			e.printStackTrace();
		}
	 
		Connection connection = null;
	 
		try {
			connection = DriverManager
			.getConnection(DB_URL,USER, PASS);
	 
		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getErrorCode() ==  1045){
				JOptionPane.showMessageDialog(null, "O acesso para o usuário foi negado. Verique se o nome do usuário e a senha estão corretos.");
			} else if (e.getErrorCode() ==  0){
				JOptionPane.showMessageDialog(null, "Não foi possivel estabelecer a conexão. Verifique se a porta ou o nome do host estão corretos.");
			} else if (e.getErrorCode() == 1049) {
				JOptionPane.showMessageDialog(null, "Não foi possivel estabelecer a conexão, pois o banco de dados spider_pe ainda não foi criado.");
			} else {
				JOptionPane.showMessageDialog(null, "Não foi possível estabelecer a conexão.");
			}
		}
	 
		if (connection != null) {
			createConfigurationFile( textFieldHost.getText(), textFieldPort.getText(), USER, PASS);
			Spider_PE_DatabaseConfiguration.getInstance().dispose();
			Spider_PE_Home.getInstance().setVisible(true);
			Spider_PE_Home.getInstance().inicializar();
			return true;
		} else {
			return false;
		}			
		
	}

	private void createConfigurationFile(String host, String port, String user,
			String password) {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("spider-pe_configuration");
			doc.appendChild(rootElement);

			// staff elements
			Element staff = doc.createElement("database");
			rootElement.appendChild(staff);

			// set attribute to staff element
			Attr attr = doc.createAttribute("id");
			attr.setValue("1");
			staff.setAttributeNode(attr);

			// shorten way
			// staff.setAttribute("id", "1");

			// firstname elements
			Element firstname = doc.createElement("hostName");
			firstname.appendChild(doc.createTextNode(host));
			staff.appendChild(firstname);

			// lastname elements
			Element lastname = doc.createElement("port");
			lastname.appendChild(doc.createTextNode(port));
			staff.appendChild(lastname);

			// nickname elements
			Element nickname = doc.createElement("userName");
			nickname.appendChild(doc.createTextNode(user));
			staff.appendChild(nickname);

			// salary elements
			Element salary = doc.createElement("password");
			try {
				salary.appendChild(doc.createTextNode(PasswordSecurity
						.encrypt(password)));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			staff.appendChild(salary);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(
					"databaseConfig.xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"Um erro ocorreu ao tentar salvar o arquivo.");
		} catch (TransformerException tfe) {
			JOptionPane.showMessageDialog(null,
					"Um erro ocorreu ao tentar salvar o arquivo.");
			tfe.printStackTrace();
		}

	}

	public synchronized static Spider_PE_DatabaseConfiguration getInstance() {
		if (databaseConfiguration == null) {
			databaseConfiguration = new Spider_PE_DatabaseConfiguration();
		}
		return databaseConfiguration;
	}

}
