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
					<td class="title" colspan="5">${board.title}</td>
				</tr>
				<tr>
					<th>글쓴이</th>
					<td class="writer">${board.writer}</td>
					<th>조회</th>
					<td class="readcount">${board.readCount}</td>
					<th>작성일</th>
					<td class="regdate">${board.regDate}</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="contents" colspan="6">${board.contents}</td>
				</tr>
			</tbody>
		</table>
		<div class="buttonbar">
				<c:if test="${empty loginMember}">
				<input type="button" value="목록" onclick="goUrl('list?searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${currentPageNumber}');">
			<input type="button" value="답글" onclick="goUrl('replyForm?num=${board.num}&searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${currentPageNumber}');">
				</c:if>
				<c:if test="${not empty loginMember}">
			<!-- <input type="button" value="목록" onclick="goUrl('board?action=list');"> -->
			<input type="button" value="목록" onclick="goUrl('list?searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${currentPageNumber}');">
			<input type="button" value="답글" onclick="goUrl('replyForm?num=${board.num}&searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${currentPageNumber}');">
			<input type="button" value="수정" onclick="goUrl('updateForm?num=${board.num}&searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${currentPageNumber}');">
			<input type="button" value="삭제" onclick="deleteCheck('remove?num=${board.num}&searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${currentPageNumber}');">
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
