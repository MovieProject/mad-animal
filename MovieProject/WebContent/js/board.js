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
// 최종 버전에 추가 수정할 부분
// review.jsp 도 같이 수정해야함. (head에 tooltip관련 스크립트 추가함)
//↓===================================================================
// 수정용 팝업 설정
function popup(num, title, content) {
	w2popup.open({
        title     : '한줄평 수정',
        body      :
        	'<div class="w2ui-centered">' +
        	'<form id="reviewUpdate" action="update" method="POST" onsubmit="return reviewWriteCheck();">' + 
			'<table><input type="hidden" name="reviewNum" value="'+ num +'">' + 
			'<tr><th>제목</th>' +
			'<td>' + title + '</td></tr>' +
			'<tr><th>한줄평</th>' +
			'<td width="530" ><textarea name="contents" id="temp" class="inputtext" type="text" style="width: 490px" onkeydown="characterCheck()" onkeyup="characterCheck()" rows="3">' + content + '</textarea>' +
			'</td></tr></table>' + 
			'</form>' + 
			'</div>',
        buttons   : 
        	'<button class="btn" onclick="updateContent();">수정</button>' +
        	'<button class="btn" onclick="w2popup.close();">취소</button> ', 
        width     : 600,
        height    : 200,
        overflow  : 'hidden',
        color     : '#333',
        speed     : '0.3',
        opacity   : '0.8',
        modal     : true,
        showClose : true,
    });
}

// 팝업창에서 수정 버튼을 눌렀을 때 한줄평의 번호와 내용을 url을 통해 전송
function updateContent() {
	var form = document.getElementById("reviewUpdate");
	if (form.contents.value == '') {
		alert('한줄평을 입력하세요.');
		form.contents.focus();
		return false;
	}
	return form.submit();
}

function characterCheck() {
//	var RegExp = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;//정규식 구문
	var RegExp = /[\'\"]/gi;	//정규식 구문
	var obj1 = document.getElementsByName("movieTitle")[0];
	var obj2 = document.getElementsByName("contents")[0];
	if (RegExp.test(obj1.value)) {
		alert("허용되지 않는 특수문자입니다. ( \' , \" )");
		obj1.value = obj1.value.replace(RegExp, "");//특수문자를 지우는 구문
	}
	if (RegExp.test(obj2.value)) {
		alert("허용되지 않는 특수문자입니다. ( \' , \" )");
		obj2.value = obj2.value.replace(RegExp, "");//특수문자를 지우는 구문
	}
	if (document.getElementsByName("contents")[1] != undefined) {
		var obj3 = document.getElementsByName("contents")[1];
		if (RegExp.test(obj3.value)) {
			alert("허용되지 않는 특수문자입니다. ( \' , \" )");
			obj3.value = obj3.value.replace(RegExp, "");//특수문자를 지우는 구문
		}
	}
}
//↑===================================================================
