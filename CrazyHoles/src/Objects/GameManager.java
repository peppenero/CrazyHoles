
package Objects;

import java.awt.Frame;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameManager
{
	
 	private WorldImpl world;
 	private Ball ball;
 	private int points=0;
 	private WorldManager wManager;
 	private int level=0;
 	private boolean levelOver= false;

 	public GameManager() throws IOException
 	{		
 		 wManager = new WorldManager();
 		world = (WorldImpl) wManager.getworld(level); 		
 	}
 	
 	
 	public GameManager(int level) throws IOException
 	{
 		this.setLevel(level);
 		wManager = new WorldManager();
 		world = (WorldImpl) wManager.getworld(this.level);
 	}
 	
 	public void start() throws IOException
 	{
 		setBall(getOneBall());
  	}
 
 	public void update() throws IOException
 	{			
 			if(ball.isIntersecate())
		 		{
		 			points = this.getPoints() + ball.getHolePoint();
		 		
		 			
			 			world.getBalls().remove(0);
			 			if(world.getBalls().isEmpty())
			 	 		{
			 				setLevelOver(true);
			 	 			level++;
			 	 			world=(WorldImpl) wManager.getworld(level);	
			 	 		}			 			
			 				setBall(getOneBall());
			 		}	 		
 	}
 	public void reset()
 	{		
 		ball.reset();
 	}
 	 		
 	public Ball getOneBall()
 	{
 		Ball tmp=world.getBalls().get(0);;
 		return tmp;
 	}
	public World getWorld() {
		return world;
	}
	public void setWorld(WorldImpl world) {
		this.world = world;
	}
	
	public List<Hole> getHoles() {
		return world.getHoles();
	}

	public List<Ball> getBalls() {
		return world.getBalls();
	}

	public void setBalls(List<Ball> balls) {
		this.world.setBalls(balls);
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}


	public boolean isLevelOver() {
		return levelOver;
	}


	public void setLevelOver(boolean levelOver) {
		this.levelOver = levelOver;
	}

}
