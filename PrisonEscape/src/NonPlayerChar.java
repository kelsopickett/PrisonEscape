import java.util.Random;

public class NonPlayerChar {
	    //Declare and initialize constants
		public static final int MAX_TOILET_WINE = 1;
		public static final int MAX_SHANK = 1;
		public static final int MAX_HEALTH = 10000;
		public static final int MAX_ATTACK_DAMAGE = 150;
		
		//Declare npc states
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
					int number = rng.nextInt(49);
					if (number < a){
						return true;
					}
					else{
						return false;
					}		
				}
				
				public boolean itemDrop(){
					
					int a = 69;
					Random rng = new Random();
					int number = rng.nextInt(157);
					if (number > a){
						//toilet wine
						return true;
					}
					else{
						//shank
						return false;
					}
				}
				
						
				//Declare getter/mutator methods for instance fields
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

				
				//normal getters and mutators
				public int getAttackDamage() {
					return mAttackDamage;
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
