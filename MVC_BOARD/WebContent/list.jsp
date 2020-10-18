<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">

<title>board</title>
<style>
body {
	padding-top: 70px;
	padding-bottom: 30px;
}

#align {
	text-align: center;
}
</style>

<script type="text/javascript">
	$(document).on('click', '#btnWriteForm', function(e) {
		e.preventDefault();
		location.href = "insertFormAction.do";
	});
</script>

</head>
<body>
	<h2 align="center">게시판</h2>
	<br>
	<article>
		<div class="container">
			<div class="table-responsive">
				<div align="right">
					<button type="button" class="btn btn-sm btn-primary"
						id="btnWriteForm">글쓰기</button>
				</div>
				<br>
				<table class="table table-striped table-sm">
					<colgroup>
						<col style="width: 15%;" />
						<col style="width: auto;" />
						<col style="width: 15%;" />
						<col style="width: 10%;" />
						<col style="width: 10%;" />
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th id="align">글제목</th>
							<th>작성자</th>
							<th>조회수</th>
							<th>작성일</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty listModel.list }">
								<tr>
									<td colspan="5" align="center">데이터가 없습니다.</td>
								</tr>
							</c:when>
							<c:when test="${!empty listModel.list}">
								<c:forEach var="board" items="${listModel.list }">
									<tr>
										<td>${board.seq }</td>
										<td><a href="detailAction.do?seq=${board.seq }">${board.title }</a></td>
										<td>${board.writer }</td>
										<td>${board.hitcount }</td>
										<td><fmt:parseDate var="dt" value="${board.regdate }"
												pattern="yyyy-MM-dd HH:mm:ss" /> <fmt:formatDate
												value="${dt }" pattern="yyyy/MM/dd" /></td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</tbody>
				</table>
			</div>
			<br>

			<!-- 페이지 처리 영역 -->
			<!-- 이전 영역 , start페이지가 5보다 클 경우-->
			<c:if test="${listModel.startPage > 5 }">
				<a href="listAction.do?pageNum=${listModel.startPage -1 }">[이전]</a>
			</c:if>

			<!-- 페이지 목록 -->
			<c:forEach var="pageNo" begin="${listModel.startPage }"
				end="${listModel.endPage }">
				<c:if test="${listModel.requestPage == pageNo }">
					<b>
				</c:if>
				<a href="listAction.do?pageNum=${pageNo }">[${pageNo }]</a>
				<c:if test="${listModel.requestPage == pageNo }">
					</b>
				</c:if>
			</c:forEach>

			<!-- 이후 영역 ,  endPage보다 totalPageCount가 크면 -->
			<c:if test="${listModel.endPage < listModel.totalPageCount }">
				<a href="listAction.do?pageNum=${listModel.endPage +1 }">[이후]</a>
			</c:if>

			<form action="listAction.do" method="post">
				<div align="center">
					<select name="area" id="searchType">
						<option value="title">제목</option>
						<option value="writer">작성자</option>
					</select> <input type="text" name="searchKey" size="10"> <input
						type="submit" value="검색">
				</div>
			</form>
		</div>
	</article>
	<br>
	<br>
</body>
</html>
