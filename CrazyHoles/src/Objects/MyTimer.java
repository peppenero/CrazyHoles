package Objects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class MyTimer {
	private Timer timer;
	private long startTime;
	private long diffTime;
	private int decSeconds;
	private int seconds;
	private int minutes;
	private Timer secondTimer;
	private GameManager manager;



	public MyTimer( final GameManager manager) {

		this.manager = manager;
		timer = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub		
				diffTime = System.currentTimeMillis() - startTime;
				decSeconds = (int) (diffTime % 1000 / 100);
				seconds = (int) (diffTime / 1000 % 60);
				minutes = (int) (diffTime / 60000 % 60);
			}
		});
		secondTimer = new Timer(10000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("mamma " + manager.getBall().getScore());
				manager.getBall().setScore(manager.getBall().getScore()-5);
			}
		});
	}

	public void init() {
		startTime = System.currentTimeMillis();
		timer.start();
		secondTimer.start();
	}

	public void setStartTime() {
		startTime = System.currentTimeMillis();
	}

	public int getDecSeconds() {
		return decSeconds;
	}

	public int getSeconds() {
		return seconds;
	}

	public int getMinutes() {
		return minutes;
	}
	public void restart()
	{
		timer.restart();
	}
	public void reset()
	{
		minutes =0;
		seconds =0;
		decSeconds=0;
	}

	public void pause()
	{
		startTime = diffTime;
		timer.stop();
		secondTimer.stop();
	}
	
	public void stop() {
		
		timer.stop();
		secondTimer.stop();
	}
	public void secondTimerRestart()
	{
		secondTimer.restart();
	}
	
}
