package Objects;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread
{
	private Socket clientSocket;
	public Client(String ip) throws UnknownHostException, IOException
	{
		clientSocket = new Socket(ip,1024);
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			
		}
	}
}
