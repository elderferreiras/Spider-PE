package br.ufpa.spider.pe.view.util;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import br.ufpa.spider.pe.view.Spider_PE_Home;
import br.ufpa.spider.pe.view.administration.JPanelFerramentaAvaliacao;
import br.ufpa.spider.pe.view.administration.JPanelFerramentaComunicacao;
import br.ufpa.spider.pe.view.administration.JPanelFerramentaEstimativa;
import br.ufpa.spider.pe.view.administration.JPanelFerramentaGestaoMudanca;
import br.ufpa.spider.pe.view.administration.JPanelFerramentaMedicao;
import br.ufpa.spider.pe.view.administration.Spider_PE_Administration;
import br.ufpa.spider.pe.view.management.JDialogOpcaoCadastrarPolitica;
import br.ufpa.spider.pe.view.management.JPanelNovaPoliticaOrganizacional;
import br.ufpa.spider.pe.view.management.JPanelPoliticaOrganizacionalDefinida;
import br.ufpa.spider.pe.view.management.Spider_PE_Management;




public class TreeMouseListenerAdmin extends MouseAdapter {

	private JTree jTree;
	private JPanel jPanel;

	public TreeMouseListenerAdmin(JTree tree, JPanel panel) {
		jTree = tree;
		jPanel = panel;
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent evt) {
		if (evt.getClickCount() == 1) {			
			if (jTree.getSelectionPath() != null) {	
				Spider_PE_Home.getInstance().Spider_PE_Settings.setCursor(new Cursor(Cursor.WAIT_CURSOR));	

				jPanel.removeAll();
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree
						.getSelectionPath().getLastPathComponent();
				String choice = node.toString();
				
				if (choice.equals(AdminTree.ESTIMATIVAS)) {					
					JPanel panel = new JPanelFerramentaEstimativa();
					GridBagConstraints c = new GridBagConstraints();
					c.fill = GridBagConstraints.BOTH;
					c.weightx = 1;
					c.weighty = 1;
					jPanel.add(panel, c);
				} else if (choice.equals(AdminTree.GESTAO_DE_MUDANCAS)){
					JPanel panel = new JPanelFerramentaGestaoMudanca();
					GridBagConstraints c = new GridBagConstraints();
					c.fill = GridBagConstraints.BOTH;
					c.weightx = 1;
					c.weighty = 1;
					jPanel.add(panel, c);
				} else if(choice.equals(AdminTree.COMUNICACAO)){
					JPanel panel = new JPanelFerramentaComunicacao();
					GridBagConstraints c = new GridBagConstraints();
					c.fill = GridBagConstraints.BOTH;
					c.weightx = 1;
					c.weighty = 1;
					jPanel.add(panel, c);
				} else if(choice.equals(AdminTree.AVALIACAO)){
					JPanel panel = new JPanelFerramentaAvaliacao();
					GridBagConstraints c = new GridBagConstraints();
					c.fill = GridBagConstraints.BOTH;
					c.weightx = 1;
					c.weighty = 1;
					jPanel.add(panel, c);
				} else if(choice.equals(AdminTree.MEDICAO)){
					JPanel panel = new JPanelFerramentaMedicao();
					GridBagConstraints c = new GridBagConstraints();
					c.fill = GridBagConstraints.BOTH;
					c.weightx = 1;
					c.weighty = 1;
					jPanel.add(panel, c);
				}
				Spider_PE_Home.getInstance().Spider_PE_Settings.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				Spider_PE_Home.getInstance().Spider_PE_Settings.validate();
			}
		}
		
	}
		
	
	public JPanel getJPanel(){
		return jPanel;
	}
}