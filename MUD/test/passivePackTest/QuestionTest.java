package passivePackTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import exceptionPack.WorldException;
import passivePack.Book;
import passivePack.Course;
import passivePack.Question;

public class QuestionTest {

	@Test
	public void testQuestionCourseStringStringArrayInt() {
		Book courseBook = new Book("Necronomicon;Cthulu;1600;0");
		List<Book> bookList = new ArrayList<>();
		bookList.add(courseBook);
		Course newCourse;
		try {
			newCourse = new Course("Elder Studies;Necronomicon;5", bookList);
			String a[] = {"Ja", "Nej", "Kanske"};
			Question question = new Question(newCourse, "Is 1 the right answer?", a, 1);
			assertEquals("A question called question exists", true, question != null);
			
		} catch (WorldException e) {
			fail("Test not working");
		}	
	}

	@Test
	public void testQuestionCourseStringStringArray() {
		Book courseBook = new Book("Necronomicon;Cthulu;1600;0");
		List<Book> bookList = new ArrayList<>();
		bookList.add(courseBook);
		Course newCourse;
		try {
			newCourse = new Course("Elder Studies;Necronomicon;5", bookList);
			String a[] = {"Ja", "Nej", "Kanske"};
			Question question = new Question(newCourse, "Is 1 the right answer?", a);
			assertEquals("A question called question exists", true, question != null);
			
		} catch (WorldException e) {
			fail("Test not working");
		}
	}

	@Test
	public void testCheckAnswer() {
		Book courseBook = new Book("Necronomicon;Cthulu;1600;0");
		List<Book> bookList = new ArrayList<>();
		bookList.add(courseBook);
		Course newCourse;
		try {
			newCourse = new Course("Elder Studies;Necronomicon;5", bookList);
			String a[] = {"Ja", "Nej", "Kanske"};
			Question question = new Question(newCourse, "Is 1 the right answer?", a, 0);
			assertEquals("A question called question exists", true, question != null);
			assertEquals("Answer 1 is correct", true, question.checkAnswer("1"));
			assertEquals("Answer 2 is false", false, question.checkAnswer("2"));
			assertEquals("Answer 3 is false", false, question.checkAnswer("3"));
			
		} catch (WorldException e) {
			fail("Test not working");
		}
	}

}
