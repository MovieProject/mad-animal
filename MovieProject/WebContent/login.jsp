<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="css/movie.css">
<script src="js/board.js"></script>
<c:if test="${not empty loginErrorMsg}">
	<ul id="loginerrormsg">
		<li>${loginErrorMsg}</li>
	</ul>
</c:if> 
<form action='<c:url value="/member/login"/>' method="POST" >
	<table id="logintable">
		<tr>
			<td><a href="<c:url value='/member/registerform'/>">회원가입</a></td>
		</tr>
		<tr>
			<td><input type="text" name="memberID" size="13" placeholder="아이디" tabindex="1"></td>
			<td rowspan="2"><input type="submit" id="login" name="login" value="로그인" tabindex="3" ></td>
		</tr>
		<tr>
			<td><input type="password" name="password" size="13" placeholder="비밀번호" tabindex="2"></td>
		</tr>
	</table>
</form>
