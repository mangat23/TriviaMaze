package maze;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = 2965691316779477347L;

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
		myX--;
	}
	
	public void moveSouth() {
		myX++;
	}
	
	public void moveEast() {
		myY++;
	}
	
	public void moveWest() {
		myY--;
	}
}
