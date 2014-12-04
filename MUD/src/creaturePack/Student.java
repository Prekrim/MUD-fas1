package creaturePack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import passivePack.Course;
import passivePack.Inventory;
import worldPack.Room;

/**
 * Defines a student with credits, backpack and active/finished courses.
 */
public class Student extends Creature{

    private int HP;
    protected Inventory backpack = new Inventory();
    private List<Course> finishedCourses = new ArrayList<Course>();
    private List<Course> activeCourses = new ArrayList<Course>();

    /**
     * Constructor for a student.
     * @param name The name of the student.
     * @param HP The credits of the student.
     * @param location The room the student is in.
     * @param moveSpd The speed of the student.
     */
    public Student(String name, int HP, Room location, int moveSpd){
    	super(name, location, moveSpd);
    	this.HP = HP;
    }
    
    /**
     * Constructor for a student. Sets the credits to 60 and randomizes the speed between 1 and 5.
     * @param name The name of the student.
     * @param location The room the student is in.
     */
    public Student(String name, Room location){
    	super(name, location, new Random().nextInt(5)+1);
    	this.HP = 60;
    }

    /**
     * Gets the student's finished courses as a List
     * @return The finsihed courses.
     */    
    public List<Course> getFinishedCourses(){
    	return this.finishedCourses;
    }

    /**
     * Gets the student's active courses as a List
     * @return The active courses.
     */        
    public List<Course> getActiveCourses(){
    	return this.activeCourses;
    }

    /**
     * Adds a course to the student as active.
     * @param newCourse Course to be added.
     */
    public void addCourse(Course newCourse){
    	activeCourses.add(newCourse);
    }
    
    /**
     * removes course from the student's finished courses if it contains it. 
     * Adds course to the student's active courses.
     * Subracts the course's credits from the students. 
     * @param course The course to use.    
     */
    public void failCourse(Course course) {
        this.finishedCourses.remove(course);
        this.activeCourses.add(course);
        this.HP -= course.getHP();
    }
    
    /**
     * removes a course from the student's active courses and adds it to the finished courses.
     * Adds the course's credits to student.
     * @param finishedCourse The course to use.
     */
    public void finishCourse(Course finishedCourse){
    	activeCourses.remove(finishedCourse);
    	finishedCourses.add(finishedCourse);
    	this.HP += finishedCourse.getHP();
    }

    /**
     * Gets the students inventory.
     * @return The student's inventory.
     */
    public Inventory getBackpack(){
    	return this.backpack;
    }
    
    public String toString(){
    	return this.getName();
    }
    
    /**
     * Gets the credits of the student.
     * @return The credits of the student.
     */
    public int getHp(){
    	return this.HP;
    }

    /**
     * Specializes the lookUpon method inherited from Creature. Returns a string representation of the student.
     * @return The string representation of the student.
     */
    public String lookUpon(){
    	return "A student named " + this.getName() + "\n";
    }
    
    /**
     * Sets the credits of a student to i.
     * @param i The number of credits.
     */
    public void setHp(int i){
    	this.HP = i;
    }
}

