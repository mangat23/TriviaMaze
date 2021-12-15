/*
 * TCSS 360
 * 
 * Question class.
 * TrivaMaze.
 */
package database;

import java.io.Serializable;
import java.util.Random;

/**
 * This class stores a question, its answers and hints.
 * 
 * @author Gurleen Grewal
 * @version Fall 2021
 */
public class Question implements Serializable{
	
	/** The serialization number. */
	private static final long serialVersionUID = 2749682192896906213L;
	
	/** The question. */
	private String myQuestion;
	
	/** The answer. */
	private String myAnswer;
	
	/** The hint. */
	private String myHint;
	
	/**
	 * This constructor gets the question, its answer and hint using a ranomly generated
	 * number from the database.
	 */
    public Question () {
	    final DataBase db = new DataBase();
	    final Random rand = new Random();
    	final int randomNum = rand.nextInt(db.getRowCount()) + 1;
	    myQuestion = db.getQuestion(randomNum);
	    myAnswer = db.getAnswer(randomNum);
	    myHint = db.getHint(randomNum);
	}
    
    /**
     * This method gets the question.
     * @return the question.
     */
	public String getQuestion() {
	    return myQuestion;
	}
	
	/**
     * This method sets the question.
     */
	public void setQuestion(String theQuestion) {
	    myQuestion = theQuestion;
	}
	
	/**
     * This method gets the answer.
     * @return the answer.
     */
	public String getAnswer() {
	    return myAnswer;
	}
	
	/**
     * This method sets the answer.
     */
	public void setAnswer(String theAnswer) {
	    myAnswer = theAnswer;
	}
	
	/**
     * This method gets the hint.
     * @return the hint.
     */
	public String getHint() {
	    return myHint;
	}
	
	/**
     * This method sets the hint.
     */
	public void setHint(String theHint) {
	    myHint = theHint;
	}
	
	/**
	 * This method checks if the answer entered by the user is correct.
	 * 
	 * @param theAnswer the answer from the user.
	 * @return true if the answer is correct, and false otherwise.
	 */
	public boolean checkAnswer(String theAnswer) {
		boolean result = false;
		if(theAnswer.equals("SKIP")) {
			result = true;
		} else {
			theAnswer = theAnswer.toLowerCase();
			result = myAnswer.equals(theAnswer);
		}
		return result;
	}
	
	/**
	 * This method returns a string version of the question.
	 */
	@Override
	public String toString() {
	    final StringBuilder sb = new StringBuilder();
	    sb.append("\n" + myQuestion + "\n");
	    return sb.toString();
    }

}
