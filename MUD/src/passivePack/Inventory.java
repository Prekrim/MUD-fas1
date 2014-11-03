package passivePack;

import java.util.ArrayList;
import java.util.List;

import exceptionPack.InventoryException;

public class Inventory {
	private int capacity = 10;
	protected List<Book> books = new ArrayList<Book>();
	protected List<Key> keys = new ArrayList<Key>();
	
	public Inventory(){
		
	}
	
	public void addKey(Key key) throws InventoryException{
		if (capacity < key.getWeight()){
			throw new InventoryException("Not enough room");
		}
		else{
		this.capacity -= key.getWeight();
		keys.add(key);
		}
	}
	private void removeKey() throws InventoryException{
	if (keys.isEmpty()) {
		throw new InventoryException("No key to remove");
		}
	else{
		this.capacity += keys.get(0).getWeight();
		keys.remove(0);
		}
	}
	
	public void useKey(){
		
	}
	
	public void throwKey(){
		
	}
	
	public void addBook(Book book) throws InventoryException{
		if (capacity < book.getWeight()){
			throw new InventoryException("Not enough room");
		}
		else{
		this.capacity -= book.getWeight();
		books.add(book);
		}
	}
	
	public void removeBook(Book book) throws InventoryException{
	if (books.isEmpty()) {
		throw new InventoryException("No key to remove");
		}
	else{
		this.capacity += book.getWeight();
		books.remove(book);
		}
	}

	public List<Book> getBooks() {
		return this.books;
	}
	

}
