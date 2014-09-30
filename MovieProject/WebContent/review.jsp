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
			<table id="listtable" class="maintable"  align="center">
				<caption id="boardtitle">리뷰</caption>
				<tbody>
				<c:forEach begin="1" end="8">
					<tr>
						<td>영화 제목</td><td>제목</td>
						<td>작성자</td><td>누구</td>
						<td>작성일</td><td>2014.07.01</td>
					</tr>
					<tr>
						<td>내용</td><td colspan="5">한줄평</td>
					</tr>
				</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td id="pagenavigator" colspan="6"><c:if
								test="${startPageNumber >1}">
								<a
									href="list?pageNumber=${startPageNumber - 1}&searchType=${searchType}&searchText=${searchText}">이전</a>
							</c:if> <c:forEach begin="${startPageNumber}" end="${endPageNumber}"
								var="pageNumber">
								<c:choose>
									<c:when test="${pageNumber eq currentPageNumber}">
										<a class="pagenumber currpage">${pageNumber}</a>
									</c:when>
									<c:otherwise>
										<a class="pagenumber"
											href="list?pageNumber=${pageNumber}&searchType=${searchType}&searchText=${searchText}">${pageNumber}</a>
									</c:otherwise>
								</c:choose>
							</c:forEach> <c:if test="${endPageNumber < totalPageCount}">
								<a
									href="list?pageNumber=${endPageNumber + 1}&searchType=${searchType}&searchText=${searchText}">다음</a>
							</c:if></td>
					</tr>
					
					<tr>
					<td>영화 제목</td><td colspan="2"><input type="text" name="content" maxlength="20"></td>
					<td>작성자</td><td><input type="text" name="content" maxlength="20"></td><td rowspan="2"><input type="button" name="write" value="글쓰기"></td>
					</tr>
					<tr>
						<td>내용</td><td colspan="4"><input type="text" maxlength="300"></td>
					</tr>
				</tfoot>
			</table>
			<div class="buttonbar" >
				<form name="searchForm" action="list" method="GET"
					onsubmit="return searchCheck();">
					<select name="searchType">
						<option value="all"
							<c:if test="${empty param.searchType}" >selected="selected"</c:if>>전체검색</option>
						<option value="title"
							<c:if test="${param.searchType == 'title'}"> selected="selected"</c:if>>제목</option>
						<option value="writer"
							<c:if test="${param.searchType eq 'writer'}"> selected="selected"</c:if>>글쓴이</option>
						<option value="contents"
							<c:if test="${param.searchType == 'contents'}"> selected="selected"</c:if>>내용</option>
					</select> <input id="searchinput" type="text" name="searchText"
						value="${param.searchText}"> <input type="submit"
						value="검색" onclick="searchCheck(this.form);"> <input
						type="button" value="목록" onclick="goUrl('list');">
				</form>
			</div>
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

