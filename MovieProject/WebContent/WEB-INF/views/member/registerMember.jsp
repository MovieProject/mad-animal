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

			<h4>[회원가입]</h4>
			<form action=" <c:url value = '/member?action=register'/> "
				method="POST">
				<table class="registertable">

					<tr>
						<th class="label">회원ID</th>
						<td class="text"><input type="text" name="memberID" size="20"
							maxlength="15"></td>
					</tr>

					<tr>
						<th class="label">비밀번호</th>
						<td><input type="password" name="password" size="20"
							maxlength="10"></td>
					</tr>
					<tr>
						<th class="label">이름</th>
						<td><input type="text" name="name" size="20" maxlength="20"></td>
					</tr>
					<tr>
						<th class="label">나이</th>
						<td><input type="text" name="age" size="20" maxlength="20"></td>
					</tr>
					<tr>
						<th class="label">주소</th>
						<td><input type="text" name="address" size="20"
							maxlength="20"></td>
					</tr>
					<tr>
						<th class="label">이메일</th>
						<td><input type="text" name="email" size="20" maxlength="20"></td>
					</tr>
					<tr>
						<th class="label">전화번호</th>
						<td><input type="text" name="tel" size="20" maxlength="20"></td>
					</tr>

					<tr>
						<td></td>
						<td><input type="submit" value="회원가입"> <input
							type="reset" value="취소"></td>
					</tr>
				</table>
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