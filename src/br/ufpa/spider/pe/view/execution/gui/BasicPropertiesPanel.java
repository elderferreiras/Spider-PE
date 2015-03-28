package br.ufpa.spider.pe.view.execution.gui;

import br.ufpa.spider.pe.model.Atividade;
import br.ufpa.spider.pe.model.Iteracao;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.dao.AtividadeDAO;
import br.ufpa.spider.pe.model.dao.IteracaoDAO;
import br.ufpa.spider.pe.model.dao.ProcessoDAO;
import br.ufpa.spider.pe.view.execution.logic.ExecutionController;
import br.ufpa.spider.pe.view.execution.logic.FindByProcess;
import br.ufpa.spider.pe.view.execution.logic.ModellingController;
import br.ufpa.spider.pe.view.execution.model.Component;
import br.ufpa.spider.pe.view.execution.model.ComponentType;
import br.ufpa.spider.pe.view.execution.model.Modelling;
import br.ufpa.spider.pe.view.execution.model.StereotypesGroup;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;
import java.awt.Dimension;

public class BasicPropertiesPanel extends javax.swing.JPanel {

    private JTextArea jTextAreaInfAdd;
    private JPanel panel_1;
    private JLabel jLabelNome;
    private JLabel jLabelStatus;
    private JLabel jLabelEstado;
    private JTextArea jTextAreaObjetivos;
    private JTextArea jTextAreaDescricao;
    private JPanel panel;

    public BasicPropertiesPanel() {
    	setBorder(new EmptyBorder(8, 8, 8, 8));
        initComponents();
    }

    public void setComponent(Component component) {
        String nome="", status="", estado="", descricao="", objetivos="", infAdd="";
        String inicioReal = "", cargaReal = "", terminoReal = "", cargaPrevista = "", inicioPrevisto = "", terminoPrevisto = "";
        if(component.getType() == ComponentType.ACTIVITY){
        	Atividade o = FindByProcess.findAtividade(component);
        	nome = o.getNome();
        	status = o.getStatus();
        	if(o.getEstado() == null)
        		estado = ExecutionController.NAO_INICIALIZADO;
        	else        		
        		estado = o.getEstado();
        	descricao = o.getDescricao();
        	objetivos = o.getObjetivos();
        	infAdd = o.getInformacoesAdicionais();
        	inicioReal = AtividadeDAO.findDataInicioReal(o);
//    		cargaReal = AtividadeDAO.findClockInterno(o);
    		terminoReal = AtividadeDAO.findDataFimReal(o);
    		cargaPrevista = AtividadeDAO.findCargaHoraria(o);
    		inicioPrevisto = AtividadeDAO.findDataInicioPrevisto(o);
    		terminoPrevisto = AtividadeDAO.findDataFimPrevisto(o);
        } else if (component.getType() == ComponentType.ITERATION){
        	Iteracao o = FindByProcess.findIteracao(component);
        	nome = o.getNome();
        	status = o.getStatus();
        	if(o.getEstado() == null)
        		estado = ExecutionController.NAO_INICIALIZADO;
        	else        		
        		estado = o.getEstado();
        	descricao = o.getDescricao();
        	objetivos = o.getObjetivos();
        	infAdd = o.getInformacoesAdicionais();
        	inicioReal = IteracaoDAO.findDataInicioReal(o);
//    		cargaReal = IteracaoDAO.findClockInterno(o);
    		terminoReal = IteracaoDAO.findDataFimReal(o);
    		cargaPrevista = IteracaoDAO.findCargaHoraria(o);
    		inicioPrevisto = IteracaoDAO.findDataInicioPrevisto(o);
    		terminoPrevisto = IteracaoDAO.findDataFimPrevisto(o);
        } else if (component.getType() == ComponentType.PROCESS){
        	Processo o = FindByProcess.findProcesso(component);
        	nome = o.getNome();
        	status = o.getStatus();
        	if(o.getEstado() == null)
        		estado = ExecutionController.NAO_INICIALIZADO;
        	else        		
        		estado = o.getEstado();
        	descricao = o.getDescricao();
        	objetivos = o.getObjetivos();
        	infAdd = o.getInformacoesAdicionais();
        	inicioReal = ProcessoDAO.findDataInicioReal(o);
//    		cargaReal = ProcessoDAO.findClockInterno(o);
    		terminoReal = ProcessoDAO.findDataFimReal(o);
    		cargaPrevista = ProcessoDAO.findCargaHoraria(o);
    		inicioPrevisto = ProcessoDAO.findDataInicioPrevisto(o);
    		terminoPrevisto = ProcessoDAO.findDataFimPrevisto(o);
        }
        jLabelNome.setText(nome);
        jLabelStatus.setText(status);
        jLabelEstado.setText(estado);
        jTextAreaDescricao.setText(descricao);
        jTextAreaObjetivos.setText(objetivos);
        jTextAreaInfAdd.setText(infAdd);
		((jPanelPropertiesPrevivedAndReal)panel).setInformacoes(inicioPrevisto, inicioReal, terminoPrevisto, terminoReal, cargaPrevista, cargaReal);
        
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
        gridBagLayout.rowHeights = new int[]{0, 339, -155, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);
        
        JLabel lblDefinirSugestesDe = new JLabel("Definir sugest\u00F5es de Arquitetura");
        lblDefinirSugestesDe.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_lblDefinirSugestesDe = new GridBagConstraints();
        gbc_lblDefinirSugestesDe.anchor = GridBagConstraints.WEST;
        gbc_lblDefinirSugestesDe.insets = new Insets(0, 0, 5, 0);
        gbc_lblDefinirSugestesDe.gridx = 0;
        gbc_lblDefinirSugestesDe.gridy = 0;
        add(lblDefinirSugestesDe, gbc_lblDefinirSugestesDe);
        
        panel_1 = new JPanel();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.insets = new Insets(0, 0, 5, 0);
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 1;
        add(panel_1, gbc_panel_1);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[]{76, 0};
        gbl_panel_1.rowHeights = new int[]{0, 0, 29, 22, 0, 0, 4, 0};
        gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
        panel_1.setLayout(gbl_panel_1);
        
        JPanel panel_2 = new JPanel();
        GridBagConstraints gbc_panel_2 = new GridBagConstraints();
        gbc_panel_2.fill = GridBagConstraints.BOTH;
        gbc_panel_2.insets = new Insets(0, 0, 5, 0);
        gbc_panel_2.gridx = 0;
        gbc_panel_2.gridy = 0;
        panel_1.add(panel_2, gbc_panel_2);
        GridBagLayout gbl_panel_2 = new GridBagLayout();
        gbl_panel_2.columnWidths = new int[]{76, 0};
        gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0};
        gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel_2.setLayout(gbl_panel_2);
        
