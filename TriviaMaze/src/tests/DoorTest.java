/*
 * TCSS 360
 * 
 * DoorTest class.
 * TrivaMaze.
 */
package tests;

import database.DataBase;
import model.Door;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for Door class.
 *
 * @author Gurleen Grewal, Tarnveer Mangat, and Abdullah Enes
 * @version Autumn 2021
 */
class DoorTest {
	
	/**Door object used throughout the test class. */
    private Door door;
    
    /**DataBase object used throughout the test class. */
    private DataBase data;
    
    /**
     * Method used to initialize the text fixture before each test.
     */
    @BeforeEach
    void setUp() {
        door = new Door();
        data = new DataBase();
    }
    
    /**
     * Test for getQuestion method at Door class
     */
    @Test
    public void testGetQuestion() {
        assertEquals("What are Hade's minions called in Hercules? Enter answer separated by "
        		+ "commas in alphabetical order.", data.getQuestion(4));
        assertEquals("What were Mickey Mouse's first words ever spoken?", 
        		data.getQuestion(16));
        assertEquals("Name Wendy's dog.", data.getQuestion(32));
        assertEquals("Which disney film has the song 'Let it Go'?", data.getQuestion(1));
        assertEquals("Cinderella's two step sisters are ______ and _____. Enter names "
        		+ "separated by a comma.", data.getQuestion(3));
    }
    
    /**
     * Test for getDoorLocked method at Door class
     */
    @Test
    void testGetDoorLocked() {
        door.setDoorlocked(false);
        assertEquals(false, door.getDoorLocked());
    }
    
    /**
     * Test for setDoorLocked method at Door class (NOT)
     */
    @Test
    void testGetDoorNotLocked() {
        door.setDoorlocked(true);
        assertEquals(true, door.getDoorLocked());
    }
    
    /**
     * Test for setDoorLocked method at Door class
     */
    @Test
    void testSetDoorlocked() {
        door.setDoorlocked(true);
        assertEquals(true, door.getDoorLocked());
    }
    
    /**
     * Test for getQuestionAnswered method at Door class
     */
    @Test
    void testGetQuestionAnswered() {
        door.setQuestionAnswered(true);
        assertEquals(true, door.getQuestionAnswered());
    }
    
    /**
     * Test for getQuestionAnswered method at Door class (NOT)
     */
    @Test
    void testGetQuestionNotAnswered() {
        door.setQuestionAnswered(false);
        assertEquals(false, door.getQuestionAnswered());
    }
    
    /**
     * Test for setAnswerQuestion method at Door class
     */
    @Test
    void testSetAnswerQuestion() {
        door.setQuestionAnswered(true);
        assertEquals(true, door.getQuestionAnswered());
    }
    
    /**
     * Test for getWall method at Door class
     */
    @Test
    void testGetWall() {
        door.setWall(true);
        assertEquals(true, door.getWall());
    }
    
    /**
     * Test for setWall method at Door class
     */
    @Test
    void testSetWall() {
        door.setWall(false);
        assertEquals(false, door.getWall());
    }
}