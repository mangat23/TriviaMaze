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
	
	public Door getDoor(final String theDoor) {
		Door door = null;
		if(theDoor.equals("north")) {
			door = myNorth;
		}
		else if(theDoor.equals("south")) {
			door = mySouth;
		}
		else if(theDoor.equals("west")) {
			door = myWest;
		}
		else if(theDoor.equals("east")) {
			door = myEast;
		}
		return door;
	}
	
	public void setNorth(final Door theNorth) {
		myNorth = theNorth;
	}
	
	public void setSouth(final Door theSouth) {
		mySouth = theSouth;
	}
	
	public void setEast(final Door theEast) {
		myEast = theEast;
	}
	
	public void setWest(final Door theWest) {
		myWest = theWest;
	}
	
	public boolean isRoomLocked() {
		return (((!myNorth.getDoorLocked() &&  myNorth.getQuestionAnswered()) 
						|| myNorth.getWall())
				&& ((!mySouth.getDoorLocked() &&  mySouth.getQuestionAnswered()) 
						|| mySouth.getWall()) 
				&& ((!myWest.getDoorLocked() &&  myWest.getQuestionAnswered()) 
						|| myWest.getWall()) 
				&& ((!myEast.getDoorLocked()  &&  myEast.getQuestionAnswered()) 
						|| myEast.getWall()));
	}
	
	public String roomStatus() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nCurrent room:");
		final Door [] doors = {myNorth, mySouth, myWest, myEast};
		final String[] doorString = {"NORTH: ", "SOUTH: ", "WEST: ", "EAST: "};
		
		for(int i =0; i<doors.length; i++) {
			sb.append("\n");
			sb.append(doorString[i]);
			if(doors[i].getDoorLocked()) {
				sb.append("OPEN");
			}
			else if(!doors[i].getDoorLocked() && doors[i].getQuestionAnswered()) {
				sb.append("CLOSED");
			} 
			else if(doors[i].getWall()) {
				sb.append("WALL");
			}
			else if(!doors[i].getDoorLocked() && !doors[i].getQuestionAnswered() && 
					!doors[i].getWall()) {
				sb.append("AVAILABLE");
			}
		}
		
		sb.append("\n");
		return sb.toString();
	}
}
