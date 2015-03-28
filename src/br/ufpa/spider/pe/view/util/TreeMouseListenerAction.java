package br.ufpa.spider.pe.view.util;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import br.ufpa.spider.pe.model.util.DataBaseManager;
import br.ufpa.spider.pe.view.Spider_PE_Home;
import br.ufpa.spider.pe.view.management.JDialogOpcaoCadastrarPolitica;
import br.ufpa.spider.pe.view.management.JPanelAvalicaoProcesso;
import br.ufpa.spider.pe.view.management.JPanelAvalicaoProduto;
import br.ufpa.spider.pe.view.management.JPanelCadastroRisco;
import br.ufpa.spider.pe.view.management.JPanelCapacidades;
import br.ufpa.spider.pe.view.management.JPanelCronogramaTarefas;
import br.ufpa.spider.pe.view.management.JPanelDefinicaoMedicao;
import br.ufpa.spider.pe.view.management.JPanelEstimarEsforco;
import br.ufpa.spider.pe.view.management.JPanelGerenciaEsforco;
import br.ufpa.spider.pe.view.management.JPanelGerenciaRecursos;
import br.ufpa.spider.pe.view.management.JPanelGerenciaResponsabilidade;
import br.ufpa.spider.pe.view.management.JPanelGerenciaRisco;
import br.ufpa.spider.pe.view.management.JPanelNovaPoliticaOrganizacional;
import br.ufpa.spider.pe.view.management.JPanelPlanoComunicacao;
import br.ufpa.spider.pe.view.management.JPanelPoliticaOrganizacionalDefinida;
import br.ufpa.spider.pe.view.management.JPanelProdutoTrabalho;
import br.ufpa.spider.pe.view.management.JPanelRevisaoMedicao;
import br.ufpa.spider.pe.view.management.JPanelRevisaoProblemas;
import br.ufpa.spider.pe.view.management.JPanelRevisaoProcesso;
import br.ufpa.spider.pe.view.management.JPanelRevisaoProdutoTrabalho;
import br.ufpa.spider.pe.view.management.JPanelStakeHolder;
import br.ufpa.spider.pe.view.management.Spider_PE_Management;
import br.ufpa.spider.pe.view.management.jPanelTreinamento;

public class TreeMouseListenerAction extends MouseAdapter {

	private JTree jTree;
	private JPanel jPanel;
	private JScrollPane scroll = new JScrollPane();

