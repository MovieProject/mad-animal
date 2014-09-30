// 해당 url로 이동하는 기능
function goUrl(url) {
	location.href = url;
}

// 검색 폼의 공백을 체크하는 기능
function searchCheck() {
	var input = document.searchForm.searchText;
	if (input.value == '') {
		alert('검색어를 입력하세요.');
		input.focus();
		return false;
	}
	return true;
}

// 삭제 여부를 확인 후 해당 url로 이동하는 기능
function deleteCheck(url) {
	if (confirm('정말 삭제하시겠어요?')) {
		location.href=url;
	}
}

// 폼 필드의 공백 여부를 체크하여 에러 메시지를 출력하는 기능
function checkNotEmpty(inputField, errorSpan) {
	if (inputField.value.length == 0) {
		errorSpan.innerHTML = "글쓴이 이름을 입력하세요.";
	} else {
		errorSpan.innerHTML = "";
	}
}

function removeMemberCheck(url) {
	if (confirm('정말로 탈퇴하시겠습니까?')) {
		location.href = url;
	}
}

// 작성,수정 폼의 공백을 체크하는 기능
function boardWriteCheck(form) {
	if (form.title.value == '') {
		alert('제목을 입력하세요.');
		form.title.focus();
		return false;
	}
	if (form.writer.value == '') {
		alert('이름을 입력하세요');
		form.writer.focus();
		return false;
	}
	if (editor.getData() == '') {
		alert('내용을 입력하세요');
		editor.focus();
		return false;
	}
	form.submit();
}

/*
// 수정 이전버전
// 작성,수정 폼의 공백을 체크하는 기능
function boardWriteCheck(form) {
	var form = document.writeForm;
	if (form.title.value.length == 0) {	// form.title.value == '' : 비슷한 결과 출력
		alert('제목을 입력하세요.');
		form.title.focus();
		return false;
	}
	if (form.writer.value.length == 0) {
		alert('이름을 입력하세요');
		form.writer.focus();
		return false;
	}
	if (form.contents.value.length == 0) {
		alert('내용을 입력하세요');
		form.contents.focus();
		return false;
	}
	return true;
}
*/