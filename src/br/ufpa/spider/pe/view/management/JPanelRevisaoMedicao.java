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
import java.text.ParseException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.ufpa.spider.pe.controller.MedicaoController;
import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.Medicao;
import br.ufpa.spider.pe.model.PlanoMedicao;
import br.ufpa.spider.pe.model.dao.MarcoDAO;
import br.ufpa.spider.pe.model.dao.MedicaoDAO;
import br.ufpa.spider.pe.model.set.SVN;
import br.ufpa.spider.pe.model.set.SpiderMPlan;
import br.ufpa.spider.pe.model.set.dao.SVNDAO;
import br.ufpa.spider.pe.model.set.dao.SpiderMPlanDAO;
import br.ufpa.spider.pe.model.util.MyTableModel;
import br.ufpa.spider.pe.view.Spider_PE_Home;
import br.ufpa.spider.pe.view.util.Icone;
import br.ufpa.spider.pe.view.util.SVNManager;

public class JPanelRevisaoMedicao extends JPanel {
	private JTextField jTextFieldRevisao;
	private JTable jTable;
	private DefaultTableModel tableModel;
	private JLabel jLabelRealizarRevisao;
	private JButton jButtonSpiderCL;
	private JLabel JLabelRevisao;
	private JButton jButtonProcurar;
	private JLabel jLabelQuando;
	private JComboBox jComboBoxQuando;
	private JButton jButtonCadastrar;
	private JScrollPane scrollPane;
	private JComboBox jComboBoxPlanoMedicao;
	private JLabel lblPlanoDeMedio;
	private JToolBar toolBar;
	private MaskFormatter formatDate;
	private JLabel lblNome;
	private JTextField jTextFieldNome;
	private JLabel lblSalvarEm;
	private JTextField jTextFieldCaminhoRepo;
	private JButton btnRepobrowser;
	
