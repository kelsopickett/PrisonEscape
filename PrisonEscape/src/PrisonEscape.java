import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class PrisonEscape {

	// Declare objects
	Random rng = new Random();
	private JFrame frame;
	private static JTextArea textArea;
	private JTextField textHealth, textSmokes, textWine, textShanks, textDamage;
	private JTextPane textGameOver, textEscape;
	private JButton btnOptionA, btnOptionB, btnOptionC, btnOptionD;
	//Declare class variables
	private static int currentScene = 0;
	// Instantiate player and enemies
	Player player = new Player();
	NonPlayerChar prisoner1 = new NonPlayerChar(80, 40, "Geralt", true); // Geralt has partial health and a shank (+15 DMG over 25 base DMG)
	NonPlayerChar yardGuard = new NonPlayerChar(100, 50, "Leeloo", true); // Leeloo has full health and a Taser (+25 DMG over 25 base DMG)
	NonPlayerChar nurse = new NonPlayerChar(200, 25, "Kevorkian", true); // Kevorkian is overhealed, but fights with his fists at 25 base DMG)
	NonPlayerChar warden = new NonPlayerChar();
	// Declare tracked statistics
	private int statPlaythroughs = 0, statVictories = 0, statHighscore = 0, statCombats = 0;
	// Initialise scenes-- parameters: description,choiceA, choiceB, choiceC, choiceD, resultA, resultB, resultC, resultD
	Scene cell = new Scene("You awake to find yourself in a dark cell.\n\nThe small, barred window above bathes you in a rich, warm light.\n\nThe light triggers"
				+ " a dull, throbbing pain in your head. You look down to discover you are covered in blood and bruises. Your body screams as you attempt to sit"
				+ " upright. Your heart pounds in your ears. Your feet reach the floor-- cold and damp, seeping through the thin soles of your shoes into your"
				+ " bones.\n\nWhat do you do?", 
		"Search cell for something useful.", "Make toilet wine.", "Crawl back in bed.", "Approach your cell door.", 
		"The cell is sparse; not much to search through.\n\nYou rummage under the shit excuse for a mattress until something reopens a wound on your hand."
				+ " Flipping the mattress over reveals a crudely made shank embedded inside. This might be useful... You rip it loose, tucking it in your"
				+ " beltline. You rise to your feet, given pause by the dull aches throughout your body, and proceed to the cell door.\n\nPlacing your hands on"
				+ " the bars, you find it loose in the frame. You press against the door with your remaining strength and force it open.\n\nYou step wearily out"
				+ " into the cellblock.", 
		"Another prisoner once taught you the best way to pass time was a kind of homemade wine made in cell toilets. Certainly not fit for a king, but you"
				+ " aren't in much a position to complain. After a little work, you taste the fruits of your labor. It's bitter and burns on the way down, but"
				+ " you feel a bit of strength returning.\n\nYou notice a thin strand of metal wound around a pipe fitting behind the toilet. It looks small"
				+ " enough to fit in the cell door's keyhole. You pull it free from the pipe and approach the cell door.\n\nYou successfully pick the lock and"
				+ " step into the cellblock.", 
		"The months you've spent in this shithole have worn you down. You are too tired and too sore to press on. You climb back into bed and rest.\n\nYou wake"
				+ " suddenly to a hand covering your mouth and a knife to your throat. An inmate stands over you, staring into your eyes as his blade slips across"
				+ " your neck.", 
		"You've had enough of this shithole. Anxious to make a move, you approach the cell door.\n\nPlacing your hands on the bars, you find it loose in the"
				+ " frame. You press against the door with your remaining strength and force it open.\n\nYou step wearily out into the cellblock.");
	Scene cellblock = new Scene("A sound to your right draws your attention, and you turn to find a fellow inmate brandishing a"
				+ " shank.\n\nHow do you proceed?", 
		"Turn and flee.", "Stand and fight.", "Assume the fetal position.", "Attempt to talk your way out.", 
		"You decide it's best not to take any chances.\n\nYou pivot away from the prisoner and sprint toward the hallway at the end of the cellblock. Your heart"
				+ " races as your feet pound the pavement, the sound echoing off the cement walls.\n\nYou reach the gate at the end of the block, slamming"
				+ " the lock shut behind you. You turn to face the prisoner, who dons a frustrated sneer before turning back the way he came.", 
		"\"Let's dance\" you mutter, gesturing the prisoner to approach. He lunges at you and a fight ensues.", 
		"You are not prepared for this fight. Realising the futility of your situation, you collapse to the floor and curl up into the fetal position. You hear"
				+ " the prisoner laugh as the sound of his footsteps grow closer.\n\nYou feel a blade plunge into your side repeatedly and the world fades to"
				+ " black.", 
		"\"I just want to get out of here\" you plead. He responds, \"You and me both. That way is a shit show,\"  gesturing"
				+ " behind him.\n\n\"Alright. Thanks. Guess I'll check down that hall,\" you suggest, pointing to the hallway at the opposite end of the block."
				+ " He replies, \"Suit yourself, I'm out of here,\" before wandering to the upper levels of the block.\n\nHis footsteps fade into the distance of"
				+ " one of the hallways above.");
	Scene cafeteria = new Scene("After entering the corridor, you arrive at the first junction and follow signs toward the cafeteria.\n\nYou reach the cafeteria"
				+ " doors and peer through the reinforced glass-- there is a group of armed prisoners at the far end of the cafeteria. None of them appear to be"
				+ " facing you.\n\nWhat's your next move?", 
		"Take the prisoners by surprise.", "Sneak to the kitchen.", "Hide in the corridor.", "Backtrack down the corridor.", 
		"You decide it's best to use the element of surprise to your advantage.\n\nWith their backs turned, you approach the group without a sound and ambush"
				+ " them.\n\nHowever, they outnumber you significantly, and your wounds have weakened you. You are slain in combat.", 
		"You decide this group is best left alone.\n\nYou slip into the cafeteria and creep silently toward the kitchen door.", 
		"You decide not to reveal your presence to the prisoners.\n\nAs you take cover in a nearby doorway, you hear footsteps approaching the cateferia doors."
				+ " They swing open and the prisoners emerge. The first through the door immediately spots you.\n\nYou attempt to flee, but it's futile-- they"
				+ " are too great in number. The last thing you see are boots stomping your face.", 
		"You decide not to reveal your presence to the prisoners.\n\nBacktracking down the corridor, you encounter a guard who orders you to halt. You offer him"
				+ " a pack of cigarettes in exhchange for safe passage through the cafeteria.\n\nHe leads the way back, swinging the cafeteria doors open and"
				+ " opening fire on the rioting prisoners as they scatter.");
	Scene kitchen = new Scene("Keeping an eye on the cafeteria, you press your back to the swinging doors into the kitchen.\n\nAs you cross the threshold, a hand"
				+ " grabs you from behind, with another pressing a knife to your throat. You hear the cook's distinctive chuckle as he recognises you and lets"
				+ " you go.\n\n\"Alright... you're good people. I'll let you go if you can answer this riddle... Poor people have it, rich people need it, but if"
				+ " you eat it, you die.\"\n\nWhat is it?", 
		"\"I don't know.\"", "\"I AM THE 1%!\"", "\"Just let me go.\"", "\"Nothing.\"", 
		"You answer, \"What kind of fucking riddle is that? I don't know, man.\"\n\n\"Then I guess you don't get to live,\" he replies, advancing toward you.\n\n"
				+ "You try to stop him, but are no match for the rotund man. He overpowers you, raising his knife overhead and plunging it into your neck.", 
		"You exclaim, \"I AM the 1%!\".\n\n\"I shoulda known you was one of them spoiled bitches. That'll make this all the sweeter,\" he quips.\n\nYou stumble"
				+ " backward, trying to put some distance between the two of you. The cook plants both feet, draws his arm back and takes aim, tossing the knife"
				+ " squarely between your eyes.", 
		"You plead, \"Just let me go.\"\n\nHe pauses, and with an obvious look of disapproval on his face, utters, \"I can't let you do that, Dave.\"\n\nHe steps"
				+ " foward, arms outstreched, and embraces you. Confused, you hug him back.\n\nYou feel cold steel plunge into your body between the shoulder"
				+ " blades as he whispers, \"I'm sorry...\" as your lifeless body slumps to the ground.", 
		"A smile crosses his face. \"Hell yeah!\" he exclaims, his contagious laughter filling the room. \"You always were too clever for your own good.\"");
	Scene yard = new Scene("Your answer satisfies the cook.\n\nHe ushers you toward the back of the room and into the rubbish chute. You slide down, landing"
				+ " in a dumpster below.\n\nYou stand to find yourself outside in one of the exercise yards.\n\nAs you vault out of the dumpster, a guard shouts"
				+ " \"HALT OR I WILL FUCKING END YOU!\".\n\nWhat do you do?", 
		"Make a break for it.", "Scramble back up the rubbish chute.", "Bribe the guard with cigarettes.", "Surrender.", 
		"You decide to ignore the guard and make a break for it.\n\nUnfortunately, the guard is in much better condition than a malnourished prisoner like you."
				+ " He catches you with relative ease and grabs you by the collar. You kick him and he stumbles backward, then draws his Taser from its holster."
				+ "\n\nYou must defend yourself.", 
		"You scurry back up the garbage shoot, hoping to reach the relative safety of the kitchen above.\n\nYou turn around, digging your fingernails into the"
				+ " grimy metal chute as you struggle to ascend.\n\nThe distinctive sound of a Taser rings out behind you, its probes planting in the small of"
				+ " your back. The electrical current flows through you, your body seizing and sliding down the chute into the dumpster below.\n\n You lay there,"
				+ " paralysed as the officer stands over you. You watch in horror as he removes his firearm from its holster, discharging three shots into your"
				+ " chest.\n\nHe puts on his aviators, and in true David Caruso fashion, mutters, \"Guess I'll leave this one to go out with the trash.\"", 
		"Thinking quickly, you pull out a pack of cigarettes and offer them to the guard.\n\nHe smiles, “I should have you waterboarded for escaping from"
				+ " your cell... but these are hard to come by, so I’ll look the other way this time.\"His radio lights up with chatter, \"I have to take"
				+ " this. If you're still here when I get back, I'll be taking a hell of a lot more than your shitty smokes.\"\n\nYou know better than to tempt"
				+ " fate again, and make like hell out of the yard.", 
		"Beaten and bruised, you know you've been bested.\n\nYou put your hands on your head and surrender to the guard. He cuffs you and leads you back to into"
				+ " the concrete labyrinth...");
	Scene infirmary = new Scene("Having dealt with the guard, you scramble toward and through the doors of the nearby infirmary, slamming them shut behind you."
				+ "\n\nYou have but a moment to catch your breath, and when you lift your head you find yourself face to face with a nurse.\n\nYour sudden"
				+ " appearance is met by a puzzled look on his worn face, \"You're in pretty bad shape. Let me help you.\"\n\nYou...", 
		"Accept offer of help.", "Fight the nurse.", "Ignore him and search the room for items.", "Have a mental breakdown.", 
		"You are in no condition for a fight, and he seems a comforting combination of genuine and harmless.\n\nYou take a seat on a nearby gurney and breathe a"
				+ " deep sigh of relief. You can feel your heartbeat slow as the adrenaline fades from your system.\n\nThe nurse places a gloved hand on your"
				+ " shoulder, but when you turn to greet him a needle plunges into your neck.\n\nThe room starts to spin and you fall to the floor, paralysed...", 
		"You've made it this far without much assistance, and you sure as hell don't need his.\n\n\"I don't need your help,\" you snarl, hunkering down and"
				+ " clenching your fists. He lunges at you and a struggle ensues.", 
		"You haven't any time to waste and, all things considered, medical staff is the least of your worries.\n\nThe nurse shuffles out the way you entered"
				+ " without another word as you move toward the cabinets at the back of the infirmary. The doors of all but one stand wide open-- they've already"
				+ " been raided. You hold your breath and pull the doors of the last cabinet open...\n\n\"Jackpot.\" Gauze, bandages, and, best of all, morphine."
				+ " You bandage your wounds as the narcotic's warm embrace washes over you.", 
		"It was hard enough being falsely imprisoned, but this is too much to bear.\n\nYou crumple the floor, taking your face in your hands as the nurse turns"
				+ " his back to you. You weep uncontrollably until he places a hand on your shoulder. You look up, eyes red and swollen. \"Come here, let's have"
				+ " a look at you,\" he says reassuringly as he helps you over to a gurney.\n\nHe bandages your wounds and doses you with something to dull the"
				+ " pain-- a warm, familiar feeling washes over you. \"That should hold you over for now,\" he says.");
	Scene entrance = new Scene("You emerge from the infirmary with a renewed sense of vigor. Your stride grows larger with each step carries you closer to the"
				+ " front gate, which is now in sight and slightly ajar. As you draw near, you realise it is not just ajar, but opening. The dull rumble of a"
				+ " diesel motor grows louder with every inch it opens.\n\nWith the gate mostly open, you see the prison bus barreling toward you with the warden"
				+ " behind the wheel.\n\nIn a split second, you decide to...", 
		"Throw your knife at the warden.", "Run for your life.", "Take cover.", "Embrace your fate.", 
		"You decide to throw your knife at the bus driver. It's a long shot, but you only have one chance.\n\nThe knife soars end-over-end through the air,"
				+ " seemingly hanging there as if suspended in time. And as suddenly as it started, the blade breaks through the windscreen and lodges itself in"
				+ " the warden's neck.\n\n\"Unbe-fucking-lievable...\" you mutter to yourself as you stumble toward the entry gate.", 
		"You know this isn't a fight you can win and take off running as fast as you can.\n\nThe warden, caught offguard, is all to eager to run you down. In his"
				+ " haste, he overestimates the handling capability of the bus.\n\nThe bus careens into a nearby storage shed with a thunderous sound. The"
				+ " cacophony yields to an explosion-- the vehicle's fuel tank punctured by the steel shed. The heat of the flames laps at your back as you are"
				+ " launched into a nearby wall.\n\nThe world fades to black...", 
		"There's no way you can outrun a bus, and a knife isn't the pinnacle of anti-vehicle weaponry. Your best bet is to take cover and force the warden from"
				+ " the vehicle.\n\nYou glance around and notice a stack of tires at the entrance of the vehicle maintenance bay. With no time to lose you sprint"
				+ " to the garage and climb inside the tires, doing your best to completely shield yourself within.\n\nBut the warden tracked your move across"
				+ " the yard and this isnt' a tire wall on a racing circuit. The warden hammers the throttle on a collision course.\n\nThe last thing you witness"
				+ " is the cacophony of metal and brick, with tires flying in every direction... You are crushed beneath the weight of the bus.", 
		"This is it. All those months spent inside, and this is how it ends... just inches from freedom.\n\nYou want it to be clear you're no threat, and"
				+ " toss your knife onto the gravel well in front of you. But in his fury, the warden takes no notice of this  white flag.\n\nThe bus careens"
				+ " down the gravel road, and the knife punctures the front driver's side tire. The warden can't correct the steering, and the bus veers sharply"
				+ " to the left, coming to an abrupt halt as it crushes into the prison walls.\n\nThe warden, thrown from the cabin in the crash, lays face down"
				+ " in the dirt. You approach with caution to find him either dead or unconscious, but you've no intention of sticking around to find out.");
	Scene escape = new Scene("The gravel makes a satisfying crunch as it rolls beneath your feet as you pass over the threshold of the gate. You take a deep"
				+ " breath of fresh, Cuban air and a moment to revel in the sunset.\n\nYou are free.");
	// Initialise array of scenes
	private Scene[] gitmo = {cell, cellblock, cafeteria, kitchen, yard, infirmary, entrance, escape};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrisonEscape window = new PrisonEscape();
					window.frame.setVisible(true);
					// Open the main menu
					window.mainMenu();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * This method simulates a combat instance between the two parameters
	 * @param player
	 * @param npc
	 */
	public static void combat(Player player, NonPlayerChar npc){						
				//Declare objects and variables local to this block
				Random rng = new Random();
				int quantity = rng.nextInt(3) + 1;
				//Keep fighting while player and npc are alive
				while(player.isAlive() && npc.isAlive()){			
					//Player attacks
					npc.setHealth(player.getAttackDamage());
					//If player attack kills npc then do/dont drop item and break
					if (!npc.isAlive()){
						if(npc.hasItem() == true){
							if(npc.itemDrop() == true){
								player.toiletWine(quantity);
								player.setWineCount(quantity);
								player.setSmokes((int) npc.getAttackDamage() / 3);		
							}
							else{
								player.setAttackDamage(quantity);
								player.setShankCount(quantity);
							}
						}
						break;
					}	
					//enemy attacks
					player.setHealth(npc.getAttackDamage());		
				}			
				//end fighting and return result	
			}
	
	/**
	 * Create the application.
	 */
	public PrisonEscape() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 640, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// textArea displays the narrative elements of the game
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(20, 50, 600, 320);
		textArea.setMargin(new Insets(10,10,10,10));
		frame.getContentPane().add(textArea);
		
		//btnOptionA-D are for displaying user options and receiving input
		btnOptionA = new JButton("Option A");
		btnOptionA.setBounds(20, 380, 600, 30);
		frame.getContentPane().add(btnOptionA);
		
		btnOptionB = new JButton("Option B");
		btnOptionB.setBounds(20, 410, 600, 30);
		frame.getContentPane().add(btnOptionB);
		
		btnOptionC = new JButton("Option C");
		btnOptionC.setBounds(20, 440, 600, 30);
		frame.getContentPane().add(btnOptionC);
		
		btnOptionD = new JButton("Option D");
		btnOptionD.setBounds(20, 470, 600, 30);
		frame.getContentPane().add(btnOptionD);
		
		// JLabels and corresponding JTextFields display player information (healthy, attack damage, etc)
		JLabel lblHealth = new JLabel("Health:");
		lblHealth.setBounds(20, 20, 61, 16);
		frame.getContentPane().add(lblHealth);
		
		textHealth = new JTextField();
		textHealth.setEditable(false);
		textHealth.setBounds(70, 15, 40, 26);
		frame.getContentPane().add(textHealth);
		textHealth.setColumns(10);
		
		JLabel lblDamage = new JLabel("Damage:");
		lblDamage.setBounds(140, 20, 61, 16);
		frame.getContentPane().add(lblDamage);
		
		textDamage = new JTextField();
		textDamage.setEditable(false);
		textDamage.setBounds(200, 15, 40, 26);
		frame.getContentPane().add(textDamage);
		textDamage.setColumns(10);
		
		JLabel lblSmokes = new JLabel("Smokes:");
		lblSmokes.setBounds(260, 20, 61, 16);
		frame.getContentPane().add(lblSmokes);
		
		textSmokes = new JTextField();
		textSmokes.setEditable(false);
		textSmokes.setBounds(320, 15, 40, 26);
		frame.getContentPane().add(textSmokes);
		textSmokes.setColumns(10);
		
		JLabel lblWine = new JLabel("Toilet Wine:");
		lblWine.setBounds(380, 20, 80, 16);
		frame.getContentPane().add(lblWine);
		
		textWine = new JTextField();
		textWine.setEditable(false);
		textWine.setBounds(460, 15, 40, 26);
		frame.getContentPane().add(textWine);
		textWine.setColumns(10);
		
		JLabel lblShanks = new JLabel("Shanks:");
		lblShanks.setBounds(520, 20, 61, 16);
		frame.getContentPane().add(lblShanks);
		
		textShanks = new JTextField();
		textShanks.setEditable(false);
		textShanks.setBounds(580, 15, 40, 26);
		frame.getContentPane().add(textShanks);
		textShanks.setColumns(10);
		
		// These two JTextPanes are displayed to the user upon "GAME OVER" or "VICTORY" conditions
		textGameOver = new JTextPane();
		textGameOver.setEditable(false);
		textGameOver.setBounds(220, 170, 200, 60);
		frame.getContentPane().add(textGameOver);
		textGameOver.setVisible(false);
		SimpleAttributeSet styleGameOver = new SimpleAttributeSet();  
		StyleConstants.setAlignment(styleGameOver , StyleConstants.ALIGN_CENTER);  
		StyleConstants.setForeground(styleGameOver, Color.RED);
		StyleConstants.setFontSize(styleGameOver, 30);
		textGameOver.setParagraphAttributes(styleGameOver,true);
		
		textEscape = new JTextPane();
		textEscape.setEditable(false);
		textEscape.setBounds(170, 120, 300, 60);
		frame.getContentPane().add(textEscape);
		textEscape.setVisible(false);
		SimpleAttributeSet styleEscape = new SimpleAttributeSet();  
		StyleConstants.setAlignment(styleEscape , StyleConstants.ALIGN_CENTER);  
		StyleConstants.setForeground(styleEscape, Color.GREEN);
		StyleConstants.setFontSize(styleEscape, 30);
		textEscape.setParagraphAttributes(styleEscape,true);
	}

	/**
	 * This method updates the choices available to the user as shown on buttons A-D
	 */
	private void updateChoices() {
		btnOptionA.setText(gitmo[currentScene].choiceA);
		btnOptionB.setText(gitmo[currentScene].choiceB);
		btnOptionC.setText(gitmo[currentScene].choiceC);
		btnOptionD.setText(gitmo[currentScene].choiceD);
	}
	
	/**
	 * This method updates the player's stats (health, attack damage, # smokes, # wine consumed, # shanks (possessed + used)
	 */
	private void updateStats() {
		textHealth.setText(Integer.toString(player.getHealth()));
		textDamage.setText(Integer.toString(player.getAttackDamage()));
		textSmokes.setText(Integer.toString(player.getSmokes()));
		textWine.setText(Integer.toString(player.getWineCount()));
		textShanks.setText(Integer.toString(player.getShankCount()));
	}
	
	/* This method has been deprecated in favor of stripSpecificActionListeners
	 *  Previously, this method removed the action listeners from buttons A-D so that could be assigned new action listeners in the subsequent screen
	 *  
	 * 	private void stripActionListeners() {
	 * 		btnOptionA.removeActionListener(btnOptionA.getActionListeners()[0]);
	 * 		btnOptionB.removeActionListener(btnOptionB.getActionListeners()[0]);
	 * 		btnOptionC.removeActionListener(btnOptionC.getActionListeners()[0]);
	 * 		btnOptionD.removeActionListener(btnOptionD.getActionListeners()[0]);
	 * 	}
	 */
	
	/**
	 * This method removes the action listeners for the buttons specified in the parameters:
	 * @param a -- if passed a '1', will strip the action listener for btnA
	 * @param b -- if passed a '1', will strip the action listener for btnB
	 * @param c -- if passed a '1', will strip the action listener for btnC
	 * @param d -- if passed a '1', will strip the action listener for btnD
	 */
	private void stripSpecificActionListeners(int a, int b, int c, int d) {
		if (a == 1) {
			btnOptionA.removeActionListener(btnOptionA.getActionListeners()[0]);
		}
		if (b == 1) {
			btnOptionB.removeActionListener(btnOptionB.getActionListeners()[0]);
		}
		if (c == 1) {
			btnOptionC.removeActionListener(btnOptionC.getActionListeners()[0]);
		}
		if (d == 1) {
			btnOptionD.removeActionListener(btnOptionD.getActionListeners()[0]);
		}
	}
	
	/**
	 * This method offers an independent binary toggle for the visibility of each button A-D:
	 * @param a: when passed a '0', will set btnOptionA visibility to 'false'; when passed a '1', will set btnOptionA visibility to 'true'
	 * @param b: when passed a '0', will set btnOptionB visibility to 'false'; when passed a '1', will set btnOptionB visibility to 'true'
	 * @param c: when passed a '0', will set btnOptionC visibility to 'false'; when passed a '1', will set btnOptionC visibility to 'true'
	 * @param d: when passed a '0', will set btnOptionD visibility to 'false'; when passed a '1', will set btnOptionD visibility to 'true'
	 */
	private void buttonVisibility(int a, int b, int c, int d) {
		if (a == 0) {
			btnOptionA.setVisible(false);
		}
		else if (a == 1) {
			btnOptionA.setVisible(true);
		}
		if (b == 0) {
			btnOptionB.setVisible(false);
		}
		else if (b == 1) {
			btnOptionB.setVisible(true);
		}
		if (c == 0) {
			btnOptionC.setVisible(false);
		}
		else if (c == 1) {
			btnOptionC.setVisible(true);
		}
		if (d == 0) {
			btnOptionD.setVisible(false);
		}
		else if (d == 1) {
			btnOptionD.setVisible(true);
		}
	}
	
	protected void mainMenu(){
		// Set appropriate button visibility
		buttonVisibility(1,1,0,1);
	    btnOptionA.setText("New Game");
    	btnOptionB.setText("Extras");
    	btnOptionD.setText("Quit");
		currentScene = 0;
		textArea.setText("Welcome to Escape from Guantanamo Bay!"
				+ "\n\nClick \"New Game\" to start a new game or \"Extras\" for more information.");
		// Create action listeners for these new choices
		btnOptionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.refreshStats(player);
				stripSpecificActionListeners(1,1,0,1);
				sceneOne();
			}
		});
		btnOptionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stripSpecificActionListeners(1,1,0,1);
				extras();
			}
		}); 
		btnOptionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	protected void extras() {
		// Set appropriate button visibility
		buttonVisibility(1,1,0,1);
		//Set description and button text
		textArea.setText("Escape from Guantanamo Bay is a text-based adventure game in which you have been imprisoned and must fight for your life to escape"
				+ " during a prisoner uprising. The plot advances based on, and responding to, player choice, offering a unique experience with each playthrough."
				+ "\n\nCharacter information (shown across the top of the window) is updated with each choice you make during the story.\n  Finding a shank"
				+ " will increase your attack damage by 25\n  Finding or crafting toilet wine will increase your health\n  Escape with as many smokes as possible"
				+ " to set a new high score");
		btnOptionA.setText("About");
		btnOptionB.setText("Statistics");
		btnOptionD.setText("Back to Main Menu");
		// Set button actions
		btnOptionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stripSpecificActionListeners(1,1,0,1);
				about();
			}
		});
		btnOptionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stripSpecificActionListeners(1,1,0,1);
				statistics();
			}
		});
		btnOptionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stripSpecificActionListeners(1,1,0,1);
				mainMenu();
			}
		});
	}
	
	protected void about() {
		// Set appropriate button visibility
		buttonVisibility(1,0,0,1);
		// Set button and description text
		textArea.setText("This game was authored by Paul Jerome, Brittany Mahoney, Colby Pernela and Kelso Pickett for Caleb Horst's Java Programming class"
				+ " (CS142) at Tacoma Community College in Spring 2016."
				+ "\n\nSource and revision history are available at https://github.com/kelsopickett/PrisonEscape"
				+ "\n\nStory by: Paul Jerome, Brittany Mahoney, Colby Pernela, Kelso Pickett"
				+ "\n\nScript by: Kelso Pickett"
				+ "\n\nCode by: Paul Jerome, Kelso Pickett"
				+ "\n\nAdditional Design by: Brittany Mahoney, Colby Pernela");
		btnOptionA.setText("Back to Extras");
		btnOptionD.setText("Back to Main Menu");
		btnOptionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stripSpecificActionListeners(1,0,0,1);
				extras();
			}
		});
		btnOptionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stripSpecificActionListeners(1,0,0,1);
				mainMenu();
			}
		});
	}
	
	protected void statistics() {
		// Set appropriate button visibility
		buttonVisibility(1,0,0,1);
		btnOptionA.setText("Back to Extras");
		btnOptionD.setText("Back to Main Menu");
		if (statPlaythroughs < 1) {
			textArea.setText("In this session you've:\n\n ... played " + statPlaythroughs + " times.\n\n ... won " + statVictories + " times.\n\n ... accumulated "
					+ statHighscore + " cigrettes in a single playthrough.\n\n ... entered combat " + statCombats + " times.\n\nYour success rate can't be calculated"
					+ " because you haven't yet played!");
		}
		else {
			textArea.setText("In this session you've:\n\n ... played " + statPlaythroughs + " times.\n\n ... won " + statVictories + " times.\n\n ... accumulated " 
					+ statHighscore + " cigrettes in a single playthrough.\n\n ... entered combat " + statCombats + " times.\n\nYour success rate is " +
					(((double)statVictories)/((double)statPlaythroughs))*(100) + "%.");
		}
		btnOptionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stripSpecificActionListeners(1,0,0,1);
				extras();
			}
		});
		btnOptionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stripSpecificActionListeners(1,0,0,1);
				mainMenu();
			}
		});
	}

	protected void sceneOne() {
		// Render first scene description in textArea, user options in buttons A-D, and update display of player stats
		textArea.setText(gitmo[currentScene].description);
		updateStats();
		updateChoices();
		// Ensure buttons are visible
		buttonVisibility(1,1,1,1);
		// Reset health and alive status of NPCs to ensure combat encounters work on successful playthroughs
		prisoner1.LiveorDead(true);
		yardGuard.LiveorDead(true);
		nurse.LiveorDead(true);
		prisoner1.resetHealth(80);
		yardGuard.resetHealth(100);
		nurse.resetHealth(200);
		// Increment stat counter(s)
		statPlaythroughs ++;
		statHighscore = player.getSmokes();
		// user choice = option a: search the cell, find a shank and force door open
		btnOptionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualize player effects (+ATK, +SHANK, +HP)
				player.setAttackDamage(1);
				player.setShankCount(1);
				player.setHealth(20);
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultA + "\n\n" + gitmo[(currentScene + 1)].description);
				// Increment scene counter
				currentScene++;
				// Unassign actions from buttons so they can be reassigned in next scene
				stripSpecificActionListeners(1,1,1,1);
				// Advance to next scene
				sceneTwo();
			}
		});
		// user choice = option b: make toilet wine, find lockpick and escape
		btnOptionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualize player effects (+TW)
				player.toiletWine(1);
				player.setWineCount(1);
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultB + "\n\n" + gitmo[(currentScene + 1)].description);
				// Increment scene counter
				currentScene++;
				// Unassign actions from buttons so they can be reassigned in next scene
				stripSpecificActionListeners(1,1,1,1);
				// Advance to next scene
				sceneTwo();
			}
		});
		// user choice = option c: get back in bed, is killed
		btnOptionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualize player effects
				player.LiveorDead(false);
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultC);
				// Unassign actions from buttons so they can be reassigned in next scene
				stripSpecificActionListeners(1,1,1,1);
				gameOver();
			}
		});
		// user choice = option d: approach cell door, force way out
		btnOptionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualize player effects (-HP)
				player.setHealth(20);
				// Render result of choice and description of next scene 
				textArea.setText(gitmo[currentScene].resultD + "\n\n" + gitmo[(currentScene + 1)].description);
				// Increment scene counter
				currentScene++;
				// Unassign actions from buttons so they can be reassigned in next scene
				stripSpecificActionListeners(1,1,1,1);
				// Advance to next scene
				sceneTwo();
			}
		});
	}

	protected void sceneTwo() {
		// Update player stats and user choices for current scene
		updateStats();
		updateChoices();
		// Increment stat counter(s)
		statHighscore = player.getSmokes();
		// user choice = option a: turn and flee, advancing them to the next scene sans consequence
		btnOptionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultA + "\n\n" + gitmo[(currentScene + 1)].description);
				// Increment scene counter
				currentScene++;
				// Unassign actions from buttons so they can be reassigned in next scene
				stripSpecificActionListeners(1,1,1,1);
				// Advance to next scene
				sceneThree();
			}
		});
		// user choice = option b: stand and fight, combat ensues and user advances less health plus items
		btnOptionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualize player effects (COMBAT)
				combat(player, prisoner1);
				// Increment stat counter(s)
				statCombats ++;
				// If the player survives combat, proceed as usual; else, game over
				if (player.isAlive() == true) {
					// Render result of choice and description of next scene
					textArea.setText(gitmo[currentScene].resultB + "\n\n" + gitmo[(currentScene + 1)].description);
					// Increment scene counter
					currentScene++;
					// Unassign actions from buttons so they can be reassigned in gameOver
					stripSpecificActionListeners(1,1,1,1);
					// Advance to next scene
					sceneThree();
				}
				else {
					// Render result of choice
					textArea.setText(gitmo[currentScene].resultB);
					stripSpecificActionListeners(1,1,1,1);
					gameOver();
					
				}
			}
		});
		// user choice = option c: assume the fetal position, dies
		btnOptionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// actualize player effects (DEATH)
				player.LiveorDead(false);
				updateStats();
				// Render result of choice
				textArea.setText(gitmo[currentScene].resultC);
				// Unassign actions from buttons so they can be reassigned in gameOver
				stripSpecificActionListeners(1,1,1,1);
				gameOver();
			}
		});
		// user choice = option d: talk their way out, user advances with an item
		btnOptionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualize player effects (+SHANK)
				player.setAttackDamage(1);
				player.setShankCount(1);
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultD + "\n\n" + gitmo[(currentScene + 1)].description);
				// Increment scene counter
				currentScene++;
				// Unassign actions from buttons so they can be reassigned in gameOver
				stripSpecificActionListeners(1,1,1,1);
				// Advance to next scene
				sceneThree();
			}
		});
	}

	protected void sceneThree() {
		// Update player stats and user choices for current scene
		updateStats();
		updateChoices();
		// Increment stat counter(s)
		statHighscore = player.getSmokes();
		// user choice = option a: take the prisoners by surprise, resulting in loss by death
		btnOptionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualize player effects (DEATH)
				player.LiveorDead(false);
				// Update player stats
				updateStats();
				// Render result of choice
				textArea.setText(gitmo[currentScene].resultA);
				// Unassign actions from buttons so they can be reassigned in next scene
				stripSpecificActionListeners(1,1,1,1);
				gameOver();
			}
		});
		// user choice = option b: sneak to the kitchen, advance
		btnOptionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultB + "\n\n" + gitmo[(currentScene + 1)].description);
				// Increment scene counter
				currentScene++;
				// Unassign actions from buttons so they can be reassigned in next scene
				stripSpecificActionListeners(1,1,1,1);
				// Advance to next scene
				sceneFour();
			}
		});
		// user choice = option c: hide in the corridor, resulting in loss by death
		btnOptionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualize player effects (DEATH)
				player.LiveorDead(false);
				// Update player stats
				updateStats();
				// Render result of choice
				textArea.setText(gitmo[currentScene].resultC);
				// Unassign actions from buttons so they can be reassigned in next scene
				stripSpecificActionListeners(1,1,1,1);
				gameOver();
			}
		});
		// user choice = option d: backtrack down corridor, resulting in guard encounter, advance
		btnOptionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualize player effects (-SMK)
				player.setSmokes(-20);
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultD + "\n\n" + gitmo[(currentScene + 1)].description);
				// Increment scene counter
				currentScene++;
				// Unassign actions from buttons so they can be reassigned in next scene
				stripSpecificActionListeners(1,1,1,1);
				// Advance to next scene
				sceneFour();
			}
		});
	}

	protected void sceneFour() {
		// Update player stats and user choices for current scene
		updateStats();
		updateChoices();
		// Increment stat counter(s)
		statHighscore = player.getSmokes();
		// user choice = option a: "I don't know"
		btnOptionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualize player effects (DEATH)
				player.LiveorDead(false);
				// Update player stats
				updateStats();
				// Render result of choice
				textArea.setText(gitmo[currentScene].resultA);
				// Unassign actions from buttons so they can be reassigned in next scene
				stripSpecificActionListeners(1,1,1,1);
				gameOver();
			}
		});
		// user choice = option b: provide wrong answer
		btnOptionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualize player effects (DEATH)
				player.LiveorDead(false);
				// Update player stats
				updateStats();
				// Render result of choice
				textArea.setText(gitmo[currentScene].resultB);
				// Unassign actions from buttons so they can be reassigned in next scene
				stripSpecificActionListeners(1,1,1,1);
				gameOver();
			}
		});
		// user choice = option c: provide wrong answer
		btnOptionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualize player effects (DEATH)
				player.LiveorDead(false);
				// Update player stats
				updateStats();
				// Render result of choice
				textArea.setText(gitmo[currentScene].resultC);
				// Unassign actions from buttons so they can be reassigned in next scene
				stripSpecificActionListeners(1,1,1,1);
				gameOver();
			}
		});
		// user choice = option d: provide correct answer
		btnOptionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualize player effects (+SMK)
				player.setSmokes(rng.nextInt(42));
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultD + "\n\n" + gitmo[(currentScene + 1)].description);
				// Increment scene counter
				currentScene++;
				// Unassign actions from buttons so they can be reassigned in next scene
				stripSpecificActionListeners(1,1,1,1);
				// Advance to next scene
				sceneFive();
			}
		});
	}

	protected void sceneFive() {
		// Update user choices for current scene
		updateStats();
		updateChoices();
		// Increment stat counter(s)
		statHighscore = player.getSmokes();
		// user choice = option a: turn and run; combat, advance if alive
		btnOptionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualise player effects (COMBAT)
				combat(player, yardGuard);
				// Increment stat counter(s)
				statCombats ++;
				// If the player survives combat, proceed as usual; else, game over
				if (player.isAlive() == true) {
					// Render result of choice and description of next scene
					textArea.setText(gitmo[currentScene].resultA + "\n\n" + gitmo[(currentScene + 1)].description);
					// Increment scene counter
					currentScene++;
					// Unassign actions from buttons so they can be reassigned in next scene
					stripSpecificActionListeners(1,1,1,1);
					// Advance to next scene
					sceneSix();
				}
				else {
					// Render result of choice
					textArea.setText(gitmo[currentScene].resultA);
					stripSpecificActionListeners(1,1,1,1);
					gameOver();
				}
			}
		});
		// user choice = option b: scramble up rubbish chute; shot to death by guard
		btnOptionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.LiveorDead(false);
				// Update player stats
				updateStats();
				// Render result of choice
				textArea.setText(gitmo[currentScene].resultB);
				// Unassign actions from buttons so they can be reassigned in next scene
				stripSpecificActionListeners(1,1,1,1);
				gameOver();
			}
		});
		// user choice = option c:bribe guard with cigarettes; advance
		btnOptionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player.getSmokes() >= 20) {
					// Actualize player effects (-SMK)
					player.setSmokes(-20);
					// Render result of choice (when player has sufficient smokes to complete bribe) and description of next scene
					textArea.setText(gitmo[currentScene].resultC + "\n\n" + gitmo[(currentScene + 1)].description);
					// Increment scene counter
					currentScene++;
					// Unassign actions from buttons so they can be reassigned in next scene
					stripSpecificActionListeners(1,1,1,1);
					// Advance to next scene
					sceneSix();
				}
				else {
					// Actualise player effects (-SMK)
					player.setSmokes(-player.getSmokes());
					// Render result of choice when player has fewer than a whole pack of smokes
					textArea.setText("Thinking quickly, you pull out a pack of cigarettes and offer them to the guard.\n\n\"This all you got? You gotta be"
							+ " fuckin' kidding me, this ain't even a whole pack! I'm taking you back to your cell.\"");
					// Initiate a timed delay so the user can read the result of their choice before returning to the first scene
					Timer delay = new Timer(10_000, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// Reset scene counter
							currentScene = 0;
							// Unassign actions from buttons so they can be reassigned in next scene
							stripSpecificActionListeners(1,1,1,1);
							// Return to first scene
							sceneOne();
						}
					});
					delay.setRepeats(false);
					delay.start();
				}
			}
		});
		// user choice = option d: surrender; lose but no death
		btnOptionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice
				textArea.setText(gitmo[currentScene].resultD);
				// Unassign actions from buttons so they can be reassigned in next scene
				stripSpecificActionListeners(1,1,1,1);
				// Initiate timed delay so user can read the result of their choice before returning to first scene
				Timer delay = new Timer(10000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// Reset scene counter
						currentScene = 0;
						// Return to first scene
						sceneOne();
					}
				});
				delay.setRepeats(false);
				delay.start();
			}
		});
	}

	protected void sceneSix() {
		// Update user choices for current scene
		updateStats();
		updateChoices();
		// Increment stat counter(s)
		statHighscore = player.getSmokes();
		// user choice = option a: accept offer of help; something bad happens, loss
		btnOptionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualize player effects (PARALYSIS)
				player.LiveorDead(false);
				// Render result of choice
				textArea.setText(gitmo[currentScene].resultA);
				// Unassign actions from buttons so they can be reassigned in next scene
				stripSpecificActionListeners(1,1,1,1);
				gameOver();
			}
		});
		// user choice = option b: fight the nurse; combat, advance if alive
		btnOptionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualise player effects (COMBAT)
				combat(player,nurse);
				// Increment stat counter(s)
				statCombats ++;
				// If the player survives combat, proceed as usual; else, game over
				if (player.isAlive() == true) {
					// Render result of choice and description of next scene
					textArea.setText(gitmo[currentScene].resultB + "\n\n" + gitmo[(currentScene + 1)].description);
					// Increment scene counter
					currentScene++;
					// Unassign actions from buttons so they can be reassigned in next scene
					stripSpecificActionListeners(1,1,1,1);
					// Advance to next scene
					sceneSeven();
				}
				else {
					// Render result of choice
					textArea.setText(gitmo[currentScene].resultB);
					// Unassign actions from buttons so they can be reassigned in next scene
					stripSpecificActionListeners(1,1,1,1);
					gameOver();
				}
			}
		});
		// user choice = option c: ignore, search for items, advance
		btnOptionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualize player effects (CHANCE: +HP)
				int findChance = rng.nextInt(100);
				if (findChance >= 75){
					player.toiletWine(1);
				}
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultC + "\n\n" + gitmo[(currentScene + 1)].description);
				// Increment scene counter
				currentScene++;
				// Unassign actions from buttons so they can be reassigned in next scene
				stripSpecificActionListeners(1,1,1,1);
				// Advance to next scene
				sceneSeven();
			}
		});
		// user choice = option d: have mental breakdown; nurse consoles you, advance
		btnOptionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualize player effects (+SMK, +HP)
				player.setSmokes(rng.nextInt(70) + 1);
				player.toiletWine(1);
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultD + "\n\n" + gitmo[(currentScene + 1)].description);
				// Increment scene counter
				currentScene++;
				// Unassign actions from buttons so they can be reassigned in next scene
				stripSpecificActionListeners(1,1,1,1);
				// Advance to next scene
				sceneSeven();
			}
		});
	}
	
	protected void sceneSeven() {
		// Update user choices for current scene
		updateStats();
		updateChoices();
		// Increment stat counter(s)
		statHighscore = player.getSmokes();
		// user choice = option a: throw knife at bus driver; combat (really low chance of success), advance if alive
		btnOptionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualize player effects
				int winChance = rng.nextInt(100) + 1;
				if (winChance == 100) {
					// Render result of choice and description of next scene
					textArea.setText(gitmo[currentScene].resultA + "\n\n" + gitmo[(currentScene + 1)].description);
					// Increment scene counter
					currentScene++;
					// Unassign actions from buttons so they can be reassigned in next scene
					stripSpecificActionListeners(1,1,1,1);
					// Advance to next scene
					sceneEight();
				}
				else{
					// Actualize player effects (DEATH)
					player.LiveorDead(false);
					// Render result of choice
					textArea.setText("You decide to throw your knife at the bus driver. It's a long shot, but you only have one chance.\n\nBut luck is not on"
							+ " your side; the handle bounces off windshield. The warden doesn't take kindly to having deadly objects thrown at him and runs you"
							+ " down with ten tons of American-made steel retribution.");
					stripSpecificActionListeners(1,1,1,1);
					gameOver();
				}
			}
		});
		// user choice = option b: turn and run; bus rolls, explodes, and you black out... return to first scene
		btnOptionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice
				textArea.setText(gitmo[currentScene].resultB);
				// Unassign actions from buttons so they can be reassigned in next scene
				stripSpecificActionListeners(1,1,1,1);
				// Initiate timed delay so user can read the result of their choice before returning to first scene
				Timer delay = new Timer(10000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// Reset scene counter
						currentScene = 0;
						// Return to first scene
						sceneOne();
					}
				});
				delay.setRepeats(false);
				delay.start();
			}
		});
		// user choice = option c: hide behind something; run over by bus, die and lose
		btnOptionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualize player effects (DEATH)
				player.LiveorDead(false);
				// Render result of choice
				textArea.setText(gitmo[currentScene].resultC);
				// Unassign actions from buttons so they can be reassigned in next scene
				stripSpecificActionListeners(1,1,1,1);
				gameOver();
			}
		});
		// user choice = option d: embrace death; toss shank, bus runs it over, tire pops and veers away... advance
		btnOptionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualize player effects (+SMK)
				player.setSmokes(100);
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultD + "\n\n" + gitmo[(currentScene + 1)].description);
				// Increment scene counter
				currentScene++;
				// Unassign actions from buttons so they can be reassigned in next scene
				stripSpecificActionListeners(1,1,1,1);
				// Advance to next scene
				sceneEight();
			}
		});
	}
	
	protected void sceneEight() {
		// Update player stats
		updateStats();
		// Set appropriate button visibility
		buttonVisibility(1,0,1,1);
		// Increment stat counter(s)
		statVictories ++;
		statHighscore = player.getSmokes();
		// Make the "Game Over" message visible
		textEscape.setVisible(true);
		textEscape.setText("YOU HAVE ESCAPED");
		// Update player choices (unlike previous scenes, these are unique to the Game Over scenario)
		btnOptionA.setText("New Game+");
		btnOptionC.setText("Main Menu");
		btnOptionD.setText("Quit");
		// Create action listeners for these new choices
		btnOptionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Reset scene counter
				currentScene = 0;
				textEscape.setVisible(false);
				stripSpecificActionListeners(1,0,1,1);
				sceneOne();
			}
		});
		btnOptionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textEscape.setVisible(false);
				stripSpecificActionListeners(1,0,1,1);
				mainMenu();
			}
		});
		btnOptionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	protected void gameOver(){
		// Update player stats
		updateStats();
		// Make the "Game Over" message visible
		textGameOver.setText("Game Over");
		textGameOver.setVisible(true);
		// Set appropriate button visibility
		buttonVisibility(1,0,0,1);
		// Update player choices (unlike previous scenes, these are unique to the Game Over scenario)
		btnOptionA.setText("Back to Main Menu");
		btnOptionD.setText("Quit");
		// Create action listeners for these new choices
		btnOptionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textGameOver.setVisible(false);
				stripSpecificActionListeners(1,0,0,1);
				mainMenu();
			}
		});
		btnOptionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}
