package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Objects.GameManager;

public class TopRightPanel extends JPanel {

	private JPanel firstPanel;
	private JPanel secondPanel;
	private JPanel thirdPanel;
	private JLabel timeLabel;
	private JLabel pointsLabel;
	private JLabel numbersOfBall;
	private JLabel time;
	private JLabel points;
	private JLabel balls;
	private GameManager manager;
	private Font font = OurFont.getInstance().deriveFont(Font.TRUETYPE_FONT, 30);
	private Timer timer;
	
	public TopRightPanel(GameManager man)
	{
		 manager = man;
		 setOpaque(false);
		 this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		 
		 firstPanel = new JPanel();
		 firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.Y_AXIS));
		 firstPanel.setAlignmentX(CENTER_ALIGNMENT);
		 firstPanel.setOpaque(false);
		 secondPanel = new JPanel();
		 secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.Y_AXIS));
		 secondPanel.setOpaque(false);
		 thirdPanel = new JPanel();
		 thirdPanel.setLayout(new BoxLayout(thirdPanel, BoxLayout.Y_AXIS));
		 thirdPanel.setOpaque(false);
		 
		 time = new JLabel("TIME");
		 time.setFont(font);
		 time.setAlignmentX(CENTER_ALIGNMENT);
		 balls = new JLabel("REMAINING BALLS");
		 balls.setFont(font);
		 balls.setAlignmentX(CENTER_ALIGNMENT);
		 points = new JLabel("POINTS"); 
		 points.setFont(font);
		 points.setAlignmentX(CENTER_ALIGNMENT);
		 
		 setTimeLabel(new JLabel("00:00.00"));
		 getTimeLabel().setForeground(Color.black);
		 getTimeLabel().setFont(font);
		 getTimeLabel().setAlignmentY(TOP_ALIGNMENT);
		 getTimeLabel().setAlignmentX(CENTER_ALIGNMENT);
		 String point = String.format("%02d", manager.getPoints());
		 setPointsLabel(new JLabel(point));
		 getPointsLabel().setForeground(Color.BLACK);
		 getPointsLabel().setAlignmentY(TOP_ALIGNMENT);
		 getPointsLabel().setAlignmentX(CENTER_ALIGNMENT);
		 getPointsLabel(). setFont(font);
		 String number = String.format("%02d", manager.getBalls().size());
		 setNumbersOfBall(new JLabel(number));
		 getNumbersOfBall().setForeground(Color.BLACK);
		 getNumbersOfBall().setAlignmentY(TOP_ALIGNMENT);
		 getNumbersOfBall().setAlignmentX(CENTER_ALIGNMENT);
		 getNumbersOfBall().setFont(font);
		 String s = String.format("%02d:%02d.%02d", manager.getTimer().getMinutes(),manager.getTimer().getSeconds(),manager.getTimer().getDecSeconds());
		 getTimeLabel().setText(s);
		 setTimer(new Timer(100, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					 String s = String.format("%02d:%02d.%d", manager.getTimer().getMinutes(),manager.getTimer().getSeconds(),manager.getTimer().getDecSeconds());
					getTimeLabel().setText(s);				
				}			
			}));
		 
		 firstPanel.add(balls);
		 firstPanel.add(getNumbersOfBall());
		 secondPanel.add(points);
		 secondPanel.add(getPointsLabel());
		 thirdPanel.add(time);
		 thirdPanel.add(getTimeLabel());
		 add(firstPanel);
		 add(secondPanel);
		 add(thirdPanel);
		 add(Box.createVerticalGlue());
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public JLabel getTimeLabel() {
		return timeLabel;
	}

	public void setTimeLabel(JLabel timeLabel) {
		this.timeLabel = timeLabel;
	}

	public JLabel getPointsLabel() {
		return pointsLabel;
	}

	public void setPointsLabel(JLabel pointsLabel) {
		this.pointsLabel = pointsLabel;
	}

	public JLabel getNumbersOfBall() {
		return numbersOfBall;
	}

	public void setNumbersOfBall(JLabel numbersOfBall) {
		this.numbersOfBall = numbersOfBall;
	}
	
}
