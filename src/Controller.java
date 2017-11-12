package lostinpandora;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.Scanner;
import java.util.Set;


public class Controller {
	static String input;
	static Scanner scanStr = new Scanner(System.in); //new Scanner to scan input.
	static HashMap<String, String> helplist = new HashMap<String, String>(); //holds the list of commands
	static GameMap map = new GameMap();
	static int currentroomno = 0;
	static String startMsg = "**********" + "\n" + "You have been kidnapped and left in an abandoned building. "
							+ "\n" + "Somehow you found a way to untie yourself. You have to find 7 sub-maps tagged "
							+ "P,A,N,D,O,R,A to get out of the building. "
							+ "\n" + "Find all submaps before the kidnappers return in 10 minutes" + "\n" + "**********";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		welcomeMsg();
	}
	
	//handles event for the current room.
		public static void currentRoom() {
			Rooms currentroom = map.getRoom(currentroomno);
			System.out.println("You are in "+ currentroom.name);
			System.out.println(currentroom.description);
			ingameInput();
		}
		
		
	//executes when the player's time elapses
	public static void endGame() {
		System.out.println("\n"+"**********You have been caught by the kidnappers again. Game ended!**********");
		System.exit(0); //end the entire program.
	}
	
	//fills the hashmap with the commands + description and pulls out the list
	public static void help() {
		helplist.put("quit","quits the game");
		 
		//iterates over the Hashmap and prints out a formatted content.
		Set<Entry<String, String>> set = helplist.entrySet();
		    Iterator<Entry<String, String>> it = set.iterator();
		    while (it.hasNext()) {
		      @SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) it.next();
		      System.out.println(entry.getKey() + " - " + entry.getValue());
		    }
		ingameInput();
	}
	
	//method accepting player's input during the game.
	public static void ingameInput() {
		System.out.print("Player> ");
		input = scanStr.nextLine().toLowerCase(); //input holds the value of the players input
		
		if (input.equals("quit")) {
			terminate();
		}
		else if (input.equals("help")) {
			help();
		}
		else {
			System.out.println("Command not recognized! Rely on the 'help' command." );
			ingameInput();
		}
	}
	
	//method accepting player's input before the game begins.
		public static void prebeginInput() {
			System.out.print("User> ");
			input = scanStr.nextLine().toLowerCase(); //input holds the value of the players input
			
			if (input.equals("start")) {
				startGame(); 
			}
			else if (input.equals("quit")) {
				terminate();
			}
			else {
				System.out.println("Command not recognized! Start or quit." );
				prebeginInput();
			}
		}
		
	//method to start game
	public static void startGame() {
		final Runnable startGameThread = new Thread() {
			@Override 
			public void run() { 
				System.out.println(startMsg);
				map.createRooms();
				currentRoom();	
			}
		};
			
		//creates a new async task executor
		final ExecutorService myExecutor = Executors.newSingleThreadExecutor();
		final Future future = myExecutor.submit(startGameThread); //sends the thread to to executor
		myExecutor.shutdown(); // maintains only current task.

		try { 
			future.get(600, TimeUnit.SECONDS);  //gives the player 10 minutes to finish the game.
		}
		catch (ExecutionException ExecutionException) { 
		}
		catch (InterruptedException InterruptedException) { 
		}
		catch (TimeoutException TimeoutException) { 
			endGame(); 
		}
		if (!myExecutor.isTerminated())
			myExecutor.shutdownNow();
	}
	
	//method to quit game by closing the scanner so player can't input text
	public static void terminate() {
		scanStr.close();
		System.out.println("You quit! Game Ended!");
	}
	
	//Prints Game's welcome message
	public static void welcomeMsg() {
		System.out.println("**********Lost in Pandora**********" +"\n" +
		"Type “start” to begin or “quit” to terminate");
		prebeginInput();
	}
}
