package passivePackTest;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptionPack.InputException;
import exceptionPack.InventoryException;
import passivePack.Book;
import passivePack.Inventory;
import passivePack.Key;
import worldPack.Room;

public class InventoryTest {

	@Test
	public void testInventory() {
		Inventory inv = new Inventory();
		assertEquals("Inv exists", true, inv != null);
		assertEquals("Inv has standard capacity", true, inv.getCurrentCapacity() == 10);
	}

	@Test
	public void testAdd() {
		Key newKey = new Key();
		Inventory inv = new Inventory();
		try {
			inv.add(newKey);
			assertEquals("newKey is in inventory", true, inv.contains(newKey));
		} catch (InventoryException e) {
			fail("Test not working");
		}
	}

	@Test
	public void testRemove() {
		Key newKey = new Key();
		Inventory inv = new Inventory();
		Book newBook = new Book("Necronomicon;Cthulu;1600;0");
		try {
			inv.add(newKey);
			inv.add(newBook);
			assertEquals("newKey is in inventory", true, inv.contains(newKey));
			assertEquals("newBook is in inventory", true, inv.contains(newBook));
			inv.remove(newKey);
			inv.remove(newBook);
			assertEquals("newKey is not in inventory", false, inv.contains(newKey));
			assertEquals("newBook is not in inventory", false, inv.contains(newBook));
		} catch (InventoryException e) {
			fail("Test not working");
		}
	}

	@Test
	public void testUseKey() {
		Inventory inv = new Inventory();
		Key newKey = new Key();
		Key newKey2 = new Key();
		
		try {
			inv.add(newKey);
			inv.add(newKey2);
			assertEquals("newKey is in inventory", true, inv.contains(newKey));
			assertEquals("newKey2 is in inventory", true, inv.contains(newKey2));
			inv.useKey();
			assertEquals("newKey is not in inventory", false, inv.contains(newKey));
			assertEquals("newKey2 is in inventory", true, inv.contains(newKey2));
		} catch (InventoryException e) {
			fail("Test not working");
		}
		
	}

	@Test
	public void testThrowLoot() {
		Inventory inv = new Inventory();
		Room dest = new Room("Dest");
		Book newBook = new Book("Necronomicon;Cthulu;1600;10");
		
		try {
			inv.add(newBook);
			assertEquals("newBook is in inventory", true, inv.contains(newBook));
			inv.throwLoot(dest, newBook);
			assertEquals("newBook is not in inventory", false, inv.contains(newBook));
			assertEquals("newBook is in the room", true, dest.getLoot().contains(newBook));
		} catch (InventoryException e) {
			fail("Test not working");
		}
	}

	@Test
	public void testContains() {
		Inventory inv = new Inventory();
		Book newBook = new Book("Necronomicon;Cthulu;1600;10");
		
		try {
			inv.add(newBook);
			assertEquals("newBook is in inventory", inv.getBooks().contains(newBook), inv.contains(newBook));
		} catch (InventoryException e) {
			fail("Test not working");
		}
		
	}

	@Test
	public void testGetBookString() {
		Inventory inv = new Inventory();
		Book newBook = new Book("Necronomicon;Cthulu;1600;0");
		Book newBook2 = new Book("Att kapa ett flygpan;Flügmeih Tieldubai;1991;1");
		
		try {
			inv.add(newBook);
			inv.add(newBook2);
			assertEquals("We recieve newBook", true, inv.getBook("Necronomicon").equals(newBook));
			assertEquals("We recieve newBook2", true, inv.getBook("Att kapa ett flygpan").equals(newBook2));
		} catch (InventoryException e) {
			fail("Test not working");
		} catch (InputException e) {
			fail("Test not working");
		}
	}

	@Test
	public void testGetBookInt() {
		Inventory inv = new Inventory();
		Book newBook = new Book("Necronomicon;Cthulu;1600;0");
		Book newBook2 = new Book("Att kapa ett flygpan;Flügmeih Tieldubai;1991;1");
		
		try {
			inv.add(newBook);
			inv.add(newBook2);
			assertEquals("We recieve newBook", true, inv.getBook(1).equals(newBook));
			assertEquals("We recieve newBook2", true, inv.getBook(2).equals(newBook2));
		} catch (InventoryException e) {
			fail("Test not working");
		} catch (InputException e) {
			fail("Test not working");
		}
	}

}
