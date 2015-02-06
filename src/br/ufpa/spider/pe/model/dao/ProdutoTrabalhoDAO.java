package br.ufpa.spider.pe.model.dao;

import java.util.List;

import br.ufpa.spider.pe.model.Fase;
import br.ufpa.spider.pe.model.ProdutoTrabalho;

public class ProdutoTrabalhoDAO extends GenericDAO {
	public static void createProdutoTrabalho(ProdutoTrabalho produtoTrabalho){
	     create(produtoTrabalho);
	}
	
	public static void updateProdutoTrabalho(ProdutoTrabalho produtoTrabalho){
	     update(produtoTrabalho);
	}
	
	public static void removeProdutoTrabalho(ProdutoTrabalho produtoTrabalho){
	     remove(produtoTrabalho);
	}
	
	public static  List<ProdutoTrabalho> findAll(){
		List<ProdutoTrabalho> list = findAll("produto_trabalho");
	    return list;
	}
	
	
	public static ProdutoTrabalho findByName(String name)
	{
		ProdutoTrabalho produtoTrabalho = null;
		for (ProdutoTrabalho user: findAll()){
			if(user.getNome().contains(name.trim())){
				produtoTrabalho = user;
				break;
			}
		}
		
		return produtoTrabalho;
	}
	
	public static ProdutoTrabalho findById(int id)
	{
		for (ProdutoTrabalho produtoTrabalho: findAll()){
			if(produtoTrabalho.getId() ==id){
				return produtoTrabalho;
			}
		}
		return null;
	}
	
	public static ProdutoTrabalho findByIdComponent(Integer idComponent, String nameFile){
		for(ProdutoTrabalho pt: findAll()){
			if(pt.getIdComponent().equals(idComponent)){
				if(pt.getArqXML().equals(nameFile)){
					return pt;
				}
			}
		}
		return null;
	}
}
