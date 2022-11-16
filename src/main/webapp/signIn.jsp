<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://myapp.fr/tld/extratags" prefix="mytags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sign In</title>
		<link rel='stylesheet' type='text/css' href='style.css' />
	</head>
	<body>
		<mytags:Header userName='${login}' />
		<h1>Sign In</h1>
		<% if (session.getAttribute("error") != null) { %>
			<p class="error">${msg}</p>
		<% } %>
        <form method="POST" action="signin">
            <input name="txtLogin" type="text" value="${login}" placeholder="Username">
            <input name="txtEmail" type="email" value="${email}" placeholder="Email">
            <input name="txtPassword" type="password" value="${password}" placeholder="Password">
            <input name="txtPassword2" type="password" value="${password2}" placeholder="Repeat password">
            <input name="btnConnect" type="submit">
        </form>
        
        
	</body>
</html>