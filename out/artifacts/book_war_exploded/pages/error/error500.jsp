<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>错误页面</title>
    <%-- 静态包含Base标签、CSS样式、jQuery文件 --%>
    <%@include file="/pages/common/head.jsp"%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }
    </style>
</head>
<body>
    <div id="header">
        <img class="logo_img" alt="" src="static/img/logo.gif">
        <span class="wel_word"></span>
    </div>

    <div id="main">

        <h1><a href="index.jsp">当前访问出现异常，请稍后重试，点击返回首页。</a></h1>

    </div>

    <%--静态包含页脚内容--%>
    <%@include file="/pages/common/footer.jsp"%>
</body>
</html>