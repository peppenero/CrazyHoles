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
<<<<<<< HEAD
	private long diffTimeTmp=0;

	public MyTimer(){
		
=======

	public MyTimer() {

>>>>>>> refs/remotes/origin/master
		timer = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
<<<<<<< HEAD
				diffTime = System.currentTimeMillis() - startTime +diffTimeTmp;	
					decSeconds = (int) (diffTime % 1000 /100) ;
					seconds = (int) (diffTime /1000 %60);
					minutes = (int) (diffTime /60000 %60);
			
=======
				diffTime = System.currentTimeMillis() - startTime;
				decSeconds = (int) (diffTime % 1000 / 100);
				seconds = (int) (diffTime / 1000 % 60);
				minutes = (int) (diffTime / 60000 % 60);
>>>>>>> refs/remotes/origin/master
			}
		});
	}

	public void init() {
		startTime = System.currentTimeMillis();
		timer.start();
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
<<<<<<< HEAD
	public void restart()
	{
		setStartTime();
		timer.restart();
	}
	public void reset()
	{
		minutes =0;
		seconds =0;
		decSeconds=0;
	}
	public void stop()
	{
		diffTimeTmp=diffTime;
=======

	public void stop() {
>>>>>>> refs/remotes/origin/master
		timer.stop();
	}
}
