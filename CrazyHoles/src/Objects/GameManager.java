
package Objects;

import java.io.IOException;
import java.util.List;

public abstract class GameManager
{
	
 	private WorldImpl world;
 	private Ball ball;
 	private int points=0;
 	private WorldManager wManager;
 	private int level=1;
 	private boolean levelOver= false;
 	private MyTimer timer;
 	private boolean gameOver=false;
 	private boolean firstClick=true;
 	private Ranking ranking;
 	private String player;
 	private boolean start;
 	
 	
 	public void start() throws IOException
 	{
 		setBall(getOneBall());
  	}
 
 	public abstract void update();
 
 	public void resetBall()
 	{		
 		ball.reset();
 	}
 	 		
 	public Ball getOneBall()
 	{
 		Ball tmp=world.getBalls().get(0);;
 		return tmp;
 	}
	public WorldImpl getWorld() {
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


	public boolean isFirstClick() {
		return firstClick;
	}


	public void setFirstClick(boolean firstClick) {
		this.firstClick = firstClick;
	}

	public WorldManager getwManager() {
		return wManager;
	}

	public void setwManager(WorldManager wManager) {
		this.wManager = wManager;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

}
