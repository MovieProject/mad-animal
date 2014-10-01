<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie</title>
<link rel="stylesheet" href="css/movie.css">
<link rel="stylesheet" href="css/board.css">
</head>
<body>

	<div id="page">

		<header id="header">
			<c:import url="top.jsp"></c:import>
		</header>

		<div id="content">

			<table id="readtable" class="maintable">
				<caption>게시글 보기</caption>
				<thead>
					<tr>
						<th>제 목</th>
						<td class="title" colspan="5">${movie.movieName}</td>
					</tr>
					<tr>
						<th>장르</th>
						<td class="writer">${movie.genre}</td>
						<th>개봉일</th>
						<td class="readcount">${movie.releaseDate}</td>
						<th>감독</th>
						<td class="regdate">${movie.director}</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>줄거리</th>
					</tr>
					<tr>
						<td class="contents" colspan="6">${movie.synopsis}</td>
					</tr>
				</tbody>
			</table>
			<div class="buttonbar">
				<c:if test="${empty loginMember}">
					<input type="button" value="목록"
						onclick="goUrl('list?searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${currentPageNumber}');">
					<input type="button" value="답글"
						onclick="goUrl('replyForm?num=${board.num}&searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${currentPageNumber}');">
				</c:if>
				<c:if test="${not empty loginMember}">
					<!-- <input type="button" value="목록" onclick="goUrl('board?action=list');"> -->
					<input type="button" value="목록"
						onclick="goUrl('movielist?searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${currentPageNumber}');">
					<input type="button" value="수정"
						onclick="goUrl('movie?action=movieupdate&num=${movie.movieNum}&searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${currentPageNumber}');">
					<input type="button" value="삭제"
						onclick="deleteCheck('movie?action=remove&num=${movie.movieNum}&searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${currentPageNumber}');">
				</c:if>
			</div>

		</div>
		<aside id="sidebar">
			<c:import url="side-bar.jsp"></c:import>
		</aside>

		<footer id="footer">
			<c:import url="footer.jsp"></c:import>
		</footer>
	</div>
</body>
</html>
