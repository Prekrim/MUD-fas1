package passivePack;

import java.util.ArrayList;
import java.util.List;

import worldPack.Room;
import exceptionPack.DoorException;
import exceptionPack.InputException;
import exceptionPack.InventoryException;

/**
 * Defines an inventory or backpack.
 * An Inventory has a maximum capacity and only stores elements of type Loot.
 * @see Loot
 */
public class Inventory {
	private int capacity = 10;
	/**
	 * The inventory's list of loot.
	 */
	protected List<Loot> loot = new ArrayList<Loot>();
	
	/**
	 * Constructs an inventory with a capacity of 10 weight units.
	 */
	public Inventory(){
		
	}
	
	/** Gets the current capacity of the inventory.
	 * @return The current capacity.
	 */
	public int getCurrentCapacity(){
		return this.capacity;
	}
	
	/** Adds loot to the inventory and changes the capacity accordingly.
	 * @param loot The loot to be added.
	 * @throws InventoryException If there is not enough capacity.
	 */
	public void add(Loot loot) throws InventoryException{
		if (capacity < loot.getWeight()){
			throw new InventoryException("Not enough room");
		}
		else{
		this.capacity -= loot.getWeight();
		this.loot.add(loot);
		}
	}
	
	/** removes loot from the inventory and changes the capacity accordingly.
	 * If loot isn't in the inventory nothing happens.
	 * @param loot The loot to be removed.
	 */
	public void remove(Loot loot) {
	if (!this.loot.isEmpty() && this.loot.contains(loot)) {
		this.capacity += loot.getWeight();
		this.loot.remove(loot);
		}
	}
	
	/** Removes a key from the Inventory.
	 * @throws InventoryException If there is no Key in inventory.
	 * @see Key
	 */
	public void useKey() throws InventoryException{
	for (Loot loot: this.loot){
		if(loot.getClass().equals(Key.class)){
			this.remove(loot);
			return;
		}
	}
	throw new InventoryException("No key available");
	}
	
	/** Removes the loot from inventory and adds it to location.
	 * If loot isn't in inventory then nothing happens.
	 * @param location The Room receiving loot.
	 * @param loot The loot to be moved.
	 */
	public void throwLoot(Room location, Loot loot){
		if(this.loot.contains(loot)){
			location.addLoot(loot);
			this.remove(loot);
		}
	}

	/** Gets the books in inventory.
	 * @return The books in inventory as a List.
	 */
	public List<Loot> getBooks() {
		List<Loot> books = new ArrayList<Loot>();
		for (Loot loot:this.loot){
			if(loot.getClass().equals(Book.class)){
			books.add(loot);
			}
		}
		
		return books;
	}

	/** Checks if the inventory contains targetLoot.
	 * @param targetLoot The specified loot to be searched for.
	 * @return True if inventory contains targetLoot, else false.
	 */
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
		int i = 1;
		for (Loot loot: this.loot){
			if (loot.getClass().equals(Key.class)){
				keys++;
			}else{
			returnString += "[" +i+"] " + loot.toString() + "\n";
			i++;
			}
		}
		if (keys != 0){
		return returnString + "\n" + keys + " key(s)";
		}
		else{ return returnString;}
	}

	/** Gets a key from inventory.
	 * @return A Key in inventory.
	 * @throws InventoryException If no key is found in inventory.
	 */
	public Key getKey() throws InventoryException {
		for (Loot loot : this.loot) {
			if(loot instanceof Key){
				return (Key) loot;
			}
		}
		throw new InventoryException("No key in Inventory");
	}
	
	/** Gets a book by name from inventory.
	 * @param name The name of the book.
	 * @return The book with corresponding name.
	 * @throws InputException If no book by name is found.
	 */
	public Book getBook(String name) throws InputException{
		for (Loot loot : this.loot){
			if (loot instanceof Book){
				Book targetBook = (Book) loot;
				if (targetBook.getName().equals(name)){
					return targetBook;
				}
			}
		}
		throw new InputException("No such book in inventory");
	}

	/** Gets a book by index from inventory.
	 * @param bookIndex the index of the book.
	 * @return The book with corresponding index.
	 * @throws InputException If the index is not found.
	 */
	public Book getBook(int bookIndex) throws InputException {
		int i = 1;
		for (Loot loot : this.loot){
			if (loot instanceof Book){
				if(i == bookIndex){
					return (Book)loot;
				}
				i++;
			}
		}
		throw new InputException("Index out of bounds");
	}

}
