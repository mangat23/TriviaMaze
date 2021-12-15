/*
 * TCSS 360
 * 
 * Maze class.
 * TrivaMaze.
 */
package model;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * This class stores the maze that the user navigates their way through.
 * 
 * @author Gurleen Grewal, Tarnveer Mangat, Abdullah Enes
 * @version Fall 2021
 */
public class Maze implements Serializable{
    
	/** The serialization number. */
	private static final long serialVersionUID = -8540040586351459632L;
	
	/**The user object that stores the user's position. */
	private final User myUser;
	
	/**The maze that contains the rooms. */
	private final Room[][] myMaze;
	
	/**The number of the hints granted to the user. */
	private int myHintNum;
	
	/**Constant that resets the color. */
	transient private static final String ANSI_RESET = "\u001B[0m";
	
	/**Constant for the color red. */
	transient private static final String ANSI_RED = "\033[0;31m"; 
	
	/**Constant for the blue color. */
	transient private static final String ANSI_BLUE = "\u001B[34m";
	
	/**Scanner object to get user input. */
	transient private final Scanner myScan = new Scanner(System.in);
    
	/**
	 * Constructor intializes the maze, hint numbers, user object, sets user position to the
	 * beginnig of the maze, and builds the rooms.
	 */
    public Maze() {
        myMaze = new Room[4][4];
        myUser = new User();
        myHintNum = 0;
        buildRooms();
    }
    
    /**
     * Builds the rooms of the maze, sets the walls and joins the doors.
     */
    public void buildRooms() {
        for (int i = 0; i < myMaze.length; i++) {
            for(int j = 0; j < myMaze[0].length; j++) {
                myMaze[i][j] = new Room();
            }
        }
        setWalls();
        joinDoors();
    }
    
    /**
     * Sets the walls of the maze to true.
     */
    public void setWalls() {
    	for(int i = 0; i < myMaze.length; i++) {
    		myMaze[0][i].getDoor("north").setWall(true);
    		myMaze[i][0].getDoor("west").setWall(true);
    		myMaze[myMaze.length-1][i].getDoor("south").setWall(true);
    		myMaze[i][myMaze.length-1].getDoor("east").setWall(true);
    	}
    }
    
    /**
     * Joins the doors of the maze to join rooms.
     */
    public void joinDoors() {
    	for(int i = 0; i < myMaze.length; i++) {
    		for(int j = 0; j < myMaze.length - 1; j++) {
    			myMaze[j+1][i].setNorth(myMaze[j][i].getDoor("south"));
    			
    			myMaze[i][j].setEast(myMaze[i][j+1].getDoor("west"));
    		}
    	}
    }
    
    /**
     * Moves the position of the user in the maze if and only if the question has been
     * answered correctly. Lets the user know if they are trying to go towards a wall
     * or if a door has been locked.
     * 
     * @param theDirection the direction the user wants to move in.
     */
    public boolean move(final String theDirection) {
    	boolean movable = false;
    	final Room currentRoom = getCurrentRoom();
    	final Door currentDoor = currentRoom.getDoor(theDirection);
    	
    	//When the door is open, the question has not been answered and there is no wall
    	if(!currentDoor.getDoorLocked() && !currentDoor.getQuestionAnswered() &&
    			!currentDoor.getWall()) {
    		
    		System.out.println(ANSI_BLUE + currentDoor.getQuestion() + ANSI_RESET);
    		System.out.print(ANSI_BLUE + getHint(theDirection) + ANSI_RESET);
    		final String answer = myScan.nextLine();
    		final boolean result = currentDoor.getQuestion().checkAnswer(answer);
    		
    		//if the question has been answered correctly then move the user and open the door.
    		if(result) {
    			movable = true;
    			playSplendid();
    			currentDoor.setDoorlocked(false);
    			currentDoor.setQuestionAnswered(true);
    			System.out.println(ANSI_RED + "You got it!"+ ANSI_RESET);
    			moveUser(theDirection);
    			
    			//Lock the door if the question has been answered incorrectly.
    		} else {
    			playDishonor();
    			currentDoor.setDoorlocked(true);
    			currentDoor.setQuestionAnswered(true);
    			System.out.println(ANSI_RED + "Oops! "+ theDirection +" door has been locked!"
    						+ ANSI_RESET);
    		}
    	}
    	else {
    		//Lets user know if the direction they are trying to move in is a wall.
    	    if(currentDoor.getWall()) {
    	    	playIdiots();
    			System.out.println(ANSI_RED + "Oops! You hit a wall."+ ANSI_RESET);
    		}
    	    //Lets user know if the door is locked.
    		else if(currentDoor.getDoorLocked() && currentDoor.getQuestionAnswered()) {
    			playIdiots();
    			System.out.println(ANSI_RED + "Oops! "+ theDirection + " door is locked!"
    						+ ANSI_RESET);
    		}
    	    //Moves the user if the door is open and question has been answered.
    	    //Does not move user if they are in the last room.
    		else if(!currentDoor.getDoorLocked() && currentDoor.getQuestionAnswered()) {
    			movable = true;
    			System.out.println(ANSI_RED + "You got it!"+ ANSI_RESET);
    			moveUser(theDirection);
    		}
    	}
    	return movable;
    } 
    
