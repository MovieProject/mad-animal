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
function removeCheck(url) {
	if (confirm('정말 탈퇴하시겠어요?')) {
		location.href=url;
		alert("탈퇴되었습니다.")
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

function validateForm(){
	var checkboxes = document.listDelete.items;
	for(var i = 0;i<checkboxes.length;i++){
		if(checkboxes[i].checked== true){
			return confirm("정말로 삭제하시겠습니까?")
		}
	}
	alert("선택된 값이 없습니다.");
	return false;
}

// 리뷰 글쓰기 폼 체크
function reviewWriteCheck() {
	var form = document.reviewWriteForm;
	if (form.movieTitle.value == '') {
		alert('영화 제목를 입력하세요.');
		form.movieTitle.focus();
		return false;
	}
	if (form.contents.value == '') {
		alert('한줄평을 입력하세요.');
		form.contents.focus();
		return false;
	}
	return true;
}

// 리뷰 한줄글 길이 제한
window.onload = function () {
	alert(document.getElementById("oneline"));
}


// 수정용 팝업 설정
function popup(num, title, content) {
	w2popup.open({
        title     : '한줄평 수정',
        body      :
        	'<div class="w2ui-centered">' +
			'<table><tr><th>제목</th>' +
			'<td>' + title + '</td></tr>' +
			'<tr><th>한줄평</th>' +
			'<td width="530" ><input id="temp" type="text" style="width: 490px" value="'+ content + '">' + // 기존 한줄평을 받아서 화면에 출력 해줌
			'</td></tr></table>' + 
			'</div>',
        buttons   : 
        	'<button class="btn" onclick="updateContent(\'' + num + '\')">수정</button>' +
        	'<button class="btn" onclick="w2popup.close();">취소</button> ',
        width     : 600,
        height    : 150,
        overflow  : 'hidden',
        color     : '#333',
        speed     : '0.3',
        opacity   : '0.8',
        modal     : true,
        showClose : true,
    });
}

// 팝업창에서 수정 버튼을 눌렀을 때 한줄평의 
function updateContent(num) {
	var temp = document.getElementById("temp").value;
	goUrl('update?reviewNum=' + num + '&contents=' + temp);
}