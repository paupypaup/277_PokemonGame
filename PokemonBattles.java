/**
 * Class to resolve Pokemon battles against Trainers or wild Pokemon
 * @author Ivan Kim
 *
 */
public class PokemonBattles {
	
	/**
	 * Contains damage modifiers depending on attacking/defending Pokemon type
	 */
	private final static double [][] fightTable = {{.5, .5, 2, 1}, {2, .5, .5, 1}, {.5, 2, .5, 1}, {1, 2, .5, .5}};
	
	/**
	 * Resolves battles between 2 Pokemon Trainers
	 * @param attacker the Trainer dealing the damage
	 * @param defender the Trainer taking the damage
	 */
	private static void trainerBattle( Trainer attacker, Trainer defender ){
		
		int damage = (int) ( attacker.battle() * fightTable[ attacker.getCurrentPokemon().getType() ][ defender.getCurrentPokemon().getType() ] );
		System.out.println("\n"+attacker.getName() + "'s "+attacker.getCurrentPokemon().getName() +" did " + damage + " damage to " + defender.getName()+"'s "+ defender.getCurrentPokemon().getName() +"!");
		defender.getCurrentPokemon().loseHp( damage );
		System.out.println("\n"+defender.getName() + "'s "+defender.getCurrentPokemon().getName() + " has " + defender.getCurrentPokemon().getHp() + " HP.\n");
	}
	
	/**
	 * Resolves battle between Player and a wild Pokemon
	 * @param player The player in the battle
	 * @param poke the wild Pokemon battling the Player
	 */
	public static void wildPokeBattle ( Trainer player, Pokemon poke ){		
			
		int damage = (int)(player.battle() * fightTable[player.getCurrentPokemon().getType()][poke.getType()]);
		System.out.println("\nYour "+ player.getCurrentPokemon().getName() +" did " + damage + " damage to wild " + poke.getName()+"!");
		poke.loseHp( damage );
		System.out.println("  Wild " + poke.getName() + " has " + poke.getHp() + " HP.\n");
		
		if (poke.getHp() == 0){
			
			System.out.println("\nWild " +poke.getName() + " has fainted!");
			int xpGain = (int) ( poke.getLevel() * 3 );
			player.getCurrentPokemon().gainExp( xpGain );
			System.out.println("\n"+ player.getCurrentPokemon().getName() + " has gained " + xpGain + " experience points!\n");
			
		}else{
			
			int wildAttackStyle = (int) ( Math.random()*2 + 1 );
			int wildAttackMove = (int) ( Math.random()*3 + 1 );
			
			damage = (int) (poke.fight(wildAttackStyle, wildAttackMove)* fightTable[poke.getType()][player.getCurrentPokemon().getType()]);
			player.getCurrentPokemon().loseHp( damage );
			System.out.println("\nWild " +poke.getName() +" did " + damage + " damage to " + player.getCurrentPokemon().getName());
			System.out.println("  Your " + player.getCurrentPokemon().getName() + " has " + player.getCurrentPokemon().getHp() + " HP.\n");

			if (player.getCurrentPokemon().getHp() == 0){
				
				if ( player.checkOutofPoke() == 0 ){
					
				}else{
					
					System.out.println("\nWhich Pokemon would you like to send out?");
					player.listPokemon();
					int pokeChoice = CheckInput.checkInt();
					int choiceResult = player.setCurrentPokemon(pokeChoice);
					while(choiceResult != 1){
						
						System.out.println("\nWhich Pokemon would you like to send out?");
						player.listPokemon();
						pokeChoice = CheckInput.checkInt();
						choiceResult = player.setCurrentPokemon(pokeChoice);
						
					}
					System.out.println("\nIt's your turn! Go " + player.getCurrentPokemon().getName()+ "!\n");
				}
			}
		}
	}
	
