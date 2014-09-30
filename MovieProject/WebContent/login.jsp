<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="css/movie.css">
<%-- request scope 속성에 에러메시지가 있으면 출력한다. --%>
<%
	String loginErrorMsg = (String) request
			.getAttribute("loginErrorMsg");
%>
<c:if test="${not empty loginErrorMsg}">

	<ul id="loginerrormsg">
		<li>${loginErrorMsg}</li>
	</ul>
</c:if>
<form action="<c:url value="/memberController?action=login"/>" method="POST">
	<table id="logintable">
		<tr>
			<td><a href="<c:url value="registerMember.jsp"/>">회원가입</a></td>
		</tr>

		<tr>
			<td><input type="text" name="memberID" size="13"
				placeholder="아이디"></td>
			<td rowspan="2"><input type="submit" name="login" value="로그인"
				id="login"></td>
		</tr>
		<tr>
			<td><input type="password" name="password" size="13"
				placeholder="비밀번호"></td>
		</tr>

	</table>
</form>