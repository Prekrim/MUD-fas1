package passivePack;

public class Key extends Loot{
	
	public Key(){
	this.weight = 1;
	}
		
	public String toString(){
		return ("A key");
	}
	
	public boolean equals(Key key){
		return (key != null);
	}
}
