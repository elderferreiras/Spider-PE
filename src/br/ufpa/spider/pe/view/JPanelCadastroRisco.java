package br.ufpa.spider.pe.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.ufpa.spider.pe.controller.RiscoController;
import br.ufpa.spider.pe.model.Atividade;
import br.ufpa.spider.pe.model.Campo;
import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Humano;
import br.ufpa.spider.pe.model.Iteracao;
import br.ufpa.spider.pe.model.Papel;
import br.ufpa.spider.pe.model.Risco;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.Tipo;
import br.ufpa.spider.pe.model.dao.CampoDAO;
import br.ufpa.spider.pe.model.dao.HumanoDAO;
import br.ufpa.spider.pe.model.dao.ProcessoDAO;
import br.ufpa.spider.pe.model.dao.RiscoDAO;
import br.ufpa.spider.pe.model.dao.TipoDAO;
import br.ufpa.spider.pe.model.util.MyTableModel;
import java.awt.Font;

public class JPanelCadastroRisco extends JPanel {
	private JTable table;
	private JTable table_1;
	private JTextField jTextFieldContigencia;
	private JTextField jTextFieldMitigacao;
	private JTextField jTextFieldSeveridade;
	private JTextField jTextFieldImpacto;
	private JTextField jTextFieldProbabilidade;
	private JTextField jTextFieldNomeRisco;
	private DefaultTableModel tableModel;
	private JTextArea jTextAreaDescricao;
	private JComboBox jComboBoxTipoRisco;
	private JComboBox jComboBoxResponsavel;

