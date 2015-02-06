package br.ufpa.spider.pe.view.pm.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.dao.FaseDAO;
import br.ufpa.spider.pe.model.dao.MarcoDAO;
import br.ufpa.spider.pe.view.pm.logic.ExecutionController;
import br.ufpa.spider.pe.view.pm.logic.FindByProcess;
import br.ufpa.spider.pe.view.pm.logic.GenericComparator;
import br.ufpa.spider.pe.view.pm.logic.ModellingController;
import br.ufpa.spider.pe.view.pm.model.Component;
import br.ufpa.spider.pe.view.pm.model.ComponentType;
import br.ufpa.spider.pe.view.pm.model.Modelling;
import br.ufpa.spider.pe.view.pm.model.StereotypesGroup;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JPanel;

public class MilestonePropertiesPanel extends javax.swing.JPanel {

    private int componentId;
    private JLabel jLabelInfMarco;
    private  JLabel jLabelNome;
    private JLabel jLabelNome2;
    private  JLabel jLabelStatus2;
    private  JTextArea jTextAreaDescricao;
    private JTextArea jTextAreaObjetivos;
    private JTextArea jTextAreaInfAdd;
    private JLabel lblEstado;
    private JLabel jLabelEstado2;
    private JPanel panel;
    private JPanel panel_1;

    public MilestonePropertiesPanel() {
    	setBorder(new EmptyBorder(8, 8, 8, 8));
        initComponents();
    }

    public void setComponent(Marco marco) {
        jLabelNome2.setText(marco.getNome());      
        jLabelStatus2.setText(marco.getStatus());
        if(marco.getEstado() == null)
        	jLabelEstado2.setText(ExecutionController.NAO_INICIALIZADO);
    	else        		
    		jLabelEstado2.setText(marco.getEstado());
        
        jTextAreaDescricao.setText(marco.getDescricao());
        jTextAreaInfAdd.setText(marco.getInformacoesAdicionais());
        jTextAreaObjetivos.setText(marco.getObjetivos());
        
        String inicioReal = MarcoDAO.findById(marco.getId()).getInicioReal();
		//String cargaReal = MarcoDAO.findById(marco.getId()).getClockInterno();
		String terminoReal = MarcoDAO.findById(marco.getId()).getFimReal();
		String cargaPrevista = MarcoDAO.findById(marco.getId()).getCargaHoraria();
		String inicioPrevisto = MarcoDAO.findById(marco.getId()).getInicioPrevisto();
		String terminoPrevisto = MarcoDAO.findById(marco.getId()).getFimPrevisto();
		//((jPanelPropertiesPrevivedAndReal)panel).setInformacoes(inicioPrevisto, inicioReal, terminoPrevisto, terminoReal, cargaPrevista, cargaReal);
        
       
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

        setName("Form");
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{90, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 118, 0, 108, 0, 105, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);
        

        jLabelInfMarco = new JLabel("Definir Sugest\u00f5es de Arquitetura");

        jLabelInfMarco = new JLabel("Definir sugest\u00F5es de Arquitetura");

        jLabelInfMarco.setFont(new Font("Dialog", Font.BOLD, 11));
        GridBagConstraints gbc_jLabelInfMarco = new GridBagConstraints();
        gbc_jLabelInfMarco.anchor = GridBagConstraints.WEST;
        gbc_jLabelInfMarco.insets = new Insets(0, 0, 5, 0);
        gbc_jLabelInfMarco.gridx = 0;
        gbc_jLabelInfMarco.gridy = 0;
        add(jLabelInfMarco, gbc_jLabelInfMarco);
        
        panel_1 = new JPanel();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.insets = new Insets(0, 0, 5, 0);
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 1;
        add(panel_1, gbc_panel_1);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[]{40, 0, 0};
        gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0};
        gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel_1.setLayout(gbl_panel_1);
        
        jLabelNome = new JLabel("Nome:");
        GridBagConstraints gbc_jLabelNome = new GridBagConstraints();
        gbc_jLabelNome.anchor = GridBagConstraints.WEST;
        gbc_jLabelNome.insets = new Insets(0, 0, 5, 5);
        gbc_jLabelNome.gridx = 0;
        gbc_jLabelNome.gridy = 0;
        panel_1.add(jLabelNome, gbc_jLabelNome);
        
        jLabelNome2 = new JLabel("");
        GridBagConstraints gbc_jLabelNome2 = new GridBagConstraints();
        gbc_jLabelNome2.anchor = GridBagConstraints.WEST;
        gbc_jLabelNome2.insets = new Insets(0, 0, 5, 0);
        gbc_jLabelNome2.gridx = 1;
        gbc_jLabelNome2.gridy = 0;
        panel_1.add(jLabelNome2, gbc_jLabelNome2);
        jLabelNome2.setFont(new Font("Dialog", Font.BOLD, 11));
        
