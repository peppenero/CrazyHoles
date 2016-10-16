package gui;


import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JPanel;

import Objects.GameManager;




public class GamePanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FlowLayout layout;
	private LeftGamePanel left;
	private RightGamePanel right;

	
	public GamePanel(GameManager manager,final JPanel menu) throws IOException, FontFormatException
	{
		right = new RightGamePanel(manager);
		left= new LeftGamePanel(manager,right,menu);
		right.setPanel(left);
		layout = new FlowLayout(FlowLayout.LEFT);
		setLayout(layout);
		add(left);
		add(right);
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	}
	
	public void paintComponent(Graphics g){

		g.drawImage(ImageProv.getIstance().getBackgroundVuoto(), 0, 0, this);
	}
	
}
