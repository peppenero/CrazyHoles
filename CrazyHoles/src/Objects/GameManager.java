
package Objects;

import java.io.IOException;
import java.util.List;

public class GameManager
{
	
 	private WorldImpl world;
 	private Ball ball;
 	private int points=0;
 	private WorldManager wManager;
 	private int level=0;
 	private boolean levelOver= false;
 	private MyTimer timer;
 	private boolean gameOver=false;
 	private Ranking ranking;
 	private String player;
 	
 	public GameManager() throws IOException
 	{
 		setRanking(new Ranking());
 		 wManager = new WorldManager();
 		world = (WorldImpl) wManager.getworld(level); 	
 		timer= new MyTimer();
 	}
 	
 	
 	public GameManager(int level) throws IOException
 	{
 		setRanking(new Ranking());
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
		 			points = this.getPoints() + ball.getHolePoint()+ball.getScore();
		 		
		 				//QUI C'È un problema(INDEX OUT OF BOUNDS)
			 			world.getBalls().remove(0);
			 			if(world.getBalls().isEmpty())
			 	 		{
			 				if(world.isLastLevel())
			 				{
			 					setGameOver(true);
			 				}
			 				if(!isGameOver())
			 				{
				 				setLevelOver(true);
				 	 			level++;
				 	 			world=(WorldImpl) wManager.getworld(level);	
			 				}
			 			}			 			
			 			if(!isGameOver())
			 			{
			 				setBall(getOneBall());
			 			}
			 		}
 			if(isGameOver())
 			{
 				timer.stop();
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


	public MyTimer getTimer() {
		return timer;
	}


	public void setTimer(MyTimer timer) {
		this.timer = timer;
	}


	public boolean isGameOver() {
		return gameOver;
	}


	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}


	public Ranking getRanking() {
		return ranking;
	}


	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}
	public void addPosition() throws IOException
	{
		Position p = new Position(getPlayer(), getPoints());
		this.ranking.addPosition(p);
		ranking.writeRanking();
	}


	public String getPlayer() {
		return player;
	}


	public void setPlayer(String player) {
		this.player = player;
	}

}
