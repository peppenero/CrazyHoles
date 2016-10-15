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
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

import Objects.GameManager;
import Objects.OfflineGameManager;
import Objects.OnlineGameManager;
import Objects.SinglePlayerGameManager;


public class RightGamePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameManager manager;
	
	private JLabel timeLabel;
	private JLabel pointsLabel;
	private JLabel opponetPointsLabel;
	private Timer timer;
	private long startTime;
	private JLabel numbersOfBall;
	private BoxLayout layout;
	private boolean started = false;
	private OurButton exitButton;
	private OurButton scoreboardButton;
	private OurButton pauseButton;
	private LeftGamePanel leftGamePanel;
	private JPanel topPanel;
	private CenterRightPanel centerPanel;
	private JPanel bottomPanel;

	public RightGamePanel(final GameManager manager)
			throws FontFormatException, IOException {
		// TODO Auto-generated constructor stub
		this.manager = manager;
		setOpaque(false);
		layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		setPreferredSize(new Dimension(450,400));
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
		topPanel.setAlignmentY(TOP_ALIGNMENT);
		bottomPanel.setAlignmentY(BOTTOM_ALIGNMENT);
		topPanel.setOpaque(false);
		bottomPanel.setOpaque(false);
		exitButton = new OurButton("EXIT");
		scoreboardButton = new OurButton("SCOREBOARD");
		pauseButton = new OurButton("PAUSE");
		pauseButton.setAlignmentX(CENTER_ALIGNMENT);
		pauseButton.setAlignmentY(BOTTOM_ALIGNMENT);
		scoreboardButton.setAlignmentX(CENTER_ALIGNMENT);
		scoreboardButton.setAlignmentY(BOTTOM_ALIGNMENT);
		exitButton.setAlignmentX(CENTER_ALIGNMENT);
		exitButton.setAlignmentY(BOTTOM_ALIGNMENT);
		String filename = "data/EASPORTS15.ttf";
		Font font = Font.createFont(Font.TRUETYPE_FONT, new File(filename));
		font = font.deriveFont(Font.TRUETYPE_FONT, 30);
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		ge.registerFont(font);
		
		 
		 timeLabel = new JLabel("00:00.00");
		 timeLabel.setForeground(Color.black);
		 timeLabel.setFont(font);
		 timeLabel.setAlignmentY(TOP_ALIGNMENT);
		 timeLabel.setAlignmentX(CENTER_ALIGNMENT);
		 String point = String.format("%02d", manager.getPoints());
		 pointsLabel = new JLabel(point);
		 pointsLabel.setForeground(Color.BLACK);
		 pointsLabel.setAlignmentY(TOP_ALIGNMENT);
		 pointsLabel.setAlignmentX(CENTER_ALIGNMENT);
		 pointsLabel. setFont(font);
		 String number = String.format("%02d", manager.getBalls().size());
		 numbersOfBall=new JLabel(number);
		 numbersOfBall.setForeground(Color.BLACK);
		 numbersOfBall.setAlignmentY(TOP_ALIGNMENT);
		 numbersOfBall.setAlignmentX(CENTER_ALIGNMENT);
		 numbersOfBall.setFont(font);
		 String s = String.format("%02d:%02d.%02d", manager.getTimer().getMinutes(),manager.getTimer().getSeconds(),manager.getTimer().getDecSeconds());
			timeLabel.setText(s);
		 timer = new Timer(100, new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				 String s = String.format("%02d:%02d.%d", manager.getTimer().getMinutes(),manager.getTimer().getSeconds(),manager.getTimer().getDecSeconds());
				timeLabel.setText(s);				
			}
			
			
		});
		 if(manager instanceof OnlineGameManager)
		 {
			 String pointsOpponent = String.format("%02d", (((OnlineGameManager) manager).getOpponentPoints()));
			 opponetPointsLabel = new JLabel(pointsOpponent);
			 opponetPointsLabel.setForeground(Color.BLACK);
			 opponetPointsLabel.setAlignmentY(TOP_ALIGNMENT);
			 opponetPointsLabel.setAlignmentX(CENTER_ALIGNMENT);
			 opponetPointsLabel.setFont(font);
			 
		 }
		 if(manager instanceof OfflineGameManager)
		 {	 
				centerPanel = new CenterRightPanel(manager);
			 
		 }
		
		
		pauseButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if (!leftGamePanel.isPause()) {

					pauseButton.setText("RESTART");
					//AGGIUSTARE
					//pauseButton.setRolloverIcon(restartSelected);
					scoreboardButton.setEnabled(false);
					leftGamePanel.setPause(true);
					
					if (started) {
						manager.getTimer().pause();
					}

				} else {
					
					pauseButton.setText("PAUSE");
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
					if(!manager.isGameOver())
					{
						manager.getTimer().stop();
						((MenuPanel)leftGamePanel.getMenuPanel()).setResumable(true);
						leftGamePanel.setBackFlag(true);
						leftGamePanel.exitToMenu();	
					}
					else
					{
						leftGamePanel.exitToMenu();	
					}
				}
			}
		});

		 topPanel.add(numbersOfBall);	
		 topPanel.add(timeLabel);
		 topPanel.add(pointsLabel);
		 add(topPanel);
		 if(manager instanceof OnlineGameManager)
		 {
			 add(opponetPointsLabel);
		 }
		 
		 if(manager instanceof OfflineGameManager)
		 {
			 add(centerPanel);
		 }
		 
		 
		 bottomPanel.add(pauseButton);
		 if(manager instanceof SinglePlayerGameManager)
		 {
			bottomPanel.add(scoreboardButton);
		 }
		 bottomPanel.add(exitButton);
		
		
		 add(bottomPanel);
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
			centerPanel.getPlayerOnePoints().setText(onePoints);
			String twoPoints = String.format("%02d", ((OfflineGameManager) manager).getSecondPlayerpoints());
			centerPanel.getPlayerTwoPoints().setText(twoPoints);
			String oneSet = String.format("%02d", ((OfflineGameManager) manager).getPlayerOneSet());
			centerPanel.getPlayerOneSet().setText(oneSet);
			String twoSet = String.format("%02d", ((OfflineGameManager) manager).getPlayertwoSet());
			centerPanel.getPlayerTwoSet().setText(twoSet);
		}
		if(manager instanceof OnlineGameManager)
		{
			 String pointsOpponent = String.format("%02d", (((OnlineGameManager) manager).getOpponentPoints()));
			 opponetPointsLabel.setText(pointsOpponent);
		}
	}
	public void onlineRefresh()
	{
		System.out.println("rightpanl " + ((OnlineGameManager) manager).getOpponentPoints());
		String pointsOpponent = String.format("%02d", (((OnlineGameManager) manager).getOpponentPoints()));
		 opponetPointsLabel.setText(pointsOpponent);
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

	public JLabel getOpponetPointsLabel() {
		return opponetPointsLabel;
	}

	public void setOpponetPointsLabel(JLabel opponetPointsLabel) {
		this.opponetPointsLabel = opponetPointsLabel;
	}
}
