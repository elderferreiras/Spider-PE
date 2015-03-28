package br.ufpa.spider.pe.view.administration;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.ufpa.spider.pe.model.set.Email;
import br.ufpa.spider.pe.model.set.dao.EmailDAO;
import br.ufpa.spider.pe.view.util.Icone;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelFerramentaComunicacao extends JPanel {
	private JPasswordField jTextFieldSenha;
	private JTextField jTextFieldUsuario;
	private JTextField jTextFieldPorta;
	private JTextField jTextFieldServidorEnvio;
	Email email;
	TitledBorder border1 = new TitledBorder(null,  "Configurar E-mail",  TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 15), null);
	private JComboBox jComboBoxProtocolo;
	public JPanelFerramentaComunicacao() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBorder(border1);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblNewLabel.setIcon(new Icone().getMailMarkRead());
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblServidorDeEnvio = new JLabel("Servidor de Envio");
		GridBagConstraints gbc_lblServidorDeEnvio = new GridBagConstraints();
		gbc_lblServidorDeEnvio.insets = new Insets(0, 0, 5, 5);
		gbc_lblServidorDeEnvio.anchor = GridBagConstraints.WEST;
		gbc_lblServidorDeEnvio.gridx = 0;
		gbc_lblServidorDeEnvio.gridy = 1;
		panel.add(lblServidorDeEnvio, gbc_lblServidorDeEnvio);
		
		jTextFieldServidorEnvio = new JTextField();
		GridBagConstraints gbc_jTextFieldServidorEnvio = new GridBagConstraints();
		gbc_jTextFieldServidorEnvio.insets = new Insets(0, 0, 5, 0);
		gbc_jTextFieldServidorEnvio.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldServidorEnvio.gridx = 1;
		gbc_jTextFieldServidorEnvio.gridy = 1;
		panel.add(jTextFieldServidorEnvio, gbc_jTextFieldServidorEnvio);
		jTextFieldServidorEnvio.setColumns(10);
		
		JLabel lblPorta = new JLabel("Porta");
		GridBagConstraints gbc_lblPorta = new GridBagConstraints();
		gbc_lblPorta.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta.anchor = GridBagConstraints.WEST;
		gbc_lblPorta.gridx = 0;
		gbc_lblPorta.gridy = 2;
		panel.add(lblPorta, gbc_lblPorta);
		
		jTextFieldPorta = new JTextField();
		jTextFieldPorta.setMaximumSize(new Dimension(100, 20));
		jTextFieldPorta.setMinimumSize(new Dimension(100, 20));
		jTextFieldPorta.setPreferredSize(new Dimension(100, 20));
		GridBagConstraints gbc_jTextFieldPorta = new GridBagConstraints();
		gbc_jTextFieldPorta.anchor = GridBagConstraints.WEST;
		gbc_jTextFieldPorta.insets = new Insets(0, 0, 5, 0);
		gbc_jTextFieldPorta.gridx = 1;
		gbc_jTextFieldPorta.gridy = 2;
		panel.add(jTextFieldPorta, gbc_jTextFieldPorta);
		jTextFieldPorta.setColumns(10);
		
		JLabel lblProtocolo = new JLabel("Protocolo");
		GridBagConstraints gbc_lblProtocolo = new GridBagConstraints();
		gbc_lblProtocolo.insets = new Insets(0, 0, 5, 5);
		gbc_lblProtocolo.anchor = GridBagConstraints.WEST;
		gbc_lblProtocolo.gridx = 0;
		gbc_lblProtocolo.gridy = 3;
		panel.add(lblProtocolo, gbc_lblProtocolo);
		
		jComboBoxProtocolo = new JComboBox();
		jComboBoxProtocolo.setPreferredSize(new Dimension(87, 20));
		GridBagConstraints gbc_jComboBoxProtocolo = new GridBagConstraints();
		gbc_jComboBoxProtocolo.anchor = GridBagConstraints.WEST;
		gbc_jComboBoxProtocolo.insets = new Insets(0, 0, 5, 0);
		gbc_jComboBoxProtocolo.gridx = 1;
		gbc_jComboBoxProtocolo.gridy = 3;
		panel.add(jComboBoxProtocolo, gbc_jComboBoxProtocolo);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		GridBagConstraints gbc_lblUsurio = new GridBagConstraints();
		gbc_lblUsurio.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsurio.anchor = GridBagConstraints.WEST;
		gbc_lblUsurio.gridx = 0;
		gbc_lblUsurio.gridy = 4;
		panel.add(lblUsurio, gbc_lblUsurio);
		
		jTextFieldUsuario = new JTextField();
		GridBagConstraints gbc_jTextFieldUsuario = new GridBagConstraints();
		gbc_jTextFieldUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_jTextFieldUsuario.gridx = 1;
		gbc_jTextFieldUsuario.gridy = 4;
		panel.add(jTextFieldUsuario, gbc_jTextFieldUsuario);
		jTextFieldUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		GridBagConstraints gbc_lblSenha = new GridBagConstraints();
		gbc_lblSenha.insets = new Insets(0, 0, 0, 5);
		gbc_lblSenha.anchor = GridBagConstraints.WEST;
		gbc_lblSenha.gridx = 0;
		gbc_lblSenha.gridy = 5;
		panel.add(lblSenha, gbc_lblSenha);
		
		jTextFieldSenha = new JPasswordField();
		GridBagConstraints gbc_jTextFieldSenha = new GridBagConstraints();
		gbc_jTextFieldSenha.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldSenha.gridx = 1;
		gbc_jTextFieldSenha.gridy = 5;
		panel.add(jTextFieldSenha, gbc_jTextFieldSenha);
		
		JButton btnConfigurar = new JButton("Configurar");
		btnConfigurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonConfigurar();
			}
		});
		GridBagConstraints gbc_btnConfigurar = new GridBagConstraints();
		gbc_btnConfigurar.anchor = GridBagConstraints.EAST;
		gbc_btnConfigurar.gridx = 0;
		gbc_btnConfigurar.gridy = 1;
		add(btnConfigurar, gbc_btnConfigurar);
		

		populaComboBox();
		
		if(EmailDAO.findAll() == null || EmailDAO.findAll().isEmpty()){
			email = new Email();
			
		} else {
			for (Email email : EmailDAO.findAll() ) {
				this.email = email;
				jTextFieldPorta.setText(email.getPorta());
				jTextFieldSenha.setText(email.getSenha());
				jTextFieldServidorEnvio.setText(email.getServidorEnvio());
				jTextFieldUsuario.setText(email.getUsuario());
				jComboBoxProtocolo.setSelectedItem(email.getProtocolo());
			}
		}	
	}
	
	private void populaComboBox() {
		jComboBoxProtocolo.removeAllItems();
		jComboBoxProtocolo.addItem("SMTP");	
		jComboBoxProtocolo.addItem("SMTPS");
	}

	protected void jButtonConfigurar() {
		email.setPorta(jTextFieldPorta.getText());
		email.setProtocolo(jComboBoxProtocolo.getSelectedItem().toString());
		email.setSenha(jTextFieldSenha.getText());
		email.setServidorEnvio(jTextFieldServidorEnvio.getText());
		email.setUsuario(jTextFieldUsuario.getText());
		EmailDAO.updateEmail(email);
		JOptionPane.showMessageDialog(null, "Ferramenta configurada.");
	}

}
