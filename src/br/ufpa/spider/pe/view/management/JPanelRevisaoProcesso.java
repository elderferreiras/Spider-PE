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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
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

import br.ufpa.spider.pe.controller.MarcoController;
import br.ufpa.spider.pe.controller.ProcessoController;
import br.ufpa.spider.pe.controller.RevisaoProcessoController;
import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.RevisaoProcesso;
import br.ufpa.spider.pe.model.Tipo;
import br.ufpa.spider.pe.model.dao.CampoDAO;
import br.ufpa.spider.pe.model.dao.MarcoDAO;
import br.ufpa.spider.pe.model.dao.RevisaoProcessoDAO;
import br.ufpa.spider.pe.model.dao.TipoDAO;
import br.ufpa.spider.pe.model.set.SVN;
import br.ufpa.spider.pe.model.set.SpiderCL;
import br.ufpa.spider.pe.model.set.dao.SVNDAO;
import br.ufpa.spider.pe.model.set.dao.SpiderCLDAO;
import br.ufpa.spider.pe.model.util.MyTableModel;
import br.ufpa.spider.pe.view.Spider_PE_Home;
import br.ufpa.spider.pe.view.util.Icone;
import br.ufpa.spider.pe.view.util.SVNManager;

public class JPanelRevisaoProcesso extends JPanel {
	private JTextField jTextFieldRevisao;
	private JTable jTable;
	private DefaultTableModel tableModel;
	private JLabel jLabelAreaProcesso;
	private JComboBox jComboBoxAreaProcesso;
	private JLabel jLabelRealizarRevisao;
	private JButton jButtonSpiderCL;
	private JLabel JLabelRevisao;
	private JButton jButtonProcurar;
	private JLabel jLabelQuando;
	private JComboBox jComboBoxQuando;
	private JButton jButtonCadastrar;
	private JScrollPane scrollPane;
	private JLabel jLabelNome;
	private JTextField jTextFieldNome;
	private MaskFormatter formatDate;
	private JLabel jLabelSalvarEm;
	private JTextField jTextFieldCaminhoRepo;
	private JButton jButtonRepobrowser;
	private JToolBar toolBar;
	private JLabel lblRevisoDerea;
	private JLabel lblData;
	private JLabel jLabelData2;
	
