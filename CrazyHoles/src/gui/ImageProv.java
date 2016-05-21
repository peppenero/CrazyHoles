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
	
	
	public ImageProv()
	{
		final Toolkit tk = Toolkit.getDefaultToolkit();
		
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
	
}











