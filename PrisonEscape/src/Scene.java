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
	
	public Scene(String description, String choiceA, String choiceB, String choiceC, String choiceD) {
		this.description = description;
		this.choiceA = choiceA;
		this.choiceB = choiceB;
		this.choiceC = choiceC;
		this.choiceD = choiceD;
	}
	public Scene(String description, String choiceA, String choiceB) {
		this.description = description;
		this.choiceA = choiceA;
		this.choiceB = choiceB;
		
	}
}

