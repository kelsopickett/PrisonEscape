public class Scene {
	
	public String mDescription;
	public int cigsAvail;
	public String choiceA;
	public String choiceB;
	public String choiceC;
	public String choiceD;
	
	public Scene(String descriptionP, int cigsAvailP, String choiceAP, String choiceBP, String choiceCP, String choiceDP) {
		mDescription = descriptionP;
		cigsAvail = cigsAvailP;
		choiceA = choiceAP;
		choiceB = choiceBP;
		choiceC = choiceCP;
		choiceD = choiceDP;
		
	}
	public Scene(String description) {
		mDescription = description;
	}
}
