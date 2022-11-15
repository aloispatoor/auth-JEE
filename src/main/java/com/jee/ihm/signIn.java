package com.jee.ihm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jee.dao.UtilConnexion;


@WebServlet("/signin")
public class signIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/signIn.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("txtLogin");
		String email = request.getParameter("txtEmail");
		String password = request.getParameter("txtPassword");
		String password2 = request.getParameter("txtPassword2");
		
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
				String query = "INSERT INTO users(username, email, password) VALUE (?, ?, ?)";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, login);
				ps.setString(2, email);
				ps.setString(3, password);
				ps.executeUpdate();
				request.getRequestDispatcher("/allusers").forward(request, response);
				con.close();
				
			} catch (Exception e) {
				request.setAttribute("msg", "Error: Database issues");
				doGet(request, response);
			}
		}
	}

}
