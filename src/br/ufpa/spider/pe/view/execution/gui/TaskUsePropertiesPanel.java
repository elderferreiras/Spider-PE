package br.ufpa.spider.pe.view.execution.gui;

import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.Papel;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.Tipo;
import br.ufpa.spider.pe.model.dao.CampoDAO;
import br.ufpa.spider.pe.view.execution.logic.ExecutionController;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;

import javax.swing.border.TitledBorder;
import javax.swing.border.EmptyBorder;

public class TaskUsePropertiesPanel extends javax.swing.JPanel {
    private int componentId;
    private JLabel lblDefinirSugestesDe;
    private JLabel lblResponsvel;
    private JLabel labelPercentagemExecucao;
    private JLabel lblDescrio;
    private JPanel panel;
    private JLabel labelEstado;
    private JLabel lblNewLabel_4;
    private JLabel lblDataTrmino;
    private JLabel lblCargaHorria;
    private JPanel panel_1;
    private JLabel labelDataInicioReal;
    private JLabel labelDataInicioPrevisto;
    private JLabel labelDataTerminoPrevisto;
    private JLabel labelDataTerminoReal;
    private JLabel labelCargaHorariaPrevisto;
    private JLabel labelCargaHorariaReal;
    private JLabel lblPrevisto;
    private JLabel lblReal;
    private JList listRH;
    private JScrollPane scrollPane;
    private JTextArea textArea;
    private JScrollPane scrollPane_1;
	private DefaultListModel listModel;
	private JLabel lblNewLabel;

    public TaskUsePropertiesPanel() {
    	setBorder(new EmptyBorder(8, 8, 8, 8));
        initComponents();
        listModel = new DefaultListModel();
    }

    public void setComponent(Tarefa tarefa) {
    	listModel.removeAllElements();
        componentId = tarefa.getIdComponent();
        labelEstado.setText((((tarefa.getEstado() == null)||(tarefa.getEstado().isEmpty()))? ExecutionController.NAO_INICIALIZADO : tarefa.getEstado()));
       
        for (Papel papel : tarefa.getPapeis()) {
			for (Humano humano : papel.getHumanosAlocados()) {				
					listModel.addElement(humano);
			}
		}
        listRH.setModel(listModel);
        
        textArea.setText(tarefa.getDescricao());
        labelPercentagemExecucao.setText("Percentagem de Execu\u00E7\u00E3o: "+ (tarefa.getPercentagemExecucao() == 0 ? "0%":Integer.toString(tarefa.getPercentagemExecucao()) +"%"));
        labelCargaHorariaPrevisto.setText(tarefa.getCargaHoraria());
        labelDataInicioPrevisto.setText(tarefa.getInicioPrevisto());
        labelDataTerminoPrevisto.setText(tarefa.getFimPrevisto());
        
        labelDataInicioReal.setText((tarefa.getInicioReal() == null)?"?":tarefa.getInicioReal());
        labelDataTerminoReal.setText((tarefa.getFimReal() == null)?"?":tarefa.getFimReal());
        labelCargaHorariaReal.setText("?");
    }

