<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>おでかけログ</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<h1>おでかけログ</h1>

<form action = "LoginServlet" method="post">
<div class="login">
<p>ユーザーID:<input type = "text" name ="id"></p>
<p id>パスワード:<input type = "text" name = "pass"></p>
<input type = "submit" value = "ログイン">
</div>
</form>
<div class="legisteruser">
<p>アカウントをお持ちでない方：<a href = "RegisterAccountServlet">ユーザー登録</a></p>
<c:if test="${not empty errorMsg}">
	<p style ="color: red "><c:out value="${errorMsg}"/></p>
</c:if>
</p>
</div>

</body>
</html>