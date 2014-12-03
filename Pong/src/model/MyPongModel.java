package model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;
import java.util.Set;
import java.awt.geom.*;
import java.awt.geom.Point2D.Double;

import model.Input.Dir;

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
	private void moveBall() {
		this.ballPos.x += this.direction.x * this.velocity;
		this.ballPos.y -= this.direction.y * this.velocity;
	}

	private	void ai(double barSpeed) {
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
	
	public void compute(Set<Input> input, long delta_t){
		/*
		 * 
		 * 
		 * 
		*/
		//Update BarKey
		final double barSpeed = 5000/delta_t;
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
		this.leftAI += delta_t;
		this.rightAI += delta_t;
		this.ai(barSpeed);
		//Update Ball
		this.moveBall();

		// Hit BarKey
		if (ballPos.x <= 150 && ballPos.y < this.leftBarKeyPos + 120 + this.leftBarHeight/2 && 
				ballPos.y > this.leftBarKeyPos - 120 - this.leftBarHeight/2){
			this.ballPos.x = 151;
			if (this.leftBarKeyPos == this.ballPos.y) {
				this.direction.x = -this.direction.x;  
			} else if (this.leftBarKeyPos <= this.ballPos.y){
				double diff = (this.ballPos.y - this.leftBarKeyPos) / (this.leftBarHeight/2.0)*(Math.PI/4);
				double inAngle = Math.PI - Math.asin(this.direction.y);
				double outAngle = Math.PI - inAngle - diff;
				// Check maximum outAngle
				outAngle = this.checkAngle(outAngle,BarKey.LEFT);
				this.direction.x = Math.cos(outAngle);
				this.direction.y = Math.sin(outAngle);
			}
			else {
				double diff = (this.leftBarKeyPos - this.ballPos.y) / (this.leftBarHeight/2.0)*(Math.PI/4);
				double inAngle = Math.PI - Math.asin(this.direction.y);
				double outAngle = Math.PI - inAngle + diff;
				// Check maximum outAngle
				outAngle = this.checkAngle(outAngle,BarKey.LEFT);
				this.direction.x = Math.cos(outAngle);
				this.direction.y = Math.sin(outAngle);
			}
			if (this.velocity <= 500){
				this.velocity *= 1.2;
			}
		}
		
		if (this.ballPos.x >= this.fieldSize.width - 150 && ballPos.y < this.rightBarKeyPos + 120 + this.rightBarHeight/2 && 
				ballPos.y > this.rightBarKeyPos - 120 - this.rightBarHeight/2){
			this.ballPos.x = this.fieldSize.width - 151;
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
			if (this.velocity <= 500){
				this.velocity *= 1.2;
			}
		}
		
		if (ballPos.y >= this.fieldSize.getHeight()){
			this.direction.y = -this.direction.y;
		}
		
		if (ballPos.y <= 0){
			this.direction.y = -this.direction.y;
		}
		
		//this.leftBarKeyPos = this.ballPos.y;
		//this.rightBarKeyPos = this.ballPos.y;
		
		// Score
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
		if(leftScore >= 10){
			message = (leftPlayer + " Wins!");
			leftScore = 0;
			rightScore = 0;
		}
		if(rightScore >= 10){
			message = (rightPlayer + " Wins!");
			leftScore = 0;
			rightScore = 0;
		}
	}

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
     * getters that take a BarKey LEFT or RIGHT
     * and return positions of the various items on the board
     */
    public int getBarPos(BarKey k){
    	if(k.equals(BarKey.LEFT)){
    		return this.leftBarKeyPos;
    	} else {
    		return this.rightBarKeyPos;
    	}
    }
    
    public int getBarHeight(BarKey k){
    	if(k.equals(BarKey.LEFT)){
    		return this.leftBarHeight;
    	} else {
    		return this.rightBarHeight;
    	}
    }
    public Point getBallPos(){
    	return this.ballPos;
    }

    /**
     * Will output information about the state of the game to be
     * displayed to the players
     */
    public String getMessage(){
    	return this.message;
    }

    /**
     * getter for the scores.
     */
    public String getScore(BarKey k){
    	if(k.equals(BarKey.LEFT)){
    		return Integer.toString(this.leftScore);
    	} else {
    		return Integer.toString(this.rightScore);
    	}
    }
    
    /**
     * a valid implementation of the model will keep the field size
     * will remain constant forever.
     */
    public Dimension getFieldSize(){
    	return this.fieldSize;
    } 
}
