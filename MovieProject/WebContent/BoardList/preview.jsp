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

	<table id="listtable" class="maintable" align="center">
		<caption id="boardtitle">공지사항</caption>
		<thead>
			<tr>
				<th class="num"></th>
				<th class="title">제 목</th>
				<th class="writer">글쓴이</th>
				<th class="regdate">작성일</th>
			</tr>
		</thead>
		<tbody>
		<tbody>
			<c:choose>

				<c:when test="${empty requestScope.boardList}">
					<tr>
						<td colspan="5">등록된 게시물이 없습니다.</td>
					</tr>
				</c:when>

				<c:otherwise>
					<c:forEach var="board" items="${requestScope.boardList}">
						<tr>
							<td class="num">${board.boardNum}</td>
							<td class="title"><c:forEach begin="1"
									end="${board.replyStep}">
									&nbsp;
								</c:forEach> <a
								href="read?pageNumber=${currentPageNumber}&boardNum=${board.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}">${board.title}</a></td>
							<td class="writer">${board.writerName}</td>
							<td class="regdate">${board.regDate}</td>
							<td class="readcount">${board.readCount}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</body>
</html>

