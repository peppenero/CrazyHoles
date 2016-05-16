package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.security.auth.Refreshable;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Objects.GameManager;
import Objects.World;

public class RightGamePanel extends JPanel 
{
	private GameManager manager;
	private JLabel timelabel;
	private JLabel pointsLabel;
	private Timer timer;
	private long startTime;
	private BoxLayout layout;

	public RightGamePanel(GameManager manager) {
		// TODO Auto-generated constructor stub
		this.manager=manager;
		layout = new BoxLayout(this,BoxLayout.Y_AXIS);
		this.setLayout(layout);
		Font font = new Font("SansSerif",Font.BOLD,50);
		 timelabel = new JLabel("00:00.0");
		 timelabel.setForeground(Color.red);
		 timelabel.setSize(50, 25);
		 timelabel.setFont(font);
		 String point = String.format("%02d", manager.getPoints());
		 pointsLabel = new JLabel(point);
		 pointsLabel.setForeground(Color.red);
		 pointsLabel.setSize(50,25);
		 pointsLabel. setFont(font);
		
		 timer = new Timer(100, new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				long diffTime = System.currentTimeMillis() - startTime;
				int decSeconds = (int) (diffTime % 1000 /100);
				int seconds = (int) (diffTime /1000 %60);
				int minutes = (int) (diffTime /60000 %60);
				String s = String.format("%02d:%02d.%d", minutes,seconds,decSeconds);
				timelabel.setText(s);
				
			}
			
		});
		 
		 this.add(timelabel);
		 this.add(Box.createVerticalGlue());
		 this.add(pointsLabel);
	}
	
	public void init()
	{
		startTime = System.currentTimeMillis();
		timer.start();
	}
	
	public void refresh()
	{
		String s = String.format("%02d",manager.getPoints());
		pointsLabel.setText(s);
	}
}
