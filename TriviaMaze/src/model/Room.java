/*
 * TCSS 360
 * 
 * Room class.
 * TrivaMaze.
 */
package model;

import java.io.Serializable;

/**
 * This class stores a room and its four doors.
 * 
 * @author Gurleen Grewal, Tarnveer Mangat
 * @version Fall 2021
 */
public class Room implements Serializable{
	
	/** The serialization number. */
	private static final long serialVersionUID = -3573322487771035478L;
	
	/**The north door. */
	private Door myNorth;
	
	/**The south door. */
	private Door mySouth;
	
	/**The east door. */
	private Door myEast;
	
	/**The west door. */
	private Door myWest;
	
	private boolean myVisited;
	
	/***
	 * Constructor creates four new doors.
	 */
	public Room() {
		myNorth = new Door();
		mySouth = new Door();
		myEast = new Door();
		myWest = new Door();
		myVisited = false;
	}
	
	/**
	 * Gets the specified door.
	 * 
	 * @param theDoor the door that we are trying to acess.
	 * @return the door that is specified.
	 */
	public Door getDoor(final String theDoor) {
		Door door = null;
		if(theDoor.equals("north")) {
			door = myNorth;
		}
		else if(theDoor.equals("south")) {
			door = mySouth;
		}
		else if(theDoor.equals("west")) {
			door = myWest;
		}
		else if(theDoor.equals("east")) {
			door = myEast;
		}
		return door;
	}
	
	/**
	 * Sets the north door to the specified door.
	 * 
	 * @param theNorth the value of the door that is set
	 */
	public void setNorth(final Door theNorth) {
		myNorth = theNorth;
	}
	
	/**
	 * Sets the south door to the specified door.
	 * 
	 * @param theSouth the value of the door that is set
	 */
	public void setSouth(final Door theSouth) {
		mySouth = theSouth;
	}
	
	/**
	 * Sets the east door to the specified door.
	 * 
	 * @param theEast the value of the door that is set
	 */
	public void setEast(final Door theEast) {
		myEast = theEast;
	}
	
	/**
	 * Sets the west door to the specified door.
	 * 
	 * @param theWest the value of the door that is set
	 */
	public void setWest(final Door theWest) {
		myWest = theWest;
	}
	
	/**
	 * Gets the visited value of the room.
	 * 
	 * @return true if visited, and false otherwise
	 */
	public boolean getVisited() {
		return myVisited;
	}
	
	/**
	 * The value of the room is set to visited or not visited.
	 * 
	 * @param theValue the value that is set.
	 */
	public void setVisited(boolean theValue) {
		myVisited = theValue;
	}
	
	/**
	 * Gives a string representation of the room. Includes the four doors and if they are
	 * available to open, closed, open or if there is a wall.
	 * 
	 * @return string representation of the states of the four doors in the room.
	 */
	public String roomStatus() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nCurrent room:");
		final Door [] doors = {myNorth, mySouth, myWest, myEast};
		final String[] doorString = {"NORTH: ", "SOUTH: ", "WEST: ", "EAST: "};
		
		for(int i =0; i<doors.length; i++) {
			sb.append("\n");
			sb.append(doorString[i]);
			if(!doors[i].getDoorLocked() && doors[i].getQuestionAnswered()) {
				sb.append("OPEN");
			}
			else if(doors[i].getDoorLocked() && doors[i].getQuestionAnswered()) {
				sb.append("CLOSED");
			} 
			else if(doors[i].getWall()) {
				sb.append("WALL");
			}
			else if(!doors[i].getDoorLocked() && !doors[i].getQuestionAnswered()) {
				sb.append("AVAILABLE");
			}
		}
		
		sb.append("\n");
		return sb.toString();
	}
}
