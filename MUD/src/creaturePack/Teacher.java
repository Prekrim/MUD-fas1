package creaturePack;

import worldPack.Room;

public class Teacher extends Creature{

    private String course = null;
    private String home = null;
    

    public Teacher(final String name,final String course, Room location){
	super(name, location);
	this.course = course;
	this.home = home;


    }
}  