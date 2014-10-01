<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>

<link rel="stylesheet" href="../css/board.css">
<link rel="stylesheet" href="../css/board2.css">


</head>
<body>


	<div class="main">

		<!-- 금주영화 -->
		<div id="week">
			<c:import url="BoardList/week_Recommend.jsp"></c:import>
		</div>

		<!-- 공지 -->
		<div id="notice">
		<c:import url="BoardList/notice.jsp"></c:import>
		</div>

		<!-- 최신영화소개 -->
		<div id="newMovieIntro">
		<c:import url="BoardList/newMovieIntro.jsp"></c:import>
		</div>

		<!-- 회원 영화 추천 -->
		<div id="member">
		<c:import url="BoardList/member_Recommend.jsp"></c:import>
		</div>

	</div>
</body>
</html>
