package model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;
import java.util.Set;
import java.awt.geom.*;
import java.awt.geom.Point2D.Double;

import model.Input.Dir;

/**
 * Models a Pong game.
 * Only computation is done and no input or output is written or read.
 */
public class MyPongModel implements PongModel{
	private int leftBarKeyPos;
	private int rightBarKeyPos;
	private int leftBarHeight;
	private int rightBarHeight;
	private Point ballPos;
	private Point2D.Double direction;
	private double velocity;
	private int leftScore;
	private int rightScore;
	private Dimension fieldSize;
	private String message;
	private int leftAI;
	private int rightAI;
	private String rightPlayer;
	private String leftPlayer;
	
	/**
	 * Contructor for a Pong model.
	 * @param leftPlayer The name of the left player.
	 * @param rightPlayer The name of the right player.
	 */
	public MyPongModel(String leftPlayer, String rightPlayer) {
		this.fieldSize = new Dimension(12000,7000);
		this.leftBarHeight = this.fieldSize.height/6;
		this.rightBarHeight = this.fieldSize.height/6;
		this.leftScore = 0;
		this.rightScore = 0;
		this.message = leftPlayer + " vs " + rightPlayer;
		this.leftAI = 100000;
		this.rightAI = 100000;
		this.resetBall();
		this.leftPlayer = leftPlayer;
		this.rightPlayer = rightPlayer;
			}
	
	/**
	 * Moves the ball in it's current direction and velocity.
	 */
	private void moveBall() {
		this.ballPos.x += this.direction.x * this.velocity;
		this.ballPos.y -= this.direction.y * this.velocity;
	}

	/**
	 * Moves the bar key's not controlled by a player with an AI algorithm.
	 * @param barSpeed The speed of the bar key.
	 */
	private void ai(double barSpeed) {
			final int delay = 10000;
			if(this.ballPos.y < this.leftBarKeyPos && this.leftBarKeyPos >= this.leftBarHeight/2 && this.leftAI >= delay){
				this.leftBarKeyPos -= barSpeed;
			}
			if(this.ballPos.y > this.leftBarKeyPos && this.leftBarKeyPos < -this.leftBarHeight/2 + this.fieldSize.height && this.leftAI >= delay){
				this.leftBarKeyPos += barSpeed;
			}
			if(this.ballPos.y < this.rightBarKeyPos && this.rightBarKeyPos >= this.rightBarHeight/2 && this.rightAI >= delay){
				this.rightBarKeyPos -= barSpeed;
			}
			if(this.ballPos.y > this.rightBarKeyPos && this.rightBarKeyPos < -this.rightBarHeight/2 + this.fieldSize.height && this.rightAI >= delay){
				this.rightBarKeyPos += barSpeed;
			}		
	}
	
	/**
	 * Check that an angle is not too steep or shallow and corrects it if needed.
	 * @param angle The angle
	 * @param side The side of the board the angle is calculated on.
	 * @return The corrected angle.
	 */
	private double checkAngle(double angle, BarKey side){
		double shift = 0.5;
		angle = (angle % (2 *Math.PI) < 0) ? angle + 2 *Math.PI : angle;
		if(side.equals(BarKey.LEFT))
		{
			if(angle < Math.PI && angle > Math.PI / 2 - shift) {
				angle = Math.PI/2 - shift;
			} else if(angle > Math.PI && angle < Math.PI*3/2 + shift){
				angle = Math.PI *3/2 + shift;
			}
			
		} else {
			if(angle > 0 && angle < Math.PI / 2 + shift) {
				angle = Math.PI / 2 + shift;
			} else if(angle < (2 * Math.PI) && angle > Math.PI*3/2 -shift){
				angle = Math.PI*3/2 - shift;
			}
		}
		return angle;
	}

	/**
	 * Computes one step in the game. 
	 * @param input The set of inputs received. 
	 * @param delta_t The time since last computation.
	 */
	public void compute(Set<Input> input, long delta_t){
		this.moveBall();
		updateBarkey(input, delta_t);
		checkCollision();
		updateScore();
		checkVictory();
	}
	
