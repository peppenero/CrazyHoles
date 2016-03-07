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
import Objects.GameManager;
import Objects.Hole;
import Objects.World;
import Objects.WorldImpl;



public class LeftGamePanel extends JPanel
{
	private World world;
	private int x;
	private int y;
	private boolean move = false;
	
	
	private Ball ball;
	private Hole hole;
	
	public LeftGamePanel(World world) 
	{	
		this.world=world;
		setPreferredSize(new Dimension(800, 200));
		x= this.world.getWidth();
		y= this.world.getHeight();
		setFocusable(true);
		 hole = new Hole(20,200,150,100,world);
		 ball= new Ball(10,this.world);
	        this.addKeyListener(new  KeyAdapter() 
	        {
	        	
	        	public void keyPressed(final KeyEvent e)
	            {
	        		
	                switch (e.getKeyCode())
	                {
	                    case KeyEvent.VK_LEFT:
	                    {
	                    	if(ball.getCorner()<160)
	                    	{
	                    		ball.updateCorner(20);
	                    	}
	                    	break;
	                    }
	                    case KeyEvent.VK_RIGHT:
	                    {
	                    	if(ball.getCorner()>30)
	                    	{	
	                    		ball.updateCorner(-20); 
	                    	}
	                    	break;
	                    }
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
		Graphics2D g2 = (Graphics2D)g;
		
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
		
		g.drawLine(world.getWidth()/2,world.getHeight(),(world.getWidth()/2), (550));

		g.fillOval((int)ball.getX()-ball.getBallRadius() ,(int)(ball.getY()-ball.getBallRadius()), ball.getBallRadius()*2, ball.getBallRadius()*2);
		g2.drawArc((int) hole.getX() - hole.getRadius(), (int) hole.getY()-hole.getRadius(), hole.getRadius(), hole.getRadius(), 250, 300);

		

		
		g.dispose();

	}
	
	public Ball getBall(){
		return this.ball;
	}
	
	
}