    /**
     * Moves the position of the user in the maze.
     * 
     * @param theDirection the direction the user wants to move in.
     */
    public void moveUser(final String theDirection) {
    	if(theDirection.toLowerCase().equals("north")) {
        	myUser.moveNorth();
        }
    	else if(theDirection.toLowerCase().equals("south")) {
        	myUser.moveSouth();
        }
    	else if(theDirection.toLowerCase().equals("west")) {
        	myUser.moveWest();
        }
    	else if(theDirection.toLowerCase().equals("east")) {
        	myUser.moveEast();
        }
    }
    
    /**
     * Gives to user an option to get a hint in the event that they have a hint or hints.
     * If user types in y/yes, the hint is displayed. If they type in n/no, the hint is not
     * displayed. 
     * 
     * @param theDirection the direction the user wants to move in. Is used to acess the door 
     * and its question.
     * @return the hint associated with a question.
     */
    public String getHint(String theDirection) {
    	final Room currentRoom = getCurrentRoom();
    	final Door currentDoor = currentRoom.getDoor(theDirection);
    	String hint  = "";
    	
    	//Give the hint only when the number of hints is atleast 1.
    	if(myHintNum > 0) {
			System.out.print(printHints());
			System.out.println(ANSI_BLUE + "Would you like a hint?" + ANSI_RESET);
			boolean result = getYesOrNo();
			if(result) {
				hint = currentDoor.getQuestion().getHint() + "\n";
	    		myHintNum--;
	    	} 
	    	else {
	    		System.out.println(ANSI_BLUE + "Okie dokie!\n" + ANSI_RESET);
	    	} 
		}
    	return hint;
    }
    
    /**
     * Gets user input that could be yes or no.
     * 
     * @return true if user input is yes, and false otherwise.
     */
    public boolean getYesOrNo() {
    	boolean result = false;
    	String input = myScan.nextLine().toLowerCase();
    	String[] options = {"y", "n", "yes", "no"};
		while(!Arrays.asList(options).contains(input)){
			System.out.println(ANSI_BLUE + "Entry is not Valid! Try again." + ANSI_RESET);
			input = myScan.nextLine().toLowerCase();
		}
		if(input.equals("y") || input.equals("yes")) {
			result = true;
		} 
		else {
			result = false;
		}
		return result;
    }
    
    /**
     * Lets the user know how many hints they have in the gameplay.
     * 
     * @return String representation of the number of hints a user has.
     */
    public String printHints() {
    	final StringBuilder sb = new StringBuilder();
    	sb.append(ANSI_RED + "Hints: \n");
    	for(int i = 0; i < myHintNum; i++) {
			sb.append("°o° ");
		}
    	sb.append("\n" + ANSI_RESET);
    	return sb.toString();
    }
    
