/**
 * Class Charmander used to make Charmander objects
 * @author Ivan Kim
 *
 */
public class Charmander extends Pokemon implements Fire{

	/**
	 * Constructor for a Charmander object
	 * @param l The level of the Charmander to be initialized
	 */
	public Charmander(int level){
		
		super("Charmander", level);
	}
	
	/**
	 * Displays the Special Attacks menu
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
		
		int damage = 333333;
		switch(move){
		
			case 1:  
				damage = ember();
				break;
			case 2: 
				damage = fireBlast();
				break;
			case 3: 
				damage = firePunch();
				break;
		}
		return damage;
	}

	/**
	 * Generates the damage of Ember
	 * @return damage done by Ember
	 */
	public int ember(){
		
		int dmg = (int) ( Math.random() * 3 ) + super.getLevel();
		System.out.println(super.getName() + " used Ember for " + dmg + " damage!");
		return dmg;
		
	}

	/**
	 * Generates the damage of Fire Blast
	 * @return damage done by Fire Blast
	 */
	public int fireBlast(){
		
		int dmg = (int) ( Math.random() * 6 ) + super.getLevel();
		System.out.println(super.getName() + " used Fire Blast for " + dmg + " damage!");
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
	 * Returns the type of Charmander
	 */
	@Override
	public int getType() {
		
		return type;
	}
}
