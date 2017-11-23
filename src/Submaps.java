package lostinpandora;

import java.util.ArrayList;
import java.util.Collections;

public class Submaps {
	String name;
	
	static ArrayList<String> allsubmaps = new ArrayList<String>();
	static ArrayList<String> myfoundsubmaps = new ArrayList<String>();
	
	public Submaps(String name) {
		this.name = name;
		allsubmaps.add(this.name);
	}
	
	public static void addsubmaptoInventory(String foundsubmap) {
		myfoundsubmaps.add(foundsubmap);
		Collections.sort(myfoundsubmaps);
		Collections.sort(allsubmaps);
		if (myfoundsubmaps.equals(allsubmaps)) {
			System.out.println("Congrats! You now have the complete map.");
			GameMap.printMap();
			System.out.println("The Exit is a hidden door at Room P13, " + "\n"
					+ "go to Room P13 and enter the 'exit' command to leave the building");
		GameMap.showhiddendoor();
		}
		else {
			
		}
	}
	public static void printInventory() {
		if (myfoundsubmaps.size() == 0) {
			System.out.println("You don't have any submap in your inventory");
		}
		else{
			System.out.println("Found Submaps:" + myfoundsubmaps);
		}
	}
	
}
