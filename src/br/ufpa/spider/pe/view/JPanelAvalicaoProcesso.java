package br.ufpa.spider.pe.view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import br.ufpa.spider.pe.controller.CheckListProcessoController;
import br.ufpa.spider.pe.controller.TipoController;
import br.ufpa.spider.pe.model.CheckListProcesso;
import br.ufpa.spider.pe.model.Tipo;
import br.ufpa.spider.pe.model.dao.CampoDAO;
import br.ufpa.spider.pe.model.dao.CheckListProcessoDAO;
import br.ufpa.spider.pe.model.dao.TipoDAO;
import br.ufpa.spider.pe.model.set.SVN;
import br.ufpa.spider.pe.model.set.SpiderCL;
import br.ufpa.spider.pe.model.set.dao.SVNDAO;
import br.ufpa.spider.pe.model.set.dao.SpiderCLDAO;
import br.ufpa.spider.pe.model.util.MyTableModel;
import br.ufpa.spider.pe.view.util.Icone;
import br.ufpa.spider.pe.view.util.SVNManager;

public class JPanelAvalicaoProcesso extends JPanel {
	private JTextField jTextFieldNomeCheckList;
	private JTextField jTextFieldEnviarChecklist;
	private JLabel jLabelArtefato;
	private JComboBox jComboxArtefato;
	private JLabel JLabelSpiderCL;
	private JButton JButtonSpiderCL;
	private JLabel jLabelNomeCheckList;
	
	private JLabel jLabelEnviarChecklist;
	private JButton jButtonProcurar;
	private JButton jButtonCadastrar;
	private JTable jTable;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private CheckListProcesso checklist;
	private JToolBar toolBar;
	private JLabel jLabelSalvarEm;
	private JTextField jTextFieldCaminhoRepo;
	private JButton jButtonRepobrowser;
	private JLabel lblAvaliaoDeProduto;

