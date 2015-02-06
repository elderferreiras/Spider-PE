package br.ufpa.spider.pe.model.dao;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.ProdutoTrabalho;
import br.ufpa.spider.pe.model.Software;

public class SoftwareDAO extends GenericDAO {
	public static void createSoftware(Software software){
	     create(software);
	}
	
	public static void updateSoftware(Software software){
	     update(software);
	}
	
	public static void removeSoftware(Software software){
	     remove(software);
	}
	
	public static  List<Software> findAll(){
		List<Software> list = findAll("software");
	    return list;
	}
	
	
//	public static Software findByName(String name)
//	{
//		Software software = null;
//		for (Software user: findAll()){
//			if(user.getNome().contains(name.trim())){
//				software = user;
//				break;
//			}
//		}
//		
//		return software;
//	}
	
	public static Software findById(int id)
	{
		for (Software software: findAll()){
			if(software.getId() ==id){
				return software;
			}
		}
		return null;
	}
	
	public static Software findByIdComponent(Integer idComponent, String nameFile){
		for(Software s: findAll()){
			if(s.getIdComponent().equals(idComponent)){
				if(s.getArqXML().equals(nameFile)){
					return s;
				}
			}
		}
		return null;
	}

}
