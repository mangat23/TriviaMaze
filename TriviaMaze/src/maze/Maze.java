package maze;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Maze implements Serializable{
    
	private static final long serialVersionUID = -8540040586351459632L;
	
	private final User myUser;

	private final Room[][] myMaze;
	
	private int myHintNum;
	
	transient private static final String ANSI_RESET = "\u001B[0m";
	
	transient private static final String ANSI_RED = "\033[0;31m"; 
	
	transient private static final String ANSI_BLUE = "\u001B[34m";
	
	transient private final Scanner myScan = new Scanner(System.in);
    
    public Maze() {
        myMaze = new Room[4][4];
        myUser = new User();
        myHintNum = 0;
        myUser.setX(0);
        myUser.setY(0);
        buildRooms();
    }
    
    public void buildRooms() {
        for (int i = 0; i < myMaze.length; i++) {
            for(int j = 0; j < myMaze[0].length; j++) {
                myMaze[i][j] = new Room();
            }
        }
        setWalls();
        joinDoors();
    }
    
    public void setWalls() {
    	for(int i = 0; i < myMaze.length; i++) {
    		myMaze[0][i].getDoor("north").setWall(true);
    		myMaze[i][0].getDoor("west").setWall(true);
    		myMaze[myMaze.length-1][i].getDoor("south").setWall(true);
    		myMaze[i][myMaze.length-1].getDoor("east").setWall(true);
    	}
    	myMaze[3][myMaze.length-1].getDoor("east").setWall(false);
    }
    
    public void joinDoors() {
    	for(int i = 0; i < myMaze.length; i++) {
    		myMaze[1][i].setNorth(myMaze[0][i].getDoor("south")); 
    		myMaze[2][i].setNorth(myMaze[1][i].getDoor("south"));
    		myMaze[3][i].setNorth(myMaze[2][i].getDoor("south"));
    		
    		myMaze[i][0].setEast(myMaze[i][1].getDoor("west"));
    		myMaze[i][1].setEast(myMaze[i][2].getDoor("west"));
    		myMaze[i][2].setEast(myMaze[i][3].getDoor("west"));
    	}
    }
    
    public void move(final String theDirection) {
    	final Room currentRoom = getCurrentRoom();
    	final Door currentDoor = currentRoom.getDoor(theDirection);
    	if(!currentDoor.getDoorLocked() && !currentDoor.getQuestionAnswered() &&
    			!currentDoor.getWall()) {
    		
    		System.out.println(ANSI_BLUE + currentDoor.getQuestion() + ANSI_RESET);
    		System.out.print(ANSI_BLUE + getHint(theDirection) + ANSI_RESET);
    		final String answer = myScan.nextLine();
    		final boolean result = currentDoor.getQuestion().checkAnswer(answer);
    		
    		if(result) {
    			currentDoor.setDoorlocked(true);
    			currentDoor.setQuestionAnswered(true);
    			System.out.println(ANSI_RED + "You got it!"+ ANSI_RESET);
    			moveUser(theDirection);
    			//System.out.println("\nUser location:" + myUser.getX() +"," + myUser.getY());
    		} else {
    			playDishonor();
    			currentDoor.setDoorlocked(false);
    			currentDoor.setQuestionAnswered(true);
    			System.out.println(ANSI_RED + "Oops! "+ theDirection +" door has been locked!"
    						+ ANSI_RESET);
    		}
    	} 
    	else {
    	    if(currentDoor.getWall()) {
    	    	playIdiots();
    			System.out.println(ANSI_RED + "Oops! You hit a wall."+ ANSI_RESET);
    		}
    		else if(!currentDoor.getDoorLocked() && currentDoor.getQuestionAnswered()) {
    			playIdiots();
    			System.out.println(ANSI_RED + "Oops! "+ theDirection + " door is locked!"
    						+ ANSI_RESET);
    		}
    		else if(currentDoor.getDoorLocked() && currentDoor.getQuestionAnswered()) {
    			System.out.println(ANSI_RED + "You got it!"+ ANSI_RESET);
    			moveUser(theDirection);
    		}
    	}
    } 
    
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
    
    public String getHint(String theDirection) {
    	final Room currentRoom = getCurrentRoom();
    	final Door currentDoor = currentRoom.getDoor(theDirection);
    	String hint  = "";
    	if(myHintNum > 0) {
			System.out.print(printHints());
			
			System.out.println(ANSI_BLUE + "Would you like a hint?" + ANSI_RESET);
			String input = myScan.nextLine().toLowerCase();
			String[] options = {"y", "n", "yes", "no"};
			while(!Arrays.asList(options).contains(input)){
				System.out.println(ANSI_BLUE + "Entry is not Valid! Try again." + ANSI_RESET);
				input = myScan.nextLine().toLowerCase();
			}
			if(input.equals("y") || input.equals("yes")) {
				hint = currentDoor.getQuestion().getHint() + "\n";
	    		myHintNum--;
	    	} 
	    	else {
	    		System.out.println(ANSI_BLUE + "Okie dokie!\n" + ANSI_RESET);
	    	} 
		}
    	return hint;
    }
    
    public String printHints() {
    	final StringBuilder sb = new StringBuilder();
    	sb.append(ANSI_RED + "Hints: \n");
    	for(int i = 0; i < myHintNum; i++) {
			sb.append("°o° ");
		}
    	sb.append("\n" + ANSI_RESET);
    	return sb.toString();
    }
    
    public int getHintNum() {
		return myHintNum;
	}
	
	public void setHintNum(final int theHintNum) {
		myHintNum = theHintNum;
	}
	
	public int[] getLocation() {
		int [] arr = {myUser.getX(), myUser.getY()};
		return arr;
	}

	public void setLocation(final int theX, final int theY) {
		myUser.setX(theX);
		myUser.setY(theY);
	}
    
    public Room getCurrentRoom() {
    	return myMaze[myUser.getX()][myUser.getY()];
    }
    
    public Room[][] getMaze() {
    	return myMaze;
    }

	public boolean isGameOver() {
		return getCurrentRoom().isRoomLocked();
	}

	public boolean isGameWon() {
		return myUser.getX() == myMaze.length-1 && myUser.getY() == myMaze[0].length-1 &&
				myMaze[myMaze.length-1][myMaze[0].length-1].getDoor("east").getDoorLocked();
	}
}
