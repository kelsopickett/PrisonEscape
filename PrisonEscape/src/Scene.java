public class Scene {
	
	public String description;
	public String choiceA;
	public String choiceB;
	public String choiceC;
	public String choiceD;	
	public String resultA;
	public String resultB;
	public String resultC;
	public String resultD;

	// Constructor for scenes one through seven, which have a description and four choices each with their own result
	public Scene(String description, String choiceA, String choiceB, String choiceC, 
			String choiceD, String resultA, String resultB, String resultC, String resultD) {
		this.description = description;
		this.choiceA = choiceA;
		this.choiceB = choiceB;
		this.choiceC = choiceC;
		this.choiceD = choiceD;
		this.resultA = resultA;
		this.resultB = resultB;
		this.resultC = resultC;
		this.resultD = resultD;
	}
	
	// Constructor for scene eight, which has only a description
	public Scene(String description) {
		this.description = description;
	}
}
