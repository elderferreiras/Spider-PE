package br.ufpa.spider.pe.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import br.ufpa.spider.pe.model.Atividade;
import br.ufpa.spider.pe.model.GerenteProcesso;
import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.Transicao;
import br.ufpa.spider.pe.model.dao.AtividadeDAO;
import br.ufpa.spider.pe.model.dao.GerenteProcessoDAO;
import br.ufpa.spider.pe.model.dao.HumanoDAO;
import br.ufpa.spider.pe.model.dao.TarefaDAO;
import br.ufpa.spider.pe.model.dao.TransicaoDAO;
import br.ufpa.spider.pe.model.set.Usuario;
import br.ufpa.spider.pe.model.set.dao.UsuarioDAO;
import br.ufpa.spider.pe.view.administration.Spider_PE_Administration;
import br.ufpa.spider.pe.view.execution.gui.Spider_PE_Execution;
import br.ufpa.spider.pe.view.management.JDialogWelcome;
import br.ufpa.spider.pe.view.management.Spider_PE_Management;
import br.ufpa.spider.pe.view.util.PasswordSecurity;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

public class Spider_PE_Home extends JDialog {

	private final JPanel contentPanel = new JPanel();
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
	
	public static Spider_PE_Home home= new Spider_PE_Home();
	public static Spider_PE_Management Spider_PE_Management = null;
	public static Spider_PE_Administration Spider_PE_Settings = null;
	public static Spider_PE_Execution execution = null;
	Usuario usuario;
	GerenteProcesso gerenteProcesso;
	Humano humano;
		
