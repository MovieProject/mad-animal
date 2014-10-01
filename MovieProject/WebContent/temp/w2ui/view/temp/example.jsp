<!DOCTYPE html>
<html>
<head>
    <title>W2UI Demo: layout-5</title>
    <link rel="stylesheet" type="text/css" href="http://w2ui.com/src/w2ui-1.4.1.min.css" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <script type="text/javascript" src="http://w2ui.com/src/w2ui-1.4.1.min.js"></script>
</head>
<body>

<div id="layout" style="width: 100%; height: 400px;"></div>

<div style="height: 10px;"></div>

<span style="display: inline-block; width: 50px;"> Main: </span>
<button class="btn" onclick="w2ui['layout'].content('main', 'This is some content set manually', 'blur')">Set Content</button>
<button class="btn" onclick="w2ui['layout'].content('main', '/dropOut.html', 'slide-left')">Load Content 1</button>
<button class="btn" onclick="w2ui['layout'].load('main', '../../dropOut.jsp', 'slide-right')">Load Content 2</button>

<script type="text/javascript">
$(function () {
    var pstyle = 'border: 1px solid #dfdfdf; padding: 5px;'
    $('#layout').w2layout({
        name: 'layout',
        panels: [
            { type: 'top', size: 50, style: pstyle, content: 'top', resizable: true },
            { type: 'left', size: 300, style: pstyle, content: 'left', resizable: true },
            { type: 'main', style: pstyle }
        ]
    });
    w2ui['layout'].load('main', '../../dropOut.jsp');
});
</script>

</body>
</html>