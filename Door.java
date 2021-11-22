package maze;

import java.io.Serializable;

import database.Question;

public class Door implements Serializable{

	private static final long serialVersionUID = -1629390207231169623L;

	private boolean myDoorLocked = false;
    
    private Question myQuestion;
    
    private boolean myWall;
    
    public Door() {
    	myQuestion = new Question();
    }
    
    public void unLockDoor() {
        myDoorLocked = true;
    }
    
    public Question getQuestion() {
        return myQuestion;
    }
    
    public boolean isDoorLocked() {
        return myDoorLocked;
    }
    
    public boolean getWall() {
        return myWall;
    }
    
    public void setWall(boolean theWall) {
    	myWall = theWall;
    }
	
    @Override 
    public String toString() {
            String key;
            if(myDoorLocked = false) {
                    key = "The Door is Locked";
            } else {
                    key = "The Door has opened!";
            }
            return key;
    }
}
