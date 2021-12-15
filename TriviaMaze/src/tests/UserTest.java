/*
 * TCSS 360
 * 
 * UserTest class.
 * TrivaMaze.
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.User;

/**
 * Test class for User.
 * 
 * @author Tarnveer Mangat
 * @version Fall 2021
 */
class UserTest {
	
	/**User object used throughout the test class. */
	private User user;
	
	/**
     * Method used to initialize the text fixture before each test.
     */
	@BeforeEach
	void setUp() throws Exception {
		user = new User();
	}
	
	/**
     * Test method for {@link model.User#getX()}.
     */
	@Test
	void testGetX() {
		assertEquals(0, user.getX());
	}
	
	/**
     * Test method for {@link model.User#getY()}.
     */
	@Test
	void testGetY() {
		assertEquals(0, user.getY());
	}
	
	/**
     * Test method for {@link model.User#setX()}.
     */
	@Test
	void testSetX() {
		user.setX(3);
		assertEquals(3, user.getX());
	}
	
	/**
     * Test method for {@link model.User#setY()}.
     */
	@Test
	void testSetY() {
		user.setY(6);
		assertEquals(6, user.getY());
	}
	
	/**
     * Test method for {@link model.User#moveNorth()}.
     */
	@Test
	void testMoveNorth() {
		user.setX(5);
		user.moveNorth();
		assertEquals(4, user.getX());
	}
	
	/**
     * Test method for {@link model.User#moveSouth()}.
     */
	@Test
	void testMoveSouth() {
		user.setX(5);
		user.moveSouth();
		assertEquals(6, user.getX());
	}
	
	/**
     * Test method for {@link model.User#moveWest()}.
     */
	@Test
	void testMoveWest() {
		user.setY(3);
		user.moveWest();
		assertEquals(2, user.getY());
	}
	
	/**
     * Test method for {@link model.User#moveEast()}.
     */
	@Test
	void testMoveEast() {
		user.setY(3);
		user.moveEast();
		assertEquals(4, user.getY());
	}

}
