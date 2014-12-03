package passivePack;

/**
 * Defines a Book with a name, author and the published year
 *
 */
public class Book extends Loot{
	private String name;
	private String author;
	private int year;
	
	/** Gets the name of the book.
	 * @return the name of the book.
	 */
	public String getName() {
		return this.name;
	}	
	
	/** Compares this book's name to bookName
	 * @param bookName The name of a book
	 * @return True if the names are the same, else false.
	 */
	public boolean isBook(String bookName){
		return (this.name.equals(bookName));
	}
	
	/** Constructor for Book
	 * @param raw A string containing the book data by format: "name;author;year;weight"
	 */
	public Book(String raw){
		String[] parts = raw.split(";");
		this.name = parts[0];
		this.author = parts[1];
		this.year = Integer.parseInt(parts[2]);
		this.weight = Integer.parseInt(parts[3]);
		
	}
	
	public String toString(){
		return ("The book: "+this.name+" by "+this.author+" ("+this.year+")");
	}

	/** Gets the author of the book.
	 * @return the author of the book.
	 */
	public String getAuthor() {
		return this.author;
	}
}
