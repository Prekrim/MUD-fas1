package passivePack;

import java.util.Random;


/**
 * Defines a question.
 */
public class Question {
	
	/**The question's corresponding course.
	 * 
	 */
	private Course course;
	
	/**The string representation of the question.
	 * 
	 */
	private String question;
	
	/**The different answers of the question.
	 * 
	 */
	private String[] answers;
	
	/**The index of the correct answer.
	 * 
	 */
	private int correct;
	
	/** Constructs a question.
	 * @param course A course that will be linked to the question.
	 * @param question The question string.
	 * @param answers The different answers.
	 * @param correct The correct answer by index.
	 */
	public Question(Course course, String question, String[] answers, int correct){
		this.course = course;
		this.question = question;
		this.correct = correct;
		this.answers = answers;
	}
	
	/** Construct a question.
	 * Randomizes the correct answer from the given String array answers.
	 * @param course A course that will be linked to the question.
	 * @param question The question string.
	 * @param answers The different answers.
	 */
	public Question(Course course, String question, String[] answers){
		this.course = course;
		Random rand = new Random();
		this.correct = rand.nextInt(answers.length-1);
		this.question = question;
		this.answers = answers;
	}
	
	/** Gets the course from the question.
	 * @return The question's corresponding course.
	 */
	public Course getCourse(){
		return this.course;
	}
	
	/** Prints the question in standard out.
	 * If gotBook is true one false answer is not printed.
	 * @param gotBook A boolean.
	 */
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

	/** Checks if the parameter i is a correct answer's index.
	 * @param i The index to be checked.
	 * @return true if i is a correct answer's index.
	 */
	public boolean checkAnswer(String i){
		return (Integer.toString(this.correct+1).equals(i));
	}
}
