<%@page import="com.javalec.ex.BDao.BDao2"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.javalec.ex.BDto.BDto2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>


<html lang="ko">

<head>

<meta charset="UTF-8"
>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Document</title>

<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900&display=swap&subset=korean" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/event_list.css">

</head>

<body>


<%

int page2 = 1; // 최초기본 1페이지 세팅

int limit = 10; // 1page당 게시글 10개


if (request.getParameter("page") != null) {

	page2 = Integer.parseInt(request.getParameter("page"));

}

        //객체선언 후 메소드 호출

		BDao2 dao2 = new BDao2();

		// 전체 게시글 count(*)
		int listcount = dao2.getlistCount();

		// 최대 페이지 수
		int maxpage = (int) ((double) listcount / limit + 0.95);
		// int maxpage = ((int)((double)page/10+0.9)-1)*10;

		// 처음페이지
		int startpage = (((int) ((double) page2 / 10 + 0.9)) - 1) * 10 + 1;

		// 마지막페이지
		int endpage = maxpage; // 1~10까지는 maxpage가 endpage가 됨
		if (endpage > startpage + 10 - 1)
			endpage = startpage + 10 - 1;

		// 페이지별 리스트 개수 가져오기
		ArrayList<BDto2> dtos = dao2.list(page2,limit);

		request.setAttribute("list", dtos);

		request.setAttribute("listcount", listcount);
		request.setAttribute("page", page2);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);

%>


<%
	
ArrayList list = new ArrayList();

BDao2 ba2= new BDao2();

list = ba2.getmembers();

%>

	<header>
	
		<ul id="me1">
		
			<a href="join.jsp"><li>회원가입</li></a>
			
				
				<!-- 세션이 있는지 확인 -->
				<%
					if (session.getAttribute("authUser") == null) {
				%>

				<li><a href="login.jsp">로그인</a></li>

				<%
					} // 세션 없을 경우
					
					else { //세션 있을 경우
				%>

				<li>
				
				<a href="m_modify.do?id=<%=session.getAttribute("id")%>">
				
				<%=session.getAttribute("name")%>님  </a>
				
				<a href="logout.jsp">로그아웃</a></li>

				<%
					}
				%>
				
			<li>고객행복센터</li>
			<li>배송지역검색</li>
			<li>기프트카드 등록</li>
			
		</ul>
		
	</header>

	<nav>
	
		<a href="main.jsp"><div class="logo"></div></a>


		<div id="search">
			<div class="search"></div>
			<br> <span>메뉴찾기</span>
		</div>

		<div id="cart">
			<div class="cart"></div>
			<br> <span>장바구니</span>
		</div>


		<div class="nav-menu">
			<ul id="me2">
				<li>COOKIT소개</li>
				<li>COOKIT 메뉴</li>
				<li>리뷰</li>
				<a href="e_list.jsp"><li>이벤트</li></a>
				<li>MY쿡킷</li>
			</ul>
		</div>
	</nav>

	<section>
		<h1>EVENT</h1>

		<article id="event_list">
		
	<ul>
	

	<%
			for (int i = 0; i < list.size(); i++) {

		     BDto2 bt = (BDto2) list.get(i);
			
		%>
		            
		            <li>
		            <img src="upload/<%=bt.getbFile() %>" width="10%">
		            
					<div class="event-now"><%=bt.getbId() %></div>
					<div class="event-title"> <a href="e_view.jsp?bId=<%=bt.getbId()%>"><%=bt.getbTitle() %></a></div>
					<div class="event-date"><%=bt.getbDate() %>~~<%=bt.getbDate2() %></div>
					</li>
			
		<%
			}
		%>
			
			</ul>
			
		</article>





		<ul class="page-num" id="pagee">


				<!-- 첫페이지 이동 -->
				<a href="e_list.jsp?page=1"><li class="first"></li></a>

				<!-- 이전페이지로 이동 -->

				<c:if test="${page2<=1}">
					<li class="prev"></li>
				</c:if>


				<c:if test="${page2>1}">
					<a href="e_list.jsp?page=${page2-1}"><li class="prev"></li></a>
				</c:if>

				<!-- 순차적 페이지 출력  -->

				<c:forEach var="a" begin="${startpage}" end="${endpage}" step="1">


					<c:choose>

					
						<c:when test="${a == page2}">
							<li class="num">
								<div>${a}</div>
							</li>
						</c:when>



						<c:when test="${a != page2}">
						
							<a href="e_list.jsp?page=${a}">
								<li class="num">
								<div>${a}</div>
								</li>
							</a>
						</c:when>

					</c:choose>
					
					

				</c:forEach>


				<!-- 다음페이지 이동 -->
				<c:if test="${page2>=maxpage }">
					<li class="next"></li>
				</c:if>

				<c:if test="${page2<maxpage} ">
					<a href="e_list.jsp?page=${page2+1}"><li class="next"></li></a>
				</c:if>


				<!-- 마지막 페이지 이동 -->
				<a href="e_list.jsp?page=${maxpage}"><li class="last"></li></a>
				
				</ul>
				
				
				
				
				
				
				
				
				<%  if (session.getAttribute("authUser") == null) {
					
					
				} 
				
				
				 else if (session.getAttribute("authUser").equals("admin")) {
					 
				%>

					<a href="e_write.jsp"><div class="write">쓰기</div></a>

				<%
					} 
				 
				 else{
					
						
					}
				%>
				
		

	</section>





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