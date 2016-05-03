
package Objects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameManager
{
	
 	private WorldImpl world;
 	private List<Ball> balls;
 	private List<Hole> holes;
 	
 	public GameManager(WorldManager worldMan) throws IOException
 	{
 		world = (WorldImpl) worldMan.getworld();
 	}
 	
 	public void start()
 	{
 		balls=world.getBalls();
 		holes=world.getHoles();
 		
 	}
 	
 	public boolean areThereBalls()
 	{
 		return balls.isEmpty();		
 	}
 		
 	public Ball getOneBall()
 	{
 		return balls.get(0);
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
}
