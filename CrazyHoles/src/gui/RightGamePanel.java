package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.NumericShaper;
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
import Objects.OfflineGameManager;
import Objects.SinglePlayerGameManager;


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
	private JLabel playerOnePoints;
	private JLabel playerTwoPoints;
	private JLabel playerOneSet;
	private JLabel playerTwoSet;
	private BoxLayout layout;
	private boolean started = false;
	private OurButton exitButton;
	private OurButton scoreboardButton;
	private OurButton pauseButton;
	private LeftGamePanel leftGamePanel;
	private JPanel top;
	private JPanel center;
	private JPanel bottom;

	public RightGamePanel(final GameManager manager)
			throws FontFormatException, IOException {
		// TODO Auto-generated constructor stub
		this.manager = manager;
		layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setAlignmentX(LEFT_ALIGNMENT);
		this.setLayout(layout);
		setPreferredSize(new Dimension(450, 800));
		exitButton = new OurButton("EXIT");
		scoreboardButton = new OurButton("SCOREBOARD");
		pauseButton = new OurButton("PAUSE");
		pauseButton.setDisabledIcon(pauseDeselected);
		scoreboardButton.setDisabledIcon(scoreBoardDeselected);
		String filename = "data/EASPORTS15.ttf";
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
		 numbersOfBall.setAlignmentY(TOP_ALIGNMENT);
		 numbersOfBall.setAlignmentX(CENTER_ALIGNMENT);
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
		 if(manager instanceof OfflineGameManager)
		 {	 
			 String pointsPone = String.format("%02d", ((OfflineGameManager) manager).getFirstPlayerPoints());
			 playerOnePoints = new JLabel(pointsPone);
			 playerOnePoints.setBorder(lowered);
			 playerOnePoints.setOpaque(true);
			 playerOnePoints.setBackground(Color.white);
			 playerOnePoints.setForeground(Color.BLACK);
			 playerOnePoints.setAlignmentY(TOP_ALIGNMENT);
			 playerOnePoints.setAlignmentX(CENTER_ALIGNMENT);
			 playerOnePoints.setFont(font);
			 
			 String pointsPtwo = String.format("%02d", ((OfflineGameManager) manager).getSecondPlayerpoints());
			 playerTwoPoints = new JLabel(pointsPtwo);
			 playerTwoPoints.setBorder(lowered);
			 playerTwoPoints.setOpaque(true);
			 playerTwoPoints.setBackground(Color.white);
			 playerTwoPoints.setForeground(Color.BLACK);
			 playerTwoPoints.setAlignmentY(TOP_ALIGNMENT);
			 playerTwoPoints.setAlignmentX(CENTER_ALIGNMENT);
			 playerTwoPoints.setFont(font);
			 
			 String setPone = String.format("%02d", ((OfflineGameManager) manager).getPlayerOneSet());
			 playerOneSet = new JLabel(setPone);
			 playerOneSet.setBorder(lowered);
			 playerOneSet.setOpaque(true);
			 playerOneSet.setBackground(Color.white);
			 playerOneSet.setForeground(Color.BLACK);
			 playerOneSet.setAlignmentY(TOP_ALIGNMENT);
			 playerOneSet.setAlignmentX(CENTER_ALIGNMENT);
			 playerOneSet.setFont(font);
			 
			 String setPtwo = String.format("%02d", ((OfflineGameManager) manager).getPlayertwoSet());
			 playerTwoSet = new JLabel(setPtwo);
			 playerTwoSet.setBorder(lowered);
			 playerTwoSet.setOpaque(true);
			 playerTwoSet.setBackground(Color.white);
			 playerTwoSet.setForeground(Color.BLACK);
			 playerTwoSet.setAlignmentY(TOP_ALIGNMENT);
			 playerTwoSet.setAlignmentX(CENTER_ALIGNMENT);
			 playerTwoSet.setFont(font);
		 }
		setOpaque(false);
		
		pauseButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if (!leftGamePanel.isPause()) {

					pauseButton.setIcon(restartIcon);
					//AGGIUSTARE
					//pauseButton.setRolloverIcon(restartSelected);
					scoreboardButton.setEnabled(false);
					leftGamePanel.setPause(true);
					
					if (started) {
						manager.getTimer().pause();
					}

				} else {
					
					pauseButton.setIcon(pauseIcon);
					//AGGIUSTARE
					//pauseButton.setRolloverIcon(pauseSelected);
					scoreboardButton.setEnabled(true);
					leftGamePanel.setPause(false);
					manager.getTimer().restart();
					
				}
				leftGamePanel.repaint();
			}
		});

		

		scoreboardButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				leftGamePanel.setPause(true);
				leftGamePanel.setsBoardActive(true);
				if (started) {
					timer.stop();
				}
				leftGamePanel.getScoreboard().setVisible(true);
				leftGamePanel.repaint();
			}
		});
		
		exitButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e ){
				if(manager instanceof SinglePlayerGameManager)
				{
					manager.getTimer().stop();
					leftGamePanel.getMenuPanel().setResumable(true);
					leftGamePanel.setBackFlag(true);
					leftGamePanel.exitToMenu();		
					leftGamePanel.getMenuPanel().setResumable(true);
					leftGamePanel.setBackFlag(true);
					leftGamePanel.exitToMenu();
				}
			}
		});

		 add(numbersOfBall);	
		 add(timeLabel);
		 add(pointsLabel);
		 
		 if(manager instanceof OfflineGameManager)
		 {
			 add(playerOnePoints);
			 add(playerTwoPoints);
			 add(playerOneSet);
			 add(playerTwoSet);
		 }
		 
		 add(pauseButton);
		 if(manager instanceof SinglePlayerGameManager)
		 {
			 add(scoreboardButton);
		 }
		 add(exitButton);
		 
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
		if(manager instanceof OfflineGameManager)
		{
			String onePoints = String.format("%02d", ((OfflineGameManager) manager).getFirstPlayerPoints());
			playerOnePoints.setText(onePoints);
			String twoPoints = String.format("%02d", ((OfflineGameManager) manager).getSecondPlayerpoints());
			playerTwoPoints.setText(twoPoints);
			String oneSet = String.format("%02d", ((OfflineGameManager) manager).getPlayerOneSet());
			playerOneSet.setText(oneSet);
			String twoSet = String.format("%02d", ((OfflineGameManager) manager).getPlayertwoSet());
			playerTwoSet.setText(twoSet);
		}
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
