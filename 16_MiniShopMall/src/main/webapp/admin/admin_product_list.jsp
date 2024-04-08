<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
th {
	text-align: center;
}
</style>
</head>
<body>
	<jsp:include page="../include/admin_header.jsp" />
	<hr width="60%" color="gray">
	<h3>상품 목록 테이블 전체 리스트</h3>
	<hr width="60%" color="gray">
	<table border="1" width="60%">
		<tr bgcolor="#ffcc00">
			<th>번호</th>
			<th>카테고리 CODE</th>
			<th>제품명</th>
			<th>이 미 지</th>
			<th>가 격</th>
			<th>수 량</th>
			<th>제 조 사</th>
			<th>등 록 일</th>
			<th>수 정&nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp; 삭제</th>
		</tr>
		<c:set var="list" value="${productList }" />
		<c:if test="${!empty list }">
			<c:forEach items="${list }" var="dto">
				<tr>
					<td>${dto.getPnum() }</td>
					<td>${dto.getPcategory_fk() }</td>
					<td>${dto.getPname() }</td>
					<td><img alt=""
						src="<%=request.getContextPath() %>/upload/${dto.getPimage() }"
						width="60" height="60"></td>
					<td><fmt:formatNumber value="${dto.getPrice() }" />원</td>
					<td>${dto.getPqty() }</td>
					<td>${dto.getPcompany() }</td>
					<td>${dto.getPinputdate().substring(0, 10) }</td>
					<td><a href="<%=request.getContextPath() %>/admin_product_modify.go?num=${dto.getPnum() }">수정</a> &nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp; <a
						href="javascript:del(${dto.getPnum() })">삭 제</a></td>

				</tr>

			</c:forEach>

		</c:if>
		
				<c:if test="${empty list }">
					<tr>
						<td colspan="9" align="center">
							<h3>상품 목록이 없습니다.</h3>
						</td>

					</tr>

				</c:if>
	</table>



	<jsp:include page="../include/admin_footer.jsp" />

</body>
</html>