package br.ufpa.spider.pe.view.set;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.ufpa.spider.pe.model.set.SpiderCL;
import br.ufpa.spider.pe.model.set.dao.SpiderCLDAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelFerramentaAvaliacao extends JPanel {
	private JTextField jTextFieldURL;

	TitledBorder border1 = new TitledBorder(null,  "Configurar Spider-CL",  TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 15), null);
	SpiderCL cl;
	public JPanelFerramentaAvaliacao() {
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
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel label = new JLabel("");
		label.setBorder(new EmptyBorder(10, 10, 10, 10));
		label.setIcon(new ImageIcon(JPanelFerramentaAvaliacao.class.getResource("/br/ufpa/spider/pe/img/spider_cl.png")));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridwidth = 2;
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);
		
		JLabel lblUrlDaFerramenta = new JLabel("URL da Ferramenta");
		GridBagConstraints gbc_lblUrlDaFerramenta = new GridBagConstraints();
		gbc_lblUrlDaFerramenta.insets = new Insets(0, 0, 0, 5);
		gbc_lblUrlDaFerramenta.anchor = GridBagConstraints.WEST;
		gbc_lblUrlDaFerramenta.gridx = 0;
		gbc_lblUrlDaFerramenta.gridy = 1;
		panel.add(lblUrlDaFerramenta, gbc_lblUrlDaFerramenta);
		
		jTextFieldURL = new JTextField();
		GridBagConstraints gbc_jTextFieldURL = new GridBagConstraints();
		gbc_jTextFieldURL.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldURL.gridx = 1;
		gbc_jTextFieldURL.gridy = 1;
		panel.add(jTextFieldURL, gbc_jTextFieldURL);
		jTextFieldURL.setColumns(10);
		
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
		
		if(SpiderCLDAO.findAll() == null || SpiderCLDAO.findAll().isEmpty()){
			cl = new SpiderCL();
			
		} else {
			for (SpiderCL cl : SpiderCLDAO.findAll() ) {
				this.cl = cl;
				jTextFieldURL.setText(cl.getUrl_ferramenta());
			}
		}
	}
	

	protected void jButtonConfigurar() {
		cl.setUrl_ferramenta(jTextFieldURL.getText());
		SpiderCLDAO.updateSpiderCL(cl);		
		JOptionPane.showMessageDialog(null, "Ferramenta configurada.");
	}

}
