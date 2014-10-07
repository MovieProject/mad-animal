<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie</title>
<link rel="stylesheet" href="../css/movie.css">
<link rel="stylesheet" href="../css/board.css">
<<<<<<< HEAD
<script type="text/javascript" src="../js/board.js">
	
</script>
=======
<script type="text/javascript" src="../js/board.js"></script>
>>>>>>> refs/remotes/origin/master
</head>
<body>

	<div id="page">

		<header id="header">
			<c:import url="/top.jsp"></c:import>
		</header>

		<div id="content">

			<form name="writeForm" action="<c:url value="writeMovie"/>"
				method="POST" enctype="multipart/form-data">
				<table id="writetable" class="maintable">

					<c:choose>
						<c:when test="${movie.type eq 2 }">
							<caption id="contenttitle">금주의 영화</caption>
						</c:when>
						<c:when test="${movie.type eq 1 }">
							<caption id="contenttitle">회원 추천 영화</caption>
						</c:when>
						<c:when test="${movie.type eq 3 }">
							<caption id="contenttitle">최신영화 정보</caption>
						</c:when>

					</c:choose>
					<thead>
						<tr>
							<th>영화 제목</th> <<<<<<< HEAD
							<td><input class="titleinput" type="text" name="title"
								id='movieWriteTitle' size="20" maxlength="50"></td>
						</tr>
						<tr>
							<th>장르</th>

							<td><input class="writerinput" type="text" name="genre"
								size="20" maxlength="20" id="movieWriteGenre"></td>

						</tr>

						<tr>
							<th>개봉일</th>

							<td><input class="writerinput" type="date" HEAD
								name="releaseDate" size="20" maxlength="20" id="movieWriteDate"></td>

						</tr>
						<tr>
							<th>감독</th>

							<td><input class="writerinput" type="text" name="director"
								size="20" maxlength="20" id="movieWriteDirector"></td>

						</tr>
						<tr>
							<th>줄거리</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="2"><textarea id="movieWriteSynopsis"
									class="contentsinput" name="synopsis"></textarea> <!-- 							<script>CKEDITOR.replace('editor');</script> -->

								<!-- <textarea class="contentsinput ckeditor" name="contents"></textarea> -->
							</td>
						</tr>
						<tr>
							<th>사진첨부</th>
							<td><input type="file" name="file" id="movieWriteFile"></td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="memberID"
					value="${loginMember.memberID }"> <input type="hidden"
					name="type" value="${type}">
				<div class="buttonbar">
					<!-- 	<input type="button" value="등록"
						onclick="boardWriteCheck(this.form);"> -->
					<input type="button" value="등록"
						onclick="checkWriteMovie(this.form)"> <input type="button"
						value="취소" onclick="goUrl('movielist?type=${type}');">
				</div>
			</form>
		</div>

		<aside id="sidebar">
			<c:import url="/side-bar.jsp"></c:import>
		</aside>

		<footer id="footer">
			<c:import url="/footer.jsp"></c:import>
		</footer>
	</div>
</body>
</html>
