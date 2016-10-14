package Objects;

import java.io.File;
import java.io.IOException;

public class FreePracticeGameManager extends GameManager {

	
	public FreePracticeGameManager(File world) throws IOException {
		
		setwManager(new WorldManager());
		setWorld((WorldImpl) getwManager().getWorld(world));
		setTimer(new MyTimer());
		setStart(true);
	}
	@Override
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
			 					setGameOver(true);
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
