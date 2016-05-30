package gui;


import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.List;
import java.util.prefs.BackingStoreException;

import javax.swing.JPanel;

import Objects.Ball;
import Objects.GameManager;
import Objects.Giratore;
import Objects.Hole;
import Objects.Muovitore;
import Objects.World;




public class GamePanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Image background;
	FlowLayout layout;
	LeftGamePanel left;
	RightGamePanel right;
	GameManager manager;
	MenuPanel menuPanel;
	
	public GamePanel(GameManager manager,final MenuPanel menu) throws IOException, FontFormatException
	{
		this.manager = manager;
		right = new RightGamePanel(manager);
		left= new LeftGamePanel(manager,right,menu);
		right.setPanel(left);
		layout = new FlowLayout(FlowLayout.LEFT);
		setLayout(layout);
		add(left);
		add(right);
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		background = Toolkit.getDefaultToolkit().createImage("images/Vuoto.jpg");
	}
	
	public void paintComponent(Graphics g){

		g.drawImage(background, 0, 0, this);
	}
	
}
