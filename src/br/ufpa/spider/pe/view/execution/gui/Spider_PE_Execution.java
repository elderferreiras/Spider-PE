package br.ufpa.spider.pe.view.execution.gui;



import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.TreePath;

import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.Papel;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.dao.GenericDAO;
import br.ufpa.spider.pe.model.dao.ProcessoDAO;
import br.ufpa.spider.pe.model.util.DataBaseManager;
import br.ufpa.spider.pe.view.Spider_PE_Home;
import br.ufpa.spider.pe.view.administration.JDialogSenha;
import br.ufpa.spider.pe.view.management.JDialogWelcome;
import br.ufpa.spider.pe.view.management.JFrameLog;
import br.ufpa.spider.pe.view.management.JFrameNotification;
import br.ufpa.spider.pe.view.execution.logic.ExecutionController;
import br.ufpa.spider.pe.view.execution.logic.ModellingController;
import br.ufpa.spider.pe.view.execution.model.Modelling;
import br.ufpa.spider.pe.view.execution.persistence.ModellingFileManager;

public class Spider_PE_Execution extends JFrame implements Runnable{
	public JPanel diagramPanel;
	private JSplitPane verticalSplitPane;
	private JTabbedPane diagramTabbedPane;
	private JSplitPane horizontalSplitPane;
	private static JTabbedPane propertiesTabbedPane;
	private JPanel panelInformacoes;
	private JPanel panelInteracao;
	private JTree processPackageTree;
	private JScrollPane propertiesScrollPane;
	private static JPanel mainPanel;
	private JScrollPane processPackageScrollPane;
	private JPanel panel_2;
	private JLabel label;
	private JToolBar toolBar;
	private JLabel label_1;
	private JLabel label_2;
	private JComboBox jComboBoxProcesso;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton jButtonLogout;
	private JMenu mnUsurio;
	private JMenu mnVisualizar;
	private JMenu mnTipos;
	private JMenu mnAjuda;
	private JMenuItem mntmAlterarAcesso;
	private JMenuItem mntmLogoff;
	private Processo processo = null;
	private JScrollPane feedBackScrollPane;
	
	
	public Spider_PE_Execution(){
		setExtendedState(Frame.MAXIMIZED_BOTH);			     
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(Spider_PE_Execution.class.getResource("/br/ufpa/spider/pe/view/util/img/favicon_pe.png")));	
		setTitle("Process User");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{516, 0};
		gridBagLayout.rowHeights = new int[]{0, 385, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		panel_2 = new JPanel();
		panel_2.setForeground(SystemColor.menu);
		panel_2.setBorder(null);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		getContentPane().add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 600, 145, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 25, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 4.9E-324, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBorderPainted(false);
		toolBar.setBorder(new EmptyBorder(0, 0, 0, 0));
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.fill = GridBagConstraints.BOTH;
		gbc_toolBar.gridheight = 2;
		gbc_toolBar.gridwidth = 7;
		gbc_toolBar.insets = new Insets(0, 0, 5, 5);
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		panel_2.add(toolBar, gbc_toolBar);
		
		button = new JButton("");
		button.setIcon(new ImageIcon(Spider_PE_Execution.class.getResource("/br/ufpa/spider/pe/view/util/img/home.png")));
		toolBar.add(button);
		
		button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonNotification();
			}
		});
		button_1.setIcon(new ImageIcon(Spider_PE_Execution.class.getResource("/br/ufpa/spider/pe/view/util/img/warning2.png")));
		toolBar.add(button_1);
		
		button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonLog();
			}

		});
		button_2.setIcon(new ImageIcon(Spider_PE_Execution.class.getResource("/br/ufpa/spider/pe/view/util/img/history.png")));
		toolBar.add(button_2);
		
		jButtonLogout = new JButton("");
		jButtonLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonLogout();
			}
		});
		jButtonLogout.setIcon(new ImageIcon(Spider_PE_Execution.class.getResource("/br/ufpa/spider/pe/view/util/img/logout.png")));
		toolBar.add(jButtonLogout);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Spider_PE_Execution.class.getResource("/br/ufpa/spider/pe/view/util/img/logo_spider-pe.png")));
		label_1.setBorder(new EmptyBorder(0, 100, 0, 0));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.gridheight = 3;
		gbc_label_1.insets = new Insets(0, 0, 0, 5);
		gbc_label_1.gridx = 7;
		gbc_label_1.gridy = 0;
		panel_2.add(label_1, gbc_label_1);
		
		label = new JLabel("Bem-vindo, " + Spider_PE_Home.getInstance().getInstanciaLogado());
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 9;
		gbc_label.gridy = 0;
		panel_2.add(label, gbc_label);
		
		label_2 = new JLabel("Processo:");
		label_2.setIcon(new ImageIcon(Spider_PE_Execution.class.getResource("/br/ufpa/spider/pe/view/util/img/process.png")));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 8;
		gbc_label_2.gridy = 1;
		panel_2.add(label_2, gbc_label_2);
		
		jComboBoxProcesso = new JComboBox();
		jComboBoxProcesso.setToolTipText("Selecione...");
		jComboBoxProcesso.setPreferredSize(new Dimension(150, 20));
		GridBagConstraints gbc_jComboBoxProcesso = new GridBagConstraints();
		gbc_jComboBoxProcesso.anchor = GridBagConstraints.EAST;
		gbc_jComboBoxProcesso.insets = new Insets(0, 0, 5, 0);
		gbc_jComboBoxProcesso.gridx = 9;
		gbc_jComboBoxProcesso.gridy = 1;
		panel_2.add(jComboBoxProcesso, gbc_jComboBoxProcesso);
		
		mainPanel = new JPanel();
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.fill = GridBagConstraints.BOTH;
		gbc_mainPanel.gridx = 0;
		gbc_mainPanel.gridy = 1;
		getContentPane().add(mainPanel, gbc_mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{516, 0};
		gbl_mainPanel.rowHeights = new int[]{385, 0};
		gbl_mainPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		horizontalSplitPane = new JSplitPane();
		horizontalSplitPane.setDividerLocation(350);
		GridBagConstraints gbc_horizontalSplitPane = new GridBagConstraints();
		gbc_horizontalSplitPane.fill = GridBagConstraints.BOTH;
		gbc_horizontalSplitPane.gridx = 0;
		gbc_horizontalSplitPane.gridy = 0;
		mainPanel.add(horizontalSplitPane, gbc_horizontalSplitPane);
		
		diagramTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		horizontalSplitPane.setRightComponent(diagramTabbedPane);
		
		diagramPanel = new JPanel();
		diagramTabbedPane.addTab("",diagramPanel);
		diagramPanel.setLayout(new BorderLayout(0, 0));
		verticalSplitPane = new JSplitPane();		
		verticalSplitPane.setDividerLocation(350);
		verticalSplitPane.setPreferredSize(new Dimension(250, 25));
		verticalSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		horizontalSplitPane.setLeftComponent(verticalSplitPane);
		
		propertiesTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		propertiesTabbedPane.setPreferredSize(new Dimension(72, 64));
		
		
		
		verticalSplitPane.setRightComponent(propertiesTabbedPane);
	
		processPackageScrollPane = new JScrollPane();
		processPackageScrollPane.setPreferredSize(new Dimension(72, 64));
		verticalSplitPane.setLeftComponent(processPackageScrollPane);
		
		processPackageTree = new JTree();
		processPackageTree.setModel(null);
		processPackageTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treeMouseClicked(evt);
            }
        });		
		processPackageScrollPane.setViewportView(processPackageTree);
	    processPackageTree.setCellRenderer(new TreeCellRenderer());
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnUsurio = new JMenu("Usu\u00E1rio");
		menuBar.add(mnUsurio);
		
		mntmAlterarAcesso = new JMenuItem("Alterar Acesso");
		mntmAlterarAcesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonAlterarAcesso();
			}
		});
		mnUsurio.add(mntmAlterarAcesso);
		
		mntmLogoff = new JMenuItem("Logoff");
		mnUsurio.add(mntmLogoff);
		
		mnVisualizar = new JMenu("Visualizar");
		menuBar.add(mnVisualizar);
		
		mnTipos = new JMenu("Tipos");
		menuBar.add(mnTipos);
		
		mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		
		ItemListener listener = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					setCursor(Cursor.WAIT_CURSOR);
					itemListenerStateChaged(e);
					setCursor(Cursor.DEFAULT_CURSOR);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		
		jComboBoxProcesso.addItemListener(listener);
		populaComboBoxProcesso();
		
	}
	 protected void jButtonNotification() {
		JFrameNotification frame = new JFrameNotification();
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(frame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		
	}
	protected void jButtonAlterarAcesso() {
		 try{
				Humano humano = (Humano) Spider_PE_Home.getInstance().getInstanciaLogado();
				JDialogSenha senha = new JDialogSenha(humano);
				senha.setLocationRelativeTo(null);
				senha.setVisible(true);
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	protected void jButtonLogout() {
		 this.dispose();	
			JDialogWelcome.getInstance().setVisible(true);
			Spider_PE_Home.getInstance().setVisible(true);
	}
	
	protected void itemListenerStateChaged(ItemEvent e) throws IOException {
		 if(!jComboBoxProcesso.getSelectedItem().toString().equals("Selecione...")){		 
			 processo = (Processo) jComboBoxProcesso.getSelectedItem();			
			byte[] b  = processo.getProcesso_xml();
		       
	        final File fileDestiny =  File.createTempFile(processo.getNome(), ".xml");	
	       
	        try {
	        	FileOutputStream o = new FileOutputStream(fileDestiny);       
				o.write(b); 
		        o.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
	       
	        openFile(fileDestiny);
               
			validate();
			repaint();
			
			
			
		 } else {
			processPackageTree.setModel(null);			
			processPackageTree.setCellRenderer(new TreeCellRenderer());		
			diagramPanel.removeAll();
			diagramPanel.setLayout(new BorderLayout(0, 0));
			diagramTabbedPane.removeAll();
			diagramTabbedPane.addTab("",diagramPanel);
			propertiesTabbedPane.removeAll();
			 
			propertiesScrollPane = new JScrollPane();
			repaint();
		 }
		
	}
	
	private void populaComboBoxProcesso(){
		 jComboBoxProcesso.addItem("Selecione...");
		 for (Processo processo : ProcessoDAO.findAll()) {
			if(processo.getHumano().contains((Humano) Spider_PE_Home.getInstance().getInstanciaLogado())){
				jComboBoxProcesso.addItem(processo);
			}
		}
	}
	 
	 private void treeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeMouseClicked
		
	        JTree tree = (JTree) evt.getSource();	        
	        TreePath treePath = tree.getSelectionPath();
	        if (treePath != null) {
	            if (evt.getClickCount() == 2) {
	                ModellingController.setSelectedComponent(((TreeNode) treePath.getLastPathComponent()).getId());
	                tree.expandPath(treePath);
	            } else if (evt.getClickCount() == 1) {	   
	            	ModellingController.setElementToShowProperties(((TreeNode) treePath.getLastPathComponent()).getId());
	            }
	        }
	}
	 
	public void openFile(File file) throws FileNotFoundException, IOException {
		
		final String caminho = file.getPath();			
		ModellingController.setDiagramPanel(diagramPanel);
		ModellingController.setProcessPackageTree(processPackageTree);
		ModellingController.setPropertiesTabbedPane(propertiesTabbedPane);
		ModellingController.setSelectedComponent(null);
		Modelling.newModelling();
		ModellingController.initialize();
		ModellingController.setAutoCreateRequiredElements(false);
		ModellingFileManager.load(caminho);	
		ModellingController.setAutoCreateRequiredElements(true);
	}
	 	 
	 public static JPanel getMainPanel(){
		 return mainPanel;
	 }
	 
	 public Processo getProcesso(){		 
			 return processo;
	 }
	 
	 private void jButtonLog() {
			JFrameLog frame = new JFrameLog();
			frame.setExtendedState(Frame.MAXIMIZED_BOTH);
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			frame.setVisible(true);
		}
	@Override
	public void run() {
		Humano humano = (Humano) Spider_PE_Home.getInstance().getInstanciaLogado();
		while(true){
			for (Papel papel : humano.getPapeisAlocados()) {
				if(papel.getTarefa().getStatus()!= null){
					if(papel.getTarefa().getStatus().equals(ExecutionController.ATRASADA)){
						//JOptionPane.showMessageDialog(null, "A Tarefa: "+papel.getTarefa().getNome()+" está atrasada!");
					}
				}
			}
		}
		
	}
}