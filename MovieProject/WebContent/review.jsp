<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Movie</title>
	<link rel="stylesheet" href="css/board.css">
	<script src="js/board.js"></script>
</head>
<body>

	<div id="page">

		<header id="header">
			<c:import url="top.jsp"></c:import>
		</header>

		<div id="content">
			<table id="reviewtable" class="maintable">
				<caption id="boardtitle">한줄평</caption>
				<thead>
					<tr><td colspan="6" align="right"><%-- align은 임시속성 --%>
						<form name="searchForm" action="list" method="GET" onsubmit="return searchCheck();">
							<select name="searchType">
								<option value="all" <c:if test="${empty param.searchType}" >selected="selected"</c:if>>전체검색</option>
								<option value="title" <c:if test="${param.searchType == 'title'}"> selected="selected"</c:if>>영화 제목</option>
								<option value="writer" <c:if test="${param.searchType eq 'writer'}"> selected="selected"</c:if>>작성자</option>
								<option value="contents" <c:if test="${param.searchType == 'contents'}"> selected="selected"</c:if>>한줄평</option>
							</select>
							<input id="searchinput" type="text" name="searchText" value="${param.searchText}">
								<input type="submit" value="검색" onclick="searchCheck(this.form);">
								<input type="button" value="목록" onclick="goUrl('review?action=list');">
						</form>
					</td></tr>
				</thead>
				<tbody>
					<c:if test="${ empty requestScope.reviewList }">
						<tr>
							<td colspan="5">등록된 게시물이 없습니다.</td>
						</tr>
					</c:if>
					<c:if test="${ not empty requestScope.reviewList }">
						<c:forEach items="${ requestScope.reviewList }" var="review">
		                    <tr>
							<th>영화 제목</th><td class="title">${ review.movieTitle }</td>
							<th class="name">작성자</th><td class="name">${ review.writerName }</td>
							<th class="regdate">작성일</th><td class="regdate">${ review.regDate }</td>
						</tr>
						<tr>
							<c:if test="${ sessionScope.loginMember.memberName != review.writerName && loginMember.grade != 2 }">
							<th>한줄평</th><td class="oneline" colspan="5">${ review.contents }</td>
							</c:if>
							<c:if test="${ sessionScope.loginMember.memberName eq review.writerName || loginMember.grade eq 2}">
							<th>한줄평</th><td class="oneline" colspan="4">${ review.contents }</td>
							<td>
								<input type="button" value="수정" onclick="alert('지금 기능이 없엉');">
								<input type="button" value="삭제" onclick="goUrl('review?action=remove&reviewNum=${review.reviewNum}');">
							</td>
							</c:if>
						</tr>
	                    </c:forEach>
					</c:if>
				</tbody>

				<tr>
					<td id="pagenavigator" colspan="6">
					<c:if test="${startPageNumber >1}">
						<a href="review?action=list&pageNumber=${startPageNumber - 1}&searchType=${searchType}&searchText=${searchText}">이전</a>
					</c:if>
					<c:forEach begin="${startPageNumber}" end="${endPageNumber}" var="pageNumber">
						<c:choose>
							<c:when test="${pageNumber eq currentPageNumber}">
								<a class="pagenumber currpage">${pageNumber}</a>
							</c:when>
							<c:otherwise>
								<a class="pagenumber" href="review?action=list&pageNumber=${pageNumber}&searchType=${searchType}&searchText=${searchText}">${pageNumber}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
						<c:if test="${endPageNumber < totalPageCount}">
							<a href="review?action=list&pageNumber=${endPageNumber + 1}&searchType=${searchType}&searchText=${searchText}">다음</a>
						</c:if>
					</td>
				</tr>
			</table>
			
			<form name="reviewWriteForm" action="review?action=write" method="POST" onsubmit="return reviewWriteCheck();">
				<table id="writereview" class="maintable">
					<c:if test="${not empty loginMember}">
						<tr>
							<th>영화 제목</th>
							<td colspan="4"><input class="inputtext" type="text" name="movieTitle"></td>
							<td id="writeform" rowspan="2"><input id="writebutton" type="submit" value="글쓰기"></td>
						</tr>
						<tr>
							<th>한줄평</th>
							<td colspan="4"><input class="inputtext" type="text" name="contents"></td>
						</tr>
					</c:if>
				</table>
			</form>
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

