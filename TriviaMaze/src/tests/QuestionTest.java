/*
 * TCSS 360
 * 
 * QuestionTest class.
 * TrivaMaze.
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.Question;

/**
 * Test class for Question.
 * 
 * @author Tarnveer Mangat
 * @version Fall 2021
 */
class QuestionTest {
	
	/**Question object used throughout the test class. */
	private Question question;
	
	/**
     * Method used to initialize the text fixture before each test.
     */
	@BeforeEach
	void setUp() throws Exception {
		question = new Question();
	}
	
	/**
     * Test method for {@link database.Question#getQuestion()}.
     */
	@Test
	void testGetQuestion() {
		question.setQuestion("What movie is the song Let it go from?");
		assertEquals(question.getQuestion(), "What movie is the song Let it go from?");
	}
	
	/**
     * Test method for {@link database.Question#getAnswer()}.
     */
	@Test
	void testGetAnswer() {
		question.setAnswer("Frozen");
		assertEquals(question.getAnswer(), "Frozen");
	}
	
	/**
     * Test method for {@link database.Question#getHint()}.
     */
	@Test
	void testGetHint() {
		question.setHint("Elsa and Anna are in this movie.");
		assertEquals(question.getHint(), "Elsa and Anna are in this movie.");
	}
	
	/**
     * Test method for {@link database.Question#checkAnswer(String)}.
     */
	@Test
	void testCheckAnswerFalse() {
		assertEquals(question.checkAnswer("blah"), false);
	}
	
	/**
     * Test method for {@link database.Question#checkAnswer(String)}.
     */
	@Test
	void testCheckAnswerTrue() {
		assertEquals(question.checkAnswer("Frozen"), false);
	}
	
	/**
     * Test method for {@link database.Question#checkAnswer(String)}.
     */
	@Test
	void testCheckAnswerSkip() {
		assertEquals(question.checkAnswer("SKIP"), true);
	}
	
	/**
     * Test method for {@link database.Question#toString()}.
     */
	@Test
	void testToString() {
		question.setQuestion("What movie is the song Let it go from?");
		assertEquals(question.toString(), "\nWhat movie is the song Let it go from?\n");
	}

}
