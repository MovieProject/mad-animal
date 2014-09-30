<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="<c:url value='/member?action=logout' />" method="POST">
	<table id="logouttable">
		<tr>
			<td class="message">${ loginMember.name }님<br> 환영합니다.
			</td>
		</tr>
		<tr>
			<td><input type="submit" name="logout" value="로그아웃"></td>
		</tr>
	</table>
</form>
