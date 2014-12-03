package passivePack;

import exceptionPack.InputException;

/**
 * Defines directions to the West, North, East and South
 *
 */
public class Direction {
	private int direction;
	
	/** Constructs a Direction by using an integer.
	 * 0-3 is north-west, respectively.
	 * @param direction The desired cardinal direction.
	 * @throws InputException If the integer falls outside {0,3}.
	 */
	public Direction(int direction) throws InputException{
		if (direction < 4 && direction >= 0){
		this.direction = direction;
		}else{
			throw new InputException("Invalid direction");
		}
	}
	
	/** Constructs a Direction by using a string.
	 * The direction is specified by the strings "north", "east", "south" and "west".
	 * @param direction The desired cardinal direction.
	 * @throws InputException If the string does not match a specified direction.
	 */
	public Direction(String direction) throws InputException{
		switch(direction){
    	case "north":
    		this.direction = 0;
    		break;
    	case "east":
    		this.direction = 1;
    		break;
    	case "south":
    		this.direction = 2;
    		break;
    	case "west":
    		this.direction = 3;
    		break;
    	default:
    		throw new InputException("Invalid direction");
    	}
	}
	/** Gets the string representation of a given direction.
	 * @return The string representation.
	 */
	public String getDirection(){
    	switch(this.direction){
    	case 0:
    		return "North";
    	case 1:
    		return "East";
    	case 2:
    		return "South";
    	case 3:
    		return "West";
    	}
    	System.out.println("Could not translate direction int to string");
    	System.exit(1);
    	return "";
	}
	
	/** Checks if the given direction is equal to this direction.
	 * @param direction The compared object.
	 * @return True if direction is of type Direction and faces the same way as this, else false.
	 */
	public boolean equals(Object direction){
		if(direction == null){ return false; }
		if(! (direction instanceof Direction)){return false;}
		Direction dir = (Direction) direction;
		if (this.direction == dir.direction){return true;}
		else{return false;}
		}
	
	public String toString(){
		return this.getDirection();
	}

	/** Gets the opposite cardinal direction to this direction.
	 * @return The opposite direction.
	 */
	public Direction oppositeDirection() {
		switch (this.toString()){
		case "North":
			try {
				return new Direction("south");
			} catch (InputException e) {
				
			}
			
		case "East":
			try {
				return new Direction("west");
			} catch (InputException e) {
				
			}
			
		case "South":
			try {
				return new Direction("north");
			} catch (InputException e) {
				
			}
			
		case "West":
			try {
				return new Direction("east");
			} catch (InputException e) {
				
			}
		default:
			System.out.println("Error: could not find opposite");
		}
		return null;
	}
	
}
