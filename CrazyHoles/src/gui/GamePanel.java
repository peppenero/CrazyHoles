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



public class GamePanel extends JPanel
{
	private World world;
	private int x;
	private int y;
	private boolean move = false;
	
	
	private Ball ball;
	private Hole hole;
	
	public GamePanel(World world) 
	{	
		this.world=world;
		setPreferredSize(new Dimension(800, 200));
		x= this.world.getWidth();
		y= this.world.getHeight();
		setFocusable(true);
		 hole = new Hole(20,10,10,5,world);
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
		g.drawLine(0*10, 0*10, 0*10, y*10 );
		g.drawLine(0,y*10,x*10 ,y*10);
		g.drawLine(x*10, 0, x*10, y*10);
		g.drawLine(0, 0,x*10, 0);
		

		
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
		
		g.fillOval((int)hole.getX1()*10,(int) hole.getY1()*10,10,10);
		g.fillOval((int)hole.getX2()*10,(int) hole.getY2()*10,10,10);
		g.fillOval((int)(ball.getX()-ball.getBallRadius())*10 ,(int)((ball.getY()-ball.getBallRadius()))*10, (ball.getBallRadius()*2)*10, (ball.getBallRadius()*2)*10);
		g.drawOval((int)(hole.getX()- hole.getRadius())*10, (int) (hole.getY()-hole.getRadius())*10, (hole.getRadius()*2)*10, (hole.getRadius()*2)*10);

		

		
		g.dispose();

	}
	
	public Ball getBall(){
		return this.ball;
	}
	
	
}
