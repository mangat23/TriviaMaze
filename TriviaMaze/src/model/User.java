/*
 * TCSS 360
 * 
 * User class.
 * TrivaMaze.
 */
package model;

import java.io.Serializable;

/**
 * This class stores methods that print various game menus, intros and outros.
 * 
 * @author Gurleen Grewal
 * @version Fall 2021
 */
public class User implements Serializable{
	
	/** The serialization number. */
	private static final long serialVersionUID = 2965691316779477347L;
	
	/**The row position of the user. */
	private int myX;
	
	/**The column position of the user. */
	private int myY;
	
	/**
	 * Sets the intital position of the user to (0,0).
	 */
	public User() {
		myX = 0;
		myY = 0;
	}
	
	/**
	 * Getter for the row position of the user.
	 * 
	 * @return the row position of the user.
	 */
	public int getX() {
		return myX;
	}
	
	/**
	 * Getter for the column position of the user.
	 * 
	 * @return the column position of the user.
	 */
	public int getY() {
		return myY;
	}
	
	/**
	 * Setter for the row position of the user.
	 * 
	 * @return the row position of the user.
	 */
	public void setX(int theNewPosition) {
		myX = theNewPosition;
	}
	
	/**
	 * Setter for the column position of the user.
	 * 
	 * @return the column position of the user.
	 */
	public void setY(int theNewPosition) {
		myY = theNewPosition;
	}
	
	/**
	 * Moves the user north.
	 */
	public void moveNorth() {
		myX--;
	}
	
	/**
	 * Moves the user south.
	 */
	public void moveSouth() {
		myX++;
	}
	
	/**
	 * Moves the user east.
	 */
	public void moveEast() {
		myY++;
	}
	
	/**
	 * Moves the user west.
	 */
	public void moveWest() {
		myY--;
	}
}
