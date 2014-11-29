package worldPackTest;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import passivePack.Direction;
import exceptionPack.InputException;
import exceptionPack.WorldException;
import worldPack.Door;
import worldPack.Room;

public class DoorTest {

	@Test
	public final void testDoor() {
		Room dest = new Room("Dest");
		try {
			Door tester = new Door(dest, "False", 0);
			assertEquals("Door exists", true, tester != null);
			assertEquals("Door is open", true, !(tester.isOpen()));
		} catch (WorldException e) {
			fail("Test does not work");
		}
	}

	@Test
	public final void testUnlock() {
		Room dest = new Room("TestRoom;X;TestRoom;X;TestRoom;X;False;X;False");
		List<Room> rooms = new ArrayList<>();
		rooms.add(dest);
		try {
			dest.addDoors(rooms);
			Direction testDir = new Direction(1);
			dest.getDoorFacing(testDir).unlock();
			assertEquals("Door is unlocked", true, dest.getDoorFacing(testDir).isOpen());
		} catch (WorldException e) {
			fail("Test does not work");
		} catch (InputException e) {
			fail("Test does not work");
		}
	}

	@Test
	public final void testIsOpen() {
		Room dest = new Room("Dest");
		try {
			Door tester = new Door(dest, "False", 0);
			Door tester2 = new Door(dest, "True", 0);
			assertEquals("Door is locked", false, tester.isOpen());
			assertEquals("Door is open", true, tester2.isOpen());
		} catch (WorldException e) {
			fail("Test does not work");
		}
	}

	@Test
	public final void testGetDirection() {
		Room dest = new Room("Dest");
		try {
			Door tester = new Door(dest, "False", 0);
			Door tester2 = new Door(dest, "True", 2);
			Direction testDir = new Direction(0);
			Direction testDir2 = new Direction(2);
			assertEquals("Door is locked", true, tester.getDirection().equals(testDir));
			assertEquals("Door is open", true, tester2.getDirection().equals(testDir2));
		} catch (WorldException e) {
			fail("Test does not work");
		} catch (InputException e) {
			fail("Test does not work");
		}
	}

	@Test
	public final void testGetDestination() {
		Room dest = new Room("Dest");
		try {
			Door tester = new Door(dest, "False", 0);
			assertEquals("Door leads to dest", true, tester.getDestination().equals(dest));
		} catch (WorldException e) {
			fail("Test does not work");
		}
	}
}
