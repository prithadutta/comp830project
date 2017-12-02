package lostinpandora;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.Scanner;


public class Controller {
	static String input;
	static Scanner scanStr = new Scanner(System.in); //new Scanner to scan input.
	static GameMap map = new GameMap();
	static int currentroomno = 0;
	static String startMsg = "**********" + "\n" + "You have been kidnapped and left in an abandoned building. "
							+ "\n" + "Somehow you found a way to untie yourself. You have to find 7 sub-maps tagged "
							+ "P,A,N,D,O,R,A to get out of the building. "
							+ "\n" + "Find all submaps before the kidnappers return in 10 minutes" + "\n" + "**********";
	static Rooms currentroom;
	static long startTime,queryTime,timeSpent,timeLeft;
	static String timetoPrint;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		welcomeMsg();
	}
	
	//print time left.
	public static void checkTime() {
		queryTime = System.currentTimeMillis();
		timeSpent = (queryTime - startTime);
		timeLeft = 600000 - timeSpent;
		timetoPrint = String.format("%02d min, %02d sec", TimeUnit.MILLISECONDS.toMinutes(timeLeft)
				,TimeUnit.MILLISECONDS.toSeconds(timeLeft) - 
			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeLeft)));
		System.out.println("You have " + timetoPrint + " left!");
		ingameInput();
	}
	
	//handles event for the current room.
	public static void currentRoom() {
		currentroom = map.getRoom(currentroomno);
		System.out.println("You are in "+ currentroom.name);
		System.out.println(currentroom.description);
		ingameInput();
	}
		
		
	//executes when the player's time elapses
	public static void endGame() {
		System.out.println("\n"+"**********Sorry, You have been caught by the kidnappers again. Game ended!**********");
		System.exit(0); //end the entire program.
	}
	
	//prints out all possible commands
	public static void help() {
		System.out.println("\t"+"******************************Help List******************************");
		System.out.println("describe - describes current room");
		System.out.println("exit - leave the building");
		System.out.println("exit east - exit current building to the room on the east");
		System.out.println("exit north - exit current building to the room on the north");
		System.out.println("exit south - exit current building to the room on the south");
		System.out.println("exit west - exit current building to the room on the west");
		System.out.println("my submaps - prints your collected submaps");
		System.out.println("pick submap - add a submap to inventory");
		System.out.println("quit - quits the game");
		System.out.println("scan - scans room for submap");
		System.out.println("timeleft - check how much time you have left");
		System.out.println("use map - reference the map of the bulding");
		System.out.println("\t"+"***************************End of Help List***************************");
		ingameInput();
	}
	
	//method accepting player's input during the game.
	public static void ingameInput() {
		System.out.print("Player> ");
		input = scanStr.nextLine().toLowerCase(); //input holds the value of the players input
		
		if (input.equals("describe")) {
			System.out.println(currentroom.description);
			ingameInput();
		}
		else if (input.equals("exit")) {
			map.exitBulding(currentroom);
		}
		else if (input.equals("exit east")) {
			moveplayerEast();
		}
		else if (input.equals("exit north")) {
			moveplayerNorth();
		}
		else if (input.equals("exit south")) {
			moveplayerSouth();
		}
		else if (input.equals("exit west")) {
			moveplayerWest();
		}
		else if (input.equals("help")) {
			help();
		}
		else if (input.equals("my submaps")) {
			Submaps.printInventory();
			ingameInput();
		}
		else if (input.equals("pick submap")) {
			map.getSubmap(currentroom);
			ingameInput();
		}
		else if (input.equals("quit")) {
			terminate();
		}
		else if (input.equals("scan")) {
			scanRoom();
		}
		else if (input.equals("timeleft")) {
			checkTime();
		}
		else if (input.equals("use map")) {
			map.validateMapprint();
			ingameInput();
		}
		else {
			System.out.println("Command not recognized! Rely on the 'help' command." );
			ingameInput();
		}
	}
	
	//moves player to the east exit of current room
	public static void moveplayerEast() {
		currentroomno = currentroom.exitEast;
		if (currentroomno == 0) {
			System.out.println("There is no exit in that direction");
			ingameInput();
		}
		else {
			currentroomno--;
			currentRoom();
		}	
	}
	
	//moves player to the north exit of current room
	public static void moveplayerNorth() {
		currentroomno = currentroom.exitNorth;
		if (currentroomno == 0) {
			System.out.println("There is no exit in that direction");
			ingameInput();
		}
		else {
			currentroomno--;
			currentRoom();
		}
	}
	
	//moves player to the south exit of current room
	public static void moveplayerSouth() {
		currentroomno = currentroom.exitSouth;
		if (currentroomno == 0) {
			System.out.println("There is no exit in that direction");
			ingameInput();
		}
		else {
			currentroomno--;
			currentRoom();
		}	
	}
	
	//moves player to the west exit of current room
	public static void moveplayerWest() {
		currentroomno = currentroom.exitWest;
		if (currentroomno == 0) {
			System.out.println("There is no exit in that direction");
			ingameInput();
		}
		else {
			currentroomno--;
			currentRoom();
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
		
		
	public static void scanRoom() {
		if (currentroom.hasSubmap.equals(true)) {
			System.out.println("Submap found!");
		}
		else if (currentroom.hasExit.equals(true)) {
			System.out.println("Hidden Exit found!");
		}
		else {
			System.out.println("Empty Room!");
		}	
		ingameInput();
	}
		
	
	//method to start game
	public static void startGame() {
		final Runnable startGameThread = new Thread() {
			@Override 
			public void run() { 
				System.out.println(startMsg);
				map.createRooms();
				startTime = System.currentTimeMillis();
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
		System.out.println("\t"+"**********Welcome to the Lost in Pandora Game**********" +"\n" +
		"Type “start” to begin or “quit” to terminate");
		prebeginInput();
	}
}
