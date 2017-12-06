package lostinpandora;

import java.util.ArrayList;
import java.util.HashMap;
public class GameMap {
	static ArrayList<Rooms> allRooms;
	static HashMap<Rooms,String> roomsubmaps = new HashMap<Rooms,String>(); 
	
	static Rooms P01 = new Rooms("Room P01","This is a room which has exits to room"
			+ " P11 on the South and P02 on the East",0,4,0,2,false);
	static Rooms P02 = new Rooms("Room P02","This is a room which has exits to room"
			+ " P12 on the South, P01 on the West, and P03 on the East",0,5,1,3,true);
	static Rooms P03 = new Rooms("Room P03","This is a room which has exits to room"
			+ " P13 on the South and P02 on the West",0,6,2,0,false);
	static Rooms P11 = new Rooms("Room P11","This is a room which has exits to room"
			+ " P01 on the North, P21 on the South and P12 on the East",1,7,0,5,true);
	static Rooms P12 = new Rooms("Room P12","This is a room which has exits to room"
			+ " P02 on the North, P22 on the South and P11 on the West and P13 on the East",2,8,4,6,false);
	static Rooms P13 = new Rooms("Room P13","This is a room which has exits to room"
			+ " P03 on the North, P23 on the South, P12 on the West",3,9,5,0,true);
	static Rooms P21 = new Rooms("Room P21","This is a room which has exits to room"
			+ " P11 on the North, P31 on the South and P22 on the East",4,10,0,8,true);
	static Rooms P22 = new Rooms("Room P22","This is a room which has exits to room"
			+ " P12 on the North, P32 on the South, P21 on the West and P23 on the East",5,11,7,9,true);
	static Rooms P23 = new Rooms("Room P23","This is a room which has exits to room"
			+ " P13 on the North, P33 on the South, and P22 on the West",6,12,8,0,false);
	static Rooms P31 = new Rooms("Room P31","This is a room which has exits to room"
			+ " P21 on the North, and P32 on the East",7,0,0,11,true);
	static Rooms P32 = new Rooms("Room P32","This is a room which has exits to room"
			+ " P22 on the North, P31 on the West, and P33 on the East",8,0,10,12,false);
	static Rooms P33 = new Rooms("Room P33","This is a room which has exits to room"
			+ " P23 on the North, and P32 on the West",9,0,11,0,true);
	Submaps P = new Submaps("P");
	Submaps A = new Submaps("A");
	Submaps N = new Submaps("N");
	Submaps D = new Submaps("D");
	Submaps O = new Submaps("O");
	Submaps R = new Submaps("R");
	Submaps A2 = new Submaps("A");
	static Boolean foundexit = false;

	public GameMap(){
		allRooms = new ArrayList<Rooms>();
	}
	
	public void createRooms() {
		allRooms.add(P01);
		allRooms.add(P02);
		allRooms.add(P03);
		allRooms.add(P11);
		allRooms.add(P12);
		allRooms.add(P13);
		allRooms.add(P21);
		allRooms.add(P22);
		allRooms.add(P23);
		allRooms.add(P31);
		allRooms.add(P32);
		allRooms.add(P33);
		roomsubmaps.put(P02, P.name);
		roomsubmaps.put(P11, A.name);
		roomsubmaps.put(P13, N.name);
		roomsubmaps.put(P21, D.name);
		roomsubmaps.put(P22, O.name);
		roomsubmaps.put(P31, R.name);
		roomsubmaps.put(P33, A2.name);
	}
	
	public void exitBulding(Rooms currentroom) {
		if (currentroom.hasExit.equals(true)) {
			System.out.println("You are now out of Pandora");
			System.out.println("\t"+"******************************Congratlations! You Won!!******************************");
			Controller.score();
			System.out.println("\t"+"************************************Game Ended!!!************************************");
			System.exit(0);
		}
		else {
			System.out.println("No exit here");
			Controller.ingameInput();
		}
	}
	
	public static void showhiddendoor() {
		foundexit = true;
		P13.hasExit = true;
	}
	
	public Rooms getRoom(int currentroomno) {
		return allRooms.get(currentroomno);
	}
	
	public void getSubmap(Rooms currentroom) {
		if (currentroom.hasSubmap.equals(true)) {
			String submapgotten = roomsubmaps.get(currentroom);
			System.out.println("Submap <" + submapgotten + "> has been added to your inventory");
			currentroom.hasSubmap = false;
			Submaps.addsubmaptoInventory(submapgotten);
		}
		else {
			System.out.println("No submap to pick");
		}
	}
	
	public static void printMap() {
		
		String[][] mappoint = new String[4][3];
		int index = 0;
	    for(int i =0; i <= 3; i++){
	      for(int j =0; j <= 2; j++){
	    	  mappoint[i][j] = allRooms.get(index).name;
	    	  index++;
	      }
	  }
	    System.out.println("");
	    System.out.println("\t"+"******************************Pandora Blueprint******************************");
	    for(int i=0;i<=3;i++)
        {
            for(int j=0;j<=2;j++)
            {	
            	System.out.print("\t"+"\t"+"|" + mappoint[i][j]+ "|");
            }
            System.out.println();
	}
	    System.out.println("\t"+"**************************End of Pandora Blueprint**************************");
	}
	
	public void validateMapprint(){
		if (foundexit.equals(true)) {
			printMap();
		}
		else {
			System.out.println("You don't have a map to use");
		}
	}
}
