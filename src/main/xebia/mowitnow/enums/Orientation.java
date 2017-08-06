package main.xebia.mowitnow.enums;

public enum Orientation {

	NORTH("N","E","W"),
	EAST("E","S","N"),
	SOUTH("S","W","E"),
	WEST("W","N","S");
	
	private String code;
	
	private String orientationOnRight;
	
	private String orientationOnLeft;
	
	Orientation(String c, String right, String left) {
		code = c;
		orientationOnRight = right;
		orientationOnLeft = left;
	}
	
	public Orientation turnLeft() {
		return getByCode(getOrientationOnLeft());
	}
	
	public Orientation turnRight() {
		return getByCode(getOrientationOnRight());
	}
	
	public static Orientation getByCode(String code) {
		if(code == null)
			return null;
		for(Orientation o : Orientation.values()) {
			if(o.getCode().equals(code))
				return o;
		}
		return null;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getOrientationOnRight() {
		return this.orientationOnRight;
	}
	
	public String getOrientationOnLeft() {
		return this.orientationOnLeft;
	}
}
