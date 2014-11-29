package creaturePack;

import worldPack.Room;

public class Sphinx extends Creature{
	
	
	public Sphinx(String name, Room location){
		super(name, location, 1);
	}
	
	public boolean studentIsWorthy(Student student){
		return (student.getHp() >= 180);
	}

	public String lookUpon(){
    	return "A sphinx!\n";
    }
	
}
