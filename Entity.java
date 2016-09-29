import java.io.Serializable;

/**
 * An abstract Entity class used to create Trainers and Pokemons
 * @author Ivan
 *
 */
public abstract class Entity implements Serializable{

	/**
	 * Name of Entity
	 */
	private String name;
	
	/**
	 * Current hit points of the Entity
	 */
	private int hp;
	
	/**
	 * Max hit points of the Entity
	 */
	private int maxHp;
	
	/**
	 * Constructor for an Entity
	 * @param n the name to be passed in
	 * @param h	the hit points to be passed in
	 */
	public Entity(String n, int h){
		
		name = n;
		maxHp = h;
		hp = h;
	}
	
	/**
	 * gets the name of the Entity
	 * @return the Entity name
	 */
	public String getName(){
		
		return name;
	}
	
	/**
	 * Gets the current HP of the Entity
	 * @return hp of Entity
	 */
	public int getHp(){
		
		return hp;
	}
	
	/**
	 * Gets the max HP of the Entity
	 * @return max HP of Entity
	 */
	public int getMaxHp(){
		
		return maxHp;
	}
	
	/**
	 * Deals damage to the Entity
	 * @param hit the HP to be removed
	 * @return the remaining HP
	 */
	public int loseHp(int hit){
		
		hp = hp - hit;
		if (hp < 0){
			hp = 0;
		}
		return hp;
	}
	
	/**
	 * Heals Entity for a certain amount	
	 * @param heal the amount of HP to gain
	 * @return the new HP
	 */
	public int gainHp(int heal){
		
		if ( (hp + heal) > maxHp){
			
			hp = maxHp;
			return hp;
		}
		hp = hp + heal;
		return hp;
	}
	
	/**
	 * Increases the max HP of the Entity
	 */
	public void incMaxHp(){
		
		maxHp = maxHp + 3;
	}
}
