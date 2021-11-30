package database;

import java.io.Serializable;
import java.util.Random;

public class Question implements Serializable{
	
	private static final long serialVersionUID = 2749682192896906213L;

	private String myQuestion;
	
	private String myAnswer;
	
	private String myHint;
	  
    public Question () {
	    DataBase db = new DataBase();
	    Random rand = new Random();
    	int randomNum = rand.nextInt(db.getRowCount()) + 1;
	    myQuestion = db.getQuestion(randomNum);
	    myAnswer = db.getAnswer(randomNum);
	    myHint = db.getHint(randomNum);
	}
    
	public String getQuestion() {
	    return myQuestion;
	}
	
	public String getAnswer() {
	    return myAnswer;
	}
	
	public String getHint() {
	    return myHint;
	}
	
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("\n" + myQuestion + "\n");
	    return sb.toString();
    }

}
