package Objects;

import gui.GameFrame;
import gui.GamePanel;

public class Muovitore extends Thread{
	
	private Ball ball;
	private GamePanel p;
	private GameManager man;
	
	public Muovitore(Ball ball,GamePanel p,GameManager man){
		this.ball=ball;
		this.p = p;
		this.man=man;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
		try {
			Thread.sleep(50);
			ball.move();
			
			man.update();
			p.repaint();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

}
