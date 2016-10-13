package gui;


import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JPanel;

import Objects.GameManager;




public class GamePanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Image background;
	private FlowLayout layout;
	private LeftGamePanel left;
	private RightGamePanel right;
	private GameManager manager;
	private MenuPanel menuPanel;
	
	public GamePanel(GameManager manager,final JPanel menu) throws IOException, FontFormatException
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
