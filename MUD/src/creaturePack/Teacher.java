package creaturePack;

import java.util.Random;

import passivePack.Course;
import passivePack.Question;
import worldPack.Room;

public class Teacher extends Creature{

    private Course course;
    private Question question;
    

    public Teacher(final String name,final Course course, Room location, Question question){
	super(name, location, new Random().nextInt(5)+1);
	this.course = course;
	this.question = question;
    }
    
    public String toString(){
    	return this.getName();
    }
    
    public String lookUpon(){
    	return "A teacher named " + this.getName() + "\n";
    }
    
    public Course getCourse(){
    	return this.course;
    }

	public Question getQuestion() {
		return this.question;
	}
}  