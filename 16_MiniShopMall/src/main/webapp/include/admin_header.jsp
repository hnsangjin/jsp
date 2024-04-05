<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#shop_title {
	float: right;
	margin-right: 200px;
	word-spacing: 10px;
	clear: both;
}

a {
	text-decoration: none;
}

ul li {
	list-style: none;
	display: inline-block;
	margin-left: 80px;
}

td {
	text-align: center;
}
</style>
</head>
<body>

	<div align="center">
		<hr width="60%" color="black">
		<br>
		<h2>관리자 페이지</h2>
		<div id="shop_title">
			<a href="#">${adminName }님 환영합니다.</a> <a
				href="<%=request.getContextPath() %>/admin_logout.go">로그아웃</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

		</div>
		<br>
		<hr width="60%" color="black">
		<table width="800">
			<tr>
				<td><a href="<%=request.getContextPath() %>/admin_main.go">관리자
						홈</a></td>
				<td><a
					href="<%=request.getContextPath() %>/admin_category_input.go">카테고리
						등록</a></td>
				<td><a
					href="<%=request.getContextPath() %>/admin_category_list.go">카테고리
						목록</a></td>
				<td><a
					href="<%=request.getContextPath() %>/admin_product_input.go">상품
						등록</a></td>
				<td><a
					href="<%=request.getContextPath() %>/admin_product_list.go">상품
						목록</a></td>
			</tr>

		</table>
		<hr width="60%" color="black">
		<br>
		
		
</body>
</html>















