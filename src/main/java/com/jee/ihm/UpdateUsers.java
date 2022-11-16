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

import com.jee.dao.UserDAO;
import com.jee.dao.UtilConnexion;
import com.jee.modele.Users;


@WebServlet("/update")
public class UpdateUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Users user = UserDAO.getUser(id);
			
			if (user != null) {
				request.setAttribute("user", user);
				request.getRequestDispatcher("edit.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/allusers").forward(request, response);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Error at update (GET)");
			request.getRequestDispatcher("/allusers").forward(request, response);
		} 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String login = (String) request.getParameter("txtLogin");
		String email = (String) request.getParameter("txtEmail");
		String password = (String) request.getParameter("txtPassword");
		String password2 = (String) request.getParameter("txtPassword2");
		
		Users u = UserDAO.getUser(id);
		
		String error = UserDAO.checkInputs(login, email, password, password2);
		
		if(error != null) {
			request.setAttribute("msg", error);
		} else {
			try {
				UserDAO.updateUser(u);
				
				request.setAttribute("msg", "User Updated!");
				request.getRequestDispatcher("/allusers").forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "Error at update (POST)");
				doGet(request, response);
			}
		}
		
	}

}
