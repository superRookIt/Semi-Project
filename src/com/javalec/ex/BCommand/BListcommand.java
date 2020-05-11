package com.javalec.ex.BCommand;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.ex.BDao.BDao;
import com.javalec.ex.BDto.BDto;

public class BListcommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		
		int page = 1; // 최초기본 1페이지 세팅
		
		int limit = 10; // 1page당 게시글 10개

		
	    String searchflag = request.getParameter("searchflag"); // 검색체크

	    
		String opt = request.getParameter("option"); // 전체, 제목, 내용
		String search = request.getParameter("searching"); // 검색어

		
		// if(opt==null) opt="";
		// if(search==null) search="";

		
		
		// 넘어온 page가 있을때 - 예)4페이지
		if (request.getParameter("page") != null) {

			page = Integer.parseInt(request.getParameter("page"));

		}
		
		

		// 객체선언 후 메소드 호출
		BDao dao = new BDao();

		
		
		// 전체 게시글 count(*)
		int listcount = dao.getlistCount(opt, search);

		
		// 최대 페이지 수
		int maxpage = (int) ((double) listcount / limit + 0.95);
		// int maxpage = ((int)((double)page/10+0.9)-1)*10;

		
		// 처음페이지
		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;

		
		// 마지막페이지
		int endpage = maxpage; // 1~10까지는 maxpage가 endpage가 됨
		if (endpage > startpage + 10 - 1)
			
			endpage = startpage + 10 - 1;

		// 페이지별 리스트 개수 가져오기
		ArrayList<BDto> dtos = dao.list(page, limit, opt, search);
		
		
		request.setAttribute("list", dtos);

		request.setAttribute("listcount", listcount);
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);

		
		if (search != "") {
			
			searchflag = "1";
			request.setAttribute("searchflag", searchflag);
			request.setAttribute("opt", opt);
			request.setAttribute("search", search);

		}

		
		System.out.println("listcount : " + listcount);
		System.out.println("page : " + page);
		System.out.println("maxpage : " + maxpage);
		System.out.println("startpage : " + startpage);
		System.out.println("endpage : " + endpage);

	}

}
