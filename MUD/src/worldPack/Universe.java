package worldPack;

import ioPack.Reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import creaturePack.*;
import exceptionPack.WorldException;
import passivePack.*;

public class Universe {
	private World world;
	private List<Book> books = new ArrayList<Book>();
	private List<Course> courses = new ArrayList<Course>();
	private List<Student> students = new ArrayList<Student>();
	private List<Teacher> teachers = new ArrayList<Teacher>();
	
	public void generateBooks(String path){
		Reader bookReader = new Reader(path);
		String[] bookArray = null;
		try {
			bookArray = bookReader.openFile();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		for(int i=0;i<bookArray.length - 1;i++){
			Book newBook = new Book(bookArray[i]);
			this.books.add(newBook);
		}
		
	}
	
	public void generateCourses(String path){
		Reader courseReader = new Reader(path);
		String[] courseArray = null;
		try {
			courseArray = courseReader.openFile();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		for(int i=0;i<courseArray.length - 1;i++){
			Course newCourse = null;
			try {
				newCourse = new Course(courseArray[i], this.books);
			} catch (WorldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
			}
			this.courses.add(newCourse);
		}
	}
	
	public Universe(String worldName, String worldPath, String bookPath, String coursePath){
		this.world = new World(worldName, worldPath);
		this.generateBooks(bookPath);
		this.generateCourses(coursePath);
	}
	
	public World getWorld(){
		return this.world;
	
	}
	
	public Room getRoom(String roomName){
		Room returnRoom = null;
		try {
			returnRoom = this.world.getRoom(roomName);
			
		} catch (WorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Could not find room in world: " + world.getName());
			System.exit(1);
		}
		return returnRoom;
	}
	
	public void createKeys(){
		
	}
	
	public Player createPlayer(String name, String location){
		Room currentRoom = this.getRoom(location);
		Player newPlayer = new Player(name, currentRoom);		
		currentRoom.addCreature(newPlayer);		
		return newPlayer;
	}
	
	public void createStudent(String name, int HP,String location){
	Room currentRoom = this.getRoom(location);
	Student newStudent = new Student(name, HP, currentRoom);
	currentRoom.addCreature(newStudent);
	}
	
	public void createTeacher(String name, String course, String location){
	Room currentRoom = this.getRoom(location);
	Teacher newTeacher = new Teacher(name, course, currentRoom);
	currentRoom.addCreature(newTeacher);
	}
	
}
