import java.awt.Point;
import java.io.Serializable;

/**
 * Creates a Player Trainer for the user
 * @author Ivan Kim
 *
 */
public class Player extends Trainer implements Serializable{

	/**
	 * Number of potions that the player has
	 */
	private int potions;
	
	/**
	 * Number of pokeballs that the player has
	 */
	private int pokeballs;
	
	/**
	 * Amount of money that the player has
	 */
	private int money;
	
	/**
	 * location of the player on the map
	 */
	private Point location;
	
	/**
	 * The area that the Player is in
	 */
	private int area;
	
	/**
	 * Constructor for Player Trainer
	 * @param n name of Player
	 * @param h HP of Player
	 */
	public Player(String n, int h, Point start) {
		super(n, h);
		money = 1000;
		potions = 2;
		pokeballs = 6;
		location = start;
		area = 1;
	}
	
	/**
	 * Uses a potion to heal the current Pokemon
	 */
	public void usePotion(){
		
		if (potions == 0){
			
			System.out.println("\nYou are out of Potions\n");
			
		}else{
			
			potions--;
			if (super.getCurrentPokemon().getHp() == super.getCurrentPokemon().getMaxHp()){
				
				System.out.println("\nYour Pokemon is alreayd at full HP");
				
			}else {
				
				super.getCurrentPokemon().gainHp(20);
				System.out.println("\n" + super.getCurrentPokemon().getName() + " was healed for 20 HP");
				System.out.println(super.getCurrentPokemon().getName() + "'s HP: " + super.getCurrentPokemon().getHp() +" / " + super.getCurrentPokemon().getMaxHp());
			
			}
		}
	}
	
	/**
	 * Gets the number of potions left
	 * @return number of potions
	 */
	public int getNumPotionsLeft(){
		return potions;
	}
	
	/**
	 * Method to buy a potion
	 */
	public void buyPotion(){
		if (money < 300){
			
			System.out.println("\nYou don't have enough money to buy a Potion");
			
		}else{
			
			spendMoney(300);
			potions++;
			System.out.println("\nYou have bought 1 Potion. You have a total of " + potions + " Potions.");
			System.out.println("You have " + money + " Pokedollars left.");	

		}
	}
	
	/**
	 * Method to use Pokeball
	 */
	public void usePokeball(){
		
		if (pokeballs == 0){
			
			System.out.println("\nYou are out of Pokeballs!\n");
			
		}else{
			
			pokeballs--;
		}
	}
	
	/**
	 * Gets the number of Pokeballs left
	 * @return the number of Pokeballs
	 */
	public int getNumPokeballsLeft(){
		
		return pokeballs;
	}
	
	/**
	 * Method to buy Pokeball
	 */
	public void buyPokeball(){
		
		if (money < 200){
			
			System.out.println("\nYou don't have enough money to buy a Pokeball");
			
		}else{
			
			spendMoney(200);
			pokeballs++;
			System.out.println("\nYou have bought 1 Pokeball. You have a total of " + pokeballs + " Pokeballs.");
			System.out.println("You have " + money + " Pokedollars left.");		
		}
	}
	
	/**
	 * Method to spend money when shopping
	 * @param price the amount to remove from total
	 */
	public void spendMoney(int price){
		
		money = money - price;
	}
	
	/**
	 * Method to gain money when winning battle
	 * @param m the amount to add to total
	 */
	public void gainMoney(int m){
		
		money += m;
	}

	/**
	 * Attack speech of the player
	 */
	@Override
	public void attackSpeech() {
		
		System.out.println("\nLet's see how my training is paying off! Go " + super.getCurrentPokemon().getName() + "!\n");		
	}

	/**
	 * Prints the win speech of the player
	 */
	@Override
	public void winSpeech() {
		
		System.out.println("Good job, "+ super.getCurrentPokemon().getName() + "!");		

	}

	/**
	 * Prints the loss speech of the player
	 */
	@Override
	public void lossSpeech() {
		
		System.out.println("I gotta get stronger...");		
		
	}

	/**
	 * Allows user the choose the style of attack
	 * @return the chosen style
	 */
	@Override
	public int chooseStyle() {
		
		System.out.println("\nChoose style: \n1. Basic attack \n2. Special attack");
		return CheckInput.checkIntRange(1, 2);

	}

	/**
	 * Allows user to choose move depending on the chosen style
	 * @param style the chosen style
	 * @return the chosen move
	 */
	@Override
	public int chooseMove(int style) {
		
		if (style == 1){
			
			super.getCurrentPokemon().displayBasicMenu();
			return CheckInput.checkIntRange(1, 3);
		}
		super.getCurrentPokemon().displaySpecialMenu();
		return CheckInput.checkIntRange(1, 3);
	}

	/**
	 * Returns the area that the Player is in
	 * @return the area that the Player is in
	 */
	public int getArea(){
		return area;
	}
	
	/**
	 * Sets a new area for the Player
	 * @param a the new area for the Player
	 */
	public void setArea(int a){
		area = a;
	}
	
	/**
	 * Gets the location of the Player
	 * @return the Player location
	 */
	public Point getLocation(){
		return location;
	}
	
	/**
	 * Sets the location of the Player
	 * @param p the new Point location of the Player
	 * @return the new Point
	 */
	public boolean setLocation(Point p){
		
		if ( p.getX() > 4 || p.getY() > 4 || p.getX() < 0 || p.getY() < 0 ){
			return false;
		} else {
			location.setLocation(p.getX(), p.getY());
			return true;
		}
	}
	
	/**
	 * Makes the Player move north on the map
	 * @param m the map that the Player is traversing
	 * @return the char at the new location
	 */
	public char goNorth( Map m ){
		
		Point p = new Point( (int)location.getX(), (int)(location.getY() - 1) );
		if (this.setLocation(p)){
			m.reveal(p);
		}else{
			System.out.println("You have hit a wall.");
			return '!';
		}
		return m.getCharAtLoc(location);
	}
	
	/**
	 * Makes the Player move south on the map
	 * @param m the map that the Player is traversing
	 * @return the char at the new location
	 */
	public char goSouth(Map m){
		
		Point p = new Point( (int)location.getX(), (int)(location.getY() + 1) );
		if (this.setLocation(p)){
			m.reveal(p);
		}else{
			System.out.println("You have hit a wall.");
			return '!';
		}
		return m.getCharAtLoc(location);
	}
	
	/**
	 * Makes the Player move east on the map
	 * @param m the map that the Player is traversing
	 * @return the char at the new location
	 */
	public char goEast(Map m){
		
		Point p = new Point( (int)(location.getX() + 1), (int)location.getY() );
		if (this.setLocation(p)){
			m.reveal(p);
		}else{
			System.out.println("You have hit a wall.");
			return '!';
		}
		return m.getCharAtLoc(location);
	}
	
	/**
	 * Makes the Player move west on the map
	 * @param m the map that the Player is traversing
	 * @return the char at the new location
	 */
	public char goWest(Map m){
		
		Point p = new Point( (int)(location.getX() - 1), (int)location.getY() );
		if (this.setLocation(p)){
			m.reveal(p);
		}else{
			System.out.println("You have hit a wall.");
			return '!';
		}
		return m.getCharAtLoc(location);
	}
}
