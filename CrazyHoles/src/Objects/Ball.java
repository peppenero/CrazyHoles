package Objects;
import common.*;

public class Ball extends Object implements HasScore {
	
	private int score=0;
	
	public Ball(String c,int s){
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

}
