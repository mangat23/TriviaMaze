package game;
import java.util.*;

public class MenuUtilities{
	
	transient private final static Scanner myScanner = new Scanner(System.in);
	
    transient private static final String ANSI_RESET = "\u001B[0m";
	
	transient private static final String ANSI_RED = "\033[0;31m";
	
	transient private static final String ANSI_GREEN = "\u001B[32m";
    
    public static void main(String [] args) {
    	final Game game = new Game();
    	game.run();
    }
    
    public static String printHomeMenu() {
    	System.out.println(ANSI_RED);
        System.out.println("1-New Game");
        System.out.println("2-Load Game");
        System.out.println("3-Help Menu");
        System.out.println("4-Quit \n" + ANSI_RESET);
        
        final String[] options = new String[]{"1", "2", "3", "4"};
        String choice = myScanner.nextLine();
        while(!Arrays.asList(options).contains(choice)) {
        	System.out.println(ANSI_RED + "Entry is not Valid! Try again." + ANSI_RESET);
    		choice = myScanner.nextLine();
        }
    	return choice;
    }
    
    public static String printMenu2() {
    	System.out.println(ANSI_RED);
    	System.out.println("What would you like to do?");
    	System.out.println("1-Move");
        System.out.println("2-Save Game");
        System.out.println("3-Quit" + ANSI_RESET);
        
        final String[] options = new String[]{"1", "2", "3", "4"};
        String choice = myScanner.nextLine();
        while(!Arrays.asList(options).contains(choice)) {
        	System.out.println(ANSI_RED + "Entry is not Valid! Try again." + ANSI_RESET);
    		choice = myScanner.nextLine();
        }
    	return choice;
    }
    
    public static String printMoveMenu() {
    	System.out.println(ANSI_RED + "Which room do you want to proceed to? Enter north,");
    	System.out.println("south, east or west.");
        System.out.println(ANSI_RESET);
        
        final String[] options = new String[]{"north", "south", "east", "west"};
        String choice = myScanner.nextLine().toLowerCase();
        while(!Arrays.asList(options).contains(choice)) {
        	System.out.println(ANSI_RED + "Entry is not Valid! Try again." + ANSI_RESET);
    		choice = myScanner.nextLine().toLowerCase();
        }
    	return choice;
    }
    
    public static String isCheat() {
    	String result = "";
    	final String [] cheats = {"pixie", "TAKEmeTOtheEND", "grantHint", "magicCarpet", 
    			"display"};
    	System.out.println(ANSI_GREEN + "\nAll you need is faith trust and ... dust" 
    				+ ANSI_RESET);
	    String input1 = myScanner.nextLine();
	    
	    String input2 = "";
	    if(input1.equals("pixie")) {
	        System.out.println(ANSI_GREEN + "CHEAT MODE ENABLED" + ANSI_RESET);
	        input2 = myScanner.nextLine();
	    }
	    if(Arrays.asList(cheats).contains(input1) && Arrays.asList(cheats).contains(input2)) {
	    	result = input2;
	    } 
	    else {
	    	System.out.println(ANSI_GREEN + "EXITING CHEAT MODE..." + ANSI_RESET);
	    }
    	return result;
    }
    
