<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<link rel="stylesheet" href="../css/board.css">

</head>
<body>


	<div class="main">

		<!-- 금주영화 -->
		<div id="week">
		
		<div class="boardpage">
				<table id="listtable" class="maintable">
<%-- 					<caption id="title">공지 사항</caption> --%>
					<thead>
						<tr>
							<th class="num"></th>
							<th class="title">제 목</th>
							<th class="writer">글쓴이</th>
							<th class="regdate">작성일</th>

						</tr>
					</thead>
					<tbody>
						<%-- 				<c:choose>

					<c:when test="${empty requestScope.boardList}">
						<tr>
							<td colspan="5">등록된 게시물이 없습니다.</td>
						</tr>
					</c:when>

					<c:otherwise>
						<c:forEach var="board" items="${requestScope.boardList}">
							<tr>
								<td class="num">${board.num}</td>
								<td class="title">
								<c:forEach begin="1" end="${board.replyStep}">
									&nbsp;
								</c:forEach>
								<a	href="read?pageNumber=${currentPageNumber}&num=${board.num}&searchType=${param.searchType}&searchText=${param.searchText}">${board.title}</a></td>
								<td class="writer">${board.writer}</td>
								<td class="regdate">${board.regDate}</td>
							</tr>
						</c:forEach>
					</c:otherwise>

				</c:choose> 
--%>
						<tr>
							<td class="num">1</td>
							<td class="title"><a href="read.jsp">여기는 금주추천영화 포스터를 보여줄건데 아직 안만든듯</a></td>
							<td class="writer">운영자</td>
							<td class="regdate">2014.7.1</td>
						</tr>
					</tbody>

				</table>

			</div>
		
		
		</div>
		<!-- 공지 -->
		<div id="notice">
			<div class="boardpage">
				<table id="listtable" class="maintable">
<%-- 					<caption id="title">공지 사항</caption> --%>
					<thead>
						<tr>
							<th class="num"></th>
							<th class="title">제 목</th>
							<th class="writer">글쓴이</th>
							<th class="regdate">작성일</th>

						</tr>
					</thead>
					<tbody>
						<%-- 				<c:choose>

					<c:when test="${empty requestScope.boardList}">
						<tr>
							<td colspan="5">등록된 게시물이 없습니다.</td>
						</tr>
					</c:when>

					<c:otherwise>
						<c:forEach var="board" items="${requestScope.boardList}">
							<tr>
								<td class="num">${board.num}</td>
								<td class="title">
								<c:forEach begin="1" end="${board.replyStep}">
									&nbsp;
								</c:forEach>
								<a	href="read?pageNumber=${currentPageNumber}&num=${board.num}&searchType=${param.searchType}&searchText=${param.searchText}">${board.title}</a></td>
								<td class="writer">${board.writer}</td>
								<td class="regdate">${board.regDate}</td>
							</tr>
						</c:forEach>
					</c:otherwise>

				</c:choose> 
--%>
						<tr>
							<td class="num">1</td>
							<td class="title"><a href="read.jsp">안녕하세요. 공지사항게시판입니다.</a></td>
							<td class="writer">운영자</td>
							<td class="regdate">2014.7.1</td>
						</tr>
					</tbody>

				</table>

			</div>
		</div>

		<!-- 최신영화소개 -->
		<div id="newMovieIntro">
			<div class="boardpage">
				<table id="listtable" class="maintable">
<%-- 					<caption id="title">최신 영화 소개</caption> --%>
					<thead>
						<tr>
							<th class="num"></th>
							<th class="title">제 목</th>
							<th class="writer">글쓴이</th>
							<th class="regdate">작성일</th>

						</tr>
					</thead>
					<tbody>
					<%-- 	<c:choose>

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
									</tr>
								</c:forEach>
							</c:otherwise>

						</c:choose>
 --%>
						<tr>
							<td class="num">1</td>
							<td class="title"><a href="read.jsp">안녕하세요. 공지사항게시판입니다.</a></td>
							<td class="writer">운영자</td>
							<td class="regdate">2014.7.1</td>
						</tr>
					</tbody>

				</table>

			</div>
		</div>

		<!-- 회원 영화 추천 -->
		<div id="member">
			<div class="boardpage">
				<table id="listtable" class="maintable">
<%-- 					<caption id="boardtitle">회원 영화 추천</caption> --%>
					<thead>
						<tr>
							<th class="num"></th>
							<th class="title">제 목</th>
							<th class="writer">글쓴이</th>
							<th class="regdate">작성일</th>

						</tr>
					</thead>
					<tbody>
						<%-- 				<c:choose>

					<c:when test="${empty requestScope.boardList}">
						<tr>
							<td colspan="5">등록된 게시물이 없습니다.</td>
						</tr>
					</c:when>

					<c:otherwise>
						<c:forEach var="board" items="${requestScope.boardList}">
							<tr>
								<td class="num">${board.num}</td>
								<td class="title">
								<c:forEach begin="1" end="${board.replyStep}">
									&nbsp;
								</c:forEach>
								<a	href="read?pageNumber=${currentPageNumber}&num=${board.num}&searchType=${param.searchType}&searchText=${param.searchText}">${board.title}</a></td>
								<td class="writer">${board.writer}</td>
								<td class="regdate">${board.regDate}</td>
							</tr>
						</c:forEach>
					</c:otherwise>

				</c:choose> 
--%>
						<tr>
							<td class="num">1</td>
							<td class="title"><a href="read.jsp">안녕하세요. 영화추천입니다.</a></td>
							<td class="writer">운영자</td>
							<td class="regdate">2014.7.1</td>
						</tr>
					</tbody>

				</table>

			</div>
		</div>
	</div>
	</body>
	</html>
	