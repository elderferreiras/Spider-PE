package br.ufpa.spider.pe.view.util;
import java.awt.Component;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;

public class ManagerTree extends DefaultTreeCellRenderer implements TreeCellRenderer {
	
	public final static String PLAN_ORGANIZACIONAL = "Pol\u00EDtica Organizacional";
	public final static String ESTIMATIVA_ESFORCO = "Estimativa de Esfor\u00E7o";
	public final static String CRONO_ATIVIDADES = "Cronograma de Tarefas e Marcos";
	public final static String RISCOS_IDENTIFICADOS = "Riscos Identificados";
	public final static String CAPACIDADE_TREINAMENTO = "Capacidade e Treinamento";
	public final static String RECURSOS_RESP = "Recursos e Responsabilidades";
	public final static String PLAN_COMUNICACAO = "Plano de Comunica\u00E7\u00E3o";
	public final static String PROD_TRAB = "Avalia\u00E7\u00E3o Produtos de Trabalho";
	public final static String AVAL_PROCESSO= "Avalia\u00E7\u00E3o do Processo";
	public final static String DEF_MEDICAO= "Defini\u00E7\u00E3o de Medi\u00E7\u00E3o";
	private static String planejamento[] = {
			PLAN_ORGANIZACIONAL,
			ESTIMATIVA_ESFORCO,
			CRONO_ATIVIDADES,
			RISCOS_IDENTIFICADOS,
			CAPACIDADE_TREINAMENTO,
			RECURSOS_RESP,
			PLAN_COMUNICACAO,
			PROD_TRAB,
			AVAL_PROCESSO,
			DEF_MEDICAO
	};
	
	public final static String REVISOES_PROCESSO = "Revis\u00F5es no Processo";
	private static String monitoramentoControle[] = {
			REVISOES_PROCESSO,
	};
    
    public static DefaultTreeModel getModelo() {
    	DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("");
    	DefaultMutableTreeNode planejamentoNode = new DefaultMutableTreeNode("Planejamento");
    	DefaultMutableTreeNode monitoramentoControleNode = new DefaultMutableTreeNode("Monitoramento e Controle");

		for (int i = 0; i < planejamento.length; i++) {
                planejamentoNode.add(new DefaultMutableTreeNode(planejamento[i]));
        }
		
		for (int i = 0; i < monitoramentoControle.length; i++) {
            monitoramentoControleNode.add(new DefaultMutableTreeNode(monitoramentoControle[i]));
    }
		
		rootNode.add(planejamentoNode);
		rootNode.add(monitoramentoControleNode);

        return new DefaultTreeModel(rootNode);
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

        setIcon(new Icone().getFolder_open());
        
        for (int i = 0; i < planejamento.length; i++) {
            if (node.toString().equals(planejamento[i])) {
                setIcon(new Icone().getForm());
            }
        }
        
        for (int i = 0; i < monitoramentoControle.length; i++) {
            if (node.toString().equals(monitoramentoControle[i])) {
                setIcon(new Icone().getForm());
            }
        }
        
        return this;
    }
    
    
}

