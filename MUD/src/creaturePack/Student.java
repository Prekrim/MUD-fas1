package creaturePack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import passivePack.Course;
import passivePack.Inventory;
import worldPack.Room;


public class Student extends Creature{

    private int HP;
    protected Inventory backpack = new Inventory();
    private List<Course> finishedCourses = new ArrayList<Course>();
    private List<Course> activeCourses = new ArrayList<Course>();

    public Student(String name, int HP, Room location, int moveSpd){
    	super(name, location, moveSpd);
    	this.HP = HP;
    }
    
    public Student(String name, Room location){
    	super(name, location, new Random().nextInt(5)+1);
    	this.HP = 60;
    }
    
    public List<Course> getFinishedCourses(){
    	return this.finishedCourses;
    }
    
    public List<Course> getActiveCourses(){
    	return this.activeCourses;
    }
    
    public void addCourse(Course newCourse){
    	activeCourses.add(newCourse);
    }
    
	public void failCourse(Course course) {
		this.finishedCourses.remove(course);
		this.activeCourses.add(course);
		this.HP -= course.getHP();
	}
    
    public void finishCourse(Course finishedCourse){
    	activeCourses.remove(finishedCourse);
    	finishedCourses.add(finishedCourse);
    	this.HP += finishedCourse.getHP();
    }

    public Inventory getBackpack(){
    	return this.backpack;
    }
    
    public String toString(){
    	return this.getName();
    }
    
    public int getHp(){
    	return this.HP;
    }

    public String lookUpon(){
    	return "A student named " + this.getName() + "\n";
    }
    
    public void setHp(int i){
    	this.HP = i;
    }
}

