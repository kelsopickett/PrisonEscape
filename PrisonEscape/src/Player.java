import java.util.Random;

public class Player {

	// Declare and initialize constants
	public static final int MAX_TOILET_WINE = 5;
	public static final int MAX_SHANK = 1;
	public static final int MAX_HEALTH = 500;
	public static final int MAX_ATTACK_DAMAGE = 150;
	// Declare player instance fields
	private int mHealth;
	private int mAttackDamage;
	private int mSmokes;
	private String mName;
	private boolean mAlive;

	// Declare, Define, and Overload Player constructor
	public Player() {
		mHealth = 350;
		mAttackDamage = 50;
		mSmokes = 20;
		mName = "Player 1";
		mAlive = true;
	}

	public Player(int wakeHealth, int wakeDamage, int wakeSmokes, String wakeName, boolean qAlive) {
		mHealth = wakeHealth;
		mAttackDamage = wakeDamage;
		mSmokes = wakeSmokes;
		mName = wakeName;
		mAlive = qAlive;
	}

	// TODO create Player behavior methods

	/*
	 * This method sets Player health in the event of a toiletWine drop
	 */
	public void toiletWine() {
		Random rng = new Random();
		int toiletWine = rng.nextInt(getHealth()) + 1;

		if (mHealth < MAX_HEALTH) {
			mHealth = mHealth + toiletWine;
		}
	}

	// Declare Getter/Mutator methods for Player instance fields
	public int getHealth() {
		return mHealth;
	}

	/*
	 * This method is called in Playerdecision loops. Utilized to set a new
	 * value of the players health during combat (where enemy attkdmg field is
	 * passed in) or after decision is made that alters health (passed random
	 * int)
	 */
	public int setHealth(int injury) {
		mHealth = mHealth - injury;
		if (mHealth <= 0) {
			mAlive = false;
		}
		return mHealth;
	}

	public int getSmokes() {
		return mSmokes;
	}

	public int setSmokes(int smokes) {
		mSmokes = smokes;
		return mSmokes;
	}

	public int getAttackDamage() {
		return mAttackDamage;
	}

	public int setAttackDamage(int shank) {
		mAttackDamage = mAttackDamage + (10 * shank);
		return mAttackDamage;
	}

	public String getName() {
		return mName;
	}

	public String setName(String name) {
		mName = name;
		return mName;
	}

	public boolean isAlive() {
		return mAlive;
	}

	public boolean LiveorDead(boolean alive) {
		mAlive = alive;
		return mAlive;
	}

}
