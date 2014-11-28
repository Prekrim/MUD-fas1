package model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;
import java.util.Set;

import model.Input.Dir;

public class MyPongModel implements PongModel{
	private int leftBarKeyPos;
	private int rightBarKeyPos;
	private int leftBarHeight;
	private int rightBarHeight;
	private Point ballPos;
	private Point direction;
	private double velocity;
	private int leftScore;
	private int rightScore;
	private Dimension fieldSize;
	private String message;
	private int leftAI;
	private int rightAI;
	
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
			}

	private	void ai(double barSpeed) {
			final int delay = 3000;
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
		this.ballPos.x += this.direction.x * this.velocity;
		this.ballPos.y += this.direction.y * this.velocity;
		
		// Hit BarKey
		if (ballPos.x <= 150 && ballPos.y < this.leftBarKeyPos + 120 + this.leftBarHeight/2 && 
				ballPos.y > this.leftBarKeyPos - 120 - this.leftBarHeight/2){
			//this.direction.x = -this.direction.x;
			
			
			double angle = Math.atan(direction.y/direction.x);
			if(this.direction.y <= 0 && this.direction.x >= 0){
				angle = Math.atan(direction.y/direction.x);
			} else if(this.direction.y > 0 && this.direction.x >= 0){
				angle = 2*Math.PI + Math.atan(direction.y/direction.x);
			} else if(this.direction.y <= 0 && this.direction.x < 0){
				angle = Math.PI + Math.atan(direction.y/direction.x);
			} else if(this.direction.y > 0 && this.direction.x < 0){
				angle = Math.PI + Math.atan(direction.y/direction.x);
			}
			long difference = (ballPos.y-leftBarKeyPos);
			//if (difference < 0){
				this.direction.y = (int) (100 * Math.sin(angle * difference));
				this.direction.x = (int) (100 * Math.cos(angle * difference));
			/*} else if( difference > 0){
				
			} else {
				this.direction.y = -this.direction.y;
			}*/
			
			if (this.velocity <= 5){
				this.velocity += 0.5;
			}
		}
		
		if (ballPos.x >= this.fieldSize.width - 150 && ballPos.y < this.rightBarKeyPos + 120 + this.rightBarHeight/2 && 
				ballPos.y > this.rightBarKeyPos - 120 - this.rightBarHeight/2){
			this.direction.x = -this.direction.x;
			if (this.velocity <= 5){
				this.velocity += 0.5;
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
		
		this.direction = new Point(0,0);
		this.direction.y = (int) (100 * Math.sin(Math.PI * degrees/180));
		this.direction.x = (int) (100 * Math.cos(Math.PI * degrees/180) * i);
		if(direction.y == Math.PI/2 || direction.y == -Math.PI/2){
			direction.y = (int) Math.PI;
		}
		this.velocity = 1;

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
