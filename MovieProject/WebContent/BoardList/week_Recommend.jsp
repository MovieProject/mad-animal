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
			<img alt="영화 포스터" src="<c:url value="http://placehold.it/150x200" />"></td>
			<th>제목</th>
			<td class="content">루시</td>
		</tr>
		<tr>
			<th>장르</th>
			<td class="content">SF</td>
		</tr>
		<tr>
			<th>감독</th>
			<td class="content">???</td>
		</tr>
		<tr>
			<th>개봉일</th>
			<td class="content">XX.XX.XX</td>
		</tr>
		<tr>
			<th colspan="2">줄거리</th>
		</tr>
		<tr>
			<td id="story" colspan="2">서사 텍스트 속에서 전개되는 이야기의 흐름과 정보들이 그 서사 텍스트 바깥의 누군가에 의해 요약의 형식으로 추출된 결과를 지칭하는 용어이다. 줄거리라는 말의 일반적인 용법에 의거하면, 줄거리는 서사 텍스트 안에 담겨 있는 이야기 자체라기보다 이야기에 관한 정보를 교환하는 과정에서 발생하는 이야기에 대한 사회적 유통의...
			</td>
		</tr>
	</table>
</body>
</html>

