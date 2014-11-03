package worldPack;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import creaturePack.Creature;
import creaturePack.Player;
import exceptionPack.WorldException;

public class Room{
    private String name;
    private List<Door> doors = new ArrayList<Door>();
    private List<String> loot = new ArrayList<String>();
    private List<Creature> creatures = new ArrayList<Creature>();
    private String[] data;

    public Room(String name, List<Door> doors, List<String> loot, List<Creature> creatures){
	this.name = name;
	this.doors = doors;
	this.loot = loot;
	this.creatures = creatures;
    }
    
    public Room(String raw){
    	String[] parts = raw.split(";");
    	this.name = parts[0];
    	this.data = parts;
    }
    
    public boolean isRoom(String name) {
    	return (this.name.equals(name));
    }
    
    public boolean equals(Room room){
    	return this.name.equals(room.name);
    }
    
    public String toString() {
    	return this.name;
    }
    
    public void addDoors(List<Room> rooms) throws WorldException{
    	for(int i = 1;i <=4;i++){
    		String roomName = this.data[i];
    		if (!roomName.equals("X")){
    		
    		//Start - - - - - 
    		Iterator<Room> itr = rooms.iterator();
    		Room targetRoom = null;
    		while(itr.hasNext())
    		{
    			Room currentRoom = itr.next();
    			if (currentRoom.isRoom(roomName)){
    			targetRoom = currentRoom;
    			}
    		}
    		if(targetRoom == null) {
    			throw new WorldException("Couldn't find room in list");
    		}    		
    		
    		//Stop - - - - - -
    		String lockedState = this.data[4+i];
    		if(!lockedState.equals("X")) {
    			Door newDoor = new Door(targetRoom,lockedState);
    			doors.add(newDoor);
    			}
    		}
    	}
    }

	public void addCreature(Creature newCreature) {
		this.creatures.add(newCreature);
		
	}
}
