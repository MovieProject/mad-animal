<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/script.js">
	
</script>
<div class="sidebar">
	<c:if test="${empty loginMember}">

		<c:import url="/login.jsp"></c:import>
	</c:if>
	
	<c:if test="${not empty loginMember}">

		<c:import url="/logout.jsp"></c:import>
	</c:if>

	<table id="sidebartable">
		<tr>
			<td id="menulabel">Menu</td>
		</tr>

		<tr>
			<td><a href="<c:url value="/index.jsp"/>">메인</a></td>
		</tr>
		<tr>
			<td><a href="<c:url value="/board/list"/>">공지사항</a></td>
		</tr>
		<tr>
			<td><a href="<c:url value="/movie/movielist?type=2"/>">금주 추천 영화</a></td>
		</tr>
		<tr>
			<td><a href="<c:url value="/movie/movielist?type=1"/>">회원 추천 영화</a></td>
		</tr>
		<tr>
			<td><a href="<c:url value="/movie/movielist?type=3"/>">최신 영화 정보</a></td>
		</tr>
			<tr>
				<td><a href="<c:url value="/review/list"/>">영화 한줄평</a></td>
			</tr>
	<c:if test="${not empty loginMember && loginMember.grade == 2 }">
				<tr>
				<td><a href="<c:url value='/member/memberlist'/>">회원 관리</a></td>
			</tr>
	</c:if>

	</table>
</div>

