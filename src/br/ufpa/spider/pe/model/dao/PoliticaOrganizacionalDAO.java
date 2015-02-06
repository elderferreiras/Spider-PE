package br.ufpa.spider.pe.model.dao;

import java.util.List;

import br.ufpa.spider.pe.model.PoliticaOrganizacional;

public class PoliticaOrganizacionalDAO extends GenericDAO{
		
		public static void createPoliticaOrganizacional(PoliticaOrganizacional politicaOrganizacional){
		     create(politicaOrganizacional);
		}
		
		public static void updatePoliticaOrganizacional(PoliticaOrganizacional politicaOrganizacional){
		     update(politicaOrganizacional);
		}
		
		public static void removePoliticaOrganizacional(PoliticaOrganizacional politicaOrganizacional){
		     remove(politicaOrganizacional);
		}
		
		public static  List<PoliticaOrganizacional> findAll(){
			List<PoliticaOrganizacional> list = findAll("politica_organizacional");
		    return list;
		}
		
		
		public static PoliticaOrganizacional findByName(String name)
		{
			PoliticaOrganizacional politica_organizacional = null;
			for (PoliticaOrganizacional user: findAll()){
				if(user.getNome().contains(name.trim())){
					politica_organizacional = user;
					break;
				}
			}
			
			return politica_organizacional;
		}
		
		public static PoliticaOrganizacional findById(int id)
		{
			for (PoliticaOrganizacional politica: findAll()){
				if(politica.getId() == id){
					return politica;
				}
			}
			return null;
		}

		

}
