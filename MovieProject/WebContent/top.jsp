<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/movie.css">

</head>
<body>
	<div id="top">
		<div class="boardpage">

			<table id="headertable" class="headertable">
				<tr>
					<td><a href="<c:url value="/board/list.jsp"/>">공지사항</a> |</td>
					<td><a href="<c:url value="/movie/movielist?type=2"/>">금주추천</a> |</td>
					<td><a href="<c:url value="/movie/movielist?type=1"/>">회원추천</a> |</td>
					<td><a href="<c:url value="/movie/movielist?type=3"/>">최신영화</a> |</td>
					<td><a href="<c:url value="/review/list"/>">영화리뷰</a></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>