package br.ufpa.spider.pe.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import br.ufpa.spider.pe.controller.PoliticaOrganizacionalController;
import br.ufpa.spider.pe.controller.ProdutoTrabalhoController;
import br.ufpa.spider.pe.model.PoliticaOrganizacional;
import br.ufpa.spider.pe.model.ProdutoTrabalho;
import br.ufpa.spider.pe.model.set.SVN;
import br.ufpa.spider.pe.model.set.dao.SVNDAO;
import br.ufpa.spider.pe.model.util.ArquivoPDF;
import br.ufpa.spider.pe.model.util.DataBaseManager;
import br.ufpa.spider.pe.view.util.Icone;
import br.ufpa.spider.pe.view.util.SVNManager;
import java.awt.BorderLayout;

public class JPanelNovaPoliticaOrganizacional extends JPanel {
	private JTextField jTextFieldNomeOrganizacao;
	private JLabel jLabelNomeOrganizacao;
	private JLabel jLabelIntroducao;
	private JLabel jLabelDiretrizesGerais;
	private JScrollPane jScrollPaneDiretrizesGerais;
	private JTextArea jTextAreaDiretrizesGerais;
	private JLabel jLabelResultadosGerais;
	private JScrollPane jScrollPaneResultadosGerais;
	private JTextArea jTextAreaResultadosGerais;
	private JLabel jLabelPoliticaQualidade;
	private JScrollPane jScrollPanePoliticaQualidade;
	private JTextArea jTextAreaPoliticaQualidade;
	private JScrollPane jScrollPanePoliticaDesenvolvimento;
	private JTextArea jTextAreaPoliticaDesenvolvimento;
	private JLabel jLabelPoliticasEspecificas;
	private JScrollPane jScrollPanePoliticasEspecificas;
	private JLabel jLabelReferencias;
	private JScrollPane jScrollPaneReferencias;
	private JButton btnDefinir;
	private JTextArea jTextAreaPoliticasEspecificas;
	private JTextArea jTextAreaReferencias;
	private JTextField jTextFieldCaminho;
	private JButton jButtonVisualizarPDF;
	private JPanel jPanelNovaPolitica;
	private JScrollPane scrollPane;
	private SVNManager svnManager = new SVNManager();
	JDialogLoginRepo repo = new JDialogLoginRepo();
	ArquivoPDF pdf;
	PoliticaOrganizacional politicaOrganizacional = new PoliticaOrganizacional();
	ProdutoTrabalho produtoTrabalho = new ProdutoTrabalho();
	private JLabel jLabelPoliticaDesenvolvimento;
	private JButton btnRepobrowser;
	private JLabel lblCaminho;

