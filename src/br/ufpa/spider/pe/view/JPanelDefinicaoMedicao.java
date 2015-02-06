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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
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

import br.ufpa.spider.pe.controller.PlanoMedicaoController;
import br.ufpa.spider.pe.controller.ProcessoController;
import br.ufpa.spider.pe.controller.ProdutoTrabalhoController;
import br.ufpa.spider.pe.model.PlanoMedicao;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.ProdutoTrabalho;
import br.ufpa.spider.pe.model.dao.PlanoMedicaoDAO;
import br.ufpa.spider.pe.model.dao.ProdutoTrabalhoDAO;
import br.ufpa.spider.pe.model.set.SVN;
import br.ufpa.spider.pe.model.set.SpiderMPlan;
import br.ufpa.spider.pe.model.set.dao.SVNDAO;
import br.ufpa.spider.pe.model.set.dao.SpiderMPlanDAO;
import br.ufpa.spider.pe.model.util.MyTableModel;
import br.ufpa.spider.pe.view.util.Icone;
import br.ufpa.spider.pe.view.util.SVNManager;

public class JPanelDefinicaoMedicao extends JPanel {
	private JLabel jLabelMonitoramento;
	private JLabel jLabelDefinirAtividades;
	private JTextField jTextFieldEnviarArquivo;
	private JButton jButtonProcurar;
	private JButton jButtonSpiderMPLAN;
	private JButton jButtonCancelar;
	private JButton jButtonEnviar;
	private JTable jTable;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private JLabel jLabelNome;
	private JTextField jTextFieldNome;
	private JLabel jLabelSalvarEm;
	private JTextField jTextFieldCaminhoRepo;
	private JButton jButtonRepoBrowser;
	private JToolBar toolBar;
	public JPanelDefinicaoMedicao() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{130, -19, 0, 1, 118, 102, 0};
		gridBagLayout.rowHeights = new int[]{74, 66, 38, 35, 35, 35, 38, 110, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		jLabelMonitoramento = new JLabel("Monitoramento e Controle");
		jLabelMonitoramento.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_jLabelMonitoramento = new GridBagConstraints();
		gbc_jLabelMonitoramento.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelMonitoramento.anchor = GridBagConstraints.SOUTH;
		gbc_jLabelMonitoramento.gridx = 2;
		gbc_jLabelMonitoramento.gridy = 0;
		add(jLabelMonitoramento, gbc_jLabelMonitoramento);
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.insets = new Insets(0, 0, 5, 5);
		gbc_toolBar.gridx = 2;
		gbc_toolBar.gridy = 1;
		add(toolBar, gbc_toolBar);
		
		jButtonSpiderMPLAN = new JButton("");
		jButtonSpiderMPLAN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonSpiderMPlan();
			}
		});
		toolBar.add(jButtonSpiderMPLAN);
		jButtonSpiderMPLAN.setBackground(Color.WHITE);
		jButtonSpiderMPLAN.setIcon(new Icone().getSpider_mplan());
		
		jLabelDefinirAtividades = new JLabel("Definir Atividades de Monitoramento e Controle");
		GridBagConstraints gbc_jLabelDefinirAtividades = new GridBagConstraints();
		gbc_jLabelDefinirAtividades.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelDefinirAtividades.gridx = 2;
		gbc_jLabelDefinirAtividades.gridy = 2;
		add(jLabelDefinirAtividades, gbc_jLabelDefinirAtividades);
		
		JLabel jLabelEnviarArquivo = new JLabel("Enviar Arquivo");
		GridBagConstraints gbc_jLabelEnviarArquivo = new GridBagConstraints();
		gbc_jLabelEnviarArquivo.anchor = GridBagConstraints.SOUTHWEST;
		gbc_jLabelEnviarArquivo.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelEnviarArquivo.gridx = 0;
		gbc_jLabelEnviarArquivo.gridy = 3;
		add(jLabelEnviarArquivo, gbc_jLabelEnviarArquivo);
		
		jTextFieldEnviarArquivo = new JTextField();
		jTextFieldEnviarArquivo.setMinimumSize(new Dimension(4, 25));
		jTextFieldEnviarArquivo.setMaximumSize(new Dimension(2147483647, 25));
		jTextFieldEnviarArquivo.setPreferredSize(new Dimension(4, 25));
		GridBagConstraints gbc_jTextFieldEnviarArquivo = new GridBagConstraints();
		gbc_jTextFieldEnviarArquivo.anchor = GridBagConstraints.SOUTH;
		gbc_jTextFieldEnviarArquivo.gridwidth = 4;
		gbc_jTextFieldEnviarArquivo.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldEnviarArquivo.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldEnviarArquivo.gridx = 1;
		gbc_jTextFieldEnviarArquivo.gridy = 3;
		add(jTextFieldEnviarArquivo, gbc_jTextFieldEnviarArquivo);
		jTextFieldEnviarArquivo.setColumns(10);
		
		jButtonProcurar = new JButton("Procurar");
		jButtonProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListennerjButtonProcurar();
			}
		});
		GridBagConstraints gbc_jButtonProcurar = new GridBagConstraints();
		gbc_jButtonProcurar.fill = GridBagConstraints.HORIZONTAL;
		gbc_jButtonProcurar.anchor = GridBagConstraints.SOUTH;
		gbc_jButtonProcurar.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonProcurar.gridx = 5;
		gbc_jButtonProcurar.gridy = 3;
		add(jButtonProcurar, gbc_jButtonProcurar);
		
		jLabelNome = new JLabel("Nome do Arquivo");
		GridBagConstraints gbc_jLabelNome = new GridBagConstraints();
		gbc_jLabelNome.anchor = GridBagConstraints.SOUTHWEST;
		gbc_jLabelNome.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelNome.gridx = 0;
		gbc_jLabelNome.gridy = 4;
		add(jLabelNome, gbc_jLabelNome);
		
		jTextFieldNome = new JTextField();
		jTextFieldNome.setEditable(false);
		jTextFieldNome.setMaximumSize(new Dimension(2147483647, 25));
		jTextFieldNome.setMinimumSize(new Dimension(4, 25));
		jTextFieldNome.setPreferredSize(new Dimension(4, 25));
		GridBagConstraints gbc_jTextFieldNome = new GridBagConstraints();
		gbc_jTextFieldNome.anchor = GridBagConstraints.SOUTH;
		gbc_jTextFieldNome.gridwidth = 4;
		gbc_jTextFieldNome.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldNome.gridx = 1;
		gbc_jTextFieldNome.gridy = 4;
		add(jTextFieldNome, gbc_jTextFieldNome);
		jTextFieldNome.setColumns(10);
		
		jButtonCancelar = new JButton("Cancelar");
		jButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionListenerjButtonCancelar();
			}
		});
		
		jLabelSalvarEm = new JLabel("Salvar Em");
		GridBagConstraints gbc_jLabelSalvarEm = new GridBagConstraints();
		gbc_jLabelSalvarEm.anchor = GridBagConstraints.SOUTHWEST;
		gbc_jLabelSalvarEm.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelSalvarEm.gridx = 0;
		gbc_jLabelSalvarEm.gridy = 5;
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
		gbc_jTextFieldCaminhoRepo.gridwidth = 4;
		gbc_jTextFieldCaminhoRepo.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldCaminhoRepo.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldCaminhoRepo.gridx = 1;
		gbc_jTextFieldCaminhoRepo.gridy = 5;
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
		gbc_jButtonRepoBrowser.anchor = GridBagConstraints.SOUTH;
		gbc_jButtonRepoBrowser.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonRepoBrowser.gridx = 5;
		gbc_jButtonRepoBrowser.gridy = 5;
		add(jButtonRepoBrowser, gbc_jButtonRepoBrowser);
		GridBagConstraints gbc_jButtonCancelar = new GridBagConstraints();
		gbc_jButtonCancelar.fill = GridBagConstraints.HORIZONTAL;
		gbc_jButtonCancelar.anchor = GridBagConstraints.SOUTH;
		gbc_jButtonCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_jButtonCancelar.gridx = 4;
		gbc_jButtonCancelar.gridy = 6;
		add(jButtonCancelar, gbc_jButtonCancelar);
		
		jButtonEnviar = new JButton("Enviar");
		jButtonEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListenerjButtonEnviar();
			}
		});
		GridBagConstraints gbc_jButtonEnviar = new GridBagConstraints();
		gbc_jButtonEnviar.fill = GridBagConstraints.HORIZONTAL;
		gbc_jButtonEnviar.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonEnviar.anchor = GridBagConstraints.SOUTH;
		gbc_jButtonEnviar.gridx = 5;
		gbc_jButtonEnviar.gridy = 6;
		add(jButtonEnviar, gbc_jButtonEnviar);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 7;
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
		 columns.add("Arquivo");
		 columns.add("Caminho");
	     columns.add("Baixar");
	     columns.add("Excluir");
			
	     tableModel = MyTableModel.modelDefinicaoMedicao(columns, data);
		 jTable.setModel(tableModel);		 
		 jTable.getTableHeader().setReorderingAllowed(false); 
		 jTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		 jTable.getColumnModel().getColumn(1).setPreferredWidth(500);
		 jTable.getColumnModel().getColumn(2).setPreferredWidth(30);
		 jTable.getColumnModel().getColumn(3).setPreferredWidth(30);

		scrollPane.setViewportView(jTable);
		populaTabela();
		scrollPane.setViewportView(jTable);
		verifica();
	}
	
	protected void jButtonSpiderMPlan() {
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
		} else JOptionPane.showMessageDialog(null, "A ferramenta Spider-MPlan ainda n\u00E7\u00E3 est\u00e1 configurada.");		
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
	private void verifica(){
//		Processo processo = Spider_PE_Home.getInstance().getProcesso();
//		if(processo.getPlanoMedicao() != null){
//			jTextFieldEnviarArquivo.setText(processo.getPlanoMedicao().getProdutoTrabalho().getCaminho());
//			jTextFieldNome.setText(processo.getPlanoMedicao().getNome());
//		} else {
//			jTextFieldEnviarArquivo.setText("");
//			jTextFieldNome.setText("");
//		}
	}
	protected void actionListenerjButtonEnviar() {
		Processo processo = JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso();
		PlanoMedicao plano = null;
		ProdutoTrabalho trabalho = null;
		if(processo.getPlanoMedicao()==null){
			plano = new PlanoMedicao();
			trabalho = new ProdutoTrabalho();
			plano.setProcesso(processo);
		} else {
			plano = processo.getPlanoMedicao();
			try {
			trabalho = plano.getProdutoTrabalho();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "NOT FOUND PRODUTO DE TRABALHO", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		plano.setNome(jTextFieldNome.getText());
		trabalho.setCaminho(jTextFieldCaminhoRepo.getText());
		trabalho.setNome(plano.getNome()+".pdf");
		
		JDialogLoginRepo repo = new JDialogLoginRepo();
		repo.setLocationRelativeTo(null);
		repo.setVisible(true);
		String login = repo.getUsuario();
		String senha = repo.getSenha();
		if(!repo.isCancel()){	  
			SVNManager.saveToRepository(trabalho.getNome(), login, senha, trabalho.getCaminho(), jTextFieldEnviarArquivo.getText());
			ProdutoTrabalhoController.saveProdutoTrabalho(trabalho, false);
			plano.setProdutoTrabalho(trabalho);
			PlanoMedicaoController.saveplanoMedicao(plano, false);
			processo.setPlanoMedicao(plano);
			populaTabela();
			jTextFieldEnviarArquivo.setText("");
			jTextFieldNome.setText("");
			
		 }else JOptionPane.showMessageDialog(null, "O Reposit\u00F3rio ainda n\u00E7\u00E3 est\u00e1 configurado.");	
	}
	protected void actionListenerjButtonCancelar() {
		jTextFieldEnviarArquivo.setText("");
	}
	protected void actionListennerjButtonProcurar() {
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
			jTextFieldEnviarArquivo.setText(fileChooser.getSelectedFile().toString());
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
	protected void jTableMouseClicked(MouseEvent arg0) {
		if(arg0.getClickCount() == 2){
			int opcao = jTable.getSelectedColumn();
			PlanoMedicao plano = (PlanoMedicao) jTable.getValueAt(jTable.getSelectedRow(), 0);
			if(opcao == 2){
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
						String filePath = plano.getNome()+".pdf";
						String url = plano.getProdutoTrabalho().getCaminho();
						String nameTemp = "Definicao de Medicao";
			        try {
						SVNManager.openFile(url, filePath, login, senha, nameTemp);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  }
				 }else JOptionPane.showMessageDialog(null, "O Reposit\u00F3rio ainda n\u00E7\u00E3 est\u00e1 configurado.");	
			} else if(opcao == 3){
				int i = JOptionPane.showConfirmDialog(null ,"Deseja excluir a defini\u00e7\u00e3o selecionada?", 
						"Definição de Medição",JOptionPane.YES_NO_OPTION); 
						if (i == JOptionPane.YES_OPTION ) { 
							//JOptionPane.showMessageDialog(null, "aqui");
							plano.setProcesso(null);
							Processo p = JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso();
							p.setPlanoMedicao(null);
							ProcessoController.saveProcesso(p);
							PlanoMedicaoDAO.removePlanoMedicao(plano);
							ProdutoTrabalhoDAO.removeProdutoTrabalho(plano.getProdutoTrabalho());
							JOptionPane.showMessageDialog(null, "Plano de Medi\u00e7\u00e3o removido com sucesso.");
							populaTabela();
							verifica();
							this.repaint();
						}
			     }
		}
	}

	private void populaTabela() {
		tableModel.setNumRows(0);
		PlanoMedicao plano = JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().getPlanoMedicao();
		if(plano !=null && plano.getProdutoTrabalho() != null)
		tableModel.addRow(new Object[]{ plano,plano.getProdutoTrabalho().getCaminho(), new Icone().getBaixar(), new Icone().getExcluir()});		
		
	}
}

