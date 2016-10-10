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

public class MultiplayerPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameFrame frame;
	private Image background;
	private OurButton offline  = new OurButton("Offline");
	private OurButton online = new OurButton("Online");
	
	private MenuPanel menuPanel;
	private OurButton backToMenuButton = new OurButton("BACK TO MENU");
	
	public MultiplayerPanel(GameFrame f)
	{	
		frame=f;
		setLayout(null);
		add(offline);
		add(online);
		
		background = Toolkit.getDefaultToolkit().createImage("images/Background.jpg");
	   
		offline.setBounds(100, 400, OurButton.WIDTH,OurButton.HEIGHT);
		online.setBounds(100, 480, OurButton.WIDTH,OurButton.HEIGHT);
		
		backToMenuButton .setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				GameFrame.switchTo(frame.getMenuPanel());
			}
		});
		
		backToMenuButton.setBounds(100,700,OurButton.WIDTH,OurButton.HEIGHT);
		add(backToMenuButton);
		
		offline.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				try {
					GameManager manager = new OfflineGameManager();
					GamePanel panel = new GamePanel(manager,menuPanel);
					GameFrame.switchTo(panel);
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				} catch (FontFormatException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		
		online.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				GameFrame.switchTo(new OnlinePanel());
			}
		});

		
	}
	

	
	
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		g.drawImage(background, 0, 0,this);
		
	}
	
}
