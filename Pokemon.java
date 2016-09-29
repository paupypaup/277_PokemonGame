/**
 * Pokemon class to make instances of Pokemon
 * @author Ivan Kim
 *
 */
public abstract class Pokemon extends Entity {
	
	/**
	 * Level of the Pokemon
	 */
	private int level;
	
	/**
	 * Experience of the Pokemon
	 */
	private int exp;
	
	/**
	 * The total experience needed for the Pokemon to level up
	 */
	private int expBar;
	
	/**
	 * Constructor for a Pokemon
	 * @param n name of Pokemon
	 * @param l	level of Pokemon
	 */
	public Pokemon( String n, int l ){
		super( n, 10 + ( 3 * l ) );
		level = l;
		expBar = level * 10;
	}
	
	/**
	 * Abstract int for the subclass to get its type
	 * @return Pokemon type
	 */
	public abstract int getType();
	
	/**
	 * Abstract int for the subclass to attack with its Special Attack
	 * @param move the Special Attack move to be selected
	 * @return the damage done by the Special move
	 */
	public abstract int specialFight(int move);
	
	/**
	 * Displays the Special Attack Menu for the sublcass
	 */
	public abstract void displaySpecialMenu();
	
	/**
	 * Gets level of Pokemon
	 * @return Pokemon level
	 */
	public int getLevel(){
		return level;
	}
	
	/**
	 * Gets experience points of Pokemon
	 * @return Pokemon exp
	 */
	public int getExp(){
		return exp;
	}
	
	/**
	 * Gets experience needed for the Pokemon to level up
	 * @return total experience needed to level up
	 */
	public int getExpBar(){
		return expBar;
	}
	
	/**
	 * Gets name of the Pokemon
	 */
	public void displayPokemon(){
		System.out.println(super.getName());
	}
	
	/**
	 * Increases the exp of the Pokemon
	 * @param x the exp to gain
	 * @return the new exp of the Pokemon
	 */
	public int gainExp(int x){

		exp = exp + x;
		if ( exp > expBar){
			level = level + 1;
			System.out.println("Congratulations your " + super.getName() + " has leveled up! It is now level "+ level +"!");
			exp = exp - expBar;
			expBar = level * 10;
			super.incMaxHp();

		}
		return exp;	
	}
	
	/**
	 * Displays the Basic Attack Menu of Pokemon
	 */
	public void displayBasicMenu(){
		System.out.println("\nSelect Basic Move:");
		System.out.println("1. Tackle");
		System.out.println("2. Slam");
		System.out.println("3. Mega Punch");
		
	}
	
	/**
	 * Chooses a Basic Attack move for the Pokemon
	 * @param move the Basic Attack move to use
	 * @return the damage to deal
	 */
	public int basicFight(int move){

		int damage = 999999;
		switch (move){
		case 1: 
			damage = tackle();
			break;
		case 2:  
			damage = slam();
			break;
			
		case 3: 
			damage = megaPunch();
			break;
		}
		return damage;
	}
	
	/**
	 * Determines whether to use a Basic Move or Special Move
	 * @param style determines whether to use a Basic Move or a Special Move 
	 * @param move which move to use
	 * @return the damage to deal
	 */
	public int fight(int style, int move){
		
		int damage = 77777;
		if (style == 1){

			damage = basicFight(move);

		} else {

			damage = specialFight(move);
		}
		
		return damage;
	}
	
	/**
	 * Determines how much damage Tackle will do
	 * @return the damage to deal
	 */
	public int tackle(){

		int dmg = (int) ( Math.random() * 3) + level;
		System.out.println(super.getName() + " used Tackle for " + dmg + " damage!");
		return dmg;
	}
	
	/**
	 * Determines how much damage Slam will do
	 * @return the damage to deal
	 */
	public int slam(){

		int dmg = (int) ( Math.random() * 6)  + level;
		System.out.println(super.getName() + " used Slam for " + dmg + " damage!");
		return dmg;
	}
	
	/**
	 * Determines how much damage Mega Punch will do
	 * @return the damage to deal
	 */
	public int megaPunch(){

		int dmg = (int) ( Math.random() * 9 ) + level;
		System.out.println(super.getName() + " used Mega Punch for " + dmg + " damage!");
		return dmg;
	}
}
