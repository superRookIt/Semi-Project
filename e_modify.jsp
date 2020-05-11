<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900&display=swap&subset=korean" rel="stylesheet">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/write.css">

<title>EVENT 글쓰기 수정하기</title>
</head>
<body>

	<section>
		<h1>EVENT 글 수정</h1>
		<hr>

		<form action="e_modify_ok.jsp" name="e_write" method="post" enctype="multipart/form-data">
		
		<input type="hidden" name="bId" value="${modify.bId }">
		
			<table>
			
				<colgroup>
					<col width="15%">
					<col width="85%">
				</colgroup>
				
				<tr>
					<th>제목</th>
					<td><input type="text" name="bTitle" value="${modify.bTitle }"></td>
				</tr>
				
				<tr>
					<th>내용</th>
					<td><textarea name="bContent" cols="50" rows="10">
					${modify.bContent }
					</textarea></td>
				</tr>
				
				<tr>
					<th>기존파일1</th>
					<td>${modify.bFile }</td>
				</tr>
				
				<tr>
					<th>기존파일2</th>
					<td>${modify.bFile2 }</td>
				</tr>
				
				
				<tr>
					<th>파일첨부1(수정)</th>
					<td><input type="file" name="bFile"></td>
				</tr>
				
				<tr>
					<th>파일첨부2(수정)</th>
					<td><input type="file" name="bFile2"></td>
				</tr>
				

			</table>
			
			<hr>
			
			
			<div class="button-wrapper">
				<button type="submit" class="write">작성완료</button>
				<button type="button" class="cancel" onclick="javascript:location.href='e_list.jsp'">취소</button><br>
			</div>
			
			
		</form>

	</section>
</body>
</html>