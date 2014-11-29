package passivePack;

public class Book extends Loot{
	private String name;
	private String author;
	private int year;
	
	public String getName() {
		return this.name;
	}	
	
	public boolean isBook(String bookName){
		return (this.name.equals(bookName));
	}
	
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

	public String getAuthor() {
		return this.author;
	}
}
