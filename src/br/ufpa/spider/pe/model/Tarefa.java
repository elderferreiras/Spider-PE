package br.ufpa.spider.pe.model;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.swing.JOptionPane;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.type.CalendarDateType;

import br.ufpa.spider.pe.model.dao.TarefaDAO;
import br.ufpa.spider.pe.model.dao.TipoDAO;
import br.ufpa.spider.pe.model.util.ProcessManagerController;
import br.ufpa.spider.pe.view.execution.logic.ExecutionController;
import br.ufpa.spider.pe.view.execution.logic.TimeController;

@Entity(name="tarefa")
public class Tarefa {
	
	public static void main(String[] args) {
	
		Date date = new Date();
		System.out.println(date.getYear());
		System.out.println(date.getMonth());
		System.out.println(date.toString());
		date.setYear(date.getYear()+1900);
		System.out.println(date.getYear());
		System.out.println(date.toGMTString());
//		Log log2 = new Log();
//		log2.setDate(new Date(2013, 1, 25, 3, 0));
//		log2.setEstado(ExecutionController.PAUSADA);
//		log2.setTarefa(tarefa);
//		Log log3 = new Log();
//		log3.setDate(new Date(2013, 1, 25, 5, 0));
//		log3.setEstado(ExecutionController.EXECUCAO);
//		log3.setTarefa(tarefa);
//		Log log4 = new Log();
//		log4.setDate(new Date(2012, 1, 25, 8, 0));
//		log4.setEstado(ExecutionController.FINALIZADA);
//		log4.setTarefa(tarefa);
//		List<Log> logs = new ArrayList<Log>();
//		logs.add(log1);
////		logs.add(log2);
////		logs.add(log3);
////		logs.add(log4);
//		tarefa.setLog(logs);
//		JOptionPane.showMessageDialog(null, "Clock Interno: "+ tarefa.getClockInterno());
	}

