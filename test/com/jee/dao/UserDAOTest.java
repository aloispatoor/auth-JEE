package com.jee.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.jee.modele.Users;

class UserDAOTest {

	/**@Test
	void test() {
		fail("Not yet implemented");
	} */
	
	@Test
	public void createTest() throws Exception {
		Users u = new Users("testjUnit", "test@junit.com", "3615testjunit");
		
		UserDAO.createUser(u);
		
		assertEquals(u.getUsername(), "testjUnit");
		assertEquals(u.getEmail(), "test@junit.com");
		assertEquals(u.getPassword(), "3615testjunit");
	}
	
	@Test
	public void deleteTest() {
		Users u = new Users(78, "testDelete", "test@delete.fr", "3612testdelete");
		
		assertTrue(UserDAO.deleteUser(u.getId()));
		assertFalse(UserDAO.deleteUser(45));
	}
	
	@Test
	public void getUserTest() {
		Users u = new Users("testGetById", "test@getbyid.fr", "3612testgetById");
		u.setId(41);
		int id = u.getId();

		assertEquals(UserDAO.getUser(id), null);
		assertEquals(UserDAO.getUser(id), 45);
	}
	
	
}
