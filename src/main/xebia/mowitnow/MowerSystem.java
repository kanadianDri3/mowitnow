package main.xebia.mowitnow;

import java.util.Queue;

import main.xebia.mowitnow.enums.Instruction;
import main.xebia.mowitnow.exceptions.FunctionalException;
import main.xebia.mowitnow.exceptions.LogicalException;
import main.xebia.mowitnow.lawn.Lawn;
import main.xebia.mowitnow.mower.Mower;

public class MowerSystem {
	
	public static final String SYM_MOWER = "o";
	public static final String SYM_NOT_MOWED = "i";
	public static final String SYM_MOWED = "*";
	public static final String SYM_LIMIT_LEFT_RIGHT = "|";
	public static final String SYM_LIMIT_TOP_BOT = "-";
	public static final String SYM_LIMIT_CORNER = "0";
	
	private Lawn lawn;

	public MowerSystem(String uri) {
		MowItFileParser mowItFP = null;
		try {
			mowItFP = new MowItFileParser(uri);
		} catch (LogicalException | FunctionalException e) {
			e.printStackTrace();
		}
		
		this.lawn = new Lawn(mowItFP.getLawnCarac()[0], mowItFP.getLawnCarac()[1]);
		mowItFP.getMowerCarac().forEach((k,v) -> this.lawn.addMower(k));
	}
	
	public MowerSystem(Lawn lawn) {
		this.lawn = lawn;
	}
	
	public void startMowing() {
		
		for (Mower mower : this.lawn.getListMowers()) {
			this.lawn.mowSquare(mower.getPosition());
			final Queue<Instruction> instructions = mower.getInstructions();
			
			while(!instructions.isEmpty()) {
				final Instruction instruction = instructions.poll();
				switch(instruction) {
				case LEFT:
					mower.rotateLeft();
					break;
				case RIGHT:
					mower.rotateRight();
					break;
				case FORWARD:
					mower.moveForward(lawn);
					break;
				default:
					//TODO: throw error: unknown instruction
				}
			}
			System.out.println("final position ----- mower " + mower.getMowerID() + ", x = " + mower.getPosition().getX()
					+ ", y = " + mower.getPosition().getY() + ", orientation = " + mower.getOrientation());
		}
	}
}
