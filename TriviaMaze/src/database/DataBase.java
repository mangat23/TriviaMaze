/*
 * TCSS 360
 * 
 * Database class.
 * TrivaMaze.
 */
package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteDataSource;

/**
 * This class establishes a connection to the database and gets questions randomly from the 
 * database.
 * 
 * @author Gurleen Grewal
 * @version Fall 2021
 */
public class DataBase {
	
	/** The questions database. */
	SQLiteDataSource myDs;
	
	/** Statement object for database.*/
	Statement myStmt;
    
	/**
	 * Establishes connection to the databse.
	 */
	public DataBase() {
		try {
			myDs = new SQLiteDataSource();
			myDs.setUrl("jdbc:sqlite:questions.db");
            final Connection conn = myDs.getConnection();
			myStmt = conn.createStatement();
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
	}
	
	/**
	 * This method gets a question from the database using a given number. The number 
	 * corresponds to a row number.
	 * 
	 * @param theRandomNum the random row number.
	 * @return the question.
	 */
	public String getQuestion(final int theRandomNum) {
		String question = "";
		try {
            String query = "SELECT QUESTIONS FROM questions where ROW_NUM =" + theRandomNum;
            ResultSet rs = myStmt.executeQuery(query);
            question = rs.getString( "QUESTIONS" );
			
		}catch(SQLException e) {
			e.printStackTrace();
            System.exit( 0 );
		}
		return question;
	}
	
	/**
	 * This method gets an answer from the databse using a given number. The number corresponds
	 * to a row number.
	 * 
	 * @param theRandomNum the random row number.
	 * @return the answer.
	 */
    public String getAnswer(final int theRandomNum) {
    	String answer = "";
        try {
            String query = "SELECT ANSWER FROM questions where ROW_NUM =" + theRandomNum;
            ResultSet rs = myStmt.executeQuery(query);
            answer = rs.getString( "ANSWER" );
			
		}catch(SQLException e) {
			e.printStackTrace();
            System.exit( 0 );
		}
    	return answer;
	}
    
    /**
	 * This method gets a hint from the databse using a given number. The number corresponds
	 * to a row number.
	 * 
	 * @param theRandomNum the random row number.
	 * @return the hint.
	 */
    public String getHint(final int theRandomNum) {
    	String hint = "";
        try {
            String query = "SELECT HINT FROM questions where ROW_NUM =" + theRandomNum;
            ResultSet rs = myStmt.executeQuery(query);
            hint = rs.getString( "HINT" );
			
		}catch(SQLException e) {
			e.printStackTrace();
            System.exit( 0 );
		}
        return hint;
	}
    
    /**
     * This method gets the number of entries in the database.
     * 
     * @return the number of entries in the database.
     */
    public int getRowCount() {
    	int count = 0;
    	try {
            String query = "SELECT COUNT(*) as total FROM questions";
            ResultSet rs = myStmt.executeQuery(query);
			count = rs.getInt( "total" );
            
		}catch(SQLException e) {
			e.printStackTrace();
            System.exit( 0 );
		}
    	return count;
    }
}
