package br.ufpa.spider.pe.view.set;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.ufpa.spider.pe.model.set.SpiderMPlan;
import br.ufpa.spider.pe.model.set.dao.SpiderMPlanDAO;

public class JPanelFerramentaMedicao extends JPanel {

	/**
	 * Create the panel.
	 */
	TitledBorder border1 = new TitledBorder(null, "Configurar Spider-MPlan",  TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 15), null);
	private JTextField jTextFieldURL;
	SpiderMPlan mplan;

	public JPanelFerramentaMedicao() {
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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblNewLabel.setIcon(new ImageIcon(JPanelFerramentaMedicao.class.getResource("/br/ufpa/spider/pe/img/spider_mplan.png")));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("URL da Ferramenta");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		jTextFieldURL = new JTextField();
		GridBagConstraints gbc_jTextFieldURL = new GridBagConstraints();
		gbc_jTextFieldURL.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldURL.gridx = 1;
		gbc_jTextFieldURL.gridy = 1;
		panel.add(jTextFieldURL, gbc_jTextFieldURL);
		jTextFieldURL.setColumns(10);
		
		JButton Configurar = new JButton("Configurar");
		Configurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonConfigurar();
			}
		});
		GridBagConstraints gbc_Configurar = new GridBagConstraints();
		gbc_Configurar.anchor = GridBagConstraints.EAST;
		gbc_Configurar.gridx = 0;
		gbc_Configurar.gridy = 1;
		add(Configurar, gbc_Configurar);

		if(SpiderMPlanDAO.findAll() == null || SpiderMPlanDAO.findAll().isEmpty()){
			mplan = new SpiderMPlan();
			
		} else {
			for (SpiderMPlan mplan : SpiderMPlanDAO.findAll() ) {
				this.mplan = mplan;
				jTextFieldURL.setText(mplan.getUrlFerramenta());
			}
		}			
	}
	
	protected void jButtonConfigurar() {
		mplan.setUrlFerramenta(jTextFieldURL.getText());
		SpiderMPlanDAO.updateSpiderMPlan(mplan);
		JOptionPane.showMessageDialog(null, "Ferramenta configurada.");		
	}


}
