package Objects;

public class Position {

	private String name;
	private int points;

	public Position(String n, int p) {
		this.setName(n);
		this.setPoints(p);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}