	/**
	 * Create the panel.
	 */
	public JPanelNovaPoliticaOrganizacional() {
		setBorder(new EmptyBorder(25, 20, 10, 20));
		setAutoscrolls(true);
		setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		add(scrollPane);

		jPanelNovaPolitica = new JPanel();
		jPanelNovaPolitica.setBorder(null);
		scrollPane.setViewportView(jPanelNovaPolitica);
		GridBagLayout gbl_jPanelNovaPolitica = new GridBagLayout();
		gbl_jPanelNovaPolitica.columnWidths = new int[] { 52, 61, 190, 69, 0,
				174, 114, 0 };
		gbl_jPanelNovaPolitica.rowHeights = new int[] { 20, 24, 14, 23, 14, 22,
				19, 22, 19, 22, 19, 22, 19, 22, 29, 23, 0 };
		gbl_jPanelNovaPolitica.columnWeights = new double[] { 0.0, 0.0, 0.0,
				0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_jPanelNovaPolitica.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0,
				0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0,
				0.0, Double.MIN_VALUE };
		jPanelNovaPolitica.setLayout(gbl_jPanelNovaPolitica);

		jLabelNomeOrganizacao = new JLabel("Nome da Organiza\u00E7\u00E3o");
		GridBagConstraints gbc_jLabelNomeOrganizacao = new GridBagConstraints();
		gbc_jLabelNomeOrganizacao.anchor = GridBagConstraints.WEST;
		gbc_jLabelNomeOrganizacao.fill = GridBagConstraints.VERTICAL;
		gbc_jLabelNomeOrganizacao.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelNomeOrganizacao.gridwidth = 2;
		gbc_jLabelNomeOrganizacao.gridx = 0;
		gbc_jLabelNomeOrganizacao.gridy = 0;
		jPanelNovaPolitica
				.add(jLabelNomeOrganizacao, gbc_jLabelNomeOrganizacao);
		jLabelNomeOrganizacao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		// setFocusTraversalPolicy(new FocusTraversalOnArray(new
		// Component[]{lblNomeDaOrganizao, textField, lblIntroduo,
		// lblDiretrizesGerais, textArea, lblResultadosGerais, textArea_1,
		// lblPolticaDeQualidade, textArea_2, lblPolticaDeDesenvolvimento,
		// textArea_3, lblNewLabel, textArea_4, lblReferncias}));

		jTextFieldNomeOrganizacao = new JTextField();
		GridBagConstraints gbc_jTextFieldNomeOrganizacao = new GridBagConstraints();
		gbc_jTextFieldNomeOrganizacao.fill = GridBagConstraints.BOTH;
		gbc_jTextFieldNomeOrganizacao.insets = new Insets(0, 0, 5, 0);
		gbc_jTextFieldNomeOrganizacao.gridwidth = 5;
		gbc_jTextFieldNomeOrganizacao.gridx = 2;
		gbc_jTextFieldNomeOrganizacao.gridy = 0;
		jPanelNovaPolitica.add(jTextFieldNomeOrganizacao,
				gbc_jTextFieldNomeOrganizacao);
		jTextFieldNomeOrganizacao.setMargin(new Insets(4, 4, 4, 4));
		jTextFieldNomeOrganizacao.setPreferredSize(new Dimension(7, 20));
		jTextFieldNomeOrganizacao.setMaximumSize(new Dimension(8, 22));
		jTextFieldNomeOrganizacao.setMinimumSize(new Dimension(5, 20));
		jTextFieldNomeOrganizacao.setBorder(new LineBorder(Color.BLACK));
		jTextFieldNomeOrganizacao.setColumns(10);

		jLabelIntroducao = new JLabel("Introdu\u00E7\u00E3o");
		GridBagConstraints gbc_jLabelIntroducao = new GridBagConstraints();
		gbc_jLabelIntroducao.anchor = GridBagConstraints.WEST;
		gbc_jLabelIntroducao.fill = GridBagConstraints.VERTICAL;
		gbc_jLabelIntroducao.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelIntroducao.gridwidth = 2;
		gbc_jLabelIntroducao.gridx = 0;
		gbc_jLabelIntroducao.gridy = 1;
		jPanelNovaPolitica.add(jLabelIntroducao, gbc_jLabelIntroducao);
		jLabelIntroducao.setBorder(new EmptyBorder(10, 1, 0, 0));
		jLabelIntroducao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jLabelDiretrizesGerais = new JLabel("          - Diretrizes Gerais");
		GridBagConstraints gbc_jLabelDiretrizesGerais = new GridBagConstraints();
		gbc_jLabelDiretrizesGerais.anchor = GridBagConstraints.NORTHWEST;
		gbc_jLabelDiretrizesGerais.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelDiretrizesGerais.gridwidth = 3;
		gbc_jLabelDiretrizesGerais.gridx = 0;
		gbc_jLabelDiretrizesGerais.gridy = 2;
		jPanelNovaPolitica.add(jLabelDiretrizesGerais,
				gbc_jLabelDiretrizesGerais);
		jLabelDiretrizesGerais.setFont(new Font("Tahoma", Font.PLAIN, 11));

		jScrollPaneDiretrizesGerais = new JScrollPane();
		GridBagConstraints gbc_jScrollPaneDiretrizesGerais = new GridBagConstraints();
		gbc_jScrollPaneDiretrizesGerais.fill = GridBagConstraints.BOTH;
		gbc_jScrollPaneDiretrizesGerais.insets = new Insets(0, 0, 5, 0);
		gbc_jScrollPaneDiretrizesGerais.gridwidth = 7;
		gbc_jScrollPaneDiretrizesGerais.gridx = 0;
		gbc_jScrollPaneDiretrizesGerais.gridy = 3;
		jPanelNovaPolitica.add(jScrollPaneDiretrizesGerais,
				gbc_jScrollPaneDiretrizesGerais);
		jScrollPaneDiretrizesGerais
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		jTextAreaDiretrizesGerais = new JTextArea();
		jTextAreaDiretrizesGerais.setPreferredSize(new Dimension(4, 35));
		jTextAreaDiretrizesGerais.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jTextAreaDiretrizesGerais.setMinimumSize(new Dimension(6, 30));
		jTextAreaDiretrizesGerais.setLineWrap(true);
		jScrollPaneDiretrizesGerais.setViewportView(jTextAreaDiretrizesGerais);
		jTextAreaDiretrizesGerais.setAlignmentX(Component.LEFT_ALIGNMENT);
		// jTextAreaDiretrizesGerais.setDropMode(DropMode.ON);
		jTextAreaDiretrizesGerais.setBorder(new MatteBorder(1, 1, 1, 1,
				(Color) new Color(0, 0, 0)));

		jLabelResultadosGerais = new JLabel("          - Resultados Gerais");
		GridBagConstraints gbc_jLabelResultadosGerais = new GridBagConstraints();
		gbc_jLabelResultadosGerais.anchor = GridBagConstraints.NORTHWEST;
		gbc_jLabelResultadosGerais.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelResultadosGerais.gridwidth = 3;
		gbc_jLabelResultadosGerais.gridx = 0;
		gbc_jLabelResultadosGerais.gridy = 4;
		jPanelNovaPolitica.add(jLabelResultadosGerais,
				gbc_jLabelResultadosGerais);
		jLabelResultadosGerais.setBorder(new EmptyBorder(0, 0, 0, 0));
		jLabelResultadosGerais.setFont(new Font("Tahoma", Font.PLAIN, 11));

		jScrollPaneResultadosGerais = new JScrollPane();
		GridBagConstraints gbc_jScrollPaneResultadosGerais = new GridBagConstraints();
		gbc_jScrollPaneResultadosGerais.fill = GridBagConstraints.BOTH;
		gbc_jScrollPaneResultadosGerais.insets = new Insets(0, 0, 5, 0);
		gbc_jScrollPaneResultadosGerais.gridwidth = 7;
		gbc_jScrollPaneResultadosGerais.gridx = 0;
		gbc_jScrollPaneResultadosGerais.gridy = 5;
		jPanelNovaPolitica.add(jScrollPaneResultadosGerais,
				gbc_jScrollPaneResultadosGerais);
		jScrollPaneResultadosGerais
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		jTextAreaResultadosGerais = new JTextArea();
		jTextAreaResultadosGerais.setPreferredSize(new Dimension(4, 35));
		jTextAreaResultadosGerais.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jTextAreaResultadosGerais.setMargin(new Insets(2, 2, 400, 2));
		jTextAreaResultadosGerais.setMinimumSize(new Dimension(6, 22));
		jTextAreaResultadosGerais.setLineWrap(true);
		jScrollPaneResultadosGerais.setViewportView(jTextAreaResultadosGerais);
		jTextAreaResultadosGerais.setBorder(new LineBorder(new Color(0, 0, 0)));

		jLabelPoliticaQualidade = new JLabel("Pol\u00EDtica de Qualidade");
		GridBagConstraints gbc_jLabelPoliticaQualidade = new GridBagConstraints();
		gbc_jLabelPoliticaQualidade.anchor = GridBagConstraints.NORTHWEST;
		gbc_jLabelPoliticaQualidade.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelPoliticaQualidade.gridwidth = 2;
		gbc_jLabelPoliticaQualidade.gridx = 0;
		gbc_jLabelPoliticaQualidade.gridy = 6;
		jPanelNovaPolitica.add(jLabelPoliticaQualidade,
				gbc_jLabelPoliticaQualidade);
		jLabelPoliticaQualidade.setBorder(new EmptyBorder(5, 0, 0, 0));
		jLabelPoliticaQualidade.setFont(new Font("Tahoma", Font.PLAIN, 11));

		jScrollPanePoliticaQualidade = new JScrollPane();
		GridBagConstraints gbc_jScrollPanePoliticaQualidade = new GridBagConstraints();
		gbc_jScrollPanePoliticaQualidade.fill = GridBagConstraints.BOTH;
		gbc_jScrollPanePoliticaQualidade.insets = new Insets(0, 0, 5, 0);
		gbc_jScrollPanePoliticaQualidade.gridwidth = 7;
		gbc_jScrollPanePoliticaQualidade.gridx = 0;
		gbc_jScrollPanePoliticaQualidade.gridy = 7;
		jPanelNovaPolitica.add(jScrollPanePoliticaQualidade,
				gbc_jScrollPanePoliticaQualidade);
		jScrollPanePoliticaQualidade
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		jTextAreaPoliticaQualidade = new JTextArea();
		jTextAreaPoliticaQualidade.setPreferredSize(new Dimension(4, 35));
		jTextAreaPoliticaQualidade.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jTextAreaPoliticaQualidade.setMinimumSize(new Dimension(6, 22));
		jTextAreaPoliticaQualidade.setLineWrap(true);
		jScrollPanePoliticaQualidade
				.setViewportView(jTextAreaPoliticaQualidade);
		jTextAreaPoliticaQualidade
				.setBorder(new LineBorder(new Color(0, 0, 0)));

		jLabelPoliticaDesenvolvimento = new JLabel(
				"Pol\u00EDtica de Desenvolvimento");
		GridBagConstraints gbc_jLabelPoliticaDesenvolvimento = new GridBagConstraints();
		gbc_jLabelPoliticaDesenvolvimento.anchor = GridBagConstraints.NORTHWEST;
		gbc_jLabelPoliticaDesenvolvimento.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelPoliticaDesenvolvimento.gridwidth = 3;
		gbc_jLabelPoliticaDesenvolvimento.gridx = 0;
		gbc_jLabelPoliticaDesenvolvimento.gridy = 8;
		jPanelNovaPolitica.add(jLabelPoliticaDesenvolvimento,
				gbc_jLabelPoliticaDesenvolvimento);
		jLabelPoliticaDesenvolvimento.setBorder(new EmptyBorder(5, 0, 0, 0));
		jLabelPoliticaDesenvolvimento
				.setFont(new Font("Tahoma", Font.PLAIN, 11));

		jScrollPanePoliticaDesenvolvimento = new JScrollPane();
		GridBagConstraints gbc_jScrollPanePoliticaDesenvolvimento = new GridBagConstraints();
		gbc_jScrollPanePoliticaDesenvolvimento.fill = GridBagConstraints.BOTH;
		gbc_jScrollPanePoliticaDesenvolvimento.insets = new Insets(0, 0, 5, 0);
		gbc_jScrollPanePoliticaDesenvolvimento.gridwidth = 7;
		gbc_jScrollPanePoliticaDesenvolvimento.gridx = 0;
		gbc_jScrollPanePoliticaDesenvolvimento.gridy = 9;
		jPanelNovaPolitica.add(jScrollPanePoliticaDesenvolvimento,
				gbc_jScrollPanePoliticaDesenvolvimento);
		jScrollPanePoliticaDesenvolvimento
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		jTextAreaPoliticaDesenvolvimento = new JTextArea();
		jTextAreaPoliticaDesenvolvimento.setPreferredSize(new Dimension(4, 35));
		jTextAreaPoliticaDesenvolvimento.setFont(new Font("Tahoma", Font.PLAIN,
				13));
		jTextAreaPoliticaDesenvolvimento.setMinimumSize(new Dimension(6, 22));
		jTextAreaPoliticaDesenvolvimento.setLineWrap(true);
		jScrollPanePoliticaDesenvolvimento
				.setViewportView(jTextAreaPoliticaDesenvolvimento);
		jTextAreaPoliticaDesenvolvimento.setBorder(new LineBorder(new Color(0,
				0, 0)));

		jLabelPoliticasEspecificas = new JLabel(
				"Pol\u00EDticas Espec\u00EDficas por \u00C1rea de Engenharia de Software");
		GridBagConstraints gbc_jLabelPoliticasEspecificas = new GridBagConstraints();
		gbc_jLabelPoliticasEspecificas.anchor = GridBagConstraints.NORTHWEST;
		gbc_jLabelPoliticasEspecificas.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelPoliticasEspecificas.gridwidth = 3;
		gbc_jLabelPoliticasEspecificas.gridx = 0;
		gbc_jLabelPoliticasEspecificas.gridy = 10;
		jPanelNovaPolitica.add(jLabelPoliticasEspecificas,
				gbc_jLabelPoliticasEspecificas);
		jLabelPoliticasEspecificas.setBorder(new EmptyBorder(5, 0, 0, 0));
		jLabelPoliticasEspecificas.setFont(new Font("Tahoma", Font.PLAIN, 11));

		jScrollPanePoliticasEspecificas = new JScrollPane();
		GridBagConstraints gbc_jScrollPanePoliticasEspecificas = new GridBagConstraints();
		gbc_jScrollPanePoliticasEspecificas.fill = GridBagConstraints.BOTH;
		gbc_jScrollPanePoliticasEspecificas.insets = new Insets(0, 0, 5, 0);
		gbc_jScrollPanePoliticasEspecificas.gridwidth = 7;
		gbc_jScrollPanePoliticasEspecificas.gridx = 0;
		gbc_jScrollPanePoliticasEspecificas.gridy = 11;
		jPanelNovaPolitica.add(jScrollPanePoliticasEspecificas,
				gbc_jScrollPanePoliticasEspecificas);
		jScrollPanePoliticasEspecificas
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		jTextAreaPoliticasEspecificas = new JTextArea();
		jTextAreaPoliticasEspecificas.setPreferredSize(new Dimension(4, 35));
		jTextAreaPoliticasEspecificas
				.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jTextAreaPoliticasEspecificas.setMinimumSize(new Dimension(6, 22));
		jTextAreaPoliticasEspecificas.setLineWrap(true);
		jScrollPanePoliticasEspecificas
				.setViewportView(jTextAreaPoliticasEspecificas);
		jTextAreaPoliticasEspecificas.setBorder(new LineBorder(new Color(0, 0,
				0)));

		jLabelReferencias = new JLabel("Refer\u00EAncias");
		GridBagConstraints gbc_jLabelReferencias = new GridBagConstraints();
		gbc_jLabelReferencias.anchor = GridBagConstraints.WEST;
		gbc_jLabelReferencias.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelReferencias.gridwidth = 2;
		gbc_jLabelReferencias.gridx = 0;
		gbc_jLabelReferencias.gridy = 12;
		jPanelNovaPolitica.add(jLabelReferencias, gbc_jLabelReferencias);
		jLabelReferencias.setBorder(new EmptyBorder(5, 0, 0, 0));
		jLabelReferencias.setFont(new Font("Tahoma", Font.PLAIN, 11));

		jScrollPaneReferencias = new JScrollPane();
		GridBagConstraints gbc_jScrollPaneReferencias = new GridBagConstraints();
		gbc_jScrollPaneReferencias.fill = GridBagConstraints.BOTH;
		gbc_jScrollPaneReferencias.insets = new Insets(0, 0, 5, 0);
		gbc_jScrollPaneReferencias.gridwidth = 7;
		gbc_jScrollPaneReferencias.gridx = 0;
		gbc_jScrollPaneReferencias.gridy = 13;
		jPanelNovaPolitica.add(jScrollPaneReferencias,
				gbc_jScrollPaneReferencias);
		jScrollPaneReferencias
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		jTextAreaReferencias = new JTextArea();
		jTextAreaReferencias.setPreferredSize(new Dimension(4, 35));
		jTextAreaReferencias.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jTextAreaReferencias.setMargin(new Insets(50, 50, 50, 50));
		jTextAreaReferencias.setMinimumSize(new Dimension(6, 22));
		jTextAreaReferencias.setLineWrap(true);
		jScrollPaneReferencias.setViewportView(jTextAreaReferencias);
		jTextAreaReferencias.setAutoscrolls(false);
		jTextAreaReferencias.setBorder(new LineBorder(new Color(0, 0, 0)));

		lblCaminho = new JLabel("Caminho");
		GridBagConstraints gbc_lblCaminho = new GridBagConstraints();
		gbc_lblCaminho.insets = new Insets(0, 0, 5, 5);
		gbc_lblCaminho.anchor = GridBagConstraints.WEST;
		gbc_lblCaminho.gridx = 0;
		gbc_lblCaminho.gridy = 14;
		jPanelNovaPolitica.add(lblCaminho, gbc_lblCaminho);

		jTextFieldCaminho = new JTextField();
		jTextFieldCaminho.setEditable(false);
		GridBagConstraints gbc_jTextFieldCaminho = new GridBagConstraints();
		gbc_jTextFieldCaminho.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldCaminho.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldCaminho.gridwidth = 5;
		gbc_jTextFieldCaminho.gridx = 1;
		gbc_jTextFieldCaminho.gridy = 14;
		jPanelNovaPolitica.add(jTextFieldCaminho, gbc_jTextFieldCaminho);
		jTextFieldCaminho.setMaximumSize(new Dimension(8, 22));
		jTextFieldCaminho.setPreferredSize(new Dimension(7, 20));
		jTextFieldCaminho.setMinimumSize(new Dimension(5, 20));
		jTextFieldCaminho.setBorder(new LineBorder(new Color(0, 0, 0)));
		jTextFieldCaminho.setText("");
		jTextFieldCaminho.setColumns(10);

		btnRepobrowser = new JButton("Repo-Browser");
		btnRepobrowser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonRepoBrowser(arg0);
			}
		});
		GridBagConstraints gbc_btnRepobrowser = new GridBagConstraints();
		gbc_btnRepobrowser.fill = GridBagConstraints.BOTH;
		gbc_btnRepobrowser.insets = new Insets(0, 0, 5, 0);
		gbc_btnRepobrowser.gridx = 6;
		gbc_btnRepobrowser.gridy = 14;
		jPanelNovaPolitica.add(btnRepobrowser, gbc_btnRepobrowser);

		jButtonVisualizarPDF = new JButton("Visualizar PDF");
		jButtonVisualizarPDF.setIcon(new Icone().getPdf_download());
		GridBagConstraints gbc_jButtonVisualizarPDF = new GridBagConstraints();
		gbc_jButtonVisualizarPDF.fill = GridBagConstraints.BOTH;
		gbc_jButtonVisualizarPDF.insets = new Insets(0, 0, 0, 5);
		gbc_jButtonVisualizarPDF.gridx = 5;
		gbc_jButtonVisualizarPDF.gridy = 15;
		jPanelNovaPolitica.add(jButtonVisualizarPDF, gbc_jButtonVisualizarPDF);
		jButtonVisualizarPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JButtonVisualizarActionPerformed(arg0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		jButtonVisualizarPDF.setEnabled(false);

		jButtonVisualizarPDF.setMaximumSize(new Dimension(81, 23));
		jButtonVisualizarPDF.setPreferredSize(new Dimension(81, 23));
		// }

		btnDefinir = new JButton("Salvar");
		btnDefinir.setIcon(new Icone().getSalvar());
		GridBagConstraints gbc_btnDefinir = new GridBagConstraints();
		gbc_btnDefinir.fill = GridBagConstraints.BOTH;
		gbc_btnDefinir.gridx = 6;
		gbc_btnDefinir.gridy = 15;
		jPanelNovaPolitica.add(btnDefinir, gbc_btnDefinir);
		btnDefinir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JButtonCadastrarActionPerformed(arg0);
			}
		});
		preencherCampos();
		jButtonEnabled();

	}

	protected void jButtonRepoBrowser(ActionEvent arg0) {
		repo.setLocationRelativeTo(null);
		repo.setVisible(true);
		String login = repo.getUsuario();
		String senha = repo.getSenha();
		if (!repo.isCancel()) {
			if (!SVNDAO.findAll().isEmpty() || !(SVNDAO.findAll() == null)) {
				RepoBrowserDialog repositorio = new RepoBrowserDialog();
				for (SVN svn : SVNDAO.findAll()) {
					repositorio.load(svn.getHost() + svn.getDiretorio(), login,
							senha);
				}

				repositorio.setLocationRelativeTo(null);
				repositorio.setVisible(true);

				try {
					jTextFieldCaminho.setText(repositorio.getCaminho());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Nenhum caminho selecionado.");
				}
			} else
				JOptionPane.showMessageDialog(null,
						"O Reposit\u00f3rio ainda n�o est� configurado.");
		}
	}

	private void JButtonCadastrarActionPerformed(ActionEvent arg0) {

		try {
			politicaOrganizacional.setNome(jTextFieldNomeOrganizacao.getText());
			politicaOrganizacional
					.setDiretrizesGerais(jTextAreaDiretrizesGerais.getText());
			politicaOrganizacional
					.setResponsabilidadesGerais(jTextAreaResultadosGerais
							.getText());
			politicaOrganizacional
					.setPoliticaQualidade(jTextAreaPoliticaQualidade.getText());
			politicaOrganizacional
					.setPoliticaDesenvolvimento(jTextAreaPoliticaDesenvolvimento
							.getText());
			politicaOrganizacional
					.setPoliticasEspecificas(jTextAreaPoliticasEspecificas
							.getText());
			politicaOrganizacional.setReferencias(jTextAreaReferencias
					.getText());
			politicaOrganizacional
					.setVersao(politicaOrganizacional.getVersao() + 1);

			produtoTrabalho.setNome(politicaOrganizacional.getNome() + "_v"
					+ politicaOrganizacional.getVersao() + ".pdf");
			produtoTrabalho.setCaminho(jTextFieldCaminho.getText());

			ProdutoTrabalhoController.saveProdutoTrabalho(produtoTrabalho);

			politicaOrganizacional.setProdutoTrabalho(produtoTrabalho);
			politicaOrganizacional.setProcesso(JDialog_Spider_Login
					.getInstance().Spider_PE_Home.getProcesso());

			pdf = new ArquivoPDF(politicaOrganizacional, "");

			JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso()
					.setPoliticaOrganizacional(politicaOrganizacional);

			salvarRepositorio(politicaOrganizacional, pdf);
			PoliticaOrganizacionalController
					.savePoliticaOrganizacional(politicaOrganizacional);

			if (!(JDialog_Spider_Login.getInstance().Spider_PE_Home
					.getProcesso().getPoliticaOrganizacional() == null)) {
				jButtonVisualizarPDF.setEnabled(true);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"A Pol�tica Organizacional n�o foi atualizada.");
			e.printStackTrace();
		}
	}

	private void salvarRepositorio(
			PoliticaOrganizacional politicaOrganizacional, ArquivoPDF pdf) {
		
		repo.setLocationRelativeTo(null);
		repo.setVisible(true);
		String login = repo.getUsuario();
		String senha = repo.getSenha();
		if (!repo.isCancel()) {
			String nomeArquivo = politicaOrganizacional.getNome() + "_v"
					+ politicaOrganizacional.getVersao() + ".pdf";
			String url = jTextFieldCaminho.getText();
			SVNManager.saveToRepositoryPolitica(nomeArquivo, login, senha, url,
					pdf.getFile());
		} else
			JOptionPane
					.showMessageDialog(null,
							"O Reposit\u00f3rio ainda n\u00E7\u00E3 est\u00e1 configurado.");

	}

	private void JButtonVisualizarActionPerformed(ActionEvent arg0)
			throws IOException {
		repo.setLocationRelativeTo(null);
		repo.setVisible(true);
		String login = repo.getUsuario();
		String senha = repo.getSenha();
		if (!repo.isCancel()) {
			if (!SVNDAO.findAll().isEmpty() || !(SVNDAO.findAll() == null)) {
				String filePath = produtoTrabalho.getNome();
				String url = produtoTrabalho.getCaminho();
				String nameTemp = "PoliticaOrganizacional";
				SVNManager.openFile(url, filePath, login, senha, nameTemp);
			} else
				JOptionPane
						.showMessageDialog(null,
								"O Reposit\u00f3rio ainda n\u00E7\u00E3 est\u00e1 configurado.");
		}

	}

	public void preencherCampos() {
		if (JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso()
				.getPoliticaOrganizacional() != null) {
			politicaOrganizacional = JDialog_Spider_Login.getInstance().Spider_PE_Home
					.getProcesso().getPoliticaOrganizacional();
			produtoTrabalho = politicaOrganizacional.getProdutoTrabalho();
			jTextFieldNomeOrganizacao.setText(politicaOrganizacional.getNome());
			jTextAreaDiretrizesGerais.setText(politicaOrganizacional
					.getDiretrizesGerais());
			jTextAreaResultadosGerais.setText(politicaOrganizacional
					.getResponsabilidadesGerais());
			jTextAreaPoliticaQualidade.setText(politicaOrganizacional
					.getPoliticaQualidade());
			jTextAreaPoliticaDesenvolvimento.setText(politicaOrganizacional
					.getPoliticaDesenvolvimento());
			jTextAreaPoliticasEspecificas.setText(politicaOrganizacional
					.getPoliticasEspecificas());
			jTextAreaReferencias.setText(politicaOrganizacional
					.getReferencias());
			jTextFieldCaminho.setText(produtoTrabalho.getCaminho());
		}
	}

	public void jButtonEnabled() {
		if (JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso()
				.getPoliticaOrganizacional() == null)
			jButtonVisualizarPDF.setEnabled(false);
		else
			jButtonVisualizarPDF.setEnabled(true);
	}
}
