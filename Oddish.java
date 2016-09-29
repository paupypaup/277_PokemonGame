/**
 * Class Oddish used to make Oddish objects
 * @author Ivan Kim
 *
 */
public class Oddish extends Pokemon implements Grass{

	/**
	 * Constructor for a Oddish object
	 * @param l The level of the Oddish to be initialized
	 */
	public Oddish( int l) {
		
		super("Oddish", l);
	}

	/**
	 * Returns the type of Oddish
	 * @return the type of Oddish
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
		
			case 1: return vineWhip();
			case 2: return razorLeaf();
			case 3: return solarBeam();
		}
		return 0;
	}

	/**
	 * Displays the Special Attacks menu
	 */
	@Override
	public void displaySpecialMenu() {
		
		System.out.println("Select Special Move:");
		System.out.println("1. Vine Whip");
		System.out.println("2. Razor Leaf");
		System.out.println("3. Solar Beam");
	}

	/**
	 * Generates the damage of Vine Whip
	 * @return damage done by Vine Whip
	 */
	@Override
	public int vineWhip() {
		
		int dmg = (int) ( Math.random() * 3 ) + super.getLevel();
		System.out.println(super.getName() + " used Vine Whip for " + dmg + " damage!");
		return dmg;
	}

	/**
	 * Generates the damage of Razor Leaf
	 * @return damage done by Razor Leaf 
	 */
	@Override
	public int razorLeaf() {
		
		int dmg = (int) ( Math.random() * 6 ) + super.getLevel();
		System.out.println(super.getName() + " used Razor Leaf for " + dmg + " damage!");
		return dmg;
	}

	/**
	 * Generates the damage of Solar Beam
	 * @return damage done by Solar Beam
	 */
	@Override
	public int solarBeam() {
		
		int dmg = (int) ( Math.random() * 9 ) + super.getLevel();
		System.out.println(super.getName() + " used Solar Beam for " + dmg + " damage!");
		return dmg;
	}
}
