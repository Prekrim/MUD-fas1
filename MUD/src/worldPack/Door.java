package worldPack;

import passivePack.Direction;
import exceptionPack.InputException;
import exceptionPack.WorldException;


public class Door{
    private Room destination;
    private Boolean unlocked;
    private Direction direction;
    
    
    public Door(Room dest, String unlocked, int direction) throws WorldException{
    this.destination = dest;
    try {
		this.direction = new Direction(direction);
	} catch (InputException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.exit(1);
	}
	if (unlocked.equals("True")) {this.unlocked = true;}
	else {
		if (unlocked.equals("False")){this.unlocked = false;}
		else{ 
			throw new WorldException("Cannot Create Door, undefined locked state.");
			}
    	}
    }
    
    public void unlock(){
    	if(this.unlocked){
    		return;
    	}
    	this.unlocked = true;
    	try {
			this.destination.getDoorFacing(direction.oppositeDirection()).unlock();
		} catch (WorldException e) {
			System.exit(1);
		}
    }
    
    public boolean isOpen() {
    	return this.unlocked;
    }
    
    public Direction getDirection(){
    	return this.direction;
    }
	public Room getDestination(){
		return this.destination;
	}
}
