<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Update User</title>
		<link rel='stylesheet' type='text/css' href='style.css' />
	</head>
	<body>
		<h1>Update User</h1>
		<% if (request.getAttribute("msg") != null) { %>
			<p class="error">${msg}</p>
		<% } %>
        <form method="POST">
            <input name="txtLogin" type="text" value="${login}" placeholder="Username">
            <input name="txtEmail" type="email" value="${email}" placeholder="Email">
            <input name="txtPassword" type="password" value="${password}" placeholder="Password">
            <input name="txtPassword2" type="password" value="${password2}" placeholder="Repeat password">
            <input name="btnConnect" type="submit">
        </form>
	</body>
</html>