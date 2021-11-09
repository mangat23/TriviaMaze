package model;

public class User {
	
	private int myX;
	
	private int myY;

	public User() {
		myX = 0;
		myY = 0;
	}
	
	public int getX() {
		return myX;
	}
	
	public int getY() {
		return myY;
	}
	
	public void setX(int theNewPosition) {
		myX = theNewPosition;
	}
	
	public void setY(int theNewPosition) {
		myY = theNewPosition;
	}
	
	public void moveNorth() {
		myY--;
	}
	
	public void moveSouth() {
		myY++;
	}
	
	public void moveEast() {
		myX++;
	}
	
	public void moveWest() {
		myX--;
	}
	//move doors?
	
	@Override
	public boolean equals(Object theOther) {
		boolean result = false;
		if(theOther != null && this.getClass() == theOther.getClass()) {
			final User otherUser = (User) theOther;
			result = (myX == otherUser.getX() && myY == otherUser.getY());
		}
		return result;
	}
}
