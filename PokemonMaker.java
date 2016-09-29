/**
 * Class to make Pokemon
 * @author Ivan Kim
 *
 */
public class PokemonMaker {
	
	/**
	 * Makes random wild Pokemon
	 * @return a new Pokemon
	 */
	public static Pokemon makeWildPokemon(){

		int wildChoice =(int) (Math.random()*8);
		int wildLevel = (int) (Math.random()*2+1);
		Pokemon wild = null;
		switch (wildChoice){
		
		case 0: wild = new Charmander(wildLevel);
			break;
		case 1: wild = new Ponyta(wildLevel);
			break;
		case 2: wild = new Squirtle(wildLevel);
			break;
		case 3: wild = new Staryu(wildLevel);
			break;
		case 4: wild = new Bulbasaur(wildLevel);
			break;
		case 5: wild = new Oddish(wildLevel);
			break;
		case 6: wild = new Pikachu(wildLevel);
			break;
		case 7: wild = new Zapdos(wildLevel);
			break;
		}
		return wild;
	}

	/**
	 * Makes a random Pokemon of a specified type
	 * @param type the type of Pokemon to make
	 * @return a new Pokemon
	 */
	public static Pokemon makeTypePokemon(int type){
		Pokemon mon = null;
		if ( type == 0)
		{
			//Make Fire type
			int wildChoice =(int) (Math.random()*2);
			int wildLevel = (int) (Math.random()*2+1);
			switch (wildChoice){
			
			case 0: mon = new Charmander(wildLevel);
				break;
			case 1: mon = new Ponyta(wildLevel);
			
			}
			
		} else if (type == 1){
			//make water type
			int wildChoice =(int) (Math.random()*2);
			int wildLevel = (int) (Math.random()*2+1);
			switch (wildChoice){
			
			case 0: mon = new Squirtle(wildLevel);
				break;
			case 1: mon = new Staryu(wildLevel);
			
			}
			
		} else if (type == 2){
			//make grass type
			int wildChoice =(int) (Math.random()*2);
			int wildLevel = (int) (Math.random()*2+1);
			switch (wildChoice){
			
			case 0: mon = new Bulbasaur(wildLevel);
				break;
			case 1: mon = new Oddish(wildLevel);
			
			}
			
		} else if (type == 3){
			//make electric
			int wildChoice =(int) (Math.random()*2);
			int wildLevel = (int) (Math.random()*2+1);
			switch (wildChoice){
			
			case 0: mon = new Pikachu(wildLevel);
				break;
			case 1: mon = new Zapdos(wildLevel);
			}
		}
		return mon;
	}
	
	/**
	 * Creates the starting Pokemon for the Player
	 * @param start The Pokemon choice
	 * @return the starting Pokemon
	 */
	public static Pokemon makeStartPokemon(int start){
		if (start == 1){
			return new Bulbasaur(5);
		}else if (start == 2){
			return new Charmander(5);		
		}else{
			return new Squirtle(5);
		}
	}
}
