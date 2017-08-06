package main.xebia.mowitnow;

public class MowItMain {

	public static void main(String[] args) {
		MowerSystem ms = new MowerSystem("resources/mowit.txt");
		
		System.out.println("File parsed. Ready to mow");
		
		ms.startMowing();
	}

}
