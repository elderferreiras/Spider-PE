package br.ufpa.spider.pe.view.management;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import br.ufpa.spider.pe.model.set.dao.UsuarioDAO;
import java.awt.Insets;
import javax.swing.JProgressBar;

public class JDialogWelcome extends JFrame {
	static JDialogWelcome home = null;
	private static JPanel panel;
	public static JProgressBar progressBar;
	
	public JDialogWelcome() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JDialogWelcome.class.getResource("/br/ufpa/spider/pe/view/util/img/favicon_pe.png")));
		setTitle("Spider-PE");
		try {
			String nomeSO = System.getProperty("os.name");
			if(nomeSO.contains("Linux")){
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			} else if(nomeSO.contains("Windows")) {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}
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
		
		getContentPane().setBackground(Color.WHITE);
		this.setUndecorated(true); 
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel label = new JLabel("");
		label.setBackground(Color.WHITE);
		label.setBorder(new EmptyBorder(10, 10, 10, 10));
		label.setIcon(new ImageIcon(JDialogWelcome.class.getResource("/br/ufpa/spider/pe/view/util/img/splash-screen.png")));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);
		
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setStringPainted(true);
		progressBar.setString("Carregando Banco de Dados...");
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 1;
		panel.add(progressBar, gbc_progressBar);

	}
	
	public synchronized static JDialogWelcome getInstance() {
		if (home == null){
			home = new JDialogWelcome();
			home.setSize(new Dimension(712, 377));
			home.setLocationRelativeTo(null);
			}
		
		return home;
	}
	
	public static void removeProgressBar(){
		panel.remove(progressBar);	
	}

	public static void setStringProgressBar(String string) {
		progressBar.setString(string);
	}

}
