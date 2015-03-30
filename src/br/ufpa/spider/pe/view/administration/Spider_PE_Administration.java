package br.ufpa.spider.pe.view.administration;

import java.awt.Cursor;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import br.ufpa.spider.pe.model.Atividade;
import br.ufpa.spider.pe.model.Campo;
import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.Iteracao;
import br.ufpa.spider.pe.model.Marco;
import br.ufpa.spider.pe.model.Papel;
import br.ufpa.spider.pe.model.Processo;
import br.ufpa.spider.pe.model.Software;
import br.ufpa.spider.pe.model.Tarefa;
import br.ufpa.spider.pe.model.Tipo;
import br.ufpa.spider.pe.model.dao.AtividadeDAO;
import br.ufpa.spider.pe.model.dao.CampoDAO;
import br.ufpa.spider.pe.model.dao.FaseDAO;
import br.ufpa.spider.pe.model.dao.IteracaoDAO;
import br.ufpa.spider.pe.model.dao.MarcoDAO;
import br.ufpa.spider.pe.model.dao.PapelDAO;
import br.ufpa.spider.pe.model.dao.ProcessoDAO;
import br.ufpa.spider.pe.model.dao.SoftwareDAO;
import br.ufpa.spider.pe.model.dao.TarefaDAO;
import br.ufpa.spider.pe.model.dao.TipoDAO;
import br.ufpa.spider.pe.model.set.Usuario;
import br.ufpa.spider.pe.model.util.ProcessManagerController;
import br.ufpa.spider.pe.view.Spider_PE_Home;
import br.ufpa.spider.pe.view.management.JDialogGerenciarHumanos;
import br.ufpa.spider.pe.view.management.JDialogWelcome;
import br.ufpa.spider.pe.view.util.Icone;
import br.ufpa.spider.pe.view.util.TreeMouseListenerAdmin;

import javax.swing.JCheckBoxMenuItem;

public class Spider_PE_Administration extends JFrame {
	private JSplitPane jSplitPane;
	private JPanel jPanelBackGround;
	private JMenuBar jMenuBarHome;
	private JMenu jMenuArquivo;
	private JMenu jMenuVisoes;
	private JMenu jMenuAjuda;
	private JPanel jPanelNorte;
	private JToolBar jToolBarAcessivel;
	private JButton jButtonHome;
	private JButton jButtonLogout;
	private JLabel jLabelLogoSpiderPe;
	private JSplitPane splitPane;
	private JSplitPane jSplitPanel;
	private JPanel jPanelGestao;
	private JPanel jPanelProcesso;
	private JPanel jPanelLeste;
	private JFrame jFrameTipos;
	private Iteracao iteracao;
	private TreeMouseListenerAdmin treeMouseListenerAdmin;
	
