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

import Objects.GameManager;
import Objects.Giratore;
import Objects.Muovitore;
import Objects.OfflineGameManager;
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
	private ImageProv prov;
	private int xMouse;
	private int yMouse;
	private boolean pause;
	private boolean sBoardActive;
	private boolean backFlag = false;
	private Muovitore m;
	private ScoreBoardMenu scoreboard;
	Giratore giratore;
	private RightGamePanel rightGamePanel;
	private PointsLabel pointsLabel;

	private MenuPanel menuPanel;

	public LeftGamePanel(GameManager manager, final RightGamePanel panel,
			final MenuPanel menu) throws IOException, FontFormatException {
		this.rightGamePanel = panel;
		setMenuPanel(menu);
		this.gameManager = manager;
		pointsLabel = new PointsLabel(gameManager, this);
		this.world = gameManager.getWorld();
		setPreferredSize(new Dimension(810, 800));
		x = world.getWidth();
		y = world.getHeight();
		gameManager.start();
		prov = new ImageProv();

		if (gameManager instanceof SinglePlayerGameManager) {
			setScoreboard(new ScoreBoardMenu(this, gameManager));
		}

		setOpaque(false);
		giratore = new Giratore(this, gameManager);
		giratore.start();

		addKeyListener(new KeyAdapter() {

			public void keyPressed(final KeyEvent e) {

				switch (e.getKeyCode()) {
				case KeyEvent.VK_ESCAPE: {
					GameFrame.switchTo(menu);
					break;
				}

				case KeyEvent.VK_LEFT: {
					if (!isMove() && !isPause())
						gameManager.getBall().moveLeft();
					break;
				}
				case KeyEvent.VK_RIGHT: {
					if (!isMove() && !isPause())
						gameManager.getBall().moveRight();
					break;
				}
				case KeyEvent.VK_SPACE: {
					gameManager.getBall().move();

					gameManager.update();

					break;
				}
				case KeyEvent.VK_UP: {
					gameManager.getHoles().get(0).move();
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
					if (gameManager.isFirstClick()) {
						gameManager.setFirstClick(false);

						gameManager.getTimer().init();
						panel.init();
					}
					if (!isMove() && !isPause()) {
						setMove(true);
						m = new Muovitore(gameManager.getBall(),
								LeftGamePanel.this, panel, gameManager);
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
					double m = (yMouse - gameManager.getBall().getY() * 10)
							/ (xMouse - gameManager.getBall().getX() * 10);
					double corner = Math.atan(m);
					gameManager.getBall().setCorner(
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

		if (gameManager.isGameOver()) 
		{
			if(gameManager instanceof SinglePlayerGameManager)
			{
				g.drawImage(prov.getGameOver(),50,50,this);
					pointsLabel.setVisible(true);
					try {
						if (pointsLabel.isSetted()) {
							gameManager.addPosition();
							pointsLabel.setVisible(false);
							exitToMenu();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
			}
			if(gameManager instanceof OfflineGameManager)
			{
				if(((OfflineGameManager) gameManager).getWinner()==0)
				{
					g.drawImage(prov.getFirstPlayerWinner() ,50, 50,this);
				}
				if(((OfflineGameManager) gameManager).getWinner()==1)
				{
					g.drawImage(prov.getSecondPlayerWinner(),50,50,this);
				}
			}
		}
		else
		{
			if(((gameManager.isLevelOver() || gameManager.isStart())))
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
					g.drawImage(prov.getPause(),200,200,this);

				g.drawLine(0 * 10, 0 * 10, 0 * 10, y * 10);
				g.drawLine(x * 10, 0 * 10, x * 10, y * 10);
				g.drawLine(0 * 10, 0 * 10, x * 10, 0 * 10);

				}


				if (!isMove())
				{

					float directionX = (gameManager.getBall().getX());
					float directionY = (gameManager.getBall().getY());

					float deltaX = gameManager.getBall().getDeltaX();
					int bal = 0;

					while (bal < 30) {
						AffineTransform at2 = new AffineTransform();
						if (directionX + deltaX <= 0
								|| directionX + deltaX >= world.getWidth())
							deltaX = -deltaX;

						at2.translate(
								(directionX + deltaX)
										* 10
										- prov.getDirectionBall()
												.getWidth(this) / 2,
								(directionY + gameManager.getBall().getDeltaY()) * 10);
						g2.drawImage(prov.getDirectionBall(), at2, this);
						directionX += (deltaX);
						directionY += (gameManager.getBall().getDeltaY());
						bal++;
					}
				}

				AffineTransform at1 = new AffineTransform();
				at1.translate(
						((gameManager.getBall().getX()) * 10)
								- prov.getBall(gameManager.getBall().getColor())
										.getWidth(this) / 2,
						((gameManager.getBall().getY()) * 10)
								- prov.getBall(gameManager.getBall().getColor())
										.getHeight(this) / 2);
				at1.scale(1, 1);
				g2.drawImage(prov.getBall(gameManager.getBall().getColor()),
						at1, this);

				for (int i = 0; i < gameManager.getHoles().size(); i++) {
					holeImage = prov.getHole(gameManager.getHoles().get(i)
							.getColor());
					AffineTransform at = new AffineTransform();
					at.translate((gameManager.getHoles().get(i).getX()) * 10,
							(gameManager.getHoles().get(i).getY()) * 10);
					at.rotate(Math.toRadians(gameManager.getHoles().get(i)
							.getAngle()));
					at.translate(-holeImage.getWidth(this) / 2,
							-holeImage.getHeight(this) / 2);
					g2.drawImage(holeImage, at, this);
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

	public MenuPanel getMenuPanel() {
		return menuPanel;
	}

	public void setMenuPanel(MenuPanel menuPanel) {
		this.menuPanel = menuPanel;
	}

	public ScoreBoardMenu getScoreboard() {
		return scoreboard;
	}

	public void setScoreboard(ScoreBoardMenu scoreboard) {
		this.scoreboard = scoreboard;
	}



	public void reset() {

		gameManager.setLevelOver(false);
		gameManager.getTimer().reset();
		rightGamePanel.resetTimerLabel();
		giratore = new Giratore(this, gameManager);
		giratore.start();
		gameManager.setFirstClick(true);
	}

	public void drawLevel(final Graphics g)
	{
		if(gameManager instanceof SinglePlayerGameManager)
		{
			g.drawImage(prov.getLevel(gameManager.getLevel()),300,200, this);
		}
		if(gameManager instanceof OfflineGameManager)
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
		if(((OfflineGameManager) gameManager).isFirstPlayer())
		{
			g.drawImage(prov.getFirstPlayer(),200,100,this);
		}
		if(((OfflineGameManager) gameManager).isSecondPlayer())
		{
			g.drawImage(prov.getSecondPlayer(),200,100,this);
		}
	}
}
