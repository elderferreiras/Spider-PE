package br.ufpa.spider.pe.controller;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Hardware;
import br.ufpa.spider.pe.model.Hardware;
import br.ufpa.spider.pe.model.dao.HardwareDAO;
import br.ufpa.spider.pe.model.dao.HardwareDAO;

public class HardwareController {

	public static void saveHardware(Hardware hardware) {
		try {
			if (hardware.getId() == 0) {
				HardwareDAO.createHardware(hardware);
				JOptionPane.showMessageDialog(null, "Hardware cadastrado.");
			} else {
				HardwareDAO.updateHardware(hardware);
				JOptionPane.showMessageDialog(null, "Hardware atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void saveHardware(Hardware hardware, boolean msg) {

		try {
			if (hardware.getId() == 0) {
				HardwareDAO.createHardware(hardware);
				if(msg)
					JOptionPane.showMessageDialog(null, "Hardware cadastrado.");
			} else {
				HardwareDAO.createHardware(hardware);
				if(msg)
					JOptionPane.showMessageDialog(null, "Hardware atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
