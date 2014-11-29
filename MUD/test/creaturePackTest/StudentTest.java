package creaturePackTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import passivePack.Book;
import passivePack.Course;
import passivePack.Question;
import creaturePack.Student;
import creaturePack.Teacher;
import exceptionPack.WorldException;
import worldPack.Room;

public class StudentTest {

	@Test
	public void testStudentStringIntRoomInt() {
		Room dest = new Room("dest");
		Student student = new Student("Student", 0, dest, 1);
		assertEquals("student exists", true, student != null);
	}

	@Test
	public void testStudentStringRoom() {
		Room dest = new Room("dest");
		Student student = new Student("Student", dest);
		assertEquals("student exists", true, student != null);
	}

	@Test
	public void testAddCourse() {
		Room dest = new Room("dest");
		Student student = new Student("Student", 0, dest, 1);
		
		List<Book> bookList = new ArrayList<>();
		Book book = new Book("Necronomicon;Cthulu;1600;99");
		bookList.add(book);
		try {
			Course course = new Course("Elder Studies;Necronomicon;1000", bookList);
			student.addCourse(course);
			assertEquals("Student has course", true, student.getActiveCourses().contains(course));
		} catch (WorldException e) {
			fail("Test not working");
		}
	}

	@Test
	public void testFailCourse() {
		Room dest = new Room("dest");
		Student student = new Student("Student", 0, dest, 1);
		
		List<Book> bookList = new ArrayList<>();
		Book book = new Book("Necronomicon;Cthulu;1600;99");
		bookList.add(book);
		try {
			Course course = new Course("Elder Studies;Necronomicon;1000", bookList);
			student.addCourse(course);
			student.finishCourse(course);
			assertEquals("Student has finished course", true, student.getFinishedCourses().contains(course));
			student.failCourse(course);
			assertEquals("Student has course", true, student.getActiveCourses().contains(course));
			
		} catch (WorldException e) {
			fail("Test not working");
		}
	}

	@Test
	public void testFinishCourse() {
		Room dest = new Room("dest");
		Student student = new Student("Student", 0, dest, 1);
		
		List<Book> bookList = new ArrayList<>();
		Book book = new Book("Necronomicon;Cthulu;1600;99");
		bookList.add(book);
		try {
			Course course = new Course("Elder Studies;Necronomicon;1000", bookList);
			student.addCourse(course);
			student.finishCourse(course);
			assertEquals("Student has finished course", true, student.getFinishedCourses().contains(course));
		} catch (WorldException e) {
			fail("Test not working");
		}
	}

}
