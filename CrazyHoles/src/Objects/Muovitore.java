package Objects;

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


		int ris = ball.move();
		while ((ris == 1 || ris == 0) && !leftGamePanel.isBackFlag()
				&& !gameManager.isGameOver() && !ball.isDropped()) {
		
				if (!leftGamePanel.isPause()) {
					leftGamePanel.repaint();
					ris = ball.move();
				}
				try {
					sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(gameManager instanceof OnlineGameManager && ((OnlineGameManager) gameManager).isRefresh())
				{
					rightGamePanel.onlineRefresh();
				}
							
		}
		if (leftGamePanel.isBackFlag()) {
			gameManager.reset();
		}
		

		leftGamePanel.setMove(false);
		gameManager.update();
		rightGamePanel.refresh();
		leftGamePanel.repaint();

	}

}
