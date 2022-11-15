package com.jee.ihm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jee.dao.UtilConnexion;


@WebServlet("/update")
public class UpdateUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			
			Connection con = UtilConnexion.seConnecter();
	
			String query = "SELECT * FROM users WHERE id='" + id + "'";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				// rs.getThing(number of column in the SQL)
				request.setAttribute("id", rs.getInt(1));
				request.setAttribute("username", rs.getString(2));
				request.setAttribute("email", rs.getString(3));
				request.setAttribute("password", rs.getString(4));
				
			}
			request.getRequestDispatcher("edit.jsp").forward(request, response);
			
			con.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			HttpSession session = request.getSession(true);
			session.setAttribute("msg", "Error at update (GET)");
			request.getRequestDispatcher("/allusers").forward(request, response);
		} 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String login = (String) request.getParameter("txtLogin");
		String email = (String) request.getParameter("txtEmail");
		String password = (String) request.getParameter("txtPassword");
		String password2 = (String) request.getParameter("txtPassword2");
		
		if(login == null || login == "") {
			request.setAttribute("msg","Error: Login can't be null");
		} else if (password == null || password == "") {
			request.setAttribute("msg","Error: password can't be null");
		} else if (email == null || email == "") {
			request.setAttribute("msg","Error: Email can't be null");
		} else if (!email.contains("@")) {
			request.setAttribute("msg","Error: Login must be an email");
		} else if (!password.equals(password2)) {
			request.setAttribute("msg","Error: Password fields aren't identical");
		} else if (password.length() < 9) {
			request.setAttribute("msg", "Error: The password must have more than 8 characters");	
		} else {
			try {
		
				Connection con = UtilConnexion.seConnecter();
				
				String query = "UPDATE users SET username =?, email=?, password=? WHERE id=?;";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, login);
				ps.setString(2, email);
				ps.setString(3, password);
				ps.setInt(4, id);
				ps.executeUpdate();
				
				con.close();
				
				request.setAttribute("msg", "User Updated!");
				request.getRequestDispatcher("/allusers").forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
				HttpSession session = request.getSession(true);
				session.setAttribute("msg", "Error at update (POST)");
				doGet(request, response);
			}
		}
	}

}
