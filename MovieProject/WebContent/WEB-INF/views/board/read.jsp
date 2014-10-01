<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie</title>
<link rel="stylesheet" href="../css/movie.css">
<link rel="stylesheet" href="../css/board.css">
<script src="../js/board.js"></script>
</head>
<body>

	<div id="page">

		<header id="header">
			<c:import url="/top.jsp"></c:import>
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
					<td class="writer">${board.writerName}</td>
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
			<input type=button value="목록" onclick="goUrl('list?pageNumber=${currentPageNumber}&boardNum=${board.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}');">
			<c:if test="${not empty loginMember && loginMember.memberID eq board.writerID}"> <!--관리자 ID 비교해서 수정,삭제할 수 있게 -->
				<input type="button" value="수정" onclick="goUrl('updateForm?boardNum=${board.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${currentPageNumber}');">
				<input type="button" value="삭제" onclick="deleteCheck('remove?boardNum=${board.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${currentPageNumber}');">
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
