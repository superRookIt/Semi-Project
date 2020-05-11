<%@page import="com.javalec.ex.BDto.BDto2"%>
<%@page import="com.javalec.ex.BDao.BDao2"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>


<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900&display=swap&subset=korean" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/read.css">

</head>

<body>
	<header>
		<ul>
			<li>회원가입</li>
			<span></span>
			<li>로그인</li>
			<span></span>
			<li>고객행복센터</li>
			<span></span>
			<li>배송지역검색</li>
			<span></span>
			<li>기프트카드 등록</li>
		</ul>
	</header>

	<nav>
		<div class="logo"></div>

		<div id="search">
			<div class="search"></div>
			<br> <span>메뉴찾기</span>
		</div>

		<div id="cart">
			<div class="cart"></div>
			<br> <span>장바구니</span>
		</div>

		<div class="nav-menu">
			<ul>
				<li>COOKIT소개</li>
				<li>COOKIT 메뉴</li>
				<li>리뷰</li>
				<li>이벤트</li>
				<li>MY쿡킷</li>
			</ul>
		</div>
	</nav>

	<section>
		
		
		<h1>EVENT</h1>
		
		
<%
		
		
int bId=Integer.parseInt(request.getParameter("bId"));
	
ArrayList list = new ArrayList();

BDao2 ba2= new BDao2();

BDto2 dto2 = ba2.getMember(bId);

BDto2 dto2_p = ba2.pregetMember(bId);

BDto2 dto2_n = ba2.nextgetMember(bId);


%>


		<table>
			<tr>
				<th><%=dto2.getbTitle() %></th>
			</tr>
			
			<tr>
				<td><%=dto2.getbDate() %>~<%=dto2.getbDate2() %></td>
			</tr>
			
			<tr>
				<td class="article">
				 <%=dto2.getbContent() %>
				<img src="upload/<%=dto2.getbFile() %>" width="80%"><%=dto2.getbFile() %>
				<img src="upload/<%=dto2.getbFile2() %>" width="80%"><%=dto2.getbFile2() %>
			</tr>
			
			<tr>
				<td>
				<strong>다음글</strong> 
				<span class="separator">|</span> <a href="e_view.jsp?bId=<%=dto2_p.getbId()%>"><%=dto2_p.getbTitle() %></a>
				</td>
			</tr>
			
			<tr>
				<td>
				<strong>이전글</strong> 
				<span class="separator">|</span> <a href="e_view.jsp?bId=<%=dto2_n.getbId()%>"><%=dto2_n.getbTitle() %></a>
				</td>
			</tr>
		</table>
		
		
		 <%  if (session.getAttribute("authUser") == null) {
					
					
				} 
				
				
				 else if (session.getAttribute("authUser").equals("admin")) {
					 
				%>

					<a href="e_list.jsp">
					<div class="list">목록</div>
					</a>
		
		             <a href="javascript:check();">
			         <div class="list">삭제</div>
		             </a> 
		
	                 <a href="javascript:modify()">
	                 <div class="list">수정</div>
	                 </a>

				<%
					} 
				 
				 else{
					
				 
						
						
					}
               
				%>
		
		

		
		
		
		
		
	</section>
	
	
<script type="text/javascript">

	function modify() {

		window.location.href = "e_modify.do?bId=" +<%=bId%>;

	}

	function check() {

		var result = confirm('데이터를 삭제 하시겠습니까?');

		if (result == true) {

			alert("삭제하겠습니다");
			window.location.href = 'e_delete.do?bId=' +<%=bId%>;
			
			
		} else {
			alert("삭제 취소");
		}

	}
	
</script>
	
	<footer>
		<div class="wrapper">
			<div class="footer-left">
				<div class="footer-logo"></div>
				<div class="copyright">© COOKIT ALL RIGHTS RESERVED</div>
			</div>

			<div class="footer-center">
				<ul class="footer-nav">
					<li class="first-list">이용약관</li>
					<li class="green">개인정보처리 방침</li>
					<li>법적고지</li>
					<li>사업자정보 확인</li>
				</ul>

				<ul class="footer-info">
					<li class="first-list">씨제이제일제당(주)</li>
					<li>대표이사 : 손경식,강신호,신현재</li>
					<li>사업자등록번호 : 104-86-09535</li>
					<li class="first-list">주소 : 서울 중구 동호로 330 CJ제일제당 센터 (우) 04560</li>
					<li>통신판매업신고 중구 제 07767호</li>
					<li class="first-list">개인정보보호책임자 : 조영민</li>
					<li>이메일 : cjon@cj.net</li>
					<li>호스팅제공자 : CJ올리브네트웍스㈜</li>
				</ul>

				<div id="check">
					고객님은 안전거래를 위해 현금등으로 결제시 LG U+ 전자 결제의 매매보호(에스크로) 서비스를 이용하실 수 있습니다. <span
						class="check">가입 사실 확인</span>
				</div>
			</div>

			<div class="footer-right">
				<div id="shortcut">
					<span>CJ그룹계열사 바로가기</span>
					<div class="shortcut"></div>
				</div>

				<div class="call">고객행복센터 1668-1920</div>
				<div class="inquery">1:1 문의</div>
			</div>

		</div>
	</footer>
</body>
</html>