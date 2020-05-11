<%@page import="com.javalec.ex.BDto.BDto3"%>
<%@page import="com.javalec.ex.BDao.BDao3"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	
//form request에서 넘어온 거 한글로 인코딩처리
request.setCharacterEncoding("utf-8");

String id = request.getParameter("id");
String pw = request.getParameter("pw");

BDao3 mdao = new BDao3();

int check = mdao.userCheck(id, pw); //리턴값 1,0,-1

%>

<!DOCTYPE html>
<html>
<head>

<%
	if (check == -1) {
%>

<script type="text/javascript">
	alert("아이디가 존재하지 않습니다. 다시 입력하세요.");
	history.back();
</script>


<%
	} else if (check == 0) {
%>

<script type="text/javascript">
	alert("패스워드가 일치하지 않습니다. 다시 입력하세요.");
	history.back();
</script>


<%
	} else {

		
	BDto3 mdto = mdao.getMember(id);

	session.setAttribute("id", mdto.getId());
	session.setAttribute("name", mdto.getName());
	session.setAttribute("authUser", id);
	response.sendRedirect("main.jsp");

}
%>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript" src="js/custom.js"></script>

</body>
</html>