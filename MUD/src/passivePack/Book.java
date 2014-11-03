package passivePack;

public class Book {
	private String name;
	private String author;
	private int year;
	private int weight;
	
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
	public int getWeight(){
	return this.weight;
	}
	
	
}
