<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie</title>
<link rel="stylesheet" href="css/test.css">
</head>
<body>

	<div id="content">

		<h4>[회원가입]</h4>
		<form action="member?action=register" method="POST">
			<table class="registertable">

				<tr>

					<td><input type="text" name="memberID" size="20"
						maxlength="15" placeholder="ID"></td>
				</tr>

				<tr>
					<td><input type="password" name="password" size="20"
						maxlength="10" placeholder="비밀번호"></td>
				</tr>
				<tr>
					<td><input type="text" name="name" size="20" maxlength="20"
						placeholder="이름"></td>
				</tr>
				<tr>
					<td><input type="text" name="age" size="20" maxlength="20"
						placeholder="나이"></td>
				</tr>
				<tr>
					<td><input type="text" name="address" size="20" maxlength="20"
						placeholder="주소"></td>
				</tr>
				<tr>
					<td><input type="text" name="email" size="20" maxlength="20"
						placeholder="E-Mail"></td>
				</tr>
				<tr>
					<td><input type="text" name="tel" size="20" maxlength="20"
						placeholder="전화번호"></td>
				</tr>

				<tr>
					<td><input type="submit" value="회원가입" id="reg_ok">
				</tr>
			</table>
		</form>
	</div>

</body>
</html>
