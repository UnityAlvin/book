<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>购物车</title>
	<%-- 静态包含Base标签、CSS样式、jQuery文件 --%>
	<%@include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        $(function () {
            $(".deleteItem").click(function () {
                return confirm("确定要删除【"+$(this).parent().parent().find("td:first").text()+"】吗");
            });

            $(".clear").click(function () {
                return confirm("确定要清空购物车吗？");
            });

            //使用内容发生改变事件
            $(".updateCount").change(function () {
            	var name = $(this).parent().parent().find("td:first").text();
                var count = this.value;
                var id = $(this).attr("bookId");
                 if (confirm("你确定要将【"+name+"】" +
                    "商品数量修改为"+count+"吗？")) {
					location.href = "${pageScope.basePath}cartServlet?action=updateCount&count="+count+"&id="+id;
				 }else{
                 	//defaultValue属性时表单Dom对象的属性，表示默认属性值
                 	this.value =this.defaultValue;
				 }
            })
        })
    </script>
</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<c:if test="${not empty sessionScope.user}">
				<%--静态包含登录成功之后的菜单--%>
				<%@include file="/pages/common/login_success_menu.jsp"%>
			</c:if>

	</div>

	<div id="main">

		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<%--如果购物车为空--%>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp" style="text-decoration: none">购物车是空的，去逛逛！</a></td>
				</tr>
			</c:if>

			<%--如果购物车非空--%>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td><input bookId = ${entry.value.id} class="updateCount" type="text" value="${entry.value.count}" style="width: 80px;text-align: center"></td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a href="cartServlet?action=deleteItem&id=${entry.value.id}" class="deleteItem">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>

		</table>

		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a href="cartServlet?action=clear" class="clear">清空购物车</a></span>
				<span class="cart_span"><a href="client/orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>