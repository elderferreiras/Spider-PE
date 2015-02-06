package br.ufpa.spider.pe.model.set.dao;

import java.util.List;

import br.ufpa.spider.pe.model.Atividade;
import br.ufpa.spider.pe.model.dao.GenericDAO;
import br.ufpa.spider.pe.model.set.Usuario;

public class UsuarioDAO extends GenericDAO {

public static void createUsuario(Usuario ferramentaEstimativa){
	     create(ferramentaEstimativa);
	}
	
	public static void updateUsuario(Usuario ferramentaEstimativa){
	     update(ferramentaEstimativa);
	}
	
	public static void removeUsuario(Usuario ferramentaEstimativa){
	     remove(ferramentaEstimativa);
	}
	
	public static  List<Usuario> findAll(){
		List<Usuario> list = findAll("usuario");
	    return list;
	}
	
	public static Usuario findById(int id)
	{
		for (Usuario ferramentaEstimativa: findAll()){
			if(ferramentaEstimativa.getId() ==id){
				return ferramentaEstimativa;
			}
		}
		return null;
	}
	

	public static Usuario findByLogin(String name)
	{
		Usuario usuario = null;
		for (Usuario user: findAll()){
			if(user.getLogin().equals(name.trim())){
				usuario = user;
				break;
			}
		}
		
		return usuario;
	}

}
