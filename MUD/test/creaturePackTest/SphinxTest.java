package creaturePackTest;

import static org.junit.Assert.*;

import org.junit.Test;

import worldPack.Room;
import creaturePack.Sphinx;
import creaturePack.Student;

public class SphinxTest {

	@Test
	public void testSphinx() {
		Room dest = new Room("dest");
		Sphinx sphinx = new Sphinx("Sphinx", dest);
		assertEquals("The sphinx exists", true, sphinx != null);
	}

	@Test
	public void testStudentIsWorthy() {
		Room dest = new Room("dest");
		Student bookWorm = new Student("Hermione", 180, dest, 5);
		Student lazybum = new Student("Ron", 10, dest, 2);
		Sphinx sphinx = new Sphinx("Sphinx", dest);
		assertEquals("Hermione is worthy", true, sphinx.studentIsWorthy(bookWorm));
		assertEquals("Ron is not worthy", false, sphinx.studentIsWorthy(lazybum));
	}

}
