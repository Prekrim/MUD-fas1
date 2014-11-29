package creaturePackTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import passivePack.Book;
import passivePack.Course;
import passivePack.Question;
import worldPack.Room;
import creaturePack.Teacher;
import exceptionPack.WorldException;

public class TeacherTest {

	@Test
	public void testTeacher() {
		Room room = new Room("Test");
		List<Book> bookList = new ArrayList<>();
		Book book = new Book("Necronomicon;Cthulu;1600;99");
		bookList.add(book);
		try {
			Course course = new Course("Elder Studies;Necronomicon;1000", bookList);
			String a[] = {"Yes", "No", "Maybe"};
			Question question = new Question(course, "Is 1 the right answer?", a, 0);
			Teacher newTeacher = new Teacher("Tobias", course, room, question);
			assertEquals("newTeacher exists", true, newTeacher != null);
		} catch (WorldException e) {
			fail("Test not working");
		}
		
	}
}
