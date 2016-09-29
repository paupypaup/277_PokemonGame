import java.io.Serializable;
import java.util.*;
/**
 * Abstract Trainer class used to make a Player or an Opponent
 * @author Ivan Kim
 *
 */
public abstract class Trainer extends Entity implements Serializable{
	
	/**
	 * ArrayList containing the Trainer's Pokemon
	 */
	private ArrayList <Pokemon> party;
	
	/**
	 * The index of the Pokemon currently selected
	 */
	private int currentPokemon = 0;

	/**
	 * Constructor for the Trainer class
	 * @param n name of Trainer
	 * @param h HP of Trainer
	 */
	public Trainer(String n, int h){
		
		super(n, h);
		party = new ArrayList<Pokemon>();
	}
	
	/**
	 * Method to call for the Attack Speech for its subclasses
	 * @return 
	 */
	public abstract void attackSpeech();
	
	/**
	 * Method to call for the Win Speech for its subclasses
	 */
	public abstract void winSpeech();
	
	/**
	 * Method to call for the Loss Speech for its subclasses
	 */
	public abstract void lossSpeech();
	
	/**
	 * Method to choose Style of attack for its supclasses
	 */
	public abstract int chooseStyle();
	
	/**
	 * Method to call for the attack move for its subclasses
	 * @param style the style of the move
	 * @return damage done by the move
	 */
	public abstract int chooseMove(int style);
	
	/**
	 * Displays the current selected Pokemon
	 */
	public void displayCurrentPokemon(){
		
		System.out.println(super.getName()+ "'s current Pokemon is " + party.get(currentPokemon).getName() + ".");
	}

	/**
	 * Gets the current Pokemon of the Trainer
	 * @return Trainer's current Pokemon
	 */
	public Pokemon getCurrentPokemon(){
		
		return party.get(currentPokemon);
	}
	
	/**
	 * Adds Pokemon to the Trainer's party
	 * @param p the Pokemon to be added
	 */
	public void addPokemon(Pokemon p){
		
		party.add(p);
	}
	
	/**
	 * Lists all the Pokemon in the party and their stats
	 */
	public void listPokemon(){
		
		for( int i = 0; i < party.size(); i++ ){
			
			System.out.println( "\n[" + i + "]: " + party.get(i).getName() );
			System.out.println( "    Level " + party.get(i).getLevel());
			System.out.println( "    HP: " + party.get(i).getHp() + "/" + party.get(i).getMaxHp());
			System.out.println( "    Exp: " + party.get(i).getExp() + "/" + party.get(i).getExpBar() + "\n");
		}
	}
	
	/**
	 * Returns all Pokemon to full HP
	 */
	public void healAllPokemon(){
		
		for ( int i = 0; i < party.size(); i ++){
			
			party.get(i).gainHp(1000000000);
		}
	}
	
	/**
	 * Checks if all Pokemon in the trainer's party is out HP
	 * @return total HP of Trainer's Pokemon
	 */
	public int checkOutofPoke(){
		
		int totalHp = 0;
		for (int i = 0; i < party.size();i++){
			
			totalHp += party.get(i).getHp();
		}
		return totalHp;
	}
	
	/**
	 * Changes the current Pokemon
	 * @param cur the new Pokemon to be selected
	 * @return -1 if invalid selction, otherwise 1
	 */
	public int setCurrentPokemon(int cur){
		
		if (cur > party.size() - 1 || cur < 0){
			
			System.out.println("Invalid input. Please input a value corresponding to a Pokemon.");
			return -1;
		}
		
		if(party.get(cur).getHp() == 0){
			
			System.out.println(party.get(cur).getName() + " has fainted. It cannot be chosen.");
			return -1;
			
		}else{
		
			currentPokemon = cur;
			return 1;	
		}		
	}
	
	/**
	 * Gets the next Pokemon in the party
	 * @return the new current Pokemon
	 */
	public int getNextCurPokemon(){
		
		int getNext = currentPokemon + 1;
		if ( getNext > party.size() -1 ){
			
			getNext = 0;
		}
		currentPokemon = getNext;
		return currentPokemon;
	}
	
	/**
	 * Calls the Trainer's Pokemon to battle
	 * @return the damage done by Trainer's Pokemon
	 */
	int battle(){
		
		int style = this.chooseStyle();
		return party.get( currentPokemon ).fight( style, this.chooseMove( style ) );
	
	}
}



















