package inputPack;

import java.util.Random;
import java.util.Scanner;

import passivePack.Book;
import passivePack.Course;
import passivePack.Direction;
import worldPack.Room;
import worldPack.Universe;
import worldPack.World;
import creaturePack.Creature;
import creaturePack.Player;
import creaturePack.Teacher;
import exceptionPack.DoorException;
import exceptionPack.GameStateException;
import exceptionPack.InputException;
import exceptionPack.WorldException;

public class Translator {
	private int questionTurn;
	private int walkTurn;
	private Scanner userInput;
	private Universe universe;
	
	public Translator(Scanner userInput, Universe universe) {
		this.questionTurn = 0;
		this.walkTurn = 0;
		this.userInput = userInput;
		this.universe = universe;
	}
	public void nextInstruction(Player player) throws GameStateException{
		String inst = this.userInput.nextLine();
		translate(inst, player);
	}
	
	public void translate(String instruction, Player player) throws GameStateException{
		instruction = instruction.toLowerCase();
		String[] stringParts = instruction.split(" ");
		if (stringParts.length == 0)
		{
			System.out.println("");
		} else {
			switch (stringParts[0]){
			case "go":
			case "w":
			case "walk":
				if(stringParts.length == 2){
					String dir = stringParts[1];
					switch (stringParts[1]){
					case "n":
						dir = "north";
						break;
					case "e":
						dir = "east";
						break;
					case "s":
						dir = "south";
						break;
					case "w":
						dir = "west";
						break;
					}
					Direction direction;
					try {
						direction = new Direction(dir);
					} catch (InputException e1) {
						System.out.println("Usage: walk [direction], [direction] = (North, East, South, West)");
						break;
					}
					try {		
						player.walk(direction);
						this.questionTurn++;
						this.walkTurn++;
						player.lookAround();
						
						//Creatures Move!
						universe.getWorld().moveCreatures(walkTurn);
						if(this.questionTurn > 2) {
							//Teacher's QuestionTime!
							for (Creature creature: player.getRoom().getCreatures()){
								if(creature instanceof Teacher){
									Teacher teacher = (Teacher) creature;
									Random rand = new Random();
									int randomVal = rand.nextInt(100);
									boolean noticed = false;
									if(player.getActiveCourses().contains(teacher.getCourse())){
										noticed = randomVal < 75;
									} else if (player.getFinishedCourses().contains(teacher.getCourse())){
										noticed = randomVal < 50;
									}
									if(noticed){
										this.questionTurn = -1;
										Conversation questionTime = new Conversation(player, creature, userInput);
										questionTime.questionTime();
									}
								}
				    		}
				    	} else {
				    		this.questionTurn++;
				    	}
						
					} catch (WorldException e){
						System.out.println("No door to the " + direction);
					} catch (DoorException e){
						System.out.println("The door is locked");
					}
					break;
				}
				System.out.println("Usage: walk [direction], direction = (North, East, South, West)");
				break;
			case "l":
			case "watch":
			case "look":
				player.lookAround();
				break;
			case "throw":
			case "drop":
			case "d":
				Book targetBook;
				
				try {
					int bookIndex = Integer.parseInt(stringParts[1]);
					targetBook = player.getBackpack().getBook(bookIndex);
					player.throwBook(targetBook);
					System.out.println("Dropped " + targetBook);
				} catch (InputException e1) {
					System.out.println("No such book in backpack");
				} catch (NumberFormatException e){
					System.out.println("Usage: throw [index]");
				}
				break;

			case "g":
			case "take":
			case "t":
			case "pick":
			case "grab":
				if (stringParts.length == 2){
					player.take(stringParts[1]);
				} else {
					System.out.println("Usage: grab [item]");
				} break;
			
			case "use":
				if (stringParts.length == 3){
					if (stringParts[1].equals("key")){
						try {
							Direction direction = new Direction(stringParts[2]);
							player.useKey(direction);
						} catch (InputException e) {
							System.out.println("Invalid direction");
						}
					}
				} else if (stringParts.length == 2){
					if (stringParts[1].equals("key")){
						System.out.println("Usage: use key [direction]");
					} else{
						System.out.println("Usage: use [item]");
					} 
				}
				
				
				break;
				
			case "talk":
			case "speak":
				if (stringParts.length == 2){
					Creature target;
					try {
						target = player.getRoom().getCreature(stringParts[1]);
						player.speak(target, userInput);
					} catch (WorldException e) {
						System.out.println(stringParts[1] + " is not in the room");
					}
					

				}else{
					System.out.println("Usage: talk/speak [target]");
				}
				break;
				
			case "backpack":
			case "i":	
			case "inventory":
				System.out.println(player.getBackpack().toString());
				break;
				
			case "c":
			case "courses":
				System.out.println("      -- Finished Courses --");
				for(Course course : player.getFinishedCourses()){
					System.out.println((course) + " -- " + course.getBook().getName());	
				}
				System.out.println("       -- Active Courses --");
				for(Course course : player.getActiveCourses()){
					System.out.println((course) + " -- " + course.getBook().getName());	
				}
				System.out.println("HP: " + player.getHp() + "/180");
				break;
			case "help":
			case "h":
			case "?":
				System.out.println("   --Commands--");
				System.out.println("walk [direction]");
				System.out.println("inventory");
				System.out.println("courses");
				System.out.println("take [item]");
				System.out.println("use [item]");
				System.out.println("throw [item]");
				System.out.println("look");
				System.out.println("talk [target]");
				break;
			case "shutdown":
			case "exit":
				System.out.println("Are you sure you want to exit? (Any unsaved progress will be lost)");
				String answer = userInput.nextLine().toLowerCase();
				String[] answerParts = answer.split(" ");
				if (answerParts[0].equals("yes") || answerParts[0].equals("y")){
					System.out.println("Goodbye!");
					throw new GameStateException("Game is shutting down");
				}
				System.out.println("Resuming game");
				break;
			default:
				System.out.println("Use help for a list of commands");
			}
		}	
	}
}
