/*
 * TCSS 360
 * 
 * Door class.
 * TrivaMaze.
 */
package model;

import java.io.Serializable;

import database.Question;

/**
 * This class contains a door and its related methods.
 * 
 * @author Gurleen Grewal, Tarnveer Mangat
 * @version Fall 2021
 */
public class Door implements Serializable{
	
	/**The serialization number. */
	private static final long serialVersionUID = -1629390207231169623L;
	
	/**The question. */
	private final Question myQuestion;
	
	/**The state that shows if the door is locked or open. */
	private boolean myDoorLocked;
	
	/**The state that shows if the question has been asnwered. */
	private boolean myQuestionAnswered;
    
	/**The state which indicates if a door is a wall. */
    private boolean myWall;
    
    /**
     * Constructor creates a new question, sets door to locked and question answered to false. 
     */
    public Door() {
    	myQuestion = new Question();
    	myDoorLocked = false;
    	myQuestionAnswered = false;
    }
    
    /**
     * Gets the question associated with the door.
     * 
     * @return the question associated with the door.
     */
    public Question getQuestion() {
        return myQuestion;
    }
    
    /**
     * Gets the state of the door and wether it is open or closed.
     * 
     * @return true if the door is open and false if the door is closed.
     */
    public boolean getDoorLocked() {
        return myDoorLocked;
    }
    
    /**
     * Sets the state of the door to open or closed.
     * 
     * @param theValue the value assigned to the door.
     */
    public void setDoorlocked(final boolean theValue) {
        myDoorLocked = theValue;
    }
    
    /**
     * Returns if a question has been asnwered or not.
     * 
     * @return true if a question has been answered, and false otherwise.
     */
    public boolean getQuestionAnswered() {
        return myQuestionAnswered;
    }
    
    /**
     * Sets the value to if the question has been answered or not.
     * 
     * @param theValue sets the value to question answered.
     */
    public void setQuestionAnswered(final boolean theValue) {
    	myQuestionAnswered = theValue;
    }
    
    /**
     * Gets the state of a door, indicating if the door is a wall or not.
     * 
     * @return true if a door is a wall, and false otherwise.
     */
    public boolean getWall() {
        return myWall;
    }
    
    /**
     * Sets the door to a wall or no wall.
     * 
     * @param theWall the value of the wall.
     */
    public void setWall(final boolean theWall) {
    	myWall = theWall;
    }
}
