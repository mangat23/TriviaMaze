/*
 * TCSS 360
 * 
 * Game class.
 * TrivaMaze.
 */
package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import model.Maze;

/**
 * This class runs the home menu and the in game menu. It also handles saving and loading of 
 * the game.
 * 
 * @author Gurleen Grewal, Abdullah Enes
 * @version Fall 2021
 */
public class Game {
	
	/**The maze object that is used to play the game. */
	private Maze myMaze;
	
	/**Constant that resets the color. */
	private final String ANSI_RESET = "\u001B[0m";

	/**Constant for the color red. */
	private static final String ANSI_RED = "\033[0;31m";
	
	/**Constant for the green color. */
	private static final String ANSI_GREEN = "\u001B[32m";
	
	/**Constant for the blue color. */
	private static final String ANSI_BLUE = "\u001B[34m";
	
	/**
	 * Prints the intro of the game and plays the intro sound.
	 */
	public Game() {
		try 
        {
            String soundName = "intro.wav";    
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
            		new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
		MenuUtilities.printGameIntro();
	}
	
	/**
	 * Runs the home menu that creates a new game, loads a game, prints the help menu or quits
	 * the game.
	 */
	public void run() {
    	final String homeSelection = MenuUtilities.printHomeMenu();
    	
    	if(homeSelection.equals("1")) {
    		myMaze = new Maze();
    		playGame();
    	}
    	else if(homeSelection.equals("2")) {
    		loadGame();
    	}
    	else if(homeSelection.equals("3")) {
    		MenuUtilities.printHelpMenu();
    		run();
    	}
    	else if(homeSelection.equals("4")) {
    		MenuUtilities.printGameOutro();
    	}
	}
	
	/**
	 * Runs the in game menu till either the user quits, the game is won or lost.
	 */
	public void playGame() {
		boolean quit = false;
		
		while(myMaze.isPath() && !myMaze.isGameWon() && !quit) {
			
			final String choice = MenuUtilities.printMenu2();
			
			if(choice.equals("1")) {			//moves the user
				System.out.println(ANSI_BLUE + myMaze.getCurrentRoom().roomStatus() 
						+ ANSI_RESET);
				String moveChoice = MenuUtilities.printMoveMenu();
				myMaze.move(moveChoice);
			}
			else if(choice.equals("2")) {		//saves the game
				saveGame();
			}
			else if(choice.equals("3")) {		//quits the game
				MenuUtilities.printGameOutro();
				quit = true;
			}
			else if(choice.equals("4")) {		//enables cheats
				getCheat();
			}
		}
		
		if(myMaze.isGameWon()) {
			MenuUtilities.printGameWon();
		}
		else if(!myMaze.isPath()) {
			
			MenuUtilities.printGameOver();
		}
	}
	
	/**
	 * Enables cheats entered by the user.
	 */
	 public void getCheat() {
	     String cheat = MenuUtilities.isCheat();
	     
	     //Play music when a cheat is entered correctly.
	     if(cheat.length() > 0) {
	    	 try 
 	        {
 	            String soundName = "clever.wav";    
 	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
 	            		new File(soundName).getAbsoluteFile());
 	            Clip clip = AudioSystem.getClip();
 	            clip.open(audioInputStream);
 	            clip.start();
 	        } catch (Exception e) {
 	        	e.printStackTrace();
 	        }
	     }
	     
	     //Takes user halfway through the maze.
	     if(cheat.equals("TAKEmeTOtheEND")) {
	    	 System.out.println(ANSI_GREEN + "Hold tight while we take you there..." 
	    			 	+ ANSI_RESET);
	    	 myMaze.setLocation(2,2);
	     }
	     
	     //Grants a hint.
	     else if(cheat.equals("grantHint")) {
	    	 System.out.println(ANSI_GREEN + "Granting you a hint °o°..." + ANSI_RESET);
	    	 myMaze.setHintNum(myMaze.getHintNum() + 1);
	     }
	     
	     //Takes user to the next user and skips the question.
	     else if(cheat.equals("magicCarpet")) {
	    	 int [] arr = myMaze.getLocation();
	    	 int x = arr[0];
	    	 int y = arr[1];
	    	 System.out.println(ANSI_GREEN + "Hold tight while we take you there..." 
	    			 	+ ANSI_RESET);
	    	 if(y < myMaze.getMaze().length - 1) {
	    		 myMaze.setLocation(x, y+1);
	    	 } 
	    	 else if(x < myMaze.getMaze().length - 1) {
	    		 myMaze.setLocation(x+1, y);
	    	 }
	     }
	     
	     //Display the user's position in the maze.
	     else if(cheat.equals("display")) {
	    	 int [] arr = myMaze.getLocation();
	    	 int x = arr[0];
	    	 int y = arr[1];
	    	 System.out.println(ANSI_GREEN + "Current Location: " + "(" + x + "," + y + ")" 
	    			 	+ ANSI_RESET);
	     }
	  }
	
	/**
	 * Loads a game.
	 */
	public void loadGame() {
        try {
            final FileInputStream file = new FileInputStream("SavedGame");
            final ObjectInputStream putIn = new ObjectInputStream(file);
            
            myMaze = (Maze)putIn.readObject();
            putIn.close();
            file.close();
            System.out.println(ANSI_RED + "Game loaded! Taking you back to the magic!" 
            			+ ANSI_RESET); 
            playGame();
        
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Game could not be loaded. Try again °o°!" 
            			+ ANSI_RESET);
            e.printStackTrace();
            run();
        }
    }
	
	/**
	 * Saves a game.
	 */
	public void saveGame() {
		try {
            final FileOutputStream file = new FileOutputStream("SavedGame");
            final ObjectOutputStream putOut = new ObjectOutputStream(file);
            putOut.writeObject(myMaze);
            putOut.close();
            file.close();
            System.out.println(ANSI_RED + "Game saved! we'll keep your secrets safe!\n" 
            			+ ANSI_RESET);
            
		} catch (Exception e) {
			System.out.println(ANSI_RED + "The game could not be saved. Try again °o°!" 
						+ ANSI_RESET);
			e.printStackTrace();
		}
	}
}
