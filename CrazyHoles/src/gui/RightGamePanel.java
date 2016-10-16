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

	private long startTime;
	private BoxLayout layout;
	private boolean started = false;
	private OurButton exitButton;
	private OurButton pauseButton;
	private LeftGamePanel leftGamePanel;
	private CenterRightPanel centerPanel;
	private JPanel bottomPanel;
	private TopRightPanel topPanel;
	private CenterOnlineRightPanel centerOnlinePanel;

	public RightGamePanel(final GameManager manager)
			throws FontFormatException, IOException {
		// TODO Auto-generated constructor stub
		this.manager = manager;
		setOpaque(false);
		layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		setPreferredSize(new Dimension(450,400));
		topPanel = new TopRightPanel(manager);
		bottomPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
		topPanel.setAlignmentY(TOP_ALIGNMENT);
		bottomPanel.setAlignmentY(BOTTOM_ALIGNMENT);
		topPanel.setOpaque(false);
		bottomPanel.setOpaque(false);
		exitButton = new OurButton("EXIT");
		pauseButton = new OurButton("PAUSE");
		pauseButton.setAlignmentX(CENTER_ALIGNMENT);
		pauseButton.setAlignmentY(BOTTOM_ALIGNMENT);
		exitButton.setAlignmentX(CENTER_ALIGNMENT);
		exitButton.setAlignmentY(BOTTOM_ALIGNMENT);
		
		
		 

		 if(manager instanceof OnlineGameManager)
		 {
			 centerOnlinePanel = new CenterOnlineRightPanel(manager);
			 
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
					leftGamePanel.setPause(true);
					
					if (started) {
						manager.getTimer().pause();
					}

				} else {
					
					pauseButton.setText("PAUSE");
					//AGGIUSTARE
					//pauseButton.setRolloverIcon(pauseSelected);
					leftGamePanel.setPause(false);
					manager.getTimer().restart();
					
				}
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

		 add(topPanel);
		 
		 if(manager instanceof OnlineGameManager)
		 {
			 add(centerOnlinePanel);
		 }
		 
		 if(manager instanceof OfflineGameManager)
		 {
			 add(centerPanel);
		 }
		 
		 
		 bottomPanel.add(pauseButton);
		 bottomPanel.add(exitButton);
		
		
		 add(bottomPanel);
	}

	public void init() {
		startTime = System.currentTimeMillis();
		topPanel.getTimer().start();
		started = true;
	}

	public void refresh() {
		String s = String.format("%02d", manager.getPoints());
		String number = String.format("%02d", manager.getBalls().size());
		topPanel.getPointsLabel().setText(s);
		topPanel.getNumbersOfBall().setText(number);
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
			 centerOnlinePanel.getOpponetPointsLabel().setText(pointsOpponent);
		}
	}
	public void onlineRefresh()
	{
		String pointsOpponent = String.format("%02d", (((OnlineGameManager) manager).getOpponentPoints()));
		 centerOnlinePanel.getOpponetPointsLabel().setText(pointsOpponent);
	}

	public void resetTimerLabel()
	{
		String s = String.format("%02d:%02d.%d", manager.getTimer().getMinutes(),manager.getTimer().getSeconds(),manager.getTimer().getDecSeconds());
		topPanel.getTimeLabel().setText(s);	
	}


	public void pause() {

		topPanel.getTimer().stop();
	}

	public void restart() {
		topPanel.getTimer().restart();
	}

	public LeftGamePanel getPanel() {
		return leftGamePanel;
	}

	public void setPanel(LeftGamePanel panel) {
		this.leftGamePanel = panel;
	}

	
}
