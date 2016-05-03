package Objects;

import gui.GameFrame;
import gui.GamePanel;

public class Muovitore extends Thread{
	
	private Ball ball;
	private GamePanel p;
	
	public Muovitore(Ball ball,GamePanel p){
		this.ball=ball;
		this.p = p;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
		try {
			Thread.sleep(50);
			ball.move();
			
			p.repaint();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

}
