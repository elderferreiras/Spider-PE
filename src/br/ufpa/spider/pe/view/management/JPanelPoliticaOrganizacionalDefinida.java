
package br.ufpa.spider.pe.view.management;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;

import br.ufpa.spider.pe.controller.PoliticaOrganizacionalController;
import br.ufpa.spider.pe.model.PoliticaOrganizacional;
import br.ufpa.spider.pe.model.ProdutoTrabalho;
import br.ufpa.spider.pe.model.set.SVN;
import br.ufpa.spider.pe.model.set.dao.SVNDAO;
import br.ufpa.spider.pe.view.Spider_PE_Home;
import br.ufpa.spider.pe.view.util.Icone;
import br.ufpa.spider.pe.view.util.SVNManager;

import javax.swing.ImageIcon;

public class JPanelPoliticaOrganizacionalDefinida extends JPanel {
	private JTextField jTextFieldCaminho;
	private JTextField jTextFieldNomeOrganizacao;
	private JLabel jLabelNomeOrganizacao;
	private JLabel jLabelLocalizePDF;
	private JButton JButtonVisualizarPDF;
	private JButton jButtonImportar;
	private JFileChooser jFileChooser;
	private String caminho;
	private PoliticaOrganizacional politicaOrganizacional = new PoliticaOrganizacional();
	private ProdutoTrabalho produtoTrabalho = new ProdutoTrabalho();
	private JLabel lblNomeDoArquivo;
	private JTextField jTextFieldNomeArquivo;
	private JButton btnRepobrowser;

	/**
	 * Create the panel.
	 */
	public JPanelPoliticaOrganizacionalDefinida() {
		setBorder(new EmptyBorder(15, 15, 15, 15));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 116, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{32, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.1, 0.1, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		jLabelNomeOrganizacao = new JLabel("Nome da Organiza\u00E7\u00E3o");
		GridBagConstraints gbc_jLabelNomeOrganizacao = new GridBagConstraints();
		gbc_jLabelNomeOrganizacao.anchor = GridBagConstraints.WEST;
		gbc_jLabelNomeOrganizacao.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelNomeOrganizacao.gridx = 0;
		gbc_jLabelNomeOrganizacao.gridy = 0;
		add(jLabelNomeOrganizacao, gbc_jLabelNomeOrganizacao);
		
		jTextFieldNomeOrganizacao = new JTextField();
		jTextFieldNomeOrganizacao.setColumns(10);
		GridBagConstraints gbc_jTextFieldNomeOrganizacao = new GridBagConstraints();
		gbc_jTextFieldNomeOrganizacao.gridwidth = 5;
		gbc_jTextFieldNomeOrganizacao.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldNomeOrganizacao.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldNomeOrganizacao.gridx = 1;
		gbc_jTextFieldNomeOrganizacao.gridy = 0;
		add(jTextFieldNomeOrganizacao, gbc_jTextFieldNomeOrganizacao);
		
		jLabelLocalizePDF = new JLabel("Caminho do arquivo");
		GridBagConstraints gbc_jLabelLocalizePDF = new GridBagConstraints();
		gbc_jLabelLocalizePDF.anchor = GridBagConstraints.WEST;
		gbc_jLabelLocalizePDF.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelLocalizePDF.gridx = 0;
		gbc_jLabelLocalizePDF.gridy = 1;
		add(jLabelLocalizePDF, gbc_jLabelLocalizePDF);
		
		jTextFieldCaminho = new JTextField();
		GridBagConstraints gbc_jTextFieldCaminho = new GridBagConstraints();
		gbc_jTextFieldCaminho.gridwidth = 4;
		gbc_jTextFieldCaminho.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldCaminho.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldCaminho.gridx = 1;
		gbc_jTextFieldCaminho.gridy = 1;
		add(jTextFieldCaminho, gbc_jTextFieldCaminho);
		jTextFieldCaminho.setColumns(10);
		
		JButtonVisualizarPDF = new JButton("Visualizar PDF");
		JButtonVisualizarPDF.setIcon(new Icone().getPdf_download());
		JButtonVisualizarPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JButtonVisualizarPDFActioPerformed(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnRepobrowser = new JButton("Repo-Browser");
		btnRepobrowser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonRepoBrowser(e);
			}
		});
		GridBagConstraints gbc_btnRepobrowser = new GridBagConstraints();
		gbc_btnRepobrowser.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRepobrowser.insets = new Insets(0, 0, 5, 5);
		gbc_btnRepobrowser.gridx = 5;
		gbc_btnRepobrowser.gridy = 1;
		add(btnRepobrowser, gbc_btnRepobrowser);
		
