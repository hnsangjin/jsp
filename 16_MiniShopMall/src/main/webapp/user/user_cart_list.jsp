<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>'
<style type="text/css">
	.center{
		text-align: center;
	}
</style>
</head>
<body>
	<jsp:include page="../include/user_header.jsp" />
	
		<table border="1" width="700" align="center">
			<tr>
				<td colspan="7" align="center">
					<h3>장바구니 상품</h3>
				</td>
			</tr>
			<tr>
				<th width="8%">주문번호</th>
				<th width="8%">상품번호</th>
				<th width="13%">상품명</th>
				<th width="15%">수 량</th>
				<th width="15%">단 가</th>
				<th width="15%">총 액</th>
				<th width="10%">삭제</th>
			</tr>
			<c:set var="list" value="${CartList }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td class="center">${dto.getCart_num() }</td>
						<td class="center">${dto.getCart_pnum() }</td>
						<td class="center">
							<img src="<%=request.getContextPath() %>/upload/${dto.getCart_pimage() }" width="50" height="50">
							<br>
							${dto.getCart_pname() }
						</td>
						<td class="center">'
							<input type="number" min="1" max="99" value="${dto.getCart_pqty() }">
						</td>
						<td>
							<fmt:formatNumber value="${dto.getCart_price() }" />원
						</td>
						<td>
							<c:set var="price" value="${dto.getCart_price() }" />
							<c:set var="amount" value="${dto.getCart_pqty() }" />
							<fmt:formatNumber value="${price * amount }" />원
						</td>
						<td class="center">
							<a href="<%=request.getContextPath() %>/user_cart_delete.go?num=${dto.getCart_num() }">[삭 제]</a>
						</td>
					</tr>
					<c:set var="total" value="${total + (price * amount) }" />
					
				</c:forEach>
				<tr>
					<td colspan="5" align="right">
					<b><font color="red">장바구니 총액 : <fmt:formatNumber value="${total }" />원</font></b>
					</td>
					<td colspan="2" align="center">
						<a href="#">결제하기</a>&nbsp;&nbsp;&nbsp;
						<a href="<%=request.getContextPath() %>/user_main.go">[쇼핑 계속하기]</a>
					</td>
				</tr>
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="7" align="center">
						<h3>장바구니가 비어 있습니다.</h3>
					</td>
				</tr>
			</c:if>
		</table>
	
	
	<jsp:include page="../include/user_footer.jsp" />
</body>
</html>