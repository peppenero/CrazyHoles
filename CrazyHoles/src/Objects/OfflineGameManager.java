package Objects;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class OfflineGameManager extends GameManager
{
	private int game;
	private File dir = new File("data/levels/");
	private boolean firstPlayer;
	private boolean secondPlayer;
	private int firstPlayerPoints = 0;
	private int secondPlayerpoints = 0;
	private int playedLevel[] = new int[6];
	private boolean changeLevel = false;
	
	public OfflineGameManager() throws IOException
	{
		setFirstPlayer(true);
		setSecondPlayer(false);
		setStart(true);
		game = 0;
		setLevel((int) Math.random()*(dir.listFiles().length)+1);
		playedLevel[game]=getLevel();
		setwManager(new WorldManager());
		setWorld((WorldImpl) getwManager().getworld(getLevel()));
		setTimer(new MyTimer());
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(getBall().isDropped())
 		{
 			getBall().setDropped(false);
 			getBall().reset();
 			Ball tmp = getWorld().getBalls().remove(0);
 			if(getWorld().getBalls().size()==0)
 			{
 				getWorld().getBalls().add(0,tmp);
 			}
 			else
 			{
 				getWorld().getBalls().add(getWorld().getBalls().size()-1,tmp);
 			}
 			setBall(getOneBall());
 		}
		if(getBall().isIntersecate())
 		{
 			setPoints(this.getPoints() + getBall().getHolePoint()+getBall().getScore());
 						
	 			getWorld().getBalls().remove(0);
	 			if(getWorld().getBalls().isEmpty())
	 	 		{
	 				setLevelOver(true);
	 	 		}
	 			if(isLevelOver())
	 			{
	 				if(isFirstPlayer())
	 				{
	 					setFirstPlayerPoints(getPoints()+getFirstPlayerPoints());
	 				}
	 				if(isSecondPlayer())
	 				{
	 					setSecondPlayerpoints(getPoints()+getSecondPlayerpoints());
	 				}
	 				setFirstPlayer(!firstPlayer);
	 				setSecondPlayer(!secondPlayer);
	 				if(game%2 !=0)
	 				{
	 					setLevel(getLevel()+1);
	 					try {
							setWorld( (WorldImpl) getwManager().getworld(getLevel()));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	 					changeLevel=true;
	 				}
	 				else
	 				{
		 				try {
							setWorld((WorldImpl) getwManager().getworld(getLevel()));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	 				}
	 				
	 				
	 				game++;
	 				
	 			}
	 			
	 				if(!isGameOver())
	 				{
	 					setBall(getOneBall());
	 				}	
	 			
	 			
	 				
	 			
	 		}
	}
	public boolean isFirstPlayer() {
		return firstPlayer;
	}
	public void setFirstPlayer(boolean firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	public boolean isSecondPlayer() {
		return secondPlayer;
	}
	public void setSecondPlayer(boolean secondPlayer) {
		this.secondPlayer = secondPlayer;
	}
	public int getFirstPlayerPoints() {
		return firstPlayerPoints;
	}
	public void setFirstPlayerPoints(int firstPlayerPoints) {
		this.firstPlayerPoints = firstPlayerPoints;
	}
	public int getSecondPlayerpoints() {
		return secondPlayerpoints;
	}
	public void setSecondPlayerpoints(int secondPlayerpoints) {
		this.secondPlayerpoints = secondPlayerpoints;
	}
	
}
