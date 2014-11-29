package worldPackTest;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptionPack.WorldException;
import passivePack.Key;
import passivePack.Loot;
import worldPack.Door;
import worldPack.Room;
import worldPack.Universe;
import worldPack.World;

public class WorldTest {

	@Test
	public void testWorld() {
		String path = World.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		World testWorld = new World("worldName", path +"resources/world.txt");
		assertEquals("The world exists", true, testWorld != null);
		
	}

	@Test
	public void testCreateKeys() {
		String path = World.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		World testWorld = new World("worldName", path +"resources/world.txt");
		testWorld.createKeys();
		
		int keys = 0;
		int locks = 0;
		for (Room room : testWorld.getRooms()){
			for (Loot loot : room.getLoot()){
				if (loot instanceof Key){
					keys++;
				}
			}
			for (Door door : room.getDoors()){
				if (!door.isOpen()){
					locks++;
				}
			}
		}
		assertEquals("The number of keys is greater than the locked doors", true, keys>=locks/2);
	}

	@Test
	public void testMoveCreatures() {
		String path = World.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		Universe polacks = new Universe("Polacks",path + "resources/world.txt",path +"resources/books.txt",path +"resources/courses.txt", path +"resources/names.txt", path +"resources/questions.txt");
		Universe compare = new Universe("Polacks",path + "resources/world.txt",path +"resources/books.txt",path +"resources/courses.txt", path +"resources/names.txt", path +"resources/questions.txt");
		boolean same = true;
		for (int i = 0; i < 10; i++){
			polacks.getWorld().moveCreatures(i);
		}
		for (Room room : polacks.getWorld().getRooms()){
			String name = room.getName();
			try {
				Room compRoom = compare.getWorld().getRoom(name);
				if (! compRoom.getCreatures().equals(room.getCreatures()) ){
					same = false;
				}
			} catch (WorldException e) {
				fail("Test not working");
			}
			if (!same){
				break;
			}
		}
		assertEquals("The creatures have moved", true, !same);
	}

}
