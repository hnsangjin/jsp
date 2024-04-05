<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 메인 페이지</title>
</head>
<body>
	<jsp:include page="../include/admin_header.jsp" />

	<ul>
		<li>
			<a href="<%=request.getContextPath() %>/admin_category_input.go">쇼핑 카테고리 등록</a>
		</li>
		<li>
			<a href="<%=request.getContextPath() %>/admin_category_list.go">쇼핑 카테고리 목록</a>
		</li>
		<li>
			<a href="<%=request.getContextPath() %>/admin_product_input.go">쇼핑 상품 등록</a>
		</li>
		<li>
			<a href="<%=request.getContextPath() %>/admin_product_list.go">쇼핑 상품 목록</a>
		</li>
	
	
	</ul>
	
	
	<jsp:include page="../include/admin_footer.jsp" />

</body>
</html>