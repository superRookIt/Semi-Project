<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html>


	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="css/m_style.css">
		
		
		
		
		<title>회원정보 수정</title>
	</head>
	
	
	<body>
	
		<header>
		
			<div id="nav_up">
				<ul>
					<li><a href="join.jsp">회원가입</a></li>
					<li><a href="login.jsp">로그인</a></li>
					<li><a href="#">고객행복센터</a></li>
					<li><a href="#">배송지역검색</a></li>
					<li><a href="#">기프트카드 등록</a></li>
				</ul>
			</div>	
			
			
			<nav>
			
				<a href="main.jsp"></a>
				<ul>
					<li><a href="#">COOKIT소개</a></li>
					<li><a href="#">COOKIT메뉴</a></li>
					<li><a href="#">리뷰</a></li>
					<li><a href="e_list.jsp">이벤트</a></li>
					<li><a href="#">MY쿡킷</a></li>
				</ul>
				<ul>
					<li>
						<a href="#"><span>장바구니</span></a>
					</li>
					<li>
						<a href="#"><span>메뉴찾기</span></a>
					</li>
				</ul>
			</nav>
			
			
			
			
		</header>


	<section>
	
	
		<form name="m_join" method="post" action="join_modify_ok.jsp">
		
			<div id="subBanner"></div>
			
			<div id="locationN">
			
				<ul>
					<li>HOME</li>
					<li>회원가입</li>
					<li>회원정보입력</li>
				</ul>
				
			</div>





			<div id="sub_top_area">
			
				<h3>회원정보 수정</h3>
				
			</div>
			


			<h4>
				필수 정보 입력 <span>(* 항목은 필수 항목입니다.)</span>
			</h4>
			
			
			
			
			
			<fieldset class="fieldset_class">
				
				
				
				
				
				
				
				<dl id="join_name_dl">
				
					<dt>
						<div></div>
						<label for="name">이름</label>
					</dt>
					
					
					<dd>
						<input type="text" id="name" name="name" value="${member.name }" required readonly="readonly"/>
					</dd>
					
				</dl>
				
				
				<dl id="join_id_dl">
				
				
					<dt>
						<div></div>
						<label for="id">아이디</label>
					</dt>
					
					
					<dd>
						<input type="text" id="id" name="id" minlength="4" maxlength="16" value="${member.id}"  readonly="readonly" required/> 
						
						<input type="button" value="중복확인" onclick="idcheck()" >	
						
						<span>4~16자리의 영문, 숫자, 특수기호(_)만 사용하실 수 있습니다.</span> 
						
						<span>첫 글자는 영문으로 입력해 주세요.</span>
						
					</dd>
					
					
				</dl>
				
				
				<dl id="join_pw1_dl">
					<dt>
						<div></div>
						<label for="pw1">비밀번호</label>
					</dt>
					
					
					<dd>
						<input type="password" id="pw1" name="pw" minlength="8" required />
						<span>영문, 숫자, 특수문자 중 2종류 조합 시 10자리 이상 입력</span> 
						<span>영문, 숫자, 특수문자 모두 조합 시 8자리 이상 입력</span>
					</dd>
				</dl>
				
				
				
				
				
				<dl id="join_pw2_dl">
				
					<dt>
						<div></div>
						<label for="pw2">비밀번호 확인</label>
					</dt>
					
					
					
					
					
					
					<dd>
						<input type="password" id="pw2" name="pw2" minlength="8" required />
						<span>비밀번호를 다시 한번 입력해 주세요.</span>
					</dd>
				</dl>
				
				
				
				
				
				<dl id="join_mail_dl">
					<dt>
						<div></div>
						<label for="mail_id">이메일</label>
					</dt>
					
					<dd>
						<input type="text" id="mail_id" name="email" value="${member.email}" required />
					</dd>
				</dl>
				


				<dl id="join_address_dl">
					<dt>
						<div></div>
						<label for="">주소</label>
					</dt>
					<dd>
					 <input type="text" id="address1" name="address" value="${member.address }" required />
					</dd>

				</dl>
				
				
				
				<dl id="join_tell_dl">
				
					<dt>
						<div></div>
						<label for="f_tell">휴대전화</label>
					</dt>
					
					<dd>
						<input type="text" id="f_tell" name="phone" maxlength="16" value="${member.phone }" required />
					</dd>
				</dl>



				<dl id="join_birth_dl">
				
					<dt>
						<div></div>
						<label for="birth_year">생년월일</label>
					</dt>
					
					<dd>
						<input type="date" name="day" value="${member.day }">
					</dd>
					
				</dl>

				
				
				<dl id="join_gender_dl">
				
				
					<dt>
						<div></div>
						<label for="">성별</label>
					</dt>
					
					
					<c:choose>
					
					<c:when test="${member.gender eq 'male' }">
					
					<dd>
						<div>
						
							<input type="radio" name="gender" id="male" value="male" checked="checked" readonly="readonly" /> 
							
							<label for="male">남성</label> 
								
							<input type="radio" name="gender" id="female" value="female" readonly="readonly"/> 
								
							<label for="female">여성</label>
							
						</div>
					</dd>
					
					</c:when>
				
				
				<c:otherwise>
				
				     <dd>
						<div>
						
							<input type="radio" name="gender" id="male" value="male" readonly="readonly" /> 
							
							<label for="male">남성</label> 
								
							<input type="radio" name="gender" id="female" value="female" checked="checked" readonly="readonly"/> 
								
							<label for="female">여성</label>
							
						</div>
					</dd>
				
				</c:otherwise>
					
				</c:choose>
				
				</dl>
				
				
				
				
				
				<dl id="join_newsletter_dl">
				
				
				
					<dt>
						<div></div>
						<label for="">뉴스레터 수신여부</label>
					</dt>
					
					
					
					<c:choose>
					
					<c:when test="${member.news eq 'yes' }">
					
					
					<dd>
						<span>이메일을 통한 상품 및 이벤트 정보 수신에 동의합니다.</span>
						
						<div>
						
							<input type="radio" name="news" id="newletter_y" value="yes" checked="checked" /> 
							<label for="newletter_y">예</label> 
								
							<input type="radio" name="news" id="newletter_n" value="no" /> 
							<label for="newletter_n">아니오</label>
							
						</div>
						
					</dd>
					
					
					</c:when>
					
					
					
					
					
					<c:otherwise>
					
					
					<dd>
						<span>이메일을 통한 상품 및 이벤트 정보 수신에 동의합니다.</span>
						
						<div>
						
							<input type="radio" name="news" id="newletter_y" value="yes"  /> 
							<label for="newletter_y">예</label> 
								
							<input type="radio" name="news" id="newletter_n" value="no" checked="checked"/> 
							<label for="newletter_n">아니오</label>
						</div>
						
						
					  </dd>
					  
					
					</c:otherwise>
					
					
					</c:choose>
					
					
				</dl>
				
				
				
				
				
				
				<dl id="join_sms_dl">
				
				
					<dt>
						<div></div>
						<label for="">SMS 수신여부</label>
					</dt>
					
						
				<c:choose>
					
					
					<c:when test="${member.sms eq 'yes' }">
					
					
					<dd>
						<span>이메일을 통한 상품 및 이벤트 정보 수신에 동의합니다.</span>
						<div>
							<input type="radio" name="sms" id="sms_y" value="yes" checked="checked" /> 
							<label for="sms_y">예</label> 
							
							<input type="radio" name="sms" id="sms_n" value="no" /> 
							<label for="sms_n">아니오</label>
						</div>
					</dd>
					
					
					</c:when>
					
					
					<c:otherwise>
					
					
					<dd>
						<span>이메일을 통한 상품 및 이벤트 정보 수신에 동의합니다.</span>
						<div>
							<input type="radio" name="sms" id="sms_y" value="yes"  /> 
							<label for="sms_y">예</label> 
							
							<input type="radio" name="sms" id="sms_n" value="no" checked="checked" /> 
							<label for="sms_n">아니오</label>
						</div>
					</dd>
					
					</c:otherwise>
					
					
					</c:choose>
					
				</dl>
				
				
				
				
				
				<c:choose>
					
					<c:when test="${member.gender eq 'male' }">
					
					
					
					</c:when>
				
				
				<c:otherwise>
				
				
				</c:otherwise>
					
				</c:choose>
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				<dl id="join_interests_dl">
				
						<dt>
						<div></div>
						<label for="">관심사</label>
					     </dt>
					
						<dd>
							<ul>

							<c:choose>

								<c:when test="${fn : contains(hobby.hobby, 'computer')}">

                                  <li>
									<input type="checkbox" name="hobby" id="computer" value="computer" checked="checked"/>
									<label for="computer">컴퓨터/인터넷</label>
								 </li>
							


								</c:when>


								<c:otherwise>
								
								
								<li>
									<input type="checkbox" name="hobby" id="computer" value="computer"/>
									<label for="computer">컴퓨터/인터넷</label>
								 </li>

								</c:otherwise>



							</c:choose>
							
							
							
							<c:choose>

								<c:when test="${fn : contains(hobby.hobby, 'movie')}">

                                 <li>
									<input type="checkbox" name="hobby" id="movie" value="movie" checked="checked" />
									<label for="movie">영화/비디오</label>
								</li>
							


								</c:when>


								<c:otherwise>
								
								
								<li>
									<input type="checkbox" name="hobby" id="movie" value="movie" />
									<label for="movie">영화/비디오</label>
								</li>

								</c:otherwise>



							</c:choose>
							
							
							
							
							<c:choose>

								<c:when test="${fn : contains(hobby.hobby, 'music')}">

                                 <li>
									<input type="checkbox" name="hobby" id="music" value="music" checked="checked" />
									<label for="music">음악</label>
								</li>
							


								</c:when>


								<c:otherwise>
								
								
								<li>
									<input type="checkbox" name="hobby" id="music" value="music" />
									<label for="music">음악</label>
								</li>

								</c:otherwise>



							</c:choose>
							
							
							
							
							
							
							<c:choose>

								<c:when test="${fn : contains(hobby.hobby, 'shopping')}">

                               
								<li>
									<input type="checkbox" name="hobby" id="shopping" value="shopping" checked="checked" />
									<label for="shopping">쇼핑</label>
								</li>
							


								</c:when>


								<c:otherwise>
								
								
								
								<li>
									<input type="checkbox" name="hobby" id="shopping" value="shopping" />
									<label for="shopping">쇼핑</label>
								</li>

								</c:otherwise>



							</c:choose>
							
							

								
							</ul>
						</dd>
					</dl>
				
				
				
				
				
				
			</fieldset>
			
			
			
			<div id="info_input_button">
			
				<input type="reset" value="취소하기"> 
				<input type="button" value="수정하기" onclick="mcheck()">
				
				
			</div>

		</form>
		
	</section>







