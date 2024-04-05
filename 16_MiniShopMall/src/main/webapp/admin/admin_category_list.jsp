<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function check(num){
		
		let res = confirm("정말 삭제하시겠습니까?");
		
		if(res){
			location.href="admin_category_delete.go?num="+num;
			
		}
	}

</script>
</head>
<body>
	<jsp:include page="../include/admin_header.jsp" />
	<hr width="60%" color="silver">
	<h3>카테고리 테이블 전체 리스트</h3>
	<hr width="60%" color="silver">
	<br>
	<table border="1" width="500">
		<tr>
			<th>카테고리 번호</th>
			<th>카테고리 CODE</th>
			<th>카테고리 이름</th>
			<th>삭 제</th>
		</tr>
		<c:set var="list" value="${categoryList }" />
		<c:if test="${!empty list }">
			<c:forEach items="${list }" var="dto">
				<tr>
					<td>${dto.getCategory_num() }</td>
					<td>${dto.getCategory_code() }</td>
					<td>${dto.getCategory_name() }</td>
					<td><input type="button" value="삭 제"
						onclick="check(${dto.getCategory_num() })"></td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty list }">
			<tr>
				<td colspan="4" align="center">
					<h4>카테고리가 없습니다.</h4>
				</td>
			</tr>
		</c:if>
	</table>

	<jsp:include page="../include/admin_footer.jsp" />
</body>
</html>