    /**
     * Plays a sound when user tries to open a closed door or a wall.
     */
    public void playIdiots() {
    	try 
        {
            String soundName = "idiots.wav";    
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
            		new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    /**
     * Plays a sound when the user answers a question incorrectly.
     */
    public void playDishonor() {
    	try 
        {
            String soundName = "dishonor.wav";    
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
            		new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    /**
     * Plays a sound when the user answers a question correctly.
     */
    public void playSplendid() {
    	try 
        {
            String soundName = "splendid.wav";    
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
            		new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    /**
     * Getter for the number of hints.
     * 
     * @return number of hints
     */
    public int getHintNum() {
		return myHintNum;
	}
	
    /**
     * Setter for the number of hints.
     * 
     * @param theHintNum the value the hints are setted to.
     */
	public void setHintNum(final int theHintNum) {
		myHintNum = theHintNum;
	}
	
	/**
	 * Gets the user location within the maze.
	 * 
	 * @return user location
	 */
	public int[] getLocation() {
		int [] arr = {myUser.getX(), myUser.getY()};
		return arr;
	}
	
	/**
	 * Sets the user's position within the maze.
	 * @param theX the row position in the maze
	 * @param theY the column position the maze.
	 */
	public void setLocation(final int theX, final int theY) {
		myUser.setX(theX);
		myUser.setY(theY);
	}
    
	/**
	 * Gets the current room the user is in.
	 * 
	 * @return the current room.
	 */
    public Room getCurrentRoom() {
    	return myMaze[myUser.getX()][myUser.getY()];
    }
    
    /**
     * Gets the maze used in the game.
     * 
     * @return the maze 
     */
    public Room[][] getMaze() {
    	return myMaze;
    }
    
    /**
     * Recursive method checks if there is a path to the end of the maze.
     * 
     * @param theRow the row position of the user
     * @param theColumn the column position of the user
     * @return true if path exists, false otherwise
     */
    public boolean endPossible(int theRow, int theColumn) {
    	boolean result = false;
    	if (theRow == myMaze.length - 1 && theColumn == myMaze[0].length - 1) {
            return true;
    	}
    	if (myMaze[theRow][theColumn].getVisited()) {
            return false;
        }
    	
    	myMaze[theRow][theColumn].setVisited(true);
    	
    	result = canEnterRoom("north", theRow, theColumn) && endPossible(theRow - 1, theColumn);
    	if(!result) {
    		result = canEnterRoom("south", theRow, theColumn) 
    				&& endPossible(theRow + 1, theColumn);
    	}
    	if(!result) {
    		result = canEnterRoom("west", theRow, theColumn) 
    				&& endPossible(theRow, theColumn - 1);
    	}
    	if(!result) {
    		result = canEnterRoom("east", theRow, theColumn) 
    				&& endPossible(theRow, theColumn + 1);
    	}
    	return result;
    }
   
    /**
     * Determines if a room can be entered.
     * 
     * @param theDoor the door which the user travles through.
     * @param theRow the x position in the maze.
     * @param theColumn the y position in the maze.
     * @return true if the user can enter a room and false otherwise.
     */
    public boolean canEnterRoom(String theDoor, int theRow, int theColumn) {
    	Room currentRoom = myMaze[theRow][theColumn];
    	return !currentRoom.getDoor(theDoor).getWall() 
    			&& !currentRoom.getDoor(theDoor).getDoorLocked();
    }
    
    /**
     * Tells user if there is a path to the end. 
     * 
     * @return true if there is a path and false otherwise.
     */
	public boolean isPath() {
		boolean result = endPossible(myUser.getX(),myUser.getY());
		
		for(int i = 0; i < myMaze.length; i++) {
    		for(int j = 0; j < myMaze.length; j++) {
    			myMaze[i][j].setVisited(false);
    		}
		}
		return result;
	}
	
	/**
     * Tells user if game is won. 
     * 
     * @return true if game is won, false otherwise.
     */
	public boolean isGameWon() {
		return myUser.getX() == myMaze.length-1 && myUser.getY() == myMaze[0].length-1;
	}
}
