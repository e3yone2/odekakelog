<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>ユーザー登録</h1>
<div class="box-register">
<table class="table-register">
<form action = "RegisterAccountServlet" method="post">
<tr><td>ユーザーID:</td><td><input type = "text" name ="id">半角英数字20文字以内</td></tr>
<tr><td>パスワード:</td><td><input type = "text" name = "pass">半角英数字20文字以内</td></tr>
<tr><td>ユーザーネーム:</td><td><input type = "text" name = "name"></td></tr>
</table>
<p id="button-rg"><input type = "submit" value = "登録"<p>
</form>
<p><a href = "LoginServlet">戻る</a></p>


<c:if test="${not empty errorMsg}">
	<p class="errorMsg-r" style ="color: red "><c:out value="${errorMsg}"/></p>
</c:if>
</div>

</body>
</html>