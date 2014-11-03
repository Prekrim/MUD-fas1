package worldPack;

import exceptionPack.WorldException;


public class Door{
    Room destination;
    Boolean unlocked;
    
    
    public Door(Room dest, String unlocked) throws WorldException{
    this.destination = dest;
	if (unlocked.equals("True")) {this.unlocked = true;}
	else {
		if (unlocked.equals("False")){this.unlocked = false;}
		else{ 
			throw new WorldException("Cannot Create Door, undefined locked state.");
			}
    	}
    }
}
