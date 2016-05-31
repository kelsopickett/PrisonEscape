import java.util.Random;



public class Game {
	public static int countAttacks;
	//Game methods
		public static String combat(Player player, NonPlayerChar npc){
			//TODO add initialization parameter
			
			//Declare objects and variables local to this block
			Random rng = new Random();
			int quantity = rng.nextInt(3) + 1;
			String win = "Damn cuz your a killer";
			String lose = "You died? Fuck off then mate!";
			
			//Keep fighting while player and npc are alive
			while(player.isAlive() && npc.isAlive()){
				
				if(countAttacks >= 1)
					System.out.println("Countinue?");
				//Player attacks
				npc.setHealth(player.getAttackDamage());
				//If player attack kills npc then do/dont drop item and break
				if (!npc.isAlive()){
					if(npc.hasItem() == true){
						
						if(npc.itemDrop() == true){
							player.toiletWine(quantity);
							System.out.println("Retrieved " + quantity + " toilet wine(s)");
						}
						else{
							player.setAttackDamage(quantity);
							System.out.println("Retrieved " + quantity + " shank(s)");
						}
					}
					break;
				}
				
				System.out.println("NPC: " + npc.getHealth());
				//int a = countAttacks;
				//System.out.println(a);
				
				
				//enemy attacks
				player.setHealth(npc.getAttackDamage());
				System.out.println("Player: " + player.getHealth());
				countAttacks += 1;
				
			}
			
			//end fighting and return result
			if (player.isAlive() == true){
			return win;
			}
			else 
			return lose;
			
		}

	public static void main(String[] args) {
		
		// TODO Start menu, only option is "Start a New Game"
		newGame();
		
		//Instantiate system objects and set their respective pointers
		
				//TODO Declare Game methods
				//TODO Play and Draw game
				
				
	}

	private static void newGame() {
		// TODO Prompt user to enter character name
		// TODO Display opening "crawl" with backstory
		// TODO Display first scene (prison cell)
			/* TODO Get user choice for first scene:
			 * 	a) search room, find weapon and lockpick; approach cell door, advance
			 * 	b) make toilet wine (++ health), advance (-- health)
			 * 	c) go back to sleep (player is killed in sleep)
			 * 	d) approach cell door, advance (--health)
			 */
		// TODO If user advances, display second scene (hallway); else display result of user choice
			/* TODO Get user choice for second scene
			 * 	a) turn and flee; advance (potential repercussions?)
			 * 	b) stand and fight; combat (chance for --health and item drop), advance if alive
			 * 	c) assume fetal position; (die)
			 * 	d) talk your way out; advance
			 */
		// TODO If the user advances, display the third scene (cafeteria); else display result of user choice
			/* TODO Get user choice for third scene
			 * 	a) attack prisoners; combat (chance for --health and item drop), advance if alive
			 * 	b) run to kitchen; advance
			 * 	c) hide behind something (prisoners kill you)
			 * 	d) return to previous room; encounter guard, bribe (--cigarettes), advance
			 */
		// TODO If the user advances, display the fourth scene (kitchen); else display result of user choice
			/* TODO Get user choice for fourth scene
			 * 	a) "I don't know" (death)
			 * 	b) answer riddle; wrong answer, combat (chance for --health and item drop), advance if alive
			 * 	c) answer riddle; wrong answer, combat (chance for --health and item drop), advance if alive
			 * 	d) answer riddle; correct answer, advance
			 */
		// TODO If the user advances, display the fifth scene (ext. yard); else display result of user choice
		/* TODO Get user choice for fifth scene
		 * 	a) turn and run; combat (chance for --health), advance if alive
		 * 	b) climb up rubbish chute; shot to death by guard
		 * 	c) bribe guard with cigarettes; advance (--cigarettes)
		 * 	d) surrender; lose but no death
		 */
		// TODO If the user advances, display the sixth scene (infirmary); else display result of user choice
		/* TODO Get user choice for sixth scene
		 * 	a) accept offer of help; something bad happens, lose
		 * 	b) fight the doctor/nurse; combat (chance for --health, item drop), advance if alive
		 * 	c) ignore, search for items (chance for ++health, item drop); advance
		 * 	d) have mental breakdown; doctor/nurse consoles you (++cigarette), advance
		 */
		// TODO If the user advances, display the seventh scene (ext. front yard); else display result of user choice
		/* TODO Get user choice for seventh scene
		 * 	a) throw knife at bus driver; combat (really low chance of success), advance if alive
		 * 	b) turn and run; bus rolls, explodes, and you black out... return to first scene
		 * 	c) hide behind something; run over by bus, die and lose
		 * 	d) embrace death; toss shank, bus runs it over, tire pops and veers away... advance
		 */
		// TODO If the user advances, display eighth scene (ext. outside prison walls) and score; else, take action based on user choice
	}

}

