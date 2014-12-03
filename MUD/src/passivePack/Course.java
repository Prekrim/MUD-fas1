package passivePack;

import java.util.Iterator;
import java.util.List;

import worldPack.Room;
import exceptionPack.WorldException;

/**
 * Defines a Course with a name, points and a book.
 * 
 */
public class Course {
	private String name;
	private int HP;
	private Book litterature;
	
	/** Creates a Course.
	 * @param raw String with course information formated as: "CourseName;Book;Points".
	 * @param books A list of books.
	 * @throws WorldException If the raw string refers to a book that isn't contained in books.
	 */
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


	/** Gets the name of the course.
	 * @return The name of the course.
	 */
	public String getName() {
		return this.name;
	}

	public boolean equals(Object course){
		if(!(course instanceof Course)){ return false; }
		Course c = (Course) course;
		return (this.name.equals(c.name));
	}
	
	/** Gets the points of the course.
	 * @return the points of the course.
	 */
	public int getHP(){
		return this.HP;
	}
	
	/** Gets the book of the course.
	 * @return the book of the course.
	 */
	public Book getBook(){
		return this.litterature;
	}
	
	public String toString(){
		return this.name + ", " + this.HP + " HP";
	}
}
