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
			<div id="week">
				<table id="weektable" class="maintable">
				 	<caption id="contenttitle">금주의 영화</caption>
					<tr>
						<td id="poster" rowspan="6">
						<img alt="영화 포스터" src="<c:url value="http://placehold.it/150x200" />"></td>
						<th>제목</th>
						<td class="content">루시</td>
					</tr>
					<tr>
						<th>장르</th>
						<td class="content">SF</td>
					</tr>
					<tr>
						<th>감독</th>
						<td class="content">???</td>
					</tr>
					<tr>
						<th>개봉일</th>
						<td class="content">XX.XX.XX</td>
					</tr>
					<tr>
						<th colspan="2">줄거리</th>
					</tr>
					<tr>
						<td id="story" colspan="2">서사 텍스트 속에서 전개되는 이야기의 흐름과 정보들이 그 서사 텍스트 바깥의 누군가에 의해 요약의 형식으로 추출된 결과를 지칭하는 용어이다. 줄거리라는 말의 일반적인 용법에 의거하면, 줄거리는 서사 텍스트 안에 담겨 있는 이야기 자체라기보다 이야기에 관한 정보를 교환하는 과정에서 발생하는 이야기에 대한 사회적 유통의...
						</td>
					</tr>
				</table>
			</div>
			
			<table id="listtable" class="maintable">
				<caption>게시글 목록</caption>
				<thead>
					<tr>
						<th class="num"></th>
						<th class="title">제 목</th>
						<th class="writer">글쓴이</th>
						<th class="regdate">작성일</th>
						<th class="readcount">조회</th>
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
							<c:forEach var="board" items="${requestScope.boardList}">
								<tr>
									<td class="num">${board.num}</td>
									<td class="title"><c:forEach begin="1"
											end="${board.replyStep}">
									&nbsp;
								</c:forEach> <a
										href="read?pageNumber=${currentPageNumber}&num=${board.num}&searchType=${param.searchType}&searchText=${param.searchText}">${board.title}</a></td>
									<td class="writer">${board.writer}</td>
									<td class="regdate">${board.regDate}</td>
									<td class="readcount">${board.readCount}</td>
								</tr>
							</c:forEach>
						</c:otherwise>

					</c:choose>


					<!-- <tr>
					<td class="num">1</td>
					<td class="title"><a href="read.jsp">안녕하세요. 게시판 지기입니다.</a></td>
					<td class="writer">운영자</td>
					<td class="regdate">2014.07.01</td>
					<td class="readcount">10</td>
				</tr> -->
				</tbody>
				<tfoot>
					<tr>
						<td id="pagenavigator" colspan="5"><c:if
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
				</tfoot>
			</table>
			<div class="buttonbar">
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
						<c:if test="${not empty loginMember}">
						<input type="button" value="글쓰기" onclick="goUrl('writeForm');">
						</c:if>
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

