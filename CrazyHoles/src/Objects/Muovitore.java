package Objects;


import gui.GameFrame;
import gui.GamePanel;
import gui.LeftGamePanel;
import gui.RightGamePanel;

public class Muovitore extends Thread{
	
	private Ball ball;
	private LeftGamePanel p;
	private RightGamePanel rp;
	private GameManager man;
	
	public Muovitore(Ball ball,LeftGamePanel p, RightGamePanel rPanel, GameManager man){
		this.ball=ball;
		this.p = p;
		this.man=man;
		this.rp=rPanel;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int ris = ball.move();
		while(ris==0 || ris==1){
		try {
			Thread.sleep(50);	
			p.repaint();
			ris=ball.move();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		p.setMove(false);
		man.update();
		rp.refresh();
		p.repaint();
	}

}
