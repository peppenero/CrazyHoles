package Objects;

import common.HasScore;

public class Hole extends Object implements HasScore {
	
	private int score=0;
	
	
	public Hole(int s,int x,int y,World world){
		super(world);
		this.setColor();
		this.setScore(s);
		setX(x);
		setY(y);
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
	public String getColor() {
		
		return this.color;
	}
	
	public void setWorld(final World world)
    {
        this.world = world;
    }

	

}
