import inputPack.Translator;

import java.util.Scanner;

import passivePack.Direction;
import passivePack.Key;
import creaturePack.Creature;
import creaturePack.Player;
import creaturePack.Student;
import exceptionPack.InputException;
import exceptionPack.WorldException;
import worldPack.Universe;
import worldPack.World;

public class Main {

	public static void main(String[] args) {
		System.out.println("Welcome!");
	    Scanner userInput = new Scanner(System.in);
		Universe polacks = new Universe("Polacks","/home/jonas/Desktop/world.txt","/home/jonas/Desktop/books.txt","/home/jonas/Desktop/courses.txt", "/home/jonas/Desktop/names.txt");
	    System.out.println("What is you name?");
	    String playerName = userInput.nextLine();
	    System.out.println("Hello " + playerName);
	    
	    Player player = polacks.createPlayer(playerName, "K1");
	    boolean running = true;
	    System.out.println("INTRO");
	    Translator.instruction("look", player);
	    
	    while (running){
	    Translator.instruction(userInput.nextLine(), player);
	    }
	    userInput.close();
	}
}