package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.List;

import javax.swing.JPanel;

import Objects.Ball;
import Objects.GameManager;
import Objects.Giratore;
import Objects.Hole;
import Objects.Muovitore;
import Objects.World;



public class LeftGamePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private World world;
	private int x;
	private int y;
	private boolean move = false;
	private GameManager gameManager;
	private Image holeImage;
	private Ball ball;
	private List<Hole> holes;
	private ImageProv prov ;
	Muovitore m ;
	Giratore g;
	
  public LeftGamePanel(GameManager manager) throws IOException 
	{	
		
		
		this.gameManager = manager;
		this.world=gameManager.getWorld();
		setPreferredSize(new Dimension(800, 800));
		x= world.getWidth();
		y= world.getHeight();
		setFocusable(true);
		gameManager.start();
		 holes = gameManager.getHoles();
		 ball= gameManager.getOneBall();
		 prov = new ImageProv();
		 
		// g=new Giratore(holes, this);
		 //g.start();
		 
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
	                    case KeyEvent.VK_UP:
	                    {
	                    	for(int i=0;i<holes.size();i++)
	                    	holes.get(i).move();
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
	                    {
	                    //	m=new Muovitore(gameManager.getBall(),GamePanel.this ,gameManager);
	                    //	m.start();
	                    	
	                    	
	                    	
	                    	break;
	                    }
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
		g.drawLine(0*10, 0*10, 0*10, y*10);
		g.drawLine(0*10,y*10,x*10 ,y*10);
		g.drawLine(x*10, 0*10, x*10, y*10);
		g.drawLine(0*10,0*10,x*10,0*10);		

		g.drawImage(prov.getBall(gameManager.getBall().getColor()),(int)(gameManager.getBall().getX()-ball.getBallRadius())*10, (int) (gameManager.getBall().getY()-ball.getBallRadius())*10,this);
		for(int i=0; i<holes.size();i++)
		{		
			holeImage =  prov.getHole(holes.get(i).getColor()); 
			AffineTransform at = new AffineTransform();
			at.translate((holes.get(i).getX())*10,(holes.get(i).getY())*10);
			g.fillOval((int)holes.get(i).getX1()*10,(int) holes.get(i).getY1()*10, 5, 5);
			g.drawOval((int)holes.get(i).getX2()*10,(int) holes.get(i).getY2()*10, 5, 5);
			at.rotate(Math.toRadians(holes.get(i).getAngle()));
			at.translate(-holeImage.getWidth(this)/2, -holeImage.getHeight(this)/2);
			
			g2.drawImage(holeImage,at,this);
		}
		g.dispose();

	}
	
	public Ball getBall(){
		return this.ball;
	}
	
	
}
