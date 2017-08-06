package main.xebia.mowitnow.lawn;

import main.xebia.mowitnow.mower.Position;

public class LawnSquare {

	private Position p;
	
	private Boolean mowed;
	
	private Boolean available;
	
	public LawnSquare(Position p) {
		this.p = p;
		mowed = false;
		available = true;
	}
	
	public Position getPosition() {
		return this.p;
	}
	
	public Boolean isMowed() {
		return this.mowed;
	}
	
	public void mow() {
		this.mowed = true;
	}
	
	public Boolean isAvailable() {
		return this.available;
	}
	
	public void unavailable() {
		this.available = false;
	}
	
	public void available() {
		this.available = true;
	}
}
