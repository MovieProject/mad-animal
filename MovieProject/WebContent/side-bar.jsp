<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/script.js">

</script>
<div class="sidebar">
<%-- 
		1. session scope에 설정된 "loginMember" 속성이 없으면 로그인 페이지(login.jsp)를,
			있으면 로그아웃 페이지(logout.jsp)를 포함시킨다.(include 액션 사용) 
--%>

	<c:if test="${empty loginMember}">
		<%-- 		<jsp:include page="login.jsp" /> --%>
		<c:import url="/login.jsp"></c:import>
	</c:if>
	<c:if test="${not empty loginMember}">
		<%-- 		<jsp:include page="logout.jsp" />
 --%>
		<c:import url="/logout.jsp"></c:import>
	</c:if>

	<table id="sidebartable">
		<tr>
			<td id="menulabel">Menu</td>
		</tr>
		<tr>
			<td><hr></td>
		</tr>
		<c:if test="${empty loginMember}">

		</c:if>
		<tr>
			<td><a href="<c:url value="notice.jsp"/>">공지사항</a></td>
		</tr>
		<tr>
			<td><a href="<c:url value="/week_Recommend.jsp"/>">금주 추천 영화 게시판</a></td>
		</tr>
		<tr>
			<td><a href="<c:url value="/member_Recommend.jsp"/>">회원 추천 영화 게시판</a></td>
		</tr>
		<tr>
			<td><a href="<c:url value="/newMovieIntro.jsp"/>">최신영화 정보 게시판</a></td>
		</tr>
		<tr>
			<td><a href="<c:url value="/review.jsp"/>">영화 리뷰 게시판</a></td>
		</tr>
		
		<c:if test="${not empty loginMember}">
			<tr>
				<td><a href="<c:url value="/member?action=select"/>">회원정보변경</a></td>
			</tr>
			<tr>
				<td><a onclick='removeCheck("<c:url value='/member?action=remove'/>");'>회원탈퇴</a></td>
				</tr>

		</c:if>
	</table>
</div>

