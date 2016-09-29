/**
 * Class Ponyta used to make Squirtle objects
 * @author Ivan Kim
 *
 */
public class Squirtle extends Pokemon implements Water{

	/**
	 * Constructor for a Squirtle object
	 * @param l The level of the Squirtle to be initialized
	 */
	public Squirtle( int l) {
		
		super("Squirtle", l);
	}

	/**
	 * Returns the type of Squirtle
	 * @return type of Squirtle
	 */
	@Override
	public int getType() {
		
		return type;
	}

	/**
	 * returns the damage done by a Special Attack
	 * @param move the Special Attack to be used
	 */
	@Override
	public int specialFight(int move) {
		
		switch(move){
		
			case 1: return waterGun();
			case 2: return bubblebeam();
			case 3: return hydroPump();
		}
		return 0;
	}

	/**
	 * Displays Special Attack Menu
	 */
	@Override
	public void displaySpecialMenu() {
		
		System.out.println("Select Special Move:\n1. Water Gun\n2. Bubblebeam\n3. Hydro Pump");
	}


	/**
	 * Generates the damage of Water Gun
	 * @return damage done by Water Gun
	 */
	@Override
	public int waterGun() {
		
		int dmg = (int) ( Math.random() * 3 ) + super.getLevel();
		System.out.println(super.getName() + " used Water Gun for " + dmg + " damage!");
		return dmg;
	}

	/**
	 * Generates the damage of Bubblebeam
	 * @return damage done by Bubblebeam
	 */
	@Override
	public int bubblebeam() {
		
		int dmg = (int) ( Math.random() * 6 ) + super.getLevel();
		System.out.println(super.getName() + " used Bubblebeam for " + dmg + " damage!");
		return dmg;
	}

	/**
	 * Generates the damage of Hydro Pump
	 * @return damage done by Hydro Pump
	 */
	@Override
	public int hydroPump() {
		
		int dmg = (int) ( Math.random() * 9 ) + super.getLevel();
		System.out.println(super.getName() + " used Hydro Pump for " + dmg + " damage!");
		return dmg;
	}
}
