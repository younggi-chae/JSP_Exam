<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">

<style>
body {
	padding-top: 70px;
	padding-bottom: 30px;
}
</style>
</head>
<body>
	<article>
		<div class="container" role="main">
			<h2>글쓰기</h2>
			<br>
			<form method="post" action="insertAction.do" enctype="multipart/form-data">
				<div class="mb-3">
					<label for="title">제목</label> <input type="text"
						class="form-control" name="title" id="title"
						placeholder="제목을 입력해 주세요">
				</div>
				<div class="mb-3">
					<label for="writer">작성자</label> <input type="text"
						class="form-control" name="writer" id="writer"
						placeholder="이름을 입력해 주세요">
				</div>

				<div class="mb-3">
					<label for="contents">내용</label>
					<textarea class="form-control" rows="5" name="contents"
						id="contents" placeholder="내용을 입력해 주세요"></textarea>
				</div>
				<div class="mb-3">
					<label for="fname">파일 업로드</label><input type="file" 
					    class="form-control" name="fname" id="fname">
				</div>
				<div>
					<input type="submit" value="등록">
				</div>
			</form>
		</div>

	</article>
</body>
</html>