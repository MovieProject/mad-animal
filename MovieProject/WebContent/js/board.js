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
		location.href = url;
	}
}
function removeCheck(url) {
	if (confirm('정말 탈퇴하시겠어요?')) {
		location.href = url;
		alert("탈퇴되었습니다.")
	}
}

function movieWriteCheck(form) {
	if (form.title.value == '') {
		alert('내용을 입력하세요');
		form.title.focus();
		return;
	}
	if (form.genre.value == '') {
		alert('내용을 입력하세요');
		form.genre.focus();
		return;
	}
	if (form.releaseDate.value == '') {
		alert('내용을 입력하세요');
		form.releaseDate.focus();
		return;
	}
	if (form.synopsis.value == '') {
		alert('내용을 입력하세요');
		form.synopsis.focus();
		return;
	}
	form.submit();
}

// 수정 폼 체크
function boardWriteCheck(form) {
	var edit = CKEDITOR.instances.contents;
	if (form.title.value == '') {
		alert('제목을 입력하세요.');
		form.title.focus();
		return;
	}

	
	if(edit.getData() == ''){
		alert('내용을 입력하세요');
		edit.focus();
		return;
	}
	form.submit();

}
// 폼필드가 비어있는지 여부를 체그하여 에러메시지를 출력
function checkNotEmpty(inputField, errorSpan) {
	if (inputField.value.length == 0) {
		errorSpan.innerHTML = '글쓴이 이름을 입력하세요.';
	} else {
		errorSpan.innerHTML = '';
	}
}

function checkUpdateMovie(form){
	var title = document.getElementById("movieUpdateTitle");
	var genre = document.getElementById("movieUpdateGenre");
	var director = document.getElementById("movieUpdateDirector");
	var synopsis = document.getElementById("movieUpdateSynopsis");
	
	if(title.value ==''){
		alert("영화 제목을 입력하세요.");
		title.focus();
		return;
	}
	if(genre.value ==''){
		alert("장르를 입력하세요.");
		genre.focus();
		return;
	}

	if(director.value ==''){
		alert("감독을 입력하세요.");
		director.focus();
		return;
	}
	if(synopsis.value ==''){
		alert("줄거리를 입력하세요.");
		synopsis.focus();
		return;
	}
	form.submit();
}
function checkWriteMovie(form){
	var title = document.getElementById("movieWriteTitle");
	var genre = document.getElementById("movieWriteGenre");
	var releaseDate = document.getElementById("movieWriteDate");
	var director = document.getElementById("movieWriteDirector");
	var synopsis = document.getElementById("movieWriteSynopsis");
	var file = document.getElementById("movieWriteFile");
	
	if(title.value ==''){
		alert("영화 제목을 입력하세요.");
		title.focus();
		return;
	}
	if(genre.value ==''){
		alert("장르를 입력하세요.");
		genre.focus();
		return;
	}
	if(releaseDate.value ==''){
		alert("개봉일자를 입력하세요");
		releaseDate.focus();
		return;
	}
	if(director.value ==''){
		alert("감독을 입력하세요");
		director.focus();
		return;
	}
	if(synopsis.value ==''){
		alert("줄거리를 입력하세요");
		synopsis.focus();
		return;
	}
	if(file.value ==''){
		alert("이미지를 선택하시 않았습니다. 기본이미지로 들어갑니다.");
	}
	form.submit();
}


function validateForm() {
	var checkboxes = document.listDelete.items;
	for (var i = 0; i < checkboxes.length; i++) {
		if (checkboxes[i].checked == true) {
			return confirm("정말로 삭제하시겠습니까?")
		}
	}
	alert("선택된 값이 없습니다.");
	return false;
}
/** 회원추천영화 선택삭제하는 기능 */
function validateMovie(){
	var form = document.getElementById("member_recommend_form");
	var checkboxes = document.getElementsByName("items");
	var booleanValue = false;
	for(var i = 0;i<checkboxes.length;i++){
		if(checkboxes[i].checked== true){
			booleanValue = true;
		}
	}
	if(booleanValue){
		if(confirm("정말로 삭제하시겠습니까?")){
			form.submit();
		}
	}else{
		alert("선택된 값이 없습니다.");		
	}
}

function validateMovie() {
	var form = document.getElementById("member_recommend_form");
	var checkboxes = document.getElementsByName("items");
	var booleanValue = false;
	for (var i = 0; i < checkboxes.length; i++) {
		if (checkboxes[i].checked == true) {
			booleanValue = true;
		}
	}
	if (booleanValue) {
		if(confirm("정말로 삭제하시겠습니까?")){
			form.submit();
		}
	} else {
		alert("선택된 값이 없습니다.");
	}
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

window.onload = function () {
	var oneline = document.getElementsByName('inputOneline')
	for (var index = 0; index < oneline.length; index++) {
		if(oneline[index].value.length > 40) {
			var sub = oneline[index].value.substr(0, 40) + '...';
			document.getElementsByName('inputOneline')[index].value = sub;
		}
	}
	$(function() {
		$( document ).tooltip();
	})
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
			'<td width="530" ><textarea name="contents" id="temp" class="inputtext" type="text" style="width: 490px" onkeydown="characterCheck()" onkeyup="characterCheck()" rows="3">'+content+'</textarea>' + // 기존
																																																				// 한줄평을
																																																				// 받아서
																																																				// 화면에
																																																				// 출력
																																																				// 해줌
			'</td></tr></table>' + 
			'</div>',
        buttons   : 
        	'<button class="btn" onclick="updateContent(\'' + num + '\')">수정</button>' +
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
function updateContent(num) {
	var temp = document.getElementById("temp").value;
	temp.indexOf("\"");
	goUrl('update?reviewNum=' + num + '&contents=' + encodeURIComponent(temp));
}

function characterCheck() {
// var RegExp = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;//정규식 구문
	var RegExp = /[\'\"]/gi;	// 정규식 구문
	var obj1 = document.getElementsByName("movieTitle")[0];
	var obj2 = document.getElementsByName("contents")[0];
	var obj3 = document.getElementsByName("contents")[1];
	if (RegExp.test(obj1.value)) {
		alert("허용되지 않는 특수문자입니다.( \' , \" )");
		obj1.value = obj1.value.replace(RegExp, "");// 특수문자를 지우는 구문
	}
	if (RegExp.test(obj2.value)) {
		alert("허용되지 않는 특수문자입니다.( \' , \" )");
		obj2.value = obj2.value.replace(RegExp, "");// 특수문자를 지우는 구문
	}
	if (RegExp.test(obj3.value)) {
		alert("허용되지 않는 특수문자입니다.( \' , \" )");
		obj3.value = obj3.value.replace(RegExp, "");// 특수문자를 지우는 구문
	}
}
