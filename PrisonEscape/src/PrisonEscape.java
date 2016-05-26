import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrisonEscape {

	private JFrame frame;
	private static JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrisonEscape window = new PrisonEscape();
					window.frame.setVisible(true);
					window.newGame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		//newGame();
		
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
		frame.getContentPane().add(textArea);
		
		JButton btnOptionA = new JButton("Option A");
		btnOptionA.setBounds(20, 330, 600, 30);
		frame.getContentPane().add(btnOptionA);
		
		JButton btnOptionB = new JButton("Option B");
		btnOptionB.setBounds(20, 360, 600, 30);
		frame.getContentPane().add(btnOptionB);
		
		JButton btnOptionC = new JButton("Option C");
		btnOptionC.setBounds(20, 390, 600, 30);
		frame.getContentPane().add(btnOptionC);
		
		JButton btnOptionD = new JButton("Option D");
		btnOptionD.setBounds(20, 420, 600, 30);
		frame.getContentPane().add(btnOptionD);
	}

	private void newGame() {
		
		int currentScene = 0;
		
		// initialise rooms-- parameters: String description, int cigsAvail, choiceA, choiceB, choiceC, choiceD
		Scene cell = new Scene("You awake to find yourself in a dark cell. The small, barred window above bathes you in a rich,"
				+ " warm light. You look down to discover you are covered in blood and bruises. Pain shoots through your body"
				+ " as you attempt to sit upright. Your feet reach the floor and you feel it, cold and damp, through the thin"
				+ " soles of your shoes. What do you do?", 3, 
				"Search room for something useful.", "Make toilet wine.", "Crawl back in bed.", "Approach your cell door.");
		cell.description = ("You awake to find yourself in a dark cell. The small, barred window above bathes you in a rich,"
				+ " warm light. You look down to discover you are covered in blood and bruises. Pain shoots through your body"
				+ " as you attempt to sit upright. Your feet reach the floor and you feel it, cold and damp, through the thin"
				+ " soles of your shoes. What do you do?");
		Scene hallway = new Scene("You successfully pick the lock and exit into the hallway. A sound to your right draws your"
				+ " attention, and you turn to find a fellow inmate brandishing a shank. How do you proceed?", 0, 
				"Turn and flee.", "Stand and fight.", "Assume the fetal position.", "Attempt to talk your way out.");
		Scene cafeteria = new Scene("You move down the hallway into a narrow corridor. You approach a junction and follow"
				+ " signs toward the cafeteria. You peer through the reinforced glass of the cafeteria doors-- there is"
				+ " a group of armed prisoners at the far end of the cafeteria. None of them appear to be facing you."
				+ " What's your next move?", 0, 
				"Take the prisoners by surprise.", "Sneak to the kitchen.", "Hide in the corridor.", "Backtrack down the corridor.");
		Scene kitchen = new Scene("Keeping an eye on the cafeteria, you press your back to the swinging doors into the kitchen."
				+ " As you cross the threshold, a hand grabs you from behind with another presses a knife to your throat. You hear"
				+ " the cook's distinctive chuckle as he recognises you. \"Alright, " // + INSERT PLAYER NAME HERE
				+ "... You're good people. I'll let you go if you can answer this riddle... Poor people have it, rich people need"
				+ " it, but if you eat it, you die.\"", 0, 
				"\"I don't know.\"", "\"I AM THE 1%!\"", "\"Just let me go.\"", "\"Nothing.\"");
		Scene yard = new Scene("Your answer satisfies the cook. He ushers you toward the corner of the room and into the rubbish"
				+ " chute. You slide down, landing in a dumpster below. You stand to find yourself outside in one of the exercise"
				+ " yards. As you vault out of the dumpster, a guard shouts \"HALT OR I'LL OPEN FIRE!\". What do you do?", 0, 
				"Make a break for it.", "Scramble back up the rubbish chute.", "Bribe the guard with cigarettes.", "Surrender.");
		Scene infirmary = new Scene("You scramble toward and through the doors of the nearby infirmary, slamming them shut behind"
				+ " you. After taking a moment to catch your breath, you turn and find yourself face to face with a nurse. He gives"
				+ " you a puzzled look, \"You're in pretty bad shape. Let me help you.\" You...", 0, 
				"Accept offer of help.", "Fight the doctor/nurse.", "Ignore him and search the room for items.", "Have a mental breakdown.");
		Scene entrance = new Scene("You emerge from the infirmary with a renewed sense of vigor. You stride grows larger as"
				+ " each step takes you closer to the front gate, which is now in sight and slightly ajar. As you draw near,"
				+ " you realise it is not just ajar, but opening. The dull rumble of a disel motor gets louder with every inch."
				+ " With the gate mostly open, you see the prison bus barreling toward you with the warden behind the wheel."
				+ " In a split second, you decide to...", 0, 
				"Take aim and throw your knife at the warden.", "Run for your life.", "Take cover behind the guardhouse.", "Embrace your fate.");
		Scene escape = new Scene("The gravel makes a satisfying sound beneath your feet as you walk out the gate. You take a deep"
				+ " breath of fresh, Cuban air and a moment to revel in the sunset. You are free.", 0, 
				"Option A", "Option B", "Option C", "Option D");
		
		// init array of eight scenes named gitmo
		Scene[] gitmo = new Scene[8];
		gitmo[0] = cell;
		gitmo[1] = hallway;
		gitmo[2] = cafeteria;
		gitmo[3] = kitchen;
		gitmo[4] = yard;
		gitmo[5] = infirmary;
		gitmo[6] = entrance;
		gitmo[7] = escape;

		System.out.println(gitmo[currentScene].description);
		// the code below attempts to update the textArea (where scene is displayed) with current scene information, but doesn't work
		textArea.setText(gitmo[currentScene].description);
		
	}
}
