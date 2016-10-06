package Objects;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Host extends Thread
{
	private ServerSocket serverSocket;
	private Socket socket;
	private boolean accepted = false;	
	BufferedReader infromClient;
	DataOutputStream outToclient;
	public Host()
	{
		try {
			serverSocket = new ServerSocket(1024);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(true)
		{
			try {
				
					socket = serverSocket.accept();
					accepted=true;
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				infromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				outToclient = new DataOutputStream(socket.getOutputStream());
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
}
