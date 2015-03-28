package br.ufpa.spider.pe.view.management;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.JobAttributes;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import br.ufpa.spider.pe.view.util.Icone;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JProgressBar;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Toolkit;

// TODO: Document class, fields, methods, and arguments [Jon Aquino 2008-01-15]

public class RepoBrowserDialog extends JDialog {

    private static JTree repoTree;
    private static JScrollPane scrollPane;
    private SVNRepository repository;
    static SVNTreeModel treeModel;
    public String getCaminho() {
		return caminho.toString() + "/";
	}    

	SVNURL caminho;

    /**
     * Constructs a new dialog (modal) that shows a repository
     */
    public RepoBrowserDialog() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(RepoBrowserDialog.class.getResource("/br/ufpa/spider/pe/view/util/img/favicon_pe.png")));
    	setModal(true);
    	setTitle("Escolha o caminho...");
    	try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        setSize(259, 305);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{484, 0};
        gridBagLayout.rowHeights = new int[]{273, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        scrollPane = new JScrollPane();
        scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 0;
        getContentPane().add(scrollPane, gbc_scrollPane);
    }
    
    public void load(String repoUrl, final String userName, final String password) {    
    	try{
	        repoTree = new JTree();
	        
	        if(System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
	        	repoTree.setCellRenderer(new SVNTreeRendererWindows());
	        else
	        	repoTree.setCellRenderer(new SVNTreeRendererLinux());
	        repoTree.setRowHeight(22);
	        MouseListener mouseListener = new MouseAdapter() {
	             public void mousePressed(MouseEvent e) {
	                 int selecetdRow = repoTree.getRowForLocation(e.getX(), e.getY());
	                 TreePath selPath = repoTree.getPathForLocation(e.getX(), e.getY());
	                 if (selecetdRow != -1) {
	                     if (e.getClickCount() == 2) {
	                         SVNNode selectedNode = (SVNNode)selPath.getLastPathComponent();
	                         caminho = selectedNode.svnDirEntry.getURL();
	                         setVisible(false);
	                     }
	                 }
	             }
	        };  
	
	       
	        repoTree.addMouseListener(mouseListener);   
	        repository = getRepository(repoUrl, userName, password);        
	        treeModel = new SVNTreeModel(repository);
	        repoTree.setModel(treeModel);
	        scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	        scrollPane.setViewportView(repoTree); 
	        //this.setVisible(true);
    	}catch(Exception e){
    		e.printStackTrace();
    		JOptionPane.showMessageDialog(null, e);
    	}
        
    }
    
    // TODO: We need this a central place
    private SVNRepository getRepository(String url, String username, String password) {
        try {
            DAVRepositoryFactory.setup();
            SVNRepositoryFactoryImpl.setup(); /* svn:// and svn+xxx:// */
            FSRepositoryFactory.setup(); /* file:// */
            SVNRepository repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
            repository.setAuthenticationManager(SVNWCUtil.createDefaultAuthenticationManager(username, password));
            repository.setTunnelProvider(SVNWCUtil.createDefaultOptions(true));
            return repository;
        } catch (SVNException e) {
            // TODO:
            throw new RuntimeException(e);
        }
    }
    
    /*
     * Classe SVNTreeModel
     */
    private static class SVNTreeModel implements TreeModel {
        private SVNRepository repository;
        private SVNNode root;
    
        public SVNTreeModel(SVNRepository repository) {        	
            super();
            this.repository = repository;
        }

        public void addTreeModelListener(TreeModelListener arg0) {
        }

        public Object getChild(Object obj, int index) {
            SVNNode node = (SVNNode)obj;
            if (node.getChildren() == null) {
                node.loadChildren(repository);
            }
            return node.getChildren().get(index);
        }

        public int getChildCount(Object obj) {
            SVNNode node = (SVNNode)obj;
            if (node.getChildren() == null) {
                node.loadChildren(repository);
            }
            return node.getChildren().size();
        }

        public int getIndexOfChild(Object arg0, Object arg1) {
            return 0;
        }

        public Object getRoot() {
            if (root == null) {
                ArrayList children = new ArrayList();
                try {
                    SVNDirEntry rootUrl = repository.getDir("/", -1, false, children);
                    List svnNodeChildren = new ArrayList(children.size());
                    for (Iterator iter = children.iterator() ; iter.hasNext() ; ) {
                        SVNDirEntry child = (SVNDirEntry)iter.next();
                        svnNodeChildren.add(new SVNNode(child, ""));
                    }
                    Collections.sort(svnNodeChildren, new SVNNodeComparator());
                    root = new SVNNode(rootUrl, svnNodeChildren);
                } catch (SVNException e) {
                    JOptionPane.showMessageDialog(null, "Verifique se a senha est\u00e1 correta.");
                }
            }
            return root; 
        }

        public boolean isLeaf(Object obj) {
            SVNNode node = (SVNNode)obj;
            if (node.getChildren() == null) {
                node.loadChildren(repository);
            }
            return node.getChildren().size() == 0;
        }

        public void removeTreeModelListener(TreeModelListener arg0) {
        }

        public void valueForPathChanged(TreePath arg0, Object arg1) {
        }
        
    }
    
    /*
     * Classe SVNNode
     */
    private static class SVNNode {
        
        private List children;
        private SVNDirEntry svnDirEntry;
        private String parentPath;
        
        public SVNNode(SVNDirEntry svnDirEntry, String parentPath) {
            super();
            this.svnDirEntry = svnDirEntry;
            this.parentPath = parentPath;
        }

        public SVNNode(SVNDirEntry svnDirEntry, List children) {
            super();
            this.children = children;
            this.svnDirEntry = svnDirEntry;
        }

        public List getChildren() {
            return children;
        }

        public SVNDirEntry getSVNDirEntry() {
            return svnDirEntry;
        }
        
        public String getParentPath() {
            return parentPath;
        }

        public String toString() {
            if (svnDirEntry.getName().equals("")) {
                return svnDirEntry.getURL().toString();
            }
            return svnDirEntry.getName();
        }
        
        public void loadChildren(SVNRepository repository) {
        	 scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try {
                children = new ArrayList();
                if (svnDirEntry.getKind() == SVNNodeKind.DIR) {
                    String path = (!parentPath.equals("") ? parentPath + "/" : "/") + svnDirEntry.getName();
                    Collection svnChildren = repository.getDir(path, -1 , null , (Collection) null );
                    Iterator iter = svnChildren.iterator();
                    while (iter.hasNext()) {
                        SVNDirEntry child = (SVNDirEntry)iter.next();
                        SVNNode svnNodeChild = new SVNNode(child, path);
                        children.add(svnNodeChild);
                    }
                }
                scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                Collections.sort(children, new SVNNodeComparator());
            } catch (SVNException e) {
                throw new RuntimeException(e);
            }

        }
    }
    
    /*
     * Classe SVNNodeComparator
     */
    private static class SVNNodeComparator implements Comparator {

        public int compare(Object o1, Object o2) {
            SVNNode obj1 = (SVNNode)o1;
            SVNNode obj2 = (SVNNode)o2;
            if (obj1.getSVNDirEntry().getKind() == SVNNodeKind.DIR && obj2.getSVNDirEntry().getKind() == SVNNodeKind.FILE) {
                return -1;
            }
            else if (obj1.getSVNDirEntry().getKind() == SVNNodeKind.FILE && obj2.getSVNDirEntry().getKind() == SVNNodeKind.DIR) {
                return 1;
            }
            else {
                return obj1.getSVNDirEntry().getName().compareTo(obj2.getSVNDirEntry().getName());
            }
        }    
    }
   
    /*
     * Classe SVNTreeRenderer
     */
    
    private static class SVNTreeRendererWindows extends DefaultTreeCellRenderer  {   
    	public Component getTreeCellRendererComponent(JTree tree, 
    			Object value, 
        		boolean sel, 
        		boolean expanded, 
        		boolean leaf, 
        		int row, 
        		boolean hasFocus) {
    		  
            SVNNode node = (SVNNode) value;  
            super.getTreeCellRendererComponent(tree, node, sel, expanded, leaf, row, hasFocus);   
            
            
            setClosedIcon(new Icone().getFolderClosed());
            setOpenIcon(new Icone().getFolderOpen());
            setLeafIcon(new Icone().getFolderClosed());
            
            if(node.getSVNDirEntry().getPath().contains(".pdf")){
            	setIcon(new Icone().getPdf());
            }else if (node.getSVNDirEntry().getPath().contains(".xls")){
            	setIcon(new Icone().getXls());
            }else if (node.getSVNDirEntry().getPath().contains(".file")){
            	setIcon(new Icone().getFile());
            }else if (node.getSVNDirEntry().getPath().contains(".doc")){
            	setIcon(new Icone().getDoc());
            }else if (node.getSVNDirEntry().getPath().contains(".jar")){
            	setIcon(new Icone().getJar());
            }else if (node.getSVNDirEntry().getPath().contains(".java")){
            	setIcon(new Icone().getJava());
            }else if (node.getSVNDirEntry().getPath().contains(".ppt")){
            	setIcon(new Icone().getPpt());
            }
            else if (node.getSVNDirEntry().getPath().contains(".txt")){
            	setIcon(new Icone().getTxt());
            }
            else if (node.getSVNDirEntry().getPath().contains(".zip")){
            	setIcon(new Icone().getTxt());
            }

           
            return this;
        }
    }
    
    private static class SVNTreeRendererLinux extends DefaultTreeCellRenderer  {   
    	public Component getTreeCellRendererComponent(JTree tree, 
    			SVNNode value, 
        		boolean sel, 
        		boolean expanded, 
        		boolean leaf, 
        		int row, 
        		boolean hasFocus) {    		  
            
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);               
            SVNNode node =  value;  
            setClosedIcon(new Icone().getFolderClosed());
            setOpenIcon(new Icone().getFolderOpen());
            setLeafIcon(new Icone().getFolderClosed());
            return this;
        }
    }
}