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
		
		String bookName = parts[1];
		for (Book book: books){
			if (book.getName().equals(bookName)){
				this.litterature = book;
				return;
			}
		}
		throw new WorldException("Couldn't find book in list");
	}
	
}
