package br.ufpa.spider.pe.view.management;

import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.Point;
import java.awt.Toolkit;

public class JDialogOpcaoCadastrarPolitica extends JDialog {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel jLabelEscolha;
	private JButton JButtonCancelar;
	private JButton JButtonProsseguir;
	private JRadioButton jRadioButtonEnviarPoliticaDefinida;
	private JRadioButton jRadioButtonDefinirNovaPolitica;
	private String radioSelecionado;  
	private boolean estado = false;
	/**
	 * Launch the application.
	 */

	public boolean isEstado() {
		return estado;
	}

	/**
	 * Create the frame.
	 */
	
	
	public JDialogOpcaoCadastrarPolitica() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JDialogOpcaoCadastrarPolitica.class.getResource("/br/ufpa/spider/pe/view/util/img/favicon_pe.png")));
		setModal(true);
		//setAlwaysOnTop();
		//setModal(true);
		setLocation(500, 500);
		//setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 462, 162);
		setTitle("Cadastrar Pol\u00EDtica Organizacional");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
		getContentPane().add(contentPane, BorderLayout.NORTH);
		jLabelEscolha = new JLabel("O que deseja fazer?");
		jLabelEscolha.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		
		JButtonCancelar = new JButton("Cancelar");
		JButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				estado = false;
			    dispose();
			}
		});
		JButtonProsseguir = new JButton("Prosseguir\r\n");
		JButtonProsseguir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
			    radioSelecionado = buttonGroup.getSelection().getActionCommand();
			    estado = true;
			    dispose();
			}
		});
		
		jRadioButtonEnviarPoliticaDefinida = new JRadioButton("Definir uma Pol\u00EDtica Organizacional");		
		jRadioButtonEnviarPoliticaDefinida.setMnemonic('2');
		buttonGroup.add(jRadioButtonEnviarPoliticaDefinida);
		jRadioButtonDefinirNovaPolitica = new JRadioButton("Selecionar uma Pol\u00EDtica Organizacional Definida");		
		jRadioButtonDefinirNovaPolitica.setMnemonic('1');
		buttonGroup.add(jRadioButtonDefinirNovaPolitica);
		
	    jRadioButtonEnviarPoliticaDefinida.setActionCommand("1");  
	    jRadioButtonDefinirNovaPolitica.setActionCommand("2");
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(jLabelEscolha)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(jRadioButtonDefinirNovaPolitica)
								.addComponent(jRadioButtonEnviarPoliticaDefinida))
							.addGap(10))))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(242, Short.MAX_VALUE)
					.addComponent(JButtonCancelar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(JButtonProsseguir)
					.addGap(26))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(jLabelEscolha)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jRadioButtonEnviarPoliticaDefinida)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jRadioButtonDefinirNovaPolitica)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(JButtonCancelar)
						.addComponent(JButtonProsseguir))
					.addGap(22))
		);
		
		
		contentPane.setLayout(gl_contentPane);
		
		
	}
	
	//HUEHE
	public int getSelecionado()
	{
		return Integer.parseInt(radioSelecionado);
	}
	//HUEHE
}
