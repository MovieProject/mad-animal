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

		<header id="header">
			<c:import url="top.jsp"></c:import>
		</header>

		<div id="content">

			<form name="writeForm" action="write" method="POST" >
			<table id="writetable" class="maintable">
				<caption>영화 등록</caption>
				<thead>
					<tr>
						<th>제 목</th>
						<td><input class="titleinput" type="text" name="title" maxlength="20"></td>
					</tr>
					<tr>
						<th>작성자</th>

						<td>
						<input class="writerinput" type="text" name="writer" maxlength="20" >
						</td>
					</tr>

				</thead>
				<tbody>
									<tr>
						<th>장르</th>

						<td>
						<input class="typeinput" type="text" name="type" maxlength="20" >
						</td>
					</tr>
										<tr>
						<th>개봉일</th>

						<td>
						<input class="dateinput" type="date" name="date" >
						</td>
					</tr>

					<tr>
					<th>사진 업로드</th>

						<td>
						<input class="uploadinput" type="text" name="upload" maxlength="20" >
						</td>
					</tr>
					<tr>
						<th>줄거리</th>
						<td colspan="2">
 							<textarea id="editor" class="contentsinput" name="contents"></textarea>
<!-- 							<script>CKEDITOR.replace('editor');</script> -->

							<!-- <textarea class="contentsinput ckeditor" name="contents"></textarea> -->
						</td>
					</tr>
				</tbody>
			</table>
			<div class="buttonbar">
				<input type="button" value="등록" onclick="boardWriteCheck(this.form);">
				<input type="button" value="취소" onclick="goUrl('list');">
			</div>
		</form>
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
