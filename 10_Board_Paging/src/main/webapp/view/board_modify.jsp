<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr color="red" width="30%">
			<h3>Board 테이블 게시물 전체 리스트 페이지</h3>
		<hr color="red" width="30%">
		<br><br>
		<c:set var="dto" value="${modify }"/>
		<form method="post" action="<%=request.getContextPath() %>/modify_ok.go?no=${dto.getBoard_no() }">
		<input type="hidden" value="${page }" name="page">
		<input type="hidden" value="${dto.getBoard_pwd() }" name="bd_pwd">
		<table border="1">
		
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" value="${dto.getBoard_writer () }"></td>
			</tr>
			<tr>
				<th>글제목</th>
				<td>
					<input type="text" name="title" value="${dto.getBoard_title() }">
				</td>
			</tr>
			
			<tr>
				<th>글 내용</th>
				<td>
					<textarea rows="7" cols="30" name="content">${dto.getBoard_cont() }</textarea>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="pwd">
				</td>
			</tr>
		
		</table>
		
		<input type="submit" value="수정완료">
		<input type="reset" value="다시작성">
		</form>
	</div>

</body>
</html>