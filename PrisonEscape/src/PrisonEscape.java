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
	private JTextField textHealth;
	private JTextField textSmokes;
	private JTextField textWine;
	private JTextField textShanks;
	private JTextField textDamage;
	private JTextPane textGameOver;
	private JTextPane textEscape;
	private JButton btnOptionA;
	private JButton btnOptionB;
	private JButton btnOptionC;
	private JButton btnOptionD;
	//Declare class variables
	private static int currentScene = 0;
	// Instantiate player and enemies
	Player player = new Player();
	NonPlayerChar prisoner1 = new NonPlayerChar(80, 40, "Geralt", true); // Geralt has partial health and a shank (+15 DMG over 25 base DMG)
	NonPlayerChar yardGuard = new NonPlayerChar(100, 50, "Leeloo", true); // Leeloo has full health and a taser (+25 DMG over 25 base DMG)
	NonPlayerChar nurse = new NonPlayerChar(200, 25, "Kevorkian", true); // Kevorkian is overhealed, but fights with his fists at 25 base DMG)
	NonPlayerChar warden = new NonPlayerChar();
	// Declare tracked statistics
	private int statPlaythroughs = 0;
	private int statVictories = 0;
	private int statHighscore = 0;
	private int statCombats = 0;
	
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
				+ " \"HALT OR I WILL FUCKING TASE YOU!\".\n\nWhat do you do?", 
		"Make a break for it.", "Scramble back up the rubbish chute.", "Bribe the guard with cigarettes.", "Surrender.", 
		"You decide to ignore the guard and make a break for it.\n\nUnfortunately, the guard is in much better condition than a malnourished prisoner like you."
				+ " He catches you with relative ease and grabs you by the collar. You kick him and he stumbles backward, then draws his Taser from its holster."
				+ "\n\nYou must defend yourself.", 
		"You decide to scurry back up the garbage shoot, back the way you came.\n\nYou turn around, digging your fingernails into the grimy metal chute as you"
				+ " struggle to ascend.\n\nThe distinctive sound of a Taser rings out behind you, its probes planting in the small of your back. The electrical"
				+ " current flows through you, your body seizing and sliding down the chute into the dumpster below.\n\n You lay there, paralysed as the officer"
				+ " stands over you. You watch in horror as he removes his firearm from its holster, discharging three shots into your chest.\n\nHe puts on his"
				+ " aviators, and in true David Caruso fashion, mutters, \"Guess I'll leave this one to go out with the trash.\"", 
		"Thinking quickly, you pull out a pack of cigarettes and offer them to the guard.\n\nHe smiles, “I should have you waterboarded for escaping from"
				+ " your cell... but these are hard to come by, so I’ll look the other way this time.\"\n\n\"I’m going to walk the perimeter of the yard. If"
				+ " you're still here when I get back, I'll be taking a hell of a lot more than your shitty smokes.\"\n\nYou know better than to tempt fate"
				+ " again, and make like hell out of the yard.", 
		"You decide that there is nowhere to go and that you have lost. You put your hands on your head and tell the guard that you surrender. The"
				+ " guard puts handcuffs on you and takes you back to your cell.");
	Scene infirmary = new Scene("You scramble toward and through the doors of the nearby infirmary, slamming them shut behind"
			+ " you.\n\nAfter taking a moment to catch your breath, you turn and find yourself face to face with a nurse. He gives"
			+ " you a puzzled look, \"You're in pretty bad shape. Let me help you.\"\n\nYou...", 
		"Accept offer of help.", "Fight the nurse.", "Ignore him and search the room for items.", "Have a mental breakdown.", 
		"You are in no condition for a fight, and he seems a comforting combination of genuine and harmless.\n\nYou take a seat on a"
			+ " nearby gurney and breath a deep sigh of relief. You can feel your heartbeat slow as the adrenaline fades from your system."
			+ "\n\nThe nurse places a gloved hand on your shoulder, but when you turn to greet him a needle plunges into your neck. The"
			+ " room starts to spin and you fall to the floor, paralysed.", 
		"You've made it this far without much assistance, and you sure as hell don't need his.\n\n\"I don't need your help,\" you snarl,"
			+ " hunkering down and clenching your fists. He lunges at you and a struggle ensues.", 
		"You haven't any time to waste and, all things considered, medical staff is the least of your worries.\n\nthe nurse shuffles out "
			+ "the way you entered as you move toward the cabinets at the back of the infirmary. The doors of all but one stand wide open--"
			+ " they've already been raided. You hold your breath and pull the doors of the last cabinet open...\n\n\"Jackpot.\" Gauze,"
			+ " bandages, and, best of all, morphine. You bandage yourself up as the narcotic's warm embrace washes over you.", 
		"It was hard enough being falsely imprisoned, but this is too much to bear.\n\nYou crumple the floor, taking your face in your"
			+ " hands as the nurse turns his back to you. You weep uncontrollably for minutes, maybe hours, until the nurse places a hand on"
			+ " your shoulder. You look up, eyes red and swollen. \"Come here, let's have a look at you,\" he says reassuringly as he helps"
			+ " you to your feet and over to a gurney. He bandages your wounds and doses you with something to dull the pain-- a warm, familiar"
			+ " feeling washes over you. \"That should hold you over for now,\" he says, before sending you on your way.");
	Scene entrance = new Scene("You emerge from the infirmary with a renewed sense of vigor. You stride grows larger as"
			+ " each step takes you closer to the front gate, which is now in sight and slightly ajar.\n\nAs you draw near,"
			+ " you realise it is not just ajar, but opening. The dull rumble of a disel motor grows louder with every inch it opens."
			+ " With the gate mostly open, you see the prison bus barreling toward you with the warden behind the wheel.\n\n"
			+ " In a split second, you decide to...", 
		"Throw your knife at the warden.", "Run for your life.", "Take cover.", "Embrace your fate.", 
		"You decide to throw your knife at the bus driver. It's a long shot, but you only have one chance.\n\nThe knife soars end-over-end through the air,"
				+ " seemingly hanging there as if suspended in time. And as suddenly as it started, the blade breaks through the windscreen and lodges itself in"
				+ " the warden's neck.\n\n\"Unbe-fucking-lievable...\" you mutter to yourself as you shuffle toward the entry gate.", 
		"You decide to run from the bus. You take off running as fast as you can. The bus driver having never had a runaway before, gets a bit"
			+ " too anxious to catch you and accelerates too quickly. The bus careens wildly into a nearby storage shed. You hear a loud"
			+ " explosion and feel the heat of the flames as you are launched into a nearby wall. The world fades to black.", 
		"You see the bus coming and decide your best choice of action is to try and hide. You quickly look around and notice a stack of tires by"
			+ " a nearby garage. You run over to the garage and climb inside, doing your best to completely submerge yourself within the ring of"
			+ " tires. In the end your efforts were in vain, because the bus driver saw you running and hit the gas. The last thing you remember"
			+ " is the loud crash of the bus colliding with the pile of tires as you are crushed beneath the weight of the bus.", 
		"You assess the situation and realize that there is no way you're getting out of this scenario alive. You want it to appear as if you are"
			+ " cooperating, so you toss your knife onto the gravel in front of you. The bus driver doesn't appear to notice the knife as he drives"
			+ " over it, popping a front tire in the process. The bus veers sharply and collides with the prison walls, the bus driver appears to be"
			+ " dead or unconscious. Somehow you seem to be free, you make a run for the entry gate.");
	Scene escape = new Scene("The gravel makes a satisfying sound beneath your feet as you walk out the gate. You take a deep"
		+ " breath of fresh, Cuban air and a moment to revel in the sunset. You are free.");

	// These scenes and array were used in various capacities during testing, but not in the current release
	/*
	 * Scene fightScreen = new Scene("You are fighting!", "Attack!?" , "Kill yourself!?");
	 * Scene startScreen = new Scene("Welcome to Escape From Guantanamo Bay, please click an option below", "Start new game", "Exit Application");
	 * Scene gameOver = new Scene("Wow your a loser, GAME OVER!!!!!", "Start a new game", "Exit Application");
	 * private Scene[] misc = {startScreen,gameOver};
	 */
	
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
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(20, 50, 600, 320);
		textArea.setMargin(new Insets(10,10,10,10));
		frame.getContentPane().add(textArea);
		
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
		textEscape.setBounds(220, 120, 300, 60);
		frame.getContentPane().add(textEscape);
		textEscape.setVisible(false);
		SimpleAttributeSet styleEscape = new SimpleAttributeSet();  
		StyleConstants.setAlignment(styleEscape , StyleConstants.ALIGN_CENTER);  
		StyleConstants.setForeground(styleEscape, Color.GREEN);
		StyleConstants.setFontSize(styleEscape, 30);
		textEscape.setParagraphAttributes(styleEscape,true);
	}

	/**
	 * This method updates the choices available to the user on buttons A-D
	 */
	private void updateChoices() {
		btnOptionA.setText(gitmo[currentScene].choiceA);
		btnOptionB.setText(gitmo[currentScene].choiceB);
		btnOptionC.setText(gitmo[currentScene].choiceC);
		btnOptionD.setText(gitmo[currentScene].choiceD);
	}
	
	/**
	 * This method updates the player's stats (health, atk dmg, # smokes, # wine consumed, # shanks (possessed + used)
	 */
	private void updateStats() {
		textHealth.setText(Integer.toString(player.getHealth()));
		textDamage.setText(Integer.toString(player.getAttackDamage()));
		textSmokes.setText(Integer.toString(player.getSmokes()));
		textWine.setText(Integer.toString(player.getWineCount()));
		textShanks.setText(Integer.toString(player.getShankCount()));
	}
	
	/**
	 * This method removes the action listeners from all four buttons so that they may be assigned new action listeners in the subsequent scene
	 */
	private void stripActionListeners() {
		btnOptionA.removeActionListener(btnOptionA.getActionListeners()[0]);
		btnOptionB.removeActionListener(btnOptionB.getActionListeners()[0]);
		btnOptionC.removeActionListener(btnOptionC.getActionListeners()[0]);
		btnOptionD.removeActionListener(btnOptionD.getActionListeners()[0]);
	}
	
	/**
	 * This method removes the action listeners for the buttons specified in the parameters:
	 * parameter: int a -- if passed a '1', will strip the action listener for btnA
	 * parameter: int b -- if passed a '1', will strip the action listener for btnB
	 * parameter: int c -- if passed a '1', will strip the action listener for btnC
	 * parameter: int d -- if passed a '1', will strip the action listener for btnD
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
				stripActionListeners();
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
				stripActionListeners();
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
				stripActionListeners();
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
				stripActionListeners();
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
				stripActionListeners();
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
					stripActionListeners();
					// Advance to next scene
					sceneThree();
				}
				else {
					// Render result of choice
					textArea.setText(gitmo[currentScene].resultB);
					stripActionListeners();
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
				stripActionListeners();
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
				stripActionListeners();
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
				stripActionListeners();
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
				stripActionListeners();
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
				stripActionListeners();
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
				stripActionListeners();
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
				stripActionListeners();
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
				stripActionListeners();
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
				stripActionListeners();
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
				stripActionListeners();
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
					stripActionListeners();
					// Advance to next scene
					sceneSix();
				}
				else {
					// Render result of choice
					textArea.setText(gitmo[currentScene].resultA);
					stripActionListeners();
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
				stripActionListeners();
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
					stripActionListeners();
					// Advance to next scene
					sceneSix();
				}
				else {
					// Actualise player effects (-SMK)
					player.setSmokes(-player.getSmokes());
					// Render result of choice when player has fewer than a whole pack of smokes
					textArea.setText("Thinking quickly, you pull out a pack of cigarettes and offer them to the guard.\n\n\"This all you got? You gotta be"
							+ " fuckin' kidding me, it ain't even a whole pack! I'm taking you back to your cell.\"");
					//TODO We need a timer here so the user can read the result before returning to the first scene
					// Reset scene counter
					currentScene = 0;
					// Unassign actions from buttons so they can be reassigned in next scene
					stripActionListeners();
					// Return to first scene
					sceneOne();
				}
			}
		});
		// user choice = option d: surrender; lose but no death
		btnOptionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice
				textArea.setText(gitmo[currentScene].resultD);
				// Unassign actions from buttons so they can be reassigned in next scene
				stripActionListeners();
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
				stripActionListeners();
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
					stripActionListeners();
					// Advance to next scene
					sceneSeven();
				}
				else {
					// Render result of choice
					textArea.setText(gitmo[currentScene].resultB);
					// Unassign actions from buttons so they can be reassigned in next scene
					stripActionListeners();
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
				stripActionListeners();
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
				stripActionListeners();
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
				//int winChance = rng.nextInt(100) + 1;
				int winChance = 100;
				System.out.println(winChance);
				if (winChance == 100) {
					// Render result of choice and description of next scene
					textArea.setText(gitmo[currentScene].resultA + "\n\n" + gitmo[(currentScene + 1)].description);
					// Increment scene counter
					currentScene++;
					// Unassign actions from buttons so they can be reassigned in next scene
					stripActionListeners();
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
					stripActionListeners();
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
				stripActionListeners();
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
				stripActionListeners();
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
				stripActionListeners();
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
				stripSpecificActionListeners(1,0,1,1);
				sceneOne();
			}
		});
		btnOptionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		buttonVisibility(0,1,1,0);
		// Update player choices (unlike previous scenes, these are unique to the Game Over scenario)
		btnOptionB.setText("Main Menu");
		btnOptionC.setText("Quit");
		// Create action listeners for these new choices
		btnOptionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stripSpecificActionListeners(0,1,1,0);
				mainMenu();
			}
		});
		btnOptionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	protected void mainMenu(){
		// Set appropriate button visibility
		buttonVisibility(0,1,1,0);
	    btnOptionB.setText("New Game");
    	btnOptionC.setText("Extras");
		currentScene = 0;
		textArea.setText("Welcome to Escape from Guantanamo Bay! Click \"New Game\" to begin.");	
		// Create action listeners for these new choices
		btnOptionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.refreshStats(player);
				stripSpecificActionListeners(0,1,1,0);
				sceneOne();
			}
		});
		btnOptionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stripSpecificActionListeners(0,1,1,0);
				extras();
			}
		}); 
	}
	
	protected void extras() {
		// Set appropriate button visibility
		buttonVisibility(1,1,1,0);
		//Set button and description text
		textArea.setText("Please select an option from the choices below:");
		btnOptionA.setText("Main menu");
		btnOptionB.setText("About");
		btnOptionC.setText("Statistics");
		// Create action listeners for these new choices
		btnOptionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stripSpecificActionListeners(1,1,1,0);
				mainMenu();
			}
		});
		btnOptionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stripSpecificActionListeners(1,1,1,0);
				about();
			}
		});
		btnOptionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stripSpecificActionListeners(1,1,1,0);
				statistics();
			}
		});
	}
	
	protected void about() {
		// Set appropriate button visibility
		buttonVisibility(0,1,1,0);
		// Set button and description text
		textArea.setText("Escape from Guantanamo Bay is a text-based adventure game in which you play as an unnamed protagonist. You have been imprisoned"
				+ " and must fight for your life to escape during a prison riot. The plot advances based on, and responding to, player choice, offering"
				+ " a unique experience with each playthrough.\n\nThis game was authored by Paul Jerome, Brittany Mahoney, Colby Pernela and Kelso Pickett"
				+ " for Caleb Horst's Java Programming class (CS142) at Tacoma Community College in Spring 2016. Source revision history is available at"
				+ " https://github.com/kelsopickett/PrisonEscape\n\nStory by: Paul Jerome, Brittany Mahoney,"
				+ " Colby Pernela, Kelso Pickett\n\nScript by: Kelso Pickett\n\nCode by: Paul Jerome, Kelso Pickett\n\nAdditional Design by: Brittany Mahoney,"
				+ " Colby Pernela");
		btnOptionB.setText("Main Menu");
		btnOptionC.setText("Extras");
		btnOptionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stripSpecificActionListeners(0,1,1,0);
				mainMenu();
			}
		});
		btnOptionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stripSpecificActionListeners(0,1,1,0);
				extras();
			}
		});
	}
	
	protected void statistics() {
		// Set appropriate button visibility
		buttonVisibility(0,1,1,0);
		btnOptionB.setText("Main Menu");
		btnOptionC.setText("Extras");
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
		
		btnOptionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stripSpecificActionListeners(0,1,1,0);
				mainMenu();
			}
		});	
		btnOptionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stripSpecificActionListeners(0,1,1,0);
				extras();
			}
		});
	}
}
