/**
 * Class Pikachu used to make Pikachu objects
 * @author Ivan Kim
 *
 */
public class Pikachu extends Pokemon implements Electric{
	
	/**
	 * Constructor for a Squirtle object
	 * @param l The level of the Squirtle to be initialized
	 */
	public Pikachu(int level){
		super("Pikachu", level);
	}
	
	/**
	 * Returns the type of Pikachu
	 * @return type of Pikachu
	 */
	@Override
	public int getType() {
		return type;
	}
	
	/**
	 * Displays Special Attack Menu
	 */
	@Override
	public void displaySpecialMenu(){
		
		System.out.println("Select Special Move:");
		System.out.println("1. Thunder Shock");
		System.out.println("2. Thunderbolt");
		System.out.println("3. Thunder");
	}
	
	/**
	 * returns the damage done by a Special Attack
	 * @param move the Special Attack to be used
	 */
	@Override
	public int specialFight(int move){
		
		switch(move){
		
			case 1: return thunderShock();
			case 2: return thunderBolt();
			case 3: return thunder();
		}
		return 0;
	}
	/**
	 * Generates the damage of Thunder Shock
	 * @return damage done by Thunder Shock
	 */
	@Override
	public int thunderShock() {
		
		int dmg = (int) ( Math.random() * 3 ) + super.getLevel();
		System.out.println(super.getName() + " used Thunder Shock for " + dmg + " damage!");
		return dmg;
	}

	/**
	 * Generates the damage of Thunderbolt
	 * @return damage done by Thunderbolt
	 */
	@Override
	public int thunderBolt() {
		
		int dmg = (int) ( Math.random() * 6 ) + super.getLevel();
		System.out.println(super.getName() + " used Thunderbolt for " + dmg + " damage!");
		return dmg;
	}

	/**
	 * Generates the damage of Thunder
	 * @return damage done by Thunder
	 */
	@Override
	public int thunder() {
		
		int dmg = (int) ( Math.random() * 9 ) + super.getLevel();
		System.out.println(super.getName() + " used Thunder for " + dmg + " damage!");
		return dmg;
	}
}