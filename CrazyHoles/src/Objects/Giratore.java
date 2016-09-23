package Objects;

import gui.LeftGamePanel;

import java.util.List;

public class Giratore extends Thread {
	private List<Hole> holes;
	private LeftGamePanel panel;
	private GameManager manager;

	public Giratore(LeftGamePanel panel, GameManager manager) {
		this.panel = panel;
		this.manager = manager;
	}

	@Override
	public void run() {

		holes = manager.getHoles();

		while (true && !panel.isBackFlag() && !manager.isLevelOver()
				&& !manager.isGameOver()) {
			try {
				sleep(150);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(!panel.isPause())
			for(int i=0;i<holes.size();i++)
			{
				holes.get(i).move();
				panel.repaint();
			}
			if(manager.isStart())
			{
				
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				manager.setStart(false);			
			}
			
		if (manager.isLevelOver()) {
			panel.repaint();
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			panel.reset();
		}
		
	}
	
	}
}
