package br.ufpa.spider.pe.view.execution.gui;

import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.dao.FaseDAO;
import br.ufpa.spider.pe.view.execution.logic.ExecutionController;
import br.ufpa.spider.pe.view.execution.logic.FindByProcess;
import br.ufpa.spider.pe.view.execution.logic.ModellingController;
import br.ufpa.spider.pe.view.execution.model.Component;
import br.ufpa.spider.pe.view.execution.model.Modelling;
import br.ufpa.spider.pe.view.execution.model.StereotypesGroup;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

public class PhasePropertiesPanel extends javax.swing.JPanel {

    private JLabel lblDefinirSugestesDe;
    private JLabel jLabelNome;
    private JLabel jLabelStatus;
    private JLabel jLabelEstado;
    private JLabel jLabelCicloVida;
    private JTextArea jTextAreaObjetivos;
    private JTextArea jTextAreaInfAdd;
    private JTextArea jTextAreaDescricao;
    private JPanel panel_1;

    public PhasePropertiesPanel() {
    	setBorder(new EmptyBorder(8, 8, 8, 8));
        initComponents();

    }

    public void setComponent(Component component) {
    	Fase fase = FindByProcess.findFase(component);
        if (fase.getCicloVida().compareToIgnoreCase("ITERATIVE") == 0) {
           jLabelCicloVida.setText("Iterativo");
        } else {
        	jLabelCicloVida.setText("Sequencial");
        }
        
        jLabelNome.setText(fase.getNome());
        if(fase.getEstado()==null)
        	jLabelEstado.setText(ExecutionController.NAO_INICIALIZADO);
        else
        	jLabelEstado.setText(fase.getEstado());
        jLabelStatus.setText(fase.getStatus());
        jTextAreaDescricao.setText(fase.getDescricao());
        jTextAreaObjetivos.setText(fase.getObjetivos());
        jTextAreaInfAdd.setText(fase.getInformacoesAdicionais());
        String inicioReal = FaseDAO.findDataInicioReal(fase) == null?"":FaseDAO.findDataInicioReal(fase);
//		String cargaReal = FaseDAO.findClockInterno(fase);
		String terminoReal = FaseDAO.findDataFimReal(fase) == null? "":  FaseDAO.findDataFimReal(fase);
		String cargaPrevista = FaseDAO.findCargaHoraria(fase);
		String inicioPrevisto = FaseDAO.findDataInicioPrevisto(fase) == null? "":FaseDAO.findDataInicioPrevisto(fase) ;
		String terminoPrevisto = FaseDAO.findDataFimPrevisto(fase) == null? "":  FaseDAO.findDataFimPrevisto(fase);
//		((jPanelPropertiesPrevivedAndReal)panel_1).setInformacoes(inicioPrevisto, inicioReal, terminoPrevisto, terminoReal, cargaPrevista, cargaReal);
    }

