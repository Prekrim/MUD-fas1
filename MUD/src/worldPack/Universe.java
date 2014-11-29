package worldPack;

import ioPack.Reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import creaturePack.*;
import exceptionPack.InventoryException;
import exceptionPack.WorldException;
import passivePack.*;

public class Universe {
	private World world;
	private List<String> names = new ArrayList<>();
	private List<Book> books = new ArrayList<>();
	private List<Course> courses = new ArrayList<>();
	private List<Student> students = new ArrayList<>();
	private List<Teacher> teachers = new ArrayList<>();
	private List<Question> questions = new ArrayList<>();
	
	public Universe(String worldName, String worldPath, String bookPath, String coursePath, String namePath, String questionPath){
		this.world = new World(worldName, worldPath);
		this.generateNames(namePath);
		this.generateBooks(bookPath);
		this.generateCourses(coursePath);
		this.generateQuestions(questionPath);
		this.generateKeys();
		this.generateStudents();
		this.generateTeachers();
		this.generateSphinx();
	}
	
	public List<Course> getCourses(){
		return this.courses;
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
		this.placeBooks(books);
	}
	
	private void placeBooks(List<Book> books){
		int bookAmount = books.size();
		Random randomizer = new Random();
		for(int i = 0;i < bookAmount;i++){
			int numberOfRooms = this.world.getRooms().size();
			int target = randomizer.nextInt(numberOfRooms-1);
			Room targetRoom = this.world.getRooms().get(target);
			targetRoom.addLoot(books.get(i));
		}
	}
	
	public void generateNames(String path){
		Reader nameReader = new Reader(path);
		String[] nameArray = null;
		try {
			nameArray = nameReader.openFile();	
			for (String name: nameArray){
			this.names.add(name);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	public void generateStudents(){
		int students = courses.size() * 2;
		Random randomizer = new Random();
		
		for (int i = 0; students > 0; i++){
			int numberOfRooms = this.world.getRooms().size();
			int target = randomizer.nextInt(numberOfRooms-1);
			Room targetRoom = this.world.getRooms().get(target);
			String name = this.names.get(0);
			this.names.remove(0);
			Student newStudent = new Student(name, targetRoom);
			newStudent.getRoom().addCreature(newStudent);
			
			if (this.courses.size() <= i){
				i = 0;
			}
			Course targetCourse = this.courses.get(i);
			newStudent.addCourse(targetCourse);
			try {
				newStudent.getBackpack().add(targetCourse.getBook());
			} catch (InventoryException e) {
				System.out.println(e.toString());
				System.exit(1);
			}
			newStudent.finishCourse(targetCourse);
			i++;
			if (this.courses.size() <= i){
				i = 0;
			}
			targetCourse = this.courses.get(i);
			
			newStudent.addCourse(targetCourse);
			this.students.add(newStudent);
			students--;
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
	
	public void generateKeys(){
		this.world.createKeys();
		}
	
	public void generateSphinx(){
		Room targetRoom = this.getRoom("Holken");
		Sphinx theSphinx = new Sphinx("Sphinx", targetRoom);
		targetRoom.addCreature(theSphinx);
	}
	
	public Player createPlayer(String name, String location){
		Room currentRoom = this.getRoom(location);
		Player newPlayer = new Player(name, currentRoom);		
		currentRoom.addCreature(newPlayer);		
		return newPlayer;
	}
	
	public void generateQuestions(String path){
		Reader questionReader = new Reader(path);
		String[] questionArray=null;
		try {
			questionArray = questionReader.openFile();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		for(int i = 0;i < questionArray.length-1;i++){
			String[] questionParts = questionArray[i].split(";");
			String courseName = questionParts[0];
			Course targetCourse = null;
			for(Course course : courses){
				if(course.getName().equals(courseName)){
					targetCourse = course;
				}
			}
			if (targetCourse == null) { 
				System.out.println("Could not find course");
				System.exit(1);
			}
			String[] answers = java.util.Arrays.copyOfRange(questionParts, 2, 5);
			Question newQuestion = new Question(targetCourse, questionParts[1], answers);
			this.questions.add(newQuestion);
		}
	}
	
	public void generateTeachers(){
		int teachers = courses.size();
		Random randomizer = new Random();
		
		for (int i = 0; teachers > 0; i++){
			int numberOfRooms = this.world.getRooms().size();
			int target = randomizer.nextInt(numberOfRooms-1);
			Room targetRoom = this.world.getRooms().get(target);
			String name = this.names.get(0);
			this.names.remove(0);
			
			Question targetQuestion = this.questions.get(i);
			Course targetCourse = null;
			for(Course course : this.courses){
				if(targetQuestion.getCourse().equals(course)){
					targetCourse = course;
				}
			}
			
			Teacher newTeacher = new Teacher(name, targetCourse ,targetRoom, targetQuestion);
			newTeacher.getRoom().addCreature(newTeacher);
			this.teachers.add(newTeacher);
			teachers--;
		}
	}
}