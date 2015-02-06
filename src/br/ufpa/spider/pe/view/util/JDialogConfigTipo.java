package br.ufpa.spider.pe.view.util;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import br.ufpa.spider.pe.controller.TipoController;
import br.ufpa.spider.pe.model.Campo;
import br.ufpa.spider.pe.model.Tipo;
import br.ufpa.spider.pe.model.dao.CampoDAO;
import br.ufpa.spider.pe.model.dao.TipoDAO;
import br.ufpa.spider.pe.model.util.ProcessManagerController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Locale;

public class JDialogConfigTipo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField jTextFieldMinuto;
	private JTextField jTextFieldHora;
	private JTextField jTextFieldDia;
	private JTextField jTextFieldMes;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialogConfigTipo dialog = new JDialogConfigTipo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDialogConfigTipo() {
		setBounds(100, 100, 240, 200);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 51, 57, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblHora = new JLabel("1 Hora");
			GridBagConstraints gbc_lblHora = new GridBagConstraints();
			gbc_lblHora.anchor = GridBagConstraints.WEST;
			gbc_lblHora.insets = new Insets(0, 0, 5, 5);
			gbc_lblHora.gridx = 0;
			gbc_lblHora.gridy = 0;
			contentPanel.add(lblHora, gbc_lblHora);
		}
		{
			JLabel jLabelImgConversion1 = new JLabel("");
			jLabelImgConversion1.setIcon(new Icone().getConversion());
			GridBagConstraints gbc_jLabelImgConversion1 = new GridBagConstraints();
			gbc_jLabelImgConversion1.insets = new Insets(0, 0, 5, 5);
			gbc_jLabelImgConversion1.gridx = 1;
			gbc_jLabelImgConversion1.gridy = 0;
			contentPanel.add(jLabelImgConversion1, gbc_jLabelImgConversion1);
		}
		{
			jTextFieldMinuto = new JTextField();
			GridBagConstraints gbc_jTextFieldMinuto = new GridBagConstraints();
			gbc_jTextFieldMinuto.insets = new Insets(0, 0, 5, 5);
			gbc_jTextFieldMinuto.fill = GridBagConstraints.HORIZONTAL;
			gbc_jTextFieldMinuto.gridx = 2;
			gbc_jTextFieldMinuto.gridy = 0;
			contentPanel.add(jTextFieldMinuto, gbc_jTextFieldMinuto);
			jTextFieldMinuto.setColumns(10);
		}
		{
			JLabel lblMinutos = new JLabel("Minuto(s)");
			GridBagConstraints gbc_lblMinutos = new GridBagConstraints();
			gbc_lblMinutos.anchor = GridBagConstraints.WEST;
			gbc_lblMinutos.insets = new Insets(0, 0, 5, 0);
			gbc_lblMinutos.gridx = 3;
			gbc_lblMinutos.gridy = 0;
			contentPanel.add(lblMinutos, gbc_lblMinutos);
		}
		{
			JLabel lblDia = new JLabel("1 Dia");
			GridBagConstraints gbc_lblDia = new GridBagConstraints();
			gbc_lblDia.anchor = GridBagConstraints.WEST;
			gbc_lblDia.insets = new Insets(0, 0, 5, 5);
			gbc_lblDia.gridx = 0;
			gbc_lblDia.gridy = 1;
			contentPanel.add(lblDia, gbc_lblDia);
		}
		{
			JLabel jLabelConversion2 = new JLabel("");
			jLabelConversion2.setIcon(new Icone().getConversion());
			GridBagConstraints gbc_jLabelConversion2 = new GridBagConstraints();
			gbc_jLabelConversion2.insets = new Insets(0, 0, 5, 5);
			gbc_jLabelConversion2.gridx = 1;
			gbc_jLabelConversion2.gridy = 1;
			contentPanel.add(jLabelConversion2, gbc_jLabelConversion2);
		}
		{
			jTextFieldHora = new JTextField();
			GridBagConstraints gbc_jTextFieldHora = new GridBagConstraints();
			gbc_jTextFieldHora.insets = new Insets(0, 0, 5, 5);
			gbc_jTextFieldHora.fill = GridBagConstraints.HORIZONTAL;
			gbc_jTextFieldHora.gridx = 2;
			gbc_jTextFieldHora.gridy = 1;
			contentPanel.add(jTextFieldHora, gbc_jTextFieldHora);
			jTextFieldHora.setColumns(10);
		}
		{
			JLabel lblHoras = new JLabel("Hora(s)");
			GridBagConstraints gbc_lblHoras = new GridBagConstraints();
			gbc_lblHoras.anchor = GridBagConstraints.WEST;
			gbc_lblHoras.insets = new Insets(0, 0, 5, 0);
			gbc_lblHoras.gridx = 3;
			gbc_lblHoras.gridy = 1;
			contentPanel.add(lblHoras, gbc_lblHoras);
		}
		{
			JLabel lblMs = new JLabel("1 Mês");
			GridBagConstraints gbc_lblMs = new GridBagConstraints();
			gbc_lblMs.anchor = GridBagConstraints.WEST;
			gbc_lblMs.insets = new Insets(0, 0, 5, 5);
			gbc_lblMs.gridx = 0;
			gbc_lblMs.gridy = 2;
			contentPanel.add(lblMs, gbc_lblMs);
		}
		{
			JLabel jLabelConversion3 = new JLabel("");
			jLabelConversion3.setIcon(new Icone().getConversion());
			GridBagConstraints gbc_jLabelConversion3 = new GridBagConstraints();
			gbc_jLabelConversion3.insets = new Insets(0, 0, 5, 5);
			gbc_jLabelConversion3.gridx = 1;
			gbc_jLabelConversion3.gridy = 2;
			contentPanel.add(jLabelConversion3, gbc_jLabelConversion3);
		}
		{
			jTextFieldDia = new JTextField();
			GridBagConstraints gbc_jTextFieldDia = new GridBagConstraints();
			gbc_jTextFieldDia.insets = new Insets(0, 0, 5, 5);
			gbc_jTextFieldDia.fill = GridBagConstraints.HORIZONTAL;
			gbc_jTextFieldDia.gridx = 2;
			gbc_jTextFieldDia.gridy = 2;
			contentPanel.add(jTextFieldDia, gbc_jTextFieldDia);
			jTextFieldDia.setColumns(10);
		}
		{
			JLabel lblDias = new JLabel("Dia(s)");
			GridBagConstraints gbc_lblDias = new GridBagConstraints();
			gbc_lblDias.anchor = GridBagConstraints.WEST;
			gbc_lblDias.insets = new Insets(0, 0, 5, 0);
			gbc_lblDias.gridx = 3;
			gbc_lblDias.gridy = 2;
			contentPanel.add(lblDias, gbc_lblDias);
		}
		{
			JLabel lblAno = new JLabel("1 Ano");
			GridBagConstraints gbc_lblAno = new GridBagConstraints();
			gbc_lblAno.anchor = GridBagConstraints.WEST;
			gbc_lblAno.insets = new Insets(0, 0, 0, 5);
			gbc_lblAno.gridx = 0;
			gbc_lblAno.gridy = 3;
			contentPanel.add(lblAno, gbc_lblAno);
		}
		{
			JLabel jLabelConversion4 = new JLabel("");
			jLabelConversion4.setIcon(new Icone().getConversion());
			GridBagConstraints gbc_jLabelConversion4 = new GridBagConstraints();
			gbc_jLabelConversion4.insets = new Insets(0, 0, 0, 5);
			gbc_jLabelConversion4.gridx = 1;
			gbc_jLabelConversion4.gridy = 3;
			contentPanel.add(jLabelConversion4, gbc_jLabelConversion4);
		}
		{
			jTextFieldMes = new JTextField();
			GridBagConstraints gbc_jTextFieldMes = new GridBagConstraints();
			gbc_jTextFieldMes.insets = new Insets(0, 0, 0, 5);
			gbc_jTextFieldMes.fill = GridBagConstraints.HORIZONTAL;
			gbc_jTextFieldMes.gridx = 2;
			gbc_jTextFieldMes.gridy = 3;
			contentPanel.add(jTextFieldMes, gbc_jTextFieldMes);
			jTextFieldMes.setColumns(10);
		}
		{
			JLabel lblMeses = new JLabel(ProcessManagerController.MESES);
			GridBagConstraints gbc_lblMeses = new GridBagConstraints();
			gbc_lblMeses.anchor = GridBagConstraints.WEST;
			gbc_lblMeses.gridx = 3;
			gbc_lblMeses.gridy = 3;
			contentPanel.add(lblMeses, gbc_lblMeses);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Salvar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						jButtonSalvar();
					}
				});
				okButton.setIcon(new Icone().getSalvar());
				okButton.setActionCommand("Salvar");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.setIcon(new Icone().getExcluir());
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						jButtonCancelar();
					}
				});
				//cancelButton.setIcon(new Icone().getClear());
				cancelButton.setActionCommand("Cancelar");
				buttonPane.add(cancelButton);
			}
			carregarInfo();
		}
	}

	protected void jButtonCancelar() {
		// TODO Auto-generated method stub
		this.dispose();
	}

	protected void jButtonSalvar() {
		Campo campo = CampoDAO.findByName("Unidade de Medida (Carga Hor\u00E1ria)");
		
		for(Tipo tipo : campo.getTipo()){
			if(tipo.getNome().equals(ProcessManagerController.HORAS)){
				tipo.setFatorConversao(Integer.parseInt(jTextFieldMinuto.getText()));
				TipoController.savetipo(tipo, false);
			} else if (tipo.getNome().equals(ProcessManagerController.DIAS)){
				tipo.setFatorConversao(Integer.parseInt(jTextFieldHora.getText()));
				TipoController.savetipo(tipo, false);
			} else if (tipo.getNome().equals(ProcessManagerController.MESES)){
				tipo.setFatorConversao(Integer.parseInt(jTextFieldDia.getText()));
				TipoController.savetipo(tipo, false);
			} else if (tipo.getNome().equals(ProcessManagerController.ANOS)){
				tipo.setFatorConversao(Integer.parseInt(jTextFieldMes.getText()));
				TipoController.savetipo(tipo, false);
			}
		}
		this.dispose();
	}
	
	protected void carregarInfo(){
		Campo campo = CampoDAO.findByName("Unidade de Medida (Carga Hor\u00E1ria)");
		
		for(Tipo tipo : campo.getTipo()){
			if(tipo.getNome().equals(ProcessManagerController.HORAS)){
				jTextFieldMinuto.setText(""+tipo.getFatorConversao());
			} else if (tipo.getNome().equals(ProcessManagerController.DIAS)){
				jTextFieldHora.setText(""+tipo.getFatorConversao());
			} else if (tipo.getNome().equals(ProcessManagerController.MESES)){
				jTextFieldDia.setText(""+tipo.getFatorConversao());
			} else if (tipo.getNome().equals(ProcessManagerController.ANOS)){
				jTextFieldMes.setText(""+tipo.getFatorConversao());
			}
		}
		this.repaint();
	}

}
