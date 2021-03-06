<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie</title>
<link rel="stylesheet" href="../css/board.css">
<script type="text/javascript" src="../js/board.js"></script>
</head>
<body>

	<div id="page">

		<header id="header">
			<c:import url="/top.jsp"></c:import>
		</header>

		<div id="content">
			<form action="removeMovieList" method="post"
				id="member_recommend_form">
				<table id="listtable" class="maintable">
					<caption id="boardtitle">회원추천 영화</caption>
					<thead>
						<tr>
							<c:if test="${loginMember.grade eq 2 }">
								<th class="check"></th>
							</c:if>
							<th class="num"></th>
							<th class="title">제 목</th>
							<th class="writer">감 독</th>
							<th class="regdate">개봉일</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>

							<c:when test="${empty requestScope.movielist}">
								<tr>
									<td colspan="5">등록된 게시물이 없습니다.</td>
								</tr>
							</c:when>

							<c:otherwise>

								<c:forEach var="movie" items="${requestScope.movielist}">
									<tr>
										<c:if test="${loginMember.grade eq 2 }">
											<td class="check"><input type="checkbox" id="items"
												name="items" value="${movie.movieNum}"></td>
										</c:if>
										<td class="num">${movie.movieNum}</td>
										<td class="title"><a
											href="movieRead?pageNumber=${currentPageNumber}&num=${movie.movieNum}&searchType=${param.searchType}&searchText=${param.searchText}&type=1">${movie.movieName}</a></td>
										<td class="writer">${movie.director}</td>
										<td class="regdate">${movie.releaseDate}</td>
									</tr>
								</c:forEach>

							</c:otherwise>
						</c:choose>
					</tbody>
					<tfoot>
						<tr>

							<td id="pagenavigator" colspan="5"><c:if
									test="${startPageNumber >1}">
									<a
										href="movielist?type=1&pageNumber=${startPageNumber - 1}&searchType=${searchType}&searchText=${searchText}">이전</a>
								</c:if> <c:forEach begin="${startPageNumber}" end="${endPageNumber}"
									var="pageNumber">
									<c:choose>
										<c:when test="${pageNumber eq currentPageNumber}">
											<a class="pagenumber currpage">${pageNumber}</a>
										</c:when>
										<c:otherwise>
											<a class="pagenumber"
												href="movielist?type=1&pageNumber=${pageNumber}&searchType=${searchType}&searchText=${searchText}">${pageNumber}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach> <c:if test="${endPageNumber < totalPageCount}">
									<a
										href="movielist?type=1&pageNumber=${endPageNumber + 1}&searchType=${searchType}&searchText=${searchText}">다음</a>
								</c:if></td>
						</tr>
					</tfoot>
				</table>
			</form>
			<div class="buttonbar">
				<form name="searchForm" action="movielist" method="post"
					onsubmit="return searchCheck(this.form);">
					<input type="hidden" name="type" value="1"> <select
						name="searchType">
						<option value="all"
							<c:if test="${empty param.searchType}" >selected="selected"</c:if>>전체검색</option>
						<option value="movie_name"
							<c:if test="${param.searchType == 'movie_name'}"> selected="selected"</c:if>>영화제목</option>
						<option value="director"
							<c:if test="${param.searchType eq 'director'}"> selected="selected"</c:if>>감독</option>
					</select> <input id="searchinput" type="text" name="searchText"
						value="${param.searchText}"> <input type="submit"
						value="검색"> <input type="button" value="목록"
						onclick="goUrl('movielist?type=1');">
					<c:if test="${not empty loginMember && loginMember.grade <= 2}">
						<input type="button" value="글쓰기"
							onclick="goUrl('writeMovieForm?type=1');">
						<c:if test="${loginMember.grade eq 2 }">
							<input type="button" value="선택 삭제" id='moviesRemove'
								onclick="validateMovie();">
						</c:if>
					</c:if>
				</form>
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

