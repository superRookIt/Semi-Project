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

<title>글수정</title>
</head>
<body>
	<section>
		<h1>게시글 수정</h1>
		<hr>

		<form action="modify_ok.do" name="modify" method="post">
		
			<input type="hidden" name="bId" value="${modify.bId }">
		
			<table>
				<colgroup>
					<col width="15%">
					<col width="85%">
				</colgroup>
				
				
				<tr>
					<th>작성자</th>
					<td><input type="text" name="bName" value="${modify.bName }" readonly></td>
				</tr>
				
				<tr>
					<th>제목</th>
					<td><input type="text" name="bTitle" value="${modify.bTitle }"></td>
				</tr>
				
				<tr>
					<th>내용</th>
					<td><textarea name="bContent" cols="50" rows="10">
					${modify.bContent}
					</textarea></td>
				</tr>

			</table>
			<hr>
			
			
			<div class="button-wrapper">
				<button type="submit" class="write">수정하기</button>
				<button type="button" class="cancel"
					onclick="javascript:location.href='list.do'">취소</button>
			</div>
		</form>

	</section>
</body>
</html>