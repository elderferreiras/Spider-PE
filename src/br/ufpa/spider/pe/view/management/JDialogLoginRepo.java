package br.ufpa.spider.pe.view.management;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JPasswordField;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JDialogLoginRepo extends JDialog {

	String usuario;
	String senha;
	private final JPanel contentPanel = new JPanel();
	private JTextField jTextFieldLogin;
	private JPasswordField jTextFieldSenha;
	private JCheckBox checkBoxMemorizaSenha;
	private JButton cancelButton;
	private JButton okButton;
	protected boolean cancelar = false;
	/**
	 * Create the dialog.
	 */
	public JDialogLoginRepo() {
		setModal(true);
		setTitle("Login no Reposit\u00F3rio SVN");
		setIconImage(Toolkit.getDefaultToolkit().getImage(JDialogLoginRepo.class.getResource("/br/ufpa/spider/pe/view/util/img/favicon_pe.png")));
		setBounds(100, 100, 263, 160);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblLogin = new JLabel("Login");
			GridBagConstraints gbc_lblLogin = new GridBagConstraints();
			gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
			gbc_lblLogin.anchor = GridBagConstraints.WEST;
			gbc_lblLogin.gridx = 0;
			gbc_lblLogin.gridy = 0;
			contentPanel.add(lblLogin, gbc_lblLogin);
		}
		{
			jTextFieldLogin = new JTextField();
			GridBagConstraints gbc_jTextFieldLogin = new GridBagConstraints();
			gbc_jTextFieldLogin.insets = new Insets(0, 0, 5, 0);
			gbc_jTextFieldLogin.fill = GridBagConstraints.HORIZONTAL;
			gbc_jTextFieldLogin.gridx = 1;
			gbc_jTextFieldLogin.gridy = 0;
			contentPanel.add(jTextFieldLogin, gbc_jTextFieldLogin);
			jTextFieldLogin.setColumns(10);
		}
		{
			JLabel lblSenha = new JLabel("Senha");
			GridBagConstraints gbc_lblSenha = new GridBagConstraints();
			gbc_lblSenha.insets = new Insets(0, 0, 5, 5);
			gbc_lblSenha.anchor = GridBagConstraints.WEST;
			gbc_lblSenha.gridx = 0;
			gbc_lblSenha.gridy = 1;
			contentPanel.add(lblSenha, gbc_lblSenha);
		}
		{
			jTextFieldSenha = new JPasswordField();
			GridBagConstraints gbc_jTextFieldSenha = new GridBagConstraints();
			gbc_jTextFieldSenha.insets = new Insets(0, 0, 5, 0);
			gbc_jTextFieldSenha.fill = GridBagConstraints.HORIZONTAL;
			gbc_jTextFieldSenha.gridx = 1;
			gbc_jTextFieldSenha.gridy = 1;
			contentPanel.add(jTextFieldSenha, gbc_jTextFieldSenha);
		}
		{
			checkBoxMemorizaSenha = new JCheckBox("Memorizar Senha");
			GridBagConstraints gbc_checkBoxMemorizaSenha = new GridBagConstraints();
			gbc_checkBoxMemorizaSenha.anchor = GridBagConstraints.WEST;
			gbc_checkBoxMemorizaSenha.gridx = 1;
			gbc_checkBoxMemorizaSenha.gridy = 2;
			contentPanel.add(checkBoxMemorizaSenha, gbc_checkBoxMemorizaSenha);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						jButtonOK();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelar  = true;
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	protected void jButtonOK() {
		if(checkBoxMemorizaSenha.isSelected()){
			senha = jTextFieldSenha.getText();
			usuario = jTextFieldLogin.getText();
		}
		dispose();
		
	}
	public String getUsuario() {
		return usuario;
	}
	public String getSenha() {
		return senha;
	}
	
	public boolean isCancel(){
		return cancelar;
	}

}
