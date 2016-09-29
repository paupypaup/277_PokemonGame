/**
 * Class to make an Opponent Trainer
 * @author Ivan Kim
 *
 */
public class Opponent extends Trainer {
	
	/**
	 * The attack speech
	 */
	private String attack;
	
	/**
	 * The win speech
	 */
	private String win;
	
	/**
	 * The loss speech
	 */
	private String loss;
	
	/**
	 * Constructor for an Opponent
	 * @param n name of Opponent
	 * @param h HP of Opponent
	 * @param a attack speech of Opponent
	 * @param w win speech of Opponent
	 * @param l loss speech of Opponent
	 */
	public Opponent(String n, int h, String a, String w, String l) {
		
		super(n, h);
		attack = a;
		win = w;
		loss = l;
	}

	/**
	 * Prints out attack speech depending on the type of Opponent
	 */
	@Override
	public void attackSpeech() {
		System.out.println("\n" + attack + "\n");
	}
	
	/**
	 * Returns the String of the Opponents attack speech
	 * @return the String of the Opponents attack speech
	 */
	public String getAttack(){
		return attack;
	}

	/**
	 * Prints out win speech depending on the type of Opponent
	 */
	@Override
	public void winSpeech() {
		System.out.println("\n" + win + "\n");
	}
	
	/**
	 * Returns the String of the Opponents win speech
	 * @return the String of the Opponents win speech
	 */
	public String getWin(){
		return win;
	}

	/**
	 * Prints out loss speech depending on the type of Opponent
	 */
	@Override
	public void lossSpeech() {
		System.out.println("\n" + loss + "\n");
	}
	
	/**
	 * Returns the String of the Opponents loss speech
	 * @return the String of the Opponents loss speech
	 */
	public String getLoss(){
		return loss;
	}
	

	/**
	 * Chooses style of attack for Opponent
	 * @return random number 1 or 2
	 */
	@Override
	public int chooseStyle() {
		
		return (int) (Math.random()*2 + 1);
	}

	/**
	 * Chooses a move depending on the chosen style
	 * @param style the style of the attack to be chosen 
	 * @return an integer between 1 and 3
	 */
	@Override
	public int chooseMove(int style) {
		
		return (int) (Math.random()*3 + 1);
	}
}
