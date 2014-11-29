package passivePack;

import java.util.Random;


public class Question {
	private Course course;
	private String question;
	private String[] answers;
	private int correct;
	
	public Question(Course course, String question, String[] answers, int correct){
		this.course = course;
		this.question = question;
		this.correct = correct;
		this.answers = answers;
	}
	
	public Question(Course course, String question, String[] answers){
		this.course = course;
		Random rand = new Random();
		this.correct = rand.nextInt(2);
		this.question = question;
		this.answers = answers;
	}
	
	public Course getCourse(){
		return this.course;
	}
	
	public void printString(boolean gotBook){
		System.out.println(this.question);
		boolean deleted = false;
		
		if (gotBook == true && this.correct != 0){
			System.out.println("");
			deleted = true;
		}else{
			System.out.println("1: " + answers[0]);
			}
		if (gotBook == true && this.correct != 1 && !deleted){
			System.out.println("");
			deleted = true;
		}else{
			System.out.println("2: " + answers[1]);
		}
		
		System.out.println("3: " + answers[2]);
	}

	public boolean checkAnswer(String i){
		return (Integer.toString(this.correct+1).equals(i));
	}
}
