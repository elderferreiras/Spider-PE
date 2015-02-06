package br.ufpa.spider.pe.view.set;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.border.EmptyBorder;

public class JPanelHomeAdmin extends JPanel {

	/**
	 * Create the panel.
	 */
	public JPanelHomeAdmin() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{96, 66, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblBoasVindas = new JLabel("Boas vindas");
		lblBoasVindas.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblBoasVindas = new GridBagConstraints();
		gbc_lblBoasVindas.anchor = GridBagConstraints.WEST;
		gbc_lblBoasVindas.insets = new Insets(0, 0, 5, 0);
		gbc_lblBoasVindas.gridx = 0;
		gbc_lblBoasVindas.gridy = 0;
		add(lblBoasVindas, gbc_lblBoasVindas);
		
		JLabel lblNewLabel = new JLabel("Instru\u00E7\u00F5es de Configura\u00E7\u00E3o");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);

	}

}
