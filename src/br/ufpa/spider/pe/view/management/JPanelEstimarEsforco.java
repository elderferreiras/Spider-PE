package br.ufpa.spider.pe.view.management;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.ufpa.spider.pe.model.set.FerramentaEstimativa;
import br.ufpa.spider.pe.model.set.dao.FerramentaEstimativaDAO;
import br.ufpa.spider.pe.view.util.Icone;
import java.awt.Color;

public class JPanelEstimarEsforco extends JPanel {
	private FerramentaEstimativa ferramentaEstimativa;
	/**
	 * Create the panel.
	 */
	public JPanelEstimarEsforco() {
		for (FerramentaEstimativa ferramenta : FerramentaEstimativaDAO.findAll()) {
			ferramentaEstimativa = ferramenta;
		}
		
		setBorder(new EmptyBorder(15, 15, 15, 15));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		TitledBorder border_1 = new TitledBorder(null, "Estimar Tamanho", TitledBorder.CENTER, TitledBorder.TOP, null, null);
		border_1.setTitleFont(new Font("Arial", Font.PLAIN, 18));  
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EmptyBorder(0, 0, 20, 0));
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0};
		gbl_panel_4.rowHeights = new int[]{138, 0};
		gbl_panel_4.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JPanel panel_1 = new JPanel(); 
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel_4.add(panel_1, gbc_panel_1);
		panel_1.setBorder(border_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.VERTICAL;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		panel_1.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setMargin(new Insets(10, 10, 0, 0));
		toolBar_1.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_toolBar_1 = new GridBagConstraints();
		gbc_toolBar_1.gridx = 0;
		gbc_toolBar_1.gridy = 0;
		panel_2.add(toolBar_1, gbc_toolBar_1);
		toolBar_1.setFloatable(false);
		
		JButton button = new JButton("");
		button.setToolTipText("Executa ferramenta de estimativas de Pontos por Fun\u00E7\u00E3o - APF");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
					try {
						File file = new File(ferramentaEstimativa.getApf());
						Desktop.getDesktop().open(file);
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "A Ferramenta selecionada ainda n\u00E3o est\u00e1 configurada.");
						e1.printStackTrace();	
					}	catch (IOException e2){
						JOptionPane.showMessageDialog(null, "A Ferramenta selecionada ainda n\u00E3o est\u00e1 configurada.");
						e2.printStackTrace();	
					}						
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (NullPointerException e2) {
					JOptionPane.showMessageDialog(null, "A Ferramenta selecionada ainda n\u00E3o est\u00e1 configurada.");
				}
			}
		});
		button.setIcon(new Icone().getSpider_apf());
		toolBar_1.add(button);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setEnabled(false);
		btnNewButton.setMargin(new Insets(40, 14, 2, 14));
		toolBar_1.add(btnNewButton);
		
		JButton button_1 = new JButton("");
		toolBar_1.add(button_1);
		button_1.setToolTipText("Executa ferramenta de estimativas de Pontos de Casos de Uso - UCP");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
					try {
						File file = new File(ferramentaEstimativa.getUcp());
						Desktop.getDesktop().open(file);
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "A Ferramenta selecionada ainda n\u00E3o est\u00e1 configurada.");
						e1.printStackTrace();	
					}	catch (IOException e2){
						JOptionPane.showMessageDialog(null, "A Ferramenta selecionada ainda n\u00E3o est\u00e1 configurada.");
						e2.printStackTrace();	
					}
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));		
				}  catch (NullPointerException e2) {
					JOptionPane.showMessageDialog(null, "A Ferramenta selecionada ainda n\u00E3o est\u00e1 configurada.");
				}
			}
		});
		button_1.setIcon(new Icone().getSpider_ucp());
		
		JPanel panel = new JPanel();
		TitledBorder border = new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Estimar Esfor\u00E7o e Custo", TitledBorder.CENTER, TitledBorder.TOP, null, null);
		border.setTitleFont(new Font("" +
				",", Font.PLAIN, 18));  
		panel.setBorder(border);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.VERTICAL;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		panel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JToolBar toolBar_2 = new JToolBar();
		GridBagConstraints gbc_toolBar_2 = new GridBagConstraints();
		gbc_toolBar_2.gridx = 0;
		gbc_toolBar_2.gridy = 0;
		panel_3.add(toolBar_2, gbc_toolBar_2);
		toolBar_2.setFloatable(false);
		
		JButton button_2 = new JButton("");
		button_2.setToolTipText("Executa ferramenta de estimativas de Esfor\u00E7o e Custo - CoCoMo");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					try {
						File file = new File(ferramentaEstimativa.getCocomo());					
						Desktop.getDesktop().open(file);
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "A Ferramenta selecionada ainda n\u00E3o est\u00e1 configurada.");
						e1.printStackTrace();	
					}	catch (IOException e2){
						JOptionPane.showMessageDialog(null, "A Ferramenta selecionada ainda n\u00E3o est\u00e1 configurada.");
						e2.printStackTrace();	
					}			
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (NullPointerException e ) {
					JOptionPane.showMessageDialog(null, "A Ferramenta selecionada ainda n\u00E3o est\u00e1 configurada.");
				}
			}
		});
		button_2.setIcon(new Icone().getSpider_cocom());
		toolBar_2.add(button_2);

	}

}