    @Override
    public boolean requestFocusInWindow() {
		return false;
       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setName("Form"); // NOI18N
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{214, 0};
        gridBagLayout.rowHeights = new int[]{0, 117, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);
        
        lblDefinirSugestesDe = new JLabel("Definir Sugest\u00F5es de Arquitetura");
        lblDefinirSugestesDe.setBorder(new EmptyBorder(5, 0, 0, 0));
        lblDefinirSugestesDe.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_lblDefinirSugestesDe = new GridBagConstraints();
        gbc_lblDefinirSugestesDe.anchor = GridBagConstraints.WEST;
        gbc_lblDefinirSugestesDe.insets = new Insets(0, 0, 5, 0);
        gbc_lblDefinirSugestesDe.gridx = 0;
        gbc_lblDefinirSugestesDe.gridy = 0;
        add(lblDefinirSugestesDe, gbc_lblDefinirSugestesDe);
        
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(0, 0, 0, 0));
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 5, 0);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 1;
        add(panel, gbc_panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{80, 131, 0};
        gbl_panel.rowHeights = new int[]{0, 0, 0, 48, 0, 41, 0};
        gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);
        
        lblNewLabel = new JLabel("Estado:");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        panel.add(lblNewLabel, gbc_lblNewLabel);
        
        labelEstado = new JLabel("Pausado");
        labelEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_labelEstado = new GridBagConstraints();
        gbc_labelEstado.anchor = GridBagConstraints.WEST;
        gbc_labelEstado.insets = new Insets(0, 0, 5, 0);
        gbc_labelEstado.gridx = 1;
        gbc_labelEstado.gridy = 0;
        panel.add(labelEstado, gbc_labelEstado);
        
        labelPercentagemExecucao = new JLabel("Percentagem \nde Execu\u00E7\u00E3o:");
        labelPercentagemExecucao.setText("Percentagem \r\nde Execu\u00E7\u00E3o: 0%");
        GridBagConstraints gbc_labelPercentagemExecucao = new GridBagConstraints();
        gbc_labelPercentagemExecucao.gridwidth = 2;
        gbc_labelPercentagemExecucao.anchor = GridBagConstraints.WEST;
        gbc_labelPercentagemExecucao.insets = new Insets(0, 0, 5, 0);
        gbc_labelPercentagemExecucao.gridx = 0;
        gbc_labelPercentagemExecucao.gridy = 1;
        panel.add(labelPercentagemExecucao, gbc_labelPercentagemExecucao);
        
        lblResponsvel = new JLabel("Respons\u00E1vel:");
        GridBagConstraints gbc_lblResponsvel = new GridBagConstraints();
        gbc_lblResponsvel.anchor = GridBagConstraints.WEST;
        gbc_lblResponsvel.insets = new Insets(0, 0, 5, 5);
        gbc_lblResponsvel.gridx = 0;
        gbc_lblResponsvel.gridy = 2;
        panel.add(lblResponsvel, gbc_lblResponsvel);
        
        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(4, 22));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridwidth = 2;
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 3;
        panel.add(scrollPane, gbc_scrollPane);
        
        listRH = new JList();
        listRH.setPreferredSize(new Dimension(20, 22));
        scrollPane.setViewportView(listRH);
        
        lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
        GridBagConstraints gbc_lblDescrio = new GridBagConstraints();
        gbc_lblDescrio.insets = new Insets(0, 0, 5, 5);
        gbc_lblDescrio.anchor = GridBagConstraints.WEST;
        gbc_lblDescrio.gridx = 0;
        gbc_lblDescrio.gridy = 4;
        panel.add(lblDescrio, gbc_lblDescrio);
        
        scrollPane_1 = new JScrollPane();
        scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
        gbc_scrollPane_1.gridwidth = 2;
        gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_1.gridx = 0;
        gbc_scrollPane_1.gridy = 5;
        panel.add(scrollPane_1, gbc_scrollPane_1);
        
        textArea = new JTextArea();
        textArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        scrollPane_1.setViewportView(textArea);
        
        panel_1 = new JPanel();
        panel_1.setBorder(new EmptyBorder(0, 0, 0, 0));
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 2;
        add(panel_1, gbc_panel_1);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[]{110, 0, 0, 0};
        gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel_1.setLayout(gbl_panel_1);
        
        lblPrevisto = new JLabel("Previsto");
        lblPrevisto.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_lblPrevisto = new GridBagConstraints();
        gbc_lblPrevisto.insets = new Insets(0, 0, 5, 5);
        gbc_lblPrevisto.gridx = 1;
        gbc_lblPrevisto.gridy = 0;
        panel_1.add(lblPrevisto, gbc_lblPrevisto);
        
        lblReal = new JLabel("Real");
        lblReal.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_lblReal = new GridBagConstraints();
        gbc_lblReal.insets = new Insets(0, 0, 5, 0);
        gbc_lblReal.gridx = 2;
        gbc_lblReal.gridy = 0;
        panel_1.add(lblReal, gbc_lblReal);
        
        lblNewLabel_4 = new JLabel("Data In\u00EDcio");
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_4.gridx = 0;
        gbc_lblNewLabel_4.gridy = 1;
        panel_1.add(lblNewLabel_4, gbc_lblNewLabel_4);
        
        labelDataInicioPrevisto = new JLabel("New label");
        GridBagConstraints gbc_labelDataInicioPrevisto = new GridBagConstraints();
        gbc_labelDataInicioPrevisto.insets = new Insets(0, 0, 5, 5);
        gbc_labelDataInicioPrevisto.gridx = 1;
        gbc_labelDataInicioPrevisto.gridy = 1;
        panel_1.add(labelDataInicioPrevisto, gbc_labelDataInicioPrevisto);
        
        labelDataInicioReal = new JLabel("New label");
        GridBagConstraints gbc_labelDataInicioReal = new GridBagConstraints();
        gbc_labelDataInicioReal.insets = new Insets(0, 0, 5, 0);
        gbc_labelDataInicioReal.gridx = 2;
        gbc_labelDataInicioReal.gridy = 1;
        panel_1.add(labelDataInicioReal, gbc_labelDataInicioReal);
        
        lblDataTrmino = new JLabel("Data T\u00E9rmino");
        GridBagConstraints gbc_lblDataTrmino = new GridBagConstraints();
        gbc_lblDataTrmino.anchor = GridBagConstraints.WEST;
        gbc_lblDataTrmino.insets = new Insets(0, 0, 5, 5);
        gbc_lblDataTrmino.gridx = 0;
        gbc_lblDataTrmino.gridy = 2;
        panel_1.add(lblDataTrmino, gbc_lblDataTrmino);
        
        labelDataTerminoPrevisto = new JLabel("New label");
        GridBagConstraints gbc_labelDataTerminoPrevisto = new GridBagConstraints();
        gbc_labelDataTerminoPrevisto.insets = new Insets(0, 0, 5, 5);
        gbc_labelDataTerminoPrevisto.gridx = 1;
        gbc_labelDataTerminoPrevisto.gridy = 2;
        panel_1.add(labelDataTerminoPrevisto, gbc_labelDataTerminoPrevisto);
        
        labelDataTerminoReal = new JLabel("New label");
        GridBagConstraints gbc_labelDataTerminoReal = new GridBagConstraints();
        gbc_labelDataTerminoReal.insets = new Insets(0, 0, 5, 0);
        gbc_labelDataTerminoReal.gridx = 2;
        gbc_labelDataTerminoReal.gridy = 2;
        panel_1.add(labelDataTerminoReal, gbc_labelDataTerminoReal);
        
        lblCargaHorria = new JLabel("Carga Hor\u00E1ria");
        GridBagConstraints gbc_lblCargaHorria = new GridBagConstraints();
        gbc_lblCargaHorria.insets = new Insets(0, 0, 0, 5);
        gbc_lblCargaHorria.anchor = GridBagConstraints.WEST;
        gbc_lblCargaHorria.gridx = 0;
        gbc_lblCargaHorria.gridy = 3;
        panel_1.add(lblCargaHorria, gbc_lblCargaHorria);
        
        labelCargaHorariaPrevisto = new JLabel("New label");
        GridBagConstraints gbc_labelCargaHorariaPrevisto = new GridBagConstraints();
        gbc_labelCargaHorariaPrevisto.insets = new Insets(0, 0, 0, 5);
        gbc_labelCargaHorariaPrevisto.gridx = 1;
        gbc_labelCargaHorariaPrevisto.gridy = 3;
        panel_1.add(labelCargaHorariaPrevisto, gbc_labelCargaHorariaPrevisto);
        
        labelCargaHorariaReal = new JLabel("New label");
        GridBagConstraints gbc_labelCargaHorariaReal = new GridBagConstraints();
        gbc_labelCargaHorariaReal.gridx = 2;
        gbc_labelCargaHorariaReal.gridy = 3;
        panel_1.add(labelCargaHorariaReal, gbc_labelCargaHorariaReal);       
       }

	public void update(Tarefa tarefa) {
		setComponent(tarefa);
	}
	
}