        JPanel panel_3 = new JPanel();
        GridBagConstraints gbc_panel_3 = new GridBagConstraints();
        gbc_panel_3.anchor = GridBagConstraints.WEST;
        gbc_panel_3.fill = GridBagConstraints.VERTICAL;
        gbc_panel_3.insets = new Insets(0, 0, 5, 0);
        gbc_panel_3.gridx = 0;
        gbc_panel_3.gridy = 0;
        panel_2.add(panel_3, gbc_panel_3);
        GridBagLayout gbl_panel_3 = new GridBagLayout();
        gbl_panel_3.columnWidths = new int[]{42, 386, 0};
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
        jLabelNome.setFont(new Font("Dialog", Font.BOLD, 11));
        GridBagConstraints gbc_jLabelNome = new GridBagConstraints();
        gbc_jLabelNome.anchor = GridBagConstraints.WEST;
        gbc_jLabelNome.gridx = 1;
        gbc_jLabelNome.gridy = 0;
        panel_3.add(jLabelNome, gbc_jLabelNome);
        
        JPanel panel_4 = new JPanel();
        GridBagConstraints gbc_panel_4 = new GridBagConstraints();
        gbc_panel_4.fill = GridBagConstraints.BOTH;
        gbc_panel_4.insets = new Insets(0, 0, 5, 0);
        gbc_panel_4.gridx = 0;
        gbc_panel_4.gridy = 1;
        panel_2.add(panel_4, gbc_panel_4);
        GridBagLayout gbl_panel_4 = new GridBagLayout();
        gbl_panel_4.columnWidths = new int[]{42, 0, 0};
        gbl_panel_4.rowHeights = new int[]{0, 0};
        gbl_panel_4.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel_4.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panel_4.setLayout(gbl_panel_4);
        
        JLabel lblStatus = new JLabel("Status:");
        GridBagConstraints gbc_lblStatus = new GridBagConstraints();
        gbc_lblStatus.anchor = GridBagConstraints.WEST;
        gbc_lblStatus.insets = new Insets(0, 0, 0, 5);
        gbc_lblStatus.gridx = 0;
        gbc_lblStatus.gridy = 0;
        panel_4.add(lblStatus, gbc_lblStatus);
        
