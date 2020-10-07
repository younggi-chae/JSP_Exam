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
body {padding-top: 70px; padding-bottom: 30px;}
#align {text-align: center;}
</style>

<script type="text/javascript">
	$(document).on('click', '#btnWriteForm', function(e){
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
							<c:when test="${empty list }">
								<tr>
									<td colspan="5" align="center">데이터가 없습니다.</td>
								</tr>
							</c:when>
							<c:when test="${!empty list}">
								<c:forEach var="board" items="${list }">
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
			<form action="listAction.do" method="post">
				<div align="center">
					<select name="area" id="searchType">
						<option value="title">제목</option>
						<option value="writer">작성자</option>
					</select> 
					<input type="text" name="searchKey" size="10"> 
					<input type="submit" value="검색">
				</div>
			</form>
		</div>
	</article>
	<br><br>
</body>
</html>
