package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Objects.World;
import Objects.WorldImpl;



public class LeftGamePanel extends JPanel
{
	private World world;
	private int x;
	private int y;
	private int width;
	private int height;
	private final int bW=25;

	
	public LeftGamePanel(World world) 
	{	
		this.world=world;
		x= 20;
		y=20;
		width = y * (bW + 2);
		height = x * (bW + 1);
		setPreferredSize(new Dimension(width, height));
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.black);
		
		// Vertical
		for(int i = 1; i <= y; i++)
			g.drawLine(i * bW, bW, i * bW, x * bW);
		

				// Horizontal
		for(int i = 1; i <= x; i++)
			g.drawLine(bW, i * bW, y * bW, i * bW);
		
		g2.dispose();

	}
	
}
