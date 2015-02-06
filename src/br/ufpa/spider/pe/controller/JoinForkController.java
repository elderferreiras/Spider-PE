package br.ufpa.spider.pe.controller;

import br.ufpa.spider.pe.model.JoinFork;
import br.ufpa.spider.pe.model.dao.JoinForkDAO;

public class JoinForkController {

	public static void savejoinFork(JoinFork joinFork) {

		try {
			if (joinFork.getId() == 0) {
				JoinForkDAO.createJoinFork(joinFork);
			} else {
				JoinForkDAO.updateJoinFork(joinFork);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
