package database;

import java.io.Serializable;
import java.util.Random;

public class Question implements Serializable{
	
	private static final long serialVersionUID = 2749682192896906213L;

	private final String myQuestion;
	
	private final String myAnswer;
	
	private final String myHint;
	  
    public Question () {
	    final DataBase db = new DataBase();
	    final Random rand = new Random();
    	final int randomNum = rand.nextInt(db.getRowCount()) + 1;
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
	
	public boolean checkAnswer(String theAnswer) {
		theAnswer = theAnswer.toLowerCase();
		return myAnswer.equals(theAnswer);
	}
	
	@Override
	public String toString() {
	    final StringBuilder sb = new StringBuilder();
	    sb.append("\n" + myQuestion + "\n");
	    return sb.toString();
    }

}
