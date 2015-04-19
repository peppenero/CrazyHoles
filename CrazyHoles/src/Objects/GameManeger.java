package Objects;

import java.util.ArrayList;

public class GameManeger
{
 	private WorldImpl world = null;
 	private ArrayList<Ball> balls ;
 	private ArrayList<Hole> holes = null;
 	
 	public void start()
 	{
 		world =new WorldImpl();
 		istanceBall();
 		
 	}
 	
 	public boolean areThereBalls()
 	{
 		if(balls.isEmpty())
 			return false;
 		return true;
 	}
 	
 	protected void istanceBall()
 	{
 		for(int i=0;i<5;i++)
 		{
 			Ball ball = new Ball(10, this.world);
 			//balls.add(ball);
 		}
 	}
 	
 	public Ball getOneBall()
 	{
 		Ball tmp = balls.get(0);
 		balls.remove(0);
 		return tmp;
 	}
	public World getWorld() {
		return world;
	}
	public void setWorld(WorldImpl world) {
		this.world = world;
	}
	public ArrayList<Ball> getBalls() {
		return balls;
	}
	public void setBalls(ArrayList<Ball> balls) {
		this.balls = balls;
	}
	public ArrayList<Hole> getHoles() {
		return holes;
	}
	public void setHoles(ArrayList<Hole> holes) {
		this.holes = holes;
	}
}
