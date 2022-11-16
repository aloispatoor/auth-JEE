package com.jee.ihm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jee.dao.UserDAO;
import com.jee.dao.UtilConnexion;
import com.jee.modele.Users;


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
		
		Users user = new Users(login, email, password);
		
		HttpSession session = request.getSession(true);
		
		String error = UserDAO.checkInputs(login, email, password, password2);
		
		if (error != null) {
			session.setAttribute("error", error);
		} else {	
			try {
				UserDAO.createUser(user);
				request.getRequestDispatcher("/allusers").forward(request, response);
				
			} catch (Exception e) {
				session.setAttribute("msg", "Error: Database issues");
				doGet(request, response);
			}
		}
		
	}

}
