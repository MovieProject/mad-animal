<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src = "js/board.js"></script>
<link rel="stylesheet" href="css/movie.css">
<!DOCTYPE html>
<form action="<c:url value="/member?action=logout"/>" method="POST">
	<table id="logouttable">

		<tr>
			<td class="message">${loginMember.memberName}님 환영합니다.
			</td>
		</tr>
		<tr>

			<td><a href="<c:url value="updateMember.jsp"/>">회원정보수정</a></td>
			<td><a onclick="removeCheck('<c:url value="/member?action=remove&memberID=${loginMember.memberID }"/>');">회원탈퇴</a></td>

		</tr>
		<tr>
			<td colspan="2"><input type="submit" name="logout" value="로그아웃" ></td>
		</tr>
	</table>
</form>
