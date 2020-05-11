<%@page import="com.javalec.ex.BDao.BDao2"%>
<%@page import="com.javalec.ex.BDto.BDto2"%>
<%@page import="com.javalec.ex.BDao.BDao"%>
<%@page import="com.javalec.ex.BDto.BDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>


<!-- usebean으로 안됨!! form에서 enctype=multipart/form-data 떄문에.. -->



<%
	


//String path = application.getRealPath("upload");
String path = request.getSession().getServletContext().getRealPath("/upload");




int size = 1024 * 1024 * 10; // 1kb*1kb*10 = 1mb*10 -> 총 10m 용량 제한




String bTitle = "";
String bContent = "";



String bFile = "";
String bFile2 = "";




try {

	
	
	//request, 파일저장경로, 용량, 인코딩타입, 중복파일명에 대한 정책	(Default-File-Rename-Policy)
	MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());

	
	
	//파일 이름 가져오기
	Enumeration files = multi.getFileNames();

	
	
	
	String name1 = (String) files.nextElement();
	bFile = multi.getFilesystemName(name1);
	//session.setAttribute("id", "user") = (name1, value[file])
			
			
			
			
	String name2 = (String) files.nextElement();
	bFile2 = multi.getFilesystemName(name2);		

	
	
	
    bTitle = multi.getParameter("bTitle");
    bContent = multi.getParameter("bContent");
    
    
    
    int bId=Integer.parseInt(multi.getParameter("bId"));
    
    
    
	BDao2 dao2 = new BDao2();

	
	
	//bt데이터 추가메소드 호출
	int check = dao2.modify_ok(bId, bTitle, bContent, bFile, bFile2);

	
	
	if (check == 1) { //성공
		
		
%>



<script type="text/javascript">
	alert('데이터가 수정되었습니다');
	window.location.href = 'e_list.jsp';
</script>



<%
	
	
	} else { //실패
		
		
		
		
%>


<script type="text/javascript">
	alert('데이터 수정 실패');
	history.back();
</script>




<%
	}//else
		
	

} //try



catch (Exception e) {
	e.printStackTrace();
}




%>


<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
</body>
</html>