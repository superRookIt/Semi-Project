package com.javalec.ex.BCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.ex.BDao.BDao2;
import com.javalec.ex.BDto.BDto2;

public class EModifycommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		int bId = Integer.parseInt(request.getParameter("bId"));

		BDao2 dao2 = new BDao2();

		BDto2 dto2 = dao2.modify(bId);

		request.setAttribute("modify", dto2);

	}

}
