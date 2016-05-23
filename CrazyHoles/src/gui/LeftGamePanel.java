package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

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
	private boolean pause;
	private Muovitore m ;
	private Giratore g;
	ScoreBoardMenu scoreboard;


	private MenuPanel menuPanel;

	public LeftGamePanel(GameManager manager,final RightGamePanel panel,final MenuPanel menu) throws IOException, FontFormatException 
	{	

		menuPanel = menu;

		this.gameManager = manager;
		this.world=gameManager.getWorld();
		setPreferredSize(new Dimension(810,800));
		x= world.getWidth();
		y= world.getHeight();
		gameManager.start(); 
		holes = gameManager.getHoles();
		prov = new ImageProv();
		scoreboard = new ScoreBoardMenu();

		setOpaque(false);

		/* g=new Giratore(holes, this);
		 g.start();*/

		addKeyListener(new  KeyAdapter() 
		{

			public void keyPressed(final KeyEvent e)
			{

				switch (e.getKeyCode())
				{ 
				case KeyEvent.VK_ESCAPE:
				{
					GameFrame.switchTo(menu);
					break;
				}

				case KeyEvent.VK_LEFT:
				{
					if(!isMove() && !isPause())
						gameManager.getBall().moveLeft();
					break;
				}
				case KeyEvent.VK_RIGHT:
				{
					if(!isMove() && !isPause())
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
					if(!isMove() && !isPause())
					{
						setMove(true);
						m=new Muovitore(gameManager.getBall(),LeftGamePanel.this,panel,gameManager);
						m.start();   
					}
					break;
				}
				}
			}

		});

		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				if(!isMove() && !isPause())
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
		g.drawLine(1*10, 1*10, 1*10, y*10);
		g.drawLine(1*10,y*10,x*10 ,y*10);
		g.drawLine(x*10, 1*10, x*10, y*10);
		g.drawLine(1*10,1*10,x*10,1*10);



		if(isPause())
		{
			g.drawImage(prov.getPause(),200,200,this);
		}

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

	public boolean isMove() {
		return move;
	}

	public void setMove(boolean move) {
		this.move = move;
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}


}
