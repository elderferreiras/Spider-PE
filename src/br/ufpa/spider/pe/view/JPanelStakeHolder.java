package br.ufpa.spider.pe.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.ufpa.spider.pe.controller.StakeHolderController;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.Stakeholder;
import br.ufpa.spider.pe.model.dao.ProcessoDAO;
import br.ufpa.spider.pe.model.dao.StakeholderDAO;
import br.ufpa.spider.pe.model.util.MyTableModel;

public class JPanelStakeHolder extends JPanel {
	private JTable table;
	private JTextField jTextFieldEmail;
	private JTextField jTextFieldStakeHolder;
	private DefaultTableModel tableModel;
	private Stakeholder stakeHolder;
	private boolean editar = false;
	private JTextArea jTextAreaDescricaoInteresse;
	private JScrollPane scrollPane;


	/**
	 * Create the panel.
	 */
	public JPanelStakeHolder() {
		setBorder(new EmptyBorder(10, 10, 0, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{70, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 2.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{70, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 5, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNomeDoStakerholder = new JLabel("Nome do Stakeholder");
		GridBagConstraints gbc_lblNomeDoStakerholder = new GridBagConstraints();
		gbc_lblNomeDoStakerholder.anchor = GridBagConstraints.WEST;
		gbc_lblNomeDoStakerholder.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomeDoStakerholder.gridx = 0;
		gbc_lblNomeDoStakerholder.gridy = 0;
		panel_1.add(lblNomeDoStakerholder, gbc_lblNomeDoStakerholder);
		
		jTextFieldStakeHolder = new JTextField();
		GridBagConstraints gbc_jTextFieldStakeHolder = new GridBagConstraints();
		gbc_jTextFieldStakeHolder.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldStakeHolder.insets = new Insets(0, 0, 5, 0);
		gbc_jTextFieldStakeHolder.gridx = 1;
		gbc_jTextFieldStakeHolder.gridy = 0;
		panel_1.add(jTextFieldStakeHolder, gbc_jTextFieldStakeHolder);
		jTextFieldStakeHolder.setColumns(10);
		
		JLabel lblDescrioDoInteresse = new JLabel("Descri\u00E7\u00E3o do Interesse");
		GridBagConstraints gbc_lblDescrioDoInteresse = new GridBagConstraints();
		gbc_lblDescrioDoInteresse.fill = GridBagConstraints.VERTICAL;
		gbc_lblDescrioDoInteresse.anchor = GridBagConstraints.WEST;
		gbc_lblDescrioDoInteresse.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescrioDoInteresse.gridx = 0;
		gbc_lblDescrioDoInteresse.gridy = 1;
		panel_1.add(lblDescrioDoInteresse, gbc_lblDescrioDoInteresse);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 1;
		panel_1.add(scrollPane_1, gbc_scrollPane_1);
		
		jTextAreaDescricaoInteresse = new JTextArea();
		jTextAreaDescricaoInteresse.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPane_1.setViewportView(jTextAreaDescricaoInteresse);
		jTextAreaDescricaoInteresse.setLineWrap(true);
		
		JLabel jTextFieldEmailContato = new JLabel("E-mail do contato");
		GridBagConstraints gbc_jTextFieldEmailContato = new GridBagConstraints();
		gbc_jTextFieldEmailContato.anchor = GridBagConstraints.WEST;
		gbc_jTextFieldEmailContato.insets = new Insets(0, 0, 0, 5);
		gbc_jTextFieldEmailContato.gridx = 0;
		gbc_jTextFieldEmailContato.gridy = 2;
		panel_1.add(jTextFieldEmailContato, gbc_jTextFieldEmailContato);
		
		jTextFieldEmail = new JTextField();
		GridBagConstraints gbc_jTextFieldEmail = new GridBagConstraints();
		gbc_jTextFieldEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldEmail.gridx = 1;
		gbc_jTextFieldEmail.gridy = 2;
		panel_1.add(jTextFieldEmail, gbc_jTextFieldEmail);
		jTextFieldEmail.setColumns(10);
		
		JButton jButtonAdicionar = new JButton("Adicionar");
		jButtonAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
					jButtonAdicionar(arg0);			
			}
		});
		GridBagConstraints gbc_jButtonAdicionar = new GridBagConstraints();
		gbc_jButtonAdicionar.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonAdicionar.gridx = 2;
		gbc_jButtonAdicionar.gridy = 1;
		panel.add(jButtonAdicionar, gbc_jButtonAdicionar);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		panel.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		Vector<String>  columns = new Vector<String>();
		 Vector<String>  data = new Vector<String>();
		 
		 columns .add("Nome");
		 columns .add("Interesse");
		 columns .add("Email");
			
		 tableModel = MyTableModel.modelStakeHolder(columns, data);
		 table.setModel(tableModel);
		 
		 table.getColumnModel().getColumn(0).setPreferredWidth(500);
		 table.getColumnModel().getColumn(1).setPreferredWidth(500);
		 table.getColumnModel().getColumn(2).setPreferredWidth(500);
		
		table.getTableHeader().setReorderingAllowed(false); 
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jTableMouseCliked(arg0);
			}
		});
		scrollPane.setViewportView(table);
		
		JButton jButtonExcluir = new JButton("Excluir");
		jButtonExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonExcluir(arg0);
			}
		});
		jButtonExcluir.setPreferredSize(new Dimension(80, 23));
		GridBagConstraints gbc_jButtonExcluir = new GridBagConstraints();
		gbc_jButtonExcluir.fill = GridBagConstraints.HORIZONTAL;
		gbc_jButtonExcluir.gridx = 2;
		gbc_jButtonExcluir.gridy = 3;
		panel.add(jButtonExcluir, gbc_jButtonExcluir);
		populaTabela();

	}


	protected void jButtonEditar() {
		editar = true;
		if(!(table.getSelectedRow()<0)){
			stakeHolder = (Stakeholder)table.getValueAt(table.getSelectedRow(), 0);					
			jTextAreaDescricaoInteresse.setText(stakeHolder.getDescInteresse());
			jTextFieldEmail.setText(stakeHolder.getEmail());
			jTextFieldStakeHolder.setText(stakeHolder.getNome());
		}
		else JOptionPane.showMessageDialog(null, "Selecione uma linha.");
	}


	protected void jTableMouseCliked(MouseEvent arg0) {
		if(arg0.getClickCount()==2){
			jButtonEditar();
		}
		
	}


	protected void jButtonExcluir(ActionEvent arg0) {
		int i = JOptionPane.showConfirmDialog(null ,"Deseja excluir a Aloca\u00e7\u00e3o selecionada?", 
				"Alocação",JOptionPane.YES_NO_OPTION); 
		if (i == JOptionPane.YES_OPTION ) { 
			Processo processo = ProcessoDAO.findByName(JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().toString());		
			stakeHolder = (Stakeholder)table.getValueAt(table.getSelectedRow(), 0);
			processo.getStakeHolder().remove(stakeHolder);
			
			StakeholderDAO.removeStakeholder(stakeHolder);
			ProcessoDAO.updateProcesso(processo);
			
			JOptionPane.showMessageDialog(null, "StakeHolder Exclu\u00eddo");
			stakeHolder = null;
			populaTabela();
		}
	}


	private void populaTabela() {
		tableModel.setNumRows(0);
		Processo processo = ProcessoDAO.findByName(JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().toString());
		for (Stakeholder stakeHolder : processo.getStakeHolder()) {
			tableModel.addRow(new Object[]{stakeHolder, stakeHolder.getDescInteresse(), stakeHolder.getEmail()});
		}

		this.repaint();
		table.repaint();
		
	}


	protected void jButtonAdicionar(ActionEvent arg0) {		
			if(editar){
				stakeHolder = (Stakeholder)table.getValueAt(table.getSelectedRow(), 0);		

				stakeHolder.setDescInteresse(jTextAreaDescricaoInteresse.getText());
				stakeHolder.setEmail(jTextFieldEmail.getText());
				stakeHolder.setNome(jTextFieldStakeHolder.getText());
				
				StakeHolderController.saveStakeHolder(stakeHolder);
			} else {
				stakeHolder = new Stakeholder();
				stakeHolder.setDescInteresse(jTextAreaDescricaoInteresse.getText());
				stakeHolder.setEmail(jTextFieldEmail.getText());
				stakeHolder.setNome(jTextFieldStakeHolder.getText());			
				Processo processo = ProcessoDAO.findByName(JDialog_Spider_Login.getInstance().Spider_PE_Home.getProcesso().toString());
				processo.getStakeHolder().add(stakeHolder);
				stakeHolder.setProcesso(processo);				
				StakeHolderController.saveStakeHolder(stakeHolder);
				ProcessoDAO.updateProcesso(processo);
			}
				
			
					
			jTextAreaDescricaoInteresse.setText("");
			jTextFieldEmail.setText("");
			jTextFieldStakeHolder.setText("");	
			
			stakeHolder = null;		
			editar = false;
			
			populaTabela();
	}
	

}
