import java.util.*;
import java.io.*;
/**
 * Creates a random Opponent for the Player to fight
 * @author Ivan
 *
 */
public class OpponentMaker {
	
	/**
	 * ArrayList containing templates for Opponents
	 */
	private ArrayList <Opponent> opponents;
	
	/**
	 * Constructor for Opponents
	 */
	public OpponentMaker(){
		opponents = new ArrayList<Opponent>();
		try{
			Scanner read = new Scanner ( new File ( "OpponentList.txt"));
			while (read.hasNextLine()){
				String s = read.nextLine();
				String [] sp = s.split(",");
				Opponent opp = new Opponent(sp[0], 1, sp[1], sp[2], sp[3]);
				opponents.add(opp);
			}
			read.close();
		}catch (FileNotFoundException e){
			System.out.println("File not found.");
		}
	}
	
	/**
	 * Creates a random Opponent from the template
	 * @return created Opponent
	 */
	public Opponent makeRandomOpponent(){
		int ran = (int)(Math.random()*opponents.size());
		
		String name = opponents.get(ran).getName();
		int hp = opponents.get(ran).getHp();
		String win = opponents.get(ran).getWin();
		String attack = opponents.get(ran).getAttack();
		String loss = opponents.get(ran).getLoss();
		
		Opponent opp = new Opponent(name, hp, attack, win, loss);
		
		if (ran == 4){
			int randomType = (int) (Math.random()*4);
			int randomType2 = (int) (Math.random()*4);
			opp.addPokemon(PokemonMaker.makeTypePokemon(randomType));
			opp.addPokemon(PokemonMaker.makeTypePokemon(randomType2));
		}else{
			opp.addPokemon(PokemonMaker.makeTypePokemon(ran));
		}
		return opp;
	}
}