		lblNomeDoArquivo = new JLabel("Nome do arquivo  (.pdf)");
		GridBagConstraints gbc_lblNomeDoArquivo = new GridBagConstraints();
		gbc_lblNomeDoArquivo.anchor = GridBagConstraints.WEST;
		gbc_lblNomeDoArquivo.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomeDoArquivo.gridx = 0;
		gbc_lblNomeDoArquivo.gridy = 2;
		add(lblNomeDoArquivo, gbc_lblNomeDoArquivo);
		
		jTextFieldNomeArquivo = new JTextField();
		jTextFieldNomeArquivo.setEditable(false);
		GridBagConstraints gbc_jTextFieldNomeArquivo = new GridBagConstraints();
		gbc_jTextFieldNomeArquivo.gridwidth = 5;
		gbc_jTextFieldNomeArquivo.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldNomeArquivo.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldNomeArquivo.gridx = 1;
		gbc_jTextFieldNomeArquivo.gridy = 2;
		add(jTextFieldNomeArquivo, gbc_jTextFieldNomeArquivo);
		jTextFieldNomeArquivo.setColumns(10);
		
		
		JButtonVisualizarPDF.setMinimumSize(new Dimension(85, 23));
		JButtonVisualizarPDF.setPreferredSize(new Dimension(85, 23));
		GridBagConstraints gbc_JButtonVisualizarPDF = new GridBagConstraints();
		gbc_JButtonVisualizarPDF.fill = GridBagConstraints.BOTH;
		gbc_JButtonVisualizarPDF.insets = new Insets(0, 0, 0, 5);
		gbc_JButtonVisualizarPDF.gridx = 4;
		gbc_JButtonVisualizarPDF.gridy = 3;
		add(JButtonVisualizarPDF, gbc_JButtonVisualizarPDF);
		
