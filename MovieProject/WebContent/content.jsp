<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="../css/board.css">
<div class="main">

	<!-- 금주영화 -->
	<div id="week">
		<iframe id="preview3" width="380px" height="300px"
			src="<c:url value='/movie/moviepreview?type=2'/>"></iframe>

	</div>
	<!-- 공지 -->
	<div id="notice">
		<iframe id="preview" width="380px" height="300px"
			src="<c:url value='/board/preview'/>"></iframe>
	</div>

	<!-- 최신영화소개 -->
	<div id="newMovieIntro">
		<iframe id="preview1" width="380px" height="300px"
			src="<c:url value='/movie/moviepreview?type=3'/>"></iframe>

	</div>

	<!-- 회원 영화 추천 -->
	<div id="member">
		<iframe id="preview2" width="380px" height="300px"
			src="<c:url value='/movie/moviepreview?type=1'/>"></iframe>
	</div>

</div>
