<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie</title>
<link rel="stylesheet" href="css/movie.css">
<link rel="stylesheet" href="css/board.css">

</head>
<body>

	<div id="page">

		<div id="header">
			<c:import url="banner.jsp"></c:import>
		</div>

		<div id="content">
			<c:import url="side-bar.jsp"></c:import>
			<c:import url="content.jsp"></c:import>
		</div>

		<div id="footer">
			<c:import url="footer.jsp"></c:import>
		</div>
	</div>
</body>
</html>
