package Objects;

import gui.GameFrame;
import gui.GamePanel;

public class Muovitore implements Runnable{
	
	private GamePanel leftGamePanel;
	
	public Muovitore(GamePanel l){
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
