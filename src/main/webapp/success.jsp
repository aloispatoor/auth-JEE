<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.jee.modele.Users" %>    
<%@ page import="com.jee.dao.UserDAO" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<link rel='stylesheet' type='text/css' href='style.css' />
	<title>Success!</title>
	</head>
	<body>
		<h1>You're registered in our database!</h1>
		
		<h2>List of the users :</h2>
		<% if (request.getAttribute("msg") != null) { %>
			<p class="error">${msg}</p>
		<% } %>
		<table>
			<thead>
				<tr>
					<td>Id</td>
					<td>Username</td>
					<td>Email</td>
					<td>Password</td>
					<td>Delete</td>
					<td>Update</td>
				</tr>
			</thead>
			<% for (Users u: UserDAO.getAllUsers()) { %>
				<tbody>
					<tr>
						<td> <%= u.getId() %> </td>
						<td> <%= u.getUsername() %> </td>
						<td> <%= u.getEmail() %> </td>
						<td> <%= u.getPassword() %> </td>
						<td> <a href="/Auth-JEE/delete?id=<%= u.getId() %> ">Delete</a> </td>
						<td> <a href="/Auth-JEE/update?id=<%= u.getId() %> ">Update</a> </td>
					</tr>
				</tbody>
			<% 
			} 
			%>
		</table>
		<a href="/Auth-JEE/signin">Sign In</a>
	</body>
</html>