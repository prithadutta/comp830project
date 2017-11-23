package lostinpandora;

public class Rooms {
	String name,description;
	int exitNorth, exitSouth,exitWest,exitEast;
	Boolean hasSubmap;
	Boolean hasExit;
	
	public Rooms(String name, String description, int exitNorth, int exitSouth,int exitWest,int exitEast, 
			Boolean hasSubmap) {
		this.name = name;
		this.description = description;
		this.exitNorth = exitNorth;
		this.exitSouth = exitSouth;
		this.exitWest = exitWest;
		this.exitEast = exitEast;
		this.hasSubmap = hasSubmap;
		this.hasExit = false;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public int getNorthExit(){
		return exitNorth;
	}
	public int getSouthExit(){
		return exitSouth;
	}
	public int getWestExit(){
		return exitWest;
	}
	public int getEastExit(){
		return exitEast;
	}
}
