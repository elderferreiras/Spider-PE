package br.ufpa.spider.pe.view.set;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.xml.stream.XMLStreamException;

import com.lowagie.text.xml.XmlParser;

import br.ufpa.spider.pe.model.set.XML;
import br.ufpa.spider.pe.model.set.dao.XMLDAO;
import br.ufpa.spider.pe.model.util.Converter;
import br.ufpa.spider.pe.model.util.XMLConverter;
import br.ufpa.spider.pe.model.util.XMLParse;
import br.ufpa.spider.pe.view.Progress;
import javax.swing.JProgressBar;

public class JPanelImportarXML extends JPanel {
	private JTextField jTextFieldCaminhoXML;
	XML xml;
	private JDialog _loadingDialog;
	 private JProgressBar _progress = new JProgressBar(0,0);  
	/**
	 * Create the panel.
	 */
	public JPanelImportarXML() {
		_loadingDialog = new JDialog();  
		_progress.setIndeterminate(true);
		_progress.setStringPainted(true);
		_loadingDialog.pack(); 
		_loadingDialog.setTitle("Importando...");
        _loadingDialog.getContentPane().add(_progress);  
		setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{30, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Importar Processos Modelados (Spider - PM)");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(JPanelImportarXML.class.getResource("/br/ufpa/spider/pe/img/spider-pm.png")));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 3;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblSelecionarProcessoxml = new JLabel("Selecionar Processo (.XML)");
		GridBagConstraints gbc_lblSelecionarProcessoxml = new GridBagConstraints();
		gbc_lblSelecionarProcessoxml.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelecionarProcessoxml.anchor = GridBagConstraints.EAST;
		gbc_lblSelecionarProcessoxml.gridx = 0;
		gbc_lblSelecionarProcessoxml.gridy = 2;
		add(lblSelecionarProcessoxml, gbc_lblSelecionarProcessoxml);
		
		jTextFieldCaminhoXML = new JTextField();
		GridBagConstraints gbc_jTextFieldCaminhoXML = new GridBagConstraints();
		gbc_jTextFieldCaminhoXML.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldCaminhoXML.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldCaminhoXML.gridx = 1;
		gbc_jTextFieldCaminhoXML.gridy = 2;
		add(jTextFieldCaminhoXML, gbc_jTextFieldCaminhoXML);
		jTextFieldCaminhoXML.setColumns(10);
		
		JButton jButtonLocalizar = new JButton("Localizar");
		jButtonLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonLocalizar();
			}
		});
		GridBagConstraints gbc_jButtonLocalizar = new GridBagConstraints();
		gbc_jButtonLocalizar.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonLocalizar.gridx = 2;
		gbc_jButtonLocalizar.gridy = 2;
		add(jButtonLocalizar, gbc_jButtonLocalizar);
		
		JButton jButtonImportar = new JButton("Importar");
		jButtonImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
							try {
								jButtonImportar();
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (XMLStreamException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
			}
		});
		GridBagConstraints gbc_jButtonImportar = new GridBagConstraints();
		gbc_jButtonImportar.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonImportar.gridx = 2;
		gbc_jButtonImportar.gridy = 3;
		add(jButtonImportar, gbc_jButtonImportar);
		if(XMLDAO.findAll() == null || XMLDAO.findAll().isEmpty()){
			xml = new XML();
			
		} else {
			for (XML xml : XMLDAO.findAll() ) {
				this.xml = xml;
				jTextFieldCaminhoXML.setText(xml.getCaminho());
			}
		}	
	}

	protected void jButtonImportar() throws FileNotFoundException, XMLStreamException {

		_loadingDialog.setVisible(true);
		_loadingDialog.pack();
		_loadingDialog.setLocationRelativeTo(null);
		Thread lerInfo = new Thread(new Runnable(){
			public void run() {
				xml.setCaminho(jTextFieldCaminhoXML.getText());
				XMLDAO.updateXML(xml);		
				_progress.setString("Importando Processo...");
				XMLConverter.setPathSource(jTextFieldCaminhoXML.getText());
				XMLConverter.save();
				for(File file : XMLConverter.getFiles()){
					Converter xmlParse = new Converter(file.getAbsolutePath());
					try {
						xmlParse.load();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				_progress.setString("Salvando no BD...");
				_loadingDialog.setVisible(false);
			JOptionPane.showMessageDialog(null, "Importa\u00E7\u00E3o Conclu\u00EDda!");
			
			}
			});
			lerInfo.start();	}

	protected void jButtonLocalizar() {
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			jTextFieldCaminhoXML.setText(fileChooser.getSelectedFile().getAbsolutePath());
	    } else {
	    	JOptionPane.showMessageDialog(null, "Nenhum arquivo foi selecionado.");
	    }
	}

}