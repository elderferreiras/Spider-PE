package br.ufpa.spider.pe.view.execution.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
/**
 * 
 * @author andre
 * Classe que contém alguns componentes graficos comuns das propriedades da fase, iteracao, processo, atividade e tarefa
 */
public class jPanelPropertiesPrevivedAndReal extends JPanel {
	private JLabel jLabelDataInicioPrevisto;
	private JLabel jLabelDataInicioReal;
	private JLabel jLabelDataTerminoPrevisto;
	private JLabel jLabelDataTerminoReal; 
	private JLabel jLabelCargaHorariaPrevista;
	private JLabel jLabelCargaHorariaReal;
	/**
	 * Create the panel.
	 */
	public jPanelPropertiesPrevivedAndReal() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 116, 112, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblPrevisto = new JLabel("Previsto");
		lblPrevisto.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblPrevisto = new GridBagConstraints();
		gbc_lblPrevisto.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrevisto.gridx = 1;
		gbc_lblPrevisto.gridy = 0;
		add(lblPrevisto, gbc_lblPrevisto);
		
		JLabel lblReal = new JLabel("Real");
		lblReal.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblReal = new GridBagConstraints();
		gbc_lblReal.insets = new Insets(0, 0, 5, 0);
		gbc_lblReal.gridx = 2;
		gbc_lblReal.gridy = 0;
		add(lblReal, gbc_lblReal);
		
