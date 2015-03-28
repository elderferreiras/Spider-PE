package br.ufpa.spider.pe.view.management;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreePath;

import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.Iteracao;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.view.Spider_PE_Home;
import br.ufpa.spider.pe.view.administration.JDialogSenha;
import br.ufpa.spider.pe.view.execution.gui.GraphComponent;
import br.ufpa.spider.pe.view.execution.gui.TreeCellRenderer;
import br.ufpa.spider.pe.view.execution.gui.TreeNode;
import br.ufpa.spider.pe.view.execution.logic.ComponentImageFile;
import br.ufpa.spider.pe.view.execution.logic.ModellingController;
import br.ufpa.spider.pe.view.execution.model.DiagramComponent;
import br.ufpa.spider.pe.view.execution.model.Modelling;
import br.ufpa.spider.pe.view.execution.persistence.ModellingFileManager;
import br.ufpa.spider.pe.view.util.Icone;
import br.ufpa.spider.pe.view.util.TreeMouseListenerAction;

import com.mxgraph.model.mxCell;

import javax.swing.JProgressBar;

public class Spider_PE_Management extends JFrame {
	private JSplitPane jSplitPane;
	private JPanel jPanelBackGround;
	private JMenuBar jMenuBarHome;
	private JMenu jMenuVisoes;
	private JMenu jMenuTipos;
	private JMenu jMenuAjuda;
	private JPanel jPanelNorte;
	private JToolBar jToolBarAcessivel;
	private JButton jButtonHome;
	private JButton jButtonReport;
	private JButton jButtonHistory;
	private JButton jButtonLogout;
	private JLabel jLabelLogoSpiderPe;
	private JLabel JLabelProcesso;
	private static JComboBox jComboBoxProcesso;
	private JSplitPane splitPane;
	private JSplitPane jSplitPanel;
	private JTabbedPane jTabbedPaneOeste;
	private JPanel jPanelGestao;
	private JPanel jPanelLeste;
	private JFrame jFrameTipos;
	private Iteracao iteracao;
	private TreeMouseListenerAction treeMouseListenerAction;
	private JMenuItem mntmTipos;
	private JLabel lblNewLabel;
	private JMenuItem menuItem;
	private JMenuItem menuItem_1;
	private JMenuItem mntmSobreASpiderpe;
	private JMenuItem mntmPolticaOrganizacional;
	private JMenuItem mntmTarefasDoProcesso;
	private JMenuItem mntmRecursosHumanos;
	private JMenuItem mntmRiscosIdentificados;
	private JMenuItem mntmTreinamentosPrevistos;
	private JMenu menu;
	private JMenuItem menuItem_3;
	private JMenuItem menuItem_4;
	private JMenu mnRecursos;
	private JMenuItem mntmGerenciar;
	private JMenu mnGerenciarAmbiente;
	private JMenuItem mntmHardware;
	private JMenuItem mntmSoftwrare;
	
