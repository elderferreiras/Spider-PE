package br.ufpa.spider.pe.model.dao;

import java.util.List;

import br.ufpa.spider.pe.model.Stakeholder;

public class StakeholderDAO extends GenericDAO{
		
		public static void createStakeholder(Stakeholder stakeHolder){
		     create(stakeHolder);
		}
		
		public static void updateStakeholder(Stakeholder stakeHolder){
		     update(stakeHolder);
		}
		
		public static void removeStakeholder(Stakeholder stakeHolder){
		     remove(stakeHolder);
		}
		
		public static  List<Stakeholder> findAll(){
			List<Stakeholder> list = findAll("stakeHolder");
		    return list;
		}
		
		
		public static Stakeholder findByName(String name)
		{
			for (Stakeholder stakeHolder: findAll()){
				if(stakeHolder.getNome().contains(name.trim())){
					return stakeHolder;
				}
			}
			return null;
		}
		
		public static Stakeholder findById(int id)
		{
			for (Stakeholder stakeHolder: findAll()){
				if(stakeHolder.getId() == (id)){
					return stakeHolder;
				}
			}
			return null;
		}

}