    @Override
    public boolean requestFocusInWindow() {
        if (super.requestFocusInWindow()) {
            
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setName("Form"); // NOI18N
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);
        
        lblDefinirSugestesDe = new JLabel("Definir sugest\u00F5es de Arquitetura");
        lblDefinirSugestesDe.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_lblDefinirSugestesDe = new GridBagConstraints();
        gbc_lblDefinirSugestesDe.anchor = GridBagConstraints.WEST;
        gbc_lblDefinirSugestesDe.insets = new Insets(0, 0, 5, 0);
        gbc_lblDefinirSugestesDe.gridx = 0;
        gbc_lblDefinirSugestesDe.gridy = 0;
        add(lblDefinirSugestesDe, gbc_lblDefinirSugestesDe);
        
        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 5, 0);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 1;
        add(panel, gbc_panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0};
        gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);
        
        JPanel panel_2 = new JPanel();
        GridBagConstraints gbc_panel_2 = new GridBagConstraints();
        gbc_panel_2.fill = GridBagConstraints.BOTH;
        gbc_panel_2.insets = new Insets(0, 0, 5, 0);
        gbc_panel_2.gridx = 0;
        gbc_panel_2.gridy = 0;
        panel.add(panel_2, gbc_panel_2);
        GridBagLayout gbl_panel_2 = new GridBagLayout();
        gbl_panel_2.columnWidths = new int[]{0, 0};
        gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl_panel_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel_2.setLayout(gbl_panel_2);
        
        JPanel panel_3 = new JPanel();
        GridBagConstraints gbc_panel_3 = new GridBagConstraints();
        gbc_panel_3.fill = GridBagConstraints.BOTH;
        gbc_panel_3.insets = new Insets(0, 0, 5, 0);
        gbc_panel_3.gridx = 0;
        gbc_panel_3.gridy = 0;
        panel_2.add(panel_3, gbc_panel_3);
        GridBagLayout gbl_panel_3 = new GridBagLayout();
        gbl_panel_3.columnWidths = new int[]{0, 0, 0};
        gbl_panel_3.rowHeights = new int[]{0, 0};
        gbl_panel_3.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panel_3.setLayout(gbl_panel_3);
        
        JLabel lblNome = new JLabel("Nome:");
        GridBagConstraints gbc_lblNome = new GridBagConstraints();
        gbc_lblNome.anchor = GridBagConstraints.WEST;
        gbc_lblNome.insets = new Insets(0, 0, 0, 5);
        gbc_lblNome.gridx = 0;
        gbc_lblNome.gridy = 0;
        panel_3.add(lblNome, gbc_lblNome);
        
        jLabelNome = new JLabel("New label");
        GridBagConstraints gbc_jLabelNome = new GridBagConstraints();
        gbc_jLabelNome.anchor = GridBagConstraints.WEST;
        gbc_jLabelNome.gridx = 1;
        gbc_jLabelNome.gridy = 0;
        panel_3.add(jLabelNome, gbc_jLabelNome);
        jLabelNome.setFont(new Font("Dialog", Font.BOLD, 11));
        
        JPanel panel_4 = new JPanel();
        GridBagConstraints gbc_panel_4 = new GridBagConstraints();
        gbc_panel_4.fill = GridBagConstraints.BOTH;
        gbc_panel_4.insets = new Insets(0, 0, 5, 0);
        gbc_panel_4.gridx = 0;
        gbc_panel_4.gridy = 1;
        panel_2.add(panel_4, gbc_panel_4);
        GridBagLayout gbl_panel_4 = new GridBagLayout();
        gbl_panel_4.columnWidths = new int[]{0, 0, 0};
        gbl_panel_4.rowHeights = new int[]{0, 0};
        gbl_panel_4.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel_4.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panel_4.setLayout(gbl_panel_4);
        
        JLabel lblStat = new JLabel("Status:");
        GridBagConstraints gbc_lblStat = new GridBagConstraints();
        gbc_lblStat.anchor = GridBagConstraints.WEST;
        gbc_lblStat.insets = new Insets(0, 0, 0, 5);
        gbc_lblStat.gridx = 0;
        gbc_lblStat.gridy = 0;
        panel_4.add(lblStat, gbc_lblStat);
        
        jLabelStatus = new JLabel("New label");
        GridBagConstraints gbc_jLabelStatus = new GridBagConstraints();
        gbc_jLabelStatus.anchor = GridBagConstraints.WEST;
        gbc_jLabelStatus.gridx = 1;
        gbc_jLabelStatus.gridy = 0;
        panel_4.add(jLabelStatus, gbc_jLabelStatus);
        jLabelStatus.setFont(new Font("Dialog", Font.BOLD, 11));
        
        JPanel panel_5 = new JPanel();
        GridBagConstraints gbc_panel_5 = new GridBagConstraints();
        gbc_panel_5.fill = GridBagConstraints.BOTH;
        gbc_panel_5.insets = new Insets(0, 0, 5, 0);
        gbc_panel_5.gridx = 0;
        gbc_panel_5.gridy = 2;
        panel_2.add(panel_5, gbc_panel_5);
        GridBagLayout gbl_panel_5 = new GridBagLayout();
        gbl_panel_5.columnWidths = new int[]{0, 0, 0};
        gbl_panel_5.rowHeights = new int[]{0, 0};
        gbl_panel_5.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel_5.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panel_5.setLayout(gbl_panel_5);
        
        JLabel lblEstado = new JLabel("Estado:");
        GridBagConstraints gbc_lblEstado = new GridBagConstraints();
        gbc_lblEstado.anchor = GridBagConstraints.WEST;
        gbc_lblEstado.insets = new Insets(0, 0, 0, 5);
        gbc_lblEstado.gridx = 0;
        gbc_lblEstado.gridy = 0;
        panel_5.add(lblEstado, gbc_lblEstado);
        
        jLabelEstado = new JLabel("New label");
        GridBagConstraints gbc_jLabelEstado = new GridBagConstraints();
        gbc_jLabelEstado.anchor = GridBagConstraints.WEST;
        gbc_jLabelEstado.gridx = 1;
        gbc_jLabelEstado.gridy = 0;
        panel_5.add(jLabelEstado, gbc_jLabelEstado);
        jLabelEstado.setFont(new Font("Dialog", Font.BOLD, 11));
        
        JPanel panel_6 = new JPanel();
        GridBagConstraints gbc_panel_6 = new GridBagConstraints();
        gbc_panel_6.fill = GridBagConstraints.BOTH;
        gbc_panel_6.gridx = 0;
        gbc_panel_6.gridy = 3;
        panel_2.add(panel_6, gbc_panel_6);
        GridBagLayout gbl_panel_6 = new GridBagLayout();
        gbl_panel_6.columnWidths = new int[]{0, 0, 0};
        gbl_panel_6.rowHeights = new int[]{0, 0};
        gbl_panel_6.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel_6.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panel_6.setLayout(gbl_panel_6);
        
        JLabel lblCicloDeVida = new JLabel("Ciclo de Vida:");
        GridBagConstraints gbc_lblCicloDeVida = new GridBagConstraints();
        gbc_lblCicloDeVida.anchor = GridBagConstraints.WEST;
        gbc_lblCicloDeVida.insets = new Insets(0, 0, 0, 5);
        gbc_lblCicloDeVida.gridx = 0;
        gbc_lblCicloDeVida.gridy = 0;
        panel_6.add(lblCicloDeVida, gbc_lblCicloDeVida);
        
        jLabelCicloVida = new JLabel("New label");
        GridBagConstraints gbc_jLabelCicloVida = new GridBagConstraints();
        gbc_jLabelCicloVida.anchor = GridBagConstraints.WEST;
        gbc_jLabelCicloVida.gridx = 1;
        gbc_jLabelCicloVida.gridy = 0;
        panel_6.add(jLabelCicloVida, gbc_jLabelCicloVida);
        jLabelCicloVida.setFont(new Font("Dialog", Font.BOLD, 11));
        
        JLabel lblDescrio = new JLabel("Descri\u00E7\u00e3o:");
        GridBagConstraints gbc_lblDescrio = new GridBagConstraints();
        gbc_lblDescrio.anchor = GridBagConstraints.WEST;
        gbc_lblDescrio.insets = new Insets(0, 0, 5, 0);
        gbc_lblDescrio.gridx = 0;
        gbc_lblDescrio.gridy = 1;
        panel.add(lblDescrio, gbc_lblDescrio);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(3, 105));
        scrollPane.setMinimumSize(new Dimension(22, 100));
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 2;
        panel.add(scrollPane, gbc_scrollPane);
        
        jTextAreaDescricao = new JTextArea();
        jTextAreaDescricao.setEditable(false);
        jTextAreaDescricao.setLineWrap(true);
        scrollPane.setViewportView(jTextAreaDescricao);
        
        JLabel lblObjetivos = new JLabel("Objetivos:");
        GridBagConstraints gbc_lblObjetivos = new GridBagConstraints();
        gbc_lblObjetivos.anchor = GridBagConstraints.WEST;
        gbc_lblObjetivos.insets = new Insets(0, 0, 5, 0);
        gbc_lblObjetivos.gridx = 0;
        gbc_lblObjetivos.gridy = 3;
        panel.add(lblObjetivos, gbc_lblObjetivos);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setMinimumSize(new Dimension(22, 105));
        scrollPane_1.setPreferredSize(new Dimension(3, 105));
        GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
        gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane_1.gridx = 0;
        gbc_scrollPane_1.gridy = 4;
        panel.add(scrollPane_1, gbc_scrollPane_1);
        
        jTextAreaObjetivos = new JTextArea();
        jTextAreaObjetivos.setEditable(false);
        jTextAreaObjetivos.setLineWrap(true);
        scrollPane_1.setViewportView(jTextAreaObjetivos);
        
        JLabel lblInformaesAdicionais = new JLabel("Informa\u00E7\u00F5es Adicionais");
        GridBagConstraints gbc_lblInformaesAdicionais = new GridBagConstraints();
        gbc_lblInformaesAdicionais.insets = new Insets(0, 0, 5, 0);
        gbc_lblInformaesAdicionais.anchor = GridBagConstraints.WEST;
        gbc_lblInformaesAdicionais.gridx = 0;
        gbc_lblInformaesAdicionais.gridy = 5;
        panel.add(lblInformaesAdicionais, gbc_lblInformaesAdicionais);
        
        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setPreferredSize(new Dimension(3, 105));
        scrollPane_2.setMinimumSize(new Dimension(22, 100));
        GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
        gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_2.gridx = 0;
        gbc_scrollPane_2.gridy = 6;
        panel.add(scrollPane_2, gbc_scrollPane_2);
        
        jTextAreaInfAdd = new JTextArea();
        jTextAreaInfAdd.setEditable(false);
        jTextAreaInfAdd.setLineWrap(true);
        scrollPane_2.setViewportView(jTextAreaInfAdd);
        
        panel_1 = new jPanelPropertiesPrevivedAndReal();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 2;
        add(panel_1, gbc_panel_1);
       

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(br.ufpa.spider.pe.view.execution.gui.Application.class).getContext().getResourceMap(PhasePropertiesPanel.class);
    }// </editor-fold>//GEN-END:initComponents
}