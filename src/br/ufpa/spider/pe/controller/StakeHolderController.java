package br.ufpa.spider.pe.controller;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.model.Stakeholder;
import br.ufpa.spider.pe.model.Stakeholder;
import br.ufpa.spider.pe.model.dao.StakeholderDAO;
import br.ufpa.spider.pe.model.dao.StakeholderDAO;
import br.ufpa.spider.pe.model.dao.PoliticaOrganizacionalDAO;

public class StakeHolderController {

	public static void saveStakeHolder(Stakeholder stakeHolder) {

		try {
			if (stakeHolder.getId() == 0) {
				StakeholderDAO.createStakeholder(stakeHolder);
				JOptionPane.showMessageDialog(null, "Stakeholder cadastrado.");
			} else {
				StakeholderDAO.updateStakeholder(stakeHolder);
				JOptionPane.showMessageDialog(null, "Stakeholder atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void saveStakeHolder(Stakeholder stakeholder, boolean msg) {

		try {
			if (stakeholder.getId() == 0) {
				StakeholderDAO.createStakeholder(stakeholder);
				if(msg)
					JOptionPane.showMessageDialog(null, "Stakeholder cadastrado.");
			} else {
				StakeholderDAO.createStakeholder(stakeholder);
				if(msg)
					JOptionPane.showMessageDialog(null, "Stakeholder atualizado.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
