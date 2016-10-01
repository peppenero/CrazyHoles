package gui;

import java.awt.Image;
import java.awt.Toolkit;



public class ImageProv 
{
	private final Image greenHole;
	//private final Image bucaBlu;
	private final Image yellowHole;
	private final Image redHole;
	private final Image redBall;
	private final Image greemBall;
	private final Image blueBall;
	private final Image yellowBall;
	private final Image directionBall;
	private final Image pause;
	private final Image pauseS;
	private boolean pauseFlag = false;
	private final Toolkit tk;
	private final Image level1;
	private final Image level2;
	private final Image level3;
	private final Image level4;
	private final Image level5;
	private final Image level6;
	private final Image gameOver;
	private final Image firstPlayer;
	private final Image secondPlayer;
	private final Image firstPlayerWin;
	private final Image secondPlayerWin;
	
	public ImageProv()
	{
		 tk = Toolkit.getDefaultToolkit();
		
		yellowHole = tk.getImage("images/BucaGialla.png");
		greenHole = tk.getImage("images/BucaVerde.png");
		//bucaBlu = tk.getImage("images/BucaBlu.png");
		redHole = tk.getImage("images/BucaRossa.png");
		redBall = tk.getImage("images/PallaRossa.png");
		greemBall = tk.getImage("images/PallaVerde.png");
		yellowBall = tk.getImage("images/PallaGialla.png");
		blueBall = tk.getImage("images/PallaBlu.png");	
		directionBall = tk.getImage("images/da.png");
		pause = tk.getImage("images/PAUSE.png");
		pauseS = tk.getImage("images/PAUSE1.png");
		level1= tk.getImage("images/level1.png");
		level2= tk.getImage("images/level2.png");
		level3= tk.getImage("images/level3.png");
		level4= tk.getImage("images/level4.png");
		level5= tk.getImage("images/level5.png");
		level6= tk.getImage("images/level6.png");
		gameOver = tk.getImage("images/gameover.png");
		firstPlayer = tk.getImage("images/Player1.png");
		secondPlayer = tk.getImage("images/Player2.png");
		firstPlayerWin = tk.getImage("images/Player1winner.png");
		secondPlayerWin = tk.getImage("images/Player2winner.png");
	}
	
	public Image getHole(final String color)
	{
		switch(color)
		{
//			case "blu":
//			{
//				return bucaBlu;
//			}
			case "verde":
			{
				return greenHole;
			}
			case "giallo":
			{
				return yellowHole;
			}
			case "rosso":
			{
				return redHole;
			}
			default:
				break;
		}
		throw new IllegalArgumentException("unsupported hole color");
		
	}
	
	public Image getGameOver()
	{
		return gameOver;
	}
	
	public Image getBall(final String color)
	{
		switch(color)
		{
			case "verde":
			{
				return greemBall;
			}
			case "blu":
			{
				return blueBall;
			}
			case "giallo":
			{
				return yellowBall;
			}
			case "rosso":
			{
				return redBall;
			}
			default:
				break;
				
		}
		throw new IllegalArgumentException("unsupported ball color");
	}
	public Image getDirectionBall()
	{
		return this.directionBall;
	}

	public Image getPause() {
		
			return pause;
		
	}
	public Image getLevel(int l)
	{
		switch(l)
		{
		 case 1:
		 {
			 return level1;
		 }
		 case 2:
		 {
			return level2;
		 }
		 case 3:
		 {
			 return level3;
		 }
		 case 4:
		 {
			 return level4;
		 }
		 case 5:
		 {
			 return level5;
		 }
		 case 6:
		 {
			 return level6;
		 }
		 default:
			 break;
		}
		
		throw new IllegalArgumentException("unsupported level");
	}

	public Image getFirstPlayer() {
		return firstPlayer;
	}

	public Image getSecondPlayer() {
		return secondPlayer;
	}

	public Image getFirstPlayerWinner() {
		return firstPlayerWin;
	}

	public Image getSecondPlayerWinner() {
		return secondPlayerWin;
	}
	
}











