package br.ufpa.spider.pe.model.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;



import br.ufpa.spider.pe.view.pm.model.Component;
import br.ufpa.spider.pe.view.pm.model.ComponentType;
import br.ufpa.spider.pe.view.pm.model.Modelling;
import br.ufpa.spider.pe.view.pm.persistence.ModellingFileManager;
import br.ufpa.spider.pe.view.pm.persistence.SimpleComponent;
import br.ufpa.spider.pe.view.pm.persistence.SimpleModelling;

public class XMLConverter {
	
	private static String pathSource;
	private static List<File> files;
	private static List<Integer> processIds;
	private static SimpleModelling simple;
	
	public static void load() throws FileNotFoundException, IOException{
		simple = ModellingFileManager.load2(pathSource);
	}
	
	public static void setPathSource(String path){
		pathSource = path;
		files = new ArrayList<File>();
		processIds = new ArrayList<Integer>();
	}

	public static void save(){
		try {
			load();
			for(SimpleComponent component: simple.getComponents()){
				if(component.getType() == ComponentType.PROCESS){
					processIds.add(component.getId());
				}
			}
			for (Integer idProcess : processIds) {
				load();
				File fileXML = File.createTempFile("processo_"+idProcess, ".xml");				
				ModellingFileManager.save(fileXML, idProcess, simple);
				files.add(fileXML);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<File> getFiles() {
		return files;
	}

}
