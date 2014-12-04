package creaturePack;

import worldPack.Room;
/**
 * Defines a Sphinx
 */
public class Sphinx extends Creature{
	
    /**
     * Contructor for sphinx.
     * @param name The name of the sphinx.
     * @param location The location of the sphinx.
     */
	public Sphinx(String name, Room location){
		super(name, location, 1);
	}
	
    /**
     * Checks if student has 180 or more credits.
     * @param student The student to be checked.
     * @return True if the student has 180 or more credits, else false.
     */
	public boolean studentIsWorthy(Student student){
		return (student.getHp() >= 180);
	}

    /*
     * Specializes the lookUpon function inherited from Creature. Returns a string representation of the sphinx.
     * @return The string representation of the sphinx. 
     */
	public String lookUpon(){
    	return "A sphinx!\n";
    }
	
}
