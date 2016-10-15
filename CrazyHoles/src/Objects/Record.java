package Objects;


public class Record implements Comparable<Record>{

	private String name;
	private Integer score;

	public Record(String n, int p) {
		this.setName(n);
		this.setScore(p);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public String toString(){
		return (getName() + "*" + getScore() + "\n");
	}
	
	public int compareTo(Record another){
		return ((another.getScore().compareTo(score)));
	}

}