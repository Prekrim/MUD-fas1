package inputPack;

import java.util.Scanner;

import passivePack.Book;
import passivePack.Course;
import passivePack.Question;
import creaturePack.*;
import exceptionPack.GameStateException;
import exceptionPack.InputException;
import exceptionPack.InventoryException;

public class Conversation {
	private Player player;
	private Creature target;
	private Scanner userInput;
	
	public Conversation(Player player, Creature target, Scanner userInput){
		this.player = player;
		this.target = target;
		this.userInput = userInput;
	}
	
	public void speak() throws GameStateException{
		boolean running = true;
		
		if (target instanceof Student){
			Course finished = ((Student)target).getFinishedCourses().get(0);
			Course active = ((Student)target).getActiveCourses().get(0);
			System.out.println("Hello!");
			System.out.println("My name is " + target.getName());
			System.out.println("I am currently studying " + active.getName());
			System.out.println("I just finished " + finished.getName());
			System.out.println("Do you want to trade the book \"" + finished.getBook().getName()+ "\"?");
			System.out.println("I need the book \"" + active.getBook().getName() + "\"?");
		} else if (target instanceof Teacher){
			System.out.println("Good day to you young student");
			System.out.println("I'm " + target.getName());
			System.out.println("I teach " + ((Teacher)target).getCourse().getName());
			System.out.println("Can I help you with something?");
		} else if (target instanceof Sphinx){
			System.out.println("Greetings mortal");
		}
			
		while(running == true){
			String nextLine = userInput.nextLine();
			String instruction = nextLine.toLowerCase();
			//String[] stringParts = instruction.split(" ");

			
				if (target instanceof Teacher){
					Teacher teacher = (Teacher) target;
					switch (instruction){
					case "who are you?":
						
					case "enroll":
						try{
							player.enroll(teacher.getCourse());
							System.out.println("Good! You are now signed up for " + teacher.getCourse());
							System.out.println("You will need the book: " + teacher.getCourse().getBook().getName() 
									+ " by " + teacher.getCourse().getBook().getAuthor() + ". Make sure to read it");
						}catch (InputException e){
							System.out.println("You have already signed up for this course");
							}

						break;
						
					case "help":
					case "?":
					case "h":
						System.out.println("enroll");
						System.out.println("goodbye");
						break;
					case "goodbye":
					case "bye":
					case "cya":
					case "later":
						System.out.println("Goodbye");
						running = false;
						break;
					default:
						System.out.println("You need to speak clearer! I can't understand what you are saying");
					}
				}
					
				if (target instanceof Student){
					Student student = (Student) target;
					switch (instruction){
					
					case "trade":
						Course finished = student.getFinishedCourses().get(0);
						Course current = student.getActiveCourses().get(0);
						Book studentBook = finished.getBook();
						Book playerBook = current.getBook();
						
						if (!student.getBackpack().contains(studentBook)){
							System.out.println("I'm sorry, I have already traded this book");
						} else if (player.getBackpack().contains(playerBook)){
							System.out.println("Do you want to trade " 
									+ playerBook.getName() + " for " 
									+ studentBook.getName() + "?");
							boolean answer = false;
							switch (userInput.nextLine().toLowerCase()) {
							case "yes":
							case "y":
							case "yeah":
							case "yuh":
							case "sure":
							case "correct":
							case "ofc":
								answer = true;
								break;
							default:
							}
							if(!answer) {
								break;
							}
							if (player.getBackpack().getCurrentCapacity() - studentBook.getWeight() < 0){
								System.out.println("It looks like you have your hands full with other things. Let's trade later.");
							}else{
								player.getBackpack().remove(playerBook);
								student.getBackpack().remove(studentBook);
								try {
									player.getBackpack().add(studentBook);
									student.getBackpack().add(playerBook);
									System.out.println("You have aquired " + studentBook);
									System.out.println(playerBook + " removed");
								} catch (InventoryException e) {
									System.out.println("ERROR: No space in Inventory");
									System.exit(1);
								}
							}
						} else {
							System.out.println("I'm sorry you don't have " 
									+ playerBook.getName() + ", which I need");
						}
						break;
					
					case "goodbye":
					case "bye":
					case "cya":
					case "later":
						System.out.println("Goodbye");
						running = false;
						break;
						
					case "help":
					case "?":
					case "h":
						System.out.println("trade");
						System.out.println("goodbye");
						break;
					default:
						System.out.println("I'm sorry I didn't quite catch that. Can you reprashe please?");
						break;
					}
				}
				if (target instanceof Sphinx){
					Sphinx sphinx = (Sphinx) target;
					switch (instruction){
					
					case "graduate":
						if (sphinx.studentIsWorthy(player)){
							throw new GameStateException("Game Over");
						}
						break;
					
					case "goodbye":
					case "bye":
					case "cya":
					case "later":
					
						System.out.println("Until next time");
						running = false;
						break;
					case "help":
					case "?":
					case "h":
						System.out.println("graduate");
						System.out.println("goodbye");
						break;
					default:
						System.out.println("\"The Sphinx stares silently at you\"");
						break;
					}
			}
		}
	}
	
	public void questionTime() {
		Teacher teacher = (Teacher) target;
		System.out.println("You are approached by the teacher " + teacher.getName() + "...");
		System.out.println("If it isn't " + player.getName() + "!");
		System.out.println("I hope you remember what you learned during class");
		
		Question question = teacher.getQuestion();
		Book book = teacher.getCourse().getBook();
		boolean hasBook = player.getBackpack().getBooks().contains(book);
		question.printString(hasBook);
		String answer = userInput.nextLine();
		if(!question.checkAnswer(answer)){
			if(player.getActiveCourses().contains(teacher.getCourse())){
				System.out.println("I am sorry, that is not the right answer. You should study some more");
				System.out.println("Goodbye");
			}
			if(player.getFinishedCourses().contains(teacher.getCourse())){
				player.failCourse(teacher.getCourse());
				System.out.println("What!? You don't remember?");
				System.out.println("I'll sign you up for the course again, maybe you will learn something this time");
				System.out.println("Goodbye");			
			}
		}else{
			if(player.getFinishedCourses().contains(teacher.getCourse())){
				System.out.println("You are correct, I am glad you learned something valuable in my class");
				System.out.println("Goodbye");

			}
			if(player.getActiveCourses().contains(teacher.getCourse())){
				System.out.println("Right you are, you have passed " + teacher.getCourse().getName()); 
				System.out.println("Congratulations!");
				player.finishCourse(teacher.getCourse());
				System.out.println("Goodbye");
			}
		}
	}
}