	@Id
	@Column(name="idtarefa")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="idcomponent")
	private Integer idComponent;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="qtd_esforco")
	private String qtdEsforco;
	
	@Column(name="und_esforco", length = 20)
	private String undEsforco;
	
	@Column(name="inicio_previsto")
	private String inicioPrevisto;
	
	@Column(name="fim_previsto")
	private String fimPrevisto;
	
	@Column(name="habilidadesRequeridas")
	private String habilidadesRequeridas;
	
	@Column(name =  "arq_xml")
	private String arqXML;

	@Column(name="inicio_real")
	private String inicioReal;
	
	@Column(name="fim_real")
	private String fimReal;
	
	@Column(name="carga_horaria")
	private String cargaHoraria;
	
	@Column(name="und_carga_horaria", length = 20)
	private String undCargaHoraria;
	
	@Column(name="clock_interno")
	private String clockInterno;
	
	@Column(name="percentagem_execucao")
	private int percentagemExecucao;
	
	@Column(name="desc_feedback", length = 200)
	private String descFeedback;
	
	@Column(name="estado", length = 45)
	private String  estado;
	
	@Column(name="status", length = 45)
	private String status;
	

	@Column(name="objetivos")
	String objetivos;
	
	@Column(name="descricao")
	String descricao;
	
	@Column(name="informacoesAdicionais")
	String informacoesAdicionais;	
	
	
	@ManyToOne
	@JoinColumn(name = "iteracao")
	private Iteracao iteracao;
	
	@Column(name = "timeStart")
	private Double timeStart;

	
	@ManyToOne
	@JoinColumn(name = "fase")
	private Fase fase;
	
	@ManyToOne
	@JoinColumn(name = "atividade")
	private Atividade atividade;
	
	@ManyToMany(fetch = FetchType.LAZY)  
	@JoinTable(name = "produto_trabalho_x_tarefa",  
	joinColumns = { @JoinColumn(name = "idtarefa",  
	updatable =  false) }, inverseJoinColumns = {  
	@JoinColumn(name = "idproduto_trabalho", updatable = false) })
	private List<ProdutoTrabalho> produtoTrabalho = new ArrayList<ProdutoTrabalho>();

	
	@ManyToMany(fetch = FetchType.LAZY)  
	@JoinTable(name = "softwares_x_tarefa",  
	joinColumns = { @JoinColumn(name = "idtarefa",  
	updatable =  false) }, inverseJoinColumns = {  
	@JoinColumn(name = "idsoftware", updatable = false) })
	@Cascade(value=CascadeType.ALL)
	private List<Software> softwares = new ArrayList<Software>();	

	@ManyToMany(fetch = FetchType.LAZY)  
	@JoinTable(name = "procedimento_x_tarefa",  
	joinColumns = { @JoinColumn(name = "idtarefa",  
	updatable =  false) }, inverseJoinColumns = {  
	@JoinColumn(name = "idprocedimento", updatable = false) })
	@Cascade(value=CascadeType.ALL)
	private List<Procedimento> procedimentos = new ArrayList<Procedimento>();
	
	@ManyToMany(fetch = FetchType.LAZY)  
	@JoinTable(name = "hardwares_x_tarefa",  
	joinColumns = { @JoinColumn(name = "idtarefa",  
	updatable =  false) }, inverseJoinColumns = {  
	@JoinColumn(name = "idhardware", updatable = false) })
	//@Cascade(value=CascadeType.ALL)
	private List<Hardware> hardwares = new ArrayList<Hardware>();
	
	@OneToMany(mappedBy="tarefa")
	@Cascade(value=CascadeType.ALL)
	private List<Papel> papeis = new ArrayList<Papel>();

	@ManyToOne
	@JoinColumn(name = "tipo")
	private Tipo tipo;
	
	@OneToMany(mappedBy="tarefa")
	@Cascade(value=CascadeType.ALL)
	private List<Log> log = new ArrayList<Log>();

	public List<Log> getLog() {
		return log;
	}

	public void setLog(List<Log> log) {
		this.log = log;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public List<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}

	public void setIdComponent(Integer idComponent) {
		this.idComponent = idComponent;
	}

	public void setClockInterno(String clockInterno) {
		this.clockInterno = clockInterno;
	}

	public void setPercentagemExecucao(Integer percentagemExecucao) {
		this.percentagemExecucao = percentagemExecucao;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	
	public String getHabilidadesRequeridas() {
		return habilidadesRequeridas;
	}

	public void setHabilidadesRequeridas(String habilidadesRequeridas) {
		this.habilidadesRequeridas = habilidadesRequeridas;
	}

	public List<ProdutoTrabalho> getProdutoTrabalho() {
		return produtoTrabalho;
	}

	public void setProdutoTrabalho(List<ProdutoTrabalho> produtoTrabalho) {
		this.produtoTrabalho = produtoTrabalho;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public Iteracao getIteracao() {
		return iteracao;
	}

	public void setIteracao(Iteracao iteracao) {
		this.iteracao = iteracao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdComponent() {
		return idComponent;
	}

	public void setIdComponent(int idComponent) {
		this.idComponent = idComponent;
	}

	public String getQtdEsforco() {
		return qtdEsforco;
	}

	public void setQtdEsforco(String qtdEsforco) {
		this.qtdEsforco = qtdEsforco;
	}

	public String getUndEsforco() {
		return undEsforco;
	}

	public void setUndEsforco(String undEsforco) {
		this.undEsforco = undEsforco;
	}

	public String getInicioPrevisto() {
		return inicioPrevisto;
	}

	public void setInicioPrevisto(String inicioPrevisto) {
		this.inicioPrevisto = inicioPrevisto;
	}

	public String getFimPrevisto() {
		return fimPrevisto;
	}

	public void setFimPrevisto(String fimPrevisto) {
		this.fimPrevisto = fimPrevisto;
	}

	public String getInicioReal() {
		return inicioReal;
	}

	public void setInicioReal(String inicioReal) {
		this.inicioReal = inicioReal;
	}

	public String getFimReal() {
		return fimReal;
	}

	public void setFimReal(String fimReal) {
		this.fimReal = fimReal;
	}

	public String getCargaHoraria() {
		return cargaHoraria;
	}
	
	public double getCargaHorariaEmMinutos(){
		if(getCargaHoraria()!= null){
			double carga = TarefaDAO.convertCargaStringtoDouble(getCargaHoraria());
			return convertToMinutes(carga, getUndCargaHoraria());
		}
		
		return 0;
	}
	
	public double convertToMinutes(double tempo, String unid){
		int fator_hora = TipoDAO.findByName(ProcessManagerController.HORAS).getFatorConversao();
		int fator_dia = TipoDAO.findByName(ProcessManagerController.DIAS).getFatorConversao();
		int fator_mes = TipoDAO.findByName(ProcessManagerController.MESES).getFatorConversao();
		int fator_ano = TipoDAO.findByName(ProcessManagerController.ANOS).getFatorConversao();
		
		if (unid.equals(ProcessManagerController.HORAS)){
			return tempo * fator_hora;
		} else if (unid.equals(ProcessManagerController.DIAS)){
			return tempo * fator_hora * fator_dia;
		} if (unid.equals(ProcessManagerController.MESES)){
			return tempo * fator_hora * fator_dia * fator_mes;
		} if (unid.equals(ProcessManagerController.ANOS)){
			return tempo * fator_hora * fator_dia * fator_mes * fator_ano;
		} if (unid.equals(ProcessManagerController.MINUTOS)){
			return tempo;
		}
		return 0;
	}

	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getUndCargaHoraria() {
		return undCargaHoraria;
	}

	public void setUndCargaHoraria(String undCargaHoraria) {
		this.undCargaHoraria = undCargaHoraria;
	}

	public String getClockInterno() {
//		Double tempoCorrente = TimeController.getTempoCorrente();
//		int convertion = 60;
//		if(clockInterno!= null){
//			Double clock = TarefaDAO.convertCargaStringtoDouble(clockInterno)*convertion + (tempoCorrente - getTimeStart());
//			clock = clock/60; //minuto para hora
//			setClockInterno(clock.toString());
//			setTimeStart(tempoCorrente);
//			return clockInterno;
//		} else {
//			return "0";
//		}
		double clock = 0;
		List<Log> logs = new ArrayList<Log>();
		logs.addAll(getLog());
		Log log_previous = null;
		for(Log log : logs){
		
			if(log.getEstado().equals(ExecutionController.PAUSADA)){
				Date dateCurrent = log.getDate();
				Date datePrevious = log_previous.getDate(); // inicio de uma execução
				Time time = new Time(dateCurrent, datePrevious);
				clock+= time.timeMinute();
			}
			
			if(log.getEstado().equals(ExecutionController.FINALIZADA)){
				Date dateCurrent = log.getDate();
				Date datePrevious = log_previous.getDate(); //inicio de uma execução
				Time time = new Time(dateCurrent, datePrevious);
				clock+= time.timeMinute();
			}
			log_previous = log;
		}
		
		if(log_previous.getEstado().equals(ExecutionController.EXECUCAO)){
			Date dateCurrent = new Date();
			//JOptionPane.showMessageDialog(null, "ano1:"+dateCurrent.getYear());
			Date dateLog = log_previous.getDate();
			//JOptionPane.showMessageDialog(null, "ano1:"+dateLog.getYear());
			Time time = new Time(dateCurrent, dateLog);
			clock+= time.timeMinute();
		}
		
		return ""+clock;
	}
	
	private class Time {
		private int anos;
		public int getMeses() {
			return meses;
		}


		public void setMeses(int meses) {
			this.meses = meses;
		}


		public int getDias() {
			return dias;
		}


		public void setDias(int dias) {
			this.dias = dias;
		}


		public int getHoras() {
			return horas;
		}


		public void setHoras(int horas) {
			this.horas = horas;
		}


		public int getMinutos() {
			return minutos;
		}


		public void setMinutos(int minutos) {
			this.minutos = minutos;
		}


		private int meses;
		private int dias;
		private int horas;
		private int minutos;
		
		Time(Date dateCurrent, Date dateLog){
			
			int years = dateCurrent.getYear() - dateLog.getYear();
			int month = dateCurrent.getMonth() - dateLog.getMonth();
			int days = dateCurrent.getDate() - dateLog.getDate();
			int hours = dateCurrent.getHours() - dateLog.getHours();
			int minute = dateCurrent.getMinutes() - dateLog.getMinutes();
			setAnos(years);
			setMeses(month);
			setDias(days);
			setHoras(hours);
			setMinutos(minute);
			
		}
		
		
		public double timeMinute(){
			double minutes = 0;
			minutes += convertToMinutes(getAnos(), ProcessManagerController.ANOS);
			//JOptionPane.showMessageDialog(null, "ano="+getAnos());
			minutes += convertToMinutes(getMeses(), ProcessManagerController.MESES);
			//JOptionPane.showMessageDialog(null, "mes="+getMeses());
			minutes += convertToMinutes(getDias(), ProcessManagerController.DIAS);
			//JOptionPane.showMessageDialog(null, "dias="+getDias());
			minutes += convertToMinutes(getHoras(), ProcessManagerController.HORAS);
			//JOptionPane.showMessageDialog(null, "horas="+getHoras());
			minutes += getMinutos();
			//JOptionPane.showMessageDialog(null, "minutos="+getMinutos());
			return minutes;
		}


		public int getAnos() {
			return anos;
		}


		public void setAnos(int anos) {
			this.anos = anos;
		}
	}


	public int getPercentagemExecucao() {
		return percentagemExecucao;
	}

	public void setPercentagemExecucao(int percentagemExecucao) {
		this.percentagemExecucao = percentagemExecucao;
	}

	public String getDescFeedback() {
		return descFeedback;
	}

	public void setDescFeedback(String descFeedback) {
		this.descFeedback = descFeedback;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getStatus() {
		if(getCargaHoraria()!="0.0" && getEstado() != null){
			if(getEstado().equals(ExecutionController.FINALIZADA)){
				
				if(getCargaHorariaEmMinutos() == TarefaDAO.convertCargaStringtoDouble(getClockInterno())){
					//setStatus(ExecutionController.EXECUCAO);
				} else if (getCargaHorariaEmMinutos() > TarefaDAO.convertCargaStringtoDouble(getClockInterno())){
					setStatus(ExecutionController.ADIANTADA);
				} else {
					setStatus(ExecutionController.ATRASADA);
				}
			} else {
				if (getCargaHorariaEmMinutos() < TarefaDAO.convertCargaStringtoDouble(getClockInterno())){
					//JOptionPane.showMessageDialog(null, "cargah:"+getCargaHorariaEmMinutos()+"\nClock:"+getClockInterno());
					setStatus(ExecutionController.ATRASADA);
				} else {
					setStatus(ExecutionController.EXECUCAO);
				}
			}
		}
		return status;
		
		
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getNome();
	}

	public List<Software> getSoftwares() {
		return softwares;
	}

	public void setSoftwares(List<Software> softwares) {
		this.softwares = softwares;
	}

	public List<Hardware> getHardwares() {
		return hardwares;
	}

	public void setHardwares(List<Hardware> hardwares) {
		this.hardwares = hardwares;
	}

	public List<Procedimento> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<Procedimento> procedimentos) {
		this.procedimentos = procedimentos;
	}

	public String getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getInformacoesAdicionais() {
		return informacoesAdicionais;
	}

	public void setInformacoesAdicionais(String informacoesAdicionais) {
		this.informacoesAdicionais = informacoesAdicionais;
	}

	public String getArqXML() {
		return arqXML;
	}

	public void setArqXML(String arqXML) {
		this.arqXML = arqXML;
	}

	public Fase getFase() {
		return fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
	}

	public Double getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Double timeStart) {
		this.timeStart = timeStart;
	}

	
}
