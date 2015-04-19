package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Objects.Ball;
import Objects.GameManeger;
import Objects.World;
import Objects.WorldImpl;



public class LeftGamePanel extends JPanel
{
	private World world;
	private int x;
	private int y;
	
	//private final int bW=20;
	private Ball ball;
	
	public LeftGamePanel(World world) 
	{	
		this.world=world;
		setPreferredSize(new Dimension(800, 200));
		x= this.world.getWidth();
		y= this.world.getHeight();
		setFocusable(true);
		 ball= new Ball(10,this.world);
	        this.addKeyListener(new  KeyAdapter() 
	        {
	        	
	        	public void keyPressed(final KeyEvent e)
	            {
	        		
	                switch (e.getKeyCode())
	                {
	                    case KeyEvent.VK_LEFT:
	             
	                        ball.updateCorner(25);
	                        break;
	                    case KeyEvent.VK_RIGHT:
	                        ball.updateCorner(-25); 
	                        break;
	                    case KeyEvent.VK_SPACE:
	                    	
	                    	ball.move();
	                    	break;
	                }
	                repaint();
	            }
			});
		 
		 
		
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		//Graphics2D g2 = (Graphics2D)g;
		
		g.setColor(Color.black);
		g.drawLine(0, 0, 0, y );
		g.drawLine(0,y,x ,y);
		g.drawLine(x, 0, x, y);
		g.drawLine(0, 0,x, 0);
		

		
		switch(ball.getColor())
		{
			case "rosso" :
			{
				g.setColor(Color.red);   
				break;
			}
			case "verde":
			{
				g.setColor(Color.green);
				break;
			}
			case "giallo":
			{
				g.setColor(Color.yellow); 
				break;
			}
			
		}
		g.fillOval((int)ball.getX() ,(int)(ball.getY()), ball.getBallRadius(), ball.getBallRadius());
		
		
		g.dispose();

	}
	
	
}