	/**
	 * Moves the bar keys for each input received.
	 * @param input The set of inputs.
	 * @param barSpeed The speed of the bar keys.
	 */
	private void movePlayerBarKeys(Set<Input> input, double barSpeed){
		for(Input nextInput : input){
			if(nextInput.dir.equals(Input.Dir.UP) && nextInput.key.equals(BarKey.LEFT) &&
					this.leftBarKeyPos >= this.leftBarHeight/2){
				this.leftBarKeyPos -= barSpeed;
				this.leftAI = 0;
			}
			if(nextInput.dir.equals(Input.Dir.DOWN) && nextInput.key.equals(BarKey.LEFT) &&
					this.leftBarKeyPos <= -this.leftBarHeight/2 + this.fieldSize.height){
				this.leftBarKeyPos += barSpeed;
				this.leftAI = 0;
			}
			if(nextInput.dir.equals(Input.Dir.UP) && nextInput.key.equals(BarKey.RIGHT) &&
					this.rightBarKeyPos >= this.rightBarHeight/2){
				this.rightBarKeyPos -= barSpeed;
				this.rightAI = 0;
			}
			if(nextInput.dir.equals(Input.Dir.DOWN) && nextInput.key.equals(BarKey.RIGHT) && 
					this.rightBarKeyPos <= -this.rightBarHeight/2 + this.fieldSize.height){
				this.rightBarKeyPos += barSpeed;
				this.rightAI = 0;
			}
		}
	}

	/**
	 * Updates all bar keys in the model.
	 * @param input The set of inputs received.
	 * @param delta_t The time since last computation.
	 */
	private void updateBarkey(Set<Input> input, long delta_t) {
		//Update BarKey
		if(delta_t == 0){
			return;
		}
		final double barSpeed = 5000/delta_t;
		movePlayerBarKeys(input, barSpeed);
		this.leftAI += delta_t;
		this.rightAI += delta_t;
		this.ai(barSpeed);
	}

	/**
	 * Checks if there has been any collision with the ball and any other object.
	 * Updates the ball direction and velocity accordingly.
	 */
	private void checkCollision(){
		hitBarkey();
		hitWall();
	}
	
	/**
	 * Calculates the direction of the ball if it would hit the upper or lower wall.
	 */
	private void hitWall(){
		if (ballPos.y >= this.fieldSize.getHeight()){
			this.direction.y = -this.direction.y;
		}
		
		if (ballPos.y <= 0){
			this.direction.y = -this.direction.y;
		}
	}
	
	/**
	 * Checks if the ball has hit any bar key and changes its direction and speed accordingly.
	 */
	private void hitBarkey(){
		if (ballPos.x <= 150 && ballPos.y < this.leftBarKeyPos + 120 + this.leftBarHeight/2 && 
				ballPos.y > this.leftBarKeyPos - 120 - this.leftBarHeight/2){
			this.ballPos.x = 151;
			calculateLeftHitDirection();
			updateVelocity();
		}
		if (this.ballPos.x >= this.fieldSize.width - 150 && ballPos.y < this.rightBarKeyPos + 120 + this.rightBarHeight/2 && 
				ballPos.y > this.rightBarKeyPos - 120 - this.rightBarHeight/2){
			this.ballPos.x = this.fieldSize.width - 151;
			calculateRightHitDirection();
			updateVelocity();
		}
	}
	
	/**
	 * Updates the velocity unless it is above the maximum speed.
	 */
	private void updateVelocity(){
		if (this.velocity <= 500){
			this.velocity *= 1.2;
		}
	}
	
	/**
	 * Calculates the direction of the ball if it would hit the left bar key.
	 */
	private void calculateLeftHitDirection(){
		if (this.leftBarKeyPos == this.ballPos.y) {
			this.direction.x = -this.direction.x;  
		} else if (this.leftBarKeyPos <= this.ballPos.y){
			double diff = (this.ballPos.y - this.leftBarKeyPos) / (this.leftBarHeight/2.0)*(Math.PI/4);
			double inAngle = Math.PI - Math.asin(this.direction.y);
			double outAngle = Math.PI - inAngle - diff;
			outAngle = this.checkAngle(outAngle,BarKey.LEFT);
			this.direction.x = Math.cos(outAngle);
			this.direction.y = Math.sin(outAngle);
		}
		else {
			double diff = (this.leftBarKeyPos - this.ballPos.y) / (this.leftBarHeight/2.0)*(Math.PI/4);
			double inAngle = Math.PI - Math.asin(this.direction.y);
			double outAngle = Math.PI - inAngle + diff;
			outAngle = this.checkAngle(outAngle,BarKey.LEFT);
			this.direction.x = Math.cos(outAngle);
			this.direction.y = Math.sin(outAngle);
		}
	}
	