	public JPanelRevisaoProcesso() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{137, 186, 140, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{45, 54, 0, 0, 35, 0, 0, 33, 33, 43, 102, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblRevisoDerea = new JLabel("Revis\u00e3o de \u00c1rea da Processo");
		lblRevisoDerea.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblRevisoDerea = new GridBagConstraints();
		gbc_lblRevisoDerea.gridwidth = 3;
		gbc_lblRevisoDerea.insets = new Insets(0, 0, 5, 5);
		gbc_lblRevisoDerea.gridx = 1;
		gbc_lblRevisoDerea.gridy = 0;
		add(lblRevisoDerea, gbc_lblRevisoDerea);
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.gridwidth = 3;
		gbc_toolBar.insets = new Insets(0, 0, 5, 5);
		gbc_toolBar.gridx = 1;
		gbc_toolBar.gridy = 1;
		add(toolBar, gbc_toolBar);
		
		jButtonSpiderCL = new JButton("");
		jButtonSpiderCL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonSpiderCL();
			}
		});
		toolBar.add(jButtonSpiderCL);
		jButtonSpiderCL.setBackground(Color.WHITE);
		jButtonSpiderCL.setIcon(new Icone().getSpider_cl());
		
		jLabelRealizarRevisao = new JLabel("Realizar Revis\u00E3o da \u00C1rea de Processo Selecionada");
		jLabelRealizarRevisao.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_jLabelRealizarRevisao = new GridBagConstraints();
		gbc_jLabelRealizarRevisao.gridwidth = 3;
		gbc_jLabelRealizarRevisao.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelRealizarRevisao.gridx = 1;
		gbc_jLabelRealizarRevisao.gridy = 2;
		add(jLabelRealizarRevisao, gbc_jLabelRealizarRevisao);
		
		jLabelAreaProcesso = new JLabel("\u00C1rea de Processo");
		GridBagConstraints gbc_jLabelAreaProcesso = new GridBagConstraints();
		gbc_jLabelAreaProcesso.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelAreaProcesso.anchor = GridBagConstraints.SOUTHWEST;
		gbc_jLabelAreaProcesso.gridx = 0;
		gbc_jLabelAreaProcesso.gridy = 3;
		add(jLabelAreaProcesso, gbc_jLabelAreaProcesso);
		
		jComboBoxAreaProcesso = new JComboBox();
		GridBagConstraints gbc_jComboBoxAreaProcesso = new GridBagConstraints();
		gbc_jComboBoxAreaProcesso.insets = new Insets(0, 0, 5, 5);
		gbc_jComboBoxAreaProcesso.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxAreaProcesso.gridx = 1;
		gbc_jComboBoxAreaProcesso.gridy = 3;
		add(jComboBoxAreaProcesso, gbc_jComboBoxAreaProcesso);
		
		JLabelRevisao = new JLabel("Revis\u00E3o");
		GridBagConstraints gbc_JLabelRevisao = new GridBagConstraints();
		gbc_JLabelRevisao.anchor = GridBagConstraints.SOUTHWEST;
		gbc_JLabelRevisao.insets = new Insets(0, 0, 5, 5);
		gbc_JLabelRevisao.gridx = 0;
		gbc_JLabelRevisao.gridy = 4;
		add(JLabelRevisao, gbc_JLabelRevisao);
		
		jTextFieldRevisao = new JTextField();
		jTextFieldRevisao.setMinimumSize(new Dimension(4, 25));
		jTextFieldRevisao.setMaximumSize(new Dimension(2147483647, 25));
		jTextFieldRevisao.setPreferredSize(new Dimension(4, 25));
		GridBagConstraints gbc_jTextFieldRevisao = new GridBagConstraints();
		gbc_jTextFieldRevisao.gridwidth = 3;
		gbc_jTextFieldRevisao.anchor = GridBagConstraints.SOUTH;
		gbc_jTextFieldRevisao.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldRevisao.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldRevisao.gridx = 1;
		gbc_jTextFieldRevisao.gridy = 4;
		add(jTextFieldRevisao, gbc_jTextFieldRevisao);
		jTextFieldRevisao.setColumns(10);
		
		jButtonProcurar = new JButton("Procurar");
		jButtonProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionListenerJButtonProcurar();
			}
		});
		GridBagConstraints gbc_jButtonProcurar = new GridBagConstraints();
		gbc_jButtonProcurar.fill = GridBagConstraints.HORIZONTAL;
		gbc_jButtonProcurar.anchor = GridBagConstraints.SOUTH;
		gbc_jButtonProcurar.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonProcurar.gridx = 4;
		gbc_jButtonProcurar.gridy = 4;
		add(jButtonProcurar, gbc_jButtonProcurar);
		
		jLabelNome = new JLabel("Nome");
		GridBagConstraints gbc_jLabelNome = new GridBagConstraints();
		gbc_jLabelNome.anchor = GridBagConstraints.SOUTHWEST;
		gbc_jLabelNome.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelNome.gridx = 0;
		gbc_jLabelNome.gridy = 5;
		add(jLabelNome, gbc_jLabelNome);
		
		jTextFieldNome = new JTextField();
		jTextFieldNome.setEditable(false);
		jTextFieldNome.setPreferredSize(new Dimension(4, 25));
		jTextFieldNome.setMinimumSize(new Dimension(4, 25));
		jTextFieldNome.setMaximumSize(new Dimension(2147483647, 25));
		GridBagConstraints gbc_jTextFieldNome = new GridBagConstraints();
		gbc_jTextFieldNome.gridwidth = 3;
		gbc_jTextFieldNome.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldNome.gridx = 1;
		gbc_jTextFieldNome.gridy = 5;
		add(jTextFieldNome, gbc_jTextFieldNome);
		jTextFieldNome.setColumns(10);
		
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
		jTextFieldCaminhoRepo.setPreferredSize(new Dimension(4, 25));
		jTextFieldCaminhoRepo.setMinimumSize(new Dimension(4, 25));
		jTextFieldCaminhoRepo.setMaximumSize(new Dimension(2147483647, 25));
		GridBagConstraints gbc_jTextFieldCaminhoRepo = new GridBagConstraints();
		gbc_jTextFieldCaminhoRepo.gridwidth = 3;
		gbc_jTextFieldCaminhoRepo.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldCaminhoRepo.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldCaminhoRepo.gridx = 1;
		gbc_jTextFieldCaminhoRepo.gridy = 6;
		add(jTextFieldCaminhoRepo, gbc_jTextFieldCaminhoRepo);
		jTextFieldCaminhoRepo.setColumns(10);
		
		jButtonRepobrowser = new JButton("Repo-Browser");
		jButtonRepobrowser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListenerJButtonRepoBrowser();
			}
		});
		GridBagConstraints gbc_jButtonRepobrowser = new GridBagConstraints();
		gbc_jButtonRepobrowser.anchor = GridBagConstraints.SOUTH;
		gbc_jButtonRepobrowser.fill = GridBagConstraints.HORIZONTAL;
		gbc_jButtonRepobrowser.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonRepobrowser.gridx = 4;
		gbc_jButtonRepobrowser.gridy = 6;
		add(jButtonRepobrowser, gbc_jButtonRepobrowser);
		
		jLabelQuando = new JLabel("Quando");
		GridBagConstraints gbc_jLabelQuando = new GridBagConstraints();
		gbc_jLabelQuando.anchor = GridBagConstraints.SOUTHWEST;
		gbc_jLabelQuando.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelQuando.gridx = 0;
		gbc_jLabelQuando.gridy = 7;
		add(jLabelQuando, gbc_jLabelQuando);
		
		jComboBoxQuando = new JComboBox();
		GridBagConstraints gbc_jComboBoxQuando = new GridBagConstraints();
		gbc_jComboBoxQuando.anchor = GridBagConstraints.SOUTH;
		gbc_jComboBoxQuando.insets = new Insets(0, 0, 5, 5);
		gbc_jComboBoxQuando.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxQuando.gridx = 1;
		gbc_jComboBoxQuando.gridy = 7;
		add(jComboBoxQuando, gbc_jComboBoxQuando);
		
		
		ItemListener listener = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemListenerStateChaged(e);
			}
		};	
		
		jComboBoxQuando.addItemListener(listener);
		
		populajComboBoxQuando();
		try {
			formatDate = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		jButtonCadastrar = new JButton("Cadastrar");
		jButtonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListenerJButtonCadastrar();
			}
		});
		
		lblData = new JLabel("Data");
		GridBagConstraints gbc_lblData = new GridBagConstraints();
		gbc_lblData.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblData.insets = new Insets(0, 0, 5, 5);
		gbc_lblData.gridx = 0;
		gbc_lblData.gridy = 8;
		add(lblData, gbc_lblData);
		
		jLabelData2 = new JLabel("");
		jLabelData2.setFont(new Font("Dialog", Font.BOLD, 12));
		GridBagConstraints gbc_jLabelData2 = new GridBagConstraints();
		gbc_jLabelData2.anchor = GridBagConstraints.SOUTHWEST;
		gbc_jLabelData2.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelData2.gridx = 1;
		gbc_jLabelData2.gridy = 8;
		add(jLabelData2, gbc_jLabelData2);
		GridBagConstraints gbc_jButtonCadastrar = new GridBagConstraints();
		gbc_jButtonCadastrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_jButtonCadastrar.anchor = GridBagConstraints.SOUTH;
		gbc_jButtonCadastrar.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonCadastrar.gridx = 4;
		gbc_jButtonCadastrar.gridy = 9;
		add(jButtonCadastrar, gbc_jButtonCadastrar);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 10;
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
		 columns.add("Quando");
		 columns.add("Data");
		 columns.add("Arquivo");
	     columns.add("Baixar");
	     columns.add("Excluir");
			
	     tableModel = MyTableModel.modelProdutoTrabalho(columns, data);
		 jTable.setModel(tableModel);
		 jTable.getTableHeader().setReorderingAllowed(false);
		 jTable.getColumnModel().getColumn(0).setPreferredWidth(250);
		 jTable.getColumnModel().getColumn(1).setPreferredWidth(300);
		 jTable.getColumnModel().getColumn(2).setPreferredWidth(250);
		 jTable.getColumnModel().getColumn(3).setPreferredWidth(10);
		 jTable.getColumnModel().getColumn(4).setPreferredWidth(10);
		scrollPane.setViewportView(jTable);
		populaTabela();
		scrollPane.setViewportView(jTable);
		popularComboBoxAreaProcesso();
	}
	private void itemListenerStateChaged(ItemEvent e) {
		if(!jComboBoxQuando.getSelectedItem().toString().equals("Selecione...")){
			if(e.getStateChange() == ItemEvent.SELECTED) {
				Marco marco = (Marco) jComboBoxQuando.getSelectedItem();
				if(marco.getInicioPrevisto() != null && marco.getFimPrevisto() != null){
					jLabelData2.setText(marco.getInicioPrevisto()+" à "+marco.getFimPrevisto());
				} else {
					jLabelData2.setText("?");
				}
				repaint();
			}
		}
	}
	protected void jButtonSpiderCL() {
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
	        * Configurar o caminho do reposit�rio na aba Ferramentas.
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
	protected void actionListenerJButtonCadastrar() {
		if(jComboBoxAreaProcesso.getSelectedIndex()!=0){
			if(!jTextFieldRevisao.getText().equals("")){
				if(!jTextFieldCaminhoRepo.getText().equals("Escolha um caminho no reposit\u00F3rio.")){
						if(jComboBoxQuando.getSelectedIndex()!=0){
							JDialogLoginRepo repo = new JDialogLoginRepo();
							repo.setLocationRelativeTo(null);
							repo.setVisible(true);
							String login = repo.getUsuario();
							String senha = repo.getSenha();
							if(!repo.isCancel()){
								RevisaoProcesso revisao = new RevisaoProcesso();
								Tipo tipo = TipoDAO.findByName(jComboBoxAreaProcesso.getSelectedItem().toString());
								revisao.setNome(jTextFieldNome.getText());
								revisao.setCaminho(jTextFieldCaminhoRepo.getText());
								Processo processo = Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso();
								revisao.setProcesso(processo);
								revisao.setChecklistprocesso(tipo.getCheklistProcesso());
								processo.getRevisaoProcessos().add(revisao);
								Marco marco = MarcoDAO.findByName(jComboBoxQuando.getSelectedItem().toString());
								revisao.setMarco(marco);
								marco.getRevisaoProcessos().add(revisao);
								
									SVNManager.saveToRepository(revisao.getNome()+".pdf", login, senha, revisao.getCaminho(), jTextFieldRevisao.getText());
									MarcoController.saveMarco(marco, false);
									ProcessoController.saveProcesso(processo, false);			
									RevisaoProcessoController.saverevisaoProcesso(revisao, false);
									populaTabela();
									JOptionPane.showMessageDialog(null, "Revis\u00E3o cadastrada com sucesso");
									jTextFieldNome.setText("");
									jTextFieldRevisao.setText("");
									jComboBoxQuando.setSelectedIndex(0);
								} else JOptionPane.showMessageDialog(null, "O reposit\u00F3rio ainda n\u00e3o est\u00e1 configurado.");		
						} else {
							JOptionPane.showMessageDialog(null, "Selecione um marco", "Error", JOptionPane.ERROR_MESSAGE);
						}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um caminho no reposit\u00F3rio.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um documento de revis\u00E3o", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Selecione uma \u00C1rea de Processo", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	protected void actionListenerJButtonProcurar() {
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
	
	private void populajComboBoxQuando(){
		jComboBoxQuando.removeAllItems();
		jComboBoxQuando.addItem("Selecione...");
		for(Marco marco: getListMarcos()){
			jComboBoxQuando.addItem(marco);
		}
	}
	
	private List<Marco> getListMarcos() {
		List<Marco> marcos = new ArrayList<Marco>();
		for (Fase fase :  Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso().getFase()) {
			for (Marco marco : fase.getMarco()) {
					marcos.add(marco);		
			}
		}
		return marcos;
	}
	
	private void popularComboBoxAreaProcesso() {
		jComboBoxAreaProcesso.addItem("Selecione...");
			for(Tipo tipo: CampoDAO.findByName("\u00C1rea de Processo").getTipo()){
				if(tipo.getCheklistProcesso()!=null){
					jComboBoxAreaProcesso.addItem(tipo.getNome());
				}
			}
	}
	protected void jTableMouseClicked(MouseEvent arg0) {
		if(arg0.getClickCount() == 2){
			int opcao = jTable.getSelectedColumn();
			RevisaoProcesso revisao = (RevisaoProcesso) jTable.getValueAt(jTable.getSelectedRow(), 2);
			if(opcao == 3){
				JDialogLoginRepo repo = new JDialogLoginRepo();
				repo.setLocationRelativeTo(null);
				repo.setVisible(true);
				String login = repo.getUsuario();
				String senha = repo.getSenha();
				if(!repo.isCancel()){
					String filePath = revisao.getNome()+".pdf";
			        String url = revisao.getCaminho();
			        String nameTemp = "Revis\u00e3o Processo";
			        try {
						SVNManager.openFile(url, filePath, login, senha, nameTemp);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }else JOptionPane.showMessageDialog(null, "O Reposit\u00f3rio ainda n\u00e3o est\u00e1 configurado.");
			}
			else if(opcao == 4){
				int i = JOptionPane.showConfirmDialog(null ,"Deseja excluir a revis\u00e3o selecionada?", 
						"Revis\u00e3o do Processo",JOptionPane.YES_NO_OPTION); 
						if (i == JOptionPane.YES_OPTION ) { 
							Processo processo =  revisao.getProcesso();
							Marco marco = revisao.getMarco();
							processo.getRevisaoProcessos().remove(revisao);
							marco.getRevisaoProcessos().remove(revisao);
							ProcessoController.saveProcesso(processo, false);
							MarcoController.saveMarco(marco, false);
							RevisaoProcessoDAO.removeRevisaoProcesso(revisao);
							populaTabela();
							JOptionPane.showMessageDialog(null, "Exclu\u00eddo com sucesso"); }
			
			}
		}
		
	}

	private void populaTabela() {
		tableModel.setNumRows(0);
		for(RevisaoProcesso revisao : Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso().getRevisaoProcessos()){
			tableModel.addRow(new Object[]{ revisao.getMarco(),revisao.getData(),revisao, new Icone().getBaixar(), new Icone().getExcluir()});
		}
		
	}

}

