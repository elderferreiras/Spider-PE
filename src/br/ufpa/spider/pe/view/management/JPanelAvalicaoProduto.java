package br.ufpa.spider.pe.view.management;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
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

import br.ufpa.spider.pe.controller.CheckListArtefatosController;
import br.ufpa.spider.pe.controller.ProdutoTrabalhoController;
import br.ufpa.spider.pe.model.Atividade;
import br.ufpa.spider.pe.model.CheckListArtefatos;
import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Iteracao;
import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.PlanoMedicao;
import br.ufpa.spider.pe.model.PoliticaOrganizacional;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.ProdutoTrabalho;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.dao.CheckListArtefatosDAO;
import br.ufpa.spider.pe.model.dao.ProdutoTrabalhoDAO;
import br.ufpa.spider.pe.model.dao.TarefaDAO;
import br.ufpa.spider.pe.model.set.SVN;
import br.ufpa.spider.pe.model.set.SpiderCL;
import br.ufpa.spider.pe.model.set.dao.SVNDAO;
import br.ufpa.spider.pe.model.set.dao.SpiderCLDAO;
import br.ufpa.spider.pe.model.util.MyTableModel;
import br.ufpa.spider.pe.view.Spider_PE_Home;
import br.ufpa.spider.pe.view.util.Icone;
import br.ufpa.spider.pe.view.util.SVNManager;

public class JPanelAvalicaoProduto extends JPanel {
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
	private CheckListArtefatos checklist = null;
	private JToolBar toolBar;
	private JLabel jLabelSalvarEm;
	private JTextField jTextFieldCaminhoRepo;
	private JButton jButtonRepoBrowser;
	private JLabel lblAvaliaoDeProduto;

	/**
	 * Create the panel.
	 */
	public JPanelAvalicaoProduto() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{168, 184, 69, 114, 0};
		gridBagLayout.rowHeights = new int[]{29, 73, 0, 0, 41, 42, 35, 0, 138, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblAvaliaoDeProduto = new JLabel("Avalia\u00E7\u00E3o de Produto de Trabalho");
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
		
		JLabelSpiderCL = new JLabel("Criar Checklist para Avalia\u00E7\u00E3o do Produto de Trabalho");
		JLabelSpiderCL.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_JLabelSpiderCL = new GridBagConstraints();
		gbc_JLabelSpiderCL.insets = new Insets(0, 0, 5, 5);
		gbc_JLabelSpiderCL.anchor = GridBagConstraints.NORTH;
		gbc_JLabelSpiderCL.gridwidth = 2;
		gbc_JLabelSpiderCL.gridx = 1;
		gbc_JLabelSpiderCL.gridy = 2;
		add(JLabelSpiderCL, gbc_JLabelSpiderCL);
		//		ItemListener listener = new ItemListener() {
		//			public void itemStateChanged(ItemEvent e) {
		//				itemListenerStateChaged(e);
		//			}
		//		};
				
				jLabelArtefato = new JLabel("Artefato");
				GridBagConstraints gbc_jLabelArtefato = new GridBagConstraints();
				gbc_jLabelArtefato.insets = new Insets(0, 0, 5, 5);
				gbc_jLabelArtefato.anchor = GridBagConstraints.SOUTHWEST;
				gbc_jLabelArtefato.gridx = 0;
				gbc_jLabelArtefato.gridy = 3;
				add(jLabelArtefato, gbc_jLabelArtefato);
		
		jComboxArtefato = new JComboBox();
		//		jComboxArtefato.addItemListener(listener);
				
				GridBagConstraints gbc_jComboxArtefato = new GridBagConstraints();
				gbc_jComboxArtefato.anchor = GridBagConstraints.SOUTH;
				gbc_jComboxArtefato.insets = new Insets(0, 0, 5, 5);
				gbc_jComboxArtefato.fill = GridBagConstraints.HORIZONTAL;
				gbc_jComboxArtefato.gridx = 1;
				gbc_jComboxArtefato.gridy = 3;
				add(jComboxArtefato, gbc_jComboxArtefato);
		populajComboBox();
		
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
			public void actionPerformed(ActionEvent arg0) {
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
		
		jButtonCadastrar = new JButton("Cadastrar");
		jButtonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionLinesterjButtonCadastrar();
			}
		});
		
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
		jTextFieldCaminhoRepo.setEditable(false);
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
		
		jButtonRepoBrowser = new JButton("Repo-Browser");
		jButtonRepoBrowser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionListenerjButtonRepoBrowser();
			}
		});
		GridBagConstraints gbc_jButtonRepoBrowser = new GridBagConstraints();
		gbc_jButtonRepoBrowser.fill = GridBagConstraints.HORIZONTAL;
		gbc_jButtonRepoBrowser.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonRepoBrowser.gridx = 3;
		gbc_jButtonRepoBrowser.gridy = 6;
		add(jButtonRepoBrowser, gbc_jButtonRepoBrowser);
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
		 columns.add("Artefato");
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
	} else JOptionPane.showMessageDialog(null, "A ferramenta Spider-CL ainda n\u00E7\u00E3o est\u00e1 configurada.");
	}

