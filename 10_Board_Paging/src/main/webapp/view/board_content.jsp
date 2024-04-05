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
		<c:set var="dto" value="${cont }"/>
		<table border="1">
			<tr>
				<th>글번호</th>
				<td>${dto.getBoard_no() }</td>
				<th width="50">조회수</th>
				<td>${dto.getBoard_hit() }</td>
			</tr>

			<tr>
				<th>제목</th>
				<td colspan="3">${dto.getBoard_title() }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td colspan="3">${dto.getBoard_writer() }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td width="300" height="300" colspan="3">${dto.getBoard_cont() }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td colspan="3">${dto.getBoard_date() }</td>
			</tr>
		
		
		</table>
		<input type="button" value="수정하기" onclick="location.href='modify.go?no=${dto.getBoard_no() }'">
		<input type="button" value="삭제하기" onclick="if(confirm('정말 삭제하시겠습니까?')){
													if(prompt('비밀번호를 입력하세요')=='${dto.getBoard_pwd() }') {
														location.href='delete.go?num=${dto.getBoard_no() }'
													}else{alert('비밀번호가 틀렸습니다.'); return;}
													}else{return;}">
		<input type="button" value="목록으로" onclick="location.href='select.go?page=${page }'">
	</div>

</body>
</html>