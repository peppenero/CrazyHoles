package Objects;

import java.io.File;
import java.io.IOException;



public class OfflineGameManager extends GameManager
{
	private int game;
	private File dir = new File("data/levels/");
	private boolean firstPlayer;
	private boolean secondPlayer;
	private int firstPlayerPoints = 0;
	private int secondPlayerpoints = 0;
	private int playedLevel[] = new int[3];
	private int playerOneSet = 0;
	private int playertwoSet=0;
	private int winner;
	
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
	 				game++;
	 				if(game==4)//modificare quando ci saranno tutti i livelli
 					{
 						setGameOver(true);
 					}
	 				setLevelOver(true);
	 	
	 				if(isFirstPlayer())
	 				{
	 					setFirstPlayerPoints(getPoints());
	 				}
	 				if(isSecondPlayer())
	 				{
	 					setSecondPlayerpoints(getPoints());
	 				}
	 				setPoints(0);
	 				setFirstPlayer(!firstPlayer);
	 				setSecondPlayer(!secondPlayer);
	 				
	 				if(game%2 ==0)
	 				{
	 					if(firstPlayerPoints>secondPlayerpoints)
	 					{
	 						setPlayerOneSet(getPlayerOneSet() + 1);
	 					}
	 					else
	 					{
	 						if(firstPlayerPoints==secondPlayerpoints)
	 						{
	 							setPlayerOneSet(getPlayerOneSet() + 1);
	 							setPlayertwoSet(getPlayertwoSet() + 1);
	 						}
	 						else
	 						{
	 							setPlayertwoSet(getPlayertwoSet() + 1);
	 						}
	 					}	
	 					setFirstPlayerPoints(0);
	 					setSecondPlayerpoints(0);
	 					if(!isGameOver())
	 					{
		 					setLevel(getLevel()+1);
		 					try {
								setWorld( (WorldImpl) getwManager().getworld(getLevel()));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	 					}
			 									
	 				} 
	 				else
	 				{
	 					if(!isGameOver())
	 					{
		 					try {
								setWorld((WorldImpl) getwManager().getworld(getLevel()));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
	 					}
	 				}
	 								
	 			}	 			
	 				if(!isGameOver())
	 				{
	 					setBall(getOneBall());
	 				}	 			
	 		}
		if(isGameOver())
		{
			getTimer().stop();
			if(isFirstPlayer())
			{
				setWinner(0);
			}
			if(isSecondPlayer())
			{
				setWinner(1);
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
	public int getPlayerOneSet() {
		return playerOneSet;
	}
	public void setPlayerOneSet(int playerOneSet) {
		this.playerOneSet = playerOneSet;
	}
	public int getPlayertwoSet() {
		return playertwoSet;
	}
	public void setPlayertwoSet(int playertwoSet) {
		this.playertwoSet = playertwoSet;
	}
	public int getWinner() {
		return winner;
	}
	public void setWinner(int winner) {
		this.winner = winner;
	}
	
}
