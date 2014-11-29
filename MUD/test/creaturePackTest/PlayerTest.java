package creaturePackTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import passivePack.Book;
import passivePack.Course;
import passivePack.Direction;
import passivePack.Inventory;
import passivePack.Key;
import worldPack.Room;
import creaturePack.Player;
import exceptionPack.DoorException;
import exceptionPack.InputException;
import exceptionPack.InventoryException;
import exceptionPack.WorldException;

public class PlayerTest {

	@Test
	public void testPlayer() {
		Room dest = new Room("dest");
		Player newPlayer = new Player("Filip", dest);
		assertEquals("newPlayer exists", true, newPlayer != null);
	}

	@Test
	public void testThrowBook() {
		Room dest = new Room("Dest");
		Book newBook = new Book("Necronomicon;Cthulu;1600;0");
		Player newPlayer = new Player("Player", dest);
		dest.addCreature(newPlayer);
		try {
			newPlayer.getBackpack().add(newBook);
			newPlayer.throwBook(newBook);
			Inventory inv = newPlayer.getBackpack();
			assertEquals("newBook is not in backpack", false, inv.contains(newBook));
			assertEquals("newBook is in the room", true, dest.getLoot().contains(newBook));
		} catch (InventoryException e) {
			fail("Test not working");
		}
	}

	@Test
	public void testUseKey() {
	Key newKey = new Key();
	Room tester = new Room("TestRoom;X;X;X;NotTestRoom;X;X;X;False");
	Player newPlayer = new Player("Player", tester);
	}
	
	@Test
	public void testTake() {
		Key newKey = new Key();
		Room newRoom = new Room("TestRoom");
		Player newPlayer = new Player("Player", newRoom);
		newRoom.addLoot(newKey);
		newPlayer.take("key");
		assertEquals("Player has newKey", true, newPlayer.getBackpack().contains(newKey));
		assertEquals("The room does not have newKey", false, newRoom.getLoot().contains(newKey));
		
	}

	@Test
	public void testEnroll() {
		Room room = new Room("Test");
		List<Book> bookList = new ArrayList<>();
		Book book = new Book("Necronomicon;Cthulu;1600;99");
		bookList.add(book);
		Player newPlayer = new Player("Player", room);
		try {
			Course course = new Course("Elder Studies;Necronomicon;1000", bookList);
			newPlayer.enroll(course);
			
		} catch (WorldException e) {
			fail("Test not working");
		} catch (InputException e) {
			fail("Test not working");
		}
	}
	
	@Test
	public void testWalk() {
		Room tester = new Room("TestRoom;X;X;X;NotTestRoom;X;X;X;True");
		List<Room> rooms = new ArrayList<>();
		Room next = new Room("NotTestRoom;X;TestRoom;X;X;X;True;X;X");
		rooms.add(next);
		rooms.add(tester);
		Player newPlayer = new Player("Player", tester);
		tester.addCreature(newPlayer);
		
		try {
			Direction west = new Direction("west");
			tester.addDoors(rooms);
			newPlayer.walk(west);
			assertEquals("Player is in the next room", true, next.getCreatures().contains(newPlayer));
		} catch (WorldException e) {
			fail("Test is not working");
		} catch (DoorException e) {
			fail("Test is not working");
		} catch (InputException e) {
			fail("Test is not working");
		}
		
	}
}