    public static void printHelpMenu() {
    	printHelpMenuIntro();
    	
    	System.out.println(ANSI_RED + "About:" + ANSI_RESET);
    	System.out.println("	Disney Trivia Maze is a trivia based game that consists of");
    	System.out.println("	disney themed questions.");
    	System.out.println("	At the beginning of the game, the player is put at the start");
    	System.out.println("	of the maze.");
    	System.out.println("	The aim of the game is to make way to the end of the maze by");
    	System.out.println("	walking through various door and rooms and answering the");
    	System.out.println("	trivia questions.");
    	System.out.println("	If the user is stuck in a room and is unable to answer any");
    	System.out.println("	of the questions in the room correctly, the game is over.");
    	System.out.println();
    	
    	System.out.println(ANSI_RED + "Instructions:" + ANSI_RESET);
    	System.out.println("	To move through the maze, simply type in the direction you");
    	System.out.println("	would like to move in. The door associated with the");
    	System.out.println("	direction and its question is prompted.");
    	System.out.println("	There are 4 types of questions: True/False, Multiple choice");
    	System.out.println("	fill in the blanks and short answer.");
    	System.out.println("	For true/false questions enter either true or false.");
    	System.out.println("	For multiple choice questions, enter the correct option that");
    	System.out.println("	is a,b,c or d.");
    	System.out.println("	For fill in the blanks and short answer type questions,");
    	System.out.println("	simply enter your answer.");
    	System.out.println("	Once you answer the question correctly, the door is opened");
    	System.out.println("	and you are taken to the next room.");
    	System.out.println("	In the event that a question is answered incorrectly, the");
    	System.out.println("	door is locked permanently and the user is unable to move");
    	System.out.println("	through that door.");
    	System.out.println("	If you are able to make way to the last room, you win the game.");
    	System.out.println("	If you get stuck in a room, the game is over.");
    	System.out.println("	The game also has a secret cheat system!");
    	System.out.println();
    }
    
    
    public static void printGameIntro() {
    	System.out.println();
    	System.out.println("                ,n888888n,");
	    System.out.println("               .8888888888b");
	    System.out.println("               888888888888nd8P~''8g,");
	    System.out.println("               88888888888888   _  `'~\\.  .n.");
	    System.out.println("               `Y888888888888. / _  |~\\ (8\"8b");
	    System.out.println("              ,nnn.. 8888888b.  |  \\ \\m\\|8888P");
	    System.out.println("            ,d8888888888888888b. \\8b|.\\P~ ~P8~");
	   	System.out.println("            888888888888888P~~_~  `8B_|      |");
	   	System.out.println("            ~888888888~'8'   d8.    ~      _/");
	   	System.out.println("             ~Y8888P'   ~\\ | |~|~b,__ __--~");
	   	System.out.println("         --~~\\   ,d8888888b.\\`\\_/ __/~");
	   	System.out.println("              \\_ d88888888888b\\_-~8888888bn.");
	   	System.out.println("                \\8888P   \"Y888888888888\"888888bn.");
	   	System.out.println("            /~'\\_\"__)      \"d88888888P,-~~-~888");
	   	System.out.println("           /  / )   ~\\     ,888888/~' /  / / 8'");
	   	System.out.println("        .-(  / / / |) )-----------(/ ~  / /  |---.");
	   	System.out.println(" ______ | (   '    /_/ Disney Trivia(__/     /   |_______");
	   	System.out.println(" \\      |   (_(_ ( /~      Maze      \\___/_/'    |      /");
	   	System.out.println("  \\     |  Welcome, Let's test your knowledge!   |     /");
	   	System.out.println("  /     (________________________________________)     \\");
	   	System.out.println(" /__________)     __--|~mb  ,g8888b.         (__________\\");
	   	System.out.println("               _/    8888b(.8P\"~'~---__");
	   	System.out.println("              /       ~~~| / ,/~~~~--, `\\");
	   	System.out.println("             (       ~\\,_) (/         ~-_`\\\\");
	    System.out.println("              \\  -__---~._ \\             ~\\\\");
	   	System.out.println("              (           )\\\\              ))\\");
	   	System.out.println("              `\\          )  \"-_           `|\\");
	 	System.out.println("                \\__    __/      ~-__   __--~\\\\");
	   	System.out.println("                   ~~\"~             ~~~\n");
	   	System.out.println();
    }
    
