package main.xebia.mowitnow.enums;

import main.xebia.mowitnow.mower.Mower;

public enum Instruction {
	
	LEFT("G"),
	RIGHT("D"),
	FORWARD("A");
	
	private String code;
	
	Instruction(String code) {
		this.code = code;
	}
	
	public static Instruction getInstructionFromCode(String code) {
		if (code == null || code.isEmpty())
			return null;
		
		for (Instruction i : Instruction.values()) {
			if (i.getCode().equals(code))
				return i;
		}
		return null;
	}
	
	public String getCode() {
		return this.code;
	}

	public void turnRight(Mower m) {
		m.rotateRight();
	}
	
	public void turnLeft(Mower m) {
		m.rotateLeft();
	}
	
	public void moveForward(Mower m) {
		
	}
}
