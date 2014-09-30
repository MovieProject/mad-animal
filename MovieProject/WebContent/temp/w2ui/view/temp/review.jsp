<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>영화추천 게시판</title>
	<link rel="stylesheet" href="css/board.css">
	<link rel="stylesheet" href="css/movie.css">
	<script src="../js/board.js"></script>
</head>
<body>
	<div class="tableContainer">
		<div class="tableRow">
			<c:import url="top.jsp" />
		</div>
		<div class="tableRow">
			<!-- START of main content-->
			<div class="boardpage">
			<div id="content">
			<c:import url="side-bar.jsp" />
				<table id="listtable" class="maintable">
					<caption>리뷰</caption>
					<tbody>
						<c:if test="${ not empty requestScope.boardList }">
							<tr>
								<td colspan="5">등록된 게시물이 없습니다.</td>
							</tr>
						</c:if>
						
						<c:if test="${ empty requestScope.boardList }">
						<%--
							<c:forEach items="${ requestScope.boardList }" var="board">
						--%>
							<c:forEach begin="1" end="10">
							<tr>
							<td>제목 : </td><td>영화 제목</td>
							<td>	작성자 : </td><td>라라라</td>
							<td>	작성일 : </td><td>YY-MM-DD</td>
							</tr>
							<tr>
							<td>	내용 : </td><td colspan="5">재밌냐?</td>
							</tr>
							</c:forEach>
						</c:if>
						
					</tbody>
					<tfoot>
						<tr>
							<td id="pagenavigator" colspan="6">
								<c:if test="${ startPageNumber > 1 }">
									<a href="list?searchType=${ searchType }&searchText=${ searchText }&pageNumber=${ startPageNumber - 1 }">이전</a>
								</c:if>
								<c:forEach begin="${ startPageNumber }" end="${ endPageNumber }" var="pageNumber">
									<c:choose>
										<c:when test="${ pageNumber eq currentPageNumber }">
											<a class="pagenumber currpage">${ currentPageNumber }</a>
										</c:when>
										<c:otherwise>
											<a class="pagenumber" href="list?searchType=${ param.searchType }&searchText=${ param.searchText }&pageNumber=${ pageNumber }">${ pageNumber }</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${ endPageNumber < totalPageCount }">
									<a href="list?searchType=${ searchType }&searchText=${ searchText }&pageNumber=${ pageNumber + endPageNumber + 1 }">다음</a>
								</c:if>
							</td>
						</tr>
							<tr>
							<td>제목 : </td><td><input type="text"></td>
							<td>	작성자 : </td><td colspan="2"><input type="text"></td>
							<td rowspan="2"><input id="login" type="button" value="글쓰기"></td>
							</tr>
							<tr>
							<td>	내용 : </td><td colspan="4"><input class="title" type="text"></td>
							</tr>
					</tfoot>
				</table>
				<div class="buttonbar">
					<form name="searchForm" action="list" method="POST" onsubmit="return searchCheck();">
						<select name="searchType">
							<option value="all" <c:if test="${ (param.searchType eq 'all') or (empty param.searchType) }"> selected="selected"</c:if>>전체검색</option>
							<option value="title" <c:if test="${ param.searchType eq 'title' }"> selected="selected"</c:if>>제목</option>
							<option value="writer" <c:if test="${ param.searchType eq 'writer' }"> selected="selected"</c:if>>글쓴이</option>
							<option value="contents" <c:if test="${ param.searchType eq 'contents' }"> selected="selected"</c:if>>내용</option>
						</select>
						<input id="searchinput" type="text" name="searchText" value="${ param.searchText }">
						<input type="submit" value="검색"> <input type="button" value="목록" onclick="goUrl('list');">
						<c:if test="${ not empty sessionScope.loginMember }">
							<input type="button" value="글쓰기" onclick="goUrl('writeForm');">
						</c:if>
					</form>
				</div>
			</div>
			</div>
		</div>
		<div class="tableRow">
			<c:import url="footer.jsp" />
		</div>
	</div>
</body>
</html>

