package passivePackTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import exceptionPack.WorldException;
import passivePack.Book;
import passivePack.Course;

public class CourseTest {

	@Test
	public void testCourse() {
		List<Book> bookList = new ArrayList<>();
		Book necronomicon = new Book("Necronomicon;Cthulu;1600;99");
		bookList.add(necronomicon);
		
		try {
			Course testCourse = new Course("Elder Studies;Necronomicon;99", bookList);
			assertEquals("testCourse exists", true, testCourse != null);
			assertEquals("There is a course named Elder Studies", true, testCourse.getName().equals("Elder Studies"));
		} catch (WorldException e) {
			fail("Test does not work");
		}
	}

	@Test
	public void testEqualsCourse() {
		List<Book> bookList = new ArrayList<>();
		Book courseBook = new Book("Vad kan matriser göra för dig?;Mats Ris;1962;5");
		Book necronomicon = new Book("Necronomicon;Cthulu;1600;99");
		bookList.add(courseBook);
		bookList.add(necronomicon);
		try {
			Course newCourse = new Course("Linjär Algebra och Geometri 1;Vad kan matriser göra för dig?;5", bookList);
			Course oldCourse = new Course("Elder Studies;Necronomicon;99", bookList);
			assertEquals("newCourse is equal to itself", true, newCourse.equals(newCourse));
			assertEquals("newCourse is not equal to oldCourse", false, newCourse.equals(oldCourse));
		} catch (WorldException e) {
			fail("Test does not work");
		}
	}

}