protected void actionListenerjButtonRepoBrowser() {
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
     for (SVN svn : SVNDAO.findAll()) {
    	 repositorio.load(svn.getHost()+svn.getDiretorio(), 
	    			login, 
	    			senha);
     }
	  

	   repositorio.setLocationRelativeTo(null);  
	   repositorio.setVisible(true);
		   
		   try{ 
			jTextFieldCaminhoRepo.setText(repositorio.getCaminho()); 
		   }
		   catch(Exception e){
			   JOptionPane.showMessageDialog(null, "Nenhum caminho selecionado.");
		   }
	 }
		
	}

	private void populajComboBox() {
		jComboxArtefato.removeAllItems();
		jComboxArtefato.addItem("Selecione");
		for(ProdutoTrabalho produtoTrabalho: getListProdutoTrabalhos()){
			jComboxArtefato.addItem(produtoTrabalho);
		}
		
	}
	
	private List<ProdutoTrabalho> getListProdutoTrabalhos(){
		List<ProdutoTrabalho> produtos = new ArrayList<ProdutoTrabalho>();
		Processo processo = Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso();
		for (ProdutoTrabalho produtoTrabalho : ProdutoTrabalhoDAO.findAll()) {
		
			PoliticaOrganizacional politica = processo.getPoliticaOrganizacional();
			PlanoMedicao plano = processo.getPlanoMedicao();
			if(politica != null){
				if(produtoTrabalho.getPoliticaOrganizacional().equals(processo.getPoliticaOrganizacional())){
					produtos.add(produtoTrabalho);
				}
			} else if(plano != null){
				if(produtoTrabalho.getPlanoMedicao().equals(processo.getPlanoMedicao())) {
				produtos.add(produtoTrabalho);
			} else {
				for(Tarefa tarefasProduto: produtoTrabalho.getTarefa()){
					for(Tarefa tarefasProcesso: TarefaDAO.getTarefasByProcesso(processo)){
						if(tarefasProduto.equals(tarefasProcesso)){
							produtos.add(produtoTrabalho);
							break;
						}
					}
					if(produtos.contains(produtoTrabalho)){
						break;
					}
				}
			}
		 }
	   }
		return produtos;
	}

	protected void actionLinesterjButtonCadastrar() {
		checklist = new CheckListArtefatos();
		ProdutoTrabalho prod = ProdutoTrabalhoDAO.findByName(jComboxArtefato.getSelectedItem().toString());
		if(jComboxArtefato.getSelectedIndex()!=0){
			if(!jTextFieldEnviarChecklist.getText().equals("")){
				if(!jTextFieldCaminhoRepo.getText().equals("Escolha um caminho no reposit\u00F3rio.")){
					JDialogLoginRepo repo = new JDialogLoginRepo();
					repo.setLocationRelativeTo(null);
					repo.setVisible(true);
					String login = repo.getUsuario();
					String senha = repo.getSenha();
					if(!repo.isCancel()){
						 if(!SVNDAO.findAll().isEmpty()||!(SVNDAO.findAll()==null)){	
						checklist.setNome(jTextFieldNomeCheckList.getText());
						checklist.setCaminho(jTextFieldCaminhoRepo.getText());
						checklist.setProdutoTrabalho(prod);
						prod.setCheckListArtefatos(checklist);	    	
							SVNManager.saveToRepository(checklist.getNome(), login, senha, checklist.getCaminho(), jTextFieldEnviarChecklist.getText());
							CheckListArtefatosController.saveCheckListArtefatos(checklist, false);
							ProdutoTrabalhoController.saveProdutoTrabalho(prod, false);
							JOptionPane.showMessageDialog(null, "CheckList cadastrado com sucesso");
							populaTabela();
							jTextFieldEnviarChecklist.setText("");
							jTextFieldNomeCheckList.setText("");
							jTextFieldCaminhoRepo.setText("Escolha um caminho no reposit\u00F3rio.");
					 }else JOptionPane.showMessageDialog(null, "O Reposit\u00F3rio ainda n\u00E7\u00E3o est\u00e1 configurado.");		
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um caminho no reposit\u00F3rio.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um CheckList");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Selecione um Artefato");
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
			CheckListArtefatos check =  (CheckListArtefatos) jTable.getValueAt(jTable.getSelectedRow(), 1);
			if(opcao == 3)
			{	JDialogLoginRepo repo = new JDialogLoginRepo();
			repo.setLocationRelativeTo(null);
			repo.setVisible(true);
			String login = repo.getUsuario();
			String senha = repo.getSenha();
			if(!repo.isCancel()){	    	
					 String filePath = check.getNome();
				        String url = check.getCaminho();
				        String nameTemp = "CheckList Artefato";
				        try {
							SVNManager.openFile(url, filePath, login, senha, nameTemp);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			      }else JOptionPane.showMessageDialog(null, "O Reposit\u00F3rio ainda n\u00E7\u00E3o est\u00e1 configurado.");		
			    
			} else if(opcao == 4) {
				int i = JOptionPane.showConfirmDialog(null ,"Deseja excluir a avalia\u00e7\u00E7\u00E3o selecionada?", 
						"Avaliação do Produto de Trabalho",JOptionPane.YES_NO_OPTION); 
						if (i == JOptionPane.YES_OPTION ) { 
							ProdutoTrabalho prod = check.getProdutoTrabalho();
							prod.setCheckListArtefatos(null);
							ProdutoTrabalhoController.saveProdutoTrabalho(prod, false);
							CheckListArtefatosDAO.removeCheckListArtefatos(check);
							JOptionPane.showMessageDialog(null, "Exclu\u00eddo com sucesso.");
							populaTabela();
							this.repaint();
						}
			}
				
		}
		
	}
	

	private void populaTabela() {
		tableModel.setNumRows(0);
		List<CheckListArtefatos> checks = new ArrayList<CheckListArtefatos>();
		for(ProdutoTrabalho prod : getListProdutoTrabalhos()){
			if(prod.getCheckListArtefatos() != null)
				checks.add(prod.getCheckListArtefatos());
		}
		for(CheckListArtefatos checklists : checks ){
			String tipoArtefato = "";
			if(checklists.getProdutoTrabalho() != null){
				if(checklists.getProdutoTrabalho().getPoliticaOrganizacional() != null){
					tipoArtefato = "Politica Organizacional";
				}
				if(checklists.getProdutoTrabalho().getPlanoMedicao() != null){
					tipoArtefato = "Plano de Medição";
				}
			}
			tableModel.addRow(new Object[]{ tipoArtefato,checklists,checklists.getCaminho(), new Icone().getBaixar(), new Icone().getExcluir()});		
		}
	}
			    
}