	public static void main(String[] args) {
		try {
			executar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void executar() {
		JDialogWelcome.getInstance().setVisible(true);
		
		Thread run = new Thread(new Runnable() {
			@Override
			public void run() {
				
				if(UsuarioDAO.findAll().isEmpty() || UsuarioDAO.findAll() == null)
				{
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
				
				JDialogWelcome.removeProgressBar();
				JDialogWelcome.getInstance().repaint();
				home.getInstance().setVisible(true);				
			}
		});
			
		run.start();
		
		
	}

	/**
	 * Create the dialog.
	 */
	public Spider_PE_Home() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		getContentPane().setBackground(Color.WHITE);
		//this.setUndecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Spider_PE_Home.class.getResource("/br/ufpa/spider/pe/view/util/img/favicon_pe.png")));
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
		setTitle("Spider - PE (Login)");
		setBounds(100, 100, 285, 171);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{53, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 34, 32, 31, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			label = new JLabel("Tipo de Login");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 0;
			contentPanel.add(label, gbc_label);
		}
		{
			comboBox = new JComboBox();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.gridwidth = 3;
			gbc_comboBox.insets = new Insets(0, 0, 5, 0);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 0;
			contentPanel.add(comboBox, gbc_comboBox);
		}
		{
			lblUsurio = new JLabel("Usu\u00E1rio: ");
			GridBagConstraints gbc_lblUsurio = new GridBagConstraints();
			gbc_lblUsurio.insets = new Insets(0, 0, 5, 5);
			gbc_lblUsurio.anchor = GridBagConstraints.WEST;
			gbc_lblUsurio.gridx = 0;
			gbc_lblUsurio.gridy = 1;
			contentPanel.add(lblUsurio, gbc_lblUsurio);
		}
		{
			jTextFieldUsuario = new JTextField();
			GridBagConstraints gbc_jTextFieldUsuario = new GridBagConstraints();
			gbc_jTextFieldUsuario.gridwidth = 3;
			gbc_jTextFieldUsuario.insets = new Insets(0, 0, 5, 0);
			gbc_jTextFieldUsuario.fill = GridBagConstraints.HORIZONTAL;
			gbc_jTextFieldUsuario.gridx = 1;
			gbc_jTextFieldUsuario.gridy = 1;
			contentPanel.add(jTextFieldUsuario, gbc_jTextFieldUsuario);
			jTextFieldUsuario.setColumns(10);
		}
		{
			lblSenha = new JLabel("Senha:");
			GridBagConstraints gbc_lblSenha = new GridBagConstraints();
			gbc_lblSenha.insets = new Insets(0, 0, 5, 5);
			gbc_lblSenha.anchor = GridBagConstraints.WEST;
			gbc_lblSenha.gridx = 0;
			gbc_lblSenha.gridy = 2;
			contentPanel.add(lblSenha, gbc_lblSenha);
		}
		{
			jTextFieldSenha = new JPasswordField();
			jTextFieldSenha.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	                    jButtonEntrar();
	                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	                }  
				}
			});
			GridBagConstraints gbc_jTextFieldSenha = new GridBagConstraints();
			gbc_jTextFieldSenha.gridwidth = 3;
			gbc_jTextFieldSenha.insets = new Insets(0, 0, 5, 0);
			gbc_jTextFieldSenha.fill = GridBagConstraints.HORIZONTAL;
			gbc_jTextFieldSenha.gridx = 1;
			gbc_jTextFieldSenha.gridy = 2;
			contentPanel.add(jTextFieldSenha, gbc_jTextFieldSenha);
		}
		{
			jButtonSair = new JButton("Sair");
			jButtonSair.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);					
				}
			});
			jButtonSair.setIcon(new ImageIcon(Spider_PE_Home.class.getResource("/br/ufpa/spider/pe/view/util/img/off.png")));
			jButtonSair.setActionCommand("Cancel");
			GridBagConstraints gbc_jButtonSair = new GridBagConstraints();
			gbc_jButtonSair.insets = new Insets(0, 0, 0, 5);
			gbc_jButtonSair.gridx = 2;
			gbc_jButtonSair.gridy = 3;
			contentPanel.add(jButtonSair, gbc_jButtonSair);
		}
		{
			jButtonEntrar = new JButton("Entrar");
			jButtonEntrar.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode() == arg0.VK_ENTER) { 
						setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	                    jButtonEntrar();
	                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	                }  
				}
			});
			jButtonEntrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    jButtonEntrar();
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			});
			GridBagConstraints gbc_jButtonEntrar = new GridBagConstraints();
			gbc_jButtonEntrar.anchor = GridBagConstraints.EAST;
			gbc_jButtonEntrar.gridx = 3;
			gbc_jButtonEntrar.gridy = 3;
			contentPanel.add(jButtonEntrar, gbc_jButtonEntrar);
			jButtonEntrar.setIcon(new ImageIcon(Spider_PE_Home.class.getResource("/br/ufpa/spider/pe/view/util/img/on.png")));
			jButtonEntrar.setActionCommand("Cancel");
		}
		populaComboBox();
		
		jTextFieldSenha.setText("123456");
		jTextFieldUsuario.setText("elderf@gmail.com");
		comboBox.setSelectedItem("Usuário do Processo");
		/*
		jTextFieldSenha.setText("123");
		jTextFieldUsuario.setText("andrecunhas@gmail.com");
>>>>>>> .r1058
		comboBox.setSelectedItem("Gerente do Processo");
		*/

		setLocationRelativeTo(null);
		
	}

	private void populaComboBox() {
		comboBox.removeAllItems();
		comboBox.addItem("Gerente do Processo");
		comboBox.addItem("Administrador");
		comboBox.addItem("Usu\u00E1rio do Processo");
	}

	protected void jButtonEntrar() {
		if(comboBox.getSelectedItem().toString().equals("Gerente do Processo")){
			gerenteProcesso();
		} else if(comboBox.getSelectedItem().toString().equals("Usu\u00E1rio do Processo")){
			recursoHumano();
		} else if(comboBox.getSelectedItem().toString().equals("Administrador")){
			administrador();
		}
	}

	private void administrador() {
		if(UsuarioDAO.findByLogin(jTextFieldUsuario.getText())==null){
			JOptionPane.showMessageDialog(null, "Usu\u00E1rio inv\u00E1lido.");
		} else {
			usuario = UsuarioDAO.findByLogin(jTextFieldUsuario.getText());
			if(login(usuario, jTextFieldSenha.getText())){
				dispose();
				JDialogWelcome.getInstance().setVisible(false);
				logado = ADMIN;
				Spider_PE_Settings = new Spider_PE_Administration();
				Spider_PE_Settings.setVisible(true);
			}
			else JOptionPane.showMessageDialog(null, "Senha inv\u00E1lida.");		
		}
	}
	

	private void gerenteProcesso() {
		if(GerenteProcessoDAO.findByLogin(jTextFieldUsuario.getText())==null){
			JOptionPane.showMessageDialog(null, "Login inv\u00E1lido.");
		} else {
			humano = GerenteProcessoDAO.findByLogin(jTextFieldUsuario.getText());
			if(login(humano, jTextFieldSenha.getText())){
				dispose();
				JDialogWelcome.getInstance().setVisible(false);
				logado = GERENTE;
				Spider_PE_Management = new Spider_PE_Management();
				Spider_PE_Management.setVisible(true);
			}
			else JOptionPane.showMessageDialog(null, "Senha inv\u00E1lida.");		
		}
	}


	private void recursoHumano() {
		if(HumanoDAO.findByEmail(jTextFieldUsuario.getText())==null){
			JOptionPane.showMessageDialog(null, "Usu\u00E1rio inv\u00E1lido.");
		} else {
			humano = HumanoDAO.findByEmail(jTextFieldUsuario.getText());
			if(!humano.getProcessos().isEmpty()){
				if(login(humano, jTextFieldSenha.getText())){
					dispose();
					JDialogWelcome.getInstance().setVisible(false);	
					
					logado = RH;
					execution= new Spider_PE_Execution();
					execution.setVisible(true);
				}
				else JOptionPane.showMessageDialog(null, "Senha inv\u00E1lida.");		
			} else JOptionPane.showMessageDialog(null, "N\u00e3o h\u00e1 processos alocado para esse usu\u00e1rio.");
		}
		
		Thread t1 = new Thread(execution);
		//t1.start();
		//t1.run();
	}

	private boolean login(Humano human, String password) {
		try {
			if(PasswordSecurity.decrypt(human.getSenha()).equals(password))
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
			if(PasswordSecurity.decrypt(admin.getSenha()).equals(password))
				return true;
			else
				return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public int getLogado(){
		return logado;
	}	
		
	public synchronized static Spider_PE_Home getInstance() {
		if (home == null){
			home = new Spider_PE_Home();
			}
		return home;
	}
	
	public Object getInstanciaLogado(){
		if(logado == ADMIN)
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
