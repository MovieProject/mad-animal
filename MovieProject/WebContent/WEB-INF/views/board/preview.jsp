<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="../js/board.js"></script>
<link rel="stylesheet" href="../css/movie.css">
<table class="prevmaintable">
	<caption id="preboardtitle">공지사항</caption>
	<thead>
		<tr>
			<th class="previewTitle">제 목</th>
			<th class="previewWriter">글쓴이</th>
			<th class="previewRegdate">작성일</th>
		</tr>
	</thead>
	<tbody>
	<c:choose>
			<c:when test="${empty requestScope.boardList}">
				<tr>
					<td colspan="5">등록된 게시물이 없습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="board" items="${requestScope.boardList}" end="6">
					<tr>
						<td class="previewTitle">
					<c:forEach begin="1" end="${board.replyStep}">
						&nbsp;
					</c:forEach>
						<a href="read?pageNumber=${currentPageNumber}&boardNum=${board.boardNum}&searchType=${param.searchType}&searchText=${param.searchText}" target="_parent">${board.title}</a></td>
						<td class="previewWriter">${board.writerName}</td>
						<td class="previewRegdate">${board.regDate}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>



