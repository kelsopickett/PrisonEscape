import java.util.Random;

public class NonPlayerChar {
	    //Declare and initialize constants
		public static final int MAX_TOILET_WINE = 1;
		public static final int MAX_SHANK = 1;
		public static final int MAX_HEALTH = 10000;
		public static final int MAX_ATTACK_DAMAGE = 150;
		
		//Declare player states
		private int mHealth;
		private int mAttackDamage;
		private String mName;
		private boolean mAlive;
		
		//Constructors
		public NonPlayerChar(){ //default constructor
			mHealth = 125;
			mAttackDamage = 75;
			mAlive = true;
			mName = "NPC";
		}
		
		public NonPlayerChar(int health, int attackDamage, String name, boolean alive){
			mHealth = health;
			mAttackDamage = attackDamage;
			mName = name;
			mAlive = alive;
		}
		//Behaviors
		public boolean hasItem(){
			int a = 50;
			Random rng = new Random();
			int number = rng.nextInt(100);
			if (number < a){
				return true;
			}
			else{
				return false;
			}		
		}
		
		public boolean hasShank(){
			
			int a = 69;
			Random rng = new Random();
			int number = rng.nextInt(157);
			if (number < a){
				return true;
			}
			else{
				return false;
			}
		}
		
		public void dropItem(){
			if (hasShank() == true){
				//dropShank
			} else{
				//dropWine
			}
				
		}
		public int getHealth() {
			return mHealth;
			
		}
		
		/*  This method is called in Playerdecision loops. 
		 * 	Utilized to set a new value of the players health
		 * 	during combat (where enemy attkdmg field is passed in)
		 *  or after decision is made that alters health (passed random int)
		 */	
		public int setHealth(int injury) {
			mHealth = mHealth - injury;
				if (mHealth <= 0){
					mAlive = false;
				}
			return mHealth;		
		}

		
		//normal getters and mutators
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
