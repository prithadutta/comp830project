package lostinpandora;

public class Rooms {
	String name;
	String description;
	
	public Rooms(String name, String description) {
		this.name = name;
		this.description = description;	
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription(){
		return description;
	}
}
