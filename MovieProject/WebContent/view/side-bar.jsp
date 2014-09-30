<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="sidebar">
	<%-- 1. session scope에 설정된 "loginMember" 속성이 없으면 로그인 페이지(login.jsp)를,
        있으면 로그아웃 페이지(logout.jsp)를 포함시킨다.(include 액션 사용) --%>
	<c:if test="${ empty sessionScope.loginMember }">
		<c:import url="/view/login.jsp" />
	</c:if>
	<c:if test="${ empty sessionScope.loginMember }">
		<c:import url="/view/logout.jsp" />
	</c:if>

	<table id="sidebartable">
		<tr>
			<td id="menulabel">Menu</td>
		</tr>
		<tr>
			<td><hr></td>
		</tr>
		<tr>
			<td><a href="<c:url value='/index.jsp' />">메인</a></td>
		</tr>
		<tr>
			<td><a href="<c:url value='/view/board/notice.jsp' />">공지사항</a></td>
		</tr>
		<tr>
			<td><a href="<c:url value='/view/board/newMovie.jsp' />">최신 영화 정보</a></td>
		</tr>
		<tr>
			<td><a href="<c:url value='/view/board/weeklyRecommend.jsp' />">금주 추천</a></td>
		</tr>
		<tr>
			<td><a href="<c:url value='/view/board/memberRecommend.jsp' />">회원 추천</a></td>
		</tr>
		<tr>
			<td><a href="<c:url value='/view/board/review.jsp' />">영화 리뷰</a></td>
		</tr>
		<c:if test="${ sessionScope.loginMember.memberID == 'duke' }">
			<tr>
				<td><a href="<c:url value='/registerProduct.jsp' />">상품등록</a></td>
			</tr>
		</c:if>
	</table>
</div>

