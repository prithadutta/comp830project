package lostinpandora;

import java.util.ArrayList;

public class GameMap {
	ArrayList<Rooms> allRooms;
	Rooms P001 = new Rooms("Room P1","This is an empty room surrounded by exits to other rooms on the East and South");
	
	public GameMap(){
		allRooms = new ArrayList<Rooms>();
	}
	
	public void createRooms() {
		allRooms.add(P001);
		//System.out.print(allRooms.get(0).description);
	}

	public Rooms getRoom(int currentroomno) {
		return allRooms.get(currentroomno);
	}
}
