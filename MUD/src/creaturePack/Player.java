package creaturePack;

import inputPack.Conversation;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import exceptionPack.*;
import passivePack.Book;
import passivePack.Course;
import passivePack.Direction;
import passivePack.Key;
import passivePack.Loot;
import worldPack.Door;
import worldPack.Room;

public class Player extends Student{
	
	public Player(String name, Room location) {
		super(name, 60, location, 1);
		
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
			if (creature.equals(this)){}
			else{
				System.out.print(creature.lookUpon());	
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
						System.out.println("Picked up " + loot);
					} catch (InventoryException e) {
						System.out.println("Not enough space in backpack");
					}
					break;
				}
			}
		} else if (target.equals("book")){
			for (Loot loot: this.location.getLoot()){
				if(loot instanceof Book){
					try {
						this.backpack.add(loot);
						this.location.removeLoot(loot);
						System.out.println("picked up " + loot);
					} catch (InventoryException e) {
						System.out.println("Not enough space in backpack");
					}
					break;
				}
			}
		} else if(target.equals("all")){
			int stop = this.location.getLoot().size();
			int keys = 0;
			for (int i = 0;i < stop;i++){
				try {
					Loot loot = this.location.getLoot().get(0);
					this.backpack.add(loot);
					if (loot instanceof Key){
						keys++;
					}else {
						System.out.println("picked up " + loot);
					}
					this.location.removeLoot(loot);
				} catch (InventoryException e) {
					if (keys > 0){
						System.out.println("picked up " + keys + " key(s)");
					}
					System.out.println("Not enough space in backpack");
					break;
				}
				
			}
			if (keys > 0){
				System.out.println("picked up " + keys + " key(s)");
			}
		} else {
			for (Loot loot: this.location.getLoot()){
				if(loot instanceof passivePack.Book){
					Book book = (Book) loot;
					if(book.getName().equalsIgnoreCase(target)){
						try {
							this.backpack.add(book);
							this.location.removeLoot(book);
							System.out.println("picked up " + book);
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
	
	public void speak(Creature target, Scanner userInput) throws GameStateException{
		Conversation newConversation = new Conversation(this, target, userInput);
		newConversation.speak();
	}

	public void enroll(Course course) throws InputException{
		if (this.getFinishedCourses().contains(course) || this.getActiveCourses().contains(course)){
			throw new InputException("Already enrolled");
		} else{
			this.addCourse(course);
		}
	}


}
	
