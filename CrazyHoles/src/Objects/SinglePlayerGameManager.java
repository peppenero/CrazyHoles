package Objects;

import java.io.IOException;

public class SinglePlayerGameManager extends GameManager
{
 	
 	public SinglePlayerGameManager() throws IOException
 	{
 		setwManager(new WorldManager());
 		setWorld((WorldImpl) getwManager().getworld(getLevel()));
 		setTimer(new MyTimer(this));
 		setStart(true);
 	}
 	
 	
 	public SinglePlayerGameManager(int level) throws IOException
 	{
 		this.setLevel(level);
 		setwManager(new WorldManager());
 		setWorld((WorldImpl) getwManager().getworld(getLevel()));
 		setTimer(new MyTimer(this));
 	}
 	
 	
 
 	public void update()
 	{	
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
		 		
		 			
		 				//QUI C'È un problema(INDEX OUT OF BOUNDS) l'ho sistemato
			 			getWorld().getBalls().remove(0);
			 			if(getWorld().getBalls().isEmpty())
			 	 		{
			 				if(getWorld().isLastLevel())
			 				{
			 					setGameOver(true);
			 				}
			 				if(!isGameOver())
			 				{		 					
				 				setLevelOver(true);		
				 				setLevel(getLevel()+1);
				 	 			try {
				 	 				setWorld((WorldImpl) getwManager().getworld(getLevel()));
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
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
 			}
 	}
 
}
