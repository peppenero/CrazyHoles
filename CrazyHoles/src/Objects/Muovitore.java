package Objects;

import java.io.IOException;
import gui.LeftGamePanel;
import gui.RightGamePanel;

public class Muovitore extends Thread {

	private Ball ball;
	private LeftGamePanel leftGamePanel;
	private RightGamePanel rightGamePanel;
	private GameManager gameManager;

	public Muovitore(Ball ball, LeftGamePanel p, RightGamePanel rPanel,
			GameManager man) {
		this.ball = ball;
		this.leftGamePanel = p;
		this.gameManager = man;
		this.rightGamePanel = rPanel;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		int ris = ball.move();		
		while((ris==1 || ris==0) && !p.isBackFlag() && !man.isGameOver() && !man.getBall().isDropped()){
		try {
			if(!p.isPause())
			{
				sleep(50);	
				p.repaint();
				ris=ball.move();
			}
=======
		int ris = ball.move();
		while ((ris == 1 || ris == 0) && !leftGamePanel.isBackFlag()
				&& !gameManager.isGameOver()) {
			try {
				if (!leftGamePanel.isPause()) {
					System.out.println("-----------");
					sleep(50);
					leftGamePanel.repaint();
					ris = ball.move();
				}
>>>>>>> refs/remotes/origin/master
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (leftGamePanel.isBackFlag()) {
			gameManager.reset();
		}
<<<<<<< HEAD
		if(p.isBackFlag())
		{
			man.resetBall();
		}
		p.setMove(false);
		
			man.update();
		
		rp.refresh();
		p.repaint();
=======
		leftGamePanel.setMove(false);
		try {
			gameManager.update();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		rightGamePanel.refresh();
		leftGamePanel.repaint();
>>>>>>> refs/remotes/origin/master
	}

}
