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

/*
 * Defines a Room.
 */
public class Room{
    private String name;
    private List<Door> doors = new ArrayList<Door>();
    private List<Loot> loot = new ArrayList<Loot>();
    private List<Creature> creatures = new ArrayList<Creature>();
    private String[] data;

    /*
     * Creates a Room.
     * @param name The name of the room.
     * @param doors The doors from the room.
     * @param loot The loot in the room.
     * @param creatures The creatures in the room.
     * @param data The formated data for the room.
     */
    public Room(String name, List<Door> doors, List<Loot> loot, List<Creature> creatures, String[] data){
	this.name = name;
	this.doors = doors;
	this.loot = loot;
	this.creatures = creatures;
	this.data = data;
    }
    
    /*
     * Creates a Room from a raw string consisting of ";" separated arguments. raw should contain the name of the room, the room you would end up in if you walked in each cardinal direction (North,East,South,West) and state if the door in each direction is unlocked or locked ("True" if unlocked, else "False"). If a destination is not desired in some direction that destination and locked state should be "X".
     * @param raw The raw string formated as: "name;destinationToTheNorth;destinationToTheEast;destinationToTheSouth;destinationToTheWest;lockNorth;lockEast;lockSouth;lockWest".
     */
    public Room(String raw){
    	String[] parts = raw.split(";");
    	this.name = parts[0];
    	this.data = parts;
    }
    
    /*
     * Checks if the Room has the name name.
     * @param name The name to compare with.
     * @return True if room has name as name, else false.
     */
    public boolean isRoom(String name) {
    	return (this.name.equals(name));
    }
   
    public boolean equals(Object obj){
	if(!(obj instanceof Room)){ return false; }
	Room room = (Room) obj;
    	return this.name.equals(room.name);
    }
    
    /*
     * Checks if the room has a door in the given direction.
     * @param direction The direction to check.
     * @return True if the room has a door in direction, else false.
     */
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
    
    /*
     * Gets the door in the given direction.
     * @param direction The direction to get from.
     * @return The door in the direction.
     * @throws WorldException If there is no door in direction.
     */
    public Door getDoorFacing(Direction direction) throws WorldException{
    	for(int i = 0;i < this.doors.size();i++){
    		Door currentDoor = this.doors.get(i);
			if(currentDoor.getDirection().equals(direction)){
				return currentDoor;
			}
    	}
    	throw new WorldException("No door in direction: "+ direction);
    }
    
    /*
     * Gets the room in the given direction.
     * @param direction The direction to get from.
     * @return The Room in direction.
     * @throws WorldException If there is no room in direction.
     */
    public Room roomInDirection(Direction direction) throws WorldException{
    	if (this.hasDoor(direction)){
    		Door targetDoor = null;
    		try {
    			targetDoor = this.getDoorFacing(direction);
    			return targetDoor.getDestination();
    		} catch (WorldException e){
    			e.printStackTrace();
    			System.exit(1);
    		}
    	}
    	throw new WorldException("No room in direction");
    }
    /*
     * Gets the doors in the room as a list.
     * return The doors in the room.
     */
    public List<Door> getDoors(){
    	return this.doors;
    }

    /*
     * Adds doors to the room using the data sent when creating a room. The destinations in the data must match the rooms' names in rooms.
     * @param rooms A list of rooms.
     * @throws WorldException If the room has insufficient data or the rooms in data couldn't be found in rooms.
     */
    public void addDoors(List<Room> rooms) throws WorldException{
    	if(this.data.length < 9){
    		throw new WorldException("Insufficient data");
    	}
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

    /*
     * Adds a creature to the room
     * @param newCreature The creature to be added.
     */
	public void addCreature(Creature newCreature) {
		this.creatures.add(newCreature);
		
	}

    /*
     * Removes a creature from the room
     * @param creature The creature to be removed.
     */
	public void removeCreature(Creature creature) {
		this.creatures.remove(creature);
		
	}

    /*
     * Gets the number of locked doors in the room.
     * @return the number of locked doors.
     */
	protected int getLocks() {
		int locks = 0;
		for(Door door : this.doors) {
			if(!door.isOpen()){
				locks++;
			}
		}
		return locks;
	}

    /*
     * Gets the loot in the room as a list.
     * @return the loot in the room.
     */
	public List<Loot> getLoot() {
		return this.loot;
	}

    /*
     * Removes loot from the room.
     * @param loot the loot to be removed.
     */
	public void removeLoot(Loot loot) {
		this.loot.remove(loot);
	}

    /*
     * Adds loot to the room.
     * @param loot the loot to be added.
     */
	public void addLoot(Loot loot) {
		this.loot.add(loot);
	}

    /*
     * Gets the loot in the room as a list
     * @return the loot in the room
     */
	public List<Creature> getCreatures() {
		return this.creatures;
	}

    /*
     * Gets the creature with the name name in the room.
     * @param name The name of the creature
     * @return The creature with corresponding name.
     * @throws WorldException If there is no creature with name in the room.
     */	
	public Creature getCreature(String name) throws WorldException{
		for (Creature creature: this.creatures){
			if (creature.getName().equalsIgnoreCase(name)){
				return creature;
			}
		}
		throw new WorldException("No such creature in room");
	}

    /*
     * Gets the name of the room.
     * @return the name of the room
     */	
	public String getName(){
		return this.name;
	}
}
