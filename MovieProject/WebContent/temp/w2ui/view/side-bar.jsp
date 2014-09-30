<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="sidebar" style="height: 100%; width: 100%;"></div>

<script>
$(function () {
    $('#sidebar').w2sidebar({
        name: 'sidebar',
        nodes: [ 
            { id: 'level-1', text: '메뉴', img: 'icon-folder', expanded: true, group: true,
              nodes: [ { id: 'level-1-1', text: '메인', icon: 'fa-star' },
                       { id: 'level-1-2', text: '공지사항', icon: 'fa-star' },
                       { id: 'level-1-3', text: '금주 추천 영화 게시판', icon: 'fa-star' },
                       { id: 'level-1-4', text: '회원 추천 영화 게시판', icon: 'fa-star' },
                       { id: 'level-1-5', text: '최신영화 정보 게시판', icon: 'fa-star' },
                       { id: 'level-1-6', text: '영화 리뷰 게시판', icon: 'fa-star' }
                     ]
            }
        ]
    });

    w2ui.sidebar.on('click',"w2ui['layout'].load('main', '<c:url value='/view/content.jsp' />')");
    
});
</script>
