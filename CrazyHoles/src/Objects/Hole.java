package Objects;

import common.HasScore;

public class Hole extends Object implements HasScore {
	
	private int score=0;
	
	public Hole(String c,int s){
		super();
		this.setColor(c);
		this.setScore(s);
	}
	
	@Override
	public void setScore(int s) {
		this.score=s;
	}

	@Override
	public int getScore() {
		return this.score;
	}

	@Override
	protected void setColor(String c) {
		this.color=c;
	}

	@Override
	protected String getColor() {
		
		return this.color;
	}

}