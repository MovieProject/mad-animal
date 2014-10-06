<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie</title>
<link rel="stylesheet" href="../css/movie.css">
</head>
<body>

	<table id="listtable" class="maintable"  align="center">
 				<caption id="boardtitle">회원추천  영화</caption>
				<thead>
					<tr>
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
							<c:forEach var="movie" items="${requestScope.movielist}" end = "2">
								<tr>
									<td class="num">${movie.movieNum}</td>
									<td class="title">									
									 <a	href="movieRead?pageNumber=${currentPageNumber}&num=${movie.movieNum}&searchType=${param.searchType}&searchText=${param.searchText}&type=1"target="_parent">${movie.movieName}</a></td>
									<td class="writer">${movie.director}</td>
									<td class="regdate">${movie.releaseDate}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>

</body>
</html>

