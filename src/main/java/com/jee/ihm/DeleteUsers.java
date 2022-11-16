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


@WebServlet("/delete")
public class DeleteUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt( request.getParameter("id") );
		boolean isOk = UserDAO.deleteUser(id);
		
		HttpSession session = request.getSession(true);
		// If isOk is true, msg = User deleted. Else, msg = Error at delete
		session.setAttribute("msg", isOk ? "User deleted!" : "Error at delete");
		request.getRequestDispatcher("/allusers").forward(request, response);
	}

}
