package passivePack;

import java.util.ArrayList;
import java.util.List;

import worldPack.Room;
import exceptionPack.DoorException;
import exceptionPack.InventoryException;

public class Inventory {
	private int capacity = 10;
	protected List<Loot> loot = new ArrayList<Loot>();
	
	public Inventory(){
		
	}
	
	public void add(Loot loot) throws InventoryException{
		if (capacity < loot.getWeight()){
			throw new InventoryException("Not enough room");
		}
		else{
		this.capacity -= loot.getWeight();
		this.loot.add(loot);
		}
	}
	
	public void remove(Loot loot) {
	if (!this.loot.isEmpty()) {
		this.capacity += loot.getWeight();
		this.loot.remove(loot);
		}
	}
	
	public void useKey() throws InventoryException{
	for (Loot loot: this.loot){
		if(loot.getClass().equals(Key.class)){
			this.remove(loot);
			return;
		}
	}
	throw new InventoryException("No key available");
	}
	
	public void throwLoot(Room location, Loot loot){
		location.addLoot(loot);
		this.remove(loot);
	}

	public List<Loot> getBooks() {
		List<Loot> books = new ArrayList<Loot>();
		for (Loot loot:this.loot){
			if(loot.getClass().equals(Book.class)){
			books.add(loot);
			}
		}
		
		return books;
	}

	public boolean contains(Loot targetLoot) {
		for(Loot loot : this.loot){
			if (loot.equals(targetLoot)){
				return true;
			}
		}
		return false;
	}
	public String toString(){
		int keys = 0;
		String returnString = "";
		for (Loot loot: this.loot){
			if (loot.getClass().equals(Key.class)){
				keys++;
			}else{
			returnString += loot.toString() + "\n";
			}
		}
		if (keys != 0){
		return returnString + "\n" + keys + " key(s)";
		}
		else{ return returnString;}
	}

	public Key getKey() throws InventoryException {
		for (Loot loot : this.loot) {
			if(loot instanceof Key){
				return (Key) loot;
			}
		}
		throw new InventoryException("No key in Inventory");
	}

}
