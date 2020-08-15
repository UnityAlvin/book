<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>编辑图书</title>
	<%-- 静态包含Base标签、CSS样式、jQuery文件 --%>
	<%@include file="/pages/common/head.jsp"%>
	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
	</style>
</head>
<body>

	<c:if test="${sessionScope.user.type == 0}">
		<jsp:forward page="/client/bookServlet?action=page"></jsp:forward>
	</c:if>
		<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">编辑图书</span>
			<%--静态包含Manager管理模块的菜单--%>
			<%@include file="/pages/common/manager_menu.jsp"%>
		</div>
		
		<div id="main">
			<form action="manager/bookServlet" method="get">
				<%--如果有参数则是修改，没参数则是添加--%>
				<input type="hidden" name="action" value="${empty param.id ? "add" :"update"}">
				<input type="hidden" name="id" value="${book.id}">
				<input type="hidden" name="pageNo" value="${param.pageNo}">

					<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="name" type="text" value="${book.name}"/></td>
						<td><input name="price" type="text" value="${book.price}"/></td>
						<td><input name="author" type="text" value="${book.author}"/></td>
						<td><input name="sales" type="text" value="${book.sales}"/></td>
						<td><input name="stock" type="text" value="${book.stock}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>

		<%--静态包含页脚内容--%>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>