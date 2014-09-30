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

			<table id="managertable" class="maintable" align="center">
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
					<tr>
						<td class="num"></td>
						<td class="check"><input type="checkbox"></td>
						<td class="memberID"><a href="<c:url value="/detailsMember.jsp"/>">lig08</a></td>
						<td class="name">송영욱</td>
						<td class="level">등급</td>
					</tr>

				</tbody>
				<tr>
				</tr>
			</table>
			<table id="manager">
			<tr>
					<td class="remove"><input type="button" value="탈퇴" id=removeButton></td>
			</tr>
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