    public static void printHelpMenuIntro() {
    	System.out.println();
    	System.out.println("                ,n888888n,");
	    System.out.println("               .8888888888b");
	    System.out.println("               888888888888nd8P~''8g,");
	    System.out.println("               88888888888888   _  `'~\\.  .n.");
	    System.out.println("               `Y888888888888. / _  |~\\ (8\"8b");
	    System.out.println("              ,nnn.. 8888888b.  |  \\ \\m\\|8888P");
	    System.out.println("            ,d8888888888888888b. \\8b|.\\P~ ~P8~");
	   	System.out.println("            888888888888888P~~_~  `8B_|      |");
	   	System.out.println("            ~888888888~'8'   d8.    ~      _/");
	   	System.out.println("             ~Y8888P'   ~\\ | |~|~b,__ __--~");
	   	System.out.println("         --~~\\   ,d8888888b.\\`\\_/ __/~");
	   	System.out.println("              \\_ d88888888888b\\_-~8888888bn.");
	   	System.out.println("                \\8888P   \"Y888888888888\"888888bn.");
	   	System.out.println("            /~'\\_\"__)      \"d88888888P,-~~-~888");
	   	System.out.println("           /  / )   ~\\     ,888888/~' /  / / 8'");
	   	System.out.println("        .-(  / / / |) )-----------(/ ~  / /  |---.");
	   	System.out.println(" ______ | (   '    /_/ Disney Trivia(__/     /   |_______");
	   	System.out.println(" \\      |   (_(_ ( /~      Maze      \\___/_/'    |      /");
	   	System.out.println("  \\     |           Here is some help!           |     /");
	   	System.out.println("  /     (________________________________________)     \\");
	   	System.out.println(" /__________)     __--|~mb  ,g8888b.         (__________\\");
	   	System.out.println("               _/    8888b(.8P\"~'~---__");
	   	System.out.println("              /       ~~~| / ,/~~~~--, `\\");
	   	System.out.println("             (       ~\\,_) (/         ~-_`\\\\");
	    System.out.println("              \\  -__---~._ \\             ~\\\\");
	   	System.out.println("              (           )\\\\              ))\\");
	   	System.out.println("              `\\          )  \"-_           `|\\");
	 	System.out.println("                \\__    __/      ~-__   __--~\\\\");
	   	System.out.println("                   ~~\"~             ~~~\n");
	   	System.out.println();
    }
    
    public static void printGameOutro() {
    	System.out.println();
    	System.out.println("                ,n888888n,");
	    System.out.println("               .8888888888b");
	    System.out.println("               888888888888nd8P~''8g,");
	    System.out.println("               88888888888888   _  `'~\\.  .n.");
	    System.out.println("               `Y888888888888. / _  |~\\ (8\"8b");
	    System.out.println("              ,nnn.. 8888888b.  |  \\ \\m\\|8888P");
	    System.out.println("            ,d8888888888888888b. \\8b|.\\P~ ~P8~");
	   	System.out.println("            888888888888888P~~_~  `8B_|      |");
	   	System.out.println("            ~888888888~'8'   d8.    ~      _/");
	   	System.out.println("             ~Y8888P'   ~\\ | |~|~b,__ __--~");
	   	System.out.println("         --~~\\   ,d8888888b.\\`\\_/ __/~");
	   	System.out.println("              \\_ d88888888888b\\_-~8888888bn.");
	   	System.out.println("                \\8888P   \"Y888888888888\"888888bn.");
	   	System.out.println("            /~'\\_\"__)      \"d88888888P,-~~-~888");
	   	System.out.println("           /  / )   ~\\     ,888888/~' /  / / 8'");
	   	System.out.println("        .-(  / / / |) )-----------(/ ~  / /  |---.");
	   	System.out.println(" ______ | (   '    /_/ Disney Trivia(__/     /   |_______");
	   	System.out.println(" \\      |   (_(_ ( /~      Maze      \\___/_/'    |      /");
	   	System.out.println("  \\     |  Hot Dog, that was fun. See you soon!  |     /");
	   	System.out.println("  /     (________________________________________)     \\");
	   	System.out.println(" /__________)     __--|~mb  ,g8888b.         (__________\\");
	   	System.out.println("               _/    8888b(.8P\"~'~---__");
	   	System.out.println("              /       ~~~| / ,/~~~~--, `\\");
	   	System.out.println("             (       ~\\,_) (/         ~-_`\\\\");
	    System.out.println("              \\  -__---~._ \\             ~\\\\");
	   	System.out.println("              (           )\\\\              ))\\");
	   	System.out.println("              `\\          )  \"-_           `|\\");
	 	System.out.println("                \\__    __/      ~-__   __--~\\\\");
	   	System.out.println("                   ~~\"~             ~~~\n");
	   	System.out.println();
    }
    
