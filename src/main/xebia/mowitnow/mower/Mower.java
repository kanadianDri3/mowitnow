package main.xebia.mowitnow.mower;

import java.util.LinkedList;
import java.util.Queue;

import main.xebia.mowitnow.enums.Instruction;
import main.xebia.mowitnow.enums.Orientation;
import main.xebia.mowitnow.lawn.Lawn;

public class Mower {

	private int mowerID;
	
	private Position position;
	
	private Orientation orientation;
	
	private Queue<Instruction> instructions;
	
	public Mower(int id, Orientation o, Position p) {
		this.mowerID = id;
		this.orientation = o;
		this.position = p;
		this.instructions = new LinkedList<>();
	}
	
	public void rotateRight() {
		orientation = orientation.turnRight();
	}
	
	public void rotateLeft() {
		orientation = orientation.turnLeft();
	}
	
	public void moveForward(Lawn lawn) {
		Position p = position.nextPosition(orientation);
		
		if (lawn.getLawnSquare(p) != null && lawn.getLawnSquare(p).isAvailable()) {
			lawn.getLawnSquare(position).available();
			position = p;
			lawn.mowSquare(p);
		}
	}
	
	public int getMowerID() {
		return this.mowerID;
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	public Orientation getOrientation() {
		return this.orientation;
	}
	
	public void setInstructions(Queue<Instruction> listI) {
		this.instructions = listI;
	}
	
	public Queue<Instruction> getInstructions() {
		return this.instructions;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Mower))
			return false;
		if (this == obj)
			return true;
		
		Mower m = (Mower) obj;
		if (mowerID != m.mowerID)
			return false;
		if (position != m.position)
			return false;
		if (orientation != m.orientation)
			return false;
		
		return true;
	}
}
