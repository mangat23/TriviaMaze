/*
 * TCSS 360
 * 
 * MazeTest class.
 * TrivaMaze.
 */
package tests;

import model.Maze;
import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for Maze class.
 *
 * @author Gurleen Grewal, Tarnveer Mangat, and Abdullah Enes
 * @version Autumn 2021
 *
 */
class MazeTest {
	
	/**Maze object used throughout the test class. */
    private Maze myMaze;
    
    /**Maze array used throughout the test class. */
    private Room[][] maze;
    
    /**Constant that resets the color. */
	transient private static final String ANSI_RESET = "\u001B[0m";
	
	/**Constant for the color red. */
	transient private static final String ANSI_RED = "\033[0;31m"; 
	
	/**
     * Method used to initialize the text fixture before each test.
     */
    @BeforeEach
    void setUp() {
        myMaze = new Maze();
        maze = myMaze.getMaze();
    }
    
    /**
     * Test for buildRooms method.
     */
    @Test
    void testBuildRooms() {
        myMaze.buildRooms();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++)  {
                assertNotNull(maze[i][j]);
            }
        }
    }
    
    /**
     * Test for setWalls method.
     */
    @Test
    void testSetWalls() {
    	myMaze.setWalls();
        for(int i = 0; i < maze.length; i++) {
            assertEquals(true, maze[0][i].getDoor("north").getWall());
            assertEquals(true, maze[i][0].getDoor("west").getWall());
            assertEquals(true, maze[maze.length-1][i].getDoor("south").getWall());
            assertEquals(true, maze[i][maze.length-1].getDoor("east").getWall());
            
            assertEquals(false, maze[i][2].getDoor("west").getWall());
        }
    }
    
    /**
     * Test for joinDoors method.
     */
    @Test
    void testJoinDoors() {
    	maze = myMaze.getMaze();
        Room room1 = maze[0][0];
        Room room2 = maze[1][0];
        assertEquals(room1.getDoor("south"), room2.getDoor("north"));
    }
    
    /**
     * Test for moveUser method towards north.
     */
    @Test
    void testMoveUserNorth() {
    	myMaze.setLocation(0, 0);
        myMaze.moveUser("north");
        assertEquals(-1, myMaze.getLocation()[0]);
        assertEquals(0, myMaze.getLocation()[1]);
    }
    
    /**
     * Test for moveUser method towards south.
     */
    @Test
    void testMoveUserSouth() {
    	myMaze.setLocation(0, 0);
    	myMaze.moveUser("south");
        assertEquals(1, myMaze.getLocation()[0]);
        assertEquals(0, myMaze.getLocation()[1]);
    }
    
    /**
     * Test for moveUser method towards west.
     */
    @Test
    void testMoveUserWest() {
    	myMaze.setLocation(0, 0);
    	myMaze.moveUser("west");
        assertEquals(0, myMaze.getLocation()[0]);
        assertEquals(-1, myMaze.getLocation()[1]);
    }
    
    /**
     * Test for moveUser method towards east.
     */
    @Test
    void testMoveUserEast() {
    	myMaze.setLocation(0, 0);
    	myMaze.moveUser("east");
        assertEquals(0, myMaze.getLocation()[0]);
        assertEquals(1, myMaze.getLocation()[1]);
    }
    
    /**
     * Tests if the number of hints are displayed correctly.
     */
    @Test
    void testPrintHints() {
    	myMaze.setHintNum(10);
    	assertEquals(myMaze.printHints(), ANSI_RED
    			+"Hints: \n"
    			+ "°o° °o° °o° °o° °o° °o° °o° °o° °o° °o° \n" 
    			+ ANSI_RESET);
    }
    
    /**
     * Test for getHintNum method.
     */
    @Test
    void testGetHintNum() {
        myMaze.setHintNum(2);
        assertEquals(2, myMaze.getHintNum());
    }
    
    /**
     * Test for setHintNum method.
     */
    @Test
    void testSetHintNum() {
        myMaze.setHintNum(16);
        assertEquals(16, myMaze.getHintNum());
    }
    
    /**
     * Test for getLocation method.
     */
    @Test
    void testGetLocation() {
        myMaze.setLocation(1,0);
        assertEquals(1,myMaze.getLocation()[0]);
        assertEquals(0,myMaze.getLocation()[1]);
    }
    
    /**
     * Test for setLocation method.
     */
    @Test
    void testSetLocation() {
        myMaze.setLocation(0,2);
        assertEquals(0,myMaze.getLocation()[0]);
        assertEquals(2,myMaze.getLocation()[1]);
    }
    
    /**
     * Test for getting the room the user is currently in.
     */
    @Test
    void testGetCurrentRoom() {
        myMaze.setLocation(0,2);
        assertEquals(myMaze.getCurrentRoom(),myMaze.getMaze()[0][2]);
    }
    
    /**
     * Test for if there is  a path to end.
     */
    @Test
    void testEndPossibleFalseDoorsLocked() {
    	myMaze.setLocation(0, 0);
    	myMaze.getMaze()[0][0].getDoor("east").setDoorlocked(true);
    	myMaze.getMaze()[0][0].getDoor("east").setQuestionAnswered(true);
    	
    	myMaze.getMaze()[0][0].getDoor("south").setDoorlocked(true);
    	myMaze.getMaze()[0][0].getDoor("south").setQuestionAnswered(true);
    	
    	assertEquals(false, myMaze.isPath());
    }
    
    /**
     * Test for if there is  a path to end.
     */
    @Test
    void testEndPossibleFalseLockedBetweenRooms() {
    	myMaze.setLocation(0, 0);
    	myMaze.getMaze()[0][0].getDoor("east").setDoorlocked(true);
    	myMaze.getMaze()[0][0].getDoor("east").setQuestionAnswered(true);
    	
    	myMaze.getMaze()[0][0].getDoor("south").setDoorlocked(false);
    	myMaze.getMaze()[0][0].getDoor("south").setQuestionAnswered(true);
    	
    	myMaze.getMaze()[1][0].getDoor("east").setDoorlocked(true);
    	myMaze.getMaze()[1][0].getDoor("east").setQuestionAnswered(true);
    	
    	myMaze.getMaze()[1][0].getDoor("south").setDoorlocked(true);
    	myMaze.getMaze()[1][0].getDoor("south").setQuestionAnswered(true);
    	
    	assertEquals(false, myMaze.isPath());
    }
    
    /**
     * Test for if there is  a path to end.
     */
    @Test
    void testEndPossibleTrue() {
    	myMaze.setLocation(2, 2);
    	assertEquals(true, myMaze.isPath());
    }
    
    /**
     * Test for if user can enter a room.
     */
    @Test
    void testCanEnterFalse() {
    	myMaze.setLocation(0, 0);
    	myMaze.getMaze()[0][0].getDoor("east").setDoorlocked(true);
    	assertEquals(myMaze.canEnterRoom("east", myMaze.getLocation()[0], 
    			myMaze.getLocation()[1]), false);
    }
    
    /**
     * Test for if user can enter a room.
     */
    @Test
    void testCanEnterTrue() {
    	myMaze.setLocation(0, 0);
    	myMaze.getMaze()[0][0].getDoor("east").setDoorlocked(false);
    	assertEquals(myMaze.canEnterRoom("east", myMaze.getLocation()[0], 
    			myMaze.getLocation()[1]), true);
    }
    
    /**
     * Test for isGameWon method.
     */
    @Test
    void testIsGameWonTrue() {
    	myMaze.setLocation(myMaze.getMaze().length - 1, myMaze.getMaze()[0].length - 1);
    	assertEquals(true, myMaze.isGameWon());
    }
    
    /**
     * Test for isGameWon method.
     */
    @Test
    void testIsGameNotWon1() {
    	myMaze.setLocation(0,0);
    	assertEquals(false, myMaze.isGameWon());
    }
    
    /**
     * Test for isGameWon method.
     */
    @Test
    void testIsGameNotWon2() {
    	myMaze.setLocation(myMaze.getMaze().length - 1,0);
    	assertEquals(false, myMaze.isGameWon());
    }
    
    /**
     * Test for isGameWon method.
     */
    @Test
    void testIsGameNotWon3() {
    	myMaze.setLocation(0,myMaze.getMaze()[0].length - 1);
    	assertEquals(false, myMaze.isGameWon());
    }
    
    /**
     * Test for getHint method at Maze class in case the answer is "yes"
     */
    @Test
    void testGetHintTrue() {
        myMaze.setHintNum(5);
        System.out.println("ENTER YES");
        myMaze.getHint("north");
        assertEquals(4, myMaze.getHintNum());
    }
    
    /**
     * Test for getHint method at Maze class in case the answer is "no"
     */
    @Test
    void testGetHintFalse() {
        myMaze.setHintNum(3);
        System.out.println("ENTER NO");
        myMaze.getHint("north");
        assertEquals(3, myMaze.getHintNum());
    }
    
    /**
     * Tests the move method when it can move.
     */
    @Test
    void testMoveTrue() {
        myMaze.getCurrentRoom().getDoor("east").setDoorlocked(false);
        myMaze.getCurrentRoom().getDoor("east").setQuestionAnswered(true);
        myMaze.getCurrentRoom().getDoor("east").setWall(false);
        assertEquals(true, myMaze.move("east"));
    }
    
    /**
     * Tests the move method when it cannot move.
     */
    @Test
    void testMoveFalse() {
        myMaze.getCurrentRoom().getDoor("east").setDoorlocked(true);
        myMaze.getCurrentRoom().getDoor("east").setQuestionAnswered(true);
        myMaze.getCurrentRoom().getDoor("east").setWall(false);
        assertEquals(false, myMaze.move("east"));
    }
    
    /**
     * Tests the move method when it cannot move.
     */
    @Test
    void testMoveFalse2() {
        myMaze.getCurrentRoom().getDoor("east").setWall(true);
        assertEquals(false, myMaze.move("east"));
    }
    
    /**
     * Tests the move method when it cannot move.
     */
    @Test
    void testMoveFalse3() {
        myMaze.getCurrentRoom().getDoor("east").setDoorlocked(true);
        myMaze.getCurrentRoom().getDoor("east").setQuestionAnswered(true);
        assertEquals(false, myMaze.move("east"));
    }
}