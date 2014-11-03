package creaturePack;

import worldPack.Room;

public class Creature{

    // Initial Values
    private String name;
    private Room location;
 
    // Constructor
    public Creature(final String name, Room location){
	this.name = name;
	this.location = location;
	
    }
    
    public void walk(String direction){
    	
    }
    
    public Room getRoom(){
    	return this.location;
    }


}

