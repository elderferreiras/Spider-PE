package br.ufpa.spider.pe.model.dao;

import java.util.List;

import br.ufpa.spider.pe.model.PlanoComunicacao;

public class PlanoComunicacaoDAO extends GenericDAO {
	public static void createPlanoComunicacao(PlanoComunicacao planoComunicacao){
	     create(planoComunicacao);
	}
	
	public static void updatePlanoComunicacao(PlanoComunicacao planoComunicacao){
	     update(planoComunicacao);
	}
	
	public static void removePlanoComunicacao(PlanoComunicacao planoComunicacao){
	     remove(planoComunicacao);
	}
	
	public static  List<PlanoComunicacao> findAll(){
		List<PlanoComunicacao> list = findAll("plano_comunicacao");
	    return list;
	}
	
	
//	public static PlanoComunicacao findByName(String name)
//	{
//		PlanoComunicacao planoComunicacao = null;
//		for (PlanoComunicacao user: findAll()){
//			if(user.getNome().contains(name.trim())){
//				planoComunicacao = user;
//				break;
//			}
//		}
//		
//		return planoComunicacao;
//	}
	
	public static PlanoComunicacao findById(int id)
	{
		for (PlanoComunicacao planoComunicacao: findAll()){
			if(planoComunicacao.getId() ==id){
				return planoComunicacao;
			}
		}
		return null;
	}
}