	public Spider_PE_Management() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Spider_PE_Management.class.getResource("/br/ufpa/spider/pe/view/util/img/favicon_pe.png")));			
		initComponents();
	}


	private void initComponents() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 955, 469);
		setTitle("Spider-PE");
		// defini��o do look and feel
		try {
			String nomeSO = System.getProperty("os.name");
			if(nomeSO.contains("Linux")){
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			} else if(nomeSO.contains("Windows")) {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}
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
		// fim da defini��o do look and feel

		jMenuBarHome = new JMenuBar();
		jMenuBarHome.setBorder(null);
		jMenuBarHome.setBorderPainted(false);
		setJMenuBar(jMenuBarHome);
		jMenuVisoes = new JMenu("Visualizar");
		jMenuTipos = new JMenu("Tipos");
		jMenuAjuda = new JMenu("Ajuda");
		
		menu = new JMenu("Usu\u00E1rio");
		jMenuBarHome.add(menu);
		
		menuItem_3 = new JMenuItem("Alterar Acesso");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonAlterarAcesso();
			}
		});
		menu.add(menuItem_3);
		
		menuItem_4 = new JMenuItem("Logoff");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonLogout();
			}
		});
		menu.add(menuItem_4);
		jMenuBarHome.add(jMenuVisoes);
		
		mntmPolticaOrganizacional = new JMenuItem("Pol\u00EDtica Organizacional");
		jMenuVisoes.add(mntmPolticaOrganizacional);
		
		mntmTarefasDoProcesso = new JMenuItem("Tarefas do Processo");
		jMenuVisoes.add(mntmTarefasDoProcesso);
		
		mntmRecursosHumanos = new JMenuItem("Recursos Humanos");
		jMenuVisoes.add(mntmRecursosHumanos);
		
		mntmRiscosIdentificados = new JMenuItem("Riscos Identificados");
		jMenuVisoes.add(mntmRiscosIdentificados);
		
		mntmTreinamentosPrevistos = new JMenuItem("Treinamentos Previstos");
		jMenuVisoes.add(mntmTreinamentosPrevistos);
		jMenuBarHome.add(jMenuTipos);
		
		mntmTipos = new JMenuItem("Gerenciar");
		mntmTipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					jMenuItemActionPerformed(arg0);
			}
		});
		jMenuTipos.add(mntmTipos);
		
		mnRecursos = new JMenu("Recursos");
		jMenuBarHome.add(mnRecursos);
		
		mntmGerenciar = new JMenuItem("Gerenciar Humanos");
		mntmGerenciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonGerenciarHumanos();
			}
		});
		mnRecursos.add(mntmGerenciar);
		
		mnGerenciarAmbiente = new JMenu("Gerenciar Ambiente");
		mnRecursos.add(mnGerenciarAmbiente);
		
		mntmHardware = new JMenuItem("Hardware");
		mntmHardware.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonHardware();
			}
		});
		mnGerenciarAmbiente.add(mntmHardware);
		
		mntmSoftwrare = new JMenuItem("Software");
		mntmSoftwrare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonSoftware();
			}
		});
		mnGerenciarAmbiente.add(mntmSoftwrare);
		
		
		jMenuBarHome.add(jMenuAjuda);
		
		menuItem = new JMenuItem("Manual Spider-PE");
		jMenuAjuda.add(menuItem);
		
		menuItem_1 = new JMenuItem("Especifica\u00E7\u00E3o T\u00E9cnica xSPIDER_ML");
		jMenuAjuda.add(menuItem_1);
		
		mntmSobreASpiderpe = new JMenuItem("Sobre a Spider-PE");
		jMenuAjuda.add(mntmSobreASpiderpe);

		jPanelBackGround = new JPanel();
		jPanelBackGround.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(jPanelBackGround);

		GridBagLayout gbl_jPanelBackGround = new GridBagLayout(); // cria��o do
																	// gridbaglayout
																	// para o
																	// panel
		gbl_jPanelBackGround.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0,
				29, 0, 0, 41, 48, 33, 0, 0, 0, 0, 0, 27, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0 };
		gbl_jPanelBackGround.rowHeights = new int[] { 62, -15, 50, 0, 0, 23, 0,
				0, 0, 0, 0, 0 };
		gbl_jPanelBackGround.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, Double.MIN_VALUE };
		gbl_jPanelBackGround.rowWeights = new double[] { 0.0, 1.0, 1.0, 1.0,
				0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		jPanelBackGround.setLayout(gbl_jPanelBackGround);
															// gridbaglayout ao
															// panel

		jPanelNorte = new JPanel();
		jPanelNorte.setBorder(null);
		jPanelNorte.setForeground(UIManager.getColor("Button.background"));

		GridBagConstraints gbc_jPanelNorte = new GridBagConstraints();
		gbc_jPanelNorte.anchor = GridBagConstraints.NORTH;
		gbc_jPanelNorte.gridwidth = 29;
		gbc_jPanelNorte.insets = new Insets(0, 0, 5, 0);
		gbc_jPanelNorte.fill = GridBagConstraints.HORIZONTAL;
		gbc_jPanelNorte.gridx = 0;
		gbc_jPanelNorte.gridy = 0;
		jPanelBackGround.add(jPanelNorte, gbc_jPanelNorte);

		GridBagLayout gbl_jPanelNorte = new GridBagLayout(); // cria��o do
																// gridbaglayout
																// para o panel
		gbl_jPanelNorte.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 600, 145, 0, 0 };
		gbl_jPanelNorte.rowHeights = new int[] { 0, 25, 0 };
		gbl_jPanelNorte.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 1.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_jPanelNorte.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		jPanelNorte.setLayout(gbl_jPanelNorte); // adiciona o gridbaglayout ao
		
		lblNewLabel = new JLabel("Bem-vindo, "  + Spider_PE_Home.getInstance().getInstanciaLogado());
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 9;
		gbc_lblNewLabel.gridy = 0;
		jPanelNorte.add(lblNewLabel, gbc_lblNewLabel);
												// panel

		jToolBarAcessivel = new JToolBar();
		jToolBarAcessivel.setBorderPainted(false);
		jToolBarAcessivel.setBorder(new EmptyBorder(0, 0, 0, 0));
		jToolBarAcessivel.setFloatable(false);
		GridBagConstraints gbc_jToolBarAcessivel = new GridBagConstraints();
		gbc_jToolBarAcessivel.fill = GridBagConstraints.BOTH;
		gbc_jToolBarAcessivel.gridheight = 2;
		gbc_jToolBarAcessivel.gridwidth = 7;
		gbc_jToolBarAcessivel.insets = new Insets(0, 0, 0, 5);
		gbc_jToolBarAcessivel.gridx = 0;
		gbc_jToolBarAcessivel.gridy = 0;
		jPanelNorte.add(jToolBarAcessivel, gbc_jToolBarAcessivel);

		// adicionar botoes ao JToolBar
		jButtonHome = new JButton("");
		jButtonHome.setMargin(new Insets(1, 1, 1, 1));
		jButtonHome.setToolTipText("Home");
		jButtonHome.setBorderPainted(false);
		jButtonHome.setBorder(new EmptyBorder(5, 5, 5, 5));
		jButtonHome.setIcon(new Icone().getHome());
		jToolBarAcessivel.add(jButtonHome);

		jButtonReport = new JButton("");
		jButtonReport.setMargin(new Insets(1, 1, 1, 1));
		jButtonReport.setToolTipText("Relat\u00F3rios");
		jButtonReport.setBorder(new EmptyBorder(5, 5, 5, 5));
		jButtonReport.setBorderPainted(false);
		jButtonReport.setIcon(new Icone().getReport());
		jToolBarAcessivel.add(jButtonReport);

		jButtonHistory = new JButton("");
		jButtonHistory.setMargin(new Insets(1, 1, 1, 1));
		jButtonHistory.setToolTipText("Log de Execu\u00E7\u00E3o");
		jButtonHistory.setBorderPainted(false);
		jButtonHistory.setBorder(new EmptyBorder(5, 5, 5, 5));
		jButtonHistory.setIcon(new Icone().getHistory());
		jToolBarAcessivel.add(jButtonHistory);

		jButtonLogout = new JButton("");
		jButtonLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonLogout();
			}
		});
		jButtonLogout.setMargin(new Insets(1, 1, 1, 1));
		jButtonLogout.setToolTipText("Logout");
		jButtonLogout.setBorder(new EmptyBorder(5, 5, 5, 5));
		jButtonLogout.setBorderPainted(false);
		jButtonLogout.setIcon(new Icone().getLogout());
		jToolBarAcessivel.add(jButtonLogout);
		// fim da criacao de um JToolBar para "alterar senha"

		// setar a logo do spider
		jLabelLogoSpiderPe = new JLabel("");
		jLabelLogoSpiderPe.setBorder(new EmptyBorder(0, 100, 0, 0));
		jLabelLogoSpiderPe.setIcon(new Icone().getLogo_spider_pe());
		GridBagConstraints gbc_jLabelLogoSpiderPe = new GridBagConstraints();
		gbc_jLabelLogoSpiderPe.gridheight = 3;
		gbc_jLabelLogoSpiderPe.insets = new Insets(0, 0, 0, 5);
		gbc_jLabelLogoSpiderPe.gridx = 7;
		gbc_jLabelLogoSpiderPe.gridy = 0;
		jPanelNorte.add(jLabelLogoSpiderPe, gbc_jLabelLogoSpiderPe);
		// fim do setar a logo do spider

		JLabelProcesso = new JLabel("Processo:");
		JLabelProcesso.setIcon(new Icone().getProcess());
		GridBagConstraints gbc_JLabelProcesso = new GridBagConstraints();
		gbc_JLabelProcesso.insets = new Insets(0, 0, 0, 5);
		gbc_JLabelProcesso.anchor = GridBagConstraints.EAST;
		gbc_JLabelProcesso.gridx = 8;
		gbc_JLabelProcesso.gridy = 1;
		jPanelNorte.add(JLabelProcesso, gbc_JLabelProcesso);

		jComboBoxProcesso = new JComboBox();		
		ItemListener listener = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					itemListenerStateChaged(e);
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		
		jComboBoxProcesso.addItemListener(listener);
		jComboBoxProcesso.setPreferredSize(new Dimension(200, 20));
		jComboBoxProcesso.setToolTipText("Selecione...");
		
		GridBagConstraints gbc_jComboBoxProcesso = new GridBagConstraints();
		gbc_jComboBoxProcesso.anchor = GridBagConstraints.EAST;
		gbc_jComboBoxProcesso.gridx = 9;
		gbc_jComboBoxProcesso.gridy = 1;
		jPanelNorte.add(jComboBoxProcesso,
				gbc_jComboBoxProcesso);

		jPanelLeste = new JPanel();
		JPanel jPanelOeste = new JPanel();
		jPanelOeste.setMinimumSize(new Dimension(350, 10));
		jPanelOeste.setPreferredSize(new Dimension(200, 10));

		jSplitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jPanelOeste,
				jPanelLeste);
		
		jPanelLeste.setLayout(new BorderLayout(1, 1));
		
		GridBagLayout gbl_jPanelOeste = new GridBagLayout();
		gbl_jPanelOeste.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 57, 0 };
		gbl_jPanelOeste.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0 };
		gbl_jPanelOeste.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, Double.MIN_VALUE };
		gbl_jPanelOeste.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		jPanelOeste.setLayout(gbl_jPanelOeste);

		jTabbedPaneOeste = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_jTabbedPaneOeste = new GridBagConstraints();
		gbc_jTabbedPaneOeste.gridheight = 11;
		gbc_jTabbedPaneOeste.gridwidth = 7;
		gbc_jTabbedPaneOeste.fill = GridBagConstraints.BOTH;
		gbc_jTabbedPaneOeste.gridx = 0;
		gbc_jTabbedPaneOeste.gridy = 0;
		jPanelOeste.add(jTabbedPaneOeste, gbc_jTabbedPaneOeste);

		jPanelGestao = new JPanelArvore();
		
		treeMouseListenerAction = new TreeMouseListenerAction(((JPanelArvore) jPanelGestao).getArvore(), getjPanelLeste());
		
		((JPanelArvore) jPanelGestao).getArvore().addMouseListener(treeMouseListenerAction);
		jTabbedPaneOeste.addTab("Gest\u00E3o", null, jPanelGestao, null);
		
		panelProcess = new JPanel();
		jTabbedPaneOeste.addTab("Processo", null, panelProcess, null);
		panelProcess.setLayout(new BorderLayout(0, 0));
		
		splitPane_1 = new JSplitPane();
		splitPane_1.setDividerLocation(350);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panelProcess.add(splitPane_1);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(10, 10));
		splitPane_1.setLeftComponent(scrollPane_1);
		
		treeProcess = new JTree();
		treeProcess.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treeMouseClicked(evt);
            }
			@Override
			public void mouseReleased(MouseEvent evt) {				
				 JTree tree = (JTree) evt.getSource();	        
			     final TreePath treePath = tree.getSelectionPath(); 
			     final GraphComponent graph = ModellingController.getGraphComponent(((TreeNode) treePath.getLastPathComponent()).getId());
			     if (treePath != null) {
				
				 final JPopupMenu popup = new JPopupMenu();  
				    JMenuItem menuItemForkJuncao = new JMenuItem("Exportar PNG");
				    popup.add(menuItemForkJuncao);  
				    menuItemForkJuncao.setIcon(new ImageIcon(Icone.class.getResource(ComponentImageFile.PNG)));  
				    menuItemForkJuncao.addActionListener(new ActionListener() {				
						@Override
						public void actionPerformed(ActionEvent e) {
							if(graph != null){
								int miny = graph.getHeight();
								int minx = graph.getWidth();
								int maxy = 0;
								int maxx = 0;
								Dimension d = graph.getGraphControl().getSize();								
								 Object[] cells = graph.getGraph().getChildCells(graph.getGraph().getDefaultParent());
							        for (Object cellobj : cells) {
							        	mxCell cell = (mxCell) cellobj;
							        	if(cell.getValue().getClass() == DiagramComponent.class){
				    	 					DiagramComponent dc = ((DiagramComponent) cell.getValue());
							        		
				    	 					if(dc.getY()<=miny){
				    	 						long x = (long) Math.round(dc.getY());				    	 						
				    	 						miny = (int)x;
				    	 					} 
				    	 					else if(dc.getY()>=maxy){
				    	 						long x = (long) Math.round(dc.getY());				    	 						
				    	 						maxy = (int)x;
				    	 					}
				    	 					
				    	 					if(dc.getX()<=minx){
				    	 						long x = (long) Math.round(dc.getX());				    	 						
				    	 						minx = (int)x;
				    	 					} else if(dc.getX() >= maxx){
				    	 						long x = (long) Math.round(dc.getX());				    	 						
				    	 						maxx = (int)x;
				    	 					}
				    	 					
							        	}
							        }
							        
								BufferedImage image = new BufferedImage(maxx + 60, maxy + 60, BufferedImage.TYPE_INT_ARGB);
								Graphics2D g = image.createGraphics();
								graph.getGraphControl().paint(g);
								
								JFileChooser fileChooser = new JFileChooser();

							    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

							    fileChooser.showSaveDialog(null);
							    
								final File outputfile = new File(fileChooser.getSelectedFile().getPath()+"\\"+Modelling.getModelling().getComponent(((TreeNode) treePath.getLastPathComponent()).getId()).getName()+".png");
								try {
									ImageIO.write(image, "png", outputfile);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}						
						}
					});
				    
				    if(graph!=null)
				  if(((TreeNode) treePath.getLastPathComponent()).getId() == ModellingController.getComponentShowingDiagramId())
				    if (evt.isPopupTrigger()) {  
		                popup.show(evt.getComponent(),  
		                		evt.getX(), evt.getY());  
		            }  
			     }
			}
        });	
		scrollPane_1.setViewportView(treeProcess);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane_1.setRightComponent(tabbedPane);
		
		scrollPane = new JScrollPane();
		tabbedPane.addTab("Informa\u00e7\u00f5es", null, scrollPane, null);

		GridBagConstraints gbc_jSplitPanel = new GridBagConstraints();
		gbc_jSplitPanel.gridheight = 10;
		gbc_jSplitPanel.gridwidth = 29;
		gbc_jSplitPanel.fill = GridBagConstraints.BOTH;
		gbc_jSplitPanel.gridx = 0;
		gbc_jSplitPanel.gridy = 1;
		jPanelBackGround.add(jSplitPanel, gbc_jSplitPanel);

		splitPane = new JSplitPane();
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.insets = new Insets(0, 0, 0, 5);
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 0;

		jPanelOeste = new JPanel();
		splitPane.setLeftComponent(jPanelOeste);
		populaComboBoxProcesso();
	}


	protected void jButtonGerenciarHumanos() {
		JDialogGerenciarHumanos dialog = new JDialogGerenciarHumanos();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
	protected void jButtonSoftware() {
		JDialogGerenciarSoftware dialog = new JDialogGerenciarSoftware();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		
	}

	protected void jButtonHardware() {
		JDialogGerenciarHardware dialog = new JDialogGerenciarHardware();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
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
	
	 private void treeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeMouseClicked
	        JTree tree = (JTree) evt.getSource();	        
	        TreePath treePath = tree.getSelectionPath();
	        if (treePath != null) {
	            if (evt.getClickCount() == 2) {
	            	jPanelLeste.removeAll();
	            	jPanelLeste.setLayout(new BorderLayout(1, 1));
	            	jPanelLeste.add(diagramTabbedPane);
	        		
	                ModellingController.setSelectedComponent(((TreeNode) treePath.getLastPathComponent()).getId());
	                tree.expandPath(treePath);
	            } else if (evt.getClickCount() == 1) {	   
	            	ModellingController.setElementToShowProperties(((TreeNode) treePath.getLastPathComponent()).getId());
	            }
	        }
	}


	protected void jButtonPasswordAdmin() {
		JDialogSenha dialog = new JDialogSenha(Spider_PE_Home.getInstance().getInstanciaLogado());
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}

	protected void jButtonLogout() {
		dispose();
		Spider_PE_Home.getInstance().setVisible(true);
	}
	
	JTabbedPane diagramTabbedPane;
	JPanel diagramPanel;
	private JPanel panelProcess;
	private JSplitPane splitPane_1;
	private JScrollPane scrollPane_1;
	private JTabbedPane tabbedPane;
	private JTree treeProcess;
	private JScrollPane scrollPane;
	private JMenu mnPegarImagem;
	private JMenuItem mntmCatch;
	
	protected void itemListenerStateChaged(ItemEvent e) throws FileNotFoundException, IOException {
		
		diagramTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		diagramPanel = new JPanel();
		diagramTabbedPane.addTab("",diagramPanel);
		diagramPanel.setLayout(new BorderLayout(0, 0));
		scrollPane.setViewportView(null);
		
		if(e.getStateChange() == ItemEvent.SELECTED) { 		
			jPanelLeste.removeAll();
			jPanelLeste.repaint();
		}
		
		 if(!jComboBoxProcesso.getSelectedItem().toString().equals("Selecione...")){
				Processo processo = (Processo) jComboBoxProcesso.getSelectedItem();
				
				byte[] b  = processo.getProcesso_xml();
			       
		        File fileDestiny =  File.createTempFile(processo.getNome(), ".xml");	
		       
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
				treeProcess.setModel(null);			
				treeProcess.setCellRenderer(new TreeCellRenderer());		
				diagramPanel.removeAll();
				diagramPanel.setLayout(new BorderLayout(0, 0));
				repaint();
			 }
	}
	
	public void openFile(File file) throws FileNotFoundException, IOException {
        ModellingController.setDiagramPanel(diagramPanel);
        ModellingController.setProcessPackageTree(treeProcess);
        ModellingController.setPropertiesTabbedPane(tabbedPane);
        ModellingController.setPropertiesScrollPane(scrollPane);
        ModellingController.setSelectedComponent(null);
        Modelling.newModelling();
        ModellingController.initialize();
        ModellingController.setAutoCreateRequiredElements(false);
        ModellingFileManager.load(file.getPath());
        ModellingController.setAutoCreateRequiredElements(true);
}
	protected void jMenuItemActionPerformed(ActionEvent arg0) {
		jFrameTipos = new JFrame();
		jFrameTipos.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		jFrameTipos.setBounds(100, 100, 450, 300);
		jFrameTipos.setSize(700, 450);
		jFrameTipos.getContentPane().add(new JPanelTelaTipos());
		jFrameTipos.setTitle("Tipos");
		jFrameTipos.setVisible(true); 
		jFrameTipos.setIconImage(Toolkit.getDefaultToolkit().getImage(Spider_PE_Management.class.getResource("/br/ufpa/spider/pe/view/util/img/favicon_pe.png")));	
		
	
		jFrameTipos.addWindowListener( new WindowListener(){  
	    public void windowClosing( WindowEvent e )  
	    {  
	    	
	    }  
	    public void windowClosed( WindowEvent e )  
	    {  
	    	
	    }  
	    public void windowOpened( WindowEvent e ) {}  
	    public void windowIconified( WindowEvent e ) {}  
	    public void windowDeiconified( WindowEvent e ) {}  
	    public void windowActivated( WindowEvent e ) {}  
	    public void windowDeactivated( WindowEvent e ) {}  
	} );
}
	
	

	private void populaComboBoxProcesso() {	
		jComboBoxProcesso.addItem("Selecione...");
		if(Spider_PE_Home.getInstance().getInstanciaLogado() instanceof Humano){
			for (Processo processo : ((Humano) Spider_PE_Home.getInstance().getInstanciaLogado()).getGerente().getProcesso()) {
				jComboBoxProcesso.addItem(processo);
			}		
		}
	}
	
	public Processo getProcesso(){
		if(jComboBoxProcesso.getSelectedItem().toString().equals("Selecione..."))
			return null;
		else
			return (Processo) jComboBoxProcesso.getSelectedItem();
	}

	public JPanel getjPanelLeste() {
		return jPanelLeste;
	}

}