	public Spider_PE_Administration() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Spider_PE_Administration.class.getResource("/br/ufpa/spider/pe/view/util/img/favicon_pe.png")));
		initComponents();
		
		preencheCampos();
		
	}
	
	private void criaSoftwares() {
		Software software1 = new Software();
		software1.setDescricao("descricao");
		software1.setObjetivos("objetivos");
		software1.setInformacoesAdicionais("informacoes add");
		software1.setNome("cr me gusta");
		SoftwareDAO.createSoftware(software1);
		
		Software software2 = new Software();
		software2.setDescricao("descricao");
		software2.setObjetivos("objetivos");
		software2.setInformacoesAdicionais("informacoes add");
		software2.setNome("office");
		SoftwareDAO.createSoftware(software2);
		
		Software software3 = new Software();
		software3.setDescricao("descricao");
		software3.setObjetivos("objetivos");
		software3.setInformacoesAdicionais("informacoes add");
		software3.setNome("jdownloader");
		SoftwareDAO.createSoftware(software3);
		
		Software software4 = new Software();
		software4.setDescricao("descricao");
		software4.setObjetivos("objetivos");
		software4.setInformacoesAdicionais("informacoes add");
		software4.setNome("diablo");
		SoftwareDAO.createSoftware(software4);
		
		Software software5 = new Software();
		software5.setDescricao("descricao");
		software5.setObjetivos("objetivos");
		software5.setInformacoesAdicionais("informacoes add");
		software5.setNome("cs source");
		SoftwareDAO.createSoftware(software5);
		
	}

	private void preencheCampos() {
	 if(CampoDAO.findAll().size()== 0 || CampoDAO.findAll() ==  null){
			//TODO Casdastrar os campos estaticos
			Campo campo1 = new Campo();
			campo1.setNome("Unidade de Medida (Esfor\u00E7o)");
			CampoDAO.createCampo(campo1);
			
			Campo campo2 = new Campo();
			campo2.setNome("Unidade de Medida (Carga Hor\u00E1ria)");
			CampoDAO.createCampo(campo2);
			
			Tipo tipo = new Tipo();
			tipo.setNome("Segundo(s)");
			tipo.setCampo(campo2);
			TipoDAO.createTipo(tipo);
			
			Tipo tipo2 = new Tipo();
			tipo2.setNome(ProcessManagerController.MINUTOS);
			tipo2.setFatorConversao(60);
			tipo2.setCampo(campo2);
			TipoDAO.createTipo(tipo2);
			
			Tipo tipo3 = new Tipo();
			tipo3.setNome(ProcessManagerController.HORAS);
			tipo3.setFatorConversao(60);
			tipo3.setCampo(campo2);
			TipoDAO.createTipo(tipo3);
			
			Tipo tipo4 = new Tipo();
			tipo4.setNome(ProcessManagerController.DIAS);
			tipo4.setFatorConversao(8);
			tipo4.setCampo(campo2);
			TipoDAO.createTipo(tipo4);
			
			Tipo tipo5 = new Tipo();
			tipo5.setNome(ProcessManagerController.MESES);
			tipo5.setFatorConversao(22);
			tipo5.setCampo(campo2);
			TipoDAO.createTipo(tipo5);
			
			Tipo tipo6 = new Tipo();
			tipo6.setNome(ProcessManagerController.ANOS);
			tipo6.setFatorConversao(12);
			tipo6.setCampo(campo2);
			TipoDAO.createTipo(tipo6);
			
			campo2.getTipo().add(tipo);
			campo2.getTipo().add(tipo2);
			campo2.getTipo().add(tipo3);
			campo2.getTipo().add(tipo4);
			campo2.getTipo().add(tipo5);
			campo2.getTipo().add(tipo6);
			
			
			Campo campo3 = new Campo();			
			campo3.setNome("Tipo de Risco");
			CampoDAO.createCampo(campo3);
			
			Campo campo4 = new Campo();
			campo4.setNome("Pap\u00E9is Exercidos");
			CampoDAO.createCampo(campo4);
			
			Campo campo5 = new Campo();
			campo5.setNome("Forma\u00E7\u00E3o Acad\u00EAmica");
			CampoDAO.createCampo(campo5);
			
			Campo campo6 = new Campo();
			campo6.setNome("Perfil de Treinamento");
			CampoDAO.createCampo(campo6);
			
			Campo campo7 = new Campo();
			campo7.setNome("Tipo de Treinamento");
			CampoDAO.createCampo(campo7);
			
			Campo campo8 = new Campo();
			campo8.setNome("Forma de Comunica\u00E7\u00E3o");
			CampoDAO.createCampo(campo8);
						
			Campo campo10 = new Campo();
			campo10.setNome("\u00C1rea de Processo");
			CampoDAO.createCampo(campo10);
			
		}
		
	}
	
	public void pseudoScript() {
		
		/*PROCESSO*/
		Processo processo1 = new Processo();
		processo1.setNome("processo_1");
		ProcessoDAO.createProcesso(processo1);
		
		Processo processo2 = new Processo();
		processo2.setNome("processo_2");
		ProcessoDAO.createProcesso(processo2);
		
		/*FASE*/
		Fase fase1 = new Fase();
		fase1.setNome("fase_1");		
		fase1.setProcesso(processo1);		
		FaseDAO.createFase(fase1);
		
		Fase fase2 = new Fase();
		fase2.setNome("fase_2");		
		fase2.setProcesso(processo2);		
		FaseDAO.createFase(fase2);
		
		/*MARCO*/
		Marco marco1 = new Marco();
		marco1.setNome("marco_1");
		marco1.setFase(fase1);
		MarcoDAO.createMarco(marco1);
		
		Marco marco2 = new Marco();
		marco2.setNome("marco_2");
		marco2.setFase(fase2);
		MarcoDAO.createMarco(marco2);
		
		/*ITERACAO*/
		Iteracao iteracao1 = new Iteracao();
		iteracao1.setNome("iteracao_1");
		iteracao1.setFase(fase1);				
		IteracaoDAO.createIteracao(iteracao1);
		
		Iteracao iteracao2 = new Iteracao();
		iteracao2.setNome("iteracao_2");
		iteracao2.setFase(fase1);				
		IteracaoDAO.createIteracao(iteracao2);
		
		Iteracao iteracao3 = new Iteracao();
		iteracao3.setNome("iteracao_3");
		iteracao3.setFase(fase2);				
		IteracaoDAO.createIteracao(iteracao3);
		
		Iteracao iteracao4 = new Iteracao();
		iteracao4.setNome("iteracao_4");
		iteracao4.setFase(fase2);				
		IteracaoDAO.createIteracao(iteracao4);
		
		/*TAREFA*/
		Tarefa tarefa1 = new Tarefa();
		tarefa1.setNome("tarefa1");
		tarefa1.setCargaHoraria("201");
		tarefa1.setFimPrevisto("11/01/2012");
		tarefa1.setInicioPrevisto("20/12/2011");
		tarefa1.setQtdEsforco("25");
		tarefa1.setIteracao(iteracao1);
		
		Tarefa tarefa2 = new Tarefa();
		tarefa2.setNome("tarefa2");
		tarefa2.setCargaHoraria("452");
		tarefa2.setFimPrevisto("21/07/2012");
		tarefa2.setInicioPrevisto("03/12/2011");
		tarefa2.setQtdEsforco("13");
		tarefa2.setIteracao(iteracao1);
		
		Tarefa tarefa3 = new Tarefa();
		tarefa3.setNome("tarefa3");
		tarefa3.setCargaHoraria("1113");
		tarefa3.setFimPrevisto("29/01/2012");
		tarefa3.setInicioPrevisto("05/01/2012");
		tarefa3.setQtdEsforco("50");
		tarefa3.setIteracao(iteracao1);

		Tarefa tarefa4 = new Tarefa();
		tarefa4.setNome("tarefa4");
		tarefa4.setCargaHoraria("013");
		tarefa4.setFimPrevisto("25/01/2011");
		tarefa4.setInicioPrevisto("05/01/2011");
		tarefa4.setQtdEsforco("100");
		tarefa4.setIteracao(iteracao2);
		
		Tarefa tarefa5 = new Tarefa();
		tarefa5.setNome("tarefa5");
		tarefa5.setCargaHoraria("011");
		tarefa5.setFimPrevisto("26/02/2012");
		tarefa5.setInicioPrevisto("25/02/2012");
		tarefa5.setQtdEsforco("50");
		tarefa5.setIteracao(iteracao2);
		
		Tarefa tarefa6 = new Tarefa();
		tarefa6.setNome("tarefa6");
		tarefa6.setCargaHoraria("201");
		tarefa6.setFimPrevisto("11/01/2012");
		tarefa6.setInicioPrevisto("20/12/2011");
		tarefa6.setQtdEsforco("25");
		tarefa6.setIteracao(iteracao3);
		
		Tarefa tarefa7 = new Tarefa();
		tarefa7.setNome("tarefa7");
		tarefa7.setCargaHoraria("452");
		tarefa7.setFimPrevisto("21/07/2012");
		tarefa7.setInicioPrevisto("03/12/2011");
		tarefa7.setQtdEsforco("13");
		tarefa7.setIteracao(iteracao3);
		
		Tarefa tarefa8 = new Tarefa();
		tarefa8.setNome("tarefa8");
		tarefa8.setCargaHoraria("1113");
		tarefa8.setFimPrevisto("29/01/2012");
		tarefa8.setInicioPrevisto("05/01/2012");
		tarefa8.setQtdEsforco("50");
		tarefa8.setIteracao(iteracao3);

		Tarefa tarefa9 = new Tarefa();
		tarefa9.setNome("tarefa9");
		tarefa9.setCargaHoraria("013");
		tarefa9.setFimPrevisto("25/01/2011");
		tarefa9.setInicioPrevisto("05/01/2011");
		tarefa9.setQtdEsforco("100");
		tarefa9.setIteracao(iteracao4);
		
		Tarefa tarefa10 = new Tarefa();
		tarefa10.setNome("tarefa10");
		tarefa10.setCargaHoraria("011");
		tarefa10.setFimPrevisto("26/02/2012");
		tarefa10.setInicioPrevisto("25/02/2012");
		tarefa10.setQtdEsforco("50");
		tarefa10.setIteracao(iteracao4);
		
		
	
		TarefaDAO.createTarefa(tarefa1);
		TarefaDAO.createTarefa(tarefa2);
		TarefaDAO.createTarefa(tarefa3);
		TarefaDAO.createTarefa(tarefa4);
		TarefaDAO.createTarefa(tarefa5);
		TarefaDAO.createTarefa(tarefa6);
		TarefaDAO.createTarefa(tarefa7);
		TarefaDAO.createTarefa(tarefa8);
		TarefaDAO.createTarefa(tarefa9);
		TarefaDAO.createTarefa(tarefa10);
		
		/*ATIVIDADE*/
		Atividade atividade1 = new Atividade();
		atividade1.setNome("atividade_1");
		atividade1.setIteracao(iteracao1);		
		AtividadeDAO.createAtividade(atividade1);
		
		Atividade atividade2 = new Atividade();
		atividade2.setNome("atividade_2");
		atividade2.setIteracao(iteracao1);		
		AtividadeDAO.createAtividade(atividade2);
	
		
		
		/*HUMANO*/
		/*
		Humano humano1 = new Humano();
		humano1.setNome("Elder Ferreira");
		humano1.setEmail("elderferreirass@gmail.com");
		humano1.setProcesso(processo1);
		HumanoDAO.createHumano(humano1);
		
		Humano humano2 = new Humano();
		humano2.setNome("Andre Cunha");
		humano2.setEmail("andrecunhas@gmail.com");
		humano2.setProcesso(processo1);
		HumanoDAO.createHumano(humano2);
		
		Humano humano3 = new Humano();
		humano3.setNome("Carlos Portela");
		humano3.setEmail("carlosportela@gmail.com");
		humano3.setProcesso(processo1);
		HumanoDAO.createHumano(humano3);
		
		Humano humano4 = new Humano();
		humano4.setNome("Pedro Baco");
		humano4.setEmail("pedrobaco@gmail.com");
		humano4.setProcesso(processo1);
		HumanoDAO.createHumano(humano4);
		
		Humano humano5 = new Humano();
		humano5.setNome("Ariane Sinimbu");
		humano5.setEmail("areane@gmail.com");
		humano5.setProcesso(processo1);
		HumanoDAO.createHumano(humano5);
		
		Humano humano6 = new Humano();
		humano6.setNome("Wallace Lira");
		humano6.setEmail("ualace@gmail.com");
		humano6.setProcesso(processo1);
		HumanoDAO.createHumano(humano6);
		
		
		Humano humano7 = new Humano();
		humano7.setNome("Yosho");
		humano7.setEmail("yosho@gmail.com");
		humano7.setProcesso(processo2);
		HumanoDAO.createHumano(humano7);
		
		Humano humano8 = new Humano();
		humano8.setNome("Sandro Bezerra");
		humano8.setEmail("sbezerra@gmail.com");
		humano8.setProcesso(processo2);
		HumanoDAO.createHumano(humano8);
		
		Humano humano9 = new Humano();
		humano9.setNome("Portela");
		humano9.setEmail("portela@gmail.com");
		humano9.setProcesso(processo2);
		HumanoDAO.createHumano(humano9);
		
		Humano humano10 = new Humano();
		humano10.setNome("Ferreira");
		humano10.setEmail("ferreira@gmail.com");
		humano10.setProcesso(processo2);
		HumanoDAO.createHumano(humano10);
		
		Humano humano11 = new Humano();
		humano11.setNome("Sinimbu");
		humano11.setEmail("sinimbu@gmail.com");
		humano11.setProcesso(processo2);
		HumanoDAO.createHumano(humano11);
		
		Humano humano12 = new Humano();
		humano12.setNome("Cunha");
		humano12.setEmail("acunha@gmail.com");
		humano12.setProcesso(processo2);
		HumanoDAO.createHumano(humano12);
		*/
			Papel papel1 = new Papel();
			papel1.setNome("engenheiro de software");
			PapelDAO.createPapel(papel1);		
			
			Papel papel2 = new Papel();
			papel2.setNome("analista de sistemas");
			PapelDAO.createPapel(papel2);
			
			Papel papel13 = new Papel();
			papel13.setNome("diretor de TI");
			PapelDAO.createPapel(papel13);		
			
			Papel papel14 = new Papel();
			papel14.setNome("manutencao de software");
			PapelDAO.createPapel(papel14);
		
		tarefa1.getPapeis().add(papel1);
		tarefa1.getPapeis().add(papel2);
		
		tarefa6.getPapeis().add(papel13);
		tarefa6.getPapeis().add(papel14);
			
			Papel papel3 = new Papel();
			papel3.setNome("desenvolvedor");
			PapelDAO.createPapel(papel3);			
	
			Papel papel4 = new Papel();
			papel4.setNome("gerente");
			PapelDAO.createPapel(papel4);
			
			Papel papel5 = new Papel();
			papel5.setNome("implementador mps.rs");		
			PapelDAO.createPapel(papel5);
			
			Papel papel15 = new Papel();
			papel15.setNome("desenvolvedor");
			PapelDAO.createPapel(papel15);			
	
			Papel papel16 = new Papel();
			papel16.setNome("gerente");
			PapelDAO.createPapel(papel16);
			
			Papel papel17 = new Papel();
			papel17.setNome("implementador mps.rs");		
			PapelDAO.createPapel(papel17);		
		
		tarefa7.getPapeis().add(papel15);
		tarefa7.getPapeis().add(papel16);
		tarefa7.getPapeis().add(papel17);
		
		tarefa2.getPapeis().add(papel3);
		tarefa2.getPapeis().add(papel4);
		tarefa2.getPapeis().add(papel5);
		
			Papel papel6 = new Papel();
			papel6.setNome("desenvolvedor");
			PapelDAO.createPapel(papel6);
			
			Papel papel7 = new Papel();
			papel7.setNome("implementador mps.rs");		
			PapelDAO.createPapel(papel7);
			
			Papel papel18 = new Papel();
			papel18.setNome("desenvolvedor");
			PapelDAO.createPapel(papel18);
			
			Papel papel19 = new Papel();
			papel19.setNome("implementador mps.rs");		
			PapelDAO.createPapel(papel19);
		
		tarefa8.getPapeis().add(papel18);
		tarefa8.getPapeis().add(papel19);
		
		tarefa3.getPapeis().add(papel6);
		tarefa3.getPapeis().add(papel7);
		
			Papel papel8 = new Papel();
			papel8.setNome("engenheiro de software");
			PapelDAO.createPapel(papel8);		
			
			Papel papel9 = new Papel();
			papel9.setNome("analista de sistemas");
			PapelDAO.createPapel(papel9);		
	
			Papel papel10 = new Papel();
			papel10.setNome("gerente");
			PapelDAO.createPapel(papel10);
			
			Papel papel20 = new Papel();
			papel20.setNome("engenheiro de software");
			PapelDAO.createPapel(papel20);		
			
			Papel papel21 = new Papel();
			papel21.setNome("analista de sistemas");
			PapelDAO.createPapel(papel21);		
	
			Papel papel22 = new Papel();
			papel22.setNome("gerente");
			PapelDAO.createPapel(papel22);
		
		tarefa5.getPapeis().add(papel9);
		tarefa5.getPapeis().add(papel10);
		tarefa5.getPapeis().add(papel8);
		
		tarefa9.getPapeis().add(papel20);
		tarefa9.getPapeis().add(papel21);
		tarefa9.getPapeis().add(papel22);
		
			Papel papel11 = new Papel();
			papel11.setNome("engenheiro de software");
			PapelDAO.createPapel(papel11);
			
			Papel papel12 = new Papel();
			papel12.setNome("desenvolvedor");
			PapelDAO.createPapel(papel12);
			
			Papel papel23 = new Papel();
			papel23.setNome("engenheiro de software");
			PapelDAO.createPapel(papel23);
			
			Papel papel24 = new Papel();
			papel24.setNome("desenvolvedor");
			PapelDAO.createPapel(papel24);
		
		tarefa4.getPapeis().add(papel11);
		tarefa4.getPapeis().add(papel12);
		
		tarefa10.getPapeis().add(papel23);
		tarefa10.getPapeis().add(papel24);

		TarefaDAO.createTarefa(tarefa5);
		TarefaDAO.createTarefa(tarefa4);
		TarefaDAO.createTarefa(tarefa3);
		TarefaDAO.createTarefa(tarefa2);
		TarefaDAO.createTarefa(tarefa1);
		TarefaDAO.createTarefa(tarefa6);
		TarefaDAO.createTarefa(tarefa7);
		TarefaDAO.createTarefa(tarefa8);
		TarefaDAO.createTarefa(tarefa9);
		TarefaDAO.createTarefa(tarefa10);
		/*		
		Capacidade capacidade1 = new Capacidade();
		capacidade1.setConhecimento("Conhecimento da SPIDER-PM");
		capacidade1.setCursosCertificacoes("Hibernate");
		capacidade1.setFormacaoProfissional("Engenharia de Software");
		capacidade1.setHumano(humano1);
		CapacidadeDAO.createCapacidade(capacidade1);
		
		Capacidade capacidade2 = new Capacidade();
		capacidade2.setConhecimento("Conhecimento da SPIDER-PM");
		capacidade2.setCursosCertificacoes("Hibernate");
		capacidade2.setFormacaoProfissional("Engenharia de Software");
		capacidade2.setHumano(humano2);
		CapacidadeDAO.createCapacidade(capacidade2);
		
		Capacidade capacidade3 = new Capacidade();
		capacidade3.setConhecimento("Conhecimento da SPIDER-PM");
		capacidade3.setCursosCertificacoes("Hibernate");
		capacidade3.setFormacaoProfissional("Engenharia de Software");
		capacidade3.setHumano(humano3);
		CapacidadeDAO.createCapacidade(capacidade3);
		
		Capacidade capacidade4 = new Capacidade();
		capacidade4.setConhecimento("Conhecimento da SPIDER-PM");
		capacidade4.setCursosCertificacoes("Hibernate");
		capacidade4.setFormacaoProfissional("Engenharia de Software");
		capacidade4.setHumano(humano4);
		CapacidadeDAO.createCapacidade(capacidade4);

		
		Capacidade capacidade5 = new Capacidade();
		capacidade5.setConhecimento("Conhecimento da SPIDER-PM");
		capacidade5.setCursosCertificacoes("Hibernate");
		capacidade5.setFormacaoProfissional("Engenharia de Software");
		capacidade5.setHumano(humano5);
		CapacidadeDAO.createCapacidade(capacidade5);
		
		Capacidade capacidade6 = new Capacidade();
		capacidade6.setConhecimento("Conhecimento da SPIDER-PM");
		capacidade6.setCursosCertificacoes("Hibernate");
		capacidade6.setFormacaoProfissional("Engenharia de Software");
		capacidade6.setHumano(humano6);
		CapacidadeDAO.createCapacidade(capacidade6);
		
		Capacidade capacidade7 = new Capacidade();
		capacidade7.setConhecimento("Conhecimento da SPIDER-PM");
		capacidade7.setCursosCertificacoes("Hibernate");
		capacidade7.setFormacaoProfissional("Engenharia de Software");
		capacidade7.setHumano(humano7);
		CapacidadeDAO.createCapacidade(capacidade7);
		
		Capacidade capacidade8 = new Capacidade();
		capacidade8.setConhecimento("Conhecimento da SPIDER-PM");
		capacidade8.setCursosCertificacoes("Hibernate");
		capacidade8.setFormacaoProfissional("Engenharia de Software");
		capacidade8.setHumano(humano8);
		CapacidadeDAO.createCapacidade(capacidade8);
		
		Capacidade capacidade9 = new Capacidade();
		capacidade9.setConhecimento("Conhecimento da SPIDER-PM");
		capacidade9.setCursosCertificacoes("Hibernate");
		capacidade9.setFormacaoProfissional("Engenharia de Software");
		capacidade9.setHumano(humano9);
		CapacidadeDAO.createCapacidade(capacidade9);
		
		Capacidade capacidade10 = new Capacidade();
		capacidade10.setConhecimento("Conhecimento da SPIDER-PM");
		capacidade10.setCursosCertificacoes("Hibernate");
		capacidade10.setFormacaoProfissional("Engenharia de Software");
		capacidade10.setHumano(humano10);
		CapacidadeDAO.createCapacidade(capacidade10);

		
		Capacidade capacidade11 = new Capacidade();
		capacidade11.setConhecimento("Conhecimento da SPIDER-PM");
		capacidade11.setCursosCertificacoes("Hibernate");
		capacidade11.setFormacaoProfissional("Engenharia de Software");
		capacidade11.setHumano(humano11);
		CapacidadeDAO.createCapacidade(capacidade11);
		
		Capacidade capacidade12 = new Capacidade();
		capacidade12.setConhecimento("Conhecimento da SPIDER-PM");
		capacidade12.setCursosCertificacoes("Hibernate");
		capacidade12.setFormacaoProfissional("Engenharia de Software");
		capacidade12.setHumano(humano12);
		CapacidadeDAO.createCapacidade(capacidade12);
		
		humano1.setCapacidade(capacidade1);
		HumanoDAO.createHumano(humano1);
		humano2.setCapacidade(capacidade2);
		HumanoDAO.createHumano(humano2);
		humano3.setCapacidade(capacidade3);
		HumanoDAO.createHumano(humano3);
		humano4.setCapacidade(capacidade4);
		HumanoDAO.createHumano(humano4);
		humano5.setCapacidade(capacidade5);
		HumanoDAO.createHumano(humano5);
		humano6.setCapacidade(capacidade6);
		HumanoDAO.createHumano(humano6);
		humano7.setCapacidade(capacidade7);
		HumanoDAO.createHumano(humano7);
		humano8.setCapacidade(capacidade8);
		HumanoDAO.createHumano(humano8);
		humano9.setCapacidade(capacidade9);
		HumanoDAO.createHumano(humano9);
		humano10.setCapacidade(capacidade10);
		HumanoDAO.createHumano(humano10);
		humano11.setCapacidade(capacidade11);
		HumanoDAO.createHumano(humano11);
		humano12.setCapacidade(capacidade12);
		HumanoDAO.createHumano(humano12);
		*/
		
	}
	private void initComponents() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1000, 1000, 1000, 1000);
		setTitle("Spider-PE");
		// defini��o do look and feel
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
		// fim da defini��o do look and feel

		jMenuBarHome = new JMenuBar();
		jMenuBarHome.setBorder(null);
		jMenuBarHome.setBorderPainted(false);
		setJMenuBar(jMenuBarHome);

		jMenuArquivo = new JMenu("Usu\u00E1rio");
		jMenuVisoes = new JMenu("Downloads");
		jMenuAjuda = new JMenu("Ajuda");

		jMenuBarHome.add(jMenuArquivo);
		
		mntmAlterarAcesso = new JMenuItem("Alterar Acesso");
		mntmAlterarAcesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonAlterarAcesso();
			}
		});
		jMenuArquivo.add(mntmAlterarAcesso);
		
		mntmLogoff = new JMenuItem("Logoff");
		mntmLogoff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonLogout();
			}
		});
		jMenuArquivo.add(mntmLogoff);
		jMenuBarHome.add(jMenuVisoes);
		
		mnRecursos = new JMenu("Recursos");
		jMenuBarHome.add(mnRecursos);
		
		mntmHumano = new JMenuItem("Gerenciar Humanos");
		mntmHumano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonGerenciarHumanos();
				
			}
		});
		mnRecursos.add(mntmHumano);
		jMenuBarHome.add(jMenuAjuda);
		
		mntmManualSpiderpe = new JMenuItem("Manual Spider-PE");
		jMenuAjuda.add(mntmManualSpiderpe);
		
		mntmEspecificaoTcnicaXspiderml = new JMenuItem("Especifica\u00E7\u00E3o T\u00E9cnica xSPIDER_ML");
		jMenuAjuda.add(mntmEspecificaoTcnicaXspiderml);
		
		mntmSobre = new JMenuItem("Sobre a Spider-PE");
		jMenuAjuda.add(mntmSobre);

		jPanelBackGround = new JPanel();
		jPanelBackGround.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(jPanelBackGround);

		GridBagLayout gbl_jPanelBackGround = new GridBagLayout(); // cria��o do
																	// gridbaglayout
																	// para o
																	// panel
		gbl_jPanelBackGround.columnWidths = new int[] { 0, 0, 0 };
		gbl_jPanelBackGround.rowHeights = new int[] { 57, -15, 50, 0, 0, 23, 0,
				0, 0, 0, 0, 0 };
		gbl_jPanelBackGround.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_jPanelBackGround.rowWeights = new double[] { 0.0, 1.0, 1.0, 1.0,
				0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		jPanelBackGround.setLayout(gbl_jPanelBackGround); // adiciona o
															// gridbaglayout ao
															// panel

		jPanelNorte = new JPanel();
		jPanelNorte.setBorder(null);
		jPanelNorte.setForeground(UIManager.getColor("Button.background"));

		GridBagConstraints gbc_jPanelNorte = new GridBagConstraints();
		gbc_jPanelNorte.anchor = GridBagConstraints.NORTH;
		gbc_jPanelNorte.gridwidth = 2;
		gbc_jPanelNorte.insets = new Insets(0, 0, 5, 0);
		gbc_jPanelNorte.fill = GridBagConstraints.HORIZONTAL;
		gbc_jPanelNorte.gridx = 0;
		gbc_jPanelNorte.gridy = 0;
		jPanelBackGround.add(jPanelNorte, gbc_jPanelNorte);

		GridBagLayout gbl_jPanelNorte = new GridBagLayout(); // cria��o do
																// gridbaglayout
																// para o panel
		gbl_jPanelNorte.columnWidths = new int[] { 0, 0, 0, 0, 0, 0,
				600, 0, 0 };
		gbl_jPanelNorte.rowHeights = new int[] { 0, 0, 0 };
		gbl_jPanelNorte.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_jPanelNorte.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		jPanelNorte.setLayout(gbl_jPanelNorte); // adiciona o gridbaglayout ao
												// panel

		jToolBarAcessivel = new JToolBar();
		jToolBarAcessivel.setBorderPainted(false);
		jToolBarAcessivel.setBorder(null);
		jToolBarAcessivel.setFloatable(false);
		GridBagConstraints gbc_jToolBarAcessivel = new GridBagConstraints();
		gbc_jToolBarAcessivel.fill = GridBagConstraints.BOTH;
		gbc_jToolBarAcessivel.gridheight = 2;
		gbc_jToolBarAcessivel.gridwidth = 6;
		gbc_jToolBarAcessivel.insets = new Insets(0, 0, 0, 5);
		gbc_jToolBarAcessivel.gridx = 0;
		gbc_jToolBarAcessivel.gridy = 0;
		jPanelNorte.add(jToolBarAcessivel, gbc_jToolBarAcessivel);

		// adicionar botoes ao JToolBar
		jButtonHome = new JButton("");
		jButtonHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonHome();
			}
		});
		jButtonHome.setMargin(new Insets(0, 0, 0, 0));
		jButtonHome.setToolTipText("Home");
		jButtonHome.setBorderPainted(false);
		jButtonHome.setBorder(new EmptyBorder(5, 5, 5, 5));
		jButtonHome.setIcon(new Icone().getHome());
		jToolBarAcessivel.add(jButtonHome);
		
		button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.WAIT_CURSOR);
				jButtonManagers();
				setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		
		button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.WAIT_CURSOR);
				jButtonImportarXML();
				setCursor(Cursor.DEFAULT_CURSOR);
			
			}
		});
		button_2.setBorder(new EmptyBorder(5, 5, 5, 5));
		button_2.setMargin(new Insets(0, 0, 0, 0));
		button_2.setIcon(new ImageIcon(Spider_PE_Administration.class.getResource("/br/ufpa/spider/pe/view/util/img/pm-icon.png")));
		jToolBarAcessivel.add(button_2);
		button.setBorder(new EmptyBorder(5, 5, 5, 5));
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setIcon(new ImageIcon(Spider_PE_Administration.class.getResource("/br/ufpa/spider/pe/view/util/img/manager.png")));
		jToolBarAcessivel.add(button);

		jButtonLogout = new JButton("");
		jButtonLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.WAIT_CURSOR);
				jButtonLogout();
				setCursor(Cursor.DEFAULT_CURSOR);
			
			}
		});
		jButtonLogout.setMargin(new Insets(0, 0, 0, 0));
		jButtonLogout.setToolTipText("Logout");
		jButtonLogout.setBorder(new EmptyBorder(5, 5, 5, 5));
		jButtonLogout.setBorderPainted(false);
		jButtonLogout.setIcon(new Icone().getLogout());
		jToolBarAcessivel.add(jButtonLogout);
		
		jLabelLogoSpiderPe = new JLabel("");
		jLabelLogoSpiderPe.setBorder(new EmptyBorder(0, 350, 0, 0));
		jLabelLogoSpiderPe.setIcon(new Icone().getLogo_spider_pe());
		GridBagConstraints gbc_jLabelLogoSpiderPe = new GridBagConstraints();
		gbc_jLabelLogoSpiderPe.gridheight = 2;
		gbc_jLabelLogoSpiderPe.insets = new Insets(0, 0, 0, 5);
		gbc_jLabelLogoSpiderPe.gridx = 6;
		gbc_jLabelLogoSpiderPe.gridy = 0;
		jPanelNorte.add(jLabelLogoSpiderPe, gbc_jLabelLogoSpiderPe);
				
		lblBemVindo = new JLabel("Bem-vindo, " + "Administrador");
		GridBagConstraints gbc_lblBemVindo = new GridBagConstraints();
		gbc_lblBemVindo.anchor = GridBagConstraints.EAST;
		gbc_lblBemVindo.insets = new Insets(0, 0, 5, 0);
		gbc_lblBemVindo.gridx = 7;
		gbc_lblBemVindo.gridy = 0;
		jPanelNorte.add(lblBemVindo, gbc_lblBemVindo);
		
		jPanelLeste = new JPanel();
		jPanelOeste = new JPanelAdminTree();
		GridBagLayout gbl_jPanelOeste = new GridBagLayout();
		gbl_jPanelOeste.columnWidths = new int[]{0};
		gbl_jPanelOeste.rowHeights = new int[]{0};
		gbl_jPanelOeste.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_jPanelOeste.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		
		treeMouseListenerAdmin = new TreeMouseListenerAdmin(((JPanelAdminTree) jPanelOeste).getTree(), jPanelLeste);
		
		((JPanelAdminTree) jPanelOeste).getTree().addMouseListener(treeMouseListenerAdmin);
		
		
		
		GridBagLayout gbl_jPanelLeste = new GridBagLayout();
		gbl_jPanelLeste.columnWidths = new int[]{0, 0};
		gbl_jPanelLeste.rowHeights = new int[]{0, 0};
		gbl_jPanelLeste.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_jPanelLeste.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		jPanelLeste.setLayout(gbl_jPanelLeste);
		
		jSplitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jPanelOeste,
				jPanelLeste);
				
		GridBagConstraints gbc_jSplitPanel = new GridBagConstraints();
		gbc_jSplitPanel.gridheight = 10;
		gbc_jSplitPanel.gridwidth = 2;
		gbc_jSplitPanel.fill = GridBagConstraints.BOTH;
		gbc_jSplitPanel.gridx = 0;
		gbc_jSplitPanel.gridy = 1;
		jPanelBackGround.add(jSplitPanel, gbc_jSplitPanel);

		splitPane = new JSplitPane();
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.insets = new Insets(0, 0, 0, 5);
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 0;
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		jPanelLeste.add(new JPanelHomeAdmin(), c);		
	}
	

	protected void jButtonGerenciarHumanos() {
		JDialogGerenciarHumanos dialog = new JDialogGerenciarHumanos();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}

	protected void jButtonAlterarAcesso() {
		try{
			Usuario usuario = (Usuario) Spider_PE_Home.getInstance().getInstanciaLogado();
			JDialogSenha senha = new JDialogSenha(usuario);
			senha.setLocationRelativeTo(null);
			senha.setVisible(true);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	protected void jButtonPasswordAdmin() {
		JDialogSenha dialog = new JDialogSenha(Spider_PE_Home.getInstance().getInstanciaLogado());
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}

	protected void jButtonHome() {
		JPanel panel  = new JPanelHomeAdmin();
		jPanelLeste.removeAll();		
		jPanelLeste.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		jPanelLeste.add(panel, c);
		
		jPanelLeste.revalidate();
		jPanelLeste.repaint();
	}

	protected void jButtonImportarXML() {
		JPanel panel  = new JPanelImportarXML();
		jPanelLeste.removeAll();		
		jPanelLeste.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		jPanelLeste.add(panel, c);
		
		jPanelLeste.revalidate();
		jPanelLeste.repaint();		
	}

	protected void jButtonManagers() {
		JPanel panel  = new JPanelManagers();
		jPanelLeste.removeAll();		
		jPanelLeste.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		jPanelLeste.add(panel, c);
		
		jPanelLeste.revalidate();
		jPanelLeste.repaint();
	}

	protected void jButtonLogout() {
		this.dispose();	
		Spider_PE_Home.getInstance().setVisible(true);
	}

	private static Spider_PE_Administration home = null;
	private JButton button;
	private JButton button_2;
	private JPanel panel_1;
	private JLabel lblBemVindo;
	private JMenuItem mntmAlterarAcesso;
	private JMenuItem mntmLogoff;
	private JMenuItem mntmManualSpiderpe;
	private JMenuItem mntmEspecificaoTcnicaXspiderml;
	private JMenuItem mntmSobre;
	private JPanel jPanelOeste;
	private JPanel jPanel;
	private JScrollPane scrollPane;
	private JMenu mnRecursos;
	private JMenuItem mntmHumano;
	
	public synchronized static Spider_PE_Administration getInstance() {
		if (home == null)
			home = new Spider_PE_Administration();
		return home;
	}

	public JPanel getjPanelLeste() {
		return jPanelLeste;
	}

	public JPanel getPanel_1() {
		return panel_1;
	}

	public void setPanel_1(JPanel panel_1) {
		this.panel_1 = panel_1;
	}

}
