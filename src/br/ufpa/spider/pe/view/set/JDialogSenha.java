package br.ufpa.spider.pe.view.set;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JPasswordField;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JLabel;

import br.ufpa.spider.pe.model.GerenteProcesso;
import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.dao.GerenteProcessoDAO;
import br.ufpa.spider.pe.model.dao.HumanoDAO;
import br.ufpa.spider.pe.model.set.Usuario;
import br.ufpa.spider.pe.model.set.dao.UsuarioDAO;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class JDialogSenha extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField jTextFieldNovaSenha;
	private JPasswordField jTextFieldRepetirSenha;
	private JPasswordField jTextFieldSenha;
	private JTextField jTextFieldLogin;
	Object usuario;
	
	/**
	 * Create the dialog.
	 * @param usuario 
	 */
	public JDialogSenha(Object object) {
		this.usuario = object;
		setIconImage(Toolkit.getDefaultToolkit().getImage(JDialogSenha.class.getResource("/br/ufpa/spider/pe/img/config/favicon_pe.png")));
		setTitle("Alterar Sennha");
		setModal(true);
		setBounds(100, 100, 324, 174);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 22, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel label = new JLabel("Login");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.anchor = GridBagConstraints.WEST;
			gbc_label.gridx = 0;
			gbc_label.gridy = 0;
			contentPanel.add(label, gbc_label);
		}
		{
			jTextFieldLogin = new JTextField();
			jTextFieldLogin.setEditable(false);
			jTextFieldLogin.setSize(new Dimension(180, 20));
			jTextFieldLogin.setPreferredSize(new Dimension(178, 20));
			jTextFieldLogin.setMinimumSize(new Dimension(145, 20));
			jTextFieldLogin.setMaximumSize(new Dimension(180, 20));
			jTextFieldLogin.setColumns(10);
			jTextFieldLogin.setAlignmentY(0.0f);
			jTextFieldLogin.setAlignmentX(0.0f);
			GridBagConstraints gbc_jTextFieldLogin = new GridBagConstraints();
			gbc_jTextFieldLogin.insets = new Insets(0, 0, 5, 0);
			gbc_jTextFieldLogin.fill = GridBagConstraints.HORIZONTAL;
			gbc_jTextFieldLogin.gridx = 1;
			gbc_jTextFieldLogin.gridy = 0;
			contentPanel.add(jTextFieldLogin, gbc_jTextFieldLogin);
		}
		{
			JLabel lblSenhaAtual = new JLabel("Senha Atual");
			GridBagConstraints gbc_lblSenhaAtual = new GridBagConstraints();
			gbc_lblSenhaAtual.insets = new Insets(0, 0, 5, 5);
			gbc_lblSenhaAtual.anchor = GridBagConstraints.WEST;
			gbc_lblSenhaAtual.gridx = 0;
			gbc_lblSenhaAtual.gridy = 1;
			contentPanel.add(lblSenhaAtual, gbc_lblSenhaAtual);
		}
		{
			jTextFieldSenha = new JPasswordField();
			jTextFieldSenha.setPreferredSize(new Dimension(145, 20));
			jTextFieldSenha.setMinimumSize(new Dimension(87, 20));
			GridBagConstraints gbc_jTextFieldSenha = new GridBagConstraints();
			gbc_jTextFieldSenha.insets = new Insets(0, 0, 5, 0);
			gbc_jTextFieldSenha.fill = GridBagConstraints.HORIZONTAL;
			gbc_jTextFieldSenha.gridx = 1;
			gbc_jTextFieldSenha.gridy = 1;
			contentPanel.add(jTextFieldSenha, gbc_jTextFieldSenha);
		}
		{
			JLabel lblNovaSenha = new JLabel("Nova Senha");
			GridBagConstraints gbc_lblNovaSenha = new GridBagConstraints();
			gbc_lblNovaSenha.insets = new Insets(0, 0, 5, 5);
			gbc_lblNovaSenha.anchor = GridBagConstraints.WEST;
			gbc_lblNovaSenha.gridx = 0;
			gbc_lblNovaSenha.gridy = 2;
			contentPanel.add(lblNovaSenha, gbc_lblNovaSenha);
		}
		{
			jTextFieldRepetirSenha = new JPasswordField();
			jTextFieldRepetirSenha.setPreferredSize(new Dimension(145, 20));
			GridBagConstraints gbc_jTextFieldRepetirSenha = new GridBagConstraints();
			gbc_jTextFieldRepetirSenha.insets = new Insets(0, 0, 5, 0);
			gbc_jTextFieldRepetirSenha.fill = GridBagConstraints.HORIZONTAL;
			gbc_jTextFieldRepetirSenha.gridx = 1;
			gbc_jTextFieldRepetirSenha.gridy = 2;
			contentPanel.add(jTextFieldRepetirSenha, gbc_jTextFieldRepetirSenha);
		}
		{
			JLabel lblRepetirSenha = new JLabel("Repetir Senha");
			GridBagConstraints gbc_lblRepetirSenha = new GridBagConstraints();
			gbc_lblRepetirSenha.insets = new Insets(0, 0, 5, 5);
			gbc_lblRepetirSenha.anchor = GridBagConstraints.WEST;
			gbc_lblRepetirSenha.gridx = 0;
			gbc_lblRepetirSenha.gridy = 3;
			contentPanel.add(lblRepetirSenha, gbc_lblRepetirSenha);
		}
		{
			jTextFieldNovaSenha = new JPasswordField();
			jTextFieldNovaSenha.setPreferredSize(new Dimension(145, 20));
			GridBagConstraints gbc_jTextFieldNovaSenha = new GridBagConstraints();
			gbc_jTextFieldNovaSenha.insets = new Insets(0, 0, 5, 0);
			gbc_jTextFieldNovaSenha.fill = GridBagConstraints.HORIZONTAL;
			gbc_jTextFieldNovaSenha.gridx = 1;
			gbc_jTextFieldNovaSenha.gridy = 3;
			contentPanel.add(jTextFieldNovaSenha, gbc_jTextFieldNovaSenha);
		}
		{
			JButton button = new JButton("Alterar");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (usuario instanceof Usuario)
						jButtonAlterarUsuario();
					else if (usuario instanceof Humano)
						jButtonAlterarHumano();
				}
			});
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.anchor = GridBagConstraints.EAST;
			gbc_button.gridx = 1;
			gbc_button.gridy = 4;
			contentPanel.add(button, gbc_button);
		}
		
		if (usuario instanceof Usuario)
			jTextFieldLogin.setText(((Usuario) usuario).getLogin());
		else if (usuario instanceof Humano)
			jTextFieldLogin.setText(((Humano) usuario).getEmail());
		
	}

	protected void jButtonAlterarHumano() {
		Humano humano = (Humano) usuario;
		if(jTextFieldSenha.getText().equals(humano.getSenha())){
			if(jTextFieldNovaSenha.getText().equals(jTextFieldRepetirSenha.getText())){
				humano.setSenha(jTextFieldNovaSenha.getText());
				humano.setEmail(jTextFieldLogin.getText());
				HumanoDAO.updateHumano(humano);
				JOptionPane.showMessageDialog(null, "Informações atualizadas.");
				dispose();
			} else JOptionPane.showMessageDialog(null, "Senhas não conferem.");
		} else JOptionPane.showMessageDialog(null, "Senha antiga digitada errada.");
	
	}

	protected void jButtonAlterarUsuario() {
		Usuario admin = (Usuario) usuario;
		if(jTextFieldSenha.getText().equals(admin.getSenha())){
			if(jTextFieldNovaSenha.getText().equals(jTextFieldRepetirSenha.getText())){
				admin.setSenha(jTextFieldNovaSenha.getText());
				admin.setLogin(jTextFieldLogin.getText());
				UsuarioDAO.updateUsuario(admin);
				JOptionPane.showMessageDialog(null, "Informações atualizadas.");
				dispose();
			} else JOptionPane.showMessageDialog(null, "Senhas não conferem.");
		} else JOptionPane.showMessageDialog(null, "Senha antiga digitada errada.");
	
	}

}
