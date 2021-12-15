/*
 * TCSS 360
 * 
 * RoomTest class.
 * TrivaMaze.
 */
package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Door;
import model.Room;

/**
 * Test class for Room.
 * 
 * @author Tarnveer Mangat
 * @version Fall 2021
 */
public class RoomTest {
	
	/**Room object used throughout the test class. */
	Room room;
	
	/**
     * Method used to initialize the text fixture before each test.
     */
	@BeforeEach
	void setUp() throws Exception {
		room = new Room();
	}
	
	/**
     * Test method for {@link model.Room#setNorth(Door)}.
     */
	@Test
	public void testSetNorth() {
		Door north = new Door();
		room.setNorth(north);
		north.setDoorlocked(true);
	    assertEquals(true, north.getDoorLocked());	
	}
	
	/**
     * Test method for {@link model.Room#setSouth(Door)}.
     */
	@Test
	public void testSetSouth() {
		Door south = new Door();
		room.setSouth(south);
		south.setDoorlocked(true);
	    assertEquals(true, south.getDoorLocked());
	}
	
	/**
     * Test method for {@link model.Room#setEast(Door)}.
     */
	@Test
	public void testSetEast() {
		Door east = new Door();
		room.setEast(east);
		east.setDoorlocked(true);
	    assertEquals(true, east.getDoorLocked());
	}
	
	/**
     * Test method for {@link model.Room#setWest(Door)}.
     */
	@Test
	public void testSetWest() {
		Door west = new Door();
		room.setWest(west);
		west.setDoorlocked(true);
	    assertEquals(true, west.getDoorLocked());
	}
	
	/**
     * Test method for {@link model.Room#getVisited()}.
     */
	@Test
	public void getVistedTest() {
		room.setVisited(true);
		assertEquals(true, room.getVisited());
	}
	
	/**
     * Test method for {@link model.Room#setVisited(boolean)}.
     */
	@Test
	public void setVisitedTest() {
		room.setVisited(false);
		assertEquals(false, room.getVisited());
	}
	
	/**
     * Test method for {@link model.Room#getDoor(String)}.
     */
	@Test
	public void testEastGetDoor() {
		Door east = new Door();
		room.setEast(east);
		assertEquals(east, room.getDoor("east"));
	}
	
	/**
     * Test method for {@link model.Room#getDoor(String)}.
     */
	@Test
	public void testNorthGetDoor() {
		Door north = new Door();
		room.setNorth(north);
		assertEquals(north, room.getDoor("north"));
	}
	
	/**
     * Test method for {@link model.Room#getDoor(String)}.
     */
	@Test
	public void testSouthGetDoor() {
		Door south = new Door();
		room.setSouth(south);
		assertEquals(south, room.getDoor("south"));

	}
	
	/**
     * Test method for {@link model.Room#getDoor(String)}.
     */
	@Test
	public void testWestGetDoor() {
		Door west = new Door();
		room.setWest(west);
		assertEquals(west, room.getDoor("west"));
	}
	
	/**
     * Test method for {@link model.Room#roomStatus()}.
     */
	 @Test
	 public void testRoomStatusAvailable() {
	     assertEquals(room.roomStatus(), "\nCurrent room:\n"
	    		+ "NORTH: AVAILABLE\n"
	    		+ "SOUTH: AVAILABLE\n"
	    		+ "WEST: AVAILABLE\n"
	    		+ "EAST: AVAILABLE\n");
    }
	
   /**
	* Test method for {@link model.Room#roomStatus()}.
	*/
	@Test
    public void testRoomStatusOpen() {
		room.getDoor("north").setDoorlocked(false);
		room.getDoor("east").setDoorlocked(false);
		room.getDoor("west").setDoorlocked(false);
		room.getDoor("south").setDoorlocked(false);
		room.getDoor("north").setQuestionAnswered(true);
		room.getDoor("east").setQuestionAnswered(true);
		room.getDoor("west").setQuestionAnswered(true);
		room.getDoor("south").setQuestionAnswered(true);
		
		assertEquals(room.roomStatus(), "\nCurrent room:\n"
	    		+ "NORTH: OPEN\n"
	    		+ "SOUTH: OPEN\n"
	    		+ "WEST: OPEN\n"
	    		+ "EAST: OPEN\n");		
	}
	
	/**
     * Test method for {@link model.Room#roomStatus()}.
     */
	@Test
	public void testRoomStatusClosed() {
		room.getDoor("north").setDoorlocked(true);
		room.getDoor("east").setDoorlocked(true);
		room.getDoor("west").setDoorlocked(true);
		room.getDoor("south").setDoorlocked(true);
		room.getDoor("north").setQuestionAnswered(true);
		room.getDoor("east").setQuestionAnswered(true);
		room.getDoor("west").setQuestionAnswered(true);
		room.getDoor("south").setQuestionAnswered(true);
		
		assertEquals(room.roomStatus(), "\nCurrent room:\n"
	    		+ "NORTH: CLOSED\n"
	    		+ "SOUTH: CLOSED\n"
	    		+ "WEST: CLOSED\n"
	    		+ "EAST: CLOSED\n");
	}
	
	/**
     * Test method for {@link model.Room#roomStatus()}.
     */
	@Test
	public void testRoomStatusWall() {
		Door north = room.getDoor("north");
		Door south = room.getDoor("south");
		Door west = room.getDoor("west");
		Door east = room.getDoor("east");
		north.setWall(true);
		east.setWall(true);
		west.setWall(true);
		south.setWall(true);
		
		assertEquals(room.roomStatus(), "\nCurrent room:\n"
	    		+ "NORTH: WALL\n"
	    		+ "SOUTH: WALL\n"
	    		+ "WEST: WALL\n"
	    		+ "EAST: WALL\n");
	}
}