import java.io.*;
/**
 * The main class for the Pokemon Game program
 * @author Ivan Kim
 *
 */
public class PokemonGame {
	public static void main(String[]args){
		
		//Generating Initial map
		Map map = new Map();
		int nothingFound = 0;

		Player myTrainer = null;

		File f = new File ("pokemon.dat");
		if ( f.exists() ){
			System.out.println("Pokemon\n\n1. Load Game\n2. New Game\n");
			int startChoice = CheckInput.checkIntRange(1, 2);
			if (startChoice == 1){
				try {
					ObjectInputStream in = new ObjectInputStream( new FileInputStream(f) );
					myTrainer = (Player) in.readObject();
					in.close();
				} catch (IOException e){
					System.out.println("Error processing file");
				} catch (ClassNotFoundException e ) {
					System.out.println("Could not find class.");
				}
				System.out.println("Loading game...\n...\n...\n...\n    DONE!\n");
			} else {
				
				//NEW GAME - Initializing User's Trainer
				System.out.println("Welcome to the wonderful world of Pokemon! Let's start with your name.\n\nWhat is your name?");
				String trainerName = CheckInput.getString();
				if (trainerName.isEmpty())
					trainerName = "Ash Ketchum";

				
				myTrainer = new Player(trainerName, 10, map.findStartLocation());
				
				System.out.println("\nHello, " + myTrainer.getName() + ". Let's begin your adventure!\n");
				
				//Choosing Starting Pokemon
				System.out.println("Choose your starting Pokemon:");
				System.out.println("1. Bulbasaur - the grass type Pokemon\n2. Charmander - the fire type Pokemon\n3. Squirtle - the water type Pokemon");
				int starterChoice = CheckInput.checkIntRange(1, 3);
				PokemonMaker.makeStartPokemon(starterChoice);
				myTrainer.addPokemon( PokemonMaker.makeStartPokemon(starterChoice) );
				
				System.out.println("\nYou have selected " + myTrainer.getCurrentPokemon().getName());
				System.out.println("Your " + myTrainer.getCurrentPokemon().getName()+ " is level " + myTrainer.getCurrentPokemon().getLevel() +"!\n");
				
			}
			
		} else {
			
			//NEW GAME - Initializing User's Trainer
			System.out.println("Welcome to the wonderful world of Pokemon! Let's start with your name.\n\nWhat is your name?");
			String trainerName = CheckInput.getString();
			if (trainerName.isEmpty())
				trainerName = "Ash Ketchum";

			
			myTrainer = new Player(trainerName, 10, map.findStartLocation());
			
			System.out.println("\nHello, " + myTrainer.getName() + ". Let's begin your adventure!\n");
			
			//Choosing Starting Pokemon
			System.out.println("Choose your starting Pokemon:");
			System.out.println("1. Bulbasaur - the grass type Pokemon\n2. Charmander - the fire type Pokemon\n3. Squirtle - the water type Pokemon");
			int starterChoice = CheckInput.checkIntRange(1, 3);
			PokemonMaker.makeStartPokemon(starterChoice);
			myTrainer.addPokemon( PokemonMaker.makeStartPokemon(starterChoice) );
			
			System.out.println("\nYou have selected " + myTrainer.getCurrentPokemon().getName());
			System.out.println("Your " + myTrainer.getCurrentPokemon().getName()+ " is level " + myTrainer.getCurrentPokemon().getLevel() +"!\n");
			
		}
		
		//Creates default map and places player on the map
		map.generateArea(myTrainer.getArea());
		myTrainer.setLocation(map.findStartLocation());

		//MAIN MENU
		boolean gameOver = false;
		while (gameOver == false){
			System.out.println("\nWhat would you like to do?\n");
			System.out.println("1. Travel");
			System.out.println("2. Switch Pokemon");
			System.out.println("3. Heal Current Pokemon");
			System.out.println("4. Check status");
			System.out.println("5. Check inventory");
			System.out.println("6. Save game");
			System.out.println("7. Quit game\n");
			int choice = CheckInput.checkIntRange(0, 7);
			switch(choice){
				//Traveling with a map
				case 1:
					System.out.println("\nWhere would you like to go?");
					System.out.println("Map:\n");
					map.displayMap(myTrainer.getLocation());
					char atNewPosition = 0;
					System.out.println("\nChoose a direction:\n1. North\n2. South\n3. East\n4. West\n");
					int directionChoice = CheckInput.checkIntRange(1, 4);
					switch (directionChoice){
						case 1:
							atNewPosition = myTrainer.goNorth(map);
							map.displayMap(myTrainer.getLocation());
							break;
							
						case 2:
							atNewPosition = myTrainer.goSouth(map);
							map.displayMap(myTrainer.getLocation());
							break;
							
						case 3: 
							atNewPosition = myTrainer.goEast(map);
							map.displayMap(myTrainer.getLocation());
							break;
							
						case 4:
							atNewPosition = myTrainer.goWest(map);
							map.displayMap(myTrainer.getLocation());
							break;
					}
					if (atNewPosition == 'c'){
						
						inTown(myTrainer);
						
					}
					if (atNewPosition == 'n'){
						System.out.println("\nNothing remarkable found.\n");
						nothingFound++;
						if (nothingFound == 8){
							//Angry Pokemon
							System.out.println("\n");
							PokemonBattles.angryPokemon(myTrainer);
							nothingFound = 0;
							if (myTrainer.getHp() == 0){
								gameOver = true;
								System.out.println("Game Over.");
							}
						}
	
					}
					if (atNewPosition == 'w'){
						if (fightOrFlight("wild Pokemon", myTrainer, map) == 1){
							wildPokemonBattle(myTrainer, map);
						}
					}
					if (atNewPosition == 'f'){
						
						if (myTrainer.getArea() == 3){
							myTrainer.setArea(1);
						}else {
							myTrainer.setArea(myTrainer.getArea() + 1);
						}
						map.generateArea(myTrainer.getArea());
						System.out.println("\nYou have moved onto the next area!\n\nYou are now in area "+ myTrainer.getArea() + ". Saving game....\n");
						try {
							ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream ( f ));
							out.writeObject( myTrainer );
							out.close();
						} catch ( IOException e ){
							System.out.print("Error processing file.");
						}
						break;
	
					}
					if (atNewPosition == 'o'){
						int ran = (int)(Math.random()*5);
						if (ran == 0){
							//Angry Trainer
							System.out.println("\n");
							PokemonBattles.angryTrainer(myTrainer);
							map.removeOppAtLoc(myTrainer.getLocation());
							if (myTrainer.getHp() == 0){
								gameOver = true;
								System.out.println("Game Over.");
							}
							
						}else{
							//Trainer Battle
							if (fightOrFlight("trainer", myTrainer, map) == 1){
								OpponentMaker oppMaker = new OpponentMaker();
								Opponent opp = oppMaker.makeRandomOpponent();
								System.out.println("\n" + opp.getName() + " challenges you to a fight!");
								opp.attackSpeech();
								System.out.println(opp.getName() 
										+ " sends out a level " + opp.getCurrentPokemon().getLevel() + " " 
										+ opp.getCurrentPokemon().getName() + "!\n");
								myTrainer.attackSpeech();
								PokemonBattles.opponentBattle(myTrainer, opp);
							}
							map.removeOppAtLoc(myTrainer.getLocation());
						}
					}
					break;
					
				//Switch Pokemon
				case 2: 
					System.out.println("\nWhich Pokemon would you like to use?");
					myTrainer.listPokemon();
					int pokeChoice = CheckInput.checkInt();
					int choiceResult = myTrainer.setCurrentPokemon(pokeChoice);
					while(choiceResult != 1){
						
						System.out.println("\nWhich Pokemon would you like to use?");
						myTrainer.listPokemon();
						pokeChoice = CheckInput.checkInt();
						choiceResult = myTrainer.setCurrentPokemon(pokeChoice);
						
					}
					System.out.println("\nYour current Pokemon is set to " + myTrainer.getCurrentPokemon().getName()+ ".\n");
					break;
					
				//Heal current Pokemon	
				case 3: 
					myTrainer.usePotion();
					break;
					
				//Check Status
				case 4: 
					System.out.println("\nYou have "+ myTrainer.getHp() + " HP.\n");
					myTrainer.listPokemon();
					break;
					
				//Inventory
				case 5: 
					System.out.println("\nYou have:" );
					System.out.println("    " + myTrainer.getNumPotionsLeft() + " Potions");
					System.out.println("    " + myTrainer.getNumPokeballsLeft() + " Pokeballs\n");
					break;
				
				//Save Game
				case 6:
					try {
						ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream ( f ));
						out.writeObject( myTrainer );
						out.close();
					} catch ( IOException e ){
						System.out.print("Error processing file.");
					}
					System.out.println("Saving game. Please wait...\n...\n...\n...\n    Save Complete!\n");
					break;
					
				//Exit Game
				case 7: 
					gameOver = true;
					System.out.println("Game Over. Exiting...");
					break;
			}
		}
	}
	
	/**
	 * Method to resolve player actions in a Town
	 * @param myTrainer the Trainer to be in the town
	 */
	public static void inTown(Player myTrainer){
		//Town
		System.out.println("\nYou have come across a town!");
		boolean inTown = true;
		
		while (inTown){
			System.out.println("\nWhere would you like to go?");
			System.out.println("1. Pokemon Center\n2. PokeMart\n3. Leave Town");
			int townChoice = CheckInput.checkIntRange(1, 3);
			switch (townChoice){
				case 1: 
					System.out.println("\nWelcome to the Pokemon Center. Please wait until we heal your Pokemon.");
					System.out.println("...\n  ...\n    ...");
					myTrainer.healAllPokemon();
					myTrainer.gainHp(1000000);
					System.out.println("Thank you for waiting. Your Pokemon are fully healed!\n");
					break;
					
				case 2: 
					System.out.println("\nWelcome to the PokeMart. What would you like to purchase?");
					boolean shopping = true;
					while (shopping){
						System.out.println("1. Potions\n2. Pokeballs\n3. Exit PokeMart");
						int martChoice = CheckInput.checkIntRange(1, 3);
						
						if (martChoice == 1){
							myTrainer.buyPotion();
						}else if (martChoice == 2){
							myTrainer.buyPokeball();
						}else{
							System.out.println("\nGoodbye. Please come back soon!\n");
							shopping = false;
						}
					}
					break;
				case 3:
					inTown = false;
					System.out.println("\nLeaving the town. On to the next destination!");	
			}
		}
	}

	/**
	 * Method to resolve wild Pokemon Battles
	 * @param myTrainer the Trainer battling
	 * @param map the map that the Trainer is traversing
	 */
	public static void wildPokemonBattle(Player myTrainer, Map map){

		Pokemon wild = PokemonMaker.makeWildPokemon();
		System.out.println("A level "+ wild.getLevel() +" wild " + wild.getName() + " appears!");
		
		boolean wildFight = true;
		while (wildFight){
			
			System.out.println("\nWhat would you like to do?\n1. Fight\n2. Throw Pokeball");
			int pokeBattleChoice = CheckInput.checkIntRange(1, 2);
			switch ( pokeBattleChoice ){
			case 1:
				PokemonBattles.wildPokeBattle(myTrainer, wild);
				if (myTrainer.checkOutofPoke() == 0 ){
					
					wildFight = false;
					System.out.println("\n"+myTrainer.getName() + " is out of Pokemon. You lose.\n");
					myTrainer.lossSpeech();
					System.out.println("\nRushing to the nearest Pokemon Center...\n...\n...\n...");
					myTrainer.healAllPokemon();
					System.out.println("\nNurse Joy: Your Pokemon are fully healed. Please be more careful in your journey.");
					
				}else if ( wild.getHp() == 0){
					map.removeOppAtLoc(myTrainer.getLocation());
					wildFight = false;
				}
				break;
				
			//Use Pokeball
			case 2:
				
				if (myTrainer.getNumPokeballsLeft() == 0){
					System.out.println("\nYou are out of Pokeballs!");
					
				}else{
					System.out.println("\nYou throw a Pokeball!");
					myTrainer.usePokeball();

					if (wild.getMaxHp()/2 < wild.getHp()){
						double chance = Math.random()*10 + 1;
						if (chance > 5){
							
							System.out.println("\n...\n...\n...\nYeah! I caught it!");
							myTrainer.addPokemon(wild);
							System.out.println(wild.getName() + " has been added to the party!\n");
							map.removeOppAtLoc(myTrainer.getLocation());
							wildFight = false;
							
						}else {
							System.out.println("\n...\n Darn it got away!");
						}
						
						
					} else if (wild.getHp() < wild.getMaxHp()/2 && wild.getHp() > wild.getMaxHp()/4){
						double chance = Math.random()*10 + 1;
						if (chance > 3){
							
							System.out.println("\n...\n...\n...\nYeah! I caught it!");
							myTrainer.addPokemon(wild);
							System.out.println(wild.getName() + " has been added to the party!\n");
							map.removeOppAtLoc(myTrainer.getLocation());
							wildFight = false;

							
						}else{
							System.out.println("\n...\n... Aww it escaped!");
						}
					
						
					} else{
						double chance = Math.random()*10 + 1;
						if (chance > 2){
							
							System.out.println("\n...\n...\n...\n\nYeah! I caught it!");
							myTrainer.addPokemon(wild);
							System.out.println(wild.getName() + " has been added to the party!\n");
							map.removeOppAtLoc(myTrainer.getLocation());
							wildFight = false;

							
						}else{
							System.out.println("\n...\n...\n...\nWow it was so close!");
						
						}
					}
				}
			}
		}
	}

	/**
	 * Allows user to choose to fight or run from a battle
	 * @param s the name of the enemy encountered
	 * @param myTrainer user's Player
	 * @param map the map of the area
	 * @return returns 1 if fighting, 2 if running
	 */
	public static int fightOrFlight(String s, Player myTrainer, Map map){
		
		System.out.println("You have encountered a " + s +". Do you want to fight or run?\n1. Fight\n2. Run\n");
		int choice = CheckInput.checkIntRange(1, 2);
		if (choice == 2){
			
			int ran =(int)(Math.random()*4);
			boolean gotAway = false;
			
			while (gotAway == false){
				if ( ran == 0 ){
					if (myTrainer.goNorth(map) == '!'){
						ran = (int)(Math.random()*4);
					}else{
						System.out.println("You ran away north!");
						gotAway = true;
					}
					
				}else if ( ran == 1){
					if (myTrainer.goSouth(map) == '!'){
						ran = (int)(Math.random()*4);
					}else{
						System.out.println("You ran away south!");
						gotAway = true;
					}
				}else if ( ran == 2){
					if (myTrainer.goEast(map) == '!'){
						ran = (int)(Math.random()*4);
					}else{
						System.out.println("You ran away east!");
						gotAway = true;
					}
				}else if ( ran == 3){
					if (myTrainer.goWest(map) == '!'){
						ran = (int)(Math.random()*4);
					}else{
						System.out.println("You ran away west!");
						gotAway = true;
					}
				}
			}
		}
		return choice;
	}
}