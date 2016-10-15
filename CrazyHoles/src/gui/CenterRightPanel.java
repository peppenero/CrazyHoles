package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Objects.GameManager;
import Objects.OfflineGameManager;

public class CenterRightPanel extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Font font;
	private GameManager manager;
	private JPanel firstPanel;
	private JPanel secondPanel;
	private JPanel thirdPanel;
	private JPanel fourthPanel;
	private JLabel playerOne;
	private JLabel playerTwo;
	private JLabel playerPoints;	
	private JLabel playerSets;
	private JLabel player2Points;	
	private JLabel player2Sets;
	private JLabel playerOnePoints;
	private JLabel playerTwoPoints;
	private JLabel playerOneSet;
	private JLabel playerTwoSet;
	
	public CenterRightPanel(GameManager manager)
	{
		super();
		this.manager=manager;
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		font = OurFont.getInstance().deriveFont(Font.TRUETYPE_FONT, 30);
		
		firstPanel = new JPanel();
		firstPanel.setOpaque(false);
		firstPanel.setAlignmentX(CENTER_ALIGNMENT);
		firstPanel.setAlignmentY(TOP_ALIGNMENT);
		secondPanel = new JPanel();
		secondPanel.setOpaque(false);
		secondPanel.setAlignmentX(CENTER_ALIGNMENT);
		secondPanel.setAlignmentY(CENTER_ALIGNMENT);
		thirdPanel = new JPanel();
		thirdPanel.setOpaque(false);
		thirdPanel.setAlignmentX(CENTER_ALIGNMENT);
		thirdPanel.setAlignmentY(CENTER_ALIGNMENT);
		fourthPanel = new JPanel();
		fourthPanel.setOpaque(false);
		fourthPanel.setAlignmentX(CENTER_ALIGNMENT);
		fourthPanel.setAlignmentY(CENTER_ALIGNMENT);
		
		firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.Y_AXIS));
		secondPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
		thirdPanel.setLayout(new BoxLayout(thirdPanel, BoxLayout.Y_AXIS));
		fourthPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
		
		playerOne = new JLabel("PLAYER 1");
		playerOne.setFont(font);
		playerOne.setAlignmentX(CENTER_ALIGNMENT);
		playerOne.setAlignmentY(BOTTOM_ALIGNMENT);
		
		playerTwo = new JLabel("PLAYER 2");
		playerTwo.setFont(font);
		playerTwo.setAlignmentX(CENTER_ALIGNMENT);
		playerTwo.setAlignmentY(BOTTOM_ALIGNMENT);
		
		playerPoints = new JLabel("POINTS");
		playerPoints.setFont(font);
		
		playerSets = new JLabel("SET");
		playerSets.setFont(font);
		
		player2Points = new JLabel("POINTS");
		player2Points.setFont(font);
		
		player2Sets = new JLabel("SET");
		player2Sets.setFont(font);
		
		 String pointsPone = String.format("%02d", ((OfflineGameManager) manager).getFirstPlayerPoints());
		 setPlayerOnePoints(new JLabel(pointsPone));
		 getPlayerOnePoints().setForeground(Color.BLACK);
		 getPlayerOnePoints().setAlignmentY(TOP_ALIGNMENT);
		 getPlayerOnePoints().setAlignmentX(CENTER_ALIGNMENT);
		 getPlayerOnePoints().setFont(font);
		 
		 String pointsPtwo = String.format("%02d", ((OfflineGameManager) manager).getSecondPlayerpoints());
		 setPlayerTwoPoints(new JLabel(pointsPtwo));
		 getPlayerTwoPoints().setForeground(Color.BLACK);
		 getPlayerTwoPoints().setAlignmentY(TOP_ALIGNMENT);
		 getPlayerTwoPoints().setAlignmentX(CENTER_ALIGNMENT);
		 getPlayerTwoPoints().setFont(font);
		 
		 String setPone = String.format("%02d", ((OfflineGameManager) manager).getPlayerOneSet());
		 setPlayerOneSet(new JLabel(setPone));
		 getPlayerOneSet().setForeground(Color.BLACK);
		 getPlayerOneSet().setAlignmentY(TOP_ALIGNMENT);
		 getPlayerOneSet().setAlignmentX(CENTER_ALIGNMENT);
		 getPlayerOneSet().setFont(font);
		 
		 String setPtwo = String.format("%02d", ((OfflineGameManager) manager).getPlayertwoSet());
		 setPlayerTwoSet(new JLabel(setPtwo));
		 getPlayerTwoSet().setForeground(Color.BLACK);
		 getPlayerTwoSet().setAlignmentY(TOP_ALIGNMENT);
		 getPlayerTwoSet().setAlignmentX(CENTER_ALIGNMENT);
		 getPlayerTwoSet().setFont(font);
		
		 firstPanel.add(playerOne);
		 secondPanel.add(playerPoints);
		 secondPanel.add(getPlayerOnePoints());
		 secondPanel.add(playerSets);
		 secondPanel.add(getPlayerOneSet());
		 thirdPanel.add(playerTwo);
		 fourthPanel.add(player2Points);
		 fourthPanel.add(getPlayerTwoPoints());
		 fourthPanel.add(player2Sets);
		 fourthPanel.add(getPlayerTwoSet());

		 this.add(firstPanel);
		 this.add(secondPanel);
		 this.add(thirdPanel);
		 this.add(fourthPanel);
		
	}

	public JLabel getPlayerOnePoints() {
		return playerOnePoints;
	}

	public void setPlayerOnePoints(JLabel playerOnePoints) {
		this.playerOnePoints = playerOnePoints;
	}

	public JLabel getPlayerTwoPoints() {
		return playerTwoPoints;
	}

	public void setPlayerTwoPoints(JLabel playerTwoPoints) {
		this.playerTwoPoints = playerTwoPoints;
	}

	public JLabel getPlayerOneSet() {
		return playerOneSet;
	}

	public void setPlayerOneSet(JLabel playerOneSet) {
		this.playerOneSet = playerOneSet;
	}

	public JLabel getPlayerTwoSet() {
		return playerTwoSet;
	}

	public void setPlayerTwoSet(JLabel playerTwoSet) {
		this.playerTwoSet = playerTwoSet;
	}
}
