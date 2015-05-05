package Objects;

import common.HasScore;

public class Hole extends Object implements HasScore {
	
	private int score=0;
	private int radius;
		
	
	public Hole(int s,int x,int y,int radius,World world){
		super(world);
		this.setColor();
		this.setScore(s);
		setX(x);
		setY(y);
		setRadius(radius);
	}
	
	@Override
	public void setScore(int s) {
		this.score=s;
	}

	@Override
	public int getScore() {
		return this.score;
	}
	
	
	public void move()
	{
		
	}

	@Override
	public String getColor() {
		
		return this.color;
	}
	
	public void setWorld(final World world)
    {
        this.world = world;
    }

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	

}
