package com.jee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jee.modele.Users;

public class UserDAO {
	// CREATE USER
	public static void createUser(Users user) {

		try {
			Connection con = UtilConnexion.seConnecter();
			PreparedStatement ps = con.prepareStatement("INSERT INTO users(username, email, password) VALUE (?, ?, ?)");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.executeUpdate();
			
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// SHOW ALL USERS
	public static List<Users> getAllUsers(){
		List<Users> res = new ArrayList<>();
		
		try {
			Connection con = UtilConnexion.seConnecter();
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM users");
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String login = rs.getString("username");
				String email = rs.getString("email");
				String password = rs.getString("password");
				
				res.add(new Users(id, login, email, password));
			}
			
			con.close();
			rs.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	// GET ONE USER
	public static Users getUser(int id) {
		Users u = null;
		
		try {
			Connection con = UtilConnexion.seConnecter();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id=?");
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				u = new Users(
					rs.getInt("id"),
					rs.getString("username"),
					rs.getString("email"),
					rs.getString("password")
				);
			}
			
			con.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return u;
	}
	
	//UPDATE ONE USER
	public static void updateUser(Users user) {
		
		try {
			Connection con = UtilConnexion.seConnecter();
			PreparedStatement ps = con.prepareStatement("UPDATE users SET username =?, email=?, password=? WHERE id=?;");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.executeUpdate();
			
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//FILTER DATAS
	public static String checkInputs(String login, String email, String password, String password2) {
		if(login == null || login == "") {
			return "Error: Login can't be null";
		} else if (password == null || password == "") {
			return "Error: password can't be null";
		} else if (email == null || email == "") {
			return "Error: Email can't be null";
		} else if (!email.contains("@")) {
			return "Error: Login must be an email";
		} else if (!password.equals(password2)) {
			return "Error: Password fields aren't identical";
		} else if (password.length() < 9) {
			return "Error: The password must have more than 8 characters";	
		} else {
			return null;
		}
	}
	
	// DELETE ONE USER
	public static boolean deleteUser(int id) {
		try {
			Connection con = UtilConnexion.seConnecter();
			PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE id= ?");
			ps.setInt(1, id);
			
			ps.executeUpdate();
			con.close();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
