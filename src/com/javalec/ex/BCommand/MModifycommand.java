package com.javalec.ex.BCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.ex.BDao.BDao3;
import com.javalec.ex.BDto.BDto3;
import com.javalec.ex.BDto.BDto3_h;

public class MModifycommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		
		
		
		String id = request.getParameter("id");
		
		
		
		
		
		BDao3 dao3= new BDao3();
		
		
		
		
		
		BDto3 dto3 = new BDto3();
		
		BDto3_h dto3_h = new BDto3_h();
		
		
		
		
		
		dto3 = dao3.m_modify(id);
		
		dto3_h = dao3.m_modify_h(id);
		
		
		
		
		
		request.setAttribute("member", dto3 );
		
		request.setAttribute("hobby", dto3_h);
		

	}

}
