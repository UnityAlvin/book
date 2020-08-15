<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <%-- 静态包含Base标签、CSS样式、jQuery文件 --%>
    <%@include file="/pages/common/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $(".receive").click(function () {
                if (confirm("您要确认收货吗？")) {
                    location.href="${pageScope.bathPath}client/orderServlet?action=receiveOrder&orderId=${requestScope.orderId}&pageNo=${requestScope.pageNo}"
                }
            })

            $(".send").click(function () {
                if (confirm("您确认要为${requestScope.username}发货吗？")) {
                    location.href="${pageScope.bathPath}manager/orderServlet?action=sendOrder&orderId=${requestScope.orderId}&pageNo=${requestScope.pageNo}"
                }
            })
        })
    </script>
</head>

<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">订单详情</span>

    <%--静态包含登录成功之后的菜单--%>
    <%@include file="/pages/common/login_success_menu.jsp" %>

</div>

<div id="main">

    <table>
        <tr>
            <td style="border-bottom: none;text-align: left" colspan="2">订单号：${requestScope.orderId}</td>
        </tr>
        <tr>
            <td>名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>总价</td>
        </tr>
        <c:forEach items="${requestScope.orderItems}" var="orderItem">
            <tr>
                <td>${orderItem.name}</td>
                <td>${orderItem.count}</td>
                <td>${orderItem.price}</td>
                <td>${orderItem.totalPrice}</td>
            </tr>
        </c:forEach>
        <c:if test="${empty requestScope.username}">
            <c:if test="${ requestScope.status == 1}">
                    <tr>
                        <td style="border-bottom: none"></td>
                        <td style="border-bottom: none"></td>
                        <td style="border-bottom: none"></td>
                        <td style="border-bottom: none">
                            <button class="receive">确认收货</button>
                        </td>
                    </tr>
            </c:if>
        </c:if>
        <c:if test="${not empty requestScope.username}">
            <c:if test="${sessionScope.user.type == 1 and requestScope.status == 0}">
                <tr>
                    <td style="border-bottom: none"></td>
                    <td style="border-bottom: none"></td>
                    <td style="border-bottom: none"></td>
                    <td style="border-bottom: none">
                        <button class="send">发货</button>
                    </td>
                </tr>
            </c:if>
        </c:if>


    </table>

</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>