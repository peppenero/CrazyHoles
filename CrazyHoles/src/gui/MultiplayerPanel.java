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

public class MultiplayerPanel extends JPanel
{
	private GameFrame frame;
	private Image background;
	private Icon off = new ImageIcon("images/Offline.png");
	private Icon offSelected = new ImageIcon("images/Offline_Selected.png");
	private MenuButton offline  = new MenuButton(off,offSelected);
	private Icon on = new ImageIcon("images/Online.png");
	private Icon onSelected = new ImageIcon("images/Online_Selected.png");
	private MenuButton online = new MenuButton(on,onSelected);
	private Icon backToMenu = new ImageIcon("images/backtomenu.png");
	private Icon backToMenuSelected = new ImageIcon("images/backtomenu_selected.png");
	private MenuButton backToMenuButton = new MenuButton(backToMenu,backToMenuSelected);
	private MenuPanel menuPanel;
	
	public MultiplayerPanel(GameFrame f)
	{	
		frame=f;
		setLayout(null);
		add(offline);
		add(online);
		add(backToMenuButton);
		
		background = Toolkit.getDefaultToolkit().createImage("images/Background.jpg");
	   
		offline.setBounds(100, 400, off.getIconWidth(),off.getIconHeight());
		online.setBounds(100, 480, on.getIconWidth(), on.getIconHeight());
		backToMenuButton.setBounds(100, 700, backToMenu.getIconWidth(), backToMenu.getIconHeight());

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
		
		backToMenuButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GameFrame.switchTo(frame.getMenuPanel());
			}
		});
	}
	

	
	
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		g.drawImage(background, 0, 0,this);
		
	}
	
}
