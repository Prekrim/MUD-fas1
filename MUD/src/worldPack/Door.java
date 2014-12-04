package worldPack;

import passivePack.Direction;
import exceptionPack.InputException;
import exceptionPack.WorldException;

/**
 * Defines a "one-way" door.
 * A door can be locked or unlocked and has a cardinal direction as well as a destination.
 */
public class Door{
    private Room destination;
    private Boolean unlocked;
    private Direction direction;
    
    /**
     * Contructs a Door.
     * @param dest The destination of the door.
     * @param unlocked Sets the door's locked status. Must be "True" or "False".
     * @param direction The direction of the door. Must be between 0 and 3 where 0 is North, 1 is East and so on.
     * @throws WorldException If unlocked is not "True" or "False".
     */
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
    
    /**
     * Unlocks the door.
     */
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
    /*
     * Checks if the door is unlocked.
     * @return True if unlocked, else false.
     */
    public boolean isOpen() {
    	return this.unlocked;
    }
    
    /*
     * Gets the cardinal direction of the door.
     * @return the direction of the door.
     */
    public Direction getDirection(){
    	return this.direction;
    }

    /*
     * Gets the destination of the door as a Room.
     * @return The destination.
     */
    public Room getDestination(){
	return this.destination;
    }
}
