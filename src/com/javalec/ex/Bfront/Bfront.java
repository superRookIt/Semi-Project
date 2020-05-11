package com.javalec.ex.Bfront;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.ex.BCommand.BCommand;
import com.javalec.ex.BCommand.BContentcommand;
import com.javalec.ex.BCommand.BDeletecommand;
import com.javalec.ex.BCommand.BListcommand;
import com.javalec.ex.BCommand.BModifyOkcommand;
import com.javalec.ex.BCommand.BModifycommand;
import com.javalec.ex.BCommand.BReplyOkcommand;
import com.javalec.ex.BCommand.BReplycommand;
import com.javalec.ex.BCommand.BWritecommand;
import com.javalec.ex.BCommand.EDeletecommand;
import com.javalec.ex.BCommand.EModifycommand;
import com.javalec.ex.BCommand.MModifycommand;
import com.sun.net.httpserver.HttpsServer;

@WebServlet("*.do")
public class Bfront extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Bfront() {

	}// 생성자

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("doGet으로 들어옴");
		actionDo(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("doPost로 들어옴");
		actionDo(request, response);

	}

	protected void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("actionDo들어옴");

		request.setCharacterEncoding("utf-8");

		String pageView = null;

		BCommand bcom = null;

		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());

		if (com.equals("/list.do")) {

			//객체 선언
			//메소드 호출
			bcom = new BListcommand();
			bcom.execute(request, response);
			pageView = "list.jsp";
			
		}
		
		else if (com.equals("/content_view.do")) {

			bcom = new BContentcommand();
			bcom.execute(request, response);
			pageView = "content_view.jsp";

		}

		else if (com.equals("/write_view.do")) {

			pageView = "write_view.jsp";

		} // 리스트에서

		else if (com.equals("/write.do")) {

			bcom = new BWritecommand();
			bcom.execute(request, response);
			pageView = "list.do";

		} 

		else if (com.equals("/delete.do")) {

			bcom = new BDeletecommand();
			bcom.execute(request, response);
			pageView = "list.do";

		}

		else if (com.equals("/modify.do")) {

			bcom = new BModifycommand();
			bcom.execute(request, response);
			pageView = "modify_view.jsp";

		}

		else if (com.equals("/modify_ok.do")) {

			bcom = new BModifyOkcommand();
			bcom.execute(request, response);
			pageView = "list.do";

		}
		
		else if (com.equals("/reply.do")) {

			bcom = new BReplycommand();
			bcom.execute(request, response);
			pageView = "reply_view.jsp";

		}
		
		else if (com.equals("/reply_ok.do")) {

			bcom = new BReplyOkcommand();
			bcom.execute(request, response);
			pageView = "list.do";
			
		}
		
		
		else if (com.equals("/e_delete.do")) {

			bcom = new EDeletecommand();
			bcom.execute(request, response);
			pageView = "e_list.jsp";

		}
		
		
		else if (com.equals("/e_modify.do")) {

			bcom = new EModifycommand();
			bcom.execute(request, response);
			pageView = "e_modify.jsp";

		}
		
		
		else if (com.equals("/m_modify.do")) {

			bcom = new MModifycommand();
			bcom.execute(request, response);
			pageView = "join_modify.jsp";

		}
		
		
		
		
		RequestDispatcher dispatch = request.getRequestDispatcher(pageView);
		dispatch.forward(request, response);
		

	}// actionDo

}// class