	public JPanelRevisaoMedicao() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 187, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{30, 54, 0, 0, 32, 0, 27, 31, 102, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		jLabelRealizarRevisao = new JLabel("Realizar Medi\u00E7\u00E3o");
		jLabelRealizarRevisao.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_jLabelRealizarRevisao = new GridBagConstraints();
		gbc_jLabelRealizarRevisao.gridwidth = 2;
		gbc_jLabelRealizarRevisao.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelRealizarRevisao.gridx = 1;
		gbc_jLabelRealizarRevisao.gridy = 0;
		add(jLabelRealizarRevisao, gbc_jLabelRealizarRevisao);
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.gridwidth = 2;
		gbc_toolBar.insets = new Insets(0, 0, 5, 5);
		gbc_toolBar.gridx = 1;
		gbc_toolBar.gridy = 1;
		add(toolBar, gbc_toolBar);
		
		jButtonSpiderCL = new JButton("");
		jButtonSpiderCL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonMPlan();
			}
		});
		toolBar.add(jButtonSpiderCL);
		jButtonSpiderCL.setBackground(Color.WHITE);
		jButtonSpiderCL.setIcon(new Icone().getSpider_mplan());
		
		lblPlanoDeMedio = new JLabel("Plano de Medi\u00E7\u00E3o");
		GridBagConstraints gbc_lblPlanoDeMedio = new GridBagConstraints();
		gbc_lblPlanoDeMedio.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlanoDeMedio.anchor = GridBagConstraints.WEST;
		gbc_lblPlanoDeMedio.gridx = 0;
		gbc_lblPlanoDeMedio.gridy = 2;
		add(lblPlanoDeMedio, gbc_lblPlanoDeMedio);
		
		jComboBoxPlanoMedicao = new JComboBox();
		GridBagConstraints gbc_jComboBoxPlanoMedicao = new GridBagConstraints();
		gbc_jComboBoxPlanoMedicao.insets = new Insets(0, 0, 5, 5);
		gbc_jComboBoxPlanoMedicao.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxPlanoMedicao.gridx = 1;
		gbc_jComboBoxPlanoMedicao.gridy = 2;
		add(jComboBoxPlanoMedicao, gbc_jComboBoxPlanoMedicao);
		
		JLabelRevisao = new JLabel("Revis\u00E3o : ");
		GridBagConstraints gbc_JLabelRevisao = new GridBagConstraints();
		gbc_JLabelRevisao.anchor = GridBagConstraints.WEST;
		gbc_JLabelRevisao.insets = new Insets(0, 0, 5, 5);
		gbc_JLabelRevisao.gridx = 0;
		gbc_JLabelRevisao.gridy = 3;
		add(JLabelRevisao, gbc_JLabelRevisao);
		
				
				jTextFieldRevisao = new JTextField();
				jTextFieldRevisao.setMinimumSize(new Dimension(4, 25));
				jTextFieldRevisao.setMaximumSize(new Dimension(2147483647, 25));
				jTextFieldRevisao.setPreferredSize(new Dimension(4, 25));
				GridBagConstraints gbc_jTextFieldRevisao = new GridBagConstraints();
				gbc_jTextFieldRevisao.gridwidth = 2;
				gbc_jTextFieldRevisao.anchor = GridBagConstraints.SOUTH;
				gbc_jTextFieldRevisao.insets = new Insets(0, 0, 5, 5);
				gbc_jTextFieldRevisao.fill = GridBagConstraints.HORIZONTAL;
				gbc_jTextFieldRevisao.gridx = 1;
				gbc_jTextFieldRevisao.gridy = 3;
				add(jTextFieldRevisao, gbc_jTextFieldRevisao);
				jTextFieldRevisao.setColumns(10);
		
		jButtonProcurar = new JButton("Procurar...");
		jButtonProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonProcurar(arg0);
			}
		});
		GridBagConstraints gbc_jButtonProcurar = new GridBagConstraints();
		gbc_jButtonProcurar.fill = GridBagConstraints.BOTH;
		gbc_jButtonProcurar.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonProcurar.gridx = 3;
		gbc_jButtonProcurar.gridy = 3;
		add(jButtonProcurar, gbc_jButtonProcurar);
		
		lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.WEST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 4;
		add(lblNome, gbc_lblNome);
		
		jTextFieldNome = new JTextField();
		jTextFieldNome.setEditable(false);
		GridBagConstraints gbc_jTextFieldNome = new GridBagConstraints();
		gbc_jTextFieldNome.gridwidth = 2;
		gbc_jTextFieldNome.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldNome.fill = GridBagConstraints.BOTH;
		gbc_jTextFieldNome.gridx = 1;
		gbc_jTextFieldNome.gridy = 4;
		add(jTextFieldNome, gbc_jTextFieldNome);
		jTextFieldNome.setColumns(10);
		
		lblSalvarEm = new JLabel("Salvar em:");
		GridBagConstraints gbc_lblSalvarEm = new GridBagConstraints();
		gbc_lblSalvarEm.anchor = GridBagConstraints.WEST;
		gbc_lblSalvarEm.insets = new Insets(0, 0, 5, 5);
		gbc_lblSalvarEm.gridx = 0;
		gbc_lblSalvarEm.gridy = 5;
		add(lblSalvarEm, gbc_lblSalvarEm);
		
		jTextFieldCaminhoRepo = new JTextField();
		jTextFieldCaminhoRepo.setFont(new Font("Tahoma", Font.ITALIC, 11));
		jTextFieldCaminhoRepo.setEditable(false);
		GridBagConstraints gbc_jTextFieldCaminhoRepo = new GridBagConstraints();
		gbc_jTextFieldCaminhoRepo.gridwidth = 2;
		gbc_jTextFieldCaminhoRepo.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldCaminhoRepo.fill = GridBagConstraints.BOTH;
		gbc_jTextFieldCaminhoRepo.gridx = 1;
		gbc_jTextFieldCaminhoRepo.gridy = 5;
		add(jTextFieldCaminhoRepo, gbc_jTextFieldCaminhoRepo);
		jTextFieldCaminhoRepo.setColumns(10);
		
		btnRepobrowser = new JButton("Repo-Browser");
		btnRepobrowser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jTextFieldCaminhoRepo.setFont(new Font("Tahoma", Font.PLAIN, 11));
				jButtonRepoBrowser(arg0);
			}
		});
		GridBagConstraints gbc_btnRepobrowser = new GridBagConstraints();
		gbc_btnRepobrowser.fill = GridBagConstraints.BOTH;
		gbc_btnRepobrowser.insets = new Insets(0, 0, 5, 0);
		gbc_btnRepobrowser.gridx = 3;
		gbc_btnRepobrowser.gridy = 5;
		add(btnRepobrowser, gbc_btnRepobrowser);
		
		jLabelQuando = new JLabel("Quando");
		GridBagConstraints gbc_jLabelQuando = new GridBagConstraints();
		gbc_jLabelQuando.anchor = GridBagConstraints.WEST;
		gbc_jLabelQuando.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelQuando.gridx = 0;
		gbc_jLabelQuando.gridy = 6;
		add(jLabelQuando, gbc_jLabelQuando);
		
		jComboBoxQuando = new JComboBox();
		jComboBoxQuando.setPreferredSize(new Dimension(23, 18));
		jComboBoxQuando.setMaximumSize(new Dimension(4, 25));
		GridBagConstraints gbc_jComboBoxQuando = new GridBagConstraints();
		gbc_jComboBoxQuando.insets = new Insets(0, 0, 5, 5);
		gbc_jComboBoxQuando.fill = GridBagConstraints.BOTH;
		gbc_jComboBoxQuando.gridx = 1;
		gbc_jComboBoxQuando.gridy = 6;
		add(jComboBoxQuando, gbc_jComboBoxQuando);

		try {
			formatDate = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		jButtonCadastrar = new JButton("Cadastrar");
		jButtonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonCadastrar(arg0);
			}
		});
		GridBagConstraints gbc_jButtonCadastrar = new GridBagConstraints();
		gbc_jButtonCadastrar.fill = GridBagConstraints.BOTH;
		gbc_jButtonCadastrar.insets = new Insets(0, 0, 5, 0);
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
				try {
					jTableMouseClicked(arg0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	
		jTable.setBorder(new LineBorder(Color.LIGHT_GRAY));
		jTable.setPreferredScrollableViewportSize(new Dimension(700, 16));	
		 Vector<String>  columns = new Vector<String>();
		 Vector<String>  data = new Vector<String>();
		 columns.add("Quando");
		 columns.add("Data");
		 columns.add("Arquivo");
	     columns.add("Baixar");
	     columns.add("Excluir");
			
	     tableModel = MyTableModel.modelProdutoTrabalho(columns, data);
		 jTable.setModel(tableModel);		 
		 jTable.getColumnModel().getColumn(0).setPreferredWidth(250);
		 jTable.getColumnModel().getColumn(1).setPreferredWidth(300);
		 jTable.getColumnModel().getColumn(2).setPreferredWidth(250);
		 jTable.getColumnModel().getColumn(3).setPreferredWidth(10);
		 jTable.getColumnModel().getColumn(4).setPreferredWidth(10);
		 jTable.getTableHeader().setReorderingAllowed(false); 
		scrollPane.setViewportView(jTable);
		
		populaTabela();		
		populaComboBox();
	}
	protected void jButtonMPlan() {
		if(!SpiderMPlanDAO.findAll().isEmpty() || !(SpiderMPlanDAO.findAll() == null)){
			for (SpiderMPlan mplan : SpiderMPlanDAO.findAll()) {
				Desktop desktop = null;  
				desktop = Desktop.getDesktop();  
				URI uri = null;  
				try {  
				     uri = new URI(mplan.getUrlFerramenta());  
				     desktop.browse(uri);  
				}  
				catch(IOException ioe) {  
				     ioe.printStackTrace();  
				}  
				catch(URISyntaxException use) {  
				     use.printStackTrace();  
				}  
			}
		} else JOptionPane.showMessageDialog(null, "O Redmine ainda n\u00e3o est\u00e1 configurado.");
	}
	protected void jButtonRepoBrowser(ActionEvent arg0) {
		JDialogLoginRepo repo = new JDialogLoginRepo();
		repo.setLocationRelativeTo(null);
		repo.setVisible(true);
		String login = repo.getUsuario();
		String senha = repo.getSenha();
		if(!repo.isCancel()){
		RepoBrowserDialog repositorio = new RepoBrowserDialog();
	       
	       /*
	        * Configurar o caminho do reposit\u00f3rio na aba Ferramentas.
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
	protected void jButtonCadastrar(ActionEvent arg0) {
		if(!jComboBoxPlanoMedicao.getSelectedItem().toString().equals("Selecione...")){
			if(!jComboBoxQuando.getSelectedItem().toString().equals("Selecione...")){
			   if(!jTextFieldCaminhoRepo.getText().contains("Escolha um caminho no reposit\u00f3rio para salvar a revis\u00e3o.")){	
				   
					Medicao medicao = new Medicao();					
					Marco marco = (Marco) jComboBoxQuando.getSelectedItem();					
					medicao.setMarco(marco);
					medicao.setCaminho(jTextFieldCaminhoRepo.getText());
					medicao.setNome("Revisao_" + jTextFieldNome.getText());
					medicao.setPlanoMedicao((PlanoMedicao) jComboBoxPlanoMedicao.getSelectedItem());
					MedicaoController.savemedicao(medicao);
					
					if(!marco.getMedicoes().contains(medicao))
						marco.getMedicoes().add(medicao);
					MarcoDAO.updateMarco(marco);
					
					/*
					ProdutoTrabalho produtoTrabalho = new ProdutoTrabalho();
					produtoTrabalho.setNome(medicao.getNome()+".pdf");
					produtoTrabalho.setCaminho(jTextFieldCaminhoRepo.getText());
								
					ProdutoTrabalhoController.saveProdutoTrabalho(produtoTrabalho);
					*/		
					
					
					saveToRepository(medicao);
					
					populaTabela();				
					resetaTela();
			   }else JOptionPane.showMessageDialog(null, "\u00c9 necess\u00e1rio escolher um caminho no reposit\u00f3rio.");
			}else JOptionPane.showMessageDialog(null, "\u00c9 necess\u00e1rio selecionar um Marco.");
		} else JOptionPane.showMessageDialog(null, "\u00c9 necess\u00e1rio selecionar um Plano de Medi�\u00e3o.");
		
		
	}
	
	private void saveToRepository(Medicao medicao) {
		JDialogLoginRepo repo = new JDialogLoginRepo();
		repo.setLocationRelativeTo(null);
		repo.setVisible(true);
		String login = repo.getUsuario();
		String senha = repo.getSenha();
		if(!repo.isCancel()){
	    	   String nomeArquivo =  medicao.getNome();
		       String url = medicao.getCaminho();       
		       String path = jTextFieldRevisao.getText();
		       SVNManager.saveToRepository(nomeArquivo, login, senha, url, path);
		  }else JOptionPane.showMessageDialog(null, "O Reposit\u00f3rio ainda n\u00e3o est\u00e1 configurado.");			
		
	}
	private void resetaTela() {
		jTextFieldRevisao.setText("");
		jTextFieldNome.setText("");
		jComboBoxPlanoMedicao.setSelectedItem("Selecione...");
		jComboBoxQuando.setSelectedItem("Selecione...");
		jTextFieldCaminhoRepo.setText("Escolha um caminho no reposit\u00f3rio para salvar a revis\u00e3o.");
	}
	private void populaComboBox() {
		jComboBoxQuando.removeAllItems();
		jComboBoxQuando.addItem("Selecione...");
		for (Fase fase : Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso().getFase()) {
			for (Marco marco : fase.getMarco()) {
				jComboBoxQuando.addItem(marco);
			}
		}
		
		jComboBoxPlanoMedicao.removeAllItems();
		jComboBoxPlanoMedicao.addItem("Selecione...");
		PlanoMedicao plano = Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso().getPlanoMedicao();
		if(!(plano == null))
			jComboBoxPlanoMedicao.addItem(plano);		
	}
	protected void jButtonProcurar(ActionEvent arg0) {
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
			jTextFieldRevisao.setText(fileChooser.getSelectedFile().toString());
	      }
		setaNome(fileChooser.getSelectedFile().toString());
	}
	
	private void setaNome(String caminho) {
		if(System.getProperties().get("os.name").toString().toUpperCase().contains("WINDOWS")){
			for(int i=caminho.length()-1; i>0; i--){
				if(caminho.charAt(i) == '\\'){
					jTextFieldNome.setText((String) caminho.subSequence(i+1, caminho.length()));
					break;
				}
			}
		}
		else {
			for(int i=caminho.length()-1; i>0; i--){
				if(caminho.charAt(i) == '/'){
					jTextFieldNome.setText((String) caminho.subSequence(i+1, caminho.length()));
					break;
				}
			}
		}
		
	}
	protected void jTableMouseClicked(MouseEvent arg0) throws IOException {
		if(arg0.getClickCount() == 2){
			int opcao = jTable.getSelectedColumn();
			if(opcao == 3){
				JDialogLoginRepo repo = new JDialogLoginRepo();
				repo.setLocationRelativeTo(null);
				repo.setVisible(true);
				String login = repo.getUsuario();
				String senha = repo.getSenha();
				if(!repo.isCancel()){		   
					 Medicao medicao = (Medicao) jTable.getValueAt(jTable.getSelectedRow(), 2);			   		    	
					String filePath = medicao.getNome();
			        String url = medicao.getCaminho();
			        String nameTemp = "RevisaoMedicao";
			        SVNManager.openFile(url, filePath, login, senha, nameTemp);
				  }else JOptionPane.showMessageDialog(null, "O Reposit\u00f3rio ainda n\u00e3o est\u00e1 configurado.");	
			}
			else if(opcao == 4){
				
				int i = JOptionPane.showConfirmDialog(null ,"Deseja excluir a revis\u00e3o selecionada?", 
						"Revis\u00e3o de Medi�\u00e3o",JOptionPane.YES_NO_OPTION); 
						if (i == JOptionPane.YES_OPTION ) {jButtonExcluir(arg0); }
					
			}
		}
		
	}

	private void jButtonExcluir(MouseEvent arg0) {
		int i = JOptionPane.showConfirmDialog(null ,"Deseja excluir a Aloca�\u00e3o selecionada?", 
				"Aloca�\u00e3o",JOptionPane.YES_NO_OPTION); 
		if (i == JOptionPane.YES_OPTION ) { 
			Medicao medicao = (Medicao) jTable.getValueAt(jTable.getSelectedRow(), 2);
			Marco marco = (Marco) jTable.getValueAt(jTable.getSelectedRow(), 0);
			marco.getMedicoes().remove(medicao);
			MarcoDAO.updateMarco(marco);
			MedicaoDAO.removeMedicao(medicao);
			populaTabela();		
		}
	}
	private void populaTabela() {
		tableModel.setNumRows(0);
		for (Fase fase : Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso().getFase()) {
			for (Marco marco : fase.getMarco()) {
				for (Medicao medicao : marco.getMedicoes()) {
					tableModel.addRow(new Object[]{ medicao.getMarco(), medicao.getData(),medicao, new Icone().getBaixar(), new Icone().getExcluir()});		
				}
				
			}
			
		}
		
		
		
	}
}
