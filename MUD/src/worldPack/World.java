package worldPack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.io.IOException;

import passivePack.Book;
import passivePack.Key;
import exceptionPack.WorldException;
import ioPack.Reader;


public class World{
	private List<Room> rooms = new ArrayList<Room>();
	private String name;
	
	public World(String name, String path){
		this.name = name;
		this.generateWorld(path);
		
	}
	
	public String getName(){
		return this.name;
	}
		
	public void generateWorld(String path){
		Reader worldReader = new Reader(path);
		String[] worldArray = null;
		try {
			worldArray = worldReader.openFile();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
		for(int i=0; i < worldArray.length; i++){
			String currentString = worldArray[i];
			Room newRoom = new Room(currentString);
			rooms.add(newRoom);
		}
		for(int i=0; i< rooms.size();i++) {
			try {
				this.rooms.get(i).addDoors(this.rooms);
			} catch (WorldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
			}
		}
		
	}
	
	public Room getRoom(String roomName) throws WorldException{
		
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
		return targetRoom;
	}

	private int numberOfLockedDoors() {
		int numberOfLocks = 0;
		for(Room room : this.rooms){
			numberOfLocks += room.getLocks();
		}
		return numberOfLocks;
	}
	
	public void createKeys() {
		int keyAmount =  (int) ((this.numberOfLockedDoors() * 1.5 )/2);
		Random randomizer = new Random();
		for(int i = 0;i < keyAmount;i++){
			Key newKey = new Key();
			int numberOfRooms = this.rooms.size();
			int target = randomizer.nextInt(numberOfRooms-1);
			Room targetRoom = this.rooms.get(target);
			targetRoom.addKey(newKey);
		}
	}

	public List<Room> getRooms() {
		return this.rooms;
	}
}


