<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie</title>
<link rel="stylesheet" href="../css/movie.css">
<script src="../js/board.js"></script>
</head>
<body>

	<div id="page">

		<header id="header">
			<c:import url="/top.jsp"></c:import>
		</header>

		<div id="content">
		
		<form name="writeForm" action="reply?boardNum=${board.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${currentPageNumber}&masterNum=${board.masterNum}&replyOrder=${board.replyOrder}&replyStep=${board.replyStep}" method="POST">
			<table id="updatetable" class="maintable">
				<caption>답글 작성</caption>
				<thead>
					<tr>
						<th>제 목</th>
						<td><input class="titleinput" type="text" name="title" maxlength="100" value = "re: ${board.title}"></td>
					</tr>
					<tr>
						<th>글쓴이</th>
						<td><input class="writerinput" type="text" name="writer" maxlength="20" readonly="readonly" value="${loginMember.memberName}"></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="2">
							<textarea class="contentsinput ckeditor" name="contents"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="buttonbar">
				<input type="submit" value="등록" onclick="boardWriteCheck(this.form);">
				<input type="button" value="취소" onclick="goUrl('read?boardNum=${board.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}&pageNumber=${currentPageNumber}');">
			</div>
		</form>
		
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












		

		