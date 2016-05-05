
package Objects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameManager
{
	
 	private WorldImpl world;
 	private List<Ball> balls;
 	private List<Hole> holes;
 	private Ball ball;
 	
 	
 	public GameManager(WorldManager worldMan) throws IOException
 	{
 		world = (WorldImpl) worldMan.getworld();
 	}
 	
 	public void start()
 	{
 		balls=world.getBalls();
 		holes=world.getHoles();
 		setBall(getOneBall());
  	}
 
 	public void update()
 	{
 		if(getBall().getD()==2)
 		{
 			balls.remove(0);
 			setBall(getOneBall());
 		}
 	}
 	
 	public boolean areThereBalls()
 	{
 		return balls.isEmpty();		
 	}
 		
 	public Ball getOneBall()
 	{
 		Ball tmp=balls.get(0);;
 		return tmp;
 	}
	public World getWorld() {
		return world;
	}
	public void setWorld(WorldImpl world) {
		this.world = world;
	}
	
	public List<Hole> getHoles() {
		return holes;
	}
	public void setHoles(List<Hole> holes) {
		this.holes = holes;
	}

	public List<Ball> getBalls() {
		return balls;
	}

	public void setBalls(List<Ball> balls) {
		this.balls = balls;
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}
}
