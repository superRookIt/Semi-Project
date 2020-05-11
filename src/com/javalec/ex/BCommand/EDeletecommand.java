package com.javalec.ex.BCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.ex.BDao.BDao2;

public class EDeletecommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		int bId = Integer.parseInt(request.getParameter("bId"));

		BDao2 dao2 = new BDao2();

		int check = dao2.delete(bId);

	}

}
