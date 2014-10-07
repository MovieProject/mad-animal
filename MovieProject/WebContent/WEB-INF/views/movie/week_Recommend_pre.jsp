<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="../css/movie.css">
<table id="weekPreListtable" class="maintable">
	<caption id="contenttitle">금주의 영화</caption>
	<tr>
		<td id="poster" rowspan="6"><img alt="영화 포스터"
			src="<c:url value="/images/${movie.photoDir}" />" width="150px"
			height="200px"></td>
		<th>제목</th>
		<td class="content">${movie.movieName}</td>
	</tr>
	<tr>
		<th>장르</th>
		<td class="content">${movie.genre}</td>
	</tr>
	<tr>
		<th>감독</th>
		<td class="content">${movie.genre}</td>
	</tr>
	<tr>
		<th>개봉일</th>
		<td class="content">${movie.director}</td>
	</tr>
	<tr>
		<th colspan="2">줄거리</th>
	</tr>
	<tr>
		<td id="story" colspan="2">${movie.synopsis}</td>
	</tr>
</table>

