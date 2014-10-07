<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<link rel="stylesheet" href="../css/movie.css">
</head>
<body>


	<div class="main">

		<!-- 금주영화 -->
		<div id="week">
			<object id="preview3" width="380px" height="300px"
				data="movie/moviepreview?type=3"></object>

		</div>

		<!-- 공지 -->
		<div id="notice">
			<object id="preview" width="380px" height="300px"
				data="board/preview"></object>

		</div>

		<!-- 최신영화소개 -->
		<div id="newMovieIntro">
			<object id="preview1" width="380px" height="300px"
				data="movie/moviepreview?type=2"></object>

		</div>
		<!-- 회원 영화 추천 -->
		<div id="member">

			<object id="preview2" width="380px" height="300px"
				data="movie/moviepreview?type=1"></object>

		</div>

	</div>
</body>
</html>
