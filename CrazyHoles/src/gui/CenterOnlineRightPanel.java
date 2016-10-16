package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Objects.GameManager;
import Objects.OnlineGameManager;

public class CenterOnlineRightPanel extends JPanel{

	private Font font = OurFont.getInstance().deriveFont(Font.TRUETYPE_FONT, 30);
	private JLabel label;
	private JLabel opponetPointsLabel;
	private GameManager manager;
	
	public CenterOnlineRightPanel(GameManager man)
	{
		manager = man; 
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setOpaque(false);
		
		label = new JLabel("OPPONENT POINTS");
		label.setForeground(Color.BLUE);
		label.setAlignmentX(CENTER_ALIGNMENT);
		label.setAlignmentY(BOTTOM_ALIGNMENT);
		label.setFont(font);
		
		 String pointsOpponent = String.format("%02d", (((OnlineGameManager) manager).getOpponentPoints()));
		 setOpponetPointsLabel(new JLabel(pointsOpponent));
		 getOpponetPointsLabel().setForeground(Color.BLACK);
		 getOpponetPointsLabel().setAlignmentY(TOP_ALIGNMENT);
		 getOpponetPointsLabel().setAlignmentX(CENTER_ALIGNMENT);
		 getOpponetPointsLabel().setFont(font);
		 
		 add(label);
		 add(opponetPointsLabel);
		 add(Box.createVerticalGlue());
	}

	public JLabel getOpponetPointsLabel() {
		return opponetPointsLabel;
	}

	public void setOpponetPointsLabel(JLabel opponetPointsLabel) {
		this.opponetPointsLabel = opponetPointsLabel;
	}

}
