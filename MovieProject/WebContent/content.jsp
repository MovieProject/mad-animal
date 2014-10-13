<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="../css/board.css">
<div class="main">

	<!-- 금주영화 -->
	<div id="prevWeek">
		<iframe id="preview3" src="<c:url value='/movie/moviepreview?type=3'/>"></iframe>
	</div>
	
	<!-- 공지 -->
	<div id="prevNotice">
		<iframe id="preview" src="<c:url value='/board/preview'/>"></iframe>
	</div>

	<!-- 최신영화소개 -->
	<div id="prevNewMovieIntro">
		<iframe id="preview1" src="<c:url value='/movie/moviepreview?type=2'/>"></iframe>
	</div>

	<!-- 회원 영화 추천 -->
	<div id="prevMember">
		<iframe id="preview2" src="<c:url value='/movie/moviepreview?type=1'/>"></iframe>
	</div>
</div>
