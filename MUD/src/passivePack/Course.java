package passivePack;

import java.util.Iterator;
import java.util.List;

import worldPack.Room;
import exceptionPack.WorldException;

public class Course {
	private String name;
	private int HP;
	private Book litterature;
	
	
	public Course(String raw, List<Book> books) throws WorldException{
		String[] parts = raw.split(";");
		this.name = parts[0];
		this.HP = Integer.parseInt(parts[2]);		
		
		//Start - - - - - 
		String bookName = parts[1];
		Iterator<Book> itr = books.iterator();
		Book targetBook = null;
		while(itr.hasNext())
		{
			Book currentBook = itr.next();
			if (currentBook.isBook(bookName)){
			targetBook = currentBook;
			}
		}
		if(targetBook == null) {
			throw new WorldException("Couldn't find book in list");
		}    		
		
		//Stop - - - - - -
		this.litterature = targetBook;
	}
	
}
