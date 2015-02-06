package br.ufpa.spider.pe.view.pm.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.JobAttributes;

import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Dimension;
import javax.swing.JTextField;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;

import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.Log;
import br.ufpa.spider.pe.model.Papel;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.Transicao;
import br.ufpa.spider.pe.model.dao.LogDAO;
import br.ufpa.spider.pe.model.dao.TarefaDAO;
import br.ufpa.spider.pe.model.dao.TransicaoDAO;
import br.ufpa.spider.pe.view.JDialog_Spider_Login;
import br.ufpa.spider.pe.view.pm.logic.ComponentImageFile;
import br.ufpa.spider.pe.view.pm.logic.ExecutionController;
import br.ufpa.spider.pe.view.pm.logic.FindByProcess;
import br.ufpa.spider.pe.view.pm.logic.ModellingController;
import br.ufpa.spider.pe.view.pm.model.Component;
import br.ufpa.spider.pe.view.pm.model.ComponentType;
import br.ufpa.spider.pe.view.pm.model.DiagramComponent;
import br.ufpa.spider.pe.view.pm.model.DiagramRelationship;
import br.ufpa.spider.pe.view.pm.model.Modelling;
import br.ufpa.spider.pe.view.pm.model.Relationship;
import br.ufpa.spider.pe.view.util.Mensagem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Font;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TaskUseFeedBackPanel extends JPanel {
	private JSlider slider;
	private JLabel label;
	private JComboBox comboBox;
	private JTextArea textArea;
	Tarefa tarefa;
	int percentagem;
	/**
	 * Create the panel.
	 */
	public TaskUseFeedBackPanel(Tarefa task) {
		tarefa = task;
		setBorder(new EmptyBorder(10, 10, 10, 10));		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{48, 100, 26, 0};
		gridBagLayout.rowHeights = new int[]{0, 43, 0, 120, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblEstado = new JLabel("Estado");
		GridBagConstraints gbc_lblEstado = new GridBagConstraints();
		gbc_lblEstado.anchor = GridBagConstraints.WEST;
		gbc_lblEstado.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstado.gridx = 0;
		gbc_lblEstado.gridy = 0;
		add(lblEstado, gbc_lblEstado);
		
		comboBox = new JComboBox();		
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent arg0) {
				//jButtonOK();
			}
		});
		
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		add(comboBox, gbc_comboBox);
		
		final JLabel lblExecuo = new JLabel("Execu\u00E7\u00E3o");
		GridBagConstraints gbc_lblExecuo = new GridBagConstraints();
		gbc_lblExecuo.anchor = GridBagConstraints.WEST;
		gbc_lblExecuo.insets = new Insets(0, 0, 5, 5);
		gbc_lblExecuo.gridx = 0;
		gbc_lblExecuo.gridy = 1;
		add(lblExecuo, gbc_lblExecuo);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{100, 0, 26, 0};
		gbl_panel.rowHeights = new int[]{43, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.fill = GridBagConstraints.BOTH;
		gbc_slider.gridwidth = 2;
		gbc_slider.insets = new Insets(0, 0, 0, 5);
		gbc_slider.gridx = 0;
		gbc_slider.gridy = 0;
		panel.add(slider, gbc_slider);
		slider.setPreferredSize(new Dimension(10, 20));
		//slider.setMajorTickSpacing(3);
		//slider.setPaintTicks(true);
		slider.setPaintLabels(false);
		slider.setSnapToTicks(true);
		
		label = new JLabel("%");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.gridx = 2;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);
		
		slider.addChangeListener(
			      new ChangeListener(){
			        public void stateChanged(ChangeEvent e) {
			          JSlider comp = (JSlider) e.getSource();			          
			          if(!comp.getValueIsAdjusting())
			        	  percentagem = (new Integer(comp.getValue()));
			        	  label.setText((new Integer(comp.getValue())).toString() + "%");			        	  
			          label.repaint();
			        }
			      }
			    );
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		GridBagConstraints gbc_lblDescrio = new GridBagConstraints();
		gbc_lblDescrio.anchor = GridBagConstraints.WEST;
		gbc_lblDescrio.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescrio.gridx = 0;
		gbc_lblDescrio.gridy = 2;
		add(lblDescrio, gbc_lblDescrio);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);
		
		textArea = new JTextArea();
		
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				 if (arg0.getKeyCode() == KeyEvent.VK_ENTER){  
					 //jButtonOK();
				 }
			}
		});
		
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		
		JButton btnOk = new JButton("Salvar");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonOK();
			}
		});
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.gridx = 2;
		gbc_btnOk.gridy = 4;
		add(btnOk, gbc_btnOk);
		populaComboBox();
		setComponent(task);
				
	}

	protected void jButtonOK() {
		if(!comboBox.getSelectedItem().equals("Selecione...")){
			tarefa.setPercentagemExecucao(percentagem);
			tarefa.setDescricao(textArea.getText());
			if(comboBox.getSelectedItem().equals("N\u00e3o Inicializada")){
				saveTarefa(tarefa);
			}
			else if(formalismoExecucao(tarefa)==true){
				saveTarefa(tarefa);
			}
			
			
		}
	}

	private void saveTarefa(Tarefa tarefa) {
		tarefa.setEstado(comboBox.getSelectedItem().toString());
        Log log = new Log();
        log.setEstado(comboBox.getSelectedItem().toString());
        log.setDate(new Date());
        log.setOrdem( TarefaDAO.getOrdem(tarefa)+ 1);
        log.setTarefa(tarefa);
        LogDAO.createLog(log);
        tarefa.getLog().add(log);
        TarefaDAO.updateTarefa(tarefa);
       
        Component component = Modelling.getModelling().getComponent(
                tarefa.getIdComponent());
        DiagramComponent diagram = component.getDiagram().getDiagramComponent(
                tarefa.getIdComponent());
        long x = (long) Math.round(diagram.getX());
        long y = (long) Math.round(diagram.getY());
        int x1 = (int) x;
        int y2 = (int) y;
        mxCell cell = (mxCell) ModellingController.getCurrentGraph().getCellAt(
                x1, y2);

        if (cell != null) {
            if (tarefa.getEstado().equals("Em Execu\u00E7\u00E3o"))
                ModellingController.getCurrentGraph().getGraph().getModel()
                        .setStyle(cell, ComponentImageFile.TASK_USE_PLAY);
            else if (tarefa.getEstado().equals("Pausada"))
                ModellingController.getCurrentGraph().getGraph().getModel()
                        .setStyle(cell, ComponentImageFile.TASK_USE_PAUSED);
            else if (tarefa.getEstado().equals("Finalizada"))
                ModellingController.getCurrentGraph().getGraph().getModel()
                        .setStyle(cell, ComponentImageFile.TASK_USE_FINISHED);
            else
                ModellingController.getCurrentGraph().getGraph().getModel()
                        .setStyle(cell, ComponentImageFile.TASK_USE);

        }
       
       
       
        ModellingController.addDiagramElements(ModellingController.currentGraph.getGraph(),
                Modelling.getModelling()
                        .getComponent(ModellingController.componentShowingDiagramId).getDiagram());
       
        if(tarefa.getIteracao()!=null)
        	ModellingController.addDiagramElements(ModellingController.getGraphComponent(tarefa.getIteracao().getIdComponent()).getGraph(),
                Modelling.getModelling().getComponent(tarefa.getIteracao().getIdComponent()).getDiagram());
        else if(tarefa.getAtividade()!=null)
        	ModellingController.addDiagramElements(ModellingController.getGraphComponent(tarefa.getAtividade().getIdComponent()).getGraph(),
                    Modelling.getModelling().getComponent(tarefa.getAtividade().getIdComponent()).getDiagram());
       
        ModellingController.getCurrentGraph().getGraph().refresh();       
        ModellingController.getCurrentGraph().getGraph().repaint();
        ModellingController.getCurrentGraph().revalidate();
        ModellingController.getCurrentGraph().repaint();
        ModellingController.getCurrentGraph().refresh();
       
        ExecutionController.update(tarefa);
       
        ModellingController.update();
		
	}

	private boolean formalismoExecucao(Tarefa tarefa) {
             Component component = Modelling.getModelling().getComponent(tarefa.getIdComponent());
             String label = null;
             Tarefa tarefaController = null;
             String fork = null;
             ArrayList<DiagramRelationship> relantionships = null;
             if(tarefa.getAtividade()!=null){
            	 relantionships = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getAtividade().getIdComponent()).getDiagram().getDiagramRelationships();
             }
             else if (tarefa.getIteracao()!=null){
            	 relantionships = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getIteracao().getIdComponent()).getDiagram().getDiagramRelationships();
             }
             else if (tarefa.getFase()!=null){
            	 relantionships = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getFase().getIdComponent()).getDiagram().getDiagramRelationships();
            	 }
             
             for(DiagramRelationship diagramaComponent: relantionships){
                 Relationship transicao = Modelling.getModelling().getRelationship(diagramaComponent.getId());
                 Modelling.getModelling().getRelationship(diagramaComponent.getId());
                 Transicao trans = TransicaoDAO.findById(transicao.getId(), transicao.getSourceId(), transicao.getDestinationId(), JDialog_Spider_Login.getInstance().view.getProcesso().getId());
                 if(trans!=null)    {
                     if(transicao.getDestinationId() == tarefa.getIdComponent()){
                         label = trans.getTipo();
                         tarefaController = FindByProcess.findTarefa(Modelling.getModelling().getComponent(transicao.getSourceId()));
                     }
                 }                        
             }                    
    
             //TASK_USE -> FORK
             ArrayList<DiagramRelationship> relantionshipsaux = null;
             for(DiagramRelationship diagramaComponent: relantionships)
             {
            	 Relationship relationship = Modelling.getModelling().getRelationship(diagramaComponent.getId());
                     if(relationship.getDestinationId().equals(tarefa.getIdComponent())){
                         Component componentSource = Modelling.getModelling().getComponent(relationship.getSourceId());
                         if(componentSource.getType().equals(ComponentType.FORK)){
                        	 if(tarefa.getAtividade()!=null){
                        		 relantionshipsaux = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getAtividade().getIdComponent()).getDiagram().getDiagramRelationships();
                             }
                             else if (tarefa.getIteracao()!=null){
                            	 relantionshipsaux = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getIteracao().getIdComponent()).getDiagram().getDiagramRelationships();
                             }
                             else if (tarefa.getFase()!=null){
                            	 relantionshipsaux = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getFase().getIdComponent()).getDiagram().getDiagramRelationships();
                            	 }
                             for(DiagramRelationship diagramaComponent1: relantionshipsaux)
                             {
                                 Relationship relationship1 = Modelling.getModelling().getRelationship(diagramaComponent1.getId());
                                 if(relationship1.getDestinationId().equals(componentSource.getId())){
                                	 if(tarefa.getAtividade()!=null)
                                		 fork =  Modelling.getModelling().getComponent(tarefa.getAtividade().getIdComponent()).getDiagram().getDiagramComponent(componentSource.getId()).toString();
                                     else if (tarefa.getIteracao()!=null)
                                    	 fork =  Modelling.getModelling().getComponent(tarefa.getIteracao().getIdComponent()).getDiagram().getDiagramComponent(componentSource.getId()).toString();
                                     else if (tarefa.getFase() !=null)
                                    	 fork =  Modelling.getModelling().getComponent(tarefa.getFase().getIdComponent()).getDiagram().getDiagramComponent(componentSource.getId()).toString();
                                     Transicao trans = TransicaoDAO.findById(relationship1.getId(), relationship1.getSourceId(), relationship1.getDestinationId(), JDialog_Spider_Login.getInstance().view.getProcesso().getId());
                                     label = trans.getTipo();
                                     tarefaController = FindByProcess.findTarefa(Modelling.getModelling().getComponent(relationship1.getSourceId()));
                                     break;
                                 }
                             }
                         }  else if (componentSource.getType().equals(ComponentType.DECISION_AND_MERGE)){
                        	 if(tarefa.getAtividade()!=null){
                        		 relantionshipsaux = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getAtividade().getIdComponent()).getDiagram().getDiagramRelationships();
                             }
                             else if (tarefa.getIteracao()!=null){
                            	 relantionshipsaux = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getIteracao().getIdComponent()).getDiagram().getDiagramRelationships();
                             }
                             else if (tarefa.getFase()!=null){
                            	 relantionshipsaux = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getFase().getIdComponent()).getDiagram().getDiagramRelationships();
                            	 }
                         for(DiagramRelationship diagramaComponent1: relantionshipsaux)
                         {
                             Relationship relationship1 = Modelling.getModelling().getRelationship(diagramaComponent1.getId());
                             if(relationship1.getDestinationId().equals(componentSource.getId())){
                                 Transicao trans = TransicaoDAO.findById(relationship1.getId(), relationship1.getSourceId(), relationship1.getDestinationId(), JDialog_Spider_Login.getInstance().view.getProcesso().getId());
                                 label = trans.getTipo();
                                 tarefaController = FindByProcess.findTarefa(Modelling.getModelling().getComponent(relationship1.getSourceId()));
                                 break;
                             }
                         }
                     }
                 }
             }
            
             //JOIN -> TASK_USE
             ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
             for(DiagramRelationship diagramaComponent: relantionships)
             {
                 Relationship relationship = Modelling.getModelling().getRelationship(diagramaComponent.getId());
                 if(relationship.getDestinationId().equals(tarefa.getIdComponent())){
                     Component componentSource = Modelling.getModelling().getComponent(relationship.getSourceId());
                     if(componentSource.getType().equals(ComponentType.JOIN)){
                         Transicao trans = TransicaoDAO.findById(relationship.getId(), relationship.getSourceId(), relationship.getDestinationId(), JDialog_Spider_Login.getInstance().view.getProcesso().getId());
                         label = trans.getTipo();
                         if(tarefa.getAtividade()!=null){
                    		 relantionshipsaux = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getAtividade().getIdComponent()).getDiagram().getDiagramRelationships();
                         }
                         else if (tarefa.getIteracao()!=null){
                        	 relantionshipsaux = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getIteracao().getIdComponent()).getDiagram().getDiagramRelationships();
                         }
                         else if (tarefa.getFase()!=null){
                        	 relantionshipsaux = (ArrayList<DiagramRelationship>) Modelling.getModelling().getComponent(tarefa.getFase().getIdComponent()).getDiagram().getDiagramRelationships();
                        	 }
                         for(DiagramRelationship diagramaComponent1: relantionshipsaux)
                         {
                             Relationship relationship1 = Modelling.getModelling().getRelationship(diagramaComponent1.getId());
                             if(relationship1.getDestinationId().equals(componentSource.getId())){
                                 tarefas.add(FindByProcess.findTarefa(Modelling.getModelling().getComponent(relationship1.getSourceId())));
                             }
                         }
                     }
                 }
             }       
             
             /*Depois de pegar as duas tarefas anteriores a um fork, eu crio uma com a combinação do estado das duas*/
             if(!tarefas.isEmpty())
                tarefaController = ExecutionController.getEstadoTarefaJoinTaskUse(tarefas);
             
             if(comboBox.getSelectedItem().toString().equals(ExecutionController.EXECUCAO)){
            	 if(tarefaController!=null){                		
	                    if(ModellingController.taskController(label, tarefaController, ExecutionController.EXECUCAO, tarefa, fork)){
	                           return true;
	                     } 
             	} else return true;        
             } else if (comboBox.getSelectedItem().toString().equals(ExecutionController.PAUSADA)){
            	 if(tarefaController!=null && (fork!=null || label!=null)){
	                    if(ModellingController.taskController(label, tarefaController, ExecutionController.PAUSADA, tarefa, fork))
	                        return true;
             	}
             	else if(tarefaController==null && tarefa!=null){
             		return true;
             	}
             } else if (comboBox.getSelectedItem().toString().equals(ExecutionController.FINALIZADA)){
            	 if(tarefaController!=null){
	                    if(ModellingController.taskController(label, tarefaController, ExecutionController.FINALIZADA, null, null)){
	                    	ModellingController.taskExtensaoComponentsFinalizar(tarefa);
	                        return true; 
	                                               
	                        }
             	}else if(tarefaController==null && tarefa!=null){             		
             		ModellingController.taskExtensaoComponentsFinalizar(tarefa);
             		 return true;
             	}
             }
             return false;
	}	

	private void populaComboBox() {
		comboBox.removeAllItems();
		comboBox.addItem("Selecione...");
		comboBox.addItem("Em Execu\u00e7\u00e3o");
		comboBox.addItem("Pausada");
		comboBox.addItem("N\u00e3o Inicializada");
		comboBox.addItem("Finalizada");
		comboBox.setSelectedItem(tarefa.getEstado());
	}

	public void setComponent(Tarefa tarefaToShow) {
		tarefa = tarefaToShow;	
		slider.setValue(tarefa.getPercentagemExecucao());
		textArea.setText(tarefa.getDescricao());
		comboBox.setSelectedItem(tarefa.getEstado() == null? "Pausada":tarefa.getEstado());
		validate();
	}

	public void update() {		
		tarefa.setPercentagemExecucao(percentagem);
		tarefa.setDescricao(textArea.getText());
		tarefa.setEstado(comboBox.getSelectedItem().toString());
		TarefaDAO.updateTarefa(tarefa);
	}
}
