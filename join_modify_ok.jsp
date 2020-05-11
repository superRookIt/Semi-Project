<%@page import="java.util.Arrays"%>
<%@page import="com.javalec.ex.BDao.BDao3"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
%>

<!-- BDto3 mdto = new BDto3(); -->
<jsp:useBean id="mdto" class="com.javalec.ex.BDto.BDto3"></jsp:useBean>

<jsp:setProperty property="*" name="mdto" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%
	
//현재 시간을 자바에서 입력
//mdto.setB_date(new Timestamp(System.currentTimeMillis()));

BDao3 mdao3 = new BDao3(); 

int check = mdao3.updateMember(mdto);


if (check == 1) {
	
	
	 String id= request.getParameter("id");
	
	 String[] hobby = request.getParameterValues("hobby");
	    
	 String hobby_h = Arrays.toString(hobby);
	    
	 int check_h = mdao3.updateMember_h(id, hobby_h);
	 
	
%>

<script>
alert('회원정보 수정이 완료되었습니다. 메인 페이지로 이동합니다');
window.location.href='main.jsp';
</script>


<%
	}


else { //아이디가 없을 경우

%>

<script>
alert('수정 실패! 다시 시도해 주세요.');
history.back(); 
</script>


<%
	} 

%>

</head>
<body>

</body>
</html>