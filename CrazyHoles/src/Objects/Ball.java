package Objects;
import java.util.ArrayList;
import java.util.List;

import common.*;

public class Ball extends Object implements HasScore {
	
	private int velocity=2;
	private int score=0;
	private float corner=270;
	private int ballRadius = 1;
	private int deltaY,deltaX;
	private List<Hole> holes;
	
	
	public EquazioniCirconferenza getEquation()
	{
		EquazioniCirconferenza eq = new EquazioniCirconferenza((int)this.getX(), (int) this.getY(), this.getBallRadius());
		return eq;
	}
	
	public Ball(int s,ArrayList<String> colors,WorldImpl world)
	{
		super(world);
		this.setColor(colors);
		this.setScore(s);
		deltaX = (int) ( (velocity * Math.cos(Math.toRadians(corner))));
		deltaY =(int) (velocity * (float) Math.sin(Math.toRadians(corner)));
		this.setX(31);
		this.setY(world.getHeight()-getBallRadius());
		holes=world.getHoles();
		
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
			
		if((((x+getBallRadius())+deltaX)>(world.getWidth()) || ((x-getBallRadius())+deltaX)<0))
		{	
			System.out.println(x);
			deltaX = -deltaX;
		}
		for(int i=0;i<holes.size();i++)
		{
			if(this.getEquation().intersezioni(holes.get(i)))
			{
				deltaX=-deltaX;
				deltaY=-deltaY;
			}
		}
		if((((y+getBallRadius()+deltaY))>(world.getHeight()) ||((y-getBallRadius())+deltaY)<0))
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
	
	public void setColor(ArrayList<String> colors)
	{
		int casual = (int) (Math.random()*colors.size());
		
		this.color=colors.get(casual);
	}

	public List<Hole> getHoles() {
		return holes;
	}

	public void setHoles(List<Hole> holes) {
		this.holes = holes;
	}
	
}
