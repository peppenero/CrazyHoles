package Objects;


import java.io.IOException;
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
		while((ris==1 || ris==0) && !p.isBackFlag() && !man.isGameOver()){
			try {
				if(!p.isPause())
				{
					System.out.println("-----------");
					sleep(50);	
					p.repaint();
					ris=ball.move();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(p.isBackFlag())
		{
			man.reset();
		}
		p.setMove(false);
		try {
			man.update();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		rp.refresh();
		p.repaint();
	}

}
