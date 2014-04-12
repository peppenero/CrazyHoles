package Objects;
import common.*;

public class Ball extends Object implements HasScore {
	
	private static final int RIGHT = 0;
	private static final int LEFT = 1;	
	
	private int score=0;
	private double corner=0.0;
	private int direction;
	
	public Ball(String c,int s,int x,int y ){
		super(x,y,null);
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
	
	public void move()
	{
		final int x = getX();
		final int y = getY();
		
		if(x<world.getWidth() || x>0)
		{
			setCorner(x+corner);
		}
		
		if(x>world.getWidth() || x<0)
		{
			setCorner(x-corner);
		}
		
		if(y>world.getHeight() || y<0)
		{
			setCorner(y+1);
		}
		
		if(y<world.getHeight() || y>0)
		{
			setCorner(y-1);
		}
	}
	
	public void setWorld(final World world)
    {
        this.world = world;
    }

	public double getCorner() {
		return corner;
	}
	
	public void setCorner(double corner) {
		this.corner = corner;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirezione(int direction)
	{
		this.direction= direction;
	}
	
	public void updateCorner()
	{
		final double c=getCorner();
		
		switch(direction)
		{
			case RIGHT:
			{
				setCorner(c+0.5);
				break;
			}
			case LEFT:
			{
				setCorner(c-0.5);
				break;
			}
		
		}
	}
}
