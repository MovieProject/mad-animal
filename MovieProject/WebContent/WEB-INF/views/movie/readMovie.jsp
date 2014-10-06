<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie</title>
<link rel="stylesheet" href="../css/movie.css">
<link rel="stylesheet" href="../css/board.css">
<script type="text/javascript" src="../js/script.js"></script>
<script type="text/javascript" src="../js/board.js"></script>
</head>
<body>

	<div id="page">

		<header id="header">
			<c:import url="/top.jsp"></c:import>
		</header>
		<div id="content">
			<table id="readtable" class="maintable">
			<c:choose>
			<c:when test="${movie.type eq 2 }">
				<caption id="contenttitle">금주의 영화</caption>
			</c:when>
			<c:when test="${movie.type eq 1 }">
				<caption id="contenttitle">회원 추천 영화</caption>
			</c:when>
			<c:when test="${movie.type eq 3 }">
				<caption id="contenttitle">최신 영화 정보</caption>
			</c:when>
			
			</c:choose>
				<tr>
					<td id="poster" rowspan="6"><img alt="영화 포스터"
						src="<c:url value="/images/${movie.photoDir}" />"></td>
					<th>제목</th>
					<td class="title">${movie.movieName}</td>
				</tr>
				<tr>
					<th>장르</th>
					<td class="writer">${movie.genre }</td>
				</tr>
				<tr>
					<th>감독</th>
					<td class="director">${movie.director }</td>
				</tr>
				<tr>
					<th>개봉일</th>
					<td class="date">${movie.releaseDate }</td>
				</tr>
				<tr>
					<th colspan="2">줄거리</th>
				</tr>
				<tr>
					<td id="story" colspan="2">${movie.synopsis }</td>
				</tr>
			</table>
			<div class="buttonbar">
				<c:if test="${empty loginMember}">
					<input type="button" value="목록"
						onclick="goUrl('movielist?type=${movie.type}&searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${currentPageNumber}');">
				</c:if>
				<c:if test="${not empty loginMember}">
					<!-- <input type="button" value="목록" onclick="goUrl('board?action=list');"> -->
					<input type="button" value="목록"
						onclick="goUrl('movielist?type=${movie.type}&searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${currentPageNumber}');">
					<input type="button" value="수정"
						onclick="goUrl('updateMovieForm?type=${movie.type}&num=${movie.movieNum}&searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${currentPageNumber}');">
					<input type="button" value="삭제"
						onclick="deleteCheck('removeMovie?type=${movie.type}&num=${movie.movieNum}&searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${currentPageNumber}');">
				</c:if>
			</div>

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