        jLabelStatus = new JLabel("New label");
        jLabelStatus.setFont(new Font("Dialog", Font.BOLD, 11));
        GridBagConstraints gbc_jLabelStatus = new GridBagConstraints();
        gbc_jLabelStatus.fill = GridBagConstraints.VERTICAL;
        gbc_jLabelStatus.anchor = GridBagConstraints.WEST;
        gbc_jLabelStatus.gridx = 1;
        gbc_jLabelStatus.gridy = 0;
        panel_4.add(jLabelStatus, gbc_jLabelStatus);
        
        JPanel panel_5 = new JPanel();
        GridBagConstraints gbc_panel_5 = new GridBagConstraints();
        gbc_panel_5.fill = GridBagConstraints.BOTH;
        gbc_panel_5.gridx = 0;
        gbc_panel_5.gridy = 2;
        panel_2.add(panel_5, gbc_panel_5);
        GridBagLayout gbl_panel_5 = new GridBagLayout();
        gbl_panel_5.columnWidths = new int[]{39, 0, 0};
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
        jLabelEstado.setFont(new Font("Dialog", Font.BOLD, 11));
        GridBagConstraints gbc_jLabelEstado = new GridBagConstraints();
        gbc_jLabelEstado.anchor = GridBagConstraints.WEST;
        gbc_jLabelEstado.gridx = 1;
        gbc_jLabelEstado.gridy = 0;
        panel_5.add(jLabelEstado, gbc_jLabelEstado);
        
        JLabel lblDescrio = new JLabel("Descri\u00E7\u00e3o:");
        GridBagConstraints gbc_lblDescrio = new GridBagConstraints();
        gbc_lblDescrio.anchor = GridBagConstraints.WEST;
        gbc_lblDescrio.insets = new Insets(0, 0, 5, 0);
        gbc_lblDescrio.gridx = 0;
        gbc_lblDescrio.gridy = 1;
        panel_1.add(lblDescrio, gbc_lblDescrio);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(3, 105));
        scrollPane.setMinimumSize(new Dimension(22, 100));
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 2;
        panel_1.add(scrollPane, gbc_scrollPane);
        
        jTextAreaDescricao = new JTextArea();
        scrollPane.setViewportView(jTextAreaDescricao);
        jTextAreaDescricao.setText("");
        
        JLabel lblObjetivos = new JLabel("Objetivos:");
        GridBagConstraints gbc_lblObjetivos = new GridBagConstraints();
        gbc_lblObjetivos.anchor = GridBagConstraints.WEST;
        gbc_lblObjetivos.insets = new Insets(0, 0, 5, 0);
        gbc_lblObjetivos.gridx = 0;
        gbc_lblObjetivos.gridy = 3;
        panel_1.add(lblObjetivos, gbc_lblObjetivos);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setMinimumSize(new Dimension(22, 100));
        scrollPane_1.setPreferredSize(new Dimension(3, 105));
        GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
        gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane_1.gridx = 0;
        gbc_scrollPane_1.gridy = 4;
        panel_1.add(scrollPane_1, gbc_scrollPane_1);
        
        jTextAreaObjetivos = new JTextArea();
        scrollPane_1.setViewportView(jTextAreaObjetivos);
        jTextAreaObjetivos.setText("");
        
        JLabel lblInformaesAdicionais = new JLabel("Informa\u00E7\u00F5es Adicionais");
        GridBagConstraints gbc_lblInformaesAdicionais = new GridBagConstraints();
        gbc_lblInformaesAdicionais.insets = new Insets(0, 0, 5, 0);
        gbc_lblInformaesAdicionais.anchor = GridBagConstraints.WEST;
        gbc_lblInformaesAdicionais.gridx = 0;
        gbc_lblInformaesAdicionais.gridy = 5;
        panel_1.add(lblInformaesAdicionais, gbc_lblInformaesAdicionais);
        
        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setMinimumSize(new Dimension(22, 100));
        scrollPane_2.setPreferredSize(new Dimension(3, 105));
        GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
        gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_2.gridx = 0;
        gbc_scrollPane_2.gridy = 6;
        panel_1.add(scrollPane_2, gbc_scrollPane_2);
        
        jTextAreaInfAdd = new JTextArea();
        scrollPane_2.setViewportView(jTextAreaInfAdd);
        jTextAreaInfAdd.setText("");
        
        panel = new jPanelPropertiesPrevivedAndReal();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 2;
        add(panel, gbc_panel);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(br.ufpa.spider.pe.view.execution.gui.Application.class).getContext().getResourceMap(BasicPropertiesPanel.class);
    }// </editor-fold>//GEN-END:initComponents
}