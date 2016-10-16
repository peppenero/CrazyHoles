package gui;

import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Objects.GameManager;
import Objects.OfflineGameManager;

public class SingleplayerPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameFrame frame;
	private Image background;
	private OurButton newGameButton = new OurButton("NEW GAME");
	private OurButton freePracticeButton = new OurButton("FREE PRACTICE");
	private OurButton backToMenuButton = new OurButton("BACK TO MENU");
	private boolean resumable = false;
	
	public SingleplayerPanel(GameFrame f)
	{	
		frame=f;
		setLayout(null);
		add(newGameButton);
		add(freePracticeButton);
		
		background = Toolkit.getDefaultToolkit().createImage("images/Background.jpg");
	   
		newGameButton.setBounds(100, 400, OurButton.WIDTH,OurButton.HEIGHT);
		freePracticeButton.setBounds(100, 480, OurButton.WIDTH,OurButton.HEIGHT);
		
		backToMenuButton .setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				GameFrame.switchTo(frame.getMenuPanel());
			}
		});
		
		backToMenuButton.setBounds(100,700,OurButton.WIDTH,OurButton.HEIGHT);
		add(backToMenuButton);
		
		newGameButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				setResumable(false);
				try {
					GameFrame.switchTo(frame.getGamePanel());
				} catch (IOException | FontFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		

		freePracticeButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GameFrame.switchTo(new ListOfLevelPanel(frame.getMenuPanel(), "FreePractice"));
			}
		});

		
	}
	

	
	
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		g.drawImage(background, 0, 0,this);
		
	}
	
	public boolean isResumable() {
		return resumable;
	}


	public void setResumable(boolean resumable) {
		this.resumable = resumable;
	}
	
}
