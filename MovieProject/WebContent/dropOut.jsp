<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/board.js"></script>
<div class="main">
	<form>
		<p id="contentsubject">회원 탈퇴</p>
		<p>"회원"님 정말로 탈퇴하시겠습니까?</p>
		<p>탈퇴 하시려면 아이디와 패스워드를 한번 더 입력 후 탈퇴 버튼을 눌러주세요.</p>
		<p>
			ID : <input type="text" name="id">
		</p>
		<p>
			Password : <input type="text" name="password">
		</p>
		<p>
			<input type="button" value="회원탈퇴" onclick="removeMemberCheck('index.jsp');">
		</p>
	</form>
</div>
>>>>>>> refs/remotes/origin/master
