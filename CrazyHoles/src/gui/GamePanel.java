package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Objects.Ball;
import Objects.GameManager;
import Objects.Hole;
import Objects.Muovitore;
import Objects.World;
import Objects.WorldImpl;
import Objects.WorldManager;



public class GamePanel extends JPanel
{
	private World world;
	private int x;
	private int y;
	private boolean move = false;
	private WorldManager worldMan;
	private GameManager gameManager;
	private Image holeImage;
	private Ball ball;
	private List<Hole> holes;
	private ImageProv prov ;
	Muovitore m ;
	
	public GamePanel(World world) throws IOException 
	{	
		
		worldMan = new WorldManager();
		gameManager = new GameManager(worldMan);
		this.world=gameManager.getWorld();
		setPreferredSize(new Dimension(800, 200));
		x= this.world.getWidth();
		y= this.world.getHeight();
		setFocusable(true);
		gameManager.start();
		 holes = gameManager.getHoles();
		
		 ball= gameManager.getOneBall();
		 prov = new ImageProv();
		 m = new Muovitore(ball, this);
		 
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
	                    	holes.get(i).setAngle((holes.get(i).getAngle()+10)%360);
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
	                    	m.start();
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
	

		g.drawImage(prov.getBall(ball.getColor()),(int)(ball.getX()-ball.getBallRadius())*10, (int) (ball.getY()-ball.getBallRadius())*10,this);
	
		for(int i=0; i<holes.size();i++)
		{
			holeImage =  prov.getHole(holes.get(i).getColor()); 
			AffineTransform at = new AffineTransform();
			at.translate((holes.get(i).getX())*10,(holes.get(i).getY())*10);
			
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
