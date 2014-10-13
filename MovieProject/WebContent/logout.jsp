<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="css/movie.css">
<script src ="../js/board.js"></script>
<form action="<c:url value="/member/logout"/>" method="POST">
	<table id="logintable">
		<tr>
			<td><a href="<c:url value="/member/updateform"/>">회원정보수정</a></td>
			<td><a onclick="removeCheck('<c:url value="member/remove?memberID=${loginMember.memberID }"/>');">회원탈퇴</a></td>
		</tr>
		<tr>
			<td class="message"><span class="loginName">${loginMember.memberName}</span>님<br> 환영합니다.
			</td>
			<td><input type="submit" name="logout" value="로그아웃" ></td>
		</tr>
	</table>
</form>
