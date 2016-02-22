package Objects;

import common.HasScore;

public class Hole extends Object implements HasScore {
	
	private int score=0;
	private int radius;
	private float x1;
	private float x2;
	private float y1;
	private float y2;
		
	
	public Hole(int s,int x,int y,int radius,World world){
		super(world);
		this.setColor();
		this.setScore(s);
		setX(x);
		setY(y);
		setRadius(radius);
		setX1(getX()-10);
		setY1(getY()+radius);
		setX2(getX()+10);
		setY2(getY()+radius);
		
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

	public float getX1() {
		return x1;
	}

	public void setX1(float x1) {
		this.x1 = x1;
	}

	public float getX2() {
		return x2;
	}

	public void setX2(float x2) {
		this.x2 = x2;
	}

	public float getY1() {
		return y1;
	}

	public void setY1(float y1) {
		this.y1 = y1;
	}

	public float getY2() {
		return y2;
	}

	public void setY2(float y2) {
		this.y2 = y2;
	}

	

}
