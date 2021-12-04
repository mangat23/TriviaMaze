package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import maze.Maze;

public class Game {
	
	private Maze myMaze;
	
	private final String ANSI_RESET = "\u001B[0m";

	transient private static final String ANSI_RED = "\033[0;31m";
	
	transient private static final String ANSI_GREEN = "\u001B[32m";
	
	transient private static final String ANSI_BLUE = "\u001B[34m";
	
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
	
	public void playGame() {
		while(!myMaze.isGameOver() || myMaze.isGameWon()) {
			final String choice = MenuUtilities.printMenu2();
			
			if(choice.equals("1")) {	//move
				System.out.println(ANSI_BLUE + myMaze.getCurrentRoom().roomStatus() 
						+ ANSI_RESET);
				String moveChoice = MenuUtilities.printMoveMenu();
				myMaze.move(moveChoice);
			}
			else if(choice.equals("2")) {	//save game
				saveGame();
			}
			else if(choice.equals("3")) {	//quit
				MenuUtilities.printGameOutro();
				break;
			}
			else if(choice.equals("4")) {	//cheats
				getCheat();
			}
		}
		
		if(myMaze.isGameWon()) {
			MenuUtilities.printGameWon();
		}
		else if(myMaze.isGameOver()) {
			MenuUtilities.printGameOver();
		}
	}
	
	 public void getCheat() {
	     String cheat = MenuUtilities.isCheat();
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
	     if(cheat.equals("TAKEmeTOtheEND")) {
	    	 System.out.println(ANSI_GREEN + "Hold tight while we take you there..." 
	    			 	+ ANSI_RESET);
	    	 myMaze.setLocation(2,2);
	     }
	     else if(cheat.equals("grantHint")) {
	    	 System.out.println(ANSI_GREEN + "Granting you a hint °o°..." + ANSI_RESET);
	    	 myMaze.setHintNum(myMaze.getHintNum() + 1);
	     }
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
	    	 else {
	    		 System.out.println(ANSI_GREEN + "\nLooks like you will have to answer this "
	    		 		+ "question °o°!" + ANSI_RESET);
	    	 }
	     }
	     else if(cheat.equals("display")) {
	    	 int [] arr = myMaze.getLocation();
	    	 int x = arr[0];
	    	 int y = arr[1];
	    	 System.out.println(ANSI_GREEN + "Current Location: " + "(" + x + "," + y + ")" 
	    			 	+ ANSI_RESET);
	     }
	  }
	
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
