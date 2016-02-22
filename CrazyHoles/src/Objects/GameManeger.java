
package Objects;

import java.util.ArrayList;
import java.util.List;

public class GameManeger
{
 	private WorldImpl world = null;
 	private List<Ball> balls = new ArrayList<Ball>();
 	private List<Hole> holes = new ArrayList<Hole>();
 	
 	public void start()
 	{
 		world = new WorldImpl();
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
	public List<Ball> getBalls() {
		return balls;
	}
	public void setBalls(List<Ball> balls) {
		this.balls = balls;
	}
	public List<Hole> getHoles() {
		return holes;
	}
	public void setHoles(List<Hole> holes) {
		this.holes = holes;
	}
}
