package main.xebia.mowitnow.mower;

import main.xebia.mowitnow.enums.Orientation;

public class Position {

	private int x;
	
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position nextPosition(Orientation o) {
		switch(o) {
		case NORTH:
			return new Position(x,y+1);
		case EAST:
			return new Position(x+1,y);
		case SOUTH:
			return new Position(x,y-1);
		case WEST:
			return new Position(x-1,y);
		default:
			return null;
	}
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	@Override
	public int hashCode() {
		final int prime = 29;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Position))
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
