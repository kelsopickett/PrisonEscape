public class Scene {
	
	public String mDescription;
	public int cigsAvail;
	public String choiceA;
	public String choiceB;
	public String choiceC;
	public String choiceD;	

	public Scene(String description, int cigsAvail, String choiceA, String choiceB, String choiceC, String choiceD) {
		this.mDescription = description;
		this.cigsAvail = cigsAvail;
		this.choiceA = choiceA;
		this.choiceB = choiceB;
		this.choiceC = choiceC;
		this.choiceD = choiceD;
	}
	
	public Scene(String description) {
		mDescription = description;
	}
}
