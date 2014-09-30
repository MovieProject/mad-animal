// 해당 url로 이동
function goUrl(url) {
	location.href = url;
}

// 검색 폼 체크
function searchCheck() {
	var form = document.searchForm;
	if (form.searchText.value == '') {
		alert('검색어를 입력하세요.');
		form.searchText.focus();
		return false;
	}
	return true;
}


// 삭제 여부 확인 후 해당 url로 이동
function deleteCheck(url) {
	if (confirm('정말 삭제하시겠어요?')) {
		location.href=url;
	}
}

// 수정 폼 체크
function boardWriteCheck(form) {
	var edit = CKEDITOR.instances.editor;
	if (form.title.value == '') {
		alert('제목을 입력하세요.');
		form.title.focus();
		return;
	}
	
/*	if (form.writer.value == '') {
		alert('이름을 입력하세요');
		form.writer.focus();
		return;
	}*/
/*	if (form.contents.value == '') {
		alert('내용을 입력하세요');
		form.contents.focus();
		return;
	}*/

	if(edit.getData() == ''){
		alert('내용을 입력하세요');
		edit.focus();
		return;
	}
	form.submit();
}
//폼필드가 비어있는지 여부를 체그하여 에러메시지를 출력
function checkNotEmpty(inputField,errorSpan){
	if(inputField.value.length == 0){
		errorSpan.innerHTML = '글쓴이 이름을 입력하세요.';
	}else{
		errorSpan.innerHTML = '';
	}
}