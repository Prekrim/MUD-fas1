package worldPackTest;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import creaturePack.Creature;
import creaturePack.Student;
import passivePack.Book;
import passivePack.Direction;
import passivePack.Key;
import passivePack.Loot;
import static org.hamcrest.CoreMatchers.*;
import worldPack.*;
import exceptionPack.*;

public class RoomTest {

	@Test
	public void testRoomStringListOfDoorListOfLootListOfCreature() {
		List<Creature> creatureList = new ArrayList<>();
		List<Door> doorList = new ArrayList<>();
		List<Loot> lootList = new ArrayList<>();
		String name = "testRoom";
		String[] data = null;
		
		Room testRoom = new Room(name, doorList, lootList, creatureList, data);
		assertEquals("There is a room named testRoom", true, testRoom.getName().equals("testRoom"));
	}

	@Test
	public void testRoomString() {
		Room tester = new Room("k1");
		assertEquals("name of room is k1","k1",tester.getName());
	}

	@Test
	public void testIsRoom() {
		Room tester = new Room("TestRoom");
		Room tester2 = new Room("");
		assertEquals("name of room is string", "TestRoom",tester.getName());
		assertThat("name of room is not string","NotTestRoom",not(tester.getName()));
		assertThat("name of room is empty string","",is(tester2.getName()));
	}

	@Test
	public void testEqualsRoom() {
		Room tester = new Room("TestRoom");
		Room tester2 = new Room("NotTestRoom");
		assertEquals("Room is room",true,tester.equals(tester));
		assertThat("TestRoom not equals NotTestRoom", true, not(tester.equals(tester2)));
	}
	
	@Test(expected = WorldException.class)
	public void testExceptionIsThrown() throws WorldException {
		Room tester = new Room("TestRoom");
		List<Room> rooms = new ArrayList<>();
		for(int i = 0;i<4;i++){
			Room next = new Room("NotTestRoom");
			rooms.add(next);
		}
		tester.addDoors(rooms);
	}

	@Test
	public void testHasDoor() {
		Room tester = new Room("TestRoom;X;X;X;NotTestRoom;X;X;X;False");
		List<Room> rooms = new ArrayList<>();
		Room next = new Room("NotTestRoom;X;TestRoom;X;X;X;False;X;X");
		rooms.add(next);
		try {
			tester.addDoors(rooms);
		} catch (WorldException e) {
			fail("Test is not working");
		}
		try {
			Direction west = new Direction("west");
			assertThat("TestRoom has door",true,is(tester.hasDoor(west)));
		} catch (InputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testRoomInDirection() {
		Room r1 = new Room("r1;X;X;south;X;X;X;False;X");
		List<Room> rooms = new ArrayList<>();
		Room r2 = new Room("south;r2;X;X;X;false;X;X;X");
		rooms.add(r2);
		try {
			r1.addDoors(rooms);
		} catch (WorldException e1) {
			fail("Test does not work");
		}
		Direction down;
		try {
			down = new Direction(2);
			assertEquals("the room r2 is to the south",true, r1.roomInDirection(down).equals(r2));
		} catch (InputException e) {
			fail("Test does not work");
		} catch (WorldException e) {
			fail("Test does not work");
		}
		



		
	}

	@Test(expected=WorldException.class)
	public void testExceptionAddDoors() throws WorldException {
		List<Door> doors = new ArrayList<>();
		List<Loot> loot = new ArrayList<>();
		List<Creature> creatures = new ArrayList<>();
		String[] data = {"TestRoom","NorthRoom","EastRoom","X","X","False","True","X","X"};
		Room tester = new Room("TestRoom",doors,loot,creatures,data);
		List<Room> rooms = new ArrayList<>();
		tester.addDoors(rooms);
	}
	
	@Test
	public void testAddDoors() {
		List<Door> doors = new ArrayList<>();
		String[] data = {"TestRoom","NorthRoom","EastRoom","X","X","False","True","X","X"};
		Room tester = new Room("TestRoom",doors,null,null,data);
		
		List<Room> rooms = new ArrayList<>();
		Room nRoom = new Room("NorthRoom",null,null,null,null);
		rooms.add(nRoom);
		Room eRoom = new Room("EastRoom",null,null,null,null);
		rooms.add(eRoom);
		
		try {
			tester.addDoors(rooms);
		} catch (WorldException e) {
			fail(e.toString());
		}
		Direction east = null;
		Direction north = null;
		Direction south = null;
		try {
			east = new Direction("east");
			north = new Direction("north");
			south = new Direction("south");
		} catch (InputException e1) {
			fail(e1.toString());
		}
		assertEquals("Door to the East exists",true,tester.hasDoor(east));
		assertEquals("Door to the West exists",true,tester.hasDoor(north));
		assertEquals("Door to the South does not exist",false,tester.hasDoor(south));
	}

	@Test
	public void testAddCreature() {
		Room dest = new Room("Dest");
		Student Filip = new Student("Filip", dest);
		dest.addCreature(Filip);
		try {
			dest.getCreature("Filip");
			assertEquals("There is a creature in the room", true, dest.getCreatures().size() == 1);
		} catch (WorldException e) {
			fail("Test is not working");
		}
		
	}

	@Test
	public void testRemoveCreature() {
		Room dest = new Room("Dest");
		Student Filip = new Student("Filip", dest);
		dest.addCreature(Filip);
		
		dest.removeCreature(Filip);
		assertEquals("There is no creature in the room", true, dest.getCreatures().size() == 0);
		try {
			dest.getCreature("Filip");
			fail("Test is not working");
		} catch (WorldException e) {

		}
	}

	@Test
	public void testRemoveLoot() {
		Room dest = new Room("Dest");
		Book newBook = new Book("Necronomicon;Cthulu;1600;360");
		Key newKey = new Key();
		
		dest.addLoot(newBook);
		dest.addLoot(newKey);
		
		dest.removeLoot(newBook);
		dest.removeLoot(newKey);
		assertEquals("There are no items in the room", true, dest.getLoot().size() == 0);
	}

	@Test
	public void testAddLoot() {
		Room dest = new Room("Dest");
		Book newBook = new Book("Necronomicon;Cthulu;1600;360");
		Key newKey = new Key();
		
		dest.addLoot(newBook);
		dest.addLoot(newKey);
		
		assertEquals("The book is in the room", true, dest.getLoot().get(0) instanceof Book);
		assertEquals("The key is in the room", true, dest.getLoot().get(1) instanceof Key);
		assertEquals("There are 2 items in the room", true, dest.getLoot().size() == 2);
	}

	@Test
	public void testGetCreature() {
		Room dest = new Room("Dest");
		Student Filip = new Student("Filip", dest);
		Student Vasam = new Student("Vasam", dest);
		dest.addCreature(Filip);
		dest.addCreature(Vasam);
		
		try {
			Creature c1 = dest.getCreature("Filip");
			Creature c2 = dest.getCreature("Vasam");
			assertEquals("Get Filip",Filip, c1);
			assertEquals("Get Filip",Vasam, c2);
		} catch (WorldException e) {
			fail("Test is not working");
		}
		

	}

}
