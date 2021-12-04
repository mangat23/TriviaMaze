package maze;

import java.io.Serializable;

import database.Question;

public class Door implements Serializable{

	private static final long serialVersionUID = -1629390207231169623L;
	
	private final Question myQuestion;
	
	private boolean myDoorLocked;
	
	private boolean myQuestionAnswered;
    
    private boolean myWall;
    
    public Door() {
    	myQuestion = new Question();
    	myDoorLocked = false;
    	myQuestionAnswered = false;
    }
    
    public Question getQuestion() {
        return myQuestion;
    }
    
    public boolean getDoorLocked() {
        return myDoorLocked;
    }
    
    public void setDoorlocked(final boolean theValue) {
        myDoorLocked = theValue;
    }
    
    public boolean getQuestionAnswered() {
        return myQuestionAnswered;
    }
    
    public void setQuestionAnswered(final boolean theValue) {
    	myQuestionAnswered = theValue;
    }
    
    public boolean getWall() {
        return myWall;
    }
    
    public void setWall(final boolean theWall) {
    	myWall = theWall;
    }
}
