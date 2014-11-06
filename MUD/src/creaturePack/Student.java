package creaturePack;

import java.util.ArrayList;
import java.util.List;

import passivePack.Course;
import passivePack.Inventory;
import worldPack.Room;


public class Student extends Creature{

    private int HP;
    protected Inventory backpack = new Inventory();
    private List<Course> finishedCourses = new ArrayList<Course>();
    private List<Course> activeCourses = new ArrayList<Course>();

    public Student(String name, int HP, Room location){
	super(name, location);
	this.HP = HP;
    }
    
    public Student(String name, Room location){
    	super(name, location);
    	this.HP = 60;
    }
    
    public void addCourse(Course newCourse){
    	activeCourses.add(newCourse);
    }
    
    public void finishCourse(Course finishedCourse){
    	activeCourses.remove(finishedCourse);
    	finishedCourses.add(finishedCourse);
    }

    public Inventory getBackpack(){
    	return this.backpack;
    }
    
    public String toString(){
    	return this.getName();
    }
}

