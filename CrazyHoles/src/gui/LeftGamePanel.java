package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.swing.JPanel;

import Objects.FreePracticeGameManager;
import Objects.GameManager;
import Objects.Giratore;
import Objects.Muovitore;
import Objects.OfflineGameManager;
import Objects.OnlineGameManager;
import Objects.SinglePlayerGameManager;
import Objects.World;

public class LeftGamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private World world;
	private int x;
	private int y;
	private boolean move = false;
	private GameManager gameManager;
	private Image holeImage;
	private int xMouse;
	private int yMouse;
	private boolean pause;
	private boolean sBoardActive;
	private boolean backFlag = false;
	private Muovitore m;
	private Giratore giratore;
	private RightGamePanel rightGamePanel;
	private PointsLabel pointsLabel;
	
	
	private JPanel menuPanel;

	public LeftGamePanel(GameManager manager, final RightGamePanel panel,
			final JPanel menu) throws IOException, FontFormatException {
		this.rightGamePanel = panel;
		setMenuPanel(menu);
		this.setGameManager(manager);
		pointsLabel = new PointsLabel(getGameManager(), this);
		this.world = getGameManager().getWorld();
		setPreferredSize(new Dimension(810, 800));
		x = world.getWidth();
		y = world.getHeight();
		getGameManager().start();

		setOpaque(false);
		giratore = new Giratore(this, getGameManager());
		giratore.start();

		addKeyListener(new KeyAdapter() {

			public void keyPressed(final KeyEvent e) {

				switch (e.getKeyCode()) {
				case KeyEvent.VK_ESCAPE: {
					if(gameManager instanceof OnlineGameManager)
					{
					
						if(((OnlineGameManager) gameManager).isServer())
						{
							if(!((OnlineGameManager) gameManager).isFinish())
							{
								try {
									((OnlineGameManager) gameManager).getHost().send("leave");
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							try {
								((OnlineGameManager) gameManager).getHost().ends();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							GameFrame.switchTo(LeftGamePanel.this.getMenuPanel());
						}
						else
						{
							if(!((OnlineGameManager) gameManager).isFinish())
							{
								try {
									((OnlineGameManager) gameManager).getClient().send("leave");
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							try {
								((OnlineGameManager) gameManager).getClient().ends();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							GameFrame.switchTo(LeftGamePanel.this.getMenuPanel());
						}
					}
					else
					{
						GameFrame.switchTo(menu);
					}
					
					break;
				}

				case KeyEvent.VK_LEFT: {
					if (!isMove() && !isPause())
						getGameManager().getBall().moveLeft();
					break;
				}
				case KeyEvent.VK_RIGHT: {
					if (!isMove() && !isPause())
						getGameManager().getBall().moveRight();
					break;
				}
				}
				repaint();
			}
		});

		addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				switch (e.getButton()) {
				case MouseEvent.BUTTON1: {
					if (getGameManager().isFirstClick()) {
						getGameManager().setFirstClick(false);

						getGameManager().getTimer().init();
						panel.init();
					}
					if (!isMove() && !isPause() && !getGameManager().isGameOver()) {
						setMove(true);
						m = new Muovitore(getGameManager().getBall(),
								LeftGamePanel.this, panel, getGameManager());
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
				if (!isMove() && !isPause()) {
					xMouse = e.getX();
					yMouse = e.getY();
					double m = (yMouse - getGameManager().getBall().getY() * 10)
							/ (xMouse - getGameManager().getBall().getX() * 10);
					double corner = Math.atan(m);
					getGameManager().getBall().setCorner(
							(float) (Math.toDegrees((corner)) - 360) % 180);
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
	 protected void paintComponent(Graphics g) {
	  super.paintComponent(g);

	  Graphics2D g2 = (Graphics2D) g;
	  boolean check = false;
	  
	  if(getGameManager() instanceof OnlineGameManager) 
	  {
		  check = false;
		  if(((OnlineGameManager) getGameManager()).isServer())
		  {
			  if(!((OnlineGameManager) getGameManager()).getHost().isAccepted())
			  {
				  g.drawImage(ImageProv.getIstance().getWaiting(),100,100,this);
				  check=true;
			  }
			  if(gameManager instanceof OnlineGameManager && ((OnlineGameManager) gameManager).getHost().isOpponentLeave())
			  {
				  check=true;
				  g.drawImage(ImageProv.getIstance().getLeave(), 200,200,this);
				  g.drawImage(ImageProv.getIstance().getEsc(),300,400,this);
			  }
		  }
		  else
		  {
			  if(gameManager instanceof OnlineGameManager && ((OnlineGameManager) gameManager).getClient().isOppenentLeave())
			  {
				  check=true;
				  g.drawImage(ImageProv.getIstance().getLeave(), 200,200,this);
				  g.drawImage(ImageProv.getIstance().getEsc(),300,400,this);
			  }
		  
		  }
	  }
	 if(!check)
	  {
		 
		  if (getGameManager().isGameOver()) 
		  {
		   if(getGameManager() instanceof SinglePlayerGameManager)
		   {
		    g.drawImage(ImageProv.getIstance().getGameOver(),50,50,this);
		     pointsLabel.setVisible(true);
		     if (pointsLabel.isSetted()) {
		      pointsLabel.setVisible(false);
		      exitToMenu();
		     } 
		   }
		   
		   if(gameManager instanceof FreePracticeGameManager)
		   {
		    g.drawImage(ImageProv.getIstance().getEnds(),100,100,this);
		    g.drawImage(ImageProv.getIstance().getEsc(), 100,200,this);
		   }
		   
		   if(getGameManager() instanceof OfflineGameManager)
		   {
		    if(((OfflineGameManager) getGameManager()).getWinner()==0)
		    {
		     g.drawImage(ImageProv.getIstance().getFirstPlayerWinner() ,50, 50,this);
		    }
		    if(((OfflineGameManager) getGameManager()).getWinner()==1)
		    {
		     g.drawImage(ImageProv.getIstance().getSecondPlayerWinner(),50,50,this);
		    }
		   }
		   if(getGameManager() instanceof OnlineGameManager)
		   {
		    System.out.println(((OnlineGameManager) getGameManager()).getOpponentPoints());
		    if(!((OnlineGameManager) getGameManager()).isOpponentEnds())
		    {
		     g.drawImage(ImageProv.getIstance().getWaiting(), 100,100,this);
		    }
		    else
		    {
		     if(((OnlineGameManager) getGameManager()).getWinner().equals("pari"))
		     {
		      g.drawImage(ImageProv.getIstance().getTie(), 100,100,this);
		      rightGamePanel.onlineRefresh();
		      g.drawImage(ImageProv.getIstance().getEsc(), 100,200,this);
		     }
		     else
		     {
		      if(((OnlineGameManager) getGameManager()).isServer())
		      {
		                                 
		        if( ((OnlineGameManager) getGameManager()).getWinner().equals("host"))
		        {
		         g.drawImage(ImageProv.getIstance().getYouWin(), 100,100,this);
		         rightGamePanel.onlineRefresh();
		         g.drawImage(ImageProv.getIstance().getEsc(), 100,200,this);
		        }
		        else
		        {
		         g.drawImage(ImageProv.getIstance().getYouLose(), 100,100,this);
		         rightGamePanel.onlineRefresh();
		         g.drawImage(ImageProv.getIstance().getEsc(), 100,200,this);
		        }
		      }
		      else
		      {
		       System.out.println(((OnlineGameManager) getGameManager()).getWinner());
		       if(((OnlineGameManager) getGameManager()).getWinner().equals("host"))
		       {
		        g.drawImage(ImageProv.getIstance().getYouLose(),100,100,this);
		        rightGamePanel.onlineRefresh();
		        g.drawImage(ImageProv.getIstance().getEsc(), 100,200,this);
		       }
		       else
		       {
		        g.drawImage(ImageProv.getIstance().getYouWin(),100,100,this);
		        rightGamePanel.onlineRefresh(); 
		        g.drawImage(ImageProv.getIstance().getEsc(), 100,200,this);
		       }
		      }
		     }
		     
		     
		    }
		   }
		  }
		  else
		  {
		   if(((getGameManager().isLevelOver() || getGameManager().isStart())))
		    {
		     rightGamePanel.pause();
		     drawLevel(g);
		    }
		   else {
	
		    g.setColor(Color.white);
	
		    g.drawLine(0*10, 0*10, 0*10, y*10);
		   
		    g.drawLine(x*10, 0*10, x*10, y*10);
		    g.drawLine(0*10,0*10,x*10,0*10);
		   
		    if(isPause() && !isBoardActive())
		    {
		     g.drawImage(ImageProv.getIstance().getPause(),200,200,this);
	
		    g.drawLine(0 * 10, 0 * 10, 0 * 10, y * 10);
		    g.drawLine(x * 10, 0 * 10, x * 10, y * 10);
		    g.drawLine(0 * 10, 0 * 10, x * 10, 0 * 10);
	
		    }
	
	
		    if (!isMove())
		    {
	
		     float directionX = (getGameManager().getBall().getX());
		     float directionY = (getGameManager().getBall().getY());
	
		     float deltaX = getGameManager().getBall().getDeltaX();
		     int bal = 0;
	
		     while (bal < 30) {
		      AffineTransform at2 = new AffineTransform();
		      if (directionX + deltaX <= 0
		        || directionX + deltaX >= world.getWidth())
		       deltaX = -deltaX;
	
		      at2.translate(
		 (directionX + deltaX)
		          * 10
		          - ImageProv.getIstance().getDirectionBall()
		            .getWidth(this) / 2,
		        (directionY + getGameManager().getBall().getDeltaY()) * 10);
		      g2.drawImage(ImageProv.getIstance().getDirectionBall(), at2, this);
		      directionX += (deltaX);
		      directionY += (getGameManager().getBall().getDeltaY());
		      bal++;
		     }
		    }
	
		    AffineTransform at1 = new AffineTransform();
		    at1.translate(
		      ((getGameManager().getBall().getX()) * 10)
		        - ImageProv.getIstance().getBall(getGameManager().getBall().getColor())
		          .getWidth(this) / 2,
		      ((getGameManager().getBall().getY()) * 10)
		        - ImageProv.getIstance().getBall(getGameManager().getBall().getColor())
		          .getHeight(this) / 2);
		    at1.scale(1, 1);
		    g2.drawImage(ImageProv.getIstance().getBall(getGameManager().getBall().getColor()),
		      at1, this);
	
		    for (int i = 0; i < getGameManager().getHoles().size(); i++) {
		     holeImage = ImageProv.getIstance().getHole(getGameManager().getHoles().get(i)
		       .getColor());
		     AffineTransform at = new AffineTransform();
		     at.translate((getGameManager().getHoles().get(i).getX()) * 10,
		       (getGameManager().getHoles().get(i).getY()) * 10);
		     at.rotate(Math.toRadians(getGameManager().getHoles().get(i)
		       .getAngle()));
		     at.translate(-holeImage.getWidth(this) / 2,
		       -holeImage.getHeight(this) / 2);
		     g2.drawImage(holeImage, at, this);
		    }
		   }
		  }
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

	// VIENE RICHIAMATA DAL RIGHTPANEL PER TORNARE AL MENU
	public void exitToMenu() {
		GameFrame.switchTo(getMenuPanel());
	}

	public boolean isBoardActive() {
		return sBoardActive;
	}

	public void setsBoardActive(boolean sBoardActive) {
		this.sBoardActive = sBoardActive;
	}

	public boolean isBackFlag() {
		return backFlag;
	}

	public void setBackFlag(boolean backFlag) {
		this.backFlag = backFlag;
	}

	public JPanel getMenuPanel() {
		return menuPanel;
	}

	public void setMenuPanel(JPanel menuPanel) {
		this.menuPanel = menuPanel;
	}



	public void reset() {

		getGameManager().setLevelOver(false);
		getGameManager().getTimer().reset();
		rightGamePanel.resetTimerLabel();
		giratore = new Giratore(this, getGameManager());
		giratore.start();
		getGameManager().setFirstClick(true);
	}

	public void drawLevel(final Graphics g)
	{
		if(getGameManager() instanceof SinglePlayerGameManager)
		{
			g.drawImage(ImageProv.getIstance().getLevel(getGameManager().getLevel()),300,200, this);
		}
		if(getGameManager() instanceof OfflineGameManager)
		{
			drawPlayer(g);
		}
	}


	public RightGamePanel getPanel() {
		return rightGamePanel;
	}

	public void setPanel(RightGamePanel panel) {
		this.rightGamePanel = panel;
	}
	
	protected void drawPlayer(Graphics g)
	{
		if(((OfflineGameManager) getGameManager()).isFirstPlayer())
		{
			g.drawImage(ImageProv.getIstance().getFirstPlayer(),200,100,this);
		}
		if(((OfflineGameManager) getGameManager()).isSecondPlayer())
		{
			g.drawImage(ImageProv.getIstance().getSecondPlayer(),200,100,this);
		}
	}

	public GameManager getGameManager() {
		return gameManager;
	}

	public void setGameManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}
	
	
}