	/**
	 * Method to be called when 2 Trainers battle
	 * @param player Player Trainer
	 * @param opponent Opponent Trainer
	 */
	public static void opponentBattle( Player player, Opponent opponent ){
		
		boolean outOfPoke = false;
		
		while (outOfPoke == false) {
			trainerBattle(player, opponent);
			if (opponent.getCurrentPokemon().getHp() == 0){
				
				System.out.println( "\n"+opponent.getCurrentPokemon().getName() + " has fainted!\n" );
				int xpGain = (int) ( opponent.getCurrentPokemon().getLevel() * 4 );
				player.getCurrentPokemon().gainExp( xpGain );
				System.out.println("\n"+player.getCurrentPokemon().getName() + " has gained " + xpGain + " experience points!\n");
				
				if ( opponent.getNextCurPokemon() == 0){
					
					System.out.println(opponent.getName() + " is out of Pokemon. You win!\n");
					opponent.lossSpeech();
					outOfPoke = true;
					player.winSpeech();
					int money = opponent.getCurrentPokemon().getLevel() * 100;
					System.out.println("\nYou have gained " + money + " Pokedollars for defeating " + opponent.getName() +"!\n");
					player.gainMoney(money);
					
				}else{
					
					opponent.setCurrentPokemon(1);
					System.out.println(opponent.getName() + " sends out a level " + opponent.getCurrentPokemon().getLevel() + " " + opponent.getCurrentPokemon().getName() +"!\n");
				
				}
				
			} else {
				
				trainerBattle(opponent, player);
				if (player.getCurrentPokemon().getHp() == 0){
					
					System.out.println(player.getCurrentPokemon().getName() + " has fainted!\n");
					
					if ( player.checkOutofPoke() == 0 ){
						
						System.out.println(player.getName() + " is out of Pokemon. You lose.\n");
						opponent.winSpeech();
						player.lossSpeech();
						outOfPoke = true;
						System.out.println("\nRushing to the nearest Pokemon Center...\n...\n...\n...");
						player.healAllPokemon();
						System.out.println("Nurse Joy: Your Pokemon are fully healed. Please be more careful in your journey.");
						
					}else{
						
						System.out.println("Which Pokemon would you like to send out?");
						player.listPokemon();
						int pokeChoice = CheckInput.checkInt();
						int choiceResult = player.setCurrentPokemon(pokeChoice);
						while(choiceResult != 1){
							
							System.out.println("\nWhich Pokemon would you like to send out?");
							player.listPokemon();
							pokeChoice = CheckInput.checkInt();
							choiceResult = player.setCurrentPokemon(pokeChoice);
							
						}
						System.out.println("\nIt's your turn! Go " + player.getCurrentPokemon().getName()+ "!\n");
					}
				}
			}
		}
	}
	
	/**
	 * Resolves encounter with an angry trainer that will do damage directly to the Player Trainer
	 * @param trainer Player trainer
	 */
	public static void angryPokemon( Trainer trainer ){
		
		System.out.println("Your " + trainer.getCurrentPokemon().getName() + " refuses to obey!");
		System.out.println("Your " + trainer.getCurrentPokemon().getName() + " attacks you for 2 damage!");
		trainer.loseHp(2);
		System.out.println("You now have " + trainer.getHp() + " HP.");
		
		if (trainer.getHp() == 0){
			System.out.println("You have 0 HP! You have fainted!");
			System.out.println("...");
			System.out.println("You wake up at the nearest Pokecenter. You are fully healed, thanks to Nurse Joy.");
			trainer.gainHp(100000);
			trainer.healAllPokemon();
		}
	}
	
	/**
	 * Resolves encounter with a disobedient Pokemon that will do damage directly to the Player Trainer
	 * @param trainer Player trainer
	 */
	public static void angryTrainer( Trainer trainer ){
		
		System.out.println("You have encountered an angry Misty!");
		System.out.println("Stupid " + trainer.getName() + "! Pay me back for my bike!");
		System.out.println("Misty hits you for 2 damage!");
		trainer.loseHp(2);
		System.out.println("You now have " + trainer.getHp() + " HP.");
		
		if (trainer.getHp() == 0){
			System.out.println("You have 0 HP! You have fainted!");
			System.out.println("...");
			System.out.println("You wake up at the nearest Pokecenter. You are fully healed, thanks to Nurse Joy.");
			trainer.gainHp(100000);
			trainer.healAllPokemon();
		}
	}
}
