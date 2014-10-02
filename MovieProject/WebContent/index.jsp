<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Movie</title>
<link rel="stylesheet" href="css/movie.css">

</head>
<body>

	<div id="page">

		<header id="header">
			<c:import  url="top.jsp"></c:import>
		</header>

		<div id="content">

			<c:import url="content.jsp"></c:import>
		</div>


		<aside id="sidebar">
			<c:import url="side-bar.jsp"></c:import>
		</aside>
		<footer id="footer">
			<c:import url="footer.jsp"></c:import>
		</footer>
	</div>
</body>
</html>
