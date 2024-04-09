<%@page import="com.shop.model.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.shop.model.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%
	CategoryDAO dao = CategoryDAO.getInstance();
	
	List<CategoryDTO> list = dao.getCategoryList();
	
	request.setAttribute("CategoryList", list);


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<table border="1" width="900">
			<tr>
				<td colspan="2" align="center">
					<a href="<%=request.getContextPath() %>/user_main.go">쇼핑몰 홈</a>&nbsp;&nbsp;&nbsp;
					<a href="<%=request.getContextPath() %>/user_cart_list.go">장바구니</a>&nbsp;&nbsp;&nbsp;
					<a href="#">${UserName }님 환영합니다.</a>&nbsp;&nbsp;&nbsp;
					<a href="<%=request.getContextPath() %>/user_logout.go">로그아웃</a>
				</td>
			
			</tr>
			
			<tr>
				<td width="250" align="center" valign="top">
				<c:set var="list" value="${CategoryList }" />
				<table>
					<tr>
						<td>카테고리 CODE</td>
					</tr>
					<c:if test="${!empty list }">
						<c:forEach items="${list }" var="dto">
							<tr>
								<td>
									<a href="<%=request.getContextPath() %>/user_category_list.go?code=${dto.getCategory_code() }">${dto.getCategory_name() } [${dto.getCategory_code() }]</a>
								</td>
							</tr>
						</c:forEach>
					
					</c:if>
				</table>
				</td>

				<td>