    public static void printGameOver() {
    	System.out.println();
    	System.out.println("                ,n888888n,");
	    System.out.println("               .8888888888b");
	    System.out.println("               888888888888nd8P~''8g,");
	    System.out.println("               88888888888888   _  `'~\\.  .n.");
	    System.out.println("               `Y888888888888. / _  |~\\ (8\"8b");
	    System.out.println("              ,nnn.. 8888888b.  |  \\ \\m\\|8888P");
	    System.out.println("            ,d8888888888888888b. \\8b|.\\P~ ~P8~");
	   	System.out.println("            888888888888888P~~_~  `8B_|      |");
	   	System.out.println("            ~888888888~'8'   d8.    ~      _/");
	   	System.out.println("             ~Y8888P'   ~\\ | |~|~b,__ __--~");
	   	System.out.println("         --~~\\   ,d8888888b.\\`\\_/ __/~");
	   	System.out.println("              \\_ d88888888888b\\_-~8888888bn.");
	   	System.out.println("                \\8888P   \"Y888888888888\"888888bn.");
	   	System.out.println("            /~'\\_\"__)      \"d88888888P,-~~-~888");
	   	System.out.println("           /  / )   ~\\     ,888888/~' /  / / 8'");
	   	System.out.println("        .-(  / / / |) )-----------(/ ~  / /  |---.");
	   	System.out.println(" ______ | (   '    /_/ Disney Trivia(__/     /   |_______");
	   	System.out.println(" \\      |   (_(_ ( /~      Maze      \\___/_/'    |      /");
	   	System.out.println("  \\     |       Better luck next time pal.       |     /");
	   	System.out.println("  /     (________________________________________)     \\");
	   	System.out.println(" /__________)     __--|~mb  ,g8888b.         (__________\\");
	   	System.out.println("               _/    8888b(.8P\"~'~---__");
	   	System.out.println("              /       ~~~| / ,/~~~~--, `\\");
	   	System.out.println("             (       ~\\,_) (/         ~-_`\\\\");
	    System.out.println("              \\  -__---~._ \\             ~\\\\");
	   	System.out.println("              (           )\\\\              ))\\");
	   	System.out.println("              `\\          )  \"-_           `|\\");
	 	System.out.println("                \\__    __/      ~-__   __--~\\\\");
	   	System.out.println("                   ~~\"~             ~~~\n");
	   	System.out.println();
    }
    
    public static void printGameWon() {
    	System.out.println();
    	System.out.println("                ,n888888n,");
	    System.out.println("               .8888888888b");
	    System.out.println("               888888888888nd8P~''8g,");
	    System.out.println("               88888888888888   _  `'~\\.  .n.");
	    System.out.println("               `Y888888888888. / _  |~\\ (8\"8b");
	    System.out.println("              ,nnn.. 8888888b.  |  \\ \\m\\|8888P");
	    System.out.println("            ,d8888888888888888b. \\8b|.\\P~ ~P8~");
	   	System.out.println("            888888888888888P~~_~  `8B_|      |");
	   	System.out.println("            ~888888888~'8'   d8.    ~      _/");
	   	System.out.println("             ~Y8888P'   ~\\ | |~|~b,__ __--~");
	   	System.out.println("         --~~\\   ,d8888888b.\\`\\_/ __/~");
	   	System.out.println("              \\_ d88888888888b\\_-~8888888bn.");
	   	System.out.println("                \\8888P   \"Y888888888888\"888888bn.");
	   	System.out.println("            /~'\\_\"__)      \"d88888888P,-~~-~888");
	   	System.out.println("           /  / )   ~\\     ,888888/~' /  / / 8'");
	   	System.out.println("        .-(  / / / |) )-----------(/ ~  / /  |---.");
	   	System.out.println(" ______ | (   '    /_/ Disney Trivia(__/     /   |_______");
	   	System.out.println(" \\      |   (_(_ ( /~      Maze      \\___/_/'    |      /");
	   	System.out.println("  \\     |       You won! See you later pal!      |     /");
	   	System.out.println("  /     (________________________________________)     \\");
	   	System.out.println(" /__________)     __--|~mb  ,g8888b.         (__________\\");
	   	System.out.println("               _/    8888b(.8P\"~'~---__");
	   	System.out.println("              /       ~~~| / ,/~~~~--, `\\");
	   	System.out.println("             (       ~\\,_) (/         ~-_`\\\\");
	    System.out.println("              \\  -__---~._ \\             ~\\\\");
	   	System.out.println("              (           )\\\\              ))\\");
	   	System.out.println("              `\\          )  \"-_           `|\\");
	 	System.out.println("                \\__    __/      ~-__   __--~\\\\");
	   	System.out.println("                   ~~\"~             ~~~\n");
	   	System.out.println();
    }
}