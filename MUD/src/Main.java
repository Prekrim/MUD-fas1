import creaturePack.Creature;
import creaturePack.Player;
import creaturePack.Student;
import worldPack.Universe;
import worldPack.World;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Welcome!");
	    Universe polacks = new Universe("Polacks","/home/jonas/Desktop/world.txt","/home/jonas/Desktop/books.txt","/home/jonas/Desktop/courses.txt");
	    
	    Player filip = polacks.createPlayer("Filip", "K1");
	    
	    
	    		
	}

}
