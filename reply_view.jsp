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
		<h1>답글쓰기</h1>
		<hr>

		<form action="reply_ok.do" name="modify" method="post">
		
			<input type="hidden" name="bId" value="${reply.bId }">
			<input type="hidden" name="bGroup" value="${reply.bGroup}">
			<input type="hidden" name="bStep" value="${reply.bStep}">
			<input type="hidden" name="bIndent" value="${reply.bIndent}">
		
			<table>
				<colgroup>
					<col width="15%">
					<col width="85%">
				</colgroup>
				
				
				<tr>
					<th>작성자</th>
					<td><input type="text" name="bName" value="${reply.bName }" readonly></td>
				</tr>
				
				<tr>
					<th>제목</th>
					<td><input type="text" name="bTitle" value="${reply.bTitle }"></td>
				</tr>
				
				<tr>
					<th>내용</th>
					<td><textarea name="bContent" cols="50" rows="10">
				
					
					-------------------------
					[답글]
					${reply.bContent}
					
					</textarea></td>
				</tr>

			</table>
			<hr>
			
			
			<div class="button-wrapper">
				<button type="submit" class="write">답글쓰기</button>
				<button type="button" class="cancel"
					onclick="javascript:location.href='list.do'">취소</button>
			</div>
		</form>

	</section>
</body>
</html>