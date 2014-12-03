package passivePack;


/**
 * Defines a key.
 *
 */
public class Key extends Loot{
	
	/** Constructor for the Key class
	 * 
	 */
	public Key(){
	this.weight = 1;
	}
		
	/** Returns a string representation of a key
	 * @return "A key" 
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return ("A key");
	}
	
	/** Compares the object key to the Key class
	 * @param key Object that is compared
	 * @return true if key is of class Key, else false
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object key){
		if (key == null){return false;}
		return (key instanceof Key);
	}
}
