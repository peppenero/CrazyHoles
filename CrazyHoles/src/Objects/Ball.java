package Objects;
import common.*;

public class Ball extends Object implements HasScore {
	
	private int velocity=5;
	private int score=0;
	private float corner=140;
	private int ballRadius = 10;
	private int deltaY,deltaX;
	private Hole hole;
	
	
	public Ball(int s,World world)
	{
		super(world);
		this.setColor();
		this.setScore(s);
		deltaX = ((int)(velocity * Math.cos(Math.toRadians(corner))));
		deltaY = ((int)(velocity * (float) Math.sin(Math.toRadians(corner))));
		this.setX(world.getWidth()/2 );
		this.setY(world.getHeight()-getBallRadius());
		System.out.println(getX());
		System.out.println(getY());
		hole = new Hole(20,100,100,100,world);	
	}
	
	public int getDeltaX()
	{
		return deltaX;
	}
	
	public int getDeltaY()
	{
		return deltaY;
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
	
	public void move()
	{
		
		int x = (int) getX();
		int y = (int) getY();
		
		
		
		if((x+getBallRadius())>=(world.getWidth()) || (x-getBallRadius())<=0)
		{
			
			deltaX = -deltaX;
		}
		
	
		
		if((y+getBallRadius())>=(world.getHeight()) ||(y-getBallRadius())<=0)
		{
			
			deltaY=-deltaY;
		}
		
		setX(getX()+deltaX);
		setY(getY()+deltaY);
	}
	
	public void setWorld(final World world)
    {
        this.world = world;
    }

	public float getCorner() {
		return corner;
	}
	
	public void updateCorner(float corner)
	{		
		this.corner+=corner;
        deltaX =  ((int)(velocity * Math.cos(Math.toRadians(this.corner))));
		deltaY =  ((int)(velocity * (float) Math.sin(Math.toRadians(this.corner))));		
	}
	
	public void setCorner(float corner) {
		this.corner = corner;
	}

	public int getBallRadius() {
		return ballRadius;
	}

	public void setBallRadius(int ballRadius) {
		this.ballRadius = ballRadius;
	}

}
