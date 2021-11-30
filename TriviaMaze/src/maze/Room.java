package maze;

import java.io.Serializable;

public class Room implements Serializable{
	
	private static final long serialVersionUID = -3573322487771035478L;
	private Door myNorth;
	private Door mySouth;
	private Door myEast;
	private Door myWest;
	
	public Room() {
		myNorth = new Door();
		mySouth = new Door();
		myEast = new Door();
		myWest = new Door();
	}
	
	public Door getDoor(String theDoor) {
		Door door = null;
		if(theDoor.equals("north")) {
			door = myNorth;
		}
		if(theDoor.equals("south")) {
			door = mySouth;
		}
		if(theDoor.equals("west")) {
			door = myWest;
		}
		if(theDoor.equals("east")) {
			door = myEast;
		}
		return door;
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
		if(((!myNorth.getDoorLocked() &&  myNorth.getQuestionAnswered()) || myNorth.getWall())
			&& ((!mySouth.getDoorLocked() &&  mySouth.getQuestionAnswered()) 
					|| mySouth.getWall()) 
			&& ((!myWest.getDoorLocked() &&  myWest.getQuestionAnswered()) 
					|| myWest.getWall()) 
			&& ((!myEast.getDoorLocked()  &&  myEast.getQuestionAnswered()) 
					|| myEast.getWall())) {
			result = true;
		}
		return result;
	}
	
	public String roomStatus() {
		StringBuilder sb = new StringBuilder();
		sb.append("Current room:");
		sb.append("\n");
		sb.append("NORTH: ");
		if(myNorth.getDoorLocked()) {
			sb.append("OPEN");
		}else {
			sb.append("CLOSED");
		}
		sb.append("\n");
		sb.append("SOUTH: ");
		if(mySouth.getDoorLocked()) {
			sb.append("OPEN");
		}else {
			sb.append("CLOSED");
		}
		sb.append("\n");
		sb.append("WEST: ");
		if(myWest.getDoorLocked()) {
			sb.append("OPEN");
		}else {
			sb.append("CLOSED");
		}
		sb.append("\n");
		sb.append("EAST: ");
		if(myEast.getDoorLocked()) {
			sb.append("OPEN");
		}else {
			sb.append("CLOSED");
		}
		sb.append("\n");
		return sb.toString();
	}
}
