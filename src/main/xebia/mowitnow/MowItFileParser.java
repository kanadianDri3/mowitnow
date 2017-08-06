package main.xebia.mowitnow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import main.xebia.mowitnow.enums.Instruction;
import main.xebia.mowitnow.enums.Orientation;
import main.xebia.mowitnow.exceptions.FunctionalException;
import main.xebia.mowitnow.exceptions.LogicalException;
import main.xebia.mowitnow.mower.Mower;
import main.xebia.mowitnow.mower.Position;

public class MowItFileParser {
	
	private static final String REGEX_LAWN_INFO = "\\d \\d*$";
	private static final String REGEX_MOWER_INFO = "\\d \\d [N|E|S|W]";
	private static final String REGEX_MOWER_INSTRUCTION = "[A|D|G]*$";
	private static final String INFO_SEPARATOR = " ";
	
	private String lawnInfo;
	private List<String> mowerInfo;
	private List<String> mowerInstruction;
	
	private int[] lawnCarac = new int[2];
	private Map<Mower, Queue<Instruction>> mowerCarac = new HashMap<>();
	
	public MowItFileParser(String uri) throws LogicalException, FunctionalException {
		
		// use supplier to use the stream several time
		final Supplier<Stream<String>> fileLinesSupplier = () -> {
			try {
				return Files.lines(Paths.get(uri));
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			
		};	
		
		// get first line in file
		lawnInfo = fileLinesSupplier.get().filter(l -> l.matches(REGEX_LAWN_INFO))
				.collect(Collectors.joining());
		
		if (lawnInfo == null || lawnInfo.isEmpty()) {
			throw new FunctionalException("NO LAWN! A problem occured while retrieving the information about the lawn");
		}
		
		mowerInfo = fileLinesSupplier.get().filter(l -> l.matches(REGEX_MOWER_INFO))
				.collect(Collectors.toList());
		
		mowerInstruction = fileLinesSupplier.get().filter(l -> l.matches(REGEX_MOWER_INSTRUCTION))
				.collect(Collectors.toList());
		
		if (mowerInfo == null || mowerInfo.isEmpty()) {
			throw new FunctionalException("NO MOWERS! A problem occured while retrieving the information about the mowers");
		}
		if (mowerInstruction == null || mowerInstruction.isEmpty()) {
			throw new FunctionalException("NO INSTRUCTIONS! A problem occured while retrieving the information about the mowers' instructions");
		}
		if (mowerInfo.size() != mowerInstruction.size()) {
			throw new FunctionalException("The number of mowers is not equal to the number of the list of instructions.\n"
					+ "mowers: " + mowerInfo.size() + ", list of instructions: " + mowerInstruction.size());
		}
		
		extractLawnInfo();
		extractMowerInfo();
	}
	
	private void extractLawnInfo() throws LogicalException {
		final String[] lawnInfoS = lawnInfo.split(INFO_SEPARATOR);
		
		if (lawnInfoS.length != 2) {
			throw new LogicalException("Not enough or too many arguments for the lawn (" + lawnInfoS.length + " args");
		}
		
		//width
		lawnCarac[0] = Integer.parseInt(lawnInfoS[0]);
		
		//height
		lawnCarac[1] = Integer.parseInt(lawnInfoS[1]);
		
		if (lawnCarac[0] < 1 || lawnCarac[1] < 1) {
			throw new LogicalException("lawn should have at least 1 square");
		}
	}
	
	private void extractMowerInfo() {
		for (int i = 0 ; i < mowerInfo.size() ; i++) {
			final String[] mowerInfoS = mowerInfo.get(i).split(INFO_SEPARATOR);
			final int xMower = Integer.parseInt(mowerInfoS[0]);
			final int yMower = Integer.parseInt(mowerInfoS[1]);
			
			if (xMower < 0 || xMower > lawnCarac[0] || yMower < 0 || yMower > lawnCarac[1]) {
				System.err.println("Mower " + i + " has one of his position out-of-bound (" + xMower + "," + yMower + "."
						+ " This mower will not be used");
				continue;
			}
			
			final Position p = new Position(xMower, yMower);
			
			final Orientation o = Orientation.getByCode(mowerInfoS[2]);
			
			final Mower m = new Mower(i, o, p);
			
			final Queue<Instruction> instructions = new LinkedList<>(Arrays.asList(
					mowerInstruction.get(i).split("")).stream()
					.map(inst -> Instruction.getInstructionFromCode(inst))
					.collect(Collectors.toList()));
			
			m.setInstructions(instructions);
			mowerCarac.put(m, instructions);
		}
	}
	
	public int[] getLawnCarac() {
		return this.lawnCarac;
	}
	
	public Map<Mower, Queue<Instruction>> getMowerCarac() {
		return this.mowerCarac;
	}
}
