package br.ufpa.spider.pe.view.administration;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.ufpa.spider.pe.model.set.FerramentaEstimativa;
import br.ufpa.spider.pe.model.set.dao.FerramentaEstimativaDAO;
import java.awt.event.ActionListener;

public class JPanelFerramentaEstimativa extends JPanel {
	private JTextField jTextFieldAPF;
	private JTextField jTextFieldUCP;
	private JTextField jTextFieldCoCoMo;
	TitledBorder border1 = new TitledBorder(null, "Configurar Spider-APF (Pontos Por Fun\u00E7\u00E3o)",  TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 15), null);
	TitledBorder border2 = new TitledBorder(null, "Configurar Spider-UCP (Pontos por Caso de Uso)",  TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 15), null);
	TitledBorder border3 = new TitledBorder(null, "Configurar Spider-CoCoMo (Constructive Cost Model)",  TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 15), null);
	private FerramentaEstimativa ferramentaEstimativa;
	/**
	 * Create the panel.
	 */
	public JPanelFerramentaEstimativa() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(border1);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		panel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 318, 27, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(JPanelFerramentaEstimativa.class.getResource("/br/ufpa/spider/pe/view/util/img/logo_spider-apf.png")));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_3.add(lblNewLabel, gbc_lblNewLabel);
		
		jTextFieldAPF = new JTextField();
		GridBagConstraints gbc_jTextFieldAPF = new GridBagConstraints();
		gbc_jTextFieldAPF.gridwidth = 2;
		gbc_jTextFieldAPF.insets = new Insets(0, 0, 0, 5);
		gbc_jTextFieldAPF.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldAPF.gridx = 0;
		gbc_jTextFieldAPF.gridy = 1;
		panel_3.add(jTextFieldAPF, gbc_jTextFieldAPF);
		jTextFieldAPF.setColumns(10);
		
		JButton button = new JButton("...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getCaminhoAPF();
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.gridx = 2;
		gbc_button.gridy = 1;
		panel_3.add(button, gbc_button);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(border2);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(JPanelFerramentaEstimativa.class.getResource("/br/ufpa/spider/pe/view/util/img/logo_spider-ucp.png")));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridwidth = 3;
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel_2.add(label, gbc_label);
		
		jTextFieldUCP = new JTextField();
		GridBagConstraints gbc_jTextFieldUCP = new GridBagConstraints();
		gbc_jTextFieldUCP.gridwidth = 2;
		gbc_jTextFieldUCP.insets = new Insets(0, 0, 0, 5);
		gbc_jTextFieldUCP.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldUCP.gridx = 0;
		gbc_jTextFieldUCP.gridy = 1;
		panel_2.add(jTextFieldUCP, gbc_jTextFieldUCP);
		jTextFieldUCP.setColumns(10);
		
		JButton button_1 = new JButton("...");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCaminhoUCP();
			}
		});
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.gridx = 2;
		gbc_button_1.gridy = 1;
		panel_2.add(button_1, gbc_button_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(border3);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(JPanelFerramentaEstimativa.class.getResource("/br/ufpa/spider/pe/view/util/img/logo_spider-cocomo.png")));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridwidth = 3;
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 0;
		panel_1.add(label_1, gbc_label_1);
		
		jTextFieldCoCoMo = new JTextField();
		jTextFieldCoCoMo.setColumns(10);
		GridBagConstraints gbc_jTextFieldCoCoMo = new GridBagConstraints();
		gbc_jTextFieldCoCoMo.gridwidth = 2;
		gbc_jTextFieldCoCoMo.insets = new Insets(0, 0, 0, 5);
		gbc_jTextFieldCoCoMo.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldCoCoMo.gridx = 0;
		gbc_jTextFieldCoCoMo.gridy = 1;
		panel_1.add(jTextFieldCoCoMo, gbc_jTextFieldCoCoMo);
		
		JButton button_2 = new JButton("...");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCaminhoCoCoMo();
			}
		});
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.gridx = 2;
		gbc_button_2.gridy = 1;
		panel_1.add(button_2, gbc_button_2);
		
		JButton btnConfigurar = new JButton("Configurar");
		btnConfigurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonFechar(e);
			}
		});
		GridBagConstraints gbc_btnConfigurar = new GridBagConstraints();
		gbc_btnConfigurar.anchor = GridBagConstraints.EAST;
		gbc_btnConfigurar.gridx = 0;
		gbc_btnConfigurar.gridy = 3;
		panel.add(btnConfigurar, gbc_btnConfigurar);
		
		if(FerramentaEstimativaDAO.findAll() == null || FerramentaEstimativaDAO.findAll().isEmpty()){
			ferramentaEstimativa = new FerramentaEstimativa();
			jTextFieldAPF.setText("");
			jTextFieldCoCoMo.setText("");
			jTextFieldUCP.setText("");
			
		} else {
			for (FerramentaEstimativa ferramenta : FerramentaEstimativaDAO.findAll() ) {
				ferramentaEstimativa = ferramenta;
				jTextFieldAPF.setText(ferramenta.getApf());
				jTextFieldCoCoMo.setText(ferramenta.getCocomo());
				jTextFieldUCP.setText(ferramenta.getUcp());
			}
		}		
	}
		
		protected void jButtonFechar(ActionEvent e) {
			ferramentaEstimativa.setApf(jTextFieldAPF.getText());
			ferramentaEstimativa.setCocomo(jTextFieldCoCoMo.getText());
			ferramentaEstimativa.setUcp(jTextFieldUCP.getText());
			FerramentaEstimativaDAO.updateFerramentaEstimativa(ferramentaEstimativa);
			JOptionPane.showMessageDialog(null, "Ferramentas configuradas.");
		}
		protected void getCaminhoAPF() {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter(){  
				   public boolean accept(File f){  
				      return (f.getName().endsWith(".jar")) || f.isDirectory();  
				   }  
				   public String getDescription(){  
				       return "*.jar";  
				   }  
				}); 
			int returnVal = fileChooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				jTextFieldAPF.setText(fileChooser.getSelectedFile().getAbsolutePath());
		    } else {
		    	JOptionPane.showMessageDialog(null, "Nenhum arquivo foi selecionado.");
		    }
			
		}
		
		protected void getCaminhoUCP() {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter(){  
				   public boolean accept(File f){  
				      return (f.getName().endsWith(".jar")) || f.isDirectory();  
				   }  
				   public String getDescription(){  
				       return "*.jar";  
				   }  
				}); 
			int returnVal = fileChooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				jTextFieldUCP.setText(fileChooser.getSelectedFile().getAbsolutePath());
		    } else {
		    	JOptionPane.showMessageDialog(null, "Nenhum arquivo foi selecionado.");
		    }
			
		}
		
		protected void getCaminhoCoCoMo() {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter(){  
				   public boolean accept(File f){  
				      return (f.getName().endsWith(".jar")) || f.isDirectory();  
				   }  
				   public String getDescription(){  
				       return "*.jar";  
				   }  
				}); 
			int returnVal = fileChooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				jTextFieldCoCoMo.setText(fileChooser.getSelectedFile().getAbsolutePath());
		    } else {
		    	JOptionPane.showMessageDialog(null, "Nenhum arquivo foi selecionado.");
		    }
			
		}

}
