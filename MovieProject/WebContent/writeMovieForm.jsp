<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie</title>
<link rel="stylesheet" href="css/movie.css">
<link rel="stylesheet" href="css/board.css">
</head>
<body>

	<div id="page">

		<header id="header">
			<c:import url="top.jsp"></c:import>
		</header>

		<div id="content">

			<form name="writeForm" action="<c:url value="/movie?action=writeMovie"/>" method="POST"  >
				<table id="writetable" class="maintable" align="center">
					<caption id="boardtitle">회원추천 영화 등록</caption>
					<thead>
						<tr>
							<th>영화 제목</th>
							<td><input class="titleinput" type="text" name="title"
								size="20" maxlength="50">
				 </td>
						</tr>
						<tr>
							<th>장르</th>

							<td><input class="writerinput" type="text" name="genre"
								size="20" maxlength="20"></td>

						</tr>

						<tr>
							<th>개봉일</th>

							<td><input class="writerinput" type="date" name="releaseDate"
								size="20" maxlength="20"></td>

						</tr>
						<tr>
							<th>감독</th>

							<td><input class="writerinput" type="text" name="director"
								size="20" maxlength="20"></td>

						</tr>
						<tr>
							<th>줄거리</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="2"><textarea id="editor" class="contentsinput"
									name="synopsis"></textarea> <!-- 							<script>CKEDITOR.replace('editor');</script> -->

								<!-- <textarea class="contentsinput ckeditor" name="contents"></textarea> -->
							</td>
						</tr>
						<tr>
							<th>사진첨부</th>
							<td><input type="file" name="file"></td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name = "memberGrade" value = "${loginMember.grade }">
				<div class="buttonbar">
				<!-- 	<input type="button" value="등록"
						onclick="boardWriteCheck(this.form);"> -->
				<input type="submit" value="등록">
						 <input
						type="button" value="취소" onclick="goUrl('list');">
				</div>
			</form>
		</div>

		<aside id="sidebar">
			<c:import url="side-bar.jsp"></c:import>
		</aside>

		<footer id="footer">
			<c:import url="footer.jsp"></c:import>
		</footer>
	</div>
</body>
</html>
