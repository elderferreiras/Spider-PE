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

import br.ufpa.spider.pe.model.set.RedMine;
import br.ufpa.spider.pe.model.set.SVN;
import br.ufpa.spider.pe.model.set.dao.RedMineDAO;
import br.ufpa.spider.pe.model.set.dao.SVNDAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelFerramentaGestaoMudanca extends JPanel {
	TitledBorder border1 = new TitledBorder(null, "Configurar SVN (Reposit\u00F3rio de Dados)",  TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 15), null);
	TitledBorder border2 = new TitledBorder(null,  "Configurar Redmine",  TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 15), null);
	private JTextField jTextFieldHost;
	private JTextField jTextFieldDiretorio;
	private JTextField jTextFieldRedmine;
	SVN svn;
	RedMine redMine;

	public JPanelFerramentaGestaoMudanca() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{148, 121, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(border1);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblNewLabel.setIcon(new ImageIcon(JPanelFerramentaGestaoMudanca.class.getResource("/br/ufpa/spider/pe/img/config/Subversion.png")));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblHost = new JLabel("Host");
		GridBagConstraints gbc_lblHost = new GridBagConstraints();
		gbc_lblHost.insets = new Insets(0, 0, 5, 5);
		gbc_lblHost.anchor = GridBagConstraints.WEST;
		gbc_lblHost.gridx = 0;
		gbc_lblHost.gridy = 1;
		panel_1.add(lblHost, gbc_lblHost);
		
		jTextFieldHost = new JTextField();
		GridBagConstraints gbc_jTextFieldHost = new GridBagConstraints();
		gbc_jTextFieldHost.insets = new Insets(0, 0, 5, 0);
		gbc_jTextFieldHost.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldHost.gridx = 1;
		gbc_jTextFieldHost.gridy = 1;
		panel_1.add(jTextFieldHost, gbc_jTextFieldHost);
		jTextFieldHost.setColumns(10);
		
		JLabel lblDiretrio = new JLabel("Diret\u00F3rio");
		GridBagConstraints gbc_lblDiretrio = new GridBagConstraints();
		gbc_lblDiretrio.insets = new Insets(0, 0, 0, 5);
		gbc_lblDiretrio.anchor = GridBagConstraints.WEST;
		gbc_lblDiretrio.gridx = 0;
		gbc_lblDiretrio.gridy = 2;
		panel_1.add(lblDiretrio, gbc_lblDiretrio);
		
		jTextFieldDiretorio = new JTextField();
		GridBagConstraints gbc_jTextFieldDiretorio = new GridBagConstraints();
		gbc_jTextFieldDiretorio.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldDiretorio.gridx = 1;
		gbc_jTextFieldDiretorio.gridy = 2;
		panel_1.add(jTextFieldDiretorio, gbc_jTextFieldDiretorio);
		jTextFieldDiretorio.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(border2);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblNewLabel_1.setIcon(new ImageIcon(JPanelFerramentaGestaoMudanca.class.getResource("/br/ufpa/spider/pe/img/redmine.png")));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblUrlDaFerramenta = new JLabel("URL da Ferramenta");
		GridBagConstraints gbc_lblUrlDaFerramenta = new GridBagConstraints();
		gbc_lblUrlDaFerramenta.insets = new Insets(0, 0, 0, 5);
		gbc_lblUrlDaFerramenta.anchor = GridBagConstraints.WEST;
		gbc_lblUrlDaFerramenta.gridx = 0;
		gbc_lblUrlDaFerramenta.gridy = 1;
		panel.add(lblUrlDaFerramenta, gbc_lblUrlDaFerramenta);
		
		jTextFieldRedmine = new JTextField();
		GridBagConstraints gbc_jTextFieldRedmine = new GridBagConstraints();
		gbc_jTextFieldRedmine.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldRedmine.gridx = 1;
		gbc_jTextFieldRedmine.gridy = 1;
		panel.add(jTextFieldRedmine, gbc_jTextFieldRedmine);
		jTextFieldRedmine.setColumns(10);
		
		JButton btnConfigurar = new JButton("Configurar");
		btnConfigurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonConfigurar();
			}
		});
		GridBagConstraints gbc_btnConfigurar = new GridBagConstraints();
		gbc_btnConfigurar.anchor = GridBagConstraints.EAST;
		gbc_btnConfigurar.gridx = 0;
		gbc_btnConfigurar.gridy = 2;
		add(btnConfigurar, gbc_btnConfigurar);
		
		if(SVNDAO.findAll() == null || SVNDAO.findAll().isEmpty()){
			svn = new SVN();
			
		} else {
			for (SVN svn : SVNDAO.findAll() ) {
				this.svn = svn;
				jTextFieldHost.setText(svn.getHost());
				jTextFieldDiretorio.setText(svn.getDiretorio());
			}
		}	
		

		if(RedMineDAO.findAll() == null || RedMineDAO.findAll().isEmpty()){
			redMine = new RedMine();
			
		} else {
			for (RedMine redMine : RedMineDAO.findAll() ) {
				this.redMine = redMine;
				jTextFieldRedmine.setText(redMine.getUrlFerramenta());			
			}
		}	

	}

	protected void jButtonConfigurar() {
		svn.setDiretorio(jTextFieldDiretorio.getText());
		svn.setHost(jTextFieldHost.getText());	
		SVNDAO.updateSVN(svn);
		
		redMine.setUrlFerramenta(jTextFieldRedmine.getText());
		RedMineDAO.updateRedMine(redMine);	
		
		JOptionPane.showMessageDialog(null, "Ferramenta configurada.");
	}
}
