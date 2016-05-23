package gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.security.auth.Refreshable;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import Objects.GameManager;
import Objects.World;

public class RightGamePanel extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameManager manager;
	private Icon pauseIcon = new ImageIcon("images/Pause.png");
	private Icon pauseSelected = new ImageIcon("images/PauseSelected.png");
	private Icon exitIcon = new ImageIcon("images/exit_.png");
	private Icon exitSelected = new ImageIcon("images/selectedExit.png");
	private Icon scoreIcon = new ImageIcon("images/scoreBoard.png");
	private Icon scoreSelected = new ImageIcon("images/selectedScoreBoard.png");
	private Icon restartIcon = new ImageIcon("images/restart.png");
	private Icon restartSelected = new ImageIcon("images/restartSelected.png");
 	private JLabel timelabel;
	private JLabel pointsLabel;
	private Timer timer;
	private long startTime;
	private JLabel numbersOfBall;
	private BoxLayout layout;
	private MenuButton exit;
	private MenuButton scoreboard;
	private MenuButton pause;
	private LeftGamePanel panel;

	public RightGamePanel(final GameManager manager) throws FontFormatException, IOException {
		// TODO Auto-generated constructor stub		
		this.manager=manager;
		layout = new BoxLayout(this,BoxLayout.Y_AXIS);
		this.setLayout(layout);
		setPreferredSize(new Dimension(500,800));
		exit = new MenuButton(exitIcon,exitSelected);
		scoreboard = new  MenuButton(scoreIcon, scoreSelected);
		pause = new MenuButton(pauseIcon, pauseSelected);	
		String filename = "images/ARCADE_N.TTF";
		Font font = Font.createFont(Font.TRUETYPE_FONT, new File(filename));
		font=font.deriveFont(Font.TRUETYPE_FONT,30);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(font);
		Border lowered = BorderFactory.createLoweredBevelBorder();
		 timelabel = new JLabel("00:00.0");
		 timelabel.setOpaque(true);
		 timelabel.setBackground(Color.WHITE);
		 timelabel.setForeground(Color.black);
		 timelabel.setFont(font);
		 timelabel.setAlignmentY(TOP_ALIGNMENT);
		 timelabel.setAlignmentX(CENTER_ALIGNMENT);
		 timelabel.setBorder(lowered);
		 String point = String.format("%02d", manager.getPoints());
		 pointsLabel = new JLabel(point);
		 pointsLabel.setBorder(lowered);
		 pointsLabel.setOpaque(true);
		 pointsLabel.setBackground(Color.white);
		 pointsLabel.setForeground(Color.BLACK);
		 pointsLabel.setAlignmentY(TOP_ALIGNMENT);
		 pointsLabel.setAlignmentX(CENTER_ALIGNMENT);
		 pointsLabel. setFont(font);
		 String number = String.format("%02d", manager.getBalls().size());
		 numbersOfBall=new JLabel(number);
		 numbersOfBall.setBorder(lowered);
		 numbersOfBall.setOpaque(true);
		 numbersOfBall.setBackground(Color.white);
		 numbersOfBall.setForeground(Color.BLACK);
		 numbersOfBall.setAlignmentX(CENTER_ALIGNMENT);
		 numbersOfBall.setAlignmentY(TOP_ALIGNMENT);
		 numbersOfBall.setFont(font);
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
		 setBackground(Color.black);
		pause.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!panel.isPause())
				{
					pause.setIcon(restartIcon);
					pause.setRolloverIcon(restartSelected);
					panel.setPause(true);					
				}
				else
				{
					pause.setIcon(pauseIcon);
					pause.setRolloverIcon(pauseSelected);
					panel.setPause(false);
				}
				panel.repaint();
			}
		});
		
		scoreboard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
					panel.scoreboard.setVisible(true);				
			}
		});
		
		 this.add(numbersOfBall);	
		 this.add(timelabel);
		 this.add(pointsLabel);
		 this.add(pause);
		 this.add(scoreboard);
		 this.add(exit);
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

	public LeftGamePanel getPanel() {
		return panel;
	}

	public void setPanel(LeftGamePanel panel) {
		this.panel = panel;
	}
}
