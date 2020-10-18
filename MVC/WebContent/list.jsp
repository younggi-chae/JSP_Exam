<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
</style>
</head>
<body>
	<a href="insertActionForm.do">글쓰기</a>
	<table border="1">
		<tr>
			<td>글번호</td>
			<td>이미지</td>
			<td>글제목</td>
			<td>작성자</td>
			<td>작성일자</td>
			<td>조회수</td>
		</tr>

		<c:forEach var="board" items="${listModel.list }">
			<tr>
				<td>${board.seq }</td>
				<td><c:if test="${board.fname != null }">
						<c:set var="head"
							value="${fn:substring(board.fname, 
												0, fn:length(board.fname)-4) }"></c:set>
						<c:set var="pattern"
							value="${fn:substring(board.fname, 
						fn:length(head) +1, fn:length(board.fname)) }"></c:set>

						<c:choose>
							<c:when test="${pattern == 'jpg' || pattern == 'gif' }">
								<img src="/MVC/upload/${head }_small.${pattern}">
							</c:when>
							<c:otherwise>
								<c:out value="NO IMAGE"></c:out>
							</c:otherwise>
						</c:choose>
					</c:if></td>
				<td><a href="detailAction.do?seq=${board.seq }">${board.title }</a></td>
				<td>${board.writer }</td>
				<td><fmt:parseDate var="dt" value="${board.regdate }"
						pattern="yyyy-MM-dd HH:mm:ss" /> <fmt:formatDate value="${dt }"
						pattern="yyyy/MM/dd" /></td>
				<td>${board.hitcount }</td>
			</tr>
		</c:forEach>
	</table>
	<br>
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


	<form action="listAction.do" method="post" id="search">
		<input type="checkbox" name="area" value="title">제목 <input
			type="checkbox" name="area" value="writer">작성자 <input
			type="text" name="searchKey" size="10"> <input type="submit"
			value="검색">
	</form>

</body>
</html>















