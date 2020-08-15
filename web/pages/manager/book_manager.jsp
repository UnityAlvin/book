<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>图书管理</title>
	<%-- 静态包含Base标签、CSS样式、jQuery文件 --%>
	<%@include file="/pages/common/head.jsp"%>
</head>
	<script type="text/javascript">
		$(function () {
			$("a.delete").click(function () {
				return confirm("你确定要删除《"+$(this).parent().parent().find("td:first").text()+"》吗？");
			})

          $("#confirm").click(function () {
              var pageNo =  $("#pn_input").val();
              var pageTotal = ${requestScope.page.pageTotal};
              //js提供了一个location地址栏对象，有一个属性href，可以获取浏览器地址，可读写
              location.href="${pageScope.bathPath}${requestScope.page.url}&pageNo="+pageNo;
            });
		})
	</script>
<body>
	<c:if test="${sessionScope.user.type == 0}">
		<jsp:forward page="/client/bookServlet?action=page"></jsp:forward>
	</c:if>
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<%--静态包含Manager管理模块的菜单--%>
			<%@include file="/pages/common/manager_menu.jsp"%>
	</div>

	<div id="main">
		<table>

			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>

			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}" class="delete">删除</a></td>
				</tr>
			</c:forEach>


			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>
		</table>
		<%@include file="/pages/common/page_nav.jsp"%>

	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>