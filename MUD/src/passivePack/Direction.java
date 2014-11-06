package passivePack;

import exceptionPack.InputException;

public class Direction {
	private int direction;
	
	public Direction(int direction) throws InputException{
		if (direction < 4 && direction >= 0){
		this.direction = direction;
		}else{
			throw new InputException("Invalid direction");
		}
	}
	
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
	
	public boolean equals(Direction direction){
		if(direction == null){ return false; }
		if (this.direction == direction.direction){return true;}
		else{return false;}
		}
	
	public String toString(){
		return this.getDirection();
	}

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
