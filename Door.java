package model;

import java.util.Random;

public class Door {

	private boolean myDoorLocked = false;
    
    private Question myQuestion;
    
    private boolean myWall;
    
    public Door() {
    	DataBase db = new DataBase();
    	Random rand = new Random();
    	int r = rand.nextInt(db.getRowCount()) + 1;
    	myQuestion = new Question(r);
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
