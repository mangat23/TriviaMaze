package model;

public class Question {
	
	private String myQuestion;
	
	private String myAnswer;
	
	private String myHint;
	  
    public Question (int theRandomNum) {
	    DataBase db = new DataBase();
	    myQuestion = db.getQuestion(theRandomNum);
	    myAnswer = db.getAnswer(theRandomNum);
	    myHint = db.getHint(theRandomNum);
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
	
	public boolean checkAnswer(String theAns) {
		boolean result = false;
		if(theAns.equals(myAnswer)) {
			result = true;
		}
		return result;
	}
	
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    
	    sb.append("Question: " + myQuestion + "/n");
	    sb.append("Answer: " + myAnswer + "/n");
	    
	    return sb.toString();
    }

}
