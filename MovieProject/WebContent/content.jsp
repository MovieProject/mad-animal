<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<link rel="stylesheet" href="../css/board.css">

</head>
<body>


	<div class="main">

		<!-- 금주영화 -->
		<div id="week">
			<c:import url="BoardList/week_Recommend.jsp"/>
		</div>

		<!-- 공지 -->
		<div id="notice">
			<iframe id="preview" width="380px" height="300px" src="board/preview"></iframe>
		<!-- 	<object id="preview" width="380px" height="300px" data="board/preview"></object> -->
		</div>

		<!-- 최신영화소개 -->
		<div id="newMovieIntro">
		<c:import url="BoardList/newMovieIntro.jsp"/>
		</div>

		<!-- 회원 영화 추천 -->
		<div id="member">
		<c:import url="BoardList/member_Recommend.jsp"/>
		</div>

	</div>
</body>
</html>
