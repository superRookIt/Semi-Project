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

//String[] hobbys = request.getParameterValues("hobby");

BDao3 mdao3 = new BDao3(); 

int check = mdao3.confirmId(mdto.getId());

if (check == 1) {
	
	
	
%>


<script type="text/javascript">
	alert('아이디가 존재합니다. 다시 입력하세요')
	history.back();
</script>


<%
	}


else { //아이디가 없을 경우
	
	
    String id= request.getParameter("id");
	
    String[] hobby = request.getParameterValues("hobby");
    
    String hobby_h = Arrays.toString(hobby);
    
    int check_h = mdao3.hobbyMember(id, hobby_h);

	int ch = mdao3.insertMember(mdto);
	
	
	

	if (ch == 1) {//데이터 저장 완료
%>


<script>
alert('회원가입이 완료 되었습니다. 로그인 페이지로 이동합니다');
window.location.href='login.jsp';
</script>

<%
	} 
	
	
	else { //저장 실패 %>

<script>
alert('저장 실패! 다시 시도해 주세요.');
history.back(); 
</script>

		
<% } //저장 실패

	
	
} ////아이디가 없을 경우
%>

</head>
<body>

</body>
</html>