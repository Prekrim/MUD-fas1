package creaturePack;

import inputPack.Conversation;
import passivePack.Direction;
import exceptionPack.DoorException;
import exceptionPack.WorldException;
import worldPack.Room;

public abstract class Creature{

    // Initial Values
    private String name;
    protected Room location;
	private int movementSpeed;
 
    // Constructor
    public Creature(final String name, Room location, int movementSpeed){
	this.name = name;
	this.location = location;
	this.movementSpeed = movementSpeed;
    }
    
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
    public Room getRoom(){
    	return this.location;
    }

    public String getName(){
    	return this.name;
    }
    
    public String lookUpon(){
    	return "Not implemented yet\n";
    }

	public int getMovementSpeed() {
		return this.movementSpeed;
	}
}

