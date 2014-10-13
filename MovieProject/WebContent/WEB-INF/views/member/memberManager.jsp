<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie</title>
<link rel="stylesheet" href="../css/movie.css">
<script type="text/javascript" src="js/board.js"></script>
</head>
<body>
	<div id="page">
		<header id="header">
			<c:import url="/top.jsp"></c:import>
		</header>
		<div id="content">
			<form action="<c:url value = '/member/listRemove' />" name="listDelete" method="post" onsubmit="return validateForm();">
				<table id="managertable" class="maintable">
					<caption id="boardtitle">회원관리</caption>
					<thead>
						<tr>
							<td class="num"></td>
							<td class="check">회원선택</td>
							<td class="memberID">ID</td>
							<td class="name">이름</td>
							<td class="level">등급</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="member" items="${requestScope.memberList}"
							varStatus="count">
							<tr>
								<td class="num">${count.count}</td>
								<c:choose>
									<c:when test="${member.grade == 2 }">
										<td class="check"><input type="checkbox" id="items" 
											value="${member.memberID }" disabled="disabled"></td>
									</c:when>
									<c:otherwise>
										<td class="check"><input type="checkbox" id="items" name = "items"
											value="${member.memberID }"></td>
									</c:otherwise>
								</c:choose>
								<td class="memberID"><a
									href="<c:url value="/member/select?memberID=${member.memberID}"/>">${member.memberID }</a></td>
								<td class="name">${member.memberName }</td>
								<c:choose>
									<c:when test="${member.grade == 2 }">
										<td class="level">관리자</td>
									</c:when>
									<c:when test="${member.grade == 1 }">
										<td class="level">우수회원</td>
									</c:when>
									<c:when test="${member.grade == 0 }">
										<td class="level">일반회원</td>
									</c:when>
								</c:choose>
							</tr>
						</c:forEach>
					</tbody>
					<tr>
					</tr>
				</table>
				<input type="submit" value="탈퇴" id="removeButton" >
			</form>
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

