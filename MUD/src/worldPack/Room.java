package worldPack;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import passivePack.Book;
import passivePack.Direction;
import passivePack.Key;
import passivePack.Loot;
import creaturePack.Creature;
import creaturePack.Player;
import exceptionPack.WorldException;

public class Room{
    private String name;
    private List<Door> doors = new ArrayList<Door>();
    private List<Loot> loot = new ArrayList<Loot>();
    private List<Creature> creatures = new ArrayList<Creature>();
    private String[] data;

    public Room(String name, List<Door> doors, List<Loot> loot, List<Creature> creatures){
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
    
    public boolean hasDoor(Direction direction){
    	for(int i = 0;i < this.doors.size();i++){
    		Door currentDoor = this.doors.get(i);
			if(currentDoor.getDirection().equals(direction)){
				return true;
			}
    	}
    	return false;
    }
    
    public String toString() {
    	return this.name;
    }
    
    public Door getDoorFacing(Direction direction) throws WorldException{
    	for(int i = 0;i < this.doors.size();i++){
    		Door currentDoor = this.doors.get(i);
			if(currentDoor.getDirection().equals(direction)){
				return currentDoor;
			}
    	}
    	throw new WorldException("No door in direction: "+ direction);
    }
    
    public Room roomInDirection(Direction direction) throws WorldException{
    	if (this.hasDoor(direction)){
    		Door targetDoor = null;
    		try {
    			targetDoor = this.getDoorFacing(direction);
    			return targetDoor.getDestination();
    		} catch (WorldException e){
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			System.exit(1);
    		}
    	}
    	throw new WorldException("No room in direction");
    }
    
    public List<Door> getDoors(){
    	return this.doors;
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
    		int direction = i - 1;
    		if(!lockedState.equals("X")) {
    			Door newDoor = new Door(targetRoom,lockedState, direction);
    			doors.add(newDoor);
    			}
    		}
    	}
    }

	public void addKey(Key newKey) {
		this.loot.add(newKey);
	}
    
	public void addCreature(Creature newCreature) {
		this.creatures.add(newCreature);
		
	}

	public void removeCreature(Creature creature) {
		this.creatures.remove(creature);
		
	}

	protected int getLocks() {
		int locks = 0;
		for(Door door : this.doors) {
			if(!door.isOpen()){
				locks++;
			}
		}
		return locks;
	}

	public List<Loot> getLoot() {
		return this.loot;
	}

	public void removeLoot(Loot loot) {
		this.loot.remove(loot);
	}

	public void addLoot(Loot loot) {
		this.loot.add(loot);
	}

	public List<Creature> getCreatures() {
		return this.creatures;
	}
}
