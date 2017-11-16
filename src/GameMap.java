package lostinpandora;

import java.util.ArrayList;
public class GameMap {
	ArrayList<Rooms> allRooms;
	Rooms roomtoleave;
	Rooms roomtogo;
	Rooms P01 = new Rooms("Room P1","This is a room which has exits to room"
			+ " P11 on the South and P2 on the East",0,4,0,2);
	Rooms P02 = new Rooms("Room P2","This is a room which has exits to room"
			+ " P12 on the South, P1 on the West, and P3 on the East",0,5,1,3);
	Rooms P03 = new Rooms("Room P3","This is a room which has exits to room"
			+ " P13 on the South and P2 on the West",0,6,2,0);
	Rooms P11 = new Rooms("Room P11","This is a room which has exits to room"
			+ " P1 on the North, P21 on the South and P12 on the West",1,7,0,5);
	Rooms P12 = new Rooms("Room P12","This is a room which has exits to room"
			+ " P2 on the North, P22 on the South and P11 on the West and P13 on the East",2,8,4,6);
	Rooms P13 = new Rooms("Room P13","This is a room which has exits to room"
			+ " P3 on the North, P23 on the South, P12 on the West",3,9,5,0);
	Rooms P21 = new Rooms("Room P21","This is a room which has exits to room"
			+ " P11 on the North, P31 on the South and P22 on the East",4,10,0,8);
	Rooms P22 = new Rooms("Room P22","This is a room which has exits to room"
			+ " P12 on the North, P32 on the South, P21 on the West and P23 on the East",5,11,7,9);
	Rooms P23 = new Rooms("Room P23","This is a room which has exits to room"
			+ " P13 on the North, P33 on the South, and P22 on the West",6,12,8,0);
	Rooms P31 = new Rooms("Room P31","This is a room which has exits to room"
			+ " P21 on the North, and P32 on the East",7,0,0,11);
	Rooms P32 = new Rooms("Room P32","This is a room which has exits to room"
			+ " P22 on the North, P31 on the West, and P33 on the East",8,0,10,12);
	Rooms P33 = new Rooms("Room P33","This is a room which has exits to room"
			+ " P23 on the North, and P32 on the West",9,0,11,0);
	
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
	}

	public Rooms getRoom(int currentroomno) {
		return allRooms.get(currentroomno);
	}
}
