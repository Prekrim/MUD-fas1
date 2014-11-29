package passivePackTest;

import static org.junit.Assert.*;

import org.junit.Test;

import passivePack.Book;
import passivePack.Key;

public class BookTest {

	@Test
	public void testIsBook() {
		Book wrongBook = new Book("Flygplan;Lyg Plan;1976;20");
		Book rightBook = new Book("Necronomicon;Cthulu;1600;99");
		
		assertEquals("The book rightBook is the wanted book", true, rightBook.isBook("Necronomicon"));
		assertEquals("The book wrongBook is not the wanted book", false, wrongBook.isBook("Necronomicon"));
	}

	@Test
	public void testBook() {
		Book testBook = new Book("Necronomicon;Cthulu;1600;99");
		assertEquals("testBook is not null", true, testBook != null);
		assertEquals("testBook is a book", true, testBook instanceof Book);
	}

	@Test
	public void testToString() {
		Book testBook = new Book("Necronomicon;Cthulu;1600;99");
		assertEquals("toString gives us the expected string", true, testBook.toString().equals(
				"The book: Necronomicon by Cthulu (1600)"));
	}

}
