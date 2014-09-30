<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="toolbar" style="padding: 4px; border: 1px solid silver; border-radius: 3px"></div>

<script type="text/javascript">
$(function () {    
    $('#toolbar').w2toolbar({
        name: 'toolbar',
        items: [
            { type: 'button',  id: 'item1',  caption: '공지사항', img: 'icon-page', hint: 'Hint for item 1' },
            { type: 'button',  id: 'item2',  caption: '금주추천', icon: 'icon-page', hint: 'Hint for item 2' },
            { type: 'button',  id: 'item3',  caption: '회원추천', icon: 'icon-page', hint: 'Hint for item 3' },
            { type: 'button',  id: 'item4',  caption: '최신영화', icon: 'icon-page', hint: 'Hint for item 4' },
            { type: 'button',  id: 'item5',  caption: '영화리뷰', icon: 'icon-page', hint: 'Hint for item 5' }
        ],
        onClick: function (event) {
            console.log('Target: '+ event.target, event);
        }
    });
});
</script>


	<div id="top">
		<div class="boardpage">

			<table id="headertable" class="headertable">
				<tr>
					<td><a href="<c:url value="notice.jsp"/>">공지사항</a> |</td>
					<td><a href="<c:url value="/week_Recommend.jsp"/>">금주추천</a> |</td>
					<td><a href="<c:url value="/member_Recommend.jsp"/>">회원추천</a> |</td>
					<td><a href="<c:url value="/newMovieIntro.jsp"/>">최신영화</a> |</td>
					<td><a href="<c:url value="/review.jsp"/>">영화리뷰</a></td>
				</tr>

				<tr>
					<td colspan="5" id="search"><input type="text"
						name="searchtext" id="searchtext" placeholder="검색어 입력">
						<a href="<c:url value="/search.jsp"/>"><input
						type="button" name="searchbutton" id="searchbutton" value="검색"></a></td>
				</tr>
			</table>
		</div>
	</div>