	public TreeMouseListenerAction(JTree tree, JPanel panel) {
		jTree = tree;
		jPanel = panel;

	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent evt) {
		if(Spider_PE_Home.getInstance().Spider_PE_Management.getProcesso()!=null){
		if (evt.getClickCount() == 1) {
			Spider_PE_Home.getInstance().Spider_PE_Management.setCursor(
					new Cursor(Cursor.WAIT_CURSOR));
			jTree.setCursor(
					new Cursor(Cursor.WAIT_CURSOR));
			jPanel.removeAll();
			Spider_PE_Home.getInstance().Spider_PE_Management.repaint();
			
			if (jTree.getSelectionPath() != null) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree
						.getSelectionPath().getLastPathComponent();
				String choice = node.toString();
				
				if (choice.equals(ManagerTree.PLAN_ORGANIZACIONAL)) {
					JDialogOpcaoCadastrarPolitica dialog = new JDialogOpcaoCadastrarPolitica();
					dialog.setVisible(true);
					
					if(dialog.isEstado() == true){
					int opcao = dialog.getSelecionado();
					JPanel panel = null;
					switch (opcao) {
					case 1:
						panel = new JPanelNovaPoliticaOrganizacional();
						break;

					case 2:
						panel = new JPanelPoliticaOrganizacionalDefinida();
						break;
					}

					jPanel.setLayout(new GridBagLayout());
					GridBagConstraints c = new GridBagConstraints();
					c.fill = GridBagConstraints.BOTH;
					c.weightx = 1;
					c.weighty = 1;
					jPanel.add(panel, c);
					}
				}
				else if(choice.equals(ManagerTree.CAPACIDADE_TREINAMENTO)){
					JPanel capacidades = new JPanelCapacidades();
					JPanel treinamento = new jPanelTreinamento();
					JTabbedPane jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
					jTabbedPane.addTab("Capacidades", null, capacidades, null);
					jTabbedPane.addTab("Treinamento", null, treinamento, null);
					//jPanel.add(jTabbedPane);
					
					jPanel.setLayout(new GridBagLayout());
					GridBagConstraints c = new GridBagConstraints();
					c.fill = GridBagConstraints.BOTH;
					c.weightx = 1;
					c.weighty = 1;
					
					jPanel.add(jTabbedPane, c);
					
				}
				
				else if (choice.equals(ManagerTree.ESTIMATIVA_ESFORCO)) {
					JPanel panelTable = new JPanelGerenciaEsforco();
					JPanel panel = new JPanelEstimarEsforco();
					JTabbedPane jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
					jTabbedPane.addTab("Estimar Esfor\u00E7o", null,panel, null);
					jTabbedPane.addTab("Ger\u00EAncia de Esfor\u00E7o", null,panelTable, null);
					//jPanel.add(jTabbedPane);
					
					jPanel.setLayout(new GridBagLayout());
					GridBagConstraints c = new GridBagConstraints();
					c.fill = GridBagConstraints.BOTH;
					c.weightx = 1;
					c.weighty = 1;
					
					jPanel.add(jTabbedPane, c);
				}
				else if(choice.equals(ManagerTree.CRONO_ATIVIDADES)) {
					JPanel panel = new JPanelCronogramaTarefas();
					//jPanel.add(panel);
					jPanel.setLayout(new GridBagLayout());
					GridBagConstraints c = new GridBagConstraints();
					c.fill = GridBagConstraints.BOTH;
					c.weightx = 1;
					c.weighty = 1;
					jPanel.add(panel, c);
					
				}
				else if(choice.equals(ManagerTree.RISCOS_IDENTIFICADOS)){
					JPanel cadastroRisco = new JPanelCadastroRisco();
					JPanel gerenciaRisco = new JPanelGerenciaRisco();
					JTabbedPane jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
					jTabbedPane.addTab("Cadastro de Risco", null, cadastroRisco, null);
					jTabbedPane.addTab("Contig\u00EAncia de Riscos", null, gerenciaRisco, null);
					//jPanel.add(jTabbedPane);
					
					jPanel.setLayout(new GridBagLayout());
					GridBagConstraints c = new GridBagConstraints();
					c.fill = GridBagConstraints.BOTH;
					c.weightx = 1;
					c.weighty = 1;
					
					jPanel.add(jTabbedPane, c);
					
				}
				else if(choice.equals(ManagerTree.RECURSOS_RESP)){
					JPanel gerenciaRecursos = new JPanelGerenciaRecursos();
					JPanel gerenciaResponsabilidades = new JPanelGerenciaResponsabilidade();
					JTabbedPane jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
					jTabbedPane.addTab("Ger\u00EAcia de Recursos", null, gerenciaRecursos, null);
					jTabbedPane.addTab("Ger\u00EAncia de Responsabilidades", null, gerenciaResponsabilidades, null);
					//jPanel.add(jTabbedPane);
					
					jPanel.setLayout(new GridBagLayout());
					GridBagConstraints c = new GridBagConstraints();
					c.fill = GridBagConstraints.BOTH;
					c.weightx = 1;
					c.weighty = 1;
					
					jPanel.add(jTabbedPane, c);
				}
				else if(choice.equals(ManagerTree.PLAN_COMUNICACAO)){
					JPanel gerenciaPlanoComunicacao = new JPanelPlanoComunicacao();
					JPanel gerenciaStakerHolder = new JPanelStakeHolder();
					JTabbedPane jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
					jTabbedPane.addTab("Ger\u00EAcia de Comunica\u00E7\u00E3o", null, gerenciaPlanoComunicacao, null);
					jTabbedPane.addTab("Ger\u00EAncia de Responsabilidades", null, gerenciaStakerHolder, null);
					//jPanel.add(jTabbedPane);
					
					jPanel.setLayout(new GridBagLayout());
					GridBagConstraints c = new GridBagConstraints();
					c.fill = GridBagConstraints.BOTH;
					c.weightx = 1;
					c.weighty = 1;
					
					jPanel.add(jTabbedPane, c);
				}
				else if (choice.equals(ManagerTree.PROD_TRAB)) {
					
					JPanel panel = new JPanelAvalicaoProduto();

					jPanel.setLayout(new GridBagLayout());
					GridBagConstraints c = new GridBagConstraints();
					c.fill = GridBagConstraints.BOTH;
					c.weightx = 1;
					c.weighty = 1;
					jPanel.add(panel, c);
				}
				else if (choice.equals(ManagerTree.AVAL_PROCESSO)) {
					
				JPanel panel = new JPanelAvalicaoProcesso();
				jPanel.setLayout(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				c.fill = GridBagConstraints.BOTH;
				c.weightx = 1;
				c.weighty = 1;
				jPanel.add(panel, c);
				}
				else if(choice.equals(ManagerTree.PROD_TRAB)){
					JPanel panel = new JPanelAvalicaoProduto();
					jPanel.setLayout(new GridBagLayout());
					GridBagConstraints c = new GridBagConstraints();
					c.fill = GridBagConstraints.BOTH;
					c.weightx = 1;
					c.weighty = 1;
					jPanel.add(panel, c);
				}
				else if(choice.equals(ManagerTree.DEF_MEDICAO)){
					JPanel panel = new JPanelDefinicaoMedicao();
					jPanel.setLayout(new GridBagLayout());
					GridBagConstraints c = new GridBagConstraints();
					c.fill = GridBagConstraints.BOTH;
					c.weightx = 1;
					c.weighty = 1;
					jPanel.add(panel, c);
				}
				else if(choice.equals(ManagerTree.REVISOES_PROCESSO)){
					JPanel panelRevisao1 = new JPanelRevisaoProcesso();
					JPanel panelRevisao2 = new JPanelRevisaoProdutoTrabalho();
					JPanel panelRevisao3 = new JPanelRevisaoMedicao();
					JPanel panelRevisao4 = new JPanelRevisaoProblemas();
					JTabbedPane jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
					jTabbedPane.addTab("Processo", null, panelRevisao1, null);
					jTabbedPane.addTab("Produtos de Trabalho", null, panelRevisao2, null);
					jTabbedPane.addTab("Medi\u00E7\u00E3o", null, panelRevisao3, null);
					jTabbedPane.addTab("Problemas", null, panelRevisao4, null);
					jPanel.setLayout(new GridBagLayout());
					GridBagConstraints c = new GridBagConstraints();
					c.fill = GridBagConstraints.BOTH;
					c.weightx = 1;
					c.weighty = 1;
					
					jPanel.add(jTabbedPane, c);
				}

			}			
			Spider_PE_Home.getInstance().Spider_PE_Management.setCursor(
					new Cursor(Cursor.DEFAULT_CURSOR));
			jTree.setCursor(
					new Cursor(Cursor.DEFAULT_CURSOR));
			Spider_PE_Home.getInstance().Spider_PE_Management.validate();
		}
	  }
		else
			JOptionPane.showMessageDialog(null, "\u00C9 necess\u00E1rio selecionar um processo.");
	}
	
	public JPanel getJPanel(){
		return jPanel;
	}
}