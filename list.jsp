<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900&display=swap&subset=korean" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/notice_list.css">


<meta charset="UTF-8">

<title>Insert title here</title>


</head>

<body>

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
				<a href="e_list.jsp"><li>이벤트</li></a>
				<li>MY쿡킷</li>
			</ul>
		</div>
	</nav>

	<section>
		<h1>NOTICE</h1>
		<div class="wrapper">

			<form action="list.do" name="search" method="post">
				<select name="option" id="category">
					<option value="all">전체</option>
					<option value="title">제목</option>
					<option value="con">내용</option>
				</select>

				<div class="search">
					<input type="text" size="16" name="searching" id="searching">
				</div>

				<button type="submit">
					<i class="fas fa-search"></i>
				</button>
			</form>
			
		</div>

		<table>
		
		
			<colgroup>
				<col width="18%">
				<col width="60%">
				<col width="18%">
			</colgroup>
			
			
			<tr>
				<th>No.</th>
				<th>제목</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>


			<c:forEach var="dto" items="${list}">
			

				<tr>
				
				
					<td> <span class="table-notice"> ${dto.bId} </span> </td>


					<td class="table-title">
					
					
					<c:forEach begin="1" end="${dto.bIndent}"> → </c:forEach>
					
					
					<a href="content_view.do?bId=${dto.bId }"> ${dto.bTitle} </a>
					
					
					 </td>
						
						
					<td>${dto.bDate}</td>
					
					<td>${dto.bHit }</td>
					
					
				</tr>


			</c:forEach>
			

		</table>


		<c:choose>
			
			
				<c:when test="${searchflag != null }">
				
				
				<ul class="page-num">

					<a href="list.do?searchflag=${searchflag}&opt=${opt }&search=${search }&page=1">
					<li class="first"></li>
					</a>


					<c:if test="${page<=1}">
						<li class="prev"></li>
					</c:if>


					<c:if test="${page>1}">
						<a href="list.do?searchflag=${searchflag}&opt=${opt }&search=${search }&page=${page-1}">
						<li class="prev"></li>
						</a>
					</c:if>



					<c:forEach var="a" begin="${startpage}" end="${endpage}" step="1">

						<c:choose>

							<c:when test="${a == page}">
							
								<li class="num">
									<div>${a}</div>
								</li>
								
							</c:when>

							<c:when test="${a != page}">
							
								<a href="list.do?searchflag=${searchflag}&opt=${opt }&search=${search }&page=${a}">
									<li class="num">
									<div>${a}</div>
									</li>
								</a>
								
							</c:when>
							


						</c:choose>
						
					</c:forEach>



					<c:if test="${page>=maxpage }">
						<li class="next"></li>
					</c:if>



					<c:if test="${page<maxpage} ">
						<a href="list.do?searchflag=${searchflag}&opt=${opt }&search=${search }&page=${page+1}">
						<li class="next"></li>
						</a>
					</c:if>
					

					<a href="list.do?searchflag=${searchflag}&opt=${opt }&search=${search }&page=${maxpage}">
					<li class="last">
					</li>
					</a>
			
			</ul>
			
			
			</c:when>
			
			

			<c:otherwise>
			
			
			<ul class="page-num">


				<a href="list.do?page=1"><
				li class="first"></li>
				</a>


				<c:if test="${page<=1}">
					<li class="prev"></li>
				</c:if>
				

				<c:if test="${page>1}">
					<a href="list.do?page=${page-1}">
					<li class="prev"></li>
					</a>
				</c:if>
				


				<c:forEach var="a" begin="${startpage}" end="${endpage}" step="1">

					<c:choose>


						<c:when test="${a == page}">
							<li class="num">
								<div>${a}</div>
							</li>
						</c:when>


						<c:when test="${a != page}">
							<a href="list.do?page=${a}">
								<li class="num">
								<div>${a}</div>
								</li>
							</a>
						</c:when>

					</c:choose>
					
					
				</c:forEach>



				<c:if test="${page>=maxpage }">
					<li class="next"></li>
				</c:if>



				<c:if test="${page<maxpage} ">
					<a href="list.do?page=${page+1}"><li class="next"></li></a>
				</c:if>


				<a href="list.do?page=${maxpage}">
				<li class="last"></li>
				</a>
				
				
				</ul>


			</c:otherwise>


		</c:choose>


               <%  if (session.getAttribute("authUser") == null) {
					
					
				} 
				
				
				 else if (session.getAttribute("authUser").equals("admin")) {
					 
				%>

					<a href="write_view.do"><div class="write">쓰기</div></a>

				<%
					
				 } 
				 
               
				 else {
					
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