package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

import Objects.GameManager;
import Objects.SinglePlayerGameManager;
import Objects.World;


public class RightGamePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameManager manager;
	private Icon pauseIcon = new ImageIcon("images/pause.png");
	private Icon pauseSelected = new ImageIcon("images/pause_selected.png");
	private Icon exitIcon = new ImageIcon("images/exit.png");
	private Icon exitSelected = new ImageIcon("images/exit_selected.png");
	private Icon scoreIcon = new ImageIcon("images/scoreboard.png");
	private Icon scoreSelected = new ImageIcon("images/scoreboard_selected.png");
	private Icon restartIcon = new ImageIcon("images/resume.png");
	private Icon restartSelected = new ImageIcon("images/resume_selected.png");
	private Icon scoreBoardDeselected = new ImageIcon("images/scoreboard_deselected.png");
	private Icon pauseDeselected = new ImageIcon("images/pause_deselected.png");
	private JLabel timeLabel;
	private JLabel pointsLabel;
	private Timer timer;
	private long startTime;
	private JLabel numbersOfBall;
	private BoxLayout layout;
	private boolean started = false;
	private MenuButton exitButton;
	private MenuButton scoreboardButton;
	private MenuButton pauseButton;
	private LeftGamePanel leftGamePanel;

	public RightGamePanel(final GameManager manager)
			throws FontFormatException, IOException {
		// TODO Auto-generated constructor stub
		this.manager = manager;
		layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setAlignmentX(LEFT_ALIGNMENT);
		this.setLayout(layout);
		setPreferredSize(new Dimension(450, 800));
		exitButton = new MenuButton(exitIcon, exitSelected);
		scoreboardButton = new MenuButton(scoreIcon, scoreSelected);
		pauseButton = new MenuButton(pauseIcon, pauseSelected);
		pauseButton.setDisabledIcon(pauseDeselected);
		scoreboardButton.setDisabledIcon(scoreBoardDeselected);
		String filename = "data/ARCADE_N.TTF";
		Font font = Font.createFont(Font.TRUETYPE_FONT, new File(filename));
		font = font.deriveFont(Font.TRUETYPE_FONT, 30);
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		ge.registerFont(font);
		Border lowered = BorderFactory.createLoweredBevelBorder();

		 timeLabel = new JLabel("00:00.0");
		 timeLabel.setOpaque(true);
		 timeLabel.setBackground(Color.WHITE);
		 timeLabel.setForeground(Color.black);
		 timeLabel.setFont(font);
		 timeLabel.setAlignmentY(TOP_ALIGNMENT);
		 timeLabel.setAlignmentX(CENTER_ALIGNMENT);
		 timeLabel.setBorder(lowered);
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
		 String s = String.format("%02d:%02d.%d", manager.getTimer().getMinutes(),manager.getTimer().getSeconds(),manager.getTimer().getDecSeconds());
			timeLabel.setText(s);
		 timer = new Timer(100, new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				 String s = String.format("%02d:%02d.%d", manager.getTimer().getMinutes(),manager.getTimer().getSeconds(),manager.getTimer().getDecSeconds());
				timeLabel.setText(s);				
			}
			
			
		});
		setOpaque(false);
		

		pauseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!leftGamePanel.isPause()) {

					pauseButton.setIcon(restartIcon);
					pauseButton.setRolloverIcon(restartSelected);
					scoreboardButton.setEnabled(false);
					leftGamePanel.setPause(true);
					
					if (started) {
						timer.stop();
						manager.getTimer().stop();
					}

				} else {
					
					pauseButton.setIcon(pauseIcon);
					pauseButton.setRolloverIcon(pauseSelected);
					scoreboardButton.setEnabled(true);

					/*if (started) {
						timer.restart();
<<<<<<< HEAD
						manager.getTimer().restart();
					}
						panel.setPause(false);
=======
					}*/
					leftGamePanel.setPause(false);
					timer.restart();

				}
				leftGamePanel.repaint();
			}
		});

		scoreboardButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				leftGamePanel.setPause(true);
				leftGamePanel.setsBoardActive(true);
				if (started) {
					timer.stop();
				}
				leftGamePanel.getScoreboard().setVisible(true);
				leftGamePanel.repaint();
			}

		});

		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				manager.getTimer().stop();
				leftGamePanel.getMenuPanel().setResumable(true);
				leftGamePanel.setBackFlag(true);
				leftGamePanel.exitToMenu();		
				leftGamePanel.getMenuPanel().setResumable(true);
				leftGamePanel.setBackFlag(true);
				leftGamePanel.exitToMenu();

			}
		});

		
		 this.add(numbersOfBall);	
		 this.add(timeLabel);
		 this.add(pointsLabel);
		 this.add(pauseButton);
		 if(manager instanceof SinglePlayerGameManager)
		 {
			 this.add(scoreboardButton);
		 }
		 this.add(exitButton);


		this.add(numbersOfBall);
		this.add(timeLabel);
		this.add(pointsLabel);
		this.add(pauseButton);
		this.add(scoreboardButton);
		this.add(exitButton);

	}

	public void init() {
		startTime = System.currentTimeMillis();
		timer.start();
		started = true;
	}

	public void refresh() {
		String s = String.format("%02d", manager.getPoints());
		String number = String.format("%02d", manager.getBalls().size());
		pointsLabel.setText(s);
		numbersOfBall.setText(number);
	}

	public void resetTimerLabel()
	{
		String s = String.format("%02d:%02d.%d", manager.getTimer().getMinutes(),manager.getTimer().getSeconds(),manager.getTimer().getDecSeconds());
		timeLabel.setText(s);	
	}


	public void pause() {

		timer.stop();
	}

	public void restart() {
		timer.restart();
	}

	public LeftGamePanel getPanel() {
		return leftGamePanel;
	}

	public void setPanel(LeftGamePanel panel) {
		this.leftGamePanel = panel;
	}
}
