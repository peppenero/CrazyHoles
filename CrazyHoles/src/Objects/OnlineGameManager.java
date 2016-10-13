package Objects;


import java.io.File;
import java.io.IOException;


public class OnlineGameManager extends GameManager {

	private Host host;
	private Client client;
	private boolean server;
	private int opponentPoints =0;
	private boolean refresh=false;
	private boolean opponentEnds = false;
	private String winner = "nessuno";
	private boolean finish = false;
	
	
	public OnlineGameManager(Host host,File world) throws IOException
	{
		setServer(true);
		this.setHost(host);
		host.start();
		setwManager(new WorldManager());
		setWorld((WorldImpl) getwManager().getWorld(world));
		setTimer(new MyTimer());
		host.setManager(this);
	}
	
	public OnlineGameManager(Client client) throws IOException, InterruptedException
	{
		setServer(false);
		this.setClient(client);
		client.start();
		setwManager(new WorldManager());
		setWorld((WorldImpl) getwManager().getWorld(client.getWorld()));
		setTimer(new MyTimer());
		client.setManager(this);
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
		 			if(isServer())
						try {
							host.send(Integer.toString(this.getPoints()));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					else
					{
						try {
							client.send(Integer.toString(getPoints()));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
		 			
		 				//QUI C'È un problema(INDEX OUT OF BOUNDS) l'ho sistemato
			 			getWorld().getBalls().remove(0);
			 			if(getWorld().getBalls().isEmpty())
			 	 		{
			 				if(isServer())
			 				{
			 					try {
									host.send("ends");
									host.send(Integer.toString(getPoints()));
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			 				}
			 				else
			 				{
			 					try {
									client.send("ends");
									client.send(Integer.toString(getPoints()));
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			 				}
			 				
			 				setGameOver(true);
			 				finish=true;
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

	public Host getHost() {
		return host;
	}

	public void setHost(Host host) {
		this.host = host;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public boolean isServer() {
		return server;
	}

	public void setServer(boolean server) {
		this.server = server;
	}

	public int getOpponentPoints() {
		return opponentPoints;
	}

	public void setOpponentPoints(int opponentPoints) {
		this.opponentPoints = opponentPoints;
		setRefresh(true);
	}

	public boolean isRefresh() {
		return refresh;
	}

	public void setRefresh(boolean refresh) {
		this.refresh = refresh;
	}

	public boolean isOpponentEnds() {
		return opponentEnds;
	}

	public void setOpponentEnds(boolean opponentEnds) {
		this.opponentEnds = opponentEnds;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

}
