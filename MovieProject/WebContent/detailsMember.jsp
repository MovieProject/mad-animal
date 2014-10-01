<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie</title>
<link rel="stylesheet" href="css/board.css">
<script type="text/javascript" src="../js/board.js"></script>
</head>
<body>

	<div id="page">

		<header id="header">
			<c:import url="top.jsp"></c:import>
		</header>

		<div id="content">
			<form name = "managerForm" action = "<c:url value = "/member?action=gradeUpdate&memberID=${selectedMember.memberID}"/>" method = "post">
				<table id="managertable" class="maintable" align="center">
					<caption id="boardtitle">회원관리</caption>
					<thead>
						<tr>
							<td class="memberID">ID</td>
							<td class="name">이름</td>
							<td class="grade">등급</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="memberID">${selectedMember.memberID}</td>
							<td class="name">${selectedMember.memberName}</td>
							<td class="grade"><select name="gradeName" class="gradeName">
									<option id="admin"
										<c:if test ="${selectedMember.grade == 2}">selected="selected"</c:if>
										value="2">관리자</option>
									<option id="greatMember"
										<c:if test ="${selectedMember.grade == 1}">selected="selected"</c:if>
										value="1">우수회원</option>
									<option id="nomalMember"
										<c:if test ="${selectedMember.grade == 0}">selected="selected"</c:if>
										value="0">일반회원</option>
							</select></td>
						</tr>

					</tbody>
					<tr>
					</tr>
				</table>
				<table id="manager">
					<tr>

						<td class="update"><input type="submit" value="수정"
							id="updateButton"></td>
						<td class="remove"><input type="button" value="탈퇴"
							id="removeButton"
							onclick="goUrl('<c:url value = "/member?action=remove&memberID=${selectedMember.memberID}"/>');"></td>

					</tr>
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

