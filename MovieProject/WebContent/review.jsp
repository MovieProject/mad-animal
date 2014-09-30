<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie</title>
<link rel="stylesheet" href="css/board.css">
</head>
<body>

	<div id="page">

		<header id="header">
			<c:import url="top.jsp"></c:import>
		</header>

		<div id="content">
			<table id="reviewtable" class="maintable">
				<caption id="boardtitle">한줄평</caption>
				<tbody>
					<c:forEach begin="1" end="8">
						<tr>
							<th>영화 제목</th>
							<td class="title">해바라기</td>
							<th class="name">작성자</th>
							<td class="name">오태식</td>
							<th class="regdate">작성일</th>
							<td class="regdate">2006.11.23</td>
						</tr>
						<tr>
							<th>한줄평</th>
							<td class="oneline" colspan="5">한줄평</td>
						</tr>
					</c:forEach>
				</tbody>

				<tr>
					<td id="pagenavigator" colspan="6">
					<c:if test="${startPageNumber >1}">
						<a href="list?pageNumber=${startPageNumber - 1}&searchType=${searchType}&searchText=${searchText}">이전</a>
					</c:if>
					<c:forEach begin="${startPageNumber}" end="${endPageNumber}" var="pageNumber">
						<c:choose>
							<c:when test="${pageNumber eq currentPageNumber}">
								<a class="pagenumber currpage">${pageNumber}</a>
							</c:when>
							<c:otherwise>
								<a class="pagenumber" href="list?pageNumber=${pageNumber}&searchType=${searchType}&searchText=${searchText}">${pageNumber}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
						<c:if test="${endPageNumber < totalPageCount}">
							<a href="list?pageNumber=${endPageNumber + 1}&searchType=${searchType}&searchText=${searchText}">다음</a>
						</c:if>
					</td>
				</tr>
			</table>
			
			<table id="writereview" class="maintable">
				<c:if test="${empty loginMember}">
					<tr>
						<th>영화 제목</th>
						<td colspan="4"><input class="inputtext" type="text" name="content"></td>
						<td id="writeform" rowspan="2"><input id="writebutton" type="button" name="write" value="글쓰기"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="4"><input class="inputtext" type="text"></td>
					</tr>
				</c:if>
			</table>
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

