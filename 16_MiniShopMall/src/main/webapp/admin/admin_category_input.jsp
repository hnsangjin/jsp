<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../include/admin_header.jsp" />

	<hr width="60%" color="lightgray">
	<h3>카테고리 등록 폼 페이지</h3>
	<hr width="60%" color="lightgray">
	<form method="post"
		action="<%=request.getContextPath()%>/admin_category_input_ok.go">
		<table border="1" width="350">
			<tr>
				<th>카테고리 CODE</th>
				<td><input name="category_code"></td>
			</tr>
			<tr>
				<th>카테고리 이름</th>
				<td><input name="category_name"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="등록">
					<input type="reset" value="취소"></td>
			</tr>
			


		</table>

	</form>


	<jsp:include page="../include/admin_footer.jsp" />

</body>
</html>