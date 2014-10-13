<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="../css/movie.css">
<table class="prevmaintable">
	<caption id="preboardtitle">최신 영화 정보</caption>
	<thead>
		<tr>
			<th class="previewTitle">제 목</th>
			<th class="previewWriter">감 독</th>
			<th class="previewRegdate">개봉일</th>
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
			<c:forEach var="movie" items="${requestScope.movielist}" end="1">
				<tr>
					<td class="previewTitle"><a
						href="movieRead?pageNumber=${currentPageNumber}&num=${movie.movieNum}&searchType=${param.searchType}&searchText=${param.searchText}&type=3"
						target="_parent">${movie.movieName}</a></td>
					<td class="previewWriter">${movie.director}</td>
					<td class="previewRegdate">${movie.releaseDate}</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	</tbody>
</table>