	/**
	 * Create the panel.
	 */
	public JPanelAvalicaoProcesso() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 164, 69, 114, 0};
		gridBagLayout.rowHeights = new int[]{29, 73, 0, 0, 41, 42, 45, 42, 138, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		jComboxArtefato = new JComboBox();
		
		lblAvaliaoDeProduto = new JLabel("Avalia\u00e7\u00e3o de \u00c1rea de Processo");
		lblAvaliaoDeProduto.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblAvaliaoDeProduto = new GridBagConstraints();
		gbc_lblAvaliaoDeProduto.gridwidth = 2;
		gbc_lblAvaliaoDeProduto.insets = new Insets(0, 0, 5, 5);
		gbc_lblAvaliaoDeProduto.gridx = 1;
		gbc_lblAvaliaoDeProduto.gridy = 0;
		add(lblAvaliaoDeProduto, gbc_lblAvaliaoDeProduto);
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.gridwidth = 2;
		gbc_toolBar.insets = new Insets(0, 0, 5, 5);
		gbc_toolBar.gridx = 1;
		gbc_toolBar.gridy = 1;
		add(toolBar, gbc_toolBar);
		
		JButtonSpiderCL = new JButton("");
		JButtonSpiderCL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCL();
			}
		});
		toolBar.add(JButtonSpiderCL);
		JButtonSpiderCL.setBackground(Color.WHITE);
		JButtonSpiderCL.setIcon(new Icone().getSpider_cl());
		JButtonSpiderCL.setForeground(UIManager.getColor("Button.highlight"));
		
		JLabelSpiderCL = new JLabel("Criar Checklist para Avalia\u00e7\u00e3o da \u00c1rea de Processo");
		JLabelSpiderCL.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_JLabelSpiderCL = new GridBagConstraints();
		gbc_JLabelSpiderCL.gridwidth = 2;
		gbc_JLabelSpiderCL.insets = new Insets(0, 0, 5, 5);
		gbc_JLabelSpiderCL.anchor = GridBagConstraints.NORTH;
		gbc_JLabelSpiderCL.gridx = 1;
		gbc_JLabelSpiderCL.gridy = 2;
		add(JLabelSpiderCL, gbc_JLabelSpiderCL);
		jComboxArtefato = new JComboBox();
		//		ItemListener listener = new ItemListener() {
		//			public void itemStateChanged(ItemEvent e) {
		//				itemListenerStateChaged(e);
		//			}
		//		};
		//		jComboxArtefato.addItemListener(listener);
				
				jComboxArtefato.addFocusListener(new FocusListener(){  
		
					@Override 
				    public void focusGained(FocusEvent arg0) {  
						popularComboBox();
				    }  
		
					@Override
				    public void focusLost(FocusEvent arg0){               
				        //a��o desejada quando perde o foco  
				    }
				      
				});  
				
				jLabelArtefato = new JLabel("\u00C1rea de Processo");
				GridBagConstraints gbc_jLabelArtefato = new GridBagConstraints();
				gbc_jLabelArtefato.insets = new Insets(0, 0, 5, 5);
				gbc_jLabelArtefato.anchor = GridBagConstraints.SOUTHWEST;
				gbc_jLabelArtefato.gridx = 0;
				gbc_jLabelArtefato.gridy = 3;
				add(jLabelArtefato, gbc_jLabelArtefato);
				GridBagConstraints gbc_jComboxArtefato = new GridBagConstraints();
				gbc_jComboxArtefato.anchor = GridBagConstraints.NORTH;
				gbc_jComboxArtefato.insets = new Insets(0, 0, 5, 5);
				gbc_jComboxArtefato.fill = GridBagConstraints.HORIZONTAL;
				gbc_jComboxArtefato.gridx = 1;
				gbc_jComboxArtefato.gridy = 3;
				add(jComboxArtefato, gbc_jComboxArtefato);
		
		jLabelEnviarChecklist = new JLabel("Enviar Checklist");
		GridBagConstraints gbc_jLabelEnviarChecklist = new GridBagConstraints();
		gbc_jLabelEnviarChecklist.anchor = GridBagConstraints.SOUTHWEST;
		gbc_jLabelEnviarChecklist.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelEnviarChecklist.gridx = 0;
		gbc_jLabelEnviarChecklist.gridy = 4;
		add(jLabelEnviarChecklist, gbc_jLabelEnviarChecklist);
		
		jTextFieldEnviarChecklist = new JTextField();
		jTextFieldEnviarChecklist.setMaximumSize(new Dimension(2147483647, 25));
		jTextFieldEnviarChecklist.setMinimumSize(new Dimension(4, 25));
		jTextFieldEnviarChecklist.setPreferredSize(new Dimension(95, 25));
		GridBagConstraints gbc_jTextFieldEnviarChecklist = new GridBagConstraints();
		gbc_jTextFieldEnviarChecklist.gridwidth = 2;
		gbc_jTextFieldEnviarChecklist.anchor = GridBagConstraints.SOUTH;
		gbc_jTextFieldEnviarChecklist.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldEnviarChecklist.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldEnviarChecklist.gridx = 1;
		gbc_jTextFieldEnviarChecklist.gridy = 4;
		add(jTextFieldEnviarChecklist, gbc_jTextFieldEnviarChecklist);
		jTextFieldEnviarChecklist.setColumns(10);
		
		jButtonProcurar = new JButton("Procurar");
		jButtonProcurar.setMinimumSize(new Dimension(81, 23));
		jButtonProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListenerjButtonProcurar();
			}
		});
		GridBagConstraints gbc_jButtonProcurar = new GridBagConstraints();
		gbc_jButtonProcurar.fill = GridBagConstraints.HORIZONTAL;
		gbc_jButtonProcurar.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonProcurar.anchor = GridBagConstraints.SOUTH;
		gbc_jButtonProcurar.gridx = 3;
		gbc_jButtonProcurar.gridy = 4;
		add(jButtonProcurar, gbc_jButtonProcurar);
		
		jLabelNomeCheckList = new JLabel("Nome do Checklist criado");
		GridBagConstraints gbc_jLabelNomeCheckList = new GridBagConstraints();
		gbc_jLabelNomeCheckList.anchor = GridBagConstraints.SOUTHWEST;
		gbc_jLabelNomeCheckList.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelNomeCheckList.gridx = 0;
		gbc_jLabelNomeCheckList.gridy = 5;
		add(jLabelNomeCheckList, gbc_jLabelNomeCheckList);
		
		jTextFieldNomeCheckList = new JTextField();
		jTextFieldNomeCheckList.setEditable(false);
		jTextFieldNomeCheckList.setPreferredSize(new Dimension(4, 25));
		jTextFieldNomeCheckList.setMinimumSize(new Dimension(4, 25));
		jTextFieldNomeCheckList.setMaximumSize(new Dimension(2147483647, 25));
		GridBagConstraints gbc_jTextFieldNomeCheckList = new GridBagConstraints();
		gbc_jTextFieldNomeCheckList.gridwidth = 2;
		gbc_jTextFieldNomeCheckList.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldNomeCheckList.anchor = GridBagConstraints.SOUTH;
		gbc_jTextFieldNomeCheckList.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldNomeCheckList.gridx = 1;
		gbc_jTextFieldNomeCheckList.gridy = 5;
		add(jTextFieldNomeCheckList, gbc_jTextFieldNomeCheckList);
		jTextFieldNomeCheckList.setColumns(10);
		
		jLabelSalvarEm = new JLabel("Salvar Em");
		GridBagConstraints gbc_jLabelSalvarEm = new GridBagConstraints();
		gbc_jLabelSalvarEm.anchor = GridBagConstraints.SOUTHWEST;
		gbc_jLabelSalvarEm.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelSalvarEm.gridx = 0;
		gbc_jLabelSalvarEm.gridy = 6;
		add(jLabelSalvarEm, gbc_jLabelSalvarEm);
		
		jTextFieldCaminhoRepo = new JTextField();
		jTextFieldCaminhoRepo.setFont(new Font("Tahoma", Font.ITALIC, 11));
		jTextFieldCaminhoRepo.setEditable(true);
		//jTextFieldCaminhoRepo.setText("Escolha um caminho no reposit\u00F3rio.");
		jTextFieldCaminhoRepo.setPreferredSize(new Dimension(4, 25));
		jTextFieldCaminhoRepo.setMinimumSize(new Dimension(4, 25));
		jTextFieldCaminhoRepo.setMaximumSize(new Dimension(2147483647, 25));
		GridBagConstraints gbc_jTextFieldCaminhoRepo = new GridBagConstraints();
		gbc_jTextFieldCaminhoRepo.anchor = GridBagConstraints.SOUTH;
		gbc_jTextFieldCaminhoRepo.gridwidth = 2;
		gbc_jTextFieldCaminhoRepo.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldCaminhoRepo.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldCaminhoRepo.gridx = 1;
		gbc_jTextFieldCaminhoRepo.gridy = 6;
		add(jTextFieldCaminhoRepo, gbc_jTextFieldCaminhoRepo);
		jTextFieldCaminhoRepo.setColumns(10);
		
		jButtonCadastrar = new JButton("Cadastrar");
		jButtonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListenerjButtonCadastrar();
			}
		});
		
		jButtonRepobrowser = new JButton("Repo-Browser");
		jButtonRepobrowser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionListenerJButtonRepoBrowser();
			}
		});
		GridBagConstraints gbc_jButtonRepobrowser = new GridBagConstraints();
		gbc_jButtonRepobrowser.fill = GridBagConstraints.HORIZONTAL;
		gbc_jButtonRepobrowser.anchor = GridBagConstraints.SOUTH;
		gbc_jButtonRepobrowser.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonRepobrowser.gridx = 3;
		gbc_jButtonRepobrowser.gridy = 6;
		add(jButtonRepobrowser, gbc_jButtonRepobrowser);
		GridBagConstraints gbc_jButtonCadastrar = new GridBagConstraints();
		gbc_jButtonCadastrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_jButtonCadastrar.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonCadastrar.anchor = GridBagConstraints.SOUTH;
		gbc_jButtonCadastrar.gridx = 3;
		gbc_jButtonCadastrar.gridy = 7;
		add(jButtonCadastrar, gbc_jButtonCadastrar);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 8;
		add(scrollPane, gbc_scrollPane);
		
		jTable = new JTable();
		jTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jTableMouseClicked(arg0);
			}
		});
		jTable.setBorder(new LineBorder(Color.LIGHT_GRAY));
		jTable.setPreferredScrollableViewportSize(new Dimension(700, 16));	
		 Vector<String>  columns = new Vector<String>();
		 Vector<String>  data = new Vector<String>();
		 columns.add("\u00C1rea de Processo");
		 columns.add("Nome do Checklist");
		 columns.add("Checklist");
	     columns.add("Baixar");
	     columns.add("Excluir");
			
		 tableModel = MyTableModel.modelProdutoTrabalho(columns, data);
		 jTable.setModel(tableModel);
		 jTable.getTableHeader().setReorderingAllowed(false); 
		 jTable.getColumnModel().getColumn(0).setPreferredWidth(250);
		 jTable.getColumnModel().getColumn(1).setPreferredWidth(300);
		 jTable.getColumnModel().getColumn(2).setPreferredWidth(250);
		 jTable.getColumnModel().getColumn(3).setPreferredWidth(40);
		 jTable.getColumnModel().getColumn(4).setPreferredWidth(40);
		scrollPane.setViewportView(jTable);
		populaTabela();
		popularComboBox();

	}

	protected void jButtonCL() {
		if(!SpiderCLDAO.findAll().isEmpty() || !(SpiderCLDAO.findAll() == null)){
			for (SpiderCL cl : SpiderCLDAO.findAll()) {
				Desktop desktop = null;  
				desktop = Desktop.getDesktop();  
				URI uri = null;  
				try {  
				     uri = new URI(cl.getUrl_ferramenta());  
				     desktop.browse(uri);  
				}  
				catch(IOException ioe) {  
				     ioe.printStackTrace();  
				}  
				catch(URISyntaxException use) {  
				     use.printStackTrace();  
				}  
			}
		} else JOptionPane.showMessageDialog(null, "A ferramenta Spider-CL ainda n\u00e3o est\u00e1 configurado.");
	}

	protected void actionListenerJButtonRepoBrowser() {
		JDialogLoginRepo repo = new JDialogLoginRepo();
		repo.setLocationRelativeTo(null);
		repo.setVisible(true);
		String login = repo.getUsuario();
		String senha = repo.getSenha();
		if(!repo.isCancel()){
	       RepoBrowserDialog repositorio = new RepoBrowserDialog();
	       
	       /*
	        * Configurar o caminho do repositório na aba Ferramentas.
	        */
	       try{
	       for (SVN svn : SVNDAO.findAll()) {
	    	   repositorio.load(svn.getHost()+svn.getDiretorio(), 
		    			login, 
		    			senha);
	       }
	       }catch(Exception e){
	    	   e.printStackTrace();
	       }
		  

		   repositorio.setLocationRelativeTo(null);  
		   repositorio.setVisible(true);
			   
			   try{ 
				jTextFieldCaminhoRepo.setText(repositorio.getCaminho()); 
			   }
			   catch(Exception e){
				   e.printStackTrace();
				   JOptionPane.showMessageDialog(null, "Nenhum caminho selecionado.");
			   }	
		}
	}

	private void popularComboBox() {
		jComboxArtefato.removeAllItems();
		jComboxArtefato.addItem("Selecione...");
			for(Tipo tipo: CampoDAO.findByName("\u00C1rea de Processo").getTipo()){
				jComboxArtefato.addItem(tipo.getNome());
			}
	}

	protected void actionListenerjButtonCadastrar() {
		checklist = new CheckListProcesso();
		Tipo tipo = TipoDAO.findByName(jComboxArtefato.getSelectedItem().toString());
		if(jComboxArtefato.getSelectedIndex()!=0){
			if(!jTextFieldEnviarChecklist.getText().equals("")){
				JDialogLoginRepo repo = new JDialogLoginRepo();
				repo.setLocationRelativeTo(null);
				repo.setVisible(true);
				String login = repo.getUsuario();
				String senha = repo.getSenha();
				if(!repo.isCancel()){
					checklist.setNome(jTextFieldNomeCheckList.getText());
					checklist.setCaminho(jTextFieldCaminhoRepo.getText());
					checklist.setTipo(tipo);
					tipo.setCheklistProcesso(checklist);    	  	
						SVNManager.saveToRepository(checklist.getNome(), login, senha, checklist.getCaminho(), jTextFieldEnviarChecklist.getText());
						CheckListProcessoController.saveCheckListProcesso(checklist, false);
						TipoController.savetipo(tipo, false);
						JOptionPane.showMessageDialog(null, "CheckList cadastrado com sucesso");
						populaTabela();
						jTextFieldEnviarChecklist.setText("");
						jTextFieldNomeCheckList.setText("");
						//jTextFieldCaminhoRepo.setText("Escolha um caminho no reposit\u00F3rio.");
					  }else JOptionPane.showMessageDialog(null, "O Reposit\u00f3rio ainda ainda n\u00e3o est\u00e1 configurado.");
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um CheckList");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Selecione uma \u00C1rea de Processo");
		}
	}

	protected void actionListenerjButtonProcurar() {
		JFileChooser fileChooser = new JFileChooser();		
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setFileFilter(new FileFilter() {
	        public boolean accept(File f) {
	          return f.getName().toLowerCase().endsWith(".pdf")
	              || f.isDirectory();
	        }

	        public String getDescription() {
	          return "PDF Files";
	        }
	      });
		int r = fileChooser.showOpenDialog(this);
		if (r == JFileChooser.APPROVE_OPTION) {
			jTextFieldEnviarChecklist.setText(fileChooser.getSelectedFile().toString());
	      }
		setaNome(fileChooser.getSelectedFile().toString());
		
	}
	
	private void setaNome(String caminho) {
		if(System.getProperties().get("os.name").toString().toUpperCase().contains("WINDOWS")){
			for(int i=caminho.length()-1; i>0; i--){
				if(caminho.charAt(i) == '\\'){
					jTextFieldNomeCheckList.setText((String) caminho.subSequence(i+1, caminho.length()));
					break;
				}
			}
		}
		else {
			for(int i=caminho.length()-1; i>0; i--){
				if(caminho.charAt(i) == '/'){
					jTextFieldNomeCheckList.setText((String) caminho.subSequence(i+1, caminho.length()));
					break;
				}
			}
		}
		
	}

	protected void jTableMouseClicked(MouseEvent arg0) {
		if(arg0.getClickCount() == 2){
			int opcao = jTable.getSelectedColumn();
			CheckListProcesso check =  (CheckListProcesso) jTable.getValueAt(jTable.getSelectedRow(), 1);
			if(opcao == 3){
				JDialogLoginRepo repo = new JDialogLoginRepo();
				repo.setLocationRelativeTo(null);
				repo.setVisible(true);
				String login = repo.getUsuario();
				String senha = repo.getSenha();
				if(!repo.isCancel()){
				 if(!SVNDAO.findAll().isEmpty()||!(SVNDAO.findAll()==null)){
					 for (SVN svn : SVNDAO.findAll()) {	    	   
					    		login = svn.getLogin(); 
					    		senha = svn.getSenha();
				       }		    	  	
					String filePath = check.getNome();
			        String url = check.getCaminho();
			        String nameTemp = "CheckList Processo";
			        try {
						SVNManager.openFile(url, filePath, login, senha, nameTemp);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }else JOptionPane.showMessageDialog(null, "O Reposit\u00f3rio ainda ainda n\u00e3o est\u00e1 configurado.");
				}
			}else if(opcao == 4)
			{
				int i = JOptionPane.showConfirmDialog(null ,"Deseja excluir a avalia\u00e7\u00e3o selecionada?", 
						"Avaliação do Processo",JOptionPane.YES_NO_OPTION); 
						if (i == JOptionPane.YES_OPTION ) { 
					Tipo tipo = check.getTipo();
					tipo.setCheklistProcesso(null);
					TipoController.savetipo(tipo, false);
					CheckListProcessoDAO.removeCheckListProcesso(check);
					JOptionPane.showMessageDialog(null, "Exclu\u00eddo com sucesso.");
					populaTabela();
					this.repaint();
				}
			}
		}
		
	}
	
//	protected void itemListenerStateChaged(ItemEvent e) {
//		if(e.getStateChange() == ItemEvent.SELECTED) {
//		
//		}
//	}


	private void populaTabela() {
		tableModel.setNumRows(0);
			for(CheckListProcesso checklist : CheckListProcessoDAO.findAll()){
				tableModel.addRow(new Object[]{ checklist.getTipo(),checklist,checklist.getCaminho(), new Icone().getBaixar(), new Icone().getExcluir()});		
			}
	}
}
