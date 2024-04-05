<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">

<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
	crossorigin="anonymous"></script>
<style type="text/css">
.pagination {
	justify-content: center;
}

.table {
	width: 70%;
}
</style>
</head>
<body>
	<div align="center">
		<hr color="red" width="30%">
		<h3>Board 테이블 게시물 전체 리스트 페이지</h3>
		<hr color="red" width="30%">
		<br>
		<br>

		<table class="table">
			<thead class="thead-light">
				<tr>
					<td colspan="5" align="right">전체 게시물 수 : ${totalRecord } 개</td>

				</tr>

				<tr>
					<th scope="col">글번호</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">조회수</th>
					<th scope="col">작성일</th>
				</tr>
			</thead>
			<c:set var="list" value="${List }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tbody>
						<tr>
							<th scope="row">${dto.getBoard_no() }</th>
							<td><a
								href="<%=request.getContextPath() %>/content.go?no=${dto.getBoard_no() }&page=${page }">
									${dto.getBoard_title() }</a></td>
							<td>${dto.getBoard_writer() }</td>
							<td>${dto.getBoard_hit() }</td>
							<td>${dto.getBoard_date().substring(0, 10) }</td>

						</tr>
					</tbody>
				</c:forEach>

			</c:if>
			<!-- 			<table class="table">
				  <thead class="thead-light">
				    <tr>
				      <th scope="col">#</th>
				      <th scope="col">First</th>
				      <th scope="col">Last</th>
				      <th scope="col">Handle</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <th scope="row">1</th>
				      <td>Mark</td>
				      <td>Otto</td>
				      <td>@mdo</td>
				    </tr>
				    <tr>
				      <th scope="row">2</th>
				      <td>Jacob</td>
				      <td>Thornton</td>
				      <td>@fat</td>
				    </tr>
				    <tr>
				      <th scope="row">3</th>
				      <td>Larry</td>
				      <td>the Bird</td>
				      <td>@twitter</td>
				    </tr>
				  </tbody>
				</table> -->

			<c:if test="${empty list }">
				<tr>
					<td colspan="4" align="center">
						<h3>전체 게시물 리스트가 없습니다.</h3>
					</td>
				</tr>
			</c:if>
		</table>
		<br>
		<br>
		<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary" data-toggle="modal"
			data-target="#staticBackdrop">글쓰기</button>

		<!-- Modal -->
		<div class="modal fade" id="staticBackdrop" data-backdrop="static"
			data-keyboard="false" tabindex="-1"
			aria-labelledby="staticBackdropLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="staticBackdropLabel">내용을 작성하세요.</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body" align="center">
						<form method="post"
							action="<%=request.getContextPath()%>/insert_ok.go">
							<table border="1" width="320">

								<div class="form-group row">
									<label for="inputEmail3" class="col-sm-2 col-form-label">작성자</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="inputEmail3"
											name="writer"><br>
									</div>


									<label for="inputEmail3" class="col-sm-2 col-form-label">제목</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="inputEmail3"
											name="title">
									</div>
								</div>
								<div class="form-group">
									<label for="exampleFormControlTextarea1">내용</label>
									<textarea class="form-control" id="exampleFormControlTextarea1"
										rows="5"></textarea>
								</div>
								</div>

								<div class="form-group mx-sm-3 mb-2">
									<label for="inputPassword2" class="sr-only">Password</label> <input
										type="password" class="form-control" id="inputPassword2"
										placeholder="Password" name="pwd">
								</div>


							</table>
					</div>


					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">닫기</button>
						<button type="submit" class="btn btn-primary">글쓰기</button>
					</div>
					</form>
				</div>
			</div>
		</div>
		<br> <br>

		<%-- 페이징 처리 --%>
		<%-- <c:if test="${page > block }">
			<a href="select.go?page=1">[처음으로]</a>
			<a href="select.go?page=${startBlock - 1 }">◀</a>
		</c:if>
		<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
			<c:if test="${i == page }">
				<b><a href="select.go?page=${i }">[${i }]</a></b>
			
			</c:if>
			<c:if test="${i != page }">
				<a href="select.go?page=${i }">[${i }]</a>
			
			</c:if>
		
		</c:forEach>
		<c:if test="${endBlock < allPage }">
			<a href="select.go?page=${endBlock + 1 }">▶</a>
			<a href="select.go?page=${allPage }">[맨 뒤로]</a>
		
		</c:if> --%>
		<nav aria-label="...">
			<ul class="pagination">

				<c:if test="${page > block }">
					<li class="page-item"><a class="page-link"
						href="select.go?page=1">처음으로</a></li>
					<li class="page-item"><a class="page-link"
						href="select.go?page=${startBlock - 1 }">Previous</a></li>
				</c:if>
				<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
					<c:if test="${i == page }">
						<li class="page-item active" aria-current="page"><a
							class="page-link" href="select.go?page=${i }">${i }</a></li>

					</c:if>
					<c:if test="${i != page }">
						<li class="page-item"><a class="page-link"
							href="select.go?page=${i }">${i }</a></li>
					</c:if>

				</c:forEach>
				<c:if test="${endBlock < allPage }">
					<li class="page-item"><a class="page-link"
						href="select.go?page=${endBlock + 1 }">Next</a></li>
					<li class="page-item"><a class="page-link"
						href="select.go?page=${allPage }">끝으로</a></li>
				</c:if>
			</ul>
		</nav>
		<br> <br>
		<form method="post" action="<%=request.getContextPath()%>/search.go">


			<div class="col-md-1 mb-1">

				<select class="custom-select my-1 mr-sm-2"
					id="inlineFormCustomSelectPref" name="field">
					<option value="title">제목</option>
					<option value="content">내용</option>
					<option value="title_content">제목/내용</option>
					<option value="writer">작성자</option>
				</select>
			</div>
			<input type="text" name="keyword"> <input type="submit"
				value="검색">
		</form>
</body>
</html>











