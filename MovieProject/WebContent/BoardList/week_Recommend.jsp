<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie</title>
<link rel="stylesheet" href="../css/movie.css">
</head>
<body>

	<table id="weektable" class="maintable">
	 	<caption id="contenttitle">금주의 영화</caption>
		<tr>
			<td colspan="2">
				<table>
					<tr>
						<td rowspan="6"><img alt="영화 포스터"
							src="<c:url value="http://placehold.it/150x200" />"></td>
						<td>제목</td>
						<td>루시</td>
					</tr>
					<tr>
						<td>장르</td>
						<td>SF</td>
					</tr>
					<tr>
						<td>감독</td>
						<td>???</td>
					</tr>
					<tr>
						<td>개봉일</td>
						<td>XX.XX.XX</td>
					</tr>
					<tr>
						<td colspan="2">줄거리</td>
					</tr>
					<tr>
						<td colspan="2">쏼라 쏼라</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>

