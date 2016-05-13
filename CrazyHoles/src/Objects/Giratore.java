package Objects;


import gui.GamePanel;
import gui.LeftGamePanel;

import java.util.List;

public class Giratore extends Thread 
{
	private List<Hole> holes;
	private LeftGamePanel panel;
	
	public Giratore(List<Hole> holes,LeftGamePanel panel)
	{
		this.holes=holes;
		this.panel=panel;
	}
	
	@Override
	public void run() {
		
		super.run();
		while(true)
		{
			try {
				sleep(75);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0;i<holes.size();i++)
			{
				holes.get(i).move();
				panel.repaint();
			}
		}	
	}
}
