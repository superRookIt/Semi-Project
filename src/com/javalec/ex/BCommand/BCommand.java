package com.javalec.ex.BCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BCommand {
	
	//메소드 execute 선언
	
	public void execute(HttpServletRequest request, HttpServletResponse response); 

	
}
