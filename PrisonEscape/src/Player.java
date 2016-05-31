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

	/* This method sets Player health in the event
	 * of a toiletWine drop, it is passed a random integer between 1 and 2
     */
	public void toiletWine(int quantity){
		Random rng = new Random();
		int toiletWine = rng.nextInt(getHealth()) + 1;
		
		if (mHealth < MAX_HEALTH){
		mHealth = mHealth + (toiletWine * quantity);
		}
	}
	
	
	
	//Declare Getter/Mutator methods for Player instance fields
	public int getHealth() {
		return mHealth;
		
	}
	
	/*  This method is called in Playerdecision loops. 
	 * 	Utilized to set a new value of the players health
	 * 	during combat (where enemy attkdmg field is passed in)
	 *  or after decision is made that alters health (passed random int)
	 */	
	public void setHealth(int injury) {
		Random rng = new Random();
		int trueInjury = rng.nextInt(injury);
		mHealth = mHealth - trueInjury;
			if (mHealth <= 0){
				mAlive = false;
			}
		
	}
	
	public int getSmokes(){
		return mSmokes;
	}
	
	public void setSmokes(int smokes){
		mSmokes = smokes;
		
	}
	
	public int getAttackDamage() {
		return mAttackDamage;
	}
	
	public void setAttackDamage(int quantity) {
		
		mAttackDamage += (10 * quantity);
				
	}
	
	public String getName() {
		return mName;
	}
	
	public void setName(String name) {
		mName = name;
				
	}
	
	public boolean isAlive() {
		
		return mAlive;
	}
	
	public void LiveorDead(boolean alive) {
		mAlive = alive;
	}
	public String toString(){
		return mName;
	}
}
