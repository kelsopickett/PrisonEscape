
public class Game {
	
	private static boolean running = true;

	public static void main(String[] args) {
		
		// TODO Start menu, only option is "Start a New Game"
		newGame();
		
		//Instantiate system objects and set their respective pointers
		
				Player paul = new Player();
				NonPlayerChar dickhead = new NonPlayerChar();
				paul.getHealth();
				paul.setName("Paul");
				System.out.println(paul.getHealth());
				
				System.out.println(paul.getHealth());
				//TODO Declare Game methods
				//TODO Play and Draw game
				do{ //start new game
					
				} while(running); //exited by player causes end of main
				
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
