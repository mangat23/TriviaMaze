package maze;

import java.io.Serializable;
import java.util.Scanner;

import database.Question;

public class Door implements Serializable{

	private static final long serialVersionUID = -1629390207231169623L;
	
	private Question myQuestion;
	
	private boolean myDoorLocked;
	
	private boolean myQuestionAnswered;
    
    private boolean myWall;
    
    private Scanner myScan = new Scanner(System.in);
    
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
    
    public void setDoorlocked(boolean theValue) {
        myDoorLocked = theValue;
    }
    
    public boolean getQuestionAnswered() {
        return myQuestionAnswered;
    }
    
    public void setAnswerQuestion(boolean theValue) {
    	myQuestionAnswered = theValue;
    }
    
    public boolean getWall() {
        return myWall;
    }
    
    public void setWall(boolean theWall) {
    	myWall = theWall;
    }
    
    public boolean answerDoorQuestion() {
    	boolean result = false;
    	if(myQuestion.getAnswer().equals(myScan.nextLine().toLowerCase())) {
    		result = true;
    	}
    	return result;
    }
    
    public boolean getHint() {
    	boolean result = false;
    	System.out.println("Would you like a hint Y/N");
    	String input = myScan.nextLine().toLowerCase();
    	if(input.equals("y")) {
    		System.out.println(myQuestion.getHint());
    		result = true;
    	} else {
    		System.out.println("Okie dokie!");
    	}
    	return result;
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
