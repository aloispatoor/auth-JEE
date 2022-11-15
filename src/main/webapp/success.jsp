<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.jee.dao.UtilConnexion" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
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
		<%
			Connection con = UtilConnexion.seConnecter();
			ResultSet rs = con.createStatement().executeQuery("SELECT * from users");
		%>
		<table>
			<thead>
				<tr>
					<td>Id</td>
					<td>Username</td>
					<td>Email</td>
					<td>Delete</td>
					<td>Update</td>
				</tr>
			</thead>
			<tbody>
				<% while (rs.next()) { %>
			<tr>
				<td> <%= rs.getInt(1) %> </td>
				<td> <%= rs.getString(2) %> </td>
				<td> <%= rs.getString(3) %> </td>
				<td> <a href="/Auth-JEE/delete?id=<%= rs.getInt(1) %>">Delete</a> </td>
				<td> <a href="/Auth-JEE/update?id=<%= rs.getInt(1) %>">Update</a> </td>
			</tr>
			<% 
			} 
			con.close();
			rs.close();
			%>
			</tbody>
		</table>
		<a href="/Auth-JEE/signin">Sign In</a>
	</body>
</html>