package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteDataSource;

public class DataBase {
	
	SQLiteDataSource ds;
	Statement stmt;
    
	public DataBase() {
		try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:questions.db");
            final Connection conn = ds.getConnection();
			stmt = conn.createStatement();
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
	}
	
	public String getQuestion(final int theRandomNum) {
		String question = "";
		try {
            String query = "SELECT QUESTIONS FROM questions where ROW_NUM =" + theRandomNum;
            ResultSet rs = stmt.executeQuery(query);
            question = rs.getString( "QUESTIONS" );
			
		}catch(SQLException e) {
			e.printStackTrace();
            System.exit( 0 );
		}
		return question;
	}
	
    public String getAnswer(final int theRandomNum) {
    	String answer = "";
        try {
            String query = "SELECT ANSWER FROM questions where ROW_NUM =" + theRandomNum;
            ResultSet rs = stmt.executeQuery(query);
            answer = rs.getString( "ANSWER" );
			
		}catch(SQLException e) {
			e.printStackTrace();
            System.exit( 0 );
		}
    	return answer;
	}
    
    public String getHint(final int theRandomNum) {
    	String hint = "";
        try {
            String query = "SELECT HINT FROM questions where ROW_NUM =" + theRandomNum;
            ResultSet rs = stmt.executeQuery(query);
            hint = rs.getString( "HINT" );
			
		}catch(SQLException e) {
			e.printStackTrace();
            System.exit( 0 );
		}
        return hint;
	}
    
    public int getRowCount() {
    	int count = 0;
    	try {
            String query = "SELECT COUNT(*) as total FROM questions";
            ResultSet rs = stmt.executeQuery(query);
			count = rs.getInt( "total" );
            
		}catch(SQLException e) {
			e.printStackTrace();
            System.exit( 0 );
		}
    	return count;
    }
}
