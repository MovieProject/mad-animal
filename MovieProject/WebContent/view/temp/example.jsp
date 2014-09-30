<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>W2UI Demo: layout-7</title>
    <link rel="stylesheet" type="text/css" href="http://w2ui.com/src/w2ui-1.4.1.min.css" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <script type="text/javascript" src="http://w2ui.com/src/w2ui-1.4.1.min.js"></script>
</head>
<body>

<div id="layout" style="width: 100%; height: 400px;"></div>
<div style="height: 20px;"></div>

<script type="text/javascript">
$(function () {
    var pstyle = 'border: 1px solid #dfdfdf; padding: 5px;';
    $('#layout').w2layout({
        name: 'layout',
        panels: [
            { type: 'top', size: 50, style: pstyle, content: 'top' },
            { type: 'left', size: 200, style: pstyle, content: 'left' },
            { type: 'main', style: 'background-color: white;', overflow: 'hidden' },
            { type: 'bottom', size: 50, style: pstyle, content: 'bottom' }
        ]
    });
    var pstyle = 'background-color: #F0F0C1; border: 1px solid #dfdfdf; padding: 5px;';
    $().w2layout({
        name: 'layout2',
        panels: [
            { type: 'top', size: 40, style: pstyle},
            { type: 'left', size: 80, style: pstyle },
            { type: 'main', style: pstyle },
        ]
    });
    
    w2ui['layout'].set('top', );
    w2ui['layout'].content('main', w2ui['layout2']);
});
</script>

</body>
</html>