        JLabel jLabelStatus = new JLabel("Status:");
        GridBagConstraints gbc_jLabelStatus = new GridBagConstraints();
        gbc_jLabelStatus.anchor = GridBagConstraints.WEST;
        gbc_jLabelStatus.insets = new Insets(0, 0, 5, 5);
        gbc_jLabelStatus.gridx = 0;
        gbc_jLabelStatus.gridy = 1;
        panel_1.add(jLabelStatus, gbc_jLabelStatus);
        
        jLabelStatus2 = new JLabel("");
        GridBagConstraints gbc_jLabelStatus2 = new GridBagConstraints();
        gbc_jLabelStatus2.anchor = GridBagConstraints.WEST;
        gbc_jLabelStatus2.insets = new Insets(0, 0, 5, 0);
        gbc_jLabelStatus2.gridx = 1;
        gbc_jLabelStatus2.gridy = 1;
        panel_1.add(jLabelStatus2, gbc_jLabelStatus2);
        jLabelStatus2.setFont(new Font("Dialog", Font.BOLD, 11));
        
        lblEstado = new JLabel("Estado:");
        GridBagConstraints gbc_lblEstado = new GridBagConstraints();
        gbc_lblEstado.anchor = GridBagConstraints.WEST;
        gbc_lblEstado.insets = new Insets(0, 0, 0, 5);
        gbc_lblEstado.gridx = 0;
        gbc_lblEstado.gridy = 2;
        panel_1.add(lblEstado, gbc_lblEstado);
        
        jLabelEstado2 = new JLabel("");
        GridBagConstraints gbc_jLabelEstado2 = new GridBagConstraints();
        gbc_jLabelEstado2.anchor = GridBagConstraints.WEST;
        gbc_jLabelEstado2.gridx = 1;
        gbc_jLabelEstado2.gridy = 2;
        panel_1.add(jLabelEstado2, gbc_jLabelEstado2);
        jLabelEstado2.setFont(new Font("Dialog", Font.BOLD, 11));
        
        JLabel lblDescrio = new JLabel("Descri\u00E7\u00e3o:");

        GridBagConstraints gbc_lblDescrio = new GridBagConstraints();
        gbc_lblDescrio.anchor = GridBagConstraints.WEST;
        gbc_lblDescrio.insets = new Insets(0, 0, 5, 0);
        gbc_lblDescrio.gridx = 0;
        gbc_lblDescrio.gridy = 2;
        add(lblDescrio, gbc_lblDescrio);
        
        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 3;
        add(scrollPane, gbc_scrollPane);
        
        jTextAreaDescricao = new JTextArea();
        jTextAreaDescricao.setEditable(false);
        jTextAreaDescricao.setLineWrap(true);
        scrollPane.setViewportView(jTextAreaDescricao);
        
        JLabel lblObjetivos = new JLabel("Objetivos:");
        GridBagConstraints gbc_lblObjetivos = new GridBagConstraints();
        gbc_lblObjetivos.anchor = GridBagConstraints.WEST;
        gbc_lblObjetivos.insets = new Insets(0, 0, 5, 0);
        gbc_lblObjetivos.gridx = 0;
        gbc_lblObjetivos.gridy = 4;
        add(lblObjetivos, gbc_lblObjetivos);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
        gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane_1.gridx = 0;
        gbc_scrollPane_1.gridy = 5;
        add(scrollPane_1, gbc_scrollPane_1);
        
        jTextAreaObjetivos = new JTextArea();
        jTextAreaObjetivos.setEditable(false);
        jTextAreaObjetivos.setLineWrap(true);
        scrollPane_1.setViewportView(jTextAreaObjetivos);
        
        JLabel lblInformaesAdicionais = new JLabel("Informa\u00E7\u00F5es Adicionais");
        GridBagConstraints gbc_lblInformaesAdicionais = new GridBagConstraints();
        gbc_lblInformaesAdicionais.anchor = GridBagConstraints.WEST;
        gbc_lblInformaesAdicionais.insets = new Insets(0, 0, 5, 0);
        gbc_lblInformaesAdicionais.gridx = 0;
        gbc_lblInformaesAdicionais.gridy = 6;
        add(lblInformaesAdicionais, gbc_lblInformaesAdicionais);
        
        JScrollPane scrollPane_2 = new JScrollPane();
        GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
        gbc_scrollPane_2.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_2.gridx = 0;
        gbc_scrollPane_2.gridy = 7;
        add(scrollPane_2, gbc_scrollPane_2);
        
        jTextAreaInfAdd = new JTextArea();
        jTextAreaInfAdd.setEditable(false);
        jTextAreaInfAdd.setLineWrap(true);
        scrollPane_2.setViewportView(jTextAreaInfAdd);
        
        panel = new jPanelPropertiesPrevivedAndReal();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 8;
        add(panel, gbc_panel);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(br.ufpa.spider.pe.view.pm.gui.Application.class).getContext().getResourceMap(MilestonePropertiesPanel.class);
    }// </editor-fold>//GEN-END:initComponents

}
