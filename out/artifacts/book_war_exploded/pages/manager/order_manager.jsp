<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>订单管理</title>
	<%-- 静态包含Base标签、CSS样式、jQuery文件 --%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("#confirm").click(function () {
				var pageNo =  $("#pn_input").val();
				var pageTotal = ${requestScope.page.pageTotal};
				//js提供了一个location地址栏对象，有一个属性href，可以获取浏览器地址，可读写
				location.href="${pageScope.bathPath}${requestScope.page.url}&pageNo="+pageNo;
			});
		})
	</script>
</head>
<body>
	<c:if test="${sessionScope.user.type == 0}">
		<jsp:forward page="/client/bookServlet?action=page"></jsp:forward>
	</c:if>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
			<%--静态包含Manager管理模块的菜单--%>
			<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>订单号</td>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>用户名</td>
				<td>详情</td>
			</tr>
			<c:forEach items="${requestScope.page.items}" var="order">
				<tr>
					<td>${order.orderId}</td>
					<td>${order.createTime}</td>
					<td>${order.price}</td>
					<c:if test="${order.status == 0}">
						<td>未发货</td>
					</c:if>
					<c:if test="${order.status == 1}">
						<td>已发货</td>
					</c:if>
					<c:if test="${order.status == 2}">
						<td>已签收</td>
					</c:if>
					<td>${order.username}</td>
					<td><a href="manager/orderServlet?action=orderDetails&orderId=${order.orderId}&status=${order.status}&pageNo=
					${requestScope.page.pageNo}&showType=allOrder&username=${order.username}">查看详情</a></td>
				</tr>
			</c:forEach>
		</table>
		<%@include file="/pages/common/page_nav.jsp"%>
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>