package Objects;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Client extends Thread
{
	private Socket clientSocket;
	private DataOutputStream outToServer;
	private ObjectInputStream ois;
	private boolean worldRecivied = false;
	private String mah;
	private File world;
	private Lock lock = new ReentrantLock();
	private Condition c = lock.newCondition();
	private OnlineGameManager manager;
	private boolean finish = false;
	private boolean end = false;
	
	public Client(String ip) throws UnknownHostException, IOException
	{
		
		clientSocket = new Socket(ip,1024);
		outToServer = new DataOutputStream(clientSocket.getOutputStream());
		ois = new ObjectInputStream(clientSocket.getInputStream());
	}
	
	@Override
	public void run() {
		
		java.lang.Object ob;
		try {
			ob = ois.readObject();
			lock.lock();
			if(ob instanceof File)
			{
				world = (File) ob;
				worldRecivied = true;
				c.signalAll();
				System.out.println("ho ricevuto il mondo");
			}
			lock.unlock();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(!end)
		{
			 try {
				ob = ois.readObject();
				if(ob instanceof String)
				{
					mah = (String) ob;
					System.out.println("client "+mah);
					if(mah.equals("ends"))
					{
						while(!finish)
						{
							mah = (String) ois.readObject();
							System.out.println("winner"+ mah);
							if(mah.equals("client") || mah.equals("host") || mah.equals("pari"))
							{
								System.out.println(mah);
								manager.setWinner(mah);
								finish=true;
							}
							else
							{
								manager.setOpponentPoints(Integer.parseInt(mah));
								manager.setOpponentEnds(true);
							}
							
						}
						
					}
					else
					{
						manager.setOpponentPoints(Integer.parseInt(mah)+manager.getOpponentPoints());
					}
				}
				
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void send(String s) throws IOException
	{
		outToServer.writeBytes(s+'\n');
	}
	public File getWorld() throws InterruptedException
	{
		lock.lock();
		while(!worldRecivied)
		{
			c.await();
		}
		lock.unlock();
			return world;
			
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
		outToServer.close();
		ois.close();
		clientSocket.close();
	}
	
}
