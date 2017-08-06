package main.xebia.mowitnow.lawn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.xebia.mowitnow.mower.Mower;
import main.xebia.mowitnow.mower.Position;

public class Lawn {

	private HashMap<Position, LawnSquare> board;
	
	private int height;
	
	private int length;
	
	private List<Mower> listMowers;
	
	public Lawn(int length, int height) {
		
		if(height < 1) {
			System.out.println("Height should be at least equal to 1");
			return;
		}
		if(length < 1) {
			System.out.println("Length should be at least equal to 1");
			return;
		}
		
		this.height = height;
		this.length = length;
		
		board = new HashMap<>();
		
		for(int i = 0; i <= height; i++) {
			for(int j = 0; j <= length; j++) {
				final Position p = new Position(i,j);
				board.put(p, new LawnSquare(p));
			}
		}
		
		listMowers = new ArrayList<>();
	}
	
	public void mowSquare(Position p) {
		getLawnSquare(p).unavailable();
		getLawnSquare(p).mow();
	}
	
	public List<Mower> getListMowers() {
		return this.listMowers;
	}
	
	public Boolean addMower(Mower m) {
		return this.listMowers.add(m);
	}
	
	public LawnSquare getLawnSquare(Position p) {
		return this.board.get(p);
	}
}