		JLabel lblNewLabel = new JLabel("Data de In\u00edcio:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		jLabelDataInicioPrevisto = new JLabel("00/00/0000");
		GridBagConstraints gbc_jLabelDataInicioPrevisto = new GridBagConstraints();
		gbc_jLabelDataInicioPrevisto.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelDataInicioPrevisto.gridx = 1;
		gbc_jLabelDataInicioPrevisto.gridy = 1;
		add(jLabelDataInicioPrevisto, gbc_jLabelDataInicioPrevisto);
		
		jLabelDataInicioReal = new JLabel("New Label");
		GridBagConstraints gbc_jLabelDataInicioReal = new GridBagConstraints();
		gbc_jLabelDataInicioReal.insets = new Insets(0, 0, 5, 0);
		gbc_jLabelDataInicioReal.gridx = 2;
		gbc_jLabelDataInicioReal.gridy = 1;
		add(jLabelDataInicioReal, gbc_jLabelDataInicioReal);
		
		JLabel lblDataTrmino = new JLabel("Data de T\u00e9rmino:");
		GridBagConstraints gbc_lblDataTrmino = new GridBagConstraints();
		gbc_lblDataTrmino.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataTrmino.anchor = GridBagConstraints.WEST;
		gbc_lblDataTrmino.gridx = 0;
		gbc_lblDataTrmino.gridy = 2;
		add(lblDataTrmino, gbc_lblDataTrmino);
		
		jLabelDataTerminoPrevisto = new JLabel("00/00/0000");
		GridBagConstraints gbc_jLabelDataTerminoPrevisto = new GridBagConstraints();
		gbc_jLabelDataTerminoPrevisto.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelDataTerminoPrevisto.gridx = 1;
		gbc_jLabelDataTerminoPrevisto.gridy = 2;
		add(jLabelDataTerminoPrevisto, gbc_jLabelDataTerminoPrevisto);
		
		jLabelDataTerminoReal = new JLabel("New label");
		GridBagConstraints gbc_jLabelDataTerminoReal = new GridBagConstraints();
		gbc_jLabelDataTerminoReal.insets = new Insets(0, 0, 5, 0);
		gbc_jLabelDataTerminoReal.gridx = 2;
		gbc_jLabelDataTerminoReal.gridy = 2;
		add(jLabelDataTerminoReal, gbc_jLabelDataTerminoReal);
		
		JLabel lblCargaHorria = new JLabel("Carga Hor\u00e1ria:");
		GridBagConstraints gbc_lblCargaHorria = new GridBagConstraints();
		gbc_lblCargaHorria.insets = new Insets(0, 0, 0, 5);
		gbc_lblCargaHorria.anchor = GridBagConstraints.WEST;
		gbc_lblCargaHorria.gridx = 0;
		gbc_lblCargaHorria.gridy = 3;
		add(lblCargaHorria, gbc_lblCargaHorria);
		
		jLabelCargaHorariaPrevista = new JLabel("00h");
		GridBagConstraints gbc_jLabelCargaHorariaPrevista = new GridBagConstraints();
		gbc_jLabelCargaHorariaPrevista.insets = new Insets(0, 0, 0, 5);
		gbc_jLabelCargaHorariaPrevista.gridx = 1;
		gbc_jLabelCargaHorariaPrevista.gridy = 3;
		add(jLabelCargaHorariaPrevista, gbc_jLabelCargaHorariaPrevista);
		
		jLabelCargaHorariaReal = new JLabel("New label");
		GridBagConstraints gbc_jLabelCargaHorariaReal = new GridBagConstraints();
		gbc_jLabelCargaHorariaReal.gridx = 2;
		gbc_jLabelCargaHorariaReal.gridy = 3;
		add(jLabelCargaHorariaReal, gbc_jLabelCargaHorariaReal);

	}
	public String getjLabelDataInicioPrevistoText() {
		return jLabelDataInicioPrevisto.getText();
	}
	public void setjLabelDataInicioPrevistoText(String inicioPrevisto) {
		this.jLabelDataInicioPrevisto.setText(inicioPrevisto);
	}
	public String getjLabelDataInicioRealText() {
		return jLabelDataInicioReal.getText();
	}
	public void setjLabelDataInicioRealText(String inicioReal) {
		this.jLabelDataInicioReal.setText(inicioReal);
	}
	public String getjLabelDataTerminoPrevistoText() {
		return jLabelDataTerminoPrevisto.getText();
	}
	public void setjLabelDataTerminoPrevistoText(String terminoPrevisto) {
		this.jLabelDataTerminoPrevisto.setText(terminoPrevisto);
	}
	public String getjLabelDataTerminoRealText() {
		return jLabelDataTerminoReal.getText();
	}
	public void setjLabelDataTerminoRealText(String terminoReal) {
		this.jLabelDataTerminoReal.setText(terminoReal);
	}
	public String getjLabelCargaHorariaPrevistaText() {
		return jLabelCargaHorariaPrevista.getText();
	}
	public void setjLabelCargaHorariaPrevistaText(String cargaHorariaPrevista) {
		this.jLabelCargaHorariaPrevista.setText(cargaHorariaPrevista);
	}
	public String getjLabelCargaHorariaRealText() {
		return jLabelCargaHorariaReal.getText();
	}
	public void setjLabelCargaHorariaRealText(String jLabelCargaHorariaReal) {
		this.jLabelCargaHorariaReal.setText(jLabelCargaHorariaReal);
	}
	/**
	 * Colocar todas as informações de datas e cargas horárias previstas e reais nos seus respectivos labels
	 * @param inicioPrevisto - Data de Inicio Previsto
	 * @param inicioReal - Data de Inicio Real
	 * @param terminoPrevisto - Data de Termino Previsto
	 * @param terminoReal - Data de Termino Real
	 * @param cargaPrevista - Carga Horaria Prevista
	 * @param cargaReal - Carga Horaria Real
	 */
	public void setInformacoes(String inicioPrevisto, String inicioReal, String terminoPrevisto, String terminoReal, String cargaPrevista, String cargaReal){
		setjLabelDataInicioPrevistoText(inicioPrevisto);
		setjLabelDataInicioRealText(inicioReal);
		setjLabelDataTerminoPrevistoText(terminoPrevisto);
		setjLabelDataTerminoRealText(terminoReal);
		setjLabelCargaHorariaPrevistaText(cargaPrevista);
		setjLabelCargaHorariaRealText(cargaReal);
		repaint();
	}

}
