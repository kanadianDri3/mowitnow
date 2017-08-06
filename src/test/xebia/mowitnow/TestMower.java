package test.xebia.mowitnow;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import main.xebia.mowitnow.enums.Instruction;
import main.xebia.mowitnow.enums.Orientation;
import main.xebia.mowitnow.lawn.Lawn;
import main.xebia.mowitnow.mower.Mower;
import main.xebia.mowitnow.mower.Position;

public class TestMower {
	
	final Mower m = new Mower(1, Orientation.EAST, new Position(1,1));
	final Lawn l = new Lawn(5,5);
	
	@Test
	public void testLeftTurn() {
		m.rotateLeft();
		Assert.assertEquals(Orientation.NORTH, m.getOrientation());
		m.rotateLeft();
		Assert.assertEquals(Orientation.WEST, m.getOrientation());
		m.rotateLeft();
		Assert.assertEquals(Orientation.SOUTH, m.getOrientation());
		m.rotateLeft();
		Assert.assertEquals(Orientation.EAST, m.getOrientation());
	}
	
	@Test
	public void testRightTurn() {
		m.rotateRight();
		Assert.assertEquals(Orientation.SOUTH, m.getOrientation());
		m.rotateRight();
		Assert.assertEquals(Orientation.WEST, m.getOrientation());
		m.rotateRight();
		Assert.assertEquals(Orientation.NORTH, m.getOrientation());
		m.rotateRight();
		Assert.assertEquals(Orientation.EAST, m.getOrientation());
	}
	
	@Test
	public void testMoveForward() {
		final Queue<Instruction> instructions = new LinkedList<>();
		Instruction[] instructionsArray = {Instruction.FORWARD, Instruction.FORWARD};
		instructions.addAll(Arrays.asList(instructionsArray));
		
		this.m.setInstructions(instructions);
		this.l.addMower(m);
		
		MowerSystemTest mstest = new MowerSystemTest(this.l);
		mstest.startMowing();
		
		Assert.assertEquals(new Position(3,1), m.getPosition());
		Assert.assertEquals(Orientation.EAST, m.getOrientation());
	}
	
	@Test
	public void testOutOfBound() {
		final Queue<Instruction> instructions = new LinkedList<>();
		Instruction[] instructionsArray = {Instruction.FORWARD, Instruction.FORWARD, Instruction.FORWARD,
				Instruction.FORWARD, Instruction.FORWARD, Instruction.FORWARD};
		instructions.addAll(Arrays.asList(instructionsArray));
		
		this.m.setInstructions(instructions);
		this.l.addMower(m);
		
		MowerSystemTest mstest = new MowerSystemTest(this.l);
		mstest.startMowing();
		
		Assert.assertEquals(new Position(5,1), m.getPosition());
		Assert.assertEquals(Orientation.EAST, m.getOrientation());
	}

	@Test
	public void testFile() {
		final Queue<Instruction> instructions = new LinkedList<>();
		Instruction[] instructionsArray = {Instruction.FORWARD, Instruction.FORWARD, Instruction.RIGHT,
				Instruction.FORWARD, Instruction.FORWARD, Instruction.RIGHT, Instruction.FORWARD,
				Instruction.RIGHT, Instruction.RIGHT, Instruction.FORWARD};
		instructions.addAll(Arrays.asList(instructionsArray));
		
		final Mower m2 = new Mower(2, Orientation.EAST, new Position(3,3));
		m2.setInstructions(instructions);
		this.l.addMower(m2);
		
		MowerSystemTest mstest = new MowerSystemTest(this.l);
		mstest.startMowing();
		
		Assert.assertEquals(new Position(5,1), m2.getPosition());
		Assert.assertEquals(Orientation.EAST, m2.getOrientation());
	}
	
}