	/**
	 * Create the panel.
	 */
	public JPanelCadastroRisco() {
		setBorder(new EmptyBorder(15, 15, 5, 15));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel_1 = new JLabel("Nome do Risco");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		jTextFieldNomeRisco = new JTextField();
		GridBagConstraints gbc_jTextFieldNomeRisco = new GridBagConstraints();
		gbc_jTextFieldNomeRisco.insets = new Insets(0, 0, 5, 0);
		gbc_jTextFieldNomeRisco.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldNomeRisco.gridx = 1;
		gbc_jTextFieldNomeRisco.gridy = 0;
		add(jTextFieldNomeRisco, gbc_jTextFieldNomeRisco);
		jTextFieldNomeRisco.setColumns(10);
		
		JLabel lblTipoDeRisco = new JLabel("Tipo de Risco");
		GridBagConstraints gbc_lblTipoDeRisco = new GridBagConstraints();
		gbc_lblTipoDeRisco.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTipoDeRisco.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoDeRisco.gridx = 0;
		gbc_lblTipoDeRisco.gridy = 1;
		add(lblTipoDeRisco, gbc_lblTipoDeRisco);
		
		jComboBoxTipoRisco = new JComboBox();
		jComboBoxTipoRisco.addFocusListener(new FocusListener(){  

			@Override 
		    public void focusGained(FocusEvent arg0) {  
				populaComboBox();
		    }  

			@Override
		    public void focusLost(FocusEvent arg0){               
		        //a��o desejada quando perde o foco  
		    }
		      
		});  
		GridBagConstraints gbc_jComboBoxTipoRisco = new GridBagConstraints();
		gbc_jComboBoxTipoRisco.insets = new Insets(0, 0, 5, 0);
		gbc_jComboBoxTipoRisco.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxTipoRisco.gridx = 1;
		gbc_jComboBoxTipoRisco.gridy = 1;
		add(jComboBoxTipoRisco, gbc_jComboBoxTipoRisco);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		GridBagConstraints gbc_lblDescrio = new GridBagConstraints();
		gbc_lblDescrio.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDescrio.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescrio.gridx = 0;
		gbc_lblDescrio.gridy = 2;
		add(lblDescrio, gbc_lblDescrio);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		jTextAreaDescricao = new JTextArea();
		jTextAreaDescricao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(jTextAreaDescricao);
		jTextAreaDescricao.setLineWrap(true);
		
		JLabel lblProbabilidade = new JLabel("Probabilidade");
		GridBagConstraints gbc_lblProbabilidade = new GridBagConstraints();
		gbc_lblProbabilidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblProbabilidade.insets = new Insets(0, 0, 5, 5);
		gbc_lblProbabilidade.gridx = 0;
		gbc_lblProbabilidade.gridy = 3;
		add(lblProbabilidade, gbc_lblProbabilidade);
		
		jTextFieldProbabilidade = new JTextField();
		GridBagConstraints gbc_jTextFieldProbabilidade = new GridBagConstraints();
		gbc_jTextFieldProbabilidade.insets = new Insets(0, 0, 5, 0);
		gbc_jTextFieldProbabilidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldProbabilidade.gridx = 1;
		gbc_jTextFieldProbabilidade.gridy = 3;
		add(jTextFieldProbabilidade, gbc_jTextFieldProbabilidade);
		jTextFieldProbabilidade.setColumns(10);
		
		JLabel lblImpacto = new JLabel("Impacto");
		GridBagConstraints gbc_lblImpacto = new GridBagConstraints();
		gbc_lblImpacto.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblImpacto.insets = new Insets(0, 0, 5, 5);
		gbc_lblImpacto.gridx = 0;
		gbc_lblImpacto.gridy = 4;
		add(lblImpacto, gbc_lblImpacto);
		
		jTextFieldImpacto = new JTextField();
		GridBagConstraints gbc_jTextFieldImpacto = new GridBagConstraints();
		gbc_jTextFieldImpacto.insets = new Insets(0, 0, 5, 0);
		gbc_jTextFieldImpacto.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldImpacto.gridx = 1;
		gbc_jTextFieldImpacto.gridy = 4;
		add(jTextFieldImpacto, gbc_jTextFieldImpacto);
		jTextFieldImpacto.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Severidade");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 5;
		add(lblNewLabel, gbc_lblNewLabel);
		
		jTextFieldSeveridade = new JTextField();
		GridBagConstraints gbc_jTextFieldSeveridade = new GridBagConstraints();
		gbc_jTextFieldSeveridade.insets = new Insets(0, 0, 5, 0);
		gbc_jTextFieldSeveridade.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldSeveridade.gridx = 1;
		gbc_jTextFieldSeveridade.gridy = 5;
		add(jTextFieldSeveridade, gbc_jTextFieldSeveridade);
		jTextFieldSeveridade.setColumns(10);
		
		JLabel lblMitigao = new JLabel("Mitiga\u00E7\u00E3o");
		GridBagConstraints gbc_lblMitigao = new GridBagConstraints();
		gbc_lblMitigao.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMitigao.insets = new Insets(0, 0, 5, 5);
		gbc_lblMitigao.gridx = 0;
		gbc_lblMitigao.gridy = 6;
		add(lblMitigao, gbc_lblMitigao);
		
		jTextFieldMitigacao = new JTextField();
		GridBagConstraints gbc_jTextFieldMitigacao = new GridBagConstraints();
		gbc_jTextFieldMitigacao.insets = new Insets(0, 0, 5, 0);
		gbc_jTextFieldMitigacao.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldMitigacao.gridx = 1;
		gbc_jTextFieldMitigacao.gridy = 6;
		add(jTextFieldMitigacao, gbc_jTextFieldMitigacao);
		jTextFieldMitigacao.setColumns(10);
		
		JLabel lblContigncia = new JLabel("Contig\u00EAncia");
		GridBagConstraints gbc_lblContigncia = new GridBagConstraints();
		gbc_lblContigncia.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblContigncia.insets = new Insets(0, 0, 5, 5);
		gbc_lblContigncia.gridx = 0;
		gbc_lblContigncia.gridy = 7;
		add(lblContigncia, gbc_lblContigncia);
		
		jTextFieldContigencia = new JTextField();
		GridBagConstraints gbc_jTextFieldContigencia = new GridBagConstraints();
		gbc_jTextFieldContigencia.insets = new Insets(0, 0, 5, 0);
		gbc_jTextFieldContigencia.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldContigencia.gridx = 1;
		gbc_jTextFieldContigencia.gridy = 7;
		add(jTextFieldContigencia, gbc_jTextFieldContigencia);
		jTextFieldContigencia.setColumns(10);
		
		JLabel lblResponsvel = new JLabel("Respons\u00E1vel");
		GridBagConstraints gbc_lblResponsvel = new GridBagConstraints();
		gbc_lblResponsvel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblResponsvel.insets = new Insets(0, 0, 5, 5);
		gbc_lblResponsvel.gridx = 0;
		gbc_lblResponsvel.gridy = 8;
		add(lblResponsvel, gbc_lblResponsvel);
		
		jComboBoxResponsavel = new JComboBox();
		GridBagConstraints gbc_jComboBoxResponsavel = new GridBagConstraints();
		gbc_jComboBoxResponsavel.insets = new Insets(0, 0, 5, 0);
		gbc_jComboBoxResponsavel.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxResponsavel.gridx = 1;
		gbc_jComboBoxResponsavel.gridy = 8;
		add(jComboBoxResponsavel, gbc_jComboBoxResponsavel);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 9;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JButtonLimparActionPerformed(arg0);
			}
		});
		btnLimpar.setPreferredSize(new Dimension(81, 23));
		GridBagConstraints gbc_btnLimpar = new GridBagConstraints();
		gbc_btnLimpar.anchor = GridBagConstraints.EAST;
		gbc_btnLimpar.fill = GridBagConstraints.VERTICAL;
		gbc_btnLimpar.insets = new Insets(0, 0, 0, 5);
		gbc_btnLimpar.gridx = 0;
		gbc_btnLimpar.gridy = 0;
		panel.add(btnLimpar, gbc_btnLimpar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButtonExcluirActionPerformed(e);
			}
		});
		btnExcluir.setPreferredSize(new Dimension(81, 23));
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.fill = GridBagConstraints.BOTH;
		gbc_btnExcluir.insets = new Insets(0, 0, 0, 5);
		gbc_btnExcluir.gridx = 1;
		gbc_btnExcluir.gridy = 0;
		panel.add(btnExcluir, gbc_btnExcluir);
		
		JButton jButtonCadastrar = new JButton("Cadastrar");
		GridBagConstraints gbc_jButtonCadastrar = new GridBagConstraints();
		gbc_jButtonCadastrar.fill = GridBagConstraints.BOTH;
		gbc_jButtonCadastrar.gridx = 2;
		gbc_jButtonCadastrar.gridy = 0;
		panel.add(jButtonCadastrar, gbc_jButtonCadastrar);
		jButtonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonCadastrar(arg0);
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 10;
		add(scrollPane_1, gbc_scrollPane_1);
		table_1 = new JTable();		
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jTableMouseCliked(arg0);
			}
		});
		table_1.setPreferredScrollableViewportSize(new Dimension(700, 16));	
		 Vector<String>  columns = new Vector<String>();
		 Vector<String>  data = new Vector<String>();
		 columns .add("Risco");
		 columns.add("Tipo");
	     columns.add("Probabilidade"); 
	     columns.add("Impacto");
	     columns.add("Respons�vel");
			
		 tableModel = MyTableModel.modelCadastroRisco(columns, data);
		 table_1.setModel(tableModel);
		 table_1.getColumnModel().getColumn(0).setPreferredWidth(100);
		 table_1.getColumnModel().getColumn(1).setPreferredWidth(50);
		 table_1.getColumnModel().getColumn(2).setPreferredWidth(50);
		 table_1.getColumnModel().getColumn(3).setPreferredWidth(50);
		 table_1.getColumnModel().getColumn(4).setPreferredWidth(100);
		 table_1.getTableHeader().setReorderingAllowed(false); 
		scrollPane_1.setViewportView(table_1);
		
		populaComboBox();
		populaTabela();	
		addComponentListener(new ComponentListener() {
				
				@Override
				public void componentShown(ComponentEvent arg0) {
					populaTabela();
					
				}
				
				@Override
				public void componentResized(ComponentEvent arg0) {
				}
				
				@Override
				public void componentMoved(ComponentEvent arg0) {
					
				}
				
				@Override
				public void componentHidden(ComponentEvent arg0) {
					
				}
			});
	}
	
	protected void JButtonExcluirActionPerformed(ActionEvent e) {	
		int i = JOptionPane.showConfirmDialog(null ,"Deseja excluir o Risco selecionado?", 
				"Risco",JOptionPane.YES_NO_OPTION);  
				if (i == JOptionPane.YES_OPTION ) {					
					if(table_1.getSelectedRow()>=0){
						try{
							Risco risco = RiscoDAO.findById(((Risco) table_1.getValueAt(table_1.getSelectedRow(), 0)).getId());		
							risco.getHumano().getRisco().remove(risco);
							RiscoDAO.removeRisco(risco);	
							JOptionPane.showMessageDialog(null, "Risco Exclu\u00eddo");
							populaTabela();		
						}
						catch (Exception es) {
							JOptionPane.showMessageDialog(null, "Erro na hora de excluir");
						}
						limparCampos();
					}  
				}
		
	}

	protected void JButtonLimparActionPerformed(ActionEvent arg0) {	
		limparCampos();		
	}
	
	private void limparCampos(){
		jTextFieldNomeRisco.setText("");
		jTextAreaDescricao.setText("");
		jTextFieldProbabilidade.setText("");
		jTextFieldImpacto.setText("");
		jTextFieldSeveridade.setText("");
		jTextFieldMitigacao.setText("");
		jTextFieldContigencia.setText("");
		jComboBoxResponsavel.setSelectedIndex(0);
		jComboBoxTipoRisco.setSelectedIndex(0);
		table_1.getSelectionModel().clearSelection();
	}

	private void preencheCampos(int selectedRow) {
		Risco risco = RiscoDAO.findById(((Risco) tableModel.getValueAt(selectedRow, 0)).getId());
		jTextFieldNomeRisco.setText(risco.getNome());
		jTextAreaDescricao.setText(risco.getDescricao());
		jTextFieldProbabilidade.setText(risco.getProbabilidade());
		jTextFieldImpacto.setText(risco.getImpacto());
		jTextFieldSeveridade.setText(risco.getSeveridade());
		jTextFieldMitigacao.setText(risco.getMitigacao());
		jTextFieldContigencia.setText(risco.getContingencia());
		jComboBoxResponsavel.setSelectedItem(risco.getHumano());
		jComboBoxTipoRisco.setSelectedItem(TipoDAO.findByName(risco.getTipoRisco()));
	}

	private void populaComboBox() {
		jComboBoxResponsavel.removeAllItems();
		jComboBoxTipoRisco.removeAllItems();
		
		jComboBoxTipoRisco.addItem("Selecione...");
		jComboBoxResponsavel.addItem("Selecione...");
		
		List<Campo> campos = CampoDAO.findAll();
		
		if(campos.size()!=0 || campos != null){
			for (Campo campo : campos) {
				if(campo.getNome().equals("Tipo de Risco")){
					for(Tipo tipo:campo.getTipo()){
						jComboBoxTipoRisco.addItem(tipo);
					}
				}
			}
		} 
				
		  List<Humano> humanos = consultaHumanos();
				
		  if(humanos.size()!=0 || humanos != null){
				for (Humano humano : humanos) {
						jComboBoxResponsavel.addItem(humano);
					}				
			}
}
	


	protected void jTableMouseCliked(MouseEvent arg0) {	
		if(arg0.getClickCount()==2){
			if(table_1.getSelectedRow()>=0)
				preencheCampos(table_1.getSelectedRow());		
		}
	}

	protected void jButtonCadastrar(ActionEvent arg0) {	
		if(table_1.getSelectedRow()>=0){
			Risco risco = RiscoDAO.findById(((Risco) tableModel.getValueAt(table_1.getSelectedRow(), 0)).getId());
			risco.setNome(jTextFieldNomeRisco.getText());
			risco.setDescricao(jTextAreaDescricao.getText());
			risco.setProbabilidade(jTextFieldProbabilidade.getText());
			risco.setImpacto(jTextFieldImpacto.getText());
			risco.setSeveridade(jTextFieldSeveridade.getText());
			risco.setMitigacao(jTextFieldMitigacao.getText());
			risco.setContingencia(jTextFieldContigencia.getText());
			risco.setHumano((Humano) jComboBoxResponsavel.getSelectedItem());
			Tipo tipo = (Tipo) jComboBoxTipoRisco.getSelectedItem();
			risco.setTipoRisco(tipo.getNome());
			RiscoController.saveRisco(risco);	
			limparCampos();
		}			
		else if (table_1.getSelectedRow()<0){
			if(!jComboBoxTipoRisco.getSelectedItem().equals("Selecione..."))
			{
				if(!jComboBoxResponsavel.getSelectedItem().equals("Selecione...")){
					Risco risco = new Risco();		
					risco.setNome(jTextFieldNomeRisco.getText());
					risco.setDescricao(jTextAreaDescricao.getText());
					risco.setProbabilidade(jTextFieldProbabilidade.getText());
					risco.setImpacto(jTextFieldImpacto.getText());
					risco.setSeveridade(jTextFieldSeveridade.getText());
					risco.setMitigacao(jTextFieldMitigacao.getText());
					risco.setContingencia(jTextFieldContigencia.getText());
					risco.setHumano((Humano) jComboBoxResponsavel.getSelectedItem());
					risco.setTipoRisco(((Tipo) jComboBoxTipoRisco.getSelectedItem()).getNome());					
					RiscoController.saveRisco(risco);	
					((Humano) jComboBoxResponsavel.getSelectedItem()).getRiscos().add(risco);

					limparCampos();
				}	else  JOptionPane.showMessageDialog(null, "Selecione...");	
				
			} else JOptionPane.showMessageDialog(null, "Selecione...");			
		}
		populaTabela();		
	}
	

	private void populaTabela() {		
		List<Risco> riscos =  consultaRiscos();
		if(riscos.size()!=0 || riscos != null){
			tableModel.setNumRows(0);
			for (Risco risco :riscos) 
				{
				  tableModel.addRow(new Object[]{ risco, risco.getTipoRisco(), risco.getProbabilidade(), risco.getImpacto(), risco.getHumano().getNome()});
				}
			}		
	} 
	
	private List<Humano> consultaHumanos() {ArrayList<Humano> humanos= new ArrayList<Humano>();	
		for (Humano humano : HumanoDAO.findAll()) {
			 humanos.add(humano);
		}	  
		return humanos;
	}
	
	private List<Risco> consultaRiscos() {
		ArrayList<Risco> riscos= new ArrayList<Risco>();
		for(Humano humano: consultaHumanos()){
			for(Risco risco: humano.getRiscos()){
				riscos.add(risco);
			}
		}		
		return riscos;
	}
	


}
