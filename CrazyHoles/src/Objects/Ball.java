package Objects;
import common.*;

public class Ball extends Object implements HasScore {
	
	private int velocity=5;
	private int score=0;
	private float corner=50;
	private int ballDiameter = 20;
	private int deltaY,deltaX;
	
	
	public Ball(int s,World world){
		super(world);
		this.setColor();
		this.setScore(s);
		deltaX = ((int)(velocity * Math.cos(Math.toRadians(corner))));
		deltaY = ((int)(velocity * (float) Math.sin(Math.toRadians(corner))));
		
		this.setX(world.getWidth()/2 );
		this.setY(world.getHeight()-getBallRadius());
		
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
		
		int ballMax=(y+getBallRadius());
		
		if((x+getBallRadius())>=(world.getWidth()) || x<=0)
		{
			
			deltaX = -deltaX;
		}
		
		if(ballMax>=(world.getHeight()) ||y<=0)
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
		System.out.println(this.corner);
		deltaX =  ((int)(float) ((Math.cos(Math.toRadians(corner)))*velocity));
		deltaY =  ((int)(float) ((Math.sin(Math.toRadians(corner)))*velocity));
		System.out.println(deltaX);
		System.out.println(deltaY);
	}
	
	public void setCorner(float corner) {
		this.corner = corner;
	}

	public int getBallRadius() {
		return ballDiameter;
	}

	public void setBallRadius(int ballRadius) {
		this.ballDiameter = ballRadius;
	}

}
