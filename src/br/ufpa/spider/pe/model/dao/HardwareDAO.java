package br.ufpa.spider.pe.model.dao;

import java.util.List;

import br.ufpa.spider.pe.model.Hardware;

public class HardwareDAO extends GenericDAO {
	public static void createHardware(Hardware hardware){
	     create(hardware);
	}
	
	public static void updateHardware(Hardware hardware){
	     update(hardware);
	}
	
	public static void removeHardware(Hardware hardware){
	     remove(hardware);
	}
	
	public static  List<Hardware> findAll(){
		List<Hardware> list = findAll("hardware");
	    return list;
	}
	
	
	public static Hardware findByName(String name)
	{
		Hardware hardware = null;
		for (Hardware user: findAll()){
			if(user.getNome().contains(name.trim())){
				hardware = user;
				break;
			}
		}
		
		return hardware;
	}
	
	public static Hardware findById(int id)
	{
		for (Hardware hardware: findAll()){
			if(hardware.getId() ==id){
				return hardware;
			}
		}
		return null;
	}
}
