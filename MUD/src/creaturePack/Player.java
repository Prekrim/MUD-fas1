package creaturePack;

import java.awt.List;
import java.util.ArrayList;

import exceptionPack.InventoryException;
import passivePack.Book;
import passivePack.Inventory;
import worldPack.Room;

public class Player extends Student{
	
	public Player(String name, Room location) {
		super(name, 60, location);
		
	}
	
	public void throwBook(Book book){
		if (this.backpack.getBooks().contains(book)){
			try {
				backpack.removeBook(book);
			} catch (InventoryException e) {
				e.printStackTrace();
				System.out.println("Could not throw book");
			}
		}
	}

}
