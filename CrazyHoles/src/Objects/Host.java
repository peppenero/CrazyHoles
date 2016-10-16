package Objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Host extends Thread
{
	private ServerSocket serverSocket;
	private Socket socket;
	private boolean accepted = false;	
	private BufferedReader infromClient;
	private ObjectOutputStream oos;
	private String mah;
	private File  world;
	private OnlineGameManager manager;
	private boolean finish = false;
	private boolean end = false;
	private boolean opponentLeave = false;
	
	public Host(File world) throws IOException
	{
		this.world = world;
		try {
			serverSocket = new ServerSocket(1024);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		
		
		try {
			if(!accepted && !end)
			{
				socket = serverSocket.accept();
				System.out.println("mi sono connesso");
				infromClient = new BufferedReader(new
				          InputStreamReader
				          (socket.getInputStream()));
				oos = new ObjectOutputStream(socket.getOutputStream());
				accepted=true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			try
			{
				if(e instanceof SocketException)
				{
					throw new SocketException();
				}
				else
				{
					e.printStackTrace();
				}
			}catch(SocketException s)
			{
				
			}
		}
		
		try {
			if(!end)
			{
				oos.writeObject(world);
				oos.flush();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		while(!end)
		{	
					try {
						mah=infromClient.readLine();
						if(mah != null)
						{
							if(mah.equals("leave"))
							{
								setOpponentLeave(true);
							}
							else
							{
								if(mah.equals("ends"))
								{
									
									mah = infromClient.readLine();
									manager.setOpponentPoints(Integer.parseInt(mah));
									manager.setOpponentEnds(true);
									while(!finish)
									{
										if(manager.isOpponentEnds() && manager.isFinish() )
										{
											if(manager.getPoints() == manager.getOpponentPoints())
											{
												manager.setWinner("pari");
												send("pari");
												finish = true;
											}
											else
											{
												if(manager.getPoints() < manager.getOpponentPoints())
												{
													manager.setWinner("client");
													send("client");
													finish= true;
												}
												else
												{
													if(manager.getPoints() > manager.getOpponentPoints())
													{
														manager.setWinner("host");
														send("host");
														finish = true;
													}											
												}
										}
									}
										
									}
								}
								else
								{
									manager.setOpponentPoints(Integer.parseInt(mah)+manager.getOpponentPoints());
								}
							}
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				
		}
	
	}

	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	public void send(String s) throws IOException
	{
		oos.writeObject(s);
	}

	public OnlineGameManager getManager() {
		return manager;
	}

	public void setManager(OnlineGameManager manager) {
		this.manager = manager;
	}
	public void ends() throws IOException
	{
		end = true;
		if(accepted)
		{
			oos.close();
			infromClient.close();
			socket.close();
		}
		serverSocket.close();
		
	}

	public boolean isOpponentLeave() {
		return opponentLeave;
	}

	public void setOpponentLeave(boolean opponentLeave) {
		this.opponentLeave = opponentLeave;
	}
}
