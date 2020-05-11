package com.javalec.ex.BCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.ex.BDao.BDao;


public class BWritecommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");

		// 객체선언 후 메소드 호출
		BDao dao = new BDao();
		int check = dao.write(bName, bTitle, bContent);

		// bId->시퀸스, bDate->sysdate, bGroup->seq, bStep=0, bIndent=0

	}

}
