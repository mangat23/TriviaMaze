package game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import maze.Maze;

public class Game {
	
	private Maze myMaze;
	
	public Game() {
		MenuUtilities.printGameIntro();
	}
	
	public void run() {
    	String homeSelection = MenuUtilities.printHomeMenu();
    	
    	if(homeSelection.equals("1")) {
    		myMaze = new Maze();
    		playGame();
    	}
    	if(homeSelection.equals("2")) {
    		loadGame();
    		playGame();
    	}
    	if(homeSelection.equals("3")) {
    		MenuUtilities.printHelpMenu();
    		run();
    	}
    	if(homeSelection.equals("4")) {
    		MenuUtilities.printGameOutro();
    	}
	}
	
	public void playGame() {
		while(!myMaze.isGameOver() || myMaze.isGameWon()) {
			String choice = MenuUtilities.printMenu2();
			if(choice.equals("1")) {
				//move
				myMaze.displayChoices();
				String moveChoice = MenuUtilities.printMoveMenu();
				myMaze.move(moveChoice);
			}
			else if(choice.equals("2")) {
				//save game
				saveGame();
				MenuUtilities.printGameOutro();
			}
			else if(choice.equals("3")) {
				//quit
				MenuUtilities.printGameOutro();
				break;
			}
			else if(choice.equals("4")) {
				//cheats
				getCheat();
			}
		}
		if(myMaze.isGameWon()) {
			MenuUtilities.printGameWon();
		}
		if(myMaze.isGameOver()) {
			MenuUtilities.printGameOver();
		}
	}
	
	//enable cheats and keep track of scores?
	 public void getCheat() {
	     String cheat = MenuUtilities.isCheat();
	     if(cheat.equals("TAKEmeTOtheEND")) {
	    	 myMaze.setLocation(2,2);
	     }
	     if(cheat.equals("grantHint")) {
	    	 myMaze.setHintNum(myMaze.getHintNum() + 1);
	     }
	     if(cheat.equals("magicCarpet")) {
	    	 //myMaze.setLocation(, );
	     }
	  }
	
	public void loadGame() {
        try {
            FileInputStream file = new FileInputStream("GameSaved.ser");
            ObjectInputStream putIn = new ObjectInputStream(file);
            
            myMaze = (Maze)putIn.readObject();
            putIn.close();
            file.close();
            System.out.println("Game loaded! Taking you back to the magic!");                      
        
        } catch (IOException e) {
            System.out.print("Game could not be loaded. Need more pixie dust °o°!");
            run();
        } catch (ClassNotFoundException e) {
            System.out.print("Game not found. Need more pixie dust °o°!");
            e.printStackTrace();
            run();
        }
    }
	
	public void saveGame() {
		try {
            FileOutputStream file = new FileOutputStream("GameSaved.ser");
            ObjectOutputStream putOut = new ObjectOutputStream(file);
            putOut.writeObject(myMaze);
            putOut.close();
            file.close();
            System.out.println("Game saved! we'll keep your secrets safe!");
		} catch (IOException e) {
			System.out.println("The game hasn't been saved.");
		}
	}
}
