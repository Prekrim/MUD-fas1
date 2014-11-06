package inputPack;

import passivePack.Direction;
import creaturePack.Player;
import exceptionPack.DoorException;
import exceptionPack.InputException;
import exceptionPack.WorldException;

public class Translator {
	public static void instruction(String instruction,Player player){
		instruction = instruction.toLowerCase();
		String[] stringParts = instruction.split(" ");
		if (stringParts.length == 0)
		{
			System.out.println("");
		} else {
			switch (stringParts[0]){
			case "walk":
				if(stringParts.length == 2){
					Direction direction;
					try {
						direction = new Direction(stringParts[1]);
					} catch (InputException e1) {
						System.out.println("Usage: walk [direction], [direction] = (North, East, South, West)");
						break;
					}
					try {		
						player.walk(direction);
						player.lookAround();
					} catch (WorldException e){
						System.out.println("No door to the " + direction);
					} catch (DoorException e){
						System.out.println("The door is locked");
					}
					break;
				}
				System.out.println("Usage: walk [direction], direction = (North, East, South, West)");
				break;
			case "look":
				player.lookAround();
				break;
			
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
				
			case "inventory":
				System.out.println(player.getBackpack().toString());
				break;
			
			case "exit":
				System.out.println("Goodbye!");
				System.exit(1);
				break;
			
			default:
				System.out.println("Use help for a list of commands");
			}
		}	
	}
}
