package model;

public class Room {
	
	private Door myNorth;
	private Door mySouth;
	private Door myEast;
	private Door myWest;
	
	//figure out border rooms
	public Room() {
		myNorth = new Door();
		mySouth = new Door();
		myEast = new Door();
		myWest = new Door();
	}
	
	public Door getNorth() {
		return myNorth;
	}
	public Door getSouth() {
		return mySouth;
	}
	public Door getEast() {
		return myEast;
	}
	public Door getWest() {
		return myWest;
	}
	public void setNorth(Door theNorth) {
		myNorth = theNorth;
	}
	public void setSouth(Door theSouth) {
		mySouth = theSouth;
	}
	public void setEast(Door theEast) {
		myEast = theEast;
	}
	public void setWest(Door theWest) {
		myWest = theWest;
	}
	
	public boolean isRoomLocked() {
		boolean result = false;
		if(myNorth.isDoorLocked() && mySouth.isDoorLocked() && myWest.isDoorLocked() 
				&& myEast.isDoorLocked()) {
			result = true;
		}
		return result;
	}
	
	public String roomStatus() {
		StringBuilder sb = new StringBuilder();
		sb.append("Current room:");
		sb.append("\n");
		sb.append("NORTH: " + myNorth.isDoorLocked());
		sb.append("\n");
		sb.append("SOUTH: " + mySouth.isDoorLocked());
		sb.append("\n");
		sb.append("WEST: " + myWest.isDoorLocked());
		sb.append("\n");
		sb.append("EAST: " + myEast.isDoorLocked());
		return sb.toString();
	}
}
