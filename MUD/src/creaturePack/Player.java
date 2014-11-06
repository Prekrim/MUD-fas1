package creaturePack;

import java.util.List;
import java.util.ArrayList;

import exceptionPack.*;
import passivePack.Book;
import passivePack.Direction;
import passivePack.Key;
import passivePack.Loot;
import worldPack.Door;
import worldPack.Room;

public class Player extends Student{
	
	public Player(String name, Room location) {
		super(name, 60, location);
		
	}
	
	public void throwBook(Book book){
		if (this.backpack.contains(book)){
			backpack.remove(book);
			this.location.addLoot(book);
		}
	}
	
	public void useKey(Direction direction){
		Door targetDoor;
		try {
			targetDoor = location.getDoorFacing(direction);
			if (!targetDoor.isOpen()){
				try {
					Key key = this.backpack.getKey();
					targetDoor.unlock();
					this.backpack.remove(key);
					System.out.println("Door unlocked!");
				} catch (InventoryException e) {
					System.out.println("A key is needed to unlock this door");
					return;
				}
			} else{
					System.out.println("Door is already open");
			}
		} catch (WorldException e) {
			System.out.println("No door to the " + direction);
		}
	}

	public void lookAround(){
		
		System.out.println("You are in room " + this.location.toString() +".");
		List<Door> doorList = this.location.getDoors();
		for(int i = 0;i < doorList.size();i++){
			Door currentDoor = doorList.get(i);
			System.out.println("To the " + currentDoor.getDirection() +
					" there is a door to " + currentDoor.getDestination() + ".");
		}
		boolean something = false;
		System.out.println("In the room you see:");
		List<Creature> creatureList = this.location.getCreatures();
		for (Creature creature: creatureList){
			if(this.equals(creature)){
				
			}
			else if(creature instanceof  Student){
				System.out.println("A student named " + creature);
				something = true;
			}else if(creature instanceof Teacher){
				System.out.println("A teacher named " + creature);
				something = true;
			}else{
				System.out.println(creature);
				something = true;
			}
		}
		
		List<Loot> lootList = this.location.getLoot();
		int numberOfKeys = 0;
		for(Loot loot : lootList){
			if (loot.toString().equals("A key")){
				numberOfKeys++;		
			}else{
			System.out.println(loot);
			something = true;
			}
		}
		if (numberOfKeys != 0){
			System.out.println(numberOfKeys + " key(s)");
			something = true;
		}
		if (!something){
			System.out.println("Nothing of value");
		}
	}
	
	public void take(String target){
		if(target.equals("key")){
			for(Loot loot : this.location.getLoot()){
				if(loot instanceof passivePack.Key){
					try {
						this.backpack.add(loot);
						this.location.removeLoot(loot);
					} catch (InventoryException e) {
						System.out.println("Not enough space in backpack");
					}
					break;
				}
			}
		} else if (target.equals("book")){
			for (Loot loot: this.location.getLoot()){
				if(loot instanceof passivePack.Book){
					try {
						this.backpack.add(loot);
						this.location.removeLoot(loot);
					} catch (InventoryException e) {
						System.out.println("Not enough space in backpack");
					}
					break;
				}
			}
		} else if(target.equals("all")){
			int stop = this.location.getLoot().size();
			for (int i = 0;i < stop;i++){
				try {
					this.backpack.add(this.location.getLoot().get(0));
					this.location.removeLoot(this.location.getLoot().get(0));
				} catch (InventoryException e) {
					System.out.println("Not enough space in backpack");
					break;
				}
			}
		} else {
			for (Loot loot: this.location.getLoot()){
				if(loot instanceof passivePack.Book){
					Book book = (Book) loot;
					if(book.getName().equalsIgnoreCase(target)){
						try {
							this.backpack.add(book);
							this.location.removeLoot(book);
						} catch (InventoryException e) {
							System.out.println("Not enough space in backpack");
						}
						break;
					}
				}
			}
			System.out.println("There is no such item in "+this.location);
		}
	}
}
	
