package tools;

public class Card {
	private int id;
	private int score;
	private String depiction;

	public Card(int id, int score, String depiction) {
		this.id = id;
		this.score = score;
		this.depiction = depiction;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getDepiction() {
		return depiction;
	}

	public void setDepiction(String depiction) {
		this.depiction = depiction;
	}
	
}