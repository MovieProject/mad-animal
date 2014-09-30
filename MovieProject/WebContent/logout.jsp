<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="css/movie.css">
<script src = "../js/board.js"></script>
<!DOCTYPE html>
<form action="<c:url value="/member?action=logout"/>" method="POST">
	<table id="logouttable">

		<tr>
			<td class="message">${loginMember.memberName}님<br> 환영합니다.
			</td>
		</tr>
		<tr>
			<td><a href="<c:url value="updateMember.jsp"/>">회원정보수정</a></td>
			<td><a href="<c:url value="/member?action=remove&memberID=${loginMember.memberID}"/>">회원탈퇴</a></td>
		</tr>
		<tr>
			<td><input type="submit" name="logout" value="로그아웃" ></td>
		</tr>
	</table>
</form>
