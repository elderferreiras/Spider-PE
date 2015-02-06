package br.ufpa.spider.pe.model.util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream.GetField;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.PoliticaOrganizacional;
import br.ufpa.spider.pe.view.util.Icone;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

public class ArquivoPDF {
	static String path="";
	static File file;
	
	public static String getPath() {
		return path;
	}


	public static void setPath(String path) {
		ArquivoPDF.path = path;
	}


	public static File getFile() {
		return file;
	}


	public static void setFile(File file) {
		ArquivoPDF.file = file;
	}


	public ArquivoPDF(PoliticaOrganizacional politicaOrganizacional, String caminhoEscolhido) throws Exception{
		path = caminhoEscolhido;
		ITextHelloWorld(politicaOrganizacional);		
	}
	

	private String getData() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	

	public String getCaminhoArquivo(){
		return path;
	}
	
	
	public void ITextHelloWorld(PoliticaOrganizacional politicaOrganizacional) throws Exception {

		Document document = new Document();
		path = path +politicaOrganizacional.getNome()+".pdf";
		file = File.createTempFile(politicaOrganizacional.getNome(), ".pdf");
		PdfWriter.getInstance(document, new FileOutputStream(file));
		
		document.open();
		Paragraph p0 = new Paragraph("\n");
		Paragraph p1 = new Paragraph("Pol\u00edtica Organizacional",
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 36, Font.BOLD));
		p1.setAlignment(Element.ALIGN_CENTER);
		p1.setSpacingBefore(230);

		Paragraph p2 = new Paragraph(politicaOrganizacional.getNome(),
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 22, Font.BOLD));
		p2.setAlignment(Element.ALIGN_CENTER);
		p2.setSpacingAfter(35);

		String paragrafo = "Vers\u00e3o do Documento: " + politicaOrganizacional.getVersao()+ ".0\nData de Emiss\u00e3o: "
				+ getData() + "\n\nRespons\u00e1vel pelo Documento: XXXXXXXXX";
		Paragraph p3 = new Paragraph(paragrafo, FontFactory.getFont(
				FontFactory.TIMES_ROMAN, 12, Font.BOLD));
		p3.setAlignment(Element.ALIGN_RIGHT);

		HeaderFooter footer = new HeaderFooter(new Phrase("This is page "),
				true);
		footer.setBorder(Rectangle.RIGHT);

		Paragraph p4 = new Paragraph("1. Introdu\u00e7\u00e3o", FontFactory.getFont(
				FontFactory.TIMES_ROMAN, 14, Font.BOLD));
		p4.setAlignment(Element.ALIGN_LEFT);
		p4.setSpacingBefore(30);

		Paragraph p5 = new Paragraph(
				"Esse documento tem por objetivo descrever a pol\u00edtica de desenvolvimento de software da "+politicaOrganizacional.getNome()+". Esta pol\u00edtica define as diretrizes e responsabilidades para o setor de desenvolvimento de software.",
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 12));
		p5.setAlignment(Element.ALIGN_JUSTIFIED);
		p5.setSpacingAfter(15);
		
		Paragraph p6 = new Paragraph("1.1 Diretrizes Gerais", FontFactory.getFont(
				FontFactory.TIMES_ROMAN, 14, Font.BOLD));
		p6.setAlignment(Element.ALIGN_LEFT);
		
		Paragraph p7 = new Paragraph(
				"A seguir s\u00e3o apresentadas as diretrizes gerais da pol\u00edtica de desenvolvimento de software:\n"+politicaOrganizacional.getDiretrizesGerais(),
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 12));
		p7.setAlignment(Element.ALIGN_JUSTIFIED);
		p7.setSpacingAfter(15);
		
		Paragraph p8 = new Paragraph("1.2 Responsabilidade Gerais", FontFactory.getFont(
				FontFactory.TIMES_ROMAN, 14, Font.BOLD));
		p8.setAlignment(Element.ALIGN_LEFT);

		Paragraph p9 = new Paragraph(
				"A seguir s\u00e3o apresentadas as responsabilidades gerais da pol\u00edtica do desenvolvimento de software: "+politicaOrganizacional.getResponsabilidadesGerais(),
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 12));
		p9.setAlignment(Element.ALIGN_JUSTIFIED);
		p9.setSpacingAfter(15);
		
		Paragraph p10 = new Paragraph("2. Pol\u00edtica de Qualidade", FontFactory.getFont(
				FontFactory.TIMES_ROMAN, 14, Font.BOLD));
		p10.setAlignment(Element.ALIGN_LEFT);

		Paragraph p11 = new Paragraph(
				politicaOrganizacional.getPoliticaQualidade(),
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 12));
		p11.setAlignment(Element.ALIGN_JUSTIFIED);
		p11.setSpacingAfter(15);
		
		Paragraph p12 = new Paragraph("3. Pol\u00edtica de Desenvolvimento", FontFactory.getFont(
				FontFactory.TIMES_ROMAN, 14, Font.BOLD));
		p12.setAlignment(Element.ALIGN_LEFT);

		Paragraph p13 = new Paragraph(
				politicaOrganizacional.getPoliticaDesenvolvimento(),
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 12));
		p13.setAlignment(Element.ALIGN_JUSTIFIED);
		p13.setSpacingAfter(15);
		
		Paragraph p14 = new Paragraph("4. Pol\u00edticas Espec\u00edficas", FontFactory.getFont(
				FontFactory.TIMES_ROMAN, 14, Font.BOLD));
		p14.setAlignment(Element.ALIGN_LEFT);

		Paragraph p15 = new Paragraph(
				politicaOrganizacional.getPoliticasEspecificas(),
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 12));
		p15.setAlignment(Element.ALIGN_JUSTIFIED);
		p15.setSpacingAfter(15);
		
		Paragraph p16 = new Paragraph("5. Refer\u00eancias", FontFactory.getFont(
				FontFactory.TIMES_ROMAN, 14, Font.BOLD));
		p16.setAlignment(Element.ALIGN_LEFT);

		Paragraph p17 = new Paragraph(
				politicaOrganizacional.getReferencias(),
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 12));
		p17.setAlignment(Element.ALIGN_JUSTIFIED);
		p17.setSpacingAfter(15);

		document.add(p0);
		document.add(p1);
		document.add(p2);
		document.add(p3);
		document.setFooter(footer);
		document.add(footer);
		document.newPage();
		document.add(p4);
		document.add(p5);
		document.add(p6);
		document.add(p7);
		document.add(p8);
		document.add(p9);
		document.add(p10);
		document.add(p11);
		document.add(p12);
		document.add(p13);
		document.add(p14);
		document.add(p15);
		document.add(p16);
		document.add(p17);
		document.close();
	}
	

}