<script>




		function mcheck() {
			

			if (m_join.pw.value == "") {
				alert('비밀번호를 입력해주세요')
				return false;
			}

			if (!(m_join.pw.value == m_join.pw2.value)) {
				alert('비밀번호가 일치하지 않습니다');
				return false;
			}
			
			
			if (!(m_join.pw.value == "${member.pw}" )){
				
				alert('기존 비밀번호가 일치하지 않습니다');
				return false;
				
			}
			

			return m_join.submit();

		}
		
		
		
		</script>










	<footer>
		<div id="footer_wrap">
			<div id="footer_cont">
				<div id="fl_l">
					<a href="#"></a>
					<p>© COOKIT ALL RIGHTS RESERVED</p>
				</div>
				<div id="fl_c">
					<ul>
						<li><a href="#">이용약관</a></li>
						<li><a href="#">개인정보처리 방침</a></li>
						<li><a href="#">법적고지</a></li>
						<li><a href="#">사업자정보 확인</a></li>
					</ul>
					<div id="fl_c_info">
						<p>씨제이제일제당(주)</p>
						<p>대표이사 : 손경식,강신호,신현재</p>
						<p>사업자등록번호 : 104-86-09535</p>
						<p>주소 : 서울 중구 동호로 330 CJ제일제당 센터 (우) 04560</p>
						<p>통신판매업신고 중구 제 07767호</p>
						<p>개인정보보호책임자 : 조영민</p>
						<p>이메일 : cjon@cj.net</p>
						<p>호스팅제공자 : CJ올리브네트웍스㈜</p>
						<p>
							고객님은 안전거래를 위해 현금등으로 결제시 LG U+ 전자 결제의 매매보호(에스크로) 서비스를 이용하실 수 있습니다.
							<a href="#">가입 사실 확인</a>
						</p>
					</div>
				</div>
				<div id="fl_r">
					<span>cj그룹계열사 바로가기 ▼</span>
					<dl>
						<dt>고객행복센터</dt>
						<dd>1688-1920</dd>
					</dl>
					<a href="#">1:1문의</a>
				</div>
			</div>
		</div>

	</footer>
	
	
	
	
</body>
</html>
