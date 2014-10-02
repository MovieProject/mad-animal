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
			<td></td>
		</tr>
		<tr>
			<td></td>
		</tr>

		<tr>
			<td><a href="<c:url value="/index.jsp"/>">메인</a></td>
		</tr>
		<tr>
			<td><a href="<c:url value="/board/list"/>">공지사항</a></td>
		</tr>
		<tr>
			<td><a href="<c:url value="/week_Recommend.jsp"/>">금주 추천 영화</a></td>
		</tr>
		<tr>
			<td><a href="<c:url value="/movie/movielist?type=1"/>">회원 추천 영화</a></td>
		</tr>
		<tr>
			<td><a href="<c:url value="/newMovieIntro.jsp"/>">최신 영화 정보</a></td>
		</tr>
			<tr>
				<td><a href="<c:url value="/review.jsp"/>">영화 한줄평</a></td>
			</tr>
	<c:if test="${not empty loginMember && loginMember.grade == 2 }">
				<tr>
				<td><a href="<c:url value='/member?action=memberlist'/>">회원 관리</a></td>
			</tr>
	</c:if>

	</table>
</div>

