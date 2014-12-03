package passivePack;

/**
 * Defines abstract loot.
 * Handles the weight of loot.
 *
 */
public abstract class Loot{
	/**
	 *Determines the weight of this loot
	 */
	int weight;
	
	/**
	 * @return The weight of the loot
	 */
	public int getWeight(){
		return this.weight;
		}
}
