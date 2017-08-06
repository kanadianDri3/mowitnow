package test.xebia.mowitnow;

import main.xebia.mowitnow.MowerSystem;
import main.xebia.mowitnow.lawn.Lawn;

public class MowerSystemTest extends MowerSystem {

	public MowerSystemTest(String uri) {
		super(uri);
	}
	
	public MowerSystemTest(Lawn lawn) {
		super(lawn);
	}

}