		jButtonImportar = new JButton("Salvar");
		jButtonImportar.setIcon(new Icone().getSalvar());
		jButtonImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButtonImportarActionPerformed(e);
			}
		});
		jButtonImportar.setMinimumSize(new Dimension(85, 23));
		jButtonImportar.setMaximumSize(new Dimension(75, 23));
		jButtonImportar.setPreferredSize(new Dimension(85, 23));
		GridBagConstraints gbc_jButtonImportar = new GridBagConstraints();
		gbc_jButtonImportar.fill = GridBagConstraints.HORIZONTAL;
		gbc_jButtonImportar.insets = new Insets(0, 0, 0, 5);
		gbc_jButtonImportar.gridx = 5;
		gbc_jButtonImportar.gridy = 3;
		add(jButtonImportar, gbc_jButtonImportar);
		preencherCampos();
		jButtonEnabled();

	}
    
    protected void jButtonRepoBrowser(ActionEvent event) {
    	JDialogLoginRepo repo = new JDialogLoginRepo();
		repo.setLocationRelativeTo(null);
		repo.setVisible(true);
		String login = repo.getUsuario();
		String senha = repo.getSenha();
		if(!repo.isCancel()){
    	 if(!SVNDAO.findAll().isEmpty()||!(SVNDAO.findAll()==null)){     
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
				jTextFieldCaminho.setText(repositorio.getCaminho()); 
			   }
			   catch(Exception e){
				   JOptionPane.showMessageDialog(null, "Nenhum caminho selecionado.");
			   }	
	       setaNome(jTextFieldCaminho.getText());
	       setaCaminho(jTextFieldCaminho.getText());
    	   }else JOptionPane.showMessageDialog(null, "O Reposit\u00f3rio ainda n\u00e3o est\u00e1 configurado.");	
		}
	}

	private void setaCaminho(String caminho) {
		for(int i=caminho.length()-2; i>=0; i--){
			if(caminho.charAt(i) == '/'){
				jTextFieldCaminho.setText((String) caminho.subSequence(0, i+1));
				break;
			}
			
		}		
	}

	private void jButtonEnabled() {	
    		if(Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso().getPoliticaOrganizacional() == null)
    			JButtonVisualizarPDF.setEnabled(false);
    		else
    			JButtonVisualizarPDF.setEnabled(true);
		
	}

	protected void JButtonVisualizarPDFActioPerformed(ActionEvent e) throws IOException {
		JDialogLoginRepo repo = new JDialogLoginRepo();
		repo.setLocationRelativeTo(null);
		repo.setVisible(true);
		String login = repo.getUsuario();
		String senha = repo.getSenha();
		if(!repo.isCancel()){
		 if(!SVNDAO.findAll().isEmpty()||!(SVNDAO.findAll()==null)){
			  String url = jTextFieldCaminho.getText();;
		        String filePath = jTextFieldNomeArquivo.getText();
		        String nameTemp = "PoliticaOrganizacional";
		        SVNManager.openFile(url, filePath, login, senha, nameTemp);
	      }else JOptionPane.showMessageDialog(null, "O Reposit\u00f3rio ainda n\u00e3o est\u00e1 configurado.");
		}
	}

	protected void JButtonImportarActionPerformed(ActionEvent evt) {
		if(!jTextFieldNomeArquivo.equals("Nenhum arquivo selecionado.")){
			try {
				produtoTrabalho.setCaminho(jTextFieldCaminho.getText());
				
				produtoTrabalho.setNome(jTextFieldNomeArquivo.getText());			
				
				politicaOrganizacional.setNome(jTextFieldNomeOrganizacao.getText());
				politicaOrganizacional.setVersao(politicaOrganizacional.getVersao() + 1);	
				
					
				politicaOrganizacional.setProcesso(Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso());								
				Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso().setPoliticaOrganizacional(politicaOrganizacional);
				
				PoliticaOrganizacionalController.savePoliticaOrganizacional(politicaOrganizacional);	
				if(Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso()!=null){
					if (!(Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso().getPoliticaOrganizacional() == null))
						JButtonVisualizarPDF.setEnabled(true);
				}
				
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro na hora de importar.");
				e.printStackTrace();
			}
		} else JOptionPane.showMessageDialog(null, "Selecione um arquivo v\u00e1lido.");
			
		
	}
	
	private void setaNome(String caminho) {
		for(int i=caminho.length()-2; i>=0; i--){
			if(caminho.charAt(i) == '/'){
				if(((String) caminho.subSequence(i+1, caminho.length()-1)).contains(".pdf"))
					jTextFieldNomeArquivo.setText(((String) caminho.subSequence(i+1, caminho.length()-1)).replaceAll("%20", " "));
				else {
					JOptionPane.showMessageDialog(null, "Nenhum arquivo pdf foi selecionado.");
					jTextFieldNomeArquivo.setText("Nenhum arquivo selecionado.");
				}
				break;
				}
			
		}
	}

	public void preencherCampos()
	{
		
		if(Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso().getPoliticaOrganizacional()!=null){	
			politicaOrganizacional = Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso().getPoliticaOrganizacional();			
			produtoTrabalho = politicaOrganizacional.getProdutoTrabalho();			
			jTextFieldNomeOrganizacao.setText(politicaOrganizacional.getNome());
			jTextFieldCaminho.setText(produtoTrabalho.getCaminho());
			jTextFieldNomeArquivo.setText(produtoTrabalho.getNome());
		}
	}

}
