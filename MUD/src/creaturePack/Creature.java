package creaturePack;

import inputPack.Conversation;
import passivePack.Direction;
import exceptionPack.DoorException;
import exceptionPack.WorldException;
import worldPack.Room;

/**
 * Defines an abstract creature.
 */
public abstract class Creature{

    private String name;
    protected Room location;
    private int movementSpeed;
 
    /**
     * Constructor for a creature. Is only called by subclasses since Creature is abstract.
     * @param name The name of the creature
     * @param location The location of the creature
     * @param movementSpeed The speed of the creature
     */
    public Creature(final String name, Room location, int movementSpeed){
	this.name = name;
	this.location = location;
	this.movementSpeed = movementSpeed;
    }
    
    /**
     * Moves the creature in direction from its location.
     * @param direction The direction to be move in.
     * @throws WorldException If there is no room in direction.
     * @throws DoorException If the door in direction is locked.
     */
    public void walk(Direction direction) throws WorldException, DoorException{
    	boolean open = false;	
		open = this.location.getDoorFacing(direction).isOpen();	
    	if(open){
    		Room oldLocation = this.location;
   	    	this.location = oldLocation.roomInDirection(direction);
   	   		oldLocation.removeCreature(this);
   	   		this.location.addCreature(this);
    	}
    	else {
    		throw new DoorException("The door is locked");
    	}
    }   

    /**
     * Gets the room where creature is
     * @return The room the creature is in.
     */
    public Room getRoom(){
    	return this.location;
    }

    /**
     * Gets the name of the creature.
     * @return The name of the creature.
     */
    public String getName(){
    	return this.name;
    }
    
    /**
     * Returns a string representation of the creature. Is to be specified by the subclasses.
     * @return The string representation of the creature.
     */
    public String lookUpon(){
    	return "Not implemented yet\n";
    }

    /**
     * Gets the speed of the creature.
     * @return The speed of the creature.
     */
    public int getMovementSpeed() {
        return this.movementSpeed;
    }
}

