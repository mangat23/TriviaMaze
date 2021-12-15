/*
 * TCSS 360
 * 
 * DataBaseTest class.
 * TrivaMaze.
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import database.DataBase;


/**
 * Test class for DataBase.
 * 
 * @author Tarnveer Mangat
 * @version Fall 2021
 */
class DatabaseTest {
	
	/**DataBase object used throughout the test class. */
	private DataBase database;
	
	/**
     * Method used to initialize the text fixture before each test.
     */
	@BeforeEach
	void setUp() throws Exception {
		database = new DataBase();
	}
	
	/**
     * Test method for {@link database.DataBase#getQuestion(int)}.
     */
	@Test
	void testGetQuestion() {
		assertEquals(database.getQuestion(2)
				, "What is the name of the bear in the Jungle Book?");
	}
	
	/**
     * Test method for {@link database.DataBase#getAnswer(int)}.
     */
	@Test
	void testGetAnswer() {
		assertEquals(database.getAnswer(2), "balloo");
	}
	
	/**
     * Test method for {@link database.DataBase#getHint(int)}.
     */
	@Test
	void testGetHint() {
		assertEquals(database.getHint(2), "The first chacracter of the answer is 'b'.");
	}
	
	/**
     * Test method for {@link database.DataBase#getRowCount()}.
     */
	@Test
	void testGetRowCount() {
		assertEquals(database.getRowCount(), 60);
	}
}
