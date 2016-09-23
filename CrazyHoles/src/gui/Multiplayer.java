package gui;

import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Objects.GameManager;
import Objects.OfflineGameManager;

public class Multiplayer extends JPanel
{
	private Image background;
	private Icon off = new ImageIcon("images/Offline.png");
	private Icon offSelected = new ImageIcon("images/Offline_Selected.png");
	private MenuButton offline  = new MenuButton(off,offSelected);
	private Icon on = new ImageIcon("images/Online.png");
	private Icon onSelected = new ImageIcon("images/Online_Selected.png");
	private MenuButton online = new MenuButton(on,onSelected);
	private MenuPanel menuPanel;
	
	public Multiplayer(MenuPanel panel)
	{		
		menuPanel = panel;
		setLayout(null);
		add(offline);
		add(online);
		
		background = Toolkit.getDefaultToolkit().createImage("images/Background.jpg");
	   
		offline.setBounds(100, 400, off.getIconWidth(),off.getIconHeight());
		online.setBounds(100, 480, on.getIconWidth(), on.getIconHeight());

		offline.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				 
				try {
					GameManager manager = new OfflineGameManager();
					GamePanel panel = new GamePanel(manager,menuPanel);
					GameFrame.switchTo(panel);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FontFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		
	}
	

	
	
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		g.drawImage(background, 0, 0,this);
		
	}
	
}
