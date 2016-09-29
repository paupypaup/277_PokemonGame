/**
 * Class Ponyta used to make Ponyta objects
 * @author Ivan Kim
 *
 */
public class Ponyta extends Pokemon implements Fire{

	/**
	 * Constructor for a Ponyta object
	 * @param l The level of the Ponyta to be initialized
	 */
	public Ponyta(int l) {
		
		super("Ponyta", l);
	}
	
	/**
	 * Displays Special Attack Menu
	 */
	@Override
	public void displaySpecialMenu(){
		
		System.out.println("Select Special Move:");
		System.out.println("1. Ember");
		System.out.println("2. Fire Blast");
		System.out.println("3. Fire Punch");

	}
	
	/**
	 * returns the damage done by a Special Attack
	 * @param move the Special Attack to be used
	 */
	@Override
	public int specialFight(int move){
		
		switch(move){
		
			case 1: return ember();
			case 2: return fireBlast();
			case 3: return firePunch();
		}
		return 0;
	}

	/**
	 * Generates the damage of Ember
	 * @return damage done by Ember
	 */
	public int ember(){
		
		int dmg = (int) ( Math.random() * 3 ) + super.getLevel();
		System.out.println(super.getName() + " used ember for " + dmg + " damage!");
		return dmg;
		
	}

	/**
	 * Generates the damage of Fire Blast
	 * @return damage done by Fire Blast
	 */
	public int fireBlast(){
		
		int dmg = (int) ( Math.random() * 6 ) + super.getLevel();
		System.out.println(super.getName() + " used fire blast for " + dmg + " damage!");
		return dmg;
	}

	/**
	 * Generates the damage of Fire Punch
	 * @return damage done by Fire Punch
	 */
	public int firePunch(){
		
		int dmg = (int) ( Math.random() * 9 ) + super.getLevel();
		System.out.println(super.getName() + " used Fire Punch for " + dmg + " damage!");
		return dmg;
	}

	/**
	 * Returns the type of Ponyta
	 * @return type of Ponyta
	 */
	@Override
	public int getType() {
		
		return type;
	}
}
