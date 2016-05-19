package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
	private int xMouse;
	private int yMouse;
	Muovitore m ;
	Giratore g;
	
  public LeftGamePanel(GameManager manager,final RightGamePanel panel) throws IOException 
	{	
	  	
		this.gameManager = manager;
		this.world=gameManager.getWorld();
		setPreferredSize(new Dimension(1280, 800));
		x= world.getWidth();
		y= world.getHeight();
		gameManager.start(); 
		 holes = gameManager.getHoles();
		 prov = new ImageProv();
		/* g=new Giratore(holes, this);
		 g.start();*/
		 
	        addKeyListener(new  KeyAdapter() 
	        {
	        	
	        	public void keyPressed(final KeyEvent e)
	            {
	        		
	                switch (e.getKeyCode())
	                { 
	                    
	                    case KeyEvent.VK_LEFT:
	                    {
	                    	gameManager.getBall().moveLeft();
	                    	break;
	                    }
	                    case KeyEvent.VK_RIGHT:
	                    {
	                    	gameManager.getBall().moveRight();
	                    	break;
	                    }
	                  }
	                repaint();
	            }
			});
	        
	        addMouseListener(new MouseAdapter() {
	        	
	        	public void mouseClicked(MouseEvent e)
	        	{
	        		switch(e.getButton())
	        		{
		        		case MouseEvent.BUTTON1:
		        		{
		        			if(!isMove())
	                    		setMove(true);
		                    	m=new Muovitore(gameManager.getBall(),LeftGamePanel.this,panel,gameManager);
		                    	m.start();   
	        	            break;
		        		}
	        		}
	        	}
	        	
			});
	        		 
		 addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				if(!isMove())
				{
					xMouse=e.getX();
					yMouse=e.getY();
					double m = (yMouse-gameManager.getBall().getY()*10)/(xMouse-gameManager.getBall().getX()*10);			
					double corner = Math.atan(m);
					gameManager.getBall().setCorner((float)(Math.toDegrees((corner))-360)%180);
					repaint();
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
		
		
		
		if(!isMove())
		{
		float directionX = (gameManager.getBall().getX());
		float directionY = (gameManager.getBall().getY());
		
		float deltaX = gameManager.getBall().getDeltaX(); 
		int bal = 0;
		
		while(bal <30)		
		{	
			AffineTransform at2 = new AffineTransform();
			if(directionX+deltaX <= 0 || directionX+deltaX >= world.getWidth())
				deltaX= -deltaX;
			
			at2.translate((directionX+deltaX)*10-prov.getDirectionBall().getWidth(this)/2, (directionY+gameManager.getBall().getDeltaY())*10);
			g2.drawImage(prov.getDirectionBall(),at2,this);		
			directionX+=(deltaX);
			directionY+=(gameManager.getBall().getDeltaY());
			bal++;
		}
		}
		
		AffineTransform at1 = new AffineTransform();
		at1.translate((gameManager.getBall().getX()-gameManager.getBall().getBallRadius())*10, (gameManager.getBall().getY()-gameManager.getBall().getBallRadius())*10);
		g2.drawImage(prov.getBall(gameManager.getBall().getColor()),at1,this);
		
		for(int i=0; i<holes.size();i++)
		{		
			holeImage =  prov.getHole(holes.get(i).getColor()); 
			AffineTransform at = new AffineTransform();
			at.translate((holes.get(i).getX())*10,(holes.get(i).getY())*10);
			at.rotate(Math.toRadians(holes.get(i).getAngle()));
			at.translate(-holeImage.getWidth(this)/2, -holeImage.getHeight(this)/2);
			g2.drawImage(holeImage,at,this);
		}
		
		
		requestFocus();
	  	setFocusable(true);
		requestFocusInWindow();
		
		g.dispose();
	}
	
	public Ball getBall(){
		return this.ball;
	}

	public boolean isMove() {
		return move;
	}

	public void setMove(boolean move) {
		this.move = move;
	}
	
	
}