	/**
	 * Calculates the direction of the ball if it would hit the right bar key.
	 */
	private void calculateRightHitDirection(){
		if (this.rightBarKeyPos == this.ballPos.y) {
			this.direction.x = -this.direction.x;  
		} else if (this.rightBarKeyPos <= this.ballPos.y){
			double diff = (this.ballPos.y - this.rightBarKeyPos) / (this.rightBarHeight/2.0)*(Math.PI/4);
			double inAngle = Math.asin(this.direction.y);
			double outAngle = Math.PI - inAngle + diff;
			//Check maximum outAngle
			outAngle = this.checkAngle(outAngle,BarKey.RIGHT);
			this.direction.x = Math.cos(outAngle);
			this.direction.y = Math.sin(outAngle);
		}
		else {
			double diff = (this.rightBarKeyPos - this.ballPos.y) / (this.rightBarHeight/2.0)*(Math.PI/4);
			double inAngle = Math.asin(this.direction.y);
			double outAngle = Math.PI - inAngle - diff;
			outAngle = this.checkAngle(outAngle,BarKey.RIGHT);
			this.direction.x = Math.cos(outAngle);
			this.direction.y = Math.sin(outAngle);
		}
	}
	
	/**
	 * Updates the score if a goal has been scored and helps the player who did not score by increasing his bar key's height.
	 */
	private void updateScore() {
		if (ballPos.x < 0 && !(ballPos.y < this.leftBarKeyPos + this.leftBarHeight/2 && 
				ballPos.y > this.leftBarKeyPos - this.leftBarHeight/2)){
			
			this.rightScore++;
			this.leftBarHeight += 100;
			this.resetBall();
		}
		if (ballPos.x > this.fieldSize.width && !(ballPos.y < this.rightBarKeyPos + this.rightBarHeight/2 && 
				ballPos.y > this.rightBarKeyPos - this.rightBarHeight/2)){
			this.leftScore++;
			this.rightBarHeight += 100;
			this.resetBall();
		}
	}
	
	/**
	 * Checks if a player has won.
	 */
	private void checkVictory(){
		if(leftScore >= 10){
			this.message = (this.leftPlayer + " Wins!");
			this.leftScore = 0;
			this.rightScore = 0;
			this.rightBarHeight = this.fieldSize.height/6;
			this.leftBarHeight = this.fieldSize.height/6;
		}
		if(rightScore >= 10){
			this.message = (this.rightPlayer + " Wins!");
			this.leftScore = 0;
			this.rightScore = 0;
			this.rightBarHeight = this.fieldSize.height/6;
			this.leftBarHeight = this.fieldSize.height/6;
		}
	}
	
	/**
	 * Resets the ball and calculates a new direction for it.
	 */
    private void resetBall() {
    	this.leftBarKeyPos = this.fieldSize.height/2;
		this.rightBarKeyPos = this.fieldSize.height/2;
		this.ballPos = new Point(this.fieldSize.width/2, this.fieldSize.height/2);
		Random rand = new Random();
		int degrees = rand.nextInt(60);
		if (degrees > 30){
			degrees = -degrees/2;
		}
		int i = 1;
		boolean left = rand.nextBoolean();
		if (left){
			i = -1;
		}
		this.direction = new Point2D.Double(0,0);
		this.direction.y = (Math.sin(Math.PI * degrees/180));
		this.direction.x = (Math.cos(Math.PI * degrees/180) * i);
		if(direction.y == Math.PI/2 || direction.y == -Math.PI/2){
			direction.y = Math.PI;
		}
		this.velocity = 100;

	}

	/**
     * Gets the position of a bar key.
     * @param k The bar key to get from.
     * @return The positions of the various items on the board
     */
    public int getBarPos(BarKey k){
    	if(k.equals(BarKey.LEFT)){
    		return this.leftBarKeyPos;
    	} else {
    		return this.rightBarKeyPos;
    	}
    }
    
    /**
     * Gets the height of a bar key.
     * @param k The bar key.
     * @return The height.
     */
    public int getBarHeight(BarKey k){
    	if(k.equals(BarKey.LEFT)){
    		return this.leftBarHeight;
    	} else {
    		return this.rightBarHeight;
    	}
    }
    
    /**
     * Gets the position of the ball.
     * @return The position.
     */
    public Point getBallPos(){
    	return this.ballPos;
    }

    /**
     * Will output information about the state of the game to be
     * displayed to the players
     * @return The information.
     */
    public String getMessage(){
    	return this.message;
    }

    /**
     * Gets the score of the player controlling the bar key k.
     * @param k The bar key.
     * @return The score.
     */
    public String getScore(BarKey k){
    	if(k.equals(BarKey.LEFT)){
    		return Integer.toString(this.leftScore);
    	} else {
    		return Integer.toString(this.rightScore);
    	}
    }
    
    /**
     * Gets the field size of the game.
     * @return The field size.
     */
    public Dimension getFieldSize(){
    	return this.fieldSize;
    } 
}
