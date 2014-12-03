import inputPack.Translator;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Scanner;

import passivePack.Course;
import creaturePack.Player;
import exceptionPack.GameStateException;
import worldPack.Universe;

/**
 * 
 * @author Jonas & Viktor
 *
 */
public class Main {
	
	public boolean running = true;

	public static void main(String[] args) {
		System.out.println("Welcome!");
	    Scanner userInput = new Scanner(System.in);
	    String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	    try {
			String decodedPath = URLDecoder.decode(path, "UTF-8");
			
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
		Universe polacks = new Universe("Polacks",path + "resources/world.txt",path +"resources/books.txt",path +"resources/courses.txt", path +"resources/names.txt", path +"resources/questions.txt");
	    System.out.println("What is you name?");
	    String playerName = userInput.nextLine();
	    System.out.println("Hello " + playerName);
	    
	    Player player = polacks.createPlayer(playerName, "K1");
	    Translator translator = new Translator(userInput, polacks);
	    boolean running = true;
	    System.out.println("");
	    System.out.println("");
	    System.out.println("Uppsala University is an ancient facility that has been teaching students for ages");
	    System.out.println("There is no one alive at this date that knows all of its mysteries");
	    System.out.println("You are currently in your second year of studing and have already collected 60HP");
	    System.out.println("However you still need 120HP more to graduate and that is no easy task");
	    System.out.println("Violent teachers and terrifying courses stand in your way");
	    System.out.println("");
	    System.out.println("The sun is still shining bright outside the window this cool summer day");
	    System.out.println("");
	    System.out.println("Make haste " + playerName + " and good luck!");
	    System.out.println("");
	    System.out.println("");
	    try {
			translator.translate("look", player);
		} catch (GameStateException e) {

		}	    
	    /*
	    player.setHp(180);
	    List<Course> courses = polacks.getCourses();
	    for (Course course: courses){
	    player.addCourse(course);
	    player.finishCourse(course);
	    }
	    */
	    while (running){
	    	try {
	    		translator.nextInstruction(player);
			
	    	} catch (GameStateException e) {
	    		running = false;
	    		if (e.getMessage().equals("Game Over")){
					System.out.println("You win!");
					System.out.println("Here is your diploma");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					for (Course course : player.getFinishedCourses()){
						System.out.println("* " + course);
					}
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	    		}
	    	}
	    }
	    
	    userInput.close();
	}
}