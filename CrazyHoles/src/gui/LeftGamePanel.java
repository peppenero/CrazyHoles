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
import Objects.Hole;
import Objects.World;
import Objects.WorldImpl;



public class LeftGamePanel extends JPanel
{
	private World world;
	private int x;
	private int y;
	private boolean move = false;
	
	
	private Ball ballProva;
	private Hole hole;
	
	public LeftGamePanel(World world) 
	{	
		this.world=world;
		setPreferredSize(new Dimension(800, 200));
		x= this.world.getWidth();
		y= this.world.getHeight();
		setFocusable(true);
		 ballProva= new Ball(10,this.world);
	        this.addKeyListener(new  KeyAdapter() 
	        {
	        	
	        	public void keyPressed(final KeyEvent e)
	            {
	        		
	                switch (e.getKeyCode())
	                {
	                    case KeyEvent.VK_LEFT:
	                    {
	                    	if(ballProva.getCorner()<160)
	                    	{
	                    		ballProva.updateCorner(20);
	                    	}
	                    	break;
	                    }
	                    case KeyEvent.VK_RIGHT:
	                    {
	                    	if(ballProva.getCorner()>30)
	                    	{	
	                    		ballProva.updateCorner(-20); 
	                    	}
	                    	break;
	                    }
	                    case KeyEvent.VK_SPACE:
	                    	ballProva.move();
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
		

		
		switch(ballProva.getColor())
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
		g.fillOval((int)ballProva.getX()-ballProva.getBallRadius() ,(int)(ballProva.getY()-ballProva.getBallRadius()), ballProva.getBallRadius()*2, ballProva.getBallRadius()*2);
		
		
		g.dispose();

	}
	
	public Ball getBall(){
		return this.ballProva;
	}
	
	
}