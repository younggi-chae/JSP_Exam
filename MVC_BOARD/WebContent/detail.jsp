<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board</title>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">

<style type="text/css">
body {
	padding-top: 70px;
	padding-bottom: 30px;
}

.board_title {
	font-weight: 700;
	font-size: 20pt
}

.board_info_box {
	border-bottom: solid 1px;
}
</style>

<script type="text/javascript">
	$(document).on('click', '#btnUpdate', function() {
		location.href = "updateFormAction.do?seq=" + ${board.seq};
	});

	$(document).on('click', '#btnDelete', function() {
		location.href = "deleteAction.do?seq=" + ${board.seq};
	});

	$(document).on('click','#btnList', function() {
		location.href = "${pageContext.request.contextPath}/board/listAction.do";
	});
</script>
</head>

<body>
	<article>
		<div class="container" role="main">
			<br>
			<div class="board_title">
				<c:out value="${board.title}" />
			</div>
			<div class="board_info_box">
				<span class="board_writer"><c:out
						value="작성자 : ${board.writer}" /></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span class="board_regdate"><c:out value="${board.regdate}" /></span>
				<span class="board_cnt"><c:out value="${hitcount}" /></span>
			</div>
			<div class="bg-white rounded shadow-sm" id="border">

				<br> <br>
				<div class="board_content">${board.contents}</div>
			</div>

			<div style="margin-top: 20px">
				<button type="button" class="btn btn-sm btn-primary" id="btnUpdate">수정</button>
				<button type="button" class="btn btn-sm btn-primary" id="btnDelete">삭제</button>
				<button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
			</div>
		</div>
	</article>
	<br>
	<br>
	<br>

	<article>
		<div class="container">
			<div>
				<span><strong>Comments</strong></span> <span id="cCnt"></span>
			</div>
			<form action="insertReplyAction.do" method="post">
				<div>

					<div>
						<table class="table">
							<tr>
								<td><input type="hidden" name="seq" value="${board.seq }">
									<textarea style="width: 500px" rows="3" cols="30"
										id="r_contents" name="r_contents" placeholder="댓글을 입력하세요."></textarea>
									<div>
										<input type="text" name="r_writer" placeholder="댓글 작성자">
										<input type="submit" value="댓글쓰기">
									</div></td>
							</tr>
						</table>
					</div>
				</div>
			</form>
		</div>
	</article>


	<article>
		<div class="container">
			<div class="input-group input-group-sm" role="group"
				style="text-align: left">
				<table class="table table-striped table-bordered" border="1">
					<thead>
						<tr>
							<td>No</td>
							<td>작성자</td>
							<td>내용</td>
							<td>날짜</td>
							<td>댓글삭제</td>
						</tr>
					</thead>					
					<tbody>						
						<c:forEach var="reply" items="${replys }">
						
							<tr>
								<td>${reply.r_no }</td>
								<td>${reply.r_writer }</td>
								<td>${reply.r_contents }</td>
								<td>${reply.r_regdate }</td>
								<td><a href="deleteReplyAction.do?r_no=${reply.r_no }">삭제</a></td>
							</tr>					
						</c:forEach>
					</tbody>					
				</table>
			</div>
		</div>
	</article>
</body>
</html>
