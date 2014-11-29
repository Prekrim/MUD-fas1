package passivePackTest;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptionPack.InputException;
import passivePack.Direction;

public class DirectionTest {

	@Test
	public void testDirectionInt() {
		try {
			Direction newDir = new Direction(2);
			assertEquals("newDir is a direction", true, (newDir instanceof Direction && newDir != null));
			assertEquals("newDir is a direction to the south", true, newDir.getDirection().equals("South"));
		} catch (InputException e) {
			fail("Test not working");
		}
	}

	@Test
	public void testDirectionString() {
		try {
			Direction newDir = new Direction("south");
			assertEquals("newDir is a direction", true, (newDir instanceof Direction && newDir != null));
		} catch (InputException e) {
			fail("Test not working");
		}
	}

	@Test
	public void testEqualsDirection() {
		
		try {
			Direction newDirN = new Direction("north");
			Direction newDirS = new Direction("south");
			Direction newDirS2 = new Direction("south");
			
			assertEquals("Direction north != direction south", false, newDirN.equals(newDirS));
			assertEquals("newDirS equal to newDirS2", true, newDirS.equals(newDirS2));
			assertEquals("newDirS equal to itself", true, newDirS.equals(newDirS));
		} catch (InputException e) {
			fail("Test not working");
		}
		
	}

	@Test
	public void testOppositeDirection() {
		try {
			Direction newDirN = new Direction("north");
			Direction opposite = newDirN.oppositeDirection();
			assertEquals("The opposite direction is to the south", true, opposite.getDirection().equals("South"));
		} catch (InputException e) {
			fail("Test not working");
		}
	}

}
