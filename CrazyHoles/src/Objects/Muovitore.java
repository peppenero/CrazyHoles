package Objects;

import gui.GamePanel;
import gui.LeftGamePanel;

public class Muovitore implements Runnable{
	
	private LeftGamePanel leftGamePanel;
	
	public Muovitore(LeftGamePanel l){
		this.leftGamePanel = l;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
		try {
			Thread.sleep(15);
			leftGamePanel.getBall().move();
			leftGamePanel.repaint();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

}
