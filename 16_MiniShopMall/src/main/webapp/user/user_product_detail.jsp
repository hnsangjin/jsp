<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function goCart(){
		document.frm.action="<%=request.getContextPath() %>/user_cart_add.go";
		
		document.frm.submit();
		
	}

</script>
</head>
<body>
	<jsp:include page="../include/user_header.jsp" />

	<table border="1" width="600">
		<c:set var="dto" value="${ProductCont }" />
		<c:if test="${!empty dto }">
			<tr>
				<td colspan="2" align="center"><b>[${dto.getPname() } 상품정보]</b>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left" height="30">
			</tr>
			<tr>
				<td align="center">
					<img src="<%=request.getContextPath() %>/upload/${dto.getPimage() }" width="180" height="160">
				</td>
				<td>
					<form method="post" name="frm">
					<input type="hidden" name="p_num" value="${dto.getPnum() }">
					<input type="hidden" name="p_spec" value="${dto.getPspec() }">
					<input type="hidden" name="p_image" value="${dto.getPimage() }">
					<input type="hidden" name="userId" value="${userId }">
						<table border="0">
							<tr>
								<th>제품번호 :</th>
								<td>${dto.getPnum() }</td>
							</tr>
							<tr>
								
								<td colspan="2" align="center" height="20"> </td>
							</tr>
							<tr>
								<th>제 품 명 :</th>
								<td><input name="p_name" readonly value="${dto.getPname() }"> </td>
							</tr>
							<tr>
								
								<td colspan="2" align="center" height="20"> </td>
							</tr>
							<tr>
								<th>제품 가격 : </th>
								<td>
									<input name="p_price" readonly value="${dto.getPrice() }">
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center" height="20"> </td>
							</tr>
							<tr>
								<th>제품 포인트 :</th>
								
									<fmt:formatNumber value="${dto.getPoint() }" var="commaPoint" />
									<td>[${commaPoint }] 포인트</td>
							</tr>
							<tr>
								<td colspan="2" align="center" height="20"> </td>
							</tr>
							<tr>
								<th>제품 수량 :</th>
								<td><input type="number" name="p_qty" min="1" max="99" value="1"> </td>
							</tr>
							<tr>
								<td colspan="2" align="center" height="20"> </td>
							</tr>
							
						</table>
						<table border="0" width="90%" align="center">
							<tr>
								<td align="center">
									<a href="javascript:goCart()">
										<img src="<%=request.getContextPath() %>/uploadFile/btn_cart.png" border="0">
									</a>
								</td>
								<td align="center">
									<a href="#">
										<img src="<%=request.getContextPath() %>/uploadFile/btn_buy.png" border="0">
									</a>
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			
			<tr height="250" valign="top">
				<td colspan="2">
					<br>
					<b>제품 소개</b>
					<br><br>
					${dto.getPcontents() }
					
				</td>
			
			</tr>
		</c:if>
		<c:if test="${empty dto }">
			<tr>
				<td colspan="2" align="center">
					<h3>제품 상세정보가 없습니다.</h3>
				</td>
			</tr>
		</c:if>
	</table>

	<jsp:include page="../include/user_footer.jsp" />
</body>