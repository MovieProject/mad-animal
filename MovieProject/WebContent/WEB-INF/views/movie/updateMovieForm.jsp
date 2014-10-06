<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie</title>
<link rel="stylesheet" href="../css/movie.css">
<link rel="stylesheet" href="../css/board.css">
<script type="text/javascript" src="../js/board.js"></script>
</head>
<body>

	<div id="page">

		<header id="header">
			<c:import url="/top.jsp"></c:import>
		</header>

		<div id="content">

			<form name="writeForm"
				action="updateMovie?type=${movie.type}&num=${movie.movieNum}&searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${currentPageNumber}"
				method="POST" enctype="multipart/form-data">
				<table id="updatetable" class="maintable">
					<caption>게시글 수정</caption>
					<thead>
						<tr>
							<th>영화 제목</th>
							<td><input class="titleinput" type="text" name="title"
								size="20" maxlength="50" value='${movie.movieName}'></td>
						</tr>
						<tr>
							<th>장르</th>

							<td><input class="writerinput" type="text" name="genre"
								size="20" maxlength="20" value='${movie.genre}'></td>

						</tr>

						<tr>
							<th>개봉일</th>

							<td><input class="writerinput" type="text"
								name="releaseDate" size="20" maxlength="20"
								value='${movie.releaseDate}' readonly="readonly" draggable="false"></td>

						</tr>
						<tr>
							<th>감독</th>

							<td><input class="writerinput" type="text" name="director"
								size="20" maxlength="20" value='${movie.director}'>
														</td>
						</tr>
						<tr>
							<th>줄거리</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<td colspan="2"><textarea id="editor" class="contentsinput"
									name="contents">${movie.synopsis}</textarea> <!-- <script>CKEDITOR.replace('editor');</script> -->
							</td>
						</tr>
						<tr>
							<th>기존파일</th>
							<td>${movie.photoDir}<input type="hidden" name = "original" value = "${movie.photoDir}">
							<input type="hidden" name = "num" value = "${movie.movieNum}">
							<input type="hidden" name = "memberID" value = "${movie.memberID}">
							<input type="hidden" name = "type" value = "${movie.type}">
							<input type="hidden" name = "pageNumber" value = "${currentPageNumber}">							
							</td>
							
						</tr>
						<tr>
							<th>사진첨부</th>
							<td><input type="file" name="file"></td>
						</tr>
					</tbody>

				</table>
				<div class="buttonbar">
					<input type="submit" value="수정"
						onclick="boardWriteCheck(this.form)&searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${currentPageNumber}&memberID=${movie.memberID}">
					<input type="button" value="취소"
						onclick="goUrl('movieRead?num=${movie.movieNum}&searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${currentPageNumber}');">
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















