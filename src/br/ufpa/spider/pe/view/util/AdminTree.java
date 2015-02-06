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

public class AdminTree extends DefaultTreeCellRenderer implements TreeCellRenderer {
	
	
	public final static String ESTIMATIVAS = "Estimativas";
	public final static String GESTAO_DE_MUDANCAS = "Gestão de Mudan\u00E7as";
	public final static String MEDICAO = "Medi\u00E7\u00e3o";
	public final static String AVALIACAO = "Avalia\u00E7\u00e3o";
	public final static String COMUNICACAO = "Comunica\u00E7\u00e3o";
	private static String ferramentas[] = {
		ESTIMATIVAS,
		GESTAO_DE_MUDANCAS,
		MEDICAO,
		AVALIACAO,
		COMUNICACAO
	};
    
    public static DefaultTreeModel getModelo() {
    	DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("");
    	DefaultMutableTreeNode ferramentasNode = new DefaultMutableTreeNode("Configurar Ferramentas");

		for (int i = 0; i < ferramentas.length; i++) {
			ferramentasNode.add(new DefaultMutableTreeNode(ferramentas[i]));
        }
		
		rootNode.add(ferramentasNode);

        return new DefaultTreeModel(rootNode);
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

       setIcon(new Icone().getTool());
        
        for (int i = 0; i < ferramentas.length; i++) {
            if (node.toString().equals(ferramentas[i])) {
            	setIcon(new Icone().getTool_icon());
            }
        }
        
        return this;
    }
    
    
    
}

