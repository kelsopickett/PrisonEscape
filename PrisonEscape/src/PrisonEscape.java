import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrisonEscape {

	// Declare objects
	private JFrame frame;
	private static JTextArea textArea;
	// Declare instance fields
	private JButton btnOptionA;
	private JButton btnOptionB;
	private JButton btnOptionC;
	private JButton btnOptionD;
	private int currentScene = 0;
	static Scene[] gitmo = new Scene[8];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		// initialise rooms-- parameters: description, cigsAvail, choiceA, choiceB, choiceC, choiceD, resultA, resultB, resultC, resultD
		Scene cell = new Scene("You awake to find yourself in a dark cell.\n\nThe small, barred window above bathes you in a rich,"
					+ " warm light. You look down to discover you are covered in blood and bruises. Pain shoots through your body"
					+ " as you attempt to sit upright. Your feet reach the floor and you feel it, cold and damp, through the thin"
					+ " soles of your shoes.\n\nWhat do you do?", 
				3, 
				"Search room for something useful.", "Make toilet wine.", "Crawl back in bed.", "Approach your cell door.", 
				"The cell is sparse; not much to search through.\n\nYou rummage under the shit excuse for"
					+ " a mattress until something pokes your hands. Flipping the mattress over reveals a crudely"
					+ " made shank embedded inside. This might be useful... You pocket it and proceed to the cell door.\n\n"
					+ "Placing your hands on the bars, you find it loose in the frame. You press against it with all your"
					+ " strength and force the door open. You step wearily out into the cellblock.", 
				"Another prisoner once taught you the best way to pass time was a kind of homemade wine made in cell toilets."
					+ " After a little work, you taste the fruits of your labor. It's bitter and burns on the way down, but you"
					+ " feel stronger.\n\nYou notice a thin strand of metal wound around a pipe fitting behind the toilet. It"
					+ " looks small enough to fit in the cell door's keyhole. You pull it free from the pipe and approach the"
					+ " cell door. \n\nYou successfully pick the lock and step into the cellblock.", 
				"You are too tired and too sore to press on. You climb back into bed and rest.\n\nYou wake to a hand covering your"
					+ " mouth and a knife to your throat. An inmate stands over you and slides the blade across your neck.", 
				"Anxious to escape, you approach the cell door.\n\nPlacing your hands on the bars, you find it loose in the frame."
					+ " You press against it with all your, strength and force the door open. You step wearily out into the"
					+ " cellblock.");
		Scene cellblock = new Scene("A sound to your right draws your attention, and you turn to find a fellow inmate brandishing a"
					+ " shank.\n\nHow do you proceed?", 
				0, 
				"Turn and flee.", "Stand and fight.", "Assume the fetal position.", "Attempt to talk your way out.", 
				"You decide it's best not to take any chances.\n\nYou pivot away from the prisoner and sprint  toward the hallway at"
					+ " the end of the cellblock. Your heart races as your feet pound the pavement, the sound echoing off the cement"
					+ " walls.\n\nYou reach the gate at the end of the cellblock, slamming the lock shut behind you. You turn to face"
					+ " the prisoner, who dons a frustrated sneer and before turning back the way he came.", 
				"\"Let's dance\" you mutter, gesturing the prisoner to approach. He lunges at you and a fight ensues.", 
				"You are not prepared for this fight. Realising the futility of your situation, you collapse to the floor and curl"
					+ " up into the fetal position. You hear the prisoner laugh as the sound of his footsteps grow closer.\n\nYou feel"
					+ " a blade plunge into your side repeatedly and the world fades to black.", 
				"\"I just want to get out of here\" you plead. He responds, \"You and me both. That way is a shit show,\"  gesturing"
				+ " behind him.\n\n\"Alright. Thanks. Guess I'll check down that hall,\" you suggest, point to the hallway at the opposite"
				+ " end of the cellblock. He replies, \"Suit yourself, I'm out of here,\" before wandering to the upper levels of the block."
				+ " His footsteps fade into the distance of one of the hallways above.");
		Scene cafeteria = new Scene("You move down the hallway into a narrow corridor. You approach a junction and follow"
					+ " signs toward the cafeteria. You peer through the reinforced glass of the cafeteria doors-- there is"
					+ " a group of armed prisoners at the far end of the cafeteria. None of them appear to be facing you."
					+ " What's your next move?", 
				0, 
				"Take the prisoners by surprise.", "Sneak to the kitchen.", "Hide in the corridor.", "Backtrack down the corridor.", 
				"You decide it's best to use the element of surprise to your advantage.\n\nWith their backs turned, you approach the group"
				+ " of prisoners without a sound and ambush them.\n\nHowever, they outnumber you significantly, and your wounds have"
				+ " weakened you. You are slain in combat.", 
				"You decide it's best to leave this group alone.\n\nYou slip into the cafeteria and creep silently toward the kitchen door.", 
				"", 
				"");
		Scene kitchen = new Scene("Keeping an eye on the cafeteria, you press your back to the swinging doors into the kitchen."
				+ " As you cross the threshold, a hand grabs you from behind with another presses a knife to your throat. You hear"
				+ " the cook's distinctive chuckle as he recognises you. \"Alright... you're good people. I'll let you go if you can"
				+ " answer this riddle... Poor people have it, rich people need it, but if you eat it, you die.\"", 
				0, 
				"\"I don't know.\"", "\"I AM THE 1%!\"", "\"Just let me go.\"", "\"Nothing.\"", 
				"", 
				"", 
				"", 
				"");
		Scene yard = new Scene("Your answer satisfies the cook. He ushers you toward the corner of the room and into the rubbish"
				+ " chute. You slide down, landing in a dumpster below. You stand to find yourself outside in one of the exercise"
				+ " yards. As you vault out of the dumpster, a guard shouts \"HALT OR I'LL OPEN FIRE!\". What do you do?", 
				0, 
				"Make a break for it.", "Scramble back up the rubbish chute.", "Bribe the guard with cigarettes.", "Surrender.", 
				"", 
				"", 
				"", 
				"");
		Scene infirmary = new Scene("You scramble toward and through the doors of the nearby infirmary, slamming them shut behind"
				+ " you. After taking a moment to catch your breath, you turn and find yourself face to face with a nurse. He gives"
				+ " you a puzzled look, \"You're in pretty bad shape. Let me help you.\" You...", 
				0, 
				"Accept offer of help.", "Fight the doctor/nurse.", "Ignore him and search the room for items.", "Have a mental breakdown.", 
				"", 
				"", 
				"", 
				"");
		Scene entrance = new Scene("You emerge from the infirmary with a renewed sense of vigor. You stride grows larger as"
				+ " each step takes you closer to the front gate, which is now in sight and slightly ajar. As you draw near,"
				+ " you realise it is not just ajar, but opening. The dull rumble of a disel motor gets louder with every inch."
				+ " With the gate mostly open, you see the prison bus barreling toward you with the warden behind the wheel."
				+ " In a split second, you decide to...", 
				0, 
				"Take aim and throw your knife at the warden.", "Run for your life.", "Take cover behind the guardhouse.", "Embrace your fate.", 
				"", 
				"", 
				"", 
				"");
		Scene escape = new Scene("The gravel makes a satisfying sound beneath your feet as you walk out the gate. You take a deep"
				+ " breath of fresh, Cuban air and a moment to revel in the sunset. You are free.", 
				"Start a new game.", "", "", "Quit");
		
		// init array of eight scenes named gitmo
		gitmo[0] = cell;
		gitmo[1] = cellblock;
		gitmo[2] = cafeteria;
		gitmo[3] = kitchen;
		gitmo[4] = yard;
		gitmo[5] = infirmary;
		gitmo[6] = entrance;
		gitmo[7] = escape;
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrisonEscape window = new PrisonEscape();
					window.frame.setVisible(true);
					window.sceneOne();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
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
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(20, 20, 600, 300);
		textArea.setMargin(new Insets(10,10,10,10));
		frame.getContentPane().add(textArea);
		
		btnOptionA = new JButton("Option A");
		btnOptionA.setBounds(20, 330, 600, 30);
		frame.getContentPane().add(btnOptionA);
		
		btnOptionB = new JButton("Option B");
		btnOptionB.setBounds(20, 360, 600, 30);
		frame.getContentPane().add(btnOptionB);
		
		btnOptionC = new JButton("Option C");
		btnOptionC.setBounds(20, 390, 600, 30);
		frame.getContentPane().add(btnOptionC);
		
		btnOptionD = new JButton("Option D");
		btnOptionD.setBounds(20, 420, 600, 30);
		frame.getContentPane().add(btnOptionD);
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
	 * This method removes the action listeners from all four buttons so that they may be assigned new action
	 * listeners in the subsequent scene
	 */
	protected void stripActionListeners() {
		btnOptionA.removeActionListener(btnOptionA.getActionListeners()[0]);
		btnOptionB.removeActionListener(btnOptionB.getActionListeners()[0]);
		btnOptionC.removeActionListener(btnOptionC.getActionListeners()[0]);
		btnOptionD.removeActionListener(btnOptionD.getActionListeners()[0]);
	}

	public void sceneOne() {

		// Render first scene description in textArea and user options in buttons A-D
		textArea.setText(gitmo[currentScene].description);
		updateChoices();
		
		// user choice = option a: search the cell, find a shank and force door open
		btnOptionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		// user choice = option b: make toilet wine (hp++, find lockpick)
		btnOptionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultC);
				//TODO GAME OVER
				// Unassign actions from buttons so they can be reassigned in next scene
				stripActionListeners();
			}
		});
		// user choice = option d: approach cell door, force way out (hp--)
		btnOptionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		// Update user choices for current scene
		updateChoices();
		
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
				// render result of choice and subsequent scene
				textArea.setText(gitmo[currentScene].resultB + "\n\n" + gitmo[(currentScene + 1)].description);
				// increment scene counter to third scene
				currentScene++;
				//TODO consequences of this action
				// unassign action from button so it can be reassigned in next scene
				stripActionListeners();
				// advance to third scene
				sceneThree();
			}
		});
		// user choice = option c: assume the fetal position, this results in GAME OVER
		btnOptionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// render result of choice
				textArea.setText(gitmo[currentScene].resultC);
				//TODO consequences of this action
				// unassign action from button so it can be reassigned in next scene
				stripActionListeners();
			}
		});
		// user choice = option d: talk their way out, user advances with an item?
		btnOptionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// render result of choice and subsequent scene
				textArea.setText(gitmo[currentScene].resultD + "\n\n" + gitmo[(currentScene + 1)].description);
				// increment scene counter to third scene
				currentScene++;
				//TODO consequences of this action
				// unassign action from button so it can be reassigned in next scene
				stripActionListeners();
				// advance to third scene
				sceneThree();
			}
		});
	}

	protected void sceneThree() {
		// Update user choices for current scene
		updateChoices();
		
		// user choice = option a: take the prisoners by surprise, resulting in loss by death
		btnOptionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultA);
				// Unassign actions from buttons so they can be reassigned in next scene
				stripActionListeners();
				//TODO GAME OVER
			}
		});
		// user choice = option b: sneak to the kitchen
		btnOptionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultB + "\n\n" + gitmo[(currentScene + 1)].description);
				// Increment scene counter
				currentScene++;
				//TODO consequences of this action
				// Unassign actions from buttons so they can be reassigned in next scene
				stripActionListeners();
				// Advance to next scene
				sceneFour();
			}
		});
		// user choice = option c: hide in the corridor, resulting in loss by death
		btnOptionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice
				textArea.setText(gitmo[currentScene].resultC);
				// Unassign actions from buttons so they can be reassigned in next scene
				stripActionListeners();
				//TODO GAME OVER
			}
		});
		// user choice = option d: backtrack down corridor, resulting in guard encounter (-- cigs, adv)
		btnOptionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultD + "\n\n" + gitmo[(currentScene + 1)].description);
				// Increment scene counter
				currentScene++;
				//TODO consequences of this action
				// Unassign actions from buttons so they can be reassigned in next scene
				stripActionListeners();
				// Advance to next scene
				sceneFour();
			}
		});
	}

	protected void sceneFour() {
		// Update user choices for current scene
		updateChoices();
		
		// user choice = option a: "I don't know"
		btnOptionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice
				textArea.setText(gitmo[currentScene].resultA);
				// Unassign actions from buttons so they can be reassigned in next scene
				stripActionListeners();
				//TODO GAME OVER
			}
		});
		// user choice = option b: provide wrong answer
		btnOptionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultB + "\n\n" + gitmo[(currentScene + 1)].description);
				// Increment scene counter
				currentScene++;
				//TODO consequences of this action
				// Unassign actions from buttons so they can be reassigned in next scene
				stripActionListeners();
				// Advance to next scene
				sceneFive();
			}
		});
		// user choice = option c: provide wrong answer
		btnOptionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultC + "\n\n" + gitmo[(currentScene + 1)].description);
				// Increment scene counter
				currentScene++;
				//TODO consequences of this action
				// Unassign actions from buttons so they can be reassigned in next scene
				stripActionListeners();
				// Advance to next scene
				sceneFive();
			}
		});
		// user choice = option d: provide correct answer
		btnOptionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		updateChoices();
		
		// user choice = option a: turn and run; combat (chance for hp--), advance if alive
		btnOptionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultA + "\n\n" + gitmo[(currentScene + 1)].description);
				// Increment scene counter
				currentScene++;
				//TODO consequences of this action
				// Unassign actions from buttons so they can be reassigned in next scene
				stripActionListeners();
				// Advance to scene six
				sceneSix();
			}
		});
		// user choice = option b: scramble up rubbish chute; shot to death by guard
		btnOptionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice=
				textArea.setText(gitmo[currentScene].resultB);
				// Unassign actions from buttons so they can be reassigned in next scene
				stripActionListeners();
				//TODO GAME OVER
			}
		});
		// user choice = option c:bribe guard with cigarettes; advance (cigs--)
		btnOptionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultC + "\n\n" + gitmo[(currentScene + 1)].description);
				//TODO consequences of this action
				// Increment scene counter
				currentScene++;
				// Unassign actions from buttons so they can be reassigned in next scene
				stripActionListeners();
				// Advance to next scene
				sceneSix();
			}
		});
		// user choice = option d: surrender; lose but no death
		btnOptionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice
				textArea.setText(gitmo[currentScene].resultD);
				// Unassign actions from buttons so they can be reassigned in next scene
				stripActionListeners();
				//TODO GAME OVER
			}
		});
	}

	protected void sceneSix() {
		// Update user choices for current scene
		updateChoices();
		
		// user choice = option a: accept offer of help; something bad happens, lose
		btnOptionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice
				textArea.setText(gitmo[currentScene].resultA);
				// Unassign actions from buttons so they can be reassigned in next scene
				stripActionListeners();
				//TODO GAME OVER
			}
		});
		// user choice = option b: fight the doctor/nurse; combat (chance for --health, item drop), advance if alive
		btnOptionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultB + "\n\n" + gitmo[(currentScene + 1)].description);
				// Increment scene counter
				currentScene++;
				//TODO consequences of this action
				// Unassign actions from buttons so they can be reassigned in next scene
				stripActionListeners();
				// Advance to next scene
				sceneSeven();
			}
		});
		// user choice = option c: ignore, search for items (chance for ++health, item drop); advance
		btnOptionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultC + "\n\n" + gitmo[(currentScene + 1)].description);
				// Increment scene counter
				currentScene++;
				//TODO consequences of this action
				// Unassign actions from buttons so they can be reassigned in next scene
				stripActionListeners();
				// Advance to next scene
				sceneSeven();
			}
		});
		// user choice = option d: have mental breakdown; doctor/nurse consoles you (++cigarette), advance
		btnOptionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultD + "\n\n" + gitmo[(currentScene + 1)].description);
				// Increment scene counter
				currentScene++;
				//TODO consequences of this action
				// Unassign actions from buttons so they can be reassigned in next scene
				stripActionListeners();
				// Advance to next scene
				sceneSeven();
			}
		});
	}
	

	protected void sceneSeven() {
		// Update user choices for current scene
		updateChoices();
		
		// user choice = option a: throw knife at bus driver; combat (really low chance of success), advance if alive
		btnOptionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultA + "\n\n" + gitmo[(currentScene + 1)].description);
				// Increment scene counter
				currentScene++;
				//TODO consequences of this action
				// Unassign actions from buttons so they can be reassigned in next scene
				stripActionListeners();
				// Advance to next scene
				sceneEight();
			}
		});
		// user choice = option b: turn and run; bus rolls, explodes, and you black out... return to first scene
		btnOptionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice
				textArea.setText(gitmo[currentScene].resultB);
				// Reset scene counter
				currentScene = 0;
				// Unassign actions from buttons so they can be reassigned in next scene
				stripActionListeners();
				// Advance to first scene
				sceneOne();
			}
		});
		// user choice = option c: hide behind something; run over by bus, die and lose
		btnOptionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Render result of choice and description of next scene
				textArea.setText(gitmo[currentScene].resultC);
				// Unassign actions from buttons so they can be reassigned in next scene
				stripActionListeners();
				//TODO GAME OVER
			}
		});
		// user choice = option d: embrace death; toss shank, bus runs it over, tire pops and veers away... advance
		btnOptionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		// TODO Final scene
	}
}
