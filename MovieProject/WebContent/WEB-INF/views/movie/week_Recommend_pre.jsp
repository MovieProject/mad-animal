<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie</title>
<link rel="stylesheet" href="css/movie.css">
</head>
<body>
	<table id="weektable" class="maintable">
	 	<caption id="contenttitle">금주의 영화</caption>
		<tr>
			<td id="poster" rowspan="6">
			<img alt="영화 포스터" src="<c:url value="/images/${movielist[0].photoDir }" />"></td>
			<th>제목</th>
			<td class="content">${movielist[0].movieName}</td>
		</tr>
		<tr>
			<th>장르</th>
			<td class="content">${movielist[0].genre}</td>
		</tr>
		<tr>
			<th>감독</th>
			<td class="content">${movielist[0].genre}</td>
		</tr>
		<tr>
			<th>개봉일</th>
			<td class="content">${movielist[0].director}</td>
		</tr>
		<tr>
			<th colspan="2">줄거리</th>
		</tr>
		<tr>
			<td id="story" colspan="2">
			${movelist[0].synopsis}
			</td>
		</tr>
	</table>
</body>
</html>

