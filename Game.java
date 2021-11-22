package game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import maze.Maze;

public class Game {
	
	private Maze myMaze;
	private Scanner myScanner = new Scanner(System.in);
	
	public Game() {
		MenuOptions.printGameIntro();
	}
	
	public void run() {
    	String homeSelection = MenuOptions.printHomeMenu();
    	
    	if(homeSelection.equals("1")) {
    		myMaze = new Maze();
    		playGame();
    	}
    	if(homeSelection.equals("2")) {
    		loadGame();
    	}
    	if(homeSelection.equals("3")) {
    		MenuOptions.printHelpMenu();
    		run();
    	}
    	if(homeSelection.equals("4")) {
    		MenuOptions.printGameOutro();
    	}
	}
	
	public void playGame() {
		while(!myMaze.isGameOver() || myMaze.isGameWon()) {
			String choice = MenuOptions.printMenu2();
			if(choice.equals("1")) {
				String moveChoice = MenuOptions.printMoveMenu();
				myMaze.move(moveChoice);
			}
			if(choice.equals("2")) {
				saveGame();
				MenuOptions.printGameOutro();
			}
			if(choice.equals("3")) {
				MenuOptions.printGameOutro();
			}
			if(choice.equals("4")) {
				getCheat();
			}
		}
		if(myMaze.isGameWon()) {
			MenuOptions.printGameWon();
		}
	}
	
	 public void getCheat() {
			String input = myScanner.next();
	    	System.out.println("All you need is faith trust and...");
	    	if(input.equals("pixie dust")) {
	    		System.out.println("CHEAT MODE ENABLED");
	    		String cheat = myScanner.next();
	    		if(cheat.equals("TAKEmeTOtheEND")) {
	    			myMaze.setLocation(2,2);
	    		}
	    		else {
	    			System.out.print("EXITING CHEAT MODE...");
	    		}
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
