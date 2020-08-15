<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%-- 静态包含Base标签、CSS样式、jQuery文件 --%>
    <%@include file="/pages/common/head.jsp"%>
</head>
    <script type="text/javascript">
        $(function () {
            $("#confirm").click(function () {
                var pageNo =  $("#pn_input").val();
                var pageTotal = ${requestScope.page.pageTotal};
                //js提供了一个location地址栏对象，有一个属性href，可以获取浏览器地址，可读写
                location.href="${pageScope.bathPath}${requestScope.page.url}&pageNo="+pageNo;
            });
            $(".addToCart").click(function () {
                //获取购物车按钮上保存的自定义属性bookId
                var bookId = $(this).attr("bookId");
                // location.href="cartServlet?action=addItem&id="+bookId;

                $.getJSON("cartServlet","action=ajaxAddItem&id="+bookId,function (data) {
                    $("#cartTotalCount").text("您的购物车中有"+data.totalCount+"件商品");
                    $("#cartLastName").html("您刚刚将<span style=\"color: red\">"+data.lastName+"</span>加入到了购物车中\n");
                });
            });
        })
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">网上书城</span>
    <div>
        <%--如果用户还没登录，显示登录、注册的菜单--%>
        <c:if test="${empty sessionScope.user}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        </c:if>
        <%--如果已经登录，则显示登录成功之后的信息--%>
        <c:if test="${not empty sessionScope.user}">
                <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
                <a href="client/orderServlet?action=page">我的订单</a>
                <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
        </c:if>
            <a href="pages/cart/cart.jsp">购物车</a>
        <c:if test="${not empty sessionScope.user and sessionScope.user.type == 1}">
            <a href="pages/manager/manager.jsp">后台管理</a>
        </c:if>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/bookServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice">
                <%--回显直接使用请求的参数即可--%>
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询" />
            </form>
        </div>
        <div style="text-align: center">
            <c:if test="${empty sessionScope.cart.items}">
                <span id="cartTotalCount"></span>
                <div id="cartLastName">
                    <span style="color: red" id="cartLastName">当前购物车为空</span>
                </div>
            </c:if>
            <c:if test="${not empty sessionScope.cart.items}">
                <span id="cartTotalCount">您的购物车中有${sessionScope.cart.totalCount}件商品</span>
                <div id="cartLastName">
                    您刚刚将<span style="color: red">${sessionScope.lastName}</span>加入到了购物车中
                </div>
            </c:if>

        </div>
        <c:forEach items="${requestScope.page.items}" var="book">

            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${book.imgPath}" />
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <button class="addToCart" bookId="${book.id}">加入购物车</button>
                    </div>
                </div>
            </div>

        </c:forEach>


    
    </div>

    <%@include file="/pages/common/page_nav.jsp"%>

</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>